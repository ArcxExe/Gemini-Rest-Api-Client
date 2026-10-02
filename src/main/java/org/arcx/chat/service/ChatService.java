package org.arcx.chat.service;

import org.arcx.chat.dto.*;
import org.arcx.chat.entity.ChatEntity;
import org.arcx.chat.entity.MessageEntity;
import org.arcx.chat.repository.ChatRepository;
import org.arcx.chat.repository.MessageRepository;
import org.arcx.gemini.integration.dto.GeminiResponse;
import org.arcx.gemini.integration.service.GeminiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class ChatService {
    private final static Logger log = LoggerFactory.getLogger(ChatService.class);
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private final GeminiService geminiService;

    public ChatService(ChatRepository chatRepository, MessageRepository messageRepository, GeminiService geminiService) {
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
        this.geminiService = geminiService;
    }

    public CreateChatResponse createChat(CreateChatRequest request) {
        log.info("Create chat: {}" , request);
        ChatEntity chat = new ChatEntity();
        chat.setTitle(request.title());
        chatRepository.save(chat);
        log.info("Chat created success. This chat save in repository: {}" , chat);

        return new CreateChatResponse(chat.getId());
    }

    @Transactional
    public MessageResponse sendMessage(MessageRequest request, UUID uuid) {
        ChatEntity chat = chatRepository.findById(uuid).orElseThrow(()  -> {
            log.error("Chat not found by id {}" , uuid );
            return new RuntimeException("Chat not found");
        });
        MessageEntity message = new MessageEntity();
        message.setChat(chat);
        message.setRole("USER");
        message.setContent(request.text());
        messageRepository.save(message); // user

        log.info("Save user message: {}", message);
        GeminiResponse geminiAnswer = geminiService.ask(request.text());
        MessageEntity geminiMessage = new MessageEntity();
        geminiMessage.setChat(chat);
        geminiMessage.setRole("MODEL");
        geminiMessage.setContent(geminiAnswer.getText());
        messageRepository.save(geminiMessage); // Gemini Answer
        log.info("Save Model answer: {}", message);

        return new MessageResponse(geminiAnswer.getText() , geminiAnswer.usageMetadata().totalTokenCount());
    }

    // Так как просто получаем данные
    @Transactional(readOnly = true)
    public List<ChatDto> getChats() {
        log.info("Get all chat");
        return chatRepository.findAll()
                .stream()
                .map(chat -> new ChatDto(chat.getTitle() , chat.getId()))
                .toList();
    }
}

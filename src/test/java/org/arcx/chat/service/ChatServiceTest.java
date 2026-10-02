package org.arcx.chat.service;

import org.arcx.chat.repository.ChatRepository;
import org.arcx.chat.repository.MessageRepository;
import org.arcx.gemini.integration.service.GeminiService;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ChatServiceTest {

    private final ChatRepository chatRepository = mock(ChatRepository.class);
    private final MessageRepository messageRepository = mock(MessageRepository.class);
    private final GeminiService geminiService = mock(GeminiService.class);
    private final ChatService chatService = new ChatService(chatRepository, messageRepository, geminiService);

    @Test
    public void createChatTest() {

    }

    @Test
    public void getChatsTest() {

    }

    @Test
    public void sendMessageTest() {

    }
}

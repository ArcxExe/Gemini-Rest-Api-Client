package org.arcx.chat.controller;

import org.arcx.chat.dto.*;
import org.arcx.chat.service.ChatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api")
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/chat")
    public CreateChatResponse createChat(@RequestBody CreateChatRequest request) {
        return chatService.createChat(request);
    }

    @GetMapping("/chat")
    public List<ChatDto> listChat() {
        return chatService.getChats();
    }

    @PostMapping("/chat/{chatId}/messages")
    public MessageResponse sendMessage(@RequestBody MessageRequest request ,
                                       @PathVariable("chatId") UUID chatId) {
        return chatService.sendMessage(request , chatId);

    }
}

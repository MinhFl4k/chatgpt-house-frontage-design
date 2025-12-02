package com.springboot.chatgpt.controller;

import com.springboot.chatgpt.dto.PromptRequest;
import com.springboot.chatgpt.service.ChatGPTService;
import com.springboot.chatgpt.service.ImageChatGPTService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/chat")
public class ChatGPTController {

    private final ChatGPTService chatGPTService;
    private final ImageChatGPTService imageChatGPTService;

    public ChatGPTController(ChatGPTService chatGPTService, ImageChatGPTService imageChatGPTService){
        this.chatGPTService = chatGPTService;
        this.imageChatGPTService = imageChatGPTService;
    }

    @PostMapping("/text")
    public String chat(@RequestBody PromptRequest promptRequest){
        return chatGPTService.getChatResponse(promptRequest);
    }

    @PostMapping("/image")
    public String generateImage(@RequestBody PromptRequest promptRequest) {
        return imageChatGPTService.generateImage(promptRequest.prompt());
    }
}

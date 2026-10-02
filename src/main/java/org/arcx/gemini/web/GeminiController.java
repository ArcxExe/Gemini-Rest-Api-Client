package org.arcx.gemini.web;

import org.arcx.gemini.integration.service.GeminiService;
import org.arcx.gemini.web.dto.AnswerResponse;
import org.arcx.gemini.web.dto.PromptRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class GeminiController {
    
    private final GeminiService geminiService;
    
    public GeminiController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @PostMapping("/request")
    public AnswerResponse requestForApi(@RequestBody PromptRequest prompt) {
        return new AnswerResponse(geminiService.ask(prompt.prompt()).getText());

    }
    
}

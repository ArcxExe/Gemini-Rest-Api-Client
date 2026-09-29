package org.arcx.gemini.service;

import org.arcx.gemini.dto.GeminiRequest;
import org.arcx.gemini.dto.GeminiResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.nio.charset.StandardCharsets;

@Service
public class GeminiService {
    private final RestClient restClient;
    private final String model;

    public GeminiService(
           @Qualifier("geminiRestClient") RestClient geminiRestClient,
            @Value("${gemini.model}") String model
    ) {
        this.model  = model;
        this.restClient = geminiRestClient;
    }

    public String ask(String prompt){
        GeminiRequest request = GeminiRequest.ofText(prompt);
        GeminiResponse response = restClient.post()
                .uri("/models/{model}:generateContent",model)
                .body(request)
                .retrieve() //отправка запроса
                .onStatus(HttpStatusCode::isError , (request1, response1) -> {
                    String errors = new String(response1.getBody().readAllBytes(), StandardCharsets.UTF_8);
                    throw new RuntimeException("Gemini Api ["+response1.getStatusCode() + "]: " + errors);
                }) // Обработка ошибок , если они есть
                .body(GeminiResponse.class);
        return response != null ? response.getText() : "";
    }
}
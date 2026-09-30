package org.arcx.gemini.integration.service;

import org.arcx.gemini.integration.dto.GeminiRequest;
import org.arcx.gemini.integration.dto.GeminiResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.charset.StandardCharsets;

@Service
public class GeminiService {
    private final static Logger log = LoggerFactory.getLogger(GeminiService.class);
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
        log.info("Отправка запроса к Gemini Api: model: {} " , model);
        log.debug("Prompt: {}", prompt);
        GeminiRequest request = GeminiRequest.ofText(prompt);
        GeminiResponse response = restClient.post()
                .uri("/models/{model}:generateContent",model)
                .body(request)
                .retrieve() //отправка запроса
                .onStatus(HttpStatusCode::isError , (request1, response1) -> {
                    String errors = new String(response1.getBody().readAllBytes(), StandardCharsets.UTF_8);
                    log.error("Сбой Gemini Api: StatusCode: {} , Error: {}" , response1.getStatusCode() , errors );
                    throw new RuntimeException("Gemini Api ["+response1.getStatusCode() + "]: " + errors);
                }) // Обработка ошибок , если они есть
                .body(GeminiResponse.class);
        log.info("Успешный запрос к Gemini API");
        return response != null ? response.getText() : "";
    }
}
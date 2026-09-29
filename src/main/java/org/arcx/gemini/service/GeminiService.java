package org.arcx.gemini.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClient;

public class GeminiService {

    private final RestClient;
    private final String model;


    public GeminiService(
            @Value("${gemini.base-url}") String baseUrl,
            @Value("${gemini.apiKey}") String apiKey,
            @Value("${gemini.model}") String model
    ) {

    }
}

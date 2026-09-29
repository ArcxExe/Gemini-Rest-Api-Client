package org.arcx.gemini.dto;


import java.util.List;

public record GeminiRequest(List<Content> contents) {
    public static GeminiRequest ofText(String text) {
        return new GeminiRequest(List.of(new Content(List.of(new Parts(text)), null)));
    }
}



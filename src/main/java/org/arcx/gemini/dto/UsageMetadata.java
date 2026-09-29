package org.arcx.gemini.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UsageMetadata( int promptTokenCount, int candidatesTokenCount, int totalTokenCount) {}

package org.arcx.gemini.integration.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UsageMetadata( int promptTokenCount, int candidatesTokenCount, int totalTokenCount) {}

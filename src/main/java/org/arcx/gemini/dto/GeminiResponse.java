package org.arcx.gemini.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record GeminiResponse(List<Candidates> candidates , UsageMetadata usageMetadata ) {
    public String getText() {
        if (candidates != null && !candidates.isEmpty()) {
            var c = candidates.getFirst();
            if (c.content() != null){
                return c.content().parts().getFirst().text();
            }
        }
        return "";
    }
}


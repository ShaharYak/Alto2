package com.example.altoexrc2.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class LanguageResponse {

    @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }
}

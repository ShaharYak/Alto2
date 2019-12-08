package com.example.altoexrc2.models.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryResponse {
    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("html_url")
    private String htmlUrl;

    @JsonProperty("description")
    private String description;

    @JsonProperty("forks")
    private int forks;

    @JsonProperty("private")
    private boolean isPrivate;

    public RepositoryResponse(String fullName, String htmlUrl, String description, int forks, boolean isPrivate) {
        this.fullName = fullName;
        this.htmlUrl = htmlUrl;
        this.description = description;
        this.forks = forks;
        this.isPrivate = isPrivate;
    }
}

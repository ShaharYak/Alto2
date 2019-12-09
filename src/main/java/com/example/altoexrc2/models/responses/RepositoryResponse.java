package com.example.altoexrc2.models.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryResponse {
    public static final String FULL_NAME = "full_name";
    public static final String HTML_URL = "html_url";
    public static final String DESCRIPTION = "description";
    public static final String FORKS = "forks";
    public static final String PRIVATE = "private";

    @JsonProperty(FULL_NAME)
    private String fullName;

    @JsonProperty(HTML_URL)
    private String htmlUrl;

    @JsonProperty(DESCRIPTION)
    private String description;

    @JsonProperty(FORKS)
    private int forks;

    @JsonProperty(PRIVATE)
    private boolean isPrivate;

    public RepositoryResponse(String fullName, String htmlUrl, String description, int forks, boolean isPrivate) {
        this.fullName = fullName;
        this.htmlUrl = htmlUrl;
        this.description = description;
        this.forks = forks;
        this.isPrivate = isPrivate;
    }
}

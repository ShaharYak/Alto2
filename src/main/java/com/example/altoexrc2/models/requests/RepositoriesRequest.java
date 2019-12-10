package com.example.altoexrc2.models.requests;

// CR: Although not specified in the task, why not allow search by name?
// CR: Don't you think that using only these two parameters is not enough?
public class RepositoriesRequest {
    private int minStarsCount;
    private String language;

    // CR: What if this is set to a negative number, how would the request be handled?
    public int getMinStarsCount() {
        return minStarsCount;
    }

    public String getLanguage() {
        return language;
    }
}

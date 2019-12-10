package com.example.altoexrc2.resources;

import com.example.altoexrc2.models.requests.RepositoriesRequest;
import com.example.altoexrc2.models.responses.LanguageResponse;
import com.example.altoexrc2.models.responses.RepositoryResponse;
import com.example.altoexrc2.services.RepositoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class RepositoriesResource {

    @Autowired
    private RepositoriesService repositoriesService;

    @PostMapping("/search")
    public List<RepositoryResponse> searchRepositories(@RequestBody RepositoriesRequest request) {
        return repositoriesService.searchRepositories(request);
    }

    @GetMapping("/languages")
    public List<String> searchRepositories() {
        return repositoriesService.getAllLanguage();
    }

    // CR: How can I search filter by issues count as required by the task?
}

package com.example.altoexrc2.services;

import com.example.altoexrc2.models.requests.RepositoriesRequest;
import com.example.altoexrc2.models.requests.RequestMethod;
import com.example.altoexrc2.models.responses.LanguageResponse;
import com.example.altoexrc2.models.responses.RepositoryResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.altoexrc2.models.responses.RepositoryResponse.*;

@Service
public class RepositoriesService {
    private  static ObjectMapper mapper = new ObjectMapper();

    public List<String> getAllLanguage() {
        HttpURLConnection conn = null;
        InputStream inputStream = null;

        try {
            String queryUrl = "https://api.github.com/languages";
            conn = createConnection(queryUrl, RequestMethod.GET);
            inputStream = conn.getInputStream();

            return mapper.readValue(inputStream, new TypeReference<List<LanguageResponse>>() {})
                    .stream().map(LanguageResponse::getName).collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println("error closing input stream");
                }
            }
            if (conn != null) conn.disconnect();
        }

        return new ArrayList<>();
    }

    public List<RepositoryResponse> searchRepositories(RepositoriesRequest request) {
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        List<RepositoryResponse> lstToReturn = new ArrayList<>();

        try {
            String queryUrl =
                    String.format("https://api.github.com/search/repositories?q=stars:>=%d+language:%s+pushed:2019-06-10+license:mit&order=desc",
                            request.getMinStarsCount(), request.getLanguage());

            conn = createConnection(queryUrl, RequestMethod.GET);
            inputStream = conn.getInputStream();
            JSONObject jsonObject = new JSONObject(IOUtils.toString(inputStream));

            jsonObject.getJSONArray("items").forEach(item -> lstToReturn.add(createRepositoryResponse((JSONObject) item)));

            return lstToReturn;

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println("error closing input stream");
                }
            }
            if (conn != null) conn.disconnect();
        }

        return lstToReturn;
    }

    private RepositoryResponse createRepositoryResponse(JSONObject item) {
        return new RepositoryResponse(item.optString(FULL_NAME),
                                      item.optString(HTML_URL),
                                      item.optString(DESCRIPTION),
                                      item.optInt(FORKS),
                                      item.optBoolean(PRIVATE));
    }

    private HttpURLConnection createConnection(String urlQuery, RequestMethod requestMethod) throws IOException {
        URL url = new URL(urlQuery);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(requestMethod.toString());
        conn.setRequestProperty("Accept", "application/vnd.github.mercy-preview+json");

        return conn;
    }
}

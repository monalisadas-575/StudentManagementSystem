package com.lisa.studentmanagementsystem.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lisa.studentmanagementsystem.configuration.MyHttpEnvironmentConfiguration;
import com.lisa.studentmanagementsystem.dto.request.BookIssueRequestDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Component
public class MyRestClientImpl  implements  MyRestClient{
    public final RestClient restClient;
    public final ObjectMapper objectMapper;
    public final MyHttpEnvironmentConfiguration myHttpEnvironmentConfiguration;

    public MyRestClientImpl(RestClient restClient, ObjectMapper objectMapper, MyHttpEnvironmentConfiguration myHttpEnvironmentConfiguration) {
        this.restClient = restClient;
        this.objectMapper = objectMapper;
        this.myHttpEnvironmentConfiguration = myHttpEnvironmentConfiguration;
    }

    @Override
    public void issueBooks(String bookName, String category, Integer count, BookIssueRequestDTO bookIssueRequestDTO) {
        Map<String ,Object> pathValues= Map.of(
                "category",category
                ,"name",bookName
        );
        restClient.post()
                .uri(UriComponentsBuilder.fromUriString(myHttpEnvironmentConfiguration.getBookIssueURI())
                        .queryParam("count",count)
                        .uriVariables(pathValues)
                        .build().toUri())
                .body(bookIssueRequestDTO)
                .retrieve()
                .toBodilessEntity();

    }
}

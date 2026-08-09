package com.lisa.myspringbootwithjpa.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Configuration
@Getter
@Setter
public class MyHttpEnvironmentConfiguration {

    @Value("${library.book.issue.uri}")
    public String bookIssueURI;

    @Bean
    public RestClient client(){
        return RestClient.create();
    }
}

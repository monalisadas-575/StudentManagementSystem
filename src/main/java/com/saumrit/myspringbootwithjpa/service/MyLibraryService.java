package com.saumrit.myspringbootwithjpa.service;

import com.saumrit.myspringbootwithjpa.client.MyRestClient;
import com.saumrit.myspringbootwithjpa.dto.request.BookIssueRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MyLibraryService {
    public final Logger logger= LoggerFactory.getLogger(MyLibraryService.class);
    public final MyRestClient restClient;

    public MyLibraryService(MyRestClient restClient) {
        this.restClient = restClient;
    }

    public void issueBook(String bookName, String category, Integer count, BookIssueRequestDTO bookIssueRequestDTO){
        logger.info("Started Book issue For Student with id {}",bookIssueRequestDTO.personId());
        restClient.issueBooks(bookName,category,count,bookIssueRequestDTO);
        logger.info("Successfully Book issued For Student with id {}",bookIssueRequestDTO.personId());
    }
}

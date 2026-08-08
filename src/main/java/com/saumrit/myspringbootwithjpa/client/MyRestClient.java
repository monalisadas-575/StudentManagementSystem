package com.saumrit.myspringbootwithjpa.client;

import com.saumrit.myspringbootwithjpa.dto.request.BookIssueRequestDTO;

public interface MyRestClient {

    public void issueBooks(String bookName, String category, Integer count, BookIssueRequestDTO bookIssueRequestDTO);
    //public void returnBooks();
}

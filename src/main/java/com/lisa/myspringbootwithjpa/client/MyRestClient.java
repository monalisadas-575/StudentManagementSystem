package com.lisa.myspringbootwithjpa.client;

import com.lisa.myspringbootwithjpa.dto.request.BookIssueRequestDTO;

public interface MyRestClient {

    public void issueBooks(String bookName, String category, Integer count, BookIssueRequestDTO bookIssueRequestDTO);
    //public void returnBooks();
}

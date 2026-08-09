package com.lisa.studentmanagementsystem.client;

import com.lisa.studentmanagementsystem.dto.request.BookIssueRequestDTO;

public interface MyRestClient {

    public void issueBooks(String bookName, String category, Integer count, BookIssueRequestDTO bookIssueRequestDTO);
    //public void returnBooks();
}

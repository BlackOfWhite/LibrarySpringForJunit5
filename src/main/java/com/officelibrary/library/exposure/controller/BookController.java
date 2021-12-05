package com.officelibrary.library.exposure.controller;

import com.officelibrary.library.exposure.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private static final String template = "Hello, %s!";

    @GetMapping("/book")
    public Book book(@RequestParam(value = "title", defaultValue = "Clean Code") String title) {
        return new Book("Clean Code", "Martin", "description");
    }
}

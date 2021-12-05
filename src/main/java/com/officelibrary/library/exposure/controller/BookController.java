package com.officelibrary.library.exposure.controller;

import java.util.List;

import com.officelibrary.library.exposure.model.Book;
import com.officelibrary.library.exposure.service.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/library")
public class BookController {

    private final LibraryService libraryService;

    public BookController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/books")
    public List<Book> book() {
        return libraryService.getBooks();
    }

    @GetMapping("/book")
    public ResponseEntity<Book> book(@RequestParam(value = "title", defaultValue = "Clean Code") String title) {
        return libraryService.getBookByTitle(title).isPresent() ?
            new ResponseEntity<>(libraryService.getBookByTitle(title).get(), HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/book")
    public void deleteBook(@RequestBody Book book) {
        libraryService.deleteBook(book);
    }

    @PostMapping("/add-book")
    public ResponseEntity<Book> createTutorial(@RequestBody Book book) {
        try {
            libraryService.addBook(book);
            return new ResponseEntity<>(book, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

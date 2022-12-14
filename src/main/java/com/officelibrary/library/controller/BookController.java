package com.officelibrary.library.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.officelibrary.library.model.Book;
import com.officelibrary.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//@RestController
@Controller
@ResponseBody
@RequestMapping("/library")
public class BookController {

    private LibraryService libraryService;

    @Autowired
    public BookController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @RequestMapping("/books")
    public List<Book> listBooks() {
        return libraryService.getBooks();
    }

    @GetMapping("/booksWithParam")
    public ResponseEntity<?> getBook(@RequestParam(value = "author") String author,
                                     @RequestParam(value = "order", defaultValue = "ASC") String order) {
        List<Book> books = libraryService.getBooksByAuthor(author);
        if (books.isEmpty()) {
            return new ResponseEntity<>("ERROR", HttpStatus.NOT_FOUND);
        }
        if ("ASC".equals(order)) {
            return new ResponseEntity<>(books.stream().sorted(Comparator.comparing(Book::getTitle)).collect(Collectors.toList()),
                HttpStatus.OK);
        } else {
            return new ResponseEntity<>(books.stream().sorted(Comparator.comparing(Book::getTitle).reversed()).collect(Collectors.toList()),
                HttpStatus.OK);
        }

    }

    @GetMapping("/booksNoCheck/{id}")
    public Book getBookNoCheck(@PathVariable("id") int id) {
        return libraryService.getBookById(id).get();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int id) {
        return libraryService.getBookById(id).isPresent() ?
            new ResponseEntity<>(libraryService.getBookById(id).get(), HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/books")
    public void deleteBook(@RequestBody Book book) {
        libraryService.deleteBook(book);
    }

    public void deleteBook(@PathVariable("id") int id) {
        libraryService.deleteBookById(id);
    }

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        try {
            libraryService.addBook(book);
            return new ResponseEntity<>(book, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") int id, @RequestBody Book book) {
        try {
            libraryService.updateBook(id, book);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

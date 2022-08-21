package com.officelibrary.library.exposure.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.officelibrary.library.exposure.model.Book;
import com.officelibrary.library.qualifer.FormatterService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {

    private LibraryRepository libraryRepository;

    private FormatterService formatterService;

    private LocalDate localDate;

    public LibraryService(LibraryRepository libraryRepository, @Qualifier("xml") FormatterService formatterService) {
        this.libraryRepository = libraryRepository;
        this.formatterService = formatterService;
        this.localDate = LocalDate.now();
    }

    public List<Book> addBook(Book book) {
        formatterService.format(book.getDescription());
        return libraryRepository.addBook(book);
    }

    public List<Book> getBooks() {
        return libraryRepository.getBooks();
    }

    public List<Book> getBooksByAuthor(String author) {
        return libraryRepository.getBookByAuthor(author);
    }

    public Optional<Book> getBookById(int id) {
        return libraryRepository.getBookById(id);
    }

    public List<Book> deleteBook(Book book) {
        return libraryRepository.deleteBook(book);
    }

    public List<Book> deleteBookById(int id) {
        return deleteBookById(id);
    }


    public void deleteAll() {
        libraryRepository.deleteAll();
    }

    public void updateBook(int id, Book newBook) {
        libraryRepository.updateBook(id, newBook);
    }
}

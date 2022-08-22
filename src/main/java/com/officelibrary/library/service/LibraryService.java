package com.officelibrary.library.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.officelibrary.library.domain.AuthorDomain;
import com.officelibrary.library.domain.BookDomain;
import com.officelibrary.library.model.Book;
import com.officelibrary.library.qualifer.FormatterService;
import com.officelibrary.library.repository.LibraryRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
//@Slf4j
public class LibraryService {

    private static final Logger logger = LogManager.getLogger(LibraryService.class);

    private LibraryRepository libraryRepository;

    private FormatterService formatterService;

    private ValidationService validationService;

    private LocalDate localDate;

    public LibraryService(LibraryRepository libraryRepository, @Qualifier("xml") FormatterService formatterService) {
        this.libraryRepository = libraryRepository;
        this.formatterService = formatterService;
        this.localDate = LocalDate.now();
    }

    public List<Book> addBook(Book book) {
        formatterService.format(book.getDescription());
        validationService.validateTitlePattern(new BookDomain(book.getTitle(), AuthorDomain.builder().name(book.getAuthor()).build()));
        return libraryRepository.addBook(book);
    }

    public List<Book> getBooks() {
        logger.info("Getting list of all books");
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

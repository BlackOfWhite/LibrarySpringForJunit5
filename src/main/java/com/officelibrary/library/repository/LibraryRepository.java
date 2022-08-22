package com.officelibrary.library.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.officelibrary.library.model.Book;
import org.springframework.stereotype.Repository;

@Repository
public class LibraryRepository {

    private List<Book> books;

    public LibraryRepository() {
        this.books = new ArrayList<>();
    }

    public List<Book> addBook(Book book) {
        this.books.add(book);
        return this.books;
    }

    public List<Book> getBooks() {
        return this.books;
    }

    public List<Book> getBookByAuthor(String author) {
        return this.books.stream().filter(book -> book.getAuthor().equals(author)).collect(Collectors.toList());
    }

    public Optional<Book> getBookById(int id) {
        return this.books.stream().filter(book -> book.getUniqueID() == id).findAny();
    }

    public List<Book> deleteBook(Book book) {
        this.books.remove(book);
        return this.books;
    }

    public List<Book> deleteBookById(int id) {
        this.books.removeIf(book -> book.getUniqueID() == id);
        return this.books;
    }


    public void deleteAll() {
        this.books.clear();
    }

    public void updateBook(int id, Book newBook) {
        this.books.set(books.indexOf(getBookById(id).orElseThrow()), newBook);
    }
}

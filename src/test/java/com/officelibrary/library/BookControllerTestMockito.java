package com.officelibrary.library;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.officelibrary.library.exposure.controller.BookController;
import com.officelibrary.library.exposure.model.Book;
import com.officelibrary.library.exposure.service.LibraryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookControllerTestMockito {

    @Mock
    private LibraryService libraryService;

    @InjectMocks
    private BookController bookController;

    private static final List<Book> library = Arrays.asList(
        new Book("Ulysses", "James Joyce", "Ulysses chronicles"),
        new Book("Don Quixote", "Miguel de Cervantes", "Retired country gentleman in his fifties"),
        new Book("One Hundred Years of Solitude", "Gabriel Garcia Marquez", "Widely beloved and acclaimed novel"),
        new Book("The Great Gatsby", "F. Scott Fitzgerald", "An era that Fitzgerald himself dubbed the.")
    );

    @DisplayName("Successfuly get all books")
    @Test
    void getAllBooks() throws Exception {
        // given
        when(libraryService.getBooks())
            .thenReturn(Collections.singletonList(
                new Book("Ulysses", "James Joyce", "Ulysses chronicles")));

        // when
        List<Book> books = bookController.listBooks();

        // then
        verify(libraryService).getBooks();
        verifyNoMoreInteractions(libraryService);
        assertAll(() -> assertEquals(1, books.size()),
            () -> assertEquals("Ulysses", books.get(0).getTitle()));
    }
}

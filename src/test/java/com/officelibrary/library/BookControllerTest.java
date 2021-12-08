package com.officelibrary.library;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import com.officelibrary.library.exposure.controller.BookController;
import com.officelibrary.library.exposure.model.Book;
import com.officelibrary.library.exposure.service.LibraryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
class BookControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    LibraryService libraryService;

    private static final List<Book> library = Arrays.asList(
        new Book("Ulysses", "James Joyce", "Ulysses chronicles"),
        new Book("Don Quixote", "Miguel de Cervantes", "Retired country gentleman in his fifties"),
        new Book("One Hundred Years of Solitude", "Gabriel Garcia Marquez", "Widely beloved and acclaimed novel"),
        new Book("The Great Gatsby", "F. Scott Fitzgerald", "An era that Fitzgerald himself dubbed the.")
    );

    @Test
    void getAllRecords() throws Exception {
        Mockito.when(libraryService.getBooks()).thenReturn(library);

        mockMvc.perform(MockMvcRequestBuilders
            .get("/library/books")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(4)))
            .andExpect(jsonPath("$[2].title", is("One Hundred Years of Solitude")));
    }
}

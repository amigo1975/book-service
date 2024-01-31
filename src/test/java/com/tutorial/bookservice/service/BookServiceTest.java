package com.tutorial.bookservice.service;

import com.tutorial.bookservice.entity.Book;
import com.tutorial.bookservice.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllBooks() {
        List<Book> mockBooks = Arrays.asList(
                new Book(1, "Book 1", "Author 1", 1),
                new Book(2, "Book 2", "Author 2", 2)
        );
        when(bookRepository.findAll()).thenReturn(mockBooks);

        List<Book> books = bookService.getAll();
        assertEquals(2, books.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void getBookById() {
        Book mockBook = new Book(1, "Book 1", "Author 1", 1);
        when(bookRepository.findById(1)).thenReturn(Optional.of(mockBook));

        Book book = bookService.getBookById(1);
        assertEquals("Book 1", book.getTitle());
        verify(bookRepository, times(1)).findById(1);
    }

    @Test
    void saveBook() {
        Book mockBook = new Book(1, "Book 3", "Author 3", 3);
        when(bookRepository.save(any(Book.class))).thenReturn(mockBook);

        Book book = bookService.save(new Book());
        assertEquals("Book 3", book.getTitle());
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void getBooksByStudentId() {
        List<Book> mockBooks = Arrays.asList(
                new Book(1, "Book 1", "Author 1", 1),
                new Book(2, "Book 2", "Author 2", 1) // Assuming these books are for the same student
        );
        when(bookRepository.findByStudentId(1)).thenReturn(mockBooks);

        List<Book> books = bookService.byStudentId(1);
        assertEquals(2, books.size());
        assertEquals(1, books.get(0).getStudentId());
        verify(bookRepository, times(1)).findByStudentId(1);
    }
}
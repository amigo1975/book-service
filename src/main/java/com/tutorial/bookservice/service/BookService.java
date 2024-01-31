package com.tutorial.bookservice.service;

import com.tutorial.bookservice.entity.Book;
import com.tutorial.bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    // List all the books from the BD
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    // Get a Book given its Id
    public Book getBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    //Save a Book (receive data in a Json format)
    public Book save(Book book) {
        Book bookNew = bookRepository.save(book);
        return bookNew;
    }

    //List Books belonging to a given Student
    public List<Book> byStudentId(int studentId) {
        return bookRepository.findByStudentId(studentId);
    }
}

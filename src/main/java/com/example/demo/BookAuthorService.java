package com.example.demo;

import com.example.demo.modal.Author;
import com.example.demo.modal.Book;
import com.example.demo.repository.AuthorRepo;
import com.example.demo.repository.BookRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BookAuthorService {

    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private AuthorRepo authorRepo;

//    @PostConstruct
    public void addAuthorBook() {
        List<Author> authors = Arrays.asList(
                new Author(null, "Joshua", "Bloch"),
                new Author(null, "Douglas", "Adams"),
                new Author(null, "Bill", "Bryson")
        );
//        authorRepo.saveAll(authors);
        List<Book> books = Arrays.asList(
                new Book(null, "Effective Java", 416, authors.get(0)),
                new Book(null, "Hitchhiker's Guide to the Galaxy", 208, authors.get(1)),
                new Book(null, "Down Under", 436, authors.get(2))
        );
        bookRepo.saveAll(books);
    }
}

package com.example.demo.controller;

import com.example.demo.exception.BookNotFoundException;
import com.example.demo.modal.Author;
import com.example.demo.modal.Book;
import com.example.demo.repository.AuthorRepo;
import com.example.demo.repository.BookRepo;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QueriesResolver implements GraphQLQueryResolver {
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private AuthorRepo authorRepo;

    public Book bookById(Long id) {
        return bookRepo.findById(id).get();
    }

    public List<Book> findAllBooks() {
        return bookRepo.findAll();
    }

    public List<Author> findAllAuthors() {
        return authorRepo.findAll();
    }

    public String getException() {
        throw new BookNotFoundException("Where is my book ???");
    }
}

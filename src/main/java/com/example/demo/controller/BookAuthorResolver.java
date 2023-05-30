package com.example.demo.controller;

import com.example.demo.modal.Author;
import com.example.demo.modal.Book;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

@Component
public class BookAuthorResolver implements GraphQLResolver<Book> {

    public Author author(Book book) {
        return book.getAuthorId();
    }
}

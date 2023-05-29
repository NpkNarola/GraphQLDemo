package com.example.demo.controller;

import com.example.demo.Exception.BookNotFoundException;
import com.example.demo.dto.BookDto;
import com.example.demo.modal.Author;
import com.example.demo.modal.Book;
import com.example.demo.repository.AuthorRepo;
import com.example.demo.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private AuthorRepo authorRepo;

    @QueryMapping
    public Book bookById(@Argument Long id) {
        return bookRepo.getReferenceById(id);
    }

    @QueryMapping
    public List<Book> findAllBooks() {
        return bookRepo.findAll();
    }

    @QueryMapping
    public List<Author> findAllAuthors() {
        return authorRepo.findAll();
    }

    @SchemaMapping
    public Author author(Book book) {
        return book.getAuthorId();
    }

    @MutationMapping
    public Book createBook(@Argument Long id, @Argument String name, @Argument int pageCount, @Argument Long authorId) {
        return bookRepo.save(new Book(id, name, pageCount, authorRepo.findById(authorId).get()));
    }

    @MutationMapping
    public Author createAuthor(@Argument String firstName, @Argument String lastName) {
        return authorRepo.save(new Author(null, firstName, lastName));
    }

    @MutationMapping
    public Author updateAuthor(@Argument Long id, @Argument String firstName, @Argument String lastName) {
        return authorRepo.save(new Author(id, firstName, lastName));
    }

    @MutationMapping
    public String deleteAuthor(@Argument Long id) {
        authorRepo.deleteById(id);
        return "author deleted";
    }


    //Adding data to table with foreign key along with book object as argument
    @MutationMapping
    public Book createBookAuthor(@Argument BookDto bookDto) {
        Author author = new Author(null, bookDto.getAuthorDto().getFirstName(), bookDto.getAuthorDto().getLastName());
        Book book = new Book(null, bookDto.getName(), bookDto.getPageCount(), author);
        return bookRepo.save(book);
    }

    @MutationMapping
    public Book updateBookAuthor(@Argument BookDto bookDto, @Argument Long id) {
        Author author = new Author(bookDto.getAuthorDto().getId(), bookDto.getAuthorDto().getFirstName(), bookDto.getAuthorDto().getLastName());
        Book book = new Book(id, bookDto.getName(), bookDto.getPageCount(), author);
        return bookRepo.save(book);
    }

    @QueryMapping
    public String getException() {
        throw new BookNotFoundException("Where is my book ???");
    }

}
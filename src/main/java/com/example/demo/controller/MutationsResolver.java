package com.example.demo.controller;

import com.example.demo.dto.BookDto;
import com.example.demo.modal.Author;
import com.example.demo.modal.Book;
import com.example.demo.repository.AuthorRepo;
import com.example.demo.repository.BookRepo;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MutationsResolver implements GraphQLMutationResolver {
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private AuthorRepo authorRepo;

    public Book createBook(String name, int pageCount, Long authorId) {
        return bookRepo.save(new Book(null, name, pageCount, authorRepo.findById(authorId).get()));
    }

    public Author createAuthor(String firstName, String lastName) {
        return authorRepo.save(new Author(null, firstName, lastName));
    }

    public Author updateAuthor(Long id, String firstName, String lastName) {
        return authorRepo.save(new Author(id, firstName, lastName));
    }

    public String deleteAuthor(Long id) {
        authorRepo.deleteById(id);
        return "author deleted";
    }

    //Adding data to table with foreign key along with book object as argument
    public Book createBookAuthor(BookDto bookDto) {
        Author author = new Author(null, bookDto.getAuthorDto().getFirstName(), bookDto.getAuthorDto().getLastName());
        Book book = new Book(null, bookDto.getName(), bookDto.getPageCount(), author);
        return bookRepo.save(book);
    }

    public Book updateBookAuthor(BookDto bookDto, Long id) {
        Author author = new Author(bookDto.getAuthorDto().getId(), bookDto.getAuthorDto().getFirstName(), bookDto.getAuthorDto().getLastName());
        Book book = new Book(id, bookDto.getName(), bookDto.getPageCount(), author);
        return bookRepo.save(book);
    }
}

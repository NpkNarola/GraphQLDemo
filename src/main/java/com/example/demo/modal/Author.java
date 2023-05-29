package com.example.demo.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;


//    private static List<Author> authors = Arrays.asList(
//            new Author("author-1", "Joshua", "Bloch"),
//            new Author("author-2", "Douglas", "Adams"),
//            new Author("author-3", "Bill", "Bryson")
//    );
//
//    public void addAuthor(Author author) {
//        authors.add(author);
//    }
//
//    public static Author getById(String id) {
//        return authors.stream()
//                .filter(author -> author.id().equals(id))
//                .findFirst()
//                .orElse(null);
//    }
}

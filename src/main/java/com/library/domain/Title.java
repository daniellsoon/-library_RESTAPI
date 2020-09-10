package com.library.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "TITLES")
@Data
public class Title {

    public Title(Long id, String bookTitle, String author, int publicationYear) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.author = author;
        this.publicationYear = publicationYear;
        this.booksInTitle = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "TITLE")
    private String bookTitle;

    @NotNull
    @Column(name = "AUTHOR")
    private String author;

    @NotNull
    @Column(name = "PUB_YEAR")
    private int publicationYear;

    @OneToMany(
            targetEntity = Book.class,
            mappedBy = "title",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private List<Book> booksInTitle = new ArrayList<>();
}
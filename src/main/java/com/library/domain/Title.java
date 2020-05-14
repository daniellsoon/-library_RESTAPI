package com.library.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "TITLES")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
public class Title {

    @Id
    @Column(unique = true, name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long id;

    @NonNull
    @Column(name = "TITLE")
    @NotNull
    private String title;

    @NonNull
    @Column(name = "AUTHOR")
    @NotNull
    private String author;

    @NonNull
    @Column(name = "PUB_YEAR")
    @NotNull
    private Integer publicationYear;

    @OneToMany (
            targetEntity = Book.class,
            mappedBy =  "title",
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST
    )
    private List<Book> booksInTitle;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }
}

package com.library.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "READERS")
@Data
public class Reader {

    public Reader(Long id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.singIn = LocalDate.now();
        this.borrowedBooks = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    @NotNull
    private String name;

    @Column(name = "LASTNAME")
    @NotNull
    private String lastName;

    @Column(name = "SING_IN")
    @NotNull
    private LocalDate singIn;

    @OneToMany (
            targetEntity = Borrow.class,
            mappedBy =  "reader",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private List<Borrow> borrowedBooks;

}

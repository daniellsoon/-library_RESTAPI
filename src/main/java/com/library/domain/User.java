package com.library.domain;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "USERS")
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

    @Id
    @Column(unique = true, name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long id;

    @NonNull
    @Column(name = "NAME")
    @NotNull
    private String name;

    @NonNull
    @Column(name = "LASTNAME")
    @NotNull
    private String lastName;

    @Column(name = "SING_IN")
    @NotNull
    private LocalDate singIn = LocalDate.now();

    @OneToMany (
            targetEntity = Borrow.class,
            mappedBy =  "user",
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST
    )
    private List<Borrow> borrowedBooks;

}

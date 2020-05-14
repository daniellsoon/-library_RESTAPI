package com.library.domain;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity(name = "BORROWS")
@NoArgsConstructor
@RequiredArgsConstructor
public class Borrow {

    @Id
    @Column(unique = true, name = "ID")
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @NotNull
    private long id;

    @NonNull
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "BOOK")
    private Book bookId;

    @NonNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER")
    private User userId;

    @Column(name = "BORROW_DATE")
    @NotNull
    private LocalDate borrowDate = LocalDate.now();

    @Column(name = "RETURN_DATE")
    private LocalDate returnDate;



}


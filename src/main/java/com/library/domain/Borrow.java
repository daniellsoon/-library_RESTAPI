package com.library.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity(name = "BORROWS")
@NoArgsConstructor
@Getter
@Setter
public class Borrow {

    public Borrow(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "BOOK")
    private Book book;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER")
    private Reader reader;

    @Column(name = "BORROW_DATE")
    @NotNull
    private LocalDate borrowDate = LocalDate.now();

    @Column(name = "RETURN_DATE")
    private LocalDate returnDate;



}


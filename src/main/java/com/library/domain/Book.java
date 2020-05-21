package com.library.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "BOOKS")
@NoArgsConstructor
@Getter
@Setter
public class Book {

    public Book (Long id, Title title) {
        this.id = id;
        this.title = title;
        status = StatusAllowed.OPEN;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "TITLE")
    private Title title;

    @NotNull
    @Column(name = "STATUS")
    private StatusAllowed status;

    @OneToOne (cascade = CascadeType.ALL, mappedBy = "book")
    @JoinColumn(name = "BORROW")
    private Borrow borrow;

}

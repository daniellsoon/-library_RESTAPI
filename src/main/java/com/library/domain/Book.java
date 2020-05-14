package com.library.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
public class Book {

    @Id
    @Column(unique =  true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long id;

    @NonNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Title title;

    @Column
    @NotNull
    private StatusAllowed status = StatusAllowed.OPEN;

    @OneToOne (cascade = CascadeType.PERSIST)
    @JoinColumn
    private Borrow borrowId;

    public void setTitle(Title title) {
        this.title = title;
    }

    public void setStatus(StatusAllowed status) {
        this.status = status;
    }

    public void setBorrowId(Borrow borrowId) {
        this.borrowId = borrowId;
    }
}

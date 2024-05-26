package com.example.book.domain.model;

import com.example.book.domain.model.vo.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    @Embedded
    private BookDesc desc;
    private BookClassification classification;
    private BookStatus status;
    private Location location;

    private Book(String title, BookDesc desc, BookClassification classification,
                 BookStatus status, Location location) {
        this.title = title;
        this.desc = desc;
        this.classification = classification;
        this.status = status;
        this.location = location;
    }

    public static Book enter(String title, BookDesc desc, BookClassification classification, Location location) {
        return new Book(
            title, desc, classification, BookStatus.ENTERED, location
        );
    }

    public Book makeAvailable() {
        this.status = BookStatus.AVAILABLE;
        return this;
    }

    public Book makeUnAvailable() {
        this.status = BookStatus.UNAVAILABLE;
        return this;
    }
}

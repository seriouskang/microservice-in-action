package com.example.book.domain.model.vo;

import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class BookDesc {
    private String desc;
    private String author;
    private String isbn;
    private LocalDate publicationDate;
    private Source source;
}

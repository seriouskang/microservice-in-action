package com.example.book.framework.httpadapter.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BookInfoDTO {
    private String title;
    private String desc;
    private String author;
    private String isbn;
    private LocalDate publicationDate;
    private String source;
    private String classification;
    private String location;
}

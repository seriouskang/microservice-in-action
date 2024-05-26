package com.example.book.domain.model.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BookDesc {
    private String desc;
    private String author;
    private String isbn;
    private LocalDate publicationDate;
    private Source source;
}

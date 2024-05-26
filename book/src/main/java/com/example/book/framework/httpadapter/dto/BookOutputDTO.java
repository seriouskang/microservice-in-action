package com.example.book.framework.httpadapter.dto;

import com.example.book.domain.model.Book;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BookOutputDTO {
    private long id;
    private String title;
    private String status;

    public static BookOutputDTO of(Book book) {
        return new BookOutputDTO(
                book.getId(),
                book.getTitle(),
                book.getStatus().toString()
        );
    }
}

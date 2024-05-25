package com.example.book.domain.model;

import com.example.book.domain.model.vo.BookClassification;
import com.example.book.domain.model.vo.BookDesc;
import com.example.book.domain.model.vo.BookStatus;
import com.example.book.domain.model.vo.Location;

public class Book {
    private long id;
    private String title;
    private BookDesc desc;
    private BookClassification classification;
    private BookStatus status;
    private Location location;
}

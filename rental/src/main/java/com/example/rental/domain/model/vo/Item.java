package com.example.rental.domain.model.vo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Item {
    private final Long id;
    private final String title;

    public long id() {
        return id;
    }

    public String title() {
        return title;
    }
}

package com.example.rental.domain.model.vo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Member {
    private final Long id;
    private final String name;

    public long id() {
        return id;
    }

    public String name() {
        return name;
    }
}

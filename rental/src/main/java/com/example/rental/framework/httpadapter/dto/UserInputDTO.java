package com.example.rental.framework.httpadapter.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserInputDTO {
    private final long id;
    private final String name;

    public long id() {
        return id;
    }

    public String name() {
        return name;
    }
}

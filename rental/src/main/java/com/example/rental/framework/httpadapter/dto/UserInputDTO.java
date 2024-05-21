package com.example.rental.framework.httpadapter.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserInputDTO {
    private final long id;
    private final String name;
}

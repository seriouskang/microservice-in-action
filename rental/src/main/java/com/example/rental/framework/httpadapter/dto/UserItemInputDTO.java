package com.example.rental.framework.httpadapter.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserItemInputDTO {
    private final long userId;
    private final String userName;
    private final long itemId;
    private final String itemTitle;
}

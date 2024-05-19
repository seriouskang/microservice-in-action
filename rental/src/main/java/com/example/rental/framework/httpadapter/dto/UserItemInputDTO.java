package com.example.rental.framework.httpadapter.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserItemInputDTO {
    private final long userId;
    private final String userName;
    private final long itemId;
    private final String itemTitle;

    public long userId() {
        return userId;
    }

    public String userName() {
        return userName;
    }

    public long itemId() {
        return itemId;
    }

    public String itemTitle() {
        return itemTitle;
    }
}

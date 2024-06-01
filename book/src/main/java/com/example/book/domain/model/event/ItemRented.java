package com.example.book.domain.model.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class ItemRented {
    private User user;
    private Item item;
    private long point;
}

package com.example.book.domain.model.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemSubmitted {
    private User user;
    private Item item;
    private long point;
}

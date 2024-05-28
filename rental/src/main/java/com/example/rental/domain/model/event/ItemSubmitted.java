package com.example.rental.domain.model.event;

import com.example.rental.domain.model.vo.Item;
import com.example.rental.domain.model.vo.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ItemSubmitted {
    private User user;
    private Item item;
    private long point;
}

package com.example.bestbook.domain.model.event;

import com.example.bestbook.domain.model.vo.Item;
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

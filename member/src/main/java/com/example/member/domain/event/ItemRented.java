package com.example.member.domain.event;

import com.example.member.domain.vo.User;
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

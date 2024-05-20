package com.example.rental.domain.model.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Item {
    private Long itemId;
    private String itemTitle;

    public long itemId() {
        return itemId;
    }

    public String itemTitle() {
        return itemTitle;
    }
}

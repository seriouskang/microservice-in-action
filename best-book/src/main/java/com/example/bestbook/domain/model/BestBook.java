package com.example.bestbook.domain.model;

import com.example.bestbook.domain.model.vo.Item;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BestBook {
    private String id;
    private Item item;
    private long rentCount;

    public static BestBook registerBestBook(Item item) {
        return new BestBook(
                UUID.randomUUID().toString(),
                item,
                1L
        );
    }

    public Long increaseBestBookCount() {
        this.rentCount += 1;
        return this.rentCount;
    }
}

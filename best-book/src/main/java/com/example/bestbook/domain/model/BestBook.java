package com.example.bestbook.domain.model;

import com.example.bestbook.domain.model.vo.Item;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Document
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BestBook {
    @Id
    private String id;
    @Setter
    private Item item;
    @Setter
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

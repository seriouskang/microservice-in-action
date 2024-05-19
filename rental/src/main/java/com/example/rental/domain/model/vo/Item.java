package com.example.rental.domain.model.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Item {
    private Long id;
    private String title;

    public long id() {
        return id;
    }

    public String title() {
        return title;
    }
}

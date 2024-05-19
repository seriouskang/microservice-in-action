package com.example.rental.domain.model.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {
    private Long id;
    private String name;

    public long id() {
        return id;
    }

    public String name() {
        return name;
    }
}

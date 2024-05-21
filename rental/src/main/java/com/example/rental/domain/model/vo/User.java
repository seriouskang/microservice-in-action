package com.example.rental.domain.model.vo;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {
    private Long userId;
    private String name;

    public long userId() {
        return userId;
    }

    public String name() {
        return name;
    }
}

package com.example.member.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Point {
    private Long point = 0L;

    public long add(long point) {
        this.point += point;
        return this.point;
    }

    public long deduct(long point) {
        if(point > this.point) {
            throw new IllegalArgumentException("Point is not enough");
        }

        this.point -= point;
        return this.point;
    }
}

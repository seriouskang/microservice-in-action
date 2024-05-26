package com.example.member.domain.vo;

public class Point {
    private Long point;

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

package com.example.rental.domain.model.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LateFee {
    private Long point;

    public static LateFee create() {
        return new LateFee(0L);
    }

    public LateFee addPoint(long point) {
        return new LateFee(this.point + point);
    }

    public LateFee removePoint(long point) throws Exception {
        validatePoint(point);
        return new LateFee(this.point - point);
    }

    private void validatePoint(long point) throws Exception {
        if(this.point < point) {
            throw new Exception("Points isn't enough");
        }
    }
}

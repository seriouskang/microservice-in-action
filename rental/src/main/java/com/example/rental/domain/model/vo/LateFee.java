package com.example.rental.domain.model.vo;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LateFee {
    private Long point;

    public static LateFee create() {
        return new LateFee(0L);
    }

    public LateFee addPoint(long point) {
        return new LateFee(this.point + point);
    }

    public LateFee deductPoint(long point) {
        validatePoint(point);
        return new LateFee(this.point - point);
    }

    public void validatePoint(long point) throws IllegalStateException {
        if(this.point < point) {
            throw new IllegalStateException("Points isn't enough");
        }
    }

    public boolean isNormalStatus() {
        return point>=0;
    }

    public Long point() {
        return point;
    }
}

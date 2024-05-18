package com.example.rental.domain.model;

import com.example.rental.domain.model.vo.LateFee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LateFeeTest {
    @Test
    void not_enough_point() {
        // given
        LateFee lateFee = LateFee.create();
        lateFee.addPoint(1000L);

        // when, then
        Assertions.assertThatThrownBy(() -> lateFee.deductPoint(1100L))
                .isInstanceOf(Exception.class)
                .hasMessage("Points isn't enough");
    }
}

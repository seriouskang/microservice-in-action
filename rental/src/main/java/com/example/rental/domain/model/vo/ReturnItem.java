package com.example.rental.domain.model.vo;

import com.example.rental.domain.model.RentalItem;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ReturnItem {
    private final RentalItem item;
    private final LocalDate returnDate;

    public static ReturnItem create(RentalItem item) {
        return new ReturnItem(item, LocalDate.now());
    }
}

package com.example.rental.domain.model.vo;

import com.example.rental.domain.model.RentalItem;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SubmitItem {
    private final RentalItem item;
    private final LocalDate returnDate;

    public static SubmitItem create(RentalItem item) {
        return new SubmitItem(item, LocalDate.now());
    }
}

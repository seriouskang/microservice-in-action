package com.example.rental.domain.model;

import com.example.rental.domain.model.vo.Item;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class RentalItem {
    private static final long RENTAL_DATE = 14;

    private final Item item;
    private final LocalDate rentalDate;
    private final LocalDate expectedReturnDate;
    private final boolean overdued;

    public static RentalItem create(Item item) {
        return new RentalItem(item, LocalDate.now(), createExpectedReturnDate(), false);
    }

    private static LocalDate createExpectedReturnDate() {
        return LocalDate.now().plusDays(RENTAL_DATE);
    }
}

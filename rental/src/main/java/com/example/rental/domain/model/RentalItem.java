package com.example.rental.domain.model;

import com.example.rental.domain.model.vo.Item;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RentalItem {
    private static final long RENTAL_DATE = 14;
    private static final long POINT_UNIT = 10;

    private final Item item;
    private final LocalDate rentalDate;
    private final LocalDate expectedReturnDate;
    private boolean overdued;

    public static RentalItem create(Item item) {
        return new RentalItem(item, LocalDate.now(), createExpectedReturnDate(), false);
    }

    private static LocalDate createExpectedReturnDate() {
        return LocalDate.now().plusDays(RENTAL_DATE);
    }

    public Item item() {
        return item;
    }

    public long calculateFee(LocalDate submitDate) {
        if(submitDate.isAfter(expectedReturnDate)) {
            return Period.between(expectedReturnDate, submitDate).getDays() * POINT_UNIT;
        }

        return 0L;
    }

    public void changeOverdued(boolean overdued) {
        this.overdued = overdued;
    }

    public boolean overdued() {
        return overdued;
    }
}

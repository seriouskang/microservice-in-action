package com.example.rental.domain.model;

import com.example.rental.domain.model.vo.Item;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.time.LocalDate;
import java.time.Period;

@Embeddable
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RentalItem {
    private static final long RENTAL_DATE = 14;
    private static final long POINT_UNIT = 10;

    @Embedded
    private Item item;
    private LocalDate rentalDate;
    private LocalDate expectedSubmitDate;
    private boolean overdued;

    public static RentalItem create(Item item) {
        return new RentalItem(item, LocalDate.now(), createExpectedReturnDate(), false);
    }

    private static LocalDate createExpectedReturnDate() {
        return LocalDate.now().plusDays(RENTAL_DATE);
    }

    public long id() {
        return item.getItemId();
    }

    public String title() {
        return item.getItemTitle();
    }

    public long calculateFee(LocalDate submitDate) {
        if(submitDate.isAfter(expectedSubmitDate)) {
            return Period.between(expectedSubmitDate, submitDate).getDays() * POINT_UNIT;
        }

        return 0L;
    }

    public void changeOverdued(boolean overdued) {
        this.overdued = overdued;
    }
}

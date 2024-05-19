package com.example.rental.domain.model.vo;

import com.example.rental.domain.model.RentalItem;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.time.LocalDate;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SubmitItem {
    @Embedded
    private RentalItem item;
    private LocalDate submitDate;

    public static SubmitItem create(RentalItem item) {
        return new SubmitItem(item, LocalDate.now());
    }

    public long id() {
        return item.id();
    }

    public String title() {
        return item.title();
    }

    public LocalDate submitDate() {
        return submitDate;
    }
}

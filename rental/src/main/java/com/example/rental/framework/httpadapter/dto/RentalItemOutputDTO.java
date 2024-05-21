package com.example.rental.framework.httpadapter.dto;

import com.example.rental.domain.model.RentalItem;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class RentalItemOutputDTO {
    private final long id;
    private final String title;
    private final LocalDate rentDate;
    private final boolean overdued;
    private final LocalDate expectedSubmitDate;

    public static RentalItemOutputDTO of(RentalItem rentalItem) {
        return new RentalItemOutputDTO(
                rentalItem.id(),
                rentalItem.title(),
                rentalItem.getRentalDate(),
                rentalItem.isOverdued(),
                rentalItem.getExpectedSubmitDate()
        );
    }
}

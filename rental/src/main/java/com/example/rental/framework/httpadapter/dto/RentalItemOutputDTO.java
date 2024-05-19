package com.example.rental.framework.httpadapter.dto;

import com.example.rental.domain.model.RentalItem;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

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
                rentalItem.rentalDate(),
                rentalItem.overdued(),
                rentalItem.expectedSubmitDate()
        );
    }
}

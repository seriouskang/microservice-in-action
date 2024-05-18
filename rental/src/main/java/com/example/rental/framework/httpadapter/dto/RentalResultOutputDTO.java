package com.example.rental.framework.httpadapter.dto;

import com.example.rental.domain.model.RentalCard;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RentalResultOutputDTO {
    private final long userId;
    private final String userName;
    private final int rentalCount;
    private final long totalLateFee;

    public static RentalResultOutputDTO of(RentalCard rentalCard) {
        return new RentalResultOutputDTO(
                rentalCard.userId(),
                rentalCard.userName(),
                rentalCard.rentalCount(),
                rentalCard.point()
        );
    }
}

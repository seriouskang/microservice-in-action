package com.example.rental.framework.httpadapter.dto;

import com.example.rental.domain.model.RentalCard;
import com.example.rental.domain.model.vo.RentStatus;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RentalCardOutputDTO {
    private final String rentalCardId;
    private final long userId;
    private final String userName;
    private final RentStatus rentStatus;
    private final int rentalCount;
    private final int submitCount;
    private final long overduedCount;

    public static RentalCardOutputDTO of(RentalCard rentalCard) {
        return new RentalCardOutputDTO(
                rentalCard.rentalCardId(),
                rentalCard.userId(),
                rentalCard.userName(),
                rentalCard.rentStatus(),
                rentalCard.rentalCount(),
                rentalCard.submitCount(),
                rentalCard.overduedCount()
        );
    }
}

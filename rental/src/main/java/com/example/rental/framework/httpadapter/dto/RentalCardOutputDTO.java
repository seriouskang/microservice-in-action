package com.example.rental.framework.httpadapter.dto;

import com.example.rental.domain.model.RentalCard;
import com.example.rental.domain.model.vo.RentStatus;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RentalCardOutputDTO {
    private final String rentalCardId;
    private final long memberId;
    private final String memberName;
    private final RentStatus rentStatus;
    private final int rentalCount;
    private final int submitCount;
    private final long overduedCount;

    public static RentalCardOutputDTO of(RentalCard rentalCard) {
        return new RentalCardOutputDTO(
                rentalCard.rentalCardId(),
                rentalCard.memberId(),
                rentalCard.memberName(),
                rentalCard.rentStatus(),
                rentalCard.rentalCount(),
                rentalCard.submitCount(),
                rentalCard.overduedCount()
        );
    }
}

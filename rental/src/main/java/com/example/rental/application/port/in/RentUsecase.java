package com.example.rental.application.port.in;

import com.example.rental.framework.httpadapter.dto.RentalCardOutputDTO;
import com.example.rental.framework.httpadapter.dto.UserItemInputDTO;

public interface RentUsecase {
    RentalCardOutputDTO rent(UserItemInputDTO rentalDTO);
}

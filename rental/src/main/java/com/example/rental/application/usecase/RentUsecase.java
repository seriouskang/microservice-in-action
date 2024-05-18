package com.example.rental.application.usecase;

import com.example.rental.framework.httpadapter.dto.RentalCardOutputDTO;
import com.example.rental.framework.httpadapter.dto.UserItemInputDTO;

public interface RentUsecase {
    RentalCardOutputDTO rent(UserItemInputDTO userItemInputDTO);
}

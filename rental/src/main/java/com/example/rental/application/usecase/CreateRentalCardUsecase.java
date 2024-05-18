package com.example.rental.application.usecase;

import com.example.rental.framework.httpadapter.dto.RentalCardOutputDTO;
import com.example.rental.framework.httpadapter.dto.UserInputDTO;

public interface CreateRentalCardUsecase {
    public RentalCardOutputDTO createRentalCard(UserInputDTO userInputDTO);
}

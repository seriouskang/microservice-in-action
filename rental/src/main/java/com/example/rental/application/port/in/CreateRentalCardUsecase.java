package com.example.rental.application.port.in;

import com.example.rental.framework.httpadapter.dto.RentalCardOutputDTO;
import com.example.rental.framework.httpadapter.dto.UserInputDTO;

public interface CreateRentalCardUsecase {
    RentalCardOutputDTO createRentalCard(UserInputDTO owner);
}

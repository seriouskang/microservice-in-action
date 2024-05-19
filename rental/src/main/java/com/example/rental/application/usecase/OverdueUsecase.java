package com.example.rental.application.usecase;

import com.example.rental.framework.httpadapter.dto.RentalCardOutputDTO;
import com.example.rental.framework.httpadapter.dto.UserItemInputDTO;

public interface OverdueUsecase {
    RentalCardOutputDTO overdue(UserItemInputDTO userItemInputDTO);
}
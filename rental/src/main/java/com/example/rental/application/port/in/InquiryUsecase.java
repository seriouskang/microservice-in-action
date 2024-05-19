package com.example.rental.application.port.in;

import com.example.rental.framework.httpadapter.dto.*;

import java.util.List;
import java.util.Optional;

public interface InquiryUsecase {
    Optional<RentalCardOutputDTO> getRentalCard(UserInputDTO userInputDTO);
    Optional<List<RentalItemOutputDTO>> getAllRentalItems(UserInputDTO userInputDTO);
    Optional<List<SubmitItemOutputDTO>> getAllSubmitItems(UserInputDTO userInputDTO);
}

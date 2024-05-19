package com.example.rental.application.service;

import com.example.rental.application.port.in.InquiryUsecase;
import com.example.rental.application.port.out.RentalCardOutputPort;
import com.example.rental.framework.httpadapter.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InquiryService implements InquiryUsecase {
    private final RentalCardOutputPort rentalCardOutputPort;

    @Override
    public Optional<RentalCardOutputDTO> getRentalCard(UserInputDTO userInputDTO) {
        return rentalCardOutputPort.loadRentalCard(userInputDTO.id())
                .map(RentalCardOutputDTO::of);
    }

    @Override
    public Optional<List<RentalItemOutputDTO>> getAllRentalItems(UserInputDTO userInputDTO) {
        return rentalCardOutputPort.loadRentalCard(userInputDTO.id())
                .map(loadedCard -> loadedCard.rentalItems()
                        .stream()
                        .map(RentalItemOutputDTO::of)
                        .collect(Collectors.toUnmodifiableList()));
    }

    @Override
    public Optional<List<SubmitItemOutputDTO>> getAllSubmitItems(UserInputDTO userInputDTO) {
        return rentalCardOutputPort.loadRentalCard(userInputDTO.id())
                .map(loadedCard -> loadedCard.submitItems()
                        .stream()
                        .map(SubmitItemOutputDTO::of)
                        .collect(Collectors.toUnmodifiableList()));
    }
}

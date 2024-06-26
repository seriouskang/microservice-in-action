package com.example.rental.application.service;

import com.example.rental.application.port.in.CreateRentalCardUsecase;
import com.example.rental.application.port.out.RentalCardOutputPort;
import com.example.rental.domain.model.RentalCard;
import com.example.rental.domain.model.vo.User;
import com.example.rental.framework.httpadapter.dto.RentalCardOutputDTO;
import com.example.rental.framework.httpadapter.dto.UserInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateRentalCardService implements CreateRentalCardUsecase {
    private final RentalCardOutputPort rentalCardOutputPort;

    @Override
    public RentalCardOutputDTO createRentalCard(UserInputDTO owner) {
        RentalCard rentalCard = RentalCard.create(new User(owner.getId(), owner.getName()));
        RentalCard saved = rentalCardOutputPort.save(rentalCard);

        return RentalCardOutputDTO.of(saved);
    }
}

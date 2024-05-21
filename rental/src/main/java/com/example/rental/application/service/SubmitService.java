package com.example.rental.application.service;

import com.example.rental.application.port.in.SubmitUsecase;
import com.example.rental.application.port.out.RentalCardOutputPort;
import com.example.rental.domain.model.RentalCard;
import com.example.rental.domain.model.vo.Item;
import com.example.rental.framework.httpadapter.dto.RentalCardOutputDTO;
import com.example.rental.framework.httpadapter.dto.UserItemInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class SubmitService implements SubmitUsecase {
    private final RentalCardOutputPort rentalCardOutputPort;

    @Override
    public RentalCardOutputDTO submit(UserItemInputDTO submitDTO) {
        RentalCard rentalCard = rentalCardOutputPort.loadRentalCard(submitDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Rental card is not exist"));

        Item submitItem = new Item(submitDTO.getItemId(), submitDTO.getItemTitle());
        RentalCard submittedRentalCard = rentalCard.submit(submitItem, LocalDate.now());

        rentalCardOutputPort.save(submittedRentalCard);     // JPA 사용 시, dirty checking으로 삭제해도 무방

        return RentalCardOutputDTO.of(submittedRentalCard);
    }
}

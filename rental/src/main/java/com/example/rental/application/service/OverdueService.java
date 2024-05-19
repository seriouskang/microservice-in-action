package com.example.rental.application.service;

import com.example.rental.application.port.in.OverdueUsecase;
import com.example.rental.application.port.out.RentalCardOutputPort;
import com.example.rental.domain.model.RentalCard;
import com.example.rental.domain.model.vo.Item;
import com.example.rental.framework.httpadapter.dto.RentalCardOutputDTO;
import com.example.rental.framework.httpadapter.dto.UserItemInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OverdueService implements OverdueUsecase {
    private final RentalCardOutputPort rentalCardOutputPort;

    @Override
    public RentalCardOutputDTO overdue(UserItemInputDTO rentalDTO) {
        RentalCard rentalCard = rentalCardOutputPort.loadRentalCard(rentalDTO.userId())
                .orElseThrow(() -> new IllegalArgumentException("Rental card is not exist"));

        Item overduedItem = new Item(rentalDTO.itemId(), rentalDTO.itemTitle());
        RentalCard overduedRentalCard = rentalCard.configOverdue(overduedItem);

        rentalCardOutputPort.save(overduedRentalCard);      // JPA 사용 시, dirty checking으로 삭제해도 무방

        return RentalCardOutputDTO.of(overduedRentalCard);
    }
}

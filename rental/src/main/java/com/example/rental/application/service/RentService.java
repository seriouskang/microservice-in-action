package com.example.rental.application.service;

import com.example.rental.application.port.in.RentUsecase;
import com.example.rental.application.port.out.EventOutputPort;
import com.example.rental.application.port.out.RentalCardOutputPort;
import com.example.rental.domain.model.RentalCard;
import com.example.rental.domain.model.event.ItemRented;
import com.example.rental.domain.model.vo.Item;
import com.example.rental.domain.model.vo.User;
import com.example.rental.framework.httpadapter.dto.RentalCardOutputDTO;
import com.example.rental.framework.httpadapter.dto.UserItemInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RentService implements RentUsecase {
    private final RentalCardOutputPort rentalCardOutputPort;
    private final EventOutputPort eventOutputPort;

    @Override
    public RentalCardOutputDTO rent(UserItemInputDTO rentalDTO) {
        RentalCard rentalCard = rentalCardOutputPort.loadRentalCard(rentalDTO.getUserId())
                .orElseGet(() -> RentalCard.create(new User(rentalDTO.getUserId(), rentalDTO.getUserName())));
        Item item = new Item(rentalDTO.getItemId(), rentalDTO.getItemTitle());

        rentalCard.rent(item);
        ItemRented itemRentedEvent = RentalCard.createItemRentedEvent(rentalCard.getUser(), item, 10L);
        eventOutputPort.publishRentalEvent(itemRentedEvent);
        RentalCard saved = rentalCardOutputPort.save(rentalCard);   // JPA 사용 시, dirty checking으로 삭제해도 무방

        return RentalCardOutputDTO.of(saved);
    }
}

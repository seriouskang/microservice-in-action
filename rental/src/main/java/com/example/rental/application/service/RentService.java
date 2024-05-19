package com.example.rental.application.service;

import com.example.rental.application.port.in.RentUsecase;
import com.example.rental.application.port.out.RentalCardOutputPort;
import com.example.rental.domain.model.RentalCard;
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

    @Override
    public RentalCardOutputDTO rent(UserItemInputDTO rental) {
        RentalCard rentalCard = rentalCardOutputPort.loadRentalCard(rental.userId())
                .orElseGet(() -> RentalCard.create(new User(rental.userId(), rental.userName())));
        Item item = new Item(rental.itemId(), rental.itemTitle());

        rentalCard.rent(item);
        RentalCard saved = rentalCardOutputPort.save(rentalCard);   // JPA 사용 시, dirty checking으로 삭제해도 무방

        return RentalCardOutputDTO.of(saved);
    }
}

package com.example.rental.application.port.out;

import com.example.rental.domain.model.RentalCard;

import java.util.Optional;

public interface RentalCardOutputPort {
    Optional<RentalCard> loadRentalCard(Long userId);
    RentalCard save(RentalCard rentalCard);
}

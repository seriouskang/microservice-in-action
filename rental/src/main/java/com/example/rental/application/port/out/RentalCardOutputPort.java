package com.example.rental.application.port.out;

import com.example.rental.domain.model.RentalCard;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentalCardOutputPort {
    Optional<RentalCard> loadRentalCard(Long userId);
    RentalCard save(RentalCard rentalCard);
}

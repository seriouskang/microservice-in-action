package com.example.rental.framework.jpaadapter;

import com.example.rental.application.port.out.RentalCardOutputPort;
import com.example.rental.domain.model.RentalCard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RentalCardJpaAdapter implements RentalCardOutputPort {
    private final RentalCardRepository rentalCardRepository;

    @Override
    public Optional<RentalCard> loadRentalCard(Long userId) {
        return rentalCardRepository.findByUserId(userId);
    }

    @Override
    public RentalCard save(RentalCard rentalCard) {
        return rentalCardRepository.save(rentalCard);
    }
}

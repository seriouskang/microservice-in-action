package com.example.rental.application.service;

import com.example.rental.application.port.in.ClearOverdueUsecase;
import com.example.rental.application.port.out.EventOutputPort;
import com.example.rental.application.port.out.RentalCardOutputPort;
import com.example.rental.domain.model.RentalCard;
import com.example.rental.domain.model.event.OverdueCleared;
import com.example.rental.framework.httpadapter.dto.ClearOverdueInfoDTO;
import com.example.rental.framework.httpadapter.dto.RentalResultOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClearOverdueService implements ClearOverdueUsecase {
    private final RentalCardOutputPort rentalCardOutputPort;
    private final EventOutputPort eventOutputPort;

    @Override
    public RentalResultOutputDTO clearOverdue(ClearOverdueInfoDTO clearOverdueInfoDTO) {
        RentalCard rentalCard = rentalCardOutputPort.loadRentalCard(clearOverdueInfoDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Rental card is not exist"));

        rentalCard.changeStatusToAvailable(clearOverdueInfoDTO.getPoint());

        OverdueCleared overdueClearedEvent = RentalCard.createOverdueClearedEvent(
                rentalCard.getUser(), clearOverdueInfoDTO.getPoint()
        );
        eventOutputPort.publishOverdueClearedEvent(overdueClearedEvent);
        rentalCardOutputPort.save(rentalCard);      // JPA 사용 시, dirty checking으로 삭제해도 무방

        return RentalResultOutputDTO.of(rentalCard);
    }
}

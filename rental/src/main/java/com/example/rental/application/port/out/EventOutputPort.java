package com.example.rental.application.port.out;

import com.example.rental.domain.model.event.ItemRented;
import com.example.rental.domain.model.event.OverdueCleared;

public interface EventOutputPort {
    void publishRentalEvent(ItemRented itemRented);
    void publishSubmitEvent(ItemRented itemRented);
    void publishOverdueClearedEvent(OverdueCleared overdueCleared);
}

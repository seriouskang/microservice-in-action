package com.example.rental.domain.model;

import com.example.rental.domain.model.vo.Item;

import java.util.ArrayList;
import java.util.List;

public class RentalItems {
    private static final int MAX_RENTAL_COUNT = 5;
    private List<RentalItem> rentalItems;

    public RentalItems() {
        this.rentalItems = new ArrayList<>();
    }

    private void addItem(Item item) {
        rentalItems.add(RentalItem.create(item));
    }

    private void validateStatus() {
        if(rentalItems.size() > MAX_RENTAL_COUNT) {
            throw new IllegalStateException("Exceed maximum number of rentals");
        }
    }

    public void rentalItem(Item item) {
        validateStatus();
        addItem(item);
    }
}

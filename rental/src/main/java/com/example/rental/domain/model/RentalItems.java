package com.example.rental.domain.model;

import com.example.rental.domain.model.vo.Item;

import java.time.LocalDate;
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

    private void removeItem(RentalItem item) {
        rentalItems.remove(item);
    }

    private void validateStatus() {
        if(rentalItems.size() > MAX_RENTAL_COUNT) {
            throw new IllegalStateException("Exceed maximum number of rentals");
        }
    }

    public List<RentalItem> rentalItems() {
        return rentalItems;
    }

    private RentalItem rentalItemOf(Item item) {
        return rentalItems.stream()
                .filter(i -> i.item().equals(item))
                .findFirst()
                .get();
    }

    public int rentalCount() {
        return rentalItems.size();
    }

    public void configOverdue(Item item) {
        rentalItemOf(item).changeOverdued(true);
    }

    public void rent(Item item) {
        validateStatus();
        addItem(item);
    }

    public long overduedCount() {
        return rentalItems.stream()
                .filter(RentalItem::overdued)
                .count();
    }

    public Long latePointOf(Item item, LocalDate submitDate) {
        return rentalItemOf(item)
                .calculateFee(submitDate);
    }

    public RentalItem remove(Item item) {
        RentalItem rentalItem = rentalItemOf(item);
        removeItem(rentalItem);
        return rentalItem;
    }
}

package com.example.rental.domain.model;

import com.example.rental.domain.model.vo.*;

import java.util.ArrayList;
import java.util.List;

import static com.example.rental.domain.model.vo.RentStatus.*;

/**
 * Aggregate
 */
public class RentalCard {
    private final RentalCardId rentalCardId;
    private final Member member;
    private RentStatus status;
    private LateFee lateFee;
    private RentalItems rentalItems;
    private List<ReturnItem> returnItems = new ArrayList<>();

    private RentalCard(RentalCardId rentalCardId, Member member, RentStatus status, LateFee lateFee) {
        this.rentalCardId = rentalCardId;
        this.member = member;
        this.status = status;
        this.lateFee = lateFee;
        this.rentalItems = new RentalItems();
    }

    private void addReturnItem(RentalItem item) {
//        rentalItems.remove(item);
        returnItems.add(ReturnItem.create(item));
    }

    private void validateStatus() {
        if(status.equals(RENT_UNAVAILABLE)) {
            throw new IllegalArgumentException("Unavailable for rental");
        }
    }

    /**
     * Create RentalCard
     * @param member
     */
    public static RentalCard create(Member member) {
        return new RentalCard(
                RentalCardId.create(),
                member,
                RENT_AVAILABLE,
                LateFee.create()
        );
    }

    public RentalCard rentItem(Item item) {
        validateStatus();
        rentalItems.rentalItem(item);
        return this;
    }
}

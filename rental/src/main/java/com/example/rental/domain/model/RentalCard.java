package com.example.rental.domain.model;

import com.example.rental.domain.model.vo.*;

import java.time.LocalDate;

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
    private SubmitItems submitItems;

    private RentalCard(RentalCardId rentalCardId, Member member, RentStatus status, LateFee lateFee) {
        this.rentalCardId = rentalCardId;
        this.member = member;
        this.status = status;
        this.lateFee = lateFee;
        this.rentalItems = new RentalItems();
        this.submitItems = new SubmitItems();
    }

    private void validateStatus() {
        if(status.equals(RENT_UNAVAILABLE)) {
            throw new IllegalArgumentException("Unavailable for rental");
        }
    }

    /**
     * Create RentalCard
     */
    public static RentalCard create(Member member) {
        return new RentalCard(
                RentalCardId.create(),
                member,
                RENT_AVAILABLE,
                LateFee.create()
        );
    }

    public RentalCard rent(Item item) {
        validateStatus();
        rentalItems.rent(item);
        return this;
    }

    public RentalCard submit(Item item, LocalDate submitDate) {
        lateFee.addPoint(rentalItems.latePointOf(item, submitDate));
        RentalItem submitted = rentalItems.remove(item);
        submitItems.add(submitted);

        return this;
    }

    // @TODO: validate expectedReturnDate
    public RentalCard configOverdue(Item item) {
        rentalItems.configOverdue(item);
        status = RENT_UNAVAILABLE;

        return this;
    }
}

package com.example.rental.domain.model;

import com.example.rental.domain.model.vo.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

import static com.example.rental.domain.model.vo.RentStatus.*;

/**
 * Aggregate
 */
@Slf4j
public class RentalCard {
    private final RentalCardId rentalCardId;
    private final Member member;
    @Getter
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

    private void validateRentStatus() {
        if(status.equals(RENT_UNAVAILABLE)) {
            throw new IllegalStateException("Unavailable for rental");
        }
    }

    public String rentalCardId() {
        return rentalCardId.id();
    }

    public long memberId() {
        return member.id();
    }

    public String memberName() {
        return member.name();
    }

    public RentStatus rentStatus() {
        return status;
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
        validateRentStatus();
        rentalItems.rent(item);
        return this;
    }

    public RentalCard submit(Item item, LocalDate submitDate) {
        lateFee = lateFee.addPoint(rentalItems.latePointOf(item, submitDate));
        RentalItem submitted = rentalItems.remove(item);
        submitItems.add(submitted);

        return this;
    }

    public long point() {
        return lateFee.getPoint();
    }

    // @TODO: validate expectedReturnDate
    public RentalCard configOverdue(Item item) {
        rentalItems.configOverdue(item);
        status = RENT_UNAVAILABLE;

        return this;
    }

    public Long changeStatus(long point) {
        validateChangeStatus(point);
        lateFee.deductPoint(point);

        if(lateFee.isNormalStatus()) {
            log.info("change status to available");
            status = RENT_AVAILABLE;
        } else {
            log.info("status is not change");
        }

        return lateFee.getPoint();
    }

    public int rentalCount() {
        return rentalItems.rentalCount();
    }

    public int submitCount() {
        return submitItems.submitCount();
    }

    public long overduedCount() {
        return rentalItems.overduedCount();
    }

    private void validateChangeStatus(long point) {
        if(rentalItems.rentalCount() > 0) {
            throw new IllegalStateException("Have items to rent");
        }
        lateFee.validatePoint(point);
    }
}

package com.example.rental.domain.model;

import com.example.rental.domain.model.event.ItemRented;
import com.example.rental.domain.model.event.ItemSubmitted;
import com.example.rental.domain.model.event.OverdueCleared;
import com.example.rental.domain.model.vo.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.example.rental.domain.model.vo.RentStatus.*;

/**
 * Aggregate
 */
@Slf4j
@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RentalCard {
    @EmbeddedId
    private RentalCardId rentalCardId;
    @Embedded
    private User user;
    @Getter
    private RentStatus status;
    @Embedded
    private LateFee lateFee;
    @ElementCollection
    private List<RentalItem> rentalItems;
    @ElementCollection
    private List<SubmitItem> submitItems;

    private static final int MAX_RENTAL_COUNT = 5;

    private RentalCard(RentalCardId rentalCardId, User user, RentStatus status, LateFee lateFee) {
        this.rentalCardId = rentalCardId;
        this.user = user;
        this.status = status;
        this.lateFee = lateFee;
        this.rentalItems = new ArrayList<>();
        this.submitItems = new ArrayList<>();
    }

    private void validateRentStatus() {
        if(status.equals(RENT_UNAVAILABLE)) {
            throw new IllegalStateException("Unavailable for rental");
        }
        if(rentalItems.size() > MAX_RENTAL_COUNT) {
            throw new IllegalStateException("Exceed maximum number of rentals");
        }
    }

    public String rentalCardId() {
        return rentalCardId.cardId();
    }

    public long userId() {
        return user.userId();
    }

    public String userName() {
        return user.name();
    }

    public RentStatus rentStatus() {
        return status;
    }

    /**
     * Create RentalCard
     */
    public static RentalCard create(User user) {
        return new RentalCard(
                RentalCardId.create(),
                user,
                RENT_AVAILABLE,
                LateFee.create()
        );
    }

    private void addItem(Item item) {
        rentalItems.add(RentalItem.create(item));
    }

    private void removeItem(RentalItem item) {
        rentalItems.remove(item);
    }

    public RentalCard rent(Item item) {
        validateRentStatus();
        addItem(item);
        return this;
    }

    public RentalCard submit(Item item, LocalDate submitDate) {
        lateFee = lateFee.addPoint(latePointOf(item, submitDate));
        RentalItem submitted = remove(item);
        submitItems.add(SubmitItem.create(submitted));

        return this;
    }

    public long point() {
        return lateFee.point();
    }

    // @TODO: validate expectedReturnDate
    public RentalCard configOverdue(Item item) {
        rentalItemOf(item).changeOverdued(true);
        status = RENT_UNAVAILABLE;

        return this;
    }

    public Long changeStatusToAvailable(long point) {
        validateChangeStatus(point);
        lateFee.deductPoint(point);

        if(lateFee.isNormalStatus()) {
            log.info("change status to available");
            status = RENT_AVAILABLE;
        } else {
            log.info("status is not change");
        }

        return lateFee.point();
    }

    public int rentalCount() {
        return rentalItems.size();
    }

    public int submitCount() {
        return submitItems.size();
    }

    public long overduedCount() {
        return rentalItems.stream()
                .filter(RentalItem::isOverdued)
                .count();
    }

    public List<RentalItem> rentalItems() {
        return rentalItems;
    }

    public List<SubmitItem> submitItems() {
        return submitItems;
    }

    private void validateChangeStatus(long point) {
        if(rentalCount() > 0) {
            throw new IllegalStateException("Have items to rent");
        }
        lateFee.validatePoint(point);
    }

    public Long latePointOf(Item item, LocalDate submitDate) {
        return rentalItemOf(item)
                .calculateFee(submitDate);
    }

    private RentalItem rentalItemOf(Item item) {
        return rentalItems.stream()
                .filter(i -> i.getItem().equals(item))
                .findFirst()
                .get();
    }

    private RentalItem remove(Item item) {
        RentalItem rentalItem = rentalItemOf(item);
        removeItem(rentalItem);
        return rentalItem;
    }

    public static ItemRented createItemRentedEvent(User user, Item item, long point) {
        return new ItemRented(user, item, point);
    }

    public static ItemSubmitted createItemSubmittedEvent(User user, Item item, long point) {
        return new ItemSubmitted(user, item, point);
    }

    public static OverdueCleared createOverdueClearedEvent(User user, long point) {
        return new OverdueCleared(user, point);
    }
}

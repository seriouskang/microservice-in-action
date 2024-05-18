package com.example.rental.domain.model;

import com.example.rental.domain.model.vo.*;

import java.util.ArrayList;
import java.util.List;

import static com.example.rental.domain.model.vo.RentStatus.*;

public class RentalCard {
    private final RentalCardId rentalCardId;
    private final Member member;
    private RentStatus status;
    private LateFee lateFee;
    private List<RentalItem> rentalItems = new ArrayList<>();
    private List<ReturnItem> returnItems = new ArrayList<>();

    private RentalCard(RentalCardId rentalCardId, Member member, RentStatus status) {
        this.rentalCardId = rentalCardId;
        this.member = member;
        this.status = status;
    }

    public static RentalCard create(RentalCardId rentalCardId, Member member) {
        return new RentalCard(rentalCardId, member, RENT_AVAILABLE);
    }

    public void rentItem(RentalItem item) {
        this.rentalItems.add(item);
    }

    public void returnItem(RentalItem item) {
        rentalItems.remove(item);
        returnItems.add(ReturnItem.create(item));
    }
}

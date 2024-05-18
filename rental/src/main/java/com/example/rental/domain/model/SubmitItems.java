package com.example.rental.domain.model;

import com.example.rental.domain.model.vo.SubmitItem;

import java.util.ArrayList;
import java.util.List;

public class SubmitItems {
    private List<SubmitItem> submitItems;

    public SubmitItems() {
        submitItems = new ArrayList<>();
    }

    private void addSubmitItem(RentalItem item) {
        submitItems.add(SubmitItem.create(item));
    }

    public void add(RentalItem item) {
        addSubmitItem(item);
    }
}

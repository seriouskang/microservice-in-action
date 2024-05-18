package com.example.rental.domain.model.vo;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class RentalCardId {
    private final String id;

    public static RentalCardId create() {
        return new RentalCardId(createId());
    }

    private static String createId() {
        return LocalDate.now().getYear() +
                "-" +
                UUID.randomUUID();
    }

    public String id() {
        return id;
    }
}

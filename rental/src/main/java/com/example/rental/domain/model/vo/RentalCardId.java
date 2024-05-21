package com.example.rental.domain.model.vo;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RentalCardId implements Serializable {
    private String cardId;

    public static RentalCardId create() {
        return new RentalCardId(createCardId());
    }

    private static String createCardId() {
        return LocalDate.now().getYear() +
                "-" +
                UUID.randomUUID();
    }

    public String cardId() {
        return cardId;
    }
}

package com.example.rental.domain.model;

import com.example.rental.domain.model.vo.Item;
import com.example.rental.domain.model.vo.Member;
import com.example.rental.domain.model.vo.RentStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class RentalCardTest {
    @Test
    void rent() {
        // given
        Member member = new Member(1L, "test-member");
        RentalCard rentalCard = RentalCard.create(member);
        Item item1 = new Item(1000L, "test-item1");
        Item item2 = new Item(1001L, "test-item2");

        // when
        rentalCard.rent(item1);
        rentalCard.rent(item2);

        // then
        assertThat(rentalCard.rentalCount()).isEqualTo(2);
    }

    @Test
    void submit() {
        // given
        Member member = new Member(1L, "test-member");
        RentalCard rentalCard = RentalCard.create(member);
        Item item1 = new Item(1000L, "test-item1");
        Item item2 = new Item(1001L, "test-item2");

        // when
        rentalCard.rent(item1);
        rentalCard.rent(item2);
        rentalCard.submit(item1, LocalDate.now());

        // then
        assertThat(rentalCard.rentalCount()).isEqualTo(1);
    }

    @Test
    void overdue_submit() {
        // given
        Member member = new Member(1L, "test-member");
        RentalCard rentalCard = RentalCard.create(member);
        Item item1 = new Item(1000L, "test-item1");

        // when
        rentalCard.rent(item1);
        rentalCard.submit(item1, LocalDate.now().plusDays(15));

        // then
        assertThat(rentalCard.point()).isEqualTo(10);
    }

    @Test
    void configure_overdue() {
        // given
        Member member = new Member(1L, "test-member");
        RentalCard rentalCard = RentalCard.create(member);
        Item item1 = new Item(1000L, "test-item1");
        Item item2 = new Item(1001L, "test-item2");

        // when
        rentalCard.rent(item1);
        rentalCard.configOverdue(item1);

        // then
        assertThat(rentalCard.getStatus()).isEqualTo(RentStatus.RENT_UNAVAILABLE);
        assertThatThrownBy(() -> rentalCard.rent(item2))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Unavailable for rental");
    }
}
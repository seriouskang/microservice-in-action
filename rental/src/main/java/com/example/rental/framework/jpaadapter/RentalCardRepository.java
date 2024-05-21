package com.example.rental.framework.jpaadapter;

import com.example.rental.domain.model.RentalCard;
import com.example.rental.domain.model.vo.RentalCardId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentalCardRepository extends JpaRepository<RentalCard, RentalCardId> {
    @Query("select m from RentalCard m where m.user.userId = :id")
    Optional<RentalCard> findByUserId(@Param("id") Long userId);

    @Query("select m from RentalCard m where m.rentalCardId.cardId = :id")
    Optional<RentalCard> findByRentalCardId(@Param("id") String rentalCardId);
}

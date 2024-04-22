package com.thuctaptotnghiem.thuctaptotnghiep.repository;

import com.thuctaptotnghiem.thuctaptotnghiep.entity.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {

    @Query(value = "select * from histories where bookings_id = ?1", nativeQuery = true)
    Optional<HistoryEntity> getHistoryByBookingId(Long bookingId);

}

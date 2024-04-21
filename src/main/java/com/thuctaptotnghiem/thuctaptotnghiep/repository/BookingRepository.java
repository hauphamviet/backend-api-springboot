package com.thuctaptotnghiem.thuctaptotnghiep.repository;

import com.thuctaptotnghiem.thuctaptotnghiep.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

    @Query(value = "select * from bookings where users_id = ?1", nativeQuery = true)
    List<BookingEntity> findBookingByUserId(Long userId);

}

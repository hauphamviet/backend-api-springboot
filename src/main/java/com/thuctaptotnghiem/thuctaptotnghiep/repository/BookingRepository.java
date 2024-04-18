package com.thuctaptotnghiem.thuctaptotnghiep.repository;

import com.thuctaptotnghiem.thuctaptotnghiep.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
}

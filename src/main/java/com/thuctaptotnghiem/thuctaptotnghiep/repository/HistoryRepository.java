package com.thuctaptotnghiem.thuctaptotnghiep.repository;

import com.thuctaptotnghiem.thuctaptotnghiep.entity.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {
}

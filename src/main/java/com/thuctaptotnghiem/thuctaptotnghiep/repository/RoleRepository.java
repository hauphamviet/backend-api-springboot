package com.thuctaptotnghiem.thuctaptotnghiep.repository;

import com.thuctaptotnghiem.thuctaptotnghiep.entity.RoleEntity;
import com.thuctaptotnghiem.thuctaptotnghiep.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(RoleEnum role);

    boolean existsByName(String role);

}

package com.example.smart.repo;

import com.example.smart.entity.LoaiRomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LoaiRomRepo extends JpaRepository<LoaiRomEntity, Long> {
    List<LoaiRomEntity> findByDeleteFlagIsFalse();
}

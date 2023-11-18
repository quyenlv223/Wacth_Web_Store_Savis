package com.example.smart.repo;

import com.example.smart.entity.RomValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RomValueRepo extends JpaRepository<RomValueEntity, Long> {
    List<RomValueEntity> findByDeleteFlagIsFalse();
}

package com.example.smart.repo;

import com.example.smart.entity.LoaiOsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LoaiOsRepo extends JpaRepository<LoaiOsEntity,Long>{
    List<LoaiOsEntity> findByDeleteFlagIsFalse();
}

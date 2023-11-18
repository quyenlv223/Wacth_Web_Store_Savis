package com.example.smart.repo;

import com.example.smart.entity.RomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RomRepo extends JpaRepository<RomEntity, Long> {

    @Query("select o from RomEntity o where o.productEntity.id = ?1 and o.status = 'ON'")
    List<RomEntity> findByProductEntity(Long id);

    List<RomEntity> findByName(String name);

    RomEntity findByIdAndDeleteFlagIsFalse(Long id);
    List<RomEntity> findByDeleteFlagIsFalse();
}

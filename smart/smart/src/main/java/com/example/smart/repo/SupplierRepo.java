package com.example.smart.repo;

import com.example.smart.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepo extends JpaRepository<SupplierEntity, Long> {

    List<SupplierEntity> findByDeleteFlagIsFalse();

    @Query("select o from SupplierEntity  o where o.deleteFlag = false and o.status = '1'")
    List<SupplierEntity> findAll();

    Optional<SupplierEntity> findByName(String name);

    Optional<SupplierEntity> findByEmail(String email);

    Optional<SupplierEntity> findByPhoneNumber(String phoneNumber);

    Optional<SupplierEntity> findByIdAndDeleteFlagIsFalse(Long id);
}

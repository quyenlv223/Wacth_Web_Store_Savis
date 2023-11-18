package com.example.smart.repo;

import com.example.smart.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findById(Long id);

    CustomerEntity findByPhoneNumber(String phone);

//    List<CustomerEntity> findPhone(String phoneNumer);

//    CustomerEntity findByPhone1(String phone);
}

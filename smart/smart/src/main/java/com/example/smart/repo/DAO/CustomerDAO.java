package com.example.smart.repo.DAO;

import com.example.smart.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDAO extends JpaRepository<CustomerEntity, Integer> {

    List<CustomerEntity> findByPhoneNumber(String phoneNumer);
}
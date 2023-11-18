package com.example.smart.repo;

import com.example.smart.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrdersRepo extends JpaRepository<OrdersEntity, Long> {

    List<OrdersEntity> findByDeleteFlagIsFalseOrderByCreateDateDesc();


    OrdersEntity findByCodeOrderAndDeleteFlagIsFalse(String id);
}

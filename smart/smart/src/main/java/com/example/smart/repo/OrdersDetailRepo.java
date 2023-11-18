package com.example.smart.repo;


import com.example.smart.entity.OrdersDetailEntity;
import com.example.smart.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrdersDetailRepo extends JpaRepository<OrdersDetailEntity, Long> {
    List<OrdersDetailEntity> findByDeleteFlagIsFalseAndOrdersEntity(OrdersEntity ordersEntity);

    OrdersDetailEntity findByDeleteFlagIsTrueAndId(Long id);

    OrdersDetailEntity findByDeleteFlagIsFalseAndId(Long id);

}

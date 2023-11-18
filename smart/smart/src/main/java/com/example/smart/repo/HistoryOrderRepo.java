package com.example.smart.repo;

import com.example.smart.entity.OrderHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryOrderRepo extends JpaRepository<OrderHistoryEntity, Long> {

}

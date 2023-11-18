package com.example.smart.repo;

import com.example.smart.dto.respone.ThongKeDto;
import com.example.smart.entity.OrdersDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ThongKeRepo extends JpaRepository<OrdersDetailEntity, Long> {
    @Query(name = "thong_ke", nativeQuery = true)
    List<ThongKeDto> findStockAkhirPerProductIn(
            @Param("month") Integer month,
            @Param("year") Integer year
    );
}


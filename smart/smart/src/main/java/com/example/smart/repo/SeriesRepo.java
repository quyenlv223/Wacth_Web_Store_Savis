package com.example.smart.repo;

import com.example.smart.entity.SeriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SeriesRepo extends JpaRepository<SeriesEntity, Long> {
    @Query("select count(o) from SeriesEntity o where o.deleteFlag = false and o.propertyProductId = ?1 and o.status = '0'")
    Long countByPropertyProductId(Long id); // count chua ban

    @Query("select o from SeriesEntity o where o.deleteFlag = false and o.propertyProductId = ?1 and o.status = '0'")
    List<SeriesEntity> findByDeleteFlagIsFalseAndPropertyProductId(Long productId);

    List<SeriesEntity> findByDeleteFlagIsFalseAndPropertyProductIdAndOrderDetailId(Long id, Long idOrder);


    @Query("select o from SeriesEntity o where o.deleteFlag = false and o.orderDetailId = ?1 and o.status = '2'")
    List<SeriesEntity> findByOrder(Long productId);


    @Query("select o from SeriesEntity o where o.deleteFlag = false and o.propertyProductId = ?1 and o.orderDetailId = ?2")
    List<SeriesEntity> findImeiDaBan(Long productId, Long idOrder);


    @Query("select o from SeriesEntity o where o.deleteFlag = false and o.id = ?1")
    SeriesEntity findByIdAndDeleteFlagIsFalse(Long id);
}

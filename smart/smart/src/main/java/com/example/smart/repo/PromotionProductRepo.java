package com.example.smart.repo;

import com.example.smart.entity.PromotionProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionProductRepo extends JpaRepository<PromotionProductEntity, Long> {




    List<PromotionProductEntity> findByPromotionIdAndDeleteFlagIsFalse(String id);

    List<PromotionProductEntity> findAllByProductIdInAndDeleteFlagFalse(List<String> productIds);

    List<PromotionProductEntity> findByProductIdAndDeleteFlagFalse(String productId);

    List<PromotionProductEntity> findAllByPromotionIdInAndDeleteFlagFalse(List<String> promotionIds);
}

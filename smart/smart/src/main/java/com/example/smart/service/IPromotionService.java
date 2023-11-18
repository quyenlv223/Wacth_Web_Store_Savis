package com.example.smart.service;



import com.example.smart.dto.request.promotion.PromotionRequestDTO;
import com.example.smart.dto.respone.promotion.PromotionResponseDTO;

import java.util.List;

public interface IPromotionService {

    List<PromotionResponseDTO> getAll();

    PromotionResponseDTO findById(String id);


    String create(PromotionRequestDTO promotionRequestDTO);

    PromotionResponseDTO findByProductId(String productId);

    String update(PromotionRequestDTO promotionRequestDTO);

    void delete(Long id);

    double getPromotionProduct(String id);

    void activePromotion(String id);

    void inActivePromotion(String id);
}

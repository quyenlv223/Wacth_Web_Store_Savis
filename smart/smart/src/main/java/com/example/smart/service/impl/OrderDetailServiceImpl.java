package com.example.smart.service.impl;

import com.example.smart.entity.OrdersDetailEntity;
import com.example.smart.entity.ProductDetailStatusEntity;
import com.example.smart.repo.OrdersDetailRepo;
import com.example.smart.repo.OrdersRepo;
import com.example.smart.repo.ProductDetailStatusRepo;
import com.example.smart.service.IOrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements IOrderDetailService {
    private final OrdersDetailRepo detailRepo;

    private final OrdersRepo ordersRepo;

    private  final ProductDetailStatusRepo propertyProductRepo;
    @Override
    public String deleteDetail(Long id, String codeOrder) {
        OrdersDetailEntity detailEntity = detailRepo.findByDeleteFlagIsFalseAndId(id);
        detailEntity.setDeleteFlag(true);
        ProductDetailStatusEntity entity = propertyProductRepo.findById(detailEntity.getIdPropertyProduct()).get();
        entity.setQuantity(detailEntity.getQuantity() + entity.getQuantity());
        propertyProductRepo.save(entity);
        detailRepo.save(detailEntity);
        return "ok";
    }
}

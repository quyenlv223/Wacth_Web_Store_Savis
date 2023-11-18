package com.example.smart.scheduing;

import com.example.smart.constant.ConstansStatus;
import com.example.smart.entity.ProductDetailStatusEntity;
import com.example.smart.entity.ProductEntity;
import com.example.smart.entity.PromotionEnity;
import com.example.smart.entity.RomEntity;
import com.example.smart.repo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Slf4j
public class PromotionJob {
    @Autowired
    private PromotionRepo promotionRepo;

    @Autowired
    private PromotionProductRepo promotionProductRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private RomRepo romRepo;

    @Autowired
    private ProductDetailStatusRepo propertyProductRepo;

//    @Scheduled(cron = "${worldphone.schedule.promotion.cron}")
//    public void jobQuetPromotion(){
//        log.info("Start Job Promotion");
//        List<PromotionEnity> promotionEnityList = promotionRepo.findByDay();
//        if(promotionEnityList.size() > 0){
//            for (PromotionEnity promotion: promotionEnityList
//            ) {
//                List<PromotionProductEntity> promotionProductEntity = promotionProductRepo.findByPromotionIdAndDeleteFlagIsFalse(String.valueOf(promotion.getId()));
//                if(promotionProductEntity.size() == 0){
//                    List<ProductEntity> productEntities = productRepo.findAll();
//                    for (ProductEntity product: productEntities
//                    ) {
//                        changePrice(promotion, product);
//                    }
//
//                }else {
//                    for (PromotionProductEntity promotionProduct: promotionProductEntity
//                    ) {
//                        ProductEntity productEntity = productRepo.findByIdAndDeleteFlagIsFalse(Long.valueOf(promotionProduct.getProductId()));
//                        changePrice(promotion, productEntity);
//                    }
//                }
//            }
//        }else {
//            List<ProductPropertyEntity> productPropertyEntityList = propertyProductRepo.findByDeleteFlagIsFalse();
//            productPropertyEntityList.forEach(e -> {
//                e.setPricePromotion(0);
//                e.setPromotionId(0);
//            });
//            propertyProductRepo.saveAll(productPropertyEntityList);
//
//        }
//
//        log.info("End Job Promotion");
//    }

    private void changePrice(PromotionEnity promotion, ProductEntity productEntity) {
        List<RomEntity> romEntities = romRepo.findByProductEntity(productEntity.getId());
        for (RomEntity rom: romEntities
        ) {
            List<ProductDetailStatusEntity> productPropertyEntityList = propertyProductRepo.findByRom(rom.getId());
            for (ProductDetailStatusEntity propertyEntity: productPropertyEntityList
            ) {
                if(promotion.getTypeDiscount().equals(ConstansStatus.PERCENT) && propertyEntity.getPrice() > 0){
                    Long oldPrice = propertyEntity.getPrice();
                    Long khuyenMaiPrice = oldPrice / 100 * promotion.getDiscount();
                    if(oldPrice - khuyenMaiPrice > propertyEntity.getPricePromotion()){
                        propertyEntity.setPricePromotion(oldPrice - khuyenMaiPrice);
                        propertyEntity.setPromotionId(promotion.getId());
                        propertyProductRepo.save(propertyEntity);
                    }

                }
                if(promotion.getTypeDiscount().equals(ConstansStatus.MONEY) && propertyEntity.getPrice() > 0 && propertyEntity.getPrice() > promotion.getDiscount()){
                    Long oldPrice = propertyEntity.getPrice();
                    if(oldPrice - promotion.getDiscount() > propertyEntity.getPricePromotion()){
                        propertyEntity.setPricePromotion(oldPrice - promotion.getDiscount());
                        propertyEntity.setPromotionId(promotion.getId());
                        propertyProductRepo.save(propertyEntity);
                    }

                }
            }
        }
    }
}

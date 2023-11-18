package com.example.smart.service.impl;


import com.example.smart.common.StatusImei;
import com.example.smart.constant.ConstansErrorCode;
import com.example.smart.constant.ConstansStatus;
import com.example.smart.dto.request.product_detail.ProductPropertyRequest;
import com.example.smart.dto.respone.imei.ImeiResponse;
import com.example.smart.dto.respone.order_detail.OrderDetailRespone;
import com.example.smart.dto.respone.product.ProductPropertyRespone;
import com.example.smart.entity.ProductDetailStatusEntity;
import com.example.smart.entity.ProductEntity;
import com.example.smart.entity.SeriesEntity;
import com.example.smart.exception.SmartExp;
import com.example.smart.repo.ColorRepo;
import com.example.smart.repo.ProductDetailStatusRepo;
import com.example.smart.repo.RomRepo;
import com.example.smart.repo.SeriesRepo;
import com.example.smart.service.IProductDetailStatusService;
import com.example.smart.until.ConvertUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductDetailStatusServiceImpl implements IProductDetailStatusService {
    private final ProductDetailStatusRepo repo;
    private final ConvertUtil convertUtil;

    private final SeriesRepo imeiRepo;

    private final ProductDetailStatusRepo propertyProductRepo;

    private final ColorRepo colorRepo;
    private final RomRepo romRepo;

    @Override
    public List<ProductPropertyRespone> findByRom(Long id) {
        List<ProductPropertyRespone> respones = new ArrayList<>();
        List<ProductDetailStatusEntity> entityList = repo.findByRom(id);
        for (ProductDetailStatusEntity a: entityList
             ) {
            respones.add(mapToDto(a));
        }
        return respones;
    }

    @Override
    public OrderDetailRespone findById(Long id) {
        OrderDetailRespone detailRespone = new OrderDetailRespone();
        ProductDetailStatusEntity propertyEntity = propertyProductRepo.getById(id);
        detailRespone.setIdProductProperty(String.valueOf(propertyEntity.getId()));
        ProductEntity productEntity = propertyEntity.getRomEntity().getProductEntity();
        detailRespone.setColorProduct(propertyEntity.getColorEntity().getValueColor());
        detailRespone.setRomProduct(propertyEntity.getRomEntity().getName());
        detailRespone.setNameProduct(productEntity.getName());
        detailRespone.setImageProduct(productEntity.getImage_key());
        detailRespone.setTonKho(String.valueOf(propertyEntity.getQuantity()));
        if(propertyEntity.getPricePromotion() > 0){
            detailRespone.setGiaBan(propertyEntity.getPricePromotion());
        }else {
            detailRespone.setGiaBan(propertyEntity.getPrice());
        }
        detailRespone.setGiaBanString(convertUtil.moneyToStringFormat(detailRespone.getGiaBan()));
        detailRespone.setTongTien(detailRespone.getGiaBan());
        detailRespone.setTongTienString(convertUtil.moneyToStringFormat(detailRespone.getGiaBan()));
        detailRespone.setStatusProduct(propertyEntity.getQuantity() == 0 ? "0" : "1");
        return detailRespone;
    }

    @Override
    public List<ProductPropertyRespone> findByRomAndColor(String romId, String colorId) {
        List<ProductPropertyRespone> respones = new ArrayList<>();
        List<ProductDetailStatusEntity> entityList = repo.findByRomAndColor(Long.valueOf(romId), Long.valueOf(colorId));
        for (ProductDetailStatusEntity a: entityList
        ) {
            respones.add(mapToDto(a));
        }
        return respones;
    }

    @Override
    public List<ProductPropertyRespone> findByColor(String colorId) {
        List<ProductPropertyRespone> respones = new ArrayList<>();
        List<ProductDetailStatusEntity> entityList = repo.findByColor(Long.valueOf(colorId));
        for (ProductDetailStatusEntity a: entityList
        ) {
            respones.add(mapToDto(a));
        }
        return respones;
    }


    @Override
    public ProductPropertyRespone updateProductProperty(ProductPropertyRequest request) {
        List<ProductDetailStatusEntity> entityList = repo.findByRomAndColor(Long.valueOf(request.getRomId()), Long.valueOf(request.getColorId()));
        if (!entityList.isEmpty()) {
            ProductDetailStatusEntity entity = entityList.get(0);
            entity.setPrice(Long.parseLong(request.getPrice()));
            entity.setQuantity(Long.parseLong(request.getQuantity()));
            ProductPropertyRespone respone = mapToDto(entity);
            try {
                repo.save(entity);
            } catch (Exception e) {
                log.info(e.getMessage());
                return null;
            }
            return respone;
        } else {
            ProductDetailStatusEntity entity = new ProductDetailStatusEntity();
            entity.setColorEntity(colorRepo.findByIdAndDeleteFlagIsFalse(Long.valueOf(request.getColorId())));
            entity.setRomEntity(romRepo.findByIdAndDeleteFlagIsFalse(Long.valueOf(request.getRomId())));
            entity.setStatus("OFF");
            entity.setDeleteFlag(false);
            entity.setCreateDate(new Timestamp(System.currentTimeMillis()));
            entity.setModifierDate(new Timestamp(System.currentTimeMillis()));
            repo.save(entity);
            entity.setPrice(Long.parseLong(request.getPrice()));
            entity.setQuantity(Long.parseLong(request.getQuantity()));
            ProductPropertyRespone respone = mapToDto(entity);
            try {
                repo.save(entity);
            } catch (Exception e) {
                log.info(e.getMessage());
                return null;
            }
            return respone;
        }
    }

    @Override
    public String udpateStatusProductProperty(ProductPropertyRequest request) {

        List<ProductDetailStatusEntity> entityList = repo.findByRomAndColor(Long.valueOf(request.getRomId()), Long.valueOf(request.getColorId()));
        if(entityList.size() < 1){
            log.error(String.valueOf(new SmartExp(ConstansErrorCode.ROM_NOT_EXIST).getErrorMessage().getVn()));
            return "false";
        }
        if(entityList.get(0).getPrice() == 0){
            return "false";
        }
        if(request.getStatus().equalsIgnoreCase("Ngừng kinh doanh")){
            entityList.get(0).setStatus(ConstansStatus.OFF);
        }else {
            entityList.get(0).setStatus(ConstansStatus.ON);
        }

        repo.save(entityList.get(0));
        return "ok";
    }


    @Override
    public boolean addSeries(String romId, String colorId, List<String> imei) {
        try {
            List<ProductDetailStatusEntity> entityList = repo.findByRomAndColor(Long.valueOf(romId), Long.valueOf(colorId));
            for (ProductDetailStatusEntity a: entityList
            ) {
                long quantity = a.getQuantity();
                List<SeriesEntity> imeiEntityList = imeiRepo.findByDeleteFlagIsFalseAndPropertyProductId(a.getId());
                List<String> imeiValue = imeiEntityList.stream().map(SeriesEntity:: getValue).collect(Collectors.toList());
                imeiValue.addAll(imei);
                Set<String> setImei = new HashSet<>(imeiValue);
                int sizeImei = (int) (quantity - imeiEntityList.size());
                for (String ime: setImei
                ) {
                    int check  = 0;
                    for (SeriesEntity entity:imeiEntityList
                         ) {
                        if(ime.equals(entity.getValue())){
                            check = 1;
                            break;
                        }
                    }
                    if(sizeImei > 0 && check == 0){
                        SeriesEntity imeiEntity = new SeriesEntity();
                        imeiEntity.setValue(ime);
                        imeiEntity.setPropertyProductId(a.getId());
                        imeiEntity.setStatus(StatusImei.CHUA_BAN.getValue());
                        imeiRepo.save(imeiEntity);
                        sizeImei--;
                    }


                }
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }


    private ProductPropertyRespone mapToDto(ProductDetailStatusEntity entity){
        if(entity == null){
            return null;
        }else {
            Long imei = imeiRepo.countByPropertyProductId(entity.getId());
            ProductPropertyRespone dto = new ProductPropertyRespone();
            dto.setColorName(entity.getColorEntity().getValueColor());
            dto.setRomId(String.valueOf(entity.getRomEntity().getId()));
            dto.setColorId(String.valueOf(entity.getColorEntity().getId()));
            dto.setId(String.valueOf(entity.getId()));
            dto.setPrice(entity.getPrice());
            dto.setQuantity(entity.getQuantity());
            dto.setStatus(entity.getStatus());
            dto.setPriceString(convertUtil.moneyToStringFormat(entity.getPrice()));
            dto.setPricePromotion(entity.getPricePromotion());
            dto.setPricePromotionString(convertUtil.moneyToStringFormat(entity.getPricePromotion()));
            dto.setCountImei(imei == null ? 0L : imei);
            List<SeriesEntity> list = imeiRepo.findByDeleteFlagIsFalseAndPropertyProductId(entity.getId());
            dto.setImeiResponses(list.stream().map(this::mapToImei).collect(Collectors.toList()));
            return dto;
        }
    }

    private ImeiResponse mapToImei(SeriesEntity imeiEntity){
        return new ImeiResponse(String.valueOf(imeiEntity.getId()), imeiEntity.getValue());
    }
}

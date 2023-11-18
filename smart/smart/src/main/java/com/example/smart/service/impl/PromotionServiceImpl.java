package com.example.smart.service.impl;

import com.example.smart.dto.request.promotion.PromotionRequestDTO;
import com.example.smart.dto.respone.promotion.PromotionResponseDTO;
import com.example.smart.entity.PromotionEnity;
import com.example.smart.entity.PromotionProductEntity;
import com.example.smart.repo.PromotionProductRepo;
import com.example.smart.repo.PromotionRepo;
import com.example.smart.service.IPromotionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PromotionServiceImpl implements IPromotionService {
    private final PromotionRepo repo;
    private final PromotionProductRepo promotionProductRepo;

    @Override
    public List<PromotionResponseDTO> getAll() {
        return repo.findAllByDeleteFlagFalseOrderByCreateDateDesc().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public PromotionResponseDTO findById(String id) {
        return null;
    }

    @Override
    public String create(PromotionRequestDTO promotionRequestDTO) {
        try {
            PromotionEnity enity = new PromotionEnity();
            enity = mapToEntity(promotionRequestDTO, enity);
            enity.setStatus(false);
            enity.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
            enity.setModifierDate(Timestamp.valueOf(LocalDateTime.now()));
            enity = repo.save(enity);
        }catch (Exception e){
            log.error("error create promotion : {}", e.getMessage());
            return "false";
        }


        return "ok";
    }

    @Override
    public PromotionResponseDTO findByProductId(String productId) {
        return null;
    }

    @Override
    public String update(PromotionRequestDTO promotionRequestDTO) {
        try {
            PromotionEnity enity = repo.findByIdAndDeleteFlagFalse(Long.valueOf(promotionRequestDTO.getId()));
            if(enity == null){
                return "false";
            }
            enity = mapToEntity(promotionRequestDTO, enity);
            enity = repo.save(enity);
        }catch (Exception e){
            log.error("error update promotion : {}", e.getMessage());
            return "false";
        }

        return "ok";
    }

    @Override
    public void delete(Long id) {
            PromotionEnity enity = repo.findByIdAndDeleteFlagFalse(id);
            if(enity != null){
                enity.setDeleteFlag(true);
                repo.save(enity);
            }
    }

    @Override
    public double getPromotionProduct(String id) {
        return 0;
    }

    @Override
    public void activePromotion(String id) {
        PromotionEnity enity = repo.findByIdAndDeleteFlagFalse(Long.valueOf(id));
        if(enity != null){
            enity.setStatus(true);
            repo.save(enity);
        }

    }

    @Override
    public void inActivePromotion(String id) {
        PromotionEnity enity = repo.findByIdAndDeleteFlagFalse(Long.valueOf(id));
        if(enity != null){
            enity.setStatus(false);
            repo.save(enity);
        }
    }

    private PromotionEnity mapToEntity (PromotionRequestDTO promotionRequestDTO, PromotionEnity enity) throws ParseException {
        enity.setDescription(promotionRequestDTO.getDescription());
        enity.setDiscount(promotionRequestDTO.getDiscount());
        enity.setEndDate(convertToString(promotionRequestDTO.getEndDate()));
        enity.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        enity.setModifierDate(Timestamp.valueOf(LocalDateTime.now()));
        enity.setStartDate(convertToString(promotionRequestDTO.getStartDate()));
        enity.setNote(promotionRequestDTO.getNote());
        enity.setTypeDiscount(promotionRequestDTO.getTypeDiscount());
        enity.setName(promotionRequestDTO.getName());
        if(promotionRequestDTO.getProductIds().size() == 0){
            return enity;
        }

        for (String id : promotionRequestDTO.getProductIds()) {
            PromotionProductEntity promotionProductEntity = new PromotionProductEntity();
            promotionProductEntity.setPromotionId(String.valueOf(enity.getId()));
            promotionProductEntity.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
            promotionProductEntity.setModifierDate(Timestamp.valueOf(LocalDateTime.now()));
            promotionProductEntity.setProductId(id);
            promotionProductRepo.save(promotionProductEntity);
        }
        return enity;
    }
    private Date convertToString(Date date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        Date date1 =  new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        return date1;
    }

    private PromotionResponseDTO mapToDto(PromotionEnity enity){
        PromotionResponseDTO responseDTO = new PromotionResponseDTO();
        responseDTO.setId(String.valueOf(enity.getId()));
        responseDTO.setName(enity.getName());
        responseDTO.setDescription(enity.getDescription());
        responseDTO.setNote(enity.getNote());
        responseDTO.setStatus(enity.getStatus());
        responseDTO.setDiscount(enity.getDiscount());
        responseDTO.setEndDate(enity.getEndDate());
        responseDTO.setStartDate(enity.getStartDate());
        responseDTO.setTypeDiscount(enity.getTypeDiscount());
        List<String> idProduct = new ArrayList<>();
        List<PromotionProductEntity> entityList = promotionProductRepo.findByPromotionIdAndDeleteFlagIsFalse(String.valueOf(enity.getId()));
        idProduct = entityList.stream().map(PromotionProductEntity::getProductId).collect(Collectors.toList());
        responseDTO.setIdProduct(idProduct);
        return responseDTO;
    }
}

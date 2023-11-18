package com.example.smart.service.impl;

import com.example.smart.dto.request.attribute.AttributeRequestAdd;
import com.example.smart.dto.request.attribute.AttributeRequestEdit;
import com.example.smart.dto.respone.attribute.AttributeRespone;
import com.example.smart.entity.AttributeProductEntity;
import com.example.smart.repo.*;
import com.example.smart.service.AttributeProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Service
@Slf4j
@RequiredArgsConstructor
public class AttributeProductServiceImpl implements AttributeProductService {
    private final AttributeProductRepo repo;
    private final RamRepo ramRepo;
    private final ChipRepo chipRepo;
    private final OsRepo osRepo;
    private final PinRepo pinRepo;
    private final ScreenRepo screenRepo;


    @Override
    public AttributeRespone findByProduct(Long id) {
        AttributeProductEntity entity = repo.findByProductId(id);
        if(entity == null){
            return null;
        }
        AttributeRespone respone = new AttributeRespone();
        respone.setRam(String.valueOf(entity.getRamID()));
        respone.setChip(String.valueOf(entity.getChipId()));
        respone.setOperatingSystem(String.valueOf(entity.getOsId()));
        respone.setPin(String.valueOf(entity.getPinId()));
        respone.setScreen(String.valueOf(entity.getScreenId()));
        return respone;
    }

    @Override
    public String saveAttribute(AttributeRequestAdd requestAdd, Long productId) {
        AttributeProductEntity entity = new AttributeProductEntity();
        entity.setChipId(Long.valueOf(requestAdd.getChip()));
        entity.setOsId(Long.valueOf(requestAdd.getOperatingSystem()));
        entity.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        entity.setModifierDate(Timestamp.valueOf(LocalDateTime.now()));
        entity.setPinId(Long.valueOf(requestAdd.getPin()));
        entity.setRamID(Long.valueOf(requestAdd.getRam()));
        entity.setScreenId(Long.valueOf(requestAdd.getScreen()));
        entity.setProductId(productId);
       try {
           repo.save(entity);
       }catch (Exception e){
           return "false";
       }
        return "ok";
    }

    @Override
    public String updateAttribute(AttributeRequestEdit attributeRequestEdit , Long productId) {
        AttributeProductEntity entity = repo.findByProductId(productId);
        entity.setScreenId(Long.valueOf(attributeRequestEdit.getScreen()));
        entity.setRamID(Long.valueOf(attributeRequestEdit.getRam()));
        entity.setPinId(Long.valueOf(attributeRequestEdit.getPin()));
        entity.setOsId(Long.valueOf(attributeRequestEdit.getOperatingSystem()));
        entity.setChipId(Long.valueOf(attributeRequestEdit.getChip()));
        try {
            repo.save(entity);
        }catch (Exception e){
            return "false";
        }
        return "ok";
    }
}

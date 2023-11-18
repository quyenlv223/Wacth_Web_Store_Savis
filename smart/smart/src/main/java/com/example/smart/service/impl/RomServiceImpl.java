package com.example.smart.service.impl;

import com.example.smart.constant.ConstansErrorCode;
import com.example.smart.dto.request.attribute.rom.RomRequest;
import com.example.smart.dto.request.rom.RomRequestAdd;
import com.example.smart.dto.respone.attribute.ram.RamRespone;
import com.example.smart.dto.respone.rom.RomRespone;
import com.example.smart.entity.ProductEntity;
import com.example.smart.entity.RomEntity;
import com.example.smart.exception.SmartExp;
import com.example.smart.repo.RomRepo;
import com.example.smart.service.IProductDetailStatusService;
import com.example.smart.service.IRomService;
import com.example.smart.until.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RomServiceImpl implements IRomService {
    private final RomRepo romRepo;

    private final IProductDetailStatusService productPropertyService;
    private final SessionUtil sessionUtil;


    @Override
    public List<RomRespone> findByProduct(Long id) {
        List<RomEntity> romEntityList = romRepo.findByProductEntity(id);
        List<RomRespone> romRespones = new ArrayList<>();
        for (RomEntity r: romEntityList
             ) {
            romRespones.add(new RomRespone(String.valueOf(r.getId()), r.getName(), null));
        }
        return romRespones;
    }

    @Override
    public String createRom(List<RomRequestAdd> romRequestAdds, ProductEntity entityPro) {
        for (RomRequestAdd a: romRequestAdds
             ) {
            RomEntity entity = mapToEntity(a);
            entity.setProductEntity(entityPro);
            romRepo.save(entity);
        }
        return "ok";
    }

    @Override
    public String createRomWithProductEdit(List<RomRequestAdd> romRequestAdds, ProductEntity entity) {
        List<RomEntity> entities = romRepo.findByProductEntity(entity.getId());
        for (RomRequestAdd a: romRequestAdds
        ) {
            int check = 0;
            for (RomEntity e: entities
                 ) {
                if(a.getNameRom().equals(e.getName())){
                    check = 1;
                    break;
                }
            }
            if(check == 0){
                RomEntity entity1 = mapToEntity(a);
                entity1.setProductEntity(entity);
                romRepo.save(entity1);
            }
        }
        return "ok";
    }

    @Override
    public List<RomRespone> findAll() {
        List<RomRespone> list = new ArrayList<>();
        List<RomEntity> entities = romRepo.findByDeleteFlagIsFalse();
        for (RomEntity e: entities
        ) {
            list.add(new RomRespone(String.valueOf(e.getId()), e.getName(), null));
        }
        return list;
    }

    @Override
    public String saveRom(RomRequest request) {
        RomEntity entity = new RomEntity();
        entity.setName(request.getName());
        entity.setCreateBy("ADMIN");
        entity.setCreateDate(new Timestamp(System.currentTimeMillis()));
        entity.setModifierDate(new Timestamp(System.currentTimeMillis()));
        entity.setModifierBy("ADMIN");
        entity.setDeleteFlag(false);
        romRepo.save(entity);
        return "ok";
    }

    @Override
    public String editRom(RomRequest request) {
        RomEntity entity = romRepo.getById(request.getId());
        entity.setName(request.getName());
        romRepo.save(entity);
        return "ok";
    }

    @Override
    public RamRespone findById(String id) {
        RomEntity entity = romRepo.getById(Long.valueOf(id));
        return new RamRespone(entity.getId(), entity.getName());
    }

    @Override
    public String deleteRom(Long id) {
        RomEntity entity = romRepo.getById(id);
        entity.setDeleteFlag(true);
        romRepo.save(entity);
        return "ok";

    }

    private RomRespone mapToDTO(RomEntity romEntity){
        if(romEntity == null){
            throw new SmartExp(ConstansErrorCode.ROM_NOT_EXIST);
        } else {
            RomRespone respone = new RomRespone();
            respone.setName(romEntity.getName());
            respone.setProductPropertyResponeList(productPropertyService.findByRom(romEntity.getId()));
            return respone;
        }
    }

    public  RomEntity mapToEntity(RomRequestAdd a){
        RomEntity entity = new RomEntity();
        entity.setStatus("ON");
        entity.setCreateBy("ADMIN");
        entity.setCreateDate(new Timestamp(System.currentTimeMillis()));
        entity.setModifierDate(new Timestamp(System.currentTimeMillis()));
        entity.setModifierBy((String) sessionUtil.getObject("username"));
        entity.setDeleteFlag(false);
        entity.setName(a.getNameRom());
        return entity;
    }
}

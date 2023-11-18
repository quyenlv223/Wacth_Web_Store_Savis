package com.example.smart.service.impl;

import com.example.smart.dto.request.attribute.os.OsRequest;
import com.example.smart.dto.respone.attribute.os.OsRespone;
import com.example.smart.entity.LoaiOsEntity;
import com.example.smart.entity.OSEntity;
import com.example.smart.repo.OsRepo;
import com.example.smart.service.IOsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OsServiceImpl implements IOsService {
    private final OsRepo repo;

    @Override
    public List<OsRespone> findAll() {
        List<OSEntity> entities = repo.findByDeleteFlagIsFalse();
        List<OsRespone> list = new ArrayList<>();
        for (OSEntity e : entities) {
            list.add(new OsRespone(e.getId(), e.getName(), e.getLoaiOsEntity().getId()));
        }
        return list;
    }

    @Override
    public String save(OsRequest request) {
        OSEntity entity = new OSEntity();
        LoaiOsEntity loaiOsEntity = new LoaiOsEntity();
        loaiOsEntity.setId(request.getLoaiOsId());
        entity.setId(request.getId());
        entity.setLoaiOsEntity(loaiOsEntity);
        entity.setName(request.getName());
        entity.setCreateBy("ADMIN");
        entity.setCreateDate(new Timestamp(System.currentTimeMillis()));
        entity.setModifierDate(new Timestamp(System.currentTimeMillis()));
        entity.setModifierBy("ADMIN");
        entity.setDeleteFlag(false);
        repo.save(entity);
        return "ok";
    }

    @Override
    public String edit(OsRequest request) {
        OSEntity entity = repo.getById(request.getId());
//        entity.setLoai(request.getLoai());
        LoaiOsEntity loaiOsEntity = new LoaiOsEntity();
        loaiOsEntity.setId(request.getLoaiOsId());
        entity.setLoaiOsEntity(loaiOsEntity);
        entity.setName(request.getName());
        repo.save(entity);
        return "ok";
    }

    @Override
    public OsRespone findById(String id) {
        OSEntity entity = repo.getById(Long.valueOf(id));
        return new OsRespone(entity.getId(), entity.getName(), entity.getLoaiOsEntity().getId());
    }

    @Override
    public String delete(Long id) {
        OSEntity entity = repo.getById(id);
        entity.setDeleteFlag(true);
        repo.save(entity);
        return "ok";
    }
}

package com.example.smart.service.impl;

import com.example.smart.dto.request.attribute.loai_os.Loai_OsRequest;
import com.example.smart.dto.respone.attribute.loai_os.Loai_OsRespone;
import com.example.smart.entity.LoaiOsEntity;
import com.example.smart.repo.LoaiOsRepo;
import com.example.smart.service.ILoaiOsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class Loai_OsServiceImpl implements ILoaiOsService {
    private final LoaiOsRepo repo;


    @Override
    public List<Loai_OsRespone> findAll() {
        List<Loai_OsRespone> list = new ArrayList<>();
        List<LoaiOsEntity> entities = repo.findByDeleteFlagIsFalse();
        for (LoaiOsEntity e : entities
        ) {
            list.add(new Loai_OsRespone(String.valueOf(e.getId()), e.getName()));
        }
        return list;
    }

    @Override
    public Loai_OsRespone findById(String id) {
        LoaiOsEntity loaios = repo.getById(Long.valueOf(id));
        return new Loai_OsRespone(String.valueOf(loaios.getId()), loaios.getName());
    }

    @Override
    public String createLoaiOs(Loai_OsRequest request) {
        LoaiOsEntity loaios = new LoaiOsEntity();
        loaios.setName(request.getName());
        repo.save(loaios);
        return "ok";
    }

    @Override
    public String updateLoaiOs(Loai_OsRequest request) {
        LoaiOsEntity loaios = new LoaiOsEntity();
        loaios.setName(request.getName());
        loaios.setId(Long.valueOf(request.getId()));
        repo.save(loaios);
        return "ok";
    }

    @Override
    public String deleteLoaiOs(Long id) {
        LoaiOsEntity loaios = repo.getById(id);
        loaios.setDeleteFlag(true);
        repo.save(loaios);
        return "ok";
    }
}

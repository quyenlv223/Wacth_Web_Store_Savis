package com.example.smart.service.impl;



import com.example.smart.dto.request.attribute.loai_rom.Loai_RomRequest;
import com.example.smart.dto.respone.attribute.loai_rom.Loai_RomRespone;
import com.example.smart.entity.LoaiRomEntity;
import com.example.smart.repo.LoaiRomRepo;
import com.example.smart.service.ILoaiRomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class Loai_RomServiceImpl implements ILoaiRomService {
    private final LoaiRomRepo repo;


    @Override
    public List<Loai_RomRespone> findAll() {
        List<Loai_RomRespone> list = new ArrayList<>();
        List<LoaiRomEntity> entities = repo.findByDeleteFlagIsFalse();
        for (LoaiRomEntity e : entities
        ) {
            list.add(new Loai_RomRespone(String.valueOf(e.getId()), e.getName()));
        }
        return list;
    }

    @Override
    public Loai_RomRespone findById(String id) {
        LoaiRomEntity loairom = repo.getById(Long.valueOf(id));
        return new Loai_RomRespone(String.valueOf(loairom.getId()), loairom.getName());
    }

    @Override
    public String createLoaiRom(Loai_RomRequest request) {
        LoaiRomEntity loairom = new LoaiRomEntity();
        loairom.setName(request.getName());
        repo.save(loairom);
        return "ok";
    }

    @Override
    public String updateLoaiRom(Loai_RomRequest request) {
        LoaiRomEntity loairom = new LoaiRomEntity();
        loairom.setName(request.getName());
        loairom.setId(Long.valueOf(request.getId()));
        repo.save(loairom);
        return "ok";
    }

    @Override
    public String deleteLoaiRom(Long id) {
        LoaiRomEntity loairom = repo.getById(id);
        loairom.setDeleteFlag(true);
        repo.save(loairom);
        return "ok";
    }
}

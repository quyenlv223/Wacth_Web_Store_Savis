package com.example.smart.service.impl;

import com.example.smart.dto.request.attribute.pin.PinRequest;
import com.example.smart.dto.respone.attribute.pin.PinRespone;
import com.example.smart.entity.PinEntity;
import com.example.smart.repo.PinRepo;
import com.example.smart.service.IPinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PinServiceImpl implements IPinService {
    private final PinRepo repo;

    @Override
    public List<PinRespone> findAll() {

        List<PinRespone> list = new ArrayList<>();
        List<PinEntity> entities = repo.findByDeleteFlagIsFalse();
        for (PinEntity e: entities
        ) {
            list.add(new PinRespone(e.getId(), e.getName()));
        }
        return list;
    }

    @Override
    public String addPin(PinRequest request) {
        PinEntity entity = new PinEntity();
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
    public String editPin(PinRequest request) {
        PinEntity entity = repo.getById(request.getId());
        entity.setName(request.getName());
        repo.save(entity);
        return "ok";
    }

    @Override
    public String delete(Long id) {
        PinEntity entity = repo.getById(id);
        entity.setDeleteFlag(true);
        repo.save(entity);
        return "ok";
    }

    @Override
    public PinRespone findById(Long id) {
        PinEntity entity = repo.getById(id);
        return new PinRespone(entity.getId(), entity.getName());
    }
}

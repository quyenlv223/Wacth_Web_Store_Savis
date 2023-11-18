package com.example.smart.service.impl;

import com.example.smart.dto.request.attribute.color.ColorRequest;
import com.example.smart.dto.respone.color.ColorRespone;
import com.example.smart.entity.ColorEntity;
import com.example.smart.repo.ColorRepo;
import com.example.smart.service.IColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements IColorService {

    private final ColorRepo colorRepo;

    @Override
    public List<ColorRespone> findAll() {
        List<ColorEntity> entityList = colorRepo.findByDeleteFlagIsFalse();
        List<ColorRespone> colorRespones = new ArrayList<>();
        for (ColorEntity e: entityList
             ) {
            colorRespones.add(mapToRespone(e));
        }
        return colorRespones;
    }

    @Override
    public ColorRespone findById(String id) {
        ColorEntity color = colorRepo.getById(Long.valueOf(id));
        return mapToRespone(color);
    }

    @Override
    public String createColor(ColorRequest request) {
        ColorEntity color = new ColorEntity();
        color.setValueColor(request.getName());
        color.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        color.setModifierDate(Timestamp.valueOf(LocalDateTime.now()));
        colorRepo.save(color);
        return "ok";
    }

    @Override
    public String updateColor(ColorRequest request) {
        ColorEntity color = new ColorEntity();
        color.setValueColor(request.getName());
        color.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        color.setModifierDate(Timestamp.valueOf(LocalDateTime.now()));
        color.setId(Long.valueOf(request.getId()));
        colorRepo.save(color);
        return "ok";
    }

    @Override
    public String deleteChip(Long id) {
        ColorEntity color = colorRepo.getById(id);
        color.setDeleteFlag(true);
        colorRepo.save(color);
        return "ok";
    }


    private ColorRespone mapToRespone(ColorEntity entity){
        return new ColorRespone(entity.getId(), entity.getValueColor());
    }
}

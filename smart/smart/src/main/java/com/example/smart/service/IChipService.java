package com.example.smart.service;


import com.example.smart.dto.request.attribute.chip.ChipRequestDto;
import com.example.smart.dto.respone.attribute.chip.ChipRespone;

import java.util.List;


public interface IChipService {
    List<ChipRespone> findAll();

    ChipRespone findByCate(String id);

    String createChip(ChipRequestDto request);

    String updateChip(ChipRequestDto request);

    String deleteChip(Long idChip);
}

package com.example.smart.service;


import com.example.smart.dto.request.attribute.rom.RomRequest;
import com.example.smart.dto.respone.attribute.rom.RomRespone;

import java.util.List;


public interface IRomValueService {
    List<RomRespone> findAll();
    String save(RomRequest request);
    String update(RomRequest request);
    String delete(Long id);
    RomRespone findById(Long id);
}

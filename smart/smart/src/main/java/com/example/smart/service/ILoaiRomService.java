package com.example.smart.service;


import com.example.smart.dto.request.attribute.loai_rom.Loai_RomRequest;
import com.example.smart.dto.respone.attribute.loai_rom.Loai_RomRespone;

import java.util.List;

public interface ILoaiRomService {
    List<Loai_RomRespone> findAll();

    Loai_RomRespone findById(String id);

    String createLoaiRom(Loai_RomRequest request);

    String updateLoaiRom(Loai_RomRequest request);

    String deleteLoaiRom(Long id);
}

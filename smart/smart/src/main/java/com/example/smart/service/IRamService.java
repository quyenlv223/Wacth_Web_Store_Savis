package com.example.smart.service;


import com.example.smart.dto.request.attribute.ram.RamRequest;
import com.example.smart.dto.respone.attribute.ram.RamRespone;

import java.util.List;

public interface IRamService {
    List<RamRespone> findAll();

    String save(RamRequest request);

    String edit(RamRequest request);

    String delete(Long id);

    RamRespone findById(String id);

}

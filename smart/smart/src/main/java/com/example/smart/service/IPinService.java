package com.example.smart.service;


import com.example.smart.dto.request.attribute.pin.PinRequest;
import com.example.smart.dto.respone.attribute.pin.PinRespone;

import java.util.List;

public interface IPinService {

    List<PinRespone> findAll();

    String addPin(PinRequest request);

    String editPin(PinRequest request);

    String delete(Long id);

    PinRespone findById(Long id);
}

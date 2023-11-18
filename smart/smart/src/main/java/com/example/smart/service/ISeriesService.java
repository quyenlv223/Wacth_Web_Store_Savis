package com.example.smart.service;


import com.example.smart.dto.request.imei.ImeiRequest;
import com.example.smart.dto.respone.imei.ImeiResponse;

import java.util.List;


public interface ISeriesService {
    boolean saveImei(List<ImeiRequest> list, Long idProductOrder);

    List<ImeiResponse> findImeiDaBan(Long product, Long order);
}

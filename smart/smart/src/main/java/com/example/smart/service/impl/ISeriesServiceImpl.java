package com.example.smart.service.impl;

import com.example.smart.common.StatusImei;
import com.example.smart.dto.request.imei.ImeiRequest;
import com.example.smart.dto.respone.imei.ImeiResponse;
import com.example.smart.entity.SeriesEntity;
import com.example.smart.repo.SeriesRepo;
import com.example.smart.service.ISeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ISeriesServiceImpl implements ISeriesService {
    @Autowired
    private SeriesRepo imeiRepo;

    @Override
    public boolean saveImei(List<ImeiRequest> list, Long idProductOrder) {
        List<SeriesEntity> imeiEntityList = imeiRepo.findByOrder(idProductOrder);
        for (SeriesEntity ent: imeiEntityList
             ) {
            ent.setOrderDetailId(null);
            ent.setStatus(StatusImei.CHUA_BAN.getValue());
        }
        imeiRepo.saveAll(imeiEntityList);
        List<SeriesEntity> list1 = new ArrayList<>();
        for (ImeiRequest request: list
             ) {
            SeriesEntity entity = imeiRepo.findByIdAndDeleteFlagIsFalse(Long.valueOf(request.getImeiId()));
            entity.setStatus(StatusImei.DA_CO_DON.getValue());
            entity.setOrderDetailId(idProductOrder);
            list1.add(entity);
        }
        imeiRepo.saveAll(list1);
        return true;
    }

    @Override
    public List<ImeiResponse> findImeiDaBan(Long product, Long order) {
        if(imeiRepo.findImeiDaBan(product, order) != null ){
            return imeiRepo.findImeiDaBan(product, order).stream().map(this::maptoDto).collect(Collectors.toList());
        }
        return null;
    }

    private ImeiResponse maptoDto(SeriesEntity entity){
        return new ImeiResponse(String.valueOf(entity.getId()), entity.getValue());
    }
}

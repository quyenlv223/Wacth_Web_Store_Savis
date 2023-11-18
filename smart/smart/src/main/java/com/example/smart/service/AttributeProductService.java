package com.example.smart.service;


import com.example.smart.dto.request.attribute.AttributeRequestAdd;
import com.example.smart.dto.request.attribute.AttributeRequestEdit;
import com.example.smart.dto.respone.attribute.AttributeRespone;

public interface AttributeProductService {
    AttributeRespone findByProduct(Long id);

    String saveAttribute(AttributeRequestAdd requestAdd, Long productId) ;

    String updateAttribute(AttributeRequestEdit attributeRequestEdit, Long productId);
}

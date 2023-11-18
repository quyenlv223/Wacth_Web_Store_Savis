package com.example.smart.service;



import com.example.smart.dto.request.product_detail.ProductPropertyRequest;
import com.example.smart.dto.respone.order_detail.OrderDetailRespone;
import com.example.smart.dto.respone.product.ProductPropertyRespone;

import java.util.List;

public interface IProductDetailStatusService {
    List<ProductPropertyRespone> findByRom(Long id);

    OrderDetailRespone findById(Long id);

    List<ProductPropertyRespone> findByRomAndColor(String romId, String colorId);

    List<ProductPropertyRespone> findByColor(String colorId);

    ProductPropertyRespone updateProductProperty(ProductPropertyRequest request);

    String udpateStatusProductProperty(ProductPropertyRequest request);

    boolean addSeries(String romId, String colorId, List<String> imei);


}

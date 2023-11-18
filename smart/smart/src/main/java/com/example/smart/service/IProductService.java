package com.example.smart.service;


import com.example.smart.dto.request.product.ProductRequestAdd;
import com.example.smart.dto.request.product.ProductRequestEdit;
import com.example.smart.dto.respone.product.ProductResponse;

import java.util.List;

public interface IProductService {

    List<ProductResponse> findAll();

    String createProduct(ProductRequestAdd requestProduct);

    String editProduct(ProductRequestEdit requestEdit);

    ProductResponse getProduct(Long id);

    List<ProductResponse> getName(String name);

    List<ProductResponse> getNameNhapHang(String name);

    String deleteProduct(Long id);

    String editStatusProduct(Long id, String value);


    List<ProductResponse> listProduct();
}

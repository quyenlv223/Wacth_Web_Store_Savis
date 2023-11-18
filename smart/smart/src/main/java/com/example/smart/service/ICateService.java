package com.example.smart.service;

import com.example.smart.dto.respone.category.CategoryDTO;
import com.example.smart.dto.respone.category.CategoryResponeDto;

import java.util.List;

public interface ICateService {
    List<CategoryResponeDto> findAll();

    String save(CategoryDTO request);

    String edit(CategoryDTO request);

    CategoryResponeDto findById(String id);

    String delete(Long id);
}

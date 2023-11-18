package com.example.smart.service;


import com.example.smart.dto.request.category.CategoryReqDto;
import com.example.smart.dto.respone.category.CategoryDTO;
import com.example.smart.dto.respone.category.CategoryResponeDto;
import com.example.smart.entity.CategoryEntity;

import java.util.List;

public interface ICategoryService {
    List<CategoryResponeDto> getAllCategory();
    CategoryEntity findById(String id);
    void saveCategory(CategoryReqDto categoryDto);
    String updateCategory(CategoryDTO categoryDto);
    void deleteCategory(CategoryEntity categoryEntity);
}

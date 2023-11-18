package com.example.smart.service.impl;

import com.example.smart.constant.ConstansErrorCode;
import com.example.smart.dto.request.category.CategoryReqDto;
import com.example.smart.dto.respone.category.CategoryDTO;
import com.example.smart.dto.respone.category.CategoryResponeDto;
import com.example.smart.entity.CategoryEntity;
import com.example.smart.exception.SmartExp;
import com.example.smart.repo.CategoryRepo;
import com.example.smart.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    public List<CategoryResponeDto> getAllCategory() {
        return categoryRepo.findByDeleteFlagIsFalseOrderByCreateDateAsc().stream()
                .map(this::mapToCategoryResp)
                .collect(Collectors.toList());
    }

    private <R> CategoryResponeDto mapToCategoryResp(CategoryEntity categoryEntity) {
        if (categoryEntity == null) return new CategoryResponeDto();
        return CategoryResponeDto.builder()
                .categoryId(String.valueOf(categoryEntity.getId()))
                .categoryName(categoryEntity.getName())
                .build();
    }

    @Override
    public CategoryEntity findById(String id) {
        return categoryRepo.getById(Long.valueOf(id));
    }

    @Override
    public void saveCategory(CategoryReqDto categoryDto) {
            Date now = new Date();
            if(categoryDto.getName().trim().length() == 0){
                throw new SmartExp(ConstansErrorCode.CATEGORY_NAME);
            }else {
                if(categoryRepo.findByNameAndDeleteFlagIsFalse(categoryDto.getName()).size() > 0){
                    throw new SmartExp(ConstansErrorCode.CATEGORY_NAME_SAME);
                }else {
                    CategoryEntity categoryEntity = new CategoryEntity();
                    categoryEntity.setName(categoryDto.getName());
                    categoryEntity.setCreateDate(new Timestamp(now.getTime()));
                    categoryEntity.setCreateBy("Admin");
                    categoryEntity.setModifierBy("Admin");
                    categoryEntity.setModifierDate(new Timestamp(now.getTime()));
                    categoryEntity.setStatus("1");
                    categoryEntity.setDeleteFlag(false);
                    categoryRepo.save(categoryEntity);
                }

            }
    }

    @Override
    public String updateCategory(CategoryDTO categoryDto) {
        if(categoryDto.getName().trim().length() == 0){
            throw new SmartExp(ConstansErrorCode.CATEGORY_NAME);
        }else {

           if(categoryRepo.findByIdAndDeleteFlagIsFalse(Long.valueOf(categoryDto.getId())).size() > 0){
              CategoryEntity categoryEntity = categoryRepo.findByIdAndDeleteFlagIsFalse(Long.valueOf(categoryDto.getId())).get(0);
               categoryEntity.setName(categoryDto.getName());

               categoryEntity.setId(Long.valueOf(categoryDto.getId()));
               categoryRepo.save(categoryEntity);
           }else {
               throw new SmartExp(ConstansErrorCode.CATEGORY_ID);
           }

        }
        return null;
    }

    @Override
    public void deleteCategory(CategoryEntity categoryEntity) {
        List<CategoryEntity> list = categoryRepo.findByIdAndDeleteFlagIsFalse(categoryEntity.getId());
        if(list.size() > 0){
            list.get(0).setDeleteFlag(true);
            categoryRepo.save(list.get(0));
        }else {
            throw new SmartExp(ConstansErrorCode.CATEGORY_ID);
        }
    }
}

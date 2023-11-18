package com.example.smart.dto.respone.category;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponeDto {

    private String categoryId;
    private String categoryName;

    public CategoryResponeDto(Long id, String name) {
    }

    public void setId(Long id) {
    }

    public void setName(String name) {
    }

    public void setCreateDate(Timestamp createDate) {
    }
}

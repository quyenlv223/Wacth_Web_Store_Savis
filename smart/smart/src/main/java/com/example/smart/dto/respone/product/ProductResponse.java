package com.example.smart.dto.respone.product;

import com.example.smart.dto.respone.attribute.AttributeRespone;
import com.example.smart.dto.respone.category.CategoryResponeDto;
import com.example.smart.dto.respone.image.ImageRespone;
import com.example.smart.dto.respone.rom.RomRespone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private Long id;

    private String nameProduct;

    private String imageProduct;

    private CategoryResponeDto categoryResponeDto;

    private String note;

    private Date create_Date;

    private String create_By;

    private String status;

    private List<ImageRespone> image;

    private AttributeRespone attributeRespone;

    private List<RomRespone> romRespones;
}

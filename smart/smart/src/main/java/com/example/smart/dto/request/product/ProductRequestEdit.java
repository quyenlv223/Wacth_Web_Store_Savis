package com.example.smart.dto.request.product;


import com.example.smart.dto.request.attribute.AttributeRequestEdit;
import com.example.smart.dto.request.attribute.color.ColorRequest;
import com.example.smart.dto.request.rom.RomRequestAdd;
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
public class ProductRequestEdit {
    private Long idProduct;

    private String nameProduct;

    private Long categoryId;

    private String note;

    private Date create_Date;

    private String create_By;

    private List<String> image;


    private AttributeRequestEdit attributeRequestedit;

    private List<RomRequestAdd> romRequestAdds;

}

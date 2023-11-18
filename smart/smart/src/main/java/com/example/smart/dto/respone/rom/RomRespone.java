package com.example.smart.dto.respone.rom;

import com.example.smart.dto.respone.product.ProductPropertyRespone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RomRespone {
    private String id;
    private String name;
    private List<ProductPropertyRespone> productPropertyResponeList;

    public RomRespone(Long id, String name) {
    }
}

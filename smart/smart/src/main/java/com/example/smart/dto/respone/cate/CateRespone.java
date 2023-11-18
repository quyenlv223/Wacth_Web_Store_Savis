package com.example.smart.dto.respone.cate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CateRespone {
    private Long id;
    private String name;
    private Long loai;

    public CateRespone(Long id, String name) {
    }
}

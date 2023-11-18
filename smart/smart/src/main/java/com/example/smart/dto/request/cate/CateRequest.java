package com.example.smart.dto.request.cate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CateRequest {
    private Long id;
    private String name;
    private Long loai;

}

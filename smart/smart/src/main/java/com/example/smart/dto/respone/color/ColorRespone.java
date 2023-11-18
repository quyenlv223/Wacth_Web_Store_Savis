package com.example.smart.dto.respone.color;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ColorRespone {
    private Long id;
    private String name;

    public ColorRespone(String valueOf, String valueColor) {
    }
}

package com.example.smart.dto.request.attribute.screen;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScreenRequest {
    private Long id;
    private String name;
    private Long loai;
   /* public long getLoaiScreenId() {
        return loai;
    }*/
}

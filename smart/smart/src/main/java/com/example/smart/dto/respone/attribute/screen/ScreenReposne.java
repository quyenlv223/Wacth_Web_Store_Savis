package com.example.smart.dto.respone.attribute.screen;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScreenReposne {
    private Long id;
    private String name;
    /*private Long loai;*/
}

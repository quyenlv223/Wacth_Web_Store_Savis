package com.example.smart.dto.respone.attribute.rom_value;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rom_ValueRespone {
    private Long id;
    private String name;
    private Long loai;
}

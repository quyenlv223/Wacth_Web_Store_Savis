package com.example.smart.dto.respone.attribute.rom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RomRespone {
    private Long id;
    private String name;
    private Long loai;

    public RomRespone(Long id, String s) {
    }
}

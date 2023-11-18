package com.example.smart.dto.request.attribute.rom_value;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rom_ValueRequest {
    private Long id;
    private String name;
    private Long loai;

    public long getLoaiRomId() {
        return loai;
    }
}

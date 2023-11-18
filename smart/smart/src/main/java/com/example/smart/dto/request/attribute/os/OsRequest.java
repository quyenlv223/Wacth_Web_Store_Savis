package com.example.smart.dto.request.attribute.os;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OsRequest {
    private Long id;
    private String name;
    private Long loai;

    public long getLoaiOsId() {
        return loai;
    }
}

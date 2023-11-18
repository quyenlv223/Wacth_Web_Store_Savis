package com.example.smart.dto.request.attribute.cam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class CamRequest {
    private Long id;
    private String nameTruoc;
    private String nameSau;
}
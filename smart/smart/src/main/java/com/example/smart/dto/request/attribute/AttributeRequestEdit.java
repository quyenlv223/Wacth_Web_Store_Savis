package com.example.smart.dto.request.attribute;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttributeRequestEdit {
    private Long id;

    private String operatingSystem; // he dieu hanh

    private String screen; // man hinh

    private String chip;

    private String cam;

    private String pin;

    private String ram;
}

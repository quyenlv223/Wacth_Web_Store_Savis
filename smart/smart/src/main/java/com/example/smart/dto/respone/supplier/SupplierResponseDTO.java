package com.example.smart.dto.respone.supplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierResponseDTO {

    private String id;

    private String name;

    private String phoneNumber;

    private String email;

    private String address;

    private String status;

    private String note;
}

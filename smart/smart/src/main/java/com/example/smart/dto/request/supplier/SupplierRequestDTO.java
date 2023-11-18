package com.example.smart.dto.request.supplier;

import lombok.Data;

@Data
public class SupplierRequestDTO {

    private String name;

    private String phoneNumber;

    private String email;

    private String address;

    private String status;

    private String note;
}

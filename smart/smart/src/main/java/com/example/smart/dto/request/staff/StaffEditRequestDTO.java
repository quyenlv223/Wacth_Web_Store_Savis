package com.example.smart.dto.request.staff;

import lombok.Data;

import java.util.Date;

@Data
public class StaffEditRequestDTO {
    private String id;

    private String fullName;

    private String phoneNumber;

    private String address;

    private String avatar;

    private Date dateOfBirth;

    private String role;

    private String note;

}

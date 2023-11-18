package com.example.smart.dto.request.staff;

import lombok.Data;

import java.util.Date;

@Data
public class StaffAddRequestDTO {

    private String fullName;

    private String phoneNumber;

    private String email;

    private String address;

    private Date dateOfBirth;

    private String avatar;

    private String role;


    private String note;

    private String status;
}

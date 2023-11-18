package com.example.smart.dto.respone.staff;


import lombok.Data;

import java.util.Date;

@Data
public class StaffResponeDto {

    private Long id;

    private String fullName;

    private String phoneNumber;

    private String email;

    private String address;

    private Date dateOfBirth;

    private String avatar;

    private String role;

    private String note;

    private String status;

    private Date createDate;
}

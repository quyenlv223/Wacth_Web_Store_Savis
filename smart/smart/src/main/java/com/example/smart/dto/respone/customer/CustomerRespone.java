package com.example.smart.dto.respone.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRespone {
    private String name;
    private String phone;
    private String birthDay;
    private String email;
}

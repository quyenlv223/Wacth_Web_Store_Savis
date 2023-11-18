package com.example.smart.service;

import com.example.smart.entity.CustomerEntity;

import java.util.List;

public interface ICustomerService  {
    List<CustomerEntity> findByPhoneNumber(String phoneNumber);

    List<CustomerEntity> listCustomer();
}

package com.example.smart.service.impl;

import com.example.smart.entity.CustomerEntity;
import com.example.smart.repo.DAO.CustomerDAO;
import com.example.smart.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    CustomerDAO dao;

    @Override
    public List<CustomerEntity> findByPhoneNumber(String phoneNumber) {
        return dao.findByPhoneNumber(phoneNumber);
    }

    @Override
    public List<CustomerEntity> listCustomer() {
        return dao.findAll();
    }
}

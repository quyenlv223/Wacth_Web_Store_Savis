
package com.example.smart.api;


import com.example.smart.entity.CustomerEntity;
import com.example.smart.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer/")
@RequiredArgsConstructor
public class CustomerApi {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/list")
    public ResponseEntity<?> listCustomer(){
        List<CustomerEntity> respone = customerService.listCustomer();

        return ResponseEntity.ok().body(respone);
    }

    @GetMapping("/search/{phoneNumber}")
    public ResponseEntity<?> findCustomer(@PathVariable("phoneNumber") String phoneNumber){
        List<CustomerEntity> respone = customerService.findByPhoneNumber(phoneNumber);

        return ResponseEntity.ok().body(respone);
    }
}

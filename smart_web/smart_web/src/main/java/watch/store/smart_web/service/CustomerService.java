package watch.store.smart_web.service;

import watch.store.smart_web.dto.request.customer.CustomerRequest;
import watch.store.smart_web.entity.CustomerEntity;

import java.text.ParseException;

public interface CustomerService {

    CustomerEntity findByEmail(String email);

    String addCustomer(CustomerRequest request) throws ParseException;

    CustomerEntity findByPhone(String phone);
}

package watch.store.smart_web.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import watch.store.smart_web.dto.request.customer.CustomerRequest;
import watch.store.smart_web.entity.CustomerEntity;
import watch.store.smart_web.repo.CustomerRepo;
import watch.store.smart_web.service.CustomerService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepo repo;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Override
    public CustomerEntity findByEmail(String email) {
        return repo.findByEmail(email);
    }

    @Override
    public String addCustomer(CustomerRequest request) throws ParseException {
        try {
            CustomerEntity entity = new CustomerEntity();
            entity.setEmail(request.getEmail());
            entity.setFullName(request.getName());
            entity.setPhoneNumber(request.getPhone());

            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(request.getDate());
            entity.setDateOfBirth(date1);
            entity.setPassWord(bCryptPasswordEncoder.encode(request.getPassword()));
            repo.save(entity);
        }catch (Exception e){
            return "false";
        }
        return "ok";
    }

    @Override
    public CustomerEntity findByPhone(String phone) {
        return repo.findByPhoneNumber(phone);
    }
}

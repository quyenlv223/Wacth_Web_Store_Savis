package watch.store.smart_web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import watch.store.smart_web.entity.CustomerEntity;


@Repository
public interface CustomerRepo extends JpaRepository<CustomerEntity, Long> {

    CustomerEntity findByEmail(String email);
    CustomerEntity findByPhoneNumber(String phone);
}

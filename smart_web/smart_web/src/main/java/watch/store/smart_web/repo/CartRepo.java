package watch.store.smart_web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import watch.store.smart_web.entity.CartEntity;
import watch.store.smart_web.entity.ProductDetailStatusEntity;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<CartEntity, Long> {

    List<CartEntity> findByDeleteFlagIsFalseAndIdCustomer(Long id);

    CartEntity findByDeleteFlagIsFalseAndIdCustomerAndIdProduct(Long id, ProductDetailStatusEntity propertyEntity);

    CartEntity findByDeleteFlagIsFalseAndId(Long id);
}

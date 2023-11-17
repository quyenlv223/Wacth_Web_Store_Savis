package watch.store.smart_web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import watch.store.smart_web.entity.CustomerEntity;
import watch.store.smart_web.entity.OrdersEntity;

import java.util.List;


@Repository
public interface OrdersRepo extends JpaRepository<OrdersEntity, Long> {
    @Query("select o from OrdersEntity o where o.status <> '-2' order by o.createDate desc")
    List<OrdersEntity> findByCustomerEntityOrderByCreateDateDesc(CustomerEntity entity);

    OrdersEntity findByCodeOrder(String code);
}

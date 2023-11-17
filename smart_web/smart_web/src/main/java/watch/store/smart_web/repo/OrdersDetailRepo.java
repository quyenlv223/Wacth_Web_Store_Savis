package watch.store.smart_web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import watch.store.smart_web.entity.OrdersDetailEntity;

import java.util.List;


@Repository
public interface OrdersDetailRepo extends JpaRepository<OrdersDetailEntity, Long> {
    @Query("select o from OrdersDetailEntity o where o.deleteFlag = false and o.ordersEntity.id = ?1")
    List<OrdersDetailEntity> findByDeleteFlagIsFalseAndOrdersEntity(Long id);
}

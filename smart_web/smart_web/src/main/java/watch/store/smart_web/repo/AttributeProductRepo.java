package watch.store.smart_web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import watch.store.smart_web.entity.AttributeProductEntity;

@Repository
public interface AttributeProductRepo extends JpaRepository<AttributeProductEntity, Long> {
    AttributeProductEntity findByProductId(Long productId);
}

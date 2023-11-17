package watch.store.smart_web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import watch.store.smart_web.entity.ImageEntity;

import java.util.List;

@Repository
public interface ImageRepo extends JpaRepository<ImageEntity, Long> {
    @Query("select o from ImageEntity o where o.deleteFlag = false and o.productId = ?1")
    List<ImageEntity> findByProductIdAndDeleteFlagIsFalse(Long id);
}

package watch.store.smart_web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import watch.store.smart_web.entity.PinEntity;

import java.util.List;

@Repository
public interface PinRepo extends JpaRepository<PinEntity, Long> {
    List<PinEntity> findByDeleteFlagIsFalse();
}

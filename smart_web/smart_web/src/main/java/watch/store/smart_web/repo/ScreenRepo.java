package watch.store.smart_web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import watch.store.smart_web.entity.ScreenEntity;

import java.util.List;

@Repository
public interface ScreenRepo extends JpaRepository<ScreenEntity, Long> {
    List<ScreenEntity> findByDeleteFlagIsFalse();
}

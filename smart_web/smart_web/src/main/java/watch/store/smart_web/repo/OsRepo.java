package watch.store.smart_web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import watch.store.smart_web.entity.OSystemEntity;

import java.util.List;

@Repository
public interface OsRepo extends JpaRepository<OSystemEntity, Long> {
    List<OSystemEntity> findByDeleteFlagIsFalse();
}

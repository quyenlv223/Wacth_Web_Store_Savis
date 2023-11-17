package watch.store.smart_web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import watch.store.smart_web.entity.RamEntity;

import java.util.List;

@Repository
public interface RamRepo extends JpaRepository<RamEntity, Long> {

    List<RamEntity> findByDeleteFlagIsFalse();
}

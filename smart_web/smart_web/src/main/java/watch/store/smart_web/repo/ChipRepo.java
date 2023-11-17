package watch.store.smart_web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import watch.store.smart_web.entity.ChipEntity;

import java.util.List;


@Repository
public interface ChipRepo extends JpaRepository<ChipEntity, Long> {
    List<ChipEntity> findByDeleteFlagIsFalse();
}

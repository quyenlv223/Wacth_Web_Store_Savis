package watch.store.smart_web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import watch.store.smart_web.entity.ColorEntity;

import java.util.List;

@Repository
public interface ColorRepo extends JpaRepository<ColorEntity, Long> {

    ColorEntity findByDeleteFlagIsFalseAndId(Long id);
    List<ColorEntity> findByDeleteFlagIsFalse();
}

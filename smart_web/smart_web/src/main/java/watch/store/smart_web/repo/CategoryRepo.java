package watch.store.smart_web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import watch.store.smart_web.entity.CategoryEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<CategoryEntity,Long> {
    @Query("select o from CategoryEntity o where o.deleteFlag = false")
    List<CategoryEntity> findByDeleteFlagIsFalse();

    Optional<CategoryEntity> findById(Long id);



    CategoryEntity findByDeleteFlagIsFalseAndId(Long id);
}


package watch.store.smart_web.service;

import org.springframework.stereotype.Service;
import watch.store.smart_web.entity.CategoryEntity;

import java.util.List;

@Service
public interface CategoryService {
    List<CategoryEntity> findByCategoryAndDeleteFlagIsFalse();
}

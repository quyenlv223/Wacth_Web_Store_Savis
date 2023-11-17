package watch.store.smart_web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import watch.store.smart_web.entity.CategoryEntity;
import watch.store.smart_web.repo.CategoryRepo;
import watch.store.smart_web.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public List<CategoryEntity> findByCategoryAndDeleteFlagIsFalse() {
        return categoryRepo.findByDeleteFlagIsFalse();
    }
}

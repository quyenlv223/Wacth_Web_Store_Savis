package watch.store.smart_web.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import watch.store.smart_web.entity.ImageEntity;
import watch.store.smart_web.repo.ImageRepo;
import watch.store.smart_web.service.ImageService;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepo repo;
    @Override
    public List<String> getAllImageByProduct(String productId) {
        List<String> stringList = new ArrayList<>();
        List<ImageEntity> entityList = repo.findByProductIdAndDeleteFlagIsFalse(Long.valueOf(productId));
        entityList.forEach(o -> {
            stringList.add(o.getLing_image());
        });
        return stringList;
    }
}

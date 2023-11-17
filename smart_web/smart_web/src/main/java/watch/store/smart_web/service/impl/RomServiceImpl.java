package watch.store.smart_web.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import watch.store.smart_web.dto.respone.rom.RomRespone;
import watch.store.smart_web.entity.RomEntity;
import watch.store.smart_web.repo.RomRepo;
import watch.store.smart_web.service.ProductDetailStatusService;
import watch.store.smart_web.service.RomService;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RomServiceImpl implements RomService {
    private final RomRepo repo;

    private final ProductDetailStatusService productDetailStatusService;

    @Override
    public List<RomRespone> findByProductId(Long id) {
        List<RomRespone> list = new ArrayList<>();
        List<RomEntity> romEntities = repo.findByProductId(id);
        romEntities.forEach(o -> {
            if (productDetailStatusService.findByRomId(o.getId()) != null && productDetailStatusService.findByRomId(o.getId()).size() > 0){
                list.add(RomRespone.builder()
                        .name(o.getName())
                        .id(String.valueOf(o.getId()))
                        .productPropertyRespones(productDetailStatusService.findByRomId(o.getId()))
                        .build());
            }

        });

        return list;
    }

    private RomRespone mapToEntity(RomEntity entity){
        RomRespone respone = new RomRespone();
        respone.setId(String.valueOf(entity.getId()));
        respone.setName(entity.getName());
        return respone;
    }
}

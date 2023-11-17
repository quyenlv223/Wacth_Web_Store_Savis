package watch.store.smart_web.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import watch.store.smart_web.dto.respone.attribute.AttributeRespone;
import watch.store.smart_web.entity.AttributeProductEntity;
import watch.store.smart_web.repo.*;
import watch.store.smart_web.service.AttributeService;


@Service
@Slf4j
@RequiredArgsConstructor
public class AttributeServiceImpl implements AttributeService {
    private final AttributeProductRepo repo;
    private final RamRepo ramRepo;
    private final ChipRepo chipRepo;
    private final OsRepo osRepo;
    private final PinRepo pinRepo;
    private final ScreenRepo screenRepo;
    @Override
    public AttributeRespone findByProduct(Long id) {
        AttributeProductEntity entity = repo.findByProductId(id);
        AttributeRespone respone = new AttributeRespone();
        respone.setOperatingSystem(osRepo.findById(entity.getOsystemId()).get().getName());
        respone.setScreen(screenRepo.findById(entity.getScreenId()).get().getName());
        respone.setRam(ramRepo.findById(entity.getRamID()).get().getName());
        respone.setPin(String.valueOf(pinRepo.findById(entity.getPinId()).get().getName()) + " mAh");
        respone.setChip(chipRepo.findById(entity.getChipId()).get().getName());
        /*respone.setCamSau(camRepo.findById(entity.getCamId()).get().getCamSau());
        respone.setCamTruoc(camRepo.findById(entity.getCamId()).get().getCamTruoc());*/
        return respone;
    }
}

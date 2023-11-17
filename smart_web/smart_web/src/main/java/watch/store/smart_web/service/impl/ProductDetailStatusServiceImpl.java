package watch.store.smart_web.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import watch.store.smart_web.dto.respone.product.ProductPropertyRespone;
import watch.store.smart_web.entity.ProductDetailStatusEntity;
import watch.store.smart_web.repo.ProductDetailStatusRepo;
import watch.store.smart_web.service.ColorService;
import watch.store.smart_web.service.ProductDetailStatusService;
import watch.store.smart_web.util.ConvertUtil;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductDetailStatusServiceImpl implements ProductDetailStatusService {

    private final ProductDetailStatusRepo repo;
    private final ConvertUtil util;

    private final ColorService colorService;

    @Override
    public List<ProductPropertyRespone> findByRomId(Long romId) {
        List<ProductDetailStatusEntity> entityList = repo.findByRomId(romId);
        List<ProductPropertyRespone> responeList = new ArrayList<>();
        entityList.forEach(o -> {
            responeList.add(ProductPropertyRespone.builder()
                    .price(o.getPrice())
                    .priceNow(util.moneyToStringFormat(o.getPricePromotion()*o.getPrice()/100))
                    .priceString(util.moneyToStringFormat(o.getPrice()))
                    .quantity(o.getQuantity())
                    .id(String.valueOf(o.getId()))
                    .color(colorService.findById(o.getColorId()))
                    .pricePromotion(o.getPricePromotion())
                    .pricePromotionString(util.moneyToStringFormat(o.getPricePromotion()))
                    .idPromotion(o.getPromotionId())
                    .build());
        });
        return responeList;
    }
}

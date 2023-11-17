package watch.store.smart_web.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import watch.store.smart_web.constant.AttributeConstant;
import watch.store.smart_web.dto.respone.voucher.VoucherRespone;
import watch.store.smart_web.entity.VoucherEntity;
import watch.store.smart_web.repo.*;
import watch.store.smart_web.service.VoucherService;
import watch.store.smart_web.util.ConvertUtil;
import watch.store.smart_web.util.SessionUtil;


@Service
@Slf4j
@RequiredArgsConstructor
public class VoucherServiceImpl implements VoucherService {
    private final VoucherRepo repo;

    private final CartRepo cartRepo;

    private final CustomerRepo customerRepo;

    private final SessionUtil sessionUtil;

    private final ProductRepo productRepo;

    private final ProductDetailStatusRepo productPropertyRepo;

    private final RomRepo romRepo;

    private final CategoryRepo categoryRepo;

    private final ColorServiceImpl colorService;
    private final ConvertUtil convertUtil;
    @Override
    public VoucherRespone findByCode(String code) {
        VoucherRespone respone = new VoucherRespone();
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        VoucherEntity entity = repo.findByDeleteFlagIsFalseAndCode(date,code);
        if(entity == null){
            return null;
        }
        if(entity.getQuantity() == 0 || entity.getStatus().equals(AttributeConstant.OFF)){
            return null;
        }
        respone.setType(entity.getTypeDiscount());
        respone.setQuantity(String.valueOf(entity.getQuantity()));
        respone.setDiscount(String.valueOf(entity.getDiscount()));
        respone.setTypeDiscountMoneyMin(entity.getTypeDiscountMoneyMin());
        respone.setMinAmount(entity.getMinAmount());
        respone.setAccompanyPromo(entity.getAccompanyPromo());
        return respone;
    }


}

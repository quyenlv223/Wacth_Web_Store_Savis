package watch.store.smart_web.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import watch.store.smart_web.constant.AttributeConstant;
import watch.store.smart_web.dto.request.cart.CartEditRequest;
import watch.store.smart_web.dto.request.cart.CartRequest;
import watch.store.smart_web.dto.respone.cart.CartRespone;
import watch.store.smart_web.entity.*;
import watch.store.smart_web.repo.*;
import watch.store.smart_web.service.CartService;
import watch.store.smart_web.util.ConvertUtil;
import watch.store.smart_web.util.SessionUtil;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {

    private final CartRepo cartRepo;
    private final ConvertUtil convertUtil;
    private final ColorServiceImpl colorService;
    private final RomRepo romRepo;
    private final ProductRepo productRepo;

    private final CustomerRepo customerRepo;

    private final SessionUtil sessionUtil;

    private final ProductDetailStatusRepo productDetailStatusRepo;


    @Override
    public List<CartRespone> findByCustomer() {
        CustomerEntity customerEntity = customerRepo.findByEmail(String.valueOf(sessionUtil.getObject("username")));
        List<CartEntity> entities = cartRepo.findByDeleteFlagIsFalseAndIdCustomer(customerEntity.getId());
        return entities.stream().map(this::mapToRespone).collect(Collectors.toList());
    }

    @Override
    public String addCart(CartRequest cartRequest) {
        CartEntity entity = new CartEntity();
        CustomerEntity customerEntity = customerRepo.findByEmail(String.valueOf(sessionUtil.getObject("username")));
        if(customerEntity != null){
            entity.setQuantity(Long.valueOf(cartRequest.getQuantityCart()));
            ProductDetailStatusEntity propertyEntity = productDetailStatusRepo.findByDeleteFlagIsFalseAndId(Long.valueOf(cartRequest.getIdProduct()));
            if(propertyEntity == null){
                return "false";
            }
            entity.setIdProduct(propertyEntity);
            entity.setIdCustomer(customerEntity.getId());
            entity.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
            entity.setModifierDate(Timestamp.valueOf(LocalDateTime.now()));
            CartEntity entity1 = cartRepo.findByDeleteFlagIsFalseAndIdCustomerAndIdProduct(customerEntity.getId(), propertyEntity);
            if(entity1 == null){
                if(propertyEntity.getQuantity() == 0){
                    return "Quá số lượng sản phẩm trong kho";
                }
                cartRepo.save(entity);
                return "ok";
            }else {
                if(entity1.getQuantity() - propertyEntity.getQuantity() >= 0){
                    return "Quá số lượng sản phẩm trong kho";
                }
                if(propertyEntity.getQuantity() < entity1.getQuantity() || propertyEntity.getQuantity() == 0){
                    return "Quá số lượng sản phẩm trong kho";
                }
                entity1.setQuantity(entity1.getQuantity() + entity.getQuantity());
                if(entity1.getQuantity() > 5){
                    return "sl";
                }else {
                    cartRepo.save(entity1);
                }
            }
            return "ok";
        }else {
            return "false";
        }
    }

    @Override
    public String deleteCart(Long id) {
        CartEntity cartEntity = cartRepo.findByDeleteFlagIsFalseAndId(id);
        if (cartEntity == null){
            return "false";
        }
        cartEntity.setDeleteFlag(true);
        cartRepo.save(cartEntity);
        return "ok";
    }

    @Override
    public String updateCart(List<CartEditRequest> list) {
        int count = 0;
        try {
            for (CartEditRequest edit : list) {
                CartEntity cartEntity = cartRepo.findByDeleteFlagIsFalseAndId(Long.valueOf(edit.getId()));
                ProductDetailStatusEntity propertyEntity = cartEntity.getIdProduct();
                if (propertyEntity.isDeleteFlag() || propertyEntity.getStatus().equals(AttributeConstant.OFF)){
                    cartEntity.setDeleteFlag(true);
                    cartRepo.save(cartEntity);
                    count++;
                    continue;
                }
                if (Long.valueOf(edit.getQuantity()) > propertyEntity.getQuantity()){
                    edit.setQuantity(String.valueOf(propertyEntity.getQuantity()));
                }
                if (Integer.parseInt(edit.getQuantity()) > 5){
                    edit.setQuantity("5");
                }
                cartEntity.setQuantity(Long.valueOf(edit.getQuantity()));
                cartRepo.save(cartEntity);
            }
        }catch (Exception e){
            return "false";
        }
        return String.valueOf(count);
    }


    private CartRespone mapToRespone(CartEntity entity){
        CartRespone respone = new CartRespone();
        respone.setId(String.valueOf(entity.getId()));
        respone.setColor(colorService.findById(entity.getIdProduct().getColorId()));
        ProductDetailStatusEntity propertyEntity = entity.getIdProduct();
        RomEntity romEntity = romRepo.findById(propertyEntity.getRomId()).get();
        ProductEntity productEntity = productRepo.findById(romEntity.getProductId()).get();
        respone.setImgProduct(productEntity.getImage_key());
        respone.setIdProduct(String.valueOf(productEntity.getId()));
        respone.setQuantityProduct(propertyEntity.getQuantity());
        respone.setNameProduct(productEntity.getName());
        respone.setQuantity(entity.getQuantity());
        respone.setPriceProduct(propertyEntity.getPrice());
        respone.setPriceProductString(convertUtil.moneyToStringFormat(respone.getPriceProduct()));
        /*respone.setRom(romEntity.getName() + " GB");*/
        respone.setRom(romEntity.getName());
        respone.setPriceProductPromotion(propertyEntity.getPricePromotion());
        respone.setPriceProductPromotionString(convertUtil.moneyToStringFormat(respone.getPriceProductPromotion()));
        if(respone.getPriceProductPromotion() > 0){
            respone.setTotal(respone.getQuantity() * respone.getPriceProductPromotion());
            respone.setTotalString(convertUtil.moneyToStringFormat(respone.getTotal()));
        }else {
            respone.setTotal(respone.getQuantity() * respone.getPriceProduct());
            respone.setTotalString(convertUtil.moneyToStringFormat(respone.getTotal()));
        }
        return respone;
    }
}

package com.example.smart.service.impl;

import com.example.smart.dto.request.voucher.VoucherRequest;
import com.example.smart.dto.respone.voucher.VoucherResponse;
import com.example.smart.entity.CategoryEntity;
import com.example.smart.entity.VoucherCustomerEntity;
import com.example.smart.entity.VoucherEntity;
import com.example.smart.repo.CategoryRepo;
import com.example.smart.repo.VoucherCustomerRepo;
import com.example.smart.repo.VoucherRepo;
import com.example.smart.service.IVoucherService;
import com.example.smart.type.VoucherKey;
import com.example.smart.until.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.smart.type.CustomerStatus.FAILED;
import static com.example.smart.type.CustomerStatus.SUCCESS;
import static org.apache.commons.lang3.BooleanUtils.OFF;
import static org.apache.commons.lang3.BooleanUtils.ON;





@Service
@RequiredArgsConstructor
public class VoucherService implements IVoucherService {

    private final VoucherRepo voucherRepo;

    private final CategoryRepo categoryRepo;

    private final VoucherCustomerRepo voucherCustomerRepo;

    //private final OrderRepo orderRepo;

    @Override
    public List<VoucherResponse> getAllVoucher() {
        return voucherRepo.findAllByDeleteFlagIsFalse().stream()
                .map(this::mapToVoucherDto).collect(Collectors.toList());
    }

    @Override
    public String activeVoucher(String id) {
        VoucherEntity voucherEntity = voucherRepo.getByIdAndDeleteFlagIsFalse(id);
        if (Objects.isNull(voucherEntity)) {
            return FAILED.name();
        }
        voucherEntity.setStatus(ON.toUpperCase());
        voucherRepo.save(voucherEntity);
        return SUCCESS.name();
    }

    @Override
    public List<Long> findAllIdCustomerByVoucherId(String voucherId) {
        return voucherCustomerRepo.findAllByVoucherId(voucherId).stream().map(VoucherCustomerEntity::getCustomerId).collect(Collectors.toList());
    }

    @Override
    public VoucherResponse getById(String voucherId) {
        return mapToVoucherDto(voucherRepo.getByIdAndDeleteFlagIsFalse(voucherId));
    }

    @Override
    public List<VoucherResponse> findAllByCode(String code) {
        return voucherRepo.findAllByCodeAndDeleteFlagIsFalse(code.toUpperCase()).stream()
                .map(this::mapToVoucherDto).collect(Collectors.toList());
    }

    @Override
    public VoucherResponse findFistByCode(String code) {
//        List<VoucherResponse> lstVoucher = findAllByCode(code.toUpperCase());
//        VoucherResponse response = null;
//        List<OrdersEntity> lstUsedByVoucherId = orderRepo.findAllByVoucherCodeAndTransactionStatusNotInAndDeleteFlagFalse(code, OrderStatus.CANCEL.name());
//        for (VoucherResponse x : lstVoucher) {
////            List<OrdersEntity> lstUsedByVoucherId = orderRepo.findAllByVoucherCodeAndDeleteFlagIsFalseAndTransactionStatusNotIn(x.getVoucherId());
//            if (x.getVoucherEndDate().compareTo(new Date()) >= 0
//                    && x.getVoucherQuantity() > lstUsedByVoucherId.size()) {
//                response = x;
//                break;
//            }
//        }
//        return response;
        return null;
    }

    @Override
    @Transactional
    public String createVoucher(VoucherRequest voucherRequest) {
        if (Objects.isNull(voucherRequest)) {
            return FAILED.name();
        }
        // check voucher đã tồnt ại hay chưa
        if (!voucherRepo.findAllByCodeAndDeleteFlagIsFalse(voucherRequest.getVoucherCode()).isEmpty()) {
            return FAILED.name();
        }
        VoucherEntity voucherEntity = mapToVoucherEntity(voucherRequest, new VoucherEntity());

        voucherEntity.setStatus(ON.toUpperCase());
        voucherEntity.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        voucherEntity.setModifierDate(Timestamp.valueOf(LocalDateTime.now()));
        voucherEntity.setAccompanyPromo(voucherRequest.getVoucherAccompanyPromo().toUpperCase().equals(ON.toUpperCase()) ? VoucherKey.USED.name() : VoucherKey.UNUSED.name());
        //List<VoucherCustomerEntity> lstVoucherCustomerEntities = new ArrayList<>();
//        if (Objects.nonNull(voucherRequest.getTypeDiscountPerson())) {
//            for (String x : voucherRequest.getTypeDiscountPerson()) {
//                lstVoucherCustomerEntities.add(VoucherCustomerEntity.builder()
//                        .customerId(Long.valueOf(x))
//                        .voucherId(Long.valueOf(voucherEntity.getId()))
//                        .status(OFF.toUpperCase())
//                        .discount(voucherEntity.getDiscount())
//                        .startDatte(voucherEntity.getStartDate())
//                        .endDate(voucherEntity.getEndDate())
//                        .build());
//            }
//        }
        try {
            voucherRepo.save(voucherEntity);
            //voucherCustomerRepo.saveAll(lstVoucherCustomerEntities);
            return SUCCESS.name();
        } catch (Exception e) {
            e.printStackTrace();
            return FAILED.name();
        }
    }

    @Override
    @Transactional
    public String updateVoucher(VoucherRequest voucherRequest) {
        if (Objects.isNull(voucherRequest)) {
            return FAILED.name();
        }
        VoucherEntity voucherEntity = voucherRepo.getByIdAndDeleteFlagIsFalse(voucherRequest.getVoucherId());
        if (Objects.isNull(voucherEntity)) {
            return FAILED.name();
        }
        String voucherId = voucherEntity.getId();
        List<VoucherEntity> lstFindAllByCode = voucherRepo.findAllByCodeAndDeleteFlagIsFalse(voucherRequest.getVoucherCode())
                .stream().filter(e -> !e.getId().equals(voucherId)).collect(Collectors.toList());
        if (!lstFindAllByCode.isEmpty()) {
            return FAILED.name();
        }
        Timestamp createDate = voucherEntity.getCreateDate();
        voucherEntity = mapToVoucherEntity(voucherRequest, voucherEntity);
        voucherEntity.setId(voucherId);
        voucherEntity.setAccompanyPromo(voucherRequest.getVoucherAccompanyPromo().toUpperCase().equals(ON.toUpperCase()) ? VoucherKey.USED.name() : VoucherKey.UNUSED.name());
        voucherEntity.setCreateDate(createDate);
        voucherEntity.setModifierDate(Timestamp.valueOf(LocalDateTime.now()));
        List<VoucherCustomerEntity> lstVoucherCustomerEntities = new ArrayList<>();
        System.out.println(voucherRequest.getTypeDiscountPerson());
        if (Objects.nonNull(voucherRequest.getTypeDiscountPerson())) {
            for (String x : voucherRequest.getTypeDiscountPerson()) {
                lstVoucherCustomerEntities.add(VoucherCustomerEntity.builder()
                        .customerId(Long.valueOf(x))
                        .voucherId(Long.valueOf(voucherEntity.getId()))
                        .status(OFF.toUpperCase())
                        .discount(voucherEntity.getDiscount())
                        .startDatte(voucherEntity.getStartDate())
                        .endDate(voucherEntity.getEndDate())
                        .build());
            }
        }
        //List<VoucherCustomerEntity> lstVoucherCustomerDelete = voucherCustomerRepo.findAllByVoucherId(voucherId);
       // lstVoucherCustomerDelete.forEach(e -> e.setDeleteFlag(true));
        //lstVoucherCustomerEntities.addAll(lstVoucherCustomerDelete);
        try {
            voucherRepo.save(voucherEntity);
            voucherCustomerRepo.saveAll(lstVoucherCustomerEntities);
            return SUCCESS.name();
        } catch (Exception e) {
            e.printStackTrace();
            return FAILED.name();
        }
    }

    @Override
    public String deleteVoucher(String id) {
        VoucherEntity voucherEntity = voucherRepo.getByIdAndDeleteFlagIsFalse(id);
        if (Objects.isNull(voucherEntity)) {
            return FAILED.name();
        }
        // TODO voucher đã được sử dụng cchuwa
        try {
            voucherEntity.setDeleteFlag(true);
            voucherRepo.save(voucherEntity);
            return SUCCESS.name();
        } catch (Exception e) {
            e.printStackTrace();
            return FAILED.name();
        }
    }

    private VoucherEntity mapToVoucherEntity(VoucherRequest x, VoucherEntity s) {
        if (x == null) return null;
        CategoryEntity categoryEntity = null;
        if(!x.getVoucherCategory().equals("")){
             categoryEntity = categoryRepo.findByIdAndDeleteFlagIsFalse(Long.valueOf(x.getVoucherCategory())).get(0);
        }
        return s.toBuilder()
                .id(x.getVoucherId())
                .name(x.getVoucherName())
                .code(x.getVoucherCode())
                .quantity(Integer.parseInt(x.getVoucherQuantity()))
                .discount(Long.parseLong(x.getVoucherDiscount()))
                .endDate(ConvertUtil.get().strToDate(x.getVoucherEndDate(), "dd-MM-yyyy"))
                .startDate(ConvertUtil.get().strToDate(x.getVoucherStartDate(), "dd-MM-yyyy"))
                .description(x.getVoucherDescription())
                .typeDiscount(x.getVoucherTypeDiscount())
                .typeDiscountMoneyMin(Objects.isNull(x.getTypeDiscountMoneyMin()) ? 0 : Long.parseLong(x.getTypeDiscountMoneyMin()))
                .minAmount(Integer.parseInt(x.getVoucherMoneyMin()))
                .categoryId(categoryEntity == null ? null : categoryEntity.getId())
                .build();
    }

    private <R> VoucherResponse mapToVoucherDto(VoucherEntity voucherEntity) {
        if (voucherEntity == null) return new VoucherResponse();
        return VoucherResponse.builder()
                .voucherId(voucherEntity.getId())
                .voucherCode(voucherEntity.getCode().toUpperCase())
                .voucherName(voucherEntity.getName())
                .voucherStartDate(voucherEntity.getStartDate())
                .voucherEndDate(voucherEntity.getEndDate())
                .voucherDiscount(voucherEntity.getDiscount())
                .voucherMinAmount(voucherEntity.getMinAmount())
                .voucherQuantity(voucherEntity.getQuantity())
                .voucherDescription(voucherEntity.getDescription())
                .categoryId(Objects.isNull(voucherEntity.getCategoryId()) ? "" : String.valueOf(voucherEntity.getCategoryId()))
                .voucherStatus(voucherEntity.getStatus())
                .voucherCreateDate(voucherEntity.getCreateDate())
                .voucherCreateBy(voucherEntity.getCreateBy())
                .voucherModifiedDate(voucherEntity.getModifierDate())
                .voucherHidden(voucherEntity.getStartDate().compareTo(new Date()) < 0)
                .voucherTypeDiscount(voucherEntity.getTypeDiscount())
                .typeDiscountMinAmount(voucherEntity.getTypeDiscountMoneyMin())
                .voucherKey(voucherEntity.getAccompanyPromo())
                .build();
    }

}

package com.example.smart.repo;

import com.example.smart.entity.VoucherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;



@Repository
public interface VoucherRepo extends JpaRepository<VoucherEntity, String> {

    List<VoucherEntity> findAllByCodeAndDeleteFlagIsFalse(String voucherCode);

    List<VoucherEntity> findAllByDeleteFlagIsFalse();

    VoucherEntity getByIdAndDeleteFlagIsFalse(String id);
    @Query("select o from VoucherEntity o where o.endDate >= ?1 and o.code = ?2 and o.status = 'ON' and o.deleteFlag = false ")
    VoucherEntity getByEndDate(Date date, String code);
}

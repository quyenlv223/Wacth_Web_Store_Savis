package com.example.smart.repo;

import com.example.smart.entity.ProductDetailStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailStatusRepo extends JpaRepository<ProductDetailStatusEntity, Long> {

    @Query("select o from ProductDetailStatusEntity o where o.deleteFlag = false  and o.romEntity.id = ?1")
    List<ProductDetailStatusEntity> findByRom(Long id);


    @Query("select o from ProductDetailStatusEntity o where o.status = 'ON' and o.deleteFlag = false  and o.romEntity.id = ?1")
    List<ProductDetailStatusEntity> findByRomAAndStatus(Long id);

    @Query("select o from ProductDetailStatusEntity o where o.deleteFlag = false  and o.romEntity.id = ?1 and o.colorEntity.id = ?2")
    List<ProductDetailStatusEntity> findByRomAndColor(Long romId, Long colorId);

    @Query("select o from ProductDetailStatusEntity o where o.deleteFlag = false  and  o.colorEntity.id = ?1")
    List<ProductDetailStatusEntity> findByColor( Long colorId);


    List<ProductDetailStatusEntity> findByDeleteFlagIsFalse();


}

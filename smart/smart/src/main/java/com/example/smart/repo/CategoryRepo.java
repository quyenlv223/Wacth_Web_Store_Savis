package com.example.smart.repo;


import com.example.smart.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<CategoryEntity, Long> {

    //@Query("select o from CategoryEntity o where o.deleteFlag = false  order by o.createDate asc ")
    List<CategoryEntity> findByDeleteFlagIsFalseOrderByCreateDateAsc();


    List<CategoryEntity> findByIdAndDeleteFlagIsFalse(Long id);

    List<CategoryEntity> findByNameAndDeleteFlagIsFalse(String name);
}

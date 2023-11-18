package com.example.smart.repo;

import com.example.smart.entity.ColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ColorRepo extends JpaRepository<ColorEntity, Long> {

    List<ColorEntity> findByDeleteFlagIsFalse();

    ColorEntity findByIdAndDeleteFlagIsFalse(Long id);
}

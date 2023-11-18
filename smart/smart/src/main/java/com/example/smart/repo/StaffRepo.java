package com.example.smart.repo;

import com.example.smart.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepo extends JpaRepository<StaffEntity, Long> {

    StaffEntity findByIdAndDeleteFlagIsFalse(Long id);

    List<StaffEntity> findAllByDeleteFlagIsTrue();

    // Staffs not deleted
    List<StaffEntity> findAllByDeleteFlagIsFalse();

    // Staffs active & not delete
    List<StaffEntity> findAllByStatusIsTrueAndDeleteFlagIsTrue();

    // Staffs inactive & not delete
    List<StaffEntity> findAllByStatusIsFalseAndDeleteFlagIsTrue();


    // Find staff by email
    @Query("select o from StaffEntity o where o.status = '1' and o.deleteFlag = false  and  o.email = ?1")
    List<StaffEntity> findByEmailAndDeleteFlagIsFalse(String email);

    // Find staff by phoneNumber
    List<StaffEntity> findByPhoneNumber(String phoneNumber);


}

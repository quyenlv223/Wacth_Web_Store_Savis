package com.example.smart.service.impl;

import com.example.smart.constant.ConstansErrorCode;
import com.example.smart.dto.request.staff.StaffAddRequestDTO;
import com.example.smart.dto.request.staff.StaffEditRequestDTO;
import com.example.smart.dto.respone.staff.StaffResponeDto;
import com.example.smart.entity.StaffEntity;
import com.example.smart.exception.SmartExp;
import com.example.smart.repo.StaffRepo;
import com.example.smart.service.IStaffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StaffServiceImpl implements IStaffService {
    private static final String DEFAULT_PASSWORD = "$2a$10$VvjMsczCmbmSM3L7usA85.e1pmfaeJERrSY1swgFBqe6h.sN3EKqa"; // default password 0123456789

    @Autowired
    private StaffRepo staffRepo;

    private PasswordEncoder encoder = new BCryptPasswordEncoder();


    @Override
    public List<StaffResponeDto> findAll() {
        List<StaffResponeDto> list = new ArrayList<>();
        List<StaffEntity> entities = staffRepo.findAll();

        // Convert Entity to DTO
        entities.forEach(e -> {
            StaffResponeDto dto = entityToDto(e);
            list.add(dto);
        });
        return list;
    }

    @Override
    public StaffResponeDto findById(String id) {
        StaffEntity entity = staffRepo.getById(Long.valueOf(id));
        return entityToDto(entity);
    }

    @Override
    public List<StaffResponeDto> findAllByDeleteFlagIsTrue() {
        return null;
    }

    @Override
    public List<StaffResponeDto> findAllByDeleteFlagIsFalse() {
        List<StaffResponeDto> list = new ArrayList<>();
        List<StaffEntity> entities = staffRepo.findAllByDeleteFlagIsFalse();

        // Convert Entity to DTO
        entities.forEach(e -> {
            StaffResponeDto dto = entityToDto(e);
            list.add(dto);
        });

        return list;
    }

    @Override
    public List<StaffResponeDto> findAllByStatusIsTrueAndDeleteFlagIsTrue() {
        return null;
    }

    @Override
    public List<StaffResponeDto> findAllByStatusIsFalseAndDeleteFlagIsTrue() {
        return null;
    }

    @Override
    public String addStaff(StaffAddRequestDTO staff) {
        StaffEntity entity = new StaffEntity();
        if (staffRepo.findByEmailAndDeleteFlagIsFalse(staff.getEmail()).size() != 0) {
            throw new SmartExp(ConstansErrorCode.STAFF_EMAIL);
        }
        entity.setFullName(staff.getFullName());
        entity.setEmail(staff.getEmail());
        entity.setPassword(DEFAULT_PASSWORD);
        entity.setRole(staff.getRole());
        entity.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        entity.setModifierDate(Timestamp.valueOf(LocalDateTime.now()));
        entity.setPhoneNumber(staff.getPhoneNumber());
        entity.setAddress(staff.getAddress());
        entity.setAvatar(staff.getAvatar());
        entity.setDateOfBirth(staff.getDateOfBirth());
        entity.setStatus("1");
        entity.setNote(staff.getNote());
        try {
            staffRepo.save(entity);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return "ok";
    }

    @Override
    public String editStaff(StaffEditRequestDTO staff) {
        StaffEntity entity = staffRepo.getById(Long.valueOf(staff.getId()));
        entity.setFullName(staff.getFullName());
        entity.setRole(staff.getRole());
        entity.setPhoneNumber(staff.getPhoneNumber());
        entity.setAddress(staff.getAddress());
        entity.setAvatar(staff.getAvatar());
        entity.setNote(staff.getNote());

        try {
            entity = staffRepo.save(entity);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "ok";
    }

    @Override
    public boolean deleteStaff(String id) {
        Optional<StaffEntity> optionalStaff = staffRepo.findById(Long.valueOf(id));

        if (optionalStaff.isPresent()) {
            StaffEntity entity = optionalStaff.get();
            entity.setDeleteFlag(true);
            staffRepo.save(entity);
            return true;
        }
        return false;
    }

    @Override
    public boolean changeStatusStaff(String id, String status) {
        Optional<StaffEntity> optionalStaff = staffRepo.findById(Long.valueOf(id));

        if (optionalStaff.isPresent()) {
            StaffEntity entity = optionalStaff.get();
            entity.setStatus(status);

            try {
                staffRepo.save(entity);
            } catch (Exception e) {
                log.error(e.getMessage());
            }

            return true;
        }
        return false;
    }

    @Override
    public Integer findByEmail(String email) {
        List<StaffEntity> list = staffRepo.findByEmailAndDeleteFlagIsFalse(email);
        if (list.isEmpty()) return 0;
        else return list.size();
    }

    @Override
    public Integer findByPhone(String phoneNumber) {
        List<StaffEntity> list = staffRepo.findByPhoneNumber(phoneNumber);
        if (list.isEmpty()) return 0;
        else return list.size();
    }

    @Override
    public StaffEntity getByEmail(String email) {
        List<StaffEntity> list = staffRepo.findByEmailAndDeleteFlagIsFalse(email);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public StaffEntity saveStaff(StaffEntity staffEntity) {
        return null;
    }

    @Override
    public String changePass(Long id, String oldPass, String newPass) {
        BCryptPasswordEncoder pass = new BCryptPasswordEncoder();
        StaffEntity entity = staffRepo.findByIdAndDeleteFlagIsFalse(id);
        if(!pass.matches(oldPass, entity.getPassword())){
            return new SmartExp(ConstansErrorCode.STAFF_NOT_MATCHES_PASS).getErrorMessage().getVn();
        }
        entity.setPassword(encoder.encode(newPass));
        staffRepo.save(entity);
        return "ok";
    }


    // Convert Staff from Entity to DTO
    private StaffResponeDto entityToDto(StaffEntity entity) {
        StaffResponeDto staff = new StaffResponeDto();
        staff.setId(entity.getId());
        staff.setFullName(entity.getFullName());
        staff.setDateOfBirth(entity.getDateOfBirth());
        staff.setPhoneNumber(entity.getPhoneNumber());
        staff.setEmail(entity.getEmail());
        staff.setAddress(entity.getAddress());
        staff.setAvatar(entity.getAvatar());
        staff.setRole(entity.getRole());
        staff.setNote(entity.getNote());
        staff.setCreateDate(entity.getCreateDate());
        staff.setStatus(entity.getStatus());
        return staff;
    }
}

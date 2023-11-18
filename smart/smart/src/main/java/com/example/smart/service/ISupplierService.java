package com.example.smart.service;


import com.example.smart.dto.request.supplier.EditSupplierDto;
import com.example.smart.dto.request.supplier.SupplierRequestDTO;
import com.example.smart.dto.respone.supplier.SupplierResponseDTO;

import java.util.List;

public interface ISupplierService {

    List<SupplierResponseDTO> findAllByDeleteFlagFalse();

    List<SupplierResponseDTO> findAll();


    Integer findByEmail(String email);
    Integer findByPhone(String phone);

    SupplierResponseDTO findById(Long id);


    SupplierRequestDTO addSupplier(SupplierRequestDTO requestDTO);

    EditSupplierDto updateSupplier(EditSupplierDto requestDTO);

    boolean deleteSupplier(Long id);

    boolean changeStatus(Long id, String status);




}

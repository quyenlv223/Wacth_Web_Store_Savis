package com.example.smart.api;

import com.example.smart.dto.request.imei.ImeiRequest;
import com.example.smart.repo.SeriesRepo;
import com.example.smart.service.ISeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/imei")
@RequiredArgsConstructor
public class SeriesApi {
    @Autowired
    private SeriesRepo imeiRepo;

    @Autowired
    private ISeriesService service;


    @GetMapping("{id}")
    public ResponseEntity<?> index(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(imeiRepo.findByDeleteFlagIsFalseAndPropertyProductId(id));
    }

    @GetMapping("{id}/{order}")
    public ResponseEntity<?> findImeiDaBan(@PathVariable("id") Long id, @PathVariable("order") Long order){
        return ResponseEntity.ok().body(service.findImeiDaBan(id, order));
    }


    @PostMapping("{id}")
    public ResponseEntity<?> saveImei(@RequestBody List<ImeiRequest> list, @PathVariable("id") Long id){
        if(service.saveImei(list, id)){
            return ResponseEntity.ok().body(new ImeiRequest());
        }else {
            return ResponseEntity.badRequest().body("");
        }

    }
}

package com.example.smart.api;

import com.example.smart.dto.request.promotion.PromotionRequestDTO;
import com.example.smart.service.IPromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/promotion")
public class PromotionApi {
    @Autowired
    private IPromotionService service;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody PromotionRequestDTO promotionRequestDTO){
        System.out.printf(String.valueOf(promotionRequestDTO.getProductIds().size()));
        String check = service.create(promotionRequestDTO);
        if(check.equals("ok")){
            return ResponseEntity.ok().body("ok");
        }
        return ResponseEntity.badRequest().body("false");
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody PromotionRequestDTO promotionRequestDTO){

        String check = service.update(promotionRequestDTO);
        if(check.equals("ok")){
            return ResponseEntity.ok().body("ok");
        }
        return ResponseEntity.badRequest().body("false");
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id ){
        service.delete(id);
        return ResponseEntity.ok().body("ok");

    }

    @GetMapping("/active/{id}")
    public ResponseEntity<?> active(@PathVariable("id") Long id ){
        service.activePromotion(String.valueOf(id));
        return ResponseEntity.ok().body("ok");

    }

    @GetMapping("/unactive/{id}")
    public ResponseEntity<?> anacvite(@PathVariable("id") Long id ){
        service.inActivePromotion(String.valueOf(id));
        return ResponseEntity.ok().body("ok");

    }
}

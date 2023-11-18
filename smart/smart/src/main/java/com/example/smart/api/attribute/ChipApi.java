package com.example.smart.api.attribute;

import com.example.smart.dto.request.attribute.chip.ChipRequestDto;
import com.example.smart.dto.respone.attribute.chip.ChipRespone;
import com.example.smart.service.IChipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chip")
public class ChipApi {

    @Autowired
    private IChipService service;

    @GetMapping("/{id}")
    public ChipRespone findByCate(@PathVariable("id") String id) {
        return service.findByCate(id);
    }

    @PostMapping
    public ResponseEntity<?> addChip(@RequestBody ChipRequestDto request) {
        String status = service.createChip(request);
        if (status.equalsIgnoreCase("ok")) {
            return ResponseEntity.ok().body(request);
        }
        return ResponseEntity.badRequest().body(request);
    }

    @PutMapping
    public ResponseEntity<?> updateChip(@RequestBody ChipRequestDto request) {
        String status = service.updateChip(request);
        if (status.equalsIgnoreCase("ok")) {
            return ResponseEntity.ok().body(request);
        }
        return ResponseEntity.badRequest().body(request);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteChip(@PathVariable("id") Long id) {
        String status = service.deleteChip(id);
        if (status.equalsIgnoreCase("ok")) {
            return ResponseEntity.ok().body("ok");
        }
        return ResponseEntity.badRequest().body("false");
    }
}

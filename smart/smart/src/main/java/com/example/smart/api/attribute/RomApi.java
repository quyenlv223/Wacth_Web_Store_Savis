package com.example.smart.api.attribute;

import com.example.smart.service.IRomValueService;
import com.example.smart.dto.request.attribute.rom.RomRequest;
import com.example.smart.dto.respone.attribute.rom.RomRespone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/rom")
public class RomApi {
    @Autowired
    private IRomValueService service;

    @GetMapping("/{id}")
    public RomRespone findById(@PathVariable("id") String id) {
        return service.findById(Long.valueOf(id));
    }

    @PostMapping
    public ResponseEntity<?> addScreen(@RequestBody RomRequest request) {
        String status = service.save(request);
        if (status.equalsIgnoreCase("ok")) {
            return ResponseEntity.ok().body(request);
        }
        return ResponseEntity.badRequest().body(request);
    }

    @PutMapping
    public ResponseEntity<?> updateScreen(@RequestBody RomRequest request) {
        String status = service.update(request);
        if (status.equalsIgnoreCase("ok")) {
            return ResponseEntity.ok().body(request);
        }
        return ResponseEntity.badRequest().body(request);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteScreen(@PathVariable("id") Long id) {
        String status = service.delete(id);
        if (status.equalsIgnoreCase("ok")) {
            return ResponseEntity.ok().body("ok");
        }
        return ResponseEntity.badRequest().body("false");
    }
}

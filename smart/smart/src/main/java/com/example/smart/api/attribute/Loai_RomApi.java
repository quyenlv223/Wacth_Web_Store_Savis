package com.example.smart.api.attribute;

import com.example.smart.dto.request.attribute.loai_rom.Loai_RomRequest;
import com.example.smart.dto.respone.attribute.loai_rom.Loai_RomRespone;
import com.example.smart.service.ILoaiRomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loairom")

public class Loai_RomApi {

        @Autowired
        private ILoaiRomService service;

        @GetMapping("/{id}")
        public Loai_RomRespone findById(@PathVariable("id") String id) {
            return service.findById(id);
        }

        @PostMapping
        public ResponseEntity<?> addScreen(@RequestBody Loai_RomRequest request) {
            String status = service.createLoaiRom(request);
            if (status.equalsIgnoreCase("ok")) {
                return ResponseEntity.ok().body(request);
            }
            return ResponseEntity.badRequest().body(request);
        }

        @PutMapping
        public ResponseEntity<?> updateScreen(@RequestBody Loai_RomRequest request) {
            String status = service.updateLoaiRom(request);
            if (status.equalsIgnoreCase("ok")) {
                return ResponseEntity.ok().body(request);
            }
            return ResponseEntity.badRequest().body(request);
        }

        @DeleteMapping("{id}")
        public ResponseEntity<?> deleteScreen(@PathVariable("id") Long id) {
            System.out.println("id +   " + id);
            String status = service.deleteLoaiRom(id);

            if (status.equalsIgnoreCase("ok")) {
                return ResponseEntity.ok().body("ok");
            }
            return ResponseEntity.badRequest().body("false");
        }

    }



package com.example.smart.api.attribute;

import com.example.smart.dto.request.attribute.ram.RamRequest;
import com.example.smart.dto.respone.attribute.ram.RamRespone;
import com.example.smart.service.IRamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ram")
public class RamApi {

    @Autowired
    private IRamService ramService;

    @GetMapping("/{id}")
    public RamRespone findById(@PathVariable("id") String id) {
        return ramService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> addScreen(@RequestBody RamRequest request) {
        String status = ramService.save(request);
        if (status.equalsIgnoreCase("ok")) {
            return ResponseEntity.ok().body(request);
        }
        return ResponseEntity.badRequest().body(request);
    }

    @PutMapping
    public ResponseEntity<?> updateScreen(@RequestBody RamRequest request) {
        String status = ramService.edit(request);
        if (status.equalsIgnoreCase("ok")) {
            return ResponseEntity.ok().body(request);
        }
        return ResponseEntity.badRequest().body(request);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteScreen(@PathVariable("id") Long id) {
        String status = ramService.delete(id);
        if (status.equalsIgnoreCase("ok")) {
            return ResponseEntity.ok().body("ok");
        }
        return ResponseEntity.badRequest().body("false");
    }
}

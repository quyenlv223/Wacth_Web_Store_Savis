package com.example.smart.api.attribute;

import com.example.smart.dto.request.attribute.pin.PinRequest;
import com.example.smart.dto.respone.attribute.pin.PinRespone;
import com.example.smart.service.IPinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pin")
public class PinApi {
    @Autowired
    private IPinService pinService;

    @GetMapping("/{id}")
    public PinRespone findById(@PathVariable("id") String id) {
        return pinService.findById(Long.valueOf(id));
    }

    @PostMapping
    public ResponseEntity<?> addScreen(@RequestBody PinRequest request) {
        String status = pinService.addPin(request);
        if (status.equalsIgnoreCase("ok")) {
            return ResponseEntity.ok().body(request);
        }
        return ResponseEntity.badRequest().body(request);
    }

    @PutMapping
    public ResponseEntity<?> updateScreen(@RequestBody PinRequest request) {
        String status = pinService.editPin(request);
        if (status.equalsIgnoreCase("ok")) {
            return ResponseEntity.ok().body(request);
        }
        return ResponseEntity.badRequest().body(request);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteScreen(@PathVariable("id") Long id) {
        String status = pinService.delete(id);
        if (status.equalsIgnoreCase("ok")) {
            return ResponseEntity.ok().body("ok");
        }
        return ResponseEntity.badRequest().body("false");
    }
}

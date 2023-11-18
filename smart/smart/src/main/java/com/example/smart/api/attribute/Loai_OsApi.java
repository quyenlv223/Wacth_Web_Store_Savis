package com.example.smart.api.attribute;


import com.example.smart.dto.request.attribute.loai_os.Loai_OsRequest;
import com.example.smart.dto.respone.attribute.loai_os.Loai_OsRespone;
import com.example.smart.service.ILoaiOsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loaios")
public class Loai_OsApi {
    @Autowired
    private ILoaiOsService service;

    @GetMapping("/{id}")
    public Loai_OsRespone findById(@PathVariable("id") String id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> addScreen(@RequestBody Loai_OsRequest request) {
        String status = service.createLoaiOs(request);
        if (status.equalsIgnoreCase("ok")) {
            return ResponseEntity.ok().body(request);
        }
        return ResponseEntity.badRequest().body(request);
    }

    @PutMapping
    public ResponseEntity<?> updateScreen(@RequestBody Loai_OsRequest request) {
        String status = service.updateLoaiOs(request);
        if (status.equalsIgnoreCase("ok")) {
            return ResponseEntity.ok().body(request);
        }
        return ResponseEntity.badRequest().body(request);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteScreen(@PathVariable("id") Long id) {
        System.out.println("id +   " + id);
        String status = service.deleteLoaiOs(id);

        if (status.equalsIgnoreCase("ok")) {
            return ResponseEntity.ok().body("ok");
        }
        return ResponseEntity.badRequest().body("false");
    }

}

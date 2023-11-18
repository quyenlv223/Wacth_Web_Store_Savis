package com.example.smart.api;

import com.example.smart.dto.request.staff.StaffAddRequestDTO;
import com.example.smart.dto.request.staff.StaffEditRequestDTO;
import com.example.smart.dto.respone.staff.StaffResponeDto;
import com.example.smart.repo.StaffRepo;
import com.example.smart.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/staff")
public class StaffApi {

    @Autowired
    private StaffRepo staffRepo;

    @Autowired
    private IStaffService staffService;

    @GetMapping("/{id}")
    public StaffResponeDto findById(@PathVariable("id") String id) {
        return staffService.findById(id);
    }

    @GetMapping("/find/email/{value}")
    public Integer findByEmail(@PathVariable("value") String value) {
        return staffService.findByEmail(value);
    }

    @GetMapping("/find/phone/{value}")
    public Integer findByPhone(@PathVariable("value") String value) {
        return staffService.findByPhone(value);
    }

    @PostMapping
    public ResponseEntity<?> addStaff(@RequestBody StaffAddRequestDTO staff) {
        String status = staffService.addStaff(staff);
        if (status.equalsIgnoreCase("ok")) {
            return ResponseEntity.ok().body(staff);
        }
        return ResponseEntity.badRequest().body(staff);
    }

    @PutMapping
    public ResponseEntity<?> updateStaff(@RequestBody StaffEditRequestDTO staff) {
        staffService.editStaff(staff);
        return ResponseEntity.ok().body(staff);
    }

    @PutMapping("/status/{id}/{status}")
    public ResponseEntity<?> deleteStaff(@PathVariable("id") String id, @PathVariable("status") String status) {
        if (staffService.changeStatusStaff(id, status)) {
            return ResponseEntity.ok("oke");
        }
        return ResponseEntity.badRequest().body("fail");
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteStaff(@PathVariable("id") String id) {
        if (staffService.deleteStaff(id)) {
            return ResponseEntity.ok("oke");
        }
        return ResponseEntity.badRequest().body("fail");
    }

    @PutMapping("change-password/{id}/{oldpass}/{newpass}")
    public ResponseEntity<?> changePass(@PathVariable("id") Long id,
                                        @PathVariable("oldpass") String oldpass,
                                        @PathVariable("newpass") String newpass){
        String check = staffService.changePass(id, oldpass, newpass);
        if(check.equals("ok")){
            return ResponseEntity.ok().body("ok");
        }
        return ResponseEntity.badRequest().body("a");
    }
}

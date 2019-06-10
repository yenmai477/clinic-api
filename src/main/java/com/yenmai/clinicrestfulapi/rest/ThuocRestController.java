package com.yenmai.clinicrestfulapi.rest;

import com.yenmai.clinicrestfulapi.entity.Thuoc;
import com.yenmai.clinicrestfulapi.service.ThuocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/thuoc")
public class ThuocRestController {
    private ThuocService thuocService;

    @Autowired
    public ThuocRestController(ThuocService thuocService) {
        this.thuocService = thuocService;
    }


    @GetMapping
    public List<Thuoc> findAll(){
        return thuocService.findAll();
    }



    @GetMapping("/{maThuoc}")
    public Thuoc getThuoc(@PathVariable int maThuoc) {

        Thuoc theThuoc = thuocService.findById(maThuoc);

        if (theThuoc == null) {
            throw new RuntimeException("Không tìm thấy thuốc có mã - " + maThuoc);
        }

        return theThuoc;
    }



    @PostMapping
    public Thuoc addThuoc(@RequestBody Thuoc theThuoc) {

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update
        theThuoc.setMaThuoc(0);

        thuocService.save(theThuoc);

        return theThuoc;
    }



    @PutMapping
    public Thuoc updateThuoc(@RequestBody Thuoc theThuoc) {

        thuocService.save(theThuoc);

        return theThuoc;
    }


    @DeleteMapping("/{maThuoc}")
    public  ResponseEntity<Thuoc> deleteDrug(@PathVariable int maThuoc) {

        Thuoc tempThuoc = thuocService.findById(maThuoc);

        // throw exception if null

        if (tempThuoc == null) {
            throw new RuntimeException("Không tìm thấy thuốc có mã- " + maThuoc);
        }

        thuocService.deleteById(maThuoc);

        //return "Đã xóa thuốc có mã- " + maThuoc;
        return new ResponseEntity<Thuoc>(HttpStatus.NO_CONTENT);
    }


}

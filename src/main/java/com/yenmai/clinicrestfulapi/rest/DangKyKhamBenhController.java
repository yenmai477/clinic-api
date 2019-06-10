package com.yenmai.clinicrestfulapi.rest;

import com.yenmai.clinicrestfulapi.entity.DangKiKhamBenh;
import com.yenmai.clinicrestfulapi.model.DangKiKhamBenhDTO;
import com.yenmai.clinicrestfulapi.service.DangKiKhamBenhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dangkikhambenh")
public class DangKyKhamBenhController {

    private DangKiKhamBenhService dangKiKhamBenhService;


    @Autowired
    public DangKyKhamBenhController(DangKiKhamBenhService dangKiKhamBenhService) {
        this.dangKiKhamBenhService = dangKiKhamBenhService;
    }

    //Tìm tất cả đăng kí khám bệnh hoặc tìm tất cả đăng kí khám bệnh theo tình trạng
    @GetMapping
    public List<DangKiKhamBenh> findAll(
            @RequestParam(value = "tinhTrang", required = false) String tinhTrang,
            @RequestParam(value = "maBenhNhan", defaultValue = "0", required = false) int maBenhNhan){
        if (tinhTrang!=null) {
            return dangKiKhamBenhService.findDangKiKhamBenhByTinhTrang(tinhTrang);
        };

        if (maBenhNhan > 0) {
            return dangKiKhamBenhService.findDangKiKhamBenhByBenhNhan(maBenhNhan);
        };

        return dangKiKhamBenhService.findAll();
    }



    //Đăng kí khám bệnh chỉ tạo và xóa không được phép sửa vì không cho sửa


    @PostMapping
    public DangKiKhamBenh addDangKiKhamBenh(@RequestBody DangKiKhamBenhDTO theDangKi) {

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update

        DangKiKhamBenh tempDangKi = new DangKiKhamBenh();
        tempDangKi = dangKiKhamBenhService.createDangKiKhamBenh(theDangKi);
        tempDangKi.setMaDangKi(0);
        tempDangKi.setTinhTrang("Đang chờ");
        dangKiKhamBenhService.save(tempDangKi);
        return tempDangKi;
    }


    @PutMapping
    public DangKiKhamBenh updateDangKiKhamBenh(@RequestBody DangKiKhamBenhDTO theDangKi) {
        DangKiKhamBenh tempDangKi = new DangKiKhamBenh();
        tempDangKi = dangKiKhamBenhService.createDangKiKhamBenh(theDangKi);
        dangKiKhamBenhService.save(tempDangKi);
        return tempDangKi;
    }


    @DeleteMapping("/{maDangKi}")
    public ResponseEntity<DangKiKhamBenh> deleteDangKiKhamBenh(@PathVariable int maDangKi) {

        DangKiKhamBenh tempDangKi = dangKiKhamBenhService.findById(maDangKi);

        // throw exception if null

        if (tempDangKi == null) {
            throw new RuntimeException("Không tìm thấy đăng kí có mã - " + maDangKi);
        }

        dangKiKhamBenhService.deleteById(maDangKi);

        //return "Xóa thành công đăng kí khám bệnh - " + maDangKi;
        return new ResponseEntity<DangKiKhamBenh>(HttpStatus.NO_CONTENT);
    }


}

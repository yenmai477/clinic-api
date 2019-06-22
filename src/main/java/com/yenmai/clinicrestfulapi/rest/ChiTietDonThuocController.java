package com.yenmai.clinicrestfulapi.rest;

import com.yenmai.clinicrestfulapi.entity.ChiTietDonThuoc;
import com.yenmai.clinicrestfulapi.model.ChiTietDonThuocDTO;
import com.yenmai.clinicrestfulapi.service.ChiTietDonThuocService;
import com.yenmai.clinicrestfulapi.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/chitietdonthuoc")
@PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
public class ChiTietDonThuocController {
    @Autowired
    private ChiTietDonThuocService chiTietDonThuocService;

    @Autowired
    private HoaDonService hoaDonService;





    @GetMapping
    public List<ChiTietDonThuoc> findAll(@RequestParam(value = "maPhieuKham", defaultValue = "0" ,required = true)
                                                     int maPhieuKham){

        return chiTietDonThuocService.findByPhieuKhamBenh(maPhieuKham);
    }


    
    @GetMapping("/{maChiTiet}")
    public ChiTietDonThuoc getChiTietKhamBenh(@PathVariable int maChiTiet) {

        ChiTietDonThuoc theChiTiet = chiTietDonThuocService.findById(maChiTiet);

        if (theChiTiet == null) {
            throw new RuntimeException("Không tìm thấy chi tiết cần tìm - " + maChiTiet);
        }

        return theChiTiet;
    }

    
    @PostMapping
    public List<ChiTietDonThuoc> addChiTietDonThuoc(@RequestBody List<ChiTietDonThuocDTO> listChiTietDTO) {

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update



        //Tạo ra mảng các chi tiết đơn thuốc
        List<ChiTietDonThuoc> chiTietDonThuocs = new ArrayList<>();

        for(ChiTietDonThuocDTO tempChiTietDTO : listChiTietDTO){
            tempChiTietDTO.setMaChiTiet(0);

            ChiTietDonThuoc tempChiTiet = new ChiTietDonThuoc();
            tempChiTiet = chiTietDonThuocService.createorUpdateChiTietDonThuoc(tempChiTiet, tempChiTietDTO);

            //Kiểm tra bệnh nhân của đơn thuốc
            chiTietDonThuocs.add(tempChiTiet);
            chiTietDonThuocService.save(tempChiTiet);
        }





        return chiTietDonThuocs;
    }

    
    @PutMapping
    public ChiTietDonThuoc updateChiTietDonThuoc(@RequestBody ChiTietDonThuocDTO theChiTietDTO) {

        ChiTietDonThuoc tempChiTiet = null;
        tempChiTiet = chiTietDonThuocService.createorUpdateChiTietDonThuoc(tempChiTiet, theChiTietDTO);

        chiTietDonThuocService.save(tempChiTiet);

        return tempChiTiet;
    }

    
    @DeleteMapping("/{maChiTiet}")
    public String deleteEmployee(@PathVariable int maChiTiet) {

        ChiTietDonThuoc tempChiTiet = chiTietDonThuocService.findById(maChiTiet);

        // throw exception if null

        if (tempChiTiet == null) {
            throw new RuntimeException("Không tìm thấy chi tiết đơn thuốc có mã- " + maChiTiet);
        }

        chiTietDonThuocService.deleteById(maChiTiet);

        return "Đã xóa bệnh nhân có mã- " + maChiTiet;
    }


}

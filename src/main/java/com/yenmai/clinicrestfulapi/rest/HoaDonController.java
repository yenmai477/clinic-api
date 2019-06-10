package com.yenmai.clinicrestfulapi.rest;

import com.yenmai.clinicrestfulapi.entity.HoaDon;
import com.yenmai.clinicrestfulapi.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hoadon")
public class HoaDonController {
    @Autowired
    private HoaDonService hoaDonService;



    @GetMapping
    public List<HoaDon> findAll(){
        return hoaDonService.findAll();
    }



    @GetMapping("/{maHoaDon}")
    public HoaDon getHoaDon(@PathVariable int maHoaDon) {

        HoaDon theHoaDon = hoaDonService.findById(maHoaDon);

        if (theHoaDon == null) {
            throw new RuntimeException("Không tìm thấy thuốc có mã - " + maHoaDon);
        }

        return theHoaDon;
    }



    @PostMapping
    public HoaDon addHoaDon(@RequestBody HoaDon theHoaDon) {

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update
        theHoaDon.setMaHoaDon(0);

        hoaDonService.save(theHoaDon);

        return theHoaDon;
    }



    @PutMapping
    public HoaDon updateHoaDon(@RequestBody HoaDon theHoaDon) {

        hoaDonService.save(theHoaDon);

        return theHoaDon;
    }


    @DeleteMapping("/{maHoaDon}")
    public String deleteHoaDon(@PathVariable int maHoaDon) {

        HoaDon tempHoaDon = hoaDonService.findById(maHoaDon);

        // throw exception if null

        if (tempHoaDon == null) {
            throw new RuntimeException("Không tìm thấy hóa đơn có mã- " + maHoaDon);
        }

        hoaDonService.deleteById(maHoaDon);

        return "Đã xóa hóa đơn có mã- " + maHoaDon;
    }


}

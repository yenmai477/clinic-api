package com.yenmai.clinicrestfulapi.rest;

import com.yenmai.clinicrestfulapi.model.CardInfoDTO;
import com.yenmai.clinicrestfulapi.model.GroupByValueDTO;
import com.yenmai.clinicrestfulapi.service.TongQuanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/tongquan")
public class TongQuanController {

    @Autowired
    private TongQuanService tongQuanService;



    @GetMapping("/cardInfo")
    public CardInfoDTO getAllCardInfo(){
        return tongQuanService.getAllCardInfo();
    }

    @GetMapping("/doanhthu")
    public List<GroupByValueDTO> getDoanhThuTheoThang(){
        return tongQuanService.getDoanhThuTheoThang();
    }

    @GetMapping("/tylegioitinh")
    public List<GroupByValueDTO> getBenhNhanTheoGioiTinh(){
        return tongQuanService.getBenhNhanTheoGioiTinh();
    }

    @GetMapping("/tylechucvu")
    public List<GroupByValueDTO> getNhanVienTheoChucVu(){
        return tongQuanService.getNhanVienTheoChucVu();
    }

}

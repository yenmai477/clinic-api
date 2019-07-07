package com.yenmai.clinicrestfulapi.rest;

import com.yenmai.clinicrestfulapi.model.*;
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
    public List<DoanhThuThangDTO> getDoanhThuTheoThang(){
        return tongQuanService.getDoanhThuTheoThang();
    }

    @GetMapping("/tylegioitinh")
    public List<GioiTinhDTO> getBenhNhanTheoGioiTinh(){
        return tongQuanService.getBenhNhanTheoGioiTinh();
    }

    @GetMapping("/tylechucvu")
    public List<GroupByValueDTO> getNhanVienTheoChucVu(){
        return tongQuanService.getNhanVienTheoChucVu();
    }

    @GetMapping("/doanhthumuoihaithang")
    public List <DoanhThuThangDTO> getDoanhThuMuoiHaiThang() {
        return tongQuanService.doanhThuMuoiHaiThangQua();
    }

    @GetMapping
    public TongQuanResponseDTO getAll() {
        TongQuanResponseDTO tempResponseDTO = new TongQuanResponseDTO();
        tempResponseDTO.setCardInfoDTO(tongQuanService.getAllCardInfo());
        tempResponseDTO.setDoanhThuTheoThang(tongQuanService.getDoanhThuTheoThang());
        tempResponseDTO.setTyleChucVu(tongQuanService.getNhanVienTheoChucVu());
        tempResponseDTO.setTyleGioiTinh(tongQuanService.getBenhNhanTheoGioiTinh());
        tempResponseDTO.setRandomNhanVien(tongQuanService.randomNhanVien());
        tempResponseDTO.setBenhNhanMoi(tongQuanService.findBenhNhanMoiNhat());
        tempResponseDTO.setDoanhThuMuoiHaiThang(tongQuanService.doanhThuMuoiHaiThangQua());
        return tempResponseDTO;
    }

}

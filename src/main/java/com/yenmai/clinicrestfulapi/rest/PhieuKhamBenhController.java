package com.yenmai.clinicrestfulapi.rest;

import com.yenmai.clinicrestfulapi.entity.ChiTietDonThuoc;
import com.yenmai.clinicrestfulapi.entity.HoaDon;
import com.yenmai.clinicrestfulapi.entity.PhieuKhamBenh;
import com.yenmai.clinicrestfulapi.model.PhieuKhamBenhDTO;
import com.yenmai.clinicrestfulapi.model.PhieuKhamBenhResponseDTO;
import com.yenmai.clinicrestfulapi.model.PhieuKhamResponseDTO;
import com.yenmai.clinicrestfulapi.service.ChiTietDonThuocService;
import com.yenmai.clinicrestfulapi.service.HoaDonService;
import com.yenmai.clinicrestfulapi.service.PhieuKhamBenhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/phieukhambenh")
public class PhieuKhamBenhController {
    @Autowired
    private PhieuKhamBenhService phieuKhamBenhService;

    @Autowired
    private ChiTietDonThuocService chiTietDonThuocService;

    @Autowired
    private HoaDonService hoaDonService;



    //Lấy ra tất cả phiếu khám bệnh do một bác sĩ lập
    @GetMapping
    public List<PhieuKhamBenh> findPhieuKhamByBacSi(
            @RequestParam(value = "maDangKi", defaultValue = "0", required = false) int maDangKi,
            @RequestParam(value = "maBacSi", defaultValue = "0", required = false) int maBacSi){
        //Lấy ra phiếu khám bệnh của đăng kí khám bệnh
        if(maDangKi > 0) {
            List<PhieuKhamBenh>  list = new ArrayList<>();
            list.add(phieuKhamBenhService.findByDangKiKhamBenh(maDangKi));
            return list;
        }
        return phieuKhamBenhService.findByBacSi(maBacSi);
    }




    @GetMapping("/{maPhieuKham}")
    public PhieuKhamBenhResponseDTO getPhieuKham(@PathVariable int maPhieuKham) {

        PhieuKhamBenh thePhieuKham = phieuKhamBenhService.findById(maPhieuKham);


        if (thePhieuKham == null) {
            throw new RuntimeException("Không tìm thấy phiếu khám bệnh có mã - " + maPhieuKham);
        }

        PhieuKhamBenhResponseDTO tempDTO= new PhieuKhamBenhResponseDTO();

        tempDTO.setMaPhieuKham(thePhieuKham.getMaPhieuKham());

        tempDTO.setChuanDoan(thePhieuKham.getChuanDoan());

        tempDTO.setTrieuChung(thePhieuKham.getTrieuChung());

        tempDTO.setTrieuChung(thePhieuKham.getTrieuChung());

        tempDTO.setTenBenhNhan(thePhieuKham.getDangKiKhamBenh().getBenhNhan().getTenBenhNhan());

        tempDTO.setDiaChi(thePhieuKham.getDangKiKhamBenh().getBenhNhan().getDiaChi());

        tempDTO.setTenBacSi(thePhieuKham.getBacSi().getTenNhanVien());

        tempDTO.setChiTietDonThuocList(thePhieuKham.getChiTietDonThuocs());

        tempDTO.setNgayThem(thePhieuKham.getNgayThem());

        return tempDTO;
    }



    @PostMapping
    public PhieuKhamResponseDTO addPhieuKham(
            @RequestBody PhieuKhamBenhDTO thePhieuKhamDTO) {

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update
        //Chuyển Phiếu khám bệnh từ DTO --->Entity
        PhieuKhamBenh tempPhieuKham = new PhieuKhamBenh();
        tempPhieuKham = phieuKhamBenhService.createorUpdatePhieuKhamBenh(tempPhieuKham, thePhieuKhamDTO);

        //Lưu phiếu khám bệnh
        tempPhieuKham.setMaPhieuKham(0);
        tempPhieuKham.getDangKiKhamBenh().setTinhTrang("Đã khám");
        phieuKhamBenhService.save(tempPhieuKham);

        HoaDon temHoaDon = new HoaDon();
        temHoaDon.setBenhNhan(tempPhieuKham.getDangKiKhamBenh().getBenhNhan());
        temHoaDon.setTinhTrang("Chưa thanh toán");
        temHoaDon.setThanhTien(300000);
        hoaDonService.save(temHoaDon);


        //Trả về dữ liêu
        PhieuKhamResponseDTO tempResponse = new PhieuKhamResponseDTO(tempPhieuKham,
                                                                    temHoaDon.getMaHoaDon());

        return tempResponse;
    }

    // Cập nhật phiếu khám bệnh

    @PutMapping
    public PhieuKhamBenh updatePhieuKham(@RequestBody PhieuKhamBenhDTO thePhieuKhamDTO) {
        PhieuKhamBenh tempPhieuKham = phieuKhamBenhService.findById(thePhieuKhamDTO.getMaPhieuKham());
        tempPhieuKham = phieuKhamBenhService.createorUpdatePhieuKhamBenh(tempPhieuKham, thePhieuKhamDTO);



        phieuKhamBenhService.save(tempPhieuKham);
        return tempPhieuKham;
    }

    // Xóa một bệnh nhân trong danh sách bệnh nhân

    @DeleteMapping("/{maPhieuKham}")
    public String deletePhieuKham(@PathVariable int maPhieuKham) {

        PhieuKhamBenh tempPhieuKham = phieuKhamBenhService.findById(maPhieuKham);


        // throw exception if null

        if (tempPhieuKham == null) {
            throw new RuntimeException("Không tìm thấy phiếu khám có mã " + maPhieuKham);
        }

        for (ChiTietDonThuoc chiTietDonThuoc : tempPhieuKham.getChiTietDonThuocs()) {

            tempPhieuKham.remove(chiTietDonThuoc);
        }


        phieuKhamBenhService.deleteById(maPhieuKham);

        return "Đã xóa phiếu khám bệnh có mã- " + maPhieuKham;
    }


}



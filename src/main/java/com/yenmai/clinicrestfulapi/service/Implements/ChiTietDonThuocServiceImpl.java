package com.yenmai.clinicrestfulapi.service.Implements;

import com.yenmai.clinicrestfulapi.dao.ChiTietDonThuocRespository;
import com.yenmai.clinicrestfulapi.dao.HoaDonRespository;
import com.yenmai.clinicrestfulapi.dao.PhieuKhamBenhRespository;
import com.yenmai.clinicrestfulapi.dao.ThuocRespository;
import com.yenmai.clinicrestfulapi.entity.*;
import com.yenmai.clinicrestfulapi.model.ChiTietDonThuocDTO;
import com.yenmai.clinicrestfulapi.service.ChiTietDonThuocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ChiTietDonThuocServiceImpl implements ChiTietDonThuocService {
    private ChiTietDonThuocRespository chiTietDonThuocRespository;

    @Autowired
    private ThuocRespository thuocRespository;

    @Autowired
    private PhieuKhamBenhRespository phieuKhamBenhRespository;

    @Autowired
    private HoaDonRespository hoaDonRespository;

    @Autowired
    public ChiTietDonThuocServiceImpl(ChiTietDonThuocRespository chiTietDonThuocRespository) {

        this.chiTietDonThuocRespository = chiTietDonThuocRespository;
    }

    @Override
    @Transactional
    public List<ChiTietDonThuoc> findAll() {

        return chiTietDonThuocRespository.findAll();
    }


    @Override
    @Transactional
    public ChiTietDonThuoc findById(int maChiTiet) {
        Optional<ChiTietDonThuoc> result = chiTietDonThuocRespository.findById(maChiTiet);

        ChiTietDonThuoc theChiTiet = null;

        if (result.isPresent()) {
            theChiTiet = result.get();
        } else {

            throw new RuntimeException("Không tìm thấy chi tiết đơn thuốc có mã - " + maChiTiet);
        }

        return theChiTiet;
    }

    @Override
    @Transactional
    public void save(ChiTietDonThuoc chiTietDonThuoc) {
        chiTietDonThuocRespository.save(chiTietDonThuoc);
    }

    @Override
    @Transactional
    public void deleteById(int maChiTiet) {
        chiTietDonThuocRespository.deleteById(maChiTiet);
    }

    @Override
    @Transactional
    public ChiTietDonThuoc createorUpdateChiTietDonThuoc(ChiTietDonThuoc chiTietDonThuoc,
                                                       ChiTietDonThuocDTO chiTietDonThuocDTO){

        chiTietDonThuoc.setCachDung(chiTietDonThuocDTO.getCachDung());

        chiTietDonThuoc.setSoLuong(chiTietDonThuocDTO.getSoLuong());

        // tạm thời không giải quyết thành tiền ở đây chiTietDonThuoc.setThanhTien();

        Thuoc tempThuoc =null;
        tempThuoc = thuocRespository.findById(chiTietDonThuocDTO.getMaThuoc()).get();
        chiTietDonThuoc.setThuoc(tempThuoc);

        PhieuKhamBenh tempPhieuKham = null;
        tempPhieuKham = phieuKhamBenhRespository.findById(chiTietDonThuocDTO.getMaPhieuKham()).get();
        chiTietDonThuoc.setPhieuKhamBenh(tempPhieuKham);

        HoaDon tempHoaDon = null;
        System.out.println(chiTietDonThuocDTO.getMaHoaDon());
        tempHoaDon = hoaDonRespository.findById(chiTietDonThuocDTO.getMaHoaDon()).get();

        chiTietDonThuoc.setHoaDon(tempHoaDon);

        return  chiTietDonThuoc;
    }

    @Override
    public List<ChiTietDonThuoc> findByPhieuKhamBenh(int maPhieuKham) {
        return chiTietDonThuocRespository.findByPhieuKhamBenh(maPhieuKham);
    }

}

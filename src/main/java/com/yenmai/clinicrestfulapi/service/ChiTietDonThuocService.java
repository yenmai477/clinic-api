package com.yenmai.clinicrestfulapi.service;

import com.yenmai.clinicrestfulapi.entity.ChiTietDonThuoc;
import com.yenmai.clinicrestfulapi.model.ChiTietDonThuocDTO;

import java.util.List;

public interface ChiTietDonThuocService {

    public List<ChiTietDonThuoc> findAll();

    public ChiTietDonThuoc findById(int maChiTiet);

    public void save(ChiTietDonThuoc chiTietDonThuoc);

    public void deleteById(int maChiTiet);

    public ChiTietDonThuoc createorUpdateChiTietDonThuoc(ChiTietDonThuoc chiTietDonThuoc,
                                                         ChiTietDonThuocDTO chiTietDonThuocDTO);

    public List<ChiTietDonThuoc> findByPhieuKhamBenh(int maPhieuKham);

}

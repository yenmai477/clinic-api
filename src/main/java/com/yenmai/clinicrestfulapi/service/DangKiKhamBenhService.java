package com.yenmai.clinicrestfulapi.service;

import com.yenmai.clinicrestfulapi.entity.DangKiKhamBenh;
import com.yenmai.clinicrestfulapi.model.DangKiKhamBenhDTO;

import java.util.List;

public interface DangKiKhamBenhService {

    public List<DangKiKhamBenh> findAll();

    public List<DangKiKhamBenh> findDangKiKhamBenhByBenhNhan(int maBenhNhan);

    public DangKiKhamBenh findById(int maDangKi);

    public void save(DangKiKhamBenh dangKiKhamBenh);

    public List<DangKiKhamBenh> findDangKiKhamBenhByTinhTrang(String tinhTrang);

    public void deleteById(int maPhieuKhamBenh);

    public List<DangKiKhamBenh> findByNgayDangKi(String ngayDangKi);

    public DangKiKhamBenh createDangKiKhamBenh(DangKiKhamBenhDTO theDangKiDTO);
}

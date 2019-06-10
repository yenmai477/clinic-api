package com.yenmai.clinicrestfulapi.service;

import com.yenmai.clinicrestfulapi.entity.PhieuKhamBenh;
import com.yenmai.clinicrestfulapi.model.PhieuKhamBenhDTO;

import java.util.List;

public interface PhieuKhamBenhService {

    //Tìm tất cả phiếu khám bệnh của một bệnh nhân
    public List<PhieuKhamBenh> findByBenhNhan(int maBenhNhan);

    //Tìm tất cả các phiếu khám bệnh của một bác sĩ lập
    public List<PhieuKhamBenh>findByBacSi(int maBacSi);

    //Tìm phiếu khám bệnh của đăng kí
    PhieuKhamBenh findByDangKiKhamBenh(int maDangKi);

    //Thêm một phiếu khám bệnh
    public PhieuKhamBenh findById(int maPhieuKham);


    //Sửa hoặc tạo một phiếu khám bệnh
    public void save(PhieuKhamBenh phieuKhamBenh);

    //Xóa một phiếu khám bệnh
    public void deleteById(int maPhieuKham);

    //Tạo một entity từ DTO
    public  PhieuKhamBenh createorUpdatePhieuKhamBenh(PhieuKhamBenh phieuKhamBenh,PhieuKhamBenhDTO phieuKhamBenhDTO);

}

package com.yenmai.clinicrestfulapi.dao;

import com.yenmai.clinicrestfulapi.entity.PhieuKhamBenh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhieuKhamBenhRespository extends JpaRepository<PhieuKhamBenh, Integer> {
    //Tìm tất cả phiếu khám bệnh của một bệnh nhân
    @Query( value = "select * from BenhNhan a, "
            + "PhieuKhamBenh b, DangKiKhamBenh c "
            +"where a.MaBenhNhan = c.MaBenhnhan "
            +"and  b.maDangKi = c.MaDangKi "
            +"and b.MaBenhNhan = :maBenhNhan", nativeQuery = true)
    List<PhieuKhamBenh> findByBenhNhan(int maBenhNhan);

    //Tìm tất cả các phiếu khám bệnh của một bác sĩ lập
    @Query( value = "select * from PhieuKhamBenh a "
            +"where a.MaBacSi = :maBacSi", nativeQuery = true)
    List<PhieuKhamBenh>findByBacSi(int maBacSi);


    //Tìm phiếu khám bệnh của đăng kí
    @Query( value = "select * from PhieuKhamBenh a "
            +"where a.MaDangKi = :maDangKi", nativeQuery = true)
    PhieuKhamBenh findByDangKiKhamBenh(int maDangKi);




}

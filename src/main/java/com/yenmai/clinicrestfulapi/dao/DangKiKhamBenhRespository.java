package com.yenmai.clinicrestfulapi.dao;

import com.yenmai.clinicrestfulapi.entity.DangKiKhamBenh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DangKiKhamBenhRespository extends JpaRepository<DangKiKhamBenh, Integer> {
    @Query( value = "select * from BenhNhan a " +
            "join DangKiKhamBenh b" +
            "   on a.MaBenhNhan = b.MaBenhNhan " +
            "where b.MaBenhNhan = :maBenhNhan "
            +"order by b.NgayDangki", nativeQuery = true)
    List<DangKiKhamBenh> findDangKiKhamBenhByBenhNhan(int maBenhNhan);

    List<DangKiKhamBenh> findByNgayDangKi(String ngayDangKi);

    List<DangKiKhamBenh> findDangKiKhamBenhByTinhTrang(String tinhTrang);


}

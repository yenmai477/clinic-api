package com.yenmai.clinicrestfulapi.dao;

import com.yenmai.clinicrestfulapi.entity.ChiTietDonThuoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChiTietDonThuocRespository extends JpaRepository<ChiTietDonThuoc, Integer> {

    //Tìm chi tiết đơn thuốc của phiếu khám bệnh
    @Query( value = "select * from ChiTietDonThuoc a "
            +"where a.MaPhieuKhamBenh = :maPhieuKham", nativeQuery = true)
    List<ChiTietDonThuoc> findByPhieuKhamBenh(int maPhieuKham);

    //Tìm chi tiết đơn thuốc của phiếu khám bệnh
    @Query( value = "select * from ChiTietDonThuoc a "
            +"where a.MaHoaDon = :maHoaDon", nativeQuery = true)
    List<ChiTietDonThuoc> findByHoaDon(int maHoaDon);

    @Query( value = "select * from ChiTietDonThuoc a "
            + "where year(ngaythem) = :namThem "
            + "and month(ngaythem) = :thangThem "
            + "order by ngaythem", nativeQuery = true)
    List<ChiTietDonThuoc> findByThangThem(int thangThem, int namThem);
}

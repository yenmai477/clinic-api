package com.yenmai.clinicrestfulapi.dao;

import com.yenmai.clinicrestfulapi.entity.BenhNhan;
import com.yenmai.clinicrestfulapi.model.GroupByGioiTinh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BenhNhanRespository extends JpaRepository<BenhNhan, Integer> {
    List<BenhNhan> findByTenBenhNhanLike(String tenBenhNhan);

    @Query( value = "select * from BenhNhan a " +
            "where year(ngaythem) = :namThem "
            +"and month(ngaythem) = :thangThem ", nativeQuery = true)
    List<BenhNhan>findBenhNhanByThangThem(int thangThem, int namThem);


    @Query( value = "select quarter(ngaythem) as quy ,GIOITINH as gioiTinh, " +
            " count(MABENHNHAN) as giaTri from benhnhan" +
            " where year(ngaythem) = year(now())" +
            " group by quy, gioitinh" +
            " order by quy;", nativeQuery = true)
    List<GroupByGioiTinh> groupBenhNhanByGioiTinh();


    @Query( value = "select  * from BENHNHAN order by ngaythem desc limit 10",
            nativeQuery = true)
    List<BenhNhan> findBenhNhanMoiNhat();

}

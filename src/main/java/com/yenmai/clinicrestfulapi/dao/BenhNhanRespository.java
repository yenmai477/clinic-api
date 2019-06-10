package com.yenmai.clinicrestfulapi.dao;

import com.yenmai.clinicrestfulapi.entity.BenhNhan;
import com.yenmai.clinicrestfulapi.report.model.GroupByResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BenhNhanRespository extends JpaRepository<BenhNhan, Integer> {
    List<BenhNhan> findByTenBenhNhanLike(String tenBenhNhan);

    @Query( value = "select * from BenhNhan a " +
            "where year(ngaythem) = :namThem "
            +"and month(ngaythem) = :thangThem ", nativeQuery = true)
    List<BenhNhan>findBenhNhanByThangThem(int thangThem, int namThem);


    @Query( value = "select GioiTinh as name, COUNT(MaBenhNhan) as value "
            + "from BENHNHAN "
            + "group by name ", nativeQuery = true)
    List<GroupByResult> groupBenhNhanByGioiTinh();


}

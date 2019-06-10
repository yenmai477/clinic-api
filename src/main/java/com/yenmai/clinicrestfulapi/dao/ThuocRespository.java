package com.yenmai.clinicrestfulapi.dao;

import com.yenmai.clinicrestfulapi.entity.Thuoc;
import com.yenmai.clinicrestfulapi.report.model.GroupByResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ThuocRespository extends JpaRepository<Thuoc, Integer> {
    //Số lượng thuốc đã dùng trong tháng
    @Query( value = "select tenthuoc as name, sum(b.soluong) as value "
            + "from thuoc a, chitietdonthuoc b "
            + "where a.mathuoc = b.mathuoc "
            + "and year(ngaythem) = :namThem "
            + "and month(ngaythem) = :thangThem "
            +  "group by a.mathuoc, tenthuoc", nativeQuery = true)
    List<GroupByResult> findThuocDaDungByThangThem(int thangThem, int namThem);
}

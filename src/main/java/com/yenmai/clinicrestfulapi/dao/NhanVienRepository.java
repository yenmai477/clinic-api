package com.yenmai.clinicrestfulapi.dao;

import com.yenmai.clinicrestfulapi.entity.NhanVien;
import com.yenmai.clinicrestfulapi.report.model.GroupByResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {

    List<NhanVien> findByTenNhanVienLike(String tenNhanVien);

    @Query( value = "select ChucVu as name, COUNT(MaNhanVien) as value "
            + "from NHANVIEN "
            + "group by name ", nativeQuery = true)
    List<GroupByResult> groupNhanVienByChucVu();


}

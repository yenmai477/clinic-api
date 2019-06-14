package com.yenmai.clinicrestfulapi.dao;

import com.yenmai.clinicrestfulapi.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaiKhoanRespository extends JpaRepository<TaiKhoan, String> {
    Optional<TaiKhoan> findByTenTaiKhoan(String tenTaiKhoan);

    Boolean existsByTenTaiKhoan(String tenTaiKhoan);

}

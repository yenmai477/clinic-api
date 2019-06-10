package com.yenmai.clinicrestfulapi.service;

import com.yenmai.clinicrestfulapi.entity.NhanVien;

import java.util.List;


public interface NhanVienService {
	//return list NhanVien for get method
		public List<NhanVien> findAll();
		public NhanVien findById(int MaNhanVien);
		public void save(NhanVien nhanVien);
		public void deleteById(int MaNhanVien);
		public List<NhanVien> findByTenNhanVienLike(String tenNhanVien);
}

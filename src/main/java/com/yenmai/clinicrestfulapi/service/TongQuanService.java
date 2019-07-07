package com.yenmai.clinicrestfulapi.service;

import com.yenmai.clinicrestfulapi.entity.BenhNhan;
import com.yenmai.clinicrestfulapi.entity.NhanVien;
import com.yenmai.clinicrestfulapi.model.CardInfoDTO;
import com.yenmai.clinicrestfulapi.model.DoanhThuThangDTO;
import com.yenmai.clinicrestfulapi.model.GioiTinhDTO;
import com.yenmai.clinicrestfulapi.model.GroupByValueDTO;

import java.util.List;

public interface TongQuanService {

    public CardInfoDTO getAllCardInfo();

    public List<DoanhThuThangDTO> getDoanhThuTheoThang();

    public List<GioiTinhDTO> getBenhNhanTheoGioiTinh();

    public List<GroupByValueDTO> getNhanVienTheoChucVu();

    public List<NhanVien> randomNhanVien();

    public  List<BenhNhan> findBenhNhanMoiNhat();

    public  List<DoanhThuThangDTO> doanhThuMuoiHaiThangQua();
}

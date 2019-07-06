package com.yenmai.clinicrestfulapi.model;

import com.yenmai.clinicrestfulapi.entity.BenhNhan;
import com.yenmai.clinicrestfulapi.entity.NhanVien;

import java.util.List;

/**
 * @author YenMai
 */
public class TongQuanResponseDTO {

    private CardInfoDTO cardInfoDTO;

    private List<GroupByValueDTO> doanhThuTheoThang;

    private  List<GroupByValueDTO> tyleGioiTinh;

    private List<GroupByValueDTO> tyleChucVu;

    private List<NhanVien> randomNhanVien;

    private  List<BenhNhan> benhNhanMoi;


    public TongQuanResponseDTO() {
    }

    public CardInfoDTO getCardInfoDTO() {
        return cardInfoDTO;
    }

    public void setCardInfoDTO(CardInfoDTO cardInfoDTO) {
        this.cardInfoDTO = cardInfoDTO;
    }

    public List<GroupByValueDTO> getDoanhThuTheoThang() {
        return doanhThuTheoThang;
    }

    public void setDoanhThuTheoThang(List<GroupByValueDTO> doanhThuTheoThang) {
        this.doanhThuTheoThang = doanhThuTheoThang;
    }

    public List<GroupByValueDTO> getTyleGioiTinh() {
        return tyleGioiTinh;
    }

    public void setTyleGioiTinh(List<GroupByValueDTO> tyleGioiTinh) {
        this.tyleGioiTinh = tyleGioiTinh;
    }

    public List<GroupByValueDTO> getTyleChucVu() {
        return tyleChucVu;
    }

    public void setTyleChucVu(List<GroupByValueDTO> tyleChucVu) {
        this.tyleChucVu = tyleChucVu;
    }

    public List<NhanVien> getRandomNhanVien() {
        return randomNhanVien;
    }

    public void setRandomNhanVien(List<NhanVien> randomNhanVien) {
        this.randomNhanVien = randomNhanVien;
    }

    public List<BenhNhan> getBenhNhanMoi() {
        return benhNhanMoi;
    }

    public void setBenhNhanMoi(List<BenhNhan> benhNhanMoi) {
        this.benhNhanMoi = benhNhanMoi;
    }
}

package com.yenmai.clinicrestfulapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yenmai.clinicrestfulapi.entity.ChiTietDonThuoc;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

public class PhieuKhamBenhResponseDTO {
    private  int maPhieuKham;

    private String trieuChung;

    private String chuanDoan;

    private String tenBacSi;

    private List<ChiTietDonThuoc> chiTietDonThuocList;

    private  String tenBenhNhan;

    private String diaChi;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    private Date ngayThem;

    public PhieuKhamBenhResponseDTO() {
    }

    public Date getNgayThem() {
        return ngayThem;
    }

    public void setNgayThem(Date ngayThem) {
        this.ngayThem = ngayThem;
    }

    public int getMaPhieuKham() {
        return maPhieuKham;
    }

    public void setMaPhieuKham(int maPhieuKham) {
        this.maPhieuKham = maPhieuKham;
    }

    public String getTrieuChung() {
        return trieuChung;
    }

    public void setTrieuChung(String trieuChung) {
        this.trieuChung = trieuChung;
    }

    public String getChuanDoan() {
        return chuanDoan;
    }

    public void setChuanDoan(String chuanDoan) {
        this.chuanDoan = chuanDoan;
    }

    public String getTenBacSi() {
        return tenBacSi;
    }

    public void setTenBacSi(String tenBacSi) {
        this.tenBacSi = tenBacSi;
    }

    public List<ChiTietDonThuoc> getChiTietDonThuocList() {
        return chiTietDonThuocList;
    }

    public void setChiTietDonThuocList(List<ChiTietDonThuoc> chiTietDonThuocList) {
        this.chiTietDonThuocList = chiTietDonThuocList;
    }

    public String getTenBenhNhan() {
        return tenBenhNhan;
    }

    public void setTenBenhNhan(String tenBenhNhan) {
        this.tenBenhNhan = tenBenhNhan;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}

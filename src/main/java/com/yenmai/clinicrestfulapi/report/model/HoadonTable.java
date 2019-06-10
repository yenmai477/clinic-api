package com.yenmai.clinicrestfulapi.report.model;

/**
 * @author YenMai
 */
public class HoadonTable {
    private Integer soTT;
    private String tenThuoc;
    private String donVi;
    private Integer soLuong;
    private String cachDung;
    private Integer thanhTien;

    public Integer getSoTT() {
        return soTT;
    }

    public void setSoTT(Integer soTT) {
        this.soTT = soTT;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Integer getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(Integer thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getCachDung() {
        return cachDung;
    }

    public void setCachDung(String cachDung) {
        this.cachDung = cachDung;
    }
}

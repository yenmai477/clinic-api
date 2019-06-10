package com.yenmai.clinicrestfulapi.model;

/**
 * @author YenMai
 */
public class ChiTietDonThuocDTO {

    private  int maChiTiet;

    private int maThuoc;

    private String cachDung;

    private int soLuong;

    private String thanhTien;

    private int maHoaDon;

    private int maPhieuKham;


    public ChiTietDonThuocDTO() {

    }

    public ChiTietDonThuocDTO(int maChiTiet, int maThuoc, String cachDung, int soLuong, int maPhieuKham, int maHoaDon) {
        this.maChiTiet = maChiTiet;
        this.maThuoc = maThuoc;
        this.cachDung = cachDung;
        this.soLuong = soLuong;
        this.maPhieuKham = maPhieuKham;
        this.maHoaDon = maHoaDon;
    }

    public int getMaChiTiet() {
        return maChiTiet;
    }

    public void setMaChiTiet(int maChiTiet) {
        this.maChiTiet = maChiTiet;
    }

    public int getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(int maThuoc) {
        this.maThuoc = maThuoc;
    }

    public String getCachDung() {
        return cachDung;
    }

    public void setCachDung(String cachDung) {
        this.cachDung = cachDung;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(String thanhTien) {
        this.thanhTien = thanhTien;
    }

    public int getMaPhieuKham() {
        return maPhieuKham;
    }

    public void setMaPhieuKham(int maPhieuKham) {
        this.maPhieuKham = maPhieuKham;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }
}

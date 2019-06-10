package com.yenmai.clinicrestfulapi.model;

import com.yenmai.clinicrestfulapi.entity.PhieuKhamBenh;

/**
 * @author YenMai
 */
public class PhieuKhamResponseDTO {
    private  int maPhieuKham;

    private String trieuChung;

    private String chuanDoan;

    private int maDangKi;

    private int maBacSi;

    private int maHoaDon;



    public PhieuKhamResponseDTO(int maPhieuKham, String trieuChung, String chuanDoan, int maDangKi, int maBacSi, int maHoaDon) {
        this.maPhieuKham = maPhieuKham;
        this.trieuChung = trieuChung;
        this.chuanDoan = chuanDoan;
        this.maDangKi = maDangKi;
        this.maBacSi = maBacSi;
        this.maHoaDon = maHoaDon;
    }

    public  PhieuKhamResponseDTO(PhieuKhamBenh tempPK, int maHoaDon) {
        this.maPhieuKham = tempPK.getMaPhieuKham();
        this.trieuChung = tempPK.getTrieuChung();
        this.chuanDoan = tempPK.getChuanDoan();
        this.maDangKi = tempPK.getDangKiKhamBenh().getMaDangKi();
        this.maBacSi = tempPK.getBacSi().getMaNhanVien();
        this.maHoaDon = maHoaDon;
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

    public int getMaDangKi() {
        return maDangKi;
    }

    public void setMaDangKi(int maDangKi) {
        this.maDangKi = maDangKi;
    }

    public int getMaBacSi() {
        return maBacSi;
    }

    public void setMaBacSi(int maBacSi) {
        this.maBacSi = maBacSi;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }
}

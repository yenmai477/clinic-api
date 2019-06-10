package com.yenmai.clinicrestfulapi.model;

public class PhieuKhamBenhDTO {

    private  int maPhieuKham;

    private String trieuChung;

    private String chuanDoan;

    private int maDangKi;

    private int maBacSi;



    public PhieuKhamBenhDTO(int maPhieuKham, String trieuChung, String chuanDoan,
                            int maDangKi, int maBacSi) {
        this.maPhieuKham = maPhieuKham;
        this.trieuChung = trieuChung;
        this.chuanDoan = chuanDoan;
        this.maDangKi = maDangKi;
        this.maBacSi = maBacSi;
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

}


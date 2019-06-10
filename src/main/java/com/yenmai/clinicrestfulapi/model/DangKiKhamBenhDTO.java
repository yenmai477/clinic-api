package com.yenmai.clinicrestfulapi.model;

public class DangKiKhamBenhDTO {

    private  int maDangKi;

    private String tinhTrang;

    private int maBenhNhan;

    public DangKiKhamBenhDTO(int maDangKi, String tinhTrang, int maBenhNhan) {
        this.maDangKi = maDangKi;
        this.tinhTrang = tinhTrang;
        this.maBenhNhan = maBenhNhan;
    }

    public int getMaDangKi() {
        return maDangKi;
    }

    public void setMaDangKi(int maDangKi) {
        this.maDangKi = maDangKi;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public int getMaBenhNhan() {
        return maBenhNhan;
    }

    public void setMaBenhNhan(int maBenhNhan) {
        this.maBenhNhan = maBenhNhan;
    }
}

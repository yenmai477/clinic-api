package com.yenmai.clinicrestfulapi.model;

/**
 * @author YenMai
 */
public class CardInfoDTO {
    private int soBenhNhan;
    private int soNhanVien;
    private int soDangKiKhamBenh;
    private int doanhThu;

    public CardInfoDTO() {
    }

    public CardInfoDTO(int soBenhNhan, int soNhanVien, int soDangKiKhamBenh, int doanhThu) {
        this.soBenhNhan = soBenhNhan;
        this.soNhanVien = soNhanVien;
        this.soDangKiKhamBenh = soDangKiKhamBenh;
        this.doanhThu = doanhThu;
    }

    public int getSoBenhNhan() {
        return soBenhNhan;
    }

    public void setSoBenhNhan(int soBenhNhan) {
        this.soBenhNhan = soBenhNhan;
    }

    public int getSoNhanVien() {
        return soNhanVien;
    }

    public void setSoNhanVien(int soNhanVien) {
        this.soNhanVien = soNhanVien;
    }

    public int getSoDangKiKhamBenh() {
        return soDangKiKhamBenh;
    }

    public void setSoDangKiKhamBenh(int soDangKiKhamBenh) {
        this.soDangKiKhamBenh = soDangKiKhamBenh;
    }

    public int getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(int doanhThu) {
        this.doanhThu = doanhThu;
    }
}

package com.yenmai.clinicrestfulapi.model;

/**
 * @author YenMai
 */
public class SignUpForm {



    private String tenTaiKhoan;


    private String matKhau;


    private String quyen;

    private int maNhanVien;

    public SignUpForm() {
    }

    public SignUpForm(String tenTaiKhoan, String matKhau, String quyen, int maNhanVien) {
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.quyen = quyen;
        this.maNhanVien = maNhanVien;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getQuyen() {
        return quyen;
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }
}

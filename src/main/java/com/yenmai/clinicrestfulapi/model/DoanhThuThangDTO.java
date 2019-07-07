package com.yenmai.clinicrestfulapi.model;

/**
 * @author YenMai
 */
public class DoanhThuThangDTO {

    private String thang;
    private int giaTri;
    private float tyLe;

    public DoanhThuThangDTO() {
    }

    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }

    public int getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(int giaTri) {
        this.giaTri = giaTri;
    }

    public float getTyLe() {
        return tyLe;
    }

    public void setTyLe(float tyLe) {
        this.tyLe = tyLe;
    }
}

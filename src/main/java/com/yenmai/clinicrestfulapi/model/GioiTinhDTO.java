package com.yenmai.clinicrestfulapi.model;

/**
 * @author YenMai
 */
public class GioiTinhDTO {
    private String quy;
    private String gioiTinh;
    private int giaTri;

    public GioiTinhDTO() {

    }

    public String getQuy() {
        return quy;
    }

    public void setQuy(String quy) {
        this.quy = quy;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(int giaTri) {
        this.giaTri = giaTri;
    }
}

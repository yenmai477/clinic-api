package com.yenmai.clinicrestfulapi.model;



public class HoaDonDTO {

    private  int maHoaDon;

    private String tinhTrang;




    public HoaDonDTO(int maHoaDon,  String tinhTrang) {
        this.maHoaDon = maHoaDon;

        this.tinhTrang = tinhTrang;

    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }



    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }


}
package com.yenmai.clinicrestfulapi.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name="THUOC")
@EntityListeners(AuditingEntityListener.class)
public class Thuoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MATHUOC")
    private  int maThuoc;

    @Column(name="TENTHUOC")
    private String tenThuoc;

    @Column(name="DONVI")
    private String donVi;

    @Column(name="GIA")
    private int Gia;

    @Column(name="SOLUONG")
    private int soLuong;


    @Column(name="NGAYSANXUAT")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date ngaySanXuat;

    @Column(name="HANSUDUNG")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date hanSuDung;

    @JsonIgnore
    @OneToMany(fetch=FetchType.LAZY, mappedBy="hoaDon", cascade = CascadeType.ALL)
    List<ChiTietDonThuoc> chiTietDonThuocs;



    public Thuoc() {
        super();
    }

    public Thuoc(String tenThuoc, String donVi, int gia, int soLuong,
                 Date ngaySanXuat, Date hanSuDung) {
        this.tenThuoc = tenThuoc;
        this.donVi = donVi;
        Gia = gia;
        this.soLuong = soLuong;
        this.ngaySanXuat = ngaySanXuat;
        this.hanSuDung = hanSuDung;
    }

    public int getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(int maThuoc) {
        this.maThuoc = maThuoc;
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

    public int getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }


    public Date getNgaySanXuat() {
        return ngaySanXuat;
    }

    public void setNgaySanXuat(Date ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
    }

    public Date getHanSuDung() {
        return hanSuDung;
    }

    public void setHanSuDung(Date hanSuDung) {
        this.hanSuDung = hanSuDung;
    }

    public List<ChiTietDonThuoc> getChiTietDonThuocs() {
        return chiTietDonThuocs;
    }

    public void setChiTietDonThuocs(List<ChiTietDonThuoc> chiTietDonThuocs) {
        this.chiTietDonThuocs = chiTietDonThuocs;
    }

    public void add(ChiTietDonThuoc tempChiTiet){
        if(this.chiTietDonThuocs == null){
            this.chiTietDonThuocs = new ArrayList<>();
        }

        //add dangkikhambenh for benhnhan
        chiTietDonThuocs.add(tempChiTiet);

        //set benhnhan to dangkikhambenh
        tempChiTiet.setThuoc(this);
    }
}

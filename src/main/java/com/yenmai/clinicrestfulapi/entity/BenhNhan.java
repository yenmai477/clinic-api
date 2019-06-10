package com.yenmai.clinicrestfulapi.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name="BENHNHAN")
@EntityListeners(AuditingEntityListener.class)
public class BenhNhan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MABENHNHAN")
    private  int maBenhNhan;

    @Column(name="TENBENHNHAN")
    private String tenBenhNhan;

    @Column(name="GIOITINH")
    private String gioiTinh;

    @Column(name="NGAYSINH")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date ngaySinh;

    @Column(name="DIACHI")
    private String diaChi;

    @Column(name = "NGHENGHIEP")
    private String ngheNghiep;

    @Column(name="SODIENTHOAI")
    private String soDienThoai;

    @Column(name="NGAYTHEM")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @CreatedDate
    @Temporal(TemporalType.DATE)
    private Date ngayThem;

    @JsonIgnore
    @OneToMany(fetch=FetchType.LAZY, mappedBy="benhNhan", cascade = CascadeType.ALL)
    List<DangKiKhamBenh> dangKiKhamBenhs;

    @JsonIgnore
    @OneToMany(fetch=FetchType.LAZY, mappedBy="benhNhan", cascade = CascadeType.ALL)
    List<HoaDon> hoaDons;

    public BenhNhan() {
        super();
    }

    public BenhNhan(String tenBenhNhan, String gioiTinh, Date ngaySinh, String diaChi, String ngheNghiep, String soDienThoai, Date ngayThem) {
        this.tenBenhNhan = tenBenhNhan;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.ngheNghiep = ngheNghiep;
        this.soDienThoai = soDienThoai;
        this.ngayThem = ngayThem;
    }



    public int getMaBenhNhan() {
        return maBenhNhan;
    }

    public void setMaBenhNhan(int maBenhNhan) {
        this.maBenhNhan = maBenhNhan;
    }

    public String getTenBenhNhan() {
        return tenBenhNhan;
    }

    public void setTenBenhNhan(String tenBenhNhan) {
        this.tenBenhNhan = tenBenhNhan;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {

        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNgheNghiep() {
        return ngheNghiep;
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public Date getNgayThem() {
        return ngayThem;
    }

    public void setNgayThem(Date ngayThem) {
        this.ngayThem = ngayThem;
    }

    public List<DangKiKhamBenh> getDangKiKhamBenhs() {
        return dangKiKhamBenhs;
    }

    public void setDangKiKhamBenhs(List<DangKiKhamBenh> dangKiKhamBenhs) {
        this.dangKiKhamBenhs = dangKiKhamBenhs;
    }

    public List<HoaDon> getHoaDons() {
        return hoaDons;
    }

    public void setHoaDons(List<HoaDon> hoaDons) {
        this.hoaDons = hoaDons;
    }

    public void add(DangKiKhamBenh tempDangKiKhamBenh){
        if(this.dangKiKhamBenhs == null){
            this.dangKiKhamBenhs = new ArrayList<>();
        }

        //add dangkikhambenh for benhnhan
        dangKiKhamBenhs.add(tempDangKiKhamBenh);

        //set benhnhan to dangkikhambenh
        tempDangKiKhamBenh.setBenhNhan(this);
    }

    public void add(HoaDon tempHoaDon){
        if(this.hoaDons == null){
            this.hoaDons = new ArrayList<>();
        }

        //add dangkikhambenh for benhnhan
        hoaDons.add(tempHoaDon);

        //set benhnhan to dangkikhambenh
        tempHoaDon.setBenhNhan(this);
    }

    @Override
    public String toString() {
        return "BenhNhan{" +
                "maBenhNhan=" + maBenhNhan +
                ", tenBenhNhan='" + tenBenhNhan + '\'' +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", ngaySinh=" + ngaySinh +
                ", diaChi='" + diaChi + '\'' +
                ", ngheNghiep='" + ngheNghiep + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", ngayThem=" + ngayThem +
                '}';
    }
}

package com.yenmai.clinicrestfulapi.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="TAIKHOAN")
public class TaiKhoan {

    @Id
    @Column(name="TENTAIKHOAN")
    private  String tenTaiKhoan;

    @Column(name="MATKHAU")
    private String matKhau;

    @Column(name="QUYEN")
    private String quyen;

    @OneToOne(cascade= CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="MANHANVIEN")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="maDangKi")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty("maNhanVien")
    private NhanVien nhanVien;

    public TaiKhoan() {
    }

    public TaiKhoan(String tenTaiKhoan, String matKhau, String quyen) {
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.quyen = quyen;
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

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    @Transient
    public List<GrantedAuthority> getAuthorities(){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add( new SimpleGrantedAuthority(getQuyen()));
        return authorities;

    }
}

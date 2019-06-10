package com.yenmai.clinicrestfulapi.entity;


import com.fasterxml.jackson.annotation.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity(name="CHITIETDONTHUOC")
@EntityListeners(AuditingEntityListener.class)
public class ChiTietDonThuoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MACHITIET")
    private  int maChiTiet;

    @Column(name="CACHDUNG")
    private String cachDung;

    @Column(name="SOLUONG")
    private int soLuong;

    @Column(name="THANHTIEN")
    private int thanhTien;

    @Column(name="NGAYTHEM")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @CreatedDate
    @Temporal(TemporalType.DATE)
    private Date ngayThem;

    @ManyToOne(cascade ={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="MATHUOC")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="maThuoc")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty("maThuoc")
    private Thuoc thuoc;


    @ManyToOne(cascade ={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="MAHOADON")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="maHoaDon")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty("maHoaDon")
    private HoaDon hoaDon;

    @ManyToOne(cascade ={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="MAPHIEUKHAMBENH")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="maPhieuKham")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty("maPhieuKham")
    private PhieuKhamBenh phieuKhamBenh;




    public ChiTietDonThuoc() {
        super();
    }

    public ChiTietDonThuoc(String cachDung, int soLuong, int thanhTien, Date ngayThem) {
        this.cachDung = cachDung;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
        this.ngayThem = ngayThem;
    }

    public int getMaChiTiet() {
        return maChiTiet;
    }

    public void setMaChiTiet(int maChiTiet) {
        this.maChiTiet = maChiTiet;
    }

    public String getCachDung() {
        return cachDung;
    }

    public void setCachDung(String cachDung) {
        this.cachDung = cachDung;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }

    public Date getNgayThem() {
        return ngayThem;
    }

    public void setNgayThem(Date ngayThem) {
        this.ngayThem = ngayThem;
    }

    public Thuoc getThuoc() {
        return thuoc;
    }

    public void setThuoc(Thuoc thuoc) {
        this.thuoc = thuoc;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public PhieuKhamBenh getPhieuKhamBenh() {
        return phieuKhamBenh;
    }

    public void setPhieuKhamBenh(PhieuKhamBenh phieuKhamBenh) {
        this.phieuKhamBenh = phieuKhamBenh;
    }
}

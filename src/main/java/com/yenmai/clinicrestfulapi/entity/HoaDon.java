package com.yenmai.clinicrestfulapi.entity;


import com.fasterxml.jackson.annotation.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name="HOADON")
@EntityListeners(AuditingEntityListener.class)
public class HoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MAHOADON")
    private  int maHoaDon;

    @Column(name="THANHTIEN")
    private int thanhTien;

    @Column(name="TINHTRANG")
    private String tinhTrang;

    @Column(name="NGAYTHEM")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @CreatedDate
    @Temporal(TemporalType.DATE)
    private Date ngayThem;


    @ManyToOne(cascade ={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="MABENHNHAN")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="maBenhNhan")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty("maBenhNhan")
    private BenhNhan benhNhan;

    @JsonIgnore
    @OneToMany(fetch=FetchType.LAZY, mappedBy="hoaDon", cascade = CascadeType.ALL)
    List<ChiTietDonThuoc> chiTietDonThuocs;

    public HoaDon() {
        super();
    }

    public HoaDon(int thanhTien, String tinhTrang, Date ngayThem) {
        this.thanhTien = thanhTien;
        this.tinhTrang = tinhTrang;
        this.ngayThem = ngayThem;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public Date getNgayThem() {
        return ngayThem;
    }

    public void setNgayThem(Date ngayThem) {
        this.ngayThem = ngayThem;
    }

    public BenhNhan getBenhNhan() {
        return benhNhan;
    }

    public void setBenhNhan(BenhNhan benhNhan) {
        this.benhNhan = benhNhan;
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
        tempChiTiet.setHoaDon(this);
    }
}

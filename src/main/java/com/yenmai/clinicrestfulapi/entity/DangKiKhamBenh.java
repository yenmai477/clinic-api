package com.yenmai.clinicrestfulapi.entity;

import com.fasterxml.jackson.annotation.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity(name="DANGKIKHAMBENH")
@EntityListeners(AuditingEntityListener.class)
public class DangKiKhamBenh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MADANGKI")
    private  int maDangKi;

    @Column(name="NGAYDANGKI")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @CreatedDate
    private Date ngayDangKi;

    @Column(name="TINHTRANG")
    private String tinhTrang;

    @ManyToOne(cascade ={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="MABENHNHAN")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="maBenhNhan")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty("maBenhNhan")
    private BenhNhan benhNhan;


    @OneToOne(mappedBy="dangKiKhamBenh", cascade=CascadeType.ALL)
    private PhieuKhamBenh phieuKhamBenh;



    public DangKiKhamBenh() {
    }

    public DangKiKhamBenh(Date ngayDangKi, String tinhTrang) {
        this.ngayDangKi = ngayDangKi;
        this.tinhTrang = tinhTrang;
    }

    public int getMaDangKi() {
        return maDangKi;
    }

    public void setMaDangKi(int maDangKi) {
        this.maDangKi = maDangKi;
    }

    public Date getNgayDangKi() {
        return ngayDangKi;
    }

    public void setNgayDangKi(Date ngayDangKi) {
        this.ngayDangKi = ngayDangKi;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    @JsonIgnore
    public BenhNhan getBenhNhan() {
        return benhNhan;
    }

    @JsonIgnore
    public void setBenhNhan(BenhNhan benhNhan) {
        this.benhNhan = benhNhan;
    }

    @JsonIgnore
    public PhieuKhamBenh getPhieuKhamBenh() {
        return phieuKhamBenh;
    }

    @JsonIgnore
    public void setPhieuKhamBenh(PhieuKhamBenh phieuKhamBenh) {
        this.phieuKhamBenh = phieuKhamBenh;
    }

    @Override
    public String toString() {
        return "DangKiKhamBenh{" +
                "maDangKi=" + maDangKi +
                ", ngayDangKi=" + ngayDangKi +
                ", tinhTrang='" + tinhTrang + '\'' +
                ", benhNhan=" + benhNhan +
                '}';
    }
}

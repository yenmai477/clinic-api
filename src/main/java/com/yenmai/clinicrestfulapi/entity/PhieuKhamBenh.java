package com.yenmai.clinicrestfulapi.entity;


import com.fasterxml.jackson.annotation.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name="PHIEUKHAMBENH")
@EntityListeners(AuditingEntityListener.class)
public class PhieuKhamBenh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MAPHIEUKHAMBENH")
    private  int maPhieuKham;

    @Column(name="TRIEUCHUNG")
    private String trieuChung;

    @Column(name="CHUANDOAN")
    private String chuanDoan;

    @Column(name="NGAYTHEM")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @CreatedDate
    @Temporal(TemporalType.DATE)
    private Date ngayThem;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="MADANGKI")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="maDangKi")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty("maDangKi")
    private DangKiKhamBenh dangKiKhamBenh;

    @ManyToOne(cascade ={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="MABACSI")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="maNhanVien")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty("maBacSi")
    private NhanVien bacSi;

    //@JsonIgnore
    @OneToMany(fetch=FetchType.LAZY, mappedBy="phieuKhamBenh", cascade = CascadeType.ALL)
    List<ChiTietDonThuoc> chiTietDonThuocs;

    public PhieuKhamBenh() {
        super();
    }

    public PhieuKhamBenh(String trieuChung, String chuanDoan, Date ngayThem) {
        this.trieuChung = trieuChung;
        this.chuanDoan = chuanDoan;
        this.ngayThem = ngayThem;
    }

    public void add(ChiTietDonThuoc tempChiTiet){
        if(this.chiTietDonThuocs == null){
            this.chiTietDonThuocs = new ArrayList<>();
        }

        //add dangkikhambenh for benhnhan
        chiTietDonThuocs.add(tempChiTiet);

        //set benhnhan to dangkikhambenh
        tempChiTiet.setPhieuKhamBenh(this);
    }

    public void remove(ChiTietDonThuoc tempChiTiet){


        chiTietDonThuocs.remove(tempChiTiet);

        //set benhnhan to dangkikhambenh
        tempChiTiet.setPhieuKhamBenh(null);
    }

    public int getMaPhieuKham() {
        return maPhieuKham;
    }

    public void setMaPhieuKham(int maPhieuKham) {
        this.maPhieuKham = maPhieuKham;
    }

    public String getTrieuChung() {
        return trieuChung;
    }

    public void setTrieuChung(String trieuChung) {
        this.trieuChung = trieuChung;
    }

    public String getChuanDoan() {
        return chuanDoan;
    }

    public void setChuanDoan(String chuanDoan) {
        this.chuanDoan = chuanDoan;
    }

    public Date getNgayThem() {
        return ngayThem;
    }

    public void setNgayThem(Date ngayThem) {
        this.ngayThem = ngayThem;
    }


    public DangKiKhamBenh getDangKiKhamBenh() {
        return dangKiKhamBenh;
    }

    public void setDangKiKhamBenh(DangKiKhamBenh dangKiKhamBenh) {
        this.dangKiKhamBenh = dangKiKhamBenh;
    }

    public NhanVien getBacSi() {
        return bacSi;
    }

    public void setBacSi(NhanVien bacSi) {
        this.bacSi = bacSi;
    }

    public List<ChiTietDonThuoc> getChiTietDonThuocs() {
        return chiTietDonThuocs;
    }

    public void setChiTietDonThuocs(List<ChiTietDonThuoc> chiTietDonThuocs) {
        this.chiTietDonThuocs = chiTietDonThuocs;
    }

    @Override
    public String toString() {
        return "PhieuKhamBenh{" +
                "maPhieuKham=" + maPhieuKham +
                ", trieuChung='" + trieuChung + '\'' +
                ", chuanDoan='" + chuanDoan + '\'' +
                ", ngayThem=" + ngayThem +
                ", dangKiKhamBenh=" + dangKiKhamBenh +
                ", bacSi=" + bacSi +
                '}';
    }
}

package com.yenmai.clinicrestfulapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="NHANVIEN")
public class NhanVien {

	//define field
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="MANHANVIEN")
	private int maNhanVien;
	
	@Column(name="TENNHANVIEN")
	private String tenNhanVien;
	
	@Column(name="GIOITINH")
	private String gioiTinh;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name="NGAYSINH")
	@Temporal(TemporalType.DATE)
	private Date ngaySinh;
	
	@Column(name="DIACHI")
	private String diaChi;
	
	@Column(name="CHUCVU")
	private String chucVu;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name="NGAYVAO")
	@Temporal(TemporalType.DATE)
	private Date ngayVao;


	
	//define constructor
	public NhanVien() {
		super();
	}


	public NhanVien(String tenNhanVien, String gioiTinh, Date ngaySinh, String diaChi, String chucVu, Date ngayVao) {
		super();
		this.tenNhanVien = tenNhanVien;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.chucVu = chucVu;
		this.ngayVao = ngayVao;
	}

	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy="bacSi", cascade = CascadeType.ALL)
	List<PhieuKhamBenh> phieuKhamBenhs;


		
	
	//define setter/getter
	
	public int getMaNhanVien() {
		return maNhanVien;
	}


	public void setMaNhanVien(int maNhanVien) {
		this.maNhanVien = maNhanVien;
	}


	public String getTenNhanVien() {
		return tenNhanVien;
	}


	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
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


	public String getChucVu() {
		return chucVu;
	}


	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}


	public Date getNgayVao() {
		return ngayVao;
	}


	public void setNgayVao(Date ngayVao) {
		this.ngayVao = ngayVao;
	}


	public List<PhieuKhamBenh> getPhieuKhamBenhs() {
		return phieuKhamBenhs;
	}

	public void setPhieuKhamBenhs(List<PhieuKhamBenh> phieuKhamBenhs) {
		this.phieuKhamBenhs = phieuKhamBenhs;
	}

	//define toString for test
	@Override
	public String toString() {
		return "NhanVien [maNhanVien= " + maNhanVien + ", tenNhanVien= " + tenNhanVien + ", gioiTinh= " + gioiTinh
				+ ", ngaySinh= " + ngaySinh + ", diaChi= " + diaChi + ", chucVu= " + chucVu + ", ngayVao= " + ngayVao + "]";
	}
	
	
}

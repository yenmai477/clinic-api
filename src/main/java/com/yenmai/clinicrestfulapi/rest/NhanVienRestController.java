package com.yenmai.clinicrestfulapi.rest;

import com.yenmai.clinicrestfulapi.entity.NhanVien;
import com.yenmai.clinicrestfulapi.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nhanvien")
public class NhanVienRestController {
	private NhanVienService nhanVienService;
	
	@Autowired
	public NhanVienRestController(NhanVienService nhanVienService) {
		this.nhanVienService = nhanVienService;
	}

	// expose "/nhanvien" and return list of nhanvien
	@GetMapping
	public List<NhanVien> findAll(@RequestParam(value = "name", required = false) String tenNhanVien){
		if (tenNhanVien!=null) {
			return nhanVienService.findByTenNhanVienLike(tenNhanVien);
		};
		return nhanVienService.findAll();
	}


	// add mapping for GET /nhanvien/{maNhanVien}

	@GetMapping("/{maNhanVien}")
	public NhanVien getNhanVien(@PathVariable int maNhanVien) {

		NhanVien theNhanVien = nhanVienService.findById(maNhanVien);

		if (theNhanVien == null) {
			throw new RuntimeException("Employee id not found - " + maNhanVien);
		}

		return theNhanVien;
	}



	// add mapping for POST /nhanvien - add new nhan vien

	@PostMapping
	public NhanVien addNhanVien(@RequestBody NhanVien theNhanVien) {

		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update

		theNhanVien.setMaNhanVien(0);

		nhanVienService.save(theNhanVien);

		return theNhanVien;
	}

	// add mapping for PUT /employees - update existing employee

	@PutMapping
	public NhanVien updateNhanVien(@RequestBody NhanVien theNhanVien) {

		nhanVienService.save(theNhanVien);

		return theNhanVien;
	}

	// add mapping for DELETE /nhanvien/{maNhanVien} - delete employee

	@DeleteMapping("/{maNhanVien}")
	public ResponseEntity<NhanVien> deleteNhanVien(@PathVariable int maNhanVien) {

		NhanVien tempEmployee = nhanVienService.findById(maNhanVien);

		// throw exception if null

		if (tempEmployee == null) {
			throw new RuntimeException("Employee id not found - " + maNhanVien);
		}

		nhanVienService.deleteById(maNhanVien);

		//return "Deleted employee id - " + maNhanVien;
		return new ResponseEntity<NhanVien>(HttpStatus.NO_CONTENT);
	}

	

}

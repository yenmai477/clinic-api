package com.yenmai.clinicrestfulapi.service.Implements;

import com.yenmai.clinicrestfulapi.dao.NhanVienRepository;
import com.yenmai.clinicrestfulapi.entity.NhanVien;
import com.yenmai.clinicrestfulapi.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class NhanVienServiceImpl implements NhanVienService {
	private NhanVienRepository nhanVienRepository;

	@Autowired
	public NhanVienServiceImpl(NhanVienRepository nhanVienRepository) {

		this.nhanVienRepository = nhanVienRepository;
	}

	@Override
	@Transactional
	public List<NhanVien> findAll() {

		return nhanVienRepository.findAll();
	}

	@Override
	@Transactional
	public List<NhanVien> findByTenNhanVienLike(String tenNhanVien) {
		List<NhanVien> result = nhanVienRepository.findByTenNhanVienLike(tenNhanVien);

		if (result.size() == 0) {
			// we didn't find the employee
			throw new RuntimeException("Không tìm thấy nhân viên có tên là  " + tenNhanVien);
		}

		return result;
	}

	@Override
	@Transactional
	public NhanVien findById(int maNhanVien) {
		Optional<NhanVien> result = nhanVienRepository.findById(maNhanVien);

		NhanVien theNhanVien = null;

		if (result.isPresent()) {
			theNhanVien = result.get();
		} else {
			// we didn't find the employee
			throw new RuntimeException("Did not find employee id - " + maNhanVien);
		}

		return theNhanVien;
	}

	@Override
	@Transactional
	public void save(NhanVien nhanVien) {
		nhanVienRepository.save(nhanVien);
	}

	@Override
	@Transactional
	public void deleteById(int maNhanVien) {
		nhanVienRepository.deleteById(maNhanVien);
	}

}

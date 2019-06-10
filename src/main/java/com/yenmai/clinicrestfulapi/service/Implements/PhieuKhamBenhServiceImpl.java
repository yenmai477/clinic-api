package com.yenmai.clinicrestfulapi.service.Implements;

import com.yenmai.clinicrestfulapi.dao.DangKiKhamBenhRespository;
import com.yenmai.clinicrestfulapi.dao.NhanVienRepository;
import com.yenmai.clinicrestfulapi.dao.PhieuKhamBenhRespository;
import com.yenmai.clinicrestfulapi.entity.DangKiKhamBenh;
import com.yenmai.clinicrestfulapi.entity.NhanVien;
import com.yenmai.clinicrestfulapi.entity.PhieuKhamBenh;
import com.yenmai.clinicrestfulapi.model.PhieuKhamBenhDTO;
import com.yenmai.clinicrestfulapi.service.PhieuKhamBenhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PhieuKhamBenhServiceImpl implements PhieuKhamBenhService {

    @Autowired
    private PhieuKhamBenhRespository phieuKhamBenhRespository;

    @Autowired
    private DangKiKhamBenhRespository dangKiKhamBenhRespository;

    @Autowired
    private NhanVienRepository nhanVienRepository;


    @Override
    @Transactional
    public List<PhieuKhamBenh> findByBenhNhan(int maBenhNhan) {
        List<PhieuKhamBenh> result = phieuKhamBenhRespository.findByBenhNhan(maBenhNhan);

        if (result.size() == 0) {
            // we didn't find the employee
            throw new RuntimeException("Không tìm thấy phiếu khám bệnh của bệnh nhân" + maBenhNhan);
        }

        return result;
    }

    @Override
    @Transactional
    public List<PhieuKhamBenh> findByBacSi(int maBacSi) {
        List<PhieuKhamBenh> result = phieuKhamBenhRespository.findByBacSi(maBacSi);

        if (result.size() == 0) {
            // we didn't find the employee
            throw new RuntimeException("Không tìm thấy phiếu khám bệnh do bác sĩ có mã " + maBacSi + " lập");
        }

        return result;
    }

    @Override
    @Transactional
    public PhieuKhamBenh findByDangKiKhamBenh(int maDangKi) {
        PhieuKhamBenh result = phieuKhamBenhRespository.findByDangKiKhamBenh(maDangKi);

        if(result == null){
            throw new RuntimeException("Không tìm thấy phiếu khám có mã - " + maDangKi);
        }

        return result;
    }

    @Override
    @Transactional
    public PhieuKhamBenh findById(int maPhieuKham) {
        Optional<PhieuKhamBenh> result = phieuKhamBenhRespository.findById(maPhieuKham);

        PhieuKhamBenh thePhieuKham = null;

        if (result.isPresent()) {
            thePhieuKham = result.get();
        } else {
            // we didn't find the employee
            throw new RuntimeException("Không tìm thấy phiếu khám bệnh có mã - " + maPhieuKham);
        }

        return thePhieuKham;
    }

    @Override
    @Transactional
    public void save(PhieuKhamBenh phieuKhamBenh) {
        phieuKhamBenhRespository.save(phieuKhamBenh);
    }

    @Override
    @Transactional
    public void deleteById(int maPhieuKham) {
        phieuKhamBenhRespository.deleteById(maPhieuKham);

    }

    @Override
    @Transactional
    public  PhieuKhamBenh createorUpdatePhieuKhamBenh(PhieuKhamBenh phieuKhamBenh, PhieuKhamBenhDTO phieuKhamBenhDTO){

        phieuKhamBenh.setTrieuChung(phieuKhamBenhDTO.getTrieuChung());

        phieuKhamBenh.setChuanDoan(phieuKhamBenhDTO.getChuanDoan());

        NhanVien tempBacSi =null;
        tempBacSi = nhanVienRepository.findById(phieuKhamBenhDTO.getMaBacSi()).get();
        phieuKhamBenh.setBacSi(tempBacSi);

        DangKiKhamBenh tempDangKi = null;
        tempDangKi = dangKiKhamBenhRespository.findById(phieuKhamBenhDTO.getMaDangKi()).get();
        phieuKhamBenh.setDangKiKhamBenh(tempDangKi);
        //System.out.println(phieuKhamBenh);

        return  phieuKhamBenh;
    }
}

///Chưa viết rest

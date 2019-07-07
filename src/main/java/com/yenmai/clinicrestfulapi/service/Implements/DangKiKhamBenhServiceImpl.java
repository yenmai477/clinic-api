package com.yenmai.clinicrestfulapi.service.Implements;

import com.yenmai.clinicrestfulapi.dao.BenhNhanRespository;
import com.yenmai.clinicrestfulapi.dao.DangKiKhamBenhRespository;
import com.yenmai.clinicrestfulapi.entity.BenhNhan;
import com.yenmai.clinicrestfulapi.entity.DangKiKhamBenh;
import com.yenmai.clinicrestfulapi.model.DangKiKhamBenhDTO;
import com.yenmai.clinicrestfulapi.service.DangKiKhamBenhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DangKiKhamBenhServiceImpl implements DangKiKhamBenhService {

    @Autowired
    private DangKiKhamBenhRespository dangKiKhamBenhRespository;

    @Autowired
    private BenhNhanRespository benhNhanRespository;

    @Override
    @Transactional
    public List<DangKiKhamBenh> findAll() {

        //return dangKiKhamBenhRespository.findAll(new Sort(Sort.Direction.DESC, "ngayDangKi")
                //.and(new Sort(Sort.Direction.DESC, "tinhTrang")));

        return dangKiKhamBenhRespository.findAll();
    }

    @Override
    @Transactional
    public DangKiKhamBenh findById(int maDangKi) {
        Optional<DangKiKhamBenh> result = dangKiKhamBenhRespository.findById(maDangKi);

        DangKiKhamBenh theDangKi = null;

        if (result.isPresent()) {
            theDangKi = result.get();
        } else {
            // we didn't find the employee
            throw new RuntimeException("Không tìm thấy đăng kí khám bệnh của bệnh nhân - " + maDangKi);
        }

        return theDangKi;
    }

    @Override
    @Transactional
    public List<DangKiKhamBenh> findByNgayDangKi(String ngayDangKi) {
        List<DangKiKhamBenh> result = dangKiKhamBenhRespository.findByNgayDangKi(ngayDangKi);

        if (result.size() == 0) {
            // we didn't find the employee
            throw new RuntimeException("Không tìm thấy đăng ký nào ở ngày " + ngayDangKi);
        }

        return result;
    }

    @Override
    @Transactional
    public void save(DangKiKhamBenh dangKiKhamBenh) {
        dangKiKhamBenhRespository.save(dangKiKhamBenh);
    }

    @Override
    @Transactional
    public List<DangKiKhamBenh> findDangKiKhamBenhByTinhTrang(String tinhTrang) {
        return dangKiKhamBenhRespository.findDangKiKhamBenhByTinhTrang(tinhTrang);
    }

    @Override
    @Transactional
    public void deleteById(int maDangKi) {
        dangKiKhamBenhRespository.deleteById(maDangKi);
    }
    @Override
    @Transactional
    public List<DangKiKhamBenh> findDangKiKhamBenhByBenhNhan(int maBenhNhan){
        return dangKiKhamBenhRespository.findDangKiKhamBenhByBenhNhan(maBenhNhan);
    }

    @Override
    @Transactional
    public DangKiKhamBenh createDangKiKhamBenh(DangKiKhamBenhDTO theDangKiDTO){
        DangKiKhamBenh tempDangKi = new DangKiKhamBenh();
        tempDangKi.setMaDangKi(theDangKiDTO.getMaDangKi());
        tempDangKi.setTinhTrang(theDangKiDTO.getTinhTrang());
        BenhNhan tempBenhNhan = benhNhanRespository.findById(theDangKiDTO.getMaBenhNhan()).get();
        tempDangKi.setBenhNhan(tempBenhNhan);
        return tempDangKi;
    }


}

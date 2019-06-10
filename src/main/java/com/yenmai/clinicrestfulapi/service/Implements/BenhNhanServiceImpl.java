package com.yenmai.clinicrestfulapi.service.Implements;

import com.yenmai.clinicrestfulapi.dao.BenhNhanRespository;
import com.yenmai.clinicrestfulapi.entity.BenhNhan;
import com.yenmai.clinicrestfulapi.service.BenhNhanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class BenhNhanServiceImpl implements BenhNhanService {
    private BenhNhanRespository benhNhanRespository;

    @Autowired
    public BenhNhanServiceImpl(BenhNhanRespository benhNhanRespository) {

        this.benhNhanRespository = benhNhanRespository;
    }

    @Override
    @Transactional
    public List<BenhNhan> findAll() {

        return benhNhanRespository.findAll();
    }

    @Override
    @Transactional
    public List<BenhNhan> findByTenBenhNhanLike(String tenBenhNhan) {
        List<BenhNhan> result = benhNhanRespository.findByTenBenhNhanLike(tenBenhNhan);

        if (result.size() == 0) {
            // we didn't find the employee
            throw new RuntimeException("Không tìm thấy bệnh nhân có tên là  " + tenBenhNhan);
        }

        return result;
    }


    @Override
    public List<Map<String, Object>> report() {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (BenhNhan benhNhan : benhNhanRespository.findAll()) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("tenBenhNhan", benhNhan.getTenBenhNhan());
            item.put("gioiTinh", benhNhan.getGioiTinh());
            item.put("ngaySinh", benhNhan.getNgaySinh());
            item.put("diaChi", benhNhan.getNgheNghiep());
            item.put("soDienThoai",benhNhan.getSoDienThoai());
            item.put("ngayThem", benhNhan.getNgayThem());
            result.add(item);
        }
        return result;

    }

    @Override
    @Transactional
    public BenhNhan findById(int maBenhNhan) {
        Optional<BenhNhan> result = benhNhanRespository.findById(maBenhNhan);

        BenhNhan theBenhNhan = null;

        if (result.isPresent()) {
            theBenhNhan = result.get();
        } else {
            // we didn't find the employee
            throw new RuntimeException("Không tìm thấy bệnh nhân có mã - " + maBenhNhan);
        }

        return theBenhNhan;
    }

    @Override
    @Transactional
    public void save(BenhNhan benhNhan) {
        benhNhanRespository.save(benhNhan);
    }

    @Override
    @Transactional
    public void deleteById(int maBenhNhan) {
        benhNhanRespository.deleteById(maBenhNhan);
    }

}

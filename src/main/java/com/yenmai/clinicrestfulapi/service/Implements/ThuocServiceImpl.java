package com.yenmai.clinicrestfulapi.service.Implements;

import com.yenmai.clinicrestfulapi.dao.ThuocRespository;
import com.yenmai.clinicrestfulapi.entity.Thuoc;
import com.yenmai.clinicrestfulapi.service.ThuocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ThuocServiceImpl implements ThuocService {
    private ThuocRespository thuocRespository;

    @Autowired
    public ThuocServiceImpl(ThuocRespository thuocRespository) {

        this.thuocRespository = thuocRespository;
    }

    @Override
    @Transactional
    public List<Thuoc> findAll() {

        return thuocRespository.findAll();
    }


    @Override
    @Transactional
    public Thuoc findById(int maThuoc) {
        Optional<Thuoc> result = thuocRespository.findById(maThuoc);

        Thuoc theThuoc = null;

        if (result.isPresent()) {
            theThuoc = result.get();
        } else {

            throw new RuntimeException("Không tìm thấy thuốc có mã - " + maThuoc);
        }

        return theThuoc;
    }

    @Override
    @Transactional
    public void save(Thuoc thuoc) {
        thuocRespository.save(thuoc);
    }

    @Override
    @Transactional
    public void deleteById(int maThuoc) {
        thuocRespository.deleteById(maThuoc);
    }

}

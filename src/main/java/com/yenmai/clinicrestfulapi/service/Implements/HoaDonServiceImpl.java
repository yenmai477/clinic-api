package com.yenmai.clinicrestfulapi.service.Implements;

import com.yenmai.clinicrestfulapi.dao.HoaDonRespository;
import com.yenmai.clinicrestfulapi.entity.HoaDon;
import com.yenmai.clinicrestfulapi.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class HoaDonServiceImpl implements HoaDonService {
    private HoaDonRespository hoaDonRespository;

    @Autowired
    public HoaDonServiceImpl(HoaDonRespository hoaDonRespository) {

        this.hoaDonRespository = hoaDonRespository;
    }

    @Override
    @Transactional
    public List<HoaDon> findAll() {

        return hoaDonRespository.findAll();
    }


    @Override
    @Transactional
    public HoaDon findById(int maHoaDon) {
        Optional<HoaDon> result = hoaDonRespository.findById(maHoaDon);

        HoaDon theHoaDon = null;

        if (result.isPresent()) {
            theHoaDon = result.get();
        } else {

            throw new RuntimeException("Không tìm thấy hóa đơn có mã - " + maHoaDon);
        }

        return theHoaDon;
    }

    @Override
    @Transactional
    public void save(HoaDon hoaDon) {
        hoaDonRespository.save(hoaDon);
    }

    @Override
    @Transactional
    public void deleteById(int maHoaDon) {
        hoaDonRespository.deleteById(maHoaDon);
    }

}

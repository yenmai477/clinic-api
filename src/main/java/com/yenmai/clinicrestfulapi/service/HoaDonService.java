package com.yenmai.clinicrestfulapi.service;

import com.yenmai.clinicrestfulapi.entity.HoaDon;

import java.util.List;

public interface HoaDonService {

    public List<HoaDon> findAll();

    public HoaDon findById(int maHoaDon);

    public void save(HoaDon hoaDon);

    public void deleteById(int maHoaDon);

}




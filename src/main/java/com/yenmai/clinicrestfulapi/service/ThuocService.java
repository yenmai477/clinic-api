package com.yenmai.clinicrestfulapi.service;

import com.yenmai.clinicrestfulapi.entity.Thuoc;

import java.util.List;

public interface ThuocService {

    public List<Thuoc> findAll();

    public Thuoc findById(int maThuoc);

    public void save(Thuoc thuoc);

    public void deleteById(int maThuoc);



}

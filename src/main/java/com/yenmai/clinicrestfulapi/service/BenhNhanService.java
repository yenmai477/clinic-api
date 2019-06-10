package com.yenmai.clinicrestfulapi.service;

import com.yenmai.clinicrestfulapi.entity.BenhNhan;

import java.util.List;
import java.util.Map;

public interface BenhNhanService {

    public List<BenhNhan> findAll();

    public BenhNhan findById(int maBenhNhan);

    public void save(BenhNhan benhNhan);

    public void deleteById(int maBenhNhan);

    public List<BenhNhan> findByTenBenhNhanLike(String tenBenhNhan);

    public List<Map<String, Object>> report();

}

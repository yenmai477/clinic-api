package com.yenmai.clinicrestfulapi.service;

import com.yenmai.clinicrestfulapi.model.CardInfoDTO;
import com.yenmai.clinicrestfulapi.model.GroupByValueDTO;

import java.util.List;

public interface TongQuanService {

    public CardInfoDTO getAllCardInfo();

    public List<GroupByValueDTO> getDoanhThuTheoThang();

    public List<GroupByValueDTO> getBenhNhanTheoGioiTinh();

    public   List<GroupByValueDTO> getNhanVienTheoChucVu();
}

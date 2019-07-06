package com.yenmai.clinicrestfulapi.service.Implements;

import com.yenmai.clinicrestfulapi.dao.BenhNhanRespository;
import com.yenmai.clinicrestfulapi.dao.DangKiKhamBenhRespository;
import com.yenmai.clinicrestfulapi.dao.HoaDonRespository;
import com.yenmai.clinicrestfulapi.dao.NhanVienRepository;
import com.yenmai.clinicrestfulapi.entity.BenhNhan;
import com.yenmai.clinicrestfulapi.entity.HoaDon;
import com.yenmai.clinicrestfulapi.entity.NhanVien;
import com.yenmai.clinicrestfulapi.model.CardInfoDTO;
import com.yenmai.clinicrestfulapi.model.GroupByValueDTO;
import com.yenmai.clinicrestfulapi.report.model.GroupByResult;
import com.yenmai.clinicrestfulapi.service.TongQuanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author YenMai
 */

@Service
public class TongQuanServiceImpl implements TongQuanService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private BenhNhanRespository benhNhanRespository;

    @Autowired
    private DangKiKhamBenhRespository dangKiKhamBenhRespository;

    @Autowired
    private HoaDonRespository hoaDonRespository;

    @Override
    @Transactional
    public CardInfoDTO getAllCardInfo() {

        CardInfoDTO tempCardInfoDTO = new CardInfoDTO();

        //Get thông tin cần thiết cho card
        int soBenhNhan = benhNhanRespository.findAll().size();
        int soNhanVien = nhanVienRepository.findAll().size();
        int soDangKiKhamBenh = dangKiKhamBenhRespository.findAll().size();
        int doanhThu = 0;
        for (HoaDon tempHoaDon : hoaDonRespository.findAll()){
            doanhThu += tempHoaDon.getThanhTien();
        }

        //Set dữ liệu cho card
        tempCardInfoDTO.setSoBenhNhan(soBenhNhan);
        tempCardInfoDTO.setSoNhanVien(soNhanVien);
        tempCardInfoDTO.setSoDangKiKhamBenh(soDangKiKhamBenh);
        tempCardInfoDTO.setDoanhThu(doanhThu);

        return tempCardInfoDTO;
    }

    @Override
    @Transactional
    public List<GroupByValueDTO> getDoanhThuTheoThang() {

        List<GroupByValueDTO> temGroupByValueDTOList = new ArrayList<>();

        //Lấy năm hiện tại
        int year = Calendar.getInstance().get(Calendar.YEAR);

        //Lấy giá trị của doanh thu theo từng thánh

        for (GroupByResult tempValue : hoaDonRespository.doanhThuTheoThangTrongNam(year)) {
            GroupByValueDTO tempGroup = new GroupByValueDTO();
            tempGroup.setName(tempValue.getName());
            tempGroup.setValue(tempValue.getValue());
            temGroupByValueDTOList.add(tempGroup);
        }

        if(temGroupByValueDTOList.size() == 0) {
            GroupByValueDTO tempGroup = new GroupByValueDTO();
            tempGroup.setName("Tháng 1");
            tempGroup.setValue(0);
            temGroupByValueDTOList.add(tempGroup);
        }

        return temGroupByValueDTOList;
    }

    @Override
    @Transactional
    public List<GroupByValueDTO> getBenhNhanTheoGioiTinh() {
        List<GroupByValueDTO> temGroupByValueDTOList = new ArrayList<>();

        //Lấy giá trị của doanh thu theo từng giới tính

        for (GroupByResult tempValue : benhNhanRespository.groupBenhNhanByGioiTinh()) {
            GroupByValueDTO tempGroup = new GroupByValueDTO();
            tempGroup.setName(tempValue.getName());
            tempGroup.setValue(tempValue.getValue());
            temGroupByValueDTOList.add(tempGroup);
        }


        return temGroupByValueDTOList;
    }

    @Override
    @Transactional
    public List<GroupByValueDTO> getNhanVienTheoChucVu() {
        List<GroupByValueDTO> temGroupByValueDTOList = new ArrayList<>();

        //Lấy giá trị của doanh thu theo từng giới tính

        for (GroupByResult tempValue : nhanVienRepository.groupNhanVienByChucVu()) {
            GroupByValueDTO tempGroup = new GroupByValueDTO();
            tempGroup.setName(tempValue.getName());
            tempGroup.setValue(tempValue.getValue());
            temGroupByValueDTOList.add(tempGroup);
        }


        return temGroupByValueDTOList;
    }

    @Override
    @Transactional
    public List<NhanVien> randomNhanVien() {
        return nhanVienRepository.randomNhanVien();
    }

    @Override
    @Transactional
    public List<BenhNhan> findBenhNhanMoiNhat() {
        return benhNhanRespository.findBenhNhanMoiNhat();
    }


}

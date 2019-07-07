package com.yenmai.clinicrestfulapi.service.Implements;

import com.yenmai.clinicrestfulapi.dao.BenhNhanRespository;
import com.yenmai.clinicrestfulapi.dao.DangKiKhamBenhRespository;
import com.yenmai.clinicrestfulapi.dao.HoaDonRespository;
import com.yenmai.clinicrestfulapi.dao.NhanVienRepository;
import com.yenmai.clinicrestfulapi.entity.BenhNhan;
import com.yenmai.clinicrestfulapi.entity.HoaDon;
import com.yenmai.clinicrestfulapi.entity.NhanVien;
import com.yenmai.clinicrestfulapi.model.*;
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
    public List<DoanhThuThangDTO> getDoanhThuTheoThang() {

        List<DoanhThuThangDTO> doanhThuThangDTOS = new ArrayList<>();

        //Lấy năm hiện tại
        int year = Calendar.getInstance().get(Calendar.YEAR);

        //Lấy giá trị của doanh thu theo từng thánh

        for (GroupByResult tempValue : hoaDonRespository.doanhThuTheoThangTrongNam(year)) {
            DoanhThuThangDTO tempDTO = new DoanhThuThangDTO();
            tempDTO.setThang(tempValue.getName());
            tempDTO.setGiaTri(tempValue.getValue());
            if(doanhThuThangDTOS.size() > 0){

                // doanh thu từ tháng trước
                int doanhThuTruoc = doanhThuThangDTOS.get(doanhThuThangDTOS.size() - 1).getGiaTri();

                //Doanh thu từ tháng sau
                int doanhThuSau  = tempValue.getValue();

                //Tính tỷ lệ
                float tyle = (float)(doanhThuSau - doanhThuTruoc) / doanhThuTruoc * 100;

                //Làm tròn tỷ lệ thành 2 chữ số thập phân
                tyle = Math.round(tyle * 100)/100f;

                tempDTO.setTyLe(tyle);
            }
            doanhThuThangDTOS.add(tempDTO);
        }



        return doanhThuThangDTOS ;
    }

    @Override
    @Transactional
    public List<GioiTinhDTO> getBenhNhanTheoGioiTinh() {
        List<GioiTinhDTO> temGroupByValueDTOList = new ArrayList<>();

        //Lấy giá trị của doanh thu theo từng giới tính

        for (GroupByGioiTinh tempValue : benhNhanRespository.groupBenhNhanByGioiTinh()) {
            GioiTinhDTO tempGroup = new GioiTinhDTO();
            tempGroup.setQuy(tempValue.getQuy());
            tempGroup.setGioiTinh(tempValue.getGioiTinh());
            tempGroup.setGiaTri(tempValue.getGiaTri());
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

    @Transactional
    @Override
    public List<DoanhThuThangDTO> doanhThuMuoiHaiThangQua() {

        List<DoanhThuThangDTO> doanhThuThangDTOS = new ArrayList<>();

        for (GroupByResult tempValue : hoaDonRespository.doanhThuMuoiHaiThangQua()) {
            DoanhThuThangDTO tempDTO = new DoanhThuThangDTO();
            tempDTO.setThang(tempValue.getName());
            tempDTO.setGiaTri(tempValue.getValue());

            //Tính tỷ lệ tạm bỏ qua

            doanhThuThangDTOS.add(tempDTO);
        }

        return doanhThuThangDTOS;
    }


}

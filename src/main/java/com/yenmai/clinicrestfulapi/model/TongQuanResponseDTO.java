package com.yenmai.clinicrestfulapi.model;

import java.util.List;

/**
 * @author YenMai
 */
public class TongQuanResponseDTO {

    private CardInfoDTO cardInfoDTO;

    private List<GroupByValueDTO> doanhThuTheoThang;

    private  List<GroupByValueDTO> tyleGioiTinh;

    private List<GroupByValueDTO> tyleChucVu;

    public TongQuanResponseDTO(CardInfoDTO cardInfoDTO, List<GroupByValueDTO> doanhThuTheoThang,
                               List<GroupByValueDTO> tyleGioiTinh, List<GroupByValueDTO> tyleChucVu) {
        this.cardInfoDTO = cardInfoDTO;
        this.doanhThuTheoThang = doanhThuTheoThang;
        this.tyleGioiTinh = tyleGioiTinh;
        this.tyleChucVu = tyleChucVu;
    }

    public TongQuanResponseDTO() {
    }

    public CardInfoDTO getCardInfoDTO() {
        return cardInfoDTO;
    }

    public void setCardInfoDTO(CardInfoDTO cardInfoDTO) {
        this.cardInfoDTO = cardInfoDTO;
    }

    public List<GroupByValueDTO> getDoanhThuTheoThang() {
        return doanhThuTheoThang;
    }

    public void setDoanhThuTheoThang(List<GroupByValueDTO> doanhThuTheoThang) {
        this.doanhThuTheoThang = doanhThuTheoThang;
    }

    public List<GroupByValueDTO> getTyleGioiTinh() {
        return tyleGioiTinh;
    }

    public void setTyleGioiTinh(List<GroupByValueDTO> tyleGioiTinh) {
        this.tyleGioiTinh = tyleGioiTinh;
    }

    public List<GroupByValueDTO> getTyleChucVu() {
        return tyleChucVu;
    }

    public void setTyleChucVu(List<GroupByValueDTO> tyleChucVu) {
        this.tyleChucVu = tyleChucVu;
    }
}

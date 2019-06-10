package com.yenmai.clinicrestfulapi.model;

import java.util.List;

/**
 * @author YenMai
 */
public class ListChiTietDonThuocDTO {
    private List<ChiTietDonThuocDTO> chiTietDonThuocDTOList;

    public ListChiTietDonThuocDTO(List<ChiTietDonThuocDTO> tietDonThuocDTOList) {
        this.chiTietDonThuocDTOList = tietDonThuocDTOList;
    }

    public List<ChiTietDonThuocDTO> getChiTietDonThuocDTOList() {
        return chiTietDonThuocDTOList;
    }

    public void setChiTietDonThuocDTOList(List<ChiTietDonThuocDTO> chiTietDonThuocDTOList) {
        this.chiTietDonThuocDTOList = chiTietDonThuocDTOList;
    }
}


package com.yenmai.clinicrestfulapi.report.service;

import com.yenmai.clinicrestfulapi.report.dao.DangKiKhamBenhReportDaoImpl;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;

@Service
public class DangKiKhamBenhReportService {

    @Autowired
    private DangKiKhamBenhReportDaoImpl dangKiKhamBenhReportDao;

    public JasperPrint exportPdfFile(int thangBaoCao, int namBaoCao) throws SQLException, JRException, IOException {
        return dangKiKhamBenhReportDao.exportPdfFile(thangBaoCao, namBaoCao);
    }
}
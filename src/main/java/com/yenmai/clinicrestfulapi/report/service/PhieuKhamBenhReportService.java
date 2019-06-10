package com.yenmai.clinicrestfulapi.report.service;

import com.yenmai.clinicrestfulapi.report.dao.PhieuKhamBenhReportDaoImpl;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;

@Service
public class PhieuKhamBenhReportService {

    @Autowired
    private PhieuKhamBenhReportDaoImpl phieuKhamBenhReportDao;

    public JasperPrint exportPdfFile(int maHoaDon) throws SQLException, JRException, IOException {
        return phieuKhamBenhReportDao.exportPdfFile(maHoaDon);
    }
}
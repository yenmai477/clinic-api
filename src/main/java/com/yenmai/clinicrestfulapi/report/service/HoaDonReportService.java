package com.yenmai.clinicrestfulapi.report.service;

import com.yenmai.clinicrestfulapi.report.dao.HoaDonReportDaoImpl;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;

@Service
public class HoaDonReportService {

    @Autowired
    private HoaDonReportDaoImpl hoaDonReportDao;

    public JasperPrint exportPdfFile(int maHoaDon) throws SQLException, JRException, IOException {
        return hoaDonReportDao.exportPdfFile(maHoaDon);
    }
}
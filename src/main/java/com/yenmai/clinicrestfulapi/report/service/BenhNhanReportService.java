package com.yenmai.clinicrestfulapi.report.service;

import com.yenmai.clinicrestfulapi.report.dao.BenhNhanReportDaoImpl;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;

@Service
public class BenhNhanReportService {

    @Autowired
    private BenhNhanReportDaoImpl benhNhanReportDao;

    public JasperPrint exportPdfFile(int thangThem, int namThem) throws SQLException, JRException, IOException {
        return benhNhanReportDao.exportPdfFile(thangThem, namThem);
    }
}
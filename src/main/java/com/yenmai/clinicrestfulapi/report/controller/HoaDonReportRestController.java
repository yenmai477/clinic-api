package com.yenmai.clinicrestfulapi.report.controller;

import com.yenmai.clinicrestfulapi.report.service.HoaDonReportService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * @author YenMai
 */
@RestController
@RequestMapping("/baocao/hoadon")
public class HoaDonReportRestController {
    @Autowired
    private HoaDonReportService hoaDonReportService;

    @GetMapping("/{maHoaDon}")
    public void export(ModelAndView model, HttpServletResponse response, @PathVariable int maHoaDon) throws IOException, JRException, SQLException {
        JasperPrint jasperPrint = null;

        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"hoadon.pdf\""));

        OutputStream out = response.getOutputStream();
        jasperPrint = hoaDonReportService.exportPdfFile(maHoaDon);
        JasperExportManager.exportReportToPdfStream(jasperPrint, out);
    }
}

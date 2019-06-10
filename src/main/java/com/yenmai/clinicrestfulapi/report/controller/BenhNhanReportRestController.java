package com.yenmai.clinicrestfulapi.report.controller;

import com.yenmai.clinicrestfulapi.report.service.BenhNhanReportService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

@RestController
@RequestMapping("/baocao/benhnhan")
public class BenhNhanReportRestController {

    @Autowired
    private BenhNhanReportService benhNhanReportService;

    @GetMapping
    public void export(ModelAndView model, HttpServletResponse response,
                       @RequestParam(value = "thangThem", defaultValue = "5", required = false) int thangThem,
                       @RequestParam(value = "namThem", defaultValue = "2019", required = false) int namThem
    ) throws IOException, JRException, SQLException {
        JasperPrint jasperPrint = null;

        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"users.pdf\""));

        OutputStream out = response.getOutputStream();
        jasperPrint = benhNhanReportService.exportPdfFile(thangThem, namThem);
        JasperExportManager.exportReportToPdfStream(jasperPrint, out);
    }
}

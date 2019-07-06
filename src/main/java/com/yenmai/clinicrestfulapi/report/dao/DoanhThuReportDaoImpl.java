package com.yenmai.clinicrestfulapi.report.dao;

import com.yenmai.clinicrestfulapi.dao.HoaDonRespository;
import com.yenmai.clinicrestfulapi.entity.HoaDon;
import com.yenmai.clinicrestfulapi.report.service.BenhNhanReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.*;

/**
 * @author YenMai
 */

@Repository
public class DoanhThuReportDaoImpl {
    Logger log = LogManager.getLogger(BenhNhanReportService.class);

    private static final String logo_path = "/jasper/images/logo.jpg";
    private final String report_template = "/jasper/DoanhThuTheoThang.jrxml";

    @Autowired
    HoaDonRespository hoaDonRespository;



    public void generateReportFor(int thangBaoCao, int namBaoCao) throws IOException {

        File pdfFile = File.createTempFile("my-report", ".pdf");

        try(FileOutputStream pos = new FileOutputStream(pdfFile))
        {
            // Load invoice jrxml template.
            final JasperReport report = loadTemplate();

            // Create parameters map.
            //final Map<String, Object> parameters = parameters(thangBaoCao, namBaoCao);

            // Create an empty datasource.
            final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singletonList("Report"));

            // Render as PDF.
            //JasperReportsUtils.renderAsPdf(report, parameters, dataSource, pos);

        }
        catch (final Exception e)
        {
            log.error(String.format("An error occured during PDF creation: %s", e));
        }
    }

    // Fill template order parametres
    private Map<String, Object> parameters(int thangBaoCao, int namBaoCao) {
        final Map<String, Object> parameters = new HashMap<>();

        parameters.put("logo", getClass().getResourceAsStream(logo_path));
        parameters.put("thangBaoCao", thangBaoCao);
        parameters.put("namBaoCao", namBaoCao);
        log.info(thangBaoCao+"/" + namBaoCao);
        return parameters;
    }


    // Fill template order field
    private List<Map<String, Object>> fieldReport(int thangBaoCao, int namBaoCao) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        for (HoaDon hoaDon : hoaDonRespository.findByThangThem(thangBaoCao, namBaoCao)) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("maHoaDon", hoaDon.getMaHoaDon());
            item.put("ngayThem", hoaDon.getNgayThem());
            item.put("tenKhachHang", hoaDon.getBenhNhan().getTenBenhNhan());
            item.put("tinhTrang", hoaDon.getTinhTrang());
            item.put("thanhTien", hoaDon.getThanhTien());

            result.add(item);
        }


        return result;
    }

    // Load invoice jrxml template
    private JasperReport loadTemplate() throws JRException {

        log.info(String.format("Invoice template path : %s", report_template));

        final InputStream reportInputStream = getClass().getResourceAsStream(report_template);
        final JasperDesign jasperDesign = JRXmlLoader.load(reportInputStream);

        log.info(String.format("Thành công log ở đây"));

        return JasperCompileManager.compileReport(jasperDesign);

    }

    public JasperPrint exportPdfFile(int thangBaoCao, int namBaoCao) throws SQLException, JRException, IOException {
        // Load invoice jrxml template.
        final JasperReport report = loadTemplate();

        // Create field map.
        final List<Map<String, Object>> tempFieldReport = fieldReport(thangBaoCao, namBaoCao);



        // Create parameters map.
        final Map<String, Object> parameters = parameters(thangBaoCao, namBaoCao);



        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(fieldReport(thangBaoCao, namBaoCao));

        JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);

        return print;
    }
}

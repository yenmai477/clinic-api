package com.yenmai.clinicrestfulapi.report.dao;

import com.yenmai.clinicrestfulapi.dao.ChiTietDonThuocRespository;
import com.yenmai.clinicrestfulapi.dao.ThuocRespository;
import com.yenmai.clinicrestfulapi.entity.ChiTietDonThuoc;
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
public class ThuocDaDungReportDaoImpl {
    Logger log = LogManager.getLogger(BenhNhanReportService.class);

    private static final String logo_path = "/jasper/images/logo.jpg";
    private final String report_template = "/jasper/ThuocDungTrongThang.jrxml";

    @Autowired
    ChiTietDonThuocRespository chiTietDonThuocRespository;

    @Autowired
    ThuocRespository thuocRespository;


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
    private Map<String, Object> parameters() {
        final Map<String, Object> parameters = new HashMap<>();

        parameters.put("logo", getClass().getResourceAsStream(logo_path));
        return parameters;
    }


    // Fill template order field
    private List<Map<String, Object>> fieldReport(int thangBaoCao, int namBaoCao) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        for (ChiTietDonThuoc chiTietDonThuoc : chiTietDonThuocRespository.findByThangThem(thangBaoCao, namBaoCao)) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("ngayKham", chiTietDonThuoc.getNgayThem());
            item.put("tenThuoc", chiTietDonThuoc.getThuoc().getTenThuoc());
            item.put("giaThuoc", chiTietDonThuoc.getThuoc().getGia());
            item.put("soLuong", chiTietDonThuoc.getSoLuong());
            item.put("thanhTien", chiTietDonThuoc.getThanhTien());
            item.put("maThuoc", chiTietDonThuoc.getThuoc().getMaThuoc());
            result.add(item);
        }

//        parameters.put("ngayThem",)
//        parameters.put("namBaoCao", namBaoCao);
//        parameters.put("thangBaoCao", thangBaoCao);

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
        final Map<String, Object> parameters = parameters();



        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(fieldReport(thangBaoCao, namBaoCao));

        JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);

        return print;
    }
}

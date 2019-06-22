package com.yenmai.clinicrestfulapi.report.dao;

import com.yenmai.clinicrestfulapi.dao.DangKiKhamBenhRespository;
import com.yenmai.clinicrestfulapi.entity.DangKiKhamBenh;
import com.yenmai.clinicrestfulapi.report.service.BenhNhanReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YenMai
 */

@Repository
public class DangKiKhamBenhReportDaoImpl {
    Logger log = LogManager.getLogger(BenhNhanReportService.class);

    private static final String logo_path = "/jasper/images/logo.jpg";
    private final String report_template = "/jasper/DangKiKhamBenh.jrxml";

    @Autowired
    DangKiKhamBenhRespository dangKiKhamBenhRespository;



    // Fill template order parametres
    private Map<String, Object> parameters(int thangBaoCao, int namBaoCao) {
        final Map<String, Object> parameters = new HashMap<>();

        parameters.put("logo", getClass().getResourceAsStream(logo_path));
        parameters.put("thangBaoCao", thangBaoCao);
        parameters.put("namBaoCao", namBaoCao);
        return parameters;
    }


    // Fill template order field
    private List<Map<String, Object>> fieldReport(int thangBaoCao, int namBaoCao) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        for (DangKiKhamBenh dangKiKhamBenh : dangKiKhamBenhRespository.findByThangThem(thangBaoCao, namBaoCao)) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("maDangKi", dangKiKhamBenh.getMaDangKi());
            item.put("ngayThem", dangKiKhamBenh.getNgayDangKi());
            item.put("tenBenhNhan", dangKiKhamBenh.getBenhNhan().getTenBenhNhan());
            item.put("tinhTrang", dangKiKhamBenh.getTinhTrang());

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

package com.yenmai.clinicrestfulapi.report.dao;

import com.yenmai.clinicrestfulapi.dao.ChiTietDonThuocRespository;
import com.yenmai.clinicrestfulapi.dao.HoaDonRespository;
import com.yenmai.clinicrestfulapi.dao.ThuocRespository;
import com.yenmai.clinicrestfulapi.entity.BenhNhan;
import com.yenmai.clinicrestfulapi.entity.ChiTietDonThuoc;
import com.yenmai.clinicrestfulapi.report.model.HoadonTable;
import com.yenmai.clinicrestfulapi.report.service.BenhNhanReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.jasperreports.JasperReportsUtils;

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
public class HoaDonReportDaoImpl {
    Logger log = LogManager.getLogger(BenhNhanReportService.class);

    private static final String logo_path = "/jasper/images/logo.jpg";
    private final String report_template = "/jasper/HoaDon.jrxml";

    @Autowired
    ChiTietDonThuocRespository chiTietDonThuocRespository;

    @Autowired
    ThuocRespository thuocRespository;

    @Autowired
    HoaDonRespository hoaDonRespository;

    public void generateReportFor(int maHoaDon) throws IOException {

        File pdfFile = File.createTempFile("my-report", ".pdf");

        try(FileOutputStream pos = new FileOutputStream(pdfFile))
        {
            // Load invoice jrxml template.
            final JasperReport report = loadTemplate();

            // Create parameters map.
            final Map<String, Object> parameters = parameters(maHoaDon);

            // Create an empty datasource.
            final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singletonList("Report"));

            // Render as PDF.
            JasperReportsUtils.renderAsPdf(report, parameters, dataSource, pos);

        }
        catch (final Exception e)
        {
            log.error(String.format("An error occured during PDF creation: %s", e));
        }
    }

    // Fill template order parametres
    private Map<String, Object> parameters(int maHoaDon) {
        final Map<String, Object> parameters = new HashMap<>();

        parameters.put("logo", getClass().getResourceAsStream(logo_path));
        //Cần tạo kiểu dữ liệu để thực hiện báo cáo
        List<HoadonTable> hoadonTables = new ArrayList<>();
        List<ChiTietDonThuoc> chiTietDonThuocs = chiTietDonThuocRespository.findByHoaDon(maHoaDon);
        //Chưa viết tìm tất cả đơn thuốc của hóa đơn
        int tienThuoc = 0;
        for (int i = 0; i < chiTietDonThuocs.size(); i++){
            tienThuoc +=chiTietDonThuocs.get(i).getThanhTien();

            HoadonTable tempRow = new HoadonTable();
            tempRow.setSoTT(i + 1);
            tempRow.setTenThuoc(chiTietDonThuocs.get(i).getThuoc().getTenThuoc());
            tempRow.setDonVi(chiTietDonThuocs.get(i).getThuoc().getDonVi());
            tempRow.setThanhTien(chiTietDonThuocs.get(i).getThanhTien());
            tempRow.setSoLuong(chiTietDonThuocs.get(i).getSoLuong());

            hoadonTables.add(tempRow);

        }

        BenhNhan tempBenhNhan = chiTietDonThuocs.get(0).getHoaDon().getBenhNhan();

        parameters.put("ItemDataSource", hoadonTables);
        parameters.put("maKhachHang", tempBenhNhan.getMaBenhNhan());
        System.out.println(tempBenhNhan.getMaBenhNhan());

        parameters.put("tenKhachHang",tempBenhNhan.getTenBenhNhan());
        System.out.println(tempBenhNhan.getTenBenhNhan());

        parameters.put("diaChi",tempBenhNhan.getDiaChi());
        System.out.println(tempBenhNhan.getDiaChi());
        parameters.put("soHoaDon", maHoaDon);
        System.out.println(maHoaDon);
        parameters.put("tienKham",3000000);
        parameters.put("tienThuoc",tienThuoc);
        System.out.println(tienThuoc);
        parameters.put("tongTien",3000000 + tienThuoc);
        return parameters;
    }

    // Load invoice jrxml template
    private JasperReport loadTemplate() throws JRException {

        log.info(String.format("Invoice template path : %s", report_template));

        final InputStream reportInputStream = getClass().getResourceAsStream(report_template);
        final JasperDesign jasperDesign = JRXmlLoader.load(reportInputStream);

        log.info(String.format("Thành công log ở đây"));

        return JasperCompileManager.compileReport(jasperDesign);

    }

    public JasperPrint exportPdfFile(int maHoaDon) throws SQLException, JRException, IOException {
        // Load invoice jrxml template.
        final JasperReport report = loadTemplate();

        // Create parameters map.
        final Map<String, Object> parameters = parameters(maHoaDon);


        JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

        return print;
    }
}

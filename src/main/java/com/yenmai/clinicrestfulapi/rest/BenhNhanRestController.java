package com.yenmai.clinicrestfulapi.rest;

import com.yenmai.clinicrestfulapi.entity.BenhNhan;
import com.yenmai.clinicrestfulapi.service.BenhNhanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/benhnhan")
public class BenhNhanRestController {
    private BenhNhanService benhNhanService;

    @Autowired
    public BenhNhanRestController(BenhNhanService benhNhanService) {
        this.benhNhanService = benhNhanService;
    }

    /**
     * Lấy ra tất cả bệnh nhân hoặc bệnh nhân có tên được truyền vào
     * @param tenBenhNhan
     * @return
     */
    @GetMapping
    public List<BenhNhan> findAll(@RequestParam(value = "name", required = false) String tenBenhNhan){
        if (tenBenhNhan!=null) {
            return benhNhanService.findByTenBenhNhanLike(tenBenhNhan);
        };
        return benhNhanService.findAll();
    }


    /**
     * Lấy ra bệnh nhân có mã được tryền vào
     * @param maBenhNhan
     * @return
     */
    @GetMapping("/{maBenhNhan}")
    public BenhNhan getBenhNhan(@PathVariable int maBenhNhan) {

        BenhNhan theBenhNhan = benhNhanService.findById(maBenhNhan);

        if (theBenhNhan == null) {
            throw new RuntimeException("Employee id not found - " + maBenhNhan);
        }

        return theBenhNhan;
    }


    /**
     * Thêm một bệnh nhân mới
     * @param thebenhNhan
     * @return
     */
    @PostMapping
    public BenhNhan addBenhNhan(@RequestBody BenhNhan thebenhNhan) {

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update
        thebenhNhan.setMaBenhNhan(0);

        benhNhanService.save(thebenhNhan);

        return thebenhNhan;
    }

    /**
     * Cập nhật thông tin bệnh nhân
     * @param theBenhNhan
     * @return
     */

    @PutMapping
    public BenhNhan updateBenhNhan(@RequestBody BenhNhan theBenhNhan) {

        benhNhanService.save(theBenhNhan);

        return theBenhNhan;
    }

    /**
     * Xóa một bệnh nhân khỏi danh sách
     * @param maBenhNhan
     * @return
     */
    @DeleteMapping("/{maBenhNhan}")
    public ResponseEntity<BenhNhan> deleteEmployee(@PathVariable int maBenhNhan) {

        BenhNhan tempBenhNhan = benhNhanService.findById(maBenhNhan);

        // throw exception if null

        if (tempBenhNhan == null) {
            throw new RuntimeException("Không tìm thấy bệnh nhân có mã- " + maBenhNhan);
        }

        benhNhanService.deleteById(maBenhNhan);

        //return "Đã xóa bệnh nhân có mã- " + maBenhNhan;
        return new ResponseEntity<BenhNhan>(HttpStatus.NO_CONTENT);
    }


}

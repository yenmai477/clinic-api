# clinic-rest-api
## BENHNHAN API


| Method 	| Link                                            	| Controller Method 	| Descriptions                                      	|
|--------	|-------------------------------------------------	|-------------------	|---------------------------------------------------	|
| GET    	| http://localhost:8080/api/benhnhan/             	| findAll()         	| Lấy ra danh sách tất cả các bệnh nhân             	|
| GET    	| http://localhost:8080/api/benhnhan/{maBenhNhan} 	| getBenhNhan()     	| Lấy ra thông tin bệnh nhân thông qua mã bệnh nhân 	|
| POST   	| http://localhost:8080/api/benhnhan              	| addBenhNhan()     	| Lưu bệnh nhân mới                                 	|
| PUT    	| http://localhost:8080/api/benhnhan              	| updateBenhNhan()  	| Cập nhật thông tin bệnh nhân                      	|
| DELETE 	| http://localhost:8080/api/benhnhan/{maBenhNhan} 	| deleteBenhNhan()  	| Xóa một bệnh nhân trong danh sách                 	|

### Dữ liệu trả về

- Lấy thông tin tất cả bệnh nhân

```json
[
    {
        "maBenhNhan": 1,
        "tenBenhNhan": "Nguyen Van A",
        "gioiTinh": "Nam",
        "ngaySinh": "1999-12-27",
        "diaChi": "Sai Gon",
        "ngheNghiep": "Coder",
        "soDienThoai": "011111111",
        "ngayThem": "2016-02-24"
    },
    {
        "maBenhNhan": 3,
        "tenBenhNhan": "Nguyen Van C",
        "gioiTinh": "Nam",
        "ngaySinh": "1999-12-27",
        "diaChi": "Sai Gon",
        "ngheNghiep": "Coder",
        "soDienThoai": "011111111",
        "ngayThem": "2016-02-24"
    }
]
```

- Lấy thông tin bệnh nhân qua mã bệnh nhân
```Json
{
    "maBenhNhan": 1,
    "tenBenhNhan": "Nguyen Van A",
    "gioiTinh": "Nam",
    "ngaySinh": "1999-12-27",
    "diaChi": "Sai Gon",
    "ngheNghiep": "Coder",
    "soDienThoai": "011111111",
    "ngayThem": "2016-02-24"
}
```

- Thêm một bệnh nhân mới

    - Dữ liệu trên body request
    ```Json
    {
        "tenBenhNhan": "Nguyen Van E",
        "gioiTinh": "Nam",
        "ngaySinh": "1999-12-27",
        "diaChi": "Sai Gon",
        "ngheNghiep": "Coder",
        "soDienThoai": "011111111",
        "ngayThem": "2016-02-24"
    }
    ```
    - Dữ liệu trả về
    ```Json
    {
        "maBenhNhan": 4,
        "tenBenhNhan": "Nguyen Van E",
        "gioiTinh": "Nam",
        "ngaySinh": "1999-12-27T00:00:00.000+0000",
        "diaChi": "Sai Gon",
        "ngheNghiep": "Coder",
        "soDienThoai": "011111111",
        "ngayThem": "2016-02-24T00:00:00.000+0000"
    }
    ```
- Cập nhật thông tin bệnh nhân 
    - Body request
    ```Json
    {
       "maBenhNhan": 1,
        "tenBenhNhan": "Nguyen Van C",
        "gioiTinh": "Nam",
        "ngaySinh": "1999-12-27",
        "diaChi": "Sai Gon",
        "ngheNghiep": "Coder",
        "soDienThoai": "011111111",
        "ngayThem": "2016-02-24"
    }
    ```

    - Kết quả trả về
    ```json
    {
    "maBenhNhan": 1,
    "tenBenhNhan": "Nguyen Van C",
    "gioiTinh": "Nam",
    "ngaySinh": "1999-12-27T00:00:00.000+0000",
    "diaChi": "Sai Gon",
    "ngheNghiep": "Coder",
    "soDienThoai": "011111111",
    "ngayThem": "2016-02-24T00:00:00.000+0000"
    }
    ```

- Xóa bệnh nhân
``` json
    Đã xóa bệnh nhân có mã-
```





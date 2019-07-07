create database quanlyphongkham;
drop database quanlyphongkham;

use  `quanlyphongkham`;


/*==============================================================*/
/* Table: NHANVIEN                                              */
/*==============================================================*/
create table NHANVIEN 
(
   MANHANVIEN int AUTO_INCREMENT not null,
   TENNHANVIEN varchar(50) not null,
   GIOITINH varchar(4) not null,
   NGAYSINH date not null,
   DIACHI char(100) not null,
   CHUCVU char(20) not null,
   NGAYVAO date  null,
   constraint PK_NHANVIEN primary key (MANHANVIEN)
);

/*==============================================================*/
/* Table: BENHNHAN                                              */
/*==============================================================*/
/*DROP TABLE benhnhan;*/
create table BENHNHAN 
(
   MABENHNHAN int AUTO_INCREMENT not null,
   TENBENHNHAN varchar(50) not null,
   GIOITINH varchar(5) not null,
   NGAYSINH date not null,
   DIACHI varchar(100) not null,
   NGHENGHIEP varchar(50) null,
   SODIENTHOAI varchar(20) null,
   NGAYTHEM date null,
   constraint PK_BENHNHAN primary key (MABENHNHAN)
);



/*==============================================================*/
/* Table: DANGKIKHAMBENH                                        */
/*==============================================================*/
/*DROP TABLE DANGKIKHAMBENH;*/
create table DANGKIKHAMBENH 
(
   MADANGKI int AUTO_INCREMENT not null,
   MABENHNHAN int,
   /*MABACSI int,*/
   NGAYDANGKI date null,
   TINHTRANG char(100),
   constraint PK_DANGKIKHAMBENH primary key (MADANGKI),
   constraint FK_DANGKIKH_DANG_KI_BENHNHAN foreign key (MABENHNHAN)
	references BENHNHAN (MABENHNHAN)
    
	/*constraint FK_DANGKIKH_KHAMBENH_NHANVIEN foreign key (MABACSI)
    references NHANVIEN (MANHANVIEN)*/
    
	ON DELETE NO ACTION ON UPDATE NO ACTION
);

/*==============================================================*/
/* Table: PHIEUKHAMBENH                                         */
/*==============================================================*/
/*DROP TABLE PHIEUKHAMBENH;*/
create table PHIEUKHAMBENH 
(
   MAPHIEUKHAMBENH      int  AUTO_INCREMENT                      not null,
   MADANGKI             int                       not null,
   TRIEUCHUNG			varchar(200)			  not null,
   CHUANDOAN            varchar(200)              not null,
   NGAYTHEM             date                      null,
   MABACSI				integer					  null,
   constraint PK_PHIEUKHAMBENH primary key (MAPHIEUKHAMBENH),
   
   constraint FK_PHIEUKHAM_CO_DANGKIKHAM foreign key (MADANGKI)
      references DANGKIKHAMBENH (MADANGKI),
      
	constraint FK_PHIEUKHAM_LAPBOI_BACSI foreign key (MABACSI)
      references NHANVIEN (MANHANVIEN)
      
	ON DELETE NO ACTION ON UPDATE NO ACTION
);

/*==============================================================*/
/* Table: THUOC                                                 */
/*==============================================================*/
/*DROP TABLE THUOC;*/
create table THUOC 
(
   MATHUOC              int     AUTO_INCREMENT                   not null,
   TENTHUOC             varchar(50)                       not null,
   DONVI                varchar(10)                       not null,
   GIA                  int                        not null,
   SOLUONG              int                        not null,
   NGAYSANXUAT          date                           not null,
   HANSUDUNG            date                           not null,
   constraint PK_THUOC primary key (MATHUOC)
   
);


/*==============================================================*/
/* Table: HOADON                                                */
/*==============================================================*/

/*DROP TABLE HOADON;*/
create table HOADON 
(
   MAHOADON             int    AUTO_INCREMENT                    not null,
   MABENHNHAN			int,
   THANHTIEN            int,
   TINHTRANG			varchar(20),
   NGAYTHEM             date,
   constraint PK_HOADON primary key (MAHOADON),
   constraint FK_HOADON_CUA_BENHNHAN foreign key (MABENHNHAN)
      references BENHNHAN (MABENHNHAN)
	
	
      
	ON DELETE NO ACTION ON UPDATE NO ACTION
);

/*==============================================================*/
/* Table: CHITIETDONTHUOC                                       */
/*==============================================================*/
/*DROP TABLE chitietdonthuoc;*/
create table CHITIETDONTHUOC 
(
   MACHITIET            int          AUTO_INCREMENT              not null,
   MATHUOC              int                         null,
   MAHOADON             int                         null,
   MAPHIEUKHAMBENH      int                         null,
   CACHDUNG             varchar(100)                       null,
   NGAYTHEM             date                           null,
   SOLUONG              int                         null,
   THANHTIEN            int                        null,
   constraint PK_CHITIETDONTHUOC primary key (MACHITIET),
   
   constraint FK_CHITIETDONTHUOC_CUA_HOADON foreign key (MAHOADON)
      references HOADON (MAHOADON),
      
	constraint FK_CHITIETDONTHUOC_CUA_PHIEUKHAM foreign key (MAPHIEUKHAMBENH)
      references PHIEUKHAMBENH (MAPHIEUKHAMBENH),
      
	constraint FK_CHITIETDONTHUOC_CANTHUOC_THUOC foreign key (MATHUOC)
      references THUOC (MATHUOC)
      
	ON DELETE NO ACTION ON UPDATE NO ACTION
   
);


create table TAIKHOAN 
(
   TENTAIKHOAN          varchar(30)   not null,
	MATKHAU 			varchar(100)  not null,
    QUYEN				varchar(20)	  not null,
	MANHANVIEN			int 		  not null,
    
   constraint PK_TAIKHOAN primary key (TENTAIKHOAN),
   constraint FK_TAIKHOAN_CUA_NHANVIEN foreign key (MANHANVIEN)
      references NHANVIEN (MANHANVIEN)
	
	
      
	ON DELETE NO ACTION ON UPDATE NO ACTION
);



/*trigger insert thành tiền chi tiết đơn thuốc và hóa đơn */
/*DROP TRIGGER before_chitietdonthuoc_insert;*/
DELIMITER $$
CREATE TRIGGER before_chitietdonthuoc_insert 
    BEFORE INSERT ON chitietdonthuoc
    FOR EACH ROW 
BEGIN
   declare v_giathuoc int;
   select gia into v_giathuoc
   from thuoc
   where mathuoc = new.mathuoc;
   set new.thanhtien = v_giathuoc * new.soluong;
   
   update hoadon
   set thanhtien = thanhtien + new.thanhtien
   where mahoadon = new.mahoadon;
   
   update thuoc
   set soluong = soluong - new.soluong
   where mathuoc = new.mathuoc;
END$$
DELIMITER ;


/*Insert dữ liệu bảng nhân viên */
Insert into NHANVIEN values(1,'Nguyễn Thành Luân', 'Nam', '1995-12-15', '731 Trần Hưng Đạo, Q5, TpHCM', 'Nhân viên','2016-08-20');
Insert into NHANVIEN values(2,'Lê Thị Phi Yến', 'Nữ', '1987-08-11','45 Nguyễn Hữu Cảnh, Q1, TpHCM', 'Bác sĩ','2013-05-21');
Insert into NHANVIEN values(3,'Trần Minh Long', 'Nam', '1977-07-02','50/34 Lê Đại Hành, Q10, TpHCM', 'Dược sĩ','2017-03-21');
Insert into NHANVIEN values(4,'Ngô Thanh Tuấn', 'Nam', '1972-09-03','34 Trương Định, Q3, TpHCM', 'Quản lý','2010-10-06');
Insert into NHANVIEN values(5,'Lê Hoài Thương', 'Nữ', '1985-04-12','227 Nguyễn Văn Cừ, Q5, TpHCM', 'Bác sĩ','2015-05-16');
Insert into NHANVIEN values(6,'Nguyễn Văn Tâm', 'Nam', '1975-12-05','32/3 Trần Bình Trọng, Q5, TpHCM', 'Bác sĩ','2014-12-26');
Insert into NHANVIEN values(7,'Phan Thị Thanh', 'Nữ', '1991-06-10','45/2 An Duong Vuong, Q5, TpHCM', 'Nhân viên','2016-11-12');



/*Insert dữ liệu bảng bệnh nhân*/
insert into BenhNhan values(1,'Hồ Thị Cẩm Hoài','Nữ','2005-12-15','873 Lê Hồng Phong, Q5, TpHCM','Học sinh',0903655711,'2017-02-03');
insert into BenhNhan values(2,'Lê Tấn Hoàng','Nam','1973-11-05',' Linh Trung, Thủ Đức, TpHCM','Lập trình viên',0735425911,'2017-12-22');
insert into BenhNhan values(3,'Bùi Trần Nhật Lệ','Nữ','1981-11-09','4/34B Nguyen Trai, Q1, TpHCM','Giáo viên',0735711197,'2018-01-02');
insert into BenhNhan values(4,'Cao Thành Nghĩa','Nam','1960-11-06','126 Le Hong Phong, Q5, TpHCM','Kỹ sư',0711197198,'2018-01-11');
insert into BenhNhan values(5,'Lê Tú Anh','Nữ','1988-11-01','83 Nguyễn Văn Cừ, Q5, TpHCM','Nhân viên',0977425671,'2018-01-13');
insert into BenhNhan values(6,'Bùi Tiến An','Nữ','2001-11-10','16 Lý Thường Kiệt, Q10, TpHCM','Học sinh',0705423213,'2018-01-21');
insert into BenhNhan values(7,'Mai Thị Lệ Hằng','Nữ','2009-01-12','23 Lê Hồng Phong, Q5, TpHCM','Học sinh',0903682197,'2018-01-28');
insert into BenhNhan values(8,'Đào Tấn Phát','Nam','1997-06-11','43 Lũy Bán Bích, Tân Phú, TP.HCM','Ca sĩ',0932714761,'2018-02-01');
insert into BenhNhan values(9,'Trần Văn Hoàng','Nam','1998-08-11','56 Bạch Đằng, Bình Thạnh, TP.HCM','Sinh viên',0973187253,'2018-02-05');
insert into BenhNhan values(10,'Lê Thị Cẩm Nhung','Nữ','1981-11-09','32 Lê Văn Việt, Q9, TP.HCM','Giảng viên',0988711488,'2018-02-15');
insert into BenhNhan values(11,'Đặng Thị Ngọc Hoài','Nữ','1992-06-11','19 Linh Trung, Thủ Đức, TP.HCM','Công nhân',0971143165,'2018-02-15');
insert into BenhNhan values(12,'Diệp Minh Toàn','Nam','1997-04-09','236 Yersin, Q10, TP HCM','Thợ làm tóc',0984145230,'2018-02-19');
insert into BenhNhan values(13,'Vũ Trí Minh','Nam','1988-09-29','10 Đỗ Quang Đẩu, Q6, TP HCM','Bảo vệ',0984145522,'2018-02-22');
insert into BenhNhan values(14,'Tạ Trường Thành','Nam','1995-07-18','376 Mai Thị Lựu, Q8, TP HCM','Nhà kinh tế học',0984145297,'2018-03-02');
insert into BenhNhan values(15,'Lê Ðình Quảng','Nam','1965-04-21','117 Yersin, Q2, TP HCM','Vệ sĩ',0984145715,'2018-03-04');
insert into BenhNhan values(16,'Trần Ngọc Quang','Nam','2010-10-17','298 Cao Bá Quát, Q11, TP HCM','Nhà báo',0984145975,'2018-03-10');
insert into BenhNhan values(17,'Đỗ Ðức Phi','Nam','2003-03-25','315 Nguyễn Văn Đại, Q1, TP HCM','Bác sĩ thú y',0984145911,'2018-03-13');
insert into BenhNhan values(18,'Lâm Tất Bình','Nam','1977-04-20','178 Nguyễn Bỉnh Khiêm, Q12, TP HCM','Lái xe taxi',0984145544,'2018-03-29');
insert into BenhNhan values(19,'Uất Khánh Bình','Nam','1984-09-02','289 Lê Văn Hưu, Q12, TP HCM','Thư ký ',0984145781,'2018-04-03');
insert into BenhNhan values(20,'Phí Bảo Thạch','Nam','1967-11-10','122 Nguyễn Thị Minh Khai, Q11, TP HCM','Thợ làm bánh',0984145063,'2018-04-03');
insert into BenhNhan values(21,'Tống Anh Quân','Nam','1996-01-22','262 Hồ Hảo Hớn, Q3, TP HCM','Thu ngân',0984145995,'2018-04-04');
insert into BenhNhan values(22,'Trần Trung Dũng','Nam','2000-07-22','70 Cô Giang, Q5, TP HCM','Thợ cắt tóc',0984145517,'2018-04-04');
insert into BenhNhan values(23,'Phan Duy Thông','Nam','1979-05-17','200 Mã Lộ, Q9, TP HCM','Thư ký ',0984145013,'2018-04-04');
insert into BenhNhan values(24,'Nguyễn Phú Hiệp','Nam','1969-03-09','374 Đông Du, Q11, TP HCM','Nhạc công',0984145503,'2018-04-11');
insert into BenhNhan values(25,'Ngô Hữu Vượng','Nam','2011-02-21','235 Đông Du, Q5, TP HCM','Quản lý khách sạn',0984145862,'2018-04-20');
insert into BenhNhan values(26,'Đặng Ðình Nam','Nam','2005-10-04','156 Nguyễn Văn Linh, Q2, TP HCM','Thu ngân',0984145078,'2018-04-25');
insert into BenhNhan values(27,'Kiều Hữu Nam','Nam','2002-07-30','374 Phùng Khắc Khoan, Q3, TP HCM','Thợ sửa máy',0984145820,'2018-05-10');
insert into BenhNhan values(28,'Phan Ðức Hòa','Nam','1987-11-30','213 Đại Lộ Đông Tây, Q1, TP HCM','Vệ sĩ',0984145828,'2018-05-12');
insert into BenhNhan values(29,'Nguyễn Chí Nam','Nam','2007-08-16','331 Nguyễn Thị Lắng, Q6, TP HCM','Thuyền trưởng',0984145672,'2018-05-12');
insert into BenhNhan values(30,'Phạm Anh Tùng','Nam','1974-05-30','386 Trần Đình Xu, Q5, TP HCM','Cố vấn tài chính',0984145569,'2018-05-12');
insert into BenhNhan values(31,'Phạm Xuân Trường','Nam','2014-03-10','219 Công Trường Mê Linh, Q12, TP HCM','Nhân viên',0984145390,'2018-06-05');
insert into BenhNhan values(32,'Nguyễn Ngọc Tuấn','Nam','1980-02-15','71 Nguyễn Văn Trỗi, Q9, TP HCM','Bảo vệ',0984145047,'2018-06-29');
insert into BenhNhan values(33,'Bạch Quảng Thông','Nam','1969-10-09','16 Trần Bình Trọng, Q9, TP HCM','Phi công',0984145725,'2018-07-04');
insert into BenhNhan values(34,'Lý Hải Thụy','Nam','1990-04-22','52 Lê Hồng Phong, Q7, TP HCM','Thám tử',0984145882,'2018-07-09');
insert into BenhNhan values(35,'Văn Lâm Ðông','Nam','1983-04-27','140 Nguyễn Hữu Nghĩa, Q1, TP HCM','Thợ xây',0984145438,'2018-07-31');
insert into BenhNhan values(36,'Lý Quốc Bình','Nam','1986-08-19','235 Nguyễn Thị Minh Khai, Q9, TP HCM','Kỹ sư',0984145754,'2018-08-01');
insert into BenhNhan values(37,'Đặng Việt Ngọc','Nam','2007-11-24','181 Nam Kỳ Khởi Nghĩa, Q7, TP HCM','Giám đốc',0984145397,'2018-10-15');
insert into BenhNhan values(38,'Nghiêm Hoàng Ân','Nam','1988-11-20','75 Nguyễn Bỉnh Khiêm, Q3, TP HCM','Thợ mộc',0984145909,'2018-10-15');
insert into BenhNhan values(39,'Lý Ngọc Thọ','Nam','1986-03-11','292 Quang Trung, Q5, TP HCM','Nhà văn',0984145360,'2018-11-04');
insert into BenhNhan values(40,'Lý Hùng Ngọc','Nam','1967-05-13','323 Trần Khánh Dư, Q7, TP HCM','Nam diễn viên',0984145494,'2018-11-08');
insert into BenhNhan values(41,'Trần Huyền Thư','Nữ','1978-09-27','327 Hòa Hưng, Q2, TP HCM','Nhạc công',0984145028,'2018-11-08');
insert into BenhNhan values(42,'Lê Phương Loan','Nữ','2009-01-18','66 Công Trường Mê Linh, Q9, TP HCM','Thuyền trưởng',0984145872,'2018-11-16');
insert into BenhNhan values(43,'Lê Quỳnh Vân','Nữ','1993-05-23','220 Đinh Tiên Hoàng, Q3, TP HCM','Đại diện bán hàng',0984145190,'2018-11-17');
insert into BenhNhan values(44,'Bùi Hoàng Lan','Nữ','2007-08-31','206 Nguyễn Văn Tráng, Q8, TP HCM','Y tá',0984145264,'2018-11-17');
insert into BenhNhan values(45,'Vũ Tuyết Trinh','Nữ','1976-11-09','198 Hai Bà Trưng, Q8, TP HCM','Nhân viên',0984145093,'2018-11-18');
insert into BenhNhan values(46,'Đàm Hải Anh','Nữ','1975-11-13','358 Nguyễn Du, Q4, TP HCM','Y tá',0984145298,'2018-12-14');
insert into BenhNhan values(47,'Ân Thúy Liễu','Nữ','1974-12-30','194 Nguyễn Trãi, Q2, TP HCM','Bồi bàn nữ',0984145599,'2018-12-20');
insert into BenhNhan values(48,'Mạch An Hạ','Nữ','2013-06-20','387 Nguyễn Đình Chiểu, Q4, TP HCM','Nhân viên',0984145693,'2018-12-27');
insert into BenhNhan values(49,'Nguyễn Việt Hà','Nữ','1973-09-26','247 Phan Liêm, Q2, TP HCM','Bảo vệ',0984145573,'2018-12-27');
insert into BenhNhan values(50,'Đỗ Ðinh Hương','Nữ','1987-12-07','291 Ký Con, Q7, TP HCM','Thợ điện',0984145394,'2018-12-29');
insert into BenhNhan values(51,'Tống Mỹ Uyên','Nữ','1968-05-22','127 Công Trường Mê Linh, Q10, TP HCM','Lái xe taxi',0984145693,'2019-01-12');
insert into BenhNhan values(52,'Phó Bích Hảo','Nữ','2005-08-15','361 Đinh Công Tráng, Q7, TP HCM','Kỹ sư',0984145089,'2019-02-12');
insert into BenhNhan values(53,'Võ Phương Dung','Nữ','1993-10-16','69 Công Trường Quốc Tế, Q12, TP HCM','Bồi bàn',0984145237,'2019-02-16');
insert into BenhNhan values(54,'Đào Thu Nguyệt','Nữ','2008-01-03','50 Lý Văn Phức, Q8, TP HCM','Cảnh sát',0984145078,'2019-03-07');
insert into BenhNhan values(55,'Chung Tuyết Hồng','Nữ','2003-12-31','393 Võ Văn Kiệt, Q4, TP HCM','Thợ điện',0984145574,'2019-04-25');
insert into BenhNhan values(56,'Tô Khánh Giao','Nữ','2007-10-16','393 Ngô Văn Năm, Q7, TP HCM','Bán hàng',0984145226,'2019-04-27');
insert into BenhNhan values(57,'Lạc Thanh Thúy','Nữ','1976-06-12','287 Mai Thị Lựu, Q1, TP HCM','Bồi bàn nữ',0984145852,'2019-04-27');
insert into BenhNhan values(58,'Trần Hiếu Hạnh','Nữ','1997-08-01','76 Đề Thám, Q1, TP HCM','Vệ sĩ',0984145931,'2019-05-17');
insert into BenhNhan values(59,'Nguyễn Thục Anh','Nữ','1982-03-06','343 Nguyễn Văn Côn, Q7, TP HCM','Giám đốc',0984145931,'2019-05-22');
insert into BenhNhan values(60,'Vĩnh Nhật Hà','Nữ','1969-10-28','198 Hàm Nghi, Q6, TP HCM','Thư ký',0984145880,'2019-06-17');



/*Insert dữ liệu bảng đăng kí khám bệnh*/

insert into DangKiKhamBenh values(1,1,'2017-11-23','Đã khám');
insert into DangKiKhamBenh values(2,1,'2018-02-19','Đã khám');
insert into DangKiKhamBenh values(3,2,'2018-02-21','Đã khám');
insert into DangKiKhamBenh values(4,3,'2018-04-11','Đã khám');
insert into DangKiKhamBenh values(5,5,'2018-04-20','Đã khám');
insert into DangKiKhamBenh values(6,6,'2018-04-28','Đã khám');
insert into DangKiKhamBenh values(7,7,'2018-05-07','Đã khám');
insert into DangKiKhamBenh values(8,3,'2018-05-12','Đã khám');
insert into DangKiKhamBenh values(9,4,'2018-05-18','Đã khám');
insert into DangKiKhamBenh values(10,9,'2018-05-20','Đã khám');
insert into DangKiKhamBenh values(11,11,'2018-06-09','Đã khám');
insert into DangKiKhamBenh values(12,10,'2018-07-16','Đã khám');
insert into DangKiKhamBenh values(13,9,'2018-07-31','Đã khám');
insert into DangKiKhamBenh values(15,1,'2018-08-05','Đã khám');
insert into DangKiKhamBenh values(16,11,'2018-09-19','Đã khám');
insert into DangKiKhamBenh values(17,12,'2018-10-05','Đã khám');
insert into DangKiKhamBenh values(18,15,'2018-11-10','Đã khám');
insert into DangKiKhamBenh values(19,12,'2018-12-17','Đã khám');
insert into DangKiKhamBenh values(20,16,'2019-01-19','Đã khám');
insert into DangKiKhamBenh values(21,15,'2019-02-10','Đã khám');
insert into DangKiKhamBenh values(22,17,'2019-02-20','Đã khám');
insert into DangKiKhamBenh values(23,19,'2019-03-09','Đã khám');
insert into DangKiKhamBenh values(24,22,'2019-03-22','Đã khám');
insert into DangKiKhamBenh values(25,23,'2019-04-05','Đã khám');
insert into DangKiKhamBenh values(26,46,'2019-05-05','Đã khám');
insert into DangKiKhamBenh values(27,43,'2019-06-17','Đã khám');
insert into DangKiKhamBenh values(28,41,'2019-07-06','Đã khám');



/*Insert dữ liệu bảng phiếu khám bệnh*/

insert into PhieuKhamBenh values(1,1,'Nhứt đầu, chóng mặt, buồn nôn','Ngộ độc thực phẩm nhẹ','2017-11-23',2);
insert into PhieuKhamBenh values(2,2,'Nghẹt mũi, sổ mũi, chóng mặt, đau đầu','Cảm cúm','2018-02-19',5);
insert into PhieuKhamBenh values(3,3,'Ho có đàm, khó thở, mệt mõi','Viêm phế quả','2018-02-21',6);
insert into PhieuKhamBenh values(4,4,'Đau đầu âm ỉ, chóng mặt, mệt mõi','Rối loạn tiền đình','2018-04-11',2);
insert into PhieuKhamBenh values(5,5,'Nhứt đầu, chóng mặt, buồn nôn','Ngộ độc thực phẩm nhẹ','2018-04-20',6);
insert into PhieuKhamBenh values(6,6,'Ho có đàm','Viêm phế quản','2018-04-28',5);
insert into PhieuKhamBenh values(7,7,'Ho có đàm','Viêm phế quản','2018-05-07',5);
insert into PhieuKhamBenh values(8,8,'Đau đầu, chóng mặt, ho có đàm','Cảm cúm','2018-05-12',5);
insert into PhieuKhamBenh values(9,9,'Ho có đàm, khó thở, tức ngực','Viêm phế quản','2018-05-18',5);
insert into PhieuKhamBenh values(10,10,'Ho có đàm','Viêm phế quản','2018-05-20',2);
insert into PhieuKhamBenh values(11,11,'Ho có đàm','Viêm phế quản','2018-06-09',5);
insert into PhieuKhamBenh values(12,12,'Ho có đàm','Viêm phế quản','2018-07-16',5);
insert into PhieuKhamBenh values(13,13,'Mệt mõi, ăn không tiêu, buồn nôn','Rối loạn tiêu hóa ','2018-07-31',6);
insert into PhieuKhamBenh values(14,15,'Nóng sốt, nhứt đầu, chóng mặt','Sốt siêu vi','2018-08-05',2);
insert into PhieuKhamBenh values(15,16,'Nghẹt mũi, sổ mũi, khó thở, nóng sốt','Cảm cúm','2018-09-19',6);
insert into PhieuKhamBenh values(16,17,'Đau răng, sưng má bênh phải','Sâu răng số sáu','2018-10-05',6);
insert into PhieuKhamBenh values(17,18,'Hắt hơi, sổ mũi, nhứt đầu','Cảm cúm','2018-11-10',6);
insert into PhieuKhamBenh values(18,19,'Ho có đàm','Viêm phế quản','2018-12-17',6);
insert into PhieuKhamBenh values(19,20,'Ho có đàm','Viêm phế quản','2019-01-19',6);
insert into PhieuKhamBenh values(20,21,'Ho có đàm','Viêm phế quản','2019-02-10',5);
insert into PhieuKhamBenh values(21,22,'Nghẹt mũi, sổ mũi, chóng mặt, đau đầu','Cảm cúm','2019-02-20',6);
insert into PhieuKhamBenh values(22,23,'Ho có đàm','Viêm phế quản','2019-03-09',6);
insert into PhieuKhamBenh values(23,24,'Ho có đàm','Viêm phế quản','2019-03-22',5);
insert into PhieuKhamBenh values(24,25,'Ho có đàm','Viêm phế quản','2019-04-05',2);
insert into PhieuKhamBenh values(25,26,'Ho có đàm','Viêm phế quản','2019-05-05',5);
insert into PhieuKhamBenh values(26,27,'Đau răng ','Sâu răng số bảy','2019-06-17',5);
insert into PhieuKhamBenh values(27,28,'Ho có đàm','Viêm phế quản','2019-07-06',2);



/*Insert dữ liệu bảng thuốc*/

insert into THUOC values (1,'Fentanyl (citrat)','viên',500,30000,'2017-05-22','2022-05-22');
insert into THUOC values (2,'Halothan','viên',500,20000,'2017-05-22','2022-05-22');
insert into THUOC values (3,'Ketamin (hydroclorid)','ống',500,10000,'2017-05-22','2022-05-22');
insert into THUOC values (4,'Bupivacain hydroclorid','bịch',500,15000,'2017-05-22','2022-05-22');
insert into THUOC values (5,'Lidocain hydroclorid','viên',500,13000,'2017-05-22','2022-05-22');
insert into THUOC values (6,'Lidocain hydroclorid','ống',500, 14000,'2017-05-22','2022-05-22');
insert into THUOC values (7,'Ephedrin hydroclorid','ống',500, 21000,'2017-05-22','2022-05-22');
insert into THUOC values (8,'Diclofenac','bịch',500,17000,'2017-05-22','2022-05-22');
insert into THUOC values (9,'Ibuprofen','bịch',500,30000,'2017-05-22','2022-05-22');
insert into THUOC values (10,'Meloxicam','ống',500,20000,'2017-05-22','2022-05-22');
insert into THUOC values (11,'Paracetamol','ống',500,15000,'2017-05-22','2022-05-22');
insert into THUOC values (12,'Piroxicam','ống',500,9000,'2017-05-22','2022-05-22');
insert into THUOC values (13,'Morphin sulfat','bịch',500,12000,'2017-05-22','2022-05-22');
insert into THUOC values (14,'Cyclizin','bịch',500,13000,'2017-05-22','2022-05-22');
insert into THUOC values (15,'Dexamethason','viên',500,10000,'2017-05-22','2022-05-22');
insert into THUOC values (16,'Diazepam','viên',500,8000,'2017-05-22','2022-05-22');
insert into THUOC values (17,'Docusat natri','viên',500,25000,'2017-05-22','2022-05-22');
insert into THUOC values (18,'Lactulose ','ống',500,12000,'2017-05-22','2022-05-22');
insert into THUOC values (19,'Midazolam','ống',500,13000,'2017-05-22','2022-05-22');
insert into THUOC values (20,'Alimemazin','viên',500 ,12000,'2017-05-22','2022-05-22');
insert into THUOC values (21,'Clorpheniramin maleat','viên',500 ,15000,'2017-05-22','2022-05-22');
insert into THUOC values (22,'Dexamethason','viên',500 ,21000,'2017-05-22','2022-05-22');
insert into THUOC values (23,'Methylprednisolon','ống',500 ,11000,'2017-05-22','2022-05-22');
insert into THUOC values (24,'Prednisolon','ống',500 ,14000,'2017-05-22','2022-05-22');
insert into THUOC values (25,'Loratadin','ống',500 ,13000,'2017-05-22','2022-05-22');
insert into THUOC values (26,'Promethazin hydroclorid','viên',500,10000,'2017-05-22','2022-05-22');
insert into THUOC values (27,'Atropin sulfat ','viên',500 ,20000,'2017-05-22','2022-05-22');
insert into THUOC values (28,'Deferoxamin mesylat','viên',500,9000,'2017-05-22','2022-05-22');
insert into THUOC values (29,'Dimercaprol','viên',500,15000,'2017-05-22','2022-05-22');
insert into THUOC values (30,'Methionin','bịch',500,16000,'2017-05-22','2022-05-22');


/*Insert dữ liệu bảng hóa đơn*/
insert into HoaDon values(1,1,'310000','Đã thanh toán','2017-11-23');
insert into HoaDon values(2,1,'310000','Đã thanh toán','2018-02-19');
insert into HoaDon values(3,2,'310000','Đã thanh toán','2018-02-21');
insert into HoaDon values(4,3,'310000','Đã thanh toán','2018-04-11');
insert into HoaDon values(5,5,'310000','Đã thanh toán','2018-04-20');
insert into HoaDon values(6,6,'315000','Đã thanh toán','2018-04-28');
insert into HoaDon values(7,7,'310000','Đã thanh toán','2018-05-07');
insert into HoaDon values(8,3,'317500','Đã thanh toán','2018-05-12');
insert into HoaDon values(9,4,'315000','Chưa thanh toán','2018-05-18');
insert into HoaDon values(10,9,'305000','Đã thanh toán','2018-05-20');
insert into HoaDon values(11,11,'310000','Chưa thanh toán','2018-06-09');
insert into HoaDon values(12,10,'317500','Chưa thanh toán','2018-07-16');
insert into HoaDon values(13,9,'315000','Chưa thanh toán','2018-07-31');
insert into HoaDon values(14,1,'315000','Chưa thanh toán','2018-08-05');
insert into HoaDon values(15,11,'310000','Chưa thanh toán','2018-09-19');
insert into HoaDon values(16,12,'315000','Chưa thanh toán','2018-10-05');
insert into HoaDon values(17,15,'310000','Chưa thanh toán','2018-11-10');
insert into HoaDon values(18,12,'310000','Chưa thanh toán','2018-12-17');
insert into HoaDon values(19,16,'310000','Chưa thanh toán','2019-01-19');
insert into HoaDon values(20,15,'310000','Chưa thanh toán','2019-02-10');
insert into HoaDon values(21,17,'310000','Chưa thanh toán','2019-02-20');
insert into HoaDon values(22,19,'310000','Chưa thanh toán','2019-03-09');
insert into HoaDon values(23,22,'315000','Chưa thanh toán','2019-03-22');
insert into HoaDon values(24,23,'305000','Chưa thanh toán','2019-04-05');
insert into HoaDon values(25,46,'315000','Chưa thanh toán','2019-05-05');
insert into HoaDon values(26,43,'310000','Chưa thanh toán','2019-06-17');
insert into HoaDon values(27,41,'305000','Chưa thanh toán','2019-07-06');




/*Insert dữ liệu bảng chi tiết hóa đơn*/
insert into ChiTietDonThuoc values(1,1,1,1,'Uống 2 viên/ngày','2017-11-23',10,5000);
insert into ChiTietDonThuoc values(2,2,1,1,'Uống 2 viên/ngày','2017-11-23',10,5000);
insert into ChiTietDonThuoc values(3,3,2,2,'Uống 2 ống/ngày','2017-11-23',10,5000);
insert into ChiTietDonThuoc values(4,4,2,2,'Uống 2 bịch/ngày','2018-02-19',10,5000);
insert into ChiTietDonThuoc values(5,5,3,3,'Uống 2 viên/ngày','2018-02-19',10,5000);
insert into ChiTietDonThuoc values(6,6,3,3,'Uống 2 ống/ngày','2018-02-21',10,5000);
insert into ChiTietDonThuoc values(7,3,4,4,'Uống 2 ống/ngày','2018-02-21',10,5000);
insert into ChiTietDonThuoc values(8,4,4,4,'Uống 2 bịch/ngày','2018-04-11',10,5000);
insert into ChiTietDonThuoc values(9,5,5,5,'Dùng 2 lần/ngày','2018-04-11',10,5000);
insert into ChiTietDonThuoc values(10,6,5,5,'Dùng 2 lần/ngày','2018-04-20',10,5000);
insert into ChiTietDonThuoc values(11,1,6,6,'2 lần/ngày','2018-04-20',10,5000);
insert into ChiTietDonThuoc values(12,2,6,6,'2 lần/ ngày','2018-04-20',10,5000);
insert into ChiTietDonThuoc values(13,5,6,6,'2 lần / ngày ','2018-04-28',10,5000);
insert into ChiTietDonThuoc values(14,15,7,7,'2 lần/ngày','2018-04-28',10,5000);
insert into ChiTietDonThuoc values(15,26,7,7,'3 lần/ngày','2018-04-28',10,5000);
insert into ChiTietDonThuoc values(16,1,8,8,'3 lần/ngày','2018-05-07',10,5000);
insert into ChiTietDonThuoc values(17,2,8,8,'2 lần/ ngày','2018-05-07',10,5000);
insert into ChiTietDonThuoc values(18,8,8,8,'2 lần/ ngày','2018-05-12',10,5000);
insert into ChiTietDonThuoc values(19,14,8,8,'1 lần/ ngày','2018-05-12',5,2500);
insert into ChiTietDonThuoc values(20,3,9,9,'2 lần/ ngày','2018-05-12',10,5000);
insert into ChiTietDonThuoc values(21,21,9,9,'2 lần/ ngày','2018-05-18',10,5000);
insert into ChiTietDonThuoc values(22,11,9,9,'2 lần/ ngày','2018-05-18',10,5000);
insert into ChiTietDonThuoc values(23,1,10,10,'2 lan/ ngay','2018-05-20',10,5000);
insert into ChiTietDonThuoc values(24,4,11,11,'2 lần/ ngày','2018-05-20',10,5000);
insert into ChiTietDonThuoc values(25,5,11,11,'3 lần / ngày','2018-06-09',10,5000);
insert into ChiTietDonThuoc values(26,1,12,12,'2 lần / ngày','2018-06-09',10,5000);
insert into ChiTietDonThuoc values(27,11,12,12,'2 lần / ngày','2018-06-09',10,5000);
insert into ChiTietDonThuoc values(28,18,12,12,'2 lần / ngày','2018-07-16',15,7500);
insert into ChiTietDonThuoc values(29,10,13,13,'2 lần / ngày','2018-07-16',10,5000);
insert into ChiTietDonThuoc values(30,13,13,13,'2 lần / ngày','2018-07-16',10,5000);
insert into ChiTietDonThuoc values(31,29,13,13,'1 lần / ngày','2018-07-31',10,5000);
insert into ChiTietDonThuoc values(32,24,14,14,'2 lần / ngày','2018-07-31',10,5000);
insert into ChiTietDonThuoc values(33,29,14,14,'2 lần / ngày ','2018-07-31',10,5000);
insert into ChiTietDonThuoc values(34,30,14,14,'2 lần / ngày ','2018-08-05',10,5000);
insert into ChiTietDonThuoc values(35,26,15,15,'2 lần / ngày ','2018-08-05',10,5000);
insert into ChiTietDonThuoc values(36,11,15,15,'2 lần / ngày','2018-09-19',10,5000);
insert into ChiTietDonThuoc values(37,12,16,16,'2 lần / ngày ','2018-09-19',10,5000);
insert into ChiTietDonThuoc values(38,27,16,16,'2 lần / ngày','2018-10-05',10,5000);
insert into ChiTietDonThuoc values(39,3,16,16,'2 lần / ngày','2018-10-05',10,5000);
insert into ChiTietDonThuoc values(40,11,17,17,'2 lần / ngày','2018-11-10',10,5000);
insert into ChiTietDonThuoc values(41,16,17,17,'2 lần / ngày','2018-11-10',10,5000);
insert into ChiTietDonThuoc values(42,16,18,18,'2 lần / ngày','2018-12-17',10,5000);
insert into ChiTietDonThuoc values(43,4,18,18,'2 lần / ngày','2018-12-17',10,5000);
insert into ChiTietDonThuoc values(44,16,19,19,'2 lần/ ngày','2018-12-17',10,5000);
insert into ChiTietDonThuoc values(45,1,19,19,'2 lần / ngày','2018-12-17',10,5000);
insert into ChiTietDonThuoc values(46,9,20,20,'2 lần/ ngày','2019-01-19',10,5000);
insert into ChiTietDonThuoc values(47,13,20,20,'2 lần / ngày ','2019-01-19',10,5000);
insert into ChiTietDonThuoc values(48,4,21,21,'2 lần/ngày','2019-02-10',10,5000);
insert into ChiTietDonThuoc values(49,19,21,21,'2 lần / ngày','2019-02-20',10,5000);
insert into ChiTietDonThuoc values(50,5,22,22,'2 lần / ngày','2019-02-20',10,5000);
insert into ChiTietDonThuoc values(51,18,22,22,' 2 lần / ngày','2019-03-09',10,5000);
insert into ChiTietDonThuoc values(52,1,23,23,'2 lần / ngày','2019-03-22',10,5000);
insert into ChiTietDonThuoc values(53,3,23,23,'2 lần/ ngày','2019-03-22',10,5000);
insert into ChiTietDonThuoc values(54,6,23,23,' 2 lần / ngày','2019-04-05',10,5000);
insert into ChiTietDonThuoc values(55,22,24,24,'2 lần / ngày','2019-04-05',10,5000);
insert into ChiTietDonThuoc values(56,1,25,25,'2 lần / ngày','2019-05-05',10,5000);
insert into ChiTietDonThuoc values(57,14,25,25,'2 lần / ngày','2019-06-17',10,5000);
insert into ChiTietDonThuoc values(58,22,25,25,'2 lần / ngày','2019-06-17',10,5000);
insert into ChiTietDonThuoc values(59,1,26,26,'2 lần / ngày','2019-07-06',10,5000);
insert into ChiTietDonThuoc values(60,2,26,26,'2 lần / ngày','2019-07-06',10,5000);
insert into ChiTietDonThuoc values(61,3,27,27,'2 lần / ngày','2019-07-06',10,5000);


/*Thêm dữ liệu vào bản tài khoản*/
INSERT INTO `taikhoan` VALUES ('admin','$2a$10$dYDm5L3AgTlvfZ4d8VCgcehEjXKTb57bOLVT0f6L3jCTErUedOOG2','ROLE_ADMIN',4),
							('doctoryen','$2a$10$zM6olU2s/GnXuxYdcl.KleHnksXTlyBN/PCYo5GLfD.yZFWIduDGG','ROLE_DOCTOR',2),
                            ('duocsi','$2a$10$njUFoyUiPYxRkbVa0SVu6OPqTilwPs/oIqS6VcOFFQf5v.70CuQTu','ROLE_PHARMACIST',3),
                            ('nhanvienluan','$2a$10$uGk1Ko39u5peEUtJJOzpxuX/LnNSQM1cvr9doE1kwPFa1OJEbnLhy','ROLE_EMPLOYEE',1);

SELECT  concat(month(ngaythem), '-', year(ngaythem)) as name, SUM(thanhtien) as value
FROM HoaDon
where ngaythem > DATE_SUB( LAST_DAY(NOW()), INTERVAL 12 MONTH)
group by  month(ngaythem), year(ngaythem)
order by ngaythem;


update  benhnhan set ngaythem = '2018--12' where mabenhnhan  IN(12,13,14);

select * from benhnhan;

DELIMITER $$
CREATE PROCEDURE changeDateDangki(
 IN madk int)
BEGIN
Declare ngaydk date;
Declare mapk int default 0; 
declare mahd int default 0;

set ngaydk = date_format(
    from_unixtime(
         rand() * 
            (unix_timestamp(now()) - unix_timestamp('2017-11-13')) + 
             unix_timestamp('2017-11-13 23:00:00')
                  ), '%Y-%m-%d %H:%i:%s') 
;
 
  /*Thay đổi ngày đăng kí khám bệnh*/
  update dangkikhambenh set NGAYDANGKI = ngaydk where madangki = madk;
  
 /*Thay đổi ngày phiếu khám bệnh*/
 update phieukhambenh set ngaythem = ngaydk where madangki = madk;
 select maphieukhambenh into mapk from phieukhambenh where madangki = madk;
 
 /*Thay đổi ngày chi tiết đơn thuốc*/
 update chitietdonthuoc set ngaythem = ngaydk where maphieukhambenh = mapk;
 select distinct mahoadon into mahd from chitietdonthuoc where maphieukhambenh = mapk;
 
 /*Thay đổi ngày hóa đơn*/
update hoadon set ngaythem = ngaydk where mahoadon = mahd;
END$$
DELIMITER ;


/*Hàm tạo dữ liêu theo tháng*/
DELIMITER $$
CREATE PROCEDURE changeDateDangkithang(
 IN madk int,
 IN ngaydk date)
BEGIN
Declare mapk int default 0; 
declare mahd int default 0;

 
  /*Thay đổi ngày đăng kí khám bệnh*/
  update dangkikhambenh set NGAYDANGKI = ngaydk where madangki = madk;
  
 /*Thay đổi ngày phiếu khám bệnh*/
 update phieukhambenh set ngaythem = ngaydk where madangki = madk;
 select maphieukhambenh into mapk from phieukhambenh where madangki = madk;
 
 /*Thay đổi ngày chi tiết đơn thuốc*/
 update chitietdonthuoc set ngaythem = ngaydk where maphieukhambenh = mapk;
 select distinct mahoadon into mahd from chitietdonthuoc where maphieukhambenh = mapk;
 
 /*Thay đổi ngày hóa đơn*/
update hoadon set ngaythem = ngaydk where mahoadon = mahd;
END$$
DELIMITER ;

select concat('Quý ', quarter(ngaythem)) as quy ,GIOITINH as gioiTinh, count(MABENHNHAN) as giaTri from benhnhan 
where year(ngaythem) = year(now())
group by quy, gioitinh
order by quy;

select * from benhnhan;
create database quanlyphongkham;
drop database quanlyphongkham;


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
DROP TABLE benhnhan;
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
DROP TABLE DANGKIKHAMBENH;
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
DROP TABLE PHIEUKHAMBENH;
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
DROP TABLE THUOC;
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
drop table hoadon;
DROP TABLE HOADON;
create table HOADON
(
   MAHOADON             int    AUTO_INCREMENT                    not null,
   MABENHNHAN			int 						not null,
   THANHTIEN            int                        not null,
   TINHTRANG			varchar(20)				   not null,
   NGAYTHEM             date                           null,
   constraint PK_HOADON primary key (MAHOADON),
   constraint FK_HOADON_CUA_BENHNHAN foreign key (MABENHNHAN)
      references BENHNHAN (MABENHNHAN)



	ON DELETE NO ACTION ON UPDATE NO ACTION
);

/*==============================================================*/
/* Table: CHITIETDONTHUOC                                       */
/*==============================================================*/
DROP TABLE chitietdonthuoc;
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
DROP TRIGGER before_chitietdonthuoc_insert;
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


/*trigger insert thành tiền chi tiết đơn thuốc và hóa đơn */
DROP TRIGGER before_chitietdonthuoc_update;
DELIMITER $$
CREATE TRIGGER before_chitietdonthuoc_update
    BEFORE UPDATE ON chitietdonthuoc
    FOR EACH ROW
BEGIN
   declare v_giathuoc int;
   select gia into v_giathuoc
   from thuoc
   where mathuoc = new.mathuoc;
   set new.thanhtien = v_giathuoc * new.soluong;

   update hoadon
   set thanhtien = thanhtien - old.thanhtien + new.thanhtien
   where mahoadon = new.mahoadon;
END$$
DELIMITER ;






/*Update lại tí bảng chi tiết hóa đơn  logic hóa đơn tính tiền của phiếu khám bệnh có các chi tiết đơn thuốc*/


/*Insert dữ liệu bảng nhân viên */
Insert into NHANVIEN values(1,'Nguyễn Thành Luâ', 'Nam', '1995-12-15', '731 Trần Hưng Đạo, Q5, TpHCM', 'Nhân viê','2016-08-20');
Insert into NHANVIEN values(2,'Lê Thị Phi Yế', 'Nữ', '1987-08-11','45 Nguyễn Hữu Cảnh, Q1, TpHCM', 'Bác sĩ','2013-05-21');
Insert into NHANVIEN values(3,'Trần Minh Long', 'Nam', '1977-07-02','50/34 Lê Đại Hành, Q10, TpHCM', 'Dược sĩ','2017-03-21');
Insert into NHANVIEN values(4,'Ngô Thanh Tuấ', 'Nam', '1972-09-03','34 Trương Định, Q3, TpHCM', 'Quản lý','2010-10-06');
Insert into NHANVIEN values(5,'Lê Hoài Thương', 'Nữ', '1985-04-12','227 Nguyễn Văn Cừ, Q5, TpHCM', 'Bác sĩ','2015-05-16');
Insert into NHANVIEN values(6,'Nguyễn Văn Tâm', 'Nam', '1975-12-05','32/3 Trần Bình Trọng, Q5, TpHCM', 'Bác sĩ','2014-12-26');
Insert into NHANVIEN values(7,'Phan Thị Thanh', 'Nữ', '1991-06-10','45/2 An Duong Vuong, Q5, TpHCM', 'Nhân viên','2016-11-12');


/*Insert dữ liệu bảng bệnh nhân*/
Insert into BENHNHAN values(1,'Hồ Thị Cẩm Hoài', 'Nữ', '2005-12-15', '873 Le Hong Phong, Q5, TpHCM', 'Học sinh', '0903655711', '2017-12-22');
Insert into BENHNHAN values(2,'Lê Tấn Hoàng', 'Nam', '1973-11-05', 'Khu phố 6, Linh Trung, Thủ Đức, TpHCM', 'Lập trình viê', '0735425911', '2018-11-02');
Insert into BENHNHAN values(3,'Bùi Trần Nhật Lệ', 'Nữ', '1981-11-09', '4/34B Nguyen Trai, Q1, TpHCM', 'Giáo viê', '0735711197', '2018-01-21');
Insert into BENHNHAN values(4,'Cao Thành Nghĩa', 'Nam', '1960-11-06', '126 Le Hong Phong, Q5, TpHCM', 'Cán bộ về hưu', '0711197198', '2016-10-03');
Insert into BENHNHAN values(5,'Lê Tú Anh', 'Nữ', '1988-11-01', '83 Nguyễn Văn Cừ, Q5, TpHCM', 'Nhân viên văn phòng', '0977425671', '2019-10-02');
Insert into BENHNHAN values(6,'Bùi Tiến A', 'Nữ', '2001-11-10', '16 Lý Thường Kiệt, Q10, TpHCM', 'Học sinh', '0705423213', '2018-12-22');
Insert into BENHNHAN values(7,'Mai Thị Lệ Hằng', 'Nữ', '2009-01-12', '23 Le Hong Phong, Q5, TpHCM', 'Học sinh', '0903682197', '2019-05-21');

/*Insert dữ liệu bảng đăng kí khám bệnh*/

insert into DANGKIKHAMBENH values(1,1,'2019-05-15','Đang chờ');
insert into DANGKIKHAMBENH values(2,1,'2019-05-12','Đã khám');
insert into DANGKIKHAMBENH values(3,2,'2019-05-12','Đã khám');
insert into DANGKIKHAMBENH values(4,3,'2019-05-15','Đang chờ');
insert into DANGKIKHAMBENH values(5,5,'2019-05-15','Đang chờ');
insert into DANGKIKHAMBENH values(6,6,'2019-05-14','Đã khám');
insert into DANGKIKHAMBENH values(7,7,'2019-05-11','Đã khám');
insert into DANGKIKHAMBENH values(8,3,'2019-05-14','Đã khám');

/*Insert dữ liệu bảng phiếu khám bệnh*/

insert into PHIEUKHAMBENH values(1,2,'Nhứt đầu, chóng mặt, buồn nôn', 'Ngộ độc thực phẩm nhẹ','2019-05-12', 2);
insert into PHIEUKHAMBENH values(2,3,'Nghẹt mũi, sổ mũi, chóng mặt, đau đầu', 'Cảm cúm','2019-12-05', 5);
insert into PHIEUKHAMBENH values(3,6,'Ho có đàm, khó thở, mệt mõi', 'Viêm phế quả','2019-05-14', 6);
insert into PHIEUKHAMBENH values(4,7,'Đau đầu âm ỉ, chóng mặt, mệt mõi', 'Rối loạn tiền đình','2019-05-11', 2);
insert into PHIEUKHAMBENH values(5,8,'Nhứt đầu, chóng mặt, buồn nôn', 'Ngộ độc thực phẩm nhẹ','2019-05-15', 6);

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
insert into HOADON(MAHOADON, MABENHNHAN, THANHTIEN, NGAYTHEM, TINHTRANG) values(1,1,300000,'2019-05-12', 'Đã thanh toán');
insert into HOADON(MAHOADON, MABENHNHAN, THANHTIEN, NGAYTHEM, TINHTRANG) values(2,2,300000,'2019-05-12', 'Đã thanh toán');
insert into HOADON(MAHOADON, MABENHNHAN, THANHTIEN, NGAYTHEM, TINHTRANG) values(3,6,300000,'2019-05-14', 'Đã thanh toán');
insert into HOADON(MAHOADON, MABENHNHAN, THANHTIEN, NGAYTHEM, TINHTRANG) values(4,7,300000,'2019-05-11', 'Đã thanh toán');
insert into HOADON(MAHOADON, MABENHNHAN, THANHTIEN, NGAYTHEM, TINHTRANG) values(5,3,300000,'2019-05-12', 'Đã thanh toán');


/*Insert dữ liệu bảng chi tiết hóa đơn*/
insert into CHITIETDONTHUOC values(1,1,1,1, 'Uống 2 viên/ngày', '2019-05-12', 10,5000);
insert into CHITIETDONTHUOC values(2,2,1,1, 'Uống 2 viên/ngày', '2019-05-12', 10,5000);

insert into CHITIETDONTHUOC values(3,3,2,2, 'Uống 2 ống/ngày', '2019-05-12', 10,5000);
insert into CHITIETDONTHUOC values(4,4,2,2, 'Uống 2 bịch/ngày', '2019-05-12', 10,5000);


insert into CHITIETDONTHUOC values(5,5,3,3, 'Uống 2 viên/ngày', '2019-05-14', 10,5000);
insert into CHITIETDONTHUOC values(6,6,3,3, 'Uống 2 ống/ngày', '2019-05-14', 10,5000);


insert into CHITIETDONTHUOC values(7,3,4,4, 'Uống 2 ống/ngày', '2019-05-11', 10,5000);
insert into CHITIETDONTHUOC values(8,4,4,4, 'Uống 2 bịch/ngày', '2019-05-11', 10,5000);

insert into CHITIETDONTHUOC values(9,5,5,5, 'Dùng 2 lần/ngày', '2019-05-12', 10,5000);
insert into CHITIETDONTHUOC values(10,6,5,5, 'Dùng 2 lần/ngày', '2019-05-12', 10,5000);


select concat('Tháng ', month(ngaythem)) as name, sum(ThanhTien) as value
from HoaDon
where year(ngaythem) = 2019
group by name
order by month(ngaythem);

select GioiTinh as name, COUNT(MaBenhNhan) as value
from BenhNhan
group by name;

select ChucVu as name, COUNT(MANHANVIEN) as value
from NhanVien
group by name;


select * from phieukhambenh;
delete from phieukhambenh where maphieukhambenh =9;

delete  from chitietdonthuoc where maphieukhambenh = 9;

delete from hoadon where mahoadon = 6;

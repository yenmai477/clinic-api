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

create table BENHNHAN 
(
   MABENHNHAN int not null,
   TENBENHNHAN varchar(50) not null,
   GIOITINH varchar(5) not null,
   NGAYSINH date not null,
   DIACHI varchar(100) not null,
   NGHENGHIEP varchar(50) null,
   SODIENTHOAI varchar(20) null,
   NGAYTHEM date null,
   constraint PK_BENHNHAN primary key (MABENHNHAN)
);


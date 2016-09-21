DROP TABLE IF EXISTS dm_loai_phieu;
CREATE TABLE  dm_loai_phieu (
  `DMLOAIPHIEU_MASO` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `DMLOAIPHIEU_MA` varchar(4) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `DMLOAIPHIEU_TEN` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `DMLOAIPHIEU_DVT` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `DMLOAITHUOC_MASO` int(10) unsigned NOT NULL,
  `DMLOAIPHIEU_NGAYGIOCN` datetime DEFAULT NULL,
  PRIMARY KEY (`DMLOAIPHIEU_MASO`),
  UNIQUE KEY `DMLOAIPHIEU_MA` (`DMLOAIPHIEU_MA`),
  KEY `FK_DM_LOAI_PHIEU_DMLOAITHUOC_MASO` (`DMLOAITHUOC_MASO`),
  CONSTRAINT `FK_DM_LOAI_PHIEU_DMLOAITHUOC_MASO` FOREIGN KEY (`DMLOAITHUOC_MASO`) REFERENCES dm_loai_thuoc (`DMLOAITHUOC_MASO`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

insert into dm_loai_phieu values(1,'TT','Thuốc thường',null,3,'2011-08-25');
insert into dm_loai_phieu values(2,'TV','Thuốc thường- Thuốc viên','IN (35, 36, 37, 44)',3,'2011-08-25');
insert into dm_loai_phieu values(3,'TK','Thuốc thường- Thuốc khác','NOT IN (35, 36, 37, 44)',3,'2011-08-25');
insert into dm_loai_phieu values(4,'HT','Hướng thần',null,3,'2011-08-25');
insert into dm_loai_phieu values(5,'GN','Gây nghiện',null,3,'2011-08-25');
insert into dm_loai_phieu values(6,'YC','Y dụng cụ',null,4,'2011-08-25');
insert into dm_loai_phieu values(7,'HC','Hóa chất',null,2,'2011-08-25');
insert into dm_loai_phieu values(8,'DY','Đông y',null,1,'2011-08-25');
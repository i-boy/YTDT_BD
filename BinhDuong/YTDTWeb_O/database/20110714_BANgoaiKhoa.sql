CREATE TABLE `hsba_chi_tiet_ngoai` (
  `HSBACTNGOAI_MA` int(11) NOT NULL auto_increment,
  `HSBACM_MA` int(11) NOT NULL,
  `HSBACTNGOAI_QTBENHLY` varchar(512) character set utf8 collate utf8_unicode_ci default NULL,
  `HSBACTNGOAI_TIENSUBENHBT` varchar(512) character set utf8 collate utf8_unicode_ci default NULL,
  `HSBACTNGOAI_TIENSUBENHGD` varchar(512) character set utf8 collate utf8_unicode_ci default NULL,
  `HSBACTNGOAI_DD_DIUNG` tinyint(1) default NULL,
  `HSBACTNGOAI_DD_MATUY` tinyint(1) default NULL,
  `HSBACTNGOAI_DD_RUOUBIA` tinyint(1) default NULL,
  `HSBACTNGOAI_DD_THUOCLA` tinyint(1) default NULL,
  `HSBACTNGOAI_DD_THUOCLAO` tinyint(1) default NULL,
  `HSBACTNGOAI_DD_KHAC` tinyint(1) default NULL,
  `HSBACTNGOAI_DD_DIUNG_TG` varchar(32) character set utf8 collate utf8_unicode_ci default NULL,
  `HSBACTNGOAI_DD_MATUY_TG` varchar(32) character set utf8 collate utf8_unicode_ci default NULL,
  `HSBACTNGOAI_DD_RUOUBIA_TG` varchar(32) character set utf8 collate utf8_unicode_ci default NULL,
  `HSBACTNGOAI_DD_THUOCLA_TG` varchar(32) character set utf8 collate utf8_unicode_ci default NULL,
  `HSBACTNGOAI_DD_THUOCLAO_TG` varchar(32) character set utf8 collate utf8_unicode_ci default NULL,
  `HSBACTNGOAI_DD_KHAC_TG` varchar(32) character set utf8 collate utf8_unicode_ci default NULL,
  `HSBACTNGOAI_TUANHOAN` varchar(512) character set utf8 collate utf8_unicode_ci default NULL,
  `HSBACTNGOAI_HOHAP` varchar(512) character set utf8 collate utf8_unicode_ci default NULL,
  `HSBACTNGOAI_TIEUHOA` varchar(512) character set utf8 collate utf8_unicode_ci default NULL,
  `HSBACTNGOAI_THANTIETNIEUSINHHOC` varchar(512) character set utf8 collate utf8_unicode_ci default NULL,
  `HSBACTNGOAI_THANKINH` varchar(512) character set utf8 collate utf8_unicode_ci default NULL,
  `HSBACTNGOAI_COXUONGKHOP` varchar(512) character set utf8 collate utf8_unicode_ci default NULL,
  `HSBACTNGOAI_TMH` varchar(512) character set utf8 collate utf8_unicode_ci default NULL,
  `HSBACTNGOAI_RHM` varchar(512) character set utf8 collate utf8_unicode_ci default NULL,
  `HSBACTNGOAI_MAT` varchar(512) character set utf8 collate utf8_unicode_ci default NULL,
  `HSBACTNGOAI_NT_DD_BLK` varchar(512) character set utf8 collate utf8_unicode_ci default NULL,
  `HSBACTNGOAI_TTBA` varchar(512) character set utf8 collate utf8_unicode_ci default NULL,
  `HSBACTNGOAI_PB` int(10) unsigned default NULL,
  `HSBACTNGOAI_TIENLUONG` int(10) unsigned default NULL,
  `HSBACTNGOAI_LYDOVAOV` varchar(512) default NULL,
  `HSBACTNGOAI_NGAYBENHTHU` int(10) unsigned default NULL,
  `HSBACTNGOAI_TOANTHAN` varchar(512) default NULL,
  `HSBACTNGOAI_QTBL_DBLS` varchar(512) default NULL,
  `HSBACTNGOAI_TTKQXNCLS` varchar(512) default NULL,
  `HSBACTNGOAI_PPDIEUTRI` varchar(512) default NULL,
  `HSBACTNGOAI_TTNGUOIBENHRAV` varchar(512) default NULL,
  `HSBACTNGOAI_HUONGDT_CDTT` varchar(512) default NULL,
  `HSBACTNGOAI_SOTOXQUANG` int(10) unsigned default NULL,
  `HSBACTNGOAI_SOTOCTSCANNER` int(10) unsigned default NULL,
  `HSBACTNGOAI_SOTOSIEUAM` int(10) unsigned default NULL,
  `HSBACTNGOAI_SOTOXN` int(10) unsigned default NULL,
  `HSBACTNGOAI_SOTOKHAC` int(10) unsigned default NULL,
  `HSBACTNGOAI_SOTOLOAIKHAC` varchar(512) default NULL,
  `HSBACTNGOAI_BENHNGOAIKHOA` varchar(512) default NULL,
  `HSBACTNGOAI_TONGSOTO` int(10) unsigned default NULL,
  `HSBACTNGOAI_BSLAMBA` int(10) unsigned default NULL,
  `HSBACTNGOAI_NGUOIGIAOBA` int(10) unsigned default NULL,
  `HSBACTNGOAI_NGUOINHANBA` int(10) unsigned default NULL,
  `HSBACTNGOAI_BSDIEUTRI` int(10) unsigned default NULL,
  PRIMARY KEY  (`HSBACTNGOAI_MA`),
  KEY `FK_HSBA_CHI_TIET_NGOAI_1` (`HSBACM_MA`),
  KEY `FK_HSBA_CHI_TIET_NGOAI_2` (`HSBACTNGOAI_TIENLUONG`),
  KEY `FK_HSBA_CHI_TIET_NGOAI_3` (`HSBACTNGOAI_BSLAMBA`),
  KEY `FK_HSBA_CHI_TIET_NGOAI_4` (`HSBACTNGOAI_NGUOIGIAOBA`),
  KEY `FK_HSBA_CHI_TIET_NGOAI_5` (`HSBACTNGOAI_NGUOINHANBA`),
  KEY `FK_HSBA_CHI_TIET_NGOAI_6` (`HSBACTNGOAI_BSDIEUTRI`),
  CONSTRAINT `FK_HSBA_CHI_TIET_NGOAI_1` FOREIGN KEY (`HSBACM_MA`) REFERENCES `hsba_chuyen_mon` (`HSBACM_MA`) ON DELETE CASCADE,
  CONSTRAINT `FK_HSBA_CHI_TIET_NGOAI_2` FOREIGN KEY (`HSBACTNGOAI_TIENLUONG`) REFERENCES `dm_thuoc` (`DMTHUOC_MASO`),
  CONSTRAINT `FK_HSBA_CHI_TIET_NGOAI_3` FOREIGN KEY (`HSBACTNGOAI_BSLAMBA`) REFERENCES `dt_dm_nhan_vien` (`DTDMNHANVIEN_MASO`),
  CONSTRAINT `FK_HSBA_CHI_TIET_NGOAI_4` FOREIGN KEY (`HSBACTNGOAI_NGUOIGIAOBA`) REFERENCES `dt_dm_nhan_vien` (`DTDMNHANVIEN_MASO`),
  CONSTRAINT `FK_HSBA_CHI_TIET_NGOAI_5` FOREIGN KEY (`HSBACTNGOAI_NGUOINHANBA`) REFERENCES `dt_dm_nhan_vien` (`DTDMNHANVIEN_MASO`),
  CONSTRAINT `FK_HSBA_CHI_TIET_NGOAI_6` FOREIGN KEY (`HSBACTNGOAI_BSDIEUTRI`) REFERENCES `dt_dm_nhan_vien` (`DTDMNHANVIEN_MASO`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='';


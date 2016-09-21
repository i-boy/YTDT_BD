#Cập nhật luôn dm_dieu_tri trong gear để đồng bộ
INSERT INTO db_ytdt_bd.dm_dieu_tri
(DMDIEUTRI_MA, DMDIEUTRI_TEN, DMDIEUTRI_DIENGIAI2, DMDIEUTRI_DEFA, DMDIEUTRI_NGAYGIOCN, DMDIEUTRI_QL, DMDIEUTRI_DT, DMDIEUTRI_DP, DMDIEUTRI_DIENGIAI1) 
VALUES ('T', 'Tử vong trước khi vào viện', 'TV.Trước', null, 1, null, 1, null, null), ('D', 'Tử vong', 'TV', null, 1, null, 1, null, null);

CREATE TABLE `tuvong_truoc` (
  `TVT_MA` varchar(11) character set utf8 collate utf8_unicode_ci NOT NULL,
  `TVT_NGAYGIO` datetime default NULL,
  `TVT_BENHNHAN` varchar(17) character set utf8 collate utf8_unicode_ci default NULL,
  `TVT_DONVIGOI` int(10) unsigned default NULL,
  `TVT_BACSI_TRUC` int(10) unsigned default NULL,
  `TVT_BACSI_NVTIEP` int(10) unsigned default NULL,
  `TVT_NHANVIENCN` int(10) unsigned default NULL,
  `TVT_THUNGAN` int(10) unsigned default NULL,
  `DIADIEM_MA` int(10) unsigned default NULL,
  `TVT_LYDOVAOV` varchar(60) character set utf8 collate utf8_unicode_ci default NULL,
  `TVT_TEN_NGUOIDUADEN` varchar(150) character set utf8 collate utf8_unicode_ci default NULL,
  `TVT_TUOI_NGUOIDUADEN` varchar(2) character set utf8 collate utf8_unicode_ci default NULL,
  `TVT_GIOITINH_NGUOIDUADEN` tinyint(1) default NULL,
  `TVT_LHBN_NGUOIDUADEN` varchar(150) character set utf8 collate utf8_unicode_ci default NULL,
  `TVT_BENHSU` varchar(400) character set utf8 collate utf8_unicode_ci default NULL,
  `TVT_PPDT_TRUOC` varchar(400) character set utf8 collate utf8_unicode_ci default NULL,
  `TVT_TUVONGLUC` datetime default NULL,
  `TVT_TIENSU` varchar(400) character set utf8 collate utf8_unicode_ci default NULL,
  `TVT_TINHTRANGCHUNG` varchar(400) character set utf8 collate utf8_unicode_ci default NULL,
  `TVT_TRIGIAC` varchar(30) character set utf8 collate utf8_unicode_ci default NULL,
  `TVT_DANIEM` varchar(30) character set utf8 collate utf8_unicode_ci default NULL,
  `TVT_DONGTU` varchar(30) character set utf8 collate utf8_unicode_ci default NULL,
  `TVT_MACH` varchar(10) character set utf8 collate utf8_unicode_ci default NULL,
  `TVT_HUYETAP` varchar(10) character set utf8 collate utf8_unicode_ci default NULL,
  `TVT_NHIETDO` varchar(10) character set utf8 collate utf8_unicode_ci default NULL,
  `TVT_TIMMACH` varchar(10) character set utf8 collate utf8_unicode_ci default NULL,
  `TVT_HOHAP` varchar(40) character set utf8 collate utf8_unicode_ci default NULL,
  `TVT_THUONGTONBENHLYCHINH` varchar(255) character set utf8 collate utf8_unicode_ci default NULL,
  `TVT_DIENTAMDO` varchar(255) character set utf8 collate utf8_unicode_ci default NULL,
  `TVT_CHANDOANSOBO` varchar(400) character set utf8 collate utf8_unicode_ci default NULL,
  `TVT_CAPCUU` varchar(255) character set utf8 collate utf8_unicode_ci default NULL,
  `TVT_CANTHIEPKHAC` varchar(255) character set utf8 collate utf8_unicode_ci default NULL,
  `TVT_GIUXAC` tinyint(1) default '0',
  `TVT_XINXACKHONGKHIEUNAI` tinyint(1) default '0',
  `TVT_NGUOIXINXAC` varchar(255) character set utf8 collate utf8_unicode_ci default NULL,
  `TVT_NGUOIXINXAC_TUOI` varchar(2) character set utf8 collate utf8_unicode_ci default NULL,
  `TVT_NGUOIXINXAC_GIOITINH` tinyint(1) default NULL,
  `TVT_NGUOIXINXAC_LIENHEBN` varchar(100) character set utf8 collate utf8_unicode_ci default NULL,
  `TVT_TAISANBENHNHAN` tinyint(1) default '0',
  `TVT_COBIENBANTAISAN` tinyint(1) default '0',
  `TVT_THAMKHAM` int(10) unsigned default NULL,
  `TVT_BANKHAM` int(10) unsigned default NULL,
  `TVT_DIACHI_NGUOIDUADEN` varchar(255) character set utf8 collate utf8_unicode_ci default NULL,
  `TVT_PHUONGTIENVANCHUYEN` varchar(125) character set utf8 collate utf8_unicode_ci default NULL,
  `TVT_PHUONGTIENVANCHUYEN_SOXE` varchar(20) default NULL,
  PRIMARY KEY  (`TVT_MA`),
  KEY `FK_TUVONGTRUOC_BACSI_TRUC` (`TVT_BACSI_TRUC`),
  KEY `FK_TUVONGTRUOC_DIADIEM` (`DIADIEM_MA`),
  KEY `FK_TUVONGTRUOC_DONVIGOI` (`TVT_DONVIGOI`),
  KEY `FK_TUVONGTRUOC_NHANVIENCN` (`TVT_NHANVIENCN`),
  KEY `FK_TUVONGTRUOC_NVTIEP` (`TVT_BACSI_NVTIEP`),
  KEY `FK_TUVONGTRUOC_THUNGAN` (`TVT_THUNGAN`),
  KEY `FK_TUVONGTRUOC_BANKHAM` (`TVT_BANKHAM`),
  KEY `FK_TUVONGTRUOC_BENHNHAN` (`TVT_BENHNHAN`),
  KEY `FK_TUVONGTRUOC_THAMKHAM` (`TVT_THAMKHAM`),
  CONSTRAINT `FK_TUVONGTRUOC_BANKHAM` FOREIGN KEY (`TVT_BANKHAM`) REFERENCES `dt_dm_ban_kham` (`DTDMBANKHAM_MASO`),
  CONSTRAINT `FK_TUVONGTRUOC_BENHNHAN` FOREIGN KEY (`TVT_BENHNHAN`) REFERENCES `benh_nhan` (`BENHNHAN_MA`),
  CONSTRAINT `FK_TUVONGTRUOC_THAMKHAM` FOREIGN KEY (`TVT_THAMKHAM`) REFERENCES `tham_kham` (`THAMKHAM_MA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;



INSERT INTO `yte_sequence` (`SEQUENCE_NAME_MA`,`SEQUENCE_CURRENT_VALUE`,`SEQUENCE_CURRENT_NEXT`,`SEQUENCE_STEP`) VALUES 
  ('MAPHIEU_BA_TUVONG_TRUOC',1,2,1);
  
  
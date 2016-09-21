DROP TABLE IF EXISTS `db_ytdt_bd`.`giay_chung_thuong`;
CREATE TABLE  `db_ytdt_bd`.`giay_chung_thuong` (
  `GCT_MA` int(11) NOT NULL auto_increment,
  `GCT_NGAYGIO` datetime default NULL,
  `GCT_THUONGTICHVAOVIEN` varchar(400) character set utf8 collate utf8_unicode_ci default NULL,
  `GCT_THUONGTICHRAVIEN` varchar(400) character set utf8 collate utf8_unicode_ci default NULL,
  `GCT_DIEUTRI` varchar(400) character set utf8 collate utf8_unicode_ci default NULL,
  `GCT_THAMKHAM` int(10) unsigned default NULL,
  PRIMARY KEY  (`GCT_MA`),
  KEY `FK_GIAYCHUNGTHUONG_THAMKHAM` (`GCT_THAMKHAM`),
  CONSTRAINT `FK_GIAYCHUNGTHUONG_THAMKHAM` FOREIGN KEY (`GCT_THAMKHAM`) REFERENCES `tham_kham` (`THAMKHAM_MA`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
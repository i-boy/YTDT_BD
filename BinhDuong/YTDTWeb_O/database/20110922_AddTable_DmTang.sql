CREATE TABLE `dm_tang` (
  `DMTANG_MASO` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `DMTANG_MA` VARCHAR(5) NOT NULL,
  `DMTANG_TEN` VARCHAR(50) NOT NULL,
  `DMKHOA_MASO` INTEGER UNSIGNED NOT NULL,
  `DMTANG_DEFAULT` TINYINT UNSIGNED,
  `DMTANG_NGAYGIOCN` DATETIME,
  `DMTANG_NHANVIENCN` INTEGER UNSIGNED,
  `DMTANG_CHON` TINYINT UNSIGNED,
  PRIMARY KEY (`DMTANG_MASO`),
  CONSTRAINT `FK_dm_tang_dm_khoa` FOREIGN KEY `FK_dm_tang_dm_khoa` (`DMKHOA_MASO`)
    REFERENCES `dm_khoa` (`DMKHOA_MASO`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_dm_tang_dm_nhanvien` FOREIGN KEY `FK_dm_tang_dm_nhanvien` (`DMTANG_NHANVIENCN`)
    REFERENCES `dt_dm_nhan_vien` (`DTDMNHANVIEN_MASO`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
)
ENGINE = InnoDB;
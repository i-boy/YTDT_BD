ALTER TABLE `thuoc_phong_kham` MODIFY COLUMN `THUOCPHONGKHAM_MAPHIEUDT` VARCHAR(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'De biet thuoc nay da lap phieu du tru tu truc chua';

ALTER TABLE `thuoc_phong_kham` ADD CONSTRAINT `FK_THUOC_PHONG_KHAM_THUOCPHONGKHAM_MAPHIEUDT` FOREIGN KEY `FK_THUOC_PHONG_KHAM_THUOCPHONGKHAM_MAPHIEUDT` (`THUOCPHONGKHAM_MAPHIEUDT`)
    REFERENCES `phieu_du_tru` (`PHIEUDT_MA`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;

ALTER TABLE `thuoc_noi_tru` MODIFY COLUMN `THUOCNOITRU_MAPHIEUDT` VARCHAR(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL;

ALTER TABLE `thuoc_noi_tru` ADD CONSTRAINT `FK_THUOC_NOI_TRU_THUOCNOITRU_MAPHIEUDT` FOREIGN KEY `FK_THUOC_NOI_TRU_THUOCNOITRU_MAPHIEUDT` (`THUOCNOITRU_MAPHIEUDT`)
    REFERENCES `phieu_du_tru` (`PHIEUDT_MA`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;

ALTER TABLE `thuoc_noi_tru` MODIFY COLUMN `THUOCNOITRU_MAPHIEUPDTTRA` VARCHAR(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Ma phieu du tru tra';

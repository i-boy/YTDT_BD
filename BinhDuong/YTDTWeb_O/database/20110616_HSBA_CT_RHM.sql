ALTER TABLE `db_ytdt_bd`.`hsba_chi_tiet_rhm` MODIFY COLUMN `HSBACTRHM_TIENLUONG` VARCHAR(255) DEFAULT NULL,
DROP INDEX `FK_hsba_chi_tiet_rhm_6`,
DROP FOREIGN KEY `FK_hsba_chi_tiet_rhm_6`;

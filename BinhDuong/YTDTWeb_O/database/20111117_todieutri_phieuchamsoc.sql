﻿ALTER TABLE `db_ytdt_bd`.`to_dieu_tri` MODIFY COLUMN `TODIEUTRI_THEODOIDIENBIEN` VARCHAR(1024) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
 MODIFY COLUMN `TODIEUTRI_THUCHIENCHAMSOC` VARCHAR(2048) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL;

 ALTER TABLE `db_ytdt_bd`.`phieu_cham_soc` MODIFY COLUMN `PHIEUCHAMSOC_THEODOIDIENBIEN` VARCHAR(1024) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
 MODIFY COLUMN `PHIEUCHAMSOC_THUCHIENCHAMSOC` VARCHAR(1024) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL;
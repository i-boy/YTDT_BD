﻿ALTER TABLE `phieu_du_tru` ADD COLUMN `DMTANG_MASO` INTEGER UNSIGNED AFTER `PHIEUDT_DAXUAT`,
 ADD CONSTRAINT `FK_PHIEU_DU_TRU_DMTANG_MASO` FOREIGN KEY `FK_PHIEU_DU_TRU_DMTANG_MASO` (`DMTANG_MASO`)
    REFERENCES `dm_tang` (`DMTANG_MASO`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;

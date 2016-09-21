DROP VIEW IF EXISTS `db_ytdt_bd`.`v_thongtinan`;
CREATE OR REPLACE VIEW `v_thongtinan` 
AS 
select `bnpba`.`BNPBA_MASO` AS `BNPBA_MASO`,
       `bnpba`.`HSBA_SOVAOVIEN` AS `HSBA_SOVAOVIEN`,
	   `pba`.`PHIEUBAOAN_NGAYAN` AS `NGAYAN`,
	   `v_lanan`.`LANAN` AS `LANAN`,
	   (ifnull(`dmdt`.`DTDMDT_MA`,0) + ifnull(`dmma`.`DTDMMA_MA`,0)) AS `MUCAN`,
       `pba`.`KHOA_MASO` AS KHOA_MASO
from `benh_nhan_phieu_bao_an` `bnpba`
     left join `phieu_bao_an` `pba` on `bnpba`.`PHIEUBAOAN_MASO` = `pba`.`PHIEUBAOAN_MASO`
		 left join `dt_dm_dong_them` `dmdt` on `bnpba`.`DTDMDT_MASO` = `dmdt`.`DTDMDT_MASO`
		 left join `dt_dm_muc_an` `dmma` on `bnpba`.`DTDMMA_MASO` = `dmma`.`DTDMMA_MASO`
		 left join `v_count_lanan` `v_lanan` on `bnpba`.`BNPBA_MASO` = `v_lanan`.`BNPBA_MASO`;
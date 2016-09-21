alter table `db_ytdt_bd`.`cls_ket_qua` 
   add column `CLSMO_MASO` int(11) NULL after `TimeIN`;
alter table `db_ytdt_bd`.`cls_mo` 
   add column `CLSMO_TH` tinyint(1) NULL after `CLSMO_THANHTIEN`;

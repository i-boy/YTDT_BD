ALTER TABLE db_ytdt_bd.hsba_phieu_phau_thuat_thu_thuat
 DROP FOREIGN KEY FK_HSBA_PHIEU_PHAU_THUAT_THU_THUAT_PHUONGPHAP;
ALTER TABLE db_ytdt_bd.hsba_phieu_phau_thuat_thu_thuat
 ADD CONSTRAINT FK_HSBA_PHIEU_PHAU_THUAT_THU_THUAT_PHUONGPHAP FOREIGN KEY (HSBAPPTTT_PHUONGPHAP) REFERENCES db_ytdt_bd.dt_dm_cls_bang_gia (DTDMCLSBG_MASO) ON UPDATE RESTRICT ON DELETE RESTRICT;

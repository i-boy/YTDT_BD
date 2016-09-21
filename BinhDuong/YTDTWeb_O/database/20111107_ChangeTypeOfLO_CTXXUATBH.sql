ALTER TABLE `ct_xuat_bh` MODIFY COLUMN `CTXUATBH_LO` VARCHAR(512) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL;

update ct_xuat_bh ct, phieu_nhap_kho nk, ct_nhap_kho ctnk
set ct.ctxuatbh_lo = ctnk.ctnhapkho_lo
where nk.phieunhapkho_ma = ctnk.phieunhapkho_ma and ct.ctxuatbh_malk = ctnk.ctnhapkho_malk;
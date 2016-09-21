-- Loại bỏ 2 space cho dt_dm_nhan_vien
UPDATE dt_dm_nhan_vien SET `DTDMNHANVIEN_TEN` = REPLACE(`DTDMNHANVIEN_TEN`,'  ',' ');
UPDATE dt_dm_nhan_vien SET `DTDMNHANVIEN_TEN` = TRIM(`DTDMNHANVIEN_TEN`);

-- Loại bỏ 2 space cho dm_thuoc
UPDATE dm_thuoc SET `DMTHUOC_TEN` = REPLACE(`DMTHUOC_TEN`,'  ',' ');
UPDATE dm_thuoc SET `DMTHUOC_TEN` = TRIM(`DMTHUOC_TEN`);

-- Loại bỏ 2 space cho dm_khoa
UPDATE dm_khoa SET `DMKHOA_TEN` = REPLACE(`DMKHOA_TEN`,'  ',' ');
UPDATE dm_khoa SET `DMKHOA_TEN` = TRIM(`DMKHOA_TEN`);


-- Upper Mã Nhân viên
UPDATE dt_dm_nhan_vien SET `DTDMNHANVIEN_MA` = UPPER(`DTDMNHANVIEN_MA`);
UPDATE nguoi_dung SET `ND_TENDANGNHAP` = UPPER(`ND_TENDANGNHAP`);

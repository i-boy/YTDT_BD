DROP VIEW IF EXISTS `v_benhnhan_nt_datt_detail`;
CREATE VIEW `v_benhnhan_nt_datt_detail` AS
SELECT A.HSBA_SOVAOVIEN benhan, G.BENHNHAN_HOTEN ho_ten, G.BENHNHAN_NAMSINH nam_sinh,
(select DMGT_MA from dm_gioi_tinh where DMGT_MASO = G.DMGT_MASO) gioi_tinh,
E.HSBABHYT_SOTHEBH mathe, (select DMTINH_MABHYT from dm_tinh where DMTINH_MASO = E.HSBABHYT_TINHBH) tinh_kcb,
(select RIGHT(DMBENHVIEN_MA,3) from dm_benh_vien where DMBENHVIEN_MASO = E.HSBABHYT_MAKCB) ma_kcb,
(select DMBENHICD_MA from dm_benh_icd where DMBENHICD_MASO = D.HSBACM_BENHCHINH) ma_benh,
A.HSBA_NGAYGIOVAOV ngay_vao, A.HSBA_NGAYGIORAV ngay_ra, (select DMBENHICD_TEN from dm_benh_icd where DMBENHICD_MASO = D.HSBACM_BENHCHINH) ten_benh,
(select DMBENHVIEN_MA  from dm_benh_vien where DMBENHVIEN_MASO = E.HSBABHYT_MAKCB) noi_kham, B.HSTHTOAN_NGAYTT ngay_tt,
month(B.HSTHTOAN_NGAYTT) thangqt, year(B.HSTHTOAN_NGAYTT) namqt, E.HSBABHYT_GIATRI0 gtri_tu, E.HSBABHYT_GIATRI1 gtri_den, G.BENHNHAN_DIACHI dia_chi,
'NOI' loaikcb, 'CSKCB' noi_ttoan, B.HSTHTOAN_MA sophieu, A.HSBA_KHOARAV ma_khoa, F.DTDMKHOIBHYT_MA ma_dt,
(select DTDMMQLBHYT_VANCHUYEN from dt_dm_mql_bhyt where DTDMMQLBHYT_MASO = substring(E.HSBABHYT_SOTHEBH,3,1)) vanchuyen,
substring(E.HSBABHYT_SOTHEBH,3,1) loai_tt, E.HSBABHYT_MOC1 ngay_g1, E.HSBABHYT_MOC2 ngay_g2, E.HSBABHYT_MOC3 ngay_g3,
E.HSBABHYT_TUYEN dieu_tri, coalesce(E.HSBABHYT_CO_GIAY_CHUYEN_VIEN,0) GIAY_CV,
(select DMBENHVIEN_TEN from dm_benh_vien where DMBENHVIEN_MASO = A.HSBA_DONVIGOI) noi_den,
(select DMTINH_MABHYT from dm_tinh where DMTINH_MASO = E.HSBABHYT_TINHBH) ma_bhxh,
(select DMBENHICD_TEN from dm_benh_icd where DMBENHICD_MASO = D.HSBACM_BENHPHU) BENH_PHU
FROM hsba A INNER JOIN hs_thtoan B ON A.HSBA_SOVAOVIEN = B.HSBA_SOVAOVIEN
INNER JOIN hsba_khoa C ON A.HSBA_SOVAOVIEN = C.HSBA_SOVAOVIEN
INNER JOIN hsba_chuyen_mon D ON A.HSBA_SOVAOVIEN = D.HSBA_SOVAOVIEN AND C.KHOA_MA = D.KHOA_MA
LEFT JOIN hsba_bhyt E ON A.HSBA_SOVAOVIEN = E.HSBA_SOVAOVIEN
LEFT JOIN dt_dm_khoi_bhyt F ON E.HSBABHYT_KHOIBH = F.DTDMKHOIBHYT_MASO
INNER JOIN benh_nhan G ON A.BENHNHAN_MA = G.BENHNHAN_MA
WHERE A.DOITUONG_MA = 2
AND A.HSBA_DACHUYENBHYT = 0
GROUP BY A.HSBA_SOVAOVIEN
ORDER BY A.HSBA_SOVAOVIEN;
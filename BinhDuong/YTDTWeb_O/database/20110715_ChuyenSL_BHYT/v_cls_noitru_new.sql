DROP VIEW IF EXISTS `v_cls_noitru`;
CREATE VIEW `v_cls_noitru` AS
SELECT B.HSBA_SOVAOVIEN, C.KHOA_MA as ma_khoa, A.CLSMO_NGAY, A.CLSMO_MAHANG, D.DTDMCLSBG_MALOAI as stt_nh, D.DTDMCLSBG_MA as ma_hieu,
(SUM(A.CLSMO_LAN) - SUM(coalesce(A.CLSMO_TRA,0))) as sl, A.CLSMO_DONGIA as gia,
coalesce(A.CLSMO_DONGIABH,0) as gia_bhxh, SUM(coalesce(A.CLSMO_DONGIABNTRA,0)) as tien_bn,
((SUM(A.CLSMO_LAN) - SUM(coalesce(A.CLSMO_TRA,0)))* coalesce(A.CLSMO_DONGIA,0)) as tong_tien,
(SUM(A.CLSMO_LAN) - SUM(coalesce(A.CLSMO_TRA,0)))*( A.CLSMO_DONGIA-coalesce(A.CLSMO_DONGIABH,0)) as tien_chenh,
(((SUM(A.CLSMO_LAN) - SUM(coalesce(A.CLSMO_TRA,0)))* coalesce(A.CLSMO_DONGIA,0)) -  SUM(coalesce(A.CLSMO_DONGIABNTRA,0))) as tien_bhxh
FROM cls_mo A, v_benhnhan_nt_datt B, hsba_khoa C, dt_dm_cls_bang_gia D
WHERE
B.HSBA_SOVAOVIEN = C.HSBA_SOVAOVIEN
AND A.HSBAKHOA_MASO = C.HSBAKHOA_MASO
AND A.CLSMO_MAHANG = D.DTDMCLSBG_MASO
group by B.HSBA_SOVAOVIEN, A.CLSMO_MAHANG
order by B.HSBA_SOVAOVIEN asc, C.KHOA_MA, A.CLSMO_NGAY asc;
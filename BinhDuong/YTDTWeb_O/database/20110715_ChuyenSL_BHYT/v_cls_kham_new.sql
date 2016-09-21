DROP VIEW IF EXISTS `v_cls_kham`;
CREATE VIEW `v_cls_kham` AS
SELECT B.TIEPDON_MA, A.CLSKHAM_NGAYGIO, A.CLSKHAM_MAHANG, D.DTDMCLSBG_MALOAI as stt_nh, D.DTDMCLSBG_MA as ma_hieu,
(SUM(A.CLSKHAM_LAN) - coalesce(SUm(A.CLSKHAM_TRA),0)) as sl, A.CLSKHAM_DONGIA as gia,
coalesce(A.CLSKHAM_DONGIABH,0) as gia_bhxh, SUM(coalesce(A.CLSKHAM_DONGIABNTRA,0)) as tien_bn,
((SUM(A.CLSKHAM_LAN) - coalesce(SUM(A.CLSKHAM_TRA),0))* coalesce(A.CLSKHAM_DONGIA,0)) as tong_tien,
(SUM(A.CLSKHAM_LAN) - coalesce(SUM(A.CLSKHAM_TRA),0))*( A.CLSKHAM_DONGIA-coalesce(A.CLSKHAM_DONGIABH,0)) as tien_chenh,
(((SUM(A.CLSKHAM_LAN) - coalesce(SUM(A.CLSKHAM_TRA),0))* coalesce(A.CLSKHAM_DONGIA,0)) - SUM(coalesce(A.CLSKHAM_DONGIABNTRA,0))) as tien_bhxh
FROM cls_kham A, v_benhnhan_ngt_datt B, tham_kham C, dt_dm_cls_bang_gia D
WHERE B.TIEPDON_MA = C.TIEPDON_MA
AND A.CLSKHAM_THAMKHAM = C.THAMKHAM_MA
AND A.CLSKHAM_MAHANG = D.DTDMCLSBG_MASO
group by B.TIEPDON_MA, A.CLSKHAM_MAHANG
order by B.TIEPDON_MA asc;
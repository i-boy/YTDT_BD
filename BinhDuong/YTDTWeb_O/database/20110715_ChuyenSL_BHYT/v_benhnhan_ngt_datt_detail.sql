DROP VIEW IF EXISTS `v_benhnhan_ngt_datt_detail`;
CREATE VIEW `v_benhnhan_ngt_datt_detail` AS 
select A.TIEPDON_MA, B.HSTHTOANK_NGAYGIOTT NGAYGIOTT, C.BENHNHAN_HOTEN ho_ten, C.BENHNHAN_NAMSINH nam_sinh,
(select DMGT_MA from dm_gioi_tinh where (DMGT_MASO = C.DMGT_MASO)) gioi_tinh, A.TIEPDON_SOTHEBH mathe,
D.DMTINH_MABHYT tinh_kcb, right(E.DMBENHVIEN_MA,3) ma_kcb,
B.HSTHTOANK_NGAYGIOTT ngay_vao, B.HSTHTOANK_NGAYGIOTT ngay_ra, 1 ngaydtr,
E.DMBENHVIEN_MA noikcb, month(B.HSTHTOANK_NGAYGIOTT) thangqt, year(B.HSTHTOANK_NGAYGIOTT) namqt, A.TIEPDON_GIATRI1 gtri_tu, A.TIEPDON_GIATRI2 gtri_den, C.BENHNHAN_DIACHI dia_chi,
'NGOAI' loaikcb, 'CSKCB' noi_ttoan, B.HSTHTOANK_MA sophieu,
A.KHOIBHYT_MA ma_dt,substr(A.TIEPDON_SOTHEBH,3,1) loai_tt, (select DTDMMQLBHYT_VANCHUYEN from dt_dm_mql_bhyt where (DTDMMQLBHYT_MASO = substr(A.TIEPDON_SOTHEBH,3,1))) vanchuyen,
A.TIEPDON_MOC1 AS ngay_g1, A.TIEPDON_MOC2 AS ngay_g2, A.TIEPDON_MOC3 AS ngay_g3,
A.TIEPDON_TUYEN AS dieu_tri, A.TIEPDON_CO_GIAY_GIOI_THIEU giay_gt, D.DMTINH_MABHYT AS ma_bhxh,
(select DMBENHVIEN_TEN from dm_benh_vien where (DMBENHVIEN_MASO = A.TIEPDON_DONVIGOI)) AS noi_den,
F.THAMKHAM_BANKHAM ma_khoa, g.DMBENHICD_MA mabenh, g.DMBENHICD_TEN benhkhac, (select DMBENHICD_TEN from dm_benh_icd where (DMBENHICD_MASO = F.BENHICD10_PHU1)) BENH_PHU
from tiep_don A INNER JOIN hs_thtoank B ON A.TIEPDON_MA = B.TIEPDON_MA
INNER JOIN benh_nhan C ON A.BENHNHAN_MA = C.BENHNHAN_MA
INNER JOIN tham_kham F ON f.tiepdon_ma = a.tiepdon_ma
LEFT JOIN dm_tinh D ON A.TINHBHYT_MA = D.DMTINH_MASO
LEFT JOIN dm_benh_vien E ON A.KCBBHYT_MA = E.DMBENHVIEN_MASO
LEFT JOIN dm_benh_icd g ON g.DMBENHICD_MASO = F.BENHICD10
WHERE B.HSTHTOANK_DATT = 1
AND A.DOITUONG_MA = 2
AND A.TIEPDON_BANKHAM is not null
group by A.TIEPDON_MA
order by A.TIEPDON_MA asc;
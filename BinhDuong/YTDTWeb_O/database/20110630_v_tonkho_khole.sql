DROP VIEW IF EXISTS `v_tonkho_khole`;
CREATE VIEW `v_tonkho_khole` AS
 SELECT TK.TONKHO_MA TONKHOMA , TK.TONKHO_MALIENKET MALIENKET , TK.DMKHOA_MASO MAKHO, TK.TONKHO_NGAYGIOCN NGAYTHANG,
		  '' CHUNGTUNXT,
			CTXK.PHIEUXUATKHO_MA PHIEUNHAPKHO, 'N' LOAIPHIEU,
			'Nhập thuốc từ Kho chính' DIENGIAI,
			TK.TONKHO_NHAP SOLUONGNHAP,
			TK.TONKHO_DONGIA * TK.TONKHO_NHAP THANHTIENNHAP,
			TK.TONKHO_XUAT SOLUONGXUAT,
			TK.TONKHO_DONGIA * TONKHO_XUAT THANHTIENXUAT,
			TK.TONKHO_TON SOLUONGTON,
			TK.TONKHO_DONGIA * TK.TONKHO_TON THANHTIENTON
FROM TON_KHO  TK, CT_XUAT_KHO_TMP CTXK, DM_NHA_CUNG_CAP NCC, DM_KHOA DMK
WHERE TK.DMKHOA_MASO = DMK.DMKHOA_MASO
AND (DMK.DMKHOA_MA = 'KBH' or DMK.DMKHOA_MA = 'KTE' or DMK.DMKHOA_MA = 'KNT')
AND TK.TONKHO_NHAP >0
AND TK.TONKHO_MA = CTXK.TONKHO_MA
GROUP BY TK.TONKHO_MA

UNION ALL

SELECT TK.TONKHO_MA TONKHOMA, TK.TONKHO_MALIENKET MALIENKET, TK.DMKHOA_MASO MAKHO, TK.TONKHO_NGAYGIOCN NGAYTHANG,
		   '' CHUNGTUNXT, PXK.PHIEUXUATKHO_MA PHIEUNHAPKHO,
			'X' LOAIPHIEU, (SELECT DMK.DMKHOA_TEN FROM DM_KHOA DMK WHERE PXK.DMKHOA_NHAN = DMK.DMKHOA_MASO) DIENGIAI,
			TK.TONKHO_NHAP SOLUONGNHAP,
			TK.TONKHO_DONGIA * TK.TONKHO_NHAP THANHTIENNHAP,
			TK.TONKHO_XUAT SOLUONGXUAT,
			TK.TONKHO_DONGIA * TK.TONKHO_XUAT THANHTIENXUAT,
			TK.TONKHO_TON SOLUONGTON,
			TK.TONKHO_DONGIA * TK.TONKHO_TON THANHTIENTON
	FROM TON_KHO TK, CT_XUAT_KHO CTXK, PHIEU_XUAT_KHO PXK, DM_KHOA DMK
	WHERE TK.DMKHOA_MASO = DMK.DMKHOA_MASO
  AND (DMK.DMKHOA_MA = 'KBH' or DMK.DMKHOA_MA = 'KTE' or DMK.DMKHOA_MA = 'KNT')
  AND CTXK.PHIEUXUATKHO_MA = PXK.PHIEUXUATKHO_MA
  AND CTXK.TONKHO_MA = TK.TONKHO_MA
	AND TK.TONKHO_XUAT >0

UNION ALL

SELECT TK.TONKHO_MA TONKHOMA, TK.TONKHO_MALIENKET MALIENKET, TK.DMKHOA_MASO MAKHO, TK.TONKHO_NGAYGIOCN NGAYTHANG,
		   '' CHUNGTUNXT, PXBH.TIEPDON_MA PHIEUNHAPKHO,
			'X' LOAIPHIEU, 'Xuất BN' DIENGIAI,
			TK.TONKHO_NHAP SOLUONGNHAP,
			TK.TONKHO_DONGIA * TK.TONKHO_NHAP THANHTIENNHAP,
			TK.TONKHO_XUAT SOLUONGXUAT,
			TK.TONKHO_DONGIA * TK.TONKHO_XUAT THANHTIENXUAT,
			TK.TONKHO_TON SOLUONGTON,
			TK.TONKHO_DONGIA * TK.TONKHO_TON THANHTIENTON
	FROM TON_KHO TK, CT_XUAT_BH CTXBH, PHIEU_XUAT_BH PXBH, DM_KHOA DMK
	WHERE TK.DMKHOA_MASO = DMK.DMKHOA_MASO
  AND (DMK.DMKHOA_MA = 'KBH' or DMK.DMKHOA_MA = 'KTE' or DMK.DMKHOA_MA = 'KNT')
  AND CTXBH.PHIEUXUATBH_MA = PXBH.PHIEUXUATBH_MA
  AND CTXBH.TONKHO_MA = TK.TONKHO_MA
	AND TK.TONKHO_XUAT >0
	
UNION ALL

SELECT TK.TONKHO_MA TONKHOMA, TK.TONKHO_MALIENKET MALIENKET, TK.DMKHOA_MASO MAKHO, TK.TONKHO_NGAYGIOCN NGAYTHANG,
		   '' CHUNGTUNXT, PXBH.HSBA_SOVAOVIEN PHIEUNHAPKHO,
			'X' LOAIPHIEU, 'Xuất BN' DIENGIAI,
			TK.TONKHO_NHAP SOLUONGNHAP,
			TK.TONKHO_DONGIA * TK.TONKHO_NHAP THANHTIENNHAP,
			TK.TONKHO_XUAT SOLUONGXUAT,
			TK.TONKHO_DONGIA * TK.TONKHO_XUAT THANHTIENXUAT,
			TK.TONKHO_TON SOLUONGTON,
			TK.TONKHO_DONGIA * TK.TONKHO_TON THANHTIENTON
	FROM TON_KHO TK, CT_XUAT_BH_XUAT_VIEN CTXBH, PHIEU_XUAT_BH_XUAT_VIEN PXBH, DM_KHOA DMK
	WHERE TK.DMKHOA_MASO = DMK.DMKHOA_MASO
    AND (DMK.DMKHOA_MA = 'KBH' or DMK.DMKHOA_MA = 'KTE' or DMK.DMKHOA_MA = 'KNT')
    AND CTXBH.PHIEUXUATBHXV_MA = PXBH.PHIEUXUATBHXV_MA
    AND CTXBH.TONKHO_MA = TK.TONKHO_MA
	AND TK.TONKHO_XUAT >0

UNION ALL

SELECT TK.TONKHO_MA TONKHOMA , TK.TONKHO_MALIENKET MALIENKET, TK.DMKHOA_MASO MAKHO, TK.TONKHO_NGAYGIOCN NGAYTHANG,
		   '' CHUNGTUNXT, PTK.PHIEUTRAKHO_MA PHIEUNHAPKHO,
			'X' LOAIPHIEU, (SELECT DMK.DMKHOA_TEN FROM DM_KHOA DMK WHERE PTK.DMKHOA_NHAN = DMK.DMKHOA_MASO) DIENGIAI,
			TK.TONKHO_TRA SOLUONGNHAP,
			TK.TONKHO_DONGIA * TK.TONKHO_TRA THANHTIENNHAP,
			TK.TONKHO_XUAT SOLUONGXUAT,
			TK.TONKHO_DONGIA * TK.TONKHO_XUAT THANHTIENXUAT,
			TK.TONKHO_TON SOLUONGTON,
			TK.TONKHO_DONGIA * TK.TONKHO_TON THANHTIENTON
	FROM TON_KHO TK, CT_TRA_KHO_TMP CTTK, PHIEU_TRA_KHO PTK, DM_KHOA DMK
	WHERE TK.DMKHOA_MASO = DMK.DMKHOA_MASO
  AND (DMK.DMKHOA_MA = 'KBH' or DMK.DMKHOA_MA = 'KTE' or DMK.DMKHOA_MA = 'KNT')
  AND PTK.PHIEUTRAKHO_MA = CTTK.PHIEUTRAKHO_MA
	AND CTTK.TONKHO_MA = TK.TONKHO_MA
	AND TK.TONKHO_XUAT >0

UNION ALL

	SELECT CTTK.TONKHO_MA TONKHOMA , CTTK.CTTRAKHO_MALK MALIENKET, PTK.DMKHOA_NHAN MAKHO, PTK.PHIEUTRAKHO_NGAYGIOCN NGAYTHANG,
		   '' CHUNGTUNXT, PTK.PHIEUTRAKHO_MA PHIEUNHAPKHO,
			'T' LOAIPHIEU, (SELECT DMK.DMKHOA_TEN FROM DM_KHOA DMK WHERE PTK.DMKHOA_TRA = DMK.DMKHOA_MASO) DIENGIAI,
			CTTK.CTTRAKHO_SOLUONG SOLUONGNHAP,
			CTTK.CTTRAKHO_DONGIA * CTTK.CTTRAKHO_SOLUONG THANHTIENNHAP,
			0 SOLUONGXUAT,
			0 THANHTIENXUAT,
			0 SOLUONGTON,
			0 THANHTIENTON
	FROM CT_TRA_KHO CTTK, PHIEU_TRA_KHO PTK, DM_KHOA DMK
	WHERE PTK.DMKHOA_NHAN = DMK.DMKHOA_MASO
    AND (DMK.DMKHOA_MA = 'KBH' or DMK.DMKHOA_MA = 'KTE' or DMK.DMKHOA_MA = 'KNT')
    AND PTK.PHIEUTRAKHO_MA = CTTK.PHIEUTRAKHO_MA
	AND PTK.DMKHOA_NHAN = DMK.DMKHOA_MASO;
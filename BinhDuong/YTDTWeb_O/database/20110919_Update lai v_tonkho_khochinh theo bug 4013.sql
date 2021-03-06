DROP VIEW IF EXISTS `v_tonkho_khochinh`;
CREATE VIEW `v_tonkho_khochinh` AS
  SELECT TK.TONKHO_MA TONKHOMA , TK.TONKHO_MALIENKET MALIENKET , TK.TONKHO_NGAYGIOCN NGAYTHANG,
		  PNK.PHIEUNHAPKHO_SOHOADON CHUNGTUNXT,
			TK.PHIEUNHAPKHO_MA PHIEUNHAPKHO, 'N' LOAIPHIEU,
			NCC.DMNHACUNGCAP_TEN DIENGIAI,
			TK.TONKHO_NHAP SOLUONGNHAP,
			TK.TONKHO_DONGIA * TK.TONKHO_NHAP THANHTIENNHAP,
			TK.TONKHO_XUAT SOLUONGXUAT,
			TK.TONKHO_DONGIA * TONKHO_XUAT THANHTIENXUAT,
			TK.TONKHO_TON SOLUONGTON,
			TK.TONKHO_DONGIA * TK.TONKHO_TON THANHTIENTON
FROM TON_KHO  TK, PHIEU_NHAP_KHO PNK, DM_NHA_CUNG_CAP NCC, DM_KHOA DMK
WHERE TK.DMKHOA_MASO = DMK.DMKHOA_MASO
AND DMK.DMKHOA_MA = 'KCH'
AND TK.TONKHO_NHAP >0
AND PNK.PHIEUNHAPKHO_MA = TK.PHIEUNHAPKHO_MA
AND NCC.DMNHACUNGCAP_MASO = TK.TONKHO_NOIBAN

UNION ALL

SELECT TK.TONKHO_MA TONKHOMA, TK.TONKHO_MALIENKET MALIENKET, TK.TONKHO_NGAYGIOCN NGAYTHANG,
		   '' CHUNGTUNXT, PXK.PHIEUXUATKHO_MA PHIEUNHAPKHO,
			'X' LOAIPHIEU, (SELECT DMK.DMKHOA_TEN FROM DM_KHOA DMK WHERE PXK.DMKHOA_NHAN = DMK.DMKHOA_MASO) DIENGIAI,
			TONKHO_NHAP SOLUONGNHAP,
			TONKHO_DONGIA * TONKHO_NHAP THANHTIENNHAP,
			TONKHO_XUAT SOLUONGXUAT,
			TONKHO_DONGIA * TONKHO_XUAT THANHTIENXUAT,
			TONKHO_TON SOLUONGTON,
			TONKHO_DONGIA * TONKHO_TON THANHTIENTON
	FROM TON_KHO TK, CT_XUAT_KHO CTXK, PHIEU_XUAT_KHO PXK, DM_KHOA DMK
	WHERE TK.DMKHOA_MASO = DMK.DMKHOA_MASO
  AND DMK.DMKHOA_MA = 'KCH'
  AND CTXK.PHIEUXUATKHO_MA = PXK.PHIEUXUATKHO_MA
  AND CTXK.TONKHO_MA = TK.TONKHO_MA
	AND TK.TONKHO_XUAT >0

UNION ALL

SELECT TK.TONKHO_MA TONKHOMA, TK.TONKHO_MALIENKET MALIENKET, TK.TONKHO_NGAYGIOCN NGAYTHANG,
		   '' CHUNGTUNXT, PTNCC.PHIEUTRANHACUNGCAP_MA PHIEUNHAPKHO,
			'X' LOAIPHIEU, CONCAT(DMK.DMKHOA_TEN,' trả nhà cung cấp') DIENGIAI,
			TONKHO_NHAP SOLUONGNHAP,
			TONKHO_DONGIA * TONKHO_NHAP THANHTIENNHAP,
			TONKHO_XUAT SOLUONGXUAT,
			TONKHO_DONGIA * TONKHO_XUAT THANHTIENXUAT,
			TONKHO_TON SOLUONGTON,
			TONKHO_DONGIA * TONKHO_TON THANHTIENTON
	FROM TON_KHO TK, CT_TRA_NHA_CUNG_CAP CTTNCC, PHIEU_TRA_NHA_CUNG_CAP PTNCC, DM_KHOA DMK
	WHERE TK.DMKHOA_MASO = DMK.DMKHOA_MASO
  AND DMK.DMKHOA_MA = 'KCH'
  AND CTTNCC.PHIEUTRANHACUNGCAP_MA = PTNCC.PHIEUTRANHACUNGCAP_MA
  AND CTTNCC.TONKHO_MA = TK.TONKHO_MA
  AND PTNCC.DMKHOA_XUAT = DMK.DMKHOA_MASO
	AND TK.TONKHO_XUAT >0

UNION ALL

SELECT TK.TONKHO_MA TONKHOMA, TK.TONKHO_MALIENKET MALIENKET, TK.TONKHO_NGAYGIOCN NGAYTHANG,
		   '' CHUNGTUNXT, CTXBHBK.TIEPDON_MA PHIEUNHAPKHO,
			'X' LOAIPHIEU, 'Xuất BN' DIENGIAI,
			TONKHO_NHAP SOLUONGNHAP,
			TONKHO_DONGIA * TONKHO_NHAP THANHTIENNHAP,
			TONKHO_XUAT SOLUONGXUAT,
			TONKHO_DONGIA * TONKHO_XUAT THANHTIENXUAT,
			TONKHO_TON SOLUONGTON,
			TONKHO_DONGIA * TONKHO_TON THANHTIENTON
	FROM TON_KHO TK, CT_XUAT_BH_THUOCBK CTXBHBK, DM_KHOA DMK
	WHERE TK.DMKHOA_MASO = DMK.DMKHOA_MASO
    AND DMK.DMKHOA_MA = 'KCH'
    AND CTXBHBK.TONKHO_MA = TK.TONKHO_MA
	AND TK.TONKHO_XUAT >0

UNION ALL
	
	SELECT TK.TONKHO_MA TONKHOMA, TK.TONKHO_MALIENKET MALIENKET, TK.TONKHO_NGAYGIOCN NGAYTHANG,
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
  AND DMK.DMKHOA_MA = 'KCH'
  AND CTXBH.PHIEUXUATBH_MA = PXBH.PHIEUXUATBH_MA
  AND CTXBH.TONKHO_MA = TK.TONKHO_MA
	AND TK.TONKHO_XUAT >0
	
UNION ALL

SELECT TK.TONKHO_MA TONKHOMA, TK.TONKHO_MALIENKET MALIENKET, TK.TONKHO_NGAYGIOCN NGAYTHANG,
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
    AND DMK.DMKHOA_MA = 'KCH'
    AND CTXBH.PHIEUXUATBHXV_MA = PXBH.PHIEUXUATBHXV_MA
    AND CTXBH.TONKHO_MA = TK.TONKHO_MA
	AND TK.TONKHO_XUAT >0

UNION ALL

SELECT CTTK.TONKHO_MA TONKHOMA , CTTK.CTTRAKHO_MALK MALIENKET, PTK.PHIEUTRAKHO_NGAYGIOCN NGAYTHANG,
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
  AND DMK.DMKHOA_MA = 'KCH'
  AND PTK.PHIEUTRAKHO_MA = CTTK.PHIEUTRAKHO_MA
	AND PTK.DMKHOA_NHAN = DMK.DMKHOA_MASO;


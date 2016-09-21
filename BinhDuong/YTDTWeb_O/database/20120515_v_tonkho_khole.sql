CREATE OR REPLACE FORCE VIEW "DB_YTDT_BD"."V_TONKHO_KHOLE" ("TONKHOMA", "MALIENKET", "MAKHO", "NGAYTHANG", "CHUNGTUNXT", "PHIEUNHAPKHO", "LOAIPHIEU", "DIENGIAI", "SOLUONGNHAP", "THANHTIENNHAP", "SOLUONGXUAT", "THANHTIENXUAT", "SOLUONGTON", "THANHTIENTON")
                                        AS
  SELECT DISTINCT(tk.TONKHO_MA)         AS TONKHOMA,
    tk.TONKHO_MALIENKET                 AS MALIENKET,
    tk.DMKHOA_MASO                      AS MAKHO,
    tk.TONKHO_NGAYGIOCN                 AS NGAYTHANG,
    ''                                  AS CHUNGTUNXT,
    ctxk.PHIEUXUATKHO_MA                AS PHIEUNHAPKHO,
    'N'                                 AS LOAIPHIEU,
    'Nhập thuốc từ Kho chính'           AS DIENGIAI,
    tk.TONKHO_NHAP                      AS SOLUONGNHAP,
    (tk.TONKHO_DONGIA * tk.TONKHO_NHAP) AS THANHTIENNHAP,
    tk.TONKHO_XUAT                      AS SOLUONGXUAT,
    (tk.TONKHO_DONGIA * tk.TONKHO_XUAT) AS THANHTIENXUAT,
    tk.TONKHO_TON                       AS SOLUONGTON,
    (tk.TONKHO_DONGIA * tk.TONKHO_TON)  AS THANHTIENTON
  FROM ton_kho tk ,
    ct_xuat_kho_tmp ctxk,
    dm_nha_cung_cap ncc,
    dm_khoa dmk
  WHERE ((tk.DMKHOA_MASO = dmk.DMKHOA_MASO)
  AND ((dmk.DMKHOA_MA    = 'KBH')
  OR (dmk.DMKHOA_MA      = 'KTE')
  OR (dmk.DMKHOA_MA      = 'KNT'))
  AND (tk.TONKHO_NHAP    > 0)
  AND (tk.TONKHO_MA      = ctxk.TONKHO_MA))
  -- group by tk.TONKHO_MA
  UNION ALL
  SELECT tk.TONKHO_MA   AS TONKHOMA,
    tk.TONKHO_MALIENKET AS MALIENKET,
    tk.DMKHOA_MASO      AS MAKHO,
    tk.TONKHO_NGAYGIOCN AS NGAYTHANG,
    ''                  AS CHUNGTUNXT,
    pxk.PHIEUXUATKHO_MA AS PHIEUNHAPKHO,
    'X'                 AS LOAIPHIEU,
    'Xuất ' ||
    (SELECT dmk.DMKHOA_TEN
    FROM dm_khoa dmk
    WHERE (pxk.DMKHOA_NHAN = dmk.DMKHOA_MASO)
    )                                   AS DIENGIAI,
    tk.TONKHO_NHAP                      AS SOLUONGNHAP,
    (tk.TONKHO_DONGIA * tk.TONKHO_NHAP) AS THANHTIENNHAP,
    tk.TONKHO_XUAT                      AS SOLUONGXUAT,
    (tk.TONKHO_DONGIA * tk.TONKHO_XUAT) AS THANHTIENXUAT,
    tk.TONKHO_TON                       AS SOLUONGTON,
    (tk.TONKHO_DONGIA * tk.TONKHO_TON)  AS THANHTIENTON
  FROM ton_kho tk ,
    ct_xuat_kho ctxk,
    phieu_xuat_kho pxk,
    dm_khoa dmk
  WHERE ((tk.DMKHOA_MASO    = dmk.DMKHOA_MASO)
  AND ((dmk.DMKHOA_MA       = 'KBH')
  OR (dmk.DMKHOA_MA         = 'KTE')
  OR (dmk.DMKHOA_MA         = 'KNT'))
  AND (ctxk.PHIEUXUATKHO_MA = pxk.PHIEUXUATKHO_MA)
  AND (ctxk.TONKHO_MA       = tk.TONKHO_MA)
  AND (tk.TONKHO_XUAT       > 0))
  UNION ALL
  SELECT tk.TONKHO_MA   AS TONKHOMA,
    tk.TONKHO_MALIENKET AS MALIENKET,
    tk.DMKHOA_MASO      AS MAKHO,
    tk.TONKHO_NGAYGIOCN AS NGAYTHANG,
    ''                  AS CHUNGTUNXT,
    pxbh.TIEPDON_MA     AS PHIEUNHAPKHO,
    'X'                 AS LOAIPHIEU,
    'Xuất BN BH'                         AS DIENGIAI,
    tk.TONKHO_NHAP                      AS SOLUONGNHAP,
    (tk.TONKHO_DONGIA * tk.TONKHO_NHAP) AS THANHTIENNHAP,
    tk.TONKHO_XUAT                      AS SOLUONGXUAT,
    (tk.TONKHO_DONGIA * tk.TONKHO_XUAT) AS THANHTIENXUAT,
    tk.TONKHO_TON                       AS SOLUONGTON,
    (tk.TONKHO_DONGIA * tk.TONKHO_TON)  AS THANHTIENTON
  FROM ton_kho tk ,
    ct_xuat_bh ctxbh,
    phieu_xuat_bh pxbh,
    dm_khoa dmk
  WHERE ((tk.DMKHOA_MASO    = dmk.DMKHOA_MASO)
  AND ((dmk.DMKHOA_MA       = 'KBH')
  OR (dmk.DMKHOA_MA         = 'KTE')
  OR (dmk.DMKHOA_MA         = 'KNT'))
  AND (ctxbh.PHIEUXUATBH_MA = pxbh.PHIEUXUATBH_MA)
  AND (ctxbh.TONKHO_MA      = tk.TONKHO_MA)
  AND (tk.TONKHO_XUAT       > 0))
  UNION ALL
  SELECT tk.TONKHO_MA   AS TONKHOMA,
    tk.TONKHO_MALIENKET AS MALIENKET,
    tk.DMKHOA_MASO      AS MAKHO,
    tk.TONKHO_NGAYGIOCN AS NGAYTHANG,
    ''                  AS CHUNGTUNXT,
    pxbh.HSBA_SOVAOVIEN AS PHIEUNHAPKHO,
    'X'                 AS LOAIPHIEU,
    'Xuất BN BH Xuất viện'                         AS DIENGIAI,
    tk.TONKHO_NHAP                      AS SOLUONGNHAP,
    (tk.TONKHO_DONGIA * tk.TONKHO_NHAP) AS THANHTIENNHAP,
    tk.TONKHO_XUAT                      AS SOLUONGXUAT,
    (tk.TONKHO_DONGIA * tk.TONKHO_XUAT) AS THANHTIENXUAT,
    tk.TONKHO_TON                       AS SOLUONGTON,
    (tk.TONKHO_DONGIA * tk.TONKHO_TON)  AS THANHTIENTON
  FROM ton_kho tk ,
    ct_xuat_bh_xuat_vien ctxbh ,
    phieu_xuat_bh_xuat_vien pxbh,
    dm_khoa dmk 
  WHERE ((tk.DMKHOA_MASO      = dmk.DMKHOA_MASO)
  AND ((dmk.DMKHOA_MA         = 'KBH')
  OR (dmk.DMKHOA_MA           = 'KTE')
  OR (dmk.DMKHOA_MA           = 'KNT'))
  AND (ctxbh.PHIEUXUATBHXV_MA = pxbh.PHIEUXUATBHXV_MA)
  AND (ctxbh.TONKHO_MA        = tk.TONKHO_MA)
  AND (tk.TONKHO_XUAT         > 0))
  UNION ALL
  SELECT tk.TONKHO_MA   AS TONKHOMA,
    tk.TONKHO_MALIENKET AS MALIENKET,
    tk.DMKHOA_MASO      AS MAKHO,
    tk.TONKHO_NGAYGIOCN AS NGAYTHANG,
    ''                  AS CHUNGTUNXT,
    ptk.PHIEUTRAKHO_MA  AS PHIEUNHAPKHO,
    'X'                 AS LOAIPHIEU,
    dmk.DMKHOA_TEN
    || ' trả kho về '
    ||
    (SELECT dmk.DMKHOA_TEN
    FROM dm_khoa dmk
    WHERE (ptk.DMKHOA_NHAN = dmk.DMKHOA_MASO)
    )                                   AS DIENGIAI,
    tk.TONKHO_TRA                       AS SOLUONGNHAP,
    (tk.TONKHO_DONGIA * tk.TONKHO_TRA)  AS THANHTIENNHAP,
    tk.TONKHO_XUAT                      AS SOLUONGXUAT,
    (tk.TONKHO_DONGIA * tk.TONKHO_XUAT) AS THANHTIENXUAT,
    tk.TONKHO_TON                       AS SOLUONGTON,
    (tk.TONKHO_DONGIA * tk.TONKHO_TON)  AS THANHTIENTON
  FROM ton_kho tk ,
    ct_tra_kho_tmp cttk,
    phieu_tra_kho ptk,
    dm_khoa dmk
  WHERE ((tk.DMKHOA_MASO  = dmk.DMKHOA_MASO)
  AND ((dmk.DMKHOA_MA     = 'KBH')
  OR (dmk.DMKHOA_MA       = 'KTE')
  OR (dmk.DMKHOA_MA       = 'KNT'))
  AND (ptk.PHIEUTRAKHO_MA = cttk.PHIEUTRAKHO_MA)
  AND (cttk.TONKHO_MA     = tk.TONKHO_MA)
  AND (tk.TONKHO_XUAT     > 0))
  UNION ALL
  SELECT cttk.TONKHO_MA       AS TONKHOMA,
    cttk.CTTRAKHO_MALK        AS MALIENKET,
    ptk.DMKHOA_NHAN           AS MAKHO,
    ptk.PHIEUTRAKHO_NGAYGIOCN AS NGAYTHANG,
    ''                        AS CHUNGTUNXT,
    ptk.PHIEUTRAKHO_MA        AS PHIEUNHAPKHO,
    'T'                       AS LOAIPHIEU,
    dmk.DMKHOA_TEN
    || ' nhận trả kho từ '
    ||(SELECT dmk.DMKHOA_TEN
    FROM dm_khoa dmk
    WHERE ptk.DMKHOA_TRA = dmk.DMKHOA_MASO)       AS DIENGIAI,
    cttk.CTTRAKHO_SOLUONG                          AS SOLUONGNHAP,
    (cttk.CTTRAKHO_DONGIA * cttk.CTTRAKHO_SOLUONG) AS THANHTIENNHAP,
    0                                              AS SOLUONGXUAT,
    0                                              AS THANHTIENXUAT,
    0                                              AS SOLUONGTON,
    0                                              AS THANHTIENTON
  FROM ct_tra_kho cttk ,
    phieu_tra_kho ptk,
    dm_khoa dmk
  WHERE ((ptk.DMKHOA_NHAN = dmk.DMKHOA_MASO)
  AND ((dmk.DMKHOA_MA     = 'KBH')
  OR (dmk.DMKHOA_MA       = 'KTE')
  OR (dmk.DMKHOA_MA       = 'KNT'))
  AND (ptk.PHIEUTRAKHO_MA = cttk.PHIEUTRAKHO_MA)
  AND (ptk.DMKHOA_NHAN    = dmk.DMKHOA_MASO)) 

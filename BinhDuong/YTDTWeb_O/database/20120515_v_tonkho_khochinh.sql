CREATE OR REPLACE FORCE VIEW "DB_YTDT_BD"."V_TONKHO_KHOCHINH" ("TONKHOMA", "MALIENKET", "NGAYTHANG", "CHUNGTUNXT", "PHIEUNHAPKHO", "LOAIPHIEU", "DIENGIAI", "SOLUONGNHAP", "THANHTIENNHAP", "SOLUONGXUAT", "THANHTIENXUAT", "SOLUONGTON", "THANHTIENTON")
                                        AS
  SELECT tk.TONKHO_MA                   AS TONKHOMA,
    tk.TONKHO_MALIENKET                 AS MALIENKET,
    tk.TONKHO_NGAYGIOCN                 AS NGAYTHANG,
    pnk.PHIEUNHAPKHO_SOHOADON           AS CHUNGTUNXT,
    tk.PHIEUNHAPKHO_MA                  AS PHIEUNHAPKHO,
    'N'                                 AS LOAIPHIEU,
    ncc.DMNHACUNGCAP_TEN                AS DIENGIAI,
    tk.TONKHO_NHAP                      AS SOLUONGNHAP,
    (tk.TONKHO_DONGIA * tk.TONKHO_NHAP) AS THANHTIENNHAP,
    tk.TONKHO_XUAT                      AS SOLUONGXUAT,
    (tk.TONKHO_DONGIA * tk.TONKHO_XUAT) AS THANHTIENXUAT,
    tk.TONKHO_TON                       AS SOLUONGTON,
    (tk.TONKHO_DONGIA * tk.TONKHO_TON)  AS THANHTIENTON
  FROM ton_kho tk,
    phieu_nhap_kho pnk,
    dm_nha_cung_cap ncc,
    dm_khoa dmk
  WHERE ((tk.DMKHOA_MASO     = dmk.DMKHOA_MASO)
  AND (dmk.DMKHOA_MA         = 'KCH')
  AND (tk.TONKHO_NHAP        > 0)
  AND (pnk.PHIEUNHAPKHO_MA   = tk.PHIEUNHAPKHO_MA)
  AND (ncc.DMNHACUNGCAP_MASO = tk.TONKHO_NOIBAN))
  UNION ALL
  SELECT tk.TONKHO_MA   AS TONKHOMA,
    tk.TONKHO_MALIENKET AS MALIENKET,
    tk.TONKHO_NGAYGIOCN AS NGAYTHANG,
    ''                  AS CHUNGTUNXT,
    pxk.PHIEUXUATKHO_MA AS PHIEUNHAPKHO,
    'X'                 AS LOAIPHIEU,
    'Xuất '
    ||
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
  FROM ton_kho tk,
    ct_xuat_kho ctxk,
    phieu_xuat_kho pxk,
    dm_khoa dmk
  WHERE ((tk.DMKHOA_MASO    = dmk.DMKHOA_MASO)
  AND (dmk.DMKHOA_MA        = 'KCH')
  AND (ctxk.PHIEUXUATKHO_MA = pxk.PHIEUXUATKHO_MA)
  AND (ctxk.TONKHO_MA       = tk.TONKHO_MA)
  AND (tk.TONKHO_XUAT       > 0))
  UNION ALL
  SELECT tk.TONKHO_MA           AS TONKHOMA,
    tk.TONKHO_MALIENKET         AS MALIENKET,
    tk.TONKHO_NGAYGIOCN         AS NGAYTHANG,
    ''                          AS CHUNGTUNXT,
    ptncc.PHIEUTRANHACUNGCAP_MA AS PHIEUNHAPKHO,
    'X'                         AS LOAIPHIEU,
    (dmk.DMKHOA_TEN
    ||' trả  nhà cung cấp')             AS DIENGIAI,
    tk.TONKHO_NHAP                      AS SOLUONGNHAP,
    (tk.TONKHO_DONGIA * tk.TONKHO_NHAP) AS THANHTIENNHAP,
    tk.TONKHO_XUAT                      AS SOLUONGXUAT,
    (tk.TONKHO_DONGIA * tk.TONKHO_XUAT) AS THANHTIENXUAT,
    tk.TONKHO_TON                       AS SOLUONGTON,
    (tk.TONKHO_DONGIA * tk.TONKHO_TON)  AS THANHTIENTON
  FROM ton_kho tk,
    ct_tra_nha_cung_cap cttncc,
    phieu_tra_nha_cung_cap ptncc,
    dm_khoa dmk
  WHERE ((tk.DMKHOA_MASO            = dmk.DMKHOA_MASO)
  AND (dmk.DMKHOA_MA                = 'KCH')
  AND (cttncc.PHIEUTRANHACUNGCAP_MA = ptncc.PHIEUTRANHACUNGCAP_MA)
  AND (cttncc.TONKHO_MA             = tk.TONKHO_MA)
  AND (ptncc.DMKHOA_XUAT            = dmk.DMKHOA_MASO)
  AND (tk.TONKHO_XUAT               > 0))
  UNION ALL
  SELECT tk.TONKHO_MA                   AS TONKHOMA,
    tk.TONKHO_MALIENKET                 AS MALIENKET,
    tk.TONKHO_NGAYGIOCN                 AS NGAYTHANG,
    ''                                  AS CHUNGTUNXT,
    ctxbhbk.TIEPDON_MA                  AS PHIEUNHAPKHO,
    'X'                                 AS LOAIPHIEU,
    'Xuất BN'                           AS DIENGIAI,
    tk.TONKHO_NHAP                      AS SOLUONGNHAP,
    (tk.TONKHO_DONGIA * tk.TONKHO_NHAP) AS THANHTIENNHAP,
    tk.TONKHO_XUAT                      AS SOLUONGXUAT,
    (tk.TONKHO_DONGIA * tk.TONKHO_XUAT) AS THANHTIENXUAT,
    tk.TONKHO_TON                       AS SOLUONGTON,
    (tk.TONKHO_DONGIA * tk.TONKHO_TON)  AS THANHTIENTON
  FROM ton_kho tk,
    ct_xuat_bh_thuocbk ctxbhbk,
    dm_khoa dmk
  WHERE ((tk.DMKHOA_MASO = dmk.DMKHOA_MASO)
  AND (dmk.DMKHOA_MA     = 'KCH')
  AND (ctxbhbk.TONKHO_MA = tk.TONKHO_MA)
  AND (tk.TONKHO_XUAT    > 0))
  UNION ALL
  SELECT tk.TONKHO_MA                   AS TONKHOMA,
    tk.TONKHO_MALIENKET                 AS MALIENKET,
    tk.TONKHO_NGAYGIOCN                 AS NGAYTHANG,
    ''                                  AS CHUNGTUNXT,
    pxbh.TIEPDON_MA                     AS PHIEUNHAPKHO,
    'X'                                 AS LOAIPHIEU,
    'Xuất BN BH'                        AS DIENGIAI,
    tk.TONKHO_NHAP                      AS SOLUONGNHAP,
    (tk.TONKHO_DONGIA * tk.TONKHO_NHAP) AS THANHTIENNHAP,
    tk.TONKHO_XUAT                      AS SOLUONGXUAT,
    (tk.TONKHO_DONGIA * tk.TONKHO_XUAT) AS THANHTIENXUAT,
    tk.TONKHO_TON                       AS SOLUONGTON,
    (tk.TONKHO_DONGIA * tk.TONKHO_TON)  AS THANHTIENTON
  FROM ton_kho tk,
    ct_xuat_bh ctxbh,
    phieu_xuat_bh pxbh,
    dm_khoa dmk
  WHERE ((tk.DMKHOA_MASO    = dmk.DMKHOA_MASO)
  AND (dmk.DMKHOA_MA        = 'KCH')
  AND (ctxbh.PHIEUXUATBH_MA = pxbh.PHIEUXUATBH_MA)
  AND (ctxbh.TONKHO_MA      = tk.TONKHO_MA)
  AND (tk.TONKHO_XUAT       > 0))
  UNION ALL
  SELECT tk.TONKHO_MA                   AS TONKHOMA,
    tk.TONKHO_MALIENKET                 AS MALIENKET,
    tk.TONKHO_NGAYGIOCN                 AS NGAYTHANG,
    ''                                  AS CHUNGTUNXT,
    pxbh.HSBA_SOVAOVIEN                 AS PHIEUNHAPKHO,
    'X'                                 AS LOAIPHIEU,
    'Xuất BN BH xuất viện'              AS DIENGIAI,
    tk.TONKHO_NHAP                      AS SOLUONGNHAP,
    (tk.TONKHO_DONGIA * tk.TONKHO_NHAP) AS THANHTIENNHAP,
    tk.TONKHO_XUAT                      AS SOLUONGXUAT,
    (tk.TONKHO_DONGIA * tk.TONKHO_XUAT) AS THANHTIENXUAT,
    tk.TONKHO_TON                       AS SOLUONGTON,
    (tk.TONKHO_DONGIA * tk.TONKHO_TON)  AS THANHTIENTON
  FROM ton_kho tk,
    ct_xuat_bh_xuat_vien ctxbh,
    phieu_xuat_bh_xuat_vien pxbh,
    dm_khoa dmk
  WHERE ((tk.DMKHOA_MASO      = dmk.DMKHOA_MASO)
  AND (dmk.DMKHOA_MA          = 'KCH')
  AND (ctxbh.PHIEUXUATBHXV_MA = pxbh.PHIEUXUATBHXV_MA)
  AND (ctxbh.TONKHO_MA        = tk.TONKHO_MA)
  AND (tk.TONKHO_XUAT         > 0))
  UNION ALL
  SELECT cttk.TONKHO_MA       AS TONKHOMA,
    cttk.CTTRAKHO_MALK        AS MALIENKET,
    ptk.PHIEUTRAKHO_NGAYGIOCN AS NGAYTHANG,
    ''                        AS CHUNGTUNXT,
    ptk.PHIEUTRAKHO_MA        AS PHIEUNHAPKHO,
    'T'                       AS LOAIPHIEU,
    'Nhận trả kho từ '
    ||
    (SELECT dmk.DMKHOA_TEN
    FROM dm_khoa dmk
    WHERE (ptk.DMKHOA_TRA = dmk.DMKHOA_MASO)
    )                                              AS DIENGIAI,
    cttk.CTTRAKHO_SOLUONG                          AS SOLUONGNHAP,
    (cttk.CTTRAKHO_DONGIA * cttk.CTTRAKHO_SOLUONG) AS THANHTIENNHAP,
    0                                              AS SOLUONGXUAT,
    0                                              AS THANHTIENXUAT,
    0                                              AS SOLUONGTON,
    0                                              AS THANHTIENTON
  FROM ct_tra_kho cttk,
    phieu_tra_kho ptk,
    dm_khoa dmk
  WHERE ((ptk.DMKHOA_NHAN = dmk.DMKHOA_MASO)
  AND (dmk.DMKHOA_MA      = 'KCH')
  AND (ptk.PHIEUTRAKHO_MA = cttk.PHIEUTRAKHO_MA)
  AND (ptk.DMKHOA_NHAN    = dmk.DMKHOA_MASO));

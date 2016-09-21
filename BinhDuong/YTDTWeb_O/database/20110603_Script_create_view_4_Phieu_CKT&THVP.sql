-- Cac view duoi day phuc vu cho bao cao Phieu cong khai thuoc va tong hop vien phi

-- View lay thong tin SOVAOVIEN, MATHUOC, NGAY, SOLUONG, DONGIA tu bang thuoc_noi_tru

CREATE OR REPLACE VIEW  v_thuocnoitru
AS
SELECT hsba.HSBA_SOVAOVIEN AS SOVAOVIEN,
       tnt.THUOCNOITRU_MATHUOC AS MATHUOC,
       DATE(tnt.THUOCNOITRU_NGAYGIO) NGAY,
       SUM(tnt.THUOCNOITRU_SOLUONG - IFNULL(tnt.THUOCNOITRU_TRA,0)) AS SOLUONG,
       -- tnt.THUOCNOITRU_TIENBNTRA AS DONGIA,
       -- Fix bug 3328 : lay chi phi benh nhan su dung (thay cho chi phi benh nhan tra sau khi tru bao hiem)
       (tnt.THUOCNOITRU_DONGIA * (tnt.THUOCNOITRU_SOLUONG - IFNULL(tnt.THUOCNOITRU_TRA,0))) AS DONGIA,
       dmlt.DMLOAITHUOC_MA
FROM db_ytdt_bd.thuoc_noi_tru tnt
    LEFT JOIN hsba_khoa hsbakhoa ON tnt.HSBA_KHOA = hsbakhoa.HSBAKHOA_MASO
    LEFT JOIN hsba ON (hsbakhoa.HSBA_SOVAOVIEN = hsba.HSBA_SOVAOVIEN AND hsbakhoa.KHOA_MA = hsba.HSBA_KHOADANGDT)
    LEFT JOIN dm_thuoc  dmthuoc ON tnt.THUOCNOITRU_MATHUOC = dmthuoc.DMTHUOC_MASO
    LEFT JOIN dm_phan_loai_thuoc dmplt ON dmthuoc.DMPHANLOAITHUOC_MASO = dmplt.DMPHANLOAITHUOC_MASO
    LEFT JOIN dm_loai_thuoc dmlt ON dmplt.DMLOAITHUOC_MASO = dmlt.DMLOAITHUOC_MASO
WHERE (tnt.THUOCNOITRU_SOLUONG - IFNULL(tnt.THUOCNOITRU_TRA,0)) > 0
GROUP BY hsba.HSBA_SOVAOVIEN,tnt.THUOCNOITRU_MATHUOC, DATE(tnt.THUOCNOITRU_NGAYGIO);

-- View count so lan an theo benh nhan
CREATE OR REPLACE VIEW  v_count_lanan
AS
SELECT bnga.BNPBA_MASO,
       COUNT(bnga.BNGA_MASO) AS LANAN
FROM benh_nhan_gio_an bnga
GROUP BY bnga.BNPBA_MASO;


-- View lay thong tin tu phieu bao an
CREATE OR REPLACE VIEW  v_thongtinan
AS
SELECT bnpba.BNPBA_MASO,
       bnpba.HSBA_SOVAOVIEN,
       pba.PHIEUBAOAN_NGAYAN AS NGAYAN,
       v_lanan.LANAN,
       (IFNULL(dmdt.DTDMDT_MA,0) + IFNULL(dmma.DTDMMA_MA,0)) AS MUCAN
FROM  benh_nhan_phieu_bao_an bnpba
      LEFT JOIN phieu_bao_an pba ON bnpba.PHIEUBAOAN_MASO = pba.PHIEUBAOAN_MASO
      -- LEFT JOIN dt_dm_muc_an dmma ON bnpba.DTDMMA_MASO = dmma.DTDMMA_MASO
      -- Khong lay muc an mien phi, chi lay muc dong them
      LEFT JOIN dt_dm_dong_them dmdt ON bnpba.DTDMDT_MASO = dmdt.DTDMDT_MASO
      -- Fix bug 3328 : lay muc an mien phi + muc dong them
      LEFT JOIN dt_dm_muc_an dmma ON bnpba.DTDMMA_MASO = dmma.DTDMMA_MASO
      LEFT JOIN
      -- View count so lan an
      v_count_lanan AS v_lanan ON bnpba.BNPBA_MASO = v_lanan.BNPBA_MASO;


-- View lay thong tin CLS
CREATE OR REPLACE VIEW  v_thongtincls
AS
SELECT hsba.HSBA_SOVAOVIEN,
       clsmo.CLSMO_LOAICLS AS LOAICLS_MASO,
       dmcls.DTDMCLS_TEN AS LOAICLS_TEN,
       dmclsbg.DTDMCLSBG_MASO CLSBG_MASO,
       dmclsbg.DTDMCLSBG_DIENGIAI AS CLSBG_TEN,
       SUM(clsmo.CLSMO_LAN - IFNULL(clsmo.CLSMO_TRA,0)) AS SOLAN,
       -- SUM(clsmo.CLSMO_DONGIABNTRA) AS THANHTIEN,
       -- Fix bug 3328 : lay chi phi benh nhan su dung (thay cho chi phi benh nhan tra sau khi tru bao hiem)
       (clsmo.CLSMO_DONGIA * (clsmo.CLSMO_LAN - IFNULL(clsmo.CLSMO_TRA,0))) AS THANHTIEN,
       clsmo.CLSMO_NGAY
FROM cls_mo clsmo
     LEFT JOIN hsba_khoa hsbakhoa ON clsmo.HSBAKHOA_MASO = hsbakhoa.HSBAKHOA_MASO
     LEFT JOIN hsba ON (hsbakhoa.HSBA_SOVAOVIEN = hsba.HSBA_SOVAOVIEN AND hsbakhoa.KHOA_MA = hsba.HSBA_KHOADANGDT)
     LEFT JOIN dt_dm_cls dmcls ON clsmo.CLSMO_LOAICLS = dmcls.DTDMCLS_MASO
     LEFT JOIN dt_dm_cls_bang_gia dmclsbg ON clsmo.CLSMO_MAHANG = dmclsbg.DTDMCLSBG_MASO
WHERE (clsmo.CLSMO_LAN - IFNULL(clsmo.CLSMO_TRA,0)) > 0
GROUP BY  hsba.HSBA_SOVAOVIEN, clsmo.CLSMO_MAHANG, clsmo.CLSMO_NGAY;
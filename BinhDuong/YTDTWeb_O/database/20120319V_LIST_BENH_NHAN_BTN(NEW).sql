CREATE OR REPLACE FORCE VIEW "DB_YTDT_BD"."V_LIST_BENH_NHAN_BTN" ("TINH_MA", "HUYEN_MASO", "HUYEN_MA", "HUYEN_TEN", "XA_MASO", "XA_MA", "XA_TEN", "BENHNHAN_MA", "HSBA_NGAYGIORAV", "DMBENHICD_MA", "MABENH_TUVONG")
                          AS
  SELECT dmtinh.DMTINH_MA AS TINH_MA,
    NVL(TRIM(dmhuyen.DMHUYEN_MASO),0)  AS HUYEN_MASO,
    dmhuyen.DMHUYEN_MA  AS HUYEN_MA,
    dmhuyen.DMHUYEN_TEN   AS HUYEN_TEN,
    dmxa.DMXA_MASO        AS XA_MASO,
    dmxa.DMXA_MA          AS XA_MA,
    dmxa.DMXA_TEN         AS XA_TEN,
    hsba.BENHNHAN_MA      AS BENHNHAN_MA,
    hsba.HSBA_NGAYGIORAV  AS HSBA_NGAYGIORAV,
    icd.DMBENHICD_MA      AS DMBENHICD_MA,
    icdtv.DMBENHICD_MA    AS MABENH_TUVONG
  FROM ((((((hsba
  LEFT JOIN dm_benh_icd icd
  ON((hsba.HSBA_MACHDRAVIEN = icd.DMBENHICD_MASO)))
  LEFT JOIN dm_benh_icd icdtv
  ON((hsba.HSBA_TUVONG = icdtv.DMBENHICD_MASO)))
  LEFT JOIN benh_nhan bn
  ON((hsba.BENHNHAN_MA = bn.BENHNHAN_MA)))
  LEFT JOIN dm_tinh dmtinh
  ON((bn.TINH_MA = dmtinh.DMTINH_MASO)))
  LEFT JOIN dm_huyen dmhuyen
  ON((bn.HUYEN_MA = dmhuyen.DMHUYEN_MASO)))
  LEFT JOIN dm_xa dmxa
  ON((bn.XA_MA = dmxa.DMXA_MASO)))
  WHERE ((icd.DMBENHICD_MA = 'A00')
  OR (icd.DMBENHICD_MA = 'A01')
  OR (icd.DMBENHICD_MA = 'A03')
  OR (icd.DMBENHICD_MA = 'A06')
  OR (icd.DMBENHICD_MA = 'A09')
  OR (icd.DMBENHICD_MA = 'A83')
  OR (icd.DMBENHICD_MA = 'A90')
  OR (icd.DMBENHICD_MA = 'A91')
  OR (icd.DMBENHICD_MA = 'B50')
  OR (icd.DMBENHICD_MA = 'B51')
  OR (icd.DMBENHICD_MA = 'B54')
  OR (icd.DMBENHICD_MA = 'B15')
  OR (icd.DMBENHICD_MA = 'B16')
  OR (icd.DMBENHICD_MA = 'B17')
  OR (icd.DMBENHICD_MA = 'B18')
  OR (icd.DMBENHICD_MA = 'B19')
  OR (icd.DMBENHICD_MA = 'A82')
  OR (icd.DMBENHICD_MA = 'A39')
  OR (icd.DMBENHICD_MA = 'B01')
  OR (icd.DMBENHICD_MA = 'A36')
  OR (icd.DMBENHICD_MA = 'A37')
  OR (icd.DMBENHICD_MA = 'A33')
  OR (icd.DMBENHICD_MA = 'A35')
  OR (icd.DMBENHICD_MA = 'A80')
  OR (icd.DMBENHICD_MA = 'B05')
  OR (icd.DMBENHICD_MA = 'B26')
  OR (icd.DMBENHICD_MA = 'J10')
  OR (icd.DMBENHICD_MA = 'J11')
  OR (icd.DMBENHICD_MA = 'J09')
  OR (icd.DMBENHICD_MA = 'B30')
  OR (icd.DMBENHICD_MA = 'A20')
  OR (icd.DMBENHICD_MA = 'A22')
  OR (icd.DMBENHICD_MA = 'A27')
  OR (icd.DMBENHICD_MA = 'B08.8')
  OR (icd.DMBENHICD_MA = 'B95'));

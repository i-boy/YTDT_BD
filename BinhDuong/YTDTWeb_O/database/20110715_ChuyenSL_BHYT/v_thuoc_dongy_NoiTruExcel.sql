DROP VIEW IF EXISTS `v_thuoc_dongy_NoiTruExcel`;
CREATE VIEW `v_thuoc_dongy_NoiTruExcel` AS
SELECT concat(A.HSBA_SOVAOVIEN,'1') Id, A.HSBA_SOVAOVIEN, 1 as stt_nh, sum(coalesce(A.THUOCDONGY_TIENBNTRA,0)) as tien_bn,
SUM(A.THUOCDONGY_SOLUONG * A.THUOCDONGY_DONGIA) as tong_tien
FROM thuoc_dong_y_noi_tru A, v_benhnhan_nt_datt B
WHERE A.HSBA_SOVAOVIEN = B.HSBA_SOVAOVIEN
GROUP BY A.HSBA_SOVAOVIEN
ORDER BY A.HSBA_SOVAOVIEN asc;
DROP VIEW IF EXISTS `v_cls_noitru_groupbySOVAOVIEN_SttNhExcel`;
CREATE VIEW `v_cls_noitru_groupbySOVAOVIEN_SttNhExcel` AS
SELECT concat(A.HSBA_SOVAOVIEN,A.stt_nh) Id, A.HSBA_SOVAOVIEN, A.stt_nh, sum(A.tong_tien) as tong_tien, sum(A.tien_bn) as tien_bn
FROM v_cls_noitru A
GROUP BY A.stt_nh, A.HSBA_SOVAOVIEN
ORDER BY A.HSBA_SOVAOVIEN asc, A.stt_nh asc;
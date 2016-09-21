DROP VIEW IF EXISTS `v_cls_kham_groupbyTIEPDON_SttNhExcel`;
CREATE VIEW `v_cls_kham_groupbyTIEPDON_SttNhExcel` AS
SELECT concat(A.TIEPDON_MA, A.stt_nh) Id, A.TIEPDON_MA, A.stt_nh, sum(A.tong_tien) as tong_tien, sum(A.tien_bn) as tien_bn
FROM v_cls_kham A
GROUP BY A.stt_nh, A.TIEPDON_MA
ORDER BY A.TIEPDON_MA, A.stt_nh asc;
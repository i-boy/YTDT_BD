DROP VIEW IF EXISTS `v_thuoc_NgoaiTru_groupbyTIEPDON_SttNhExcel`;
CREATE VIEW `v_thuoc_NgoaiTru_groupbyTIEPDON_SttNhExcel` AS
SELECT concat(TIEPDON_MA, stt_nh) Id, TIEPDON_MA, stt_nh, sum(tong_tien) as tong_tien, sum(tien_bn) as tien_bn
FROM v_thuoc_ngoaitru
GROUP BY TIEPDON_MA, stt_nh
ORDER BY TIEPDON_MA;
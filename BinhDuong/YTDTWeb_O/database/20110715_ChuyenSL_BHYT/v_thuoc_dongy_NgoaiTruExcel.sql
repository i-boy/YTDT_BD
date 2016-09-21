DROP VIEW IF EXISTS `v_thuoc_dongy_NgoaiTruExcel`;
CREATE VIEW `v_thuoc_dongy_NgoaiTruExcel` AS
SELECT concat(A.TIEPDON_MA,'1') Id, A.TIEPDON_MA, 1 as stt_nh, sum(coalesce(A.THUOCDONGY_TIENBNTRA,0)) as tien_bn,
sum(A.THUOCDONGY_SOLUONG * A.THUOCDONGY_DONGIA) as tong_tien
FROM thuoc_dong_y_ngoai_tru A, v_benhnhan_ngt_datt B
WHERE A.TIEPDON_MA = B.TIEPDON_MA
AND (A.THUOCDONGY_LOAI = 1 or A.THUOCDONGY_LOAI = 3)
GROUP BY A.TIEPDON_MA
ORDER BY A.TIEPDON_MA asc;
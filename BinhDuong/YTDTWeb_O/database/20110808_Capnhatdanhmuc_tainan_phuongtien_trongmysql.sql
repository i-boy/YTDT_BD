#Chay script trong mysql
Update dm_tai_nan set dmtainan_ten = 'Tai nạn giao thông (V01-V99)' where dmtainan_maso = 1;
Update dm_tai_nan set dmtainan_ten = 'Tự tử (X60-X80)' where dmtainan_maso = 2;
Update dm_tai_nan set dmtainan_ten = 'Bạo lực, xung đột (X85-Y90)' where dmtainan_maso = 3;
Update dm_tai_nan set dmtainan_ten = 'Khác' where dmtainan_maso = 5;

INSERT INTO db_ytdt_bd.dm_tai_nan (DMTAINAN_MA, DMTAINAN_TEN, DMTAINAN_NGAYGIOCN, DMTAINAN_DT, DMTAINAN_QL, DMTAINAN_DP) 
VALUES ('NG', 'Ngã (W01-W19)', 1, 1, 1, 1);
INSERT INTO db_ytdt_bd.dm_tai_nan (DMTAINAN_MA, DMTAINAN_TEN, DMTAINAN_NGAYGIOCN, DMTAINAN_DT, DMTAINAN_QL, DMTAINAN_DP) 
VALUES ('TD', 'Tai nạn lao động (W20-W49)', 1, 1, 1, 1);
INSERT INTO db_ytdt_bd.dm_tai_nan (DMTAINAN_MA, DMTAINAN_TEN, DMTAINAN_NGAYGIOCN, DMTAINAN_DT, DMTAINAN_QL, DMTAINAN_DP) 
VALUES ('SV', 'Súc vật, động vật (W50-W64)', 1, 1, 1, 1);
INSERT INTO db_ytdt_bd.dm_tai_nan (DMTAINAN_MA, DMTAINAN_TEN, DMTAINAN_NGAYGIOCN, DMTAINAN_DT, DMTAINAN_QL, DMTAINAN_DP) 
VALUES ('DN', 'Đuối nước (W65-W84)', 1, 1, 1, 1);
INSERT INTO db_ytdt_bd.dm_tai_nan (DMTAINAN_MA, DMTAINAN_TEN, DMTAINAN_NGAYGIOCN, DMTAINAN_DT, DMTAINAN_QL, DMTAINAN_DP) 
VALUES ('BO', 'Bỏng (W85-W99,X00-X19)', 1, 1, 1, 1);

update tiep_don set tiep_don.DMPTGTN_MASO = 37 where tiep_don.TAINAN_MA = 4 and tiep_don.DMPTGTN_MASO in (28,29,30,32,33,34,35);
update hsba set hsba.DMPTGTN_MASO = 37 where hsba.TAINAN_MA = 4 and hsba.DMPTGTN_MASO in (28,29,30,32,33,34,35);

Delete from dm_phuong_thuc_gay_tai_nan where dm_phuong_thuc_gay_tai_nan.DMPTGTN_MASO in (28,29,30,32,33,34,35);

INSERT INTO db_ytdt_bd.dm_phuong_thuc_gay_tai_nan
(DMPTGTN_MA, DMTAINAN_MASO, DMPTGTN_TEN, DMPTGTN_BHYT, DMPTGTN_DT, DMPTGTN_QL, DMPTGTN_DP, DMPTGTN_NGAYGIOCN) 
VALUES ('56', 4, 'Động vật', null,1, null, null, 1);
INSERT INTO db_ytdt_bd.dm_phuong_thuc_gay_tai_nan
(DMPTGTN_MA, DMTAINAN_MASO, DMPTGTN_TEN, DMPTGTN_BHYT, DMPTGTN_DT, DMPTGTN_QL, DMPTGTN_DP, DMPTGTN_NGAYGIOCN) 
VALUES ('57', 4, 'Thực vật có độc', null,1, null, null, 1);
INSERT INTO db_ytdt_bd.dm_phuong_thuc_gay_tai_nan
(DMPTGTN_MA, DMTAINAN_MASO, DMPTGTN_TEN, DMPTGTN_BHYT, DMPTGTN_DT, DMPTGTN_QL, DMPTGTN_DP, DMPTGTN_NGAYGIOCN) 
VALUES ('58', 4, 'Rượu', null,1, null, null, 1);

update dm_phuong_thuc_gay_tai_nan set dmptgtn_ten = 'Khác' where DMPTGTN_MASO = 37;


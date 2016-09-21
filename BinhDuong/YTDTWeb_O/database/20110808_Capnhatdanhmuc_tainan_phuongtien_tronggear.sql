#Chu y: Chay script nay trong google gear
Update dm_tai_nan set ten = 'Tai nạn giao thông (V01-V99)' where maso = 1;
Update dm_tai_nan set ten = 'Tự tử (X60-X80)' where maso = 2;
Update dm_tai_nan set ten = 'Bạo lực, xung đột (X85-Y90)' where maso = 3;
Update dm_tai_nan set ten = 'Khác' where maso = 5;


INSERT INTO dm_tai_nan (ROWID, MASO, MA, TEN, NGAYCHINHSUA,QL,DT,DP) 
VALUES (6,6,'NG', 'Ngã (W01-W19)', 1, 1, 1, 1);
INSERT INTO dm_tai_nan (ROWID, MASO, MA, TEN, NGAYCHINHSUA,QL,DT,DP) 
VALUES (7,7,'TD', 'Tai nạn lao động (W20-W49)', 1, 1, 1, 1);
INSERT INTO dm_tai_nan (ROWID, MASO, MA, TEN, NGAYCHINHSUA,QL,DT,DP) 
VALUES (8,8,'SV', 'Súc vật, động vật (W50-W64)', 1, 1, 1, 1);
INSERT INTO dm_tai_nan (ROWID, MASO, MA, TEN, NGAYCHINHSUA,QL,DT,DP) 
VALUES (9,9,'DN', 'Đuối nước (W65-W84)', 1, 1, 1, 1);
INSERT INTO dm_tai_nan (ROWID, MASO, MA, TEN, NGAYCHINHSUA,QL,DT,DP) 
VALUES (10,10,'BO', 'Bỏng (W85-W99,X00-X19)', 1, 1, 1, 1);



Delete from dm_phuong_thuc_gay_tai_nan where dm_phuong_thuc_gay_tai_nan.MASO in (28,29,30,32,33,34,35);

INSERT INTO dm_phuong_thuc_gay_tai_nan (ROWID, MASO,MA,DMTAINAN_MASO, TEN, DMPTGTN_BHYT, NGAYCHINHSUA,QL,DT,DP) 
VALUES (56,56,'56', 4, 'Động vật', 0,1, 0, 1, 0);
INSERT INTO dm_phuong_thuc_gay_tai_nan (ROWID, MASO,MA,DMTAINAN_MASO, TEN, DMPTGTN_BHYT, NGAYCHINHSUA,QL,DT,DP) 
VALUES (57,57,'57', 4, 'Thực vật có độc', 0,1, 0, 1, 0);
INSERT INTO dm_phuong_thuc_gay_tai_nan (ROWID, MASO,MA,DMTAINAN_MASO, TEN, DMPTGTN_BHYT, NGAYCHINHSUA,QL,DT,DP) 
VALUES (58,58,'58', 4, 'Rượu', 0,1, 0, 1, 0);

update dm_phuong_thuc_gay_tai_nan set ten = 'Khác' where MASO = 37;

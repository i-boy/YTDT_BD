CREATE TABLE DM_PHAN_NHOM_THUOC(rowid INTEGER NOT NULL PRIMARY KEY , MaSo INTEGER, Ma VARCHAR(10),DMNHOMBCTHUOC_MASO INTEGER, Ten VARCHAR(36),DMPHANNHOMTHUOC_THUTU VARCHAR(6),DMPHANNHOMTHUOC_MAPHU VARCHAR(2), DMPHANNHOMTHUOC_GHICHU VARCHAR(60), DMNHANVIEN_CN VARCHAR(8), NgayChinhSua REAL,QL INTEGER,DT INTEGER,DP INTEGER);

insert into dm_phan_nhom_thuoc(MaSo,Ma,Ten,DMPHANNHOMTHUOC_THUTU,NgayChinhSua,DT) values(1,'TT','Thuốc thường',1,1,1);
insert into dm_phan_nhom_thuoc(MaSo,Ma,Ten,DMPHANNHOMTHUOC_THUTU,NgayChinhSua,DT) values(2,'TD','Thuốc độc',2,1,1);
insert into dm_phan_nhom_thuoc(MaSo,Ma,Ten,DMPHANNHOMTHUOC_THUTU,NgayChinhSua,DT) values(3,'GN','Gây nghiện',3,1,1);
insert into dm_phan_nhom_thuoc(MaSo,Ma,Ten,DMPHANNHOMTHUOC_THUTU,NgayChinhSua,DT) values(4,'HT','Hướng thần',4,1,1);
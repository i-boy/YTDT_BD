ALTER TABLE `dm_thuoc` ADD COLUMN `DMTHUOC_ISTONKHOKC` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 AFTER `DMTHUOC_PLBHYT`,
 ADD COLUMN `DMTHUOC_ISTONKHONT` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 AFTER `DMTHUOC_ISTONKHOKC`,
 ADD COLUMN `DMTHUOC_ISTONKHOBH` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 AFTER `DMTHUOC_ISTONKHONT`,
 ADD COLUMN `DMTHUOC_ISTONKHOTE` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 AFTER `DMTHUOC_ISTONKHOBH`;
 
 -- update cac cot tren = 1 neu cac thuoc xem con ton
update dm_thuoc t, v_tonkho_khoa_hientai tkht, dm_khoa k set t.dmthuoc_istonkhont = 1 where t.dmthuoc_maso = tkht.dmthuoc_maso and tkht.dmkhoa_maso = k.dmkhoa_maso and k.dmkhoa_ma = 'KNT' and tkht.tonkho_ton > 0;
update dm_thuoc t, v_tonkho_khoa_hientai tkht, dm_khoa k set t.dmthuoc_istonkhokc = 1 where t.dmthuoc_maso = tkht.dmthuoc_maso and tkht.dmkhoa_maso = k.dmkhoa_maso and k.dmkhoa_ma = 'KCH' and tkht.tonkho_ton > 0;
update dm_thuoc t, v_tonkho_khoa_hientai tkht, dm_khoa k set t.dmthuoc_istonkhobh = 1 where t.dmthuoc_maso = tkht.dmthuoc_maso and tkht.dmkhoa_maso = k.dmkhoa_maso and k.dmkhoa_ma = 'KBH' and tkht.tonkho_ton > 0;
update dm_thuoc t, v_tonkho_khoa_hientai tkht, dm_khoa k set t.dmthuoc_istonkhote = 1 where t.dmthuoc_maso = tkht.dmthuoc_maso and tkht.dmkhoa_maso = k.dmkhoa_maso and k.dmkhoa_ma = 'KTE' and tkht.tonkho_ton > 0;

-- trigger after insert ton_kho doi voi cac kho: KBH, KNT, KTE, KCH
-- Binh Duong
delimiter |

CREATE TRIGGER dmthuoc_update AFTER INSERT ON `ton_kho`
  FOR EACH ROW BEGIN
    IF (NEW.dmkhoa_maso = 35) THEN
    	IF(NEW.tonkho_ton = 0) THEN
			UPDATE dm_thuoc t SET dmthuoc_istonkhokc = 0 WHERE t.dmthuoc_maso = NEW.dmthuoc_maso and NEW.dmkhoa_maso = 35;
	 	ELSE
	 		UPDATE dm_thuoc t SET dmthuoc_istonkhokc = 1 WHERE t.dmthuoc_maso = NEW.dmthuoc_maso and NEW.dmkhoa_maso = 35;
		END IF;	
	ELSEIF (NEW.dmkhoa_maso = 36) THEN
		IF(NEW.tonkho_ton = 0) THEN
			UPDATE dm_thuoc t SET dmthuoc_istonkhobh = 0 WHERE t.dmthuoc_maso = NEW.dmthuoc_maso and NEW.dmkhoa_maso = 36;
		ELSE
			UPDATE dm_thuoc t SET dmthuoc_istonkhobh = 1 WHERE t.dmthuoc_maso = NEW.dmthuoc_maso and NEW.dmkhoa_maso = 36;
		END IF;
	ELSEIF (NEW.dmkhoa_maso = 37) THEN
		IF(NEW.tonkho_ton = 0) THEN
			UPDATE dm_thuoc t SET dmthuoc_istonkhote = 0 WHERE t.dmthuoc_maso = NEW.dmthuoc_maso and NEW.dmkhoa_maso = 37;
		ELSE
			UPDATE dm_thuoc t SET dmthuoc_istonkhote = 1 WHERE t.dmthuoc_maso = NEW.dmthuoc_maso and NEW.dmkhoa_maso = 37;
		END IF;
	ELSEIF (NEW.dmkhoa_maso = 39) THEN
		IF(NEW.tonkho_ton = 0) THEN
			UPDATE dm_thuoc t SET dmthuoc_istonkhont = 0 WHERE t.dmthuoc_maso = NEW.dmthuoc_maso and NEW.dmkhoa_maso = 39;
		ELSE
			UPDATE dm_thuoc t SET dmthuoc_istonkhont = 0 WHERE t.dmthuoc_maso = NEW.dmthuoc_maso and NEW.dmkhoa_maso = 39;
		END IF;
	END IF;
  END;
|

delimiter ;
-- Dien Khanh
delimiter |

CREATE TRIGGER dmthuoc_update AFTER INSERT ON `ton_kho`
  FOR EACH ROW BEGIN
	IF (NEW.dmkhoa_maso = 13) THEN
		IF(NEW.tonkho_ton = 0) THEN
			UPDATE dm_thuoc t SET dmthuoc_istonkhokc = 0 WHERE t.dmthuoc_maso = NEW.dmthuoc_maso and NEW.dmkhoa_maso = 13;
		ELSE
			UPDATE dm_thuoc t SET dmthuoc_istonkhokc = 1 WHERE t.dmthuoc_maso = NEW.dmthuoc_maso and NEW.dmkhoa_maso = 13;
		END IF;
	ELSEIF (NEW.dmkhoa_maso = 15) THEN
		IF(NEW.tonkho_ton = 0) THEN
			UPDATE dm_thuoc t SET dmthuoc_istonkhobh = 0 WHERE t.dmthuoc_maso = NEW.dmthuoc_maso and NEW.dmkhoa_maso = 15;
		ELSE
			UPDATE dm_thuoc t SET dmthuoc_istonkhobh = 1 WHERE t.dmthuoc_maso = NEW.dmthuoc_maso and NEW.dmkhoa_maso = 15;
		END IF;
	ELSE 
		IF(NEW.tonkho_ton = 0) THEN
			UPDATE dm_thuoc t SET dmthuoc_istonkhont = 0 WHERE t.dmthuoc_maso = NEW.dmthuoc_maso and NEW.dmkhoa_maso = 14;
		ELSE
			UPDATE dm_thuoc t SET dmthuoc_istonkhont = 1 WHERE t.dmthuoc_maso = NEW.dmthuoc_maso and NEW.dmkhoa_maso = 14;
		END IF;
	END IF;
  END;
|

delimiter ;

-- van ninh
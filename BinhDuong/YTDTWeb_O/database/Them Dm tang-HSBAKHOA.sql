ALTER TABLE DB_YTDT_BD.DM_TANG
 DROP PRIMARY KEY CASCADE;

DROP TABLE DB_YTDT_BD.DM_TANG CASCADE CONSTRAINTS;

CREATE TABLE DB_YTDT_BD.DM_TANG
(
  DMTANG_MASO        NUMBER(10)                 NOT NULL,
  DMTANG_MA          VARCHAR2(5 BYTE)           NOT NULL,
  DMTANG_TEN         VARCHAR2(50 BYTE)          NOT NULL,
  DMKHOA_MASO        NUMBER(10)                 NOT NULL,
  DMTANG_DEFAULT     NUMBER(3),
  DMTANG_NGAYGIOCN   DATE,
  DMTANG_NHANVIENCN  NUMBER(10),
  DMTANG_CHON        NUMBER(3)
)
TABLESPACE USERS
RESULT_CACHE (MODE DEFAULT)
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
            FLASH_CACHE      DEFAULT
            CELL_FLASH_CACHE DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE UNIQUE INDEX DB_YTDT_BD.DM_TANG_PK ON DB_YTDT_BD.DM_TANG
(DMTANG_MASO)
LOGGING
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
            FLASH_CACHE      DEFAULT
            CELL_FLASH_CACHE DEFAULT
           )
NOPARALLEL;


ALTER TABLE DB_YTDT_BD.DM_TANG ADD (
  CONSTRAINT DM_TANG_PK
  PRIMARY KEY
  (DMTANG_MASO)
  USING INDEX DB_YTDT_BD.DM_TANG_PK
  ENABLE VALIDATE);

ALTER TABLE DB_YTDT_BD.DM_TANG ADD (
  CONSTRAINT FK_DM_TANG_DM_KHOA 
  FOREIGN KEY (DMKHOA_MASO) 
  REFERENCES DB_YTDT_BD.DM_KHOA (DMKHOA_MASO)
  ENABLE VALIDATE,
  CONSTRAINT FK_DM_TANG_DM_NHANVIEN 
  FOREIGN KEY (DMTANG_NHANVIENCN) 
  REFERENCES DB_YTDT_BD.DT_DM_NHAN_VIEN (DTDMNHANVIEN_MASO)
  ENABLE VALIDATE);


ALTER TABLE DB_YTDT_BD.HSBA_KHOA
 ADD (DMTANG_MASO  NUMBER(10))
;

ALTER TABLE DB_YTDT_BD.HSBA_KHOA
 ADD CONSTRAINT FK_hsba_khoa_tang 
  FOREIGN KEY (DMTANG_MASO) 
  REFERENCES DB_YTDT_BD.DM_TANG (DMTANG_MASO)
  ENABLE VALIDATE
;

CREATE SEQUENCE DM_TANG_DMTANG_MASO_SEQ INCREMENT BY 1 START WITH 1 
MAXVALUE 9999999999999999999999999  MINVALUE 1 NOCACHE;

DROP TRIGGER DB_YTDT_BD.DM_TANG_DMTANG_MASO_TRG;

CREATE OR REPLACE TRIGGER DB_YTDT_BD.DM_TANG_DMTANG_MASO_TRG BEFORE INSERT OR UPDATE ON DB_YTDT_BD.DM_TANG
FOR EACH ROW
DECLARE 
v_newVal NUMBER(12) := 0;
v_incval NUMBER(12) := 0;
BEGIN
  IF INSERTING AND :new.DMTANG_MASO IS NULL THEN
    SELECT  DM_TANG_DMTANG_MASO_SEQ.NEXTVAL INTO v_newVal FROM DUAL;
    -- If this is the first time this table have been inserted into (sequence == 1)
    IF v_newVal = 1 THEN 
      --get the max indentity value from the table
      SELECT NVL(max(DMTANG_MASO),0) INTO v_newVal FROM dm_tang;
      v_newVal := v_newVal + 1;
      --set the sequence to that value
      LOOP
           EXIT WHEN v_incval>=v_newVal;
           SELECT DM_TANG_DMTANG_MASO_SEQ.nextval INTO v_incval FROM dual;
      END LOOP;
    END IF;
    --used to emulate LAST_INSERT_ID()
    --mysql_utilities.identity := v_newVal; 
   -- assign the value from the sequence to emulate the identity column
   :new.DMTANG_MASO := v_newVal;
  END IF;
END;

ALTER TABLE DB_YTDT_BD.PHIEU_DU_TRU
 ADD (DMTANG_MASO  NUMBER(10))
;

ALTER TABLE DB_YTDT_BD.PHIEU_DU_TRU
 ADD CONSTRAINT FK_PHIEU_DU_TRU_DMTANG_MASO
  FOREIGN KEY (DMTANG_MASO) 
  REFERENCES DB_YTDT_BD.DM_TANG (DMTANG_MASO)
  ENABLE VALIDATE
;


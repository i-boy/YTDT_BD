﻿ALTER TABLE "DB_YTDT_BD"."PHIEU_KHAM_DT_NGOAI_TRU"
ADD ( "PKDTNT_BENHNHAN" VARCHAR2(17) NULL  ) 
ADD ( "PKDTNT_SOTHEBH" VARCHAR2(50) NULL  ) 
ADD ( "PKDTNT_BANKHAM" NUMBER(10) NULL  ) 
ADD ( "PKDTNT_SOVAOVIEN" VARCHAR2(17) NULL  ) ;

ALTER TABLE "DB_YTDT_BD"."PHIEU_KHAM_DT_NGOAI_TRU" ADD CONSTRAINT "FK_PKDTNT_BENHNHAN" FOREIGN KEY ("PKDTNT_BENHNHAN") REFERENCES "DB_YTDT_BD"."BENH_NHAN" ("BENHNHAN_MA");

ALTER TABLE "DB_YTDT_BD"."PHIEU_KHAM_DT_NGOAI_TRU" ADD CONSTRAINT "FK_PKDTNT_BANKHAM" FOREIGN KEY ("PKDTNT_BANKHAM") REFERENCES "DB_YTDT_BD"."DT_DM_BAN_KHAM" ("DTDMBANKHAM_MASO");
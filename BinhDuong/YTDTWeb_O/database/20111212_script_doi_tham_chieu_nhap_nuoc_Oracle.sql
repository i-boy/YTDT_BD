﻿ALTER TABLE "DB_YTDT_BD"."NHAP_NUOC"
ADD ( "DMTANG_MASO" NUMBER(10) NULL  ) ;

ALTER TABLE "DB_YTDT_BD"."NHAP_NUOC" ADD CONSTRAINT "FK_NHAP_NUOC_3" FOREIGN KEY ("DMTANG_MASO") REFERENCES "DB_YTDT_BD"."DM_TANG" ("DMTANG_MASO");
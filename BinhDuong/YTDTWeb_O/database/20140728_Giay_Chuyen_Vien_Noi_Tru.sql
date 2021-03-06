ALTER TABLE HSBA_CHUYEN_VIEN 
ADD (DIEUTRI1_DONVI NUMBER(10) );

ALTER TABLE HSBA_CHUYEN_VIEN 
ADD (DIEUTRI1_TUYEN VARCHAR2(10) );

ALTER TABLE HSBA_CHUYEN_VIEN 
ADD (DIEUTRI1_TUNGAY DATE );

ALTER TABLE HSBA_CHUYEN_VIEN 
ADD (DIEUTRI1_DENNGAY DATE );

ALTER TABLE HSBA_CHUYEN_VIEN 
ADD (HSBACV_HUONGDIEUTRI VARCHAR2(512) );

ALTER TABLE HSBA_CHUYEN_VIEN 
ADD (HSBACV_LYDO_RADIO VARCHAR2(10) );

CREATE INDEX IX_HSBA_CV_DT1_DONVI ON HSBA_CHUYEN_VIEN (DIEUTRI1_DONVI ASC);

ALTER TABLE HSBA_CHUYEN_VIEN
ADD CONSTRAINT FK_HSBA_CV_DT1_DONVI FOREIGN KEY
(
  DIEUTRI1_DONVI 
)
REFERENCES DM_BENH_VIEN
(
  DMBENHVIEN_MASO 
)
ENABLE;

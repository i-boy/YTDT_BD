﻿ALTER TABLE `yte_sequence` ADD UNIQUE INDEX `IDX_SEQUENCE_NAME_MA` USING BTREE(`SEQUENCE_NAME_MA`);

update yte_sequence set SEQUENCE_RESET_YEAR = 1 where sequence_name_ma in ('BENHNHAN_MA', 'TIEPDON_MA','SOVAOVIEN','SOLUUTRU','BA_CAPCUULUU');
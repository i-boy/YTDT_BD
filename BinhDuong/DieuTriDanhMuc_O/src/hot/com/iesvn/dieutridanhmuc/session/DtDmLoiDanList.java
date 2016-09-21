package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmLoiDanList")
public class DtDmLoiDanList extends EntityQuery<DtDmLoiDan> {

	private static final String EJBQL = "select dtDmLoiDan from DtDmLoiDan dtDmLoiDan";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmLoiDan.dtdmldMa) like concat('%',lower(#{dtDmLoiDanList.dtDmLoiDan.dtdmldMa}),'%')",
			"lower(dtDmLoiDan.dtdmldTen) like concat('%',lower(#{dtDmLoiDanList.dtDmLoiDan.dtdmldTen}),'%')", };

	private DtDmLoiDan dtDmLoiDan = new DtDmLoiDan();

	public DtDmLoiDanList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmLoiDan getDtDmLoiDan() {
		return dtDmLoiDan;
	}
}

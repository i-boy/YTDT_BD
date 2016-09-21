package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmLoaiAn2List")
public class DtDmLoaiAn2List extends EntityQuery<DtDmLoaiAn2> {

	private static final String EJBQL = "select dtDmLoaiAn2 from DtDmLoaiAn2 dtDmLoaiAn2";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmLoaiAn2.dtdmla2Ma) like concat('%',lower(#{dtDmLoaiAn2List.dtDmLoaiAn2.dtdmla2Ma}),'%')",
			"lower(dtDmLoaiAn2.dtdmla2Ten) like concat('%',lower(#{dtDmLoaiAn2List.dtDmLoaiAn2.dtdmla2Ten}),'%')", };

	private DtDmLoaiAn2 dtDmLoaiAn2 = new DtDmLoaiAn2();

	public DtDmLoaiAn2List() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmLoaiAn2 getDtDmLoaiAn2() {
		return dtDmLoaiAn2;
	}
}

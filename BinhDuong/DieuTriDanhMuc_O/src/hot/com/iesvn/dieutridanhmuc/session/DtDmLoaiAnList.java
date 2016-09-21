package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmLoaiAnList")
public class DtDmLoaiAnList extends EntityQuery<DtDmLoaiAn> {

	private static final String EJBQL = "select dtDmLoaiAn from DtDmLoaiAn dtDmLoaiAn";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmLoaiAn.dtdmlaMa) like concat('%',lower(#{dtDmLoaiAnList.dtDmLoaiAn.dtdmlaMa}),'%')",
			"lower(dtDmLoaiAn.dtdmlaTen) like concat('%',lower(#{dtDmLoaiAnList.dtDmLoaiAn.dtdmlaTen}),'%')", };

	private DtDmLoaiAn dtDmLoaiAn = new DtDmLoaiAn();

	public DtDmLoaiAnList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmLoaiAn getDtDmLoaiAn() {
		return dtDmLoaiAn;
	}
}

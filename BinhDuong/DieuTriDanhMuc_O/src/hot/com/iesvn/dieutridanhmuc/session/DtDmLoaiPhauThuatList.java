package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmLoaiPhauThuatList")
public class DtDmLoaiPhauThuatList extends EntityQuery<DtDmLoaiPhauThuat> {

	private static final String EJBQL = "select dtDmLoaiPhauThuat from DtDmLoaiPhauThuat dtDmLoaiPhauThuat";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmLoaiPhauThuat.dtdmloaiptMa) like concat('%',lower(#{dtDmLoaiPhauThuatList.dtDmLoaiPhauThuat.dtdmloaiptMa}),'%')",
			"lower(dtDmLoaiPhauThuat.dtdmloaiptTen) like concat('%',lower(#{dtDmLoaiPhauThuatList.dtDmLoaiPhauThuat.dtdmloaiptTen}),'%')", };

	private DtDmLoaiPhauThuat dtDmLoaiPhauThuat = new DtDmLoaiPhauThuat();

	public DtDmLoaiPhauThuatList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmLoaiPhauThuatList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DtDmLoaiPhauThuat getDtDmLoaiPhauThuat() {
		return dtDmLoaiPhauThuat;
	}
}

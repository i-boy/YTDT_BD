package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmDieuTriList")
public class DmDieuTriList extends EntityQuery<DmDieuTri> {

	private static final String EJBQL = "select dmDieuTri from DmDieuTri dmDieuTri";

	private static final String[] RESTRICTIONS = {
			"lower(dmDieuTri.dmdieutriDiengiai1) like concat('%',lower(#{dmDieuTriList.dmDieuTri.dmdieutriDiengiai1}),'%')",
			"lower(dmDieuTri.dmdieutriDiengiai2) like concat('%',lower(#{dmDieuTriList.dmDieuTri.dmdieutriDiengiai2}),'%')",
			"lower(dmDieuTri.dmdieutriMa) like concat('%',lower(#{dmDieuTriList.dmDieuTri.dmdieutriMa}),'%')",
			"lower(dmDieuTri.dmdieutriTen) like concat('%',lower(#{dmDieuTriList.dmDieuTri.dmdieutriTen}),'%')", };

	private DmDieuTri dmDieuTri = new DmDieuTri();

	public DmDieuTriList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmDieuTri getDmDieuTri() {
		return dmDieuTri;
	}
}

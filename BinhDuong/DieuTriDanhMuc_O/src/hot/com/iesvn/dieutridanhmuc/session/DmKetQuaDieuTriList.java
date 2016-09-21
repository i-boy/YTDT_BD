package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmKetQuaDieuTriList")
public class DmKetQuaDieuTriList extends EntityQuery<DmKetQuaDieuTri> {

	private static final String EJBQL = "select dmKetQuaDieuTri from DmKetQuaDieuTri dmKetQuaDieuTri";

	private static final String[] RESTRICTIONS = {
			"lower(dmKetQuaDieuTri.dmkqdtGhichu) like concat('%',lower(#{dmKetQuaDieuTriList.dmKetQuaDieuTri.dmkqdtGhichu}),'%')",
			"lower(dmKetQuaDieuTri.dmkqdtMa) like concat('%',lower(#{dmKetQuaDieuTriList.dmKetQuaDieuTri.dmkqdtMa}),'%')",
			"lower(dmKetQuaDieuTri.dmkqdtTen) like concat('%',lower(#{dmKetQuaDieuTriList.dmKetQuaDieuTri.dmkqdtTen}),'%')", };

	private DmKetQuaDieuTri dmKetQuaDieuTri = new DmKetQuaDieuTri();

	public DmKetQuaDieuTriList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmKetQuaDieuTri getDmKetQuaDieuTri() {
		return dmKetQuaDieuTri;
	}
}

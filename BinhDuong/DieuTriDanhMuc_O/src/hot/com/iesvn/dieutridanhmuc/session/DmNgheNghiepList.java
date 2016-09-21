package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmNgheNghiepList")
public class DmNgheNghiepList extends EntityQuery<DmNgheNghiep> {

	private static final String EJBQL = "select dmNgheNghiep from DmNgheNghiep dmNgheNghiep";

	private static final String[] RESTRICTIONS = {
			"lower(dmNgheNghiep.dmnghenghiepMa) like concat('%',lower(#{dmNgheNghiepList.dmNgheNghiep.dmnghenghiepMa}),'%')",
			"lower(dmNgheNghiep.dmnghenghiepPhanloai2) like concat('%',lower(#{dmNgheNghiepList.dmNgheNghiep.dmnghenghiepPhanloai2}),'%')",
			"lower(dmNgheNghiep.dmnghenghiepTen) like concat('%',lower(#{dmNgheNghiepList.dmNgheNghiep.dmnghenghiepTen}),'%')", };

	private DmNgheNghiep dmNgheNghiep = new DmNgheNghiep();

	public DmNgheNghiepList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmNgheNghiepList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DmNgheNghiep getDmNgheNghiep() {
		return dmNgheNghiep;
	}
}

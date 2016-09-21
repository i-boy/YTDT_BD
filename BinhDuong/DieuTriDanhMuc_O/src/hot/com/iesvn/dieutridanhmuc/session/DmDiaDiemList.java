package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmDiaDiemList")
public class DmDiaDiemList extends EntityQuery<DmDiaDiem> {

	private static final String EJBQL = "select dmDiaDiem from DmDiaDiem dmDiaDiem";

	private static final String[] RESTRICTIONS = {
			"lower(dmDiaDiem.dmdiadiemMa) like concat('%',lower(#{dmDiaDiemList.dmDiaDiem.dmdiadiemMa}),'%')",
			"lower(dmDiaDiem.dmdiadiemTen) like concat('%',lower(#{dmDiaDiemList.dmDiaDiem.dmdiadiemTen}),'%')", };

	private DmDiaDiem dmDiaDiem = new DmDiaDiem();

	public DmDiaDiemList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmDiaDiemList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DmDiaDiem getDmDiaDiem() {
		return dmDiaDiem;
	}
}

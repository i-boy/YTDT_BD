package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmNguonKinhPhiList")
public class DmNguonKinhPhiList extends EntityQuery<DmNguonKinhPhi> {

	private static final String EJBQL = "select dmNguonKinhPhi from DmNguonKinhPhi dmNguonKinhPhi";

	private static final String[] RESTRICTIONS = {
			"lower(dmNguonKinhPhi.dmnguonkinhphiMa) like concat('%',lower(#{dmNguonKinhPhiList.dmNguonKinhPhi.dmnguonkinhphiMa}),'%')",
			"lower(dmNguonKinhPhi.dmnguonkinhphiTen) like concat('%',lower(#{dmNguonKinhPhiList.dmNguonKinhPhi.dmnguonkinhphiTen}),'%')", };

	private DmNguonKinhPhi dmNguonKinhPhi = new DmNguonKinhPhi();

	public DmNguonKinhPhiList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmNguonKinhPhiList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DmNguonKinhPhi getDmNguonKinhPhi() {
		return dmNguonKinhPhi;
	}
}

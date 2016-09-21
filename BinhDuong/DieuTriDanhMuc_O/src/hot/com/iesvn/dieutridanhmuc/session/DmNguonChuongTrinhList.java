package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmNguonChuongTrinhList")
public class DmNguonChuongTrinhList extends EntityQuery<DmNguonChuongTrinh> {

	private static final String EJBQL = "select dmNguonChuongTrinh from DmNguonChuongTrinh dmNguonChuongTrinh";

	private static final String[] RESTRICTIONS = {
			"lower(dmNguonChuongTrinh.dmnctMa) like concat('%',lower(#{dmNguonChuongTrinhList.dmNguonChuongTrinh.dmnctMa}),'%')",
			"lower(dmNguonChuongTrinh.dmnctTen) like concat('%',lower(#{dmNguonChuongTrinhList.dmNguonChuongTrinh.dmnctTen}),'%')", };

	private DmNguonChuongTrinh dmNguonChuongTrinh = new DmNguonChuongTrinh();

	public DmNguonChuongTrinhList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmNguonChuongTrinhList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DmNguonChuongTrinh getDmNguonChuongTrinh() {
		return dmNguonChuongTrinh;
	}
}

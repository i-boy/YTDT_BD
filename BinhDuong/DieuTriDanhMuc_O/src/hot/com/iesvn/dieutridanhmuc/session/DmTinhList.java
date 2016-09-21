package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmTinhList")
public class DmTinhList extends EntityQuery<DmTinh> {

	private static final String EJBQL = "select dmTinh from DmTinh dmTinh";

	private static final String[] RESTRICTIONS = {
			"lower(dmTinh.dmtinhMa) like concat('%',lower(#{dmTinhList.dmTinh.dmtinhMa}),'%')",
			"lower(dmTinh.dmtinhMabhyt) like concat('%',lower(#{dmTinhList.dmTinh.dmtinhMabhyt}),'%')",
			"lower(dmTinh.dmtinhTen) like concat('%',lower(#{dmTinhList.dmTinh.dmtinhTen}),'%')", };

	private DmTinh dmTinh = new DmTinh();

	public DmTinhList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmTinhList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DmTinh getDmTinh() {
		return dmTinh;
	}
}

package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmGioiTinhList")
public class DmGioiTinhList extends EntityQuery<DmGioiTinh> {

	private static final String EJBQL = "select dmGioiTinh from DmGioiTinh dmGioiTinh";

	private static final String[] RESTRICTIONS = {
			"lower(dmGioiTinh.dmgtGhichu) like concat('%',lower(#{dmGioiTinhList.dmGioiTinh.dmgtGhichu}),'%')",
			"lower(dmGioiTinh.dmgtMa) like concat('%',lower(#{dmGioiTinhList.dmGioiTinh.dmgtMa}),'%')",
			"lower(dmGioiTinh.dmgtTen) like concat('%',lower(#{dmGioiTinhList.dmGioiTinh.dmgtTen}),'%')", };

	private DmGioiTinh dmGioiTinh = new DmGioiTinh();

	public DmGioiTinhList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmGioiTinh getDmGioiTinh() {
		return dmGioiTinh;
	}
}

package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmBenhTruyenNhiemList")
public class DmBenhTruyenNhiemList extends EntityQuery<DmBenhTruyenNhiem> {

	private static final String EJBQL = "select dmBenhTruyenNhiem from DmBenhTruyenNhiem dmBenhTruyenNhiem";

	private static final String[] RESTRICTIONS = {
			"lower(dmBenhTruyenNhiem.dmbtnGhichu) like concat('%',lower(#{dmBenhTruyenNhiemList.dmBenhTruyenNhiem.dmbtnGhichu}),'%')",
			"lower(dmBenhTruyenNhiem.dmbtnIcd10) like concat('%',lower(#{dmBenhTruyenNhiemList.dmBenhTruyenNhiem.dmbtnIcd10}),'%')",
			"lower(dmBenhTruyenNhiem.dmbtnTc) like concat('%',lower(#{dmBenhTruyenNhiemList.dmBenhTruyenNhiem.dmbtnTc}),'%')",
			"lower(dmBenhTruyenNhiem.dmbtnYtdt) like concat('%',lower(#{dmBenhTruyenNhiemList.dmBenhTruyenNhiem.dmbtnYtdt}),'%')", };

	private DmBenhTruyenNhiem dmBenhTruyenNhiem = new DmBenhTruyenNhiem();

	public DmBenhTruyenNhiemList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmBenhTruyenNhiem getDmBenhTruyenNhiem() {
		return dmBenhTruyenNhiem;
	}
}

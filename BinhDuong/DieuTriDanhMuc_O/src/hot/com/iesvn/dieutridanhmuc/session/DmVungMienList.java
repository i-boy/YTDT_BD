package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmVungMienList")
public class DmVungMienList extends EntityQuery<DmVungMien> {

	private static final String EJBQL = "select dmVungMien from DmVungMien dmVungMien";

	private static final String[] RESTRICTIONS = {
			"lower(dmVungMien.dmvungmienMa) like concat('%',lower(#{dmVungMienList.dmVungMien.dmvungmienMa}),'%')",
			"lower(dmVungMien.dmvungmienTen) like concat('%',lower(#{dmVungMienList.dmVungMien.dmvungmienTen}),'%')", };

	private DmVungMien dmVungMien = new DmVungMien();

	public DmVungMienList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmVungMien getDmVungMien() {
		return dmVungMien;
	}
}

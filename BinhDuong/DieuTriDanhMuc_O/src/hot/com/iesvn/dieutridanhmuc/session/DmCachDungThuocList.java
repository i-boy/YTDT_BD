package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmCachDungThuocList")
public class DmCachDungThuocList extends EntityQuery<DmCachDungThuoc> {

	private static final String EJBQL = "select dmCachDungThuoc from DmCachDungThuoc dmCachDungThuoc";

	private static final String[] RESTRICTIONS = {
			"lower(dmCachDungThuoc.dmcachdungthuocMa) like concat('%',lower(#{dmCachDungThuocList.dmCachDungThuoc.dmcachdungthuocMa}),'%')",
			"lower(dmCachDungThuoc.dmcachdungthuocMaphu) like concat('%',lower(#{dmCachDungThuocList.dmCachDungThuoc.dmcachdungthuocMaphu}),'%')",
			"lower(dmCachDungThuoc.dmcachdungthuocTen) like concat('%',lower(#{dmCachDungThuocList.dmCachDungThuoc.dmcachdungthuocTen}),'%')", };

	private DmCachDungThuoc dmCachDungThuoc = new DmCachDungThuoc();

	public DmCachDungThuocList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmCachDungThuoc getDmCachDungThuoc() {
		return dmCachDungThuoc;
	}
}

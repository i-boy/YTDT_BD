package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmBaiThuocList")
public class DmBaiThuocList extends EntityQuery<DmBaiThuoc> {

	private static final String EJBQL = "select dmBaiThuoc from DmBaiThuoc dmBaiThuoc";

	private static final String[] RESTRICTIONS = {
			"lower(dmBaiThuoc.dmbaithuocGhichu) like concat('%',lower(#{dmBaiThuocList.dmBaiThuoc.dmbaithuocGhichu}),'%')",
			"lower(dmBaiThuoc.dmbaithuocMa) like concat('%',lower(#{dmBaiThuocList.dmBaiThuoc.dmbaithuocMa}),'%')",
			"lower(dmBaiThuoc.dmbaithuocTen) like concat('%',lower(#{dmBaiThuocList.dmBaiThuoc.dmbaithuocTen}),'%')", };

	private DmBaiThuoc dmBaiThuoc = new DmBaiThuoc();

	public DmBaiThuocList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmBaiThuoc getDmBaiThuoc() {
		return dmBaiThuoc;
	}
}

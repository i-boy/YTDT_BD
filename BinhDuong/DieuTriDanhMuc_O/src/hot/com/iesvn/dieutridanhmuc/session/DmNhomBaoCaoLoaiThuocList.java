package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmNhomBaoCaoLoaiThuocList")
public class DmNhomBaoCaoLoaiThuocList extends
		EntityQuery<DmNhomBaoCaoLoaiThuoc> {

	private static final String EJBQL = "select dmNhomBaoCaoLoaiThuoc from DmNhomBaoCaoLoaiThuoc dmNhomBaoCaoLoaiThuoc";

	private static final String[] RESTRICTIONS = {};

	private DmNhomBaoCaoLoaiThuoc dmNhomBaoCaoLoaiThuoc = new DmNhomBaoCaoLoaiThuoc();

	public DmNhomBaoCaoLoaiThuocList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmNhomBaoCaoLoaiThuocList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DmNhomBaoCaoLoaiThuoc getDmNhomBaoCaoLoaiThuoc() {
		return dmNhomBaoCaoLoaiThuoc;
	}
}

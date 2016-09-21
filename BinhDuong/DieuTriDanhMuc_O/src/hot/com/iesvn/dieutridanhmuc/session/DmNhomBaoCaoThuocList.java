package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmNhomBaoCaoThuocList")
public class DmNhomBaoCaoThuocList extends EntityQuery<DmNhomBaoCaoThuoc> {

	private static final String EJBQL = "select dmNhomBaoCaoThuoc from DmNhomBaoCaoThuoc dmNhomBaoCaoThuoc";

	private static final String[] RESTRICTIONS = {
			"lower(dmNhomBaoCaoThuoc.dmnhanvienCn) like concat('%',lower(#{dmNhomBaoCaoThuocList.dmNhomBaoCaoThuoc.dmnhanvienCn}),'%')",
			"lower(dmNhomBaoCaoThuoc.dmnhombcthuocGhichu) like concat('%',lower(#{dmNhomBaoCaoThuocList.dmNhomBaoCaoThuoc.dmnhombcthuocGhichu}),'%')",
			"lower(dmNhomBaoCaoThuoc.dmnhombcthuocMa) like concat('%',lower(#{dmNhomBaoCaoThuocList.dmNhomBaoCaoThuoc.dmnhombcthuocMa}),'%')",
			"lower(dmNhomBaoCaoThuoc.dmnhombcthuocMaphu) like concat('%',lower(#{dmNhomBaoCaoThuocList.dmNhomBaoCaoThuoc.dmnhombcthuocMaphu}),'%')",
			"lower(dmNhomBaoCaoThuoc.dmnhombcthuocTen) like concat('%',lower(#{dmNhomBaoCaoThuocList.dmNhomBaoCaoThuoc.dmnhombcthuocTen}),'%')",
			"lower(dmNhomBaoCaoThuoc.dmnhombcthuocThutu) like concat('%',lower(#{dmNhomBaoCaoThuocList.dmNhomBaoCaoThuoc.dmnhombcthuocThutu}),'%')", };

	private DmNhomBaoCaoThuoc dmNhomBaoCaoThuoc = new DmNhomBaoCaoThuoc();

	public DmNhomBaoCaoThuocList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmNhomBaoCaoThuocList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));		
	}

	public DmNhomBaoCaoThuoc getDmNhomBaoCaoThuoc() {
		return dmNhomBaoCaoThuoc;
	}
}

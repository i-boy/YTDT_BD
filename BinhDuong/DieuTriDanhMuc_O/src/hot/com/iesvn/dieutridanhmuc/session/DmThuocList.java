package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmThuocList")
public class DmThuocList extends EntityQuery<DmThuoc> {

	private static final String EJBQL = "select dmThuoc from DmThuoc dmThuoc";

	private static final String[] RESTRICTIONS = {
			"lower(dmThuoc.dmthuocCapphat) like concat('%',lower(#{dmThuocList.dmThuoc.dmthuocCapphat}),'%')",
			"lower(dmThuoc.dmthuocDacbiet) like concat('%',lower(#{dmThuocList.dmThuoc.dmthuocDacbiet}),'%')",
			"lower(dmThuoc.dmthuocGchu) like concat('%',lower(#{dmThuocList.dmThuoc.dmthuocGchu}),'%')",
			"lower(dmThuoc.dmthuocHamluong) like concat('%',lower(#{dmThuocList.dmThuoc.dmthuocHamluong}),'%')",
			"lower(dmThuoc.dmthuocMa) like concat('%',lower(#{dmThuocList.dmThuoc.dmthuocMa}),'%')",
			"lower(dmThuoc.dmthuocMahang2) like concat('%',lower(#{dmThuocList.dmThuoc.dmthuocMahang2}),'%')",
			"lower(dmThuoc.dmthuocMaphu) like concat('%',lower(#{dmThuocList.dmThuoc.dmthuocMaphu}),'%')",
			"lower(dmThuoc.dmthuocNuoc) like concat('%',lower(#{dmThuocList.dmThuoc.dmthuocNuoc}),'%')",
			"lower(dmThuoc.dmthuocPhankhob) like concat('%',lower(#{dmThuocList.dmThuoc.dmthuocPhankhob}),'%')",
			"lower(dmThuoc.dmthuocPhankhoc) like concat('%',lower(#{dmThuocList.dmThuoc.dmthuocPhankhoc}),'%')",
			"lower(dmThuoc.dmthuocPhankhol) like concat('%',lower(#{dmThuocList.dmThuoc.dmthuocPhankhol}),'%')",
			"lower(dmThuoc.dmthuocSets) like concat('%',lower(#{dmThuocList.dmThuoc.dmthuocSets}),'%')",
			"lower(dmThuoc.dmthuocSodk) like concat('%',lower(#{dmThuocList.dmThuoc.dmthuocSodk}),'%')",
			"lower(dmThuoc.dmthuocTen) like concat('%',lower(#{dmThuocList.dmThuoc.dmthuocTen}),'%')",
			"lower(dmThuoc.dmthuocTenkh) like concat('%',lower(#{dmThuocList.dmThuoc.dmthuocTenkh}),'%')",
			"lower(dmThuoc.dmthuocThutu) like concat('%',lower(#{dmThuocList.dmThuoc.dmthuocThutu}),'%')",
			"lower(dmThuoc.dtdmnhanvienCn) like concat('%',lower(#{dmThuocList.dmThuoc.dtdmnhanvienCn}),'%')", };

	private DmThuoc dmThuoc = new DmThuoc();

	public DmThuocList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmThuocList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));		
	}

	public DmThuoc getDmThuoc() {
		return dmThuoc;
	}
}

package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmPhanNhomThuocList")
public class DmPhanNhomThuocList extends EntityQuery<DmPhanNhomThuoc> {

	private static final String EJBQL = "select dmPhanNhomThuoc from DmPhanNhomThuoc dmPhanNhomThuoc";

	private static final String[] RESTRICTIONS = {
			"lower(dmPhanNhomThuoc.dmnhanvienCn) like concat('%',lower(#{dmPhanNhomThuocList.dmPhanNhomThuoc.dmnhanvienCn}),'%')",
			"lower(dmPhanNhomThuoc.dmphannhomthuocGhichu) like concat('%',lower(#{dmPhanNhomThuocList.dmPhanNhomThuoc.dmphannhomthuocGhichu}),'%')",
			"lower(dmPhanNhomThuoc.dmphannhomthuocMa) like concat('%',lower(#{dmPhanNhomThuocList.dmPhanNhomThuoc.dmphannhomthuocMa}),'%')",
			"lower(dmPhanNhomThuoc.dmphannhomthuocMaphu) like concat('%',lower(#{dmPhanNhomThuocList.dmPhanNhomThuoc.dmphannhomthuocMaphu}),'%')",
			"lower(dmPhanNhomThuoc.dmphannhomthuocTen) like concat('%',lower(#{dmPhanNhomThuocList.dmPhanNhomThuoc.dmphannhomthuocTen}),'%')",
			"lower(dmPhanNhomThuoc.dmphannhomthuocThutu) like concat('%',lower(#{dmPhanNhomThuocList.dmPhanNhomThuoc.dmphannhomthuocThutu}),'%')", };

	private DmPhanNhomThuoc dmPhanNhomThuoc = new DmPhanNhomThuoc();

	public DmPhanNhomThuocList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmPhanNhomThuoc getDmPhanNhomThuoc() {
		return dmPhanNhomThuoc;
	}
}

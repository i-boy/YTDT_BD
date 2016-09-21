package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmBenhVienList")
public class DmBenhVienList extends EntityQuery<DmBenhVien> {

	private static final String EJBQL = "select dmBenhVien from DmBenhVien dmBenhVien";

	private static final String[] RESTRICTIONS = {
			"lower(dmBenhVien.dmBenhvienPrefixma) like concat('%',lower(#{dmBenhVienList.dmBenhVien.dmBenhvienPrefixma}),'%')",
			"lower(dmBenhVien.dmbenhvienDiachi) like concat('%',lower(#{dmBenhVienList.dmBenhVien.dmbenhvienDiachi}),'%')",
			"lower(dmBenhVien.dmbenhvienDienthoai) like concat('%',lower(#{dmBenhVienList.dmBenhVien.dmbenhvienDienthoai}),'%')",
			"lower(dmBenhVien.dmbenhvienMa) like concat('%',lower(#{dmBenhVienList.dmBenhVien.dmbenhvienMa}),'%')",
			"lower(dmBenhVien.dmbenhvienTen) like concat('%',lower(#{dmBenhVienList.dmBenhVien.dmbenhvienTen}),'%')", };

	private DmBenhVien dmBenhVien = new DmBenhVien();

	public DmBenhVienList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmBenhVienList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DmBenhVien getDmBenhVien() {
		return dmBenhVien;
	}
}

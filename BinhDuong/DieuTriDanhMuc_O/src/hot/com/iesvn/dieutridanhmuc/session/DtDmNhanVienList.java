package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmNhanVienList")
public class DtDmNhanVienList extends EntityQuery<DtDmNhanVien> {

	private static final String EJBQL = "select dtDmNhanVien from DtDmNhanVien dtDmNhanVien";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmNhanVien.dtdmnhanvienBienche) like concat('%',lower(#{dtDmNhanVienList.dtDmNhanVien.dtdmnhanvienBienche}),'%')",
			"lower(dtDmNhanVien.dtdmnhanvienDiachi) like concat('%',lower(#{dtDmNhanVienList.dtDmNhanVien.dtdmnhanvienDiachi}),'%')",
			"lower(dtDmNhanVien.dtdmnhanvienEmail) like concat('%',lower(#{dtDmNhanVienList.dtDmNhanVien.dtdmnhanvienEmail}),'%')",
			"lower(dtDmNhanVien.dtdmnhanvienGioitinh) like concat('%',lower(#{dtDmNhanVienList.dtDmNhanVien.dtdmnhanvienGioitinh}),'%')",
			"lower(dtDmNhanVien.dtdmnhanvienMa) like concat('%',lower(#{dtDmNhanVienList.dtDmNhanVien.dtdmnhanvienMa}),'%')",
			"lower(dtDmNhanVien.dtdmnhanvienMahh) like concat('%',lower(#{dtDmNhanVienList.dtDmNhanVien.dtdmnhanvienMahh}),'%')",
			"lower(dtDmNhanVien.dtdmnhanvienMatma) like concat('%',lower(#{dtDmNhanVienList.dtDmNhanVien.dtdmnhanvienMatma}),'%')",
			"lower(dtDmNhanVien.dtdmnhanvienMobile) like concat('%',lower(#{dtDmNhanVienList.dtDmNhanVien.dtdmnhanvienMobile}),'%')",
			"lower(dtDmNhanVien.dtdmnhanvienSobhxh) like concat('%',lower(#{dtDmNhanVienList.dtDmNhanVien.dtdmnhanvienSobhxh}),'%')",
			"lower(dtDmNhanVien.dtdmnhanvienTen) like concat('%',lower(#{dtDmNhanVienList.dtDmNhanVien.dtdmnhanvienTen}),'%')",
			"lower(dtDmNhanVien.dtdmnhanvienTkatm) like concat('%',lower(#{dtDmNhanVienList.dtDmNhanVien.dtdmnhanvienTkatm}),'%')",
			"lower(dtDmNhanVien.nhomMa) like concat('%',lower(#{dtDmNhanVienList.dtDmNhanVien.nhomMa}),'%')", };

	private DtDmNhanVien dtDmNhanVien = new DtDmNhanVien();

	public DtDmNhanVienList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}
	public DtDmNhanVienList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));		
	}

	public DtDmNhanVien getDtDmNhanVien() {
		return dtDmNhanVien;
	}
}

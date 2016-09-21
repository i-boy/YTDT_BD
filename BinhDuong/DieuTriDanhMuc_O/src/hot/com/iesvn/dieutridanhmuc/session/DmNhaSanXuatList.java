package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmNhaSanXuatList")
public class DmNhaSanXuatList extends EntityQuery<DmNhaSanXuat> {

	private static final String EJBQL = "select dmNhaSanXuat from DmNhaSanXuat dmNhaSanXuat";

	private static final String[] RESTRICTIONS = {
			"lower(dmNhaSanXuat.dmnhasanxuatMa) like concat('%',lower(#{dmNhaSanXuatList.dmNhaSanXuat.dmnhasanxuatMa}),'%')",
			"lower(dmNhaSanXuat.dmnhasanxuatPhanbiet) like concat('%',lower(#{dmNhaSanXuatList.dmNhaSanXuat.dmnhasanxuatPhanbiet}),'%')",
			"lower(dmNhaSanXuat.dmnhasanxuatPhanloai) like concat('%',lower(#{dmNhaSanXuatList.dmNhaSanXuat.dmnhasanxuatPhanloai}),'%')",
			"lower(dmNhaSanXuat.dmnhasanxuatTen) like concat('%',lower(#{dmNhaSanXuatList.dmNhaSanXuat.dmnhasanxuatTen}),'%')",
			"lower(dmNhaSanXuat.dmnhasanxuatTenvn) like concat('%',lower(#{dmNhaSanXuatList.dmNhaSanXuat.dmnhasanxuatTenvn}),'%')", };

	private DmNhaSanXuat dmNhaSanXuat = new DmNhaSanXuat();

	public DmNhaSanXuatList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmNhaSanXuatList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DmNhaSanXuat getDmNhaSanXuat() {
		return dmNhaSanXuat;
	}
}

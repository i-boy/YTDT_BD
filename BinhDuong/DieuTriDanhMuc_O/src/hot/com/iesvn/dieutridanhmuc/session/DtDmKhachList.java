package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmKhachList")
public class DtDmKhachList extends EntityQuery<DtDmKhach> {

	private static final String EJBQL = "select dtDmKhach from DtDmKhach dtDmKhach";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmKhach.dtdmkhachMa) like concat('%',lower(#{dtDmKhachList.dtDmKhach.dtdmkhachMa}),'%')",
			"lower(dtDmKhach.dtdmkhachDiachi) like concat('%',lower(#{dtDmKhachList.dtDmKhach.dtdmkhachDiachi}),'%')",
			"lower(dtDmKhach.dtdmkhachDienthoai) like concat('%',lower(#{dtDmKhachList.dtDmKhach.dtdmkhachDienthoai}),'%')",
			"lower(dtDmKhach.dtdmkhachFax) like concat('%',lower(#{dtDmKhachList.dtDmKhach.dtdmkhachFax}),'%')",
			"lower(dtDmKhach.dtdmkhachMabophan) like concat('%',lower(#{dtDmKhachList.dtDmKhach.dtdmkhachMabophan}),'%')",
			"lower(dtDmKhach.dtdmkhachMasothue) like concat('%',lower(#{dtDmKhachList.dtDmKhach.dtdmkhachMasothue}),'%')",
			"lower(dtDmKhach.dtdmkhachPhanbiet) like concat('%',lower(#{dtDmKhachList.dtDmKhach.dtdmkhachPhanbiet}),'%')",
			"lower(dtDmKhach.dtdmkhachTen) like concat('%',lower(#{dtDmKhachList.dtDmKhach.dtdmkhachTen}),'%')", };

	private DtDmKhach dtDmKhach = new DtDmKhach();

	public DtDmKhachList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmKhach getDtDmKhach() {
		return dtDmKhach;
	}
}

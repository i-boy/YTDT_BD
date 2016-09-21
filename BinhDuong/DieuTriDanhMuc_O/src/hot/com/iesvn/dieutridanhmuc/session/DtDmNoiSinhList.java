package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmNoiSinhList")
public class DtDmNoiSinhList extends EntityQuery<DtDmNoiSinh> {

	private static final String EJBQL = "select dtDmNoiSinh from DtDmNoiSinh dtDmNoiSinh";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmNoiSinh.dtdmnoisinhGhichu) like concat('%',lower(#{dtDmNoiSinhList.dtDmNoiSinh.dtdmnoisinhGhichu}),'%')",
			"lower(dtDmNoiSinh.dtdmnoisinhMa) like concat('%',lower(#{dtDmNoiSinhList.dtDmNoiSinh.dtdmnoisinhMa}),'%')",
			"lower(dtDmNoiSinh.dtdmnoisinhMaphu) like concat('%',lower(#{dtDmNoiSinhList.dtDmNoiSinh.dtdmnoisinhMaphu}),'%')",
			"lower(dtDmNoiSinh.dtdmnoisinhPhanloai) like concat('%',lower(#{dtDmNoiSinhList.dtDmNoiSinh.dtdmnoisinhPhanloai}),'%')",
			"lower(dtDmNoiSinh.dtdmnoisinhTen) like concat('%',lower(#{dtDmNoiSinhList.dtDmNoiSinh.dtdmnoisinhTen}),'%')", };

	private DtDmNoiSinh dtDmNoiSinh = new DtDmNoiSinh();

	public DtDmNoiSinhList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmNoiSinhList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DtDmNoiSinh getDtDmNoiSinh() {
		return dtDmNoiSinh;
	}
}

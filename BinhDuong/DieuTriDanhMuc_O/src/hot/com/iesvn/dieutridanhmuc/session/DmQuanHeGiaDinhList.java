package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmQuanHeGiaDinhList")
public class DmQuanHeGiaDinhList extends EntityQuery<DmQuanHeGiaDinh> {

	private static final String EJBQL = "select dmQuanHeGiaDinh from DmQuanHeGiaDinh dmQuanHeGiaDinh";

	private static final String[] RESTRICTIONS = {
			"lower(dmQuanHeGiaDinh.dmqhgdMa) like concat('%',lower(#{dmQuanHeGiaDinhList.dmQuanHeGiaDinh.dmqhgdMa}),'%')",
			"lower(dmQuanHeGiaDinh.dmqhgdTen) like concat('%',lower(#{dmQuanHeGiaDinhList.dmQuanHeGiaDinh.dmqhgdTen}),'%')", };

	private DmQuanHeGiaDinh dmQuanHeGiaDinh = new DmQuanHeGiaDinh();

	public DmQuanHeGiaDinhList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmQuanHeGiaDinh getDmQuanHeGiaDinh() {
		return dmQuanHeGiaDinh;
	}
}

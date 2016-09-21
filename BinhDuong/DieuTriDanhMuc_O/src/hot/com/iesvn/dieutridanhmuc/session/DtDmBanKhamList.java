package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmBanKhamList")
public class DtDmBanKhamList extends EntityQuery<DtDmBanKham> {

	private static final String EJBQL = "select dtDmBanKham from DtDmBanKham dtDmBanKham";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmBanKham.dtdmbankhamKyhieu) like concat('%',lower(#{dtDmBanKhamList.dtDmBanKham.dtdmbankhamKyhieu}),'%')",
			"lower(dtDmBanKham.dtdmbankhamMa) like concat('%',lower(#{dtDmBanKhamList.dtDmBanKham.dtdmbankhamMa}),'%')",
			"lower(dtDmBanKham.dtdmbankhamMa0) like concat('%',lower(#{dtDmBanKhamList.dtDmBanKham.dtdmbankhamMa0}),'%')",
			"lower(dtDmBanKham.dtdmbankhamMa2) like concat('%',lower(#{dtDmBanKhamList.dtDmBanKham.dtdmbankhamMa2}),'%')",
			"lower(dtDmBanKham.dtdmbankhamTen) like concat('%',lower(#{dtDmBanKhamList.dtDmBanKham.dtdmbankhamTen}),'%')",
			"lower(dtDmBanKham.dtdmbankhamTinhchat) like concat('%',lower(#{dtDmBanKhamList.dtDmBanKham.dtdmbankhamTinhchat}),'%')",
			"lower(dtDmBanKham.dtdmnhanvienNhom) like concat('%',lower(#{dtDmBanKhamList.dtDmBanKham.dtdmnhanvienNhom}),'%')",
			"lower(dtDmBanKham.dtdmnhanvienTenbc) like concat('%',lower(#{dtDmBanKhamList.dtDmBanKham.dtdmnhanvienTenbc}),'%')", };

	private DtDmBanKham dtDmBanKham = new DtDmBanKham();

	public DtDmBanKhamList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmBanKhamList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DtDmBanKham getDtDmBanKham() {
		return dtDmBanKham;
	}
}

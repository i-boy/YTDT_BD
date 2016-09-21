package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmBenhVnList")
public class DmBenhVnList extends EntityQuery<DmBenhVn> {

	private static final String EJBQL = "select dmBenhVn from DmBenhVn dmBenhVn";

	private static final String[] RESTRICTIONS = {
			"lower(dmBenhVn.dmbenhvnBaogom) like concat('%',lower(#{dmBenhVnList.dmBenhVn.dmbenhvnBaogom}),'%')",
			"lower(dmBenhVn.dmbenhvnMa) like concat('%',lower(#{dmBenhVnList.dmBenhVn.dmbenhvnMa}),'%')",
			"lower(dmBenhVn.dmbenhvnMachung) like concat('%',lower(#{dmBenhVnList.dmBenhVn.dmbenhvnMachung}),'%')",
			"lower(dmBenhVn.dmbenhvnMaicd10) like concat('%',lower(#{dmBenhVnList.dmBenhVn.dmbenhvnMaicd10}),'%')",
			"lower(dmBenhVn.dmbenhvnMaicd10b) like concat('%',lower(#{dmBenhVnList.dmBenhVn.dmbenhvnMaicd10b}),'%')",
			"lower(dmBenhVn.dmbenhvnTen) like concat('%',lower(#{dmBenhVnList.dmBenhVn.dmbenhvnTen}),'%')", };

	private DmBenhVn dmBenhVn = new DmBenhVn();

	public DmBenhVnList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmBenhVnList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DmBenhVn getDmBenhVn() {
		return dmBenhVn;
	}
}

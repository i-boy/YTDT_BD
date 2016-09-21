package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmBenhIcdList")
public class DmBenhIcdList extends EntityQuery<DmBenhIcd> {

	private static final String EJBQL = "select dmBenhIcd from DmBenhIcd dmBenhIcd";

	private static final String[] RESTRICTIONS = {
			"lower(dmBenhIcd.dmbenhicdLienhe) like concat('%',lower(#{dmBenhIcdList.dmBenhIcd.dmbenhicdLienhe}),'%')",
			"lower(dmBenhIcd.dmbenhicdMa) like concat('%',lower(#{dmBenhIcdList.dmBenhIcd.dmbenhicdMa}),'%')",
			"lower(dmBenhIcd.dmbenhicdMachung) like concat('%',lower(#{dmBenhIcdList.dmBenhIcd.dmbenhicdMachung}),'%')",
			"lower(dmBenhIcd.dmbenhicdTen) like concat('%',lower(#{dmBenhIcdList.dmBenhIcd.dmbenhicdTen}),'%')",
			"lower(dmBenhIcd.dmbenhicdTienganh) like concat('%',lower(#{dmBenhIcdList.dmBenhIcd.dmbenhicdTienganh}),'%')", };

	private DmBenhIcd dmBenhIcd = new DmBenhIcd();

	public DmBenhIcdList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmBenhIcdList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DmBenhIcd getDmBenhIcd() {
		return dmBenhIcd;
	}
}

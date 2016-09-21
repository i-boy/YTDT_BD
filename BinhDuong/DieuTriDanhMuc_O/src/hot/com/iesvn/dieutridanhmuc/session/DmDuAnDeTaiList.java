package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmDuAnDeTaiList")
public class DmDuAnDeTaiList extends EntityQuery<DmDuAnDeTai> {

	private static final String EJBQL = "select dmDuAnDeTai from DmDuAnDeTai dmDuAnDeTai";

	private static final String[] RESTRICTIONS = {
			"lower(dmDuAnDeTai.dmduandetaiMa) like concat('%',lower(#{dmDuAnDeTaiList.dmDuAnDeTai.dmduandetaiMa}),'%')",
			"lower(dmDuAnDeTai.dmduandetaiTen) like concat('%',lower(#{dmDuAnDeTaiList.dmDuAnDeTai.dmduandetaiTen}),'%')", };

	private DmDuAnDeTai dmDuAnDeTai = new DmDuAnDeTai();

	public DmDuAnDeTaiList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmDuAnDeTai getDmDuAnDeTai() {
		return dmDuAnDeTai;
	}
}

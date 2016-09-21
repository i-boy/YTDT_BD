package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmDonViList")
public class DmDonViList extends EntityQuery<DmDonVi> {

	private static final String EJBQL = "select dmDonVi from DmDonVi dmDonVi";

	private static final String[] RESTRICTIONS = {
			"lower(dmDonVi.dmdonviDienthoai) like concat('%',lower(#{dmDonViList.dmDonVi.dmdonviDienthoai}),'%')",
			"lower(dmDonVi.dmdonviDvquanlytructiep) like concat('%',lower(#{dmDonViList.dmDonVi.dmdonviDvquanlytructiep}),'%')",
			"lower(dmDonVi.dmdonviMa) like concat('%',lower(#{dmDonViList.dmDonVi.dmdonviMa}),'%')",
			"lower(dmDonVi.dmdonviSonhatuyenduong) like concat('%',lower(#{dmDonViList.dmDonVi.dmdonviSonhatuyenduong}),'%')",
			"lower(dmDonVi.dmdonviTen) like concat('%',lower(#{dmDonViList.dmDonVi.dmdonviTen}),'%')",
			"lower(dmDonVi.dmdonviTenbaocao) like concat('%',lower(#{dmDonViList.dmDonVi.dmdonviTenbaocao}),'%')",
			"lower(dmDonVi.dmdonviUrl) like concat('%',lower(#{dmDonViList.dmDonVi.dmdonviUrl}),'%')", };

	private DmDonVi dmDonVi = new DmDonVi();

	public DmDonViList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmDonVi getDmDonVi() {
		return dmDonVi;
	}
}

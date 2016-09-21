package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmDonViTuoiList")
public class DmDonViTuoiList extends EntityQuery<DmDonViTuoi> {

	private static final String EJBQL = "select dmDonViTuoi from DmDonViTuoi dmDonViTuoi";

	private static final String[] RESTRICTIONS = {
			"lower(dmDonViTuoi.dmdonvituoiMa) like concat('%',lower(#{dmDonViTuoiList.dmDonViTuoi.dmdonvituoiMa}),'%')",
			"lower(dmDonViTuoi.dmdonvituoiTen) like concat('%',lower(#{dmDonViTuoiList.dmDonViTuoi.dmdonvituoiTen}),'%')", };

	private DmDonViTuoi dmDonViTuoi = new DmDonViTuoi();

	public DmDonViTuoiList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmDonViTuoi getDmDonViTuoi() {
		return dmDonViTuoi;
	}
}

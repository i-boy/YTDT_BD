package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmDonViTinhList")
public class DmDonViTinhList extends EntityQuery<DmDonViTinh> {

	private static final String EJBQL = "select dmDonViTinh from DmDonViTinh dmDonViTinh";

	private static final String[] RESTRICTIONS = {
			"lower(dmDonViTinh.dmdonvitinhDacdiem) like concat('%',lower(#{dmDonViTinhList.dmDonViTinh.dmdonvitinhDacdiem}),'%')",
			"lower(dmDonViTinh.dmdonvitinhDtich) like concat('%',lower(#{dmDonViTinhList.dmDonViTinh.dmdonvitinhDtich}),'%')",
			"lower(dmDonViTinh.dmdonvitinhMa) like concat('%',lower(#{dmDonViTinhList.dmDonViTinh.dmdonvitinhMa}),'%')",
			"lower(dmDonViTinh.dmdonvitinhTen) like concat('%',lower(#{dmDonViTinhList.dmDonViTinh.dmdonvitinhTen}),'%')", };

	private DmDonViTinh dmDonViTinh = new DmDonViTinh();

	public DmDonViTinhList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmDonViTinh getDmDonViTinh() {
		return dmDonViTinh;
	}
}

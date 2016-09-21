package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmPhuongThucGayTaiNanList")
public class DmPhuongThucGayTaiNanList extends
		EntityQuery<DmPhuongThucGayTaiNan> {

	private static final String EJBQL = "select dmPhuongThucGayTaiNan from DmPhuongThucGayTaiNan dmPhuongThucGayTaiNan";

	private static final String[] RESTRICTIONS = {
			"lower(dmPhuongThucGayTaiNan.dmptgtnMa) like concat('%',lower(#{dmPhuongThucGayTaiNanList.dmPhuongThucGayTaiNan.dmptgtnMa}),'%')",
			"lower(dmPhuongThucGayTaiNan.dmptgtnTen) like concat('%',lower(#{dmPhuongThucGayTaiNanList.dmPhuongThucGayTaiNan.dmptgtnTen}),'%')", };

	private DmPhuongThucGayTaiNan dmPhuongThucGayTaiNan = new DmPhuongThucGayTaiNan();

	public DmPhuongThucGayTaiNanList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmPhuongThucGayTaiNanList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DmPhuongThucGayTaiNan getDmPhuongThucGayTaiNan() {
		return dmPhuongThucGayTaiNan;
	}
}

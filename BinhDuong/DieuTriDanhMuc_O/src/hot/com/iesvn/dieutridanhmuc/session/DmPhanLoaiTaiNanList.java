package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmPhanLoaiTaiNanList")
public class DmPhanLoaiTaiNanList extends EntityQuery<DmPhanLoaiTaiNan> {

	private static final String EJBQL = "select dmPhanLoaiTaiNan from DmPhanLoaiTaiNan dmPhanLoaiTaiNan";

	private static final String[] RESTRICTIONS = {
			"lower(dmPhanLoaiTaiNan.dmpltainanMa) like concat('%',lower(#{dmPhanLoaiTaiNanList.dmPhanLoaiTaiNan.dmpltainanMa}),'%')",
			"lower(dmPhanLoaiTaiNan.dmpltainanTaptin) like concat('%',lower(#{dmPhanLoaiTaiNanList.dmPhanLoaiTaiNan.dmpltainanTaptin}),'%')",
			"lower(dmPhanLoaiTaiNan.dmpltainanTen) like concat('%',lower(#{dmPhanLoaiTaiNanList.dmPhanLoaiTaiNan.dmpltainanTen}),'%')",
			"lower(dmPhanLoaiTaiNan.dmpltainanTen2) like concat('%',lower(#{dmPhanLoaiTaiNanList.dmPhanLoaiTaiNan.dmpltainanTen2}),'%')", };

	private DmPhanLoaiTaiNan dmPhanLoaiTaiNan = new DmPhanLoaiTaiNan();

	public DmPhanLoaiTaiNanList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmPhanLoaiTaiNanList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));		
	}

	public DmPhanLoaiTaiNan getDmPhanLoaiTaiNan() {
		return dmPhanLoaiTaiNan;
	}
}

package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmThanhPhanGiaDinhList")
public class DmThanhPhanGiaDinhList extends EntityQuery<DmThanhPhanGiaDinh> {

	private static final String EJBQL = "select dmThanhPhanGiaDinh from DmThanhPhanGiaDinh dmThanhPhanGiaDinh";

	private static final String[] RESTRICTIONS = {
			"lower(dmThanhPhanGiaDinh.dmtpgdMa) like concat('%',lower(#{dmThanhPhanGiaDinhList.dmThanhPhanGiaDinh.dmtpgdMa}),'%')",
			"lower(dmThanhPhanGiaDinh.dmtpgdTen) like concat('%',lower(#{dmThanhPhanGiaDinhList.dmThanhPhanGiaDinh.dmtpgdTen}),'%')", };

	private DmThanhPhanGiaDinh dmThanhPhanGiaDinh = new DmThanhPhanGiaDinh();

	public DmThanhPhanGiaDinhList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmThanhPhanGiaDinh getDmThanhPhanGiaDinh() {
		return dmThanhPhanGiaDinh;
	}
}

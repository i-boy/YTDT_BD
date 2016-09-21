package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmChucVuList")
public class DmChucVuList extends EntityQuery<DmChucVu> {

	private static final String EJBQL = "select dmChucVu from DmChucVu dmChucVu";

	private static final String[] RESTRICTIONS = {
			"lower(dmChucVu.dmchucvuMa) like concat('%',lower(#{dmChucVuList.dmChucVu.dmchucvuMa}),'%')",
			"lower(dmChucVu.dmchucvuTen) like concat('%',lower(#{dmChucVuList.dmChucVu.dmchucvuTen}),'%')",
			"lower(dmChucVu.dmchucvuTenbc) like concat('%',lower(#{dmChucVuList.dmChucVu.dmchucvuTenbc}),'%')", };

	private DmChucVu dmChucVu = new DmChucVu();

	public DmChucVuList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmChucVu getDmChucVu() {
		return dmChucVu;
	}
}

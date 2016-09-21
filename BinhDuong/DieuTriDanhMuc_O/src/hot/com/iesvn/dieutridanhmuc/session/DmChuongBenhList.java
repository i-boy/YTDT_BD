package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmChuongBenhList")
public class DmChuongBenhList extends EntityQuery<DmChuongBenh> {

	private static final String EJBQL = "select dmChuongBenh from DmChuongBenh dmChuongBenh";

	private static final String[] RESTRICTIONS = {
			"lower(dmChuongBenh.dmbenhicdMa) like concat('%',lower(#{dmChuongBenhList.dmChuongBenh.dmbenhicdMa}),'%')",
			"lower(dmChuongBenh.dmchuongbenhMa) like concat('%',lower(#{dmChuongBenhList.dmChuongBenh.dmchuongbenhMa}),'%')",
			"lower(dmChuongBenh.dmchuongbenhTen) like concat('%',lower(#{dmChuongBenhList.dmChuongBenh.dmchuongbenhTen}),'%')", };

	private DmChuongBenh dmChuongBenh = new DmChuongBenh();

	public DmChuongBenhList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmChuongBenhList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DmChuongBenh getDmChuongBenh() {
		return dmChuongBenh;
	}
}

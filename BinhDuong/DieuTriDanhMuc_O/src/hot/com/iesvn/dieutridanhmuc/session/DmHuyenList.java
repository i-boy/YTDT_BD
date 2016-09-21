package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmHuyenList")
public class DmHuyenList extends EntityQuery<DmHuyen> {

	private static final String EJBQL = "select dmHuyen from DmHuyen dmHuyen";

	private static final String[] RESTRICTIONS = {
			"lower(dmHuyen.dmhuyenMa) like concat('%',lower(#{dmHuyenList.dmHuyen.dmhuyenMa}),'%')",
			"lower(dmHuyen.dmhuyenTen) like concat('%',lower(#{dmHuyenList.dmHuyen.dmhuyenTen}),'%')", };

	private DmHuyen dmHuyen = new DmHuyen();

	public DmHuyenList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmHuyen getDmHuyen() {
		return dmHuyen;
	}
}

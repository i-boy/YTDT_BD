package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmPhongList")
public class DtDmPhongList extends EntityQuery<DtDmPhong> {

	private static final String EJBQL = "select dtDmPhong from DtDmPhong dtDmPhong";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmPhong.dtdmpMa) like concat('%',lower(#{dtDmPhongList.dtDmPhong.dtdmpMa}),'%')",
			"lower(dtDmPhong.dtdmpTen) like concat('%',lower(#{dtDmPhongList.dtDmPhong.dtdmpTen}),'%')", };

	private DtDmPhong dtDmPhong = new DtDmPhong();

	public DtDmPhongList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmPhong getDtDmPhong() {
		return dtDmPhong;
	}
}

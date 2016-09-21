package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmBuongList")
public class DtDmBuongList extends EntityQuery<DtDmBuong> {

	private static final String EJBQL = "select dtDmBuong from DtDmBuong dtDmBuong";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmBuong.dtdmbMa) like concat('%',lower(#{dtDmBuongList.dtDmBuong.dtdmbMa}),'%')",
			"lower(dtDmBuong.dtdmbTen) like concat('%',lower(#{dtDmBuongList.dtDmBuong.dtdmbTen}),'%')", };

	private DtDmBuong dtDmBuong = new DtDmBuong();

	public DtDmBuongList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmBuong getDtDmBuong() {
		return dtDmBuong;
	}
}

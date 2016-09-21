package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmLoaiMienList")
public class DtDmLoaiMienList extends EntityQuery<DtDmLoaiMien> {

	private static final String EJBQL = "select dtDmLoaiMien from DtDmLoaiMien dtDmLoaiMien";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmLoaiMien.dtdmloaimienMa) like concat('%',lower(#{dtDmLoaiMienList.dtDmLoaiMien.dtdmloaimienMa}),'%')",
			"lower(dtDmLoaiMien.dtdmloaimienTen) like concat('%',lower(#{dtDmLoaiMienList.dtDmLoaiMien.dtdmloaimienTen}),'%')", };

	private DtDmLoaiMien dtDmLoaiMien = new DtDmLoaiMien();

	public DtDmLoaiMienList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmLoaiMien getDtDmLoaiMien() {
		return dtDmLoaiMien;
	}
}

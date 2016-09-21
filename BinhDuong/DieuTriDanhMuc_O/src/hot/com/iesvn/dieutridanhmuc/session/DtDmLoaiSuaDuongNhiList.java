package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmLoaiSuaDuongNhiList")
public class DtDmLoaiSuaDuongNhiList extends EntityQuery<DtDmLoaiSuaDuongNhi> {

	private static final String EJBQL = "select dtDmLoaiSuaDuongNhi from DtDmLoaiSuaDuongNhi dtDmLoaiSuaDuongNhi";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmLoaiSuaDuongNhi.dtdmlsdnMa) like concat('%',lower(#{dtDmLoaiSuaDuongNhiList.dtDmLoaiSuaDuongNhi.dtdmlsdnMa}),'%')",
			"lower(dtDmLoaiSuaDuongNhi.dtdmlsdnTen) like concat('%',lower(#{dtDmLoaiSuaDuongNhiList.dtDmLoaiSuaDuongNhi.dtdmlsdnTen}),'%')", };

	private DtDmLoaiSuaDuongNhi dtDmLoaiSuaDuongNhi = new DtDmLoaiSuaDuongNhi();

	public DtDmLoaiSuaDuongNhiList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmLoaiSuaDuongNhi getDtDmLoaiSuaDuongNhi() {
		return dtDmLoaiSuaDuongNhi;
	}
}

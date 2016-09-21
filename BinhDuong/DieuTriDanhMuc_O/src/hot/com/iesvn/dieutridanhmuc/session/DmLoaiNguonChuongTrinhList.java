package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmLoaiNguonChuongTrinhList")
public class DmLoaiNguonChuongTrinhList extends
		EntityQuery<DmLoaiNguonChuongTrinh> {

	private static final String EJBQL = "select dmLoaiNguonChuongTrinh from DmLoaiNguonChuongTrinh dmLoaiNguonChuongTrinh";

	private static final String[] RESTRICTIONS = {
			"lower(dmLoaiNguonChuongTrinh.dmlnctGhichu) like concat('%',lower(#{dmLoaiNguonChuongTrinhList.dmLoaiNguonChuongTrinh.dmlnctGhichu}),'%')",
			"lower(dmLoaiNguonChuongTrinh.dmlnctMa) like concat('%',lower(#{dmLoaiNguonChuongTrinhList.dmLoaiNguonChuongTrinh.dmlnctMa}),'%')",
			"lower(dmLoaiNguonChuongTrinh.dmlnctTen) like concat('%',lower(#{dmLoaiNguonChuongTrinhList.dmLoaiNguonChuongTrinh.dmlnctTen}),'%')", };

	private DmLoaiNguonChuongTrinh dmLoaiNguonChuongTrinh = new DmLoaiNguonChuongTrinh();

	public DmLoaiNguonChuongTrinhList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmLoaiNguonChuongTrinh getDmLoaiNguonChuongTrinh() {
		return dmLoaiNguonChuongTrinh;
	}
}

package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmLoaiSinhList")
public class DmLoaiSinhList extends EntityQuery<DmLoaiSinh> {

	private static final String EJBQL = "select dmLoaiSinh from DmLoaiSinh dmLoaiSinh";

	private static final String[] RESTRICTIONS = {
			"lower(dmLoaiSinh.dmloaisinhMa) like concat('%',lower(#{dmLoaiSinhList.dmLoaiSinh.dmloaisinhMa}),'%')",
			"lower(dmLoaiSinh.dmloaisinhTen) like concat('%',lower(#{dmLoaiSinhList.dmLoaiSinh.dmloaisinhTen}),'%')", };

	private DmLoaiSinh dmLoaiSinh = new DmLoaiSinh();

	public DmLoaiSinhList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmLoaiSinhList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DmLoaiSinh getDmLoaiSinh() {
		return dmLoaiSinh;
	}
}

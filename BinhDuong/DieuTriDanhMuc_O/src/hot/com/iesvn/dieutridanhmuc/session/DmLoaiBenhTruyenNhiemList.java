package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmLoaiBenhTruyenNhiemList")
public class DmLoaiBenhTruyenNhiemList extends
		EntityQuery<DmLoaiBenhTruyenNhiem> {

	private static final String EJBQL = "select dmLoaiBenhTruyenNhiem from DmLoaiBenhTruyenNhiem dmLoaiBenhTruyenNhiem";

	private static final String[] RESTRICTIONS = {
			"lower(dmLoaiBenhTruyenNhiem.dmlbtnGhichu) like concat('%',lower(#{dmLoaiBenhTruyenNhiemList.dmLoaiBenhTruyenNhiem.dmlbtnGhichu}),'%')",
			"lower(dmLoaiBenhTruyenNhiem.dmlbtnMa) like concat('%',lower(#{dmLoaiBenhTruyenNhiemList.dmLoaiBenhTruyenNhiem.dmlbtnMa}),'%')",
			"lower(dmLoaiBenhTruyenNhiem.dmlbtnTen) like concat('%',lower(#{dmLoaiBenhTruyenNhiemList.dmLoaiBenhTruyenNhiem.dmlbtnTen}),'%')", };

	private DmLoaiBenhTruyenNhiem dmLoaiBenhTruyenNhiem = new DmLoaiBenhTruyenNhiem();

	public DmLoaiBenhTruyenNhiemList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmLoaiBenhTruyenNhiem getDmLoaiBenhTruyenNhiem() {
		return dmLoaiBenhTruyenNhiem;
	}
}

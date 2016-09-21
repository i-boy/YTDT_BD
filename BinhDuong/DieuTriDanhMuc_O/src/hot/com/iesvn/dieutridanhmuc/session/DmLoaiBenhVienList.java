package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmLoaiBenhVienList")
public class DmLoaiBenhVienList extends EntityQuery<DmLoaiBenhVien> {

	private static final String EJBQL = "select dmLoaiBenhVien from DmLoaiBenhVien dmLoaiBenhVien";

	private static final String[] RESTRICTIONS = {
			"lower(dmLoaiBenhVien.dmloaibvMa) like concat('%',lower(#{dmLoaiBenhVienList.dmLoaiBenhVien.dmloaibvMa}),'%')",
			"lower(dmLoaiBenhVien.dmloaibvTen) like concat('%',lower(#{dmLoaiBenhVienList.dmLoaiBenhVien.dmloaibvTen}),'%')", };

	private DmLoaiBenhVien dmLoaiBenhVien = new DmLoaiBenhVien();

	public DmLoaiBenhVienList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmLoaiBenhVien getDmLoaiBenhVien() {
		return dmLoaiBenhVien;
	}
}

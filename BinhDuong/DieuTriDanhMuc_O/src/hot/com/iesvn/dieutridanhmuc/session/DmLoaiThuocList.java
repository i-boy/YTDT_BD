package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmLoaiThuocList")
public class DmLoaiThuocList extends EntityQuery<DmLoaiThuoc> {

	private static final String EJBQL = "select dmLoaiThuoc from DmLoaiThuoc dmLoaiThuoc";

	private static final String[] RESTRICTIONS = {
			"lower(dmLoaiThuoc.dmloaithuocMa) like concat('%',lower(#{dmLoaiThuocList.dmLoaiThuoc.dmloaithuocMa}),'%')",
			"lower(dmLoaiThuoc.dmloaithuocQuyen) like concat('%',lower(#{dmLoaiThuocList.dmLoaiThuoc.dmloaithuocQuyen}),'%')",
			"lower(dmLoaiThuoc.dmloaithuocTen) like concat('%',lower(#{dmLoaiThuocList.dmLoaiThuoc.dmloaithuocTen}),'%')", };

	private DmLoaiThuoc dmLoaiThuoc = new DmLoaiThuoc();

	public DmLoaiThuocList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmLoaiThuocList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DmLoaiThuoc getDmLoaiThuoc() {
		return dmLoaiThuoc;
	}
}

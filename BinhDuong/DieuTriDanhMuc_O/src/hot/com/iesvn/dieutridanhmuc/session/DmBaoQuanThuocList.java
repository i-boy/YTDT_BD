package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmBaoQuanThuocList")
public class DmBaoQuanThuocList extends EntityQuery<DmBaoQuanThuoc> {

	private static final String EJBQL = "select dmBaoQuanThuoc from DmBaoQuanThuoc dmBaoQuanThuoc";

	private static final String[] RESTRICTIONS = {
			"lower(dmBaoQuanThuoc.dmbaoquanthuocGhichu) like concat('%',lower(#{dmBaoQuanThuocList.dmBaoQuanThuoc.dmbaoquanthuocGhichu}),'%')",
			"lower(dmBaoQuanThuoc.dmbaoquanthuocMa) like concat('%',lower(#{dmBaoQuanThuocList.dmBaoQuanThuoc.dmbaoquanthuocMa}),'%')",
			"lower(dmBaoQuanThuoc.dmbaoquanthuocMaphu) like concat('%',lower(#{dmBaoQuanThuocList.dmBaoQuanThuoc.dmbaoquanthuocMaphu}),'%')",
			"lower(dmBaoQuanThuoc.dmbaoquanthuocTen) like concat('%',lower(#{dmBaoQuanThuocList.dmBaoQuanThuoc.dmbaoquanthuocTen}),'%')",
			"lower(dmBaoQuanThuoc.nhanvienCn) like concat('%',lower(#{dmBaoQuanThuocList.dmBaoQuanThuoc.nhanvienCn}),'%')", };

	private DmBaoQuanThuoc dmBaoQuanThuoc = new DmBaoQuanThuoc();

	public DmBaoQuanThuocList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmBaoQuanThuoc getDmBaoQuanThuoc() {
		return dmBaoQuanThuoc;
	}
}

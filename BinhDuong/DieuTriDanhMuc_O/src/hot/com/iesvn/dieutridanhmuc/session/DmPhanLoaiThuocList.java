package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmPhanLoaiThuocList")
public class DmPhanLoaiThuocList extends EntityQuery<DmPhanLoaiThuoc> {

	private static final String EJBQL = "select dmPhanLoaiThuoc from DmPhanLoaiThuoc dmPhanLoaiThuoc";

	private static final String[] RESTRICTIONS = {
			"lower(dmPhanLoaiThuoc.dmphanloaithuocGhichu) like concat('%',lower(#{dmPhanLoaiThuocList.dmPhanLoaiThuoc.dmphanloaithuocGhichu}),'%')",
			"lower(dmPhanLoaiThuoc.dmphanloaithuocMa) like concat('%',lower(#{dmPhanLoaiThuocList.dmPhanLoaiThuoc.dmphanloaithuocMa}),'%')",
			"lower(dmPhanLoaiThuoc.dmphanloaithuocNhom2) like concat('%',lower(#{dmPhanLoaiThuocList.dmPhanLoaiThuoc.dmphanloaithuocNhom2}),'%')",
			"lower(dmPhanLoaiThuoc.dmphanloaithuocNhom3) like concat('%',lower(#{dmPhanLoaiThuocList.dmPhanLoaiThuoc.dmphanloaithuocNhom3}),'%')",
			"lower(dmPhanLoaiThuoc.dmphanloaithuocTen) like concat('%',lower(#{dmPhanLoaiThuocList.dmPhanLoaiThuoc.dmphanloaithuocTen}),'%')", };

	private DmPhanLoaiThuoc dmPhanLoaiThuoc = new DmPhanLoaiThuoc();

	public DmPhanLoaiThuocList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmPhanLoaiThuocList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DmPhanLoaiThuoc getDmPhanLoaiThuoc() {
		return dmPhanLoaiThuoc;
	}
}

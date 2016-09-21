package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmKhoaList")
public class DmKhoaList extends EntityQuery<DmKhoa> {

	private static final String EJBQL = "select dmKhoa from DmKhoa dmKhoa";

	private static final String[] RESTRICTIONS = {
			"lower(dmKhoa.dmkhoaCon) like concat('%',lower(#{dmKhoaList.dmKhoa.dmkhoaCon}),'%')",
			"lower(dmKhoa.dmkhoaDienthoai) like concat('%',lower(#{dmKhoaList.dmKhoa.dmkhoaDienthoai}),'%')",
			"lower(dmKhoa.dmkhoaKho) like concat('%',lower(#{dmKhoaList.dmKhoa.dmkhoaKho}),'%')",
			"lower(dmKhoa.dmkhoaKyhieu) like concat('%',lower(#{dmKhoaList.dmKhoa.dmkhoaKyhieu}),'%')",
			"lower(dmKhoa.dmkhoaMa) like concat('%',lower(#{dmKhoaList.dmKhoa.dmkhoaMa}),'%')",
			"lower(dmKhoa.dmkhoaMakhoada) like concat('%',lower(#{dmKhoaList.dmKhoa.dmkhoaMakhoada}),'%')",
			"lower(dmKhoa.dmkhoaTen) like concat('%',lower(#{dmKhoaList.dmKhoa.dmkhoaTen}),'%')",
			"lower(dmKhoa.dmkhoaTen2) like concat('%',lower(#{dmKhoaList.dmKhoa.dmkhoaTen2}),'%')",
			"lower(dmKhoa.dmkhoaTenbc) like concat('%',lower(#{dmKhoaList.dmKhoa.dmkhoaTenbc}),'%')",
			"lower(dmKhoa.dmkhoaTinhchat) like concat('%',lower(#{dmKhoaList.dmKhoa.dmkhoaTinhchat}),'%')", };

	private DmKhoa dmKhoa = new DmKhoa();

	public DmKhoaList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmKhoaList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DmKhoa getDmKhoa() {
		return dmKhoa;
	}
}

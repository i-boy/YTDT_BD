package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmNhomKhoaList")
public class DmNhomKhoaList extends EntityQuery<DmNhomKhoa> {

	private static final String EJBQL = "select dmNhomKhoa from DmNhomKhoa dmNhomKhoa";

	private static final String[] RESTRICTIONS = {
			"lower(dmNhomKhoa.dmnhomkhoaMa) like concat('%',lower(#{dmNhomKhoaList.dmNhomKhoa.dmnhomkhoaMa}),'%')",
			"lower(dmNhomKhoa.dmnhomkhoaTen) like concat('%',lower(#{dmNhomKhoaList.dmNhomKhoa.dmnhomkhoaTen}),'%')", };

	private DmNhomKhoa dmNhomKhoa = new DmNhomKhoa();

	public DmNhomKhoaList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmNhomKhoa getDmNhomKhoa() {
		return dmNhomKhoa;
	}
}

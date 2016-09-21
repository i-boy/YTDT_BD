package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmNgheNghiepBaoCaoList")
public class DmNgheNghiepBaoCaoList extends EntityQuery<DmNgheNghiepBaoCao> {

	private static final String EJBQL = "select dmNgheNghiepBaoCao from DmNgheNghiepBaoCao dmNgheNghiepBaoCao";

	private static final String[] RESTRICTIONS = {
			"lower(dmNgheNghiepBaoCao.dmnghenghiepbcMa) like concat('%',lower(#{dmNgheNghiepBaoCaoList.dmNgheNghiepBaoCao.dmnghenghiepbcMa}),'%')",
			"lower(dmNgheNghiepBaoCao.dmnghenghiepbcTen) like concat('%',lower(#{dmNgheNghiepBaoCaoList.dmNgheNghiepBaoCao.dmnghenghiepbcTen}),'%')", };

	private DmNgheNghiepBaoCao dmNgheNghiepBaoCao = new DmNgheNghiepBaoCao();

	public DmNgheNghiepBaoCaoList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmNgheNghiepBaoCaoList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DmNgheNghiepBaoCao getDmNgheNghiepBaoCao() {
		return dmNgheNghiepBaoCao;
	}
}

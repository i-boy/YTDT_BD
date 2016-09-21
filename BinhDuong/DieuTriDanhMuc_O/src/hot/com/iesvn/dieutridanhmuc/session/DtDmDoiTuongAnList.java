package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmDoiTuongAnList")
public class DtDmDoiTuongAnList extends EntityQuery<DtDmDoiTuongAn> {

	private static final String EJBQL = "select dtDmDoiTuongAn from DtDmDoiTuongAn dtDmDoiTuongAn";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmDoiTuongAn.dtdmdtaMa) like concat('%',lower(#{dtDmDoiTuongAnList.dtDmDoiTuongAn.dtdmdtaMa}),'%')",
			"lower(dtDmDoiTuongAn.dtdmdtaTen) like concat('%',lower(#{dtDmDoiTuongAnList.dtDmDoiTuongAn.dtdmdtaTen}),'%')", };

	private DtDmDoiTuongAn dtDmDoiTuongAn = new DtDmDoiTuongAn();

	public DtDmDoiTuongAnList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmDoiTuongAn getDtDmDoiTuongAn() {
		return dtDmDoiTuongAn;
	}
}

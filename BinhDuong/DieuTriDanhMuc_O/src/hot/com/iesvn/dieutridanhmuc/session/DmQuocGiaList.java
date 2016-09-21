package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmQuocGiaList")
public class DmQuocGiaList extends EntityQuery<DmQuocGia> {

	private static final String EJBQL = "select dmQuocGia from DmQuocGia dmQuocGia";

	private static final String[] RESTRICTIONS = {
			"lower(dmQuocGia.dmquocgiaMa) like concat('%',lower(#{dmQuocGiaList.dmQuocGia.dmquocgiaMa}),'%')",
			"lower(dmQuocGia.dmquocgiaTen) like concat('%',lower(#{dmQuocGiaList.dmQuocGia.dmquocgiaTen}),'%')",
			"lower(dmQuocGia.dmquocgiaTenvn) like concat('%',lower(#{dmQuocGiaList.dmQuocGia.dmquocgiaTenvn}),'%')", };

	private DmQuocGia dmQuocGia = new DmQuocGia();

	public DmQuocGiaList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmQuocGiaList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DmQuocGia getDmQuocGia() {
		return dmQuocGia;
	}
}

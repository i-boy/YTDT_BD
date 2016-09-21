package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmLoaiThietBiList")
public class DmLoaiThietBiList extends EntityQuery<DmLoaiThietBi> {

	private static final String EJBQL = "select dmLoaiThietBi from DmLoaiThietBi dmLoaiThietBi";

	private static final String[] RESTRICTIONS = {
			"lower(dmLoaiThietBi.dmloaithietbiMa) like concat('%',lower(#{dmLoaiThietBiList.dmLoaiThietBi.dmloaithietbiMa}),'%')",
			"lower(dmLoaiThietBi.dmloaithietbiTen) like concat('%',lower(#{dmLoaiThietBiList.dmLoaiThietBi.dmloaithietbiTen}),'%')", };

	private DmLoaiThietBi dmLoaiThietBi = new DmLoaiThietBi();

	public DmLoaiThietBiList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmLoaiThietBi getDmLoaiThietBi() {
		return dmLoaiThietBi;
	}
}

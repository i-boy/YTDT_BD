package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmLoaiBhytList")
public class DtDmLoaiBhytList extends EntityQuery<DtDmLoaiBhyt> {

	private static final String EJBQL = "select dtDmLoaiBhyt from DtDmLoaiBhyt dtDmLoaiBhyt";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmLoaiBhyt.dtdmloaibhytGhichu) like concat('%',lower(#{dtDmLoaiBhytList.dtDmLoaiBhyt.dtdmloaibhytGhichu}),'%')",
			"lower(dtDmLoaiBhyt.dtdmloaibhytMa) like concat('%',lower(#{dtDmLoaiBhytList.dtDmLoaiBhyt.dtdmloaibhytMa}),'%')",
			"lower(dtDmLoaiBhyt.dtdmloaibhytTen) like concat('%',lower(#{dtDmLoaiBhytList.dtDmLoaiBhyt.dtdmloaibhytTen}),'%')", };

	private DtDmLoaiBhyt dtDmLoaiBhyt = new DtDmLoaiBhyt();

	public DtDmLoaiBhytList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmLoaiBhyt getDtDmLoaiBhyt() {
		return dtDmLoaiBhyt;
	}
}

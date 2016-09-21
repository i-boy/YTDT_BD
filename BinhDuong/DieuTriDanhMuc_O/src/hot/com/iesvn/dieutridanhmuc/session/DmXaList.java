package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmXaList")
public class DmXaList extends EntityQuery<DmXa> {

	private static final String EJBQL = "select dmXa from DmXa dmXa";

	private static final String[] RESTRICTIONS = {
			"lower(dmXa.dmxaMa) like concat('%',lower(#{dmXaList.dmXa.dmxaMa}),'%')",
			"lower(dmXa.dmxaTen) like concat('%',lower(#{dmXaList.dmXa.dmxaTen}),'%')", };

	private DmXa dmXa = new DmXa();

	public DmXaList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmXa getDmXa() {
		return dmXa;
	}
}

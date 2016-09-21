package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmKhoaBytList")
public class DmKhoaBytList extends EntityQuery<DmKhoaByt> {

	private static final String EJBQL = "select dmKhoaByt from DmKhoaByt dmKhoaByt";

	private static final String[] RESTRICTIONS = {
			"lower(dmKhoaByt.dmkhoabytMa) like concat('%',lower(#{dmKhoaBytList.dmKhoaByt.dmkhoabytMa}),'%')",
			"lower(dmKhoaByt.dmkhoabytMabyt3) like concat('%',lower(#{dmKhoaBytList.dmKhoaByt.dmkhoabytMabyt3}),'%')",
			"lower(dmKhoaByt.dmkhoabytTen) like concat('%',lower(#{dmKhoaBytList.dmKhoaByt.dmkhoabytTen}),'%')", };

	private DmKhoaByt dmKhoaByt = new DmKhoaByt();

	public DmKhoaBytList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmKhoaBytList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DmKhoaByt getDmKhoaByt() {
		return dmKhoaByt;
	}
}

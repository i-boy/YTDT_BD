package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmHocHamList")
public class DmHocHamList extends EntityQuery<DmHocHam> {

	private static final String EJBQL = "select dmHocHam from DmHocHam dmHocHam";

	private static final String[] RESTRICTIONS = {
			"lower(dmHocHam.dmhochamMa) like concat('%',lower(#{dmHocHamList.dmHocHam.dmhochamMa}),'%')",
			"lower(dmHocHam.dmhochamTen) like concat('%',lower(#{dmHocHamList.dmHocHam.dmhochamTen}),'%')", };

	private DmHocHam dmHocHam = new DmHocHam();

	public DmHocHamList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmHocHam getDmHocHam() {
		return dmHocHam;
	}
}

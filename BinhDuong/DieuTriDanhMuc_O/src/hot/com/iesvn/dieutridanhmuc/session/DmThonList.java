package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmThonList")
public class DmThonList extends EntityQuery<DmThon> {

	private static final String EJBQL = "select dmThon from DmThon dmThon";

	private static final String[] RESTRICTIONS = {
			"lower(dmThon.dmthonMa) like concat('%',lower(#{dmThonList.dmThon.dmthonMa}),'%')",
			"lower(dmThon.dmthonTen) like concat('%',lower(#{dmThonList.dmThon.dmthonTen}),'%')", };

	private DmThon dmThon = new DmThon();

	public DmThonList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmThon getDmThon() {
		return dmThon;
	}
}

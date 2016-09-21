package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmTramYTeBhytList")
public class DtDmTramYTeBhytList extends EntityQuery<DtDmTramYTeBhyt> {

	private static final String EJBQL = "select dtDmTramYTeBhyt from DtDmTramYTeBhyt dtDmTramYTeBhyt";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmTramYTeBhyt.dtdmtramytebhytMa) like concat('%',lower(#{dtDmTramYTeBhytList.dtDmTramYTeBhyt.dtdmtramytebhytMa}),'%')",
			"lower(dtDmTramYTeBhyt.dtdmtramytebhytTen) like concat('%',lower(#{dtDmTramYTeBhytList.dtDmTramYTeBhyt.dtdmtramytebhytTen}),'%')", };

	private DtDmTramYTeBhyt dtDmTramYTeBhyt = new DtDmTramYTeBhyt();

	public DtDmTramYTeBhytList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmTramYTeBhyt getDtDmTramYTeBhyt() {
		return dtDmTramYTeBhyt;
	}
}

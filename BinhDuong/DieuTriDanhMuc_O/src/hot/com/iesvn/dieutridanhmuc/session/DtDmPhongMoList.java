package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmPhongMoList")
public class DtDmPhongMoList extends EntityQuery<DtDmPhongMo> {

	private static final String EJBQL = "select dtDmPhongMo from DtDmPhongMo dtDmPhongMo";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmPhongMo.dtdmphongmoGhichu) like concat('%',lower(#{dtDmPhongMoList.dtDmPhongMo.dtdmphongmoGhichu}),'%')",
			"lower(dtDmPhongMo.dtdmphongmoMa) like concat('%',lower(#{dtDmPhongMoList.dtDmPhongMo.dtdmphongmoMa}),'%')",
			"lower(dtDmPhongMo.dtdmphongmoTenphong) like concat('%',lower(#{dtDmPhongMoList.dtDmPhongMo.dtdmphongmoTenphong}),'%')", };

	private DtDmPhongMo dtDmPhongMo = new DtDmPhongMo();

	public DtDmPhongMoList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmPhongMoList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DtDmPhongMo getDtDmPhongMo() {
		return dtDmPhongMo;
	}
}

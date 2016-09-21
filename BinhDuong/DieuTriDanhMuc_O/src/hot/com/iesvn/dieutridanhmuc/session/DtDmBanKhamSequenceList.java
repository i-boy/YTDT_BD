package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmBanKhamSequenceList")
public class DtDmBanKhamSequenceList extends EntityQuery<DtDmBanKhamSequence> {

	private static final String EJBQL = "select dtDmBanKhamSequence from DtDmBanKhamSequence dtDmBanKhamSequence";

	private static final String[] RESTRICTIONS = { "lower(dtDmBanKhamSequence.ma) like concat('%',lower(#{dtDmBanKhamSequenceList.dtDmBanKhamSequence.ma}),'%')", };

	private DtDmBanKhamSequence dtDmBanKhamSequence = new DtDmBanKhamSequence();

	public DtDmBanKhamSequenceList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmBanKhamSequence getDtDmBanKhamSequence() {
		return dtDmBanKhamSequence;
	}
}

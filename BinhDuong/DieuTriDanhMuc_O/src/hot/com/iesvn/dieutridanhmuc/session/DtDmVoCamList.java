package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmVoCamList")
public class DtDmVoCamList extends EntityQuery<DtDmVoCam> {

	private static final String EJBQL = "select dtDmVoCam from DtDmVoCam dtDmVoCam";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmVoCam.dtdmvocamDiengiai) like concat('%',lower(#{dtDmVoCamList.dtDmVoCam.dtdmvocamDiengiai}),'%')",
			"lower(dtDmVoCam.dtdmvocamGhichu) like concat('%',lower(#{dtDmVoCamList.dtDmVoCam.dtdmvocamGhichu}),'%')",
			"lower(dtDmVoCam.dtdmvocamMa) like concat('%',lower(#{dtDmVoCamList.dtDmVoCam.dtdmvocamMa}),'%')",
			"lower(dtDmVoCam.dtdmvocamMaphu) like concat('%',lower(#{dtDmVoCamList.dtDmVoCam.dtdmvocamMaphu}),'%')",
			"lower(dtDmVoCam.dtdmvocamPhanloai) like concat('%',lower(#{dtDmVoCamList.dtDmVoCam.dtdmvocamPhanloai}),'%')", };

	private DtDmVoCam dtDmVoCam = new DtDmVoCam();

	public DtDmVoCamList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmVoCamList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DtDmVoCam getDtDmVoCam() {
		return dtDmVoCam;
	}
}

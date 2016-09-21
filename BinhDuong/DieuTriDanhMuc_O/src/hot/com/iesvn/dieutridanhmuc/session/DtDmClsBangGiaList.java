package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmClsBangGiaList")
public class DtDmClsBangGiaList extends EntityQuery<DtDmClsBangGia> {

	private static final String EJBQL = "select dtDmClsBangGia from DtDmClsBangGia dtDmClsBangGia";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmClsBangGia.dmclsbgLoai) like concat('%',lower(#{dtDmClsBangGiaList.dtDmClsBangGia.dmclsbgLoai}),'%')",
			"lower(dtDmClsBangGia.dtdmclsbgCdha) like concat('%',lower(#{dtDmClsBangGiaList.dtDmClsBangGia.dtdmclsbgCdha}),'%')",
			"lower(dtDmClsBangGia.dtdmclsbgDiengiai) like concat('%',lower(#{dtDmClsBangGiaList.dtDmClsBangGia.dtdmclsbgDiengiai}),'%')",
			"lower(dtDmClsBangGia.dtdmclsbgMa) like concat('%',lower(#{dtDmClsBangGiaList.dtDmClsBangGia.dtdmclsbgMa}),'%')",
			"lower(dtDmClsBangGia.dtdmclsbgMa2) like concat('%',lower(#{dtDmClsBangGiaList.dtDmClsBangGia.dtdmclsbgMa2}),'%')",
			"lower(dtDmClsBangGia.dtdmclsbgPhanbiet) like concat('%',lower(#{dtDmClsBangGiaList.dtDmClsBangGia.dtdmclsbgPhanbiet}),'%')", };

	private DtDmClsBangGia dtDmClsBangGia = new DtDmClsBangGia();

	public DtDmClsBangGiaList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmClsBangGiaList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DtDmClsBangGia getDtDmClsBangGia() {
		return dtDmClsBangGia;
	}
}

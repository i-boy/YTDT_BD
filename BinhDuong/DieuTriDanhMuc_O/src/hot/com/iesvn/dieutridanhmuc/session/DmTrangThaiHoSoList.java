package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmTrangThaiHoSoList")
public class DmTrangThaiHoSoList extends EntityQuery<DmTrangThaiHoSo> {

	private static final String EJBQL = "select dmTrangThaiHoSo from DmTrangThaiHoSo dmTrangThaiHoSo";

	private static final String[] RESTRICTIONS = {
			"lower(dmTrangThaiHoSo.dmtthsGhichu) like concat('%',lower(#{dmTrangThaiHoSoList.dmTrangThaiHoSo.dmtthsGhichu}),'%')",
			"lower(dmTrangThaiHoSo.dmtthsMa) like concat('%',lower(#{dmTrangThaiHoSoList.dmTrangThaiHoSo.dmtthsMa}),'%')",
			"lower(dmTrangThaiHoSo.dmtthsTen) like concat('%',lower(#{dmTrangThaiHoSoList.dmTrangThaiHoSo.dmtthsTen}),'%')", };

	private DmTrangThaiHoSo dmTrangThaiHoSo = new DmTrangThaiHoSo();

	public DmTrangThaiHoSoList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmTrangThaiHoSo getDmTrangThaiHoSo() {
		return dmTrangThaiHoSo;
	}
}

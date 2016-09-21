package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmLoaiHoSoList")
public class DmLoaiHoSoList extends EntityQuery<DmLoaiHoSo> {

	private static final String EJBQL = "select dmLoaiHoSo from DmLoaiHoSo dmLoaiHoSo";

	private static final String[] RESTRICTIONS = {
			"lower(dmLoaiHoSo.dmloaihosoGhichu) like concat('%',lower(#{dmLoaiHoSoList.dmLoaiHoSo.dmloaihosoGhichu}),'%')",
			"lower(dmLoaiHoSo.dmloaihosoMa) like concat('%',lower(#{dmLoaiHoSoList.dmLoaiHoSo.dmloaihosoMa}),'%')",
			"lower(dmLoaiHoSo.dmloaihosoTen) like concat('%',lower(#{dmLoaiHoSoList.dmLoaiHoSo.dmloaihosoTen}),'%')", };

	private DmLoaiHoSo dmLoaiHoSo = new DmLoaiHoSo();

	public DmLoaiHoSoList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmLoaiHoSo getDmLoaiHoSo() {
		return dmLoaiHoSo;
	}
}

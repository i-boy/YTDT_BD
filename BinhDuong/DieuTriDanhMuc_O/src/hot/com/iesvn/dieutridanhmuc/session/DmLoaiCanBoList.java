package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmLoaiCanBoList")
public class DmLoaiCanBoList extends EntityQuery<DmLoaiCanBo> {

	private static final String EJBQL = "select dmLoaiCanBo from DmLoaiCanBo dmLoaiCanBo";

	private static final String[] RESTRICTIONS = {
			"lower(dmLoaiCanBo.dmloaicanboMa) like concat('%',lower(#{dmLoaiCanBoList.dmLoaiCanBo.dmloaicanboMa}),'%')",
			"lower(dmLoaiCanBo.dmloaicanboTen) like concat('%',lower(#{dmLoaiCanBoList.dmLoaiCanBo.dmloaicanboTen}),'%')", };

	private DmLoaiCanBo dmLoaiCanBo = new DmLoaiCanBo();

	public DmLoaiCanBoList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmLoaiCanBo getDmLoaiCanBo() {
		return dmLoaiCanBo;
	}
}

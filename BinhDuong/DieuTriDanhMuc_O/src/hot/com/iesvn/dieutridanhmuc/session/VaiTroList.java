package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("vaiTroList")
public class VaiTroList extends EntityQuery<VaiTro> {

	private static final String EJBQL = "select vaiTro from VaiTro vaiTro";

	private static final String[] RESTRICTIONS = {
			"lower(vaiTro.vaitroMa) like concat('%',lower(#{vaiTroList.vaiTro.vaitroMa}),'%')",
			"lower(vaiTro.vaitroTen) like concat('%',lower(#{vaiTroList.vaiTro.vaitroTen}),'%')", };

	private VaiTro vaiTro = new VaiTro();

	public VaiTroList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public VaiTroList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public VaiTro getVaiTro() {
		return vaiTro;
	}
}

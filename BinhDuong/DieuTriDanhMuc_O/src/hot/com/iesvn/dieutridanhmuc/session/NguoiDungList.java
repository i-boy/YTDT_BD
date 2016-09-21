package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("nguoiDungList")
public class NguoiDungList extends EntityQuery<NguoiDung> {

	private static final String EJBQL = "select nguoiDung from NguoiDung nguoiDung";

	private static final String[] RESTRICTIONS = {
			"lower(nguoiDung.ndMadangnhap) like concat('%',lower(#{nguoiDungList.nguoiDung.ndMadangnhap}),'%')",
			"lower(nguoiDung.ndTendangnhap) like concat('%',lower(#{nguoiDungList.nguoiDung.ndTendangnhap}),'%')", };

	private NguoiDung nguoiDung = new NguoiDung();

	public NguoiDungList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public NguoiDung getNguoiDung() {
		return nguoiDung;
	}
	
	public NguoiDungList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}
}

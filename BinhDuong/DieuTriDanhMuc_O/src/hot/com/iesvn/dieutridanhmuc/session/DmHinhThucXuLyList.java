package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmHinhThucXuLyList")
public class DmHinhThucXuLyList extends EntityQuery<DmHinhThucXuLy> {

	private static final String EJBQL = "select dmHinhThucXuLy from DmHinhThucXuLy dmHinhThucXuLy";

	private static final String[] RESTRICTIONS = {
			"lower(dmHinhThucXuLy.dmhinhthucxulyMa) like concat('%',lower(#{dmHinhThucXuLyList.dmHinhThucXuLy.dmhinhthucxulyMa}),'%')",
			"lower(dmHinhThucXuLy.dmhinhthucxulyTen) like concat('%',lower(#{dmHinhThucXuLyList.dmHinhThucXuLy.dmhinhthucxulyTen}),'%')", };

	private DmHinhThucXuLy dmHinhThucXuLy = new DmHinhThucXuLy();

	public DmHinhThucXuLyList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmHinhThucXuLy getDmHinhThucXuLy() {
		return dmHinhThucXuLy;
	}
}

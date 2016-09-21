package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("nguoiDungVaiTroList")
public class NguoiDungVaiTroList extends EntityQuery<NguoiDungVaiTro> {

	private static final String EJBQL = "select nguoiDungVaiTro from NguoiDungVaiTro nguoiDungVaiTro";

	private static final String[] RESTRICTIONS = {};

	private NguoiDungVaiTro nguoiDungVaiTro = new NguoiDungVaiTro();

	public NguoiDungVaiTroList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public NguoiDungVaiTroList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public NguoiDungVaiTro getNguoiDungVaiTro() {
		return nguoiDungVaiTro;
	}
	
	public boolean getRoleByNguoidungMaso(Integer nguoiDungMaso){
		setEjbql("select nguoiDungVaiTro from NguoiDungVaiTro nguoiDungVaiTro where ND_MASO = " + nguoiDungMaso + " and VAITRO_MASO = 10");
		if(this.getResultList().size() != 0){
			return true;
		}		
		return false;
	}
}

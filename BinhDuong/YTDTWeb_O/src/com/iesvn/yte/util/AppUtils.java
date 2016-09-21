package com.iesvn.yte.util;

import org.jboss.seam.contexts.Contexts;

import com.iesvn.yte.dieutri.vienphi.action.ChonXuatFileBaoCao_VienPhi;

public class AppUtils {
	public static ChonXuatFileBaoCao_VienPhi getChonXuatFileBaoCao_VienPhiTrongContexts() {
		FacesUtils.getValueOfExpr("#{ChonXuatFileBaoCao_VienPhi}", String.class);
		return (ChonXuatFileBaoCao_VienPhi)Contexts.getSessionContext().get("ChonXuatFileBaoCao_VienPhi");
	}
}

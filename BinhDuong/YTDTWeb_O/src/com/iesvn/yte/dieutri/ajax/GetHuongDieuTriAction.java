// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   GetHuongDieuTriAction.java

package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;

public class GetHuongDieuTriAction extends Action {

	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List listDtDmHuongDt = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listDtDmHuongDt = dtutilDelegate.findByNgayGioCN(Double
					.parseDouble(request), "DtDmHuongDt",
					"dtdmhuongdtNgaygiocn");
		}
		catch (Exception ex) {
		}
		buf.append("<list>");
		/*
		if (listDtDmHuongDt != null) {
			for (Object obj : listDtDmHuongDt) {
				DtDmHuongDt hdt = (DtDmHuongDt) obj;
				buf.append("<record " +
						" MaSo='" + hdt.getDtdmhuongdtMaso()
						+ "' Ma='" + hdt.getDtdmhuongdtMa() 
						+ "' Ten='" + hdt.getDtdmhuongdtTen()
						+ "' DT='" + hdt.getDtdmhuongdtChon() 
						+ "' NgayChinhSua='" + hdt.getDtdmhuongdtNgaygiocn() + "' />");
			}
		}
		*/
		buf.append("</list>");
		return buf.toString();
	}
}



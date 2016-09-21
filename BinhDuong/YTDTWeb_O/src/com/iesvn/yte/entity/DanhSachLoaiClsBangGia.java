package com.iesvn.yte.entity;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.iesvn.yte.util.IConstantsRes;

public class DanhSachLoaiClsBangGia {	
	private List<SelectItem> listLoaiCls;
	
	public DanhSachLoaiClsBangGia() {
		listLoaiCls = new ArrayList<SelectItem>();
		listLoaiCls.add(new SelectItem("","..."));
		// Version 1
		/*listLoaiCls.add(new SelectItem("A1","Xét nghiệm - Huyết học"));
		listLoaiCls.add(new SelectItem("A2","Xét nghiệm - Hóa sinh"));
		listLoaiCls.add(new SelectItem("A3","Xét nghiệm - Vi khuẩn"));
		listLoaiCls.add(new SelectItem("A4","Xét nghiệm - HIV"));
		listLoaiCls.add(new SelectItem("A5","Xét nghiệm - Khác"));
		
		listLoaiCls.add(new SelectItem("B1","Chẩn đoán hình ảnh - Chiếu X Quang"));
		listLoaiCls.add(new SelectItem("B2","Chẩn đoán hình ảnh - Chụp X Quang"));
		listLoaiCls.add(new SelectItem("B3","Chẩn đoán hình ảnh - Siêu âm"));
		listLoaiCls.add(new SelectItem("B4","Chẩn đoán hình ảnh - CT, Scanner"));
		listLoaiCls.add(new SelectItem("B5","Chẩn đoán hình ảnh - Cộng hưởng từ"));
		listLoaiCls.add(new SelectItem("B6","Chẩn đoán hình ảnh - Khác"));
		
		listLoaiCls.add(new SelectItem("C1","Thăm dò chức năng - Điện tim"));
		listLoaiCls.add(new SelectItem("C2","Thăm dò chức năng - Điện não"));
		listLoaiCls.add(new SelectItem("C3","Thăm dò chức năng - Nội soi"));
		listLoaiCls.add(new SelectItem("C4","Thăm dò chức năng - Khác"));
		
		listLoaiCls.add(new SelectItem("D1","Truyền máu"));
		
		listLoaiCls.add(new SelectItem("E1","Giải phẩu bệnh - Đại thể"));
		listLoaiCls.add(new SelectItem("E2","Giải phẩu bệnh - Vi thể"));
		listLoaiCls.add(new SelectItem("E3","Giải phẩu bệnh - Khác"));
		*/
		// Version 2
		/*
		listLoaiCls.add(new SelectItem("10","Huyết học"));		
		listLoaiCls.add(new SelectItem("13","Sinh hóa"));		
		listLoaiCls.add(new SelectItem("11","Vi trùng"));
		listLoaiCls.add(new SelectItem("12","Ký sinh trùng"));				
		listLoaiCls.add(new SelectItem("14","HIV"));				
		listLoaiCls.add(new SelectItem("15","Xét nghiệm khác"));
		
		
		listLoaiCls.add(new SelectItem("1","Chiếu X Quang"));		
		listLoaiCls.add(new SelectItem("2","Chụp X Quang(T)"));
		listLoaiCls.add(new SelectItem("3","Chụp X Quang(KT)"));		
		listLoaiCls.add(new SelectItem("5","Siêu âm"));		
		listLoaiCls.add(new SelectItem("7","CT Scanner"));		
		listLoaiCls.add(new SelectItem("28","CĐHA (Cộng hưởng từ)"));		
		listLoaiCls.add(new SelectItem("29","CĐHA khác"));
				
		listLoaiCls.add(new SelectItem("4","Điện tim"));		
		listLoaiCls.add(new SelectItem("8","Điện não"));		
		listLoaiCls.add(new SelectItem("6","Nội soi"));		
		listLoaiCls.add(new SelectItem("9","TDCN (khác)"));
				
		listLoaiCls.add(new SelectItem("27","Truyền máu"));
				
		listLoaiCls.add(new SelectItem("20","Mổ đại thể"));		
		listLoaiCls.add(new SelectItem("21","XN Vi thể"));		
		listLoaiCls.add(new SelectItem("22","GPB (khác)"));
		
		// Cac loai duoi day khong su dung trong bao cao Can lam sang
		listLoaiCls.add(new SelectItem("16","KSTST (Dương tính)"));
		listLoaiCls.add(new SelectItem("17","KSTST (FALCI)"));
		listLoaiCls.add(new SelectItem("18","KSTST (VIVAX)"));
		listLoaiCls.add(new SelectItem("19","KSTST (F+V)"));
		listLoaiCls.add(new SelectItem("23","VLTL (Chiếu HN)"));
		listLoaiCls.add(new SelectItem("24","VLTL (Kéo tạ)"));
		listLoaiCls.add(new SelectItem("25","VLTL (Tập vận động)"));
		listLoaiCls.add(new SelectItem("26","Khác"));
		*/
		// Version 3
		listLoaiCls.add(new SelectItem("10",IConstantsRes.DS_LOAI_CLS_BANG_GIA_HUYET_HOC));		
		listLoaiCls.add(new SelectItem("13",IConstantsRes.DS_LOAI_CLS_BANG_GIA_SINH_HOA));		
		listLoaiCls.add(new SelectItem("11",IConstantsRes.DS_LOAI_CLS_BANG_GIA_VI_TRUNG));
		listLoaiCls.add(new SelectItem("12",IConstantsRes.DS_LOAI_CLS_BANG_GIA_KY_SINH_TRUNG));				
		listLoaiCls.add(new SelectItem("14",IConstantsRes.DS_LOAI_CLS_BANG_GIA_HIV));				
		listLoaiCls.add(new SelectItem("15",IConstantsRes.DS_LOAI_CLS_BANG_GIA_XNKHAC));
		
		
		listLoaiCls.add(new SelectItem("1",IConstantsRes.DS_LOAI_CLS_BANG_GIA_CHIEU_XQ));		
		listLoaiCls.add(new SelectItem("2",IConstantsRes.DS_LOAI_CLS_BANG_GIA_CHUP_XQT));
		listLoaiCls.add(new SelectItem("3",IConstantsRes.DS_LOAI_CLS_BANG_GIA_CHUP_XQKT));		
		listLoaiCls.add(new SelectItem("5",IConstantsRes.DS_LOAI_CLS_BANG_GIA_SIEU_AM));		
		listLoaiCls.add(new SelectItem("7",IConstantsRes.DS_LOAI_CLS_BANG_GIA_CT_SCANNER));		
		listLoaiCls.add(new SelectItem("28",IConstantsRes.DS_LOAI_CLS_BANG_GIA_CDHA_CHT));		
		listLoaiCls.add(new SelectItem("29",IConstantsRes.DS_LOAI_CLS_BANG_GIA_CDHA_KHAC));
				
		listLoaiCls.add(new SelectItem("4",IConstantsRes.DS_LOAI_CLS_BANG_GIA_DIEN_TIM));		
		listLoaiCls.add(new SelectItem("8",IConstantsRes.DS_LOAI_CLS_BANG_GIA_DIEN_NAO));		
		listLoaiCls.add(new SelectItem("6",IConstantsRes.DS_LOAI_CLS_BANG_GIA_NOI_SOI));		
		listLoaiCls.add(new SelectItem("9",IConstantsRes.DS_LOAI_CLS_BANG_GIA_TDCN_KHAC));
				
		listLoaiCls.add(new SelectItem("27",IConstantsRes.DS_LOAI_CLS_BANG_GIA_TRUYEN_MAU));
				
		listLoaiCls.add(new SelectItem("20",IConstantsRes.DS_LOAI_CLS_BANG_GIA_MO_DAI_THE));		
		listLoaiCls.add(new SelectItem("21",IConstantsRes.DS_LOAI_CLS_BANG_GIA_XN_VI_THE));		
		listLoaiCls.add(new SelectItem("22",IConstantsRes.DS_LOAI_CLS_BANG_GIA_GPB_KHAC));
		
		// Cac loai duoi day khong su dung trong bao cao Can lam sang
		listLoaiCls.add(new SelectItem("16",IConstantsRes.DS_LOAI_CLS_BANG_GIA_KSTST_DT));
		listLoaiCls.add(new SelectItem("17",IConstantsRes.DS_LOAI_CLS_BANG_GIA_KSTST_FALCI));
		listLoaiCls.add(new SelectItem("18",IConstantsRes.DS_LOAI_CLS_BANG_GIA_KSTST_VIVAX));
		listLoaiCls.add(new SelectItem("19",IConstantsRes.DS_LOAI_CLS_BANG_GIA_KSTST_FV));
		listLoaiCls.add(new SelectItem("23",IConstantsRes.DS_LOAI_CLS_BANG_GIA_VLTL_CHIEU_HN));
		listLoaiCls.add(new SelectItem("24",IConstantsRes.DS_LOAI_CLS_BANG_GIA_VLTL_KEO_TA));
		listLoaiCls.add(new SelectItem("25",IConstantsRes.DS_LOAI_CLS_BANG_GIA_VLTL_TAP_VAN_DONG));
		listLoaiCls.add(new SelectItem("26",IConstantsRes.DS_LOAI_CLS_BANG_GIA_KHAC));
	}
	public List<SelectItem> getListLoaiCls() {
		return listLoaiCls;
	}
	public void setListLoaiCls(List<SelectItem> listLoaiCls) {
		this.listLoaiCls = listLoaiCls;
	}
	
}

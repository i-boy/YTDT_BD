package com.iesvn.yte.dieutri.duocpham.action;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.security.Restrict;

import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;

@Scope(ScopeType.CONVERSATION)
@Name("Xuatdendonvituyenduoi")
public class XuatDenCacDonViTuyenDuoiAction implements Serializable {
	private static final long serialVersionUID = 10L;
	private static Logger logger = Logger.getLogger(XuatDenCacDonViTuyenDuoiAction.class);
	
	@In(required = false)
	@Out(required = false)
	private String tenChuongTrinh;
	
	
	
	@Restrict("#{s:hasRole('NV_KhoaDuoc') or s:hasRole('QT_HT_Duoc')}")
	@Begin(join=true)
	public String init(String tumenu) {
		logger.info("-----init()-----");
		reset();
		if ("KC".equals(tumenu)){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoChinh;
			
		}
		
		
		return "Xuatdendonvituyenduoi";
	}
	@End
	public void endFunc(){
		
	}
	
	
	
	public void reset() {
	
	}
	
}
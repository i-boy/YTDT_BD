package com.iesvn.yte.identity.extension;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;

import com.iesvn.yte.dieutri.delegate.CauHinhDelegate;
import com.iesvn.yte.dieutri.delegate.YteSequenceDelegate;
import com.iesvn.yte.dieutri.entity.CauHinh;
import com.iesvn.yte.util.IConstantsRes;


@Scope(ScopeType.CONVERSATION)
@Name("admin_cauhinh_client")
public class AdminCauHinhClientAction implements Serializable{
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(AdminCauHinhClientAction.class);
	private String phuongPhapTimKiemThuoc;
	private String phuongPhapTimKiemCLS;
	
	
	@Begin(join =  true)
	public String init() {
		log.info("begin init()");
		
		resetAsBegin();
		
		log.info("end init()");
		return "AdminCauHinhHeThong";
	}
	
	private void resetAsBegin(){
		phuongPhapTimKiemThuoc = "0";
		phuongPhapTimKiemCLS = "0";
		// tim cau hinh hien co'
		
		CauHinhDelegate cauHinhD = CauHinhDelegate.getInstance();
		List<CauHinh> lstCauHinh = cauHinhD.findAll();
		
		for (CauHinh cauhinh: lstCauHinh){
			String name = cauhinh.getChbvName();
			String giatri = cauhinh.getChbvGiatri();
			if ("PHUONG_PHAP_SEARCH_THUOC".equals(name)){
				phuongPhapTimKiemThuoc = giatri;
				continue;
			}
			if ("PHUONG_PHAP_SEARCH_CLS".equals(name)){
				phuongPhapTimKiemCLS = giatri;
				continue;
			}
			
		
		}
	}
	@End
	public void end() {
		log.info("begin end()");
		
		
		log.info("end end()");
	}
	
	public void ghiNhan(){
		log.info("-----ghiNhan()-----");
		
		CauHinhDelegate cauHinhD = CauHinhDelegate.getInstance();
		List<CauHinh> lstCauHinh = cauHinhD.findAll();
		
		for (CauHinh cauhinh: lstCauHinh){
			String name = cauhinh.getChbvName();		
			cauhinh.setChbvNgaygiocn((double)(new Date()).getTime());
			
			if ("PHUONG_PHAP_SEARCH_THUOC".equals(name)){
				
				cauhinh.setChbvGiatri(phuongPhapTimKiemThuoc);
				cauHinhD.edit(cauhinh);
				continue;
			}
			if ("PHUONG_PHAP_SEARCH_CLS".equals(name)){
				cauhinh.setChbvGiatri(phuongPhapTimKiemCLS);
				
				cauHinhD.edit(cauhinh);
				continue;
			}
			
		
		}
		FacesMessages.instance().add(IConstantsRes.SUCCESS);
		
	}
	
	public void reset(){
		resetAsBegin();
	}
	
	public void startHeThongBaoCao(){
		YteSequenceDelegate timer = YteSequenceDelegate.getInstance();
		String THOI_GIAN_LAP_HE_THONG_DONG_HO = IConstantsRes.THOI_GIAN_LAP_HE_THONG_DONG_HO;
		long thoigian = 1*60*60;
		try{
			thoigian = Long.parseLong(THOI_GIAN_LAP_HE_THONG_DONG_HO);
		}catch(Exception ex){
			thoigian = 1*60*60;
		}
		timer.startMyTimer(thoigian*1000);
		
	}
	
	public void copyGear(){
		String path = IConstantsRes.PATH_BASE + "/autocopy.bat " 
					+ IConstantsRes.PATH_GEAR_SERVER + " " + IConstantsRes.PATH_GEAR_SHARE + " "
					+ IConstantsRes.SHARE_FOLDER_IP_USERNAME + " " + IConstantsRes.SHARE_FOLDER_PASS;
		boolean err = false;
		try {
			log.info("Copy Gear to shared folder: " + path);
			Runtime.getRuntime().exec(path);
		} catch (IOException e) {
			log.info("ERROR copy Gear to shared folder !!!");
			err = true;
			e.printStackTrace();
		}
		if(err){
			FacesMessages.instance().add(IConstantsRes.FAIL);
		} else {
			FacesMessages.instance().add(IConstantsRes.SUCCESS);
		}
		
	}

	public String getPhuongPhapTimKiemThuoc() {
		return phuongPhapTimKiemThuoc;
	}

	public void setPhuongPhapTimKiemThuoc(String phuongPhapTimKiemThuoc) {
		this.phuongPhapTimKiemThuoc = phuongPhapTimKiemThuoc;
	}

	public String getPhuongPhapTimKiemCLS() {
		return phuongPhapTimKiemCLS;
	}

	public void setPhuongPhapTimKiemCLS(String phuongPhapTimKiemCLS) {
		this.phuongPhapTimKiemCLS = phuongPhapTimKiemCLS;
	}

	
		
}

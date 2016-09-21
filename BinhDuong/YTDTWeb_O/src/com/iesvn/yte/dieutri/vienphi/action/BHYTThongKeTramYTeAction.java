package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.ThongKeTramYTeBhytDelegate;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.DtDmNhomBhyt;
import com.iesvn.yte.dieutri.entity.DtDmTramYTeBhyt;
import com.iesvn.yte.dieutri.entity.ThongKeTramYTeBhyt;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("bhyt_thongketramyte")
@Synchronized(timeout = 6000000)
public class BHYTThongKeTramYTeAction implements Serializable {



	private static final long serialVersionUID = 10L;
	
	
	private static Logger log = Logger.getLogger(BHYTThongKeTramYTeAction.class);

	
	
	@DataModel
	private java.util.List<ThongKeTramYTeBhyt> ctctTKTramYTes;
	
	@In(required = false)
	@Out(required = false)
	Identity identity;

	
	@End 
	public void end(){
		
	}
	
	
	
	private DtDmNhanVien nhanVienCN = null;
	
	private String thoigian_thang=null;  
	private String thoigian_nam=null;
	
	private Integer masonhomdoituong;
	private String manhomdoituong;
	
	private Integer masotramyte;
	private String matramyte;
	
	private List<DtDmNhomBhyt> lstDtDmNhomBhyt;
	
	@In(required = false)
	@Out(required = false)	
	private String noitruorngoaitru ; //noitru or ngoaitru
	
	@Begin(join = true)
	public String init(String noiorngoai){ 
		this.noitruorngoaitru = noiorngoai;
		resetValue();		
	
		ngayhientai = Utils.getCurrentDate();
		
		DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
		nhanVienCN = nvDelegate.findByNd(identity.getUsername());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		
		thoigian_thang=String.valueOf(currentDate.getMonth() +1);  
		thoigian_nam=String.valueOf(currentDate.getYear()+1900);
		
		ctctTKTramYTes = new ArrayList<ThongKeTramYTeBhyt>();
		
		/**
		DieuTriUtilDelegate dtUDele = DieuTriUtilDelegate.getInstance();
		lstDtDmNhomBhyt = (List<DtDmNhomBhyt>) dtUDele.findAll("DtDmNhomBhyt");
		log.info(lstDtDmNhomBhyt);
		if (lstDtDmNhomBhyt != null){
			for (DtDmNhomBhyt nhom: lstDtDmNhomBhyt){
				
				ThongKeTramYTeBhyt thongKeTramYTeBhyt = new ThongKeTramYTeBhyt();
				
				thongKeTramYTeBhyt.setDtdmnhombhytMaso(nhom);
				ctctTKTramYTes.add(thongKeTramYTeBhyt);
			}
		}
		*/
		log.info(ctctTKTramYTes);
		return "thongkechiphitramyte";
	}

	public void TimKiem(){
		//tim tram y te
		DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
		DtDmTramYTeBhyt tramyte = (DtDmTramYTeBhyt) dtUtil.findByMa(matramyte, "DtDmTramYTeBhyt", "dtdmtramytebhytMa");
		
		
		ThongKeTramYTeBhytDelegate tkTramYTe = ThongKeTramYTeBhytDelegate.getInstance();
		
		Boolean noitru ;
		if ("noitru".equals(noitruorngoaitru)){
			noitru = true;
		}else{
			noitru = false;
		}
		
		ctctTKTramYTes = tkTramYTe.findByThangNamTramYTe(thoigian_thang, thoigian_nam, matramyte,noitru);	
		if (ctctTKTramYTes == null || ctctTKTramYTes.size() == 0){
			ctctTKTramYTes = new ArrayList<ThongKeTramYTeBhyt>();
			DieuTriUtilDelegate dtUDele = DieuTriUtilDelegate.getInstance();
			lstDtDmNhomBhyt = (List<DtDmNhomBhyt>) dtUDele.findAll("DtDmNhomBhyt");
			log.info(lstDtDmNhomBhyt);
			if (lstDtDmNhomBhyt != null){
				for (DtDmNhomBhyt nhom: lstDtDmNhomBhyt){
					
					ThongKeTramYTeBhyt thongKeTramYTeBhyt = new ThongKeTramYTeBhyt();
					
					thongKeTramYTeBhyt.setDtdmnhombhytMaso(nhom);
					
					thongKeTramYTeBhyt.setTktramytebhytThang(thoigian_thang);
					thongKeTramYTeBhyt.setTktramytebhytNam(thoigian_nam);
					thongKeTramYTeBhyt.setDtdmtramytebhytMaso(tramyte);
					
					if ("noitru".equals(noitruorngoaitru)){
						thongKeTramYTeBhyt.setDtdmtramytebhytNoiTru(true);
					}else{
						thongKeTramYTeBhyt.setDtdmtramytebhytNoiTru(false);
					}
					
					ctctTKTramYTes.add(thongKeTramYTeBhyt);
				}
			}	
		}
	}
	
	public void resetValue(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		
		thoigian_thang=String.valueOf(currentDate.getMonth() +1);  
		thoigian_nam=String.valueOf(currentDate.getYear()+1900);
		ctctTKTramYTes = new ArrayList<ThongKeTramYTeBhyt>();
	}
	
	

	//
	// ***************************************************************************************
	// Ham ghi nhan
	public String ghiNhanAjax() throws Exception {
		log.info("*****Begin ghiNhanAjax() *****");
		
		if (ctctTKTramYTes == null || ctctTKTramYTes.size() == 0){
			FacesMessages.instance().add(IConstantsRes.TRAM_Y_TE_CHON_THANG_NAM_TRAM_Y_TE);
			return "";
		}
		
		try {
			
			
			log.info("----------------");
			for ( ThongKeTramYTeBhyt tk: ctctTKTramYTes){
				log.info(tk.getTktramytebhytSothebhyt());
			}
			log.info("----------------");
			
			
			ThongKeTramYTeBhytDelegate tkTramYTe = ThongKeTramYTeBhytDelegate.getInstance();
			tkTramYTe.luuTruTTThongKeTramYTeBHYT(ctctTKTramYTes);
			
			//////////////////////////////////////////////////////////////////
			
			
			FacesMessages.instance().add(IConstantsRes.SUCCESS);
			
		} catch (Exception e) {
			FacesMessages.instance().add(IConstantsRes.FAIL);
			//resultHidden = "fail";
			e.printStackTrace();
			log.error("*************loi***********" + e.toString());
		}
		ctctTKTramYTes = new ArrayList<ThongKeTramYTeBhyt>();
		
		log.info("*****End ghiNhanAjax() *****");
		
		
		return null;

	}

	private String ngayhientai;
	

	public String getNgayhientai() {
		return ngayhientai;
	}


	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
	}


	public static Logger getLog() {
		return log;
	}


	public static void setLog(Logger log) {
		BHYTThongKeTramYTeAction.log = log;
	}


	public java.util.List<ThongKeTramYTeBhyt> getCtctTKTramYTes() {
		return ctctTKTramYTes;
	}


	public void setCtctTKTramYTes(java.util.List<ThongKeTramYTeBhyt> ctctTKTramYTes) {
		this.ctctTKTramYTes = ctctTKTramYTes;
	}


	public Identity getIdentity() {
		return identity;
	}


	public void setIdentity(Identity identity) {
		this.identity = identity;
	}


	public DtDmNhanVien getNhanVienCN() {
		return nhanVienCN;
	}


	public void setNhanVienCN(DtDmNhanVien nhanVienCN) {
		this.nhanVienCN = nhanVienCN;
	}


	public String getThoigian_thang() {
		return thoigian_thang;
	}


	public void setThoigian_thang(String thoigian_thang) {
		this.thoigian_thang = thoigian_thang;
	}


	public String getThoigian_nam() {
		return thoigian_nam;
	}


	public void setThoigian_nam(String thoigian_nam) {
		this.thoigian_nam = thoigian_nam;
	}


	public Integer getMasonhomdoituong() {
		return masonhomdoituong;
	}


	public void setMasonhomdoituong(Integer masonhomdoituong) {
		this.masonhomdoituong = masonhomdoituong;
	}


	public String getManhomdoituong() {
		return manhomdoituong;
	}


	public void setManhomdoituong(String manhomdoituong) {
		this.manhomdoituong = manhomdoituong;
	}


	public Integer getMasotramyte() {
		return masotramyte;
	}


	public void setMasotramyte(Integer masotramyte) {
		this.masotramyte = masotramyte;
	}


	public String getMatramyte() {
		return matramyte;
	}


	public void setMatramyte(String matramyte) {
		this.matramyte = matramyte;
	}


	public String getNoitruorngoaitru() {
		return noitruorngoaitru;
	}


	public void setNoitruorngoaitru(String noitruorngoaitru) {
		this.noitruorngoaitru = noitruorngoaitru;
	}


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}




}



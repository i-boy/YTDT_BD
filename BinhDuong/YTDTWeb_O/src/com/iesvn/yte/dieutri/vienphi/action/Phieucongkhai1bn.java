/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DmKhoaDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaKhoaDelegate;
import com.iesvn.yte.dieutri.entity.HsThtoan;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.HsbaKhoa;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmTang;
import com.iesvn.yte.util.HoSoThanhToanUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;
import com.lowagie.text.pdf.PdfCopyFields;
import com.lowagie.text.pdf.PdfReader;

@Name("B3133_Phieucongkhai1bn")
@Scope(CONVERSATION)
public class Phieucongkhai1bn implements Serializable {
	@Logger
	private Log log;
	
	@Out(required=false)
	@In(required=false)
	private String reportPathVP=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeVP=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintVP=null;
	
	@Out(required=false)
	@In(required=false)
	private String resetFormB3133=null;
	
	private HsbaKhoa hsbaKhoa = null;
	private Hsba hsba = null;
	private HsbaBhyt hsbaBhyt = null;

	private int index=0;
	private String ngayhientai="";
//	private String khoaMa="";
	private String hsbaMa="";
	private String ngaydungstr="";
	private String denngayStr = "";
	private String fromMenu = "";
	
	
	private DmKhoa dmKhoa;
	private DmTang dmTang;
	private List<SelectItem> listDmTangs = new ArrayList<SelectItem>();
	private HashMap<String, DmKhoa> hmDmKhoaNTAll = new HashMap<String, DmKhoa>();
	private List<SelectItem> listDmKhoaNTs = new ArrayList<SelectItem>();
	private DmKhoaDelegate dmKhoaDel = DmKhoaDelegate.getInstance();
	private List<DmKhoa> listDmKhoaNTAll = new ArrayList<DmKhoa>();
	
	@Begin(join=true)
	public String init(String fromType) {
		log.info("init(), fromType : " + fromType);
		initresetFormB3133();
		refreshDmKhoaNT();
		fromMenu = fromType;
		return "VienPhiTaiKhoa_XemInPhieu_PhieuCongKhaiCuaBN";
	}
	
	@End
	public void EndFunc(){
		
	}
	
	@Factory("resetFormB3133")
	public void initresetFormB3133() {
		log.info("initresetFormB3133()");
		ngayhientai = Utils.getCurrentDate();
		resetForm();
	}
	
	public void onblurMaKhoaAction() {
		log.info("-----BEGIN onblurMaKhoaAction()-----");
		if (dmKhoa != null && dmKhoa.getDmkhoaMa() != null) {
			String maKhoa = dmKhoa.getDmkhoaMa();
			if (hmDmKhoaNTAll != null) {
				DmKhoa dmKhoa = (DmKhoa) hmDmKhoaNTAll.get(maKhoa.toUpperCase());
				if (dmKhoa != null) {
					this.dmKhoa.setDmkhoaMaso(dmKhoa.getDmkhoaMaso());
					this.dmKhoa.setDmkhoaMa(dmKhoa.getDmkhoaMa());
					this.dmKhoa.setDmkhoaTen(dmKhoa.getDmkhoaTen());
					log.info("Tim ma khoa: Da thay khoa "
							+ dmKhoa.getDmkhoaTen());
				} else {
					this.dmKhoa = new DmKhoa();
					return;
				}
			}
			dmTang = new DmTang();
			refreshDmTang();
		}
		log.info("-----END onblurMaKhoaAction()-----");
	}

	public void refreshDmTang() {
		listDmTangs = new ArrayList<SelectItem>();
		if (dmKhoa != null && dmKhoa.getDmkhoaMaso() != null) {
			String khoaMa = dmKhoa.getDmkhoaMa();
			DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
	
			List<DmTang> lstDmTangs = dtUtil.findMaLike("DmTang","dmkhoaMaso.dmkhoaMa", "dmtangChon", khoaMa, true);
			if (lstDmTangs != null && lstDmTangs.size() > 0) {
				for (DmTang dmTang : lstDmTangs) {
					listDmTangs.add(new SelectItem(dmTang.getDmtangTen()));
				}
//				for (DmTang dmTang : lstDmTangs) {
//					if (dmTang.getDmtangDefault().booleanValue() == true) {
//						this.dmTang = dmTang;
//						break;
//					}
//				}
			}
		}
	}

	public void refreshDmKhoaNT() {
		dmKhoaDel = DmKhoaDelegate.getInstance();
		listDmKhoaNTAll.clear();
		listDmKhoaNTs.clear();
		listDmKhoaNTAll = dmKhoaDel.getKhoaNT();
		hmDmKhoaNTAll.clear();
		for (DmKhoa o : listDmKhoaNTAll) {
			hmDmKhoaNTAll.put(o.getDmkhoaMa(), o);
		}
		for (DmKhoa each : listDmKhoaNTAll) {
			listDmKhoaNTs.add(new SelectItem(each.getDmkhoaTen()));
		}
	}
	
	public void onblurTenKhoaAction(){
		log.info("-----BEGIN onblurTenKhoaAction()-----");
		if(dmKhoa != null && dmKhoa.getDmkhoaTen() != null){
			String tenKhoa = dmKhoa.getDmkhoaTen();
			Boolean hasTenKhoa = false;
			java.util.Set set = hmDmKhoaNTAll.entrySet();
		    Iterator i = set.iterator();
		    DmKhoa dmKhoa_Finded = new DmKhoa();
		    while(i.hasNext()){
		    	Map.Entry me = (Map.Entry)i.next();
		    	DmKhoa dmKhoa = (DmKhoa)me.getValue();
		    	if(dmKhoa.getDmkhoaTen() == tenKhoa || dmKhoa.getDmkhoaTen().equals(tenKhoa)){
		    		hasTenKhoa = true;
		    		dmKhoa_Finded = dmKhoa;
		    		break;
		    	}	    		
		    }
		    if(hasTenKhoa){
		    	dmKhoa.setDmkhoaMaso(dmKhoa_Finded.getDmkhoaMaso());
		    	dmKhoa.setDmkhoaMa(dmKhoa_Finded.getDmkhoaMa());
		    	dmKhoa.setDmkhoaTen(dmKhoa_Finded.getDmkhoaTen());
		    }else{
		    	dmKhoa = new DmKhoa();
		    	return;
		    }
		    dmTang = new DmTang();
		    refreshDmTang();
		}
		log.info("-----END onblurTenKhoaAction()-----");
	}
	
	public void onblurTenTangAction(){
		if(dmTang != null && dmTang.getDmtangTen() != null && dmTang.getDmtangTen() != "Tất cả"){
			DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
			List<DmTang> lstTangs = dtUtil.findTenLike("DmTang", "dmtangTen", dmTang.getDmtangTen());
			if(lstTangs != null && lstTangs.size()>0){
				dmTang = lstTangs.get(0);
			}else{
				dmTang = new DmTang();
			}
		}else{
			dmTang = new DmTang();
		}
	}
	
	
	public void loadHsba() {
		log.info("-----loadHsba()-----");
		hsbaKhoa = new HsbaKhoa();
		hsba = new Hsba();
		hsbaBhyt = new HsbaBhyt();
		if (dmKhoa.getDmkhoaMa() != null && !hsbaMa.equals("")) {
			HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();
			try {
				
				HsbaKhoaDelegate hsbaKhoaDelegate = HsbaKhoaDelegate.getInstance();
				
				// 20120312 bao.ttc: them tieu chi load theo Tang -- chinh giao dien
				if (dmTang!= null && dmTang.getDmtangMaso()!= null){
					hsbaKhoa = hsbaKhoaDelegate.findBySoVaoVienAndKhoaMaAndTang(hsbaMa, dmKhoa.getDmkhoaMa(), dmTang.getDmtangMaso());
				}else {
					hsbaKhoa = hsbaKhoaDelegate.findBySoVaoVienAndKhoaMa(hsbaMa, dmKhoa.getDmkhoaMa());
				}
				
				if (hsbaKhoa == null) {
					FacesMessages.instance().add(IConstantsRes.HSBA_NULL, hsbaMa);
					hsbaMa = "";
					return;
				}
				/*	
				// kiem tra khoaMa co' phai la khoa cuoi cung ko
				if (!khoaMa.equals(hsbaKhoa.getKhoaMa().getDmkhoaMa())){
					FacesMessages.instance().add(IConstantsRes.HSBA_NULL, hsbaMa);
					hsbaMa = "";
					return;
				}
				*/
				HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
				hsba = hsbaDelegate.find(hsbaMa);
			    if  (hsba == null){
			    	FacesMessages.instance().add(IConstantsRes.HSBA_NULL, hsbaMa);
			    	hsbaMa = "";
					return ;
			    }
				//hsba = hsbaKhoa.getHsbaSovaovien();
				hsbaMa = hsba.getHsbaSovaovien();
				log.info("-----hsba: " + hsba);
				log.info("-----ho ten: " + hsba.getBenhnhanMa().getBenhnhanHoten());
				hsbaBhyt = hsbaBhytDelegate.findBySoVaoVienLastHsbaBhyt(hsbaMa);
				if(hsbaBhyt == null)
					hsbaBhyt = new HsbaBhyt();
				
			} catch (Exception e) {
				FacesMessages.instance().add(IConstantsRes.HSBA_NULL, hsbaMa);
				e.printStackTrace();
			}

		}
	}
	
	public String thuchienAction(){
		//XuatReport();
		// phuc.lc: 19-05-2011 : Kiem tra BN da thanh toan vien phi hay chua, neu chua thanh toan thi phai 
		// chay phuong thuc tinh vien phi de cap nhat gia BN phai tra cho tung CLS, thuoc
		HsThtoan hsbaHsThtoan_temp = HsThtoanDelegate.getInstance().findBySovaovien(hsba.getHsbaSovaovien());
		if (hsbaHsThtoan_temp != null && hsbaHsThtoan_temp.getHsthtoanNgaytt() == null) {
			HsThtoan hsThtoan = new HsThtoan();
			hsThtoan.setHsbaSovaovien(hsba);
			HoSoThanhToanUtil hsthtoanUtil = new HoSoThanhToanUtil(hsba);
			hsthtoanUtil.tinhToanChoHSTT(hsThtoan);
		}
		XuatReport2();
		resetFormB3133=null;
		return "B3360_Chonmenuxuattaptin";
		//return null;
	}
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){		
//		khoaMa="";
		hsbaMa="";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		ngaydungstr=sdf.format(currentDate);
		denngayStr=sdf.format(currentDate);
		hsbaKhoa = new HsbaKhoa();
		hsba = new Hsba();
		hsbaBhyt = new HsbaBhyt();
		FacesMessages.instance().clear();
		dmTang = new DmTang();
		dmKhoa = new DmKhoa();
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeVP="Phieucongkhai1bn";
		log.info("Vao Method XuatReport phieu cong khai 1 benh nhan");
		String baocao1=null;
		String pathTemplate=null;
		String pathTemplate_sub1=null;
		try {
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieucongkhaicuabenhnhan.jrxml";
			pathTemplate_sub1 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieucongkhaicuabenhnhan_sub1.jrxml";
			log.info("da thay file templete 5" + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			log.info("da thay file templete 5" + pathTemplate_sub1);
			JasperReport sub1Report =JasperCompileManager.compileReport(pathTemplate_sub1);
			log.info("da thay file template ");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			params.put("SUBREPORT_DIR", sub1Report);
			
			if(hsbaKhoa.getHsbaSovaovien() != null){
				params.put("KHOA",dmKhoa.getDmkhoaTen());
				params.put("TENNGUOIBENH", hsba.getBenhnhanMa().getBenhnhanHoten());
				params.put("NGAYDUNG", ngaydungstr);
				params.put("NGAYDUNGDATE", sdf.parse(ngaydungstr));
				params.put("DENNGAY", denngayStr);
				params.put("DENNGAYDATE", sdf.parse(denngayStr));
				String temp="";
				if(hsba.getBenhnhanMa().getBenhnhanDonvituoi() == 2)
					temp+=IConstantsRes.THANG;
				else if(hsba.getBenhnhanMa().getBenhnhanDonvituoi() == 3)
					temp+=IConstantsRes.NAM;
				params.put("TUOI", String.valueOf(hsba.getBenhnhanMa().getBenhnhanTuoi())+" "+temp);
				params.put("GIOITINH", hsba.getBenhnhanMa().getDmgtMaso(true).getDmgtTen() );
				if(hsba.getHsbaNgaygiovaov() != null)
					params.put("NGAYVAOVIEN", sdf.format(hsba.getHsbaNgaygiovaov()));
				if(hsba.getHsbaNgaygiorav() != null)
					params.put("NGAYRAVIEN", sdf.format(hsba.getHsbaNgaygiorav()));
				params.put("CHANDOAN", hsba.getHsbaMachdoanbd(true).getDmbenhicdTen());
				// phuc.lc 17-11-2010 : fix bug 1400
				params.put("SOBENHAN", hsbaMa);
				params.put("KHOAMA", dmKhoa.getDmkhoaMa());
				if  (dmTang!= null && dmTang.getDmtangMaso()!= null){
					params.put("TANGMASO", dmTang.getDmtangMaso());
				}else {
					params.put("TANGMASO", null);
				}
				log.info("hsbaKhoa.getHsbaSovaovien() != null"+ hsbaKhoa.getHsbaSovaovien());
			}
			log.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			    log.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintVP =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","Phieucongkhai1bn");
			    reportPathVP=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathVP);
			    index+= 1;
			    log.info("Index :" + getIndex());
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Thoat Method XuatReport");
	}
	
	public void XuatReport2() {
		reportTypeVP="Phieucongkhai1bn";
		log.info("Vao Method XuatReport phieu cong khai 1 benh nhan");
		String baocao1=null;
		String pathTemplate=null;
		
		try {
			
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieucongkhaithuocvatonghopvienphi.jasper";						
			log.info("da thay file template " + pathTemplate);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			params.put("SUBREPORT_DIR", IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/");
			
			if(hsba.getHsbaSovaovien() != null){
				Date dTungay = sdf.parse(ngaydungstr);
				Date dDenngay = sdf.parse(denngayStr);
				Calendar cal = Calendar.getInstance();
				List<String> listTenfile = new ArrayList<String>();
				params.put("SOVAOVIEN", hsbaMa);								
				//log.info("================= , khoaMa = " + khoaMa + ", khoa ma so = " + hsbaKhoa.getKhoaMa().getDmkhoaMa());
				//params.put("KHOA", hsba.getHsbaKhoadangdt(true).getDmkhoaTen());
				params.put("KHOA", (hsbaKhoa == null ? "" : (hsbaKhoa.getKhoaMa() == null ? "" : hsbaKhoa.getKhoaMa().getDmkhoaTen())));
				params.put("KHOAMA", (hsbaKhoa == null ? null : (hsbaKhoa.getKhoaMa() == null ? null : hsbaKhoa.getKhoaMa().getDmkhoaMaso())));
				if  (dmTang!= null && dmTang.getDmtangMaso()!= null){
					params.put("TANGMASO", dmTang.getDmtangMaso());
					params.put("TANGTEN", dmTang.getDmtangTen());
				}else {
					params.put("TANGMASO", null);
					params.put("TANGTEN", null);
				}
				Class.forName("oracle.jdbc.OracleDriver");
			    log.info("da thay driver mysql");
				Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    
				cal.setTimeInMillis(dTungay.getTime());
				Date tungayTmp = dTungay;
				while(listTenfile.size() == 0 || tungayTmp.compareTo(dDenngay) <=0) {
					params.put("TUNGAY",tungayTmp);
					params.put("DENNGAY",tungayTmp);
					
					params.put("NGAY_01", tungayTmp);
					
					cal.add(Calendar.DAY_OF_MONTH, 1);
					if(cal.getTime().compareTo(dDenngay) <=0) params.put("DENNGAY",cal.getTime());
					params.put("NGAY_02", cal.getTime());
					
					cal.add(Calendar.DAY_OF_MONTH, 1);
					if(cal.getTime().compareTo(dDenngay) <=0) params.put("DENNGAY",cal.getTime());
					params.put("NGAY_03", cal.getTime());
					
					cal.add(Calendar.DAY_OF_MONTH, 1);
					if(cal.getTime().compareTo(dDenngay) <=0) params.put("DENNGAY",cal.getTime());
					params.put("NGAY_04", cal.getTime());
					
					cal.add(Calendar.DAY_OF_MONTH, 1);
					if(cal.getTime().compareTo(dDenngay) <=0) params.put("DENNGAY",cal.getTime());
					params.put("NGAY_05", cal.getTime());
					
					cal.add(Calendar.DAY_OF_MONTH, 1);
					if(cal.getTime().compareTo(dDenngay) <=0) params.put("DENNGAY",cal.getTime());
					params.put("NGAY_06", cal.getTime());
					
					cal.add(Calendar.DAY_OF_MONTH, 1);
					if(cal.getTime().compareTo(dDenngay) <=0) params.put("DENNGAY",cal.getTime());
					params.put("NGAY_07", cal.getTime());
					
					cal.add(Calendar.DAY_OF_MONTH, 1);
					if(cal.getTime().compareTo(dDenngay) <=0) params.put("DENNGAY",cal.getTime());
					params.put("NGAY_08", cal.getTime());
					
					cal.add(Calendar.DAY_OF_MONTH, 1);
					if(cal.getTime().compareTo(dDenngay) <=0) params.put("DENNGAY",cal.getTime());
					params.put("NGAY_09", cal.getTime());
					
					cal.add(Calendar.DAY_OF_MONTH, 1);
					if(cal.getTime().compareTo(dDenngay) <=0) params.put("DENNGAY",cal.getTime());
					params.put("NGAY_10", cal.getTime());
					
					cal.add(Calendar.DAY_OF_MONTH, 1);
					tungayTmp = cal.getTime();					
				    
				    jasperPrintVP =  JasperFillManager.fillReport(pathTemplate,params, conn);
				    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","Phieucongkhai1bn");
				    listTenfile.add(baocao1);				    				    
				    index+= 1;
				} // end while
				if(conn != null) conn.close();
				// Ghep file
				String appendFileName = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/PhieucongkhaithuocvaThvp_" + new Date().getTime() + ".pdf";
				PdfCopyFields copy = new PdfCopyFields(new FileOutputStream(appendFileName));
				for(String eachFile : listTenfile) {
					PdfReader reader = new PdfReader(eachFile);
					copy.addDocument(reader);
				}
				copy.close();
				// Xoa file tam				
				for(String eachFile : listTenfile) {
					File f = new File(eachFile);
					f.delete();
				}
				reportPathVP=appendFileName.replaceFirst(IConstantsRes.PATH_BASE,"");		   
				log.info("duong dan -------------------- :"+reportPathVP);
			}    
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
		
	    log.info("Thoat Method XuatReport");
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public String getNgayhientai() {
		return ngayhientai;
	}

	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
	}

//	public String getKhoaMa() {
//		return khoaMa;
//	}
//
//	public void setKhoaMa(String khoaMa) {
//		this.khoaMa = khoaMa;
//	}

	public String getHsbaMa() {
		return hsbaMa;
	}

	public void setHsbaMa(String hsbaMa) {
		this.hsbaMa = hsbaMa;
	}

	public String getNgaydungstr() {
		return ngaydungstr;
	}

	public void setNgaydungstr(String ngaydungstr) {
		this.ngaydungstr = ngaydungstr;
	}

	public String getDenngayStr() {
		return denngayStr;
	}

	public void setDenngayStr(String denngayStr) {
		this.denngayStr = denngayStr;
	}

	public HsbaKhoa getHsbaKhoa() {
		return hsbaKhoa;
	}

	public void setHsbaKhoa(HsbaKhoa hsbaKhoa) {
		this.hsbaKhoa = hsbaKhoa;
	}

	public Hsba getHsba() {
		return hsba;
	}

	public void setHsba(Hsba hsba) {
		this.hsba = hsba;
	}

	public HsbaBhyt getHsbaBhyt() {
		return hsbaBhyt;
	}

	public void setHsbaBhyt(HsbaBhyt hsbaBhyt) {
		this.hsbaBhyt = hsbaBhyt;
	}

	public String getFromMenu() {
		return fromMenu;
	}

	public void setFromMenu(String fromMenu) {
		this.fromMenu = fromMenu;
	}

	public DmKhoa getDmKhoa() {
		return dmKhoa;
	}

	public void setDmKhoa(DmKhoa dmKhoa) {
		this.dmKhoa = dmKhoa;
	}

	public DmTang getDmTang() {
		return dmTang;
	}

	public void setDmTang(DmTang dmTang) {
		this.dmTang = dmTang;
	}

	public List<SelectItem> getListDmTangs() {
		return listDmTangs;
	}

	public void setListDmTangs(List<SelectItem> listDmTangs) {
		this.listDmTangs = listDmTangs;
	}

	public HashMap<String, DmKhoa> getHmDmKhoaNTAll() {
		return hmDmKhoaNTAll;
	}

	public void setHmDmKhoaNTAll(HashMap<String, DmKhoa> hmDmKhoaNTAll) {
		this.hmDmKhoaNTAll = hmDmKhoaNTAll;
	}

	public List<SelectItem> getListDmKhoaNTs() {
		return listDmKhoaNTs;
	}

	public void setListDmKhoaNTs(List<SelectItem> listDmKhoaNTs) {
		this.listDmKhoaNTs = listDmKhoaNTs;
	}

	public DmKhoaDelegate getDmKhoaDel() {
		return dmKhoaDel;
	}

	public void setDmKhoaDel(DmKhoaDelegate dmKhoaDel) {
		this.dmKhoaDel = dmKhoaDel;
	}

	public List<DmKhoa> getListDmKhoaNTAll() {
		return listDmKhoaNTAll;
	}

	public void setListDmKhoaNTAll(List<DmKhoa> listDmKhoaNTAll) {
		this.listDmKhoaNTAll = listDmKhoaNTAll;
	}
	
}

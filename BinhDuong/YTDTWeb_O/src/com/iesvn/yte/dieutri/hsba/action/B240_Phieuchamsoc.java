package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.log4j.Logger;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.PhieuChamSocDelegate;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.PhieuChamSoc;
import com.iesvn.yte.util.IConstantsRes;

@Scope(CONVERSATION)
@Name("B240_Phieuchamsoc")
@Synchronized(timeout = 6000000)
public class B240_Phieuchamsoc implements Serializable {
	private static String FORMAT_DATE = "dd/MM/yyyy";
	private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH:mm";
	private static final long serialVersionUID = 10L;
	
	private String hsbaMaso;
	private String tenKhoa;
	private String hotenBN;
	private String gioitinh; // 0 : Nu; 1 Nam
	private String tuoi;
	private String donvituoi;
	private String sogiuong;
	private String sobuong;
	private String chandoan;
	private boolean isEdit;
	private Hsba hsba;
	private String gio;
	private String ngay;
	private String ngayhientai;
	private PhieuChamSoc pcs;  	
	private List<PhieuChamSoc> listPcs;	
	private String loaiBA;
	private String tenBs = "";
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
	
	@Out(required=false)
	@In(required=false)
	private String duongdanStrDT=null;
	
	@Out(required=false)
	@In(required=false)
	private String loaiBCDT=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintDT = null;

	private static Logger log = Logger.getLogger(B240_Phieuchamsoc.class);
	
	@In(required = false)
	@Out(required = false)
	private String tenChuongTrinh;
	
	@In(scope=ScopeType.PAGE,required = false)
	@Out(scope=ScopeType.PAGE,required = false)
	private String initB240_Phieuchamsoc;
	
	@Factory("initB240_Phieuchamsoc")
	public void init(){
		tenChuongTrinh = MyMenuYTDTAction.vienPhiTaiKhoa;
		resetValue();
		SimpleDateFormat df = new SimpleDateFormat(FORMAT_DATE);			
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		ngayhientai = df.format(cal.getTime());				
		initB240_Phieuchamsoc = "";
		DtDmNhanVien nguoidung = DtDmNhanVienDelegate.getInstance().findByNd(identity.getUsername());
		if(nguoidung!=null){
			tenBs = nguoidung.getDmhocviMaso(true).getDmhocviMa()+". "+nguoidung.getDtdmnhanvienTen();
		}
	}
	
	public void resetValue() {
		System.out.println("Tên người đăng nhập");
		hsbaMaso =  gio = ngay = tenKhoa = "";
		hotenBN = gioitinh = tuoi = donvituoi = "";		
		sogiuong = sobuong = chandoan = loaiBA = "";
		pcs = new PhieuChamSoc();
		hsba = new Hsba();
		isEdit = false;
		listPcs = new ArrayList<PhieuChamSoc>();
	}
	@End
	public void destroy(){
		
	}
	public void displayInfor(){
		log.info("Begin displayInfo, hsbaMaso = " + hsbaMaso);
		if (hsbaMaso.trim().length() > 0) {
			// Find hsba object
			hsba = HsbaDelegate.getInstance().find(hsbaMaso);
			if (hsba != null) {
				hsbaMaso = hsba.getHsbaSovaovien();
				log.info("hsba.getHsbaKhoadangdt() = " + hsba.getHsbaKhoadangdt());
				tenKhoa = (hsba.getHsbaKhoadangdt() == null ? "" : hsba.getHsbaKhoadangdt().getDmkhoaTen());				
				hotenBN = (hsba.getBenhnhanMa() == null ? "" : hsba.getBenhnhanMa().getBenhnhanHoten());
				gioitinh = (hsba.getBenhnhanMa() == null ? "" : hsba.getBenhnhanMa().getDmgtMaso() == null ? "" : hsba.getBenhnhanMa().getDmgtMaso().getDmgtMa());
				tuoi = (hsba.getBenhnhanMa() == null ? "" :  "" + hsba.getBenhnhanMa().getBenhnhanTuoi());
				donvituoi = (hsba.getBenhnhanMa() == null ? "" : hsba.getBenhnhanMa().getBenhnhanDonvituoi().intValue() == 1 ? "" : hsba.getBenhnhanMa().getBenhnhanDonvituoi().intValue() == 2 ? "(Tháng)":"(Ngày)");
				HsbaChuyenMon hsbaCm = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_LastHSBACM(hsbaMaso);
				sobuong = (hsbaCm == null ? "" : hsbaCm.getHsbacmSobuong());
				sogiuong = (hsbaCm == null ? "" : hsbaCm.getHsbacmSogiuong());
				chandoan = (hsba.getHsbaMachdoanbd() == null ? "" : hsba.getHsbaMachdoanbd().getDmbenhicdTen());
				loaiBA = (hsba.getHsbaType() == null ? "0" : hsba.getHsbaType().trim().equalsIgnoreCase("BA_LUU") ? "1" : "0");
				resetList();
				gio = ngay = "";
				pcs = new PhieuChamSoc();
				isEdit = false;
			} else {
				// Hien thi thong bao khong tim thay
				FacesMessages.instance().add(IConstantsRes.HSBA_KHONG_TON_TAI, hsbaMaso);
				resetValue();
				
			}
		} else {
			resetValue();
		}
		log.info("End displayInfo, listTdt.size = " + listPcs.size());
	}
	
	public void resetList(){
		listPcs.clear();
		PhieuChamSocDelegate pcsDel = PhieuChamSocDelegate.getInstance();
		listPcs = pcsDel.findByHsba(hsbaMaso);
	}
	
	public void addChitiet() {
		log.info("Begin addChitiet, hsba = " + hsba);
		Calendar cal = Calendar.getInstance();
		String gioTmp = (gio.trim().length() < 1 ? "00:00" : gio);
		try {
			SimpleDateFormat df = new SimpleDateFormat(FORMAT_DATE_TIME);
			cal.setTime(df.parse(ngay + " " + gioTmp));
			pcs.setPhieuchamsocNgaygio(cal.getTime());
			pcs.setHsbaSovaovien(hsba);
			pcs.setPhieuchamsocTenbs(tenBs);
			// Luu thong tin xuong database
			pcs = PhieuChamSocDelegate.getInstance().createPhieuChamSoc(pcs);
			// Them vao list de hien thi len giao dien
//			if (!isEdit ){
//				listPcs.add(pcs);
//			}
			resetList();
			// Reset value
			pcs = new PhieuChamSoc();
			gio = ngay = "";
			isEdit = false;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void editChitiet(PhieuChamSoc pcsTmp) {
		log.info("Begin editChitiet, pcsTmp = " + pcsTmp);		
		pcs = pcsTmp;		
		isEdit = true;		
		gio = showGio(pcs.getPhieuchamsocNgaygio());		
		ngay = showNgay(pcs.getPhieuchamsocNgaygio());
		log.info("End editChitiet");
	}
	public void deleteChitiet(PhieuChamSoc pcsTmp) {
		// Xoa khoi list
		listPcs.remove(pcsTmp);
		// Xoa trong database
		PhieuChamSocDelegate.getInstance().remove(pcsTmp);
		pcs = new PhieuChamSoc();		
		gio = "";
		ngay = "";
		isEdit = false;
	}
	
	public String showGio(Date dateTmp) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateTmp);
		String strGio = (cal.get(Calendar.HOUR_OF_DAY) < 10 ? "0" + cal.get(Calendar.HOUR_OF_DAY) : cal.get(Calendar.HOUR_OF_DAY)) + ":" + (cal.get(Calendar.MINUTE) < 10 ? "0" + cal.get(Calendar.MINUTE) : cal.get(Calendar.MINUTE));
		return strGio;
	}
	public String showNgay(Date dateTmp) {
		SimpleDateFormat df = new SimpleDateFormat(FORMAT_DATE);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateTmp);
		String strNgay = df.format(cal.getTime());
		return strNgay;
	}
	public String showGioNgay(Date dateTmp) {
		return showGio(dateTmp) + " " + showNgay(dateTmp);
	}
	public String thuchienAction(){
		XuatReport();
		return "B260_Chonmenuxuattaptin";
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		loaiBCDT="phieuchamsoc";
		log.info("Vao Method XuatReport To Dieu tri");
		String baocao1=null;
		String pathTemplate = null;
		try {
			log.info("bat dau method XuatReport ");
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/D09_phieuchamsoc.jrxml";
			
			
			log.info("da thay file templete " + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			Map<String, Object> params = new HashMap<String, Object>();        
	        params.put("tenso", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
	        params.put("tendonvi",IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
	        params.put("sovaovien", hsbaMaso);
	        params.put("hoten", hotenBN);	        
			params.put("tuoi", tuoi);
	        //params.put("gioitinh", (gioitinh.equals("0") ? "Nữ" : "Nam"));
			params.put("gioitinh", gioitinh);
	        params.put("khoa", tenKhoa);
	        params.put("buong", sobuong);
	        params.put("giuong", sogiuong);
	        params.put("chandoan", chandoan);
	        params.put("loaiba", loaiBA);
	        params.put("donvituoi",hsba.getBenhnhanMa() == null ? "" : "" + hsba.getBenhnhanMa().getBenhnhanDonvituoi().intValue());
			log.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			    log.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintDT =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintDT,new Long(new Date().getTime()).intValue(),IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"hsba/", "pdf","phieuchamsoc");
			    duongdanStrDT=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+duongdanStrDT);
			    
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Thoat Method XuatReport");
	}

	public String getHsbaMaso() {
		return hsbaMaso;
	}

	public void setHsbaMaso(String hsbaMaso) {
		this.hsbaMaso = hsbaMaso;
	}

	public String getTenKhoa() {
		return tenKhoa;
	}

	public void setTenKhoa(String tenKhoa) {
		this.tenKhoa = tenKhoa;
	}

	public String getHotenBN() {
		return hotenBN;
	}

	public void setHotenBN(String hotenBN) {
		this.hotenBN = hotenBN;
	}

	public String getGioitinh() {
		return gioitinh;
	}

	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}

	public String getTuoi() {
		return tuoi;
	}

	public void setTuoi(String tuoi) {
		this.tuoi = tuoi;
	}

	public String getDonvituoi() {
		return donvituoi;
	}

	public void setDonvituoi(String donvituoi) {
		this.donvituoi = donvituoi;
	}

	public String getSogiuong() {
		return sogiuong;
	}

	public void setSogiuong(String sogiuong) {
		this.sogiuong = sogiuong;
	}

	public String getSobuong() {
		return sobuong;
	}

	public void setSobuong(String sobuong) {
		this.sobuong = sobuong;
	}

	public String getChandoan() {
		return chandoan;
	}

	public void setChandoan(String chandoan) {
		this.chandoan = chandoan;
	}

	public String getGio() {
		return gio;
	}

	public void setGio(String gio) {
		this.gio = gio;
	}

	public String getNgay() {
		return ngay;
	}

	public void setNgay(String ngay) {
		this.ngay = ngay;
	}

	public String getNgayhientai() {
		return ngayhientai;
	}

	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
	}

	public PhieuChamSoc getPcs() {
		return pcs;
	}

	public void setPcs(PhieuChamSoc pcs) {
		this.pcs = pcs;
	}

	public List<PhieuChamSoc> getListPcs() {
		return listPcs;
	}

	public void setListPcs(List<PhieuChamSoc> listPcs) {
		this.listPcs = listPcs;
	}
	
	
}
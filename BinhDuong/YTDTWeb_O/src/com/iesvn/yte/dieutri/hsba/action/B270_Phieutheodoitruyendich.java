package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.log4j.Logger;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.faces.FacesMessages;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DmThuocDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.PhieuTheoDoiTruyenDichDelegate;
import com.iesvn.yte.dieutri.delegate.ToDieuTriDelegate;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.PhieuTheoDoiTruyenDich;
import com.iesvn.yte.dieutri.entity.ToDieuTri;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.entity.DmThuoc;
import com.iesvn.yte.util.IConstantsRes;

@Scope(CONVERSATION)
@Name("B270_Theodoitruyendich")
@Synchronized(timeout = 6000000)
public class B270_Phieutheodoitruyendich implements Serializable {
	private static String FORMAT_DATE = "dd/MM/yyyy";
	private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH:mm";
	private static String DEFAULT_HOUR = "00:00";
	private static final long serialVersionUID = 10L;
	private String giobd;
	private String ngaybd;
	private String giokt;
	private String ngaykt;
	private String dmThuocMa;
	private Integer dmThuocMaso;	
	private String bacsiMa;
	private Integer bacsiMaso;
	private String ytaMa;
	private Integer ytaMaso;
	private String ngayhientai;
	private String hsbaMaso;
	private String tenKhoa;
	private String khoaMa;
	private String hotenBN;
	private String gioitinh; // 0 : Nu; 1 Nam
	private String tuoi;
	private String donvituoi;
	private String sogiuong;
	private String sobuong;
	private String chandoan;
//	private Integer tonkhoMaso;
	private String soLoSX;
	private PhieuTheoDoiTruyenDich ptdtd;  	
	private List<PhieuTheoDoiTruyenDich> listPtdtd;
	private boolean isEdit;
	private Hsba hsba;
	
	@Out(required=false)
	@In(required=false)
	private String loaiBCDT=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintDT = null;
	
	@Out(required=false)
	@In(required=false)
	private String duongdanStrDT=null;
	
	@In(scope=ScopeType.PAGE,required = false)
	@Out(scope=ScopeType.PAGE,required = false)
	private String initB270_Theodoitruyendich;
	private static Logger log = Logger.getLogger(B270_Phieutheodoitruyendich.class);
					
	@Factory("initB270_Theodoitruyendich")
	public void init(){
		resetValue();
		SimpleDateFormat df = new SimpleDateFormat(FORMAT_DATE);			
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		ngayhientai = df.format(cal.getTime());				
		initB270_Theodoitruyendich = "";
		/** ==================== BEGIN LY THEM CODE ==================== */
		dmThuocDelegate = DmThuocDelegate.getInstance();
		listDmThuocs.clear();
		for(DmThuoc each : dmThuocDelegate.findAll()){
			listDmThuocs.add(new SelectItem(each.getDmthuocTen()));
		}
		/** ==================== END LY THEM CODE ==================== */
	}
	public void resetValue() {
		hsbaMaso =  giobd = ngaybd = giokt = ngaykt = tenKhoa = khoaMa = "";
		hotenBN = gioitinh = tuoi = donvituoi = "";		
		sogiuong = sobuong = chandoan = "";
		dmThuocMa = dmThuocTen = bacsiMa = ytaMa = soLoSX =  "";
		dmThuocMaso = bacsiMaso = ytaMaso = null;		
		ptdtd = new PhieuTheoDoiTruyenDich();
		ptdtd.setPtdtdSoluong(new Short("1"));
		hsba = new Hsba();
		isEdit = false;
		listPtdtd = new ArrayList<PhieuTheoDoiTruyenDich>();
	}
	
	/** ==================== BEGIN LY THEM CODE ==================== */	
	private DmThuocDelegate dmThuocDelegate;
	private String dmThuocTen;
	private List<SelectItem> listDmThuocs = new ArrayList<SelectItem>();
	
	public String getDmThuocTen() {
		return dmThuocTen;
	}
	public void setDmThuocTen(String dmThuocTen) {
		this.dmThuocTen = dmThuocTen;
	}
	public List<SelectItem> getListDmThuocs() {
		return listDmThuocs;
	}

	public void setListDmThuocs(List<SelectItem> listDmThuocs) {
		this.listDmThuocs = listDmThuocs;
	}
	
	public void onblurMaThuocAction(){
		log.info("-----BEGIN onblurMaThuocAction()-----");
		//Tùy logic form mà set cho đúng
		
			DmThuoc dmThuoc = dmThuocDelegate.findByMaThuoc(dmThuocMa);
			if(dmThuoc != null) {
				setDmThuocTen(dmThuoc.getDmthuocTen());
				log.info("-----DA THAY DMTHUOC-----");
			}
			else {
				setDmThuocTen("");
			}
		log.info("-----END onblurMaThuocAction()-----");
	}
	
	public void onblurTenThuocAction(){
		log.info("-----BEGIN onblurTenThuocAction()-----");

			DmThuoc dmThuoc = dmThuocDelegate.findByTenThuoc(dmThuocTen);
			//Tùy logic form mà set cho đúng
			if(dmThuoc != null) {
				setDmThuocMa(dmThuoc.getDmthuocMa());
				log.info("-----DA THAY DMTHUOC-----");
			}
			else {
				setDmThuocMa("");
			}		
		log.info("-----END onblurTenThuocAction()-----");
	}
	
	public void refreshDmThuoc(){
		listDmThuocs.clear();
		dmThuocDelegate = DmThuocDelegate.getInstance();
		for(DmThuoc each : dmThuocDelegate.findAll()){
			listDmThuocs.add(new SelectItem(each.getDmthuocTen()));
		}
	}

/** ==================== END LY THEM CODE ==================== */	
	
	public void displayInfor(){
		log.info("Begin displayInfo, hsbaMaso = " + hsbaMaso);
		if (hsbaMaso.trim().length() > 0) {
			// Find hsba object
			hsba = HsbaDelegate.getInstance().find(hsbaMaso);
			if (hsba != null) {
				hsbaMaso = hsba.getHsbaSovaovien();
				log.info("hsba.getHsbaKhoadangdt() = " + hsba.getHsbaKhoadangdt());
				tenKhoa = (hsba.getHsbaKhoadangdt() == null ? "" : hsba.getHsbaKhoadangdt().getDmkhoaTen());
				khoaMa = (hsba.getHsbaKhoadangdt() == null ? "" : hsba.getHsbaKhoadangdt().getDmkhoaMa());
				hotenBN = (hsba.getBenhnhanMa() == null ? "" : hsba.getBenhnhanMa().getBenhnhanHoten());
				gioitinh = (hsba.getBenhnhanMa() == null ? "" : hsba.getBenhnhanMa().getDmgtMaso() == null ? "" : hsba.getBenhnhanMa().getDmgtMaso().getDmgtMa());
				tuoi = (hsba.getBenhnhanMa() == null ? "" :  "" + hsba.getBenhnhanMa().getBenhnhanTuoi());
				donvituoi = (hsba.getBenhnhanMa() == null ? "" : hsba.getBenhnhanMa().getBenhnhanDonvituoi().intValue() == 1 ? "" : hsba.getBenhnhanMa().getBenhnhanDonvituoi().intValue() == 2 ? IConstantsRes.THANG :IConstantsRes.NGAY);
				HsbaChuyenMon hsbaCm = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_LastHSBACM(hsbaMaso);
				sobuong = (hsbaCm == null ? "" : hsbaCm.getHsbacmSobuong());
				sogiuong = (hsbaCm == null ? "" : hsbaCm.getHsbacmSogiuong());
				chandoan = (hsba.getHsbaMachdoanbd() == null ? "" : hsba.getHsbaMachdoanbd().getDmbenhicdTen());
				resetPhieuTheoDoiTruyenDich();
				giobd = ngaybd = giokt = ngaykt = "";
				dmThuocMa = dmThuocTen = bacsiMa = ytaMa = soLoSX = "";
				dmThuocMaso = bacsiMaso = ytaMaso  = null;		
				ptdtd = new PhieuTheoDoiTruyenDich();
				ptdtd.setPtdtdSoluong(new Short("1"));
				isEdit = false;
			} else {
				// Hien thi thong bao khong tim thay
				FacesMessages.instance().add(IConstantsRes.HSBA_KHONG_TON_TAI, hsbaMaso);
				resetValue();
				
			}
		} else {
			resetValue();
		}
		log.info("End displayInfo, listPtdtd.size = " + listPtdtd.size());
	}
	public void resetPhieuTheoDoiTruyenDich()
	{
		listPtdtd.clear();
		PhieuTheoDoiTruyenDichDelegate ptdtdDel = PhieuTheoDoiTruyenDichDelegate.getInstance();
		listPtdtd = ptdtdDel.findByHsba(hsbaMaso);
	}
	public void addChitiet() {
		log.info("Begin addChitiet, hsba = " + hsba);
		Calendar cal = Calendar.getInstance();
		String gioBdTmp = (giobd.trim().length() < 1 ? DEFAULT_HOUR : giobd);
		String gioKtTmp = (giokt.trim().length() < 1 ? DEFAULT_HOUR : giokt);
		try {
			SimpleDateFormat df = new SimpleDateFormat(FORMAT_DATE_TIME);
			// Set ngay gio bat dau
			cal.setTime(df.parse(ngaybd + " " + gioBdTmp));
			ptdtd.setPtdtdBatdau(cal.getTime());
			// Set ngay gio ket thuc
			if (ngaykt.trim().length() > 0) {
				cal.setTime(df.parse(ngaykt + " " + gioKtTmp));
				ptdtd.setPtdtdKetthuc(cal.getTime());
			} else {
				ptdtd.setPtdtdKetthuc(null);
			}
						
			ptdtd.setHsbaSovaovien(hsba);
			
			ptdtd.setDmthuocMaso(null);
			ptdtd.setBacsiMaso(null);
			ptdtd.setYtaMaso(null);
			ptdtd.setTonkhoMa(null);
			DieuTriUtilDelegate utilsDelegate = DieuTriUtilDelegate.getInstance();			
			if (dmThuocMa.trim().length() > 0) {				
				DmThuoc dmt = (DmThuoc) utilsDelegate.findByMa(dmThuocMa, "DmThuoc", "dmthuocMa");
				ptdtd.setDmthuocMaso(dmt);
			}
			if (bacsiMa.trim().length() > 0) {
				DtDmNhanVien nv = (DtDmNhanVien) utilsDelegate.findByMa(bacsiMa, "DtDmNhanVien", "dtdmnhanvienMa");
				ptdtd.setBacsiMaso(nv);
			}
			if (ytaMa.trim().length() > 0) {
				DtDmNhanVien nv = (DtDmNhanVien) utilsDelegate.findByMa(ytaMa, "DtDmNhanVien", "dtdmnhanvienMa");
				ptdtd.setYtaMaso(nv);
				
			}
//			if (tonkhoMaso != null) {
//				TonKho tk = (TonKho) utilsDelegate.findByMaSo(tonkhoMaso, "TonKho", "tonkhoMa");
//				ptdtd.setTonkhoMa(tk);
//			}
			// Luu thong tin xuong database
			ptdtd = PhieuTheoDoiTruyenDichDelegate.getInstance().createPhieuTheoDoiTruyenDich(ptdtd);
			// Them vao list de hien thi len giao dien
//			if (!isEdit ){
//				listPtdtd.add(ptdtd);
//			}
			resetPhieuTheoDoiTruyenDich();
			// Reset value
			ptdtd = new PhieuTheoDoiTruyenDich();
			ptdtd.setPtdtdSoluong(new Short("1"));
			giobd = ngaybd = giokt = ngaykt = "";
			dmThuocMa = dmThuocTen = bacsiMa = ytaMa = "";
			dmThuocMaso = bacsiMaso = ytaMaso = null;			
			isEdit = false;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public void editChitiet(PhieuTheoDoiTruyenDich ptdtdTmp) {
		log.info("Begin editChitiet, ptdtdTmp = " + ptdtdTmp);		
		ptdtd = ptdtdTmp;		
		isEdit = true;		
		giobd = showGio(ptdtd.getPtdtdBatdau());		
		ngaybd = showNgay(ptdtd.getPtdtdBatdau());
		giokt = showGio(ptdtd.getPtdtdKetthuc());		
		ngaykt = showNgay(ptdtd.getPtdtdKetthuc());
		
		dmThuocMa = (ptdtd.getDmthuocMaso() == null ? "" : ptdtd.getDmthuocMaso().getDmthuocMa());
		dmThuocTen = (ptdtd.getDmthuocMaso() == null ? "" : ptdtd.getDmthuocMaso().getDmthuocTen());
		dmThuocMaso = (ptdtd.getDmthuocMaso() == null ? null : ptdtd.getDmthuocMaso().getDmthuocMaso());
		
		bacsiMa = (ptdtd.getBacsiMaso() == null ? "" : ptdtd.getBacsiMaso().getDtdmnhanvienMa());
		bacsiMaso = (ptdtd.getBacsiMaso() == null ? null : ptdtd.getBacsiMaso().getDtdmnhanvienMaso());
		
		ytaMa = (ptdtd.getYtaMaso() == null ? "" : ptdtd.getYtaMaso().getDtdmnhanvienMa());
		ytaMaso = (ptdtd.getYtaMaso() == null ? null : ptdtd.getYtaMaso().getDtdmnhanvienMaso());
		
//		tonkhoMaso = (ptdtd.getTonkhoMa() == null ? null : ptdtd.getTonkhoMa().getTonkhoMa());
		log.info("End editChitiet");
	}
	public void deleteChitiet(PhieuTheoDoiTruyenDich ptdtdTmp) {
		// Xoa khoi list
		listPtdtd.remove(ptdtdTmp);
		// Xoa trong database
		PhieuTheoDoiTruyenDichDelegate.getInstance().remove(ptdtdTmp);
		// Reset value
		ptdtd = new PhieuTheoDoiTruyenDich();
		ptdtd.setPtdtdSoluong(new Short("1"));
		giobd = ngaybd = giokt = ngaykt = "";
		dmThuocMa = dmThuocTen = bacsiMa = ytaMa = soLoSX = "";
		dmThuocMaso = bacsiMaso = ytaMaso  = null;
		isEdit = false;
	}
	private String showGio(Date dateTmp) {
		String strGio = "";
		if (dateTmp != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateTmp);
			strGio = (cal.get(Calendar.HOUR_OF_DAY) < 10 ? "0" + cal.get(Calendar.HOUR_OF_DAY) : cal.get(Calendar.HOUR_OF_DAY)) + ":" + (cal.get(Calendar.MINUTE) < 10 ? "0" + cal.get(Calendar.MINUTE) : cal.get(Calendar.MINUTE));
		}
		return strGio;
	}
	private String showNgay(Date dateTmp) {
		String strNgay = "";
		if (dateTmp != null) {
			SimpleDateFormat df = new SimpleDateFormat(FORMAT_DATE);
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateTmp);
			strNgay = df.format(cal.getTime());
		}
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
		loaiBCDT="PhieuTheoDoiTruyenDich";
		log.info("Vao Method XuatReport To Dieu tri");
		String baocao1=null;
		String pathTemplate = null;
		try {
			log.info("bat dau method XuatReport ");
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/D17_phieutheodoitruyendich.jrxml";
			
			
			log.info("da thay file templete " + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			Map<String, Object> params = new HashMap<String, Object>();        
	        params.put("tenso", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
	        params.put("tendonvi",IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
	        params.put("sovaovien", hsbaMaso);
	        params.put("hoten", hotenBN);	        
			params.put("tuoi", tuoi + (donvituoi.trim().length() > 0 ? donvituoi : ""));
	        params.put("gioitinh", (gioitinh.equals("0") ? IConstantsRes.GIOI_TINH_NU : IConstantsRes.GIOI_TINH_NAM));
	        params.put("khoa", tenKhoa);
	        params.put("buong", sobuong);
	        params.put("giuong", sogiuong);
	        params.put("chandoan", chandoan);
	        	        			
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintDT,new Long(new Date().getTime()).intValue(),IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"hsba/", "pdf","PhieuTheoDoiTruyenDich");
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
	
	public String getNgayhientai() {
		return ngayhientai;
	}
	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
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
	public String getGiobd() {
		return giobd;
	}
	public void setGiobd(String giobd) {
		this.giobd = giobd;
	}
	public String getNgaybd() {
		return ngaybd;
	}
	public void setNgaybd(String ngaybd) {
		this.ngaybd = ngaybd;
	}
	public String getGiokt() {
		return giokt;
	}
	public void setGiokt(String giokt) {
		this.giokt = giokt;
	}
	public String getNgaykt() {
		return ngaykt;
	}
	public void setNgaykt(String ngaykt) {
		this.ngaykt = ngaykt;
	}
	public String getDmThuocMa() {
		return dmThuocMa;
	}
	public void setDmThuocMa(String dmThuocMa) {
		this.dmThuocMa = dmThuocMa;
	}
	public Integer getDmThuocMaso() {
		return dmThuocMaso;
	}
	public void setDmThuocMaso(Integer dmThuocMaso) {
		this.dmThuocMaso = dmThuocMaso;
	}
	public String getBacsiMa() {
		return bacsiMa;
	}
	public void setBacsiMa(String bacsiMa) {
		this.bacsiMa = bacsiMa;
	}
	public Integer getBacsiMaso() {
		return bacsiMaso;
	}
	public void setBacsiMaso(Integer bacsiMaso) {
		this.bacsiMaso = bacsiMaso;
	}
	public String getYtaMa() {
		return ytaMa;
	}
	public void setYtaMa(String ytaMa) {
		this.ytaMa = ytaMa;
	}
	public Integer getYtaMaso() {
		return ytaMaso;
	}
	public void setYtaMaso(Integer ytaMaso) {
		this.ytaMaso = ytaMaso;
	}
	public PhieuTheoDoiTruyenDich getPtdtd() {
		return ptdtd;
	}
	public void setPtdtd(PhieuTheoDoiTruyenDich ptdtd) {
		this.ptdtd = ptdtd;
	}
	public List<PhieuTheoDoiTruyenDich> getListPtdtd() {
		return listPtdtd;
	}
	public void setListPtdtd(List<PhieuTheoDoiTruyenDich> listPtdtd) {
		this.listPtdtd = listPtdtd;
	}
	
	public String getKhoaMa() {
		return khoaMa;
	}
	public void setKhoaMa(String khoaMa) {
		this.khoaMa = khoaMa;
	}
	public String getSoLoSX() {
		return soLoSX;
	}
	public void setSoLoSX(String soLoSX) {
		this.soLoSX = soLoSX;
	}
	
}
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
import org.jboss.seam.security.Identity;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocNoiTruDelegate;
import com.iesvn.yte.dieutri.delegate.ToDieuTriDelegate;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
import com.iesvn.yte.dieutri.entity.ToDieuTri;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B239_Todieutri")
@Synchronized(timeout = 6000000)
public class B239_Todieutri implements Serializable {
	private static String FORMAT_DATE = "dd/MM/yyyy";
	private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH:mm";
	private static final long serialVersionUID = 10L;
	private String gio;
	private String ngay;
	private String ngayhientai;
	private String hsbaMaso;
	private String tenKhoa;
	private String hotenBN;
	private String gioitinh; // 0 : Nu; 1 Nam
	private String tuoi;
	private String donvituoi;
	private String sogiuong;
	private String sobuong;
	private String chandoan;
	private String thuoc;
	
	private ToDieuTri tdt;  	
	private List<ToDieuTri> listTdt;
	private boolean isEdit;
	private Hsba hsba;
	private String loaiBA;
	private String tenBs= "";
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
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
	private String initB239_Todieutri;
	private static Logger log = Logger.getLogger(B239_Todieutri.class);
					
	@Factory("initB239_Todieutri")
	public void init(){
		resetValue();
		SimpleDateFormat df = new SimpleDateFormat(FORMAT_DATE);			
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		ngayhientai = df.format(cal.getTime());				
		initB239_Todieutri = "";
		DtDmNhanVien nguoidung = DtDmNhanVienDelegate.getInstance().findByNd(identity.getUsername());
		if(nguoidung != null){
			// 20110826 bao.ttc: check null
			tenBs = ( nguoidung.getDmhocviMaso(true).getDmhocviMa() == null ? "" : nguoidung.getDmhocviMaso(true).getDmhocviMa() + ". " )
					+ ( nguoidung.getDtdmnhanvienTen() == null ? "" : nguoidung.getDtdmnhanvienTen() );
		}
	}
	public void resetValue() {
		hsbaMaso =  gio = ngay = tenKhoa = "";
		hotenBN = gioitinh = tuoi = donvituoi = "";		
		sogiuong = sobuong = chandoan = loaiBA = "";
		tdt = new ToDieuTri();
		hsba = new Hsba();
		isEdit = false;
		listTdt = new ArrayList<ToDieuTri>();
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
				tdt = new ToDieuTri();
				isEdit = false;
			} else {
				// Hien thi thong bao khong tim thay
				FacesMessages.instance().add(IConstantsRes.HSBA_KHONG_TON_TAI, hsbaMaso);
				resetValue();
				
			}
		} else {
			resetValue();
		}
		log.info("End displayInfo, listTdt.size = " + listTdt.size());
	}
	
	public void get_thuoc_info(){
        thuoc = "";
        if(ngay != null && !ngay.equals("")){
        	
        	if(Utils.getDBDate("00:00", ngay) == null){
        		log.info("### To dieu tri -- get_thuoc_info(): ERROR Parse NGAY !! ");
				return;
			}
        	
            List<ThuocNoiTru> listThuocNoiTru;
            ThuocNoiTruDelegate tntDelegate = ThuocNoiTruDelegate.getInstance();
            listThuocNoiTru = tntDelegate.findBySoVaoVienAndKhoaMaAndNgay(hsba.getHsbaSovaovien(), (hsba.getHsbaKhoadangdt() == null ? "" : hsba.getHsbaKhoadangdt(true).getDmkhoaMa()),ngay);
            if(listThuocNoiTru != null && listThuocNoiTru.size() >0){
                for (ThuocNoiTru each : listThuocNoiTru){
                	thuoc += "- " + each.getThuocnoitruMathuoc(true).getDmthuocTen();
                    if(each.getThuocnoitruSoluong() != null)
                    	thuoc += ", " + each.getThuocnoitruSoluong().toString();
                    thuoc += " " + each.getThuocnoitruMathuoc(true).getDmdonvitinhMaso(true).getDmdonvitinhTen();
                    thuoc += "\n";
                } // END FOR
            } else {
                thuoc = IConstantsRes.KHONG_DUNG_THUOC;
            }
            
            if(thuoc.length() > 2040){
            	thuoc = thuoc.substring(0, 2039) + " ...";
			}
            
            // 20110912 bao.ttc: xoa thong tin cu de cap nhat lai thong tin moi
            tdt.setTodieutriThuchienchamsoc(thuoc);
//            if(tdt.getTodieutriThuchienchamsoc() == null || tdt.getTodieutriThuchienchamsoc().equals("")){
//            	tdt.setTodieutriThuchienchamsoc(thuoc);
//            } else {
//            	String tmp = tdt.getTodieutriThuchienchamsoc();
//            	tdt.setTodieutriThuchienchamsoc(tmp + "\n" + thuoc);
//            }
            
        }
    }	
	
	public void resetList()
	{
		listTdt.clear();
		ToDieuTriDelegate tdtDel = ToDieuTriDelegate.getInstance();
		listTdt = tdtDel.findByHsba(hsbaMaso);
	}
	
	public void addChitiet() {
		log.info("Begin addChitiet, hsba = " + hsba);
		Calendar cal = Calendar.getInstance();
		String gioTmp = (gio.trim().length() < 1 ? "00:00" : gio);
		try {
			SimpleDateFormat df = new SimpleDateFormat(FORMAT_DATE_TIME);
			cal.setTime(df.parse(ngay + " " + gioTmp));
			tdt.setTodieutriNgaygio(cal.getTime());
			tdt.setHsbaSovaovien(hsba);
			tdt.setTodieutriTenbs(tenBs);
			// Luu thong tin xuong database
			tdt = ToDieuTriDelegate.getInstance().createToDieuTri(tdt);
			
			// Them vao list de hien thi len giao dien
//			if (!isEdit ){
//				listTdt.add(tdt);
//			}
			resetList();
			// Reset value
			tdt = new ToDieuTri();
			gio = ngay = "";
			isEdit = false;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void editChitiet(ToDieuTri tdtTmp) {
		log.info("Begin editChitiet, tdtTmp = " + tdtTmp);		
		tdt = tdtTmp;		
		isEdit = true;		
		gio = showGio(tdt.getTodieutriNgaygio());		
		ngay = showNgay(tdt.getTodieutriNgaygio());
		log.info("End editChitiet");
	}
	
	public void deleteChitiet(ToDieuTri tdtTmp) {
		// Xoa khoi list
		listTdt.remove(tdtTmp);
		// Xoa trong database
		ToDieuTriDelegate.getInstance().remove(tdtTmp);
		tdt = new ToDieuTri();		
		gio = "";
		ngay = "";
		isEdit = false;
	}
	
	private String showGio(Date dateTmp) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateTmp);
		String strGio = (cal.get(Calendar.HOUR_OF_DAY) < 10 ? "0" + cal.get(Calendar.HOUR_OF_DAY) : cal.get(Calendar.HOUR_OF_DAY)) + ":" + (cal.get(Calendar.MINUTE) < 10 ? "0" + cal.get(Calendar.MINUTE) : cal.get(Calendar.MINUTE));
		return strGio;
	}
	
	private String showNgay(Date dateTmp) {
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
		loaiBCDT="ToDieuTri";
		log.info("Vao Method XuatReport To Dieu tri");
		String baocao1=null;
		String pathTemplate = null;
		try {
			log.info("bat dau method XuatReport ");
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/D39_todieutri.jrxml";
			
			
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintDT,new Long(new Date().getTime()).intValue(),IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"hsba/", "pdf","ToDieuTri");
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
	public ToDieuTri getTdt() {
		return tdt;
	}
	public void setTdt(ToDieuTri tdt) {
		this.tdt = tdt;
	}
	public List<ToDieuTri> getListTdt() {
		return listTdt;
	}
	public void setListTdt(List<ToDieuTri> listTdt) {
		this.listTdt = listTdt;
	}
	
}
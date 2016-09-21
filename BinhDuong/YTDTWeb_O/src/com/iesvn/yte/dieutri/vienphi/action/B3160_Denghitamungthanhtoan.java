package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaKhoaDelegate;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.HsbaKhoa;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B3160_Denghitamungthanhtoan")
@Synchronized(timeout = 6000000)
public class B3160_Denghitamungthanhtoan implements Serializable {
	
	private String ngayDenghi;
	private String maKhoa = "";
	private Hsba hoSoBenhAn;
	private HsbaBhyt hsbaBHYT;
	private String tamUngThToan;
	private String soTien;

	@Out(required=false)
	@In(required=false)
	private String reportTypeVP=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintVP=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportPathVP=null;
	
	
	@In(scope=ScopeType.PAGE,required=false)
	@Out(scope=ScopeType.PAGE,required=false)
	private String resetFormB3160=null;
	private static Logger log = Logger.getLogger(B3160_Denghitamungthanhtoan.class);
	@Factory("resetFormB3160")
	public void init() {
		log.info("init()");
		resetForm();		
	}
	
	public void resetForm(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		ngayDenghi = sdf.format(new Date());
		maKhoa = "";
		hoSoBenhAn = new Hsba();
		hsbaBHYT = new HsbaBhyt();
		tamUngThToan = "0";
		soTien = "0";
		resetFormB3160 = "";
	}

public void displayInfor(){
		
		if (hoSoBenhAn.getHsbaSovaovien() == null || hoSoBenhAn.getHsbaSovaovien().equals("")
				|| maKhoa == null || maKhoa.equals("")) {
			resetForm();
			FacesMessages.instance().add(IConstantsRes.KHOA_HSBA_NULL,"","");			
			return;
		}
		
		HsbaDelegate hsbaDel = HsbaDelegate.getInstance();
		hoSoBenhAn = hsbaDel.find(hoSoBenhAn.getHsbaSovaovien());
				
		HsbaKhoaDelegate hsbaKhoaDelegate = HsbaKhoaDelegate.getInstance();
//		hsbaKhoa = hsbaKhoaDelegate.findBySoVaoVienAndKhoaMa(hsba.getHsbaSovaovien(),khoaMa);

		// lay hsba khoa cuoi cung
		log.info("hoSoBenhAn.getHsbaSovaovien():"+hoSoBenhAn.getHsbaSovaovien() + "ma khoa "+ maKhoa);
		
		HsbaKhoa hsbaKhoa = hsbaKhoaDelegate.findBySoVaoVienAndKhoaMa(hoSoBenhAn.getHsbaSovaovien(),maKhoa);
		if (hsbaKhoa == null) {			
			resetForm();
			FacesMessages.instance().add(IConstantsRes.KHOA_HSBA_NULL,"","");			
			return;
		}	
		
		// 20120312 bao.ttc: xem xet khong can nhap thong tin khoa tai form nay vi khong can thiet
		// kiem tra khoaMa co' phai la khoa cuoi cung ko
		log.info("maKhoa:"+maKhoa);
		if (!maKhoa.equals(hsbaKhoa.getKhoaMa().getDmkhoaMa())){
			resetForm();
			FacesMessages.instance().add(IConstantsRes.KHOA_HSBA_NULL,"","");			
			return;
		}
		
		
		
		HsbaBhytDelegate hsbaBHYTDelegate = HsbaBhytDelegate.getInstance();
		hsbaBHYT = hsbaBHYTDelegate.findBySoVaoVienLastHsbaBhyt(hoSoBenhAn.getHsbaSovaovien());
		if (hsbaBHYT == null){
			hsbaBHYT = new HsbaBhyt();
		}
		tamUngThToan = "0";
		soTien = "0";
		log.info("-----hsba: " + hoSoBenhAn);
		log.info("-----ho ten: " + hoSoBenhAn.getBenhnhanMa().getBenhnhanHoten());
		
	}
	int index = 0;
	public String thuchienAction(){
		log.info("Begin thuchienAction(), tamUngThToan: " + tamUngThToan);
		reportTypeVP="B3160_Denghitamungthanhtoan";
		log.info("Vao Method XuatReport B3160_Denghitamungthanhtoan");
		String baocao1=null;
		String pathTemplate = null;
		try {
			//logger.info("bat dau method XuatReport ");
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/B135_Denghitamungthanhtoan.jrxml";
			log.info("da thay file templete" + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			//logger.info(String.format("-----tenSo: %s", params.get("tenSo")));
			params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			//logger.info(String.format("-----tenDonVi: %s", params.get("tenDonVi")));
			params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			//logger.info(String.format("-----dienThoaiDonVi: %s", params.get("dienThoaiDonVi")));
			
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			
			params.put("loaidenghi", tamUngThToan);
			params.put("ngayDeNghi", Utils.getCurrentDate());
			
			params.put("soBenhAn", hoSoBenhAn.getHsbaSovaovien());
			params.put("KhoaDT", hoSoBenhAn.getHsbaKhoadangdt() == null ? null : hoSoBenhAn.getHsbaKhoadangdt(true).getDmkhoaTen() );
			params.put("BuongDT", hoSoBenhAn.getTangDangdt() == null ? null : hoSoBenhAn.getTangDangdt(true).getDmtangTen() );
			params.put("TEN", hoSoBenhAn.getBenhnhanMa(true).getBenhnhanHoten());
			
			String diachistr = "";
			if(hoSoBenhAn.getBenhnhanMa(true).getBenhnhanDiachi() != null && !hoSoBenhAn.getBenhnhanMa(true).getBenhnhanDiachi().equals("") )
				diachistr += hoSoBenhAn.getBenhnhanMa(true).getBenhnhanDiachi();
			if(hoSoBenhAn.getBenhnhanMa(true).getXaMa(true).getDmxaTen() !=null && !hoSoBenhAn.getBenhnhanMa(true).getXaMa(true).getDmxaTen().equals(""))
				diachistr += ", " + hoSoBenhAn.getBenhnhanMa(true).getXaMa(true).getDmxaTen();
			if(hoSoBenhAn.getBenhnhanMa(true).getHuyenMa(true).getDmhuyenTen() != null && !hoSoBenhAn.getBenhnhanMa(true).getHuyenMa(true).getDmhuyenTen().equals(""))
				diachistr += ", " + hoSoBenhAn.getBenhnhanMa(true).getHuyenMa(true).getDmhuyenTen();
			if(hoSoBenhAn.getBenhnhanMa(true).getTinhMa(true).getDmtinhTen() != null && !hoSoBenhAn.getBenhnhanMa(true).getTinhMa(true).getDmtinhTen().equals(""))
				diachistr += ", " + hoSoBenhAn.getBenhnhanMa(true).getTinhMa(true).getDmtinhTen();
			params.put("DIACHI", diachistr );
			
			params.put("DANTOC", hoSoBenhAn.getBenhnhanMa(true).getDantocMa(true).getDmdantocTen());
			params.put("KCB", hsbaBHYT.getHsbabhytMakcb(true).getDmbenhvienTen());
			if (hsbaBHYT.getHsbabhytTuyen() == null) {
				params.put("TUYEN", "");
			} else {
				params.put("TUYEN", String.valueOf( hsbaBHYT.getHsbabhytTuyen()));
			}
			params.put("GIOITINH", hoSoBenhAn.getBenhnhanMa(true).getDmgtMaso(true).getDmgtTen());
			
			Double soTienTmp = null;
			try{
				soTienTmp = new Double(soTien);
			}catch(Exception e){
				soTienTmp = new Double(0);
			}
			params.put("SOTIEN", soTienTmp);
			params.put("TIENBANGCHU", Utils.NumberToString(soTienTmp));
			
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","B135_Denghitamungthanhtoan");
			    reportPathVP=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    //logger.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathVP);
			    index+=1;
			    //logger.info("Index :" + index);
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
		return "B3360_Chonmenuxuattaptin";
		
	}
	public String getNgayDenghi() {
		return ngayDenghi;
	}

	public void setNgayDenghi(String ngayDenghi) {
		this.ngayDenghi = ngayDenghi;
	}

	public String getMaKhoa() {
		return maKhoa;
	}

	public void setMaKhoa(String maKhoa) {
		this.maKhoa = maKhoa;
	}

	public Hsba getHoSoBenhAn() {
		return hoSoBenhAn;
	}

	public void setHoSoBenhAn(Hsba hoSoBenhAn) {
		this.hoSoBenhAn = hoSoBenhAn;
	}

	public HsbaBhyt getHsbaBHYT() {
		return hsbaBHYT;
	}

	public void setHsbaBHYT(HsbaBhyt hsbaBHYT) {
		this.hsbaBHYT = hsbaBHYT;
	}

	public String getTamUngThToan() {
		return tamUngThToan;
	}

	public void setTamUngThToan(String tamUngThToan) {
		this.tamUngThToan = tamUngThToan;
	}

	public String getSoTien() {
		return soTien;
	}

	public void setSoTien(String soTien) {
		this.soTien = soTien;
	}
	
}



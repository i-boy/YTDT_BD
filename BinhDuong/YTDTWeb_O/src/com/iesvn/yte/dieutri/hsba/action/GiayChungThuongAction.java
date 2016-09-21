package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.faces.FacesMessages;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaGiayChungThuongDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.HsbaGiayChungThuong;
import com.iesvn.yte.entity.DmBenhIcd;
import com.iesvn.yte.entity.DmDanToc;
import com.iesvn.yte.entity.DmGioiTinh;
import com.iesvn.yte.entity.DmHuyen;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmNgheNghiep;
import com.iesvn.yte.entity.DmTinh;
import com.iesvn.yte.entity.DmXa;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B231_Giaychungthuong")
@Synchronized(timeout = 6000000)
public class GiayChungThuongAction implements Serializable {

	private static final long serialVersionUID = -1896799593376801640L;
	private static Logger log = Logger.getLogger(GiayChungThuongAction.class);
	
	private HsbaGiayChungThuong gct;
	private HsbaChuyenMon cm;
	private Hsba hsba_tmp; // 20101110 bao.ttc: dung load chan doan len giao dien
	private String nnTru;
	private String maHSTD;
	private String nosuccess;
	private String nofound;
	private String nofoundBATD;
	private boolean isUpdate;
	private String ngaycn;
	private String ngayyc;
	
	private String resultReportFileName;
	private String resultReportName;	
	private String loaiFileXuat;
	@Out(required = false)
	private String isReport = "false"; 
	
	@In(required=false)
	@Out(required=false)
	private String duongdanStrDT = null;
	
	@Out(required=false)
	@In(required=false)
	private String loaiBCDT = null;
	
	@In(required=false)
	@Out(required=false)
	JasperPrint jasperPrintDT = null; 
	
	private int index=0;
	
	@Begin (join = true)
	public String init() {	
		resetValue();
		return "DieuTri_LapVanBanTheoMau_GiayChungThuong";
	}

	@End
	public void endFunc(){
		
	}
	public void resetValue() {
		log.info("---resetValue");
		gct = new HsbaGiayChungThuong();
		setInfoIfNullForHsbaGiayChungThuong(gct);
		cm = new HsbaChuyenMon();
		setInfoIfNullForHsbaChuyenMon(cm);
		hsba_tmp = new Hsba();				// 20101110 bao.ttc
		setInfoIfNullForHsba(hsba_tmp);		// 20101110 bao.ttc
		nnTru="noi";
		maHSTD="";
		ngaycn=ngayyc=new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
		nosuccess=nofound=nofoundBATD="false";
		isUpdate=false;
	}
	
	public void ghiNhan(){
		log.info("---ghiNhan");
		String result="";
		if (!ngayyc.trim().equals("")){
			gct.setHsbagctNgayyc(Utils.getDBDate("00:00", ngayyc).getTime());
		}
		if (!ngaycn.trim().equals("")){
			gct.setHsbagctNgaygiocn(Utils.getDBDate("00:00", ngaycn).getTime());
		}
		if (isUpdate){
			result = HsbaGiayChungThuongDelegate.getInstance().update(gct);
			if (result == null || result.equals("")){
				nosuccess = "true";
				FacesMessages.instance().add(IConstantsRes.GCT_CN_THATBAI);
			}else{
				gct.setHsbagctMa(result);			
				FacesMessages.instance().add(IConstantsRes.GCT_CN_THANHCONG, result);
			}
		}else{
			result = HsbaGiayChungThuongDelegate.getInstance().insert(gct);
			if (result == null || result.equals("")){
				nosuccess = "true";
				FacesMessages.instance().add(IConstantsRes.GCT_LT_THATBAI);
			}else{
				gct.setHsbagctMa(result);			
				FacesMessages.instance().add(IConstantsRes.GCT_LT_THANHCONG, result);
			}
		}
		
	}
	
	public void displayGiayCT(){
		log.info("---displayGiayRaVien");
		String maGct = gct.getHsbagctMa().trim();
		HsbaGiayChungThuong gct_tmp = null;
		if (!maGct.equals("")){
			gct_tmp = HsbaGiayChungThuongDelegate.getInstance().findByHsbagctMa(maGct);
			if (gct_tmp==null){
				nofound = "true";
				gct_tmp = new HsbaGiayChungThuong();
				FacesMessages.instance().add(IConstantsRes.GCT_NULL, maGct);
			}	
			setInfoIfNullForHsbaGiayChungThuong(gct_tmp);
			gct = gct_tmp;
			maHSTD = gct_tmp.getHsbaSovaovien().getHsbaSovaovien();
			nnTru = "noi";
			isUpdate = true;
		}else{
			nofound = "true";
			gct = new HsbaGiayChungThuong();
			setInfoIfNullForHsbaGiayChungThuong(gct);
		}
	}
	
	public void displayHsba(){
		log.info("---displayHSBA");
		hsba_tmp=null;
		if (!maHSTD.trim().equals("")){
			if (nnTru.equals("noi")){
				hsba_tmp = HsbaDelegate.getInstance().find(maHSTD.trim());
				if (hsba_tmp==null){
					nofoundBATD = "true";
					hsba_tmp = new Hsba();
					FacesMessages.instance().add(IConstantsRes.HSBA_NULL, maHSTD);
				}else{
					if (hsba_tmp.getHsbaKhoarav()!=null){
						HsbaChuyenMon cm_tmp = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_MaKhoa(hsba_tmp.getHsbaSovaovien(), hsba_tmp.getHsbaKhoarav().getDmkhoaMa());
						if (cm_tmp!=null){
							cm = cm_tmp;
							setInfoIfNullForHsbaChuyenMon(cm);
						}
					}					
				}
			}else if (nnTru.equals("ngoai")){
				hsba_tmp = HsbaDelegate.getInstance().findByTiepDonMa(maHSTD.trim());
				if (hsba_tmp==null){
					nofoundBATD = "true";
					hsba_tmp = new Hsba();
					FacesMessages.instance().add(IConstantsRes.HSBA_BY_MTD_NULL, maHSTD);
				}else{
					if (hsba_tmp.getHsbaKhoarav()!=null){
						HsbaChuyenMon cm_tmp = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_MaKhoa(hsba_tmp.getHsbaSovaovien(), hsba_tmp.getHsbaKhoarav().getDmkhoaMa());
						if (cm_tmp!=null){
							cm = cm_tmp;
							setInfoIfNullForHsbaChuyenMon(cm);
						}
					}					
				}
			}
			setInfoIfNullForHsba(hsba_tmp);
			gct.setHsbaSovaovien(hsba_tmp);
			maHSTD=hsba_tmp.getHsbaSovaovien();
		}else{
			nofoundBATD = "true";
			hsba_tmp = new Hsba();
			setInfoIfNullForHsba(hsba_tmp);
			gct.setHsbaSovaovien(hsba_tmp);
		}
	}
	
	
	public String thuchienAction(){
		XuatReport();
		return "B260_Chonmenuxuattaptin";
	}
	
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		loaiBCDT="Giaychungnhanthuongtich";
		log.info("Vao Method XuatReport Giaychungnhanthuongtich");
		String baocao1=null;
		String pathTemplate=null;
		try {
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/Giaychungnhanthuongtich.jrxml";
			log.info("Da thay file template: " + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			Map<String, Object> params = new HashMap<String, Object>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // 20101108 bao.ttc
			Hsba hsba = gct.getHsbaSovaovien();
			
			params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("tenDonViFull", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL);
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			
			params.put("soGCT", gct.getHsbagctMa());
			params.put("soVaoVien", hsba.getHsbaSovaovien());
			
			params.put("hoTen", hsba.getBenhnhanMa().getBenhnhanHoten());
			params.put("gioiTinh", hsba.getBenhnhanMa().getDmgtMaso(true).getDmgtTen());
			
			// 20101108 bao.ttc: Neu co ngay sinh thi su dung, neu khong thi dung nam sinh
			Date dNgaySinh = hsba.getBenhnhanMa().getBenhnhanNgaysinh();
			if (dNgaySinh != null) {
				
				params.put("ngaySinh", "" + sdf.format(hsba.getBenhnhanMa().getBenhnhanNgaysinh()) );
				if(hsba.getBenhnhanMa().getBenhnhanNamsinh() != null)
					params.put("namSinh", hsba.getBenhnhanMa().getBenhnhanNamsinh());
				else
					params.put("namSinh", "");
				
			} else {
				if(hsba.getBenhnhanMa().getBenhnhanNamsinh() != null)
					params.put("namSinh", hsba.getBenhnhanMa().getBenhnhanNamsinh());
				else
					params.put("namSinh", "");
			}
			
			params.put("ngheTen", hsba.getBenhnhanMa().getBenhnhanNghe(true).getDmnghenghiepTen());
			params.put("cmnd", hsba.getBenhnhanMa().getBenhnhanCmnd());
			
			// 20101110 bao.ttc: them chi tiet dia chi
			String diachistr = "";
			if (hsba.getBenhnhanMa().getBenhnhanDiachi() != null)
				diachistr += hsba.getBenhnhanMa().getBenhnhanDiachi();
			if (hsba.getBenhnhanMa().getXaMa(true).getDmxaTen() != null)
				diachistr += ", " + hsba.getBenhnhanMa().getXaMa(true).getDmxaTen();
			if (hsba.getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() != null)
				diachistr += ", " + hsba.getBenhnhanMa().getHuyenMa(true).getDmhuyenTen();
			if (hsba.getBenhnhanMa().getTinhMa(true).getDmtinhTen() != null)
				diachistr += ", " + hsba.getBenhnhanMa().getTinhMa(true).getDmtinhTen();
			params.put("diaChi", diachistr);
			
			// 20101110 bao.ttc: Them chan doan
			String maChanDoan = "";
			String tenChanDoan = "";

			if (hsba.getHsbaMachdoanbd() != null) {
				
				if (hsba.getHsbaMachdoanbd().getDmbenhicdMa() != null)
					maChanDoan = hsba.getHsbaMachdoanbd().getDmbenhicdMa();
				if (hsba.getHsbaMachdoanbd().getDmbenhicdTen() != null)
					tenChanDoan = hsba.getHsbaMachdoanbd().getDmbenhicdTen();

			}
			params.put("CHANDOAN", maChanDoan + " - " + tenChanDoan);
			// 20101110 bao.ttc: Them chan doan -- END
			
			Date dVaov = hsba.getHsbaNgaygiovaov();
			if (dVaov != null) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(dVaov);
				params.put("gioVaov", "" + cal.get(Calendar.HOUR_OF_DAY));
				params.put("phutVaov", "" + cal.get(Calendar.MINUTE));
				params.put("ngayVaov", "" + cal.get(Calendar.DAY_OF_MONTH));
				params.put("thangVaov", "" + (cal.get(Calendar.MONTH) + 1));
				params.put("namVaov", "" + cal.get(Calendar.YEAR));
			} else {
				params.put("gioVaov", "");
				params.put("phutVaov", "");
				params.put("ngayVaov", "");
				params.put("thangVaov", "");
				params.put("namVaov", "");
			}
			
			Date dRav = hsba.getHsbaNgaygiorav();
			if (dRav != null) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(dRav);
				params.put("gioRav", "" + cal.get(Calendar.HOUR_OF_DAY));
				params.put("phutRav", "" + cal.get(Calendar.MINUTE));
				params.put("ngayRav", "" + cal.get(Calendar.DAY_OF_MONTH));
				params.put("thangRav", "" + (cal.get(Calendar.MONTH) + 1));
				params.put("namRav", "" + cal.get(Calendar.YEAR));
			} else {
				params.put("gioRav", "");
				params.put("phutRav", "");
				params.put("ngayRav", "");
				params.put("thangRav", "");
				params.put("namRav", "");
			}
			
			params.put("thuongtichvaovien", gct.getHsbagctTtvv());
			params.put("thuongtichravien", gct.getHsbagctTtrv());
			params.put("dieutri", gct.getHsbagctNoidung());
			
			
			
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintDT,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"hsba/", "pdf","Giaychungnhanthuongtich");
			    duongdanStrDT=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report : " + baocao1);
			    log.info("duong dan -------------------- : " + duongdanStrDT);
			    index+= 1;
			    log.info("Index :" + getIndex());
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Thoat Method XuatReport");
	}
	
	private void setInfoIfNullForHsbaGiayChungThuong(HsbaGiayChungThuong obj){
		if (obj.getHsbaSovaovien()==null){
			Hsba _hsba = new Hsba();			
			setInfoIfNullForHsba(_hsba);
			obj.setHsbaSovaovien(_hsba);
		}
		if (obj.getHsbaSovaovien().getHsbaKhoarav()==null)
			obj.getHsbaSovaovien().setHsbaKhoarav(new DmKhoa());
	}
	
	private void setInfoIfNullForHsba(Hsba obj){
		if (obj.getBenhnhanMa()==null){
			BenhNhan _benhnhan = new BenhNhan();
			setInfoIfNullForBenhNhan(_benhnhan);
			obj.setBenhnhanMa(_benhnhan);
		}
	}
	
	private void setInfoIfNullForBenhNhan(BenhNhan obj) {
		if (obj.getDmgtMaso()==null)
			obj.setDmgtMaso(new DmGioiTinh());
		if (obj.getDantocMa()==null)
			obj.setDantocMa(new DmDanToc());
		if (obj.getTinhMa()==null)
			obj.setTinhMa(new DmTinh());
		if (obj.getHuyenMa()==null)
			obj.setHuyenMa(new DmHuyen());
		if (obj.getXaMa()==null)
			obj.setXaMa(new DmXa());
		if (obj.getBenhnhanNghe()==null)
			obj.setBenhnhanNghe(new DmNgheNghiep());
	}
	
	private void setInfoIfNullForHsbaChuyenMon(HsbaChuyenMon _cm) {
		if (_cm.getHsbacmBenhchinh()==null)
			_cm.setHsbacmBenhchinh(new DmBenhIcd());
	}
	
		
	public String formatDate(Date date){
		return date==null?"":new SimpleDateFormat("dd/MM/yyyy").format(date);
	}
		
	public String formatDateTime(Date date){
		return date==null?"":Utils.getGioPhut(date);
	}
	
	public String formatGtBenhNhan(String gioitinh){
		if (gioitinh==null || gioitinh.trim().equals("")){
			return "";
		}
		return gioitinh.equals("1")?"true":"false";
	}
	
	public Hsba getHsba_tmp() {
		return hsba_tmp;
	}

	public void setHsba_tmp(Hsba hsbaTmp) {
		hsba_tmp = hsbaTmp;
	}

	public HsbaChuyenMon getCm() {
		return cm;
	}

	public void setCm(HsbaChuyenMon cm) {
		this.cm = cm;
	}
	
	public String getMaHSTD() {
		return maHSTD;
	}

	public void setMaHSTD(String maHSTD) {
		this.maHSTD = maHSTD;
	}

	public String getNnTru() {
		return nnTru;
	}

	public void setNnTru(String nnTru) {
		this.nnTru = nnTru;
	}

	public HsbaGiayChungThuong getGct() {
		return gct;
	}

	public void setGct(HsbaGiayChungThuong gct) {
		this.gct = gct;
	}
	
	public String getNofoundBATD() {
		return nofoundBATD;
	}

	public void setNofoundBATD(String nofoundBATD) {
		this.nofoundBATD = nofoundBATD;
	}

	public String getNofound() {
		return nofound;
	}

	public void setNofound(String nofound) {
		this.nofound = nofound;
	}

	public String getNosuccess() {
		return nosuccess;
	}

	public void setNosuccess(String nosuccess) {
		this.nosuccess = nosuccess;
	}
	
	public String getNgaycn() {
		return ngaycn;
	}

	public void setNgaycn(String ngaycn) {
		this.ngaycn = ngaycn;
	}

	public String getNgayyc() {
		return ngayyc;
	}

	public void setNgayyc(String ngayyc) {
		this.ngayyc = ngayyc;
	}

	public boolean isUpdate() {
		return isUpdate;
	}

	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public String getResultReportFileName() {
		return resultReportFileName;
	}

	public void setResultReportFileName(String resultReportFileName) {
		this.resultReportFileName = resultReportFileName;
	}

	public String getLoaiFileXuat() {
		return loaiFileXuat;
	}

	public void setLoaiFileXuat(String loaiFileXuat) {
		this.loaiFileXuat = loaiFileXuat;
	}

	public String getIsReport() {
		return isReport;
	}

	public void setIsReport(String isReport) {
		this.isReport = isReport;
	}

	public String getResultReportName() {
		return resultReportName;
	}

	public void setResultReportName(String resultReportName) {
		this.resultReportName = resultReportName;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

}

package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
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
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.ClsMoDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaKhoaDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate; // 20101116 bao.ttc
import com.iesvn.yte.dieutri.delegate.ThuocNoiTruDelegate;
import com.iesvn.yte.dieutri.delegate.YteLogDelegate;
import com.iesvn.yte.dieutri.entity.ClsMo;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.HsbaKhoa;
import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
import com.iesvn.yte.dieutri.entity.YteLog;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.entity.DmBenhIcd;
import com.iesvn.yte.entity.DmGioiTinh;
import com.iesvn.yte.entity.DmHuyen;
import com.iesvn.yte.entity.DmKetQuaDieuTri;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmTinh;
import com.iesvn.yte.entity.DmXa;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;


@Scope(CONVERSATION)
@Name("B3115_Xacnhanthongtindieutri")
@Synchronized(timeout = 6000000)
public class XacNhanThongTinDieuTriAction implements Serializable {
	private static final long serialVersionUID = 10L;
	private static Logger log = Logger.getLogger(XacNhanThongTinDieuTriAction.class);
	
	private String ngayHt;
	private String ngayVaov;
	private String khoaMa;
	private String soVaoVien;
	private String ngaySinh;
	private String khoaDt;
	private String khoaDangDt; // 20111003 bao.ttc: them vao de dua len giao dien, tranh gan truc tiep tren entity de tranh null
	private Hsba hsba;
	private HsbaChuyenMon hsbaCm;
	private HsbaBhyt hsbabhyt;
	
	private Boolean hienThiInPhieu;
	private String ngayRaVien;
	private String gioRaVien;
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
	public String getGioRaVien() {
		return gioRaVien;
	}

	public String getNgayRaVien() {
		return ngayRaVien;
	}

	public void setNgayRaVien(String ngayRaVien) {
		this.ngayRaVien = ngayRaVien;
	}

	public void setGioRaVien(String gioRaVien) {
		this.gioRaVien = gioRaVien;
	}

	@Begin(join = true)
	public String init() {
		log.info("-----init()-----");
		reset();
		hienThiInPhieu = false;
		return "VienPhiTaiKhoa_SoLieuBNSuDung_XacNhanThongTinDieuTri";
	}
	
	@End
	public void endFunct(){
		
	}
	public void xacNhan(){
		HsbaDelegate hsbaDele = HsbaDelegate.getInstance();
		
		RemoveUtil.removeAllNullFromHSBA(hsba);
		if (ngayRaVien != null && !ngayRaVien.equals("")
		   && gioRaVien != null && !gioRaVien.equals("")		
		){
			Calendar cTemp = Utils.getDBDate(gioRaVien, ngayRaVien);
			if (cTemp != null){
				hsba.setHsbaNgaygiorav(cTemp.getTime());
			}
		} else { // 20101104 bao.ttc: Truong hop user khong nhap ngay gio ra vien thi mac dinh dung ngay gio hien tai cua he thong
			ngayRaVien = Utils.getCurrentDate();
			Date date = new Date();
			gioRaVien = Utils.getGioPhut(date);
			Calendar cTemp = Utils.getDBDate(gioRaVien, ngayRaVien);
			if (cTemp != null){
				hsba.setHsbaNgaygiorav(cTemp.getTime());
			}
		}
		//Kiem tra co thuoc hay CLS nao duoc chi dinh truoc ngay vao vien hoac sau ngay ra vien hay khong
		ThuocNoiTruDelegate tntDel = ThuocNoiTruDelegate.getInstance();
		ClsMoDelegate clsmoDel = ClsMoDelegate.getInstance();
		
		List<ThuocNoiTru> listTntTruocNgayVV = tntDel.checkThuocNoiTruTruocNgayVaoVien(soVaoVien);
		List<ThuocNoiTru> listTntSauNgayRV = tntDel.checkThuocNoiTruSauNgayRaVien(soVaoVien, hsba.getHsbaNgaygiorav());
		List<ClsMo> listClsmoTruocNgayVV = clsmoDel.checkClsmoTruocNgayVaoVien(soVaoVien);
		List<ClsMo> listClsmoSauNgayRV = clsmoDel.checkClsmoSauNgayRaVien(soVaoVien, hsba.getHsbaNgaygiorav());
		
		int countThuocTruocNgayVV = (listTntTruocNgayVV == null ? 0 : listTntTruocNgayVV.size());
		int countThuocSauNgayRV = (listTntSauNgayRV == null ? 0 : listTntSauNgayRV.size());
		int countClsTruocNgayVV = (listClsmoTruocNgayVV == null ? 0 : listClsmoTruocNgayVV.size());
		int countClsSauNgayRV = (listClsmoSauNgayRV == null ? 0 : listClsmoSauNgayRV.size());
		if (countThuocTruocNgayVV > 0 && countClsTruocNgayVV > 0) {
			FacesMessages.instance().add(IConstantsRes.BENH_NHAN_CO_THUOC_CLS_TRUOC_NGAY_VAO_VIEN, IConstantsRes.THUOC_VA_CAN_LAM_SANG);
		} else if (countThuocTruocNgayVV > 0 && countClsTruocNgayVV == 0) {
			FacesMessages.instance().add(IConstantsRes.BENH_NHAN_CO_THUOC_CLS_TRUOC_NGAY_VAO_VIEN, IConstantsRes.THUOC);
		} else if (countThuocTruocNgayVV == 0 && countClsTruocNgayVV > 0) {
			FacesMessages.instance().add(IConstantsRes.BENH_NHAN_CO_THUOC_CLS_TRUOC_NGAY_VAO_VIEN, IConstantsRes.CAN_LAM_SANG);
		}
		
		if (countThuocSauNgayRV > 0 && countClsSauNgayRV > 0) {
			FacesMessages.instance().add(IConstantsRes.BENH_NHAN_CO_THUOC_CLS_SAU_NGAY_RA_VIEN, IConstantsRes.THUOC_VA_CAN_LAM_SANG);
		} else if (countThuocSauNgayRV > 0 && countClsSauNgayRV == 0) {
			FacesMessages.instance().add(IConstantsRes.BENH_NHAN_CO_THUOC_CLS_SAU_NGAY_RA_VIEN, IConstantsRes.THUOC);
		} else if (countThuocSauNgayRV == 0 && countClsSauNgayRV > 0) {
			FacesMessages.instance().add(IConstantsRes.BENH_NHAN_CO_THUOC_CLS_SAU_NGAY_RA_VIEN, IConstantsRes.CAN_LAM_SANG);
		}
		if (countThuocTruocNgayVV > 0 || countClsTruocNgayVV > 0 || countThuocSauNgayRV > 0 || countClsSauNgayRV > 0) {
			hienThiInPhieu = false;
			return;
		}
		// 20101105 bao.ttc: set Khoa dang dieu tri la khoa ra vien
		if(hsba.getHsbaKhoadangdt() != null)
			hsba.setHsbaKhoarav(hsba.getHsbaKhoadangdt());
		
		// 20101130 bao.ttc: set ma ICD Tu Vong neu KQ dieu tri la tu vong
		if(hsba.getHsbaKetqua() != null){
			if(hsba.getHsbaKetqua().getDmkqdtMaso() == 5){
				if(hsba.getHsbaMachdravien() != null)
					hsba.setHsbaTuvong(hsba.getHsbaMachdravien());
			}
		}
		
		// 20110418 bao.ttc: check neu Yeu cau == "" thi cap nhat yeu cau mac dinh (de co the Thanh toan ra vien)
		if (hsba.getHsbaYeuCau() == null || hsba.getHsbaYeuCau().equals("")){
			hsba.setHsbaYeuCau("  ");
		}
		
		
		hsbaDele.edit(hsba);
		
		// 20101116 bao.ttc: set HSBACM.Huong dieu tri la Ra vien
		HsbaChuyenMonDelegate hsbacmDele = HsbaChuyenMonDelegate.getInstance();
		HsbaChuyenMon hsbacm_temp = hsbacmDele.findBySoVaoVien_MaKhoa(soVaoVien, khoaMa);
		if(hsbacm_temp != null){
			hsbaCm = hsbacm_temp;
			hsbaCm.setHsbacmHuongdieutri("3"); // 1: chuyen khoa; 	2: chuyen tuyen tren; 	3: ra vien
			
			// 20120216 bao.ttc: set lai KQ Dieu tri vao HSBACM
			if(hsba.getHsbaKetqua() != null){
				hsbaCm.setKetquaMa(hsba.getHsbaKetqua());
			}
			
			hsbacmDele.edit(hsbaCm);
			log.info("### Edit thanh cong HSBACM.HuongDieuTri: 3 ###");
		}
		
//		Luu log he thong
		 SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE_TIME_HOUR_FIRST);
		 YteLogDelegate yteLogDele = YteLogDelegate.getInstance();
         YteLog yteLog = new YteLog();

         yteLog.setForm("B3115_Xacnhanthongtindieutri");
         yteLog.setUserId(identity == null ? "NULL" : identity.getUsername());
         yteLog.setObjectId(soVaoVien);
         yteLog.setLogString("Ngay cap nhat: "+ ngayHt + "\n "+
        		 			 " Chuan doan ra vien: " + (hsba == null ? "NULL" :hsba.getHsbaMachdravien(true).getDmbenhicdMa()) +"\n "+
        		 			 " Ket qua dieu tri: " + (hsba == null ? "NULL" : hsba.getHsbaKetqua(true).getDmkqdtTen())+"\n "+
        		 			 " Ngay ra vien: " + (hsba == null ? "NULL" : hsba.getHsbaNgaygiorav()));
         yteLog.setDateTime(new Date());

         yteLogDele.create(yteLog);
         
		
		FacesMessages.instance().add(IConstantsRes.SUCCESS);
		hienThiInPhieu = true;
	}
	public void reset() {
		ngayHt = Utils.getCurrentDate();
		ngayVaov = "";
		ngaySinh = "";
		khoaMa = "";
		soVaoVien = "";
		
		khoaDt = "";
		khoaDangDt = "";
		hsba = new Hsba();
		hsbaCm = new HsbaChuyenMon();
		hsbabhyt = new HsbaBhyt();
		hienThiInPhieu = false;
		ngayRaVien = Utils.getCurrentDate();
		Date date = new Date();
		gioRaVien = Utils.getGioPhut(date);
	}
	
	public void loadHsba() {
		
		if (soVaoVien == null || soVaoVien.equals("") ){
			log.info("hsba.getHsbaSovaovien() == null");
			reset();
			return;
		}
		
		try {
			
			HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
			Hsba hsbaCur = hsbaDelegate.find(soVaoVien);
		    if  (hsbaCur == null){
		    	FacesMessages.instance().add(IConstantsRes.HSBA_NULL, soVaoVien);
				reset();
				return ;
		    }
		    
		    // hsba da ton tai
		    hsba = hsbaCur;
		    soVaoVien = hsba.getHsbaSovaovien();		    
		    hienThiInPhieu = (hsba.getHsbaYeuCau() == null ? false : !hsba.getHsbaYeuCau().equals(""));
		    if (hsba.getHsbaKhoadangdt() == null){
		    	FacesMessages.instance().add(IConstantsRes.HSBA_NULL, soVaoVien);
		    	log.info("khoaDangDieuTri == null");
		    	reset();
		    	return;
		    }
		    khoaMa = hsba.getHsbaKhoadangdt(true).getDmkhoaMa();
		    
		    khoaDangDt = (hsba.getHsbaKhoadangdt(true).getDmkhoaMa() == null ? "" : hsba.getHsbaKhoadangdt(true).getDmkhoaMa())
		    			+ (hsba.getHsbaKhoadangdt(true).getDmkhoaTen() == null ? "" : " - " + hsba.getHsbaKhoadangdt(true).getDmkhoaTen());
			
			HsbaKhoaDelegate hsbaKhoaDelegate = HsbaKhoaDelegate.getInstance();
			
//			HsbaKhoa hsbaKhoa = hsbaKhoaDelegate.findBySoVaoVienAndKhoaMa(hsba.getHsbaSovaovien(), hsba.getHsbaKhoadangdt(true).getDmkhoaMa());
//			if (hsbaKhoa == null) {
//				FacesMessages.instance().add(IConstantsRes.HSBA_NULL, soVaoVien);
//				reset();
//				return;
//			}
			
			// 20120312 bao.ttc: list cac khoa DT can them thong tin Tang cho cu the
			List<HsbaKhoa> lstHsbaKhoa = hsbaKhoaDelegate.findBySoVaoVien(soVaoVien);
			khoaDt = "";
			for (HsbaKhoa hK : lstHsbaKhoa) {
				khoaDt += hK.getKhoaMa().getDmkhoaMa() + "   ";
			}
	
			HsbaBhytDelegate hsbabhytDele = HsbaBhytDelegate.getInstance();
			HsbaBhyt tmphsbabhyt = hsbabhytDele.findBySoVaoVienLastHsbaBhyt(soVaoVien);
			if (tmphsbabhyt != null){
				hsbabhyt = tmphsbabhyt;
			}
			
		} catch (Exception e) {
			FacesMessages.instance().add(IConstantsRes.HSBA_NULL, soVaoVien);
			e.printStackTrace();
			reset();
		}
			
			
		Date dNgaySinh = hsba.getBenhnhanMa(true).getBenhnhanNgaysinh();
		if (dNgaySinh != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(dNgaySinh);
			ngaySinh = Utils.convertCalendar2Str(cal);
		}
		
		Date dNgayVaov = hsba.getHsbaNgaygiovaov();
		if (dNgayVaov != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(dNgayVaov);
			ngayVaov = Utils.convertCalendar2Str(cal);
		}
		
		Date dNgayRav = hsba.getHsbaNgaygiorav();
		if (dNgayRav != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(dNgayRav);
			ngayRaVien = Utils.convertCalendar2Str(cal);
			gioRaVien = Utils.getGioPhut(dNgayRav);
		}
		
		if (hsba.getHsbaMachdoanbd() != null){
			if (hsba.getHsbaMachdravien() == null){
				hsba.setHsbaMachdravien(hsba.getHsbaMachdoanbd());
			}
		}
			
		
	}

	@Out(required=false)
	@In(required=false)
	private String reportPathVP=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeVP=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintVP=null;
	
	public String thuchienAction(){
		XuatReport();
		return "B3360_Chonmenuxuattaptin";
	}
	
	int index = 0;
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeVP="Xacnhanthongtindieutri";
		log.info("Vao Method XuatReport Xacnhanthongtindieutri");
		String baocao1=null;
		String pathTemplate=null;
		try {
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/Xacnhanthongtindieutri.jrxml";
			
			log.info("da thay file templete " + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("benhvien", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("sodt", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			
			String gt=null;
			params.put("TEN", hsba.getBenhnhanMa().getBenhnhanHoten());
			
			DieuTriUtilDelegate dieuTriDelegate = DieuTriUtilDelegate.getInstance();
			
			if(hsba.getBenhnhanMa().getDmgtMaso() != null) {
				DmGioiTinh gioiTinh = (DmGioiTinh)dieuTriDelegate.findByMa(hsba.getBenhnhanMa().getDmgtMaso(true).getDmgtMa(), "DmGioiTinh", "dmgtMa");
				if (gioiTinh != null){
					gt = gioiTinh.getDmgtTen();
				}else{
					gt = "";
				}
				
			}
			log.info("Gioi tinh " + gt);
			params.put("GT", gt);
			
			log.info("Dan toc " + hsba.getBenhnhanMa().getDantocMa(true).getDmdantocTen());
			params.put("DANTOC", hsba.getBenhnhanMa().getDantocMa(true).getDmdantocTen() );
			
			
			String diachistr="";
			if(  hsba.getBenhnhanMa().getBenhnhanDiachi() != null)
				diachistr += hsba.getBenhnhanMa().getBenhnhanDiachi() + ", ";
			
			if(hsba.getBenhnhanMa().getXaMa(true).getDmxaMa() !=null){
				DmXa xa = (DmXa)dieuTriDelegate.findByMa(hsba.getBenhnhanMa().getXaMa(true).getDmxaMa(), "DmXa", "dmxaMa");
				diachistr += xa.getDmxaTen() + ", ";
			}
			
			if(hsba.getBenhnhanMa().getHuyenMa(true).getDmhuyenMa() != null){
				DmHuyen huyen = (DmHuyen)dieuTriDelegate.findByMa(hsba.getBenhnhanMa().getHuyenMa(true).getDmhuyenMa(), "DmHuyen", "dmhuyenMa");
				diachistr += huyen.getDmhuyenTen() + ", ";
			}
			
			if(hsba.getBenhnhanMa().getTinhMa(true).getDmtinhMa() != null){
				DmTinh tinh = (DmTinh)dieuTriDelegate.findByMa(hsba.getBenhnhanMa().getTinhMa(true).getDmtinhMa(), "DmTinh", "dmtinhMa");
				diachistr += tinh.getDmtinhTen();
			}
			
			log.info("Dia chi " + diachistr);
			params.put("DIACHI", diachistr );
			
			DieuTriUtilDelegate dieutridele = DieuTriUtilDelegate.getInstance();
			DmKhoa khoa = (DmKhoa)dieutridele.findByMa(khoaMa, "DmKhoa", "dmkhoaMa");
			params.put("KHOA", khoa.getDmkhoaTen());
			
			String donViTuoi = hsba.getBenhnhanMa().getBenhnhanDonvituoi() == 2? "th" : ( hsba.getBenhnhanMa().getBenhnhanDonvituoi() == 3? "ng":"");
			params.put("TUOI", hsba.getBenhnhanMa().getBenhnhanTuoi() + donViTuoi);
			
			DmBenhIcd benhIcd = (DmBenhIcd)dieutridele.findByMa(hsba.getHsbaMachdravien(true).getDmbenhicdMa(), "DmBenhIcd", "dmbenhicdMa");
			if (benhIcd != null){
				params.put("CHANDOAN", benhIcd.getDmbenhicdTen());
			}else{
				params.put("CHANDOAN", "");
			}
			
			
			DmKetQuaDieuTri ketqua = (DmKetQuaDieuTri)dieutridele.findByMa(hsba.getHsbaKetqua(true).getDmkqdtMa(), "DmKetQuaDieuTri", "dmkqdtMa");
			if (ketqua != null){
				params.put("KETQUA", ketqua.getDmkqdtTen());
			}else{
				params.put("KETQUA", "");
			}
			
			
			params.put("YEUCAU", hsba.getHsbaYeuCau());
			
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			
			params.put("SOBENHAN", soVaoVien);
			
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","Xacnhanthongtindieutri");
			    reportPathVP=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathVP);
			    index+= 1;
			    log.info("Index :" + index);
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Thoat Method XuatReport");
	}
	
	public String getNgayHt() {
		return ngayHt;
	}

	public void setNgayHt(String ngayHt) {
		this.ngayHt = ngayHt;
	}

	public String getKhoaMa() {
		return khoaMa;
	}

	public void setKhoaMa(String khoaMa) {
		this.khoaMa = khoaMa;
	}

	public String getSoVaoVien() {
		return soVaoVien;
	}

	public void setSoVaoVien(String soVaoVien) {
		this.soVaoVien = soVaoVien;
	}

	public Hsba getHsba() {
		return hsba;
	}

	public void setHsba(Hsba hsba) {
		this.hsba = hsba;
	}

	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getNgayVaov() {
		return ngayVaov;
	}

	public void setNgayVaov(String ngayVaov) {
		this.ngayVaov = ngayVaov;
	}

	public HsbaBhyt getHsbabhyt() {
		return hsbabhyt;
	}

	public void setHsbabhyt(HsbaBhyt hsbabhyt) {
		this.hsbabhyt = hsbabhyt;
	}

	public HsbaChuyenMon getHsbaCm() {
		return hsbaCm;
	}

	public void setHsbaCm(HsbaChuyenMon hsbaCm) {
		this.hsbaCm = hsbaCm;
	}

	public String getKhoaDt() {
		return khoaDt;
	}

	public void setKhoaDt(String khoaDt) {
		this.khoaDt = khoaDt;
	}

	public String getKhoaDangDt() {
		return khoaDangDt;
	}

	public void setKhoaDangDt(String khoaDangDt) {
		this.khoaDangDt = khoaDangDt;
	}

	public Boolean getHienThiInPhieu() {
		return hienThiInPhieu;
	}

	public void setHienThiInPhieu(Boolean hienThiInPhieu) {
		this.hienThiInPhieu = hienThiInPhieu;
	}

	

	
}

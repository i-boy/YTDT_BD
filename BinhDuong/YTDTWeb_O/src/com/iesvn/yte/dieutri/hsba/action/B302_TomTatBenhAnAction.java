package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.iesvn.yte.dieutri.delegate.BenhNhanDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenVienDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaGiayRaVienDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaTomTatBenhAnDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.HsbaChuyenVien;
import com.iesvn.yte.dieutri.entity.HsbaGiayRaVien;
import com.iesvn.yte.dieutri.entity.HsbaTomTatBenhAn;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.entity.DmDanToc;
import com.iesvn.yte.entity.DmGioiTinh;
import com.iesvn.yte.entity.DmHuyen;
import com.iesvn.yte.entity.DmNgheNghiep;
import com.iesvn.yte.entity.DmTinh;
import com.iesvn.yte.entity.DmXa;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B302_Tomtatbenhan")
@Synchronized(timeout = 6000000)
public class B302_TomTatBenhAnAction implements Serializable{

	private static final long serialVersionUID = 8711728630888663422L;
	private static Logger log = Logger.getLogger(B302_TomTatBenhAnAction.class);
	
	private HsbaTomTatBenhAn ttba;

	private boolean isUpdate;
	
	private String nosuccess;
	private String nofound;
	private String nofoundHSBA;
	
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
	
	
    
	//Ngày chứng minh nhân dân
	public String ngayCMT = "";
	
	public String getNgayCMT() {
        return ngayCMT;
    }

    public void setNgayCMT(String ngayCMT) {
        this.ngayCMT = ngayCMT;
    }
    
    //Ngày cấp
    public String ngayCap = "";

    public String getNgayCap() {
        return ngayCap;
    }

    public void setNgayCap(String ngayCap) {
        this.ngayCap = ngayCap;
    }
    
    //Mã HSBA_SOVAOVIE dùng để lấy thông tin từ HSBA
    public String maHsba = "";

    public String getMaHsba() {
        return maHsba;
    }

    public void setMaHsba(String maHsba) {
        this.maHsba = maHsba;
    }
   

	
	@Begin (join = true)
	public String init() {
	
		resetValue();		
		return "DieuTri_LapVanBanTheoMau_BangTomTatBenhAn";
	}

	@End
	public void endFunc(){
		
	}
	public void resetValue() {
		log.info("---resetValue");
		
		ttba = new HsbaTomTatBenhAn();
		setInfoIfNullForHsbaTomTatBenhAn(ttba);
		
		ngayCMT = "";
		ngayCap = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		nosuccess=nofound=nofoundHSBA="false";
		isUpdate=false;
		}

	

	private void setInfoIfNullForHsbaTomTatBenhAn(HsbaTomTatBenhAn obj) {
		if (obj.getHsbaSovaovien()==null){
			Hsba _hsba = new Hsba();			
			setInfoIfNullForHsba(_hsba);
			obj.setHsbaSovaovien(_hsba);
		}
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
	
	public void displayHSBA(){
		log.info("---displayHSBA");
		Hsba hsba_tmp=null;
		maHsba = ttba.getHsbaSovaovien(true).getHsbaSovaovien();
		if (!maHsba.trim().equals("")){
				hsba_tmp = HsbaDelegate.getInstance().find(maHsba.trim());
				if (hsba_tmp==null){
					nofoundHSBA = "true";
					hsba_tmp = new Hsba();
					FacesMessages.instance().add(IConstantsRes.HSBA_NULL, maHsba);
			}
			setInfoIfNullForHsba(hsba_tmp);
			ttba.setHsbaSovaovien(hsba_tmp);
			maHsba=hsba_tmp.getHsbaSovaovien();
			
		}else{
			nofoundHSBA = "true";
			hsba_tmp = new Hsba();
			setInfoIfNullForHsba(hsba_tmp);
			ttba.setHsbaSovaovien(hsba_tmp);
		}		
	}
	
	public void displayTomTatBenhAn(){
		log.info("---displayTomTatBenhAn");
		String maGiay = ttba.getHsbattbaMa().trim();
		HsbaTomTatBenhAn ttba_tmp = null;
		if (!maGiay.equals("")){
			ttba_tmp = HsbaTomTatBenhAnDelegate.getInstance().findByHsbattbaMa(maGiay);
			if (ttba_tmp==null){
				nofound = "true";
				ttba = new HsbaTomTatBenhAn();
				FacesMessages.instance().add(IConstantsRes.TTBA_NULL, maGiay);
			} else {
				setInfoIfNullForHsbaTomTatBenhAn(ttba_tmp);
				ttba = ttba_tmp;
				if(ttba.getHsbattbaNgaycap()!=null){
					ngayCap = new SimpleDateFormat("dd/MM/yyyy").format(ttba.getHsbattbaNgaycap());
				}
				maHsba  = ttba_tmp.getHsbaSovaovien().getHsbaSovaovien();
				isUpdate = true;
			}
		}else{
			nofound = "true";
			ttba = new HsbaTomTatBenhAn();
			setInfoIfNullForHsbaTomTatBenhAn(ttba);
		}
	}
	
	public void updateChild()
	{
		BenhNhan benhNhan = BenhNhanDelegate.getInstance().find(ttba.getHsbaSovaovien(true).getBenhnhanMa(true).getBenhnhanMa());
		benhNhan.setBenhnhanCmnd(ttba.getHsbaSovaovien(true).getBenhnhanMa(true).getBenhnhanCmnd());
		RemoveUtil.removeAllNullFromBN(benhNhan);
		BenhNhanDelegate.getInstance().edit(benhNhan);
	}
	
	public void ghiNhan() throws ParseException{
		log.info("---ghiNhan");
		removeInfoIfNullForHsbaTomTatBenhAn(ttba);
		String result="";
//		if (!ngayCMT.trim().equals("")){
//			ttba.setHsbattbaNgaycmt(Utils.getDBDate("00:00", ngayCMT).getTime());
//		}
		if (!ngayCap.trim().equals("")){
			ttba.setHsbattbaNgaycap(Utils.getDBDate("00:00", ngayCap).getTime());
		}
		ttba.setHsbattbaNgaygiocn(new Date());
		
		String maGiay = ttba.getHsbattbaMa().trim();
		if (!maGiay.equals("")){
			isUpdate = true;
		}
		
		System.out.println("Magiay = "+maGiay);
		System.out.println("IsUpdate = "+isUpdate);
		
		if (isUpdate){
			result = HsbaTomTatBenhAnDelegate.getInstance().update(ttba);
			if (result == null || result.equals("")){
				nosuccess = "true";
				FacesMessages.instance().add(IConstantsRes.TTBA_CN_THATBAI);
			}else{
				ttba.setHsbattbaMa(result);	
				setInfoIfNullForHsbaTomTatBenhAn(ttba);
				FacesMessages.instance().add(IConstantsRes.TTBA_CN_THANHCONG, result);
				updateChild();
			}
		}else{
			result = HsbaTomTatBenhAnDelegate.getInstance().insert(ttba);
			if (result == null || result.equals("")){
				nosuccess = "true";
				FacesMessages.instance().add(IConstantsRes.TTBA_LT_THATBAI);
			}else{
				ttba.setHsbattbaMa(result);
				setInfoIfNullForHsbaTomTatBenhAn(ttba);
				FacesMessages.instance().add(IConstantsRes.TTBA_LT_THANHCONG, result);
				updateChild();
			}
		}
//		System.out.println("Maphieu sau khi create, update"+result);
//		System.out.println("Maphieu sau khi create, update"+ttba.getHsbattbaMa());
//		System.out.println("Maso sau khi create, update"+ttba.getHsbattbaMaso());
		if(result!=null && !result.equals("")){
			ttba = HsbaTomTatBenhAnDelegate.getInstance().findByHsbattbaMa(result);
//			System.out.println("Maso sau khi create, update"+ttba.getHsbattbaMaso());
			maGiay = result;
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
		loaiBCDT="Bangtomtatbenhan";
		log.info("Vao Method XuatReport giay chuyen vien");
		String baocao1=null;
		String pathTemplate = "";
		String sub1Template = "";
		String sub2Template = "";
		try {
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/Bangtomtatbenhan.jrxml";
			sub1Template  = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/Bangtomtatbenhan_sub1.jrxml";
			sub2Template  = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/Bangtomtatbenhan_sub2.jrxml";
			
			log.info("Da thay file template: " + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Map<String, Object> params = new HashMap<String, Object>();
			JasperReport rpt1 = JasperCompileManager.compileReport(sub1Template);
			JasperReport rpt2 = JasperCompileManager.compileReport(sub2Template);
			params.put("sub1"	, rpt1);
			params.put("sub2"	, rpt2);
			params.put("BENHVIEN_HEADER", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL);
//			params.put("NGAYCAP", ttba.getHsbattbaNgaycap());
			params.put("KHOADANGDT", ttba.getHsbaSovaovien(true).getHsbaKhoadangdt(true).getDmkhoaTen());
			HsbaChuyenMon hsbaCm = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_MaKhoa(ttba.getHsbaSovaovien(true).getHsbaSovaovien(), ttba.getHsbaSovaovien(true).getHsbaKhoadangdtCm(true).getDmkhoaMa()); 
			//	HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_LastHSBACM(ttba.getHsbaSovaovien(true).getHsbaSovaovien());
			if (hsbaCm != null) {
				
				params.put("BUONG", hsbaCm.getHsbacmSobuong());
				//params.put("KHOADANGDT",hsbaCm.getKhoaMa(true).getDmkhoaTen());
				params.put("BACSI", hsbaCm.getHsbacmBacsi(true).getDtdmnhanvienTen());
				params.put("CHUANDOAN_KHOA", hsbaCm.getHsbacmBenhchinh(true).getDmbenhicdTen());
				
				if (hsbaCm.getKetquaMa() != null) {
					if(hsbaCm.getKetquaMa().getDmkqdtMaso()==1){
						params.put("DIEUTRI_KHOI","X");
					}
					if(hsbaCm.getKetquaMa(true).getDmkqdtMaso()==3){
						params.put("DIEUTRI_KHONGDO","X");
					}
					if(hsbaCm.getKetquaMa(true).getDmkqdtMaso()==4){
						params.put("DIEUTRI_NANGTHEM","X");
					}
				}
				
				if(hsbaCm.getHsbacmHuongdieutri()!=null){
					if(hsbaCm.getHsbacmHuongdieutri().equals("2")){
						params.put("CHUYENVIEN_TUYENSAU","X");
						HsbaChuyenVien cv = HsbaChuyenVienDelegate.getInstance().findBySoVaoVien(ttba.getHsbaSovaovien(true).getHsbaSovaovien());
						if(cv!=null){
							params.put("CHUYENVIEN_TINHTRANG",cv.getHsbacvTinhtrangnguoibenh());
							params.put("CHUYENVIEN_GIOXUATPHAT", cv.getHsbacvNgagiochvien());
							params.put("CHUYENVIEN_PHUONGTIEN", cv.getHsbacvPhuongtienvanchuyen());
						}
					}
					if(hsbaCm.getHsbacmHuongdieutri().equals("3")){
						HsbaGiayRaVien grv= HsbaGiayRaVienDelegate.getInstance().findBySoVaoVien(ttba.getHsbaSovaovien(true).getHsbaSovaovien());
						if(grv != null) {
							if (grv.getHsbagrvLoidan()!= null) {
								params.put("RAVIEN_LOIDAN","X");
							}
							if (grv.getHsbagrvTaikham() != null){
								params.put("RAVIEN_NGAYTAIKHAM", grv.getHsbagrvTaikham().intValue() + "");
							}
						}
					}
				}
				
			}
			params.put("GIAMDOC", IConstantsRes.REPORT_DIEUTRI_GIAMDOC_BV);
			System.out.println("ma giay "+ttba.getHsbattbaMa());
			params.put("MaGiay",ttba.getHsbattbaMa());
			
			
			System.out.println("MaGiay = "+ttba.getHsbattbaMa());
			Class.forName("oracle.jdbc.OracleDriver");
		    
			log.info("da thay driver mysql");
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,
						IConstantsRes.DATABASE_USER,
						IConstantsRes.DATABASE_PASS);
			} catch (Exception e) {
				log.info(e.getMessage());
			}
			jasperPrintDT = JasperFillManager.fillReport(jasperReport, params,
					conn);
			baocao1 = XuatReportUtil.ReportUtil(jasperPrintDT, index,
					IConstantsRes.PATH_BASE
							+ IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI
							+ "hsba/", "pdf", "TomTatBenhAnnamvien");
			duongdanStrDT = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
			log.info("duong dan file xuat report :" + baocao1);
			log.info("duong dan -------------------- :" + duongdanStrDT);
			index += 1;
			log.info("Index :" + getIndex());
			if (conn != null)
				conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Thoat Method XuatReport");
	    log.info("Thoat Method XuatReport");
	}
	private static long daysBetween(Date d1, Date d2)
	{
		if(d1==null) d1=new Date();
		if(d2==null) d2=new Date();
		return ((d2.getTime() - d1.getTime() + ONE_HOUR) / (ONE_HOUR * 24));
	}
	private static final long ONE_HOUR = 60 * 60 * 1000L;
	private void removeInfoIfNullForHsbaTomTatBenhAn(HsbaTomTatBenhAn obj) {
		if (Utils.reFactorString(obj.getHsbattbaBacsi(true).getDtdmnhanvienMa()).equals(""))
			obj.setHsbattbaBacsi(null);
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

	public HsbaTomTatBenhAn getTtba() {
		return ttba;
	}

	public void setTtba(HsbaTomTatBenhAn ttba) {
		this.ttba = ttba;
	}

	public String getNosuccess() {
		return nosuccess;
	}

	public void setNosuccess(String nosuccess) {
		this.nosuccess = nosuccess;
	}

	public String getNofound() {
		return nofound;
	}

	public void setNofound(String nofound) {
		this.nofound = nofound;
	}

	public String getNofoundHSBA() {
		return nofoundHSBA;
	}

	public void setNofoundHSBA(String nofoundHSBA) {
		this.nofoundHSBA = nofoundHSBA;
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

	public String getResultReportName() {
		return resultReportName;
	}

	public void setResultReportName(String resultReportName) {
		this.resultReportName = resultReportName;
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

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	
}

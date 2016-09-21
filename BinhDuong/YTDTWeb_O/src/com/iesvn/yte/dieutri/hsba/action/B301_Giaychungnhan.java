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
import com.iesvn.yte.dieutri.delegate.HsbaGiayChungNhanDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.HsbaGiayChungNhan;
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
@Name("B301_Giaychungnhan")
@Synchronized(timeout = 6000000)
public class B301_Giaychungnhan implements Serializable{

	private static final long serialVersionUID = 8711728630888663422L;
	private static Logger log = Logger.getLogger(B301_Giaychungnhan.class);
	
	private HsbaGiayChungNhan gcn;

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
	
	public HsbaGiayChungNhan getGcn() {
        return gcn;
    }

    public void setGcn(HsbaGiayChungNhan gcn) {
        this.gcn = gcn;
    }
    
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
		return "DieuTri_LapVanBanTheoMau_GiayChungNhan";
	}

	@End
	public void endFunc(){
		
	}
	public void resetValue() {
		log.info("---resetValue");
		
		gcn = new HsbaGiayChungNhan();
		setInfoIfNullForHsbaGiayChungNhan(gcn);
		
		ngayCMT = "";
		ngayCap = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		//cm = new HsbaChuyenMon();
		//setInfoIfNullForHsbaChuyenMon(cm);
		nosuccess=nofound=nofoundHSBA="false";
		isUpdate=false;
		}

	

	private void setInfoIfNullForHsbaGiayChungNhan(HsbaGiayChungNhan obj) {
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
		maHsba = gcn.getHsbaSovaovien().getHsbaSovaovien();
		gcn = new HsbaGiayChungNhan();
		if (!maHsba.trim().equals("")){
				hsba_tmp = HsbaDelegate.getInstance().find(maHsba.trim());
				if (hsba_tmp==null){
					nofoundHSBA = "true";
					hsba_tmp = new Hsba();
					FacesMessages.instance().add(IConstantsRes.HSBA_NULL, maHsba);
				}else{
					HsbaGiayChungNhan gcn_tmp = HsbaGiayChungNhanDelegate.getInstance().findBySoVaoVien(hsba_tmp.getHsbaSovaovien());
					if (gcn_tmp!= null){
						gcn = gcn_tmp;
					}	
				}
			setInfoIfNullForHsba(hsba_tmp);
			gcn.setHsbaSovaovien(hsba_tmp);
			maHsba=hsba_tmp.getHsbaSovaovien();
			
		}else{
			nofoundHSBA = "true";
			hsba_tmp = new Hsba();
			setInfoIfNullForHsba(hsba_tmp);
			gcn.setHsbaSovaovien(hsba_tmp);
		}		
	}
	
	public void displayGiayChungNhan(){
		log.info("---displayGiaychungnhan");
		String maGiay = gcn.getHsbagcnMa().trim();
		HsbaGiayChungNhan gcn_tmp = null;
		if (!maGiay.equals("")){
			gcn_tmp = HsbaGiayChungNhanDelegate.getInstance().findByHsbagcnMa(maGiay);
			if (gcn_tmp==null){
				nofound = "true";
				gcn_tmp = new HsbaGiayChungNhan();
				FacesMessages.instance().add(IConstantsRes.GCN_NULL, maGiay);
			}
			
			setInfoIfNullForHsbaGiayChungNhan(gcn_tmp);
			gcn = gcn_tmp;
			if(gcn.getHsbagcnNgaycap()!=null){
				ngayCap = new SimpleDateFormat("dd/MM/yyyy").format(gcn.getHsbagcnNgaycap());
			}
			if(gcn.getHsbagcnNgaycmt()!=null){
				ngayCMT = new SimpleDateFormat("dd/MM/yyyy").format(gcn.getHsbagcnNgaycmt());
			}
			
			maHsba  = gcn_tmp.getHsbaSovaovien().getHsbaSovaovien();
			isUpdate = true;
		}else{
			nofound = "true";
			gcn = new HsbaGiayChungNhan();
			setInfoIfNullForHsbaGiayChungNhan(gcn);
		}
	}
	
	public void updateChild()
	{
		BenhNhan benhNhan = BenhNhanDelegate.getInstance().find(gcn.getHsbaSovaovien(true).getBenhnhanMa(true).getBenhnhanMa());
		benhNhan.setBenhnhanCmnd(gcn.getHsbaSovaovien(true).getBenhnhanMa(true).getBenhnhanCmnd());
		RemoveUtil.removeAllNullFromBN(benhNhan);
		BenhNhanDelegate.getInstance().edit(benhNhan);
	}
	
	public void ghiNhan() throws ParseException{
		log.info("---ghiNhan");
		removeInfoIfNullForHsbaGiayChungNhan(gcn);
		String result="";
		if (!ngayCMT.trim().equals("")){
			gcn.setHsbagcnNgaycmt(Utils.getDBDate("00:00", ngayCMT).getTime());
		}
		if (!ngayCap.trim().equals("")){
			gcn.setHsbagcnNgaycap(Utils.getDBDate("00:00", ngayCap).getTime());
		}
		gcn.setHsbagcnNgaygiocn(new Date());
		
		String maGiay = gcn.getHsbagcnMa().trim();
		if (!maGiay.equals("")){
			isUpdate = true;
		}
		
		System.out.println("Magiay = "+maGiay);
		System.out.println("IsUpdate = "+isUpdate);
		
		if (isUpdate){
			result = HsbaGiayChungNhanDelegate.getInstance().update(gcn);
			if (result == null || result.equals("")){
				nosuccess = "true";
				FacesMessages.instance().add(IConstantsRes.GCN_CN_THATBAI);
			}else{
				gcn.setHsbagcnMa(result);	
				setInfoIfNullForHsbaGiayChungNhan(gcn);
				FacesMessages.instance().add(IConstantsRes.GCN_CN_THANHCONG, result);
				updateChild();
			}
		}else{
			result = HsbaGiayChungNhanDelegate.getInstance().insert(gcn);
			if (result == null || result.equals("")){
				nosuccess = "true";
				FacesMessages.instance().add(IConstantsRes.GCN_LT_THATBAI);
			}else{
				gcn.setHsbagcnMa(result);
				setInfoIfNullForHsbaGiayChungNhan(gcn);
				FacesMessages.instance().add(IConstantsRes.GCN_LT_THANHCONG, result);
				updateChild();
			}
		}
		System.out.println(""+gcn.getHsbagcnMaso());
		if(result!=null && !result.equals("")){
			gcn = HsbaGiayChungNhanDelegate.getInstance().findByHsbagcnMa(result);
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
		loaiBCDT="GiayChungNhan";
		log.info("Vao Method XuatReport giay chuyen vien");
		String baocao1=null;
		String pathTemplate=null;
		try {
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/giaychungnhannamvien.jrxml";
			log.info("Da thay file template: " + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put("BENHVIEN_HEADER", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL);
			params.put("NGAYCAP", gcn.getHsbagcnNgaycap());
			HsbaChuyenMon hsbaCm = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_MaKhoa(gcn.getHsbaSovaovien(true).getHsbaSovaovien(), gcn.getHsbaSovaovien(true).getHsbaKhoadangdtCm(true).getDmkhoaMa()); 
			
			if(hsbaCm!=null){
				params.put("BUONG", hsbaCm.getHsbacmSobuong());
				//params.put("KHOADANGDT",hsbaCm.getKhoaMa(true).getDmkhoaTen());
				params.put("BACSI", hsbaCm.getHsbacmBacsi(true).getDtdmnhanvienTen());
			}
			params.put("KHOADANGDT", gcn.getHsbaSovaovien(true).getHsbaKhoadangdt(true).getDmkhoaTen());
			params.put("GIAMDOC", IConstantsRes.REPORT_DIEUTRI_GIAMDOC_BV);
			params.put("MaGiay",gcn.getHsbagcnMa());
			System.out.println("MaGiay = "+gcn.getHsbagcnMa());
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
							+ "hsba/", "pdf", "giaychungnhannamvien");
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
	private void removeInfoIfNullForHsbaGiayChungNhan(HsbaGiayChungNhan obj) {
		if (Utils.reFactorString(obj.getHsbagcnBacsi(true).getDtdmnhanvienMa()).equals(""))
			obj.setHsbagcnBacsi(null);
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

	public HsbaGiayChungNhan getGcv() {
		return gcn;
	}

	public void setGcv(HsbaGiayChungNhan gcn) {
		this.gcn = gcn;
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

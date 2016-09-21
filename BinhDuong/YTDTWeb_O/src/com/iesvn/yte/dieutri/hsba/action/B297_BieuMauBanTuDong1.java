package com.iesvn.yte.dieutri.hsba.action;

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

import org.apache.commons.collections.set.CompositeSet.SetMutator;
import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.session.DmBenhIcdFacade;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;

import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;

import com.iesvn.yte.entity.DmBenhIcd;
import com.iesvn.yte.entity.DmDanToc;
import com.iesvn.yte.entity.DmGioiTinh;
import com.iesvn.yte.entity.DmHuyen;
import com.iesvn.yte.entity.DmNgheNghiep;
import com.iesvn.yte.entity.DmTinh;
import com.iesvn.yte.entity.DmXa;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B297_BieuMauBanTuDong1")
@Synchronized(timeout = 6000000)
public class B297_BieuMauBanTuDong1 implements Serializable{

	private static final long serialVersionUID = -5106695460338085910L;
	private static Logger log = Logger.getLogger(B297_BieuMauBanTuDong1.class);
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	private Hsba hsba;
	private String nosuccess;
	private String nofound;
	private String nofoundHSBA;
	private String crrDate;
	private boolean isUpdate; 
	
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
	private String ngayCap;
	private String ngayCX;
	private String resultReportFileName;
	private String resultReportName;	
	private String loaiFileXuat;
	@Out(required = false)
	private String isReport = "false";
	
	@Restrict("#{s:hasRole('NV_KhoaNoiTru') or s:hasRole('QT_KhoaNoiTru')}")
	@Begin (join = true)
	public String init() {	
		resetValue();	
		return "DieuTri_LapVanBanTheoMau_BieuMauBanTuDong1";
	}

	public Hsba getHsba(){
		return hsba;
	}
	public void setHsba(Hsba hsba){
		this.hsba = hsba;
	}
	private String gioCX;

    public String getGioCX() {
        return gioCX;
    }

    public void setGioCX(String gioCX) {
        this.gioCX = gioCX;
    }
    
    private String gioVV;
    private String ngayVV;
    
    public String getGioVV() {
        return gioVV;
    }

    public void setGioVV(String gioVV) {
        this.gioVV = gioVV;
    }

    public String getNgayVV() {
        return ngayVV;
    }

    public void setNgayVV(String ngayVV) {
        this.ngayVV = ngayVV;
    }
    
    private int chuandoanMaso;
    private String chuandoanMa="";
    private String chuandoanName="";
	
    public String getChuandoanMa() {
        return chuandoanMa;
    }

    public void setChuandoanMa(String chuandoanMa) {
        this.chuandoanMa = chuandoanMa;
    }

    public int getChuandoanMaso() {
        return chuandoanMaso;
    }

    public void setChuandoanMaso(int chuandoanMaso) {
        this.chuandoanMaso = chuandoanMaso;
    }

    public String getChuandoanName() {
        return chuandoanName;
    }

    public void setChuandoanName(String chuandoanName) {
        this.chuandoanName = chuandoanName;
    }
    
    
    
    
	@End
	public void endFunc(){
		
	}
	
	
	public void resetValue() {
		log.info("---resetValue");
		hsba = new Hsba();	
		crrDate= ngayCap = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		nosuccess=nofound=nofoundHSBA="false";
		isUpdate=false;
	}
	
	

	public void displayHSBA(){
		log.info("---displayHSBAquang");
		String maHsba = hsba.getHsbaSovaovien().trim();
		log.info("maHsba = "+maHsba);
		Hsba hsba_tmp=null;
		if (!maHsba.equals("")){
			hsba_tmp = HsbaDelegate.getInstance().find(maHsba);
			if (hsba_tmp==null){
				nofoundHSBA = "true";				
				hsba_tmp = new Hsba();
				FacesMessages.instance().add(IConstantsRes.HSBA_NULL, maHsba);
				hsba_tmp = new Hsba();
				setInfoIfNullForHsba(hsba_tmp);
				hsba = hsba_tmp;
			}else{
				hsba = hsba_tmp;
			}
			
			//System.out.println("chuandoanName = "+chuandoanName);
		}else{
			nofoundHSBA = "true";			
			hsba = new Hsba();
			setInfoIfNullForHsba(hsba);
			
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
	
	private String loaiBaoCao = "";
	
	public String getLoaiBaoCao() {
        return loaiBaoCao;
    }

    public void setLoaiBaoCao(String loaiBaoCao) {
        this.loaiBaoCao = loaiBaoCao;
    }
	
	public String thuchienAction(){
		XuatReport();
		return "B260_Chonmenuxuattaptin";
	}
	private String sobuong = "";
	private String sogiuong = "";

	public String getSobuong() {
		return sobuong;
	}

	public void setSobuong(String sobuong) {
		if(sobuong!=null)
		this.sobuong = sobuong;
	}

	public String getSogiuong() {
		return sogiuong;
	}

	public void setSogiuong(String sogiuong) {
		if(sogiuong!=null)
		this.sogiuong = sogiuong;
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		loaiBCDT="BieuMauBanTuDong1";
		log.info("Vao Method XuatReport giay chuyen xac");
		String baocao1=null;
		String pathTemplate=null;
		loaiBaoCao="1";
		try {
			System.out.println("loaibaocao = "+loaiBaoCao);
			
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/Bieumaubantudong_sub"+loaiBaoCao+".jrxml";
			log.info("da thay file templete " + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			// log.info(String.format("-----tenSo: %s", params.get("tenSo")));
			params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL);
			params.put("tenDonViTat", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			
			String diachistr = "";
			String hinh1 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/Bieumaubantudong_sub"+loaiBaoCao+".jpg";
			params.put("HINH1", hinh1);
			if (hsba.getBenhnhanMa().getBenhnhanDiachi() != null)
				diachistr += hsba.getBenhnhanMa().getBenhnhanDiachi()
						+ ", ";
			if (hsba.getBenhnhanMa().getXaMa(true).getDmxaTen() != null)
				diachistr += hsba.getBenhnhanMa().getXaMa(true)
						.getDmxaTen()
						+ ", ";
			if (hsba.getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() != null)
				diachistr += hsba.getBenhnhanMa().getHuyenMa(true)
						.getDmhuyenTen()
						+ ", ";
			if (hsba.getBenhnhanMa().getTinhMa(true).getDmtinhTen() != null)
				diachistr += hsba.getBenhnhanMa().getTinhMa(true)
						.getDmtinhTen();
			params.put("DIACHI", diachistr);
			
			try {
				if (hsba.getHsbaKhoadangdtCm(true).getDmkhoaMaso() != null) {
					HsbaChuyenMonDelegate hsbaChuyenMonDelegate = HsbaChuyenMonDelegate.getInstance();
					HsbaChuyenMon hsbaChuyenMon = hsbaChuyenMonDelegate.findBySoVaoVien_MaKhoa(hsba.getHsbaSovaovien(), hsba.getHsbaKhoadangdtCm(true).getDmkhoaMa());
					
					if(hsbaChuyenMon != null){
						setSobuong(hsbaChuyenMon.getHsbacmSobuong());
						setSogiuong(hsbaChuyenMon.getHsbacmSogiuong());
						String chandoan = "";
						chandoan += hsbaChuyenMon.getHsbacmBenhchinh() == null ? "" : hsbaChuyenMon.getHsbacmBenhchinh(true).getDmbenhicdTen();
						chandoan += hsbaChuyenMon.getHsbacmBenhphu() == null ? "" : ", " + hsbaChuyenMon.getHsbacmBenhphu(true).getDmbenhicdTen();
						chandoan += hsbaChuyenMon.getHsbacmBenhphu2() == null ? "" : ", " + hsbaChuyenMon.getHsbacmBenhphu2(true).getDmbenhicdTen();
						chandoan += hsbaChuyenMon.getHsbacmBenhphu3() == null ? "" : ", " + hsbaChuyenMon.getHsbacmBenhphu3(true).getDmbenhicdTen();
						chandoan += hsbaChuyenMon.getHsbacmBenhphu4() == null ? "" : ", " + hsbaChuyenMon.getHsbacmBenhphu4(true).getDmbenhicdTen();
						chandoan += hsbaChuyenMon.getHsbacmBenhphu5() == null ? "" : ", " + hsbaChuyenMon.getHsbacmBenhphu5(true).getDmbenhicdTen();
						params.put("CHANDOAN", chandoan);
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			params.put("BUONG", getSobuong());
			params.put("GIUONG", getSogiuong());
			if (hsba.getHsbaKhoadangdt().getDmkhoaTen() != null)
				params.put("KHOA_HIENTAI", hsba.getHsbaKhoadangdt()
						.getDmkhoaTen());
			else
				params.put("KHOA_HIENTAI", "");
			
			params.put("gioiTinh",  hsba.getBenhnhanMa().getDmgtMaso(true).getDmgtTen());
			
			params.put("SOVAOVIEN", hsba.getHsbaSovaovien());
			
			log.info("Sovaovien = "+hsba.getHsbaSovaovien());
			
			
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintDT,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"hsba/", "pdf","BieuMauBanTuDong1");
			    duongdanStrDT=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+duongdanStrDT);
			    index+= 1;
			    log.info("Index :" + getIndex());
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Thoat Method XuatReport");
	}
	
	public String formatDate(Date date){
		return date==null?"":new SimpleDateFormat("dd/MM/yyyy").format(date);
	}
		
	public String formatDateTime(Date date){
		System.out.println("qdate = "+(date==null?"isnull":date.toString()));
		return date==null?"":Utils.getGioPhut(date);
	}
	
	public String formatGtBenhNhan(String gioitinh){
		if (gioitinh==null || gioitinh.trim().equals("")){
			return "";
		}
		return gioitinh.equals("1")?"true":"false";
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

	public String getCrrDate() {
		return crrDate;
	}

	public void setCrrDate(String crrDate) {
		this.crrDate = crrDate;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}

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
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaGiayChuyenXacDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.HsbaGiayChuyenXac;
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
@Name("B290_Giaychuyenxac")
@Synchronized(timeout = 6000000)
public class GiayChuyenXacAction implements Serializable{

	private static final long serialVersionUID = -5106695460338085910L;
	private static Logger log = Logger.getLogger(GiayChuyenXacAction.class);
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	private HsbaGiayChuyenXac gcx;
	private String ngayCap;
	private String ngayCX;
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
	
	private String resultReportFileName;
	private String resultReportName;	
	private String loaiFileXuat;
	@Out(required = false)
	private String isReport = "false";
	
	@Restrict("#{s:hasRole('NV_KhoaNoiTru') or s:hasRole('QT_KhoaNoiTru')}")
	@Begin (join = true)
	public String init() {	
		resetValue();	
		return "DieuTri_LapVanBanTheoMau_GiayChuyenXac";
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
		gcx = new HsbaGiayChuyenXac();	
		crrDate= ngayCap = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		nosuccess=nofound=nofoundHSBA="false";
		isUpdate=false;
	}
	
	

	public void displayHSBA(){
		log.info("---displayHSBAquang");
		String maHsba = gcx.getHsbaSovaovien().getHsbaSovaovien().trim();
		Hsba hsba_tmp=null;
		if (!maHsba.equals("")){
			hsba_tmp = HsbaDelegate.getInstance().find(maHsba);
			if (hsba_tmp==null){
				nofoundHSBA = "true";				
				hsba_tmp = new Hsba();
				FacesMessages.instance().add(IConstantsRes.HSBA_NULL, maHsba);
				hsba_tmp = new Hsba();
				setInfoIfNullForHsba(hsba_tmp);
				gcx.setHsbaSovaovien(hsba_tmp);
				setNgayVV("");
				setGioVV("");
				setChuandoanMa("");
				setChuandoanMaso(0);
				setChuandoanName("");
				
			}else{
				gcx.setHsbaSovaovien(hsba_tmp);
				
				setNgayVV(formatDate(hsba_tmp.getHsbaNgaygiovaov()));
				setGioVV(formatDateTime(hsba_tmp.getHsbaNgaygiovaov()));
				setChuandoanMa(hsba_tmp.getHsbaTuvong(true).getDmbenhicdMa());
				if(hsba_tmp.getHsbaTuvong(true).getDmbenhicdMaso()!=null){
					setChuandoanMaso(hsba_tmp.getHsbaTuvong(true).getDmbenhicdMaso());
				}
				setChuandoanName(hsba_tmp.getHsbaTuvong(true).getDmbenhicdTen());
			}
			
			//System.out.println("chuandoanName = "+chuandoanName);
		}else{
			nofoundHSBA = "true";			
			hsba_tmp = new Hsba();
			setInfoIfNullForHsba(hsba_tmp);
			gcx.setHsbaSovaovien(hsba_tmp);
			setNgayVV("");
			setGioVV("");
			setChuandoanMa("");
			setChuandoanMaso(0);
			setChuandoanName("");
		}		
		System.out.println("Ma so = "+getChuandoanMaso());
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
	
	public void displayGiayChuyenXac(){
		log.info("---displayGiayChuyenXac");
		String maGcx = gcx.getHsbagcxMa().trim();
		HsbaGiayChuyenXac gcx_tmp = null;
		if (!maGcx.equals("")){
			gcx_tmp = HsbaGiayChuyenXacDelegate.getInstance().findByHsbagcxMa(maGcx);
			if (gcx_tmp==null){
				nofound = "true";
				crrDate= ngayCap = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
				gcx_tmp = new HsbaGiayChuyenXac();
				FacesMessages.instance().add(IConstantsRes.GCX_NULL, maGcx);
			}	
			gcx = gcx_tmp;
			
			setNgayVV(formatDate(gcx.getHsbaSovaovien(true).getHsbaNgaygiovaov()));
			setGioVV(formatDateTime(gcx.getHsbaSovaovien(true).getHsbaNgaygiovaov()));
			setChuandoanMa(gcx.getHsbaSovaovien(true).getHsbaTuvong(true).getDmbenhicdMa());
			if(gcx.getHsbaSovaovien(true).getHsbaTuvong(true).getDmbenhicdMaso()!=null){
				setChuandoanMaso(gcx.getHsbaSovaovien(true).getHsbaTuvong(true).getDmbenhicdMaso());
			}
			setNgayCX(formatDate(gcx.getHsbagcxNgaycx()));
			setGioCX(formatDateTime(gcx.getHsbagcxNgaycx()));
			setChuandoanName(gcx.getHsbaSovaovien(true).getHsbaTuvong(true).getDmbenhicdTen());
			
			isUpdate = true;
		}else{
			nofound = "true";
			crrDate= ngayCap = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
			gcx = new HsbaGiayChuyenXac();
		}
	}
	
	public String getdmBenhIcdMaCX(){
		return gcx.getHsbaSovaovien(true).getHsbaTuvong(true).getDmbenhicdMa();
	}
	
	public String getdmBenhIcdTenCX(){
		return gcx.getHsbaSovaovien(true).getHsbaTuvong(true).getDmbenhicdTen();
	}
	
	public void ghiNhan(){
		log.info("---ghiNhan");
		String result="";
		if (!ngayCap.trim().equals("")){
			gcx.setHsbagcxNgaygiocn(Utils.getDBDate("00:00", ngayCap).getTime());
		}
		if (!ngayCX.trim().equals("")){
			if(gioCX.trim().equals("")){
				gcx.setHsbagcxNgaycx(Utils.getDBDate("00:00", ngayCX).getTime());
			}else
			{
				gcx.setHsbagcxNgaycx(Utils.getDBDate(gioCX, ngayCX).getTime());
			}
		}
		if (!ngayVV.trim().equals("")){
			if(gioVV.trim().equals("")){
				gcx.setHsbagcxNgayvaovien(Utils.getDBDate("00:00", ngayVV).getTime());
			}else
			{
				gcx.setHsbagcxNgayvaovien(Utils.getDBDate(gioVV, ngayVV).getTime());
			}
		}
		gcx.setHsbagcxNgaygiocn(new Date());
		gcx.setNhanvienMa(DtDmNhanVienDelegate.getInstance().findByNd(identity.getUsername()));
		DmBenhIcd dmbTmp = new DmBenhIcd();
		System.out.println("chuan doan ma so = "+getChuandoanMaso());
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			//System.out.println("chuandoanma = "+getChuandoanMa());
//			dmbTmp = (DmBenhIcd)dtutilDelegate.findByMaSo(getChuandoanMaso(), "DmBenhIcd", "dmbenhicdMaso");
			dmbTmp = (DmBenhIcd)dtutilDelegate.findByMa(getChuandoanMa(), "DmBenhIcd", "dmbenhicdMa");
			//System.out.println("Maso tim duoc = "+dmbTmp.getDmbenhicdMaso());
			gcx.setHsbagcxChuandoanma(dmbTmp);
		} catch (Exception ex) {
			System.out.println("Loi tai "+ex.toString());
		}
		if (isUpdate){
			result = HsbaGiayChuyenXacDelegate.getInstance().update(gcx);
			if (result == null || result.equals("")){
				nosuccess = "true";
				FacesMessages.instance().add(IConstantsRes.GCX_CN_THATBAI);
			}else{
				gcx.setHsbagcxMa(result);			
				FacesMessages.instance().add(IConstantsRes.GCX_CN_THANHCONG, result);
			}
		}else{
			result = HsbaGiayChuyenXacDelegate.getInstance().insert(gcx);
			if (result == null || result.equals("")){
				nosuccess = "true";
				FacesMessages.instance().add(IConstantsRes.GCX_LT_THATBAI);
			}else{
				gcx.setHsbagcxMa(result);			
				FacesMessages.instance().add(IConstantsRes.GCX_LT_THANHCONG, result);
			}
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
		loaiBCDT="GiayChuyenXac";
		log.info("Vao Method XuatReport giay chuyen xac");
		String baocao1=null;
		String pathTemplate=null;
		try {
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/Giaychuyenxac.jrxml";
			log.info("da thay file templete 5" + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			//log.info(String.format("-----tenSo: %s", params.get("tenSo")));
			params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			//log.info(String.format("-----tenDonVi: %s", params.get("tenDonVi")));
			params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			//log.info(String.format("-----dienThoaiDonVi: %s", params.get("dienThoaiDonVi")));
			params.put("khoaRav", gcx.getHsbaSovaovien(true).getHsbaKhoarav(true).getDmkhoaTen());
			//log.info(String.format("-----khoa ra vien: %s", params.get("khoaRav")));
			params.put("GIAMDOC", IConstantsRes.REPORT_DIEUTRI_GIAMDOC_BV);
			
			params.put("magiay", gcx.getHsbagcxMa());
			log.info(gcx.getHsbagcxMa());
			
			// 20101111 bao.ttc: them cac thong tin khac
			if(gcx.getHsbaSovaovien() != null){
				
				Hsba hsba = gcx.getHsbaSovaovien();
				String diachistr="";
				if(hsba.getBenhnhanMa().getBenhnhanDiachi() != null)
					diachistr += hsba.getBenhnhanMa().getBenhnhanDiachi() + ", ";
				if(hsba.getBenhnhanMa().getXaMa(true).getDmxaTen() != null)
					diachistr += hsba.getBenhnhanMa().getXaMa(true).getDmxaTen() + ", ";
				if(hsba.getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() != null)
					diachistr += hsba.getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() + ", ";
				if(hsba.getBenhnhanMa().getTinhMa(true).getDmtinhTen() != null)
					diachistr += hsba.getBenhnhanMa().getTinhMa(true).getDmtinhTen();
				params.put("DIACHI", diachistr );
				
				HsbaBhytDelegate hsbaBHYTDelegate = HsbaBhytDelegate.getInstance();
				HsbaBhyt hsbaBHYT = hsbaBHYTDelegate.findBySoVaoVienLastHsbaBhyt(hsba.getHsbaSovaovien());
				if(hsbaBHYT != null){
					if(hsbaBHYT.getHsbabhytGiatri0() != null)
						params.put("GIATRITU", sdf.format(hsbaBHYT.getHsbabhytGiatri0()));
					if(hsbaBHYT.getHsbabhytGiatri1() != null)
						params.put("GIATRIDEN", sdf.format(hsbaBHYT.getHsbabhytGiatri1()));
					//if(hsbaBHYT.getHsbabhytSothebh() != null)
					//	params.put("SOTHEBH", hsbaBHYT.getHsbabhytSothebh());
					
					String so_bhyt = "";
					if (hsbaBHYT.getHsbabhytSothebh() != null && !hsbaBHYT.getHsbabhytSothebh().equals("")){
						so_bhyt = hsbaBHYT.getHsbabhytSothebh();
						params.put("khoiBhytMa", so_bhyt.substring(0, 2));
						params.put("khoiBhytMa1", so_bhyt.substring(2, 3));
						params.put("tinhBhytMa", so_bhyt.substring(3, 5));
						params.put("namBhyt", so_bhyt.substring(5, 7));
						params.put("maCoQuan", so_bhyt.substring(7, 10));
						if (hsbaBHYT.getHsbabhytMakcb(true).getDmbenhvienMa() != null)
							params.put("THEBHYT", so_bhyt.substring(10) + " - " + hsbaBHYT.getHsbabhytMakcb(true).getDmbenhvienMa());
						else
							params.put("THEBHYT", so_bhyt.substring(10));
					}
					
				}
				
			}
			// 20101111 bao.ttc: them cac thong tin khac -- END
			
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintDT,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"hsba/", "pdf","GiayChuyenXac");
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

	
	

	public HsbaGiayChuyenXac getGcx() {
		return gcx;
	}

	public void setGcx(HsbaGiayChuyenXac gcx) {
		this.gcx = gcx;
	}

	public String getNgayCap() {
		return ngayCap;
	}

	public void setNgayCap(String ngayCap) {
		this.ngayCap = ngayCap;
	}

	public String getNgayCX() {
        return ngayCX;
    }

    public void setNgayCX(String ngayCX) {
        this.ngayCX = ngayCX;
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

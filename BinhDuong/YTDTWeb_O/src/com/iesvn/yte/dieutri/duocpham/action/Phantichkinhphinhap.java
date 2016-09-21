/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.duocpham.action;

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

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.entity.DmNhaCungCap;
import com.iesvn.yte.util.IConstantsRes;


@Name("B4413_Phantichkinhphinhap")
@Scope(CONVERSATION)
public class Phantichkinhphinhap implements Serializable {
	@Logger
	private Log log;
	
	@Out(required=false)
	@In(required=false)
	private String reportPathKC=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeKC=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintKC = null; 
	
	@Out(required=false)
	@In(required=false)
	private String resetFormB4413=null;
	
	private String thoigian_thang=null;  
	private String thoigian_nam=null;
	private String tungay=null;
	private String denngay=null;
	private String ngayhientai;
	private int index=0;
	private String pttheo=null;
	private Integer lthuoc_maso=null;
	private Integer plthuoc_maso=null;
	private Integer kho_maso=null;
	private Integer ct_maso=null;
	private Integer kinhphi_maso=null;
	private Integer noiban_maso=null;
	private String lthuoc_ma="";
	private String plthuoc_ma="";
	private String kho_ma="";
	private String ct_ma="";
	private String kinhphi_ma="";
	private String noiban_ma="";
	
	@Begin (join = true)
	public String init() {
		log.info("init()");
		resetForm();
		return "BaoCaoDuoc_InBaoCaoKhoChinh_PhanTichKinhPhiNhap";
	}
	
	@End
	public void end(){
		
	}
	
	@Factory("resetFormB4413")
	public void initresetFormB4413() {
		log.info("init()");
		ngayhientai = com.iesvn.yte.util.Utils.getCurrentDate();
		resetForm();
	}
	
	
	
	public String thuchienAction(){
		XuatReport();
		resetFormB4413 = null;
		return "B4160_Chonmenuxuattaptin";
	}
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		thoigian_thang=String.valueOf(currentDate.getMonth() +1);  
		thoigian_nam=String.valueOf(currentDate.getYear()+1900);
		tungay = sdf.format(currentDate);
		denngay = sdf.format(currentDate);
		resetFormB4413 = "";
		setPttheo("r1");
		setLthuoc_ma("");
		setPlthuoc_ma("");
		setKho_ma("");
		setCt_ma("");
		setKinhphi_ma("");
		setNoiban_ma("");
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeKC="tinhhinhkinhphinhap";
		log.info("Vao Method XuatReport tinh hinh kinh phi nhap");
		String baocao1=null;
		String pathTemplate =null;
		try {
			log.info("bat dau method XuatReport ");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			DieuTriUtilDelegate dtutilDelegate=DieuTriUtilDelegate.getInstance();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			
			log.info("tu ngay date " + tungay);
			params.put("TUNGAYDATE", sdf.parse(tungay));
			params.put("TUNGAY", tungay);
			
			log.info("den ngay date " + denngay);
			params.put("DENNGAYDATE", sdf.parse(denngay));
			params.put("DENNGAY", denngay);
			
			log.info("truyen tham so loai thuoc " + lthuoc_maso);
			if(lthuoc_maso!=null){
				params.put("LOAITHUOCMASO", lthuoc_maso);
				DmLoaiThuoc loai=new DmLoaiThuoc();
				Object obj=dtutilDelegate.findByMa(lthuoc_ma , "DmLoaiThuoc", "dmloaithuocMa");
				loai=(DmLoaiThuoc)obj;
				params.put("PHANLOAI", loai.getDmloaithuocTen());
			}else{
				params.put("PHANLOAI", "ALL");
			}
			log.info("truyen tham so  khoa " + kho_maso);
			if(kho_maso!=null){
				params.put("KHOMASO", kho_maso);
			}
			log.info("truyen tham so  nguon chuong trinh " + ct_maso);
			if(ct_maso!=null){
				params.put("CTMASO", ct_maso);
			}
			log.info("truyen tham so  nguon kinh phi " + kinhphi_maso);
			if(kinhphi_maso!=null){
				params.put("KPMASO", kinhphi_maso);
			}
			log.info("truyen tham so  noi ban " + noiban_maso);
			if(noiban_maso!=null){
				params.put("NOIBANMASO", noiban_maso);
				DmNhaCungCap nccap=new DmNhaCungCap();
				Object obj=dtutilDelegate.findByMa(noiban_ma , "DmNhaCungCap", "dmnhacungcapMa");
				nccap=(DmNhaCungCap)obj;
				params.put("NOIBAN", nccap.getDmnhacungcapTen());
			}else{
				params.put("NOIBAN", "ALL");
			}
			if(pttheo.equals("r1")){
				log.info("Theo nha cung cap");
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D18_phantichkinhphinhap_nhasx.jrxml";
			}else if(pttheo.equals("r2")){
				log.info("Theo nguon chuong trinh");
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D18_phantichkinhphinhap_nct.jrxml";
			}else if(pttheo.equals("r3")){
				log.info("Theo nguon kinh phi");
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D18_phantichkinhphinhap_nkp.jrxml";
			}
			
			log.info("da thay file templete 5" + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			log.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			    log.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintKC =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintKC,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"duocpham/", "pdf","Tinhhinhkinhphinhap");
			    reportPathKC=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathKC);
			    index+=1;
			    log.info("Index :" + getIndex());
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Thoat Method XuatReport");
	}
	
	
	public String getThoigian_thang() {
		return thoigian_thang;
	}
	public void setThoigian_thang(String thoigian_thang) {
		this.thoigian_thang = thoigian_thang;
	}
	public String getThoigian_nam() {
		return thoigian_nam;
	}
	public void setThoigian_nam(String thoigian_nam) {
		this.thoigian_nam = thoigian_nam;
	}
	public String getTungay() {
		return tungay;
	}
	public void setTungay(String tungay) {
		this.tungay = tungay;
	}
	public String getDenngay() {
		return denngay;
	}
	public void setDenngay(String denngay) {
		this.denngay = denngay;
	}
	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
	}
	public String getNgayhientai() {
		return ngayhientai;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public String getPttheo() {
		return pttheo;
	}

	public void setPttheo(String pttheo) {
		this.pttheo = pttheo;
	}

	public Integer getLthuoc_maso() {
		return lthuoc_maso;
	}

	public void setLthuoc_maso(Integer lthuoc_maso) {
		this.lthuoc_maso = lthuoc_maso;
	}

	public Integer getPlthuoc_maso() {
		return plthuoc_maso;
	}

	public void setPlthuoc_maso(Integer plthuoc_maso) {
		this.plthuoc_maso = plthuoc_maso;
	}

	public Integer getKho_maso() {
		return kho_maso;
	}

	public void setKho_maso(Integer kho_maso) {
		this.kho_maso = kho_maso;
	}

	public Integer getCt_maso() {
		return ct_maso;
	}

	public void setCt_maso(Integer ct_maso) {
		this.ct_maso = ct_maso;
	}

	public Integer getKinhphi_maso() {
		return kinhphi_maso;
	}

	public void setKinhphi_maso(Integer kinhphi_maso) {
		this.kinhphi_maso = kinhphi_maso;
	}

	public Integer getNoiban_maso() {
		return noiban_maso;
	}

	public void setNoiban_maso(Integer noiban_maso) {
		this.noiban_maso = noiban_maso;
	}

	public String getLthuoc_ma() {
		return lthuoc_ma;
	}

	public void setLthuoc_ma(String lthuoc_ma) {
		this.lthuoc_ma = lthuoc_ma;
	}

	public String getPlthuoc_ma() {
		return plthuoc_ma;
	}

	public void setPlthuoc_ma(String plthuoc_ma) {
		this.plthuoc_ma = plthuoc_ma;
	}

	public String getKho_ma() {
		return kho_ma;
	}

	public void setKho_ma(String kho_ma) {
		this.kho_ma = kho_ma;
	}

	public String getCt_ma() {
		return ct_ma;
	}

	public void setCt_ma(String ct_ma) {
		this.ct_ma = ct_ma;
	}

	public String getKinhphi_ma() {
		return kinhphi_ma;
	}

	public void setKinhphi_ma(String kinhphi_ma) {
		this.kinhphi_ma = kinhphi_ma;
	}

	public String getNoiban_ma() {
		return noiban_ma;
	}

	public void setNoiban_ma(String noiban_ma) {
		this.noiban_ma = noiban_ma;
	}
}

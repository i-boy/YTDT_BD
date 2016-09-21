/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.duocpham.action;

import static org.jboss.seam.ScopeType.SESSION;

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
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Name("B4418_Inbanglietkechungtu")
@Scope(SESSION)
public class Inbanglietkechungtu implements Serializable {
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
	private String resetFormB4418=null;
	
	private String thoigian_thang=null;  
	private String thoigian_nam=null;
	private String tungay=null;
	private String denngay=null;
	private String ngayhientai;
	private int index=0;
	private Integer lthuoc_maso=null;
	private Integer kho_maso=null;
	private String lthuoc_ma="";
	private String kho_ma="";
	private String inTheo = null;
	
	@Begin (join = true)
	public String init() {
		log.info("init()");
		initresetFormB4418();
		return "BaoCaoDuoc_InBaoCaoKhoChinh_InBangLietKeChungTu";
	}
	
	@End
	public void end(){
		
	}
	
	@Factory("resetFormB4418")
	public void initresetFormB4418() {
		log.info("init()");
		ngayhientai = Utils.getCurrentDate();
		resetForm();
	}
	
	public String thuchienAction(){
		XuatReport();
		resetFormB4418="";
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
		resetFormB4418 = "";
		setInTheo("r1");
		setLthuoc_ma("");
		setKho_ma("");
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeKC="Inbanglietkechungtu";
		log.info("Vao Method XuatReport in bang liet ke chung tu");
		String baocao1=null;
		String pathTemplate = null;
		try {
			log.info("bat dau method XuatReport ");
			if(inTheo.equals("r1"))
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D25_banlietkechungtunhapkho.jrxml";
			else if(inTheo.equals("r2"))
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D26_banglietkechungtuxuatkho.jrxml";
			
			log.info("da thay file templete 5" + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			DieuTriUtilDelegate dtutilDelegate=DieuTriUtilDelegate.getInstance();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			
			log.info("tu ngay date " + tungay);
			params.put("TUNGAY", sdf.parse(tungay));
			log.info("den ngay date " + denngay);
			params.put("DENNGAY", sdf.parse(denngay));
			
			log.info("truyen tham so loai thuoc " + lthuoc_maso);
			if(!lthuoc_ma.equals("")){
				params.put("LOAITHUOCMASO", lthuoc_maso);
				DmLoaiThuoc loai=new DmLoaiThuoc();
				Object obj=dtutilDelegate.findByMa(lthuoc_ma , "DmLoaiThuoc", "dmloaithuocMa");
				loai=(DmLoaiThuoc)obj;
				params.put("LOAITHUOCTEN", loai.getDmloaithuocTen());
			}else{
				params.put("LOAITHUOCTEN", "Tất cả");
			}
			log.info("truyen tham so  khoa " + kho_maso);
			if(!kho_ma.equals("")){
				params.put("KHOMASO", kho_maso);
			}
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

			    baocao1=XuatReportUtil.ReportUtil(jasperPrintKC,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"duocpham/", "pdf","Inbanglietkechungtu");
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

	public Integer getLthuoc_maso() {
		return lthuoc_maso;
	}

	public void setLthuoc_maso(Integer lthuoc_maso) {
		this.lthuoc_maso = lthuoc_maso;
	}

	public Integer getKho_maso() {
		return kho_maso;
	}

	public void setKho_maso(Integer kho_maso) {
		this.kho_maso = kho_maso;
	}

	public String getLthuoc_ma() {
		return lthuoc_ma;
	}

	public void setLthuoc_ma(String lthuoc_ma) {
		this.lthuoc_ma = lthuoc_ma;
	}

	public String getKho_ma() {
		return kho_ma;
	}

	public void setKho_ma(String kho_ma) {
		this.kho_ma = kho_ma;
	}

	public String getInTheo() {
		return inTheo;
	}

	public void setInTheo(String inTheo) {
		this.inTheo = inTheo;
	}
}

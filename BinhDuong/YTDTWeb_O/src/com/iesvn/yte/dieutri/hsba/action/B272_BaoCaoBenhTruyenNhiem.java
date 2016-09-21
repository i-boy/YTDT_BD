/**
 * author : HOAI NAM
 */

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

import org.jboss.seam.ScopeType;
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
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Name("B272_BaoCaoBenhTruyenNhiem")
@Scope(CONVERSATION)
public class B272_BaoCaoBenhTruyenNhiem implements Serializable {
	@Logger
	private Log log;
	
	@In(required=false)
	@Out(required=false)
	private String duongdanStrDT = null;
	
	@Out(required=false)
	@In(required=false)
	private String loaiBCDT=null;
	
	@In(required=false)
	@Out(required=false)
	JasperPrint jasperPrintDT = null;
	
	@In(scope=ScopeType.PAGE,required=false)
	@Out(scope=ScopeType.PAGE,required=false)
	private String resetFormB272=null;
		
	private String tungay=null;
	private String denngay=null;
	private String ngayhientai;
	
	private int index=0;	
	private String tieude ="";

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }
	@Factory("resetFormB272")
	public String init() {		 
		resetForm();
		return "/B2_Dieutri/B272_BaoCaoBenhTruyenNhiem.xhtml";
	}
	
	
	public String thuchienAction(){
		XuatReport();
		resetFormB272=null;
		return "B260_Chonmenuxuattaptin";
	}
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		log.info("Bat dau ham reset form");
		ngayhientai = Utils.getCurrentDate();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		resetFormB272 = "";		
		tungay = sdf.format(currentDate);
		denngay = sdf.format(currentDate);		
		log.info("ket thuc ham reset form");
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		loaiBCDT="BCBenhTruyenNhiem";
		log.info("Vao Method XuatReport BC Benh Truyen Nhiem");
		String baocao1=null;
		String pathTemplate = null;
		String sub0Template = null;
		String sub1Template = null;
		String sub2Template = null;
		try {
			
			if(IConstantsRes.CAP_TRIEN_KHAI_PHAN_MEM.equalsIgnoreCase("TINH")) {
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/BA02_BaoCaoBenhTruyenNhiem_Tinh.jrxml";
				sub0Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/BA02_BaoCaoBenhTruyenNhiem_Tinh_subreport0.jrxml";			
				sub1Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/BA02_BaoCaoBenhTruyenNhiem_Tinh_subreport1.jrxml";
				sub2Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/BA02_BaoCaoBenhTruyenNhiem_Tinh_subreport2.jrxml";
			} else {
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/BA02_BaoCaoBenhTruyenNhiem_Huyen.jrxml";
				sub0Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/BA02_BaoCaoBenhTruyenNhiem_Huyen_subreport0.jrxml";			
				sub1Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/BA02_BaoCaoBenhTruyenNhiem_Huyen_subreport1.jrxml";
				sub2Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/BA02_BaoCaoBenhTruyenNhiem_Huyen_subreport2.jrxml";
			}
			
			log.info("da thay file templete" + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			JasperReport sub0Report =JasperCompileManager.compileReport(sub0Template);
			JasperReport sub1Report =JasperCompileManager.compileReport(sub1Template);
			JasperReport sub2Report =JasperCompileManager.compileReport(sub2Template);
			log.info("da thay file template ");
			
			Map<String, Object> params = new HashMap<String, Object>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("TENBENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);						
			params.put("TUNGAY", sdf.parse(tungay));
			params.put("DENNGAY", sdf.parse(denngay));
			params.put("MAHUYEN", IConstantsRes.HUYEN_DEFAULT);
			params.put("MATINH", IConstantsRes.TINH_DEFAULT);
			params.put("sub0", sub0Report);			
			params.put("sub1", sub1Report);
			params.put("sub2", sub2Report);
			params.put("SUBREPORT_DIR", IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/");
			if(tieude.trim().equals("")){
				params.put("tieude", IConstantsRes.TU+" "+tungay+" "+IConstantsRes.DEN+" "+denngay);
			}else{
				params.put("tieude", tieude);
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
			    jasperPrintDT =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintDT,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"hsba/", "pdf","BCBenhTruyenNhiem");
			    duongdanStrDT=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+duongdanStrDT);
			    index+=1;
			    log.info("Index :" + getIndex());
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Thoat Method XuatReport");
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
	
}

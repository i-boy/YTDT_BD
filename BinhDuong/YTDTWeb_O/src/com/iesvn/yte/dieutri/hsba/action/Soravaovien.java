/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
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
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.util.IConstantsRes;

@Name("B236_Soravaovien")
@Scope(CONVERSATION)
public class Soravaovien implements Serializable {
	@Logger
	private Log log;
	
	@In(required=false)
	@Out(required=false)
	private String duongdanStrDT = null;
	
	@Out(required=false)
	@In(required=false)
	private String loaiBCDT = null;
	
	@In(required=false)
	@Out(required=false)
	JasperPrint jasperPrintDT = null;
	
	@Out(required=false)
	@In(required=false)
	private String resetFormB236=null;

	private int index=0;
	private String sovaovien_tu="";
	private String sovaovien_den="";
	
	@Factory("resetFormB236")
	public void initresetFormB236() {
		log.info("init()");
		resetForm();
	}
	
	
	@Begin (join = true)
	public String init() {
		log.info("init()");	
		resetForm();
		return "DieuTri_LapVanBanTheoMau_SoRaVaoVien";
	}
	
	

	@End
	public void end(){
		
	}
	public String thuchienAction(){
		XuatReport();
		return "B260_Chonmenuxuattaptin";
	}
	
	public void loadSoVaoVienTu() {
		log.info("-----So vao vien-----" + sovaovien_tu);
		
		if (sovaovien_tu != null && !sovaovien_tu.equals("")) {
			try {
				HsbaDelegate hsbaDAO = HsbaDelegate.getInstance();
				Hsba hsbaTmp = hsbaDAO.find(sovaovien_tu);
				if (hsbaTmp == null) {
					FacesMessages.instance().add(IConstantsRes.HSBA_NULL, sovaovien_tu);
					sovaovien_tu ="";
					return;
				}else{
					sovaovien_tu = hsbaTmp.getHsbaSovaovien();
				}	
			} catch (Exception e) {
				FacesMessages.instance().add(IConstantsRes.HSBA_NULL, sovaovien_tu);
				e.printStackTrace();
			}
		}
	}
	public void loadSoVaoVienDen() {
		log.info("-----So vao vien-----" + sovaovien_den);
		
		if (sovaovien_den != null && !sovaovien_den.equals("")) {
			try {
				HsbaDelegate hsbaDAO = HsbaDelegate.getInstance();
				Hsba hsbaTmp = hsbaDAO.find(sovaovien_den);
				if (hsbaTmp == null) {
					FacesMessages.instance().add(IConstantsRes.HSBA_NULL, sovaovien_den);
					sovaovien_den = "";
					return;
				}else{
					sovaovien_den = hsbaTmp.getHsbaSovaovien();
				}	
			} catch (Exception e) {
				FacesMessages.instance().add(IConstantsRes.HSBA_NULL, sovaovien_den);
				e.printStackTrace();
			}
		}
	}
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		sovaovien_den = "";
		sovaovien_tu = "";
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		loaiBCDT="Soravaovien";
		log.info("Vao Method XuatReport so ra vao vien");
		String baocao1=null;
		String pathTemplate=null;
		try {
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/sovaovienravien.jrxml";
			log.info("da thay file templete 5" + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			if(!sovaovien_tu.trim().equals(""))
				params.put("SOBATU", sovaovien_tu.trim());
			if(!sovaovien_den.trim().equals(""))
				params.put("SOBADEN", sovaovien_den.trim());
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintDT,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"hsba/", "pdf","Soravaovien");
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

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public String getSovaovien_tu() {
		return sovaovien_tu;
	}

	public void setSovaovien_tu(String sovaovien_tu) {
		this.sovaovien_tu = sovaovien_tu;
	}

	public String getSovaovien_den() {
		return sovaovien_den;
	}

	public void setSovaovien_den(String sovaovien_den) {
		this.sovaovien_den = sovaovien_den;
	}

}

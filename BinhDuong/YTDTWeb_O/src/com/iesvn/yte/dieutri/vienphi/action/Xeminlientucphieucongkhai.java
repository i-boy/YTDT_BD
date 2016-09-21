/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Name("B3134_Xeminlientucphieucongkhai")
@Scope(CONVERSATION)
public class Xeminlientucphieucongkhai implements Serializable {
	@Logger
	private Log log;
	
	@Out(required=false)
	@In(required=false)
	private String reportPathVP=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeVP=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintVP=null;
	
	@Out(required=false)
	@In(required=false)
	private String resetFormB3134=null;

	private int index=0;
	private String ngayhientai="";
	private String khoaMa="";
	private String ngaydungstr="";
	
	@Begin(join=true)
	public String init(){
		log.info("init()");
		initresetFormB3134();
		return "VienPhiTaiKhoa_XemInPhieu_XemInLienTucPhieuCongKhai";
	}
	
	@Factory("resetFormB3134")
	public void initresetFormB3134() {
		log.info("init()");
		ngayhientai = Utils.getCurrentDate();
		resetForm();
	}
	
	public String thuchienAction(){
		XuatReport();
		resetFormB3134=null;
		return "B3360_Chonmenuxuattaptin";
	}
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		resetFormB3134=null;
		khoaMa="";
		ngaydungstr="";
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeVP="Xeminlientucphieucongkhai";
		log.info("Vao Method XuatReport Xem in lien tuc phieu cong khai");
		String baocao1=null;
		String pathTemplate=null;
		try {
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/xeminlientucphieucongkhai.jrxml";
			log.info("da thay file templete 5" + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("NGAYDUNG", ngaydungstr);
			params.put("NGAYDUNGDATE", sdf.parse(ngaydungstr));
			if(!khoaMa.equals("")){
				DmKhoa dmkhoa = new DmKhoa();
				DieuTriUtilDelegate dtUtilDAO = DieuTriUtilDelegate.getInstance();
				Object obj = dtUtilDAO.findByMa( khoaMa , "DmKhoa" , "dmkhoaMa");
				dmkhoa = (DmKhoa)obj;
				params.put("KHOA", dmkhoa.getDmkhoaTen());
				params.put("KHOAMASO", dmkhoa.getDmkhoaMaso());
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
			    jasperPrintVP =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","Xeminlientucphieucongkhai");
			    reportPathVP=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathVP);
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

	public String getNgayhientai() {
		return ngayhientai;
	}

	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
	}

	public String getKhoaMa() {
		return khoaMa;
	}

	public void setKhoaMa(String khoaMa) {
		this.khoaMa = khoaMa;
	}

	public String getNgaydungstr() {
		return ngaydungstr;
	}

	public void setNgaydungstr(String ngaydungstr) {
		this.ngaydungstr = ngaydungstr;
	}
}

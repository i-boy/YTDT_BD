/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaNopDelegate;
import com.iesvn.yte.dieutri.entity.HsbaNop;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.util.IConstantsRes;

@Name("B238_Soluutrubenhan")
@Scope(CONVERSATION)
public class SoLuuTruHSBA implements Serializable {
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
	

	private int index=0;
	private String soluutru_tu="";
	private String soluutru_den="";
	
	private String inTheo ;
	
	private Integer masoKhoa;
	private String maKhoa;
	
	private List ttIn;
	
	public String thuchienAction(){
		XuatReport();
		return "B260_Chonmenuxuattaptin";
	}
	
	
	@Begin (join = true)
	public String init() {
		log.info("init()");		
		return "DieuTri_LapVanBanTheoMau_SoLuuTruBenhAn";
	}
	
	

	@End
	public void end(){
		
	}
	
	public void loadSoVaoVienTu() {
		log.info("-----So vao vien-----" + soluutru_tu);
		
		if (soluutru_tu != null && !soluutru_tu.equals("")) {
			try {
				HsbaNopDelegate hsbaNopDAO = HsbaNopDelegate.getInstance();
				HsbaNop hsbaNopTmp = hsbaNopDAO.findBySoLuuTru(soluutru_tu);
				if (hsbaNopTmp == null) {
					FacesMessages.instance().add(IConstantsRes.HSBA_NOP_NULL, soluutru_tu);
					soluutru_tu ="";
					return;
				}else{
					soluutru_tu = hsbaNopTmp.getHsbanopSoluutru();
				}	
			} catch (Exception e) {
				FacesMessages.instance().add(IConstantsRes.HSBA_NOP_NULL, soluutru_tu);
				e.printStackTrace();
			}
		}
	}
	public void loadSoVaoVienDen() {
		log.info("-----So vao vien-----" + soluutru_den);
		
		if (soluutru_den != null && !soluutru_den.equals("")) {
			try {
				HsbaNopDelegate hsbaNopDAO = HsbaNopDelegate.getInstance();
				HsbaNop hsbaNopTmp = hsbaNopDAO.findBySoLuuTru(soluutru_den);
				if (hsbaNopTmp == null) {
					FacesMessages.instance().add(IConstantsRes.HSBA_NOP_NULL, soluutru_den);
					soluutru_den = "";
					return;
				}else{
					soluutru_den = hsbaNopTmp.getHsbanopSoluutru();
				}	
			} catch (Exception e) {
				FacesMessages.instance().add(IConstantsRes.HSBA_NOP_NULL, soluutru_den);
				e.printStackTrace();
			}
		}
	}
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		soluutru_den = "";
		soluutru_tu = "";
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		loaiBCDT="soluutruhsba";
		log.info("Vao Method XuatReport soluutruhsba");
		String baocao1=null;
		String pathTemplate=null;
		try {
			
			if ("1".equals(inTheo)){
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/soluutruhsba.jrxml";
			}else{
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/soluutruhsba_tuvong.jrxml";
			}
			
			
			log.info("da thay file templete 5" + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			if(!soluutru_tu.trim().equals(""))
				params.put("SOBATU", soluutru_tu.trim());
			if(!soluutru_den.trim().equals(""))
				params.put("SOBADEN", soluutru_den.trim());
			
			if (maKhoa != null && !maKhoa.equals("")){
				params.put("MASOKHOA", masoKhoa);
				DieuTriUtilDelegate dtUtilsDe = DieuTriUtilDelegate.getInstance();
				DmKhoa khoa = (DmKhoa)dtUtilsDe.findByMa(maKhoa, "DmKhoa", "");
				if (khoa != null){
					params.put("TENKHOA", khoa.getDmkhoaTen());
				}
			}else{
				params.put("MASOKHOA", null);
				params.put("TENKHOA", null);
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintDT,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"hsba/", "pdf","soluutruhsba");
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

	public String getSoluutru_tu() {
		return soluutru_tu;
	}

	public void setSoluutru_tu(String soluutru_tu) {
		this.soluutru_tu = soluutru_tu;
	}

	public String getSoluutru_den() {
		return soluutru_den;
	}

	public void setSoluutru_den(String soluutru_den) {
		this.soluutru_den = soluutru_den;
	}
	
	public List<SelectItem> getTtIn() {
		if(ttIn==null){
			ttIn=new ArrayList<SelectItem>();
			ttIn.add(new SelectItem(1,IConstantsRes.so_luu_tru_hsba));
			ttIn.add(new SelectItem(2,IConstantsRes.so_luu_tru_hsba_tu_vong));
		}
		return ttIn;
	}
	
	
	public String getDuongdanStrDT() {
		return duongdanStrDT;
	}

	public void setDuongdanStrDT(String duongdanStrDT) {
		this.duongdanStrDT = duongdanStrDT;
	}

	public String getInTheo() {
		return inTheo;
	}

	public void setInTheo(String inTheo) {
		this.inTheo = inTheo;
	}

	public Integer getMasoKhoa() {
		return masoKhoa;
	}

	public void setMasoKhoa(Integer masoKhoa) {
		this.masoKhoa = masoKhoa;
	}

	public String getMaKhoa() {
		return maKhoa;
	}

	public void setMaKhoa(String maKhoa) {
		this.maKhoa = maKhoa;
	}

	public void setTtIn(List ttIn) {
		this.ttIn = ttIn;
	}

}

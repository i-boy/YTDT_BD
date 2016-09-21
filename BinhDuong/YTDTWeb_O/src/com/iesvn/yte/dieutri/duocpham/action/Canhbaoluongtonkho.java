/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.duocpham.action;

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
import com.iesvn.yte.util.IConstantsRes;

@Name("B4161_Canhbaoluongtonkho")
@Scope(CONVERSATION)
public class Canhbaoluongtonkho implements Serializable {
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
	
	@In(scope=ScopeType.SESSION,required=false)
	@Out(scope=ScopeType.SESSION,required=false)
	private String resetFormB4161=null;
	
	private int index=0;
	private Integer lthuoc_maso=null;
	private Integer pthuoc_maso=null;
	private Integer khoa_maso=null;
	private Integer nguonct_maso=null;
	private Integer nguonkp_maso=null;
	private Double dSoLuongMin=null;
	private Double dSoLuongMax=null;
	private String lthuoc_ma=null;
	private String pthuoc_ma=null;
	private String khoa_ma=null;
	private String nguonct_ma=null;
	private String nguonkp_ma=null;
	
	String dmKhoXuat = "";
	
	public String getDmKhoXuat() {
		return dmKhoXuat;
	}
	public void setDmKhoXuat(String dmKhoXuat) {
		this.dmKhoXuat = dmKhoXuat;
	}
	
	@Factory("resetFormB4161")
	public void init() {
		log.info("init()");
		resetForm();
	}
	
	@Begin (join = true)
	public String init(String kho) {
		dmKhoXuat = kho;	
		return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_Canhbaoluongtonkho";
	}
	@End
	public void end(){
		
	}
	
	public String thuchienAction(){
		log.info("****Begin thuchienAction*****");
		XuatReport();
		resetForm();
		resetFormB4161=null;
		log.info("****End thuchienAction*****");
		return "B4160_Chonmenuxuattaptin";
	}
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		//resetFormB4173 = "";
		setLthuoc_ma(null);
		setPthuoc_ma(null);
		setNguonct_ma(null);
		setNguonkp_ma(null);
		setDSoLuongMax(null);
		setDSoLuongMin(null);
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeKC="Canhbaoluongtonkho";
		log.info("Vao Method XuatReport Canhbaoluongtonkho");
		String baocao1=null;
		try {
			log.info("bat dau method XuatReport ");
			String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/LuongHangTonKho.jrxml";
			log.info("da thay file templete : " + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			
			//log.info("truyen tham so loai thuoc " + lthuoc_maso);
			if(lthuoc_maso!=null){
				params.put("LOAITHUOCMASO", lthuoc_maso);
			}
			//log.info("truyen tham so  phan loai thuoc " + pthuoc_maso);
			if(pthuoc_maso!=null){
				params.put("PLTHUOCMASO", pthuoc_maso);
			}
			//log.info("truyen tham so  khoa " + khoa_maso);
			if(khoa_maso!=null){
				params.put("DMKHOAMASO", khoa_maso);
			}
			//log.info("truyen tham so  nguon chuong trinh " + nguonct_maso);
			if(nguonct_maso!=null){
				params.put("NGUONCTMASO", nguonct_maso);
			}
			//log.info("truyen tham so  nguon kinh phi " + nguonkp_maso);
			if(nguonkp_maso!=null){
				params.put("NGUONKPMASO", nguonkp_maso);
			}
			if(dSoLuongMin!=null){
				params.put("SOLUONGMIN", dSoLuongMin);
				
			}
			if(dSoLuongMax!=null){
				params.put("SOLUONGMAX", dSoLuongMax);
			}			
			
			log.info("================= ");
			Class.forName(IConstantsRes.ORACLE_DRIVER);
			    log.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintKC =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintKC,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"duocpham/", "pdf","Canhbaoluongtonkho");
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

	public Integer getPthuoc_maso() {
		return pthuoc_maso;
	}

	public void setPthuoc_maso(Integer pthuoc_maso) {
		this.pthuoc_maso = pthuoc_maso;
	}

	public Integer getKhoa_maso() {
		return khoa_maso;
	}

	public void setKhoa_maso(Integer khoa_maso) {
		this.khoa_maso = khoa_maso;
	}

	public Integer getNguonct_maso() {
		return nguonct_maso;
	}

	public void setNguonct_maso(Integer nguonct_maso) {
		this.nguonct_maso = nguonct_maso;
	}

	public Integer getNguonkp_maso() {
		return nguonkp_maso;
	}

	public void setNguonkp_maso(Integer nguonkp_maso) {
		this.nguonkp_maso = nguonkp_maso;
	}

	public String getLthuoc_ma() {
		return lthuoc_ma;
	}

	public void setLthuoc_ma(String lthuoc_ma) {
		this.lthuoc_ma = lthuoc_ma;
	}

	public String getPthuoc_ma() {
		return pthuoc_ma;
	}

	public void setPthuoc_ma(String pthuoc_ma) {
		this.pthuoc_ma = pthuoc_ma;
	}

	public String getKhoa_ma() {
		return khoa_ma;
	}

	public void setKhoa_ma(String khoa_ma) {
		this.khoa_ma = khoa_ma;
	}

	public String getNguonct_ma() {
		return nguonct_ma;
	}

	public void setNguonct_ma(String nguonct_ma) {
		this.nguonct_ma = nguonct_ma;
	}

	public String getNguonkp_ma() {
		return nguonkp_ma;
	}

	public void setNguonkp_ma(String nguonkp_ma) {
		this.nguonkp_ma = nguonkp_ma;
	}
	public Double getDSoLuongMin()
	{
		return dSoLuongMin;
	}
	public void setDSoLuongMin(Double soLuongMin)
	{
		dSoLuongMin = soLuongMin;
	}
	public Double getDSoLuongMax()
	{
		return dSoLuongMax;
	}
	public void setDSoLuongMax(Double soLuongMax)
	{
		dSoLuongMax = soLuongMax;
	}
	
	
	
}

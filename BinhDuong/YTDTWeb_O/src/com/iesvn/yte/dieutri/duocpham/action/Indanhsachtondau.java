/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.duocpham.action;

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
import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.util.IConstantsRes;

@Name("B4142_Indanhsachtondau")
@Scope(CONVERSATION)
public class Indanhsachtondau implements Serializable {
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
	private String resetFormB4142=null;
	
	private int index=0;
	private List<SelectItem> ttIn=null;
	private Integer inTheo=null;
	private Integer loaihang_maso=null;
	private Integer khoa_maso=null;
	private String loaihang_ma="";
	private String khoa_ma="";
	
	String dmKhoXuat = "";
	
	@Factory("resetFormB4142")
	public void initresetFormB4142() {
		log.info("init() B4142");
		resetForm();
		
	}
	
	@Begin (join = true)
	public String init(String kho) {
		log.info("init()");
		dmKhoXuat = kho;
		return "QuanLyKhoChinh_KiemKeKhoChinh_InDanhSachTonDau";
	}
	
	public String getDmKhoXuat() {
		return dmKhoXuat;
	}

	public void setDmKhoXuat(String dmKhoXuat) {
		this.dmKhoXuat = dmKhoXuat;
	}

	@End
	public void end(){
		
	}
	
	
	public String thuchienAction(){
		XuatReport();
		resetFormB4142=null;
		return "B4160_Chonmenuxuattaptin";
	}
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		setLoaihang_ma("");
		resetFormB4142 = "";
		setInTheo(new Integer(1));
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeKC="Indanhsachtondau";
		log.info("Vao Method XuatReport cap nhat ton dau");
		log.info("loai hang " + loaihang_ma);
		log.info("khoa ma  " + khoa_ma);
		String baocao1=null;
		String pathTemplate=null;
		try {
			log.info("bat dau method XuatReport ");
			if(inTheo==1)
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D12_solieutondau_intachrieng.jrxml";
			else if(inTheo==2)
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D12_solieutondau_ingop.jrxml";
			log.info("da thay file templete 5" + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			DieuTriUtilDelegate dtUtils=DieuTriUtilDelegate.getInstance();
			if(loaihang_ma.trim().equals("")){
				setLoaihang_ma(null);
			}else{
				DmLoaiThuoc dmloaithuoc = new DmLoaiThuoc();
				Object objLThuoc= dtUtils.findByMa(loaihang_ma ,"DmLoaiThuoc" , "dmloaithuocMa");
				dmloaithuoc = (DmLoaiThuoc)objLThuoc;
				params.put("LOAITHUOC", dmloaithuoc.getDmloaithuocTen());
				params.put("LOAITHUOCMASO", loaihang_maso);
			}
			if(!khoa_ma.trim().equals("")){
				log.info("khoa ma so" + khoa_maso);
				DmKhoa dmkhoa = new DmKhoa();
				Object obj = dtUtils.findByMa(khoa_ma  , "DmKhoa", "dmkhoaMa");
				dmkhoa = (DmKhoa)obj;
				params.put("KHO", dmkhoa.getDmkhoaTen());
				params.put("KHOMASO", khoa_maso);
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintKC,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"duocpham/", "pdf","solieutondau");
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

	public List<SelectItem> getTtIn() {
		if(ttIn==null){
			ttIn=new ArrayList<SelectItem>();
			ttIn.add(new SelectItem(1,IConstantsRes.In_Tach_Rieng));
			ttIn.add(new SelectItem(2,IConstantsRes.In_Gop_Ma_NCT_NSX));
		}
		return ttIn;
	}

	public void setTtIn(List<SelectItem> ttIn) {
		this.ttIn = ttIn;
	}

	public Integer getInTheo() {
		return inTheo;
	}

	public void setInTheo(Integer inTheo) {
		this.inTheo = inTheo;
	}

	public Integer getLoaihang_maso() {
		return loaihang_maso;
	}

	public void setLoaihang_maso(Integer loaihang_maso) {
		this.loaihang_maso = loaihang_maso;
	}

	public Integer getKhoa_maso() {
		return khoa_maso;
	}

	public void setKhoa_maso(Integer khoa_maso) {
		this.khoa_maso = khoa_maso;
	}

	public String getLoaihang_ma() {
		return loaihang_ma;
	}

	public void setLoaihang_ma(String loaihang_ma) {
		this.loaihang_ma = loaihang_ma;
	}

	public String getKhoa_ma() {
		return khoa_ma;
	}

	public void setKhoa_ma(String khoa_ma) {
		this.khoa_ma = khoa_ma;
	}
}

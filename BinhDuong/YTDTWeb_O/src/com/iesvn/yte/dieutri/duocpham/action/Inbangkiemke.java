/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.duocpham.action;

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

@Name("B4145_Inbangkiemke")
@Scope(CONVERSATION)
public class Inbangkiemke  implements Serializable {
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
	private String resetFormB4145=null;
	
	private Integer inTheo=null;
	private int index=0;
	private String ngayhientai;
	private Integer loaihang_maso=null;
	private Integer plthuoc_maso=null;
	private Integer khoa_maso=null;
	private Integer ct_maso=null;
	private Integer kp_maso=null;
	private String plthuoc_ma="";
	private String loaihang_ma="";
	private String khoa_ma="";
	private String ct_ma="";
	private String kp_ma="";
	private String thoidiemtinh="";
	private Boolean chonTK;
	private Boolean chonGD;
	private Boolean chonGT;
	private Boolean chonTT;
	
	@Factory("resetFormB4145")
	public void initresetFormB4145() {
		resetFormB4145="";
		log.info("init()");
		ngayhientai = com.iesvn.yte.util.Utils.getCurrentDate();
		thoidiemtinh = com.iesvn.yte.util.Utils.getCurrentDate();
		resetForm();
	}
	
	String dmKhoXuat = "";
	
	@Begin (join = true)
	public String init(String kho) {
		dmKhoXuat = kho;
		return "QuanLyKhoChinh_KiemKeKhoChinh_InBangKiemKe";
	}
	
	
	@End
	public void end(){
		
	}
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		setPlthuoc_ma("");
		setLoaihang_ma("");
		setCt_ma("");
		setKp_ma("");
		setInTheo(new Integer(2));
		setChonGD(true);
		setChonGT(true);
		setChonTK(true);
		setChonTT(true);
	}
	
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeKC="inbangkiemke";
		log.info("Vao Method XuatReport in bang kiem ke");
		String baocao1=null;
		String pathTemplate=null;
		try {
			log.info("bat dau method XuatReport ");
			if(inTheo==1){
				
			}else if(inTheo==2)
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D13_bangkiemkevattucongcusanphamhanghoa_tenhang.jrxml";
			log.info("da thay file templete 5" + pathTemplate);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Map<String, Object> params = new HashMap<String, Object>();
			params=new HashMap<String, Object>();
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			log.info("chon cot thua thieu " + chonTT);
			if(chonTT == false)
				params.put("CHONTT", null);
			else
				params.put("CHONTT", "");
			log.info("chon cot ton kho " + chonTK);
			if(chonTK == false)
				params.put("CHONTK", null);
			else
				params.put("CHONTK", "");
			log.info("chon cot ton kho " + chonTT);
			if(chonTT == false)
				params.put("CHONTT", null);
			else
				params.put("CHONTT", "");
			
			
			if(chonGT == false)
				params.put("CHONGT", null);
			else
				params.put("CHONGT", "");
			
			log.info("chonGT: " + chonGT);
			
			
			
			log.info("thoi diem tinh " + thoidiemtinh);
			if(!thoidiemtinh.equals("")){
				params.put("TINHDENNGAY", sdf.parse(thoidiemtinh));
			}
			if(loaihang_ma.trim().equals("")){
				setLoaihang_ma(null);
			}else{
				params.put("LTHUOCMASO", loaihang_maso);
			}
			if(khoa_ma.trim().equals("")){
				setKhoa_ma(null);
			}else{
				params.put("KHOAMASO", khoa_maso);
			}
			if(ct_ma.trim().equals("")){
				setCt_ma(null);
			}else{
				params.put("NCTMASO", ct_maso);
			}
			if(kp_ma.trim().equals("")){
				setKp_ma(null);
			}else{
				params.put("NKPMASO", kp_maso);
			}
			if(plthuoc_ma.trim().equals("")){
				setPlthuoc_ma(null);
			}else{
				params.put("PLTHUOCMASO", plthuoc_maso);
			}
			log.info("=========Thong so truoc khi tim kiem================");
			log.info("kinh phi " + kp_ma);
			log.info("chuong trinh " + ct_ma);
			log.info("khoa " + khoa_ma);
			log.info("loai hang " + loaihang_ma);
			log.info("phan loai loai hang " + plthuoc_ma);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintKC,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"duocpham/", "pdf","inbangkiemke");
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
	
	/**
	 * Ham thuc hien cap nhat bang kiem ke
	 * @return
	 */
	public String thuchienAction(){
		XuatReport();
		resetFormB4145=null;
		return "B4160_Chonmenuxuattaptin";		
	}
	
	public String getNgayhientai() {
		return ngayhientai;
	}


	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
	}


	public Integer getLoaihang_maso() {
		return loaihang_maso;
	}


	public void setLoaihang_maso(Integer loaihang_maso) {
		this.loaihang_maso = loaihang_maso;
	}


	public Integer getPlthuoc_maso() {
		return plthuoc_maso;
	}


	public void setPlthuoc_maso(Integer plthuoc_maso) {
		this.plthuoc_maso = plthuoc_maso;
	}


	public Integer getKhoa_maso() {
		return khoa_maso;
	}


	public void setKhoa_maso(Integer khoa_maso) {
		this.khoa_maso = khoa_maso;
	}


	public Integer getCt_maso() {
		return ct_maso;
	}


	public void setCt_maso(Integer ct_maso) {
		this.ct_maso = ct_maso;
	}


	public Integer getKp_maso() {
		return kp_maso;
	}

	public void setKp_maso(Integer kp_maso) {
		this.kp_maso = kp_maso;
	}

	public String getPlthuoc_ma() {
		return plthuoc_ma;
	}


	public void setPlthuoc_ma(String plthuoc_ma) {
		this.plthuoc_ma = plthuoc_ma;
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


	public String getCt_ma() {
		return ct_ma;
	}


	public void setCt_ma(String ct_ma) {
		this.ct_ma = ct_ma;
	}


	public String getKp_ma() {
		return kp_ma;
	}


	public void setKp_ma(String kp_ma) {
		this.kp_ma = kp_ma;
	}

	public void setInTheo(Integer inTheo) {
		this.inTheo = inTheo;
	}


	public Integer getInTheo() {
		return inTheo;
	}


	public void setIndex(int index) {
		this.index = index;
	}


	public int getIndex() {
		return index;
	}

	public void setThoidiemtinh(String thoidiemtinh) {
		this.thoidiemtinh = thoidiemtinh;
	}

	public String getThoidiemtinh() {
		return thoidiemtinh;
	}

	public Boolean getChonTK() {
		return chonTK;
	}

	public void setChonTK(Boolean chonTK) {
		this.chonTK = chonTK;
	}

	public Boolean getChonGD() {
		return chonGD;
	}

	public void setChonGD(Boolean chonGD) {
		this.chonGD = chonGD;
	}

	public Boolean getChonGT() {
		return chonGT;
	}

	public void setChonGT(Boolean chonGT) {
		this.chonGT = chonGT;
	}

	public Boolean getChonTT() {
		return chonTT;
	}

	public void setChonTT(Boolean chonTT) {
		this.chonTT = chonTT;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public String getReportPathKC() {
		return reportPathKC;
	}

	public void setReportPathKC(String reportPathKC) {
		this.reportPathKC = reportPathKC;
	}

	public String getReportTypeKC() {
		return reportTypeKC;
	}

	public void setReportTypeKC(String reportTypeKC) {
		this.reportTypeKC = reportTypeKC;
	}

	public JasperPrint getJasperPrintKC() {
		return jasperPrintKC;
	}

	public void setJasperPrintKC(JasperPrint jasperPrintKC) {
		this.jasperPrintKC = jasperPrintKC;
	}

	public String getResetFormB4145() {
		return resetFormB4145;
	}

	public void setResetFormB4145(String resetFormB4145) {
		this.resetFormB4145 = resetFormB4145;
	}

	public String getDmKhoXuat() {
		return dmKhoXuat;
	}

	public void setDmKhoXuat(String dmKhoXuat) {
		this.dmKhoXuat = dmKhoXuat;
	}
}

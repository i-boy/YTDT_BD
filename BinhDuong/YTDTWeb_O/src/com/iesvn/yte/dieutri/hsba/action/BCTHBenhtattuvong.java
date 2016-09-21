/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Name("B224_Baocaotinhhinhbenhtattuvong")
@Scope(CONVERSATION)
public class BCTHBenhtattuvong implements Serializable {
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
	private String resetFormB224=null;
	
	private String thoigian_thang=null;  
	private String thoigian_nam=null;
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
   
    
	@Factory("resetFormB224")
	public void initresetFormB224() {
		log.info("init()");
		ngayhientai = Utils.getCurrentDate();
		resetForm();
	}
	
	@Out(required=false)
	@In(required=false)
	private String loaiBaoCao = null;
	
	@Begin (join = true)
	public String init(String loaibaocao) {
		this.loaiBaoCao = loaibaocao; 
		resetForm();
		return "DieuTri_BaoCaoHoatDongDieuTri_BaoCaoTinhHinhBenhTatTuVong";
	}
	@End
	public void end(){
		
	}
	public String nam;
	  public int thoigian=3;

	    public String getNam() {
	        return nam;
	    }

	    public void setNam(String nam) {
	        this.nam = nam;
	    }

	    public int getThoigian() {
	        return thoigian;
	    }

	    public void setThoigian(int thoigian) {
	        this.thoigian = thoigian;
	    }
	public String thuchienAction(){
		XuatReport();
		resetFormB224=null;
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
		resetFormB224 = "";
		/*thoigian_thang=String.valueOf(currentDate.getMonth() +1);  
		thoigian_nam=String.valueOf(currentDate.getYear()+1900);
		Calendar cal=Calendar.getInstance();
		setNam(cal.get(Calendar.YEAR)+"");
		tungay = "01/01/"+nam;
		denngay = tinhDenngay();
		*/
		tungay = sdf.format(currentDate);
		denngay = sdf.format(currentDate);
		log.info("Tu ngay = "+ tungay);
		log.info("Den ngay = "+ denngay);
		log.info("ket thuc ham reset form");
	}
	public String tinhDenngay()
	{
		if(thoigian==6||thoigian==9){
			return "30/"+thoigian+"/"+nam;
		}else
		{
			return "31/"+thoigian+"/"+nam;
		}
	}
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		loaiBCDT="BCTHBenhTatTuVong";
		log.info("Vao Method XuatReport bao cao tinh hinh benh tat tu vong");
		String baocao1=null;
		String pathTemplate = null;
		try {
			log.info("bat dau method XuatReport ");
			if ("tinhinhbenhtattuvong".equals(loaiBaoCao)){
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/B04_THBenhtatvatuvongtaibenhvien.jrxml";
			}else{
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/B03_BaoCaoHoatDongDieuTri.jrxml";
			}
			
			
			log.info("da thay file templete" + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			//denngay = tinhDenngay();
			Map<String, Object> params = new HashMap<String, Object>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE_SHORT);
			params.put("benhvien", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL);
			params.put("sodt", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			params.put("diadiem", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			params.put("TuNgay", sdf.parse(tungay));
			params.put("DenNgay", sdf.parse(denngay));
			if(tieude.trim().equals("")){
				params.put("tieude",  IConstantsRes.TU+" "+tungay+" "+IConstantsRes.DEN+" "+denngay);
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintDT,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"hsba/", "pdf","BCTHBenhTatTuVong");
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
}

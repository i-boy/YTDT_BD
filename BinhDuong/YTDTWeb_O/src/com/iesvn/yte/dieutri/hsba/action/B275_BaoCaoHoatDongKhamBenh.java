/**
 * author : i-boy
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
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.util.IConstantsRes;


@Name("B275_BaoCaoHoatDongKhamBenh")
@Scope(CONVERSATION)
public class B275_BaoCaoHoatDongKhamBenh implements Serializable {
	
	private static final long serialVersionUID = 1749463646874557659L;

	@Logger
	private Log log;
	
	@Out(required=false)
	@In(required=false)
	private String duongdanStrDT=null;
	
	@Out(required=false)
	@In(required=false)
	private String loaiBCDT=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintTD = null;
	
	private String thoigian_thang = "";  
	private String thoigian_nam = "";
	private String tungay = null;
	private String denngay = null;
	private String ngayhientai = "";
	
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
	  
	
	private int index=0;	
	private String tieude ="";

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }
	@Begin (join = true)
	public String init() {
		resetForm();
		return "DieuTri_BaoCaoHoatDongDieuTri_BaoCaoHoatDongKhamBenh";
	}
	@End
	public void end(){
		
	}
	
	public String thuchienAction(){
		XuatReport();
		return "B260_Chonmenuxuattaptin";
	}
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		ngayhientai = sdf.format(currentDate);
		/*thoigian_nam = sdf.format(currentDate).substring(6);
		thoigian_thang = sdf.format(currentDate).substring(3, 5);  
		
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		setNam(cal.get(Calendar.YEAR)+"");
		log.info("Năm = "+ cal.get(Calendar.YEAR));
		tungay = "01/01/"+nam;
		denngay = tinhDenngay();*/
		tungay = sdf.format(currentDate);
		denngay = sdf.format(currentDate);
		log.info("Tu ngay = "+ tungay);
		log.info("Den ngay = "+ denngay);
//		log.info("Ket thuc ham reset form B275_BaoCaoHoatDongKhamBenh");
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
		loaiBCDT="BCHoatDongKhamBenh";
		log.info("Vao Method XuatReport In danh sach B275_BaoCaoHoatDongKhamBenh");
		String baocao1=null;
		String pathTemplate = null;
		try {
			log.info("bat dau method XuatReport ");
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/hoatdongkhambenh.jrxml";
			log.info("da thay file templete" + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			Map<String, Object> params = new HashMap<String, Object>();

			//denngay = tinhDenngay();
			params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE_SHORT);
			params.put("benhvien", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL);
			log.info("Tu ngay = "+ tungay);
			log.info("Den ngay = "+ denngay);
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");		
			params.put("fromDate", df.parse(tungay));
			params.put("toDate", df.parse(denngay));
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
			    	// bao.ttc: tao connection den DB dang ky kham benh online
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintTD =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintTD,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"hsba/", "pdf","hoatdongkhambenh");
			    duongdanStrDT=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+duongdanStrDT);
			    index += 1;
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

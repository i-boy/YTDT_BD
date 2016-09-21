package com.iesvn.yte.dieutri.vienphi.action;

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

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B3327_Phantichchiphidieutrinoitru")
@Synchronized(timeout = 6000000)
public class PhanTichChiPhiDieuTriNoiTruAction implements Serializable {
	private static Logger logger = Logger.getLogger(PhanTichChiPhiDieuTriNoiTruAction.class);
	
	private String thangHt;
	private String namHt;
	private String ngayHt;
	private String tuNgay;
	private String denNgay;
	private DmKhoa khoa;
	private DmDoiTuong dt;
	
	
	@Out(required=false)
	@In(required=false)
	private String loaiBC=null;
	
	@Begin(join=true)
	public String init(String loai) {
		logger.debug("-----init()-----");
		loaiBC = loai;
		reset();
		return "BaoCaoVienPhi_HoSoBaoCao_PhanTichChiPhiDieuTriNoiTru";
		
	}
	@End
	public void endFunc(){
		
	}
	
	public void reset() {
		
		
		ngayHt = Utils.getCurrentDate();
		khoa = new DmKhoa();
		dt = new DmDoiTuong();
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		
		thangHt =String.valueOf(currentDate.getMonth() +1);  
		namHt=String.valueOf(currentDate.getYear()+1900);
		tuNgay = sdf.format(currentDate);
		denNgay = sdf.format(currentDate);
	}
	int index = 0;
	public String thuchienAction(){
		XuatReport();
	
		return "B3360_Chonmenuxuattaptin";
	}
	@Out(required=false)
	@In(required=false)
	private String reportPathVP=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeVP=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintVP=null;
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeVP="V14_Thanhtoanchiphibenhnhandieutridoituongthuphi";
		logger.info("Vao Method XuatReport V14_Thanhtoanchiphibenhnhandieutridoituongthuphi");
		String baocao1=null;
		String pathTemplate = null;
		try {
			logger.info("bat dau method XuatReport ");
			if ("dieutrinoitru".equals( loaiBC)){
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/V14_Thanhtoanchiphibenhnhandieutridoituongthuphi.jrxml";
				
			}else if ("tienphong".equals( loaiBC)){
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/V16_PTTienphong.jrxml";
				
			}else if ("mienphingoaitru".equals( loaiBC)){
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/V18_THChiphimienphidantocngoaitru.jrxml";
				
			}else if ("mienphinoitru".equals( loaiBC)){
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/V18b_THChiphimienphidantocnoitru.jrxml";
				
			}
			
			
			
			
			logger.info("da thay file templete " + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			Map<String, Object> params = new HashMap<String, Object>();
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			
			params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("benhvien", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("sodt", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
	        params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
	        params.put("tungay", formatter.parse(tuNgay));
	        params.put("denngay", formatter.parse(denNgay));
	        
	        
	        
	        DieuTriUtilDelegate dtUtils = DieuTriUtilDelegate.getInstance();
	        DmDoiTuong dmDT =  null;
	        if (dt.getDmdoituongMa() != null && !dt.getDmdoituongMa().equals("") ){
	        	 dmDT = (DmDoiTuong)dtUtils.findByMa(dt.getDmdoituongMa(), "DmDoiTuong", "dmdoituongMa");
	        }
	       
	        if (dmDT != null){
	        	 params.put("TENDOITUONG", dmDT.getDmdoituongTen());
	        	 params.put("MADOITUONG", dmDT.getDmdoituongMa());
	        	 params.put("MaSoDoiTuong", dmDT.getDmdoituongMaso());
	        	 logger.info("MaSoDoiTuong:" + dmDT.getDmdoituongMaso());
	        	 
	        }else{
	        	 params.put("TENDOITUONG", null);
	        	 params.put("MADOITUONG", null);
	        	 params.put("MaSoDoiTuong", null);
	        	 logger.info("MaSoDoiTuong:" );
	        }
	        
	        DmKhoa khoaTmp = null;
	        if (khoa.getDmkhoaMa() != null && !khoa.getDmkhoaMa().equals("")){
	        	 khoaTmp = (DmKhoa)dtUtils.findByMa(khoa.getDmkhoaMa(), "DmKhoa", "dmkhoaMa");
	        }
	        
	        
	        if (khoaTmp != null){
	        	params.put("MaSoKhoa", khoaTmp.getDmkhoaMaso());
	        	params.put("TenKhoa", khoaTmp.getDmkhoaTen());
	        }else{
	        	params.put("MaSoKhoa", null);
	        	params.put("TenKhoa", null);
	        }
	       
//	        params.put("tungay", null);
//	        params.put("denngay", null);
//	        params.put("MaSoDoiTuong", null);
	        
	        logger.info("tungay:" + formatter.parse(tuNgay));
	        logger.info("denngay:" + formatter.parse(denNgay));
	        
	        
			logger.info("End set params cho report");
			
			logger.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			    logger.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	logger.info(e.getMessage());
			    }
			    jasperPrintVP =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","V14_Thanhtoanchiphibenhnhandieutridoituongthuphi");
			    reportPathVP=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    logger.info("duong dan file xuat report :" + baocao1);
			    logger.info("duong dan -------------------- :"+reportPathVP);
			    index+=1;
			    logger.info("Index :" + index);
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    logger.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    logger.info("Thoat Method XuatReport");
	}
//	public String inPhieu(String loaiFile) {
//		logger.info("-----inPhieu()-----");
//		logger.info(String.format("-----loai file: %s", loaiFile));
//		Date currentDate = new Date();
//		String pathTemplate = "V14_Thanhtoanchiphibenhnhandieutridoituongthuphi";
//		
//		Map<String, Object> params = new HashMap<String, Object>();
//		String fileNameExt = String.valueOf(currentDate.getTime());
//		try {
//			String fileName = com.iesvn.yte.ReportUtil.xuatReport(IConstantsRes.PATH_BASE,
//							IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI, 
//							IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI, "vienphi/",
//							pathTemplate , params, loaiFile, fileNameExt);
//
//			if(loaiFile.equalsIgnoreCase("HTML")) {
//				setResultReportName(fileName);
//			} else {
//				setResultReportFileName(fileName);
//			}
//			setIsReport("true");
//		} catch (Exception e) {
//			logger.error(e);
//		}
//		
//		return "/B3_Vienphi/BaoCao/B3327_Chonmenuxuattaptin.xhtml";
//	}
	
	public String troVe(){
		try {
			logger.info("***** troVe()");
			return "/B3_Vienphi/BaoCao/B3327_Phantichchiphidieutrinoitru.xhtml";
		} 		
		catch (Exception e) {
			logger.info("***** End exception = " + e);    	
		} 
		return null;
	}

	public String getThangHt() {
		return thangHt;
	}

	public void setThangHt(String thangHt) {
		this.thangHt = thangHt;
	}

	public String getNamHt() {
		return namHt;
	}

	public void setNamHt(String namHt) {
		this.namHt = namHt;
	}

	public String getNgayHt() {
		return ngayHt;
	}

	public void setNgayHt(String ngayHt) {
		this.ngayHt = ngayHt;
	}

	public DmKhoa getKhoa() {
		return khoa;
	}

	public void setKhoa(DmKhoa khoa) {
		this.khoa = khoa;
	}

	public String getTuNgay() {
		return tuNgay;
	}

	public void setTuNgay(String tuNgay) {
		this.tuNgay = tuNgay;
	}

	public String getDenNgay() {
		return denNgay;
	}

	public void setDenNgay(String denNgay) {
		this.denNgay = denNgay;
	}

	

	public DmDoiTuong getDt() {
		return dt;
	}

	public void setDt(DmDoiTuong dt) {
		this.dt = dt;
	}
}

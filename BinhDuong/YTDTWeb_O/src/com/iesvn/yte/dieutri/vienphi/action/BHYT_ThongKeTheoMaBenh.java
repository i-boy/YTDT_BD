/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.log.Log;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.DtDmPlBhyt;
import com.iesvn.yte.entity.DmBenhIcd;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Name("BHYT_ThongKeTheoMaBenh")
@Scope(CONVERSATION)
public class BHYT_ThongKeTheoMaBenh implements Serializable {
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
	
	
	
	@DataModel
	private List<DmBenhIcd> listBenhICDB3332 = new ArrayList<DmBenhIcd>();

	@DataModelSelection("listBenhICDB3332")
	@Out(required = false)
	private DmBenhIcd dmBenhICD3332Selected; 
	
	private String thoigian_thang=null;  
	private String thoigian_nam=null;
	private String tungay=null;
	private String denngay=null;
	private String ngayhientai;
	private int index=0; 
	
	private Integer maSoBenhICD = null;
	private String maBenhICD = "";
	 
	
	
	private Integer masoPLBHYT = null;
	private String maPLBHYT="";
	
//	@Factory("resetFormB3332")	
//	public void initTmp() {
//		log.info("init()");
//		ngayhientai = Utils.getCurrentDate();
//		resetForm();
//	}
	
	@Out(required = false)
	@In(required = false)
	String loaiKCB = null;
	
	@Begin(join=true)
	public String init(String tempLoaiKCB) { // noi_tru; ngoai_tru
		ngayhientai = Utils.getCurrentDate();
		loaiKCB = tempLoaiKCB;
		resetForm();
		return "BaoCaoVienPhi_BHYT_ThongKeTheoMaBenh";
	}
	@End
	public void end(){
		
	}
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		
		thoigian_thang=String.valueOf(currentDate.getMonth() +1);  
		thoigian_nam=String.valueOf(currentDate.getYear()+1900);
		tungay = sdf.format(currentDate);
		denngay = sdf.format(currentDate);
		
	
		listBenhICDB3332.clear();
		setMaPLBHYT("");
	}
	
	/**
	 * Method tra ve String truyen tham so vao file jrxml
	 */
	private String getListDVMaParamsHelp(List<String> inputs){
        log.info("Vao method getListDVMaParamsHelp ");
        log.info("Inputs: " + inputs.toString());
        String result = "";
        for(String each : inputs){
                if(each != "")
                        result += "'"+each + "',";
        }
        result = result.substring(0, result.length()-1);
        log.info("Thoat method getListDVMaParamsHelp ");
        log.info("Output: " + result);
        return result;
	}
	
	public String thuchienAction(){
		XuatReport();
		
		return "B3360_Chonmenuxuattaptin";
	}
	
	/**
	 * ham them mot phan tu vao DS
	 */
	public void enter(){
		log.info("bat dau them du lieu vao luoi" + maBenhICD);
		DmBenhIcd benhICD;
		boolean test=false;
		DieuTriUtilDelegate dtutilDelegate=DieuTriUtilDelegate.getInstance();
		if(!maBenhICD.equals("")){
			if((listBenhICDB3332.size()==0) && (maBenhICD !=null)){
				log.info("size list bang 0");
				Object obj=dtutilDelegate.findByMa(maBenhICD , "DmBenhIcd", "dmbenhicdMa");
				benhICD=(DmBenhIcd)obj;
				listBenhICDB3332.add(benhICD);
				log.info("da add phan tu dau tien" + listBenhICDB3332.size());
			}else if ((listBenhICDB3332.size()>0) && (maBenhICD !=null)){
				log.info("size list lon hon 0");
				for(DmBenhIcd item:listBenhICDB3332){
					if(item.getDmbenhicdMa().equals(maBenhICD)){
						test=true;
					}
				}
				if(test == false){
					Object obj=dtutilDelegate.findByMa(maBenhICD , "DmBenhIcd", "dmbenhicdMa");
					benhICD=(DmBenhIcd)obj;
					listBenhICDB3332.add(benhICD);
				}
				log.info("da add phan tu" + listBenhICDB3332.size());
			}
		}
		setMaBenhICD("");
	}
	
	
	/**
	 * Ham xoa mot record
	 */
	public void deletedmBenhICD(){
		log.info("bat dau xoa , size" + listBenhICDB3332.size());
		listBenhICDB3332.remove(dmBenhICD3332Selected);
		log.info("da xoa , size" + listBenhICDB3332.size());
		log.info("ket thuc xoa");
	} 
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeVP="BaocaoBHYTnoitru";
		log.info("Vao Method XuatReport bao cao BHYT noi tru");
		String baocao1=null;
		String pathTemplate = null;
		try {
			log.info("bat dau method XuatReport ");
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/V22a_DSNguoibenhkhamchuabenhnoitrudenghithanhtoan.jrxml";
			
			log.info("da thay file templete " + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			Map<String, Object> params = new HashMap<String, Object>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("benhvien", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("sodt", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			params.put("TuNgay", sdf.parse(tungay));
			
			params.put("DenNgay", sdf.parse(denngay));
			
			
			if(listBenhICDB3332.size()>0){
				List<String> listtemp=new ArrayList<String>();
				for(DmBenhIcd item:listBenhICDB3332){
					listtemp.add(item.getDmbenhicdMa());
				}
				params.put("khoi", getListDVMaParamsHelp(listtemp));
				log.info("list phan loai " + getListDVMaParamsHelp(listtemp));
			}
			
			DieuTriUtilDelegate dtUtils = DieuTriUtilDelegate.getInstance();
			
				DtDmPlBhyt plbhyt = new DtDmPlBhyt();
				Object obj = dtUtils.findByMa(maPLBHYT, "DtDmPlBhyt", " dtdmphloaibhytMa");
				plbhyt = (DtDmPlBhyt)obj;
				params.put("TenPLDoiTuong", plbhyt.getDtdmphloaibhytTen() );
				params.put("MaPLDoiTuong", plbhyt.getDtdmphloaibhytMa() );
				log.info(" ma phan loai " + maPLBHYT + "ten phan loai " + plbhyt.getDtdmphloaibhytTen());
			

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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","BaocaoBHYTnoitru");
			    reportPathVP=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathVP);
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


	public Integer getMaSoBenhICD() {
		return maSoBenhICD;
	}


	public void setMaSoBenhICD(Integer maSoBenhICD) {
		this.maSoBenhICD = maSoBenhICD;
	}


	public String getMaBenhICD() {
		return maBenhICD;
	}


	public void setMaBenhICD(String maBenhICD) {
		this.maBenhICD = maBenhICD;
	}




	

	public Integer getMasoPLBHYT() {
		return masoPLBHYT;
	}

	public void setMasoPLBHYT(Integer masoPLBHYT) {
		this.masoPLBHYT = masoPLBHYT;
	}

	public String getMaPLBHYT() {
		return maPLBHYT;
	}

	public void setMaPLBHYT(String maPLBHYT) {
		this.maPLBHYT = maPLBHYT;
	}
	public Log getLog() {
		return log;
	}
	public void setLog(Log log) {
		this.log = log;
	}
	public String getReportPathVP() {
		return reportPathVP;
	}
	public void setReportPathVP(String reportPathVP) {
		this.reportPathVP = reportPathVP;
	}
	public String getReportTypeVP() {
		return reportTypeVP;
	}
	public void setReportTypeVP(String reportTypeVP) {
		this.reportTypeVP = reportTypeVP;
	}
	public JasperPrint getJasperPrintVP() {
		return jasperPrintVP;
	}
	public void setJasperPrintVP(JasperPrint jasperPrintVP) {
		this.jasperPrintVP = jasperPrintVP;
	}
	public List<DmBenhIcd> getListBenhICDB3332() {
		return listBenhICDB3332;
	}
	public void setListBenhICDB3332(List<DmBenhIcd> listBenhICDB3332) {
		this.listBenhICDB3332 = listBenhICDB3332;
	}
	public DmBenhIcd getDmBenhICD3332Selected() {
		return dmBenhICD3332Selected;
	}
	public void setDmBenhICD3332Selected(DmBenhIcd dmBenhICD3332Selected) {
		this.dmBenhICD3332Selected = dmBenhICD3332Selected;
	}
	public String getLoaiKCB() {
		return loaiKCB;
	}
	public void setLoaiKCB(String loaiKCB) {
		this.loaiKCB = loaiKCB;
	}
}

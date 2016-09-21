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
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Factory;
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
import com.iesvn.yte.dieutri.entity.DtDmKcbBhyt;
import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Name("B3336_BaocaoBHYTtaiphongphatthuoc")
@Scope(CONVERSATION)
public class BaocaoBHYTtaiphongphatthuoc implements Serializable {
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
	private String resetFormB3336=null;
	
	private String thoigian_thang=null;  
	private String thoigian_nam=null;
	private String tungay=null;
	private String denngay=null;
	private String ngayhientai;
	private int index=0;
	
	private String chonKhoi = "";
	private String chonKCB = "";
	private String maKhoiBHYT = "";
	private String maKCBBHYT = "" ;
	
	@DataModel
	private List<DtDmKhoiBhyt> listkhoiBHYT = new ArrayList<DtDmKhoiBhyt>();

	@DataModelSelection("listkhoiBHYT")
	@Out(required = false)
	private DtDmKhoiBhyt dmkhoiBHYTSelected; 
	
	@DataModel
	private List<DtDmKcbBhyt> listKCBBHYT = new ArrayList<DtDmKcbBhyt>();

	@DataModelSelection("listKCBBHYT")
	@Out(required = false)
	private DtDmKcbBhyt dmKCBBHYTSelected; 
	
	@Factory("resetFormB3336")
	
	public void initresetFormB3336() {
		log.info("init()");
		ngayhientai = Utils.getCurrentDate();
		resetForm();
	}
	@Begin(join=true)
	public String init(){
		initresetFormB3336();
		return "BaoCaoVienPhi_BaoCaoBHYT_BaoCaoBHYTTaiPhongPhatThuoc";
	}
	@Create
	public void endFunc(){
		
	}
	
	public String thuchienAction(){
		XuatReport();
		resetFormB3336=null;
		return "B3360_Chonmenuxuattaptin";
	}
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		resetFormB3336 = "";
		thoigian_thang=String.valueOf(currentDate.getMonth() +1);  
		thoigian_nam=String.valueOf(currentDate.getYear()+1900);
		tungay = sdf.format(currentDate);
		denngay = sdf.format(currentDate);
		setMaKCBBHYT("");
		setMaKhoiBHYT("");
		setChonKCB("k1");
		setChonKhoi("r1");
		listKCBBHYT.clear();
		listkhoiBHYT.clear();
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
	
	/**
	 * ham them mot phan tu vao DS
	 */
	public void enter(){
		log.info("bat dau them du lieu vao luoi" + maKhoiBHYT);
		DtDmKhoiBhyt khoiBHYT;
		boolean test=false;
		DieuTriUtilDelegate dtutilDelegate=DieuTriUtilDelegate.getInstance();
		if(!maKhoiBHYT.equals("")){
			if((listkhoiBHYT.size()==0) && (maKhoiBHYT !=null)){
				log.info("size list bang 0");
				Object obj=dtutilDelegate.findByMa(maKhoiBHYT , "DtDmKhoiBhyt", "dtdmkhoibhytMa");
				khoiBHYT=(DtDmKhoiBhyt)obj;
				listkhoiBHYT.add(khoiBHYT);
				log.info("da add phan tu dau tien" + listkhoiBHYT.size());
			}else if ((listkhoiBHYT.size()>0) && (maKhoiBHYT !=null)){
				log.info("size list lon hon 0");
				for(DtDmKhoiBhyt item:listkhoiBHYT){
					if(item.getDtdmkhoibhytMa().equals(maKhoiBHYT)){
						test=true;
					}
				}
				if(test == false){
					Object obj=dtutilDelegate.findByMa(maKhoiBHYT , "DtDmKhoiBhyt", "dtdmkhoibhytMa");
					khoiBHYT=(DtDmKhoiBhyt)obj;
					listkhoiBHYT.add(khoiBHYT);
				}
				log.info("da add phan tu" + listkhoiBHYT.size());
			}
		}
		setMaKhoiBHYT("");
	}
	
	/**
	 * Ham xoa mot record
	 */
	public void deletedmkhoiBHYT(){
		log.info("bat dau xoa , size" + listkhoiBHYT.size());
		listkhoiBHYT.remove(dmkhoiBHYTSelected);
		log.info("da xoa , size" + listkhoiBHYT.size());
		log.info("ket thuc xoa");
	} 
	
	/**
	 * ham them mot phan tu vao DS
	 */
	public void enterKCB(){
		log.info("bat dau them du lieu vao luoi KCB" + maKCBBHYT);
		DtDmKcbBhyt kcbBHYT;
		boolean test=false;
		DieuTriUtilDelegate dtutilDelegate=DieuTriUtilDelegate.getInstance();
		if(!maKCBBHYT.equals("")){
			if((listKCBBHYT.size()==0) && (maKCBBHYT !=null)){
				log.info("size list bang 0");
				Object obj=dtutilDelegate.findByMa(maKCBBHYT , "DtDmKcbBhyt", "dtdmkcbbhytMa");
				kcbBHYT=(DtDmKcbBhyt)obj;
				listKCBBHYT.add(kcbBHYT);
				log.info("da add phan tu dau tien" + listKCBBHYT.size());
			}else if ((listKCBBHYT.size()>0) && (maKCBBHYT !=null)){
				log.info("size list lon hon 0");
				for(DtDmKcbBhyt item:listKCBBHYT){
					if(item.getDtdmkcbbhytMa().equals(maKhoiBHYT)){
						test=true;
					}
				}
				if(test == false){
					Object obj=dtutilDelegate.findByMa(maKCBBHYT , "DtDmKcbBhyt", "dtdmkcbbhytMa");
					kcbBHYT=(DtDmKcbBhyt)obj;
					listKCBBHYT.add(kcbBHYT);
				}
				log.info("da add phan tu" + listKCBBHYT.size());
			}
		}
		setMaKCBBHYT("");
	}
	
	/**
	 * Ham xoa mot record
	 */
	public void deletedmKCBBHYT(){
		log.info("bat dau xoa , size" + listKCBBHYT.size());
		listKCBBHYT.remove(dmKCBBHYTSelected);
		log.info("da xoa , size" + listKCBBHYT.size());
		log.info("ket thuc xoa");
	} 
	
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeVP="BaocaoBHYTtaiphongphatthuoc";
		log.info("Vao Method XuatReport bao cao BHYT tai phong phat thuoc");
		String baocao1=null;
		String pathTemplate = null;
		try {
			log.info("bat dau method XuatReport ");
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/V24_Chiphitaikhocapphatthuocngoaitru.jrxml";
			log.info("da thay file templete " + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			Map<String, Object> params = new HashMap<String, Object>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("benhvien", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("sodt", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			params.put("TuNgay", sdf.parse(tungay));
			params.put("DenNgay", sdf.parse(denngay));
			
			if(chonKhoi.equals("r1")){
				params.put("chon1", "IN");
			}else if(chonKhoi.equals("r1")){
				params.put("chon1", "NOT IN");
			}
			
			if(chonKCB.equals("k1")){
				params.put("chon2", "IN");
			}else if(chonKCB.equals("k1")){
				params.put("chon2", "NOT IN");
			}
			
			if(listkhoiBHYT.size()>0){
				List<String> listtemp=new ArrayList<String>();
				for(DtDmKhoiBhyt item:listkhoiBHYT){
					listtemp.add(item.getDtdmkhoibhytMa());
				}
				params.put("khoi", getListDVMaParamsHelp(listtemp));
				log.info("list khoi " + getListDVMaParamsHelp(listtemp));
			}
			
			if(listKCBBHYT.size()>0){
				List<String> listtemp=new ArrayList<String>();
				for(DtDmKcbBhyt item:listKCBBHYT){
					listtemp.add(item.getDtdmhuyenbhytMa());
				}
				params.put("noiKB", getListDVMaParamsHelp(listtemp));
				log.info("list KCB " + getListDVMaParamsHelp(listtemp));
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","BaocaoBHYTtaiphongphatthuoc");
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

	public String getChonKhoi() {
		return chonKhoi;
	}

	public void setChonKhoi(String chonKhoi) {
		this.chonKhoi = chonKhoi;
	}

	public String getChonKCB() {
		return chonKCB;
	}

	public void setChonKCB(String chonKCB) {
		this.chonKCB = chonKCB;
	}

	public String getMaKhoiBHYT() {
		return maKhoiBHYT;
	}

	public void setMaKhoiBHYT(String maKhoiBHYT) {
		this.maKhoiBHYT = maKhoiBHYT;
	}

	public String getMaKCBBHYT() {
		return maKCBBHYT;
	}

	public void setMaKCBBHYT(String maKCBBHYT) {
		this.maKCBBHYT = maKCBBHYT;
	}
}

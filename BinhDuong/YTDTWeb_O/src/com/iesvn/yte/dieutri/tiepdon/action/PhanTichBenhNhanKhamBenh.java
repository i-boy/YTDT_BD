/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.tiepdon.action;


import static org.jboss.seam.ScopeType.CONVERSATION;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Name("B141_Phantichbenhnhankhambenh")
@Scope(CONVERSATION)
public class PhanTichBenhNhanKhamBenh implements Serializable {
	@Logger
	private Log log;
	
	@Out(required=false)
	@In(required=false)
	private String reportPathTD=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeTD=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintTD = null;
	
	@Out(required=false)
	@In(required=false)
	private String resetFormB141;
	
	@Out(required=false)
	@In(required=false)
	private String loaikhambenh;
	
	@DataModel
	private List<DtDmKhoiBhyt> listkhoiBHYTB141 = new ArrayList<DtDmKhoiBhyt>();

	@DataModelSelection("listkhoiBHYTB141")
	@Out(required = false)
	private DtDmKhoiBhyt dmkhoiBHYTB3332Selected; 
	
	private String thoigian_thang=null;  
	private String thoigian_nam=null;
	private String tungay=null;
	private String denngay=null;
	private String ngayhientai;
	private int index=0; 
	
	private Integer maSoKhoiBHYT = null;
	private String maKhoiBHYT = "";
	private Integer tuyen; 
	
	private String chonloaibc;
	
	private String maDoiTuong = null;
	
	private String bankhamMa=null;
	private Integer bankhamMaSo;
	
	
	public String getBankhamMa() {
		return bankhamMa;
	}
	public void setBankhamMa(String bankhamMa) {
		this.bankhamMa = bankhamMa;
	}
	public Integer getBankhamMaSo() {
		return bankhamMaSo;
	}
	public void setBankhamMaSo(Integer bankhamMaSo) {
		this.bankhamMaSo = bankhamMaSo;
	}
	public String getMaDoiTuong() {
		return maDoiTuong;
	}
	public void setMaDoiTuong(String maDoiTuong) {
		this.maDoiTuong = maDoiTuong;
	}
	
	@Factory("resetFormB141")	
	public void initTmp() {
		log.info("init()");
		ngayhientai = Utils.getCurrentDate();
		resetForm();
	}
	
	@Begin(join=true)
	public String init(String loai) {
		log.info("init()");
		loaikhambenh = loai;
		ngayhientai = Utils.getCurrentDate();
		resetForm();
		return "TiepDon_PhanTichBaoCao_PhanTichBenhNhanKhamBenh";
	}
	@End
	public void end(){
		
	}
	
	/*
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		
		thoigian_thang=String.valueOf(currentDate.getMonth() +1);  
		thoigian_nam=String.valueOf(currentDate.getYear()+1900);
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH,1);
		
		maDoiTuong = "";
		bankhamMa = "";
		maKhoiBHYT = "";
		tungay = sdf.format(cal.getTime());
		denngay = sdf.format(currentDate);
		chonloaibc = "theodoituong";
		listkhoiBHYTB141.clear();
		
	}
	
	/**
	 * Method tra ve String truyen tham so vao file jrxml
	 */
	private String getListDVMaParamsHelp(List<String> inputs){
        log.info("Vao method getListDVMaParamsHelp ");
        log.info("Inputs: " + inputs.toString());
        if(inputs==null || inputs.size()==0) return null;
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
		
		return "B160_Chonmenuxuattaptin";
	}
	
	/**
	 * ham them mot phan tu vao DS
	 */
	public void enter(){
		log.info("bat dau them du lieu vao luoi: " + maKhoiBHYT);
		DtDmKhoiBhyt khoiBHYT;
		boolean test=false;
		DieuTriUtilDelegate dtutilDelegate=DieuTriUtilDelegate.getInstance();
		if(!maKhoiBHYT.equals("")){
			if((listkhoiBHYTB141.size()==0) && (maKhoiBHYT !=null)){
				log.info("size list bang 0");
				Object obj=dtutilDelegate.findByMa(maKhoiBHYT , "DtDmKhoiBhyt", "dtdmkhoibhytMa");
				khoiBHYT=(DtDmKhoiBhyt)obj;
				listkhoiBHYTB141.add(khoiBHYT);
				log.info("da add phan tu dau tien" + listkhoiBHYTB141.size());
			}else if ((listkhoiBHYTB141.size()>0) && (maKhoiBHYT !=null)){
				log.info("size list lon hon 0");
				for(DtDmKhoiBhyt item:listkhoiBHYTB141){
					if(item.getDtdmkhoibhytMa().equals(maKhoiBHYT)){
						test=true;
					}
				}
				if(test == false){
					Object obj=dtutilDelegate.findByMa(maKhoiBHYT , "DtDmKhoiBhyt", "dtdmkhoibhytMa");
					khoiBHYT=(DtDmKhoiBhyt)obj;
					listkhoiBHYTB141.add(khoiBHYT);
				}
				log.info("da add phan tu: " + listkhoiBHYTB141.size());
			}
		}
		setMaKhoiBHYT("");
	}
	
	
	/**
	 * Ham xoa mot record
	 */
	public void deletedmkhoiBHYT(){
		log.info("bat dau xoa , size" + listkhoiBHYTB141.size());
		listkhoiBHYTB141.remove(dmkhoiBHYTB3332Selected);
		log.info("da xoa , size" + listkhoiBHYTB141.size());
		log.info("ket thuc xoa");
	} 
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeTD="B141_Phantichbenhnhankhambenh";
		log.info("Vao Method XuatReport B141_Phantichbenhnhankhambenh");
		String baocao1=null;
		String pathTemplate = null;
		try {
			log.info("bat dau method XuatReport ");
			if(chonloaibc.equals("theodoituong")){
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/T04_Phantichbenhnhankhambenhtheodoituong.jrxml";
			}else if(chonloaibc.equals("theodoituongBHYT")){
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/T05_PhantichbenhnhankhambenhtheoDTBHYT.jrxml";
			}else if(chonloaibc.equals("theonoidangkyKCBBD")){
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/T06_PhantichbenhnhankhambenhtheoNoiDKKCBBD.jrxml";
			}else if(chonloaibc.equals("theotuyen")){
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/T07_PhantichbenhnhankhambenhtheoTuyen.jrxml";
			}else if(chonloaibc.equals("theobankham")){
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/T08_Phantichbenhnhankhambenhtheobankham.jrxml";
			}
			log.info("da thay file templete " + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			Map<String, Object> params = new HashMap<String, Object>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/MM");
			params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("benhvien", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("sodt", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			
			Calendar cal=Calendar.getInstance();
			
			Date dateTemp = sdf.parse(tungay);
			log.info("*****dateTemp "+ dateTemp);
			cal.setTime(dateTemp);
			String sdate=cal.get(Calendar.YEAR)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.DATE);
			
			params.put("STUNGAY", sdate);
			log.info("*****STUNGAY "+ sdate);
			params.put("tungay" , dateTemp);
			log.info("*****tungay "+ tungay);
			
			dateTemp = sdf.parse(denngay);
			log.info("*****dateTemp "+ dateTemp);
			cal.setTime(dateTemp);
			sdate= cal.get(Calendar.YEAR)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.DATE);
			
			params.put("SDENNGAY", sdate);
			log.info("*****SDENNGAY "+ sdate);
			params.put("denngay", dateTemp);
			log.info("******denngay "+ denngay);

			
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			
			if(listkhoiBHYTB141.size()>0){
				List<String> listtemp=new ArrayList<String>();
				for(DtDmKhoiBhyt item:listkhoiBHYTB141){
					listtemp.add(item.getDtdmkhoibhytMa());
				}
				params.put("khoi", getListDVMaParamsHelp(listtemp));
				log.info("list phan loai " + getListDVMaParamsHelp(listtemp));
				
				params.put("tenkhoi", getListDVMaParamsHelp(listtemp));
				
			}
//			else
//			{
//				params.put("khoi", null);
//			}
			
			if (maDoiTuong != null && !maDoiTuong.equals("")) {
				params.put("MaDoiTuong", maDoiTuong);
				log.info("***** MaDoiTuong: " + maDoiTuong);
			}
//			else {
//				params.put("MaDoiTuong", null);
//			}


			params.put("loaikhambenh", loaikhambenh); // 'noccl' or 'ccl'
			log.info("***** loaikhambenh: " + loaikhambenh);

			if (bankhamMa != null && !bankhamMa.equals("")) {
				params.put("bankhamma", bankhamMa);
				log.info("***** bankhamma: " + bankhamMa);
			}else {
				params.put("bankhamma", null);
				log.info("*****bankhamma: " + bankhamMa);
			}
			
			if (bankhamMaSo != null && !bankhamMaSo.equals("")){
				params.put("bankhammaso", bankhamMaSo);
				log.info("***** bankhamMaSo: " + bankhamMaSo);
			}else {
				params.put("bankhammaso", null);
				log.info("***** bankhamMaSo: " + bankhamMaSo);
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
			    jasperPrintTD =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintTD,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"tiepdon/", "pdf","B141_Phantichbenhnhankhambenh");
			    reportPathTD=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathTD);
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


	public Integer getMaSoKhoiBHYT() {
		return maSoKhoiBHYT;
	}


	public void setMaSoKhoiBHYT(Integer maSoKhoiBHYT) {
		this.maSoKhoiBHYT = maSoKhoiBHYT;
	}


	public String getMaKhoiBHYT() {
		return maKhoiBHYT;
	}


	public void setMaKhoiBHYT(String maKhoiBHYT) {
		this.maKhoiBHYT = maKhoiBHYT;
	}


	public Integer getTuyen() {
		return tuyen;
	}


	public void setTuyen(Integer tuyen) {
		this.tuyen = tuyen;
	}
	public String getChonloaibc() {
		return chonloaibc;
	}
	public void setChonloaibc(String chonloaibc) {
		this.chonloaibc = chonloaibc;
	}
	public String getLoaikhambenh() {
		return loaikhambenh;
	}
	public void setLoaikhambenh(String loaikhambenh) {
		this.loaikhambenh = loaikhambenh;
	}


	

}

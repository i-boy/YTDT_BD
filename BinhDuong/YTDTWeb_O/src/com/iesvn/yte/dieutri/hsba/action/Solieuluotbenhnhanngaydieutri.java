/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

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
import com.iesvn.yte.entity.DmBenhIcd;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.util.IConstantsRes;

@Name("B246_Luotbenhnhanngaydieutri")
@Scope(SESSION)
public class Solieuluotbenhnhanngaydieutri implements Serializable {
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
	JasperPrint jasperPrintDT = null;
	
	
	@DataModel
	private List<DmBenhIcd> listDmBenhICD = new ArrayList<DmBenhIcd>();

	@DataModelSelection("listDmBenhICD")
	@Out(required = false)
	private DmBenhIcd dmbenhICDSelect;
	
	@DataModel
	private List<DmKhoa> listKhoa = new ArrayList<DmKhoa>();

	@DataModelSelection("listKhoa")
	@Out(required = false)
	private DmKhoa dmkhoaSelect;
	
	/*
	 * bien chua tieu chi xuat bao cao
	 */
	private String tungay=null;
	private String denngay=null;
	private Integer tuoi_tu;  
	private Integer tuoi_den;
	private String doituong_ma=null;
	private String ketqua_ma=null;
	private String khoa_ma=null;
	private String tainan_ma=null;
	private Integer doituong_maso=null;
	private Integer ketqua_maso=null;
	private Integer khoa_maso=null;
	private Integer tainan_maso=null;
	private String locdulieu_ma=null;
	private String chon=null;
	private String gtselect=null;
	private int index=0;
	private String benhICDMa=null;
	private String khoaMa=null;
	
	
	
	/**
	 * ham them mot phan tu vao DS benh chinh
	 */
	public void enterDmBenhICD(){
		log.info("bat dau them du lieu vao luoi");
		DmBenhIcd benhicd;
		boolean test=false;
		DieuTriUtilDelegate dtutilDelegate=DieuTriUtilDelegate.getInstance();
		if((listDmBenhICD.size()==0) && (getBenhICDMa() !=null)){
			log.info("size list bang 0");
			Object obj=dtutilDelegate.findByMa(getBenhICDMa() , "DmBenhIcd", "dmbenhicdMa");
			benhicd=(DmBenhIcd)obj;
			listDmBenhICD.add(benhicd);
			log.info("da add phan tu dau tien" + listDmBenhICD.size());
		}else if ((listDmBenhICD.size()>0) && (getBenhICDMa() !=null)){
			log.info("size list lon hon 0");
			for(DmBenhIcd item:listDmBenhICD){
				if(item.getDmbenhicdMa().equals(getBenhICDMa())){
					test=true;
				}
			}
			if(test == false){
				Object obj=dtutilDelegate.findByMa(getBenhICDMa() ,"DmBenhIcd", "dmbenhicdMa");
				benhicd=(DmBenhIcd)obj;
				listDmBenhICD.add(benhicd);
			}
			log.info("da add phan tu" + listDmBenhICD.size());
		}
	}
	
	
	/**
	 * Ham xoa mot record trong ds Benh Chinh
	 */
	public void deleteDmBenhICD(){
		log.info("bat dau xoa , size" + listDmBenhICD.size());
		listDmBenhICD.remove(dmbenhICDSelect);
		log.info("da xoa , size" + listDmBenhICD.size());
		log.info("ket thuc xoa");
	}
	
	/**
	 * ham them mot phan tu vao DS Khoa
	 */
	public void enterDmKhoa(){
		log.info("bat dau them du lieu vao luoi");
		DmKhoa khoa;
		boolean test=false;
		DieuTriUtilDelegate dtutilDelegate=DieuTriUtilDelegate.getInstance();
		if((listKhoa.size()==0) && (getKhoaMa() !=null)){
			log.info("size list bang 0");
			Object obj=dtutilDelegate.findByMa(getKhoaMa() , "DmKhoa", "dmkhoaMa");
			khoa=(DmKhoa)obj;
			listKhoa.add(khoa);
			log.info("da add phan tu dau tien" + listKhoa.size());
		}else if ((listKhoa.size()>0) && (getKhoaMa() !=null)){
			log.info("size list lon hon 0");
			for(DmKhoa item:listKhoa){
				if(item.getDmkhoaMa().equals(getKhoaMa())){
					test=true;
				}
			}
			if(test == false){
				Object obj=dtutilDelegate.findByMa(getKhoaMa() , "DmKhoa", "dmkhoaMa");
				khoa=(DmKhoa)obj;
				listKhoa.add(khoa);
			}
			log.info("da add phan tu" + listKhoa.size());
		}
	}
	
	
	/**
	 * Ham xoa mot record trong ds khoa
	 */
	public void deleteDmKhoa(){
		log.info("bat dau xoa , size" + listKhoa.size());
		listKhoa.remove(dmkhoaSelect);
		log.info("da xoa , size" + listKhoa.size());
		log.info("ket thuc xoa");
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
	
	
	/*
	 * 
	 * 
	 * chon bao cao cua huyen hay xa
	 */
	public String thuchienAction(){
		XuatReport();
		return "B260_Chonmenuxuattaptin";
	}
	
	/*
	 * thuc hien khi nhan nut chon lai
	 */
	public void chonlaiAction(){
		setTungay("");
		setDenngay("");
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		loaiBCDT="BCSolieuluotBNngayDT";
		jasperPrintDT=null;
		duongdanStrDT=null;
		log.info("Vao Method XuatReport bao cao so lieu benh nhan luot dieu tri");
		String resultReportName = null;
		String baocaotuvong=null;
		try {
			log.info("bat dau method XuatReport ");
			String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/B22_Luotdieutritheoicd10.jrxml";
			log.info("da thay file templete" + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
 			
			Map<String, Object> params = new HashMap<String, Object>();
				log.info("=================");
				Class.forName("oracle.jdbc.OracleDriver");
			    log.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintDT =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocaotuvong=XuatReportUtil.ReportUtil(jasperPrintDT,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"hsba/", "pdf","Solieuluotbenhnhanngaydieutri");
			    duongdanStrDT=baocaotuvong.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocaotuvong);
			    log.info("duong dan -------------------- :"+duongdanStrDT);
			    index+=1;
			    log.info("Index :" + index);
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Output: " + resultReportName);
	    log.info("Thoat Method XuatReport");
	}

	public void setTungay(String tungay) {
		this.tungay = tungay;
	}

	public String getTungay() {
		return tungay;
	}

	public void setDenngay(String denngay) {
		this.denngay = denngay;
	}

	public String getDenngay() {
		return denngay;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public String getDoituong_ma() {
		return doituong_ma;
	}

	public void setDoituong_ma(String doituong_ma) {
		this.doituong_ma = doituong_ma;
	}

	public String getKetqua_ma() {
		return ketqua_ma;
	}

	public void setKetqua_ma(String ketqua_ma) {
		this.ketqua_ma = ketqua_ma;
	}

	public String getKhoa_ma() {
		return khoa_ma;
	}

	public void setKhoa_ma(String khoa_ma) {
		this.khoa_ma = khoa_ma;
	}

	public String getTainan_ma() {
		return tainan_ma;
	}

	public void setTainan_ma(String tainan_ma) {
		this.tainan_ma = tainan_ma;
	}

	public String getLocdulieu_ma() {
		return locdulieu_ma;
	}

	public void setLocdulieu_ma(String locdulieu_ma) {
		this.locdulieu_ma = locdulieu_ma;
	}

	public Integer getTuoi_tu() {
		return tuoi_tu;
	}

	public void setTuoi_tu(Integer tuoi_tu) {
		this.tuoi_tu = tuoi_tu;
	}

	public Integer getTuoi_den() {
		return tuoi_den;
	}

	public void setTuoi_den(Integer tuoi_den) {
		this.tuoi_den = tuoi_den;
	}

	public Integer getDoituong_maso() {
		return doituong_maso;
	}

	public void setDoituong_maso(Integer doituong_maso) {
		this.doituong_maso = doituong_maso;
	}

	public Integer getKetqua_maso() {
		return ketqua_maso;
	}

	public void setKetqua_maso(Integer ketqua_maso) {
		this.ketqua_maso = ketqua_maso;
	}

	public Integer getKhoa_maso() {
		return khoa_maso;
	}

	public void setKhoa_maso(Integer khoa_maso) {
		this.khoa_maso = khoa_maso;
	}

	public Integer getTainan_maso() {
		return tainan_maso;
	}

	public void setTainan_maso(Integer tainan_maso) {
		this.tainan_maso = tainan_maso;
	}

	public void setGtselect(String gtselect) {
		this.gtselect = gtselect;
	}

	public String getGtselect() {
		return gtselect;
	}

	public void setChon(String chon) {
		this.chon = chon;
	}

	public String getChon() {
		return chon;
	}


	public void setBenhICDMa(String benhICDMa) {
		this.benhICDMa = benhICDMa;
	}


	public String getBenhICDMa() {
		return benhICDMa;
	}


	public void setKhoaMa(String khoaMa) {
		this.khoaMa = khoaMa;
	}


	public String getKhoaMa() {
		return khoaMa;
	}
}

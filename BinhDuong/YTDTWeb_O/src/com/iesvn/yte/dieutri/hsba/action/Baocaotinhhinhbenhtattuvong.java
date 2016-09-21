/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.jboss.seam.annotations.Create;
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
import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmTaiNan;
import com.iesvn.yte.util.IConstantsRes;

@Name("BaoCaoTinhHinhBenhTatTuVong")
@Scope(SESSION)
public class Baocaotinhhinhbenhtattuvong implements Serializable {
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
	private List<DmDoiTuong> listDoituong = new ArrayList<DmDoiTuong>();

	@DataModelSelection("listDoituong")
	@Out(required = false)
	private DmDoiTuong dmdoituongSelect;
	
	@DataModel
	private List<DmKhoa> listKhoa = new ArrayList<DmKhoa>();

	@DataModelSelection("listKhoa")
	@Out(required = false)
	private DmKhoa dmkhoaSelect;
	
	/*
	 * bien chua tieu chi xuat bao cao
	 */
	private Integer tuoi_tu;  
	private Integer tuoi_den;
	private String gioitinh=null;
	private String tungay=null;
	private String denngay=null;
	private String chon=null;
	private static int index=0;
	private Integer khoa_maso=null;
	private Integer doituong_maso=null;
	private Integer tainan_maso=null;
	private String khoa_ma=null;
	private String doituong_ma=null;
	private String tainan_ma=null;
	private String gtSelect;
	private String kqdtMa=null;
	private String benhICDMa=null;
	private String dtMa=null;
	private String khoaMa=null;
	
	
	@Create
	public void init(){
		setGtSelect("namnu");
	}
	
	/*
	 * chon bao cao
	 */
	public String thuchienAction() throws ParseException{
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
	 * ham them mot phan tu vao DS benh chinh
	 */
	public void enterDmBenhICD(){
		log.info("bat dau them du lieu vao luoi");
		DmBenhIcd benhicd;
		boolean test=false;
		DieuTriUtilDelegate dtutilDelegate=DieuTriUtilDelegate.getInstance();
		if((listDmBenhICD.size()==0) && (benhICDMa !=null)){
			log.info("size list bang 0");
			Object obj=dtutilDelegate.findByMa(benhICDMa , "DmBenhIcd", "dmbenhicdMa");
			benhicd=(DmBenhIcd)obj;
			listDmBenhICD.add(benhicd);
			log.info("da add phan tu dau tien" + listDmBenhICD.size());
		}else if ((listDmBenhICD.size()>0) && (benhICDMa !=null)){
			log.info("size list lon hon 0");
			for(DmBenhIcd item:listDmBenhICD){
				if(item.getDmbenhicdMa().equals(benhICDMa)){
					test=true;
				}
			}
			if(test == false){
				Object obj=dtutilDelegate.findByMa(benhICDMa ,"DmBenhIcd", "dmbenhicdMa");
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
	 * ham them mot phan tu vao DS doi tuong
	 */
	public void enterDmDoiTuong(){
		log.info("bat dau them du lieu vao luoi");
		DmDoiTuong doituong;
		boolean test=false;
		DieuTriUtilDelegate dtutilDelegate=DieuTriUtilDelegate.getInstance();
		if((listDoituong.size()==0) && (dtMa !=null)){
			log.info("size list bang 0");
			Object obj=dtutilDelegate.findByMa(dtMa , "DmDoiTuong", "dmdoituongMa");
			doituong=(DmDoiTuong)obj;
			listDoituong.add(doituong);
			log.info("da add phan tu dau tien" + listDoituong.size());
		}else if ((listDoituong.size()>0) && (dtMa !=null)){
			log.info("size list lon hon 0");
			for(DmDoiTuong item:listDoituong){
				if(item.getDmdoituongMa().equals(dtMa)){
					test=true;
				}
			}
			if(test == false){
				Object obj=dtutilDelegate.findByMa(dtMa , "DmDoiTuong", "dmdoituongMa");
				doituong=(DmDoiTuong)obj;
				listDoituong.add(doituong);
			}
			log.info("da add phan tu" + listDoituong.size());
		}
	}
	
	
	/**
	 * Ham xoa mot record trong ds doi tuong
	 */
	public void deleteDmDoiTuong(){
		log.info("bat dau xoa , size" + listDoituong.size());
		listDoituong.remove(dmdoituongSelect);
		log.info("da xoa , size" + listDoituong.size());
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
		if((listKhoa.size()==0) && (khoaMa !=null)){
			log.info("size list bang 0");
			Object obj=dtutilDelegate.findByMa(khoaMa , "DmKhoa", "dmkhoaMa");
			khoa=(DmKhoa)obj;
			listKhoa.add(khoa);
			log.info("da add phan tu dau tien" + listKhoa.size());
		}else if ((listKhoa.size()>0) && (khoaMa !=null)){
			log.info("size list lon hon 0");
			for(DmKhoa item:listKhoa){
				if(item.getDmkhoaMa().equals(khoaMa)){
					test=true;
				}
			}
			if(test == false){
				Object obj=dtutilDelegate.findByMa(khoaMa , "DmKhoa", "dmkhoaMa");
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
	
	/**
	 * xuat report 
	 * @throws ParseException 
	 */
	public void XuatReport() throws ParseException {
		loaiBCDT="BCTHTuVong";
		jasperPrintDT=null;
		duongdanStrDT=null;
		log.info("chon la :" + getChon());
		log.info("Vao Method XuatReport bao cao benh tat tu vong");
		String resultReportName = null;
		String baocaotuvong=null;
		String pathTemplate=null;
		String tenfilexuat=null;
		//tao param truyen vao bao cao
		Map<String, Object> params = new HashMap<String, Object>();
		if(!tungay.equals("")){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			params.put("TUNGAY", tungay);
			params.put("TUNGAY_DATE", sdf.parse(tungay));
			if(!denngay.equals("")){
				params.put("DENNGAY", denngay);
				params.put("DENNGAY_DATE", sdf.parse(denngay));
			}
		}else{
			params.put("TUNGAY_DATE", null);
			params.put("DENNGAY_DATE", null);
		}
		//truyen tham so vao benh vien
		log.info("banh vien xuat bao cao "+ IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
		params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
		params.put("DIACHI", IConstantsRes.REPORT_DIEUTRI_DIA_CHI);
		DieuTriUtilDelegate dtutilDelegate=DieuTriUtilDelegate.getInstance();
		if(chon!=null){
			if(chon.equals(new String("5"))){
				log.info("tre em duoi 7 ngay" );
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/B19_DSBenhnhi7ngaytuoituvong.jrxml";
				tenfilexuat="Baocaotinhhinhbenhtattuvong7ngay";
				params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			}else if(chon.equals(new String("6"))){
				log.info("tre em duoi duoi 28 ngay" );
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/B19_DSBenhnhi28ngaytuoituvong.jrxml";
				tenfilexuat="Baocaotinhhinhbenhtattuvong28ngay";
				params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			}else{
				if(chon.equals(new String("0"))){
					log.info("--------Theo ket qua theo benh chinh dc chon--------");
					if(listDmBenhICD.size()>0){
						List<String> listTemp=new ArrayList<String>();
						for(DmBenhIcd item:listDmBenhICD){
							listTemp.add(item.getDmbenhicdMa());
						}
						String benhicd=getListDVMaParamsHelp(listTemp);
						log.info("danh sach benh icd" + benhicd);
						params.put("LISTBENHICD", benhicd);
					}
				}
				
				
				if(chon.equals(new String("2"))){
					log.info("--------Theo ket qua theo doi tuong dc chon--------");
					if(listDoituong.size()>0){
						List<String> listTemp=new ArrayList<String>();
						for(DmDoiTuong item:listDoituong){
							listTemp.add(item.getDmdoituongMa());
						}
						String doituongstr=getListDVMaParamsHelp(listTemp);
						log.info("danh sach doi tuong" + doituongstr);
						params.put("LISTDOITUONG", doituongstr);
					}
				}
				if(chon.equals(new String("4"))){
					log.info("--------Theo ket qua theo khoa dc chon--------");
					if(listKhoa.size()>0){
						List<String> listTemp=new ArrayList<String>();
						for(DmKhoa item:listKhoa){
							listTemp.add(item.getDmkhoaMa());
						}
						String khoastr=getListDVMaParamsHelp(listTemp);
						log.info("danh sach khoa" + khoastr);
						params.put("LISTKHOA", khoastr);
					}
				}
				log.info("khac" );
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/B18_DSBenhnhatuvong.jrxml";
				tenfilexuat="Baocaotinhhinhbenhtattuvong";
				//tim khoa
				if(khoa_maso!=null){
					log.info("khoa ma " + khoa_maso );
					params.put("KHOAMASO", new Integer(khoa_maso));
					DmKhoa dmkhoa=new DmKhoa();
					Object obj=dtutilDelegate.findByMa(khoa_ma, "DmKhoa", "dmkhoaMa");
					dmkhoa=(DmKhoa)obj;
					log.info("Dm khoa tim thay"  +dmkhoa);
					params.put("KHOA", dmkhoa.getDmkhoaTen());
				}else{
					params.put("KHOA", "\ufeffTo\u00e0n vi\u1ec7n");
				}
				//tim doi tuong
				if(doituong_maso!=null){
					log.info("doi tuong " + doituong_maso);
					params.put("DOITUONGMASO", doituong_maso);
				}
				//tim tai nan
				if(tainan_maso!=null){
					log.info("tai nan " + tainan_maso);
					params.put("TAINANMASO", tainan_maso);
					DmTaiNan dmtainan=new DmTaiNan();
					Object obj=dtutilDelegate.findByMa(tainan_ma, "DmTaiNan", "dmtainanMa");
					dmtainan=(DmTaiNan)obj;
					log.info("Dm tai nam tim thay"  +dmtainan);
					params.put("TAINAN", dmtainan.getDmtainanTen());
				}
				//Truyen tham so vao gioi tinh
				if(gtSelect !=null){
					if(gtSelect.equals("nam")){
						log.info("1");
						params.put("GTMASO", new Integer(2));
						params.put("GIOITINH", "Nam");
					}else if(gtSelect.equals("nu")){
						log.info("2");
						params.put("GTMASO", new Integer(1));
						params.put("GIOITINH", "\ufeffN\u1eef");
					}
				}
				
				//truyen tham so tuoi vao report
				if(tuoi_tu !=null){
					params.put("TUOITU", tuoi_tu);
					params.put("TUOIDEN", tuoi_den);
				}else{
					params.put("TUOITU", null);
					params.put("TUOIDEN", null);
				}
				
			}
		}
	
		try {
			log.info("bat dau method XuatReport ");
			log.info("da thay file templete" + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			
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
			    baocaotuvong=XuatReportUtil.ReportUtil(jasperPrintDT,Baocaotinhhinhbenhtattuvong.index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"hsba/", "pdf",tenfilexuat);
			    duongdanStrDT=baocaotuvong.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocaotuvong);
			    log.info("duong dan -------------------- :"+duongdanStrDT);
			    Baocaotinhhinhbenhtattuvong.index+=1;
			    log.info("Index :" + getIndex());
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Output: " + resultReportName);
	    log.info("Thoat Method XuatReport");
	}

	public static void setIndex(int index) {
		Baocaotinhhinhbenhtattuvong.index = index;
	}

	public static int getIndex() {
		return index;
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


	public void setChon(String chon) {
		this.chon = chon;
	}

	public String getChon() {
		return chon;
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

	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}

	public String getGioitinh() {
		return gioitinh;
	}

	public Integer getKhoa_maso() {
		return khoa_maso;
	}

	public void setKhoa_maso(Integer khoa_maso) {
		this.khoa_maso = khoa_maso;
	}

	public Integer getDoituong_maso() {
		return doituong_maso;
	}

	public void setDoituong_maso(Integer doituong_maso) {
		this.doituong_maso = doituong_maso;
	}

	public Integer getTainan_maso() {
		return tainan_maso;
	}

	public void setTainan_maso(Integer tainan_maso) {
		this.tainan_maso = tainan_maso;
	}

	public String getKhoa_ma() {
		return khoa_ma;
	}

	public void setKhoa_ma(String khoa_ma) {
		this.khoa_ma = khoa_ma;
	}

	public String getDoituong_ma() {
		return doituong_ma;
	}

	public void setDoituong_ma(String doituong_ma) {
		this.doituong_ma = doituong_ma;
	}

	public String getTainan_ma() {
		return tainan_ma;
	}

	public void setTainan_ma(String tainan_ma) {
		this.tainan_ma = tainan_ma;
	}

	public void setGtSelect(String gtSelect) {
		this.gtSelect = gtSelect;
	}

	public String getGtSelect() {
		return gtSelect;
	}

	public void setKqdtMa(String kqdtMa) {
		this.kqdtMa = kqdtMa;
	}

	public String getKqdtMa() {
		return kqdtMa;
	}

	public void setBenhICDMa(String benhICDMa) {
		this.benhICDMa = benhICDMa;
	}

	public String getBenhICDMa() {
		return benhICDMa;
	}

	public void setDtMa(String dtMa) {
		this.dtMa = dtMa;
	}

	public String getDtMa() {
		return dtMa;
	}

	public void setKhoaMa(String khoaMa) {
		this.khoaMa = khoaMa;
	}

	public String getKhoaMa() {
		return khoaMa;
	}
}

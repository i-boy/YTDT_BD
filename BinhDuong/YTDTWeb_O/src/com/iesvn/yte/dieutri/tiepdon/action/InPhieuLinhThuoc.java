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
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.entity.DtDmBanKham;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.util.IConstantsRes;
@Scope(CONVERSATION)
@Name("B123_Inphieulinhthuoc")
@Synchronized(timeout = 6000000)
public class InPhieuLinhThuoc  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Logger
	private Log log;
	
	private String maBanKham;
	private Integer maSoBanKham;
	
	int index = 0;
	
	private String tugio = null;
	private String dengio = null;
	
	private String tungay=null;
	private String denngay=null;
	
	private String ngayLapPhieu=null;
	
	private String sotiepdonStr="";
	
	
	@Out(required=false)
	@In(required=false)
	private String reportPathTD=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeTD=null;
	
		
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintTD = null; 
	
	@Begin(join = true)
	public String init(){
		resetForm();
		return "TiepDon_KhamBenh_InPhieuLinhThuoc";
	}
	@End 
	public void end(){
		
	}
	
	private String ngayhientai="";
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		
		tungay = sdf.format(currentDate);
		denngay = sdf.format(currentDate);
		ngayLapPhieu= sdf.format(currentDate);
		
		ngayhientai= sdf.format(currentDate);
		maBanKham = "";
		
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		
		sdf = new SimpleDateFormat("HH:mm");
		tugio= sdf.format(cal.getTime());
		dengio= sdf.format(currentDate);
		hotenBNStr="";
		sotiepdonStr="";
		listBN.clear();
	}
	@DataModel
	private List<TiepDon> listBN = new ArrayList<TiepDon>();

	@DataModelSelection("listBN")
	@Out(required = false)
	private TiepDon tiepDonSelect;
	
	private TiepDon tiepDon = null;
	
	private String hotenBNStr="";
	/**
	 * Lay ten benh nhan dua vao so vao vien
	 */
	public void loadTiepdon() {
		log.info("-----So vao vien-----" + sotiepdonStr);
		tiepDon = new TiepDon();
		if (!sotiepdonStr.equals("")) {
			try {
				TiepDonDelegate tiepdonDAO = TiepDonDelegate.getInstance();
				tiepDon = tiepdonDAO.find(sotiepdonStr);
				if (tiepDon == null) {
					FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND, sotiepdonStr);
					return;
				}else{
					hotenBNStr  = tiepDon.getBenhnhanMa().getBenhnhanHoten();
				}	
			} catch (Exception e) {
				FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND, sotiepdonStr);
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ham them mot phan tu vao DS
	 */
	public void enter(){
		log.info("bat dau them du lieu vao luoi");
		boolean test=false;
		log.info("tiepDon:"+tiepDon);
		if(tiepDon != null){
			log.info("tiepDon2:"+tiepDon);
			if(listBN.size()==0){
				log.info("size list bang 0");
				listBN.add(tiepDon);
				log.info("da add phan tu dau tien" + listBN.size());
			}else if (listBN.size()>0){
				log.info("size list lon hon 0");
				for(TiepDon item:listBN){
					if(item.getTiepdonMa().equals(sotiepdonStr)){
						test=true;
					}
				}
				if(test == false){
					listBN.add(tiepDon);
				}
				log.info("da add phan tu" + listBN.size());
			}
		}
		sotiepdonStr = "";
		setHotenBNStr("");
		tiepDon = null;
		log.info("ketthuc them du lieu vao luoi");
	}
	
	public String thuchienAction(){
		XuatReport();
		//resetFormB124= null ; 
		return "B160_Chonmenuxuattaptin";
	}
	
	/**
	 * Ham xoa mot record
	 */
	public void deleteBN(){
		log.info("bat dau xoa , size" + listBN.size());
		listBN.remove(tiepDonSelect);
		log.info("da xoa , size" + listBN.size());
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
                        result += "'"+each + "', ";
        }
        result = result.substring(0, result.length()-2);
        log.info("Thoat method getListDVMaParamsHelp ");
        log.info("Output: " + result);
        return result;
	}
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeTD="B123_Inphieulinhthuoc";
		log.info("Vao Method XuatReport B123_Inphieulinhthuoc");
		String baocao1=null;
		String pathTemplate=null;
		try {
			
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/T15_Inphieulinhthuoc.jrxml";
			
			
			
			log.info("da thay file templete 5" + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Map<String, Object> params = new HashMap<String, Object>();
			
			
			DtDmBanKham dtDmBanKham = new DtDmBanKham();
			DieuTriUtilDelegate dtUtilDAO = DieuTriUtilDelegate.getInstance();
			Object obj = dtUtilDAO.findByMa( maBanKham , "DtDmBanKham" , "dtdmbankhamMa");
			dtDmBanKham = (DtDmBanKham)obj;
			
			params.put("BANKHAMMASO", maSoBanKham );
			params.put("BANKHAMTEN", dtDmBanKham.getDtdmbankhamTen() );
			
			
			params.put("NGAYLAPPHIEU", sdf.parse( ngayLapPhieu));
			
			
			Date dTuNgay = null;
			Date dDenNgay = null;
			
			if (tungay != null && !tungay.equals("")){
				if (tugio == null || tugio.equals("")){
					tugio = "00:00";
				}
				dTuNgay = com.iesvn.yte.util.Utils.getDBDate(tugio, tungay).getTime();
			}
			if (denngay != null && !denngay.equals("")){
				if (dengio == null || dengio.equals("")){
					dengio = "23:59";
				}
				dDenNgay = com.iesvn.yte.util.Utils.getDBDate(dengio, denngay).getTime();
			}
			
			
			params.put("NGAYDUNGTU", dTuNgay);
			params.put("NGAYDUNGDEN", dDenNgay);
			
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("donvi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			
			
			if(listBN.size()>0){
				List<String> listtemp=new ArrayList<String>();
				for(TiepDon item:listBN){
					listtemp.add(item.getTiepdonMa());
				}
				params.put("MATIEPDON", getListDVMaParamsHelp(listtemp));
				log.info("list phan loai " + getListDVMaParamsHelp(listtemp));
			}else{
				
				params.put("MATIEPDON", null);
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintTD,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"tiepdon/", "pdf","B123_Inphieulinhthuoc");
			    reportPathTD=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathTD);
			    index+= 1;
			    log.info("Index :" + getIndex());
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Thoat Method XuatReport");
	}

	
	public String getMaBanKham() {
		return maBanKham;
	}
	public void setMaBanKham(String maBanKham) {
		this.maBanKham = maBanKham;
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
	public String getNgayLapPhieu() {
		return ngayLapPhieu;
	}
	public void setNgayLapPhieu(String ngayLapPhieu) {
		this.ngayLapPhieu = ngayLapPhieu;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getTugio() {
		return tugio;
	}

	public void setTugio(String tugio) {
		this.tugio = tugio;
	}

	public String getDengio() {
		return dengio;
	}

	public void setDengio(String dengio) {
		this.dengio = dengio;
	}

	public String getReportPathTD() {
		return reportPathTD;
	}

	public void setReportPathTD(String reportPathTD) {
		this.reportPathTD = reportPathTD;
	}

	public String getReportTypeTD() {
		return reportTypeTD;
	}

	public void setReportTypeTD(String reportTypeTD) {
		this.reportTypeTD = reportTypeTD;
	}

	public JasperPrint getJasperPrintTD() {
		return jasperPrintTD;
	}

	public void setJasperPrintTD(JasperPrint jasperPrintTD) {
		this.jasperPrintTD = jasperPrintTD;
	}
	public String getSotiepdonStr() {
		return sotiepdonStr;
	}
	public void setSotiepdonStr(String sotiepdonStr) {
		this.sotiepdonStr = sotiepdonStr;
	}
	public TiepDon getTiepDon() {
		return tiepDon;
	}
	public void setTiepDon(TiepDon tiepDon) {
		this.tiepDon = tiepDon;
	}
	public String getHotenBNStr() {
		return hotenBNStr;
	}
	public void setHotenBNStr(String hotenBNStr) {
		this.hotenBNStr = hotenBNStr;
	}
	public Integer getMaSoBanKham() {
		return maSoBanKham;
	}
	public void setMaSoBanKham(Integer maSoBanKham) {
		this.maSoBanKham = maSoBanKham;
	}
	
	
	public List<TiepDon> getListBN() {
		return listBN;
	}
	public void setListBN(List<TiepDon> listBN) {
		this.listBN = listBN;
	}
	public TiepDon getTiepDonSelect() {
		return tiepDonSelect;
	}
	public void setTiepDonSelect(TiepDon tiepDonSelect) {
		this.tiepDonSelect = tiepDonSelect;
	}
	public String getNgayhientai() {
		return ngayhientai;
	}
	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
	}
	

}

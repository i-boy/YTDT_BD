package com.iesvn.yte.dieutri.tiepdon.action;

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

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.faces.FacesMessages;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.GiayNghiViecHuongBhxhDelegate;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
import com.iesvn.yte.dieutri.entity.GiayNghiViecHuongBhxh;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B116_Giaychungnhan")
@Synchronized(timeout = 6000000)
public class GiayChungNhanAction implements Serializable {

	private static String FORMAT_DATE = "dd/MM/yyyy";
	private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";

	private static final long serialVersionUID = 10L;
	private int stt;
	private String ngaySinh;
	private String thoiGian;
	private String gioThamKham;
	
	private GiayNghiViecHuongBhxhDelegate gnvhbhxhDAO;
	
	private static Logger log = Logger.getLogger(GiayChungNhanAction.class);
	
	@In(required = false)
	@Out(required = false)
	private String maBanKhamOut;
	
	@In(required = false)
	@Out(required = false)
	private String maTiepDonOut;
	
	@In(required = false)
	@Out(required = false)
	private String goToGiayChungNhan;
	
	@Out(required = false)
	private String donViCongTac;
	
	@Out(required = false)
	private String lyDoNghiViec;
	
	@Out(required = false)
	private String soNgayChoNghi;
	
	@Out(required = false)
	private String tuNgay;
	
	@Out(required = false)
	private String denNgay;
	
	@In(required = false)
	@Out(required = false)
	private String bacSiKCB;
	
	private SimpleDateFormat formatter;
	
	public String getLyDoNghiViec() {
		return lyDoNghiViec;
	}

	public void setLyDoNghiViec(String lyDoNghiViec) {
		this.lyDoNghiViec = lyDoNghiViec;
	}
	
	public String getDonViCongTac() {
		return donViCongTac;
	}

	public void setDonViCongTac(String donViCongTac) {
		this.donViCongTac = donViCongTac;
	}

	public String getSoNgayChoNghi() {
		return soNgayChoNghi;
	}

	public void setSoNgayChoNghi(String soNgayChoNghi) {
		this.soNgayChoNghi = soNgayChoNghi;
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

	public String getBacSiKCB() {
		return bacSiKCB;
	}

	public void setBacSiKCB(String bacSiKCB) {
		this.bacSiKCB = bacSiKCB;
	}

	public String getGoToGiayChungNhan() {
		return goToGiayChungNhan;
	}

	public void setGoToGiayChungNhan(String goToGiayChungNhan) {
		this.goToGiayChungNhan = goToGiayChungNhan;
	}
	private BenhNhan benhNhan;

	private ThamKham thamkham;
	
	private GiayNghiViecHuongBhxh giayNghiViec;

	public void resetValue() {

	}
	private String resultHidden ="";
	
	@Begin(nested = true)
	@Factory("goToGiayChungNhan")
	public void init() throws Exception {
		log.info("***Starting init ***");		
		try{
			formatter = new SimpleDateFormat("dd/MM/yyyy");
			log.info("maBanKhamOut:"+maBanKhamOut);
			log.info("maTiepDonOut:"+maTiepDonOut);
			formatter = new SimpleDateFormat("dd/MM/yyyy");
			ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
			gnvhbhxhDAO = GiayNghiViecHuongBhxhDelegate.getInstance();
			thamkham = tkDelegate.findByBanKhamVaMaTiepDon(maBanKhamOut,maTiepDonOut);
			benhNhan = thamkham.getTiepdonMa().getBenhnhanMa();
			// 20110106 bao.ttc: load BS tu tham kham
			if(thamkham.getThamkhamBacsi() != null)
				bacSiKCB = thamkham.getThamkhamBacsi(true).getDtdmnhanvienTen();
			giayNghiViec = gnvhbhxhDAO.findByThamKhamMa(thamkham.getThamkhamMa());
			if(giayNghiViec == null){
				giayNghiViec = new GiayNghiViecHuongBhxh();
			}
			else{
				donViCongTac = giayNghiViec.getGnvhbhxhDonvicongtac();
				lyDoNghiViec = giayNghiViec.getGnvhbhxhLydonghiviec();
				soNgayChoNghi = giayNghiViec.getGnvhbhxhSongaynghi();
				if(giayNghiViec.getGnvhbhxhTungay() != null)
					tuNgay = formatter.format(giayNghiViec.getGnvhbhxhTungay());
				if(giayNghiViec.getGnvhbhxhDenngay() != null)
					denNgay = formatter.format(giayNghiViec.getGnvhbhxhDenngay());
			}
			//log.info("thamkham.getTiepdonMa().getDoituongMa().getDmdoituongTen() = " + thamkham.getTiepdonMa().getDoituongMa().getDmdoituongTen());
			//log.info("thamkham.getTiepdonMa().getDoituongMa().getDmdoituongMa() = " + thamkham.getTiepdonMa().getDoituongMa().getDmdoituongMa());
			//log.info("benhNhan.getBenhnhanMa():" + benhNhan.getBenhnhanMa());
			//log.info("benhNhan.getBenhnhanNgaysinh():" + benhNhan.getBenhnhanNgaysinh());
			
			//log.info("thamkham.getThamkhamBankham().getDtdmbankhamMa():" + thamkham.getThamkhamBankham().getDtdmbankhamMa());
			//log.info("thamkham.getThamkhamBankham().getDtdmbankhamTen():" + thamkham.getThamkhamBankham().getDtdmbankhamTen());
			//log.info("thamkham.getThamkhamBankham().getThamkhamNgaygio():" + thamkham.getThamkhamNgaygio());
	
			setOtherValue();
			
			destroyService();
		}catch(Exception e){
			log.info("***init Exception = " + e);
		}
		log.info("***Finished init ***");		
	}

	@End 
	public void end(){
		
	}
		
	//***********************************************************************************
	// Ham ghi nhan
	//Xu ly cho nut ghi nhan 
	public String ghiNhanAjax() throws Exception {
		log.info("*****Begin ghiNhanAjax() *****");		
		try {
			giayNghiViec.setGnvhbhxhTungay(formatter.parse(tuNgay));
			giayNghiViec.setGnvhbhxhDenngay(formatter.parse(denNgay));
			giayNghiViec.setGnvhbhxhDonvicongtac(donViCongTac);
			giayNghiViec.setGnvhbhxhLydonghiviec(lyDoNghiViec);
			giayNghiViec.setGnvhbhxhSongaynghi(soNgayChoNghi);
			giayNghiViec.setThamkhamMa(thamkham);
			if(giayNghiViec.getGnvhbhxhMaso() == null){
				gnvhbhxhDAO.create(giayNghiViec);
			}
			else{
				gnvhbhxhDAO.edit(giayNghiViec);
			}
			
		} catch (Exception e) {
			FacesMessages.instance().add(IConstantsRes.FAIL);
			log.error("*************loi***********" + e.toString());
			return null;
			
		}

		
		log.info("*****End ghiNhanAjax() *****");
		return quayLai();
	}
	
	//Xu ly cho nut quay lai
	public String quayLai()  throws Exception {
		log.info("*****Begin quayLai() *****");
		goToGiayChungNhan = null;
		log.info("*****End quayLai() *****");
		return "ghinhan";
	}

	
	
	
	
	@Out(required=false)
	@In(required=false)
	private String reportPathTD=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeTD=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintTD = null;
	
	public String thuchienAction(){
		try {
			ghiNhanAjax();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XuatReport();
		return "B160_Chonmenuxuattaptin";
	}
	
	public void XuatReport() {
		reportTypeTD="giaychungnhan";
		log.info("Vao Method XuatReport giaychungnhan");
		String baocao1=null;
		String pathTemplate=null;
		try {
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/C65-HD_GiaychungnhannghiviechuongBHXH.jrxml";
			log.info("da thay file templete 5" + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put("hoTen", benhNhan.getBenhnhanHoten());
			if(benhNhan.getBenhnhanNamsinh() != null)
				params.put("namSinh", benhNhan.getBenhnhanNamsinh());
			params.put("soNgayNghi", soNgayChoNghi);
			params.put("donViCongTac", donViCongTac);
			
			params.put("bacSiKCB", bacSiKCB);
			params.put("lyDoNghiViec", lyDoNghiViec);
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_TINH);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL);
			
			try{
				params.put("tuNgay", formatter.parse(tuNgay));
				params.put("denNgay", formatter.parse(denNgay));
			}catch(Exception e){}
															
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintTD,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"tiepdon/", "pdf","giaychungnhan");
			    reportPathTD=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathTD);
			    index+= 1;
			    log.info("Index :" + index);
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Thoat Method XuatReport");
	}
	
	private int index = 0;
	//***********************************************************************************
	public String getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(String thoiGian) {
		this.thoiGian = thoiGian;
	}

	private void setOtherValue() {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
		if (thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh() != null && !thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().equals("")) {
			ngaySinh = formatter.format(thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().getTime());
		}
		else if(thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNamsinh()!=null)
			ngaySinh = thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNamsinh();
		
		if (thamkham.getThamkhamNgaygio() != null
				&& !thamkham.getThamkhamNgaygio().equals("")) {
			thoiGian = formatter
					.format(thamkham.getThamkhamNgaygio().getTime());
		}
		if(thamkham.getThamkhamNgaygio() != null){
			gioThamKham = Utils.getGioPhut(thamkham.getThamkhamNgaygio()) ;
		}
	}

	private void setinfor(ClsKham nhapctSelected) {
		// SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
		DtDmClsBangGia dmkythuat = nhapctSelected.getClskhamMahang();
		//dmkythuat.setDtdmkythuatDiengiai(diengiai);
		nhapctSelected.setClskhamMahang(dmkythuat);
//		if (thamkham.getTiepdonMa() != null) {
//			nhapctSelected.setTiepdonMa(thamkham.getTiepdonMa());
//		}
		if (thamkham.getThamkhamNgaygio() != null
				&& !"".equals(thamkham.getThamkhamNgaygio())) {
			nhapctSelected.setClskhamNgaygio(thamkham.getThamkhamNgaygio());
		}

//		if (thamkham.getThamkhamBankham() != null) {
//			nhapctSelected.setClskhamBankham(thamkham.getThamkhamBankham());
//		}
	}

	

	public void displayInfor() throws Exception {
		try {

		} catch (Exception e) {
			System.out.println("error on function displayInfor" + e);
		}
	}



///////////////////////	
///////////////////
	//Ham huy cac service da khoi tao
	public void destroyService() {
		try {
			log.debug("===== begin destroyService() method");			
//			thamKhamWS = null;
			log.debug("***** End destroyService() method");
		} catch (Exception ex) {
			log.debug("*****destroyService Exception: " + ex);
		}
	}	
	
	
	//Ham  se duoc goi khi het session (session timeout cau hinh trong file web.xml)
	@Destroy 
	public void destroy() {
		log.info("************************* destroy ThamKhamAction");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static String getFORMAT_DATE() {
		return FORMAT_DATE;
	}

	public static void setFORMAT_DATE(String format_date) {
		FORMAT_DATE = format_date;
	}

	public static String getFORMAT_DATE_TIME() {
		return FORMAT_DATE_TIME;
	}

	public static void setFORMAT_DATE_TIME(String format_date_time) {
		FORMAT_DATE_TIME = format_date_time;
	}

	public BenhNhan getBenhNhan() {
		return benhNhan;
	}

	public void setBenhNhan(BenhNhan benhNhan) {
		this.benhNhan = benhNhan;
	}


	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getStt() {
		return stt;
	}

	public void setStt(int stt) {
		this.stt = stt;
	}

	

	public ThamKham getThamkham() {
		return thamkham;
	}

	public void setThamkham(ThamKham thamkham) {
		this.thamkham = thamkham;
	}

	

	

	

	public String getResultHidden() {
		return resultHidden;
	}

	public void setResultHidden(String resultHidden) {
		this.resultHidden = resultHidden;
	}

	
	public String getGioThamKham() {
		return gioThamKham;
	}

	public void setGioThamKham(String gioThamKham) {
		this.gioThamKham = gioThamKham;
	}

	

	public String getMaBanKhamOut() {
		return maBanKhamOut;
	}

	public void setMaBanKhamOut(String maBanKhamOut) {
		this.maBanKhamOut = maBanKhamOut;
	}

	public String getMaTiepDonOut() {
		return maTiepDonOut;
	}

	public void setMaTiepDonOut(String maTiepDonOut) {
		this.maTiepDonOut = maTiepDonOut;
	}

	

}



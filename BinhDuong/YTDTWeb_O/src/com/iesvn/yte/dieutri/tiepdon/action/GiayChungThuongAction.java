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
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.GiayChungThuongDelegate;
//import com.iesvn.yte.dieutri.delegate.CtGiayChungThuongDelegate;
import com.iesvn.yte.dieutri.delegate.BenhAnNgoaiTruDelegate;
import com.iesvn.yte.dieutri.delegate.CtBenhAnNgoaiTruDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
import com.iesvn.yte.dieutri.entity.GiayChungThuong;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.ClsKham;
//import com.iesvn.yte.dieutri.entity.CtGiayChungThuong;
import com.iesvn.yte.dieutri.entity.BenhAnNgoaiTru;
import com.iesvn.yte.dieutri.entity.CtBenhAnNgoaiTru;
import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
import com.iesvn.yte.entity.DmBenhIcd;
import com.iesvn.yte.entity.DmDanToc;
import com.iesvn.yte.entity.DmHuyen;
import com.iesvn.yte.entity.DmNgheNghiep;
import com.iesvn.yte.entity.DmQuocGia;
import com.iesvn.yte.entity.DmTinh;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B121_12_giaychungthuong")
@Synchronized(timeout = 6000000)
public class GiayChungThuongAction implements Serializable {

	private static String FORMAT_DATE = "dd/MM/yyyy";
	private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";

	private static final long serialVersionUID = 10L;
	private int stt;
	private String ngay;
	private String thoiGian;
	private String gioThamKham;
	private static Logger log = Logger.getLogger(ThamKhamAction.class);
	
	@In(required = false)
	@Out(required = false)
	private String maBanKhamOut;
	
	@In(required = false)
	@Out(required = false)
	private String maTiepDonOut;
	
	@In(required = false)
	@Out(required = false)
	private String goToGiayChungThuong;
	private SimpleDateFormat formatter;
	private BenhNhan benhNhan;
	public String ngaySinh;
	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	
	private ThamKham thamkham;

	
	    public String ngaytuvong;
	     public String giotuvong;

	        public String getGiotuvong() {
	            return giotuvong;
	        }

	        public void setGiotuvong(String giotuvong) {
	            this.giotuvong = giotuvong;
	        }

	        public String getNgaytuvong() {
	            return ngaytuvong;
	        }

	        public void setNgaytuvong(String ngaytuvong) {
	            this.ngaytuvong = ngaytuvong;
	        }

	
	public void resetValue() {
		ngaytuvong = giotuvong ="";

	}
	private String resultHidden ="";
	
	private GiayChungThuong giayChungThuong = null;
	public String getNgay() {
		return ngay;
	}

	public void setNgay(String ngay) {
		this.ngay = ngay;
	}
	
	
	@Begin(nested = true)
	@Factory("goToGiayChungThuong")
	public void init() throws Exception {
		log.info("***Starting init ***");		
		try{
		
			ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
			thamkham = tkDelegate.findByBanKhamVaMaTiepDon(maBanKhamOut,maTiepDonOut);
			
			if(thamkham != null && thamkham.getTiepdonMa() != null){
				benhNhan = thamkham.getTiepdonMa().getBenhnhanMa();
			} else {
				benhNhan = new BenhNhan();
			}
			
			
			GiayChungThuong giayChungThuongTemp = null;
			try{
				giayChungThuongTemp = GiayChungThuongDelegate.getInstance().getGiayChungThuong(thamkham.getThamkhamMa());
			}catch(Exception e){
				log.info("error:"+ e);
			}
			
			ngaytuvong=giotuvong="";
			
			if (giayChungThuongTemp != null){
				giayChungThuong = giayChungThuongTemp;
			} else {
				giayChungThuong = new GiayChungThuong();
				giayChungThuong.setGctThamkham(thamkham);
			}
			setOtherValue();
			
			
			destroyService();
			
			goToGiayChungThuong = "";
		}catch(Exception e){
			log.info("***init Exception = " + e);
		}
		log.info("***Finished init ***");		
	}

	@End 
	public void end(){
		goToGiayChungThuong = null;
	}
	
	
		
	private void setOtherValue() {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
		if (thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh() != null	&& !thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().equals("")) {
			ngaySinh = formatter.format(thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().getTime());
		}
		else if(thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNamsinh()!=null)
			ngaySinh = thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNamsinh();
			
		if (thamkham.getThamkhamNgaygio() != null && !thamkham.getThamkhamNgaygio().equals("")) {
			thoiGian = formatter.format(thamkham.getThamkhamNgaygio().getTime());
			ngay = formatter.format(thamkham.getThamkhamNgaygio().getTime());
			gioThamKham = Utils.getGioPhut(thamkham.getThamkhamNgaygio()) ;
		}
	}
	
	   public GiayChungThuong getGiayChungThuong() {
	        return giayChungThuong;
	    }

	    public void setGiayChungThuong(GiayChungThuong giayChungThuong) {
	        this.giayChungThuong = giayChungThuong;
	    }
	  
	    public String formatDate(Date date) {
			return date == null ? "" : new SimpleDateFormat("dd/MM/yyyy")
					.format(date);
		}

		public String formatDateTime(Date date) {
			System.out.println("qdate = "
					+ (date == null ? "isnull" : date.toString()));
			return date == null ? "" : Utils.getGioPhut(date);
		}
	//Ly
	public void enter() throws Exception {
		log.info("*****Begin Enter() *****");
		insertRow();
		reset_ctbant();
		log.info("*****End Enter() *****");
	}
	private void insertRow(){
		
	}
	
   
	
	public void delete(int index) throws Exception {
		log.info("*****Begin delete() *****");
		reset_ctbant();
		log.info("*****End delete() *****");
	}
	
	public void reset_ctbant(){
		
		
	}
	
	// Ham ghi nhan
	//Xu ly cho nut ghi nhan 
	public String ghiNhanAjax() throws Exception {
		log.info("***Starting ghinhan **");
		log.info("***giayChungThuong.getGctMa() = **"+giayChungThuong.getGctMa());
		if (giayChungThuong.getGctMa() == null){
			GiayChungThuongDelegate.getInstance().create(giayChungThuong);
		}else{
			GiayChungThuongDelegate.getInstance().edit(giayChungThuong);
		}
		FacesMessages.instance().add(IConstantsRes.SUCCESS);
		log.info("***Finished ghinhan **");
		return "/B1_Tiepdon/B121_12_Giaychungthuong";
	}
	
	//Xu ly cho nut quay lai
	public String quayLai()  throws Exception {
		log.info("*****Begin quayLai() *****");
		goToGiayChungThuong = null;
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
		XuatReport();
		return "B160_Chonmenuxuattaptin";
	}
	
	public void XuatReport() {
		reportTypeTD="giaychungthuong";
		
		log.info("Vao Method XuatReport giaychungthuong");
		String baocao1=null;
		String pathTemplate=null;
		try {
			
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/Giaychungthuong.jrxml";
			log.info("da thay file templete 5" + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // 20101108 bao.ttc
			
			GiayChungThuong giayChungThuongTemp = null;
			try{
				giayChungThuongTemp = GiayChungThuongDelegate.getInstance().getGiayChungThuong(thamkham.getThamkhamMa());
			}catch(Exception e){
				log.info("error:"+ e);
			}
			
			if (giayChungThuongTemp != null){
				giayChungThuong = giayChungThuongTemp;
			}
			
			
			params.put("hoTen", benhNhan.getBenhnhanHoten());
			
			// 20101108 bao.ttc: Neu co ngay sinh thi su dung, neu khong thi dung nam sinh
			Date dNgaySinh = benhNhan.getBenhnhanNgaysinh();
			if (dNgaySinh != null) {
				
				params.put("ngaySinh", "" + sdf.format(benhNhan.getBenhnhanNgaysinh()) );
				
			} else {
				if(benhNhan.getBenhnhanNamsinh() != null)
					params.put("namSinh", benhNhan.getBenhnhanNamsinh());
				else
					params.put("namSinh", "");
			}
			
			params.put("ngheTen", benhNhan.getBenhnhanNghe(true).getDmnghenghiepTen());
			params.put("cmnd", benhNhan.getBenhnhanCmnd());
			
			// 20101110 bao.ttc: them chi tiet dia chi
			String diachistr = "";
			if (benhNhan.getBenhnhanDiachi() != null)
				diachistr += benhNhan.getBenhnhanDiachi();
			if (benhNhan.getXaMa(true).getDmxaTen() != null)
				diachistr += ", " + benhNhan.getXaMa(true).getDmxaTen();
			if (benhNhan.getHuyenMa(true).getDmhuyenTen() != null)
				diachistr += ", " + benhNhan.getHuyenMa(true).getDmhuyenTen();
			if (benhNhan.getTinhMa(true).getDmtinhTen() != null)
				diachistr += ", " + benhNhan.getTinhMa(true).getDmtinhTen();
			params.put("diaChi", diachistr);
			
			// 20101110 bao.ttc: Them chan doan
			String maChanDoan = "";
			String tenChanDoan = "";

			if (giayChungThuong.getGctThamkham().getTiepdonMa().getTiepdonMachdoanbd() != null) {
				
				if (giayChungThuong.getGctThamkham().getTiepdonMa().getTiepdonMachdoanbd().getDmbenhicdMa() != null)
					maChanDoan = giayChungThuong.getGctThamkham().getTiepdonMa().getTiepdonMachdoanbd().getDmbenhicdMa();
				if (giayChungThuong.getGctThamkham().getTiepdonMa().getTiepdonMachdoanbd().getDmbenhicdTen() != null)
					tenChanDoan = giayChungThuong.getGctThamkham().getTiepdonMa().getTiepdonMachdoanbd().getDmbenhicdTen();

			}
			params.put("CHANDOAN", maChanDoan + " - " + tenChanDoan);
			// 20101110 bao.ttc: Them chan doan -- END
			
			Date dVaov = giayChungThuong.getGctThamkham().getTiepdonMa().getTiepdonNgaygio();
			if (dVaov != null) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(dVaov);
				params.put("gioVaov", "" + cal.get(Calendar.HOUR));
				params.put("phutVaov", "" + cal.get(Calendar.MINUTE));
				params.put("ngayVaov", "" + cal.get(Calendar.DAY_OF_MONTH));
				params.put("thangVaov", "" + (cal.get(Calendar.MONTH) + 1));
				params.put("namVaov", "" + cal.get(Calendar.YEAR));
			} else {
				params.put("gioVaov", "");
				params.put("phutVaov", "");
				params.put("ngayVaov", "");
				params.put("thangVaov", "");
				params.put("namVaov", "");
			}
			
			Date dRav = giayChungThuong.getGctThamkham().getTiepdonMa().getTiepdonNgaygiora();
			if (dRav != null) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(dRav);
				params.put("gioRav", "" + cal.get(Calendar.HOUR));
				params.put("phutRav", "" + cal.get(Calendar.MINUTE));
				params.put("ngayRav", "" + cal.get(Calendar.DAY_OF_MONTH));
				params.put("thangRav", "" + (cal.get(Calendar.MONTH) + 1));
				params.put("namRav", "" + cal.get(Calendar.YEAR));
			} else {
				params.put("gioRav", "");
				params.put("phutRav", "");
				params.put("ngayRav", "");
				params.put("thangRav", "");
				params.put("namRav", "");
			}
			params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("tenDonViFull", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL);
			params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			params.put("soVaoVien", giayChungThuong.getGctThamkham().getTiepdonMa().getTiepdonMa());
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			params.put("gioiTinh", benhNhan.getDmgtMaso(true).getDmgtTen());
			
			params.put("soGCT", giayChungThuong.getGctMa());
			params.put("thuongtichvaovien", giayChungThuong.getGctThuongtichvaovien());
			params.put("thuongtichravien", giayChungThuong.getGctThuongtichravien());
			params.put("dieutri", giayChungThuong.getGctDieutri());
			
						
			
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintTD,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"tiepdon/", "pdf","benhanvaovien");
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

	private String tuNgay;
	private String denNgay;
	
	
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


}



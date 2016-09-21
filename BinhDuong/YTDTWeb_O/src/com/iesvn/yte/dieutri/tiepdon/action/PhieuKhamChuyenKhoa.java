package com.iesvn.yte.dieutri.tiepdon.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
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
import org.jboss.seam.faces.FacesMessages;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.PhieuKhamChuyenKhoaDelegate;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.entity.DmBenhIcd;
import com.iesvn.yte.util.IConstantsRes;

@Scope(CONVERSATION)
@Name("B119_Phieukhamchuyenkhoa")
@Synchronized(timeout = 6000000)
public class PhieuKhamChuyenKhoa implements Serializable {

	private static String FORMAT_DATE = "dd/MM/yyyy";
	private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";

	private static final long serialVersionUID = 10L;
	private int stt;
	private String sMaPhieuKhamDT;
	private String ngaySinh="";
	private String gioi="";
	private com.iesvn.yte.dieutri.entity.PhieuKhamChuyenKhoa objPhieuKhamCK=new com.iesvn.yte.dieutri.entity.PhieuKhamChuyenKhoa(); 
	private String sShowDel="";
	private String sShowPrint="";
	
	private static Logger log = Logger.getLogger(ThamKhamAction.class);
	
	@In(required = false)
	@Out(required = false)
	private String maBanKhamOut;
	
	@In(required = false)
	@Out(required = false)
	private String maTiepDonOut;
	
	@In(required = false)
	@Out(required = false)
	private String goToPhieuKhamChuyenKhoa;
	
	@Out(required = false)
	private String bacSiKCB;

	private BenhNhan benhNhan;

	private ThamKham thamkham;

	public void resetValue() {

	}
	
	@Begin(nested = true)
	@Factory("goToPhieuKhamChuyenKhoa")
	public void init() throws Exception {
		log.info("***Starting init ***");		
		try{
			ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
			
			thamkham = tkDelegate.findByBanKhamVaMaTiepDon(maBanKhamOut,maTiepDonOut);
			if(thamkham!=null)
			{
				benhNhan = thamkham.getTiepdonMa().getBenhnhanMa();
				objPhieuKhamCK=PhieuKhamChuyenKhoaDelegate.getInstance().findByMaThamKham(thamkham.getThamkhamMa());
				if(objPhieuKhamCK!=null)
				{
					sMaPhieuKhamDT=objPhieuKhamCK.getPkckMa();
					sShowDel="true";
					sShowPrint="true";
				}
				else
				{
					objPhieuKhamCK=new com.iesvn.yte.dieutri.entity.PhieuKhamChuyenKhoa();
					sMaPhieuKhamDT="";
					sShowDel="false";
					sShowPrint="false";
				}
				
			}
			
			if (benhNhan.getDmgtMaso() != null){
				if ("1".equals(benhNhan.getDmgtMaso().getDmgtMa())){
					gioi = "r1";  //1 : Name
				}else{
					gioi = "r2";
				}					
			}else{
				gioi = null;
			}
			
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
	public String ghiNhan() throws Exception {
		log.info("*****Begin ghiNhan() *****");
		
		try {
			PhieuKhamChuyenKhoaDelegate PhieuKhamDtNgoaiTruDel=PhieuKhamChuyenKhoaDelegate.getInstance();
			
			objPhieuKhamCK.setPkdtntThamkham(thamkham);
			sMaPhieuKhamDT=PhieuKhamDtNgoaiTruDel.capNhatPhieuKhamChuyenKhoa(objPhieuKhamCK, sMaPhieuKhamDT);
			FacesMessages.instance().add(IConstantsRes.RPPKCK_INSERT_SUCCESS,sMaPhieuKhamDT);
			log.info("*****CAP NHAT THANH CONG  PhieuKhamDtNgoaiTruAction *****");
			sShowDel="true";
			sShowPrint="true";
		} catch (Exception e) {
			FacesMessages.instance().add(IConstantsRes.FAIL);
			log.error("*************loi***********" + e.toString());
			return null;
		}
		log.info("*****End ghiNhan() *****");
		return null;
	}
	
	public void huyPhieu()
	{
		log.info("***** start  huyPhieu() *****");
		if(sMaPhieuKhamDT==null||sMaPhieuKhamDT.equals(""))
		{
			return;
		}
		PhieuKhamChuyenKhoaDelegate PhieuKhamDtNgoaiTruDel=PhieuKhamChuyenKhoaDelegate.getInstance();
		com.iesvn.yte.dieutri.entity.PhieuKhamChuyenKhoa obj=PhieuKhamDtNgoaiTruDel.find(sMaPhieuKhamDT);
		if(obj==null)
			return;
		
		PhieuKhamDtNgoaiTruDel.remove(obj);
		FacesMessages.instance().add(IConstantsRes.RPPKCK_DELETE_SUCCESS,sMaPhieuKhamDT);
		log.info("***** XOA THANH CONG *****");
		objPhieuKhamCK=new com.iesvn.yte.dieutri.entity.PhieuKhamChuyenKhoa();
		sMaPhieuKhamDT="";
		sShowDel="false";
		sShowPrint="false";
		log.info("***** end  huyPhieu() *****");
	}
	
	//Xu ly cho nut quay lai
	public String quayLai()  throws Exception {
		log.info("*****Begin quayLai() *****");
		goToPhieuKhamChuyenKhoa = null;
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
		reportTypeTD="phieukhamchuyenkhoa";
		log.info("Vao Method XuatReport phieukhamchuyenkhoa");
		String baocao1=null;
		String pathTemplate=null;
		try {
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieukhamchuyenkhoa.jrxml";
			log.info("da thay file templete 5" + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			
			params.put("HOTEN", benhNhan.getBenhnhanHoten());
			params.put("GIOI", benhNhan.getDmgtMaso(true).getDmgtTen());
			
			// 20101105 bao.ttc: them Ma phieu
			if(sMaPhieuKhamDT != null && !sMaPhieuKhamDT.equals(""))
				params.put("MAPHIEU", sMaPhieuKhamDT);
			
			String diaChi = "";
			String tinh = "";
			String huyen = "";
			String xa = "";
			String duong = "";
			if (benhNhan.getTinhMa() == null) {
				tinh = "";
			} else {
				tinh = benhNhan.getTinhMa().getDmtinhTen();
			}
			if (benhNhan.getHuyenMa() == null) {
				huyen = "";
			} else {
				huyen = benhNhan.getHuyenMa().getDmhuyenTen() + "-";
			}
			if (benhNhan.getXaMa() == null) {
				xa = "";
			} else {
				xa = benhNhan.getXaMa().getDmxaTen() + "-";
			}
			if (benhNhan.getBenhnhanDiachi() == null) {
				duong = "";
			} else {
				duong = benhNhan.getBenhnhanDiachi() + "-";
			}
			diaChi = duong + xa + huyen + tinh;
			params.put("DIACHI", diaChi);
			params.put("MATHEBHYT", thamkham.getTiepdonMa(true).getTiepdonSothebh());
			params.put("KHOA", thamkham.getTiepdonMa(true).getTiepdonBankham().getDtdmbankhamTen());
			if(thamkham.getTiepdonMa(true).getTiepdonGiatri1()!=null && thamkham.getTiepdonMa(true).getTiepdonGiatri2()!=null)
			{
				params.put("GTSUDUNG1", sdf.format(thamkham.getTiepdonMa(true).getTiepdonGiatri1()));
				params.put("GTSUDUNG2",sdf.format(thamkham.getTiepdonMa(true).getTiepdonGiatri2()));
			}
			
			DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
			String maChanDoan = "";
			String tenChanDoan ="";
			if (thamkham.getBenhicd10() != null && thamkham.getBenhicd10().getDmbenhicdMaso() !=null){
				DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
				}
				
			}
			
			String chanDoan = maChanDoan + " " +  tenChanDoan;
			//tiep tuc them benh phu.
			if (thamkham.getBenhicd10phu1() != null && thamkham.getBenhicd10phu1().getDmbenhicdMaso() !=null){
				DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu1().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					chanDoan += " , "+ maChanDoan + " " +  tenChanDoan;
				}
			}
			if (thamkham.getBenhicd10phu2() != null && thamkham.getBenhicd10phu2().getDmbenhicdMaso() !=null){
				DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu2().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					chanDoan += " , "+ maChanDoan + " " +  tenChanDoan;
				}
				
			}
			if (thamkham.getBenhicd10phu3() != null && thamkham.getBenhicd10phu3().getDmbenhicdMaso() !=null){
				DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu3().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					chanDoan += " , "+ maChanDoan + " " +  tenChanDoan;
				}
			}
			if (thamkham.getBenhicd10phu4() != null && thamkham.getBenhicd10phu4().getDmbenhicdMaso() !=null){
				DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu4().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					chanDoan += " , "+ maChanDoan + " " +  tenChanDoan;
				}
			}
			if (thamkham.getBenhicd10phu5() != null && thamkham.getBenhicd10phu5().getDmbenhicdMaso() !=null){
				DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu5().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					chanDoan += " , "+ maChanDoan + " " +  tenChanDoan;
				}
			}
			params.put("CHANDOAN", chanDoan );
			params.put("BUONG", thamkham.getThamkhamBankham(true).getDtdmbankhamTen());
			
			
			log.info("*****chanDoan: "+chanDoan);
			
			
			if(thamkham.getThamkhamBacsi(true).getDtdmnhanvienTen()!=null)
			{
				params.put("BACSI", thamkham.getThamkhamBacsi(true).getDtdmnhanvienTen());
			}
			params.put("KINHGOI", objPhieuKhamCK.getPkckKinhgoi());
			params.put("YEUCAU", objPhieuKhamCK.getPkckYeucaukhamck()); // 20101101 bao.ttc
			
			String sDonViTuoi="";
			//if(benhNhan.getBenhnhanDonvituoi()==1)
			//	sDonViTuoi=IConstantsRes.NAM;
			//else
			if(benhNhan.getBenhnhanDonvituoi()==2)
				sDonViTuoi=IConstantsRes.THANG;
			else if(benhNhan.getBenhnhanDonvituoi()==3)
				sDonViTuoi=IConstantsRes.NGAY;
			params.put("TUOI", benhNhan.getBenhnhanTuoi() + " " + sDonViTuoi);
															
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintTD,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"tiepdon/", "pdf","phieukhamchuyenkhoa");
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

	private void setOtherValue() {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
		if (thamkham.getTiepdonMa().getBenhnhanMa()	.getBenhnhanNgaysinh() != null	&& !thamkham.getTiepdonMa().getBenhnhanMa() .getBenhnhanNgaysinh().equals("")) {
			ngaySinh = formatter.format(thamkham.getTiepdonMa()	.getBenhnhanMa().getBenhnhanNgaysinh().getTime());
		}
		else if(thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNamsinh()!=null)
			ngaySinh = thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNamsinh();
	}

	public void displayInfor() throws Exception {
		try {
			
		if(sMaPhieuKhamDT==null|| sMaPhieuKhamDT.equals(""))
			return;
		
		PhieuKhamChuyenKhoaDelegate PhieuKhamDtNgoaiTruDel=PhieuKhamChuyenKhoaDelegate.getInstance();
		objPhieuKhamCK=new com.iesvn.yte.dieutri.entity.PhieuKhamChuyenKhoa();
		List<com.iesvn.yte.dieutri.entity.PhieuKhamChuyenKhoa> ls=PhieuKhamDtNgoaiTruDel.findByPhieuKhamChuyenKhoa(getMaPhieuKhamDtNgoaiTru());
		
		if(ls==null||ls.size()==0)
		{
			FacesMessages.instance().add(IConstantsRes.RPPKCK_NOT_EXIST);
			sMaPhieuKhamDT="";
			sShowDel="false";
			sShowPrint="false";
			return;
		}
		sShowDel="true";
		sShowPrint="true";
		objPhieuKhamCK=ls.get(0);
		sMaPhieuKhamDT=objPhieuKhamCK.getPkckMa();
		//setDaKCT(giayChuyenVien.getGcvbhytKct());
		//setDtNgoaiTru(giayChuyenVien.getGcvbhytDadtngoaitru());
		//setDtNoiTru(giayChuyenVien.getGcvbhytDadtnoitru());
		log.info("*****sMaPhieuKhamDT: "+sMaPhieuKhamDT)		;
		
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
		log.info("************************* destroy GiayChuyenVienNguoiBenhCoTheBHYTAction");
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

	public String getGioi()
	{
		return gioi;
	}

	public void setGioi(String gioi)
	{
		this.gioi = gioi;
	}


	public String getBacSiKCB() {
		return bacSiKCB;
	}

	public void setBacSiKCB(String bacSiKCB) {
		this.bacSiKCB = bacSiKCB;
	}

	public String getGoToPhieuKhamChuyenKhoa() {
		return goToPhieuKhamChuyenKhoa;
	}

	public void setGoToPhieuKhamChuyenKhoa(String str) {
		this.goToPhieuKhamChuyenKhoa = str;
	}

	public String getMaPhieuKhamDtNgoaiTru()
	{
		return sMaPhieuKhamDT;
	}

	public void setMaPhieuKhamDtNgoaiTru(String maGiayChuyenVien)
	{
		sMaPhieuKhamDT = maGiayChuyenVien;
	}
	
	public String getShowDel()
	{
		return sShowDel;
	}

	public void setShowDel(String showDel)
	{
		sShowDel = showDel;
	}

	public String getShowPrint()
	{
		return sShowPrint;
	}

	public void setShowPrint(String showPrint)
	{
		sShowPrint = showPrint;
	}

	public com.iesvn.yte.dieutri.entity.PhieuKhamChuyenKhoa getPhieuKhamChuyenKhoa()
	{
		return objPhieuKhamCK;
	}

	public void setPhieuKhamChuyenKhoa(com.iesvn.yte.dieutri.entity.PhieuKhamChuyenKhoa objPhieuKhamDTNgoaiTru)
	{
		this.objPhieuKhamCK = objPhieuKhamDTNgoaiTru;
	}
	
}



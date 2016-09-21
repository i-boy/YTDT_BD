package com.iesvn.yte.dieutri.tiepdon.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
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
import org.jboss.seam.faces.FacesMessages;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.GiayCnSucKhoeDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.GiayCnSucKhoe;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B121_6_Giaychungnhansuckhoe")
@Synchronized(timeout = 6000000)
public class GiayChungNhanSucKhoeAction implements Serializable {

	private static String FORMAT_DATE = "dd/MM/yyyy";
	private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";

	private static final long serialVersionUID = 10L;
	private int stt;
	private String sMaPhieuKVV;
	private String ngaySinh="";
	private String gioi="";
	private GiayCnSucKhoe objGiayCnSucKhoe= new GiayCnSucKhoe(); 
	private String sShowDel="";
	private String sShowPrint="";
	private Integer iMaSoBacSi;
	private String sMaBacSi;
	
	
	private static Logger log = Logger.getLogger(GiayChungNhanSucKhoeAction.class);
	
	@In(required = false)
	@Out(required = false)
	private String maBanKhamOut;
	
	@In(required = false)
	@Out(required = false)
	private String maTiepDonOut;
	
	@In(required = false)
	@Out(required = false)
	private String goToGiayChungNhanSucKhoe;
	
	@Out(required = false)
	private String bacSiKCB;

	private BenhNhan benhNhan;

	private ThamKham thamkham;

	public void resetValue() {

	}
	
	@Begin(nested = true)
	@Factory("goToGiayChungNhanSucKhoe")
	public void init() throws Exception {
		log.info("***Starting init ***");		
		try{
			ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
			
			thamkham = tkDelegate.findByBanKhamVaMaTiepDon(maBanKhamOut,maTiepDonOut);
			if(thamkham!=null)
			{
				benhNhan = thamkham.getTiepdonMa().getBenhnhanMa();
				objGiayCnSucKhoe=GiayCnSucKhoeDelegate.getInstance().findByMaThamKham(thamkham.getThamkhamMa());
				if(objGiayCnSucKhoe!=null)
				{
					sMaPhieuKVV = objGiayCnSucKhoe.getGcnskMa().toString();
				
					sShowDel="true";
					sShowPrint="true";
					iMaSoBacSi=objGiayCnSucKhoe.getGcnskBacsikl(true).getDtdmnhanvienMaso();
					sMaBacSi=objGiayCnSucKhoe.getGcnskBacsikl(true).getDtdmnhanvienMa();
				}
				else
				{
					objGiayCnSucKhoe=new GiayCnSucKhoe();
					sMaPhieuKVV="";
					sShowDel="false";
					sShowPrint="false";
					iMaSoBacSi=thamkham.getThamkhamBacsi(true).getDtdmnhanvienMaso();
					sMaBacSi=thamkham.getThamkhamBacsi(true).getDtdmnhanvienMa();
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
			GiayCnSucKhoeDelegate GiayCnSucKhoeDel=GiayCnSucKhoeDelegate.getInstance();
			
			objGiayCnSucKhoe.setGcnskThamkham(thamkham);
			objGiayCnSucKhoe.getGcnskBacsikl(true).setDtdmnhanvienMaso(getMaSoBacSi());
			GiayCnSucKhoeDel.capNhatGiayCnSucKhoe(objGiayCnSucKhoe);
			FacesMessages.instance().add(IConstantsRes.UPDATE_SUCCESS);
			log.info("*****CAP NHAT THANH CONG  GiayCnSucKhoeAction *****");
			sShowDel="true";
			sShowPrint="true";
			init();
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
		if(sMaPhieuKVV==null||sMaPhieuKVV.equals(""))
		{
			return;
		}
		GiayCnSucKhoeDelegate GiayCnSucKhoeDel=GiayCnSucKhoeDelegate.getInstance();
		GiayCnSucKhoe obj=GiayCnSucKhoeDel.find(objGiayCnSucKhoe.getGcnskMa());
		if(obj==null)
			return;
		
		GiayCnSucKhoeDel.remove(obj);
		FacesMessages.instance().add(IConstantsRes.HUY_PHIEU_THANH_CONG,sMaPhieuKVV);
		log.info("***** XOA THANH CONG *****");
		objGiayCnSucKhoe=new GiayCnSucKhoe();
		sMaPhieuKVV="";
		sShowDel="false";
		sShowPrint="false";
		iMaSoBacSi=thamkham.getThamkhamBacsi(true).getDtdmnhanvienMaso();
		sMaBacSi=thamkham.getThamkhamBacsi(true).getDtdmnhanvienMa();
		log.info("***** end  huyPhieu() *****");
	}
	
	//Xu ly cho nut quay lai
	public String quayLai()  throws Exception {
		log.info("*****Begin quayLai() *****");
		goToGiayChungNhanSucKhoe = null;
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
		reportTypeTD="giaychungnhansuckhoe";
		log.info("Vao Method XuatReport giaychungnhansuckhoe");
		String baocao1=null;
		String pathTemplate=null;
		try {
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/giaychungnhansuckhoe.jrxml";
			String sub1Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/giaychungnhansuckhoe_sub1.jrxml";
			String sub2Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/giaychungnhansuckhoe_sub2.jrxml";
			log.info("da thay file giaychungnhansuckhoe" );
			log.info("da thay file giaychungnhansuckhoe_sub1" );
			log.info("da thay file giaychungnhansuckhoe_sub2" );
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			JasperReport sub1Report =JasperCompileManager.compileReport(sub1Template);
			JasperReport sub2Report =JasperCompileManager.compileReport(sub2Template);
			
			log.info("----create JasperReport");
			log.info("----create JasperReport_Sub1");
			log.info("----create JasperReport_Sub2");
			
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put("sub1", sub1Report);
			params.put("sub2", sub2Report);
			
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("DONVI", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			
			params.put("HOTEN", benhNhan.getBenhnhanHoten());
			Date nsDate = thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh();
			SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
			if (nsDate != null){
				params.put("NGAYSINH", sdf.format(nsDate));
			}else{
				params.put("NGAYSINH", thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNamsinh());
			}
			//params.put("NGAYSINH", benhNhan.getBenhnhanNgaysinh());
			params.put("GIOI", benhNhan.getDmgtMaso(true).getDmgtTen());
			
			String diachistr="";
			if(thamkham.getTiepdonMa().getBenhnhanMa(true).getBenhnhanDiachi() != null)
				diachistr += thamkham.getTiepdonMa(true).getBenhnhanMa().getBenhnhanDiachi()+",";
			if(thamkham.getTiepdonMa().getBenhnhanMa(true).getXaMa(true).getDmxaTen() !=null)
				diachistr += thamkham.getTiepdonMa(true).getBenhnhanMa().getXaMa(true).getDmxaTen()+",";
			if(thamkham.getTiepdonMa().getBenhnhanMa(true).getHuyenMa(true).getDmhuyenTen() != null)
				diachistr += thamkham.getTiepdonMa(true).getBenhnhanMa().getHuyenMa(true).getDmhuyenTen()+",";
			if(thamkham.getTiepdonMa().getBenhnhanMa(true).getTinhMa(true).getDmtinhTen() != null)
				diachistr += thamkham.getTiepdonMa(true).getBenhnhanMa().getTinhMa(true).getDmtinhTen();
			params.put("DIACHI", diachistr );
			
			params.put("BIDANH",objGiayCnSucKhoe.getGcnskBidanh()!=null?objGiayCnSucKhoe.getGcnskBidanh():"");
			params.put("NGHENGHIEP",benhNhan.getBenhnhanNghe(true).getDmnghenghiepTen()!=null?benhNhan.getBenhnhanNghe(true).getDmnghenghiepTen():"");
			params.put("COQUANCTAC",thamkham.getTiepdonMa(true).getTiepdonMacoquan()!=null?thamkham.getTiepdonMa(true).getTiepdonMacoquan():"");
			params.put("QUEQUAN",objGiayCnSucKhoe.getGcnskQuequan()!=null?objGiayCnSucKhoe.getGcnskQuequan():"");
			params.put("TIENSUBANTHAN",objGiayCnSucKhoe.getGcnskTiensubanthan()!=null?objGiayCnSucKhoe.getGcnskTiensubanthan():"");
			
			if(thamkham.getTiepdonMa(true).getTiepdonChieucao()!=null)
				params.put("CHIEUCAO",thamkham.getTiepdonMa(true).getTiepdonChieucao());
			if(thamkham.getTiepdonMa(true).getTiepdonTrluong()!=null)
				params.put("CANNANG",thamkham.getTiepdonMa(true).getTiepdonTrluong());
			if(objGiayCnSucKhoe.getGcnskVongnguctb()!=null)
				params.put("VONGNGUC",objGiayCnSucKhoe.getGcnskVongnguctb());
			if(objGiayCnSucKhoe.getGcnskLucboptaythuan()!=null)
				params.put("LUCBOPTAYTHUAN",objGiayCnSucKhoe.getGcnskLucboptaythuan());
			if(objGiayCnSucKhoe.getGcnskLucboptaykhongthuan()!=null)
				params.put("LUCBOPTAYKHONGTHUAN",objGiayCnSucKhoe.getGcnskLucboptaykhongthuan());
			params.put("MATPHAIKHONGKINH",objGiayCnSucKhoe.getGcnskMatphaikhongkieng()!=null?objGiayCnSucKhoe.getGcnskMatphaikhongkieng():"");
			params.put("MATPHAICOKINH",objGiayCnSucKhoe.getGcnskMatphaicokieng()!=null?objGiayCnSucKhoe.getGcnskMatphaicokieng():"");
			params.put("MATTRAIKHONGKINH",objGiayCnSucKhoe.getGcnskMattraikhongkieng()!=null?objGiayCnSucKhoe.getGcnskMattraikhongkieng():"");
			params.put("MATTRAICOKINH",objGiayCnSucKhoe.getGcnskMattraicokieng()!=null?objGiayCnSucKhoe.getGcnskMattraicokieng():"");
			params.put("KINHLOAI",objGiayCnSucKhoe.getGcnskKiengloai()!=null?objGiayCnSucKhoe.getGcnskKiengloai():"");
			params.put("SO",objGiayCnSucKhoe.getGcnskSo()!=null?objGiayCnSucKhoe.getGcnskSo():"");
			params.put("SACGIAC",objGiayCnSucKhoe.getGcnskSacgiac()!=null?objGiayCnSucKhoe.getGcnskSacgiac():"");
			params.put("BENHOMAT",objGiayCnSucKhoe.getGcnskBenhomat()!=null?objGiayCnSucKhoe.getGcnskBenhomat():"");
			if(objGiayCnSucKhoe.getGcnskTaiphainghenoibt()!=null)
				params.put("TAIPHAINGHENOITHUONG",objGiayCnSucKhoe.getGcnskTaiphainghenoibt());
			if(objGiayCnSucKhoe.getGcnskTaiphainghenoitham()!=null)
				params.put("TAIPHAINGHENOITHAM",objGiayCnSucKhoe.getGcnskTaiphainghenoitham());
			if(objGiayCnSucKhoe.getGcnskTaitrainghenoitham()!=null)
				params.put("TAITRAINGHENOITHAM",objGiayCnSucKhoe.getGcnskTaitrainghenoitham());
			if(objGiayCnSucKhoe.getGcnskTaitrainghenoibt()!=null)
				params.put("TAITRAINGHENOITHUONG",objGiayCnSucKhoe.getGcnskTaitrainghenoibt());
			if(objGiayCnSucKhoe.getGcnskLuckeothan()!=null)
				params.put("LUCKEO",objGiayCnSucKhoe.getGcnskLuckeothan());
			
			params.put("BENHTAI",objGiayCnSucKhoe.getGcnskBenhtai()!=null?objGiayCnSucKhoe.getGcnskBenhtai():"");
			params.put("BENHMUI",objGiayCnSucKhoe.getGcnskBenhmui()!=null?objGiayCnSucKhoe.getGcnskBenhmui():"");
			params.put("BENHHONG",objGiayCnSucKhoe.getGcnskBenhhong()!=null?objGiayCnSucKhoe.getGcnskBenhhong():"");
			params.put("HAMTREN",objGiayCnSucKhoe.getGcnskHamtren()!=null?objGiayCnSucKhoe.getGcnskHamtren():"");
			params.put("HAMDUOI",objGiayCnSucKhoe.getGcnskHamduoi()!=null?objGiayCnSucKhoe.getGcnskHamduoi():"");
			params.put("DONGKINH",objGiayCnSucKhoe.getGcnskDongkinh()!=null?objGiayCnSucKhoe.getGcnskDongkinh():"");
			params.put("TELIET",objGiayCnSucKhoe.getGcnskTeliet()!=null?objGiayCnSucKhoe.getGcnskTeliet():"");
			params.put("PXTAY",objGiayCnSucKhoe.getGcnskPxtay()!=null?objGiayCnSucKhoe.getGcnskPxtay():"");
			params.put("PXCHAN",objGiayCnSucKhoe.getGcnskPxchan()!=null?objGiayCnSucKhoe.getGcnskPxchan():"");
			params.put("BENHTHANKINH",objGiayCnSucKhoe.getGcnskBenhthankinh()!=null?objGiayCnSucKhoe.getGcnskBenhthankinh():"");
			params.put("BENHTAMTHAN",objGiayCnSucKhoe.getGcnskBenhtamthan()!=null?objGiayCnSucKhoe.getGcnskBenhtamthan():"");
			
			
			
			//----PAGE 2
			if(thamkham.getTiepdonMa(true).getTiepdonHamin()!=null)
				params.put("HUYETAPMIN",  thamkham.getTiepdonMa(true).getTiepdonHamin()); //Double
			if(thamkham.getTiepdonMa(true).getTiepdonHamax()!=null)
				params.put("HUYETAPMAX",  thamkham.getTiepdonMa(true).getTiepdonHamax()); //Double
			if(thamkham.getTiepdonMa(true).getTiepdonMach()!=null)
				params.put("MACH",  thamkham.getTiepdonMa(true).getTiepdonMach()); //Double
			params.put("BENHTIM",objGiayCnSucKhoe.getGcnskBenhtim()!=null?objGiayCnSucKhoe.getGcnskBenhtim():"");
			params.put("BENHMACHMAU",objGiayCnSucKhoe.getGcnskBenhmachmau()!=null?objGiayCnSucKhoe.getGcnskBenhmachmau():"");
			params.put("KHOP",objGiayCnSucKhoe.getGcnskKhop()!=null?objGiayCnSucKhoe.getGcnskKhop():"");
			params.put("XUONGCO",objGiayCnSucKhoe.getGcnskXuongco()!=null?objGiayCnSucKhoe.getGcnskXuongco():"");
			params.put("HOHAP",objGiayCnSucKhoe.getGcnskHohap()!=null?objGiayCnSucKhoe.getGcnskHohap():"");
			params.put("TIEUHOA",objGiayCnSucKhoe.getGcnskTieuhoa()!=null?objGiayCnSucKhoe.getGcnskTieuhoa():"");
			params.put("TIETNIEUSD",objGiayCnSucKhoe.getGcnskTietnieusd()!=null?objGiayCnSucKhoe.getGcnskTietnieusd():"");
			params.put("BENHNGOAIDA",objGiayCnSucKhoe.getGcnskBenhngoaidadlhl()!=null?objGiayCnSucKhoe.getGcnskBenhngoaidadlhl():"");
			params.put("CACBPKHAC",objGiayCnSucKhoe.getGcnskCacbophankhac()!=null?objGiayCnSucKhoe.getGcnskCacbophankhac():"");
			params.put("XETNGHIEM",objGiayCnSucKhoe.getGcnskXncanlamsang()!=null?objGiayCnSucKhoe.getGcnskXncanlamsang():"");
			params.put("KETLUAN",objGiayCnSucKhoe.getGcnskKetluan()!=null?objGiayCnSucKhoe.getGcnskKetluan():"");
			params.put("BACSIKL",objGiayCnSucKhoe.getGcnskBacsikl(true).getDtdmnhanvienTen()!=null?objGiayCnSucKhoe.getGcnskBacsikl(true).getDtdmnhanvienTen():"");
															
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintTD,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"tiepdon/", "pdf","giaychungnhansuckhoe");
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
		if (thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh() != null && !thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().equals("")) {
			ngaySinh = formatter.format(thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().getTime());
		}
		else if(thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNamsinh()!=null)
			ngaySinh = thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNamsinh();
	}

	public void displayInfor() throws Exception {
		try {
			
		if(sMaPhieuKVV==null|| sMaPhieuKVV.equals(""))
			return;
		
		GiayCnSucKhoeDelegate GiayCnSucKhoeDel=GiayCnSucKhoeDelegate.getInstance();
		objGiayCnSucKhoe=new GiayCnSucKhoe();
		List<GiayCnSucKhoe> ls=GiayCnSucKhoeDel.findByGiayCnSucKhoe(getMaPhieuKhamBenhVaoVien());
		
		if(ls==null||ls.size()==0)
		{
			FacesMessages.instance().add(IConstantsRes.RPPKBVV_NOT_EXIST);
			sMaPhieuKVV="";
			sShowDel="false";
			sShowPrint="false";
			return;
		}
		sShowDel="true";
		sShowPrint="true";
		objGiayCnSucKhoe=ls.get(0);
		
		if(objGiayCnSucKhoe.getGcnskMa() != null)
			sMaPhieuKVV = objGiayCnSucKhoe.getGcnskMa().toString();
		else
			sMaPhieuKVV = "";
		
		log.info("*****sMaPhieuKVV: "+sMaPhieuKVV)		;
		
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
		log.info("************************* destroy PhieuKbVaoVien");
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

	public String getGoToGiayChungNhanSucKhoe() {
		return goToGiayChungNhanSucKhoe;
	}

	public void setGoToGiayChungNhanSucKhoe(String str) {
		this.goToGiayChungNhanSucKhoe = str;
	}

	public String getMaPhieuKhamBenhVaoVien()
	{
		return sMaPhieuKVV;
	}

	public void setMaPhieuKhamBenhVaoVien(String maGiayChuyenVien)
	{
		sMaPhieuKVV = maGiayChuyenVien;
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

	public GiayCnSucKhoe getObjGiayCnSucKhoe() {
		return objGiayCnSucKhoe;
	}

	public void setObjGiayCnSucKhoe(GiayCnSucKhoe objGiayCnSucKhoe) {
		this.objGiayCnSucKhoe = objGiayCnSucKhoe;
	}

	public Integer getMaSoBacSi() {
		return iMaSoBacSi;
	}

	public void setMaSoBacSi(Integer maSoBacSi) {
		iMaSoBacSi = maSoBacSi;
	}

	public String getMaBacSi() {
		return sMaBacSi;
	}

	public void setMaBacSi(String maBacSi) {
		sMaBacSi = maBacSi;
	}

	

	

}



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

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.annotations.security.Restrict;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
import com.iesvn.yte.dieutri.entity.DtDmPlBhyt;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;


@Scope(CONVERSATION)
@Name("B3335_BaoCaoBHYTNgoaiTru")
@Synchronized(timeout = 6000000)
public class B3335_BaoCaoBHYTNgoaiTru implements Serializable {
	private static Logger log = Logger
			.getLogger(B3335_BaoCaoBHYTNgoaiTru.class);

	private String thoigian_thang=null;  
	private String thoigian_nam=null;
	private String tungay=null;
	private String denngay=null;
	private String ngayhientai;
	
	private Integer maSoPLBHYT=null;
	private String maPLBHYT = "";
	
	private Integer maSoKhoiBHYT = null;
	private String maKhoiBHYT = "";
	private String chonloaibo = "";
	private String chonloaibc = "";
	private String khoa_ma = "";
	
	private Integer tuyen; 
	
	@DataModel
	private List<DtDmKhoiBhyt> listkhoiBHYT = new ArrayList<DtDmKhoiBhyt>();

	@DataModelSelection("listkhoiBHYT")
	@Out(required = false)
	private DtDmKhoiBhyt dmkhoiBHYTSelected; 
	
	@Out(required=false)
	@In(required=false)
	private String reportPathVP=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeVP=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintVP=null;

	private int index=0;
	
	@Restrict("#{s:hasRole('NV_VienPhi') or s:hasRole('QT_VienPhi')}")
	@Begin(join = true)
	public String init() throws Exception {
		log.info("***Starting init ***");
		emtyData();
		log.info("***Finished init ***");

		SimpleDateFormat format = new SimpleDateFormat(Utils.FORMAT_DATE);
		ngayhientai = format.format(new Date());
		
		return "BaoCaoVienPhi_BaoCaoBHYT_BaoCaoBHYTNgoaiTru";
	}
	
	@End
	public void endFunct(){
		
	}
	public void emtyData() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		thoigian_thang=String.valueOf(currentDate.getMonth() +1);  
		thoigian_nam=String.valueOf(currentDate.getYear()+1900);
		tungay = sdf.format(currentDate);
		denngay = sdf.format(currentDate);
		setMaKhoiBHYT("");
		setMaPLBHYT("");
		setKhoa_ma("");
		setTuyen(null);
		setChonloaibo("r1");
		setChonloaibc("l1");
		listkhoiBHYT.clear();
	}

	public void resetValue() {
		emtyData();
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
	 * Method tra ve String truyen tham so vao file jrxml
	 */
	private String getListDVMaParamsHelp(List<Integer> inputs){
        log.info("Vao method getListDVMaParamsHelp ");
        log.info("Inputs: " + inputs.toString());
        String result = "";
        for(Integer each : inputs){
                if(each != null)
                	result += each.intValue() + ",";
        }
        result = result.substring(0, result.length()-1);
        log.info("Thoat method getListDVMaParamsHelp ");
        log.info("Output: " + result);
        return result;
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
	
	public String thuchienAction(){
		XuatReport();
		return "B3360_Chonmenuxuattaptin";
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeVP="BaocaoBHYTNgoaitru";
		log.info("Vao Method XuatReport bao cao BHYT ngoai tru");
		String baocao1=null;
		String pathTemplate=null;
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		try {
			if(chonloaibc.equals("l1")){	//phuc.lc 15-11-2010: Xuat mau bao cao 25a
				//pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/V23a_DSNguoibenhkhamchuabenhngoaitrudenghithanhtoan.jrxml";
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/VP25a_DSDeNghiThanhToanChiPhiKCBNgoaiTru.jrxml";
			}else if(chonloaibc.equals("l2")){ //phuc.lc 15-11-2010: Xuat mau bao cao 25b
				//pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/V23b_TKChiphichuabenhngoaitruquy.jrxml";
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/VP25b_ThongBaoThanhToanChiPhiKCBNgoaiTru.jrxml";
			}
			else if(chonloaibc.equals("l3")){ //phuc.lc 15-11-2010: Xuat mau bao cao 25c 
				// Mau 79aHD
			/*	pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/MauC79aHD_Template.jrxml";
				String sub0Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/V25_DSNguoibenhkhamchuabenhngoaitrudenghithanhtoan_MauC97aHD.jrxml";
				String sub1Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/MauC79aHD_Template_TramYTe.jrxml";
				log.info("da thay file templete " + pathTemplate);
				log.info("da thay file sub 0 templete " + sub0Template);
				log.info("da thay file sub 1 templete " + sub1Template);
				
				
				JasperReport sub0Report =JasperCompileManager.compileReport(sub0Template);
				JasperReport sub1Report =JasperCompileManager.compileReport(sub1Template);
				
				params.put("sub0", sub0Report);
				params.put("sub1", sub1Report);
				
				params.put("noitru", false);
			*/
				//pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/VP25c_ThongBaoThanhToanChiPhiKCBNgoaiTru.jrxml";
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/VP25a_TongHopChiPhiKCBNgoaiTru.jrxml";
				
			}
			else if(chonloaibc.equals("l4")){  //phuc.lc 15-11-2010: Xuat mau bao cao 14a
				//Mau 14a/GDYT
				/*
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/Baocaochiphikcbtheodoituongvatuyencmkt.jrxml";
				String sub0Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/V31_BCChiPhiKCBNgoaiTruTheoDTvaTuyenCMKT.jrxml";
				String sub1Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/chiphikcbtheotuyencmkt _ngoaitru_tuyenhuyen.jrxml";
				String sub2Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/chiphikcbtheotuyencmkt.jrxml";
				
				log.info("da thay file templete " + pathTemplate);
				log.info("da thay file sub 0 templete " + sub0Template);
				log.info("da thay file sub 1 templete " + sub1Template);
				log.info("da thay file sub 1 templete " + sub2Template);
				
				JasperReport sub0Report =JasperCompileManager.compileReport(sub0Template);
				JasperReport sub1Report =JasperCompileManager.compileReport(sub1Template);
				JasperReport sub2Report =JasperCompileManager.compileReport(sub2Template);
				params.put("sub0", sub0Report);
				params.put("sub1", sub1Report);
				params.put("sub2", sub2Report);
				
				params.put("NOITRU", false);
				*/
				params.put("TINH_BH", IConstantsRes.REPORT_DIEUTRI_TINH);
				params.put("TUYEN_BV", IConstantsRes.MASO_TUYEN_BV_TRIEN_KHAI);
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/VP14a_BaoCaoChiPhiKCBNgoaiTruCacNhomDTTheoTuyenCMKT.jrxml";
			
			} else if(chonloaibc.equals("l5")){ //phuc.lc 15-11-2010: Xuat mau bao cao 25d				
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/VP25d_ThongBaoThanhToanChiPhiKCBNgoaiTru.jrxml";
			}
			
			log.info("da thay file templete " + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			//##########
			params.put("ten_cskcb", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL);
			params.put("maso_cskcb", IConstantsRes.MASO_CO_SO_KCB);
			
			params.put("TuNgay", sdf.parse(tungay));
			params.put("DenNgay", sdf.parse(denngay));
			params.put("makhoa", khoa_ma);
			if(chonloaibo.equals("r1")){
				params.put("chon", "IN");
				log.info("chon:IN");
			}else if(chonloaibo.equals("r2")){
				params.put("chon", "NOT IN");
				log.info("chon:NOT IN");
			}
			if(listkhoiBHYT.size()>0){
				List<Integer> listtemp=new ArrayList<Integer>();
				for(DtDmKhoiBhyt item:listkhoiBHYT){
					listtemp.add(item.getDtdmkhoibhytMaso());
				}
				params.put("khoi", getListDVMaParamsHelp(listtemp));
				log.info("list phan loai " + getListDVMaParamsHelp(listtemp));
			
			}else{
				params.put("chon", "NOT IN");
				params.put("khoi", "0");
				log.info("khoi " + null);
			}
			if (!chonloaibc.equals("l4")) {
				params.put("ma_noi_tinh", IConstantsRes.TINH_BHYT_DEFAULT);
			}
			if(chonloaibc.equals("l1") || chonloaibc.equals("l3") ||  chonloaibc.equals("l5")){				
				params.put("maso_cskcb_cung_tinh", IConstantsRes.MASO_CO_SO_KCB_CUNG_TINH);
			} else if (chonloaibc.equals("l4")) {
				params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
				params.put("diadiem", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			}
			//#######
			/*params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("benhvien", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("sodt", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			params.put("TuNgay", sdf.parse(tungay));
			
			params.put("DenNgay", sdf.parse(denngay));
			if(chonloaibo.equals("r1")){
				params.put("chon", "IN");
			}else if(chonloaibo.equals("r1")){
				params.put("chon", "NOT IN");
			}
			
			if(listkhoiBHYT.size()>0){
				List<String> listtemp=new ArrayList<String>();
				for(DtDmKhoiBhyt item:listkhoiBHYT){
					listtemp.add(item.getDtdmkhoibhytMa());
				}
				params.put("khoi", getListDVMaParamsHelp(listtemp));
				log.info("list phan loai " + getListDVMaParamsHelp(listtemp));
			}
			
			DieuTriUtilDelegate dtUtils = DieuTriUtilDelegate.getInstance();
			if(chonloaibc.equals("l1")){
				DtDmPlBhyt plbhyt = new DtDmPlBhyt();
				Object obj = dtUtils.findByMa(maPLBHYT, "DtDmPlBhyt", "dtdmphloaibhytMa");
				if (obj != null){
					plbhyt = (DtDmPlBhyt)obj;
					params.put("TenPLDoiTuong", plbhyt.getDtdmphloaibhytTen() );
					params.put("MaPLDoiTuong", plbhyt.getDtdmphloaibhytMa() );
					log.info(" ma phan loai " + maPLBHYT + "ten phan loai " + plbhyt.getDtdmphloaibhytTen());
				}
			}
			*/
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","BaocaoBHYTNgoaitru");
			    reportPathVP=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathVP);
			    index+= 1;
			    log.info("Index :" + getIndex());
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Thoat Method XuatReport");
	}
	

	public Integer getTuyen() {
		return tuyen;
	}

	public void setTuyen(Integer tuyen) {
		this.tuyen = tuyen;
	}

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		B3335_BaoCaoBHYTNgoaiTru.log = log;
	}

	public Integer getMaSoPLBHYT() {
		return maSoPLBHYT;
	}

	public void setMaSoPLBHYT(Integer maSoPLBHYT) {
		this.maSoPLBHYT = maSoPLBHYT;
	}

	public String getMaPLBHYT() {
		return maPLBHYT;
	}

	public void setMaPLBHYT(String maPLBHYT) {
		this.maPLBHYT = maPLBHYT;
	}
	
	

	// Ham ghi nhan thuc thi sau khi nhan nut Ghi Nhan
	public void ghinhan() throws Exception {

	}

	public void nhaplai() throws Exception {
		ResetForm();
	}


	// Ham reset form
	private void ResetForm() {
		log.info("Begining ResetForm(): ");
		emtyData();
		log.info("End ResetForm(): ");
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

	public void setChonloaibo(String chonloaibo) {
		this.chonloaibo = chonloaibo;
	}

	public String getChonloaibo() {
		return chonloaibo;
	}

	public void setChonloaibc(String chonloaibc) {
		this.chonloaibc = chonloaibc;
	}

	public String getChonloaibc() {
		return chonloaibc;
	}

	public String getKhoa_ma() {
		return khoa_ma;
	}

	public void setKhoa_ma(String khoaMa) {
		khoa_ma = khoaMa;
	}
	
	}



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

import org.jboss.seam.ScopeType;
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
import com.iesvn.yte.dieutri.entity.DtDmPlBhyt;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Name("B3332_BaocaoBHYTnoitru")
@Scope(CONVERSATION)
public class BaocaoBHYTnoitru implements Serializable {
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
	
	@Out(scope=ScopeType.PAGE,required=false)
	@In(scope=ScopeType.PAGE,required=false)
	private String resetFormB3332;
	
	@DataModel
	private List<DtDmKhoiBhyt> listkhoiBHYTB3332 = new ArrayList<DtDmKhoiBhyt>();

	@DataModelSelection("listkhoiBHYTB3332")
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
	private String chonloaibo = "";
	private String chonloaibc = "";
	private String khoa_ma = "";
	private Integer masoPLBHYT = null;
	private String maPLBHYT="";
	
	@Factory("resetFormB3332")	
	public void initTmp() {
		log.info("init()");
		ngayhientai = Utils.getCurrentDate();
		resetForm();
	}
	
	@Begin(join=true)
	public String init() {
		return "BaoCaoVienPhi_BaoCaoBHYT_BaoCaoBHYTNoiTru";
	}
	@End
	public void end(){
		
	}
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		resetFormB3332 = "";
		thoigian_thang=String.valueOf(currentDate.getMonth() +1);  
		thoigian_nam=String.valueOf(currentDate.getYear()+1900);
		tungay = sdf.format(currentDate);
		denngay = sdf.format(currentDate);
		setChonloaibc("l1");
		setChonloaibo("r1");
		setTuyen(null);
		setKhoa_ma("");		
		listkhoiBHYTB3332.clear();
		setMaPLBHYT("");
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
	
	public String thuchienAction(){
		XuatReport();
		resetFormB3332=null;
		return "B3360_Chonmenuxuattaptin";
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
			if((listkhoiBHYTB3332.size()==0) && (maKhoiBHYT !=null)){
				log.info("size list bang 0");
				Object obj=dtutilDelegate.findByMa(maKhoiBHYT , "DtDmKhoiBhyt", "dtdmkhoibhytMa");
				khoiBHYT=(DtDmKhoiBhyt)obj;
				listkhoiBHYTB3332.add(khoiBHYT);
				log.info("da add phan tu dau tien" + listkhoiBHYTB3332.size());
			}else if ((listkhoiBHYTB3332.size()>0) && (maKhoiBHYT !=null)){
				log.info("size list lon hon 0");
				for(DtDmKhoiBhyt item:listkhoiBHYTB3332){
					if(item.getDtdmkhoibhytMa().equals(maKhoiBHYT)){
						test=true;
					}
				}
				if(test == false){
					Object obj=dtutilDelegate.findByMa(maKhoiBHYT , "DtDmKhoiBhyt", "dtdmkhoibhytMa");
					khoiBHYT=(DtDmKhoiBhyt)obj;
					listkhoiBHYTB3332.add(khoiBHYT);
				}
				log.info("da add phan tu" + listkhoiBHYTB3332.size());
			}
		}
		setMaKhoiBHYT("");
	}
	
	
	/**
	 * Ham xoa mot record
	 */
	public void deletedmkhoiBHYT(){
		log.info("bat dau xoa , size" + listkhoiBHYTB3332.size());
		listkhoiBHYTB3332.remove(dmkhoiBHYTB3332Selected);
		log.info("da xoa , size" + listkhoiBHYTB3332.size());
		log.info("ket thuc xoa");
	} 
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeVP="BaocaoBHYTnoitru";
		log.info("Vao Method XuatReport bao cao BHYT noi tru");
		String baocao1=null;
		String pathTemplate = null;
		try {
			log.info("bat dau method XuatReport ");
			
			Map<String, Object> params = new HashMap<String, Object>();
			
			if(chonloaibc.equals("l1")){   // phuc.lc 10-11-2010 : Xuat mau bao cao 26a
				//pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/V22a_DSNguoibenhkhamchuabenhnoitrudenghithanhtoan.jrxml";
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/VP26a_DSDeNghiThanhToanChiPhiKCBNoiTru.jrxml";
			}else if(chonloaibc.equals("l2")){ // phuc.lc 10-11-2010 : Xuat mau bao cao 26b
				//pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/V22b_THChiphikhamchuabenhcuabenhnhanngoaitinhquy.jrxml";
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/VP26b_ThongBaoThanhToanChiPhiKCBNoiTru.jrxml";
			}else if(chonloaibc.equals("l3")){ // phuc.lc 11-11-2010 : Xuat mau bao cao 26c
				//C80a-HD
				//pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/MauC79aHD_Template.jrxml";
				//String sub0Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/V26_DSNguoibenhkhamchuabenhnoitrudenghithanhtoan_MauC80aHD.jrxml";
				//String sub1Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/MauC79aHD_Template_TramYTe.jrxml";
				//log.info("da thay file templete " + pathTemplate);
				//log.info("da thay file sub 0 templete " + sub0Template);
				//log.info("da thay file sub 1 templete " + sub1Template);
				
				
				//JasperReport sub0Report =JasperCompileManager.compileReport(sub0Template);
				//JasperReport sub1Report =JasperCompileManager.compileReport(sub1Template);
				
				//params.put("sub0", sub0Report);
				//params.put("sub1", sub1Report);
				
				//params.put("noitru", true);
				//pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/VP26c_ThongBaoThanhToanChiPhiKCBNoiTru.jrxml";
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/VP26a_TongHopChiPhiKCBNoiTru.jrxml";
			}else if(chonloaibc.equals("l4")){ // phuc.lc 11-11-2010 : Xuat mau bao cao 14b								
				//pathTemplate = IConstantsRes.PATH_BASE+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+ "vienphi/V32_BCChiPhiKCBNoiTruTheoDTvaTuyenCMKT.jrxml";
				//params.put("NoiTinh", IConstantsRes.NOI_TINH);
				params.put("TINH_BH", IConstantsRes.REPORT_DIEUTRI_TINH);
				params.put("TUYEN_BV", IConstantsRes.MASO_TUYEN_BV_TRIEN_KHAI);
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/VP14b_BaoCaoChiPhiKCBNoiTruCacNhomDTTheoTuyenCMKT.jrxml";
				
			} else if(chonloaibc.equals("l5")){ // phuc.lc 11-11-2010 : Xuat mau bao cao 26d
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/VP26d_ThongBaoThanhToanChiPhiKCBNoiTru.jrxml";
			}
			log.info("da thay file templete " + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			//if(chonloaibc.equals("l1")){   // phuc.lc 10-11-2010 : Xuat mau bao cao 26a
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
				if(listkhoiBHYTB3332.size()>0){
					List<Integer> listtemp=new ArrayList<Integer>();
					for(DtDmKhoiBhyt item:listkhoiBHYTB3332){
						listtemp.add(item.getDtdmkhoibhytMaso());
					}
					String tempStr = getListDVMaParamsHelp(listtemp);
					params.put("khoi", tempStr);
					
					log.info("khoi " + tempStr);
				}else{
					params.put("chon", "NOT IN");
					params.put("khoi", "0");
					log.info("khoi " + null);
				}
			//}
				if (!chonloaibc.equals("l4")) {
					params.put("ma_noi_tinh", IConstantsRes.TINH_BHYT_DEFAULT);
				}
				if(chonloaibc.equals("l1") || chonloaibc.equals("l3") ||  chonloaibc.equals("l5")){
					params.put("maso_cskcb_cung_tinh", IConstantsRes.MASO_CO_SO_KCB_CUNG_TINH);
				} else if (chonloaibc.equals("l4")) {
					params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
					params.put("diadiem", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
				}
			    Integer congNgayDT = (IConstantsRes.CONG_VAO_SO_NGAY_DIEU_TRI.trim().length() > 0 ? new Integer(IConstantsRes.CONG_VAO_SO_NGAY_DIEU_TRI) : new Integer("0"));
			    params.put("cong_ngay_dt", congNgayDT);
			
			
			
			
			
			
			/*
			log.info("maPLBHYT:"+maPLBHYT);
			
			DieuTriUtilDelegate dtUtils = DieuTriUtilDelegate.getInstance();
			if(chonloaibc.equals("l1")){
				DtDmPlBhyt plbhyt = new DtDmPlBhyt();
				log.info("maPLBHYT:"+maPLBHYT);
				Object obj = dtUtils.findByMa(maPLBHYT, "DtDmPlBhyt", "dtdmphloaibhytMa");
				if (obj != null){
					plbhyt = (DtDmPlBhyt)obj;
					params.put("TenPLDoiTuong", plbhyt.getDtdmphloaibhytTen() );
					params.put("MaPLDoiTuong", plbhyt.getDtdmphloaibhytMa() );
					
					
					log.info("MaPLDoiTuong:"+plbhyt.getDtdmphloaibhytMa());
					
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","BaocaoBHYTnoitru");
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


	public String getChonloaibo() {
		return chonloaibo;
	}


	public void setChonloaibo(String chonloaibo) {
		this.chonloaibo = chonloaibo;
	}


	public String getChonloaibc() {
		return chonloaibc;
	}


	public void setChonloaibc(String chonloaibc) {
		this.chonloaibc = chonloaibc;
	}


	public void setKhoa_ma(String khoa_ma) {
		this.khoa_ma = khoa_ma;
	}


	public String getKhoa_ma() {
		return khoa_ma;
	}

	public Integer getMasoPLBHYT() {
		return masoPLBHYT;
	}

	public void setMasoPLBHYT(Integer masoPLBHYT) {
		this.masoPLBHYT = masoPLBHYT;
	}

	public String getMaPLBHYT() {
		return maPLBHYT;
	}

	public void setMaPLBHYT(String maPLBHYT) {
		this.maPLBHYT = maPLBHYT;
	}
}

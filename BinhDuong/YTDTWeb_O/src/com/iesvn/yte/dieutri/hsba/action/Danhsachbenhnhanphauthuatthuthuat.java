/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.jboss.seam.ScopeType;
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
import com.iesvn.yte.dieutri.entity.DtDmLoaiPhauThuat;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmTaiNan;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Name("Danhsachbenhnhanphauthuatthuthuat")
@Scope(SESSION)
public class Danhsachbenhnhanphauthuatthuthuat implements Serializable {
	@Logger
	private Log log;
	
	@Out(required=false)
	@In(required=false)
	private String reportPath=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportPath5b=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportType=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jPrint = null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jPrint1 = null;
	
	@In(scope=ScopeType.SESSION,required=false)
	@Out(scope=ScopeType.SESSION,required=false)
	private String resetFormB225=null;
	
	@DataModel
	private List<DtDmLoaiPhauThuat> listDtDmLoaiPT = new ArrayList<DtDmLoaiPhauThuat>();

	@DataModelSelection("listDtDmLoaiPT")
	@Out(required = false)
	private DtDmLoaiPhauThuat dtDmLoaiPTSelect;
	
	/*
	 * bien chua tieu chi xuat bao cao
	 */
	
	private String thoigian_thang=null;  
	private String thoigian_nam=null;
	private String tungay=null;
	private String denngay=null;
	private static int index=0;
	private static int index5b=0;
	private Integer khoa_maso=null;
	private Integer doituong_maso=null;
	private Integer tainan_maso=null;
	private Integer bacsi_maso=null;
	private Integer pt_maso=null;
	private String khoa_ma=null;
	private String doituong_ma=null;
	private String tainan_ma=null;
	private String bacsi_ma=null;
	private String pt_ma=null;
	private String loaiPT_ma=null;
	private String gtSelect;
	private String capcuuSelect;
	private String songSelect;
	private String ngayhientai;
	
	
	@Factory("resetFormB225")
	public void init() {
		log.info("init()");
		ngayhientai = Utils.getCurrentDate();
		resetForm();
	}
	
	
	/*
	 * chon bao cao cua huyen hay xa
	 */
	public String thuchienAction(){
		XuatReport();
		resetForm();
		resetFormB225 = null;
		return "B225_Chonmenuxuattaptin";
	}
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		setThoigian_nam(null);
		setThoigian_thang(null);
		setTungay("");
		setDenngay("");
		setKhoa_ma(null);
		setTainan_ma(null);
		setDoituong_ma(null);
		setBacsi_ma(null);
		setPt_ma(null);
		setLoaiPT_ma(null);
		setGtSelect("namnu");
		setCapcuuSelect("ccphien");
		setSongSelect("songchet");
		listDtDmLoaiPT.clear();
	}
	
	/**
	 * ham them mot phan tu vao DS
	 */
	public void enter(){
		log.info("bat dau them du lieu vao luoi");
		DtDmLoaiPhauThuat loaiPT;
		boolean test=false;
		DieuTriUtilDelegate dtutilDelegate=DieuTriUtilDelegate.getInstance();
		if((listDtDmLoaiPT.size()==0) && (loaiPT_ma !=null)){
			log.info("size list bang 0");
			Object obj=dtutilDelegate.findByMa(loaiPT_ma , "DtDmLoaiPhauThuat", "dtdmloaiptMa");
			loaiPT=(DtDmLoaiPhauThuat)obj;
			listDtDmLoaiPT.add(loaiPT);
			log.info("da add phan tu dau tien" + listDtDmLoaiPT.size());
		}else if ((listDtDmLoaiPT.size()>0) && (getLoaiPT_ma() !=null)){
			log.info("size list lon hon 0");
			for(DtDmLoaiPhauThuat item:listDtDmLoaiPT){
				if(item.getDtdmloaiptMa().equals(getLoaiPT_ma())){
					test=true;
				}
			}
			if(test == false){
				Object obj=dtutilDelegate.findByMa(loaiPT_ma ,"DtDmLoaiPhauThuat", "dtdmloaiptMa");
				loaiPT=(DtDmLoaiPhauThuat)obj;
				listDtDmLoaiPT.add(loaiPT);
			}
			log.info("da add phan tu" + listDtDmLoaiPT.size());
		}
	}
	
	
	/**
	 * Ham xoa mot record
	 */
	public void deleteDtDmLoaiPT(){
		log.info("bat dau xoa , size" + listDtDmLoaiPT.size());
		listDtDmLoaiPT.remove(dtDmLoaiPTSelect);
		log.info("da xoa , size" + listDtDmLoaiPT.size());
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
	 */
	public void XuatReport() {
		reportType="DSBNPhauthuatThuThuat";
		log.info("Vao Method XuatReport Danh sach benh nhan phau thuat thu thuat");
		String resultReportName = null;
		String baocao1=null;
		String baocao2=null;
		try {
			log.info("bat dau method XuatReport ");
			String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/B05_Benhnhanphauthuat.jrxml";
			String pathTemplate5b = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/B05b_Benhnhanphauthuat.jrxml";
			
			log.info("da thay file templete 5" + pathTemplate);
			log.info("da thay file templete 5b" + pathTemplate5b);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			JasperReport jasperReport5b =JasperCompileManager.compileReport(pathTemplate5b);
			
			log.info("da thay file template ");
			DieuTriUtilDelegate dtutilDelegate=DieuTriUtilDelegate.getInstance();
			Map<String, Object> params = new HashMap<String, Object>();
			
			//tim khoa
			if(khoa_maso!=null){
				log.info("khoa ma " + khoa_maso );
				params.put("KHOAMASO", new Integer(khoa_maso));
				DmKhoa dmkhoa=new DmKhoa();
				Object obj=dtutilDelegate.findByMa(khoa_ma, "DmKhoa", "dmkhoaMa");
				dmkhoa=(DmKhoa)obj;
				log.info("Dm khoa tim thay"  +dmkhoa);
				params.put("KHOA", dmkhoa.getDmkhoaTen());
			}
			//tim doi tuong
			if(doituong_maso!=null){
				log.info("doi tuong " + doituong_maso);
				params.put("DOITUONGMASO", doituong_maso);
				DmDoiTuong dmdoituong=new DmDoiTuong();
				Object obj=dtutilDelegate.findByMa(doituong_ma, "DmDoiTuong", "dmdoituongMa");
				dmdoituong=(DmDoiTuong)obj;
				log.info("Dm doi tuong tim thay"  +dmdoituong);
				params.put("DOITUONG", dmdoituong.getDmdoituongTen());
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
			//tim bac si
			if(bacsi_maso!=null){
				log.info("bac si  ma" +bacsi_maso );
				params.put("BACSIMASO", bacsi_maso);
				DtDmNhanVien dtdmnhanvien=new DtDmNhanVien();
				Object obj=dtutilDelegate.findByMa(bacsi_ma, "DtDmNhanVien", "dtdmnhanvienMa");
				dtdmnhanvien=(DtDmNhanVien)obj;
				log.info("Dm nhan vien tim thay"  +dtdmnhanvien);
				params.put("BACSI", dtdmnhanvien.getDtdmnhanvienTen());
			}
			//tim phau thuat
			if(pt_maso!=null){
				log.info("phau thuat " + pt_maso);
				params.put("PTMASO", pt_maso);
				params.put("MAMO", pt_ma);
			}
			
			if(!tungay.equals("")){
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				params.put("TUNGAY", tungay);
				params.put("TUNGAYDATE", sdf.parse(tungay));
				if(!denngay.equals("")){
					params.put("DENNGAY", denngay);
					params.put("DENNGAYDATE", sdf.parse(denngay));
				}
			}
			
			//truyen tham so vao benh vien
			log.info("banh vien xuat bao cao "+ IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			
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
			
			//Truyen tham so cap cuu hay phien
			if(capcuuSelect !=null){
				if(capcuuSelect.equals("capcuu")){
					log.info("3");
					params.put("CCMASO", new Integer(1));
					params.put("CCPHIEN", "C\u1ea5p c\u1ee9u");
				}else if(capcuuSelect.equals("phien")){
					log.info("4");
					params.put("CCMASO", new Integer(2));
					params.put("CCPHIEN", "Phi\u00ean");
				}
			}
			
			//Truyen tham so song hay chet
			if(songSelect != null){
				if(songSelect.equals("chet")){
					log.info("vao method in song chet");
					params.put("KETQUADT", "Ch\u1ebft");
				}else{
					params.put("KETQUADT", "S\u1ed1ng");
				}
			}
			
			//Truyen tham so danh sach loai phau thuat
			if(listDtDmLoaiPT.size()>0){
				log.info("bat dau add danh sach loai phau thuat");
				List<String> listTemp=new ArrayList<String>();
				for(DtDmLoaiPhauThuat item:listDtDmLoaiPT){
					listTemp.add(item.getDtdmloaiptMa());
				}
				String listLoaiPT=getListDVMaParamsHelp(listTemp);
				log.info("danh sach loai phau thuat" + listLoaiPT);
				params.put("LOAIPTMASO", listLoaiPT);
			}
			
			log.info("================= khoa ma" + khoa_maso );
			Class.forName("oracle.jdbc.OracleDriver");
			    log.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jPrint =  JasperFillManager.fillReport(jasperReport,params, conn);
			    jPrint1 =  JasperFillManager.fillReport(jasperReport5b,params, conn);
			    
			    baocao1=XuatReportUtil.ReportUtil(jPrint,Danhsachbenhnhanphauthuatthuthuat.index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"hsba/", "pdf","Danhsachbenhnhanphauthuat");
			    baocao2=XuatReportUtil.ReportUtil(jPrint1,Danhsachbenhnhanphauthuatthuthuat.index5b,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"hsba/", "pdf","Danhsachbenhnhanphauthuat5b");
			    reportPath=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    reportPath5b=baocao2.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPath);
			    log.info("duong dan file xuat report 5b:" + baocao2);
			    log.info("duong dan 5b-------------------- :"+reportPath5b);
			    Danhsachbenhnhanphauthuatthuthuat.index+=1;
			    Danhsachbenhnhanphauthuatthuthuat.index5b+=1;
			    log.info("Index :" + getIndex());
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
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

	public static void setIndex(int index) {
		Danhsachbenhnhanphauthuatthuthuat.index = index;
	}

	public static int getIndex() {
		return index;
	}

	public void setLoaiPT_ma(String loaiPT_ma) {
		this.loaiPT_ma = loaiPT_ma;
	}

	public String getLoaiPT_ma() {
		return loaiPT_ma;
	}

	public String getGtSelect() {
		return gtSelect;
	}

	public void setGtSelect(String gtSelect) {
		this.gtSelect = gtSelect;
	}

	public String getCapcuuSelect() {
		return capcuuSelect;
	}

	public void setCapcuuSelect(String capcuuSelect) {
		this.capcuuSelect = capcuuSelect;
	}

	public String getSongSelect() {
		return songSelect;
	}

	public void setSongSelect(String songSelect) {
		this.songSelect = songSelect;
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

	public Integer getBacsi_maso() {
		return bacsi_maso;
	}

	public void setBacsi_maso(Integer bacsi_maso) {
		this.bacsi_maso = bacsi_maso;
	}

	public Integer getPt_maso() {
		return pt_maso;
	}

	public void setPt_maso(Integer pt_maso) {
		this.pt_maso = pt_maso;
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

	public String getBacsi_ma() {
		return bacsi_ma;
	}

	public void setBacsi_ma(String bacsi_ma) {
		this.bacsi_ma = bacsi_ma;
	}

	public String getPt_ma() {
		return pt_ma;
	}

	public void setPt_ma(String pt_ma) {
		this.pt_ma = pt_ma;
	}

	public static void setIndex5b(int index5b) {
		Danhsachbenhnhanphauthuatthuthuat.index5b = index5b;
	}

	public static int getIndex5b() {
		return index5b;
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


	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
	}


	public String getNgayhientai() {
		return ngayhientai;
	}
}

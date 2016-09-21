
package com.iesvn.yte.dieutri.duocpham.action;

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

import javax.faces.model.SelectItem;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.log.Log;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.entity.DmNguonChuongTrinh;
import com.iesvn.yte.entity.DmNguonKinhPhi;
import com.iesvn.yte.entity.DmPhanLoaiThuoc;
import com.iesvn.yte.entity.DmPhanNhomThuoc;
import com.iesvn.yte.entity.DmQuocGia;
import com.iesvn.yte.util.IConstantsRes;



@Scope(CONVERSATION)
@Name("B4134_Tinhtonkhovagiatri")
@Synchronized(timeout = 6000000)
public class TinhTonKhoVaGiaTri implements Serializable {
	private static final long serialVersionUID = 10L;
	
	@org.jboss.seam.annotations.Logger
	private Log log;
	
	@Out(required=false)
	@In(required=false)
	private String reportPathKC=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeKC=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintKC = null;
	
	@Out(required=false)
	@In(required=false)
	private String resetFormB4134=null;
	
	@DataModel
	private List<DmPhanLoaiThuoc> listDmPLThuocKK = new ArrayList<DmPhanLoaiThuoc>();

	@DataModelSelection("listDmPLThuocKK")
	@Out(required = false)
	private DmPhanLoaiThuoc dmPLThuocSelectKK;
	
	private List<SelectItem> ttIn=null;
	private Integer inTheo=null;
	private int index=0;
	private String ngayhientai;
	private String ngayNHAPLIEU;
	private String nienHAN= "";
	private Integer loaihang_maso=null;
	private Integer plthuoc_maso=null;
	private Integer khoa_maso=null;
	private Integer ct_maso=null;
	private Integer kp_maso=null;
	private Integer nsx_maso=null;
	private Boolean chon=false;
	private String plthuoc_ma="";
	private String loaihang_ma="";
	private String khoa_ma="";
	private String ct_ma="";
	private String kp_ma="";
	private String nsx_ma = "";
	private boolean baoGOMTON=false;
	Map<String, Object> params = null;
	
	String dmKhoXuat = "";
	private String nhomthuoc_ma ="";
	private Integer nhomthuoc_maso = null;
	
	public String getDmKhoXuat() {
		return dmKhoXuat;
	}
	public void setDmKhoXuat(String dmKhoXuat) {
		this.dmKhoXuat = dmKhoXuat;
	}
	
	@Factory("resetFormB4134")
	public void initresetFormB4134() {
		log.info("init()");
		ngayhientai = com.iesvn.yte.util.Utils.getCurrentDate();
		ngayNHAPLIEU = com.iesvn.yte.util.Utils.getCurrentDate();
		resetForm();
	}
	
	@In(required = false)
	@Out(required = false)
	private String tenChuongTrinh;
	
	@Begin (join = true)
	public String init(String kho) {
		if("ALL".equals(kho)){
			tenChuongTrinh = MyMenuYTDTAction.baoCaoDuoc;
		}
		dmKhoXuat = kho;
		return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_TinhTonKhoVaGiaTri";
	}
	@End
	public void end(){
		
	}
	
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetList(){
		log.info("=============reset listttttttttttt");
		listDmPLThuocKK.clear();
		setPlthuoc_ma("");
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
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		resetFormB4134 = "";
		setPlthuoc_ma("");
		listDmPLThuocKK.clear();
		setLoaihang_ma("");
		setCt_ma("");
		setKp_ma("");
		setNsx_ma("");
		setNienHAN("");
		setBaoGOMTON(false);
		setInTheo(new Integer(1));
		nhomthuoc_ma ="";
	}
	
	/**
	 * ham them mot phan tu vao DS
	 */
	public void enter(){
		log.info("bat dau them du lieu vao luoi");
		DmPhanLoaiThuoc plThuoc;
		boolean test=false;
		DieuTriUtilDelegate dtutilDelegate=DieuTriUtilDelegate.getInstance();
		if(!plthuoc_ma.equals("")){
			if((listDmPLThuocKK.size()==0) && (plthuoc_ma !=null)){
				log.info("size list bang 0");
				Object obj=dtutilDelegate.findByMa(plthuoc_ma , "DmPhanLoaiThuoc", "dmphanloaithuocMa");
				plThuoc=(DmPhanLoaiThuoc)obj;
				if(plThuoc != null)
					listDmPLThuocKK.add(plThuoc);
				log.info("da add phan tu dau tien" + listDmPLThuocKK.size());
			}else if ((listDmPLThuocKK.size()>0) && (plthuoc_ma !=null)){
				log.info("size list lon hon 0");
				for(DmPhanLoaiThuoc item:listDmPLThuocKK){
					if(item.getDmphanloaithuocMa().equals(plthuoc_ma)){
						test=true;
					}
				}
				if(test == false){
					Object obj=dtutilDelegate.findByMa(plthuoc_ma , "DmPhanLoaiThuoc", "dmphanloaithuocMa");
					plThuoc=(DmPhanLoaiThuoc)obj;
					if(plThuoc != null)
						listDmPLThuocKK.add(plThuoc);
				}
				log.info("da add phan tu" + listDmPLThuocKK.size());
			}
		}
		setPlthuoc_ma("");
	}
	
	
	/**
	 * Ham xoa mot record
	 */
	public void deletedmPLThuoc(){
		log.info("bat dau xoa , size" + listDmPLThuocKK.size());
		listDmPLThuocKK.remove(dmPLThuocSelectKK);
		log.info("da xoa , size" + listDmPLThuocKK.size());
		log.info("ket thuc xoa");
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeKC="Tinhtonkhovagiatri";
		log.info("Vao Method XuatReport cap nhat bang kiem ke");
		String baocao1=null;
		String pathTemplate=null;
		try {
			log.info("bat dau method XuatReport ");
			if(inTheo == 1){
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D07_Baocaotonkho_gopgia.jrxml";
			}else if(inTheo == 2){
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D07_Baocaotonkho_tachgia.jrxml";
			}else if(inTheo == 3){
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D07_Baocaotonkho_goplo.jrxml";
			}
			log.info("da thay file templete 5" + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			
			
			log.info("da thay file template ");
			log.info("================= ");
			Class.forName(IConstantsRes.ORACLE_DRIVER);
			    log.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintKC =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintKC,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"duocpham/", "pdf","Tinhtonkhovagiatri");
			    reportPathKC=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathKC);
			    index+=1;
			    log.info("Index :" + getIndex());
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Thoat Method XuatReport");
	}
			
	public String thuchienAction(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		params=new HashMap<String, Object>();
		DieuTriUtilDelegate dtutils = DieuTriUtilDelegate.getInstance();
		params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
		params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
		params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
		params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
		try{
			if(baoGOMTON == false ){
				params.put("TON0", new Integer(0));
			}
			
			if(loaihang_ma != null && !loaihang_ma.trim().equals("")){
				DmLoaiThuoc dmlthuoc =new DmLoaiThuoc();
				Object objlt = dtutils.findByMa(loaihang_ma , "DmLoaiThuoc", "dmloaithuocMa");
				dmlthuoc = (DmLoaiThuoc)objlt;
				params.put("MALOAI", loaihang_maso);
				params.put("LOAI", dmlthuoc.getDmloaithuocTen());
			}else{
				loaihang_maso = null;
				params.put("MALOAI", loaihang_maso);
				params.put("LOAI", "T\u1EA5t c\u1EA3");
			}
			log.info("MALOAI " + loaihang_maso);
			if(!khoa_ma.trim().equals("")){
				params.put("MAKHO", khoa_maso);
				DmKhoa dmkhoa = new DmKhoa();
				Object objK = dtutils.findByMa(khoa_ma , "DmKhoa", "dmkhoaMa");
				dmkhoa = (DmKhoa)objK;
				params.put("THUOCKHO", dmkhoa.getDmkhoaTen());	
			}else{
				khoa_maso = null;
				params.put("MAKHO", khoa_maso);
			}
			log.info("MAKHO " + khoa_maso);
			if(!ct_ma.trim().equals("")){
				DmNguonChuongTrinh dmnct = new DmNguonChuongTrinh();
				Object objnct = dtutils.findByMa(ct_ma , "DmNguonChuongTrinh", "dmnctMa");
				dmnct = (DmNguonChuongTrinh)objnct;
				params.put("MACT", ct_maso);
				params.put("NGUON", dmnct.getDmnctTen());
			}else{
				ct_maso = null;
				params.put("MACT", ct_maso);
				params.put("NGUON","T\u1EA5t c\u1EA3");
			}	
			log.info("MACT " + ct_maso);
			if(!kp_ma.trim().equals("")){
				params.put("MAKP", kp_maso);
				DmNguonKinhPhi dmnkp = new DmNguonKinhPhi();
				Object objnkp = dtutils.findByMa(kp_ma , "DmNguonKinhPhi", "dmnguonkinhphiMa");
				dmnkp = (DmNguonKinhPhi)objnkp;
				params.put("KINHPHI", dmnkp.getDmnguonkinhphiTen());	
			}else{
				kp_maso = null;
				params.put("KINHPHI","T\u1EA5t c\u1EA3");
				params.put("MAKP", kp_maso);
			}
			log.info("MAKP " + kp_maso);
			if(!nsx_ma.trim().equals("")){
				params.put("MANSX", nsx_maso);
				DmQuocGia dmqg = new DmQuocGia();
				Object objnsx = dtutils.findByMa(nsx_ma , "DmQuocGia", "dmquocgiaMa");
				dmqg = (DmQuocGia)objnsx;
				params.put("NSX", dmqg.getDmquocgiaTen());
			}else{
				nsx_maso = null;
				params.put("NSX", "T\u1EA5t c\u1EA3");
				params.put("MANSX", nsx_maso);
			}
			log.info("MANSX " + nsx_maso);
			
			String strInputTonDauDate = IConstantsRes.INPUT_TONDAU;		
			Date dNgayTonCuoi = sdf.parse(strInputTonDauDate);			
			if(!ngayNHAPLIEU.trim().equals("")){
				Date dNgayNhapLieu = sdf.parse(ngayNHAPLIEU);
				if(dNgayNhapLieu.before(dNgayTonCuoi) || dNgayNhapLieu.equals(dNgayTonCuoi)){
					params.put("NGAY", dNgayTonCuoi);
				}else{
					params.put("NGAY", dNgayNhapLieu);
				}				
			}
						
			if(listDmPLThuocKK.size()>0){
				List<String> listtemp=new ArrayList<String>();
				for(DmPhanLoaiThuoc item:listDmPLThuocKK){
					listtemp.add(item.getDmphanloaithuocMa());
				}
				params.put("PHANLOAI", getListDVMaParamsHelp(listtemp));
				log.info("list phan loai " + getListDVMaParamsHelp(listtemp));				
			}else{
				params.put("PHANLOAI", null);
			}
			
			if(!nhomthuoc_ma.equals("")){
				DmPhanNhomThuoc dmpnthuoc =new DmPhanNhomThuoc();
				dmpnthuoc = (DmPhanNhomThuoc)dtutils.findByMa(nhomthuoc_ma , "DmPhanNhomThuoc", "dmphannhomthuocMa");
				params.put("NHOMTHUOCMASO", dmpnthuoc.getDmphannhomthuocMaso());
			}else{
				params.put("NHOMTHUOCMASO", null);
			}
			XuatReport();
			resetFormB4134=null;
			return "B4160_Chonmenuxuattaptin";
		}catch(Exception ex){
			log.info("That bai ");
			ex.printStackTrace();
		}
		return null;
	}
	
	public String getNgayhientai() {
		return ngayhientai;
	}

	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
	}

	public Integer getLoaihang_maso() {
		return loaihang_maso;
	}

	public void setLoaihang_maso(Integer loaihang_maso) {
		this.loaihang_maso = loaihang_maso;
	}

	public Integer getPlthuoc_maso() {
		return plthuoc_maso;
	}

	public void setPlthuoc_maso(Integer plthuoc_maso) {
		this.plthuoc_maso = plthuoc_maso;
	}

	public Integer getKhoa_maso() {
		return khoa_maso;
	}

	public void setKhoa_maso(Integer khoa_maso) {
		this.khoa_maso = khoa_maso;
	}

	public Integer getCt_maso() {
		return ct_maso;
	}

	public void setCt_maso(Integer ct_maso) {
		this.ct_maso = ct_maso;
	}

	public Integer getKp_maso() {
		return kp_maso;
	}

	public void setKp_maso(Integer kp_maso) {
		this.kp_maso = kp_maso;
	}

	public Boolean getChon() {
		return chon;
	}

	public void setChon(Boolean chon) {
		this.chon = chon;
	}

	public String getPlthuoc_ma() {
		return plthuoc_ma;
	}

	public void setPlthuoc_ma(String plthuoc_ma) {
		this.plthuoc_ma = plthuoc_ma;
	}

	public String getLoaihang_ma() {
		return loaihang_ma;
	}

	public void setLoaihang_ma(String loaihang_ma) {
		this.loaihang_ma = loaihang_ma;
	}

	public String getKhoa_ma() {
		return khoa_ma;
	}

	public void setKhoa_ma(String khoa_ma) {
		this.khoa_ma = khoa_ma;
	}

	public String getCt_ma() {
		return ct_ma;
	}

	public void setCt_ma(String ct_ma) {
		this.ct_ma = ct_ma;
	}

	public String getKp_ma() {
		return kp_ma;
	}

	public void setKp_ma(String kp_ma) {
		this.kp_ma = kp_ma;
	}

	public void setTtIn(List<SelectItem> ttIn) {
		this.ttIn = ttIn;
	}

	public List<SelectItem> getTtIn() {
		if(ttIn==null){
			ttIn=new ArrayList<SelectItem>();
			ttIn.add(new SelectItem(1,"In theo m\u00e3 h\u00e0ng"));
			ttIn.add(new SelectItem(2,"In theo t\u00ean h\u00e0ng"));
		}
		return ttIn;
	}

	public void setInTheo(Integer inTheo) {
		this.inTheo = inTheo;
	}

	public Integer getInTheo() {
		return inTheo;
	}

	public Integer getNsx_maso() {
		return nsx_maso;
	}

	public void setNsx_maso(Integer nsx_maso) {
		this.nsx_maso = nsx_maso;
	}

	public String getNsx_ma() {
		return nsx_ma;
	}

	public void setNsx_ma(String nsx_ma) {
		this.nsx_ma = nsx_ma;
	}

	public void setNgayNHAPLIEU(String ngayNHAPLIEU) {
		this.ngayNHAPLIEU = ngayNHAPLIEU;
	}

	public String getNgayNHAPLIEU() {
		return ngayNHAPLIEU;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public void setNienHAN(String nienHAN) {
		this.nienHAN = nienHAN;
	}

	public String getNienHAN() {
		return nienHAN;
	}

	public boolean isBaoGOMTON() {
		return baoGOMTON;
	}

	public void setBaoGOMTON(boolean baoGOMTON) {
		this.baoGOMTON = baoGOMTON;
	}
	
	public Integer getNhomthuoc_maso() {
		return nhomthuoc_maso;
	}

	public void setNhomthuoc_maso(Integer nhomthuoc_maso) {
		this.nhomthuoc_maso = nhomthuoc_maso;
	}
	
	public String getNhomthuoc_ma() {
		return nhomthuoc_ma;
	}

	public void setNhomthuoc_ma(String nhomthuoc_ma) {
		this.nhomthuoc_ma = nhomthuoc_ma;
	}
}



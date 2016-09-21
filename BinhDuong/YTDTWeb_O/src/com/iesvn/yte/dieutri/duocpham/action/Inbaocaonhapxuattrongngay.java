/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.duocpham.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.entity.DmNguonChuongTrinh;
import com.iesvn.yte.entity.DmNguonKinhPhi;
import com.iesvn.yte.entity.DmPhanLoaiThuoc;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Name("B4131_Inbaocaonhapxuattrongngay")
@Scope(CONVERSATION)
public class Inbaocaonhapxuattrongngay implements Serializable {
	@Logger
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
	private String resetFormB4131=null;
	
	private String thoigian_thang=null;  
	private String thoigian_nam=null;
	private String tungay=null;
	private String denngay=null;
	private String ngayhientai;
	private int index=0;
	private Integer khoa_maso=null;
	private Integer lthuoc_maso=null;
	private Integer nct_maso=null;
	private Integer nkp_maso=null;
	private Integer plthuoc_maso=null;
	private String khoa_ma="";
	private String nct_ma="";
	private String nkp_ma="";
	private String lthuoc_ma=null;
	private String plthuoc_ma=null;
	private boolean chon=false;
	
	String dmKhoXuat = "";
	public String getDmKhoXuat() {
		return dmKhoXuat;
	}

	public void setDmKhoXuat(String dmKhoXuat) {
		this.dmKhoXuat = dmKhoXuat;
	}
	
	@Factory("resetFormB4131")	
	public void resetFormB4131Func() { 
		log.info("init()");
		resetForm();
		ngayhientai = Utils.getCurrentDate();
	}
	
	
	@Begin (join = true)
	public String init(String kho) { // kho = KC, KL , BHYT
		dmKhoXuat = kho;
		return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_InBaoCaoNhapXuatTrongNgay";
	}
	
	@End
	public void end(){
		
	}

	public String thuchienAction(){
		XuatReport();
		resetFormB4131=null;
		return "B4160_Chonmenuxuattaptin";
	}
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		resetFormB4131 = "";
		log.info("bat dau ham reset");
		setChon(false);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		thoigian_thang=String.valueOf(currentDate.getMonth() +1);
		thoigian_nam=String.valueOf(currentDate.getYear()+1900);
		tungay = sdf.format(currentDate);
		denngay = sdf.format(currentDate);
		lthuoc_maso=null;
		nct_maso=null;
		nkp_maso=null;
		plthuoc_maso=null;
		nct_ma="";
		nkp_ma="";
		lthuoc_ma=null;
		plthuoc_ma=null;
		log.info("ket thuc ham reset");
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeKC="Inbaocaonhapxuattrongngay";
		log.info("Vao Method XuatReport bao cao nhap xuat trong ngay");
		String baocao1=null;
		try {
			log.info("bat dau method XuatReport ");
			String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D04_tinhhinhnhapxuat.jrxml";
			log.info("da thay file templete 5" + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			params.put("NGAY", ngayhientai);
			String strInputTonDauDate = IConstantsRes.INPUT_TONDAU;		
			Date dNgayTonCuoi = sdf.parse(strInputTonDauDate);
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(strInputTonDauDate));
			c.add(Calendar.DATE, 1);
			strInputTonDauDate = sdf.format(c.getTime());
			Date dNgayTonDau = sdf.parse(strInputTonDauDate);
			Date dTuNgay = sdf.parse(tungay);
			if(dTuNgay.before(dNgayTonCuoi) || dTuNgay.equals(dNgayTonCuoi))
				params.put("TUNGAYDATE", dNgayTonDau);
			else
				params.put("TUNGAYDATE", dTuNgay);
			params.put("DENNGAYDATE", sdf.parse(denngay));
			String boqua = null;
			if (chon == true) {
				boqua = "1";
				params.put("BOQUA", boqua);
			}else{
				params.put("BOQUA", boqua);
			}
			log.info("BOQUA :" + boqua);
			DieuTriUtilDelegate dtUtils=DieuTriUtilDelegate.getInstance();
			log.info("set gia tri cho loai thuoc :" + lthuoc_ma);
			if(lthuoc_ma.trim().equals("")){
				lthuoc_maso =null;
				params.put("LOAITHUOCMASO", lthuoc_maso);
			}else{
				DmLoaiThuoc dmloaithuoc = new DmLoaiThuoc();
				Object objLThuoc= dtUtils.findByMa(lthuoc_ma ,"DmLoaiThuoc" , "dmloaithuocMa");
				dmloaithuoc = (DmLoaiThuoc)objLThuoc;
				params.put("LOAITHUOC", dmloaithuoc.getDmloaithuocTen());
				params.put("LOAITHUOCMASO", lthuoc_maso);
			}
			log.info("set gia tri cho phan loai thuoc :" + plthuoc_ma);
			if(plthuoc_ma.trim().equals("")){
				plthuoc_maso =null;
				params.put("PLMASO", plthuoc_maso);
			}else{
				DmPhanLoaiThuoc dmplthuoc = new DmPhanLoaiThuoc();
				Object objLThuoc= dtUtils.findByMa(plthuoc_ma ,"DmPhanLoaiThuoc" , "dmphanloaithuocMa");
				dmplthuoc = (DmPhanLoaiThuoc)objLThuoc;
				params.put("PHANLOAI", dmplthuoc.getDmphanloaithuocTen());
				params.put("PLMASO", plthuoc_maso);
			}
			log.info("set gia tri cho kho :" + khoa_ma);
			if(khoa_ma.trim().equals("")){
				khoa_maso =null;
				params.put("KHOMASO", khoa_maso);
			}else{
				DmKhoa dmkhoa = new DmKhoa();
				Object objdmkhoa = dtUtils.findByMa(khoa_ma , "DmKhoa", "dmkhoaMa");
				dmkhoa = (DmKhoa)objdmkhoa;
				params.put("THUOCKHO", dmkhoa.getDmkhoaTen());
				params.put("KHOMASO", khoa_maso);
			}
			log.info("set gia tri cho nguon kinh phi");
			if(nkp_ma.trim().equals("")){				
				nkp_maso =null;
				params.put("NKPMASO", nkp_maso);
			}else{
				DmNguonKinhPhi dmnkp = new DmNguonKinhPhi();
				Object objdmnkp = dtUtils.findByMa(nkp_ma , "DmNguonKinhPhi", "dmnguonkinhphiMa");
				dmnkp = (DmNguonKinhPhi)objdmnkp;
				params.put("NKPMASO", nkp_maso);
			}
			log.info("set gia tri cho nguon chuong trinh");
			if(nct_ma.trim().equals("")){				
				nct_maso = null;
				params.put("NCTMASO", nct_maso);
			}else{
				DmNguonChuongTrinh dmnct = new DmNguonChuongTrinh();
				Object objdmnct = dtUtils.findByMa(nct_ma , "DmNguonChuongTrinh", "dmnctMa");
				dmnct = (DmNguonChuongTrinh)objdmnct;
				params.put("NGUON", dmnct.getDmnctTen());
				params.put("NCTMASO", nct_maso);
			}
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintKC,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"duocpham/", "pdf","Inbaocaonhapxuattrongngay");
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

	public Integer getKhoa_maso() {
		return khoa_maso;
	}

	public void setKhoa_maso(Integer khoa_maso) {
		this.khoa_maso = khoa_maso;
	}

	public Integer getLthuoc_maso() {
		return lthuoc_maso;
	}

	public void setLthuoc_maso(Integer lthuoc_maso) {
		this.lthuoc_maso = lthuoc_maso;
	}

	public Integer getNct_maso() {
		return nct_maso;
	}

	public void setNct_maso(Integer nct_maso) {
		this.nct_maso = nct_maso;
	}

	public Integer getNkp_maso() {
		return nkp_maso;
	}

	public void setNkp_maso(Integer nkp_maso) {
		this.nkp_maso = nkp_maso;
	}

	public Integer getPlthuoc_maso() {
		return plthuoc_maso;
	}

	public void setPlthuoc_maso(Integer plthuoc_maso) {
		this.plthuoc_maso = plthuoc_maso;
	}

	public String getKhoa_ma() {
		return khoa_ma;
	}

	public void setKhoa_ma(String khoa_ma) {
		this.khoa_ma = khoa_ma;
	}

	public String getNct_ma() {
		return nct_ma;
	}

	public void setNct_ma(String nct_ma) {
		this.nct_ma = nct_ma;
	}

	public String getNkp_ma() {
		return nkp_ma;
	}

	public void setNkp_ma(String nkp_ma) {
		this.nkp_ma = nkp_ma;
	}

	public String getLthuoc_ma() {
		return lthuoc_ma;
	}

	public void setLthuoc_ma(String lthuoc_ma) {
		this.lthuoc_ma = lthuoc_ma;
	}

	public String getPlthuoc_ma() {
		return plthuoc_ma;
	}

	public void setPlthuoc_ma(String plthuoc_ma) {
		this.plthuoc_ma = plthuoc_ma;
	}

	public void setChon(boolean chon) {
		this.chon = chon;
	}

	public boolean isChon() {
		return chon;
	}
}

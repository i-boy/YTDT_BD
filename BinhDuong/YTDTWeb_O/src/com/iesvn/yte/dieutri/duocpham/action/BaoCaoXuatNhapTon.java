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

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.log4j.Logger;
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

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.entity.DmNguonChuongTrinh;
import com.iesvn.yte.entity.DmNguonKinhPhi;
import com.iesvn.yte.entity.DmPhanLoaiThuoc;
import com.iesvn.yte.entity.DmPhanNhomThuoc;
import com.iesvn.yte.util.IConstantsRes;

@Scope(CONVERSATION)
@Name("B4412_Inbaocaonhapxuatthang")
@Synchronized(timeout = 6000000)
public class BaoCaoXuatNhapTon implements Serializable {
	
	private static final long serialVersionUID = 10L;
	private String position = com.iesvn.yte.util.IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "duocpham/";	
	private static Logger logger = Logger.getLogger(BaoCaoXuatNhapTon.class);
	
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
	private String resetFormB4412=null;
	
	@DataModel
	private List<DmPhanLoaiThuoc> listDmPLThuoc = new ArrayList<DmPhanLoaiThuoc>();

	@DataModelSelection("listDmPLThuoc")
	@Out(required = false)
	private DmPhanLoaiThuoc dmPLThuocSelect;
	
	private String inTheo=null;
	private int index=0;
	private String ngayhientai = "";
	private String ngaykiem = "";
	private Integer loaihang_maso=null;
	private Integer plthuoc_maso=null;
	private Integer khoa_maso=null;
	private Integer ct_maso=null;
	private Integer kp_maso=null;
	private String plthuoc_ma="";
	private String loaihang_ma="";
	private String khoa_ma="";
	private String ct_ma="";
	private String kp_ma="";
	Map<String, Object> params = null;
	private String tungay = "";
	private String tenThuoc = "";
	private String nhomthuoc_ma ="";
	private Integer nhomthuoc_maso = null;
	
	@Factory("resetFormB4412")
	public void initresetFormB4412() {
		logger.info("init()");
		ngayhientai = com.iesvn.yte.util.Utils.getCurrentDate();
		resetForm();
	}
	
	String dmKhoXuat = "";
	
	public String getDmKhoXuat() {
		return dmKhoXuat;
	}
	public void setDmKhoXuat(String dmKhoXuat) {
		this.dmKhoXuat = dmKhoXuat;
	}
	
	@Begin (join = true)
	public String init(String kho) {
		logger.info("init()");
		resetForm();
		dmKhoXuat = kho;
		return "BaoCaoDuoc_InBaoCaoKhoChinh_InBaoCaoNhapXuatTrongThang";
	}
	
	@End
	public void end(){
		
	}
	
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetList(){
		logger.info("=============reset listttttttttttt " + listDmPLThuoc.size());
		if(listDmPLThuoc.size() > 0){
			DmPhanLoaiThuoc dmplt = new DmPhanLoaiThuoc();
			dmplt = listDmPLThuoc.get(0);
			logger.info(dmplt);
			if(!dmplt.getDmloaithuocMaso().getDmloaithuocMa().equals(loaihang_ma)){
				listDmPLThuoc.clear();
				setPlthuoc_ma("");
			}
		}
	}
	
	/**
	 * Method tra ve String truyen tham so vao file jrxml
	 */
	private String getListDVMaParamsHelp(List<String> inputs){
        logger.info("Vao method getListDVMaParamsHelp ");
        logger.info("Inputs: " + inputs.toString());
        String result = "";
        for(String each : inputs){
                if(each != "")
                        result += "'"+each + "',";
        }
        result = result.substring(0, result.length()-1);
        logger.info("Thoat method getListDVMaParamsHelp ");
        logger.info("Output: " + result);
        return result;
	}
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		setPlthuoc_ma("");
		listDmPLThuoc.clear();
		ngaykiem = com.iesvn.yte.util.Utils.getCurrentDate();
		tungay = com.iesvn.yte.util.Utils.getCurrentDate();
		setLoaihang_ma("");
		setCt_ma("");
		resetFormB4412 = "";
		setKp_ma("");
		setInTheo("r1");
		tenThuoc ="";
		nhomthuoc_ma = "";
	}
	
	/**
	 * ham them mot phan tu vao DS
	 */
	public void enter(){
		logger.info("bat dau them du lieu vao luoi");
		DmPhanLoaiThuoc plThuoc;
		boolean test=false;
		DieuTriUtilDelegate dtutilDelegate=DieuTriUtilDelegate.getInstance();
		if(!plthuoc_ma.equals("")){
			if((listDmPLThuoc.size()==0) && (plthuoc_ma !=null)){
				logger.info("size list bang 0");
				Object obj=dtutilDelegate.findByMa(plthuoc_ma , "DmPhanLoaiThuoc", "dmphanloaithuocMa");
				plThuoc=(DmPhanLoaiThuoc)obj;
				listDmPLThuoc.add(plThuoc);
				logger.info("da add phan tu dau tien" + listDmPLThuoc.size());
			}else if ((listDmPLThuoc.size()>0) && (plthuoc_ma !=null)){
				logger.info("size list lon hon 0");
				for(DmPhanLoaiThuoc item:listDmPLThuoc){
					if(item.getDmphanloaithuocMa().equals(plthuoc_ma)){
						test=true;
					}
				}
				if(test == false){
					Object obj=dtutilDelegate.findByMa(plthuoc_ma , "DmPhanLoaiThuoc", "dmphanloaithuocMa");
					plThuoc=(DmPhanLoaiThuoc)obj;
					listDmPLThuoc.add(plThuoc);
				}
				logger.info("da add phan tu" + listDmPLThuoc.size());
			}
		}
		setPlthuoc_ma("");
	}
	
	
	/**
	 * Ham xoa mot record
	 */
	public void deletedmPLThuoc(){
		logger.info("bat dau xoa , size" + listDmPLThuoc.size());
		listDmPLThuoc.remove(dmPLThuocSelect);
		logger.info("da xoa , size" + listDmPLThuoc.size());
		logger.info("ket thuc xoa");
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeKC="Inbaocaonhapxuathang";
		logger.info("Vao Method XuatReport In bao coa nhap xuat hang");
		String baocao1=null;
		String pathTemplate=null;
		try {
			if(khoa_ma != null && !khoa_ma.equals("")){//Chon kho
				if(khoa_ma.toUpperCase().equals("KCH")){
					if(inTheo.equals("r1")){
						pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D17_BCXuatnhapton_gopgia.jrxml";
					}else if(inTheo.equals("r2")){
						pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D17_BCXuatnhapton_tachgia.jrxml";
					}else{
						pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D17_BCXuatnhapton_goplo.jrxml";
					}
				}else{//Kho le: Noi tru, BHYT, TE...
					if(inTheo.equals("r1")){
						pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D17_BCXuatnhapton_gopgia_KL.jrxml";
					}else if(inTheo.equals("r2")){
						pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D17_BCXuatnhapton_tachgia_KL.jrxml";
					}else{
						pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D17_BCXuatnhapton_goplo_KL.jrxml";
					}
				}
			}else{//Khong chon kho tuc xuat nhap ton cac kho
				if(inTheo.equals("r1")){
					pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D17_BCXuatnhapton_gopgia_ALL.jrxml";
				}else if(inTheo.equals("r2")){
					pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D17_BCXuatnhapton_tachgia_ALL.jrxml";
				}else{
					pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D17_BCXuatnhapton_goplo_ALL.jrxml";
				}
			}
				
			logger.info("da thay file templete 5" + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			logger.info("da thay file template ");
			logger.info("================= ");
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			DieuTriUtilDelegate dtDAO = DieuTriUtilDelegate.getInstance();
			params=new HashMap<String, Object>();
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			
			params.put("DENNGAY", sdf.parse(ngaykiem));
			logger.info("den ngay " + ngaykiem);
			
			String strInputTonDauDate = IConstantsRes.INPUT_TONDAU;		
			Date dNgayTonCuoi = sdf.parse(strInputTonDauDate);
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(strInputTonDauDate));
			c.add(Calendar.DATE, 1);
			strInputTonDauDate = sdf.format(c.getTime());
			Date dNgayTonDau = sdf.parse(strInputTonDauDate);
			Date dTuNgay = sdf.parse(tungay);
			if(dTuNgay.before(dNgayTonCuoi) || dTuNgay.equals(dNgayTonCuoi)){
				params.put("TUNGAY", dNgayTonDau);
			}else{
				params.put("TUNGAY", dTuNgay);
			}
			
			String listPl="(";
			logger.info("loai hang " + loaihang_ma);
			logger.info("phan loai " + listDmPLThuoc.size());
			logger.info("khoa " + khoa_ma);
			logger.info("ten thuoc " + tenThuoc);
			logger.info("chuong trinh " + ct_ma);
			logger.info("kinh phi " + kp_ma);
			if(!loaihang_ma.trim().equals("")){
				params.put("DMLOAITHUOC_MASO", loaihang_maso);
				DmLoaiThuoc lthuoc = new DmLoaiThuoc();
				Object obj = dtDAO.findByMa( loaihang_ma , "DmLoaiThuoc" , "dmloaithuocMa");
				lthuoc = (DmLoaiThuoc)obj;
				params.put("DTDMLOAI_TEN", lthuoc.getDmloaithuocTen());				
			}else{
				loaihang_maso = null;
				params.put("DMLOAITHUOC_MASO", loaihang_maso);
			}
			if(!khoa_ma.trim().equals("")){
				params.put("DMKHOA_MASO", khoa_maso);
			}else{
				khoa_maso = null;
				params.put("DMKHOA_MASO", khoa_maso);
			}
			
			if(!tenThuoc.trim().equals("")){
				params.put("DMTHUOC_TEN", tenThuoc.toUpperCase());
			}else{
				params.put("DMTHUOC_TEN", null);
			}
			
			if(!ct_ma.trim().equals("")){
				params.put("DMNCT_MASO", ct_maso);
				DmNguonChuongTrinh nct = new DmNguonChuongTrinh();
				Object obj = dtDAO.findByMa( ct_ma , "DmNguonChuongTrinh" , "dmnctMa");
				nct = (DmNguonChuongTrinh)obj;
				params.put("DTDMNGUON_TEN", nct.getDmnctTen());
			}else{
				ct_maso = null;
				params.put("DMNCT_MASO", ct_maso);
			}
			if(!kp_ma.trim().equals("")){
				params.put("DMNGUONKINHPHI_MASO", kp_maso);
				DmNguonKinhPhi nkp = new DmNguonKinhPhi();
				Object obj = dtDAO.findByMa( kp_ma , "DmNguonKinhPhi" , "dmnguonkinhphiMa");
				nkp = (DmNguonKinhPhi)obj;
				params.put("DMKINHPHI_TEN", nkp.getDmnguonkinhphiTen());
			}else{
				kp_maso = null;
				params.put("DMNGUONKINHPHI_MASO", kp_maso);
			}
			if(listDmPLThuoc.size()>0){
				setLoaihang_ma(null);
				List<String> listtemp=new ArrayList<String>();
				for(DmPhanLoaiThuoc item:listDmPLThuoc){
					listtemp.add(item.getDmphanloaithuocMa());
				}
				params.put("DMPHANLOAI_MA", getListDVMaParamsHelp(listtemp));
				listPl=listPl+getListDVMaParamsHelp(listtemp)+")";
				logger.info("list phan loai " + listPl);
			}else{
				params.put("DMPHANLOAI_MA", null);
			}
			
			if(!nhomthuoc_ma.equals("")){
				DmPhanNhomThuoc dmpnthuoc =new DmPhanNhomThuoc();
				dmpnthuoc = (DmPhanNhomThuoc)dtDAO.findByMa(nhomthuoc_ma , "DmPhanNhomThuoc", "dmphannhomthuocMa");
				params.put("NHOMTHUOCMASO", dmpnthuoc.getDmphannhomthuocMaso());
			}else{
				params.put("NHOMTHUOCMASO", null);
			}
			
			Class.forName(IConstantsRes.ORACLE_DRIVER);
			Connection conn = null;
			try{
			  	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);
			}catch(Exception e){
			  	logger.info(e.getMessage());
			}
			jasperPrintKC =  JasperFillManager.fillReport(jasperReport,params, conn);
			baocao1=XuatReportUtil.ReportUtil(jasperPrintKC,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"duocpham/", "pdf","Inbaocaonhapxuathang");
			reportPathKC=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			logger.info("duong dan file xuat report :" + baocao1);
			logger.info("duong dan -------------------- :"+reportPathKC);
			index+=1;
			logger.info("Index :" + getIndex());
			if(conn != null) conn.close();
		}catch (Exception e) {
		    logger.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    logger.info("Thoat Method XuatReport");
	}
	
	/**
	 * Ham thuc hien cap nhat bang kiem ke
	 * @return
	 */
	public String thuchienAction(){
		XuatReport();
		resetFormB4412=null;
		return "B4160_Chonmenuxuattaptin";		
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

	public void setInTheo(String inTheo) {
		this.inTheo = inTheo;
	}

	public String getInTheo() {
		return inTheo;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}
	
	public void setNgaykiem(String ngaykiem) {
		this.ngaykiem = ngaykiem;
	}

	public String getNgaykiem() {
		return ngaykiem;
	}
	public String getTungay() {
		return tungay;
	}
	public void setTungay(String tungay) {
		this.tungay = tungay;
	}
	
	public String getTenThuoc() {
		return tenThuoc;
	}

	public void setTenThuoc(String tenThuoc) {
		this.tenThuoc = tenThuoc;
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
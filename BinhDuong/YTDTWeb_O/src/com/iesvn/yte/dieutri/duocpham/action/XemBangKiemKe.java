/**
 * author : HOAI NAM
 */

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
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.KiemKeDelegate;
import com.iesvn.yte.dieutri.delegate.KiemKeKhoDelegate;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.KiemKeKho;
import com.iesvn.yte.dieutri.entity.KiemKePhanLoaiThuoc;
import com.iesvn.yte.dieutri.entity.KiemKe;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.entity.DmPhanLoaiThuoc;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.PagedList;

@Name("B4144_XemBangKiemKe")
@Scope(CONVERSATION)
public class XemBangKiemKe  implements Serializable {
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
	private String resetFormXemBangKiemKe=null;
	
	@In(required = false)
	@Out(required = false)
	private DmKhoa dmKhoaKhoOut;
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
	@In(required = false)
	@Out(required = false)
	private KiemKe kiemkeOut;
	
	@In(required = false)
	@Out(required = false)
	private KiemKeKho kiemkeKhoOut;
	
	@In(required = false)
	@Out(required = false)
	private String trangthaittOut;
	
	@DataModel
	private List<DmPhanLoaiThuoc> listDmPLThuocKK = new ArrayList<DmPhanLoaiThuoc>();
	
//	@DataModel
//	private List<KiemKeKho> listThuocBangKiemKe = new ArrayList<KiemKeKho>();	

	private int index=0;
	private Integer khoa_maso=null;
	private Boolean chon=false;
	private String tenLoaiThuoc="";
	private String khoa_ma="";
	Map<String, Object> params = null;
	private String ngayKiem;
	private String ngaygioCN;
	private String nhanvienKiemKe;
	private String trangthaiKK;
	private String trangthaitt;
	private KiemKe kiemke;
	//Tho add Paging
	private int page;
	private int itemsPerPage; 
	@DataModel
	private List<KiemKeKho> items;
	private int totalPages;
	
	public int getItemsPerPage() { 
		return itemsPerPage;
	}  

	public void setItemsPerPage(int itemsPerPage) {  
		this.itemsPerPage = itemsPerPage;
	}  

	public int getPage() { 
		return page;
	}  
	
	public void setPage(int page) {
		this.page = page; 
		fetch();
	} 
	
	public int getTotalPages() { 
		return totalPages;
	}  
	
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages; 
	} 
	
	public List<KiemKeKho> getItems() {
		return items;  
	}
	
	public void setItems(List<KiemKeKho> items) {
		this.items = items;  
	}
	
	private void fetch() { 
		KiemKeKhoDelegate kkkDel=KiemKeKhoDelegate.getInstance();
		int total = kkkDel.getAllKiemKeKhoTotal(kiemke.getMaphieukiem()).intValue(); 
		int limit = itemsPerPage;  
		int offset = (page - 1) * itemsPerPage; 
		List<KiemKeKho> list = kkkDel.getItemKiemKeKhos(kiemke.getMaphieukiem(),limit, offset);  
		items = new PagedList(list, total, itemsPerPage); 
		//listThuocBangKiemKe = items;
	} 
	
	public void resetItemsPerPage(){
		setPage(1);
		KiemKeKhoDelegate kkkDel=KiemKeKhoDelegate.getInstance();
		int total = kkkDel.getAllKiemKeKhoTotal(kiemke.getMaphieukiem()).intValue(); 
		totalPages = (int)(total/itemsPerPage) + 1;
	} 
	//End Tho add Paging
	
	String dmKhoXuat = "";	
	
	public String getDmKhoXuat() {
		return dmKhoXuat;
	}
	public void setDmKhoXuat(String dmKhoXuat) {
		this.dmKhoXuat = dmKhoXuat;
	}
	@Factory("resetFormXemBangKiemKe")
	public void resetFormXemBangKiemKe() {
		log.info("resetFormXemBangKiemKe()");	
		listDmPLThuocKK.clear();
		items = new ArrayList<KiemKeKho>();
		kiemke = kiemkeOut;
		trangthaitt = trangthaittOut;
		itemsPerPage = 20;
		setPage(1); 		
		KiemKeKhoDelegate kiemkeKhoDel=KiemKeKhoDelegate.getInstance();
		int total = kiemkeKhoDel.getAllKiemKeKhoTotal(kiemke.getMaphieukiem()).intValue(); 
		totalPages = (int)(total/itemsPerPage)+1;
		displayPhieuKiemKe();
	}
	@Begin (join = true)
	public String init() {
		log.info("init()");		
		listDmPLThuocKK.clear();
		items = new ArrayList<KiemKeKho>();
		kiemke = kiemkeOut;
		trangthaitt = trangthaittOut;
		itemsPerPage = 20;
		setPage(1); 		
		KiemKeKhoDelegate kiemkeKhoDel=KiemKeKhoDelegate.getInstance();
		int total = kiemkeKhoDel.getAllKiemKeKhoTotal(kiemke.getMaphieukiem()).intValue();
		totalPages = (int)(total/itemsPerPage)+1;
		displayPhieuKiemKe();
		return "QuanLyKhoLe_KiemKeKhoChinh_XemBangKiemKeDinhKy";
	}
	@End
	public void end(){
		
	}
	
	public String quayve() throws Exception{
		resetFormXemBangKiemKe = null;
		return "QuanLyKhoChinh_KiemKeKhoChinh_TimKiemBangKiemKeDinhKy";
	}
	
	/**
	 * Hien thi phieu kiem ke len giao dien
	 */
	public void displayPhieuKiemKe() {
		log.info(" ***** displayPhieuKiemKe() ***** ");
		if (kiemke != null) {
			SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			ngayKiem = sf.format(kiemke.getNgaykiemke());
			ngaygioCN = sf.format(kiemke.getNgaygiocn());
			DmLoaiThuoc dmLT = kiemke.getDmloaithuocMaso();
			if(dmLT != null){
				if(dmLT.getDmloaithuocTen() != null){
					setTenLoaiThuoc(dmLT.getDmloaithuocTen());
				}else{
					setTenLoaiThuoc("*All*");
				}
			}else{
				setTenLoaiThuoc("*All*");
			}
			
			KiemKeDelegate kiemkeDelegate = KiemKeDelegate.getInstance();
			List<KiemKePhanLoaiThuoc> listKKPLThuoc = kiemkeDelegate.getListKiemKePhanLoaiThuoc(kiemke.getMaphieukiem());
			listDmPLThuocKK.clear();
			if(listKKPLThuoc.size()>0){
				for(KiemKePhanLoaiThuoc item:listKKPLThuoc){
					if(item.getDmphanloaithuocMaso() != null){
						System.out.println("Phan loai thuoc: " + item.getDmphanloaithuocMaso().getDmphanloaithuocMa());
						listDmPLThuocKK.add(item.getDmphanloaithuocMaso());
					}			
				}
			}
			setNhanvienKiemKe(kiemke.getDtdmnhanvienKiemke().getDtdmnhanvienTen());	
			String tt = kiemke.getTrangthai();
			if(tt.equals("OPEN")){
				setTrangthaiKK("\u0110ang ki\u1EC3m k\u00EA");
			}else{
				setTrangthaiKK("\u0110\u00E3 ho\u00E0n t\u1EA5t");
			}			
		}
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
        return result;
	}
	
	public String huyKiemke(){
		KiemKeDelegate kiemkeDel = KiemKeDelegate.getInstance();
		//do object nay luc la null, luc khong la null nen su dung lenh nay de ngan
		if(kiemke.getDmloaithuocMaso() != null){
			if(kiemke.getDmloaithuocMaso().getDmloaithuocMaso() == null){
				kiemke.setDmloaithuocMaso(null);				
			}
		}
		String result  = kiemkeDel.huyKiemKe(kiemke);
		if(result.equals("SUCCESS")){
			resetFormXemBangKiemKe = null;
			return "QuanLyKhoChinh_KiemKeKhoChinh_TimKiemBangKiemKeDinhKy";
		}else{
			FacesMessages.instance().add("B\u1EA1n kh\u00F4ng th\u1EC3 h\u1EE7y \u0111\u01B0\u1EE3c b\u1EA3ng ki\u1EC3m k\u00EA n\u00E0y. " + result, kiemke.getMaphieukiem());
			return "";
		}
	}
	
	public void hoantatKiemke(){
		DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
		DtDmNhanVien nv = nvDelegate.findByNd(identity.getUsername());
		if(nv == null){
			FacesMessages.instance().add(identity.getUsername() + " kh\u00F4ng thu\u1ED9c nh\u00E2n vi\u00EAn c\u1EE7a B\u1EC7nh vi\u1EC7n");
			return;
		}
		//do object nay luc la null, luc khong la null nen su dung lenh nay de ngan
		if(kiemke.getDmloaithuocMaso() != null){
			if(kiemke.getDmloaithuocMaso().getDmloaithuocMaso() == null){
				kiemke.setDmloaithuocMaso(null);				
			}
		}
		kiemke.setDtdmnhanvienCn(nv);
		kiemke.setNgaygiocn(new Date());
		kiemke.setTrangthai("CLOSE");
		KiemKeDelegate kiemkeDel = KiemKeDelegate.getInstance();
		String result  = kiemkeDel.hoantatKiemKe(kiemke);
		if(result.equals("SUCCESS")){
			setTrangthaiKK("\u0110\u00E3 ho\u00E0n t\u1EA5t");
			FacesMessages.instance().add("\u0110\u00E3 ho\u00E0n th\u00E0nh ki\u1EC3m k\u00EA.", kiemke.getMaphieukiem());
		}else{
			FacesMessages.instance().add("B\u1EA1n kh\u00F4ng th\u1EC3 ho\u00E0n t\u1EA5t \u0111\u01B0\u1EE3c b\u1EA3ng ki\u1EC3m k\u00EA n\u00E0y. " + result, kiemke.getMaphieukiem());
		}
	}
	
	public String capnhatsolieuGUI(){
		kiemkeOut = kiemke;
		kiemkeKhoOut = null;
		resetFormXemBangKiemKe = null;
		return "QuanLyKhoChinh_KiemKeKhoChinh_CapNhatSoLieuKiemKeThucTeGUI";
	}
	
	public String capnhatsolieuFile(){
		kiemkeOut = kiemke;
		kiemkeKhoOut = null;
		resetFormXemBangKiemKe = null;
		return "QuanLyKhoChinh_KiemKeKhoChinh_CapNhatSoLieuKiemKeThucTeFile";
	}
	
	public String selectKiemKeKho(int index){
		kiemkeKhoOut = items.get(index);
		items = null;
		resetFormXemBangKiemKe = null;
		return "QuanLyKhoChinh_KiemKeKhoChinh_CapNhatSoLieuKiemKeThucTeGUI";
	}
	
	/**
	 * xuat report 
	 */
	public String xuatSolieu() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");	
		DmLoaiThuoc dmLT = new DmLoaiThuoc();
		dmLT = kiemke.getDmloaithuocMaso();
		params=new HashMap<String, Object>();
		params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
		params.put("NGAYKIEM", kiemke.getNgaykiemke());
		params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
		params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
		params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
		try{
			Date dateTemp=kiemke.getNgaykiemke();
			Calendar calTemp=Calendar.getInstance();
			calTemp.setTime(dateTemp);
			calTemp.add(Calendar.DATE, -1);
			dateTemp = calTemp.getTime();
			params.put("TINHDENNGAY" , dateTemp);
		}catch(Exception ex){}
		String temp=null;
		String listPl="(";
		khoa_ma = dmKhoaKhoOut.getDmkhoaMa();
		log.info("khoa_ma " + khoa_ma);
		String loaihang_ma ="";
		if(dmLT != null){
			loaihang_ma = dmLT.getDmloaithuocMa();
			params.put("LOAITHUOC", dmLT.getDmloaithuocTen());
			params.put("LTHUOCMASO", dmLT.getDmloaithuocMaso());
		}
		log.info("loai hang " + loaihang_ma);
		log.info("phan loai " + listDmPLThuocKK.size());
		log.info("khoa " + khoa_ma);
		DieuTriUtilDelegate dtutilDelegate=DieuTriUtilDelegate.getInstance();
		params.put("Kho", dmKhoaKhoOut.getDmkhoaTen());
		params.put("KHOAMASO", dmKhoaKhoOut.getDmkhoaMaso());
		if(listDmPLThuocKK.size()>0){
			temp="";
			List<String> listtemp=new ArrayList<String>();
			for(DmPhanLoaiThuoc item:listDmPLThuocKK){
				listtemp.add(item.getDmphanloaithuocMa());
			}
			params.put("PLTHUOCMASO", getListDVMaParamsHelp(listtemp));
			listPl=listPl+getListDVMaParamsHelp(listtemp)+")";
			log.info("list phan loai " + listPl);
		}else{
			temp=null;
			listPl="('')";
			params.put("PLTHUOCMASO", null);
		}
		
		params.put("MaPhieuKiem", kiemke.getMaphieukiem());
		params.put("NGAYGIOKIEMKE", kiemke.getNgaykiemke());
		reportTypeKC="Inbangkiemke";
		log.info("Vao Method XuatReport cap nhat bang kiem ke");
		String baocao1=null;
		String pathTemplate=null;
		try {
			log.info("bat dau method XuatReport ");
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D13_bangkiemkevattucongcusanphamhanghoa_tenhang.jrxml";
			
			log.info("da thay file templete 5" + pathTemplate);
			
			JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			log.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			    log.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintKC =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintKC,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"duocpham/", "pdf","Capnhatbangkiemke");
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
	    return "B4160_Chonmenuxuattaptin";
	}		
	
	public Integer getKhoa_maso() {
		return khoa_maso;
	}


	public void setKhoa_maso(Integer khoa_maso) {
		this.khoa_maso = khoa_maso;
	}


	public String getTenLoaiThuoc() {
		return tenLoaiThuoc;
	}


	public void setTenLoaiThuoc(String tenLoaiThuoc) {
		this.tenLoaiThuoc = tenLoaiThuoc;
	}

	public String getNhanvienKiemKe() {
		return nhanvienKiemKe;
	}


	public void setNhanvienKiemKe(String nhanvienKiemKe) {
		this.nhanvienKiemKe = nhanvienKiemKe;
	}
	
	public String getNgayKiem() {
		return ngayKiem;
	}


	public void setNgayKiem(String ngayKiem) {
		this.ngayKiem = ngayKiem;
	}
	
	public String getNgaygioCN() {
		return ngaygioCN;
	}


	public void setNgaygioCN(String ngaygioCN) {
		this.ngaygioCN = ngaygioCN;
	}
	
	public String getTrangthaiKK() {
		return trangthaiKK;
	}


	public void setTrangthaiKK(String trangthaiKK) {
		this.trangthaiKK = trangthaiKK;
	}
	
	public String getTrangthaitt() {
		return trangthaitt;
	}


	public void setTrangthaitt(String trangthaitt) {
		this.trangthaitt = trangthaitt;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}
	
	public KiemKe getKiemke() {
		return kiemke;
	}
	public void setKiemke(KiemKe kiemke) {
		this.kiemke = kiemke;
	}
	
//	public List<KiemKeKho> getListThuocBangKiemKe() {
//		return listThuocBangKiemKe;
//	}
//
//	public void setListThuocBangKiemKe(List<KiemKeKho> listThuocBangKiemKe) {
//		this.listThuocBangKiemKe = listThuocBangKiemKe;
//	}
}




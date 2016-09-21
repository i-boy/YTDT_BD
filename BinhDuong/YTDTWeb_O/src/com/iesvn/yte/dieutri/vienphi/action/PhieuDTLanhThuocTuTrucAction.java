package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import net.sf.jasperreports.engine.JasperPrint;

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
import org.jboss.seam.security.Identity;

import com.iesvn.yte.dieutri.delegate.CtPhieuDtDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DmKhoaDelegate;
import com.iesvn.yte.dieutri.delegate.DmLoaiPhieuDelegate;
import com.iesvn.yte.dieutri.delegate.DmLoaiThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DmLoaiPhieuDelegate;
import com.iesvn.yte.dieutri.delegate.DmThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.PhieuDuTruDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocNoiTruDelegate;
import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
import com.iesvn.yte.dieutri.delegate.YteLogDelegate;
import com.iesvn.yte.dieutri.entity.CtPhieuDt;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.PhieuDuTru;
import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.dieutri.entity.YteLog;
import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
import com.iesvn.yte.dieutri.vienphi.action.PhieuDTLanhThuocTuTrucNGTAction.CtPhieuDuTruExt;
import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.entity.DmDonViTinh;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmLoaiPhieu;
import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.entity.DmTang;
import com.iesvn.yte.entity.DmThuoc;
import com.iesvn.yte.report.XuatReportDuocPham;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;
import com.sun.org.apache.commons.beanutils.BeanUtils;

@Scope(CONVERSATION)
@Name("B3142_Phieulanhthuoctutruc")
@Synchronized(timeout = 6000000)
public class PhieuDTLanhThuocTuTrucAction implements Serializable{
	
	private YteLogDelegate yteLogDele = YteLogDelegate.getInstance();
    private YteLog yteLog;
    private String listDataLog;
  
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
	private static final long serialVersionUID = 10L;

	private static Logger log = Logger.getLogger(PhieuDTLanhThuocTuTrucAction.class);
	@DataModel
	private ArrayList<CtPhieuDuTruExt> listCtDTLanhThuocEx=new ArrayList<CtPhieuDuTruExt>();
	private ArrayList<CtPhieuDt> listCtPhieuDtDel;
	@DataModelSelection
	private CtPhieuDuTruExt selected;
	private List<Integer> listMas = new ArrayList<Integer>();

	private PhieuDuTru phieuDuTru=new PhieuDuTru();
	private String maPhieu="";
	private String ngayXuat="";
	private String ngayXuatDen="";
	private String dmtMa="";
	private Double tonkho;
	private Double xuat;
	private String tonkhoMa="";
	private Integer updateItem = -1;
	private Integer count;
	private String ngayHienTai="";
	private String nguonMa="";
	private String kpMa="";
	private String malk="";
	private Double tongTien;	
	private String hienThiGhiNhan="true";
	private String hienThiHuyPhieu = "";
	private String hienThiInPhieu="";
	private String gioLapPhieuTu = "";
	private String gioLapPhieuDen = "";
	private String dmDoiTuongTen = "";
	
	String dmKhoXuat = "";
	String dmKhoaNhan = "";
	//Tho add
	private String dmLoaiTen="";
	private DmLoaiThuocDelegate dmLoaiThuocDelegate;
	private List<SelectItem> listDmLoaiThuocs = new ArrayList<SelectItem>();
	HashMap<String, DmLoaiThuoc> hmLoaiThuoc = new HashMap<String, DmLoaiThuoc>();
	HashMap<String,DmThuoc> hmDmThuoc = new HashMap<String,DmThuoc>();
	private DmLoaiPhieuDelegate dmLoaiPhieuDelegate;
	private List<SelectItem> listDmLoaiPhieus = new ArrayList<SelectItem>();
	HashMap<String,DmLoaiPhieu> hmDmLoaiPhieu = new HashMap<String,DmLoaiPhieu>();
	private String dmLoaiPhieuMa ="";
	private List<SelectItem> listDmDoiTuongs = new ArrayList<SelectItem>();
	HashMap<String, DmDoiTuong> hmDoiTuong = new HashMap<String, DmDoiTuong>();
	private List<SelectItem> listDmTangs = new ArrayList<SelectItem>();
	
	private String resultReportFileName;
	private String resultReportName;	
	private String loaiFileXuat;
	@Out(required = false)
	private String isReport = "false";
	private String isUpdate;
	private String isDeleted;
	
	private final int CHUA_LUU_PHIEU_XUAT=0;
	private static String FORMAT_DATE = "dd/MM/yyyy HH:mm:ss";

	@Begin(join = true)
	public String init(String loaiDT, String khoNhan, String khoXuat) {
		log.info("***** init PhieuDTLanhThuocTuTrucAction() *****");
		reset();
		dmKhoXuat = khoXuat;
		dmKhoaNhan = khoNhan;
		listDmKhoXuats.clear();
		hmDmKhoXuat.clear();
		refreshDmKhoXuat();
		
		listDmThuocs.clear();
		listDmLoaiThuocs.clear();
		hmLoaiThuoc.clear();
		hmDmThuoc.clear();
		
		refreshDmLoaiThuoc();
		refreshDmDoiTuong();
		refreshDmKhoaNhan();
		return "VienPhiTaiKhoa_PhieuDTLanhThuocTuTru";
	}
	
	@End
	public void endConversation(){
		
	}

	@Factory("listCtDTLanhThuocEx")
	public void createTable() {
		log.info("-----createTable()-----");
		listCtDTLanhThuocEx = new ArrayList<CtPhieuDuTruExt>();
	}
	
	/** ==================== BEGIN LY THEM CODE ==================== */	
	private DmThuocDelegate dmThuocDelegate;
	private String dmtTen="";
	private List<SelectItem> listDmThuocs = new ArrayList<SelectItem>();
	
	public String getDmtTen() {
		return dmtTen;
	}

	public void setDmtTen(String dmtTen) {
		this.dmtTen = dmtTen;
	}

	public List<SelectItem> getListDmThuocs() {
		return listDmThuocs;
	}

	public void setListDmThuocs(List<SelectItem> listDmThuocs) {
		this.listDmThuocs = listDmThuocs;
	}
	
	public void onblurMaThuocAction(){
		log.info("-----BEGIN onblurMaThuocAction()-----");
		DmThuoc dmThuoc = hmDmThuoc.get(dmtMa.toUpperCase());
		if(dmThuoc != null) {
			setDmtTen(dmThuoc.getDmthuocTen());
			log.info("-----DA THAY DMTHUOC-----");
		}
		else {
			setDmtTen("");
		}
		log.info("-----END onblurMaThuocAction()-----");
	}
	
	public void onblurTenThuocAction(){
		log.info("-----BEGIN onblurTenThuocAction()-----");
		Boolean hasTenThuoc = false;
		String maThuoc = null;
//		DmThuocDelegate dmThuocDel = DmThuocDelegate.getInstance();
//		DmThuoc dmThuoc = dmThuocDel.findByTenThuoc(dmtTen);
//		if(dmThuoc != null){
//			setDmtMa(dmThuoc.getDmthuocMa());
//			log.info("-----DA THAY DMTHUOC-----");
//		}else{
//			setDmtMa("");
//		}
		if( hmDmThuoc != null ){
			java.util.Set set = hmDmThuoc.entrySet();
		    Iterator i = set.iterator();
		    while(i.hasNext()){
		    	Map.Entry me = (Map.Entry)i.next();
		    	DmThuoc dmThuoc = (DmThuoc)me.getValue();
		    	if(dmThuoc.getDmthuocTen() == dmtTen || dmThuoc.getDmthuocTen().equals(dmtTen)){
		    		hasTenThuoc = true;
		    		maThuoc = dmThuoc.getDmthuocMa();
		    		break;
		    	}	    		
		    }
	    }
	    if(hasTenThuoc){
	    	setDmtMa(maThuoc);
			log.info("-----DA THAY DMTHUOC-----");
	    }else{
	    	setDmtMa("");
	    }
		log.info("-----END onblurTenThuocAction()-----");
	}
	
	public void refreshDmThuoc(){
		listDmThuocs.clear();
		hmDmThuoc.clear();
		dmThuocDelegate = DmThuocDelegate.getInstance();
		
		String loaiMa = "";
		String phanloaiMa = "";
		String loaiPhieu = phieuDuTru.getPhieudtLoaiPhieu();
		if(loaiPhieu != null || !loaiPhieu.equals(""))
		{
			if (phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocMa() != null ){
				if( phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocMa().toUpperCase().equals("TD"))
				{					
					if(!dmLoaiPhieuMa.equals("HT") && !dmLoaiPhieuMa.equals("GN")){
						loaiMa = "TD-TT";
						DmLoaiPhieu lp = hmDmLoaiPhieu.get(dmLoaiPhieuMa);
						if(lp != null && lp.getDmloaiphieuDvt() != null){
							loaiMa+=";"+lp.getDmloaiphieuDvt();
						}
					}else{
						loaiMa = "TD-"+ dmLoaiPhieuMa;
					}
				}else if( phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocMa().toUpperCase().equals("DY")){
					DmLoaiPhieu lp = hmDmLoaiPhieu.get(dmLoaiPhieuMa);
					if(lp.getDmloaiphieuDvt() != null){
						phanloaiMa = lp.getDmloaiphieuDvt();
						loaiMa = phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocMa();
					}
				}else{
					//query theo dm loai thuoc trong ThuocNoiTruDelegate
					loaiMa = phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocMa();
				}	
			}	
		}
		System.out.println("loaiMa: "+loaiMa);
		System.out.println("phanloaiMa: "+phanloaiMa);
		System.out.println("Kho: "+phieuDuTru.getPhieudtMakho().getDmkhoaMaso());
		List<DmThuoc> lstDmThuoc = new ArrayList<DmThuoc>();
		if(!loaiMa.equals("") && !phieuDuTru.getPhieudtLoaiPhieu().equals("")){
			lstDmThuoc = dmThuocDelegate.findByLoaiPhanLoaiThuocNhomThuocDVTKho(loaiMa, phanloaiMa, phieuDuTru.getPhieudtMakho().getDmkhoaMaso());
		}		
		
		if(lstDmThuoc !=null && lstDmThuoc.size()>0)
		{
			System.out.println("lstDmThuoc.size: "+ lstDmThuoc.size());
			for(DmThuoc o: lstDmThuoc){
				listDmThuocs.add(new SelectItem(o.getDmthuocTen()));
				hmDmThuoc.put(o.getDmthuocMa(), o);
			}
		}
	}

/** ==================== END LY THEM CODE ==================== */	
	//Khoa nhan
	private DmKhoaDelegate dmKhoaDelegate;
	private List<SelectItem> listDmKhoaNhans = new ArrayList<SelectItem>();	
	HashMap<String, DmKhoa> hmDmKhoaNhan = new HashMap<String, DmKhoa>();
	private String dmkhoaNhanTen;
	
	public String getDmkhoaNhanTen(){
		return this.dmkhoaNhanTen;
	}
	
	public void setDmkhoaNhanTen(String dmkhoaNhanTen){
		this.dmkhoaNhanTen = dmkhoaNhanTen;
	}
	
	public List<SelectItem> getListDmKhoaNhans() {
		return listDmKhoaNhans;
	}

	public void setListDmKhoaNhans(List<SelectItem> listDmKhoaNhans) {
		this.listDmKhoaNhans = listDmKhoaNhans;
	}
	
	public void onblurMaKhoaNhanAction(){
		log.info("-----BEGIN onblurMaKhoaNhanAction()-----");
		if(phieuDuTru != null && (phieuDuTru.getDmkhoaMaso(true).getDmkhoaMa() != ""  || phieuDuTru.getDmkhoaMaso(true).getDmkhoaMa() != null)){
			DmKhoa dmKhoa = hmDmKhoaNhan.get(phieuDuTru.getDmkhoaMaso(true).getDmkhoaMa().toUpperCase());
			if(dmKhoa != null) {
				phieuDuTru.setDmkhoaMaso(dmKhoa);
		    	setDmkhoaNhanTen(dmKhoa.getDmkhoaTen());
				log.info("-----DA THAY DMKHOANHAN-----");
			}
			else {
				phieuDuTru.setDmkhoaMaso(new DmKhoa());
		    	setDmkhoaNhanTen("");
			}
		}
		refreshDmTang();
	    phieuDuTru.setDmtangMaso(new DmTang());
		log.info("-----END onblurMaKhoaNhanAction()-----");
	}
	
	public void onblurTenKhoaNhanAction(){
		log.info("-----BEGIN onblurTenKhoaNhanAction()-----");
		Boolean hasTenKhoaNhan = false;
		DmKhoa dmKhoa_Finded = new DmKhoa();
		if(dmkhoaNhanTen != ""){
			if( hmDmKhoaNhan != null ){
				java.util.Set set = hmDmKhoaNhan.entrySet();
			    Iterator i = set.iterator();
			    while(i.hasNext()){
			    	Map.Entry me = (Map.Entry)i.next();
			    	DmKhoa dmKhoa = (DmKhoa)me.getValue();
			    	if(dmKhoa.getDmkhoaTen() == dmkhoaNhanTen || dmKhoa.getDmkhoaTen().equals(dmkhoaNhanTen)){
			    		hasTenKhoaNhan = true;
			    		dmKhoa_Finded = dmKhoa;
			    		break;
			    	}	    		
			    }
		    }
		}
		
	    if(hasTenKhoaNhan){
	    	phieuDuTru.setDmkhoaMaso(dmKhoa_Finded);
	    	setDmkhoaNhanTen(dmKhoa_Finded.getDmkhoaTen());
			log.info("-----DA THAY DMKHOANHAN-----");
	    }else{
	    	phieuDuTru.setDmkhoaMaso(new DmKhoa());
	    	setDmkhoaNhanTen("");
	    }
	    refreshDmTang();
	    phieuDuTru.setDmtangMaso(new DmTang());
		log.info("-----END onblurTenKhoaNhanAction()-----");
	}
	
	public void refreshDmKhoaNhan(){
		listDmKhoaNhans.clear();
		hmDmKhoaNhan.clear();
		dmKhoaDelegate = DmKhoaDelegate.getInstance();		
		
		List<DmKhoa> lstDmKhoa = dmKhoaDelegate.getKhoaNhom12NOTINKhoDuoc();		
		if(lstDmKhoa !=null && lstDmKhoa.size()>0)
		{
			for(DmKhoa o: lstDmKhoa){
				listDmKhoaNhans.add(new SelectItem(o.getDmkhoaTen()));
				hmDmKhoaNhan.put(o.getDmkhoaMa(), o);
			}
		}
	}	
	
	//Kho xuat
	private List<SelectItem> listDmKhoXuats = new ArrayList<SelectItem>();	
	HashMap<String, DmKhoa> hmDmKhoXuat = new HashMap<String, DmKhoa>();
	private Integer dmkhoaXuatMaso;
	private String dmkhoaXuatMa;
	private String dmkhoaXuatTen;
	
	public Integer getDmkhoaXuatMaso(){
		return this.dmkhoaXuatMaso;
	}
	
	public void setDmkhoaXuatMaso(Integer dmkhoaXuatMaso){
		this.dmkhoaXuatMaso = dmkhoaXuatMaso;
	}
	
	public String getDmkhoaXuatMa(){
		return this.dmkhoaXuatMa;
	}
	
	public void setDmkhoaXuatMa(String dmkhoaXuatMa){
		this.dmkhoaXuatMa = dmkhoaXuatMa;
	}
	
	public String getDmkhoaXuatTen(){
		return this.dmkhoaXuatTen;
	}
	
	public void setDmkhoaXuatTen(String dmkhoaXuatTen){
		this.dmkhoaXuatTen = dmkhoaXuatTen;
	}
	
	public List<SelectItem> getListDmKhoXuats() {
		return listDmKhoXuats;
	}

	public void setListDmKhoXuats(List<SelectItem> listDmKhoXuats) {
		this.listDmKhoXuats = listDmKhoXuats;
	}
	
	public void onblurMaKhoXuatAction(){
		log.info("-----BEGIN onblurMaKhoXuatAction()-----");
		if(dmkhoaXuatMa != ""){
			DmKhoa dmKhoa = hmDmKhoXuat.get(dmkhoaXuatMa.toUpperCase());
			if(dmKhoa != null) {
				phieuDuTru.setPhieudtMakho(dmKhoa);
		    	setDmkhoaXuatMaso(dmKhoa.getDmkhoaMaso());
		    	setDmkhoaXuatTen(dmKhoa.getDmkhoaTen());
				log.info("-----DA THAY DMKHOXUAT-----");
			}
			else {
				phieuDuTru.setPhieudtMakho(null);
				setDmkhoaXuatMaso(null);
		    	setDmkhoaXuatTen("");
		    	setDmkhoaXuatMa("");
			}
		}
		listDmThuocs.clear();
		hmDmThuoc.clear();
		refreshDmThuoc();
		setDmtMa("");
		setDmtTen("");
		log.info("-----END onblurMaKhoXuatAction()-----");
	}
	
	public void onblurTenKhoXuatAction(){
		log.info("-----BEGIN onblurTenKhoXuatAction()-----");
		Boolean hasTenKhoXuat = false;
		DmKhoa dmKhoa_Finded = new DmKhoa();
		if(dmkhoaXuatTen != ""){
			if( hmDmKhoXuat != null ){
				java.util.Set set = hmDmKhoXuat.entrySet();
			    Iterator i = set.iterator();
			    while(i.hasNext()){
			    	Map.Entry me = (Map.Entry)i.next();
			    	DmKhoa dmKhoa = (DmKhoa)me.getValue();
			    	if(dmKhoa.getDmkhoaTen() == dmkhoaXuatTen || dmKhoa.getDmkhoaTen().equals(dmkhoaXuatTen)){
			    		hasTenKhoXuat = true;
			    		dmKhoa_Finded = dmKhoa;
			    		break;
			    	}	    		
			    }
		    }
		}
		
	    if(hasTenKhoXuat){
	    	phieuDuTru.setPhieudtMakho(dmKhoa_Finded);
	    	setDmkhoaXuatMa(dmKhoa_Finded.getDmkhoaMa());
	    	setDmkhoaXuatMaso(dmKhoa_Finded.getDmkhoaMaso());
			log.info("-----DA THAY DMKHOXUAT-----");
	    }else{
	    	phieuDuTru.setPhieudtMakho(null);
	    	setDmkhoaXuatMa("");
	    	setDmkhoaXuatMaso(null);
	    }	    
	    listDmThuocs.clear();
		hmDmThuoc.clear();
		refreshDmThuoc();
		setDmtMa("");
		setDmtTen("");
		log.info("-----END onblurTenKhoXuatAction()-----");
	}
	
	public void refreshDmKhoXuat(){
		listDmKhoXuats.clear();
		hmDmKhoXuat.clear();
		dmKhoaDelegate = DmKhoaDelegate.getInstance();		
		
		List<DmKhoa> lstDmKhoa = dmKhoaDelegate.getKhoChinh_KhoLe();		
		if(lstDmKhoa !=null && lstDmKhoa.size()>0)
		{
			for(DmKhoa o: lstDmKhoa){
				listDmKhoXuats.add(new SelectItem(o.getDmkhoaTen()));
				hmDmKhoXuat.put(o.getDmkhoaMa(), o);
			}
		}
	}
	
	//Begin Tho add
	public void onblurTenTangAction(){
		if(phieuDuTru != null && phieuDuTru.getDmtangMaso(true).getDmtangTen() != null){
			DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
			List<DmTang> lstTangs = dtUtil.findTenLike("DmTang", "dmtangTen", phieuDuTru.getDmtangMaso(true).getDmtangTen());
			if(lstTangs != null && lstTangs.size()>0){
				phieuDuTru.setDmtangMaso(lstTangs.get(0));
			}else{
				phieuDuTru.setDmtangMaso(new DmTang());
			}
		}
	}
	
	public void refreshDmTang(){
		listDmTangs.clear();
		if(phieuDuTru != null && phieuDuTru.getDmkhoaMaso(true).getDmkhoaMaso() != null){
			String khoaMa = phieuDuTru.getDmkhoaMaso(true).getDmkhoaMa();			
			DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
			listDmTangs.add(new SelectItem("Tat ca"));
			//Get tat ca cac tang cua khoa chuyen den, show gia tri default truoc
			List<DmTang> lstDmTangs = dtUtil.findMaLike("DmTang", "dmkhoaMaso.dmkhoaMa", "dmtangChon", khoaMa, true);
			if(lstDmTangs != null && lstDmTangs.size()>0){				
				for(DmTang dmTang:lstDmTangs){					
					listDmTangs.add(new SelectItem(dmTang.getDmtangTen()));
				}
			}
		}
	}
	public String getDmLoaiTen() {
		return dmLoaiTen;
	}

	public void setDmLoaiTen(String dmLoaiTen) {
		this.dmLoaiTen = dmLoaiTen;
	}

	public List<SelectItem> getListDmLoaiThuocs() {
		return listDmLoaiThuocs;
	}

	public void setListDmLoaiThuocs(List<SelectItem> listDmLoaiThuocs) {
		this.listDmLoaiThuocs = listDmLoaiThuocs;
	}
	
	public void onblurMaLoaiAction(){
		log.info("-----BEGIN onblurMaLoaiAction()-----"+phieuDuTru.getDmloaithuocMaso().getDmloaithuocMa());
		String loaihang_ma = phieuDuTru.getDmloaithuocMaso().getDmloaithuocMa();
		DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)hmLoaiThuoc.get(loaihang_ma.toUpperCase());
		if(dmLoaiThuoc != null) {
			setDmLoaiTen(dmLoaiThuoc.getDmloaithuocTen());
			phieuDuTru.setDmloaithuocMaso(dmLoaiThuoc);
		}
		else {
			setDmLoaiTen("");			
		}
		listDmLoaiPhieus.clear();
		refreshDmLoaiPhieu();
		setDmtMa("");
	    setDmtTen("");
	    phieuDuTru.setPhieudtLoaiPhieu("");
	    setDmLoaiPhieuMa("");
	    listDmThuocs.clear();
	    hmDmThuoc.clear();
		log.info("-----END onblurMaLoaiAction()-----");
	}
	
	public void onblurTenLoaiThuocAction(){
		log.info("-----BEGIN onblurTenLoaiThuocAction()-----");
		Boolean hasTenLoai = false;
		String maLoai = null;
		
		DmLoaiThuoc dmLoaiThuoc_Find = new DmLoaiThuoc();
		java.util.Set set = hmLoaiThuoc.entrySet();
		Iterator i = set.iterator();
		while(i.hasNext()){
		    Map.Entry me = (Map.Entry)i.next();
		    DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)me.getValue();
		    if(dmLoaiThuoc.getDmloaithuocTen() == dmLoaiTen || dmLoaiThuoc.getDmloaithuocTen().equals(dmLoaiTen)){
		    	hasTenLoai = true;
		    	dmLoaiThuoc_Find = dmLoaiThuoc;
		    	break;
		    }	    		
		}	
		if(hasTenLoai){
			phieuDuTru.setDmloaithuocMaso(dmLoaiThuoc_Find);
		}
	    listDmLoaiPhieus.clear();
		refreshDmLoaiPhieu();
	    setDmtMa("");
	    setDmtTen("");
	    phieuDuTru.setPhieudtLoaiPhieu("");
	    setDmLoaiPhieuMa("");
	    listDmThuocs.clear();
	    hmDmThuoc.clear();
		log.info("-----END onblurTenLoaiThuocAction()-----");
	}
	
	public void refreshDmLoaiThuoc(){
		listDmLoaiThuocs.clear();
		dmLoaiThuocDelegate = DmLoaiThuocDelegate.getInstance();
		hmLoaiThuoc.clear();
		hmLoaiThuoc = dmLoaiThuocDelegate.findAllDm();
		if( hmLoaiThuoc != null ){
			java.util.Set set = hmLoaiThuoc.entrySet();
		    Iterator i = set.iterator();
		    while(i.hasNext()){
		    	Map.Entry me = (Map.Entry)i.next();
		    	DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)me.getValue();
		    	listDmLoaiThuocs.add(new SelectItem(dmLoaiThuoc.getDmloaithuocTen()));
		    }
		}
	}
	
	public List<SelectItem> getListDmLoaiPhieus() {
		return listDmLoaiPhieus;
	}

	public void setListDmLoaiPhieus(List<SelectItem> listDmLoaiPhieus) {
		this.listDmLoaiPhieus = listDmLoaiPhieus;
	}
	
	public void refreshDmLoaiPhieu(){
		listDmLoaiPhieus.clear();
		hmDmLoaiPhieu.clear();
		dmLoaiPhieuDelegate = DmLoaiPhieuDelegate.getInstance();
		if(phieuDuTru != null && phieuDuTru.getDmloaithuocMaso() != null){
			List<DmLoaiPhieu> lstDmLoaiPhieus = dmLoaiPhieuDelegate.findAll();			
			if(lstDmLoaiPhieus !=null && lstDmLoaiPhieus.size()>0)
			{
				for(DmLoaiPhieu o: lstDmLoaiPhieus){
					hmDmLoaiPhieu.put(o.getDmloaiphieuMa(), o);
					if(phieuDuTru.getDmloaithuocMaso().getDmloaithuocMaso().equals(o.getDmloaithuocMaso().getDmloaithuocMaso())){
						listDmLoaiPhieus.add(new SelectItem(o.getDmloaiphieuMa() + " - " +o.getDmloaiphieuTen()));
					}
				}
			}
		}
	}
	
	public void onblurLoaiPhieuAction(){
		log.info("-----BEGIN onblurLoaiPhieuAction()-----");	
		if(maPhieu == null || maPhieu.equals("")){
			listCtDTLanhThuocEx.clear();
		}
		String loaiPhieuItem = phieuDuTru.getPhieudtLoaiPhieu();
		if(!loaiPhieuItem.equals("")){
			dmLoaiPhieuMa = loaiPhieuItem.substring(0, loaiPhieuItem.indexOf(" - ")).trim();
		}		
		if(phieuDuTru != null && phieuDuTru.getPhieudtMakho().getDmkhoaMaso() != null){			
			refreshDmThuoc();
		}		
		log.info("-----END onblurLoaiPhieuAction()-----");
	}
	
	//Dm doi tuong
	public String getDmDoiTuongTen() {
		return dmDoiTuongTen;
	}
	
	public void setDmDoiTuongTen(String dmDoiTuongTen) {
		this.dmDoiTuongTen = dmDoiTuongTen;
	}
	
	public List<SelectItem> getListDmDoiTuongs() {
		return listDmDoiTuongs;
	}

	public void setListDmDoiTuongs(List<SelectItem> listDmDoiTuongs) {
		this.listDmDoiTuongs = listDmDoiTuongs;
	}
	public void onblurMaDoiTuongAction(){
		log.info("-----BEGIN onblurMaDoiTuongAction()-----");
		String doituong_ma = phieuDuTru.getDmdoituongMaso(true).getDmdoituongMa();
		DmDoiTuong dmDoiTuong = (DmDoiTuong)hmDoiTuong.get(doituong_ma.toUpperCase());
		if(dmDoiTuong != null) {
			setDmDoiTuongTen(dmDoiTuong.getDmdoituongTen());
			phieuDuTru.setDmdoituongMaso(dmDoiTuong);
		}
		else {
			setDmDoiTuongTen("");	
			phieuDuTru.setDmdoituongMaso(new DmDoiTuong());
		}
		log.info("-----END onblurMaDoiTuongAction()-----");
	}
	
	public void onblurTenDoiTuongAction(){
		log.info("-----BEGIN onblurTenDoiTuongAction()-----");
		Boolean hasTenDT = false;
		String maDT= null;
		if(dmDoiTuongTen.equals("T\u00E2\u0301t ca\u0309")){
			phieuDuTru.setDmdoituongMaso(new DmDoiTuong());
		}else{
			DmDoiTuong dmDoiTuong_Find = new DmDoiTuong();
			java.util.Set set = hmDoiTuong.entrySet();
			Iterator i = set.iterator();
			while(i.hasNext()){
			    Map.Entry me = (Map.Entry)i.next();
			    DmDoiTuong dmDoiTuong = (DmDoiTuong)me.getValue();
			    if(dmDoiTuong.getDmdoituongTen() == dmDoiTuongTen || dmDoiTuong.getDmdoituongTen().equals(dmDoiTuongTen)){
			    	hasTenDT = true;
			    	dmDoiTuong_Find = dmDoiTuong;
			    	break;
			    }	    		
			}	
			if(hasTenDT){
				phieuDuTru.setDmdoituongMaso(dmDoiTuong_Find);
			}else{
				phieuDuTru.setDmdoituongMaso(new DmDoiTuong());
			}
		}
		log.info("-----END onblurTenDoiTuongAction()-----");
	}
	
	public void refreshDmDoiTuong(){
		listDmDoiTuongs.clear();
		DieuTriUtilDelegate dieutriDel = DieuTriUtilDelegate.getInstance();
		hmDoiTuong.clear();
		hmDoiTuong = dieutriDel.findByDoiTuongThuPhi();
		if( hmDoiTuong != null ){
			java.util.Set set = hmDoiTuong.entrySet();
		    Iterator i = set.iterator();
		    listDmDoiTuongs.add(new SelectItem("T\u00E2\u0301t ca\u0309"));
		    while(i.hasNext()){
		    	Map.Entry me = (Map.Entry)i.next();
		    	DmDoiTuong dmDoiTuong = (DmDoiTuong)me.getValue();		    	
		    	listDmDoiTuongs.add(new SelectItem(dmDoiTuong.getDmdoituongTen()));
		    }
		}		
	}
	//End Tho add
	/**
	 * Xoa chi tiet xuat
	 */
	public void deleteCt(int index) {
		log.info("-----deleteCt()-----");
		CtPhieuDuTruExt ctExDel = listCtDTLanhThuocEx.remove(index);
		listCtPhieuDtDel.add(ctExDel.getCtPhieuDt());
		this.count = listCtDTLanhThuocEx.size();
		tinhTien();
	}
	
	public String getDmKhoaNhan() {
		return dmKhoaNhan;
	}

	public void setDmKhoaNhan(String dmKhoaNhan) {
		this.dmKhoaNhan = dmKhoaNhan;
	}

	/**
	 * Hien thi chi tiet xuat
	 */
	public void selectCt(Integer index) {
		log.info("-----selectCt()-----");
		//log.info(String.format("-----selected: %s", selected.getCtTraKho().getCttrakhoThutu()));
		try {
			selected=listCtDTLanhThuocEx.get(index);
			listMas = selected.getListThuocNoiTruMa();
			TonKho tk = selected.getTonKhoXuat();
			CtPhieuDt ctx = selected.getCtPhieuDt();
			updateItem = index;
			System.out.println("tonkhoMa: "+tk);
			if(tk.getTonkhoMa() != null){
				tonkhoMa = tk.getTonkhoMa().toString();
				tonkho = tk.getTonkhoTon();				
			}
			dmtMa = ctx.getDmthuocMaso().getDmthuocMa();
			dmtTen = ctx.getDmthuocMaso().getDmthuocTen();
			xuat = ctx.getCtdtSoluong();
		} catch (Exception e) {
			log.info("***** error: "+e);
		}
		//int index = selected.getCtXuatKho().getCtxuatkhoThutu().intValue() - 1;
		
		
	}

	/**
	 * Cap nhat chi tiet phieu xuat
	 */
	public void tiepTucNhap() {
		log.info("-----tiepTucNhap()-----");	
		
		if (xuat == null || xuat.equals("") || tonkho  == null || tonkho.equals("")){
			return;
		}
		      
		if ("".equals(tonkhoMa) && tonkhoMa == null) {
			log.info("-----tonkho ma is null.");
		} else {
			log.info(String.format("-----tonkho ma: %s", tonkhoMa));
			TonKho tk = null;
			
			TonKhoDelegate tkDelegate;
			try {
				tkDelegate = TonKhoDelegate.getInstance();
				
				tk = tkDelegate.find(Integer.valueOf(tonkhoMa));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			CtPhieuDuTruExt ctxEx = new CtPhieuDuTruExt();
			CtPhieuDt ctx = new CtPhieuDt();
			Double slXuat = new Double("0");
			//ThoVNA 28/03/2011 - Begin add: kiem tra neu cung 1 lo thi cho phep cong don hay khong
			//Su dung doi tuong updateItem de luu lai doi tuong truoc khi chinh sua
			//khi chinh sua xong se xoa doi tuong nay va them vao 1 doi tuong da chinh sua
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date dHt = null;
			try{
				dHt = df.parse(ngayXuat);
			}catch(ParseException er){
				return;
			}
					
			int viTri = -1;
			if(updateItem != -1 ){
				if (listCtDTLanhThuocEx.size() > updateItem){
					viTri = updateItem ;
					updateItem = -1;						
				}else{
					updateItem = -1;
					return;
				}					
			}
			if (tk != null) {
				ctx.setCtdtNgaygiocn(new Date());
				slXuat += Double.valueOf(xuat);
				ctx.setCtdtSoluong(slXuat);
				ctx.setCtdtMalk(tk.getTonkhoMalienket());
				
				String malk = ctx.getCtdtMalk();
				Double slXuatKho = new Double("0");
				if(listCtDTLanhThuocEx.size() > 0){
					for(int i = 0; i< listCtDTLanhThuocEx.size();i++){
						CtPhieuDuTruExt ctPhieuDuTruExt = listCtDTLanhThuocEx.get(i);
						CtPhieuDt ctxk = ctPhieuDuTruExt.getCtPhieuDt();
						if(malk.toUpperCase().equals(ctxk.getCtdtMalk())){
							slXuatKho += ctxk.getCtdtSoluong();
						}
					}
					Double xuat = ctx.getCtdtSoluong();
					slXuatKho += Double.valueOf(xuat);
					if(slXuatKho > tk.getTonkhoTon()){
						FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_SLKHONGDUXUAT);
						return;
					}
				}
				/*
				 * Neu chua co thuoc nao trong list thi add vao listCtDTLanhThuocEx
				 * Neu da co thuoc trong list thi kiem tra:
				 * + Thuoc da ton tai trong DB CtPhieuDuTru chua: dua vao ctxk.getCtdtMa() bang hay khac null
				 * 		- Neu chua(== null): cho phep cong don neu cung 1 lo tuc trung ma lien ket
				 * 		- Neu ton tai roi (khac null): kiem tra xem thuoc nay da tao phieu xuat chua (PhieuDTDaxuat bang null hay khac null)
				 * 			+ Bang null: Chua tao phieu xuat cho phieu du tru nay: cho phep cong don
				 * 			+ Khac null: Tao phieu xuat roi: add dong moi neu cung 1 lo
				 * + Thuoc chua ton tai trong DB CtPhieuDuTru: cho phep cong don
				*/
				if (viTri == -1){//add moi
					log.info("-----them moi ct");
					if(listCtDTLanhThuocEx.size() > 0){
						int vt = -1;
						double sl = 0;
						boolean foundOnNewsList = false;
						for(int i = 0; i< listCtDTLanhThuocEx.size();i++){
							CtPhieuDuTruExt ctPhieuDuTruExt = listCtDTLanhThuocEx.get(i);
							CtPhieuDt ctxk = ctPhieuDuTruExt.getCtPhieuDt();
							if(ctx.getCtdtMalk().equals(ctxk.getCtdtMalk())){
								if(ctxk.getCtdtMa() == null){
									if(!ctPhieuDuTruExt.getIsTongHopButton()){
										foundOnNewsList = true;
										vt = i;	
										sl = ctx.getCtdtSoluong() + ctxk.getCtdtSoluong();
									}
								}else{//Da luu trong DB
									if(ctxk.getPhieudtMa().getPhieudtDaXuat() == null){//kiem tra xem neu chua xuat: cho phep update tuc cho phep cong don
										foundOnNewsList = true;
										vt = i;	
										sl = ctx.getCtdtSoluong() + ctxk.getCtdtSoluong();
										ctx.setCtdtMa(ctxk.getCtdtMa());
									}
								}										
								break;
							}
						}
						if(foundOnNewsList ==  false){//Them moi
							ctxEx = createCtDTru(ctx, tk);
							listCtDTLanhThuocEx.add(ctxEx);
						}else{//update
							ctx.setCtdtSoluong(sl);
							ctxEx = createCtDTru(ctx, tk);
							listCtDTLanhThuocEx.set(vt, ctxEx);
						}
					}else{//add moi dong dau tien vao list
						ctxEx = createCtDTru(ctx, tk);
						listCtDTLanhThuocEx.add(ctxEx);
					}
				}else{//update link
					log.info("-----Cap nhat ct-----");					
					selected = listCtDTLanhThuocEx.get(viTri);
					System.out.println("Duoc phep chinh sua: "+selected.getIsTongHopButton());
					if(selected.getIsTongHopButton()){//Khong cho phep chinh sua so luong, chi cho phep thay doi ton kho ma
						if(ctx.getCtdtSoluong().equals(selected.getCtPhieuDt().getCtdtSoluong())){
							ctx.setCtdtMa(selected.getCtPhieuDt().getCtdtMa());
							ctxEx = new CtPhieuDuTruExt();
							ctxEx = createCtDTru(ctx, tk);												
						}else{
							FacesMessages.instance().add(IConstantsRes.PHIEU_DU_TRU_NOTEDIT);
							tonkhoMa = "";
							dmtMa = "";
							dmtTen = "";
							tonkho = new Double(0);
							xuat = new Double(0);
							updateItem = -1;
							return;	
						}
					}else{
						ctx.setCtdtMa(selected.getCtPhieuDt().getCtdtMa());
						ctxEx = new CtPhieuDuTruExt();
						ctxEx = createCtDTru(ctx, tk);	
					}
					listCtDTLanhThuocEx.set(viTri, ctxEx);
				}
			}			
			//ThoVNA 28/03/2011 - End add: kiem tra neu cung 1 lo thi cho phep cong don hay khong
			
			count = listCtDTLanhThuocEx.size();
			log.info(String.format("-----listCtXuatKho: %s", listCtDTLanhThuocEx.size()));
			tonkhoMa = "";
			dmtMa = "";
			dmtTen = "";
			tonkho = new Double(0);
			xuat = new Double(0);
			updateItem = -1;
			listMas = new ArrayList<Integer>();
			selected = new CtPhieuDuTruExt();
		}
		tinhTien();
	}
	
	public List<ThuocNoiTru> getThuocNoiTru(){
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
		Date tuNgay = null;
		Date denNgay = null;
		try{
			ngayXuat = ngayXuat + " " + gioLapPhieuTu+":00";
			ngayXuatDen = ngayXuatDen + " " + gioLapPhieuDen+":00";
			tuNgay = formatter.parse(ngayXuat);
			denNgay = formatter.parse(ngayXuatDen);
		}catch(Exception er){	
			er.printStackTrace();
		}
		Integer khoaMaso = 0;//Khoa nhan
		
		if (phieuDuTru.getDmkhoaMaso().getDmkhoaMa() != null && !phieuDuTru.getDmkhoaMaso().getDmkhoaMa().equals("")) {
			khoaMaso = phieuDuTru.getDmkhoaMaso().getDmkhoaMaso();
		}
		
		String loaiMa = "";
		Integer doituongMaso = 0;
		if(phieuDuTru.getDmdoituongMaso(true).getDmdoituongMaso() != null){
			doituongMaso = phieuDuTru.getDmdoituongMaso(true).getDmdoituongMaso();
		}
		
		Integer dmTangMaso = 0;
		if(phieuDuTru.getDmtangMaso(true).getDmtangMaso() != null){
			dmTangMaso = phieuDuTru.getDmtangMaso(true).getDmtangMaso();
		}
		
		String loaiPhieu = phieuDuTru.getPhieudtLoaiPhieu();
		if(loaiPhieu != null || !loaiPhieu.equals(""))
		{
			if (phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocMa() != null ){
				if( phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocMa().toUpperCase().equals("TD"))
				{
					if(!dmLoaiPhieuMa.equals("HT") && !dmLoaiPhieuMa.equals("GN")){
						loaiMa = "TD-TT";
						DmLoaiPhieu lp = hmDmLoaiPhieu.get(dmLoaiPhieuMa);
						if(lp.getDmloaiphieuDvt() != null){
							loaiMa+=";"+lp.getDmloaiphieuDvt();
						}
					}else{
						loaiMa = "TD-"+ dmLoaiPhieuMa;
					}
				}else if( phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocMa().toUpperCase().equals("DY")){
					DmLoaiPhieu lp = hmDmLoaiPhieu.get(dmLoaiPhieuMa);
					if(lp.getDmloaiphieuDvt() != null){
						loaiMa = "DY-"+ lp.getDmloaiphieuDvt();
					}
				}else{
					//query theo dm loai thuoc trong ThuocNoiTruDelegate
					loaiMa = phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocMa();
				}	
			}	
		}
		/*Format cua loaiMa: loaiMa chac chan khac ""
	        Tan duoc: thuoc thuong -> Format la: TD-TT
	        Tan duoc: thuoc vien   -> Format la: TD-TT;IN (35, 36, 37, 44)
	        Tan duoc: thuoc khac   -> Format la: TD-TT;NOT IN (35, 36, 37, 44)
	        Tan duoc: huong than   -> Format la: TD-HT
	        Tan duoc: gay nghien   -> Format la: TD-GN
	        Y dung cu:             -> Format la YC
	        Hoa chat               -> Format la HC
	        Dong Y - Thanh pham    -> Format la DY-DD
	        Dong Y - Thuoc vi	   -> Format la DY-DD2
	        Thuoc chuong trinh     -> Format la CT
		 */
		ThuocNoiTruDelegate tntDelegate = ThuocNoiTruDelegate.getInstance();
		List<ThuocNoiTru> listTNT = tntDelegate.findDanhSachTNTDuTruLinhTuTruc(tuNgay, denNgay, loaiMa, khoaMaso, doituongMaso, dmTangMaso);
			
		return listTNT;
	}

	/**
	 * Ket thuc nhap phieu xuat
	 * @return
	 */
	public void end() {
		log.info("-----end()-----");		
		if (listCtDTLanhThuocEx.size() > 0) {
			log.info(String.format("-----ngayxuat %s", ngayXuat));
			if (!ngayXuat.equals("")) {
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				try {
					Date dtNgayXuat = df.parse(ngayXuat);
					Calendar cal = Calendar.getInstance();
					cal.setTime(dtNgayXuat);
					phieuDuTru.setPhieudtNgaygiocn(cal.getTime());
					phieuDuTru.setPhieudtNgay(cal.getTime());
				} catch (ParseException e) {
					log.error(String.format("-----Error: %s", e.toString()));
				}
			}
			try {
				yteLog = new YteLog();
				listDataLog="";
				double tt = Double.valueOf("0");
				ArrayList<CtPhieuDt> listCtPhieuDt = new ArrayList<CtPhieuDt>();
				List<Integer> listThuocNoiTruMa = new ArrayList<Integer>();
				HashMap<Integer,List> ctdt_matnt = new HashMap<Integer,List>();
				CtPhieuDuTruExt obj=new CtPhieuDuTruExt();
				for (int i=0;i<listCtDTLanhThuocEx.size();i++) {
					CtPhieuDt ct = listCtDTLanhThuocEx.get(i).getCtPhieuDt();
					List<Integer> listThuocNoiTruMa_Temp = listCtDTLanhThuocEx.get(i).getListThuocNoiTruMa();
					if(listThuocNoiTruMa_Temp.size()>0){
						for(int j=0;j<listThuocNoiTruMa_Temp.size();j++){
							listThuocNoiTruMa.add(listThuocNoiTruMa_Temp.get(j));
						}
					}
					ct.setCtdtNgaygiocn(new Date());
					ctdt_matnt.put(i, listThuocNoiTruMa_Temp);
					log.info(String.format("-----so luong: %s", ct.getCtdtSoluong()));
					tt += ct.getCtdtSoluong() * ct.getCtdtDongia();
					listCtPhieuDt.add(ct);
					//luu log thong tin thuoc
					listDataLog += "Ma LK:"+ ct.getCtdtMalk()+
							" Don gia: "+  ct.getCtdtDongianhap()+ " Don gia ban: "+ ct.getCtdtDongia() + 
							" So luong: "+ ct.getCtdtSoluong()+
							" So lo: "+ ct.getCtdtLo() +
							" Nam SX: " + ct.getCtdtNamnhap()+
							" Nam HD: " + ct.getCtdtNamhandung()+ "\n";
				}
				log.info(String.format("-----thanh tien: %s", tt));
				phieuDuTru.setPhieudtNgaygiocn(new Date());
				phieuDuTru.setPhieudtTrangThai(CHUA_LUU_PHIEU_XUAT);
				phieuDuTru.setPhieudtPhanBiet(CtPhieuDtDelegate.LANH_THUOC_TU_TRUC);
				if ("".equals(Utils.reFactorString(phieuDuTru.getDmtangMaso(true).getDmtangMa())))
				{
					phieuDuTru.setDmtangMaso(new DmTang());
					log.info("Buong null");
				}
				CtPhieuDtDelegate pxDelegate = CtPhieuDtDelegate.getInstance();
				clearInfor();
				String ketqua = pxDelegate.updatePhieuDuTruLinhNT(phieuDuTru, listCtPhieuDt, listCtPhieuDtDel, listThuocNoiTruMa, ctdt_matnt, IConstantsRes.PRIORITY_TON_LO_THUOC);
				
				if (ketqua != null && !ketqua.equals("")) {
					if (ketqua.indexOf(".") >= 0){
						FacesMessages.instance().add(ketqua);
						hienThiGhiNhan="true";
						hienThiHuyPhieu = "";
						hienThiInPhieu="";
					}else{
						resetInfo();
						maPhieu = ketqua;
						FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THANHCONG, maPhieu);
						displayPhieuXuatKho();
						//reset();
						setIsUpdate("1");
						isDeleted=phieuDuTru.getPhieudtTrangThai().toString();
						hienThiGhiNhan="";
						hienThiHuyPhieu = "";
						hienThiInPhieu="true";

						
//						Luu log he thong
				         yteLog.setForm("B3142_Phieulanhthuoctutruc");
				         yteLog.setUserId(identity == null ? "NULL" : identity.getUsername());
				         yteLog.setObjectId(maPhieu);
				         yteLog.setLogString(" Ngay lap tu ngay: "+ gioLapPhieuTu + " " + ngayXuat +
				        		 			" Ngay lap den ngay: "+ gioLapPhieuDen + " " + ngayXuatDen +
				        		 			" Doi tuong: "+ (dmDoiTuongTen == null ? "NULL" : dmDoiTuongTen)+
				        		 			" Loai thuoc: " + phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocMa()+
				        		 			" Loai phieu: "+ phieuDuTru.getPhieudtLoaiPhieu()+
				        		 			" Khoa nhan: "+ phieuDuTru.getDmkhoaMaso(true).getDmkhoaMa()+
				        		 			" Buong: "+ phieuDuTru.getDmtangMaso(true).getDmtangMa()+
				        		 			" Kho nhan: "+ (dmkhoaXuatMa == null ? "NULL" : dmkhoaXuatMa)+
				        		 			" Nguoi nhap: "+ phieuDuTru.getDtdmnhanvienCn(true).getDtdmnhanvienMa()+
				        		 			" Nguoi lap: "+ phieuDuTru.getDtdmnhanvienLapphieu(true).getDtdmnhanvienMa()+
				        		 			" Nguoi ky:" + phieuDuTru.getDtdmnhanvienBacsiky(true).getDtdmnhanvienMa()+
				        		 			" Thanh tien: "+ tongTien
				         					);
				         yteLog.setDateTime(new Date());
				         yteLog.setListData(listDataLog);
				         
				         yteLogDele.create(yteLog);
					}
				} else {
					FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THATBAI);
				}
				
			} catch (Exception e) {
				FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THATBAI);
				log.error(String.format("-----Error: %s", e.toString()));
			}
		} else {
			FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_DMT_NULL);
		}
	}
	
	public void taothongtin(){
		log.info("Begin taothongtin() ******");		
		log.info("phieuDuTru.getPhieudtMa():");
		/* Kiem tra neu la phieu du tru linh cho cac khoa noi tru, thi tong hop se tu ba?ng thuoc noi tru ung voi thuocnoitru_tutruc_pdt = 1
		 * Thuocnoitru_maphieudt, neu = null tuc la chua tong hop, khac null tuc la da tong hop roi, k can tong hop lai
		 * */
		if (phieuDuTru.getPhieudtMa() == null || phieuDuTru.getPhieudtMa().equals("")) {
			try{
				listCtDTLanhThuocEx.clear();
				List<ThuocNoiTru> tnt_temp = getThuocNoiTru();
				if (tnt_temp != null) {
					log.info("tnt_temp = " + tnt_temp.size());
					/* Kiem tra neu danh sach tong hop co cung ma thuoc thi cong don lai va luu cac ma thuoc noi tru thanh 1 dong
					 * Sau do add vao luoi
					 * */
					ArrayList<CtPhieuDuTruExt> listCtDTEx=new ArrayList<CtPhieuDuTruExt>();
					CtPhieuDt ctPhieuDt = new CtPhieuDt();
					List<Integer> tntMaList = new ArrayList<Integer>();		
					for (ThuocNoiTru tnt : tnt_temp) {	
						ctPhieuDt = new CtPhieuDt();
						tntMaList = new ArrayList<Integer>();	
						
						ctPhieuDt.setDmthuocMaso(tnt.getThuocnoitruMathuoc());
						ctPhieuDt.setCtdtSoluong(tnt.getThuocnoitruSoluong());
						ctPhieuDt.setCtdtDongia(tnt.getThuocnoitruDongiabh());
						tntMaList.add(tnt.getThuocnoitruMa());
						
						if(listCtDTEx.size() == 0){	
							CtPhieuDuTruExt ctPhieuDuTruExt = new CtPhieuDuTruExt();
							ctPhieuDuTruExt.setIsTongHopButton(true);							
							
							ctPhieuDuTruExt.setListThuocPhongKhamMa(tntMaList);
							ctPhieuDuTruExt.setCtPhieuDt(ctPhieuDt);
							
							listCtDTEx.add(ctPhieuDuTruExt);
						}else{
							CtPhieuDuTruExt resultListExt = new CtPhieuDuTruExt();							
							boolean foundInList = false;
							int vitri = -1;
							for(int j = 0; j<listCtDTEx.size(); j++){
								CtPhieuDuTruExt ctPhieuDuTruExt1 = listCtDTEx.get(j);
								CtPhieuDt ctPhieuDt1 = ctPhieuDuTruExt1.getCtPhieuDt();
								if(ctPhieuDt1.getDmthuocMaso().getDmthuocMa().equals(tnt.getThuocnoitruMathuoc().getDmthuocMa())){	
									resultListExt = ctPhieuDuTruExt1;
									foundInList = true;
									vitri = j;
									break;
								}
							}
							if(foundInList == true){
								ctPhieuDt = resultListExt.getCtPhieuDt();
								tntMaList = resultListExt.getListThuocNoiTruMa();
								ctPhieuDt.setCtdtSoluong(ctPhieuDt.getCtdtSoluong() + tnt.getThuocnoitruSoluong());
								tntMaList = resultListExt.getListThuocNoiTruMa();
								tntMaList.add(tnt.getThuocnoitruMa());
									
								resultListExt.setCtPhieuDt(ctPhieuDt);
								resultListExt.setListThuocPhongKhamMa(tntMaList);
								listCtDTEx.set(vitri,resultListExt);
							}else{
								CtPhieuDuTruExt ctPhieuDuTruExt = new CtPhieuDuTruExt();
								ctPhieuDuTruExt.setIsTongHopButton(true);
								ctPhieuDuTruExt.setListThuocPhongKhamMa(tntMaList);
								ctPhieuDuTruExt.setCtPhieuDt(ctPhieuDt);
								
								listCtDTEx.add(ctPhieuDuTruExt);
							}
						}
						ctPhieuDt = new CtPhieuDt();
						tntMaList = new ArrayList<Integer>();
					}	
					System.out.println("listCtDTEx.size: " +listCtDTEx.size());	
					//Tho add 17/8/2011: Lam tron neu so luong tu 0.0001 - 0.9999 se tang len 1 neu phan loai thuoc cua thuoc do cho phep lam tron
					List<CtPhieuDuTruExt> listCtDTExTemp1=new ArrayList<CtPhieuDuTruExt>();
					if(listCtDTEx.size()>0){
						for(CtPhieuDuTruExt ctPhieuDuTruExt: listCtDTEx){
							CtPhieuDt ctdt = ctPhieuDuTruExt.getCtPhieuDt();
							Double soluongXinLinh = ctdt.getCtdtSoluong();
							System.out.println("Co lam tron hay khong: "+ctdt.getDmthuocMaso().getDmphanloaithuocMaso().getDmphanloaithuocNhom3());
							if(ctdt.getDmthuocMaso().getDmphanloaithuocMaso().getDmphanloaithuocNhom3() != null && ctdt.getDmthuocMaso().getDmphanloaithuocMaso().getDmphanloaithuocNhom3().equals("1")){
								//Tho add rule 17/8/2011: Se duoc lam tron neu 0.0001 den 0.9999 se lam tron thanh 1
								int sochan = soluongXinLinh.intValue();						
								if(sochan < soluongXinLinh){
									sochan+=1;
								}
								soluongXinLinh = Double.parseDouble(Integer.toString(sochan));
							}
							ctdt.setCtdtSoluong(soluongXinLinh);
							ctPhieuDuTruExt.setCtPhieuDt(ctdt);
							listCtDTExTemp1.add(ctPhieuDuTruExt);
						}
					}	
					//End Tho add 17/8/2011
					/*get so luong ton hien tai cua ma thuoc theo thu tu uu tien de so sanh: neu du so luong thi lay ton kho do
					 * Nguoc lai, neu khong du so luong thi khong add vao luoi
					 * */
					ArrayList<CtPhieuDuTruExt> listCtDTEx_Temp=new ArrayList<CtPhieuDuTruExt>();					
					if(listCtDTExTemp1.size()>0){
						for(CtPhieuDuTruExt ctPhieuDuTruExt: listCtDTEx){							
							Integer thuocMaso = ctPhieuDuTruExt.getCtPhieuDt().getDmthuocMaso().getDmthuocMaso();
							Double soluongXinLinh = ctPhieuDuTruExt.getCtPhieuDt().getCtdtSoluong();
							TonKhoDelegate tonkhoDel = TonKhoDelegate.getInstance();
							List<TonKho> lstTonKhoHT = new ArrayList<TonKho>();
							lstTonKhoHT = tonkhoDel.getTonKhoHienTai(thuocMaso, dmkhoaXuatMaso, IConstantsRes.PRIORITY_TON_LO_THUOC);
							if(lstTonKhoHT != null && lstTonKhoHT.size()>0){
								for(int i = 0; i<lstTonKhoHT.size(); i++){
									TonKho tonkhoHienTai = lstTonKhoHT.get(i);
									Double tonLo = tonkhoHienTai.getTonkhoTon();
									CtPhieuDt ctPhieuDt_temp = ctPhieuDuTruExt.getCtPhieuDt();
									CtPhieuDuTruExt ctPhieuDuTruExt_Temp = ctPhieuDuTruExt;
									
									if(soluongXinLinh <= tonLo){
										ctPhieuDt_temp.setCtdtSoluong(soluongXinLinh);
										System.out.println("So luong xin linh1: " +soluongXinLinh);
										ctPhieuDuTruExt_Temp.setCtPhieuDt(ctPhieuDt_temp);
										ctPhieuDuTruExt_Temp.setTonKho(tonkhoHienTai);
										ctPhieuDuTruExt_Temp = createCtDTru(ctPhieuDt_temp, tonkhoHienTai);										
										ctPhieuDuTruExt_Temp.setIsTongHopButton(ctPhieuDuTruExt.getIsTongHopButton());
										ctPhieuDuTruExt_Temp.setListThuocPhongKhamMa(ctPhieuDuTruExt.getListThuocNoiTruMa());
										
										listCtDTEx_Temp.add(ctPhieuDuTruExt_Temp);
										
										ctPhieuDt_temp = new CtPhieuDt();
										ctPhieuDuTruExt_Temp = new CtPhieuDuTruExt();
										break;
									}else{
										soluongXinLinh = soluongXinLinh - tonLo;
										System.out.println("So luong xin linh2: " +tonLo);
										ctPhieuDt_temp.setCtdtSoluong(tonLo);
										ctPhieuDuTruExt_Temp.setCtPhieuDt(ctPhieuDt_temp);
										ctPhieuDuTruExt_Temp.setTonKho(tonkhoHienTai);
										ctPhieuDuTruExt_Temp = createCtDTru(ctPhieuDt_temp, tonkhoHienTai);
										ctPhieuDuTruExt_Temp.setIsTongHopButton(ctPhieuDuTruExt.getIsTongHopButton());
										ctPhieuDuTruExt_Temp.setListThuocPhongKhamMa(ctPhieuDuTruExt.getListThuocNoiTruMa());
										
										listCtDTEx_Temp.add(ctPhieuDuTruExt_Temp);
										
										ctPhieuDt_temp = new CtPhieuDt();
										ctPhieuDuTruExt_Temp = new CtPhieuDuTruExt();
									}
								}
							}
						}
					}
					System.out.println("listCtDTEx_Temp.size: " +listCtDTEx_Temp.size());	
					//Add listCtDTEx vao listCtDTLanhThuocEx
					if(listCtDTEx_Temp.size()>0){
						for(CtPhieuDuTruExt ctPhieuDuTruExt: listCtDTEx_Temp){
							listCtDTLanhThuocEx.add(ctPhieuDuTruExt);
							System.out.println("List tnt: "+ctPhieuDuTruExt.getListThuocNoiTruMa());
						}
					}
					tinhTien();
				}else{
					FacesMessages.instance().add(IConstantsRes.PHIEU_DU_TRU_KHONGCOTHUOC);
					return;
				}
				
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Error: " + e.toString());
			}
		}		
		log.info("End taothongtin()");
	}
	
	public void huyPhieuDT(){
		log.info("****** start huyPhieuDT() ******");
		PhieuDuTruDelegate ctPhieuDTDelegate = PhieuDuTruDelegate.getInstance();
		boolean returnKQ = ctPhieuDTDelegate.removeAllPhieuDuTru(phieuDuTru) ;
		if (!returnKQ){
			FacesMessages.instance().add(IConstantsRes.HUY_PHIEU_THAT_BAI,phieuDuTru.getPhieudtMa());
			 hienThiGhiNhan="";
			 hienThiHuyPhieu = "";
			 hienThiInPhieu="";
		}else{
			FacesMessages.instance().add(IConstantsRes.HUY_PHIEU_THANH_CONG,phieuDuTru.getPhieudtMa());
			
			
//			Luu log he thong
	         yteLog.setForm("B3142_Phieulanhthuoctutruc");
	         yteLog.setUserId(identity == null ? "NULL" : identity.getUsername());
	         yteLog.setObjectId(phieuDuTru.getPhieudtMa());
	         yteLog.setLogString(" Huy phieu du tru tu truc: " +phieuDuTru.getPhieudtMa()+
	         					" Ngay lap tu ngay: "+ gioLapPhieuTu + " " + ngayXuat +
	        		 			" Ngay lap den ngay: "+ gioLapPhieuDen + " " + ngayXuatDen +
	        		 			" Doi tuong: "+ (dmDoiTuongTen == null ? "NULL" : dmDoiTuongTen)+
	        		 			" Loai thuoc: " + phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocMa()+
	        		 			" Loai phieu: "+ phieuDuTru.getPhieudtLoaiPhieu()+
	        		 			" Khoa nhan: "+ phieuDuTru.getDmkhoaMaso(true).getDmkhoaMa()+
	        		 			" Buong: "+ phieuDuTru.getDmtangMaso(true).getDmtangMa()+
	        		 			" Kho nhan: "+ (dmkhoaXuatMa == null ? "NULL" : dmkhoaXuatMa)+
	        		 			" Nguoi nhap: "+ phieuDuTru.getDtdmnhanvienCn(true).getDtdmnhanvienMa()+
	        		 			" Nguoi lap: "+ phieuDuTru.getDtdmnhanvienLapphieu(true).getDtdmnhanvienMa()+
	        		 			" Nguoi ky:" + phieuDuTru.getDtdmnhanvienBacsiky(true).getDtdmnhanvienMa()+
	        		 			" Thanh tien: "+ tongTien
	         					);
	         yteLog.setDateTime(new Date());
	         yteLog.setListData(listDataLog);
	         
	         yteLogDele.create(yteLog);
		
			
			reset();
			hienThiGhiNhan = "true";
			hienThiHuyPhieu = "";
			hienThiInPhieu = "";
			isUpdate = "1";
			isDeleted="0";
		}	
		log.info("****** end huyPhieuDT() ******");
	}
	
	@Destroy
	public void destroy() {
		log.info("-----destroy()-----");
	}
	
	/**
	 * Hien thi phieu xuat len giao dien
	 */
	public void displayPhieuXuatKho() {
		log.info("-----displayPhieuXuatKho()-----");
		listCtDTLanhThuocEx = new ArrayList<CtPhieuDuTruExt>();
		listCtPhieuDtDel = new ArrayList<CtPhieuDt>();
		if (!maPhieu.equals("")) {
			log.info(String.format("-----Phieu xuat ma: %s", this.maPhieu));

			try {
				CtPhieuDtDelegate pxkWS = CtPhieuDtDelegate.getInstance();
				phieuDuTru = pxkWS.findByPhieuDuTruPhanBiet(maPhieu, CtPhieuDtDelegate.LANH_THUOC_TU_TRUC);
				if (phieuDuTru != null) {
					yteLog = new YteLog();
					listDataLog="";

					maPhieu = phieuDuTru.getPhieudtMa();
					resetInfo();
					dmkhoaXuatMa = phieuDuTru.getPhieudtMakho().getDmkhoaMa();
					dmkhoaXuatTen = phieuDuTru.getPhieudtMakho().getDmkhoaTen();
					dmkhoaXuatMaso = phieuDuTru.getPhieudtMakho().getDmkhoaMaso();
					dmLoaiTen = phieuDuTru.getDmloaithuocMaso().getDmloaithuocTen();
					dmDoiTuongTen = phieuDuTru.getDmdoituongMaso(true).getDmdoituongTen();
					dmkhoaNhanTen = phieuDuTru.getDmkhoaMaso(true).getDmkhoaTen();
					for (CtPhieuDt ct : pxkWS.findByPhieuDuTruMa(maPhieu)){
						log.info("Ct xuat kho ma: " + ct.getCtdtMa());
						CtPhieuDuTruExt ctxEx = new CtPhieuDuTruExt();
						ctxEx.setCtPhieuDt(ct);
						listCtDTLanhThuocEx.add(ctxEx);
						//luu log thong tin thuoc
						listDataLog += "Ma LK:"+ ct.getCtdtMalk()+
								" Don gia: "+  ct.getCtdtDongianhap()+ " Don gia ban: "+ ct.getCtdtDongia() + 
								" So luong: "+ ct.getCtdtSoluong()+
								" So lo: "+ ct.getCtdtLo() +
								" Nam SX: " + ct.getCtdtNamnhap()+
								" Nam HD: " + ct.getCtdtNamhandung()+ "\n";

					}
					count = listCtDTLanhThuocEx.size();
					//Tho add					
					hienThiInPhieu="true";	
					if(phieuDuTru.getPhieudtDaXuat() == null){
						setIsUpdate("1");
						hienThiHuyPhieu="true";
						hienThiGhiNhan = "true";
					}else{
						hienThiHuyPhieu="";
						hienThiGhiNhan = "";
						setIsUpdate("0");
					}
					
					isDeleted=phieuDuTru.getPhieudtTrangThai().toString();
					refreshDmThuoc();
				} else {
					FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_NULL, maPhieu);
					reset();
				}
				tinhTien();
			} catch (Exception e) {
				FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_NULL, maPhieu);
				reset();
				log.error(String.format("-----Error: %s", e));
			}
		}
		
	}

	/**
	 * Tao chi tiet xuat kho tu ton kho
	 * @param ctx
	 * @param tk
	 * @return
	 */
	private CtPhieuDuTruExt createCtDTru(CtPhieuDt ctx, TonKho tk) {
		log.info(String.format("-----createCtTraKho()-----"));
		log.info(String.format("-----Ct xuat kho (input): %s", ctx.getCtdtMa()));
		log.info(String.format("-----ton kho (input): %s", tk.getTonkhoMa()));
		
		TonKho tkTra = null;
		try {
			tkTra = (TonKho) BeanUtils.cloneBean(tk);
			tkTra.setTonkhoNgaygiocn(new Date());
			tkTra.setTonkhoMa(null);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (tkTra != null) {
			tkTra.setTonkhoXuat(ctx.getCtdtSoluong());
			tkTra.setTonkhoTra(null);
			tkTra.setTonkhoNhap(null);
			tkTra.setTonkhoMa(tk.getTonkhoMa());
		}
		
		TonKho tkNhan = null;
		try {
			tkNhan = (TonKho) BeanUtils.cloneBean(tk);
			tkNhan.setTonkhoNgaygiocn(new Date());
			tkNhan.setTonkhoMa(null);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (tkNhan != null) {
			tkNhan.setTonkhoNhap(ctx.getCtdtSoluong());
			tkNhan.setTonkhoTra(null);
			tkNhan.setTonkhoXuat(null);
		}
		
		ctx.setDmnguonkinhphiMaso(tk.getDmnguonkinhphiMaso());
		ctx.setDmquocgiaMaso(tk.getDmquocgiaMaso());
		ctx.setDmthuocMaso(tk.getDmthuocMaso());
		ctx.setDmnhasanxuatMaso(tk.getDmnhasanxuatMaso());
		ctx.setDmnctMaso(tk.getDmnctMaso());
		ctx.setCtdtDongia(tk.getTonkhoDongia());
		ctx.setCtdtLo(tk.getTonkhoLo());
		ctx.setCtdtMalk(tk.getTonkhoMalienket());
		ctx.setCtdtNamhandung(tk.getTonkhoNamhandung());
		ctx.setCtdtNamnhap(tk.getTonkhoNamnhap());
		//ctx.setCtdtNgaygiocn(new Date());
		ctx.setCtdtNgayhandung(tk.getTonkhoNgayhandung());
		ctx.setCtdtThanghandung(tk.getTonkhoThanghandung());
		ctx.setPhieudtMa(phieuDuTru);
		ctx.setCtdtSodangky(tk.getTonkhoSodangky());
		//log.info("***** Ton kho ma:"+ ctx.getTonkhoMa());
		
		CtPhieuDuTruExt ctxEx = new CtPhieuDuTruExt();
		ctxEx.setCtPhieuDt(ctx);
		ctxEx.setTonKhoXuat(tkTra);
		ctxEx.setTonKhoNhan(tkNhan);
		return ctxEx;
	}
	public String thuchienAction(){
		XuatReport();
		return "B3360_Chonmenuxuattaptin";
	}
	int index= 0;
	/**
	 * xuat report 
	 */
	
	@Out(required=false)
	@In(required=false)
	private String reportPathVP=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeVP=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintVP = null;
	
	public void XuatReport() {
		reportTypeVP = "PhieuLanhThuocTuTrucKhoaPhong";
		log.info("Vao Method XuatReport PhieuLanhThuocTuTrucKhoaPhong");
		String baocao1 = null;
		Date currentDate = new Date();
		if (maPhieu != null && !maPhieu.equals("")) {
			try {
				CtPhieuDtDelegate pxkWS = CtPhieuDtDelegate.getInstance();
				phieuDuTru = pxkWS.findByPhieuDuTruPhanBiet(maPhieu, CtPhieuDtDelegate.LANH_THUOC_TU_TRUC);
				resetInfo();
				String loaiPhieu = phieuDuTru.getPhieudtLoaiPhieu();
				XuatReportDuocPham xuatReport = new XuatReportDuocPham();
				xuatReport.reportTypeKC = reportTypeVP;
				String loaiThuoc = loaiPhieu.substring(0, loaiPhieu.indexOf(" - ")).trim();
				xuatReport.XuatReportPhieuDuTruTuTruc(log, phieuDuTru, index, loaiThuoc);
				jasperPrintVP = xuatReport.jasperPrintKC;
				reportPathVP = xuatReport.reportPathKC;
				reportTypeVP = xuatReport.reportTypeKC;
				index = index + 1;
			} catch (Exception e) {
				log.info("ERROR Method XuatReport!!!");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public String troVe(){
		try {
			log.info("***** troVe()");
			return "B3142_Phieulanhthuoctutruc";
		} 		
		catch (Exception e) {
			log.info("***** End exception = " + e);    	
		} 
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public void reset() {
		log.info("-----reset()-----");
		maPhieu = "";
		updateItem = -1;
		tonkhoMa = "";
		dmtMa = "";
		tonkho = new Double(0);
		xuat = new Double(0);
		nguonMa = "";
		kpMa = "";
		count = 0;
		phieuDuTru = new PhieuDuTru();
		listCtDTLanhThuocEx = new ArrayList<CtPhieuDuTruExt>();
		listCtPhieuDtDel = new ArrayList<CtPhieuDt>();
		listMas = new ArrayList<Integer>();
		resetInfo();
		ngayXuat = Utils.getCurrentDate();
		ngayXuatDen  = Utils.getCurrentDate();
		ngayHienTai = Utils.getCurrentDate();
		isUpdate = "1";
		isDeleted="0";
		log.info("-----identity: " + identity.getUsername());
		DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
		DtDmNhanVien nv = nvDelegate.findByNd(identity.getUsername());
		if (nv != null) {
			phieuDuTru.setDtdmnhanvienCn(nv);
		}
		tongTien = new Double("0");
		dmkhoaXuatMa ="";
		dmkhoaXuatMaso = null;
		dmkhoaXuatTen ="";
		dmLoaiTen ="";
		listDmThuocs.clear();
		listDmLoaiPhieus.clear();
		gioLapPhieuTu ="00:00";
		gioLapPhieuDen ="23:59";
		dmDoiTuongTen ="";
		dmLoaiPhieuMa ="";
		hmDmLoaiPhieu.clear();
		phieuDuTru.setDmtangMaso(new DmTang());
	}
	
	private void tinhTien() {
		tongTien = new Double("0");
		for (CtPhieuDuTruExt ctExt : listCtDTLanhThuocEx) {
			Double sl = ctExt.getCtPhieuDt().getCtdtSoluong();
			if (sl == null) {
				sl = new Double("0");
			}
			Double dg = ctExt.getCtPhieuDt().getCtdtDongia();
			if (dg == null) {
				dg = new Double("0");
			}
			tongTien += sl * dg;
		}
		log.info("-----tong tien: " + tongTien);
	}
	
	private void resetInfo() {
		log.info("-----resetInfo()-----");
		if (phieuDuTru.getDmkhoaMaso() == null) {
			phieuDuTru.setDmkhoaMaso(new DmKhoa());
		}
		if (phieuDuTru.getPhieudtMakho() == null) {
			phieuDuTru.setPhieudtMakho(new DmKhoa());
		}
		if (phieuDuTru.getDmdoituongMaso() == null) {
			phieuDuTru.setDmdoituongMaso(new DmDoiTuong());
		}
		
		if (phieuDuTru.getDtdmnhanvienBacsiky() == null) {
			phieuDuTru.setDtdmnhanvienBacsiky(new DtDmNhanVien());
		}
		if (phieuDuTru.getDtdmnhanvienCn() == null) {
			phieuDuTru.setDtdmnhanvienCn(new DtDmNhanVien());
		}
		/*if (phieuTra.getDtdmnhanvienNhan() == null) {
			phieuTra.setDtdmnhanvienNhan(new DtDmNhanVien());
		}*/
		if (phieuDuTru.getDtdmnhanvienLapphieu() == null) {
			phieuDuTru.setDtdmnhanvienLapphieu(new DtDmNhanVien());
		}
		if (phieuDuTru.getDmtangMaso() == null) {
			phieuDuTru.setDmtangMaso(new DmTang());
		}
	}
	private void clearInfor(){
		
		if ("".equals(Utils.reFactorString(phieuDuTru.getDmkhoaMaso().getDmkhoaMaso()))) {
			phieuDuTru.setDmkhoaMaso(null);
		}
		if ("".equals(Utils.reFactorString(phieuDuTru.getPhieudtMakho().getDmkhoaMaso()))) {
			phieuDuTru.setPhieudtMakho(null);
		}
		if ("".equals(Utils.reFactorString(phieuDuTru.getDmdoituongMaso().getDmdoituongMaso() ))) {
			phieuDuTru.setDmdoituongMaso(null);
		}
		/*if ("".equals(Utils.reFactorString(phieuTra.getDmloaithuocMaso().getDmloaithuocMaso() ))) {
			phieuTra.setDmloaithuocMaso(null);
		}*/
		if ("".equals(Utils.reFactorString(phieuDuTru.getDtdmnhanvienBacsiky().getDtdmnhanvienMaso() ))) {
			phieuDuTru.setDtdmnhanvienBacsiky(null);
		}
		if ("".equals(Utils.reFactorString(phieuDuTru.getDtdmnhanvienCn().getDtdmnhanvienMaso() ))) {
			phieuDuTru.setDtdmnhanvienCn(null);
		}
		/*if ("".equals(Utils.reFactorString(phieuTra.getDtdmnhanvienNhan().getDtdmnhanvienMaso() ))) {
			phieuTra.setDtdmnhanvienNhan(null);
		}*/
		if ("".equals(Utils.reFactorString(phieuDuTru.getDtdmnhanvienLapphieu().getDtdmnhanvienMaso() ))) {
			phieuDuTru.setDtdmnhanvienLapphieu(null);
		}
		if ("".equals(Utils.reFactorString(phieuDuTru.getDmtangMaso().getDmtangMaso() ))) {
			phieuDuTru.setDmtangMaso(null);
		}
	}

	public void setPhieuDuTru(PhieuDuTru phieuXuat) {
		this.phieuDuTru = phieuXuat;
	}

	public PhieuDuTru getPhieuDuTru() {
		return phieuDuTru;
	}

	public void setNgayXuat(String ngayXuat) {
		this.ngayXuat = ngayXuat;
	}

	public String getNgayXuat() {
		return ngayXuat;
	}
	
	public void setNgayXuatDen(String ngayXuatDen) {
		this.ngayXuatDen = ngayXuatDen;
	}

	public String getNgayXuatDen() {
		return ngayXuatDen;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getCount() {
		return count;
	}

	public void setMaPhieu(String maPhieu) {
		this.maPhieu = maPhieu;
	}

	public String getMaPhieu() {
		return maPhieu;
	}

	public void setDmtMa(String dmtMa) {
		this.dmtMa = dmtMa;
	}

	public String getDmtMa() {
		return dmtMa;
	}

	public void setTonkho(Double tonkho) {
		this.tonkho = tonkho;
	}

	public Double getTonkho() {
		return tonkho;
	}

	public void setXuat(Double xuat) {
		this.xuat = xuat;
	}

	public Double getXuat() {
		return xuat;
	}

	public void setTonkhoMa(String tonkhoMa) {
		this.tonkhoMa = tonkhoMa;
	}

	public String getTonkhoMa() {
		return tonkhoMa;
	}

	public void setUpdateItem(Integer updateItem) {
		this.updateItem = updateItem;
	}

	public Integer getUpdateItem() {
		return updateItem;
	}

	public void setNgayHienTai(String ngayHienTai) {
		this.ngayHienTai = ngayHienTai;
	}

	public String getNgayHienTai() {
		return ngayHienTai;
	}

	public static Logger getLogger() {
		return log;
	}

	public static void setLogger(Logger logger) {
		PhieuDTLanhThuocTuTrucAction.log = logger;
	}

	public CtPhieuDuTruExt getSelected() {
		return selected;
	}

	public void setSelected(CtPhieuDuTruExt selected) {
		this.selected = selected;
	}
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getResultReportFileName() {
		return resultReportFileName;
	}

	public void setResultReportFileName(String resultReportFileName) {
		this.resultReportFileName = resultReportFileName;
	}

	public String getResultReportName() {
		return resultReportName;
	}

	public void setResultReportName(String resultReportName) {
		this.resultReportName = resultReportName;
	}

	public String getLoaiFileXuat() {
		return loaiFileXuat;
	}

	public void setLoaiFileXuat(String loaiFileXuat) {
		this.loaiFileXuat = loaiFileXuat;
	}

	public String getIsReport() {
		return isReport;
	}

	public void setIsReport(String isReport) {
		this.isReport = isReport;
	}

	public void setIsUpdate(String isUpdate) {
		this.isUpdate = isUpdate;
	}

	public String getIsUpdate() {
		return isUpdate;
	}

	public void setKpMa(String kpMa) {
		this.kpMa = kpMa;
	}

	public String getKpMa() {
		return kpMa;
	}

	public void setNguonMa(String nguonMa) {
		this.nguonMa = nguonMa;
	}

	public String getNguonMa() {
		return nguonMa;
	}

	public String getMalk() {
		return malk;
	}

	public void setMalk(String malk) {
		this.malk = malk;
	}

	public Double getTongTien() {
		return tongTien;
	}

	public void setTongTien(Double tongTien) {
		this.tongTien = tongTien;
	}

	public String getDmKhoXuat() {
		return dmKhoXuat;
	}

	public void setDmKhoXuat(String dmKhoXuat) {
		this.dmKhoXuat = dmKhoXuat;
	}
	
	public String getHienThiGhiNhan() {
		return hienThiGhiNhan;
	}

	public void setHienThiGhiNhan(String hienThiGhiNhan) {
		this.hienThiGhiNhan = hienThiGhiNhan;
	}

	public String getHienThiHuyPhieu() {
		return hienThiHuyPhieu;
	}

	public void setHienThiHuyPhieu(String hienThiHuyPhieu) {
		this.hienThiHuyPhieu = hienThiHuyPhieu;
	}

	public String getHienThiInPhieu() {
		return hienThiInPhieu;
	}

	public void setHienThiInPhieu(String hienThiInPhieu) {
		this.hienThiInPhieu = hienThiInPhieu;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public String getGioLapPhieuTu() {
		return gioLapPhieuTu;
	}
	
	public void setGioLapPhieuTu(String gioLapPhieuTu) {
		this.gioLapPhieuTu = gioLapPhieuTu;
	}
	
	public String getGioLapPhieuDen() {
		return gioLapPhieuDen;
	}
	
	public void setGioLapPhieuDen(String gioLapPhieuDen) {
		this.gioLapPhieuDen = gioLapPhieuDen;
	}
	
	public String getDmLoaiPhieuMa() {
		return dmLoaiPhieuMa;
	}
	
	public void setDmLoaiPhieuMa(String dmLoaiPhieuMa) {
		this.dmLoaiPhieuMa = dmLoaiPhieuMa;
	}
	
	public List<SelectItem> getListDmTangs() {
		return listDmTangs;
	}

	public void setListDmTangs(List<SelectItem> listDmTangs) {
		this.listDmTangs = listDmTangs;
	}

	public class CtPhieuDuTruExt implements Serializable{
		private static final long serialVersionUID = 0L;
		private CtPhieuDt ctDuTru;
		private TonKho tonKho;
		private TonKho tonKhoXuat;
		private TonKho tonKhoNhan;
		private Double thanhTien;
		private boolean isTongHopButton;
		private List<Integer> listThuocNoiTruMa;
		public Double getThanhTien() {
			return thanhTien;
		}
		public void setThanhTien(Double thanhTien) {
			this.thanhTien = thanhTien;
		}
		public CtPhieuDuTruExt(){
			ctDuTru = new CtPhieuDt();
			tonKho = new TonKho();
			listThuocNoiTruMa = new ArrayList<Integer>();
			SetInforUtil.setInforIfNullForTonKho(tonKho);
			if (tonKho.getDmthuocMaso().getDmdonvitinhMaso() == null) {
				tonKho.getDmthuocMaso().setDmdonvitinhMaso(new DmDonViTinh());
			}
			isTongHopButton = false;
			thanhTien = new Double(0);
			
		}
		public CtPhieuDt getCtPhieuDt() {
			return ctDuTru;
		}
		public void setCtPhieuDt(CtPhieuDt obj) {
			this.ctDuTru = obj;
		}
		public TonKho getTonKho() {
			return tonKho;
		}
		public void setTonKho(TonKho obj) {
			this.tonKho = obj;
		}
		public TonKho getTonKhoXuat() {
			if(tonKhoXuat==null)
				tonKhoXuat=new TonKho();
			return tonKhoXuat;
		}
		public void setTonKhoXuat(TonKho obj) {
			this.tonKhoXuat = obj;
		}
		public TonKho getTonKhoNhan() {
			return tonKhoNhan;
		}
		public void setTonKhoNhan(TonKho tonKhoNhan) {
			this.tonKhoNhan = tonKhoNhan;
		}
		
		public boolean getIsTongHopButton() {
			return isTongHopButton;
		}
		public void setIsTongHopButton(boolean isTongHopButton) {
			this.isTongHopButton = isTongHopButton;
		}
		
		public List<Integer> getListThuocNoiTruMa() {
			return listThuocNoiTruMa;
		}
		public void setListThuocPhongKhamMa(List<Integer> listThuocNoiTruMa) {
			this.listThuocNoiTruMa = listThuocNoiTruMa;
		}
	}
	
}

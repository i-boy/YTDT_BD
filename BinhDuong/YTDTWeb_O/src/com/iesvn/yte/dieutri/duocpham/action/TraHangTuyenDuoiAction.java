package com.iesvn.yte.dieutri.duocpham.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

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
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
import com.iesvn.yte.dieutri.delegate.CtTraKhoDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DmKhoaDelegate;
import com.iesvn.yte.dieutri.delegate.DmLoaiPhieuDelegate;
import com.iesvn.yte.dieutri.delegate.DmLoaiThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DmPhanLoaiThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DmPhanNhomThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DmThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.PhieuTraKhoDelegate;
import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
import com.iesvn.yte.dieutri.delegate.YteLogDelegate;
import com.iesvn.yte.dieutri.entity.CtTraKho;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.PhieuTraKho;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.dieutri.entity.YteLog;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
import com.iesvn.yte.entity.DmDonViTinh;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmLoaiPhieu;
import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.entity.DmNguonChuongTrinh;
import com.iesvn.yte.entity.DmNguonKinhPhi;
import com.iesvn.yte.entity.DmPhanLoaiThuoc;
import com.iesvn.yte.entity.DmPhanNhomThuoc;
import com.iesvn.yte.entity.DmThuoc;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;
import com.sun.org.apache.commons.beanutils.BeanUtils;

@Scope(CONVERSATION)
@Name("B4167_TuyenDuoiTraHang")
@Synchronized(timeout = 6000000)
/***
 *		Form nay tra hang tu cac don vi tuyen duoi
 * */
public class TraHangTuyenDuoiAction implements Serializable {

	private static final long serialVersionUID = 4366573502725366923L;
	private static Logger log = Logger.getLogger(TraHangTuyenDuoiAction.class);
	private SimpleDateFormat formatter;
	private PhieuTraKho phieuTrakho;
	private String ngaytra;
	private DmPhanLoaiThuoc plthuoc;
	private DmNguonKinhPhi nguonkinhphi;
	private DmNguonChuongTrinh nguonchuongtrinh;
	@DataModel
	private List<CtTraKhoExt> listCtTraKhoExt;	
	@DataModelSelection
	private CtTraKhoExt selectedCtTraKhoExt;
	private CtTraKhoExt ctTraKhoExt;
	private boolean flagUpdateCtTrakho = false;
	
	private String resultReportFileName;
	private String resultReportName;	
	private String loaiFileXuat;
	@Out(required = false)
	private String isReport = "false";
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
	private String nofound;
	private String nosuccess;
	private String noinphieu;
	
	private Integer count;


	@In(required = false)
	@Out(required = false)
	private String tenChuongTrinh;
	
	String dmKhoNhan = "";
	
	public String getNosuccess() {
		return nosuccess;
	}

	public void setNosuccess(String nosuccess) {
		this.nosuccess = nosuccess;
	}
	
	 private YteLogDelegate yteLogDele = YteLogDelegate.getInstance();
     private YteLog yteLog;
     private String listDataLog;
	//Tho add
	HashMap<String,DmThuoc> hmDmThuoc = new HashMap<String,DmThuoc>();
	
	private String dmLoaiTen="";
	private DmLoaiThuocDelegate dmLoaiThuocDelegate;
	private List<SelectItem> listDmLoaiThuocs = new ArrayList<SelectItem>();
	HashMap<String, DmLoaiThuoc> hmLoaiThuoc = new HashMap<String, DmLoaiThuoc>();
	private DmLoaiPhieuDelegate dmLoaiPhieuDelegate;
	HashMap<String,DmLoaiPhieu> hmDmLoaiPhieu = new HashMap<String,DmLoaiPhieu>();
	private String dmLoaiPhieuMa ="";
	String loaiPhieu = "";
	private List<SelectItem> listDmLoaiPhieus = new ArrayList<SelectItem>();

	@Restrict("#{s:hasRole('NV_KhoaDuoc') or s:hasRole('QT_HT_Duoc')}")
	@Begin (join = true)
	public String init(String kho) { // kho = KC, NT
		log.info("***** init() *****");
		resetValue();		
		dmKhoNhan = kho;
		log.info("***** Kho nhan:? "+kho);
		if ("NT".equals(kho)){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoNoiTru;
		}else if ("KC".equals(kho)){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoChinh;
		}
		listDmLoaiThuocs.clear();
		listDmLoaiPhieus.clear();
		listDmThuocs.clear();
		hmLoaiThuoc.clear();
		hmDmThuoc.clear();
		refreshDmLoaiThuoc();
		refreshDmKhoTra();
		log.info("***** ten chuong trinh: "+tenChuongTrinh);
		log.info("***** end init() *****");
		
		return "QuanLyKhoChinh_NhapKhoChinh_TraHangTuyenDuoi";
	}
	
	@End
	public void end(){
		
	}
	
	/** ==================== BEGIN LY THEM CODE ==================== */	
	private DmThuocDelegate dmThuocDelegate;
	private List<SelectItem> listDmThuocs = new ArrayList<SelectItem>();
	
	public List<SelectItem> getListDmThuocs() {
		return listDmThuocs;
	}

	public void setListDmThuocs(List<SelectItem> listDmThuocs) {
		this.listDmThuocs = listDmThuocs;
	}
	//Tho edit again
	public void onblurMaThuocAction(){
		log.info("-----BEGIN onblurMaThuocAction()-----");
		if(ctTraKhoExt != null && ctTraKhoExt.tonKho != null && ctTraKhoExt.tonKho.getDmthuocMaso() != null){
			String maThuoc = ctTraKhoExt.tonKho.getDmthuocMaso().getDmthuocMa();
			DmThuoc dmThuoc = hmDmThuoc.get(maThuoc.toUpperCase());
			if(dmThuoc != null) {
				ctTraKhoExt.tonKho.setDmthuocMaso(dmThuoc);
				log.info("-----DA THAY DMTHUOC-----");
			}
			else {
				ctTraKhoExt.tonKho.setDmthuocMaso(new DmThuoc());
				ctTraKhoExt.tonKho.setDmthuocMaso(null);
			}
		}
		log.info("-----END onblurMaThuocAction()-----");		
	}
	//Tho edit again
	public void onblurTenThuocAction(){
		log.info("-----BEGIN onblurTenThuocAction()-----");
		if(ctTraKhoExt != null && ctTraKhoExt.tonKho != null && ctTraKhoExt.tonKho.getDmthuocMaso() != null){
			String tenThuoc = ctTraKhoExt.tonKho.getDmthuocMaso().getDmthuocTen();
//			DmThuoc dmThuoc = dmThuocDelegate.findByTenThuoc(tenThuoc);
//			if(dmThuoc != null) {
//				ctTraKhoExt.tonKho.setDmthuocMaso(dmThuoc);
//				log.info("-----DA THAY DMTHUOC-----");
//			}
//			else {
//				ctTraKhoExt.tonKho.setDmthuocMaso(null);
//			}
			Boolean hasTenThuoc = false;
			String maThuoc = null;
			if( hmDmThuoc != null ){
				java.util.Set set = hmDmThuoc.entrySet();
			    Iterator i = set.iterator();
			    while(i.hasNext()){
			    	Map.Entry me = (Map.Entry)i.next();
			    	DmThuoc dmThuoc = (DmThuoc)me.getValue();
			    	if(dmThuoc.getDmthuocTen().trim() == tenThuoc || dmThuoc.getDmthuocTen().trim().equals(tenThuoc)){
			    		hasTenThuoc = true;
			    		maThuoc = dmThuoc.getDmthuocMa();
			    		break;
			    	}	    		
			    }
		    }
		    if(hasTenThuoc){
		    	DieuTriUtilDelegate dieutriUtilDelegate = DieuTriUtilDelegate.getInstance();
		    	DmThuoc dmThuoc = (DmThuoc)dieutriUtilDelegate.findByMa(maThuoc, "DmThuoc", "dmthuocMa");
		    	ctTraKhoExt.tonKho.setDmthuocMaso(dmThuoc);
				log.info("-----DA THAY DMTHUOC-----");
		    }else{
		    	ctTraKhoExt.tonKho.setDmthuocMaso(null);
		    }
		}
		log.info("-----END onblurTenThuocAction()-----");		
	}
	
	public void refreshDmThuoc(){
		listDmThuocs.clear();
		hmDmThuoc.clear();
		dmThuocDelegate = DmThuocDelegate.getInstance();
				
		String loaiMa = "";
		if(!loaiPhieu.equals(""))
		{
			if (phieuTrakho != null && phieuTrakho.getDmloaithuocMaso(true).getDmloaithuocMa() != null ){
				if( phieuTrakho.getDmloaithuocMaso(true).getDmloaithuocMa().toUpperCase().equals("TD"))
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
				}else{
					//query theo dm loai thuoc trong ThuocNoiTruDelegate
					loaiMa = phieuTrakho.getDmloaithuocMaso(true).getDmloaithuocMa();
				}	
			}	
		}
		System.out.println("loaiMa: "+loaiMa);
		System.out.println("Kho: "+dmkhoaMaso);
		
		List<DmThuoc> lstDmThuoc = new ArrayList<DmThuoc>();
		if(!loaiMa.equals("") && !loaiPhieu.equals("") && dmkhoaMaso != null){
			lstDmThuoc = dmThuocDelegate.findByLoaiPhanLoaiThuocNhomThuocDVTKho(loaiMa.toUpperCase(), "", dmkhoaMaso);
		}		
		
		if(lstDmThuoc !=null && lstDmThuoc.size()>0)
		{
			for(DmThuoc o: lstDmThuoc){
				listDmThuocs.add(new SelectItem(o.getDmthuocTen()));
				hmDmThuoc.put(o.getDmthuocMa(), o);
			}
		}		
	}

/** ==================== END LY THEM CODE ==================== */	
	
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
		log.info("-----BEGIN onblurMaLoaiAction()-----");
		String loaihang_ma = phieuTrakho.getDmloaithuocMaso().getDmloaithuocMa();
		DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)hmLoaiThuoc.get(loaihang_ma.toUpperCase());
		if(dmLoaiThuoc != null) {
			setDmLoaiTen(dmLoaiThuoc.getDmloaithuocTen());
			phieuTrakho.setDmloaithuocMaso(dmLoaiThuoc);
		}
		else {
			setDmLoaiTen("");			
		}
		listDmLoaiPhieus.clear();
		refreshDmLoaiPhieu();
	    setLoaiPhieu("");
	    dmLoaiPhieuMa ="";
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
			phieuTrakho.setDmloaithuocMaso(dmLoaiThuoc_Find);
		}
	    listDmLoaiPhieus.clear();
		refreshDmLoaiPhieu();
	    setLoaiPhieu("");
	    dmLoaiPhieuMa ="";
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
	
	public void onblurTenLoaiPhieuAction(){
		log.info("-----BEGIN onblurTenLoaiPhieuAction()-----");
		String loaiPhieuItem = loaiPhieu;
		dmLoaiPhieuMa = loaiPhieuItem.substring(0, loaiPhieuItem.indexOf(" - ")).trim();
		refreshDmThuoc();
		log.info("-----END onblurTenLoaiPhieuAction()-----");
	}
	
	public void refreshDmLoaiPhieu(){
		listDmLoaiPhieus.clear();
		hmDmLoaiPhieu.clear();
		dmLoaiPhieuDelegate = DmLoaiPhieuDelegate.getInstance();
		if(phieuTrakho != null && phieuTrakho.getDmloaithuocMaso() != null){
			List<DmLoaiPhieu> lstDmLoaiPhieus = dmLoaiPhieuDelegate.findAll();
			if(lstDmLoaiPhieus !=null && lstDmLoaiPhieus.size()>0)
			{
				for(DmLoaiPhieu o: lstDmLoaiPhieus){
					hmDmLoaiPhieu.put(o.getDmloaiphieuMa(), o);
					if(phieuTrakho.getDmloaithuocMaso().getDmloaithuocMaso().equals(o.getDmloaithuocMaso().getDmloaithuocMaso())){
						listDmLoaiPhieus.add(new SelectItem(o.getDmloaiphieuMa() + " - " +o.getDmloaiphieuTen()));
					}
				}
			}
		}
	}
	
	public String getLoaiPhieu() {
		return loaiPhieu;
	}

	public void setLoaiPhieu(String loaiPhieu) {
		this.loaiPhieu = loaiPhieu;
	}
	
	private DmKhoaDelegate dmKhoaDelegate;
	private List<SelectItem> listDmKhoTras = new ArrayList<SelectItem>();	
	HashMap<String, DmKhoa> hmDmKhoTra = new HashMap<String, DmKhoa>();
	private Integer dmkhoaMaso;
	private String dmkhoaMa;
	private String dmkhoaTen;
	
	public Integer getDmkhoaMaso() {
		return dmkhoaMaso;
	}

	public void setDmkhoaMaso(Integer dmkhoaMaso) {
		this.dmkhoaMaso = dmkhoaMaso;
	}
	
	public String getDmkhoaMa() {
		return dmkhoaMa;
	}

	public void setDmkhoaMa(String dmkhoaMa) {
		this.dmkhoaMa = dmkhoaMa;
	}
	
	public String getDmkhoaTen() {
		return dmkhoaTen;
	}

	public void setDmkhoaTen(String dmkhoaTen) {
		this.dmkhoaTen = dmkhoaTen;
	}
	
	public List<SelectItem> getListDmKhoTras() {
		return listDmKhoTras;
	}

	public void setListDmKhoTras(List<SelectItem> listDmKhoXuats) {
		this.listDmKhoTras = listDmKhoTras;
	}
	
	public void onblurMaKhoTraAction(){
		log.info("-----BEGIN onblurMaKhoTraAction()-----");
		if(dmkhoaMa != null){
			DmKhoa dmKhoa = hmDmKhoTra.get(dmkhoaMa.toUpperCase());
			if(dmKhoa != null) {
				phieuTrakho.setDmkhoaTra(dmKhoa);
				dmkhoaMaso = dmKhoa.getDmkhoaMaso();
				dmkhoaMa = dmKhoa.getDmkhoaMa().toUpperCase();
				dmkhoaTen = dmKhoa.getDmkhoaTen();
				log.info("-----DA THAY DMKHOTRA-----");
			}
			else {
				phieuTrakho.setDmkhoaTra(new DmKhoa());
				dmkhoaMaso = null;
				dmkhoaMa = "";
				dmkhoaTen = "";
			}
		}
		listDmThuocs.clear();
		hmDmThuoc.clear();
		refreshDmThuoc();
		log.info("-----END onblurMaKhoTraAction()-----");
	}
	
	public void onblurTenKhoTraAction(){
		log.info("-----BEGIN onblurTenKhoTraAction()-----");
		Boolean hasTenKhoTra = false;
		DmKhoa dmKhoa_Finded = new DmKhoa();
		if(dmkhoaTen != ""){
			if( hmDmKhoTra != null ){
				java.util.Set set = hmDmKhoTra.entrySet();
			    Iterator i = set.iterator();
			    while(i.hasNext()){
			    	Map.Entry me = (Map.Entry)i.next();
			    	DmKhoa dmKhoa = (DmKhoa)me.getValue();
			    	if(dmKhoa.getDmkhoaTen() == dmkhoaTen || dmKhoa.getDmkhoaTen().equals(dmkhoaTen)){
			    		hasTenKhoTra = true;
			    		dmKhoa_Finded = dmKhoa;
			    		break;
			    	}	    		
			    }
		    }
		}
		
	    if(hasTenKhoTra){
	    	phieuTrakho.setDmkhoaTra(dmKhoa_Finded);
	    	dmkhoaMaso = dmKhoa_Finded.getDmkhoaMaso();
			dmkhoaMa = dmKhoa_Finded.getDmkhoaMa();
			log.info("-----DA THAY DMKHOTRA-----");
	    }else{
	    	phieuTrakho.setDmkhoaTra(new DmKhoa());
	    	dmkhoaMaso = null;
			dmkhoaMa = "";
			dmkhoaTen = "";
	    }	    
	    listDmThuocs.clear();
		hmDmThuoc.clear();
		refreshDmThuoc();
		log.info("-----END onblurTenKhoTraAction()-----");
	}
	
	public void refreshDmKhoTra(){
		listDmKhoTras.clear();
		hmDmKhoTra.clear();
		dmKhoaDelegate = DmKhoaDelegate.getInstance();		
		
		List<DmKhoa> lstDmKhoa = new ArrayList<DmKhoa>();
		lstDmKhoa = dmKhoaDelegate.getKhoaNhom12NOTINKhoDuoc();
		if(lstDmKhoa !=null && lstDmKhoa.size()>0)
		{
			for(DmKhoa o: lstDmKhoa){
				listDmKhoTras.add(new SelectItem(o.getDmkhoaTen()));
				hmDmKhoTra.put(o.getDmkhoaMa(), o);
			}
		}
	}

	public void resetValue(){
		log.info("---resetValue");
		phieuTrakho = new PhieuTraKho();
		SetInforUtil.setInforIfNullForPhieuTraKho(phieuTrakho);
		String format_date = "dd/MM/yyyy";
		formatter = new SimpleDateFormat(format_date);
		Calendar cal = Calendar.getInstance();
		ngaytra = formatter.format(cal.getTime());
		ctTraKhoExt = new CtTraKhoExt();
		plthuoc = new DmPhanLoaiThuoc();
		nguonkinhphi = new DmNguonKinhPhi();
		nguonchuongtrinh = new DmNguonChuongTrinh();
		DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
		DtDmNhanVien nv = nvDelegate.findByNd(identity.getUsername());
		if (nv != null) {
			phieuTrakho.setDtdmnhanvienCn(nv);
		}
		flagUpdateCtTrakho = false;
		listCtTraKhoExt = new ArrayList<CtTraKhoExt>();
		resultReportFileName = "";
		resultReportName = "";	
		loaiFileXuat = "";
		nofound = "false";
		nosuccess = "false";
		noinphieu = "false";
		//loai thuoc
		loaiPhieu ="";
		dmLoaiTen ="";
		dmkhoaMaso = null;
		dmkhoaMa ="";
		dmkhoaTen ="";		
		listDmLoaiThuocs.clear();
		hmLoaiThuoc.clear();
		refreshDmLoaiThuoc();
		listDmLoaiPhieus.clear();
		//Thuoc
		listDmThuocs.clear();
		hmDmThuoc.clear();
		refreshDmThuoc();
		dmLoaiPhieuMa ="";
		hmDmLoaiPhieu.clear();
	}
	
	public void enter() throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException{
		log.info("Add vao luoi");	
		updateTonKho(ctTraKhoExt);
		if (flagUpdateCtTrakho){
			flagUpdateCtTrakho = false;
		}else{
			log.info("enter11"+ ctTraKhoExt);
			listCtTraKhoExt.add(ctTraKhoExt);
		}
		tinhTongTien();
		ctTraKhoExt = new CtTraKhoExt();
		this.count = listCtTraKhoExt.size();
		log.info("Count: "+ count);
	}
	
	private void updateTonKho(CtTraKhoExt ctTraKhoExt) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
		Integer matonkho = ctTraKhoExt.getTonKho().getTonkhoMa();
		if (matonkho==null) return;
		TonKhoDelegate tkDelegate = TonKhoDelegate.getInstance();
		TonKho tk = tkDelegate.find(matonkho);		
		
		int updateCaseMalk = -1;
		
		CtTraKho cttk = ctTraKhoExt.getCtTraKho();
		
		/*--------------------*/
		
		String malk = ctTraKhoExt.getTonKho().getTonkhoMalienket();
		Double slTra = new Double("0");
		for (int i = 0; i < listCtTraKhoExt.size(); i++) {
			CtTraKho cttk_temp = listCtTraKhoExt.get(i).getCtTraKho();
			if (malk.toUpperCase().equals(cttk_temp.getCttrakhoMalk())) {				
				slTra += cttk_temp.getCttrakhoSoluong();
				flagUpdateCtTrakho = true;
				updateCaseMalk =  i;
			}
		}
		Double tra = ctTraKhoExt.getCtTraKho().getCttrakhoSoluong();
		slTra += Double.valueOf(tra);
		if(slTra > tk.getTonkhoTon()){
			nosuccess = "true";
			FacesMessages.instance().add(IConstantsRes.PHIEUTRAKHO_SLKHONGDUTRA);
			return;
		}
		cttk.setCttrakhoSoluong(slTra);
		/*--------------------*/	
		
		TonKho tkTra = duplicateTk(tk);
		if (tkTra != null) {
			tkTra.setTonkhoXuat(cttk.getCttrakhoSoluong());
			tkTra.setTonkhoNhap(null);
			tkTra.setTonkhoTra(null);
		}
		
		TonKho tkNhan = duplicateTk(tk);
		if (tkNhan != null) {
			tkNhan.setTonkhoNhap(null);
			tkNhan.setTonkhoXuat(null);
			tkNhan.setTonkhoTra(cttk.getCttrakhoSoluong());
		}		
		
		ctTraKhoExt.setTonKhoTra(tkTra);
		ctTraKhoExt.setTonKhoNhan(tkNhan);
		cttk.setDmnguonkinhphiMaso(tk.getDmnguonkinhphiMaso());
		cttk.setDmquocgiaMaso(tk.getDmquocgiaMaso());
		cttk.setDmthuocMaso(tk.getDmthuocMaso());
		cttk.setDmnhasanxuatMaso(tk.getDmnhasanxuatMaso());
		cttk.setDmnctMaso(tk.getDmnctMaso());
		cttk.setCttrakhoDongia(tk.getTonkhoDongia());
		cttk.setCttrakhoDongiaban(tk.getTonkhoDongiaban());
		cttk.setCttrakhoLo(tk.getTonkhoLo());
		cttk.setCttrakhoMalk(tk.getTonkhoMalienket());
		cttk.setCttrakhoNamhandung(tk.getTonkhoNamhandung());
		cttk.setCttrakhoNamnhap(tk.getTonkhoNamnhap());
		cttk.setCttrakhoNgaygiocn(new Date());
		cttk.setCttrakhoNgayhandung(tk.getTonkhoNgayhandung());
		cttk.setCttrakhoThanghandung(tk.getTonkhoThanghandung());
		cttk.setPhieutrakhoMa(phieuTrakho);
		cttk.setCttrakhoNgaygiocn(new Date());
		
		if (updateCaseMalk > -1){
			listCtTraKhoExt.set(updateCaseMalk, ctTraKhoExt);
		}	
	}
	
	private TonKho duplicateTk(TonKho tk) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException{
		if (tk != null) {
			TonKho temp = (TonKho) BeanUtils.cloneBean(tk);
			temp.setTonkhoMa(null);
			temp.setTonkhoNgaygiocn(new Date());
			return temp;
		}
		return null;
	}

	private void tinhTongTien(){
		Double tongtien = new Double(0);
		for (CtTraKhoExt obj : listCtTraKhoExt) {
			tongtien += obj.getThanhtien();
		}
		phieuTrakho.setPhieutrakhoThanhtien(tongtien);
	}
	
	public void delete(){
		log.info("delete");
		listCtTraKhoExt.remove(selectedCtTraKhoExt);
		tinhTongTien();
		ctTraKhoExt = new CtTraKhoExt();
		
		this.count = listCtTraKhoExt.size();
	}
	
	public void selected(){
		log.info("selected");
		ctTraKhoExt = selectedCtTraKhoExt;
		flagUpdateCtTrakho = true;
	}
	
	public void ghinhan(){	
		try{
			log.info("ghinhan");
			if (phieuTrakho.getPhieutrakhoMa() != null && !phieuTrakho.getPhieutrakhoMa().equals("") ){
				return;
			}		
			
			if (listCtTraKhoExt.size()>0) {
				List<CtTraKho> list = new ArrayList<CtTraKho>();
				List<TonKho> listTonKhoTra = new ArrayList<TonKho>();
				List<TonKho> listTonKhoNhan = new ArrayList<TonKho>();
				for (CtTraKhoExt obj : listCtTraKhoExt) {					
					list.add(obj.getCtTraKho());
					listTonKhoTra.add(obj.getTonKhoTra());
					listTonKhoNhan.add(obj.getTonKhoNhan());

					//luu log thong tin thuoc
					if(obj.getCtTraKho()!= null){
						listDataLog += "Ma LK:"+ obj.getCtTraKho().getCttrakhoMalk()+
								" Don gia: "+ obj.getCtTraKho().getCttrakhoDongia()+ " Don gia ban: "+ obj.getCtTraKho().getCttrakhoDongiaban() + 
								" So luong: "+ obj.getCtTraKho().getCttrakhoSoluong()+
								" So lo: "+ obj.getCtTraKho().getCttrakhoLo()+
								" Nam SX: " + obj.getCtTraKho().getCttrakhoNamnhap()+
								" Nam HD: " + obj.getCtTraKho().getCttrakhoNamhandung()+"\n";
						}
					}
				
				
				phieuTrakho.setPhieutrakhoNgaygiocn(new Date());
				if (!ngaytra.equals("")) {
					phieuTrakho.setPhieutrakhoNgay(formatter.parse(ngaytra));
				}
				phieuTrakho.setPhieutrakhoLoaiPhieu(loaiPhieu);
				DmKhoa dmkhoaTra = new DmKhoa();
				dmkhoaTra.setDmkhoaMaso(dmkhoaMaso);
				dmkhoaTra.setDmkhoaMa(dmkhoaMa);
				dmkhoaTra.setDmkhoaTen(dmkhoaTen);
				phieuTrakho.setDmkhoaTra(dmkhoaTra);
				RemoveUtil.removeIfNullForPhieuTraKho(phieuTrakho);
				
				PhieuTraKhoDelegate delegate = PhieuTraKhoDelegate.getInstance();
				String result = delegate.createPhieuTra(phieuTrakho, list, listTonKhoNhan, listTonKhoTra);
				if (result.equals("")){
					nosuccess = "true";
					FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THATBAI);
				}else if (result.equals("Soluongxuatkhonghople")){
					nosuccess = "true";
					FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_SLKHONGDUXUATTL);
				}else{
					phieuTrakho.setPhieutrakhoMa(result);
					SetInforUtil.setInforIfNullForPhieuTraKho(phieuTrakho);
					FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THANHCONG, result);
					displayPhieuXuatKho();

//					Luu log he thong
			         yteLog.setForm("B4167_TuyenDuoiTraHang");
			         yteLog.setUserId(identity == null ? "NULL" : identity.getUsername());
			         yteLog.setObjectId(phieuTrakho.getPhieutrakhoMa());
			         yteLog.setLogString("Ngay tra: "+ ngaytra + 
			        		 			" Loai thuoc: "+ phieuTrakho.getDmloaithuocMaso(true).getDmloaithuocMa()+
			        		 			" Loai phieu: "+ loaiPhieu+
			        		 			" Khoa tra: "+ dmkhoaMaso + 
			        		 			" Kho nhan: "+ dmKhoNhan+ 
			        		 			" Chuong trinh: "+ (nguonchuongtrinh == null ? "NULL" :nguonchuongtrinh.getDmnctMa())+
			        		 			" Nguon KP: "+ (nguonkinhphi == null ? "NULL":nguonkinhphi.getDmnguonkinhphiMa())+
			         					" Nguoi nhap: "+ phieuTrakho.getDtdmnhanvienCn(true).getDtdmnhanvienMa()+
			         					" Nguoi ky: "+ phieuTrakho.getDtdmnhanvienBacsi(true).getDtdmnhanvienMa()+
			         					" Kho nhan: "+ phieuTrakho.getDmkhoaNhan(true).getDmkhoaMa()+
			         					" Nguoi lap: "+ phieuTrakho.getDtdmnhanvienPhat(true).getDtdmnhanvienMa());
			         yteLog.setDateTime(new Date());
			         yteLog.setListData(listDataLog);

			         yteLogDele.create(yteLog);
					
				}
			}else{
				nosuccess = "true";
				FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_DMT_NULL);
			}
		}catch(Exception er){
			nosuccess = "true";
			System.out.println("Loi");
			return;
		}
	}
	
	public void nhapmoi() throws Exception{
		log.info("nhapmoi");
		resetValue();
	}
	
	public void displayPhieuXuatKho() throws Exception{
		log.info("displayPhieuXuatKho");
		listCtTraKhoExt.clear();
		ctTraKhoExt = new CtTraKhoExt();
		String maPhieuXuat = phieuTrakho.getPhieutrakhoMa();
		if (maPhieuXuat !=null && !maPhieuXuat.equals("")){
			PhieuTraKhoDelegate delegate = PhieuTraKhoDelegate.getInstance();			
			PhieuTraKho ptk_tmp = delegate.findByPhieutrakhoMa(maPhieuXuat);
			if (ptk_tmp != null){
				phieuTrakho = ptk_tmp;
				SetInforUtil.setInforIfNullForPhieuTraKho(phieuTrakho);
				dmLoaiTen = phieuTrakho.getDmloaithuocMaso(true).getDmloaithuocTen();
				loaiPhieu = phieuTrakho.getPhieutrakhoLoaiPhieu();
				dmkhoaTen = phieuTrakho.getDmkhoaTra().getDmkhoaTen();
				dmkhoaMa = phieuTrakho.getDmkhoaTra().getDmkhoaMa();
				dmkhoaMaso = phieuTrakho.getDmkhoaTra().getDmkhoaMaso();
				ngaytra = formatter.format(phieuTrakho.getPhieutrakhoNgay());
				CtTraKhoDelegate delegateCt = CtTraKhoDelegate.getInstance();
				for (CtTraKho obj : delegateCt.findByphieutrakhoMa(phieuTrakho.getPhieutrakhoMa())) {
					CtTraKhoExt ct = new CtTraKhoExt();
					ct.setCtTraKho(obj);
					ct.setThanhtien(obj.getCttrakhoSoluong() * obj.getCttrakhoDongia());
					listCtTraKhoExt.add(ct);
				}
			}else{
				nofound = "true";
			}
			noinphieu = "false";
		}
	}
	public String thuchienAction(){
		XuatReport();
		return "B4160_Chonmenuxuattaptin";
	}
	int index= 0;
	/**
	 * xuat report 
	 */
	
	@Out(required=false)
	@In(required=false)
	private String reportPathKC=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeKC=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintKC = null;
	
	public void XuatReport() {
		reportTypeKC="D03_phieutrakhoTD";
		log.info("Vao Method XuatReport D03_phieutrakhoTD");
		String baocao1=null;
		Date currentDate = new Date();

		if (!phieuTrakho.getPhieutrakhoMa().equals("")) {
			
		try {
			log.info("bat dau method XuatReport ");
			String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/phieutrakho_01.jrxml";
			log.info("da thay file templete 5" + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			
			PhieuTraKhoDelegate pxkWS = PhieuTraKhoDelegate.getInstance();
			PhieuTraKho pt = pxkWS.find(phieuTrakho.getPhieutrakhoMa());
			
			params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			log.info(String.format("-----tenSo: %s", params.get("tenSo")));
			params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			log.info(String.format("-----tenDonVi: %s", params.get("tenDonVi")));
			params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			log.info(String.format("-----dienThoaiDonVi: %s", params.get("dienThoaiDonVi")));
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			Calendar cal = Calendar.getInstance();
			cal.setTime(pt.getPhieutrakhoNgay());
			if (cal != null) {
				log.info(String.format("-----ngay lap: %s", cal.getTime()));
				params.put("ngayHt", "" + cal.get(Calendar.DAY_OF_MONTH));
				params.put("thangHt", "" + (cal.get(Calendar.MONTH) + 1));
				params.put("namHt", "" + cal.get(Calendar.YEAR));
			} else {
				log.info("-----ngay lap is null");
				params.put("ngayHt", "");
				params.put("thangHt", "");
				params.put("namHt", "");
			}

			SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
			String ngayGioHt = df.format(Calendar.getInstance().getTime());
			log.info(String.format("-----ngay gio hien tai: %s", ngayGioHt));
			params.put("gioHt", ngayGioHt);

			params.put("pxMa", pt.getPhieutrakhoMa());

			if (pt.getDmkhoaNhan() != null) {
				params.put("khoaNhan", pt.getDmkhoaNhan().getDmkhoaTen());
			} else {
				params.put("khoaNhan", "");
			}
			log.info(String.format("-----khoaNhan: %s", params.get("khoaNhan")));

			if (pt.getDmkhoaTra() != null) {
				params.put("khoaXuat", pt.getDmkhoaTra().getDmkhoaTen());
			} else {
				params.put("khoaXuat", "");
			}
			log.info(String.format("-----khoaXuat: %s", params.get("khoaXuat")));

			params.put("tongTien", pt.getPhieutrakhoThanhtien());
			log.info(String.format("-----tongTien: %s", params.get("tongTien")));
			params.put("loaiMa", pt.getDmloaithuocMaso().getDmloaithuocMa());
			params.put("nhanvienCn", pt.getDtdmnhanvienCn().getDtdmnhanvienMa());
			
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintKC,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"duocpham/", "pdf","D03_phieuxuatkho_khoaphong");
			    reportPathKC=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathKC);
			    index+=1;
			    log.info("Index :" + index);
			    if(conn != null) conn.close();
		}catch (Exception e) {
			log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
		}
		log.info("Thoat Method XuatReport");
	}
	
	public String troVe(){
		try {
			log.info("***** troVe()");
			return "B4123_Phieuxuathangkhoaphong";
		} 		
		catch (Exception e) {
			log.info("***** End exception = " + e);    	
		} 
		return null;
	}
	
	public CtTraKhoExt getSelectedCtTraKhoExt() {
		return selectedCtTraKhoExt;
	}
	
	public void setSelectedCtTraKhoExt(CtTraKhoExt selectedCtTraKhoExt) {
		this.selectedCtTraKhoExt = selectedCtTraKhoExt;
	}

	public CtTraKhoExt getCtTraKhoExt() {
		return ctTraKhoExt;
	}
	
	public void setCtTraKhoExt(CtTraKhoExt CtTraKhoExt) {
		this.ctTraKhoExt = CtTraKhoExt;
	}
	
	public String getNgaytra() {
		return ngaytra;
	}
	
	public void setNgaytra(String ngaytra) {
		this.ngaytra = ngaytra;
	}
	
	public PhieuTraKho getPhieuTrakho() {
		return phieuTrakho;
	}
	
	public void setPhieuTrakho(PhieuTraKho phieuTrakho) {
		this.phieuTrakho = phieuTrakho;
	}
	
	public DmPhanLoaiThuoc getPlthuoc() {
		return plthuoc;
	}
	
	public void setPlthuoc(DmPhanLoaiThuoc plthuoc) {
		this.plthuoc = plthuoc;
	}
	
	public DmNguonKinhPhi getNguonkinhphi() {
		return nguonkinhphi;
	}
	
	public void setNguonkinhphi(DmNguonKinhPhi nguonkinhphi) {
		this.nguonkinhphi = nguonkinhphi;
	}
	
	public DmNguonChuongTrinh getNguonchuongtrinh() {
		return nguonchuongtrinh;
	}
	
	public void setNguonchuongtrinh(DmNguonChuongTrinh nguonchuongtrinh) {
		this.nguonchuongtrinh = nguonchuongtrinh;
	}	

	public List<CtTraKhoExt> getListCtTraKhoExt() {
		return listCtTraKhoExt;
	}
	public void setListCtTraKhoExt(List<CtTraKhoExt> listCtTraKhoExt) {
		this.listCtTraKhoExt = listCtTraKhoExt;
	}

	public String getLoaiFileXuat() {
		return loaiFileXuat;
	}

	public void setLoaiFileXuat(String loaiFileXuat) {
		this.loaiFileXuat = loaiFileXuat;
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

	public String getIsReport() {
		return isReport;
	}

	public void setIsReport(String isReport) {
		this.isReport = isReport;
	}

	public String getNofound() {
		return nofound;
	}

	public void setNofound(String nofound) {
		this.nofound = nofound;
	}

	public String getNoinphieu() {
		return noinphieu;
	}

	public void setNoinphieu(String noinphieu) {
		this.noinphieu = noinphieu;
	}	

	public SimpleDateFormat getFormatter() {
		return formatter;
	}

	public void setFormatter(SimpleDateFormat formatter) {
		this.formatter = formatter;
	}

	public boolean isFlagUpdateCtTrakho() {
		return flagUpdateCtTrakho;
	}

	public void setFlagUpdateCtTrakho(boolean flagUpdateCtTrakho) {
		this.flagUpdateCtTrakho = flagUpdateCtTrakho;
	}

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	public String getTenChuongTrinh() {
		return tenChuongTrinh;
	}

	public void setTenChuongTrinh(String tenChuongTrinh) {
		this.tenChuongTrinh = tenChuongTrinh;
	}

	public String getDmKhoNhan() {
		return dmKhoNhan;
	}

	public void setDmKhoNhan(String dmKhoNhan) {
		this.dmKhoNhan = dmKhoNhan;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}	
	
	public String getDmLoaiPhieuMa() {
		return dmLoaiPhieuMa;
	}
	
	public void setDmLoaiPhieuMa(String dmLoaiPhieuMa) {
		this.dmLoaiPhieuMa = dmLoaiPhieuMa;
	}
	
	public class CtTraKhoExt implements Serializable{
		private static final long serialVersionUID = 5212617651932213622L;
		private CtTraKho ctTraKho;
		private TonKho tonKho;
		private Double thanhtien;
		private TonKho tonKhoTra;
		private TonKho tonKhoNhan;

		public CtTraKhoExt(){
			ctTraKho = new CtTraKho();
			tonKho = new TonKho();
			SetInforUtil.setInforIfNullForTonKho(tonKho);
			if (tonKho.getDmthuocMaso().getDmdonvitinhMaso() == null) {
				tonKho.getDmthuocMaso().setDmdonvitinhMaso(new DmDonViTinh());
			}
			thanhtien = new Double(0);
		}
		
		public Double getThanhtien() {
			return thanhtien;
		}

		public void setThanhtien(Double thanhtien) {
			this.thanhtien = thanhtien;
		}

		public CtTraKho getCtTraKho() {
			return ctTraKho;
		}

		public void setCtTraKho(CtTraKho ctTraKho) {
			this.ctTraKho = ctTraKho;
		}

		public TonKho getTonKho() {
			return tonKho;
		}

		public void setTonKho(TonKho tonKho) {
			this.tonKho = tonKho;
		}
		
		public TonKho getTonKhoTra() {
			return tonKhoTra;
		}

		public void setTonKhoTra(TonKho tonKhoTra) {
			this.tonKhoTra = tonKhoTra;
		}

		public TonKho getTonKhoNhan() {
			return tonKhoNhan;
		}

		public void setTonKhoNhan(TonKho tonKhoNhan) {
			this.tonKhoNhan = tonKhoNhan;
		}
	}
}

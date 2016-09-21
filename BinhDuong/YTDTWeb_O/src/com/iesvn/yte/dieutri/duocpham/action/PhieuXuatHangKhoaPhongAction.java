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
import com.iesvn.yte.dieutri.delegate.CtXuatKhoDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DmLoaiPhieuDelegate;
import com.iesvn.yte.dieutri.delegate.DmLoaiThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DmPhanLoaiThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DmPhanNhomThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DmThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.PhieuXuatKhoDelegate;
import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
import com.iesvn.yte.dieutri.delegate.YteLogDelegate;
import com.iesvn.yte.dieutri.entity.CtXuatKho;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.PhieuXuatKho;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.dieutri.entity.YteLog;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
import com.iesvn.yte.entity.DmDonViTinh;
import com.iesvn.yte.entity.DmLoaiPhieu;
import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.entity.DmNguonChuongTrinh;
import com.iesvn.yte.entity.DmNguonKinhPhi;
import com.iesvn.yte.entity.DmPhanLoaiThuoc;
import com.iesvn.yte.entity.DmPhanNhomThuoc;
import com.iesvn.yte.entity.DmThuoc;
import com.iesvn.yte.util.IConstantsRes;
import com.sun.org.apache.commons.beanutils.BeanUtils;

@Scope(CONVERSATION)
@Name("B4123_Phieuxuathangkhoaphong")
@Synchronized(timeout = 6000000)
/***
 *		Form nay xuat thang(tru thang tu kho, khong thong qua phieu du tru)
 * */
public class PhieuXuatHangKhoaPhongAction implements Serializable {

	private static final long serialVersionUID = 4366573502725366923L;
	private static Logger log = Logger.getLogger(PhieuXuatHangKhoaPhongAction.class);
	private SimpleDateFormat formatter;
	private PhieuXuatKho pxuatkho;
	private String ngayxuat;
	private DmPhanLoaiThuoc plthuoc;
	private DmNguonKinhPhi nguonkinhphi;
	private DmNguonChuongTrinh nguonchuongtrinh;
	@DataModel
	private List<CtXuatKhoExt> listCtXuatKhoExt;	
	@DataModelSelection
	private CtXuatKhoExt selectedCtXuatKhoExt;
	private CtXuatKhoExt ctXuatKhoExt;
	private boolean flagUpdateCtxuatkho = false;
	
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
	
	 private YteLogDelegate yteLogDele = YteLogDelegate.getInstance();
     private YteLog yteLog;
     private String listDataLog;


	@In(required = false)
	@Out(required = false)
	private String tenChuongTrinh;
	
	String dmKhoXuat = "";
	
	public String getNosuccess() {
		return nosuccess;
	}

	public void setNosuccess(String nosuccess) {
		this.nosuccess = nosuccess;
	}
	
	private String thanhly ;
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

	//	@Factory("listCtXuatKhoExt")
//	public void createListCtXuatKhoExt(){
//		listCtXuatKhoExt = new ArrayList<CtXuatKhoExt>();
//	}
	@Restrict("#{s:hasRole('NV_KhoaDuoc') or s:hasRole('QT_HT_Duoc')}")
	@Begin (join = true)
	public String init(String kho, String thanhly) { // kho = KC, KL , BHYT ; thanhly = TL or ''
		log.info("***** init() *****");
		resetValue();
		
		dmKhoXuat = kho;
		log.info("***** Kho:? "+kho);
		log.info("***** thanh ly:? :"+thanhly);
		this.thanhly = thanhly;
		if ("NT".equals(kho)){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoNoiTru;
		}else if ("BHYT".equals(kho)){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoBHYT;
		}else if ("KC".equals(kho)){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoChinh;
		}else if ("TE".equals(kho)){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoTE;
		}
		listDmLoaiThuocs.clear();
		listDmLoaiPhieus.clear();
		listDmThuocs.clear();
		hmLoaiThuoc.clear();
		hmDmThuoc.clear();
		refreshDmLoaiThuoc();
		log.info("***** ten chuong trinh: "+tenChuongTrinh);
		log.info("***** end init() *****");
		
		return "QuanLyKhoChinh_XuatKhoChinh_XuatHangDenTuTrucThanhLy";
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
		if(ctXuatKhoExt != null && ctXuatKhoExt.tonKho != null && ctXuatKhoExt.tonKho.getDmthuocMaso() != null){
			String maThuoc = ctXuatKhoExt.tonKho.getDmthuocMaso().getDmthuocMa();
			DmThuoc dmThuoc = hmDmThuoc.get(maThuoc.toUpperCase());
			if(dmThuoc != null) {
				ctXuatKhoExt.tonKho.setDmthuocMaso(dmThuoc);
				log.info("-----DA THAY DMTHUOC-----");
			}
			else {
				ctXuatKhoExt.tonKho.setDmthuocMaso(new DmThuoc());
				ctXuatKhoExt.tonKho.setDmthuocMaso(null);
			}
		}
		log.info("-----END onblurMaThuocAction()-----");		
	}
	//Tho edit again
	public void onblurTenThuocAction(){
		log.info("-----BEGIN onblurTenThuocAction()-----");
		if(ctXuatKhoExt != null && ctXuatKhoExt.tonKho != null && ctXuatKhoExt.tonKho.getDmthuocMaso() != null){
			String tenThuoc = ctXuatKhoExt.tonKho.getDmthuocMaso().getDmthuocTen();
			Boolean hasTenThuoc = false;
			String maThuoc = null;
//			DmThuoc dmThuoc = dmThuocDelegate.findByTenThuoc(tenThuoc);
//			if(dmThuoc != null) {
//				ctXuatKhoExt.tonKho.setDmthuocMaso(dmThuoc);
//				log.info("-----DA THAY DMTHUOC-----");
//			}else {
//				ctXuatKhoExt.tonKho.setDmthuocMaso(null);
//			}			
			
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
		    	ctXuatKhoExt.tonKho.setDmthuocMaso(dmThuoc);
				log.info("-----DA THAY DMTHUOC-----");
		    }else{
		    	ctXuatKhoExt.tonKho.setDmthuocMaso(null);
		    }
		}
		log.info("-----END onblurTenThuocAction()-----");		
	}
	
	public void refreshDmThuoc(){
		listDmThuocs.clear();
		hmDmThuoc.clear();
		dmThuocDelegate = DmThuocDelegate.getInstance();
		
		String loaiMa = "";
		if(loaiPhieu != null || !loaiPhieu.equals(""))
		{
			if (pxuatkho.getDmloaithuocMaso(true).getDmloaithuocMa() != null ){
				if( pxuatkho.getDmloaithuocMaso(true).getDmloaithuocMa().toUpperCase().equals("TD"))
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
					loaiMa = pxuatkho.getDmloaithuocMaso(true).getDmloaithuocMa();
				}	
			}	
		}
		System.out.println("loaiMa: "+loaiMa);
		System.out.println("Kho: "+pxuatkho.getDmkhoaXuat().getDmkhoaMaso());
		
		List<DmThuoc> lstDmThuoc = new ArrayList<DmThuoc>();
		if(!loaiMa.equals("") && !loaiPhieu.equals("")){
			lstDmThuoc = dmThuocDelegate.findByLoaiPhanLoaiThuocNhomThuocDVTKho(loaiMa, "", pxuatkho.getDmkhoaXuat().getDmkhoaMaso());
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
		log.info("-----BEGIN onblurMaLoaiAction()-----"+pxuatkho.getDmloaithuocMaso().getDmloaithuocMa());
		String loaihang_ma = pxuatkho.getDmloaithuocMaso().getDmloaithuocMa();
		DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)hmLoaiThuoc.get(loaihang_ma.toUpperCase());
		if(dmLoaiThuoc != null) {
			setDmLoaiTen(dmLoaiThuoc.getDmloaithuocTen());
		}
		else {
			setDmLoaiTen("");			
		}
		listDmLoaiPhieus.clear();
		refreshDmLoaiPhieu();
	    setLoaiPhieu("");
	    setDmLoaiPhieuMa("");
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
			pxuatkho.setDmloaithuocMaso(dmLoaiThuoc_Find);
		}
	    listDmLoaiPhieus.clear();
		refreshDmLoaiPhieu();
	    setLoaiPhieu("");
	    setDmLoaiPhieuMa("");
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
		if(pxuatkho != null && pxuatkho.getDmloaithuocMaso() != null){
			List<DmLoaiPhieu> lstDmLoaiPhieus = dmLoaiPhieuDelegate.findAll();
			if(lstDmLoaiPhieus !=null && lstDmLoaiPhieus.size()>0)
			{
				for(DmLoaiPhieu o: lstDmLoaiPhieus){
					hmDmLoaiPhieu.put(o.getDmloaiphieuMa(), o);
					if(pxuatkho.getDmloaithuocMaso().getDmloaithuocMaso().equals(o.getDmloaithuocMaso().getDmloaithuocMaso())){
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
	
	public String getThanhly() {
		return thanhly;
	}

	public void setThanhly(String thanhly) {
		this.thanhly = thanhly;
	}

	public void resetValue(){
		log.info("---resetValue");
		pxuatkho = new PhieuXuatKho();
		SetInforUtil.setInforIfNullForPhieuXuatKho(pxuatkho);
		String format_date = "dd/MM/yyyy";
		formatter = new SimpleDateFormat(format_date);
		Calendar cal = Calendar.getInstance();
		ngayxuat = formatter.format(cal.getTime());
		ctXuatKhoExt = new CtXuatKhoExt();
		plthuoc = new DmPhanLoaiThuoc();
		nguonkinhphi = new DmNguonKinhPhi();
		nguonchuongtrinh = new DmNguonChuongTrinh();
		DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
		DtDmNhanVien nv = nvDelegate.findByNd(identity.getUsername());
		if (nv != null) {
			pxuatkho.setDtdmnhanvienCn(nv);
		}
		flagUpdateCtxuatkho = false;
		listCtXuatKhoExt = new ArrayList<CtXuatKhoExt>();
		resultReportFileName = "";
		resultReportName = "";	
		loaiFileXuat = "";
		nofound = "false";
		nosuccess = "false";
		noinphieu = "false";
		//loai thuoc
		loaiPhieu ="";
		dmLoaiTen ="";
		listDmLoaiThuocs.clear();
		hmLoaiThuoc.clear();
		refreshDmLoaiThuoc();
		listDmLoaiPhieus.clear();
		//Thuoc
		listDmThuocs.clear();
		hmDmThuoc.clear();
		refreshDmThuoc();
		hmDmLoaiPhieu.clear();
		dmLoaiPhieuMa ="";
	}
	
	public void enter() throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException{
		log.info("enter");	
		updateTonKho(ctXuatKhoExt);
		if (flagUpdateCtxuatkho){
			flagUpdateCtxuatkho = false;
		}else{
			listCtXuatKhoExt.add(ctXuatKhoExt);
		}
		tinhTongTien();
		ctXuatKhoExt = new CtXuatKhoExt();
		this.count = listCtXuatKhoExt.size();
	}
	
	private void updateTonKho(CtXuatKhoExt ctXuatKhoExt) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
		Integer matonkho = ctXuatKhoExt.getTonKho().getTonkhoMa();
		if (matonkho==null) return;
		TonKhoDelegate tkDelegate = TonKhoDelegate.getInstance();
		TonKho tk = tkDelegate.find(matonkho);
		
		
		int updateCaseMalk = -1;
		
		CtXuatKho ctxk = ctXuatKhoExt.getCtXuatKho();
		
		/*--------------------*/
		
		String malk = ctXuatKhoExt.getTonKho().getTonkhoMalienket();
		log.info("malk:"+malk);
		Double slXuat = new Double("0");
		for (int i = 0; i < listCtXuatKhoExt.size(); i++) {
			CtXuatKho ctxk_temp = listCtXuatKhoExt.get(i).getCtXuatKho();
			if (malk.equals(ctxk_temp.getCtxuatkhoMalk())) {
				log.info("-----malk " + malk);
				slXuat += ctxk_temp.getCtxuatkhoSoluong();
				flagUpdateCtxuatkho = true;
				log.info("flagUpdateCtxuatkho = true");
				updateCaseMalk =  i;
			}
		}
		Double xuat = ctXuatKhoExt.getCtXuatKho().getCtxuatkhoSoluong();
		slXuat += Double.valueOf(xuat);
		ctxk.setCtxuatkhoSoluong(slXuat);
		log.info("slXuat:"+slXuat);
		
		/*--------------------*/
		
		
		
		TonKho tkXuat = duplicateTk(tk);
		if (tkXuat != null) {
			tkXuat.setTonkhoXuat(ctxk.getCtxuatkhoSoluong());
			tkXuat.setTonkhoNhap(null);
			tkXuat.setTonkhoTra(null);
		}
		
		TonKho tkNhan = duplicateTk(tk);
		if (tkNhan != null) {
			tkNhan.setTonkhoNhap(ctxk.getCtxuatkhoSoluong());
			tkNhan.setTonkhoXuat(null);
			tkNhan.setTonkhoTra(null);
		}
		
		
		ctXuatKhoExt.setTonKhoXuat(tkXuat);
		ctXuatKhoExt.setTonKhoNhan(tkNhan);
		ctxk.setDmnguonkinhphiMaso(tk.getDmnguonkinhphiMaso());
		ctxk.setDmquocgiaMaso(tk.getDmquocgiaMaso());
		ctxk.setDmthuocMaso(tk.getDmthuocMaso());
		ctxk.setDmnhasanxuatMaso(tk.getDmnhasanxuatMaso());
		ctxk.setDmnctMaso(tk.getDmnctMaso());
		ctxk.setCtxuatkhoDongia(tk.getTonkhoDongia());
		ctxk.setCtxuatkhoDongiaban(tk.getTonkhoDongiaban());
		ctxk.setCtxuatkhoLo(tk.getTonkhoLo());
		ctxk.setCtxuatkhoMalk(tk.getTonkhoMalienket());
		ctxk.setCtxuatkhoNamhandung(tk.getTonkhoNamhandung());
		ctxk.setCtxuatkhoNamnhap(tk.getTonkhoNamnhap());
		ctxk.setCtxuatkhoNgaygiocn(new Date());
		ctxk.setCtxuatkhoNgayhandung(tk.getTonkhoNgayhandung());
		ctxk.setCtxuatkhoThanghandung(tk.getTonkhoThanghandung());
		ctxk.setPhieuxuatkhoMa(pxuatkho);
		ctxk.setCtxuatkhoNgaygiocn(new Date());
		
		if (updateCaseMalk > -1){
			listCtXuatKhoExt.set(updateCaseMalk, ctXuatKhoExt);
		}
		
		
	}
	
	private TonKho duplicateTk(TonKho tk) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException{
		log.debug(String.format("-----duplicateTk()-----"));
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
		for (CtXuatKhoExt obj : listCtXuatKhoExt) {
			tongtien += obj.getThanhtien();
		}
		pxuatkho.setPhieuxuatkhoThanhtien(tongtien);
	}
	
	public void delete(){
		log.info("delete");
		listCtXuatKhoExt.remove(selectedCtXuatKhoExt);
		tinhTongTien();
		ctXuatKhoExt = new CtXuatKhoExt();
		
		this.count = listCtXuatKhoExt.size();
	}
	
	public void selected(){
		log.info("selected");
		ctXuatKhoExt = selectedCtXuatKhoExt;
		flagUpdateCtxuatkho = true;
	}
	
	public void ghinhan() throws Exception{
		
		if (pxuatkho.getPhieuxuatkhoMa() != null && !pxuatkho.getPhieuxuatkhoMa().equals("") ){
			return;
		}		
		
		log.info("ghinhan");
		if (listCtXuatKhoExt.size()>0) {
			yteLog = new YteLog();
			listDataLog="";
			List<CtXuatKho> list = new ArrayList<CtXuatKho>();
			List<TonKho> listTonKhoXuat = new ArrayList<TonKho>();
			List<TonKho> listTonKhoNhan = new ArrayList<TonKho>();
			for (CtXuatKhoExt obj : listCtXuatKhoExt) {

				list.add(obj.getCtXuatKho());
				listTonKhoXuat.add(obj.getTonKhoXuat());
				listTonKhoNhan.add(obj.getTonKhoNhan());
				//luu log thong tin thuoc
				if(obj.getCtXuatKho()!= null){
					listDataLog += "Ma LK:"+ (obj.getCtXuatKho().getCtxuatkhoMalk()) +
							" Don gia: "+ obj.getCtXuatKho().getCtxuatkhoDongia()+ " Don gia ban: "+ obj.getCtXuatKho().getCtxuatkhoDongiaban() + 
							" So luong nhap: "+ (obj.getTonKhoNhan() == null ? "NULL" : obj.getTonKhoNhan().getTonkhoNhap())+
							" So luong nhap: "+ (obj.getTonKhoXuat() == null ? "NULL" : obj.getTonKhoXuat().getTonkhoXuat())+
							" So lo: "+ obj.getCtXuatKho().getCtxuatkhoLo()+
							" Nam SX: " +  obj.getCtXuatKho().getCtxuatkhoNamnhap()+
							" Nam HD: " +  obj.getCtXuatKho().getCtxuatkhoNamhandung()+ "\n";
				}
				
			}
			//pxuatkho.setPhieuxuatkhoThanhtien(ctXuatKhoExt.getThanhtien());
			pxuatkho.setPhieuxuatkhoNgaygiocn(new Date());
			if (!ngayxuat.equals("")) {
				pxuatkho.setPhieuxuatkhoNgaylap(formatter.parse(ngayxuat));
			}
			pxuatkho.setPhieuxuatkhoLoaiPhieu(loaiPhieu);
			RemoveUtil.removeIfNullForPhieuXuatKho(pxuatkho);
			PhieuXuatKhoDelegate delegate = PhieuXuatKhoDelegate.getInstance();
			String result = delegate.createPhieuXuat(pxuatkho, list, listTonKhoNhan, listTonKhoXuat);
			if (result.equals("")){
				nosuccess = "true";
				FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THATBAI);
			}else if (result.equals("Soluongxuatkhonghople")){
				nosuccess = "true";
				FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_SLKHONGDUXUATTL);
			}else{
				pxuatkho.setPhieuxuatkhoMa(result);
				SetInforUtil.setInforIfNullForPhieuXuatKho(pxuatkho);
				FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THANHCONG, result);
				displayPhieuXuatKho();
				
//				Luu log he thong
		         yteLog.setForm("B4123_Phieuxuathangkhoaphong");
		         yteLog.setUserId(identity == null ? "NULL" : identity.getUsername());
		         yteLog.setObjectId(pxuatkho.getPhieuxuatkhoMa());
		         yteLog.setLogString(" Ngay xuat: "+ ngayxuat+
		         					" Loai thuoc: " + pxuatkho.getDmloaithuocMaso(true).getDmloaithuocMa()+
		         					" Loai phieu: "+loaiPhieu+
		         					" Khoa nhan: "+ pxuatkho.getDmkhoaNhan(true).getDmkhoaMa() +
		         					" Khoa tra: "+ pxuatkho.getDmkhoaXuat(true).getDmkhoaMa()+
		         					" Chuong trinh: "+ (nguonchuongtrinh == null ? "NULL" : nguonchuongtrinh.getDmnctMaso())+
		         					" Nguon KP: "+ (nguonkinhphi == null ? "NULL"  : nguonkinhphi.getDmnguonkinhphiMa())+
		         					" Nguoi nhap: "+  pxuatkho.getDtdmnhanvienCn(true).getDtdmnhanvienMa()+
		         					" Nguoi lap: "+(pxuatkho.getDtdmnhanvienNhan(true).getDtdmnhanvienMa())+
		         					" Nguoi ky: "+(pxuatkho.getDtdmnhanvienBacsi(true).getDtdmnhanvienMa())+
		         					" Thanh tien: "+ pxuatkho.getPhieuxuatkhoThanhtien());
		         yteLog.setDateTime(new Date());
		         yteLog.setListData(listDataLog);
		         yteLogDele.create(yteLog);
			}
			
			noinphieu = "true";
		}else{
			nosuccess = "true";
			FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_DMT_NULL);
		}
	}
	
	public void nhapmoi() throws Exception{
		log.info("nhapmoi");
		resetValue();
	}
	
	public void displayPhieuXuatKho() throws Exception{
		log.info("displayPhieuXuatKho");
		ctXuatKhoExt = new CtXuatKhoExt();
		listCtXuatKhoExt.clear();
		String maPhieuXuat = pxuatkho.getPhieuxuatkhoMa();
		if (maPhieuXuat !=null && !maPhieuXuat.equals("")){
			PhieuXuatKhoDelegate delegate = PhieuXuatKhoDelegate.getInstance();			
			PhieuXuatKho pxk_tmp = delegate.findByPhieuxuatkhoMa(maPhieuXuat);
			if (pxk_tmp != null){
				pxuatkho = pxk_tmp;
				SetInforUtil.setInforIfNullForPhieuXuatKho(pxuatkho);
				dmLoaiTen = pxuatkho.getDmloaithuocMaso(true).getDmloaithuocTen();
				loaiPhieu = pxuatkho.getPhieuxuatkhoLoaiPhieu();
				ngayxuat = formatter.format(pxuatkho.getPhieuxuatkhoNgaylap());
				CtXuatKhoDelegate delegateCt = CtXuatKhoDelegate.getInstance();
				for (CtXuatKho obj : delegateCt.findByphieuxuatkhoMa(pxuatkho.getPhieuxuatkhoMa())) {
					CtXuatKhoExt ct = new CtXuatKhoExt();
					ct.setCtXuatKho(obj);
					ct.setThanhtien(obj.getCtxuatkhoSoluong()*obj.getCtxuatkhoDongia());
					listCtXuatKhoExt.add(ct);
				}
			}else{
				nofound = "true";
				noinphieu ="true";
			}
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
		reportTypeKC="D03_phieuxuatkho_khoaphong";
		log.info("Vao Method XuatReport D03_phieuxuatkho");
		String baocao1=null;
		Date currentDate = new Date();

		if (!pxuatkho.getPhieuxuatkhoMa().equals("")) {
			
		try {
			log.info("bat dau method XuatReport ");
			String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/phieuxuatkho_01.jrxml";
			log.info("da thay file templete 5" + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			
			PhieuXuatKhoDelegate pxkWS = PhieuXuatKhoDelegate.getInstance();
			PhieuXuatKho px = pxkWS.find(pxuatkho.getPhieuxuatkhoMa());
			
			params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			log.info(String.format("-----tenSo: %s", params.get("tenSo")));
			params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			log.info(String.format("-----tenDonVi: %s", params.get("tenDonVi")));
			params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			log.info(String.format("-----dienThoaiDonVi: %s", params.get("dienThoaiDonVi")));
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			Calendar cal = Calendar.getInstance();
			cal.setTime(px.getPhieuxuatkhoNgaylap());
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

			params.put("pxMa", px.getPhieuxuatkhoMa());

			if (px.getDmkhoaNhan() != null) {
				params.put("khoaNhan", px.getDmkhoaNhan().getDmkhoaTen());
			} else {
				params.put("khoaNhan", "");
			}
			log.info(String.format("-----khoaNhan: %s", params.get("khoaNhan")));

			if (px.getDmkhoaXuat() != null) {
				params.put("khoaXuat", px.getDmkhoaXuat().getDmkhoaTen());
			} else {
				params.put("khoaXuat", "");
			}
			log.info(String.format("-----khoaXuat: %s", params.get("khoaXuat")));

			params.put("tongTien", px.getPhieuxuatkhoThanhtien());
			log.info(String.format("-----tongTien: %s", params.get("tongTien")));
			params.put("loaiMa", px.getDmloaithuocMaso().getDmloaithuocMa());
			params.put("nhanvienCn", px.getDtdmnhanvienCn().getDtdmnhanvienMa());
			
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
	
	public CtXuatKhoExt getSelectedCtXuatKhoExt() {
		return selectedCtXuatKhoExt;
	}
	
	public void setSelectedCtXuatKhoExt(CtXuatKhoExt selectedCtXuatKhoExt) {
		this.selectedCtXuatKhoExt = selectedCtXuatKhoExt;
	}

	public CtXuatKhoExt getCtXuatKhoExt() {
		return ctXuatKhoExt;
	}
	
	public void setCtXuatKhoExt(CtXuatKhoExt ctXuatKhoExt) {
		this.ctXuatKhoExt = ctXuatKhoExt;
	}
	
	public String getNgayxuat() {
		return ngayxuat;
	}
	
	public void setNgayxuat(String ngayxuat) {
		this.ngayxuat = ngayxuat;
	}
	
	public PhieuXuatKho getPxuatkho() {
		return pxuatkho;
	}
	
	public void setPxuatkho(PhieuXuatKho pxuatkho) {
		this.pxuatkho = pxuatkho;
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
	
	public class CtXuatKhoExt implements Serializable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 5212617651932213622L;
		private CtXuatKho ctXuatKho;
		private TonKho tonKho;
		private Double thanhtien;
		private TonKho tonKhoXuat;
		private TonKho tonKhoNhan;

		public CtXuatKhoExt(){
			ctXuatKho = new CtXuatKho();
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

		public CtXuatKho getCtXuatKho() {
			return ctXuatKho;
		}

		public void setCtXuatKho(CtXuatKho ctXuatKho) {
			this.ctXuatKho = ctXuatKho;
		}

		public TonKho getTonKho() {
			return tonKho;
		}

		public void setTonKho(TonKho tonKho) {
			this.tonKho = tonKho;
		}
		
		public TonKho getTonKhoXuat() {
			return tonKhoXuat;
		}

		public void setTonKhoXuat(TonKho tonKhoXuat) {
			this.tonKhoXuat = tonKhoXuat;
		}

		public TonKho getTonKhoNhan() {
			return tonKhoNhan;
		}

		public void setTonKhoNhan(TonKho tonKhoNhan) {
			this.tonKhoNhan = tonKhoNhan;
		}
	}

	public List<CtXuatKhoExt> getListCtXuatKhoExt() {
		return listCtXuatKhoExt;
	}
	public void setListCtXuatKhoExt(List<CtXuatKhoExt> listCtXuatKhoExt) {
		this.listCtXuatKhoExt = listCtXuatKhoExt;
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

	public boolean isFlagUpdateCtxuatkho() {
		return flagUpdateCtxuatkho;
	}

	public void setFlagUpdateCtxuatkho(boolean flagUpdateCtxuatkho) {
		this.flagUpdateCtxuatkho = flagUpdateCtxuatkho;
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

	public String getDmKhoXuat() {
		return dmKhoXuat;
	}

	public void setDmKhoXuat(String dmKhoXuat) {
		this.dmKhoXuat = dmKhoXuat;
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
}

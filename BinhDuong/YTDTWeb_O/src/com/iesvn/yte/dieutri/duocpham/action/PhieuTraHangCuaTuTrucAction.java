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
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
import com.iesvn.yte.dieutri.delegate.CtPhieuDtDelegate;
import com.iesvn.yte.dieutri.delegate.CtTraKhoDelegate;
import com.iesvn.yte.dieutri.delegate.CtXuatKhoDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DmLoaiPhieuDelegate;
import com.iesvn.yte.dieutri.delegate.DmLoaiThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DmPhanNhomThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.KiemKeDelegate;
import com.iesvn.yte.dieutri.delegate.PhieuTraKhoDelegate;
import com.iesvn.yte.dieutri.delegate.PhieuXuatKhoDelegate;
import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
import com.iesvn.yte.dieutri.delegate.YteLogDelegate;
import com.iesvn.yte.dieutri.entity.CtPhieuDt;
import com.iesvn.yte.dieutri.entity.CtTraKho;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.PhieuDuTru;
import com.iesvn.yte.dieutri.entity.PhieuTraKho;
import com.iesvn.yte.dieutri.entity.PhieuXuatKho;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.dieutri.entity.YteLog;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmLoaiPhieu;
import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.entity.DmPhanNhomThuoc;
import com.iesvn.yte.entity.DmThuoc;
import com.iesvn.yte.util.IConstantsRes;

@Scope(CONVERSATION)
@Name("B4165_Phieutrahangtutruc")
@Synchronized(timeout = 6000000)
public class PhieuTraHangCuaTuTrucAction implements Serializable{
	
	private static final long serialVersionUID = 3694949080102104995L;
	private static Logger log = Logger.getLogger(PhieuTraHangCuaTuTrucAction.class);
	private PhieuTraKho phieuTraKho;
	private String ngayXuat;
	private PhieuDuTru phieuDuTru;
	private Integer nguonCtMaSo;
	private String nguonCtMa;
	private Integer nguonKpMaSo;
	private String nguonKpMa;
	private String maKho;

	@DataModel
	private List<GridItem> listCtTraKhoB4165;
	@DataModelSelection
	private GridItem selCtTraKhoB4113;		
	private GridItem ctTraKhoB4113;
	private boolean isUpdate;
	
	private String nofoundPDT;
	private String nosuccess;
	private String nofound;
	
	private String hienThiGhiNhan="";
	private String hienThiInPhieu="";
	
	private String dmLoaiTen="";
	private DmLoaiThuocDelegate dmLoaiThuocDelegate;
	private List<SelectItem> listDmLoaiThuocs = new ArrayList<SelectItem>();
	HashMap<String, DmLoaiThuoc> hmLoaiThuoc = new HashMap<String, DmLoaiThuoc>();
	private DmLoaiPhieuDelegate dmLoaiPhieuDelegate;
	private List<SelectItem> listDmLoaiPhieus = new ArrayList<SelectItem>();
	private YteLogDelegate yteLogDele = YteLogDelegate.getInstance();
	private YteLog yteLog;
	private String listDataLog ;
	private DmKhoa dmKhoaNhan = new DmKhoa();
	
	public String getNofound() {
		return nofound;
	}

	public void setNofound(String nofound) {
		this.nofound = nofound;
	}

	public String getNofoundPDT() {
		return nofoundPDT;
	}

	public void setNofoundPDT(String nofoundPDT) {
		this.nofoundPDT = nofoundPDT;
	}
	
	@In(required = false)
	@Out(required = false)
	Identity identity;

	private DtDmNhanVien nvCn;
	
	@In(required = false)
	@Out(required = false)
	private String tenChuongTrinh;

	@Begin(join = true)
	public String init(String loaikho) {
		log.info("***** init QuanLyKhoChinh_XuatKhoChinh_TraHangCuaTuTruc *****");
		resetValue();
		if ("KhoNoiTru".equals(loaikho)){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoNoiTru;
			maKho = IConstantsRes.KHOA_NT_MA;
		}else if ("BHYT".equals(loaikho)){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoBHYT;
			maKho = IConstantsRes.KHOA_BHYT_MA;
		}else if ("KC".equals(loaikho)){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoChinh;
			maKho = IConstantsRes.KHOA_KC_MA;
		}else if ("TE".equals(loaikho)){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoTE;
			maKho = IConstantsRes.KHOA_TE_MA;
		}
		
		DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();		
		dmKhoaNhan = (DmKhoa)dieuTriUtilDelegate.findByMa(maKho, "DmKhoa", "dmkhoaMa");
		
		log.info("tenChuongTrinh " + tenChuongTrinh);
		DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
		setNvCn(nvDelegate.findByNd(identity.getUsername()));
		if (getNvCn() == null) {
			setNvCn(new DtDmNhanVien());
		}
		log.info("nvCn" + nvCn);
		listDmLoaiThuocs.clear();
		hmLoaiThuoc.clear();
		refreshDmLoaiThuoc();
		phieuTraKho.setDtdmnhanvienCn(nvCn);
		
		
		return "QuanLyKhoChinh_XuatKhoChinh_TraHangCuaTuTruc";
	}
	@End
	public void end(){
		
	}
	
	private void resetValue() {
		log.info("---resetValue");
		ngayXuat = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
		phieuTraKho = new PhieuTraKho();
		phieuTraKho.setPhieutrakhoThanhtien(0.0);
		SetInforUtil.setInforIfNullForPhieuTraKho(phieuTraKho);
		phieuDuTru = new PhieuDuTru();
		SetInforUtil.setInforIfNullForPhieuDuTru(phieuDuTru);
		nofoundPDT = "false";
		nosuccess = "false";
		nofound = "false";
		listCtTraKhoB4165 = new ArrayList<GridItem>();
		ctTraKhoB4113 = new GridItem();
		nguonCtMa="";
		nguonKpMa="";
		isUpdate = false;
		dmLoaiTen ="";
	}
	
	// Hien thi thong tin phieu du tru sau khi nhap Ma phieu du tru
	//Luu y: neu ma phieu du tru nay da ton tai trong bang phieu tra kho thi cho phep hien thi phieu tra kho len
	//Nguoc lai thi load phieu du tru tu bang phieu du tru
	public void displayPhieuDuTru(){
		log.info("displayPhieuDuTru");		
		listCtTraKhoB4165.clear();
		if (phieuDuTru.getPhieudtMa() == null || phieuDuTru.getPhieudtMa().equals("")){
			hienThiGhiNhan="";			
			hienThiInPhieu="";
			return;
		}
		
		PhieuTraKhoDelegate ptkDel = PhieuTraKhoDelegate.getInstance();
		CtTraKhoDelegate cttkDel = CtTraKhoDelegate.getInstance();
		PhieuTraKho ptkho = ptkDel.findByPhieuDuTruMa(phieuDuTru.getPhieudtMa(), dmKhoaNhan.getDmkhoaMaso());
		if(ptkho != null){
			phieuTraKho = ptkho;
			SetInforUtil.setInforIfNullForPhieuTraKho(phieuTraKho);
			ngayXuat = new SimpleDateFormat("dd/MM/yyyy").format(phieuTraKho.getPhieutrakhoNgay());
			List<CtTraKho> listCtTraKho = cttkDel.findByphieutrakhoMa(phieuTraKho.getPhieutrakhoMa());
			for (CtTraKho obj : listCtTraKho) {
				GridItem ct = new GridItem();
				ct.setCtTraKho(obj);
				listCtTraKhoB4165.add(ct);
			}
			PhieuDuTru pdt_tmp = phieuTraKho.getPhieudtMa();
			if (pdt_tmp != null){
				phieuDuTru = pdt_tmp;
				dmLoaiTen = phieuDuTru.getDmloaithuocMaso().getDmloaithuocTen();
				SetInforUtil.setInforIfNullForPhieuDuTru(phieuDuTru);
			}
			tinhTongTien();
			FacesMessages.instance().add(IConstantsRes.PHIEU_DU_TRU_DA_TRA_HANG,phieuDuTru.getPhieudtMa());
			hienThiGhiNhan="";
			hienThiInPhieu = "true";
			return;
		}
		
		PhieuDuTru objPhieuDuTru = CtPhieuDtDelegate.getInstance().findByPhieuDuTruPhanBietKho(phieuDuTru.getPhieudtMa(),CtPhieuDtDelegate.TRA_THUOC_TU_TRUC, dmKhoaNhan.getDmkhoaMaso());
		if (objPhieuDuTru == null ){
			nofoundPDT = "true";
			hienThiGhiNhan = "";
			hienThiInPhieu = "";
			FacesMessages.instance().add(IConstantsRes.PHIEU_DU_TRU_KHO_NOT_FOUND, phieuDuTru.getPhieudtMa());
			return;
		}else{		
			List<CtPhieuDt> objCtPhieuDt =CtPhieuDtDelegate.getInstance().findByPhieuDuTruMa(phieuDuTru.getPhieudtMa());
			phieuDuTru =objPhieuDuTru;
			dmLoaiTen = phieuDuTru.getDmloaithuocMaso().getDmloaithuocTen();
			SetInforUtil.setInforIfNullForPhieuTraKho(phieuTraKho);
			phieuTraKho.setPhieutrakhoLoaiPhieu(phieuDuTru.getPhieudtLoaiPhieu());
			phieuTraKho.setDmloaithuocMaso(phieuDuTru.getDmloaithuocMaso());
			log.info("-----listCtpdt_tmp: " + objCtPhieuDt);
			for (CtPhieuDt ctpdt : objCtPhieuDt) {
				log.info("-----cttk ma: " + ctpdt.getCtdtMa());
				log.info("-----cttk soluong: " + ctpdt.getCtdtTra());
				CtTraKho cttk = new CtTraKho();
				cttk.setPhieutrakhoMa(phieuTraKho);
				cttk.setCttrakhoDongia(ctpdt.getCtdtDongia());
				cttk.setCttrakhoSoluong(ctpdt.getCtdtSoluong());
				cttk.setDmthuocMaso(ctpdt.getDmthuocMaso());
				cttk.setCttrakhoMalk(ctpdt.getCtdtMalk());
				SetInforUtil.setInforIfNullForCTPhieuTraKho(cttk);
				listCtTraKhoB4165.add(new GridItem(cttk,ctpdt.getCtdtSoluong()));
				log.info("***** ctpdt.getCtdtTra() "+ctpdt.getCtdtTra());
				log.info("***** ctpdt.getCtdtSoluong() "+ctpdt.getCtdtSoluong());
			}
			log.info("-----listCtTraKhoB4165: " + listCtTraKhoB4165.size());
			
			hienThiGhiNhan = "true";

			hienThiInPhieu = "";
			tinhTongTien();
			log.info("tong tien phieu: " + phieuTraKho.getPhieutrakhoThanhtien());
			
			PhieuTraKhoDelegate phieuTraKhoDelegate = PhieuTraKhoDelegate.getInstance();
			
			log.info("phieuDuTru.getPhieudtMa():"+phieuDuTru.getPhieudtMa());
			
			boolean daTraPhieuDuTru = phieuTraKhoDelegate.daTraPhieuDuTru(phieuDuTru.getPhieudtMa());
			log.info("-----da tra pdt: " + daTraPhieuDuTru);
			if (daTraPhieuDuTru == true){				
				hienThiGhiNhan="";
				//listCtTraKhoB4113 = new ArrayList<GridItem>();
				hienThiInPhieu="";
				FacesMessages.instance().add(IConstantsRes.PHIEU_DU_TRU_DA_TRA_HANG,phieuDuTru.getPhieudtMa());
				return;
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
		}
		else {
			setDmLoaiTen("");			
		}
		listDmLoaiPhieus.clear();
		refreshDmLoaiPhieu();
		phieuDuTru.setPhieudtLoaiPhieu("");
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
		phieuDuTru.setPhieudtLoaiPhieu("");
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
		dmLoaiPhieuDelegate = DmLoaiPhieuDelegate.getInstance();
		if(phieuDuTru != null && phieuDuTru.getDmloaithuocMaso() != null){
			List<DmLoaiPhieu> lstDmLoaiPhieus = dmLoaiPhieuDelegate.findAll();
			if(lstDmLoaiPhieus !=null && lstDmLoaiPhieus.size()>0)
			{
				for(DmLoaiPhieu o: lstDmLoaiPhieus){
					if(phieuDuTru.getDmloaithuocMaso().getDmloaithuocMaso().equals(o.getDmloaithuocMaso().getDmloaithuocMaso())){
						listDmLoaiPhieus.add(new SelectItem(o.getDmloaiphieuTen()));
					}
				}
			}
		}	
	}
	
	public void delete(){
		log.info("---delete");
		listCtTraKhoB4165.remove(selCtTraKhoB4113);
		ctTraKhoB4113 = new GridItem();
	}
	
	public void select(){
		log.info("---select");
		ctTraKhoB4113 = selCtTraKhoB4113;
		isUpdate = true;
	}
	
	public void enter(){
		log.info("---enter");
		if (isUpdate) {
			isUpdate = false;
		}else{
			listCtTraKhoB4165.add(ctTraKhoB4113);
		}
		tinhTongTien();
		ctTraKhoB4113 = new GridItem();
	}
	
	private void tinhTongTien(){
		Double tongtien = new Double(0);
		if (listCtTraKhoB4165 != null) {
			for (GridItem obj : listCtTraKhoB4165) {
				log.info("-----obj: " + obj.getCtTraKho());
				tongtien += (obj.getCtTraKho().getCttrakhoSoluong() == null ?0: obj.getCtTraKho().getCttrakhoSoluong())* ( obj.getCtTraKho().getCttrakhoDongia() == null ? 0: obj.getCtTraKho().getCttrakhoDongia());
			}
		}
		phieuTraKho.setPhieutrakhoThanhtien(tongtien);
	}
	
	public void nhapMoi() throws Exception{
		log.info("---nhapMoi");
		hienThiGhiNhan="";			
		hienThiInPhieu="";
		resetValue();
	}

	public void ghiNhan() throws Exception{
		log.info("---ghiNhan");
		yteLog = new YteLog();
		listDataLog="";
		TonKhoDelegate tkDelegate = TonKhoDelegate.getInstance();
		KiemKeDelegate kiemkeDel = KiemKeDelegate.getInstance();
		if (phieuDuTru.getPhieudtMa() == null || phieuDuTru.getPhieudtMa().equals("") || listCtTraKhoB4165 == null || listCtTraKhoB4165.size()==0 || phieuTraKho.getPhieutrakhoThanhtien() == 0.0){
			nosuccess = "true";
			FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THATBAI);
			hienThiGhiNhan = "";
			hienThiInPhieu = "";
		}else{
			phieuTraKho.setPhieudtMa(phieuDuTru);
			phieuTraKho.setPhieutrakhoNgaygiocn(new Date());
			
			if (!ngayXuat.equals("")) {
				phieuTraKho.setPhieutrakhoNgay(new SimpleDateFormat("dd/MM/yyyy").parse(ngayXuat));
			}
			
			log.info("***** DmkhoaTra "+phieuDuTru.getPhieudtMakho());
			log.info("***** DmkhoanNhan "+phieuDuTru.getDmkhoaMaso());
			phieuTraKho.setDmkhoaTra(phieuDuTru.getPhieudtMakho());
			phieuTraKho.setDmkhoaNhan(phieuDuTru.getDmkhoaMaso());
			
			for (GridItem item : listCtTraKhoB4165) {
				CtTraKho ct = item.getCtTraKho();
				//28-4-2011 - kiem tra tai kho nhan, neu dang kiem ke thi khong duoc tra thuoc
				boolean tinhtrangKiemKe = kiemkeDel.dangKiemKe(ct.getCttrakhoMalk(),phieuTraKho.getDmkhoaNhan().getDmkhoaMaso());
				if(tinhtrangKiemKe == true){
					FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_DANGKIEMKE, ct.getCttrakhoMalk());
					return;
				}
				//End kiem tra
			}
			
			RemoveUtil.removeIfNullForPhieuTraKho(phieuTraKho);
			List<CtTraKho> listTraKho = new ArrayList<CtTraKho>();
			CtTraKho tmp = null;
			
			for (GridItem item : listCtTraKhoB4165) {
				tmp = item.getCtTraKho();
				RemoveUtil.removeIfNullForCTPhieuTraKho(tmp);
				listTraKho.add(tmp);
				//luu log thong tin thuoc
				listDataLog += "Ma LK:"+ tmp.getCttrakhoMalk() +
						" Don gia: "+ tmp.getCttrakhoDongia()+ " Don gia ban: "+ tmp.getCttrakhoDongiaban() + 
						" So luong: "+ tmp.getCttrakhoSoluong()+
						" So lo: "+ tmp.getCttrakhoLo()+
						" Nam SX: " + tmp.getCttrakhoNamnhap()+
						" Nam HD: " + tmp.getCttrakhoNamhandung()+ "\n";
			}
			
			String result = PhieuTraKhoDelegate.getInstance().updatePhieuDTTuTrucTraKho(listTraKho, phieuTraKho, IConstantsRes.PRIORITY_TON_LO_THUOC);
			if (result == null || result.equals("")){
				nosuccess = "true";
				FacesMessages.instance().add(IConstantsRes.PHIEU_DU_TRU_DA_TRA_HANG,phieuDuTru);
				hienThiGhiNhan="";
				hienThiInPhieu="";
			}else{//result return "mathuoc-Soluongton";
				if(result.indexOf(".")>0){//Khong du so luong ton de tra nhu du tru
					nosuccess = "true";
					String maThuoc = result.substring(0, result.indexOf("-"));
					String soluongTon = result.substring(result.indexOf("-")+1);
					FacesMessages.instance().add(IConstantsRes.PHIEU_DU_TRU_KHONGDUTHUOCTRA,maThuoc,soluongTon);
					hienThiGhiNhan="";
					hienThiInPhieu="";
				}else{
					phieuTraKho.setPhieutrakhoMa(result);
					SetInforUtil.setInforIfNullForPhieuTraKho(phieuTraKho);
					FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THANHCONG, result);
					hienThiGhiNhan="";
					hienThiInPhieu="true";

//					Luu log he thong
			         yteLog.setForm("B4165_Phieutrahangtutruc");
			         yteLog.setUserId(identity == null ? "NULL" : identity.getUsername());
			         yteLog.setObjectId(phieuTraKho.getPhieutrakhoMa());
			         yteLog.setLogString("Phieu du tru: "+ phieuDuTru.getPhieudtMa()  + 
			         					" Ngay tra: "+ ngayXuat+
			         					" Loai thuoc: " + phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocMa()+
			         					" Loai phieu: "+ phieuDuTru.getPhieudtLoaiPhieu()+
			         					" Kho nhap: "+ phieuDuTru.getDmkhoaMaso(true).getDmkhoaMa()+
			         					" Khoa tra: "+ phieuDuTru.getDmkhoaMaso(true).getDmkhoaMa()+
			         					" Nguoi nhap: "+  phieuTraKho.getDtdmnhanvienCn(true).getDtdmnhanvienMa()+
			         					" Nguoi lap: "+phieuTraKho.getDtdmnhanvienLapphieu(true).getDtdmnhanvienMa()+
			         					" Nguoi ky: "+phieuTraKho.getDtdmnhanvienBacsi(true).getDtdmnhanvienMa()+
			         					" Thanh tien: "+ phieuTraKho.getPhieutrakhoThanhtien());
			         yteLog.setDateTime(new Date());
			         yteLog.setListData(listDataLog);

			         yteLogDele.create(yteLog);
				}				
			}
		}
	}
	
	public void displayPhieuTraKho() throws Exception{
		log.info("---displayPhieuTraKho");
		ctTraKhoB4113 = new GridItem();
		phieuDuTru = new PhieuDuTru();
		SetInforUtil.setInforIfNullForPhieuDuTru(phieuDuTru);
		String maPhieuTra = phieuTraKho.getPhieutrakhoMa();
		if (maPhieuTra !=null && !maPhieuTra.equals("")){
			PhieuTraKhoDelegate delegate = PhieuTraKhoDelegate.getInstance();
			PhieuTraKho ptk_tmp = delegate.findByPhieutrakhoByKhoNhan(maPhieuTra, dmKhoaNhan.getDmkhoaMaso());
			if (ptk_tmp != null){
				phieuTraKho = ptk_tmp;
				SetInforUtil.setInforIfNullForPhieuTraKho(phieuTraKho);
				ngayXuat = new SimpleDateFormat("dd/MM/yyyy").format(phieuTraKho.getPhieutrakhoNgay());
				CtTraKhoDelegate delegateCt = CtTraKhoDelegate.getInstance();
				for (CtTraKho obj : delegateCt.findByphieutrakhoMa(phieuTraKho.getPhieutrakhoMa())) {
					GridItem ct = new GridItem();
					ct.setCtTraKho(obj);
					listCtTraKhoB4165.add(ct);
				}
				PhieuDuTru pdt_tmp = phieuTraKho.getPhieudtMa();
				if (pdt_tmp != null){
					phieuDuTru = pdt_tmp;
					SetInforUtil.setInforIfNullForPhieuDuTru(phieuDuTru);
				}
				hienThiGhiNhan="";					
				hienThiInPhieu="true";				
			} else {
				nofound = "true";
				hienThiGhiNhan = "";
				hienThiInPhieu = "";
			}
		}
	}
	
	public TonKho createTonKhoNhan(CtTraKho ct) {
		TonKho tk = new TonKho();
		tk.setDmnctMaso(ct.getDmnctMaso());
		tk.setDmnguonkinhphiMaso(ct.getDmnguonkinhphiMaso());
		tk.setDmnhasanxuatMaso(ct.getDmnhasanxuatMaso());
		tk.setDmquocgiaMaso(ct.getDmquocgiaMaso());
		tk.setDmthuocMaso(ct.getDmthuocMaso());
		tk.setDtdmkhoMaso(null);
		tk.setDtdmnhanvienCn(phieuTraKho.getDtdmnhanvienCn());
		tk.setTonkhoDongia(ct.getCttrakhoDongia());
		tk.setTonkhoDongiaban(ct.getCttrakhoDongiaban());
		tk.setTonkhoHienthi(true);
		tk.setTonkhoLo(ct.getCttrakhoLo());
		//tk.setTonkhoMucthue(ct.getPhieunhapkhoMucthue());
		tk.setTonkhoNhap(ct.getCttrakhoSoluong());
		tk.setTonkhoXuat(null);
		tk.setTonkhoTra(null);
		tk.setTonkhoNgayhandung(ct.getCttrakhoNgayhandung());
		tk.setTonkhoThanghandung(ct.getCttrakhoThanghandung());
		tk.setTonkhoNamhandung(ct.getCttrakhoNamhandung());
		tk.setTonkhoNamnhap(ct.getCttrakhoNamnhap());
		tk.setTonkhoSodangky(ct.getCtxuatkhoSodangky());
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		//if (ngayHt != "") {
			try {
				//Date dNgayCn = df.parse(ngayHt);
				Date dNgayCn = new Date();
				tk.setTonkhoNgaygiocn(dNgayCn);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		//}
		return tk;
	}
	
	public TonKho createTonKhoTra(CtTraKho ct) {
		TonKho tk = new TonKho();
		tk.setDmnctMaso(ct.getDmnctMaso());
		tk.setDmnguonkinhphiMaso(ct.getDmnguonkinhphiMaso());
		//tk.setDmNhaCungCap(phieuTra.getNhacungcap(true));
		tk.setDmnhasanxuatMaso(ct.getDmnhasanxuatMaso());
		tk.setDmquocgiaMaso(ct.getDmquocgiaMaso());
		tk.setDmthuocMaso(ct.getDmthuocMaso());
		tk.setDtdmkhoMaso(null);
		tk.setDtdmnhanvienCn(phieuTraKho.getDtdmnhanvienCn());
		tk.setTonkhoDongia(ct.getCttrakhoDongia());
		tk.setTonkhoDongiaban(ct.getCttrakhoDongiaban());
		tk.setTonkhoHienthi(true);
		tk.setTonkhoLo(ct.getCttrakhoLo());
		//tk.setTonkhoMucthue(ct.getPhieunhapkhoMucthue());
		tk.setTonkhoNhap(null);
		tk.setTonkhoXuat(null);
		tk.setTonkhoTra(ct.getCttrakhoSoluong());
		tk.setTonkhoNgayhandung(ct.getCttrakhoNgayhandung());
		tk.setTonkhoThanghandung(ct.getCttrakhoThanghandung());
		tk.setTonkhoNamhandung(ct.getCttrakhoNamhandung());
		tk.setTonkhoNamnhap(ct.getCttrakhoNamnhap());
		tk.setTonkhoSodangky(ct.getCtxuatkhoSodangky());
			try {
				Date dNgayCn = new Date();
				tk.setTonkhoNgaygiocn(dNgayCn);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		return tk;
	}
	
	public PhieuTraKho getPhieuTraKho() {
		return phieuTraKho;
	}
	public void setPhieuTraKho(PhieuTraKho phieuTraKho) {
		this.phieuTraKho = phieuTraKho;
	}
	public String getNgayXuat() {
		return ngayXuat;
	}
	public void setNgayXuat(String ngayXuat) {
		this.ngayXuat = ngayXuat;
	}
	public PhieuDuTru getPhieuDuTru() {
		return phieuDuTru;
	}
	public void setPhieuDuTru(PhieuDuTru phieuDuTru) {
		this.phieuDuTru = phieuDuTru;
	}

	public Integer getNguonCtMaSo() {
		return nguonCtMaSo;
	}

	public void setNguonCtMaSo(Integer nguonCtMaSo) {
		this.nguonCtMaSo = nguonCtMaSo;
	}

	public String getNguonCtMa() {
		return nguonCtMa;
	}

	public void setNguonCtMa(String nguonCtMa) {
		this.nguonCtMa = nguonCtMa;
	}

	public Integer getNguonKpMaSo() {
		return nguonKpMaSo;
	}

	public void setNguonKpMaSo(Integer nguonKpMaSo) {
		this.nguonKpMaSo = nguonKpMaSo;
	}

	public String getNguonKpMa() {
		return nguonKpMa;
	}

	public void setNguonKpMa(String nguonKpMa) {
		this.nguonKpMa = nguonKpMa;
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
	
	public String thuchienAction(){
		XuatReport();
		return "B4160_Chonmenuxuattaptin";
	}
	public void XuatReport() {
		reportTypeKC="phieutrahangcuatutruc";
		log.info("Vao Method XuatReport D03_phieuxuatkho");
		String baocao1=null;

		if (!phieuTraKho.getPhieutrakhoMa().equals("")) {
			
		try {
			PhieuTraKhoDelegate ptkWS = PhieuTraKhoDelegate.getInstance();
			PhieuTraKho px = ptkWS.find(phieuTraKho.getPhieutrakhoMa());

			
			String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/phieutrakho_01.jrxml";
			log.info(String.format("-----pathTemplate: %s", pathTemplate));
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			log.info(String.format("-----tenSo: %s", params.get("tenSo")));
			params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			log.info(String.format("-----tenDonVi: %s", params.get("tenDonVi")));
			params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			
			params.put("DONVI", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			
			log.info(String.format("-----dienThoaiDonVi: %s", params.get("dienThoaiDonVi")));
			Calendar cal = Calendar.getInstance();
			cal.setTime(px.getPhieutrakhoNgay());
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

			params.put("pxMa", px.getPhieutrakhoMa());

			if (px.getDmkhoaNhan() != null) {
				params.put("khoaNhan", px.getDmkhoaNhan().getDmkhoaTen());
			} else {
				params.put("khoaNhan", "");
			}
			log.info(String.format("-----khoaNhan: %s", params.get("khoaNhan")));

			if (px.getDmkhoaTra() != null) {
				params.put("khoaXuat", px.getDmkhoaTra().getDmkhoaTen());
			} else {
				params.put("khoaXuat", "");
			}
			log.info(String.format("-----khoaXuat: %s", params.get("khoaXuat")));

			params.put("tongTien", px.getPhieutrakhoThanhtien());
			log.info(String.format("-----tongTien: %s", params.get("tongTien")));
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintKC,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"duocpham/", "pdf","phieutrahangtheodutrutra");
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
	public class GridItem{
		private CtTraKho ctTraKho;
		private Double soLuong;
		public GridItem(){
			ctTraKho = new CtTraKho();
			if (ctTraKho.getDmthuocMaso()==null){
				ctTraKho.setDmthuocMaso(new DmThuoc());
			}
			soLuong = new Double(0);
		}
		public GridItem(CtTraKho _ctTraKho, Double _soLuong){
			ctTraKho = _ctTraKho;
			soLuong = _soLuong;
		}
		public CtTraKho getCtTraKho() {
			return ctTraKho;
		}
		public void setCtTraKho(CtTraKho ctTraKho) {
			this.ctTraKho = ctTraKho;
		}
		public Double getSoLuong() {
			return soLuong;
		}
		public void setSoLuong(Double soLuong) {
			this.soLuong = soLuong;
		}
	}

	public GridItem getSelCtTraKhoB4113() {
		return selCtTraKhoB4113;
	}

	public void setSelCtTraKhoB4113(GridItem selCtTraKhoB4113) {
		this.selCtTraKhoB4113 = selCtTraKhoB4113;
	}

	public List<GridItem> getListCtTraKhoB4113() {
		return listCtTraKhoB4165;
	}

	public void setListCtTraKhoB4113(List<GridItem> listCtTraKhoB4113) {
		this.listCtTraKhoB4165 = listCtTraKhoB4113;
	}

	public GridItem getCtTraKhoB4113() {
		return ctTraKhoB4113;
	}

	public void setCtTraKhoB4113(GridItem ctTraKhoB4113) {
		this.ctTraKhoB4113 = ctTraKhoB4113;
	}

	public String getNosuccess() {
		return nosuccess;
	}

	public void setNosuccess(String nosuccess) {
		this.nosuccess = nosuccess;
	}

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		PhieuTraHangCuaTuTrucAction.log = log;
	}

	public boolean isUpdate() {
		return isUpdate;
	}

	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public String getHienThiGhiNhan() {
		return hienThiGhiNhan;
	}

	public void setHienThiGhiNhan(String hienThiGhiNhan) {
		this.hienThiGhiNhan = hienThiGhiNhan;
	}

	public String getHienThiInPhieu() {
		return hienThiInPhieu;
	}

	public void setHienThiInPhieu(String hienThiInPhieu) {
		this.hienThiInPhieu = hienThiInPhieu;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	public DtDmNhanVien getNvCn() {
		return nvCn;
	}

	public void setNvCn(DtDmNhanVien nvCn) {
		this.nvCn = nvCn;
	}
	

}

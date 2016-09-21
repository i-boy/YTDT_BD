package com.iesvn.yte.dieutri.duocpham.action;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
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

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.log4j.Logger;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
import com.iesvn.yte.dieutri.delegate.CtNhapKhoDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DmThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DmLoaiThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.PhieuNhapKhoDelegate;
import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
import com.iesvn.yte.dieutri.delegate.YteLogDelegate;
import com.iesvn.yte.dieutri.entity.CtNhapKho;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.PhieuNhapKho;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.dieutri.entity.YteLog;
import com.iesvn.yte.entity.DmDonViTinh;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.entity.DmNhaSanXuat;
import com.iesvn.yte.entity.DmQuocGia;
import com.iesvn.yte.entity.DmThuoc;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;
import com.sun.org.apache.commons.beanutils.BeanUtils;

@Scope(ScopeType.CONVERSATION)
@Name("B4111_phieunhapkhochinh")
public class PhieuNhapKhoAction implements Serializable {
	private static final long serialVersionUID = 10L;
	private static Logger logger = Logger.getLogger(PhieuNhapKhoAction.class);
	
	@In(required = false)
	@Out(required = false)
	private String tenChuongTrinh;
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
	private PhieuNhapKho pnk;
	private CtNhapKho cnk;
	private DmKhoa khoa;
	private String ngayHt;
	private String ngayLap="";
	
	private String ngayHD;
	private String thangHD;
	private String namHD;
	
	private String namNhap;
	private Boolean isUpdate;
	private Integer count;
	private String maPhieu;
	@DataModel
	private ArrayList<CtNhapKho> listCtNhapKho;
	@DataModelSelection
	private CtNhapKho selected;
	private Double thanhTien;
	private Double thueGtgt;
	private Double thanhTienThue;
	
	private String resultReportFileName;
	private String resultReportName;	
	private String loaiFileXuat;
	@Out(required = false)
	private String isReport = "false";
	//Tho add
	private DmLoaiThuocDelegate dmLoaiThuocDelegate;
	private String dmloaithuocTen="";
	HashMap<String, DmLoaiThuoc> hmLoaiThuoc = new HashMap<String, DmLoaiThuoc>();	
	HashMap<String, DmThuoc> hmDmThuoc = new HashMap<String, DmThuoc>();
	private Double tonkhoHT;
	private Double giadauthau;
	private Double giacuoi;
	
	 private YteLogDelegate yteLogDele = YteLogDelegate.getInstance();
     private YteLog yteLog;
     private String listDataLog ;

	
	@Restrict("#{s:hasRole('NV_KhoaDuoc') or s:hasRole('QT_HT_Duoc')}")
	@Begin(join=true)
	public String init() {
		//logger.info("-----init()-----");
		reset();
		tenChuongTrinh = MyMenuYTDTAction.quanLyKhoChinh;
		return "QuanLyKhoChinh_NhapKhoChinh_NhapHangTuNhaCungCap";
	}
	@End
	public void endFunc(){
		
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
		
		public void onblurMaThuocAction() {
			//logger.info("-----BEGIN onblurMaThuocAction()-----");
			if (cnk != null && cnk.getDmthuocMaso(true) != null) {
				String mathang_ma = cnk.getDmthuocMaso(true).getDmthuocMa();
				DmThuoc dmThuoc = hmDmThuoc.get(mathang_ma.toUpperCase());
				if(dmThuoc != null) {
					cnk.setDmthuocMaso(dmThuoc);					
					giadauthau = dmThuoc.getDmthuocDonGiaDauThau();
					DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
					giacuoi = dtutilDelegate.getGiaCuoi(dmThuoc.getDmthuocMa());
					cnk.setCtnhapkhoSodangky(dmThuoc.getDmthuocSoDangKy());
					cnk.setDmquocgiaMaso(dmThuoc.getDmthuocNuocdefa());
					cnk.setDmnhasanxuatMaso(dmThuoc.getDmnhasanxuatMaso());
					//logger.info("-----DA THAY DMTHUOC-----");
				}
				else {
					cnk.setDmthuocMaso(new DmThuoc());
					giacuoi = null;
					giadauthau = null;
					cnk.setCtnhapkhoSodangky(null);
					cnk.setDmquocgiaMaso(new DmQuocGia());
					cnk.setDmnhasanxuatMaso(new DmNhaSanXuat());
				}
			}	
			cnk.setCtnhapkhoSoluong(null);
			cnk.setCtnhapkhoDongia(0.0);
			cnk.setCtnhapkhoDongiaban(null);
			setTonkhoHT(null);
			cnk.setCtnhapkhoLo(null);
			//logger.info("-----END onblurMaThuocAction()-----");
		}

		public void onblurTenThuocAction() {
			//logger.info("-----BEGIN onblurTenThuocAction()-----");
			if (cnk != null && cnk.getDmthuocMaso(true) != null) {
				String tenThuoc = cnk.getDmthuocMaso(true).getDmthuocTen();
				Boolean hasTenThuoc = false;
				String maThuoc = null;
				Integer masoThuoc = null;
				if( hmDmThuoc != null ){
					java.util.Set set = hmDmThuoc.entrySet();
				    Iterator i = set.iterator();
				    while(i.hasNext()){
				    	Map.Entry me = (Map.Entry)i.next();
				    	DmThuoc dmThuoc = (DmThuoc)me.getValue();
				    	if(dmThuoc.getDmthuocTen().trim() == tenThuoc.trim() || dmThuoc.getDmthuocTen().trim().equals(tenThuoc.trim())){
				    		hasTenThuoc = true;
				    		maThuoc = dmThuoc.getDmthuocMa();
				    		masoThuoc =dmThuoc.getDmthuocMaso();
				    		break;
				    	}	    		
				    }
			    }
			    if(hasTenThuoc){
			    	DieuTriUtilDelegate dieutriUtilDelegate = DieuTriUtilDelegate.getInstance();
			    	DmThuoc dmThuoc = (DmThuoc)dieutriUtilDelegate.findByMaSo(masoThuoc, "DmThuoc", "dmthuocMaso");
			    	cnk.setDmthuocMaso(dmThuoc);
					giadauthau = dmThuoc.getDmthuocDonGiaDauThau();					
					giacuoi = dieutriUtilDelegate.getGiaCuoi(dmThuoc.getDmthuocMa());
					cnk.setCtnhapkhoSodangky(dmThuoc.getDmthuocSoDangKy());
					cnk.setDmquocgiaMaso(dmThuoc.getDmthuocNuocdefa());
					cnk.setDmnhasanxuatMaso(dmThuoc.getDmnhasanxuatMaso());
					//logger.info("-----DA THAY DMTHUOC-----");
			    }else{
			    	cnk.setDmthuocMaso(new DmThuoc());
					giacuoi = null;
					giadauthau = null;
					cnk.setCtnhapkhoSodangky(null);
					cnk.setDmquocgiaMaso(new DmQuocGia());
					cnk.setDmnhasanxuatMaso(new DmNhaSanXuat());
			    }
			}
			cnk.setCtnhapkhoSoluong(null);
			cnk.setCtnhapkhoDongia(0.0);
			cnk.setCtnhapkhoDongiaban(null);
			setTonkhoHT(null);
			cnk.setCtnhapkhoLo(null);
			//logger.info("-----END onblurTenThuocAction()-----");
		}
		
		public void refreshDmThuoc(){
			listDmThuocs.clear();
			hmDmThuoc.clear();
			dmThuocDelegate = DmThuocDelegate.getInstance();
			if(pnk != null && pnk.getDmloaithuocMaso() != null && pnk.getDmloaithuocMaso(true).getDmloaithuocMa() != null){
				String lthuoc_ma = pnk.getDmloaithuocMaso(true).getDmloaithuocMa();
				List<DmThuoc> lstDmThuoc = dmThuocDelegate.findByLoaiPhanLoaiThuoc(lthuoc_ma, "");	
				if(lstDmThuoc !=null && lstDmThuoc.size()>0)
				{
					for(DmThuoc o: lstDmThuoc){
						listDmThuocs.add(new SelectItem(o.getDmthuocTen()));
						hmDmThuoc.put(o.getDmthuocMa(), o);
					}
				}
			}
		}

	/** ==================== END LY THEM CODE ==================== */	
	
	//Tho add		
		public String getDmloaithuocTen(){
			return dmloaithuocTen;
		}
		public void setDmloaithuocTen(String dmloaithuocTen){
			this.dmloaithuocTen = dmloaithuocTen;
		}
		private List<SelectItem> listDmLoaiThuocs = new ArrayList<SelectItem>();
		
		public List<SelectItem> getListDmLoaiThuocs() {
			return listDmLoaiThuocs;
		}

		public void setListDmLoaiThuocs(List<SelectItem> listDmLoaiThuocs) {
			this.listDmLoaiThuocs = listDmLoaiThuocs;
		}
		
		public void onblurMaLoaiThuocAction() {
			//logger.info("-----BEGIN onblurMaLoaiThuocAction()-----");
			if(pnk != null && pnk.getDmloaithuocMaso() != null && pnk.getDmloaithuocMaso(true).getDmloaithuocMa() != null){
				String lthuoc_ma = pnk.getDmloaithuocMaso(true).getDmloaithuocMa();
				DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)hmLoaiThuoc.get(lthuoc_ma.toUpperCase());
				if(dmLoaiThuoc != null) {
					setDmloaithuocTen(dmLoaiThuoc.getDmloaithuocTen());
					pnk.setDmloaithuocMaso(dmLoaiThuoc);
				}
				else {
					setDmloaithuocTen("");		
					pnk.setDmloaithuocMaso(null);
				}	
			}
			cnk.setDmthuocMaso(null);
			listDmThuocs.clear();
			hmDmThuoc.clear();
			refreshDmThuoc();
			cnk.setDmthuocMaso(new DmThuoc());
			cnk.setCtnhapkhoSodangky(null);
			giacuoi = null;
			giadauthau = null;
			cnk.setCtnhapkhoSodangky(null);
			cnk.setDmquocgiaMaso(new DmQuocGia());
			cnk.setDmnhasanxuatMaso(new DmNhaSanXuat());
			cnk.setCtnhapkhoSoluong(null);
			cnk.setCtnhapkhoDongia(0.0);
			cnk.setCtnhapkhoDongiaban(null);
			setTonkhoHT(null);
			cnk.setCtnhapkhoLo(null);
			//logger.info("-----END onblurMaLoaiThuocAction()-----");
		}

		public void onblurTenLoaiThuocAction() {
			//logger.info("-----BEGIN onblurTenLoaiThuocAction()-----");
			Boolean hasTenLoai = false;
			if (dmloaithuocTen == ""){
				pnk.setDmloaithuocMaso(null);
			}
			java.util.Set set = hmLoaiThuoc.entrySet();
		    Iterator i = set.iterator();
		    DmLoaiThuoc dmLoaiThuoc_Finded = new DmLoaiThuoc();
		    while(i.hasNext()){
		    	Map.Entry me = (Map.Entry)i.next();
		    	DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)me.getValue();
		    	if(dmLoaiThuoc.getDmloaithuocTen() == dmloaithuocTen || dmLoaiThuoc.getDmloaithuocTen().equals(dmloaithuocTen)){
		    		hasTenLoai = true;
		    		dmLoaiThuoc_Finded = dmLoaiThuoc;
		    		break;
		    	}	    		
		    }	
		    if(hasTenLoai){
		    	pnk.setDmloaithuocMaso(dmLoaiThuoc_Finded);
		    }else{
		    	pnk.setDmloaithuocMaso(null);
		    }
		    listDmThuocs.clear();
		    hmDmThuoc.clear();
		    refreshDmThuoc();
		    cnk.setDmthuocMaso(null);
			listDmThuocs.clear();
			hmDmThuoc.clear();
			refreshDmThuoc();
			cnk.setDmthuocMaso(new DmThuoc());
			cnk.setCtnhapkhoSodangky(null);
			giacuoi = null;
			giadauthau = null;
			cnk.setCtnhapkhoSodangky(null);
			cnk.setDmquocgiaMaso(new DmQuocGia());
			cnk.setDmnhasanxuatMaso(new DmNhaSanXuat());
			cnk.setCtnhapkhoSoluong(null);
			cnk.setCtnhapkhoDongia(0.0);
			cnk.setCtnhapkhoDongiaban(null);
			setTonkhoHT(null);
			cnk.setCtnhapkhoLo(null);
			//logger.info("-----END onblurTenLoaiThuocAction()-----");
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
	//End Tho add
	
	public void reset() {
		pnk = new PhieuNhapKho();
		cnk = new CtNhapKho();
		khoa = new DmKhoa();
		ngayHt = Utils.getCurrentDate();
		listCtNhapKho = new ArrayList<CtNhapKho>();
		selected = new CtNhapKho();
		ngayLap = Utils.getCurrentDate();
		
		ngayHD="";
		thangHD = "";
		namHD = "";
		
		
		maPhieu = "";
		namNhap = "" + Calendar.getInstance().get(Calendar.YEAR);
		isUpdate = false;
		count = listCtNhapKho.size();
		thanhTien = new Double("0");
		thueGtgt = new Double("0");
		pnk.setPhieunhapkhoMucthue(thueGtgt); // bao.ttc: set Thue GTGT default
		thanhTienThue = new Double("0");
		
		DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
		try {
			DtDmNhanVien nvCn = nvDelegate.findByNd(identity.getUsername());
			if (nvCn != null) {
				pnk.setDtdmnhanvienTieplieu(nvCn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		DieuTriUtilDelegate dtDelegate = DieuTriUtilDelegate.getInstance();
		try {
			khoa = (DmKhoa) dtDelegate.findByMa(IConstantsRes.KHOA_KC_MA, "DmKhoa", "dmkhoaMa");
			//logger.info("----- khoa: " + khoa);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		listDmLoaiThuocs.clear();
		listDmThuocs.clear();
		hmLoaiThuoc.clear();
		hmDmThuoc.clear();
		setDmloaithuocTen("");
		refreshDmLoaiThuoc();
		refreshDmThuoc();
		tonkhoHT = new Double(0.0);
		/** ==================== END LY THEM CODE ==================== */

		
	}
	
	public void selectCt(Integer index) {
		//logger.info("-----selectCt()-----");
		//logger.info("-----index: " + index);
		try{
			cnk = new CtNhapKho();
			cnk = (CtNhapKho)BeanUtils.cloneBean(listCtNhapKho.get(index.intValue()));
			DieuTriUtilDelegate dtriDel = DieuTriUtilDelegate.getInstance();
			DmDonViTinh dmDVT = (DmDonViTinh)dtriDel.findByMaSo(cnk.getDmthuocMaso().getDmdonvitinhMaso().getDmdonvitinhMaso(), "DmDonViTinh", "dmdonvitinhMaso");
			if(dmDVT != null){
				cnk.getDmthuocMaso(true).setDmdonvitinhMaso(dmDVT);
			}else{
				cnk.getDmthuocMaso(true).setDmdonvitinhMaso(new DmDonViTinh());
			}
			ngayHD="";
			thangHD = "";
			namHD = "";
			if (cnk.getCtnhapkhoNgayhandung().trim() != null ){
				ngayHD= cnk.getCtnhapkhoNgayhandung() ;
			}
			
			if (cnk.getCtnhapkhoThanghandung().trim() != null ){
				thangHD= cnk.getCtnhapkhoThanghandung() ;
			}
			if (cnk.getCtnhapkhoNamhandung().trim() != null ){
				namHD= cnk.getCtnhapkhoNamhandung() ;
			}
			TonKhoDelegate tkDel = TonKhoDelegate.getInstance();
			if(cnk != null && cnk.getDmthuocMaso(true).getDmthuocMa() != null && cnk.getCtnhapkhoLo() != null){
				TonKho tkHT = tkDel.getTonKhoHienTai(cnk.getDmthuocMaso(true).getDmthuocMaso(), cnk.getCtnhapkhoLo(), cnk.getCtnhapkhoDongia(), khoa.getDmkhoaMaso());
				if(tkHT != null){
					tonkhoHT = tkHT.getTonkhoTon();
				}else{
					tonkhoHT = 0.0;
				}
			}
			giadauthau = cnk.getDmthuocMaso(true).getDmthuocDonGiaDauThau();
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			giacuoi = dtutilDelegate.getGiaCuoi(cnk.getDmthuocMaso(true).getDmthuocMa());
		}catch(Exception er){
			logger.info("Error: " + er.getMessage());
		}
	}
	
	public void deleteCt(Integer index) {
		//logger.info("-----deleteCt()-----");
		//logger.info("-----index: " + index);
		listCtNhapKho.remove(index.intValue());
		count = listCtNhapKho.size();
		for (int i = 0; i < listCtNhapKho.size(); i++) {
			CtNhapKho ct = listCtNhapKho.get(i);
			ct.setCtnhapkhoThutu(i);
		}
		tinhTien();
		//logger.info("-----listCtNhapKho.size(): " + listCtNhapKho.size());
		cnk = new CtNhapKho();
		
		ngayHD="";
		thangHD = "";
		namHD="";
	}
	
	public void themCt() {
		//logger.info("-----themCt()-----");
		if (!isUpdate) {
			try {
				DieuTriUtilDelegate dtriDel = DieuTriUtilDelegate.getInstance();
				DmNhaSanXuat nhasx = (DmNhaSanXuat)dtriDel.findByMaSo(cnk.getDmnhasanxuatMaso().getDmnhasanxuatMaso(), "DmNhaSanXuat", "dmnhasanxuatMaso");
				if(nhasx != null){
					cnk.setDmnhasanxuatMaso(nhasx);
				}else{
					cnk.setDmnhasanxuatMaso(new DmNhaSanXuat());
				}
				
				DmQuocGia nuocsx = (DmQuocGia)dtriDel.findByMaSo(cnk.getDmquocgiaMaso().getDmquocgiaMaso(), "DmQuocGia", "dmquocgiaMaso");
				if(nuocsx != null){
					cnk.setDmquocgiaMaso(nuocsx);
				}else{
					cnk.setDmquocgiaMaso(new DmQuocGia());
				}
				
				CtNhapKho ctNew = (CtNhapKho) BeanUtils.cloneBean(cnk);
				ctNew.setCtnhapkhoNamnhap(namNhap);
				
				if (ngayHD !=""){
					if(ngayHD.length()==1){
						ngayHD = "0"+ngayHD;
					}
					ctNew.setCtnhapkhoNgayhandung(ngayHD);
				}else{
					ctNew.setCtnhapkhoNgayhandung("");
				}
				if (thangHD !=""){
					if(thangHD.length()==1){
						thangHD = "0"+thangHD;
					}
					ctNew.setCtnhapkhoThanghandung(thangHD);
				}else{
					ctNew.setCtnhapkhoThanghandung("");
				}
				if (namHD !=""){
					ctNew.setCtnhapkhoNamhandung(namHD);
				}else{
					ctNew.setCtnhapkhoNamhandung("");
				}
				if(ctNew.getCtnhapkhoSodangky() == null){
					ctNew.setCtnhapkhoSodangky("");
				}
				
				// 20120606 bao.ttc: lam tron Don gia ban
				Double donGiaBanChuaLamTron = ( ctNew.getCtnhapkhoDongiaban() == null ? new Double(0.0) : ctNew.getCtnhapkhoDongiaban() );
				//logger.info("-----##### Don gia ban OLD: " + donGiaBanChuaLamTron);
				ctNew.setCtnhapkhoDongiaban(Utils.roundDoubleTwoDecimals(donGiaBanChuaLamTron));
				//logger.info("-----##### Don gia ban NEW: " + ctNew.getCtnhapkhoDongiaban());
				
				// tinh don gian ban, don gia co' VAT
				Double mucThue = pnk.getPhieunhapkhoMucthue();
				if (mucThue != null && mucThue > 0){
					Double donGiaBan = ctNew.getCtnhapkhoDongiaban();
					Double donGia = (mucThue / 100) * donGiaBan + donGiaBan;
					
					// 20120606 bao.ttc: lam tron Don gia
					//logger.info("-----##### Don gia OLD: " + donGia);
					ctNew.setCtnhapkhoDongia(Utils.roundDoubleTwoDecimals(donGia));
					//logger.info("-----##### Don gia NEW: " + ctNew.getCtnhapkhoDongia());
					
				}else{
					ctNew.setCtnhapkhoDongia(ctNew.getCtnhapkhoDongiaban());
				}
				
				if (ctNew.getCtnhapkhoThutu() != null) {
					//logger.info("-----cap nhat ct nhap kho: " + ctNew.getCtnhapkhoThutu());
					listCtNhapKho.set(ctNew.getCtnhapkhoThutu(), ctNew);
				} else {
					//logger.info("-----them moi ct nhap kho");
					ctNew.setCtnhapkhoThutu(listCtNhapKho.size());
					listCtNhapKho.add(ctNew);
				}
				
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
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			tinhTien();
			count = listCtNhapKho.size();
			cnk = new CtNhapKho();
			
			ngayHD="";
			thangHD = "";
			namHD="";
			giacuoi = null;
			giadauthau = null;
		}
	}
	
	public Double getTonkhoHT(){
		return this.tonkhoHT;
	}
	
	public void setTonkhoHT(Double tonkhoHT){
		this.tonkhoHT = tonkhoHT;
	}
	
	public void onblurTonLoThuocAction(){
		//get ton kho hien tai ung voi ma thuoc, so lo, don gia va setTonkhoHT(tonkhoHT); 
		TonKhoDelegate tkDel = TonKhoDelegate.getInstance();
		if(cnk != null && cnk.getDmthuocMaso(true).getDmthuocMa() != null && cnk.getCtnhapkhoLo() != null){
			TonKho tkHT = tkDel.getTonKhoHienTai(cnk.getDmthuocMaso(true).getDmthuocMaso(), cnk.getCtnhapkhoLo(), cnk.getCtnhapkhoDongiaban(), khoa.getDmkhoaMaso());
			if(tkHT != null){
				tonkhoHT = tkHT.getTonkhoTon();
			}else{
				tonkhoHT = 0.0;
			}
		}		
	}
	
	public void end() {
		//logger.info("-----end()-----");
		
		//@Trungnh update to fix bug duplicate Hoadon
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (ngayLap != "") {
			try {
				Date dNgayHoaDon = df.parse(ngayLap);
				pnk.setPhieunhapkhoNgayhoadon(dNgayHoaDon);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		//duyen.lp bo qua check so hop dong va ngay hop dong
//		PhieuNhapKhoDelegate pnkDelegate1 = PhieuNhapKhoDelegate.getInstance();
//		if(pnkDelegate1.checkExisted(pnk.getPhieunhapkhoSohoadon(), pnk.getPhieunhapkhoNgayhoadon()))
//		{
//			FacesMessages.instance().add(IConstantsRes.DUPLICATE_HOADON);
//			return;
//		}
		
		if (listCtNhapKho.size() > 0) {
			yteLog = new YteLog();
			listDataLog="";
			if (!isUpdate) {				
				
				pnk.setPhieunhapkhoNgaygiocn(new Date());//Tho update - khong dung ngayHt, vi can luu ca ngay gio
								
				PhieuNhapKhoDelegate pnkDelegate = PhieuNhapKhoDelegate.getInstance();
				removeInfo();
				double tt = 0;
				for (int i = 0; i < listCtNhapKho.size(); i++) {
					CtNhapKho ct = listCtNhapKho.get(i);
					tt += ct.getCtnhapkhoSoluong().doubleValue() * ct.getCtnhapkhoDongia();
					//luu log thong tin thuoc
					listDataLog += "Ma thuoc:"+ ct.getDmthuocMaso(true).getDmthuocMa()+
							" Don gia: "+ ct.getCtnhapkhoDongia()+ " Don gia ban: "+ ct.getCtnhapkhoDongiaban() + 
							" So luong: "+ ct.getCtnhapkhoSoluong()+
							" So lo: "+ ct.getCtnhapkhoLo()+
							" Nam SX: " + ct.getCtnhapkhoNamnhap()+
							" Nam HD: " + ct.getCtnhapkhoNamhandung()+"\n";
				}
				double thue = (tt * pnk.getPhieunhapkhoMucthue()) / 100;
				pnk.setPhieunhapkhoThanhtien(tt);
				pnk.setPhieunhapkhoThue(thue);
								
				try {
					maPhieu = pnkDelegate.createPhieuNhap(pnk, listCtNhapKho, khoa.getDmkhoaMaso());
					if (!maPhieu.equals("")) {
						FacesMessages.instance().add(IConstantsRes.PHIEUNHAPKHO_THANHCONG, maPhieu);
						isUpdate = true;

//						Luu log he thong
				         yteLog.setForm("B4111_phieunhapkhochinh");
				         yteLog.setUserId(identity == null ? "NULL" : identity.getUsername());
				         yteLog.setObjectId(maPhieu);
				         yteLog.setLogString("Loai thuoc: "+ pnk.getDmloaithuocMaso(true).getDmloaithuocMa()  + 
				         					" Nguon kinh phi: "+ pnk.getDmnguonkinhphiMaso(true).getDmnguonkinhphiMa()+
				         					" NV nhap lieu: "+ pnk.getDtdmnhanvienCn(true).getDtdmnhanvienMa()+
				         					" So HD: "+ pnk.getPhieunhapkhoSohoadon()+
				         					" Kho nhap: "+ pnk.getDtdmkhoMaso(true).getDtdmkhoMa()+
				         					" Noi ban: "+ pnk.getDtdmnoibanMa(true).getDmnhacungcapMa()+
				         					" Thue GTGT: "+ thueGtgt+
				         					" Chuong trinh: "+ pnk.getDmnctMaso(true).getDmnctMa()+
				         					" Thanh tien: "+ thanhTien);
				         yteLog.setDateTime(new Date());
				         yteLog.setListData(listDataLog);

				         yteLogDele.create(yteLog);
					} else {
						FacesMessages.instance().add(IConstantsRes.PHIEUNHAPKHO_THATBAI, maPhieu);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		} else {
			FacesMessages.instance().add(IConstantsRes.PHIEUNHAPKHO_DMT_NULL);
		}
	}
	
	public void loadPhieuNhap() {
		//logger.info("-----loadPhieuNhap()-----");
		if (!maPhieu.equals("")) {
			PhieuNhapKhoDelegate pnkDelegate = PhieuNhapKhoDelegate.getInstance();
			CtNhapKhoDelegate ctDelegate = CtNhapKhoDelegate.getInstance();
			try {
				PhieuNhapKho pnk_temp = pnkDelegate.findByPhieunhapkhoMa(maPhieu);
				if (pnk_temp != null) {
					maPhieu = pnk_temp.getPhieunhapkhoMa();
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					ngayHt = df.format(pnk_temp.getPhieunhapkhoNgaygiocn());
					ngayLap = df.format(pnk_temp.getPhieunhapkhoNgayhoadon());
					listCtNhapKho = (ArrayList<CtNhapKho>) ctDelegate.findByPhieuNhapKho(maPhieu);
					count = listCtNhapKho.size();
					isUpdate = true;
					
					pnk = pnk_temp;
				} else {
					FacesMessages.instance().add(IConstantsRes.PHIEUNHAPKHO_NULL, maPhieu);
				}
				tinhTien();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
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
	int index= 0;
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeKC="D01_Phieunhapkhothuocthuong";
		//logger.info("Vao Method XuatReport D01_Phieunhapkhothuocthuong");
		String baocao1=null;

		if (isUpdate) {
			
		try {
			//logger.info("bat dau method XuatReport ");
			String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/phieunhapkho_01.jrxml";
			//logger.info("da thay file templete 5" + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			//logger.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			
			
			PhieuNhapKhoDelegate pnkDelegate = PhieuNhapKhoDelegate.getInstance();
			pnk = pnkDelegate.findByPhieunhapkhoMa(maPhieu);
			params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			//logger.info(String.format("-----tenSo: %s", params.get("tenSo")));
			params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			//logger.info(String.format("-----tenDonVi: %s", params.get("tenDonVi")));
			params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			//logger.info(String.format("-----dienThoaiDonVi: %s", params.get("dienThoaiDonVi")));
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			params.put("phieuSo", maPhieu.substring(maPhieu.length() - 5));
			//logger.info("phieuSo: " + params.get("phieuSo"));
			
			params.put("loaiMa", pnk.getDmloaithuocMaso().getDmloaithuocMa());
			//logger.info("loaiMa: " + params.get("loaiMa"));
			
			params.put("tenLoai", pnk.getDmloaithuocMaso().getDmloaithuocTen());
			//logger.info("tenLoai: " + params.get("tenLoai"));
			
			
			params.put("ngayHt", ngayLap);
			//logger.info("ngayHt: " + params.get("ngayHt"));
			
			params.put("tenNhaCungCap", pnk.getDtdmnoibanMa().getDmnhacungcapTen());
			//logger.info("tenNhaCungCap: " + params.get("tenNhaCungCap"));
			
			params.put("nvCn", pnk.getDtdmnhanvienCn(true).getDtdmnhanvienTen());
			//logger.info("nvCn: " + params.get("nvCn"));
			
			params.put("nguonCt", pnk.getDmnctMaso().getDmnctTen());
			//logger.info("nguonCt: " + params.get("nguonCt"));
			
			params.put("chungTu", pnk.getPhieunhapkhoChungtu());
			//logger.info("chungTu: " + params.get("chungTu"));
			
			params.put("namNhap", namNhap);
			//logger.info("namNhap: " + params.get("namNhap"));
			
			params.put("namNhap", namNhap);
			//logger.info("namNhap: " + params.get("namNhap"));
			
			params.put("khoa", khoa.getDmkhoaTen());
			//logger.info("khoa: " + params.get("khoa"));
			
			params.put("nguonKp", pnk.getDmnguonkinhphiMaso().getDmnguonkinhphiTen());
			//logger.info("nguonKp: " + params.get("nguonKp"));
			
			params.put("maKp", pnk.getDmnguonkinhphiMaso().getDmnguonkinhphiMa());
			//logger.info("maKp: " + params.get("maKp"));
			
			params.put("maPhieu", maPhieu);
			//logger.info("maPhieu: " + params.get("maPhieu"));
			
			params.put("mucThue", pnk.getPhieunhapkhoMucthue().toString());
			//logger.info("mucThue: " + params.get("mucThue"));
			
			params.put("hoadon", pnk.getPhieunhapkhoSohoadon());
			
			
			//logger.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			   //logger.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	//logger.info(e.getMessage());
			    }
			    jasperPrintKC =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintKC,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"duocpham/", "pdf","D01_Phieunhapkhothuocthuong");
			    reportPathKC=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    //logger.info("duong dan file xuat report :" + baocao1);
			    //logger.info("duong dan -------------------- :"+reportPathKC);
			    index+=1;
			    //logger.info("Index :" + index);
			    if(conn != null) conn.close();
		}catch (Exception e) {
			logger.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
		}
		//logger.info("Thoat Method XuatReport");
	}
	
	/**
	 * 
	 * @return
	 */
	public String troVe(){
		try {
			//logger.info("***** troVe()");
			return "B4111_phieunhapkhochinh";
		} 		
		catch (Exception e) {
			//logger.info("***** End exception = " + e);    	
		} 
		return null;
	}
	
	private void removeInfo() {
		if (pnk.getDmloaithuocMaso().getDmloaithuocMaso() == null) {
			pnk.setDmloaithuocMaso(null);
		}
		if (pnk.getDmnctMaso().getDmnctMaso() == null) {
			pnk.setDmnctMaso(null);
		}
		if (pnk.getDmnguonkinhphiMaso().getDmnguonkinhphiMaso() == null) {
			pnk.setDmnguonkinhphiMaso(null);
		}
		if (pnk.getDtdmkhoMaso().getDtdmkhoMaso() == null) {
			pnk.setDtdmkhoMaso(null);
		}
		if (pnk.getDtdmnhanvienCn().getDtdmnhanvienMaso() == null) {
			pnk.setDtdmnhanvienCn(null);
		}
		if (pnk.getDtdmnhanvienTieplieu().getDtdmnhanvienMaso() == null) {
			pnk.setDtdmnhanvienTieplieu(null);
		}
		if (pnk.getDtdmnoibanMa().getDmnhacungcapMaso() == null) {
			pnk.setDtdmnoibanMa(null);
		}
		
		for (CtNhapKho ct : listCtNhapKho) {
			if (ct.getDmnhasanxuatMaso().getDmnhasanxuatMaso() == null) {
				ct.setDmnhasanxuatMaso(null);
			}
			if (ct.getDmquocgiaMaso().getDmquocgiaMaso() == null) {
				ct.setDmquocgiaMaso(null);
			}
			if (ct.getDmthuocMaso().getDmthuocMaso() == null) {
				ct.setDmthuocMaso(null);
			}
		}
	}
	
	public TonKho createTonKho(CtNhapKho ct) {
		TonKho tk = new TonKho();
		tk.setDmnctMaso(pnk.getDmnctMaso());
		tk.setDmnguonkinhphiMaso(pnk.getDmnguonkinhphiMaso());
		tk.setDmNhaCungCap(pnk.getDtdmnoibanMa());
		tk.setDmnhasanxuatMaso(ct.getDmnhasanxuatMaso());
		tk.setDmquocgiaMaso(ct.getDmquocgiaMaso());
		tk.setDmthuocMaso(ct.getDmthuocMaso());
		tk.setDtdmkhoMaso(pnk.getDtdmkhoMaso());
		tk.setDtdmnhanvienCn(pnk.getDtdmnhanvienCn());
		tk.setTonkhoDongia(ct.getCtnhapkhoDongia());
		tk.setTonkhoDongiaban(ct.getCtnhapkhoDongiaban());
		tk.setTonkhoHienthi(true);
		tk.setTonkhoLo(ct.getCtnhapkhoLo());
		//tk.setTonkhoMucthue(pnk.getPhieunhapkhoMucthue());
		tk.setTonkhoNhap(ct.getCtnhapkhoSoluong());
		tk.setTonkhoXuat(null);
		tk.setTonkhoTra(null);
		tk.setTonkhoNgayhandung(ct.getCtnhapkhoNgayhandung());
		tk.setTonkhoThanghandung(ct.getCtnhapkhoThanghandung());
		tk.setTonkhoNamhandung(ct.getCtnhapkhoNamhandung());
		tk.setTonkhoNamnhap(ct.getCtnhapkhoNamnhap());
		tk.setTonkhoSodangky(ct.getCtnhapkhoSodangky());
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (ngayHt != "") {
			try {
				Date dNgayCn = df.parse(ngayHt);
				tk.setTonkhoNgaygiocn(dNgayCn);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return tk;
	}
	
	private void tinhTien() {
		if (pnk == null){
			return;
		}
		if (listCtNhapKho == null){
			return;
		}
		//logger.info("-----tinhTien()-----");
		thanhTien = new Double("0");
		for (CtNhapKho ctnk : listCtNhapKho) {
			Double sl = ctnk.getCtnhapkhoSoluong();
			Double dg = ctnk.getCtnhapkhoDongiaban();
			thanhTien += sl * dg;
		}
		Double mucThue = pnk.getPhieunhapkhoMucthue();
		if (mucThue == null) {
			mucThue = new Double("0");
		}
		thueGtgt = (thanhTien * mucThue) / 100;
		thanhTienThue = thanhTien + thueGtgt;
		//logger.info("-----thueGtgt: " + thueGtgt);
		//logger.info("-----thanhTienThue: " + thanhTienThue);
	}
	
	public void tinhTienThue() {
		
		if (pnk == null){
			return;
		}
		
		
		//logger.info("-----tinhTienThue()-----");
		Double mucThue = pnk.getPhieunhapkhoMucthue();
		if (mucThue == null) {
			mucThue = new Double("0");
		}
		thueGtgt = (thanhTien * mucThue) / 100;
		thanhTienThue = thanhTien + thueGtgt;
		//logger.info("-----thueGtgt: " + thueGtgt);
		//logger.info("-----thanhTienThue: " + thanhTienThue);
	}

	public void setTenChuongTrinh(String tenChuongTrinh) {
		this.tenChuongTrinh = tenChuongTrinh;
	}

	public String getTenChuongTrinh() {
		return tenChuongTrinh;
	}

	public PhieuNhapKho getPnk() {
		return pnk;
	}

	public void setPnk(PhieuNhapKho pnk) {
		this.pnk = pnk;
	}

	public CtNhapKho getCnk() {
		return cnk;
	}

	public void setCnk(CtNhapKho cnk) {
		this.cnk = cnk;
	}

	public ArrayList<CtNhapKho> getListCtNhapKho() {
		return listCtNhapKho;
	}

	public void setListCtNhapKho(ArrayList<CtNhapKho> listCtNhapKho) {
		this.listCtNhapKho = listCtNhapKho;
	}

	public CtNhapKho getSelected() {
		return selected;
	}

	public void setSelected(CtNhapKho selected) {
		this.selected = selected;
	}

	public String getNgayHt() {
		return ngayHt;
	}

	public void setNgayHt(String ngayHt) {
		this.ngayHt = ngayHt;
	}

	public String getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(String ngayLap) {
		this.ngayLap = ngayLap;
	}	

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		PhieuNhapKhoAction.logger = logger;
	}

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	public String getThangHD() {
		return thangHD;
	}

	public void setThangHD(String thangHD) {
		this.thangHD = thangHD;
	}

	public String getNamHD() {
		return namHD;
	}

	public void setNamHD(String namHD) {
		this.namHD = namHD;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNamNhap() {
		return namNhap;
	}

	public void setNamNhap(String namNhap) {
		this.namNhap = namNhap;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
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

	public DmKhoa getKhoa() {
		return khoa;
	}

	public void setKhoa(DmKhoa khoa) {
		this.khoa = khoa;
	}

	public String getMaPhieu() {
		return maPhieu;
	}

	public void setMaPhieu(String maPhieu) {
		this.maPhieu = maPhieu;
	}

	public Double getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(Double thanhTien) {
		this.thanhTien = thanhTien;
	}

	public Double getThueGtgt() {
		return thueGtgt;
	}

	public void setThueGtgt(Double thueGtgt) {
		this.thueGtgt = thueGtgt;
	}

	public Double getThanhTienThue() {
		return thanhTienThue;
	}

	public void setThanhTienThue(Double thanhTienThue) {
		this.thanhTienThue = thanhTienThue;
	}
	
	public String getNgayHD() {
		return ngayHD;
	}
	
	public void setNgayHD(String ngayHD) {
		this.ngayHD = ngayHD;
	}
	
	public Double getGiadauthau() {
		return giadauthau;
	}

	public void setGiadauthau(Double giadauthau) {
		this.giadauthau = giadauthau;
	}
	
	public Double getGiacuoi() {
		return giacuoi;
	}

	public void setGiacuoi(Double giacuoi) {
		this.giacuoi = giacuoi;
	}
}


package com.iesvn.yte.dieutri.duocpham.action;

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
import org.jboss.seam.ScopeType;
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
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
import com.iesvn.yte.dieutri.delegate.CtXuatKhoDelegate;
import com.iesvn.yte.dieutri.delegate.DmLoaiPhieuDelegate;
import com.iesvn.yte.dieutri.delegate.DmLoaiThuocDelegate;
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
import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmLoaiPhieu;
import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.entity.DmThuoc;
import com.iesvn.yte.report.XuatReportDuocPham;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;
import com.sun.org.apache.commons.beanutils.BeanUtils;


@Scope(ScopeType.CONVERSATION)
@Name("B4163_Phieudutrulanhthuoc")
@Synchronized(timeout = 6000000)
public class PhieuDuTruLanhthuocAction  implements Serializable {
	@In(required = false)
	@Out(required = false)
	private String tenChuongTrinh;
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
	private static final long serialVersionUID = 10L;

	private static Logger log = Logger.getLogger(PhieuDuTruLanhthuocAction.class);
	@DataModel
	private ArrayList<CtXuatKhoEx> listCtDtXuatKhoEx;
	@DataModelSelection
	private CtXuatKhoEx selected=new CtXuatKhoEx();

	private PhieuXuatKho phieuXuat;
	private String maPhieu;
	private String ngayXuat;
	private String dmtMa;
	private Double tonkho;
	private Double xuat;
	private String tonkhoMa;
	private Integer updateItem = -1;
	private Integer count;
	private String ngayHienTai;
	private String nguonMa;
	private String kpMa;
	private String malk;
	private Double tongTien;
	
	String dmKhoXuat = "";
	String dmKhoNhan = "";
	
	private String resultReportFileName;
	private String resultReportName;	
	private String loaiFileXuat;
	@Out(required = false)
	private String isReport = "false";
	private String isUpdate;
	
	private YteLogDelegate yteLogDele = YteLogDelegate.getInstance();
    private YteLog yteLog;
    private String listDataLog;
	
	//Tho add
	private String dmLoaiTen="";
	private DmLoaiThuocDelegate dmLoaiThuocDelegate;
	private List<SelectItem> listDmLoaiThuocs = new ArrayList<SelectItem>();
	HashMap<String, DmLoaiThuoc> hmLoaiThuoc = new HashMap<String, DmLoaiThuoc>();
	HashMap<String,DmThuoc> hmDmThuoc = new HashMap<String,DmThuoc>();
	private DmLoaiPhieuDelegate dmLoaiPhieuDelegate;
	HashMap<String,DmLoaiPhieu> hmDmLoaiPhieu = new HashMap<String,DmLoaiPhieu>();
	private String dmLoaiPhieuMa ="";
	String loaiPhieu = "";
	private List<SelectItem> listDmLoaiPhieus = new ArrayList<SelectItem>();

	@Restrict("#{s:hasRole('NV_KhoaDuoc') or s:hasRole('QT_HT_Duoc')}")
	@Begin(join = true)
	public String init(String khoNhan, String khoXuat) {
		log.info("***** init() *****");
		
		reset();
		if ("BHYT".equals(khoNhan)){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoBHYT;
		}else if ("KC".equals(khoNhan)){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoChinh;
		}else if ("TE".equals(khoNhan)){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoTE;
		}else if ("NT".equals(khoNhan)){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoNoiTru;
		}
		log.info("***** Ten chuong trinh: "+tenChuongTrinh);
		dmKhoXuat = khoXuat;
		dmKhoNhan = khoNhan;
		
		listDmThuocs.clear();
		listDmLoaiThuocs.clear();
		hmLoaiThuoc.clear();
		hmDmThuoc.clear();
		
		refreshDmLoaiThuoc();
		
		return "QuanLyKhoChinh_NhapKhoChinh_DuTruLanhThuoc";
	}
	
	@End
	public void endConversation(){
		
	}
	
	@Factory("listCtDtXuatKhoEx")
	public void createTable() {
		log.info(" *****createTable() *****");
		listCtDtXuatKhoEx = new ArrayList<CtXuatKhoEx>();
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
	//Tho edit
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
	//Tho edit
	public void onblurTenThuocAction(){
		log.info("-----BEGIN onblurTenThuocAction()-----"+dmtTen);
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
		    	if(dmThuoc.getDmthuocTen().trim() == dmtTen || dmThuoc.getDmthuocTen().trim().equals(dmtTen)){
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
	//Tho edit
	public void refreshDmThuoc(){
		listDmThuocs.clear();
		hmDmThuoc.clear();
		dmThuocDelegate = DmThuocDelegate.getInstance();
				
		String loaiMa = "";
		if(loaiPhieu != null || !loaiPhieu.equals(""))
		{
			if (phieuXuat.getDmloaithuocMaso(true).getDmloaithuocMa() != null ){
				if( phieuXuat.getDmloaithuocMaso(true).getDmloaithuocMa().toUpperCase().equals("TD"))
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
					loaiMa = phieuXuat.getDmloaithuocMaso(true).getDmloaithuocMa();
				}	
			}	
		}
		System.out.println("loaiMa: "+loaiMa);
		System.out.println("Kho: "+phieuXuat.getDmkhoaXuat().getDmkhoaMaso());
		
		List<DmThuoc> lstDmThuoc = new ArrayList<DmThuoc>();
		if(!loaiMa.equals("") && !loaiPhieu.equals("")){
			lstDmThuoc = dmThuocDelegate.findByLoaiPhanLoaiThuocNhomThuocDVTKho(loaiMa, "", phieuXuat.getDmkhoaXuat().getDmkhoaMaso());
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
	//Begin Tho add
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
		log.info("-----BEGIN onblurMaLoaiAction()-----"+phieuXuat.getDmloaithuocMaso().getDmloaithuocMa());
		String loaihang_ma = phieuXuat.getDmloaithuocMaso().getDmloaithuocMa();
		DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)hmLoaiThuoc.get(loaihang_ma.toUpperCase());
		if(dmLoaiThuoc != null) {
			setDmLoaiTen(dmLoaiThuoc.getDmloaithuocTen());
		}
		else {
			setDmLoaiTen("");			
		}
		listDmLoaiPhieus.clear();
		refreshDmLoaiPhieu();
		setDmtMa("");
	    setDmtTen("");
	    setLoaiPhieu("");
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
		    phieuXuat.setDmloaithuocMaso(dmLoaiThuoc_Find);
		}
	    listDmLoaiPhieus.clear();
		refreshDmLoaiPhieu();
	    setDmtMa("");
	    setDmtTen("");
	    setLoaiPhieu("");
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
		if(phieuXuat != null && phieuXuat.getDmloaithuocMaso() != null){
			List<DmLoaiPhieu> lstDmLoaiPhieus = dmLoaiPhieuDelegate.findAll();
			if(lstDmLoaiPhieus !=null && lstDmLoaiPhieus.size()>0)
			{
				for(DmLoaiPhieu o: lstDmLoaiPhieus){
					hmDmLoaiPhieu.put(o.getDmloaiphieuMa(), o);
					if(phieuXuat.getDmloaithuocMaso().getDmloaithuocMaso().equals(o.getDmloaithuocMaso().getDmloaithuocMaso())){
						listDmLoaiPhieus.add(new SelectItem(o.getDmloaiphieuMa() + " - "+o.getDmloaiphieuTen()));
					}
				}
			}
		}		
	}
	
	public void onblurLoaiPhieuAction(){
		log.info("-----BEGIN onblurLoaiPhieuAction()-----");
		setDmtMa("");
		setDmtTen("");
		hmDmThuoc.clear();
		listDmThuocs.clear();
		String loaiPhieuItem = loaiPhieu;
		dmLoaiPhieuMa = loaiPhieuItem.substring(0, loaiPhieuItem.indexOf(" - ")).trim();
		refreshDmThuoc();
		log.info("-----END onblurLoaiPhieuAction()-----");
	}
	//End Tho add
	
	
	/**
	 * Xoa chi tiet xuat
	 */
	public void deleteCt() {
		log.info(" *****deleteCt() *****");
		//int index = selected.getCtXuatKho().getCtxuatkhoThutu().intValue() - 1;
		listCtDtXuatKhoEx.remove(selected);
		this.count = listCtDtXuatKhoEx.size();
		tinhTien();
	}
	
	public String getDmKhoNhan() {
		return dmKhoNhan;
	}

	public void setDmKhoNhan(String dmKhoNhan) {
		this.dmKhoNhan = dmKhoNhan;
	}

	/**
	 * Hien thi chi tiet xuat
	 */
	public void selectCt(Integer index) {
		log.info(" *****selectCt() *****");
		log.info(String.format(" *****selected: %s", selected.getCtXuatKho().getCtxuatkhoThutu()));
		//int index = selected.getCtXuatKho().getCtxuatkhoThutu().intValue() - 1;
		TonKho tk = selected.getTonkhoXuat();
		CtXuatKho ctx = selected.getCtXuatKho();
		updateItem = index;
		tonkhoMa = tk.getTonkhoMa().toString();
		log.info(" *****ton kho: " + tonkho);
		
		//DecimalFormat df = new DecimalFormat("###.##");
		tonkho = tk.getTonkhoTon();
		dmtMa = ctx.getDmthuocMaso().getDmthuocMa();
		dmtTen = ctx.getDmthuocMaso().getDmthuocTen();
		xuat = ctx.getCtxuatkhoSoluong();
		
	}

	/**
	 * Cap nhat chi tiet phieu xuat
	 */
	public void tiepTucNhap() {
		log.info(" *****tiepTucNhap() *****");
		log.info(String.format(" *****index: %s", updateItem));
		
		log.info("tonkhoMa:"+tonkhoMa);
		log.info("xuat:"+xuat);
		log.info("dmtMa:"+dmtMa);
		log.info("updateItem:"+updateItem);
		log.info("tonkho:"+tonkho);
		
		
		if (xuat == null || xuat.equals("") || tonkho  == null || tonkho.equals("")){
			return;
		}
		      
		if ("".equals(tonkhoMa) && tonkhoMa == null) {
			log.info(" *****tonkho ma is null.");
		} else {
			log.info(String.format(" *****tonkho ma: %s", tonkhoMa));
			TonKho tk = null;
			
			TonKhoDelegate tkDelegate;
			try {
				tkDelegate = TonKhoDelegate.getInstance();
				
				tk = tkDelegate.find(Integer.valueOf(tonkhoMa));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			CtXuatKho ctx = new CtXuatKho();
			
			Double slXuat = new Double("0");
			if (updateItem.equals(-1)) {
				for (int i = 0; i < listCtDtXuatKhoEx.size(); i++) {
					CtXuatKho ctxk = listCtDtXuatKhoEx.get(i).getCtXuatKho();
					if (malk.equals(ctxk.getCtxuatkhoMalk())) {
						log.info(" *****malk " + malk);
						slXuat += ctxk.getCtxuatkhoSoluong();
						updateItem = i;
					}
				}
			}
			slXuat += Double.valueOf(xuat);
			ctx.setCtxuatkhoSoluong(slXuat);
			CtXuatKhoEx ctxEx = createCtXuatKho(ctx, tk);
			log.info(" *****xuat: " + slXuat);
			
			if (updateItem.equals(-1)) {
				log.info(" *****them moi ct");
				listCtDtXuatKhoEx.add(ctxEx);
			} else {
				log.info(" *****Cap nhat ct *****");
				if (tk != null) {
					listCtDtXuatKhoEx.set(updateItem, ctxEx);
				} else {
					FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_TK_NULL, dmtMa);
				}

				log.info(String.format(" *****update ct: %s", ctx.getCtxuatkhoThutu()));
			}

			count = listCtDtXuatKhoEx.size();
			log.info(String.format(" *****listCtXuatKho: %s", listCtDtXuatKhoEx.size()));
			tonkhoMa = "";
			dmtMa = "";
			dmtTen = "";
			tonkho = new Double(0);
			xuat = new Double(0);
			updateItem = -1;
		}
		tinhTien();
	}

	/**
	 * Ket thuc nhap phieu xuat
	 * @return
	 */
	public void end() {
		log.info(" *****end() *****");
		yteLog = new YteLog();
		listDataLog="";
		if (listCtDtXuatKhoEx.size() > 0) {
			log.info(String.format(" *****ngayxuat %s", ngayXuat));
			if (!ngayXuat.equals("")) {
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				try {
					Date dtNgayXuat = df.parse(ngayXuat);
					Calendar cal = Calendar.getInstance();
					cal.setTime(dtNgayXuat);
					phieuXuat.setPhieuxuatkhoNgaylap(cal.getTime());
				} catch (ParseException e) {
					log.error(String.format(" *****Error: %s", e.toString()));
				}
			}

			
			try {
				double tt = Double.valueOf("0");
				ArrayList<CtXuatKho> listCtx = new ArrayList<CtXuatKho>();
				ArrayList<TonKho> listTkXuat = new ArrayList<TonKho>();
				ArrayList<TonKho> listTkNhan = new ArrayList<TonKho>();
				for (CtXuatKhoEx ctx : listCtDtXuatKhoEx) {
					int i = 0;
					CtXuatKho ct = ctx.getCtXuatKho();
					log.info(String.format(" *****so luong: %s", ct.getCtxuatkhoSoluong()));
					tt += ct.getCtxuatkhoSoluong() * ct.getCtxuatkhoDongia();
					ct.setCtxuatkhoThutu(Short.valueOf("" + (i + 1)));
					listCtx.add(ctx.getCtXuatKho());
					ctx.getTonkhoXuat().setTonkhoMa(null);
					listTkXuat.add(ctx.getTonkhoXuat());
					ctx.getTonkhoNhan().setTonkhoMa(null);
					listTkNhan.add(ctx.getTonkhoNhan());
					i++;
					//luu log thong tin thuoc
					listDataLog += "Ma LK:"+ ct.getCtxuatkhoMalk()+
							" Don gia: "+ct.getCtxuatkhoDongia()+ " Don gia ban: "+ ct.getCtxuatkhoDongiaban() + 
							" So luong: "+ ct.getCtxuatkhoSoluong()+
							" So lo: "+ ct.getCtxuatkhoLo()+
							" Nam SX: " + ct.getCtxuatkhoNamnhap()+
							" Nam HD: " + ct.getCtxuatkhoNamhandung()+ "\n";
				}
				log.info(String.format(" *****thanh tien: %s", tt));
				phieuXuat.setPhieuxuatkhoThanhtien(tt);
				phieuXuat.setPhieuxuatkhoNgaygiocn(new Date());
				phieuXuat.setPhieuxuatkhoLoaiPhieu(loaiPhieu);
				PhieuXuatKhoDelegate pxDelegate = PhieuXuatKhoDelegate.getInstance();
				log.info(String.format(" *****phieu xuat: %s", phieuXuat));

				clearInfor();

				//maPhieu = pxDelegate.createPhieuXuat(phieuXuat, listCtx, listTkNhan, listTkXuat);
				maPhieu = pxDelegate.upDatePhieuXuat(phieuXuat, listCtx);
				
				if (maPhieu != "") {
					resetInfo();
					phieuXuat.setPhieuxuatkhoMa(maPhieu);
					log.info(String.format(" *****result: %s", maPhieu));
					FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THANHCONG, maPhieu);
					//reset();
					displayPhieuXuatKho();
					isUpdate = "1";

//					Luu log he thong
			         yteLog.setForm("B4163_Phieudutrulanhthuoc");
			         yteLog.setUserId(identity == null ? "NULL" : identity.getUsername());
			         yteLog.setObjectId(maPhieu);
			         yteLog.setLogString("Ngay xuat: "+ ngayXuat+
			        		 			" Loai thuoc: "+ phieuXuat.getDmloaithuocMaso(true).getDmloaithuocMa()  + 
			         					" Loai phieu: "+ loaiPhieu+
			         					" Bo phan nhan: "+ phieuXuat.getDmkhoaNhan(true).getDmkhoaMa()+
			         					" Kho xuat: "+ phieuXuat.getDmkhoaXuat(true).getDmkhoaMa()+
			         					" Chuong trinh: "+ nguonMa+ 
			         					" Nguon kinh phi: "+ kpMa+
			         					" Nguoi nhap: "+ phieuXuat.getDtdmnhanvienCn(true).getDtdmnhanvienMa()+
			         					" Nguoi lap: "+ phieuXuat.getDtdmnhanvienPhat(true).getDtdmnhanvienMa()+
			         					" Nguoi ky: "+ phieuXuat.getDtdmnhanvienBacsi(true).getDtdmnhanvienMa()
			        		 			);
			         yteLog.setDateTime(new Date());
			         yteLog.setListData(listDataLog);

			         yteLogDele.create(yteLog);
				} else {
					FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THATBAI);
				}
				
			} catch (Exception e) {
				FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THATBAI);
				log.error(String.format(" *****Error: %s", e.toString()));
			}
		} else {
			FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_DMT_NULL);
		}
		
	}
	
	@Destroy
	public void destroy() {
		log.info(" *****destroy() *****");
	}
	
	/**
	 * Hien thi phieu xuat len giao dien
	 */
	public void displayPhieuXuatKho() {
		log.info(" *****displayPhieuXuatKho() *****");
		if (!maPhieu.equals("")) {
			log.info(String.format(" *****Phieu xuat ma: %s", this.maPhieu));
			listCtDtXuatKhoEx.clear();
			try {
				PhieuXuatKhoDelegate pxkWS = PhieuXuatKhoDelegate.getInstance();
				CtXuatKhoDelegate ctxWS = CtXuatKhoDelegate.getInstance();
				//phieuXuat = pxkWS.findByPhieuxuatkhoMa(maPhieu);
				phieuXuat = pxkWS.findPhieuXuatKhoByKhoaNhan(maPhieu, phieuXuat.getDmkhoaNhan(true).getDmkhoaMaso());
				if (phieuXuat != null) {
					maPhieu = phieuXuat.getPhieuxuatkhoMa();
					loaiPhieu = phieuXuat.getPhieuxuatkhoLoaiPhieu();
					dmLoaiTen = phieuXuat.getDmloaithuocMaso(true).getDmloaithuocTen();
					resetInfo();
					log.info(String.format(" *****find ct xuat kho by phieu xuat kho ma %s", maPhieu));
					for (CtXuatKho ct : ctxWS.findByphieuxuatkhoMa(phieuXuat.getPhieuxuatkhoMa())) {
						SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
						ngayXuat = df.format(phieuXuat.getPhieuxuatkhoNgaylap());
						log.info("Ct xuat kho ma: " + ct.getCtxuatkhoMa());
						log.info("getPhieuxuatkhoNgaylap: " + phieuXuat.getPhieuxuatkhoNgaylap());
						CtXuatKhoEx ctxEx = new CtXuatKhoEx();
						ctxEx.setCtXuatKho(ct);
						listCtDtXuatKhoEx.add(ctxEx);
					}
					count = listCtDtXuatKhoEx.size();
					isUpdate = "1";
				} else {
					FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_NULL, maPhieu);
					reset();
				}
				tinhTien();
			} catch (Exception e) {
				FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_NULL, maPhieu);
				reset();
				log.error(String.format(" *****Error: %s", e));
			}
		}
		
	}

	/**
	 * Tao chi tiet xuat kho tu ton kho
	 * @param ctx
	 * @param tk
	 * @return
	 */
	private CtXuatKhoEx createCtXuatKho(CtXuatKho ctx, TonKho tk) {
		log.info(String.format(" *****createCtXuatKho() *****"));
		log.info(String.format(" *****Ct xuat kho (input): %s", ctx.getCtxuatkhoMa()));
		log.info(String.format(" *****ton kho (input): %s", tk.getTonkhoMa()));
		
		TonKho tkXuat = null;
		try {
			tkXuat = (TonKho) BeanUtils.cloneBean(tk);
			tkXuat.setTonkhoNgaygiocn(new Date());
			tkXuat.setTonkhoMa(null);
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
		if (tkXuat != null) {
			tkXuat.setTonkhoXuat(ctx.getCtxuatkhoSoluong());
			tkXuat.setTonkhoTra(null);
			tkXuat.setTonkhoNhap(null);
			tkXuat.setTonkhoMa(tk.getTonkhoMa());
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
			tkNhan.setTonkhoNhap(ctx.getCtxuatkhoSoluong());
			tkNhan.setTonkhoTra(null);
			tkNhan.setTonkhoXuat(null);
		}
		
		ctx.setDmnguonkinhphiMaso(tk.getDmnguonkinhphiMaso());
		ctx.setDmquocgiaMaso(tk.getDmquocgiaMaso());
		ctx.setDmthuocMaso(tk.getDmthuocMaso());
		ctx.setDmnhasanxuatMaso(tk.getDmnhasanxuatMaso());
		ctx.setDmnctMaso(tk.getDmnctMaso());
		ctx.setCtxuatkhoDongia(tk.getTonkhoDongia());
		ctx.setCtxuatkhoDongiaban(tk.getTonkhoDongiaban());
		ctx.setCtxuatkhoLo(tk.getTonkhoLo());
		ctx.setCtxuatkhoMalk(tk.getTonkhoMalienket());
		ctx.setCtxuatkhoNamhandung(tk.getTonkhoNamhandung());
		ctx.setCtxuatkhoNamnhap(tk.getTonkhoNamnhap());
		ctx.setCtxuatkhoNgaygiocn(new Date());
		ctx.setCtxuatkhoNgayhandung(tk.getTonkhoNgayhandung());
		ctx.setCtxuatkhoThanghandung(tk.getTonkhoThanghandung());
		ctx.setPhieuxuatkhoMa(phieuXuat);
		ctx.setCtxuatkhoNgaygiocn(new Date());
		ctx.setTonKhoMa(tk.getTonkhoMa());
		log.info(String.format(" *****ct xuat kho: %s", ctx.getCtxuatkhoMa()));
		
		CtXuatKhoEx ctxEx = new CtXuatKhoEx();
		ctxEx.setCtXuatKho(ctx);
		ctxEx.setTonkhoXuat(tkXuat);
		ctxEx.setTonkhoNhan(tkNhan);
		return ctxEx;
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
		reportTypeKC="PhieuDuTruLanhThuocCuaKhoLe";
		log.info("Vao Method XuatReport PhieuLanhThuocTuTrucKhoaPhong");
		if (phieuXuat.getPhieuxuatkhoMa() != null) {
			try {
				XuatReportDuocPham xuatReport = new XuatReportDuocPham();
				xuatReport.reportTypeKC = reportTypeKC;
				String loaiThuoc = loaiPhieu.substring(0, loaiPhieu.indexOf(" - ")).trim();
				xuatReport.XuatReportPhieuDuTruKhoLeLanhThuoc(log, phieuXuat, index, loaiThuoc);
				jasperPrintKC = xuatReport.jasperPrintKC;
				reportPathKC = xuatReport.reportPathKC;
				reportTypeKC = xuatReport.reportTypeKC;
				index = index + 1;
			} catch (Exception e) {
				log.info("ERROR Method XuatReport!!!");
				e.printStackTrace();
			}
		}
	}
	/**
	 * Hien thi trang in
	 * @param loaiFile
	 * @return
	 */
	
	public String troVe(){
		try {
			log.info(" ***** troVe()");
			return "B4163_Phieudutrulanhthuoc";
		} 		
		catch (Exception e) {
			log.info(" ***** End exception = " + e);    	
		} 
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public void reset() {
		log.info(" *****reset() *****");
		maPhieu = "";
		updateItem = -1;
		tonkhoMa = "";
		dmtMa = "";
		dmtTen = "";
		tonkho = new Double(0);
		xuat = new Double(0);
		nguonMa = "";
		kpMa = "";
		count = 0;
		phieuXuat = new PhieuXuatKho();
		listCtDtXuatKhoEx = new ArrayList<CtXuatKhoEx>();
		resetInfo();
		ngayXuat = Utils.getCurrentDate();
		ngayHienTai = Utils.getCurrentDate();
		isUpdate = "0";
		log.info(" *****identity: " + identity.getUsername());
		DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
		DtDmNhanVien nv = nvDelegate.findByNd(identity.getUsername());
		if (nv != null) {
			phieuXuat.setDtdmnhanvienCn(nv);
		}
		tongTien = new Double("0");
		loaiPhieu="";
		dmLoaiTen ="";
		listDmLoaiPhieus.clear();
		listDmThuocs.clear();
		hmDmLoaiPhieu.clear();
		dmLoaiPhieuMa ="";
	}
	
	private void tinhTien() {
		tongTien = new Double("0");
		for (CtXuatKhoEx ctExt : listCtDtXuatKhoEx) {
			Double sl = ctExt.getCtXuatKho().getCtxuatkhoSoluong();
			if (sl == null) {
				sl = new Double("0");
			}
			Double dg = ctExt.getCtXuatKho().getCtxuatkhoDongia();
			if (dg == null) {
				dg = new Double("0");
			}
			tongTien += sl * dg;
		}
		log.info(" *****tong tien: " + tongTien);
	}
	
	private void resetInfo() {
		log.info(" ***** resetInfo()  *****");
		if (phieuXuat.getDmkhoaNhan() == null) {
			phieuXuat.setDmkhoaNhan(new DmKhoa());
		}
		if (phieuXuat.getDmkhoaXuat() == null) {
			phieuXuat.setDmkhoaXuat(new DmKhoa());
		}
		if (phieuXuat.getDmdoituongMaso() == null) {
			phieuXuat.setDmdoituongMaso(new DmDoiTuong());
		}
		
		if (phieuXuat.getDmloaithuocMaso() == null) {
			phieuXuat.setDmloaithuocMaso(new DmLoaiThuoc());
		}
		if (phieuXuat.getDtdmnhanvienBacsi() == null) {
			phieuXuat.setDtdmnhanvienBacsi(new DtDmNhanVien());
		}
		if (phieuXuat.getDtdmnhanvienCn() == null) {
			phieuXuat.setDtdmnhanvienCn(new DtDmNhanVien());
		}
		if (phieuXuat.getDtdmnhanvienNhan() == null) {
			phieuXuat.setDtdmnhanvienNhan(new DtDmNhanVien());
		}
		if (phieuXuat.getDtdmnhanvienPhat() == null) {
			phieuXuat.setDtdmnhanvienPhat(new DtDmNhanVien());
		}
	}
	private void clearInfor(){
		
		if ("".equals(Utils.reFactorString(phieuXuat.getDmkhoaNhan().getDmkhoaMaso()))) {
			phieuXuat.setDmkhoaNhan(null);
		}
		if ("".equals(Utils.reFactorString(phieuXuat.getDmkhoaXuat().getDmkhoaMaso()))) {
			phieuXuat.setDmkhoaXuat(null);
		}
		if ("".equals(Utils.reFactorString(phieuXuat.getDmdoituongMaso().getDmdoituongMaso() ))) {
			phieuXuat.setDmdoituongMaso(null);
		}
		if ("".equals(Utils.reFactorString(phieuXuat.getDmloaithuocMaso().getDmloaithuocMaso() ))) {
			phieuXuat.setDmloaithuocMaso(null);
		}
		if ("".equals(Utils.reFactorString(phieuXuat.getDtdmnhanvienBacsi().getDtdmnhanvienMaso() ))) {
			phieuXuat.setDtdmnhanvienBacsi(null);
		}
		if ("".equals(Utils.reFactorString(phieuXuat.getDtdmnhanvienCn().getDtdmnhanvienMaso() ))) {
			phieuXuat.setDtdmnhanvienCn(null);
		}
		if ("".equals(Utils.reFactorString(phieuXuat.getDtdmnhanvienNhan().getDtdmnhanvienMaso() ))) {
			phieuXuat.setDtdmnhanvienNhan(null);
		}
		if ("".equals(Utils.reFactorString(phieuXuat.getDtdmnhanvienPhat().getDtdmnhanvienMaso() ))) {
			phieuXuat.setDtdmnhanvienPhat(null);
		}
	}

	public void setPhieuXuat(PhieuXuatKho phieuXuat) {
		this.phieuXuat = phieuXuat;
	}

	public PhieuXuatKho getPhieuXuat() {
		return phieuXuat;
	}

	public void setNgayXuat(String ngayXuat) {
		this.ngayXuat = ngayXuat;
	}

	public String getNgayXuat() {
		return ngayXuat;
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
		PhieuDuTruLanhthuocAction.log = logger;
	}

	public ArrayList<CtXuatKhoEx> getListCtXuatKhoEx() {
		return listCtDtXuatKhoEx;
	}

	public void setListCtXuatKhoEx(ArrayList<CtXuatKhoEx> listCtXuatKhoEx) {
		this.listCtDtXuatKhoEx = listCtXuatKhoEx;
	}

	public CtXuatKhoEx getSelected() {
		return selected;
	}

	public void setSelected(CtXuatKhoEx selected) {
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

	public void setTenChuongTrinh(String tenChuongTrinh) {
		this.tenChuongTrinh = tenChuongTrinh;
	}

	public String getTenChuongTrinh() {
		return tenChuongTrinh;
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
	
	public String getLoaiPhieu() {
		return loaiPhieu;
	}

	public void setLoaiPhieu(String loaiPhieu) {
		this.loaiPhieu = loaiPhieu;
	}	
	
	public String getDmLoaiPhieuMa() {
		return dmLoaiPhieuMa;
	}
	
	public void setDmLoaiPhieuMa(String dmLoaiPhieuMa) {
		this.dmLoaiPhieuMa = dmLoaiPhieuMa;
	}
}
package com.iesvn.yte.dieutri.duocpham.action;

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

import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
import com.iesvn.yte.dieutri.delegate.CtTraKhoDelegate;
import com.iesvn.yte.dieutri.delegate.DmLoaiPhieuDelegate;
import com.iesvn.yte.dieutri.delegate.DmLoaiThuocDelegate;
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
import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.entity.DmDonViTinh;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmLoaiPhieu;
import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.entity.DmPhanNhomThuoc;
import com.iesvn.yte.entity.DmThuoc;
import com.iesvn.yte.report.XuatReportDuocPham;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;
import com.sun.org.apache.commons.beanutils.BeanUtils;

@Scope(CONVERSATION)
@Name("B4444_Phieutrahangbhytchokhochinh")
@Synchronized(timeout = 6000000)
public class PhieutrahangbhytchokhochinhAction implements Serializable{

	@In(required = false)
	@Out(required = false)
	private String tenChuongTrinh;
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
	private static final long serialVersionUID = 10L;

	private static Logger log = Logger.getLogger(PhieutrahangbhytchokhochinhAction.class);
	@DataModel
	private ArrayList<CtTraKhoExt> listCtTraKhoEx=new ArrayList<CtTraKhoExt>();
	@DataModelSelection
	private CtTraKhoExt selected=new CtTraKhoExt();

	private PhieuTraKho phieuTra;
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
	private Integer dmloaithuocMaso = null;
	private String dmloaithuocMa ="";
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

	@Begin(join = true)
	public String init(String khoNhan, String khoXuat) {
		log.info("***** init PhieutrahangbhytchokhochinhAction() *****");
		reset();
		if ("KhoNoiTru".equals(khoXuat)){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoNoiTru;
		}else if ("BHYT".equals(khoXuat)){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoBHYT;
		}else if ("KC".equals(khoXuat)){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoChinh;
		}else if ("KTE".equals(khoXuat)){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoTE;
		}else if ("NT".equals(khoXuat)){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoNoiTru;
		}
		dmKhoXuat = khoXuat;
		dmKhoNhan = khoNhan;
		
		listDmThuocs.clear();
		listDmLoaiThuocs.clear();
		hmLoaiThuoc.clear();
		hmDmThuoc.clear();
		
		refreshDmLoaiThuoc();
		
		return "QuanLyKhoBHYT_XuatKhoBHYT_BHYTTraKhoChinh";
	}
	
	@End
	public void endConversation(){
		
	}
	
	@Factory("listCtTraKhoEx")
	public void createTable() {
		log.info("-----createTable()-----");
		listCtTraKhoEx = new ArrayList<CtTraKhoExt>();
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
	
	public void refreshDmThuoc(){
		listDmThuocs.clear();
		hmDmThuoc.clear();
		dmThuocDelegate = DmThuocDelegate.getInstance();
				
		String loaiMa = "";
		if(loaiPhieu != null || !loaiPhieu.equals(""))
		{
			if(!dmloaithuocMa.equals("")){
				if( dmloaithuocMa.toUpperCase().equals("TD"))
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
					loaiMa = dmloaithuocMa;
				}	
			}	
		}
		System.out.println("loaiMa: "+loaiMa);
		System.out.println("Kho: "+phieuTra.getDmkhoaTra().getDmkhoaMaso());
		
		List<DmThuoc> lstDmThuoc = new ArrayList<DmThuoc>();
		if(!loaiMa.equals("") && !loaiPhieu.equals("")){
			lstDmThuoc = dmThuocDelegate.findByLoaiPhanLoaiThuocNhomThuocDVTKho(loaiMa, "", phieuTra.getDmkhoaTra().getDmkhoaMaso());
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
		log.info("-----BEGIN onblurMaLoaiAction()-----");
		String loaihang_ma = dmloaithuocMa;
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
		    	setDmloaithuocMaso(dmLoaiThuoc.getDmloaithuocMaso());
		    	setDmloaithuocMa(dmLoaiThuoc.getDmloaithuocMa());
		    	break;
		    }	    		
		}
	    listDmLoaiPhieus.clear();
		refreshDmLoaiPhieu();
	    setLoaiPhieu("");
	    setDmtMa("");
	    setDmtTen("");
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
	public Integer getDmloaithuocMaso() {
		return dmloaithuocMaso;
	}

	public void setDmloaithuocMaso(Integer dmloaithuocMaso) {
		this.dmloaithuocMaso = dmloaithuocMaso;
	}
	
	public String getDmloaithuocMa() {
		return dmloaithuocMa;
	}

	public void setDmloaithuocMa(String dmloaithuocMa) {
		this.dmloaithuocMa = dmloaithuocMa;
	}
	
	public String getLoaiPhieu() {
		return loaiPhieu;
	}

	public void setLoaiPhieu(String loaiPhieu) {
		this.loaiPhieu = loaiPhieu;
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
		if(dmloaithuocMaso != null){
			List<DmLoaiPhieu> lstDmLoaiPhieus = dmLoaiPhieuDelegate.findAll();
			if(lstDmLoaiPhieus !=null && lstDmLoaiPhieus.size()>0)
			{
				for(DmLoaiPhieu o: lstDmLoaiPhieus){
					hmDmLoaiPhieu.put(o.getDmloaiphieuMa(), o);
					if(dmloaithuocMaso.equals(o.getDmloaithuocMaso().getDmloaithuocMaso())){
						listDmLoaiPhieus.add(new SelectItem(o.getDmloaiphieuMa() + " - " +o.getDmloaiphieuTen()));
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
		log.info("-----deleteCt()-----");
		//int index = selected.getCtXuatKho().getCtxuatkhoThutu().intValue() - 1;
		listCtTraKhoEx.remove(selected);
		this.count = listCtTraKhoEx.size();
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
		log.info("-----selectCt()-----");
		log.info(String.format("-----selected: %s", selected.getCtTraKho().getCttrakhoThutu()));
		log.info("***** listCtTraKhoEx.size: "+listCtTraKhoEx.size());
		try {
			// int index =
			// selected.getCtXuatKho().getCtxuatkhoThutu().intValue() - 1;
			selected = listCtTraKhoEx.get(index);
			log.info("***** ton kho ma: "+listCtTraKhoEx.get(index).getTonKhoTra().getTonkhoMa());
			TonKho tk = selected.getTonKhoTra();
			CtTraKho ctx = selected.getCtTraKho();
			updateItem = index;
			tonkhoMa = tk.getTonkhoMa().toString();
			log.info("-----ton kho: " + tonkho);
			// DecimalFormat df = new DecimalFormat("###.##");
			tonkho = tk.getTonkhoTon();
			dmtMa = ctx.getDmthuocMaso().getDmthuocMa();
			dmtTen = ctx.getDmthuocMaso().getDmthuocTen();
			xuat = ctx.getCttrakhoSoluong();
		} catch (Exception e) {
			log.info("****** Error: "+e);
		}
		
		
	}

	/**
	 * Cap nhat chi tiet phieu xuat
	 */
	public void tiepTucNhap() {
		log.info("-----tiepTucNhap()-----");
		log.info(String.format("-----index: %s", updateItem));
		
		log.info("tonkhoMa:"+tonkhoMa);
		log.info("xuat:"+xuat);
		log.info("dmtMa:"+dmtMa);
		log.info("updateItem:"+updateItem);
		log.info("tonkho:"+tonkho);
		
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
			
			CtTraKho ctx = new CtTraKho();
			Double slXuat = new Double("0");
			if (updateItem.equals(-1)) {
			for (int i = 0; i < listCtTraKhoEx.size(); i++) {
				CtTraKho ctxk = listCtTraKhoEx.get(i).getCtTraKho();
				if (malk.equals(ctxk.getCttrakhoMalk())) {
					log.info("-----malk " + malk);
					slXuat += ctxk.getCttrakhoSoluong();
					updateItem = i;
				}
			}
			}
			slXuat += Double.valueOf(xuat);
			ctx.setCttrakhoSoluong(slXuat);
			CtTraKhoExt ctxEx = createCtTraKho(ctx, tk);
			log.info("-----xuat: " + slXuat);
			
			if (updateItem.equals(-1)) {
				log.info("-----them moi ct");
				listCtTraKhoEx.add(ctxEx);
				index = 0;
				System.out.println("***** Ton kho ma: "+ctxEx.getCtTraKho().getTonkhoMa());
			} else {
				log.info("-----Cap nhat ct-----");
				if (tk != null) {
					listCtTraKhoEx.set(updateItem, ctxEx);
				} else {
					FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_TK_NULL, dmtMa);
				}

				log.info(String.format("-----update ct: %s", ctx.getCttrakhoThutu()));
			}

			count = listCtTraKhoEx.size();
			log.info(String.format("-----listCtXuatKho: %s", listCtTraKhoEx.size()));
			log.info("***** ton kho ma: "+listCtTraKhoEx.get(index).getCtTraKho().getTonkhoMa());
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
		log.info("-----end()-----");
		
		Date dtNgayXuat = new Date();//@Trungnh  fix bug 471
		if (listCtTraKhoEx.size() > 0) {
			log.info(String.format("-----ngayxuat %s", ngayXuat));
			if (!ngayXuat.equals("")) {
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				try {
					dtNgayXuat = df.parse(ngayXuat);
					Calendar cal = Calendar.getInstance();
					cal.setTime(dtNgayXuat);
					phieuTra.setPhieutrakhoNgaygiocn(cal.getTime());
				} catch (ParseException e) {
					log.error(String.format("-----Error: %s", e.toString()));
				}
			}

			try {
				yteLog = new YteLog();
				listDataLog="";
				double tt = Double.valueOf("0");
				ArrayList<CtTraKho> listCtTra = new ArrayList<CtTraKho>();
				//ArrayList<TonKho> listTkXuat = new ArrayList<TonKho>();
				//ArrayList<TonKho> listTkNhan = new ArrayList<TonKho>();
				CtTraKhoExt obj=new CtTraKhoExt();
				for (int i=0;i<listCtTraKhoEx.size();i++) {
					CtTraKho ct = listCtTraKhoEx.get(i).getCtTraKho();
					log.info(String.format("-----so luong: %s", ct.getCttrakhoSoluong()));
					//log.info(String.format("-----Ton kho ma: "+ ct.getCtTraKho().getTonkhoMa()));
					log.info(String.format("-----Ton kho ma in List: "+ listCtTraKhoEx.get(i).getCtTraKho().getTonkhoMa()));
					tt += ct.getCttrakhoSoluong() * ct.getCttrakhoDongia();
					ct.setCttrakhoThutu(Short.valueOf("" + (i + 1)));
					listCtTra.add(ct);
					//ctx.getTonKhoTra().setTonkhoMa(null);
					//listTkXuat.add(ctx.getTonKhoTra());
					//ctx.getTonKhoNhan().setTonkhoMa(null);
					//listTkNhan.add(ctx.getTonKhoNhan());
					
					//luu log thong tin thuoc
					listDataLog += "Ma LK:"+ ct.getCttrakhoMalk()+
							" Don gia: "+ ct.getCttrakhoDongia()+ " Don gia ban: "+ ct.getCttrakhoDongiaban() + 
							" So luong: "+ ct.getCttrakhoSoluong()+
							" So lo: "+ ct.getCttrakhoLo()+
							" Nam SX: " + ct.getCttrakhoNamnhap()+
							" Nam HD: " + ct.getCttrakhoNamhandung()+ "\n";
				}
				log.info(String.format("-----thanh tien: %s", tt));
				phieuTra.setPhieutrakhoThanhtien(tt);
				phieuTra.setPhieutrakhoNgaygiocn(new Date());
				phieuTra.setPhieutrakhoNgay(dtNgayXuat);//@Trungnh fix bug 471
				phieuTra.setDmloaithuocMaso(new DmLoaiThuoc(dmloaithuocMaso));
				phieuTra.setPhieutrakhoLoaiPhieu(loaiPhieu);
				PhieuTraKhoDelegate pxDelegate = PhieuTraKhoDelegate.getInstance();
				log.info(String.format("-----phieu xuat: %s", phieuTra));

				clearInfor();

				//maPhieu = pxDelegate.createPhieuTra(phieuTra, listCtx, listTkNhan, listTkXuat);
				maPhieu = pxDelegate.updatePhieuTraKho(phieuTra, listCtTra);
				if (maPhieu != "") {
					resetInfo();
					phieuTra.setPhieutrakhoMa(maPhieu);
					log.info(String.format("-----result: %s", maPhieu));
					FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THANHCONG, maPhieu);
					//reset();
					isUpdate = "1";

//					Luu log he thong
			         yteLog.setForm("B4444_Phieutrahangbhytchokhochinh");
			         yteLog.setUserId(identity == null ? "NULL" : identity.getUsername());
			         yteLog.setObjectId(maPhieu);
			         yteLog.setLogString(" Ngày xuất: "+ ngayXuat+
	         					" Loại thuốc: " + dmloaithuocMa +
	         					" Loại phiếu: "+loaiPhieu+
	         					" Nhà nhận: "+ phieuTra.getDmkhoaNhan(true).getDmkhoaMa()+
	         					" Khoa trả: "+ phieuTra.getDmkhoaTra(true).getDmkhoaMa()+
//	         					" Chương trình: "+ phieuTra.getDmnctMaso(true).getDmnctMaso()+
//	         					" Nguồn KP: "+ phieuTra.getDmnguonkinhphiMaso(true).getDmnguonkinhphiMa()+
//	         					" Người nhập: "+  phieuXuat.getDtdmnhanvienCn(true).getDtdmnhanvienMa()+
//	         					" Người lập: "+(phieuXuat.getDtdmnhanvienPhat() == null ? "NULL" : phieuXuat.getDtdmnhanvienPhat().getDtdmnhanvienMa())+
//	         					" Người ký: "+(phieuXuat.getDtdmnhanvienBacsi() == null ? "NULL" : phieuXuat.getDtdmnhanvienBacsi().getDtdmnhanvienMa())+
	         					" Thành tiền: "+ tongTien);
			         yteLog.setDateTime(new Date());
			         yteLog.setListData(listDataLog);

			         yteLogDele.create(yteLog);
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
	
	@Destroy
	public void destroy() {
		log.info("-----destroy()-----");
	}
	
	/**
	 * Hien thi phieu xuat len giao dien
	 */
	public void displayPhieuXuatKho() {
		log.info("-----displayPhieuXuatKho()-----");
		if (!maPhieu.equals("")) {
			log.info(String.format("-----Phieu xuat ma: %s", this.maPhieu));

			try {
				PhieuTraKhoDelegate pxkWS = PhieuTraKhoDelegate.getInstance();
				CtTraKhoDelegate ctxWS = CtTraKhoDelegate.getInstance();
				
				//phieuTra = pxkWS.findByPhieutrakhoMa(maPhieu);
				phieuTra = pxkWS.findPhieuTraKhoByKhoaTra(maPhieu, phieuTra.getDmkhoaTra(true).getDmkhoaMaso());
				if (phieuTra != null) {
					maPhieu = phieuTra.getPhieutrakhoMa();
					dmloaithuocMaso = phieuTra.getDmloaithuocMaso(true).getDmloaithuocMaso();
					dmloaithuocMa = phieuTra.getDmloaithuocMaso(true).getDmloaithuocMa();
					dmLoaiTen = phieuTra.getDmloaithuocMaso(true).getDmloaithuocTen();
					loaiPhieu = phieuTra.getPhieutrakhoLoaiPhieu();
					resetInfo();
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					ngayXuat = df.format(phieuTra.getPhieutrakhoNgay());
					log.info(String.format("-----find ct xuat kho by phieu xuat kho ma %s", maPhieu));
					for (CtTraKho ct : ctxWS.findByphieutrakhoMa(phieuTra.getPhieutrakhoMa())) {
						log.info("Ct xuat kho ma: " + ct.getCttrakhoMa());
						CtTraKhoExt ctxEx = new CtTraKhoExt();
						ctxEx.setCtTraKho(ct);
						listCtTraKhoEx.add(ctxEx);
					}
					count = listCtTraKhoEx.size();
					isUpdate = "1";
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
	private CtTraKhoExt createCtTraKho(CtTraKho ctx, TonKho tk) {
		log.info(String.format("-----createCtTraKho()-----"));
		log.info(String.format("-----Ct xuat kho (input): %s", ctx.getCttrakhoMa()));
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
			tkTra.setTonkhoXuat(ctx.getCttrakhoSoluong());
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
			tkNhan.setTonkhoNhap(ctx.getCttrakhoSoluong());
			tkNhan.setTonkhoTra(null);
			tkNhan.setTonkhoXuat(null);
		}
		
		ctx.setDmnguonkinhphiMaso(tk.getDmnguonkinhphiMaso());
		ctx.setDmquocgiaMaso(tk.getDmquocgiaMaso());
		ctx.setDmthuocMaso(tk.getDmthuocMaso());
		ctx.setDmnhasanxuatMaso(tk.getDmnhasanxuatMaso());
		ctx.setDmnctMaso(tk.getDmnctMaso());
		ctx.setCttrakhoDongia(tk.getTonkhoDongia());
		ctx.setCttrakhoDongiaban(tk.getTonkhoDongiaban());
		ctx.setCttrakhoLo(tk.getTonkhoLo());
		ctx.setCttrakhoMalk(tk.getTonkhoMalienket());
		ctx.setCttrakhoNamhandung(tk.getTonkhoNamhandung());
		ctx.setCttrakhoNamnhap(tk.getTonkhoNamnhap());
		ctx.setCttrakhoNgaygiocn(new Date());
		ctx.setCttrakhoNgayhandung(tk.getTonkhoNgayhandung());
		ctx.setCttrakhoThanghandung(tk.getTonkhoThanghandung());
		ctx.setPhieutrakhoMa(phieuTra);
		ctx.setCttrakhoNgaygiocn(new Date());
		ctx.setCtxuatkhoSodangky(tk.getTonkhoSodangky());
		ctx.setTonkhoMa(tk.getTonkhoMa());
		log.info("***** Ton kho ma:"+ ctx.getTonkhoMa());
		
		CtTraKhoExt ctxEx = new CtTraKhoExt();
		ctxEx.setCtTraKho(ctx);
		ctxEx.setTonKhoTra(tkTra);
		ctxEx.setTonKhoNhan(tkNhan);
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
		reportTypeKC = "PhieuDuTruTraThuoc";
		log.info("Vao Method XuatReport Phieutrahangbhytchokhochinh");
		if (phieuTra.getPhieutrakhoMa()!= null && !phieuTra.getPhieutrakhoMa().equals("")) {
			try {
				XuatReportDuocPham xuatReport = new XuatReportDuocPham();
				xuatReport.reportTypeKC = reportTypeKC;
				String loaiThuoc = loaiPhieu.substring(0, loaiPhieu.indexOf(" - ")).trim();
				xuatReport.XuatReportPhieuDuTruKhoLeTraThuoc(log, phieuTra, index, loaiThuoc);
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
	 * 
	 * @return
	 */
	public String troVe(){
		try {
			log.info("***** troVe()");
			return "B4444_Phieutrahangbhytchokhochinh";
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
		dmtTen = "";
		tonkho = new Double(0);
		xuat = new Double(0);
		nguonMa = "";
		kpMa = "";
		count = 0;
		index = 0;
		phieuTra = new PhieuTraKho();
		listCtTraKhoEx = new ArrayList<CtTraKhoExt>();
		resetInfo();
		ngayXuat = Utils.getCurrentDate();
		ngayHienTai = Utils.getCurrentDate();
		isUpdate = "0";
		log.info("-----identity: " + identity.getUsername());
		DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
		DtDmNhanVien nv = nvDelegate.findByNd(identity.getUsername());
		if (nv != null) {
			phieuTra.setDtdmnhanvienCn(nv);
		}
		tongTien = new Double("0");
		listDmLoaiPhieus.clear();
		listDmThuocs.clear();
		loaiPhieu ="";
		dmloaithuocMaso =null;
		dmloaithuocMa ="";
		dmLoaiTen ="";
		hmDmLoaiPhieu.clear();
		dmLoaiPhieuMa ="";
	}
	
	private void tinhTien() {
		tongTien = new Double("0");
		for (CtTraKhoExt ctExt : listCtTraKhoEx) {
			Double sl = ctExt.getCtTraKho().getCttrakhoSoluong();
			if (sl == null) {
				sl = new Double("0");
			}
			Double dg = ctExt.getCtTraKho().getCttrakhoDongia();
			if (dg == null) {
				dg = new Double("0");
			}
			tongTien += sl * dg;
		}
		log.info("-----tong tien: " + tongTien);
	}
	
	private void resetInfo() {
		log.info("-----resetInfo()-----");
		if (phieuTra.getDmkhoaNhan() == null) {
			phieuTra.setDmkhoaNhan(new DmKhoa());
		}
		if (phieuTra.getDmkhoaTra() == null) {
			phieuTra.setDmkhoaTra(new DmKhoa());
		}
		if (phieuTra.getDmdoituongMaso() == null) {
			phieuTra.setDmdoituongMaso(new DmDoiTuong());
		}
		
		/*if (phieuTra.getDmloaithuocMaso() == null) {
			phieuTra.setDmloaithuocMaso(new DmLoaiThuoc());
		}*/
		if (phieuTra.getDtdmnhanvienBacsi() == null) {
			phieuTra.setDtdmnhanvienBacsi(new DtDmNhanVien());
		}
		if (phieuTra.getDtdmnhanvienCn() == null) {
			phieuTra.setDtdmnhanvienCn(new DtDmNhanVien());
		}
		/*if (phieuTra.getDtdmnhanvienNhan() == null) {
			phieuTra.setDtdmnhanvienNhan(new DtDmNhanVien());
		}*/
		if (phieuTra.getDtdmnhanvienPhat() == null) {
			phieuTra.setDtdmnhanvienPhat(new DtDmNhanVien());
		}
	}
	private void clearInfor(){
		
		if ("".equals(Utils.reFactorString(phieuTra.getDmkhoaNhan().getDmkhoaMaso()))) {
			phieuTra.setDmkhoaNhan(null);
		}
		if ("".equals(Utils.reFactorString(phieuTra.getDmkhoaTra().getDmkhoaMaso()))) {
			phieuTra.setDmkhoaTra(null);
		}
		if ("".equals(Utils.reFactorString(phieuTra.getDmdoituongMaso().getDmdoituongMaso() ))) {
			phieuTra.setDmdoituongMaso(null);
		}
		/*if ("".equals(Utils.reFactorString(phieuTra.getDmloaithuocMaso().getDmloaithuocMaso() ))) {
			phieuTra.setDmloaithuocMaso(null);
		}*/
		if ("".equals(Utils.reFactorString(phieuTra.getDtdmnhanvienBacsi().getDtdmnhanvienMaso() ))) {
			phieuTra.setDtdmnhanvienBacsi(null);
		}
		if ("".equals(Utils.reFactorString(phieuTra.getDtdmnhanvienCn().getDtdmnhanvienMaso() ))) {
			phieuTra.setDtdmnhanvienCn(null);
		}
		/*if ("".equals(Utils.reFactorString(phieuTra.getDtdmnhanvienNhan().getDtdmnhanvienMaso() ))) {
			phieuTra.setDtdmnhanvienNhan(null);
		}*/
		if ("".equals(Utils.reFactorString(phieuTra.getDtdmnhanvienPhat().getDtdmnhanvienMaso() ))) {
			phieuTra.setDtdmnhanvienPhat(null);
		}
	}

	public void setPhieuTra(PhieuTraKho phieuXuat) {
		this.phieuTra = phieuXuat;
	}

	public PhieuTraKho getPhieuTra() {
		return phieuTra;
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
		PhieutrahangbhytchokhochinhAction.log = logger;
	}

	public ArrayList<CtTraKhoExt> getListCtXuatKhoEx() {
		return listCtTraKhoEx;
	}

	public void setListCtXuatKhoEx(ArrayList<CtTraKhoExt> listCtTraKhoEx) {
		this.listCtTraKhoEx = listCtTraKhoEx;
	}

	public CtTraKhoExt getSelected() {
		return selected;
	}

	public void setSelected(CtTraKhoExt selected) {
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
	
	public String getDmLoaiPhieuMa() {
		return dmLoaiPhieuMa;
	}
	
	public void setDmLoaiPhieuMa(String dmLoaiPhieuMa) {
		this.dmLoaiPhieuMa = dmLoaiPhieuMa;
	}
	
	public class CtTraKhoExt implements Serializable{
		private static final long serialVersionUID = 0L;
		private CtTraKho ctTraKho;
		private TonKho tonKho;
		private TonKho tonKhoTra;
		private TonKho tonKhoNhan;
		private Double thanhTien;
		public Double getThanhTien() {
			return thanhTien;
		}
		public void setThanhTien(Double thanhTien) {
			this.thanhTien = thanhTien;
		}
		public CtTraKhoExt(){
			ctTraKho = new CtTraKho();
			tonKho = new TonKho();
			SetInforUtil.setInforIfNullForTonKho(tonKho);
			if (tonKho.getDmthuocMaso().getDmdonvitinhMaso() == null) {
				tonKho.getDmthuocMaso().setDmdonvitinhMaso(new DmDonViTinh());
			}
			thanhTien = new Double(0);
			
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


package com.iesvn.yte.dieutri.duocpham.action;

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

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
import com.iesvn.yte.dieutri.delegate.CtXuatKhoDelegate;
import com.iesvn.yte.dieutri.delegate.DmLoaiPhieuDelegate;
import com.iesvn.yte.dieutri.delegate.DmLoaiThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DmPhanNhomThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.KiemKeDelegate;
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
import com.iesvn.yte.entity.DmPhanNhomThuoc;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.TonKhoUtil;
import com.iesvn.yte.util.Utils;
import com.sun.org.apache.commons.beanutils.BeanUtils;


@Scope(ScopeType.CONVERSATION)
@Name("B4121_Phieuxuathangkhole")
@Synchronized(timeout = 6000000)
public class XuatHangDenKhoKhac  implements Serializable {
	@In(required = false)
	@Out(required = false)
	private String tenChuongTrinh;
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
	private static final long serialVersionUID = 10L;

	private static Logger log = Logger.getLogger(XuatHangDenKhoKhac.class);
	@DataModel
	private ArrayList<CtXuatKhoEx> listCtXuatKhoEx;
	@DataModelSelection
	private CtXuatKhoEx selected;

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
	private String isFound;
	
	String dmKhoXuat = "";
	String dmKhoNhan = "";
	
	private String resultReportFileName;
	private String resultReportName;	
	private String loaiFileXuat;
	@Out(required = false)
	private String isReport = "false";
	private String isUpdate;
	
	private String dmLoaiTen="";
	private DmLoaiThuocDelegate dmLoaiThuocDelegate;
	private List<SelectItem> listDmLoaiThuocs = new ArrayList<SelectItem>();
	HashMap<String, DmLoaiThuoc> hmLoaiThuoc = new HashMap<String, DmLoaiThuoc>();
	private DmLoaiPhieuDelegate dmLoaiPhieuDelegate;
	String loaiPhieu = "";
	private List<SelectItem> listDmLoaiPhieus = new ArrayList<SelectItem>();

	 private YteLogDelegate yteLogDele = YteLogDelegate.getInstance();
     private YteLog yteLog;
     private String listDataLog;
	
	@Restrict("#{s:hasRole('NV_KhoaDuoc') or s:hasRole('QT_HT_Duoc')}")
	@Begin(join = true)
	public String init(String khoNhan, String kho) {
		log.info("-----init()-----");
		reset();
		dmKhoXuat = kho;
		dmKhoNhan = khoNhan;
		
		return "QuanLyKhoChinh_XuatKhoChinh_XuatHangDenKhoLeKhoBHYT";
	}
	
	@End
	public void endConversation(){
		
	}
	
	@Factory("listCtXuatKhoEx")
	public void createTable() {
		log.info(" ***** createTable() ***** ");
		listCtXuatKhoEx = new ArrayList<CtXuatKhoEx>();
	}

	/**
	 * Xoa chi tiet xuat
	 */
	public void deleteCt() {
		log.info(" ***** deleteCt() ***** ");
		//int index = selected.getCtXuatKho().getCtxuatkhoThutu().intValue() - 1;
		listCtXuatKhoEx.remove(selected);
		this.count = listCtXuatKhoEx.size();
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
		log.info(" ***** selectCt() ***** ");
		log.info(String.format(" ***** selected: %s", selected.getCtXuatKho().getCtxuatkhoThutu()));
		//int index = selected.getCtXuatKho().getCtxuatkhoThutu().intValue() - 1;
		TonKho tk = selected.getTonkhoXuat();
		CtXuatKho ctx = selected.getCtXuatKho();
		updateItem = index;
		tonkhoMa = tk.getTonkhoMa().toString();
		log.info(" ***** ton kho: " + tonkho);
		
		//DecimalFormat df = new DecimalFormat("###.##");
		tonkho = tk.getTonkhoTon();
		dmtMa = ctx.getDmthuocMaso().getDmthuocMa();
		xuat = ctx.getCtxuatkhoSoluong();
		
	}

	/**
	 * Cap nhat chi tiet phieu xuat
	 */
	public void tiepTucNhap() {
		log.info(" ***** tiepTucNhap() ***** ");
		log.info(String.format(" ***** index: %s", updateItem));
		
		log.info("tonkhoMa:"+tonkhoMa);
		log.info("xuat:"+xuat);
		log.info("dmtMa:"+dmtMa);
		log.info("updateItem:"+updateItem);
		log.info("tonkho:"+tonkho);
		
		
		if (xuat == null || xuat.equals("") || tonkho  == null || tonkho.equals("")){
			return;
		}
		      
		if ("".equals(tonkhoMa) && tonkhoMa == null) {
			log.info(" ***** tonkho ma is null.");
		} else {
			log.info(String.format(" ***** tonkho ma: %s", tonkhoMa));
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
			for (int i = 0; i < listCtXuatKhoEx.size(); i++) {
				CtXuatKho ctxk = listCtXuatKhoEx.get(i).getCtXuatKho();
				if (malk.equals(ctxk.getCtxuatkhoMalk())) {
					log.info(" ***** malk " + malk);
					slXuat += ctxk.getCtxuatkhoSoluong();
					updateItem = i;
				}
			}
			slXuat += Double.valueOf(xuat);
			ctx.setCtxuatkhoSoluong(slXuat);
			CtXuatKhoEx ctxEx = createCtXuatKho(ctx, tk);
			log.info(" ***** xuat: " + slXuat);
			
			if (updateItem.equals(-1)) {
				log.info(" ***** them moi ct");
				listCtXuatKhoEx.add(ctxEx);
			} else {
				log.info(" ***** Cap nhat ct ***** ");
				if (tk != null) {
					listCtXuatKhoEx.set(updateItem, ctxEx);
				} else {
					FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_TK_NULL, dmtMa);
				}

				log.info(String.format(" ***** update ct: %s", ctx.getCtxuatkhoThutu()));
			}

			count = listCtXuatKhoEx.size();
			log.info(String.format(" ***** listCtXuatKho: %s", listCtXuatKhoEx.size()));
			tonkhoMa = "";
			dmtMa = "";
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
		log.info(" ***** end() ***** ");
		
		if (listCtXuatKhoEx.size() > 0) {
			yteLog = new YteLog();
			listDataLog="";
			log.info(String.format(" ***** ngayxuat %s", ngayXuat));
			log.info("**** ma phieu xuat"+maPhieu);
			PhieuXuatKhoDelegate pxkWS = PhieuXuatKhoDelegate.getInstance();
			TonKhoDelegate tkDelegate = TonKhoDelegate.getInstance();
			KiemKeDelegate kiemkeDel = KiemKeDelegate.getInstance();
			phieuXuat = pxkWS.findByPhieuxuatkhoMa(maPhieu);
			if (!ngayXuat.equals("")) {
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				try {
					Date dtNgayXuat = df.parse(ngayXuat);
					Calendar cal = Calendar.getInstance();
					cal.setTime(dtNgayXuat);
					phieuXuat.setPhieuxuatkhoNgaylap(cal.getTime());
				} catch (Exception e) {
					log.error(String.format(" ***** Error: %s", e.toString()));
				}
			}
			
			try {
				for(int i=0;i<listCtXuatKhoEx.size();i++)
				{
					CtXuatKho ct = listCtXuatKhoEx.get(i).getCtXuatKho();
					//28-4-2011 - kiem tra tai kho nhan, neu dang kiem ke thi khong duoc tra thuoc
					boolean tinhtrangKiemKe = kiemkeDel.dangKiemKe(ct.getCtxuatkhoMalk(),phieuXuat.getDmkhoaNhan().getDmkhoaMaso());
					if(tinhtrangKiemKe == true){
						FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_DANGKIEMKE, ct.getCtxuatkhoMalk());
						return;
					}
					//End kiem tra					
				}
				double tt = Double.valueOf("0");
				ArrayList<CtXuatKho> listCtx = new ArrayList<CtXuatKho>();
				ArrayList<TonKho> listTkXuat = new ArrayList<TonKho>();
				ArrayList<TonKho> listTkNhan = new ArrayList<TonKho>();
				TonKho tkXuat;
				TonKho tkNhan;
					
				for (int i=0;i<listCtXuatKhoEx.size();i++) {
					CtXuatKho ct = listCtXuatKhoEx.get(i).getCtXuatKho();
					log.info(String.format("-----so luong: %s", ct.getCtxuatkhoSoluong()));
					tt += ct.getCtxuatkhoSoluong() * ct.getCtxuatkhoDongia();
					ct.setCtxuatkhoThutu(Short.valueOf("" + (i + 1)));
					listCtx.add(ct);					
					tkXuat=createTonKhoXuat(listCtXuatKhoEx.get(i));
					//ctx.getTonKhoTra().setTonkhoMa(null);
					listTkXuat.add(tkXuat);
					log.info("***** ma ton kho xuat: "+tkXuat.getTonkhoMa());
					
					tkNhan=createTonKhoNhap(listCtXuatKhoEx.get(i));
					//ctx.getTonKhoNhan().setTonkhoMa(null);
					listTkNhan.add(tkNhan);
					log.info("***** ma ton kho tkNhan: "+tkNhan.getTonkhoMa());
					log.info("***** ma ton kho: "+listCtXuatKhoEx.get(i).getCtXuatKho().getTonKhoMa());
					//luu log thong tin thuoc
					listDataLog += "Ma LK:"+ tkNhan.getTonkhoMalienket() +
							" Don gia: "+  tkNhan.getTonkhoDongia()+ " Don gia ban: "+  tkNhan.getTonkhoDongiaban() + 
							" So luong: "+  tkNhan.getTonkhoXuat()+
							" So lo: "+ tkNhan.getTonkhoLo()+
							" Nam SX: " +  tkNhan.getTonkhoNamnhap()+
							" Nam HD: " +  tkNhan.getTonkhoNamhandung()+ "\n";
				}
				log.info(String.format(" ***** thanh tien: %s", tt));
				//phieuXuat.setPhieuxuatkhoThanhtien(tt);
				phieuXuat.setPhieuxuatkhoNgaygiocn(new Date());
				phieuXuat.setPhieuxuatkhoNgaygiophat(new Date());
				PhieuXuatKhoDelegate pxDelegate = PhieuXuatKhoDelegate.getInstance();
				log.info(String.format(" ***** phieu xuat: %s", phieuXuat));
	
				//	clearInfor();
	
				maPhieu = pxDelegate.thucHienPhieuXuat(phieuXuat, listCtx, listTkNhan, listTkXuat, IConstantsRes.PRIORITY_TON_LO_THUOC);
				if (maPhieu!=null && !maPhieu.equals("")) {
					log.info("maPhieu:"+maPhieu);
					if (maPhieu.indexOf(".") >= 0){
						FacesMessages.instance().add(maPhieu);
					}else{
						log.info("***** insert thanh cong ma phieu: "+maPhieu);
						resetInfo();
						
						log.info(String.format(" ***** result: %s", maPhieu));
						FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THANHCONG, maPhieu);
						displayPhieuXuatKho();
						isUpdate = "0"; //@Trung, tuong tu bug 473
						isFound="true";
					}
//					Luu log he thong
			         yteLog.setForm("B4121_Phieuxuathangkhole");
			         yteLog.setUserId(identity == null ? "NULL" : identity.getUsername());
			         yteLog.setObjectId(maPhieu);
			         yteLog.setLogString(" Ngay xuat: "+ ngayXuat+
			         					" Loai thuoc: " + phieuXuat.getDmloaithuocMaso(true).getDmloaithuocMa()+
			         					" Loai phieu: "+ loaiPhieu+
			         					" Kho nhan: "+ phieuXuat.getDmkhoaNhan(true).getDmkhoaMa()+
			         					" Khoa xuat: "+ phieuXuat.getDmkhoaXuat(true).getDmkhoaMa()+
			         					" Chuong trinh: "+ nguonMa + 
			         					" Nguon kinh phi: "+ kpMa + 
			         					" Nguoi nhap: "+  phieuXuat.getDtdmnhanvienCn(true).getDtdmnhanvienMa()+
			         					" Nguoi lap: "+ phieuXuat.getDtdmnhanvienPhat(true).getDtdmnhanvienMa()+
			         					" Nguoi ky: "+phieuXuat.getDtdmnhanvienBacsi(true).getDtdmnhanvienMa()+
			         					" Thanh tien: "+ phieuXuat.getPhieuxuatkhoThanhtien());
			         yteLog.setDateTime(new Date());
			         yteLog.setListData(listDataLog);

			         yteLogDele.create(yteLog);
				} else {
					FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THATBAI);
				}
				
			} catch (Exception e) {
				FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THATBAI);
				log.error(String.format(" ***** Error: %s", e.toString()));
			}			
			
		} else {
			FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_DMT_NULL);
		}
		
	}
	
	@Destroy
	public void destroy() {
		log.info(" ***** destroy() ***** ");
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
	    setLoaiPhieu("");
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
	    setLoaiPhieu("");
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
		if(phieuXuat != null && phieuXuat.getDmloaithuocMaso() != null){
			List<DmLoaiPhieu> lstDmLoaiPhieus = dmLoaiPhieuDelegate.findAll();
			if(lstDmLoaiPhieus !=null && lstDmLoaiPhieus.size()>0)
			{
				for(DmLoaiPhieu o: lstDmLoaiPhieus){
					if(phieuXuat.getDmloaithuocMaso().getDmloaithuocMaso().equals(o.getDmloaithuocMaso().getDmloaithuocMaso())){
						listDmLoaiPhieus.add(new SelectItem(o.getDmloaiphieuTen()));
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
	
	/**
	 * Hien thi phieu xuat len giao dien
	 */
	public void displayPhieuXuatKho() {
		log.info(" ***** displayPhieuXuatKho() ***** ");
		if (!maPhieu.equals("")) {
			log.info(String.format(" ***** Phieu xuat ma: %s", this.maPhieu));
			listCtXuatKhoEx.clear();
			try {
				PhieuXuatKhoDelegate pxkWS = PhieuXuatKhoDelegate.getInstance();
				CtXuatKhoDelegate ctxWS = CtXuatKhoDelegate.getInstance();
				TonKhoDelegate tkDelegate = TonKhoDelegate.getInstance();
				phieuXuat = pxkWS.findByPhieuxuatkhoMa(maPhieu);
				if (phieuXuat != null) {
					maPhieu = phieuXuat.getPhieuxuatkhoMa();
					loaiPhieu = phieuXuat.getPhieuxuatkhoLoaiPhieu();
					dmLoaiTen = phieuXuat.getDmloaithuocMaso(true).getDmloaithuocTen();
					resetInfo();
					log.info(String.format(" ***** find ct xuat kho by phieu xuat kho ma %s", maPhieu));
					for (CtXuatKho ct : ctxWS.findByphieuxuatkhoMa(phieuXuat.getPhieuxuatkhoMa())) {
						log.info("Ct xuat kho ma: " + ct.getCtxuatkhoMa());
						CtXuatKhoEx ctxEx = new CtXuatKhoEx();
						ctxEx.setCtXuatKho(ct);	
						TonKho tonkhoXuat = tkDelegate.getTonKhoHienTai(ct.getCtxuatkhoMalk(), phieuXuat.getDmkhoaXuat().getDmkhoaMaso());//find(ct.getTonKhoMa());
						ctxEx.setTonkhoXuat(tonkhoXuat);
						listCtXuatKhoEx.add(ctxEx);
					}
					count = listCtXuatKhoEx.size();
					isFound="true";
					if (phieuXuat.getPhieuxuatkhoNgaygiophat() == null){
						isUpdate = "1";						
					}else{
						isUpdate = "0";
						FacesMessages.instance().add(IConstantsRes.PHIEU_DU_TRU_DA_XUAT_HANG, maPhieu);
					}
				} else {
					
					FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_NULL, maPhieu);
					reset();
				}
				tinhTien();
			} catch (Exception e) {
				FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_NULL, maPhieu);
				reset();
				log.error(String.format(" ***** Error: %s", e));
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
		log.info(String.format(" ***** createCtXuatKho() ***** "));
		log.info(String.format(" ***** Ct xuat kho (input): %s", ctx.getCtxuatkhoMa()));
		log.info(String.format(" ***** ton kho (input): %s", tk.getTonkhoMa()));
		
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
		log.info(String.format(" ***** ct xuat kho: %s", ctx.getCtxuatkhoMa()));
		
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
		reportTypeKC="xuathangdenkhokhac";
		log.info("Vao Method XuatReport xuathangdenkhokhac");
		String baocao1=null;
		Date currentDate = new Date();

		if (!maPhieu.equals("")) {
			
		try {
			log.info("bat dau method XuatReport ");
			String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/phieuxuatkho_02.jrxml";
			log.info("da thay file templete 5" + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			
			PhieuXuatKhoDelegate pxkWS = PhieuXuatKhoDelegate.getInstance();
			PhieuXuatKho px = pxkWS.find(maPhieu);
			// saveSum(tuNgay, denNgay, donViMa);
			params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			log.info(String.format(" ***** tenSo: %s", params.get("tenSo")));
			params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			log.info(String.format(" ***** tenDonVi: %s", params.get("tenDonVi")));
			params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			log.info(String.format(" ***** dienThoaiDonVi: %s", params.get("dienThoaiDonVi")));
			Calendar cal = Calendar.getInstance();
			cal.setTime(px.getPhieuxuatkhoNgaylap());
			if (cal != null) {
				log.info(String.format(" ***** ngay lap: %s", cal.getTime()));
				params.put("ngayHt", "" + cal.get(Calendar.DAY_OF_MONTH));
				params.put("thangHt", "" + (cal.get(Calendar.MONTH) + 1));
				params.put("namHt", "" + cal.get(Calendar.YEAR));
			} else {
				log.info(" ***** ngay lap is null");
				params.put("ngayHt", "");
				params.put("thangHt", "");
				params.put("namHt", "");
			}

			SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
			String ngayGioHt = df.format(Calendar.getInstance().getTime());
			log.info(String.format(" ***** ngay gio hien tai: %s", ngayGioHt));
			params.put("gioHt", ngayGioHt);

			params.put("pxMa", px.getPhieuxuatkhoMa());

			if (px.getDmkhoaNhan() != null) {
				params.put("khoaNhan", px.getDmkhoaNhan().getDmkhoaTen());
			} else {
				params.put("khoaNhan", "");
			}
			log.info(String.format(" ***** khoaNhan: %s", params.get("khoaNhan")));

			if (px.getDmkhoaXuat() != null) {
				params.put("khoaXuat", px.getDmkhoaXuat().getDmkhoaTen());
			} else {
				params.put("khoaXuat", "");
			}
			log.info(String.format(" ***** khoaXuat: %s", params.get("khoaXuat")));

			params.put("tongTien", px.getPhieuxuatkhoThanhtien());
			log.info(String.format(" ***** tongTien: %s", params.get("tongTien")));
			//params.put("loaiMa", px.getDmloaithuocMaso().getDmloaithuocMa());
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintKC,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"duocpham/", "pdf","xuathangdenkhokhac");
			    reportPathKC=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan  *****  *****  *****  *****  :"+reportPathKC);
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
	
	/**
	 * 
	 * @return
	 */
	public String troVe(){
		try {
			log.info("***** troVe()");
			return "B4121_Phieuxuathangkhole";
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
		log.info(" ***** reset() ***** ");
		isFound="false";
		maPhieu = "";
		updateItem = -1;
		tonkhoMa = "";
		dmtMa = "";
		tonkho = new Double(0);
		xuat = new Double(0);
		nguonMa = "";
		kpMa = "";
		count = 0;
		loaiPhieu ="";
		dmLoaiTen ="";
		phieuXuat = new PhieuXuatKho();
		listCtXuatKhoEx = new ArrayList<CtXuatKhoEx>();
		resetInfo();
		ngayXuat = Utils.getCurrentDate();
		ngayHienTai = Utils.getCurrentDate();
		isUpdate = "0";
		log.info(" ***** identity: " + identity.getUsername());
		DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
		DtDmNhanVien nv = nvDelegate.findByNd(identity.getUsername());
		if (nv != null) {
			phieuXuat.setDtdmnhanvienCn(nv);
		}
		tongTien = new Double("0");
		listDmLoaiThuocs.clear();
		hmLoaiThuoc.clear();
		refreshDmLoaiThuoc();
	}
	
	private void tinhTien() {
		tongTien = new Double("0");
		for (CtXuatKhoEx ctExt : listCtXuatKhoEx) {
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
		log.info(" ***** tong tien: " + tongTien);
	}
	
	
	
	private void resetInfo() {
		log.info(" ***** resetInfo() ***** ");
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
		XuatHangDenKhoKhac.log = logger;
	}

	public ArrayList<CtXuatKhoEx> getListCtXuatKhoEx() {
		return listCtXuatKhoEx;
	}

	public void setListCtXuatKhoEx(ArrayList<CtXuatKhoEx> listCtXuatKhoEx) {
		this.listCtXuatKhoEx = listCtXuatKhoEx;
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

	public String getIsFound() {
		return isFound;
	}

	public void setIsFound(String notFound) {
		this.isFound = notFound;
	}
	
	public TonKho createTonKhoXuat(CtXuatKhoEx ct) {
		TonKho tk = new TonKho();
		try {
			tk=TonKhoUtil.getTonKhoHienTai(ct.getCtXuatKho().getCtxuatkhoMalk(), phieuXuat.getDmkhoaXuat().getDmkhoaMaso());
			tk.setTonkhoNhap(null);
			tk.setTonkhoXuat(ct.getCtXuatKho().getCtxuatkhoSoluong());
			tk.setTonkhoTra(null);
			tk.setTonkhoMa(null);
			tk.setDtdmnhanvienCn(phieuXuat.getDtdmnhanvienCn());
			tk.setTonkhoNgaygiocn(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		return tk;
	}
	
	public TonKho createTonKhoNhap(CtXuatKhoEx ct) {
		TonKho tk = new TonKho();
		try {
			tk=TonKhoUtil.getTonKhoHienTai(ct.getCtXuatKho().getCtxuatkhoMalk(), phieuXuat.getDmkhoaXuat().getDmkhoaMaso());
			tk.setTonkhoTra(null);
			tk.setTonkhoXuat(null);
			tk.setTonkhoNhap(ct.getCtXuatKho().getCtxuatkhoSoluong());
			tk.setTonkhoMa(null);
			tk.setDtdmnhanvienCn(phieuXuat.getDtdmnhanvienCn());
			tk.setTonkhoNgaygiocn(new Date());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return tk;
	}
	
	
}
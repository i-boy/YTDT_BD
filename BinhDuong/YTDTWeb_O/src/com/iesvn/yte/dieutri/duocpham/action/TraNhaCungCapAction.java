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
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
import com.iesvn.yte.dieutri.delegate.CtTraNhaCungCapDelegate;
import com.iesvn.yte.dieutri.delegate.DmLoaiPhieuDelegate;
import com.iesvn.yte.dieutri.delegate.DmLoaiThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DmThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.PhieuTraNhaCungCapDelegate;
import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
import com.iesvn.yte.dieutri.delegate.YteLogDelegate;
import com.iesvn.yte.dieutri.entity.CtTraNhaCungCap;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.PhieuTraNhaCungCap;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.dieutri.entity.YteLog;
import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmLoaiPhieu;
import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.entity.DmNguonKinhPhi;
import com.iesvn.yte.entity.DmNhaCungCap;
import com.iesvn.yte.entity.DmThuoc;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.TonKhoUtil;
import com.iesvn.yte.util.Utils;
import com.sun.org.apache.commons.beanutils.BeanUtils;

@Scope(ScopeType.CONVERSATION)
@Name("Tranhacungcap")
public class TraNhaCungCapAction implements Serializable {
	private static final long serialVersionUID = 10L;
	private static Logger logger = Logger.getLogger(TraNhaCungCapAction.class);
	
	@In(required = false)
	@Out(required = false)
	private String tenChuongTrinh;
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
	@DataModel
	private List<CtTraNhaCungCap> listCtTraNhaCungCap=new ArrayList<CtTraNhaCungCap>();
	@DataModelSelection
	private CtTraNhaCungCap selected=new CtTraNhaCungCap();

	private PhieuTraNhaCungCap phieuXuat;
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
	
	private String resultReportFileName;
	private String resultReportName;	
	private String loaiFileXuat;
	@Out(required = false)
	private String isReport = "false";
	private String isUpdate;
	//Ly add
	private DmThuocDelegate dmThuocDelegate;
	private String dmtTen="";
	
	//Tho add
	HashMap<String, DmThuoc> hmDmThuoc = new HashMap<String, DmThuoc>();
	
	private String dmLoaiTen="";
	private DmLoaiThuocDelegate dmLoaiThuocDelegate;
	private List<SelectItem> listDmLoaiThuocs = new ArrayList<SelectItem>();
	HashMap<String, DmLoaiThuoc> hmLoaiThuoc = new HashMap<String, DmLoaiThuoc>();
	private DmLoaiPhieuDelegate dmLoaiPhieuDelegate;
	HashMap<String,DmLoaiPhieu> hmDmLoaiPhieu = new HashMap<String,DmLoaiPhieu>();
	private String dmLoaiPhieuMa ="";
	String loaiPhieu = "";
	private List<SelectItem> listDmLoaiPhieus = new ArrayList<SelectItem>();
	
	 private YteLogDelegate yteLogDele = YteLogDelegate.getInstance();
     private YteLog yteLog;
     private String listDataLog;
	
	@Restrict("#{s:hasRole('NV_KhoaDuoc') or s:hasRole('QT_HT_Duoc')}")
	@Begin(join=true)
	public String init(String tumenu) {
		logger.info("*****init()*****");
		reset();
		if ("KC".equals(tumenu)){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoChinh;			
		}		
		
		return "Tranhacungcap";
	}
	@End
	public void endFunc(){
		
	}
	
	
		
	@Factory("listCtTraNhaCungCap")
	public void createTable() {
		logger.info("*****createTable()*****");
		listCtTraNhaCungCap = new ArrayList<CtTraNhaCungCap>();
	}

	/**
	 * Xoa chi tiet xuat
	 */
	public void deleteCt() {
		logger.info("*****deleteCt()*****");
		//int index = selected.getCtXuatKho().getCtxuatkhoThutu().intValue() - 1;
		listCtTraNhaCungCap.remove(selected);
		this.count = listCtTraNhaCungCap.size();
		tinhTien();
	}
	
	/** ==================== BEGIN LY THEM CODE - Tho edit again ==================== */		
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
		logger.info("-----BEGIN onblurMaThuocAction()-----");
		if (dmtMa != null) {
			DmThuoc dmThuoc = hmDmThuoc.get(dmtMa.toUpperCase());
			if(dmThuoc != null) {
				setDmtTen(dmThuoc.getDmthuocTen());
				logger.info("-----DA THAY DMTHUOC-----");
			}
			else {
				setDmtTen("");
			}
		}
		logger.info("-----END onblurMaThuocAction()-----");
	}
	
	public void onblurTenThuocAction(){
		logger.info("-----BEGIN onblurTenThuocAction()-----");
		Boolean hasTenThuoc = false;
		String maThuoc = "";
//		DmThuocDelegate dmThuocDel = DmThuocDelegate.getInstance();
//		DmThuoc dmThuoc = dmThuocDel.findByTenThuoc(dmtTen);
//		if(dmThuoc != null){
//			setDmtMa(dmThuoc.getDmthuocMa());
//			logger.info("-----DA THAY DMTHUOC-----");
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
			logger.info("-----DA THAY DMTHUOC-----");
	    }else{
	    	setDmtMa("");
	    }
		logger.info("-----END onblurTenThuocAction()-----");
	}
	
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
		logger.info("-----BEGIN onblurMaLoaiAction()-----"+phieuXuat.getDmloaithuocMaso().getDmloaithuocMa());
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
	    setDmLoaiPhieuMa("");
		logger.info("-----END onblurMaLoaiAction()-----");
	}
	
	public void onblurTenLoaiThuocAction(){
		logger.info("-----BEGIN onblurTenLoaiThuocAction()-----");
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
	    setDmLoaiPhieuMa("");
		logger.info("-----END onblurTenLoaiThuocAction()-----");
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
		logger.info("-----BEGIN onblurTenLoaiPhieuAction()-----");
		String loaiPhieuItem = loaiPhieu;
		dmLoaiPhieuMa = loaiPhieuItem.substring(0, loaiPhieuItem.indexOf(" - ")).trim();
		refreshDmThuoc();
		logger.info("-----END onblurTenLoaiPhieuAction()-----");
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
	/**
	 * Hien thi chi tiet xuat
	 */
	public void selectCt(Integer index) {
		logger.info("*****selectCt()*****");
		//logger.info(String.format("*****selected: %s", selected.getCttranhacungcapThutu()));
		//int index = selected.getCtXuatKho().getCtxuatkhoThutu().intValue() - 1;
		//TonKho tk = selected.getTonKhoMa();
		selected = listCtTraNhaCungCap.get(index.intValue());
		CtTraNhaCungCap ctx = selected;//.getCtXuatKho();
		updateItem = index;
		tonkhoMa = selected.getTonKhoMa().toString();
		logger.info("*****ton kho: " + tonkho);
		//DecimalFormat df = new DecimalFormat("###.##");
		//tonkho = tk.getTonkhoTon();
		dmtMa = ctx.getDmthuocMaso().getDmthuocMa();
		dmtTen = ctx.getDmthuocMaso().getDmthuocTen();
		xuat = ctx.getCttranhacungcapSoluong();
		
	}

	/**
	 * Cap nhat chi tiet phieu xuat
	 */
	public void tiepTucNhap() {
		logger.info("*****tiepTucNhap()*****");
		logger.info(String.format("*****index: %s", updateItem));
		
		logger.info("tonkhoMa:"+tonkhoMa);
		logger.info("xuat:"+xuat);
		logger.info("dmtMa:"+dmtMa);
		logger.info("updateItem:"+updateItem);
		logger.info("tonkho:"+tonkho);
		
		
		if (xuat == null || xuat.equals("") || tonkho  == null || tonkho.equals("")){
			return;
		}
		      
		if ("".equals(tonkhoMa) && tonkhoMa == null) {
			logger.info("*****tonkho ma is null.");
		} else {
			logger.info(String.format("*****tonkho ma: %s", tonkhoMa));
			TonKho tk = null;
			
			TonKhoDelegate tkDelegate;
			try {
				tkDelegate = TonKhoDelegate.getInstance();
				
				tk = tkDelegate.find(Integer.valueOf(tonkhoMa));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			CtTraNhaCungCap ctx = new CtTraNhaCungCap();
			
			Double slXuat = new Double("0");
			if (updateItem.equals(-1)) {
				for (int i = 0; i < listCtTraNhaCungCap.size(); i++) {
					String mlktemp = listCtTraNhaCungCap.get(i).getCttranhacungcapMalk();
					if (malk.equals(mlktemp)) {
						logger.info("*****malk " + malk);
						slXuat +=listCtTraNhaCungCap.get(i).getCttranhacungcapSoluong();
						updateItem = i;
					}
				}
			}
			slXuat += Double.valueOf(xuat);
			ctx.setCttranhacungcapSoluong(slXuat);
			CtTraNhaCungCap ctxEx = createCtTraNhaCungCap(ctx, tk);
			logger.info("*****xuat: " + slXuat);
			
			if (updateItem.equals(-1)) {
				logger.info("*****them moi ct");
				listCtTraNhaCungCap.add(ctxEx);
			} else {
				logger.info("*****Cap nhat ct*****");
				if (tk != null) {
					listCtTraNhaCungCap.set(updateItem, ctxEx);
				} else {
					FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_TK_NULL, dmtMa);
				}

				logger.info(String.format("*****update ct: %s", ctx.getCttranhacungcapThutu()));
			}

			count = listCtTraNhaCungCap.size();
			logger.info(String.format("*****listCtXuatKho: %s", listCtTraNhaCungCap.size()));
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
		logger.info("*****end()*****");
		yteLog = new YteLog();
		listDataLog="";
		if (listCtTraNhaCungCap.size() > 0) {
			logger.info(String.format("*****ngayxuat %s", ngayXuat));
			if (!ngayXuat.equals("")) {
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				try {
					Date dtNgayXuat = df.parse(ngayXuat);
					Calendar cal = Calendar.getInstance();
					cal.setTime(dtNgayXuat);
					phieuXuat.setPhieutranhacungcapNgaylap(cal.getTime());
				} catch (Exception e) {
					logger.error(String.format("*****Error: %s", e.toString()));
				}
			}

			
			try {
				double tt = Double.valueOf("0");
				ArrayList<CtTraNhaCungCap> listCtx = new ArrayList<CtTraNhaCungCap>();
				ArrayList<TonKho> listTkXuat = new ArrayList<TonKho>();
				for (int i=0;i<listCtTraNhaCungCap.size();i++) {
					CtTraNhaCungCap ct = listCtTraNhaCungCap.get(i);
					TonKho tk=createTonKho(ct);
					logger.info(String.format("*****so luong: %s", ct.getCttranhacungcapSoluong()));
					tt += ct.getCttranhacungcapSoluong() * ct.getCttranhacungcapDongia();
					ct.setCttranhacungcapThutu(Short.valueOf("" + (i + 1)));
					listCtx.add(ct);
					listTkXuat.add(tk);
					//luu log thong tin thuoc
					listDataLog += "Ma LK:"+ tk.getTonkhoMalienket() +
							" Don gia: "+ tk.getTonkhoDongia()+ " Don gia ban: "+ tk.getTonkhoDongiaban() + 
							" So luong: "+ tk.getTonkhoXuat()+
							" So lo: "+ tk.getTonkhoLo()+
							" Nam SX: " + tk.getTonkhoNamnhap()+
							" Nam HD: " + tk.getTonkhoNamhandung()+ "\n";
				}
				logger.info(String.format("*****thanh tien: %s", tt));
				phieuXuat.setPhieutranhacungcapThanhtien(tt);
				phieuXuat.setPhieutranhacungcapNgaygiocn(new Date());
				phieuXuat.setPhieutranhacungcapLoaiPhieu(loaiPhieu);
				
				PhieuTraNhaCungCapDelegate pxDelegate = PhieuTraNhaCungCapDelegate.getInstance();
				logger.info(String.format("*****phieu xuat: %s", phieuXuat));

				clearInfor();

				//maPhieu = pxDelegate.createPhieu(phieuXuat, listCtx, listTkNhan, listTkXuat);
				logger.info("PhieutranhacungcapMa: "+phieuXuat.getPhieutranhacungcapMa());
				logger.info("DtdmnhanvienMaso: "+phieuXuat.getDtdmnhanvienCn(true).getDtdmnhanvienMaso());
				logger.info("DmnhacungcapMaso: "+phieuXuat.getNhacungcap(true).getDmnhacungcapMaso());
				maPhieu = pxDelegate.updatePhieuTraNhaCungCap(phieuXuat, listCtx, listTkXuat);
				
				logger.info("***** ma phieu xuat tra NCC: "+maPhieu);
				if (!maPhieu.equals("")) {
					resetInfo();
					
					logger.info(String.format("*****result: %s", maPhieu));
					FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THANHCONG, maPhieu);
					//reset();
					isUpdate = "1";
					
//					Luu log he thong
			         yteLog.setForm("Tranhacungcap");
			         yteLog.setUserId(identity == null ? "NULL" : identity.getUsername());
			         yteLog.setObjectId(maPhieu);
			         yteLog.setLogString(" Ngay xuat: "+ ngayXuat+
			         					" Loai thuoc: " + phieuXuat.getDmloaithuocMaso(true).getDmloaithuocMa()+
			         					" Loai phieu: "+loaiPhieu+
			         					" Nha cung cap: "+ phieuXuat.getNhacungcap(true).getDmnhacungcapMa()+
			         					" Khoa tra: "+ phieuXuat.getDmkhoaXuat(true).getDmkhoaMa()+
			         					" Chuong trinh: "+ phieuXuat.getDmnctMaso(true).getDmnctMaso()+
			         					" Nguon KP: "+ phieuXuat.getDmnguonkinhphiMaso(true).getDmnguonkinhphiMa()+
			         					" Nguoi nhap: "+  phieuXuat.getDtdmnhanvienCn(true).getDtdmnhanvienMa()+
			         					" Nguoi lap: "+(phieuXuat.getDtdmnhanvienPhat() == null ? "NULL" : phieuXuat.getDtdmnhanvienPhat().getDtdmnhanvienMa())+
			         					" Nguoi ky: "+(phieuXuat.getDtdmnhanvienBacsi() == null ? "NULL" : phieuXuat.getDtdmnhanvienBacsi().getDtdmnhanvienMa())+
			         					" Thanh tien: "+ tongTien);
			         yteLog.setDateTime(new Date());
			         yteLog.setListData(listDataLog);
			         yteLogDele.create(yteLog);
				} else {
					FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THATBAI);
				}
				
			} catch (Exception e) {
				FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THATBAI);
				logger.error(String.format("*****Error: %s", e.toString()));
			}
		} else {
			FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_DMT_NULL);
		}
		
	}
	
	@Destroy
	public void destroy() {
		logger.info("*****destroy()*****");
	}
	
	/**
	 * Hien thi phieu xuat len giao dien
	 */
	public void displayPhieuXuatKho() {
		logger.info("*****displayPhieuXuatKho()*****");
		if (!maPhieu.equals("")) {
			logger.info(String.format("*****Phieu xuat ma: %s", this.maPhieu));
			try {
				PhieuTraNhaCungCapDelegate pxkWS = PhieuTraNhaCungCapDelegate.getInstance();
				CtTraNhaCungCapDelegate ctxWS = CtTraNhaCungCapDelegate.getInstance();
				
				phieuXuat = pxkWS.findPhieuTraNhaCungCapByMa(maPhieu);
				if (phieuXuat != null) {
					logger.info("***** phieuXuat: "+phieuXuat.getPhieutranhacungcapMa());
					maPhieu = phieuXuat.getPhieutranhacungcapMa();
					resetInfo();
					dmLoaiTen = phieuXuat.getDmloaithuocMaso(true).getDmloaithuocTen();
					loaiPhieu = phieuXuat.getPhieutranhacungcapLoaiPhieu();
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					ngayXuat = df.format(phieuXuat.getPhieutranhacungcapNgaylap());
					logger.info(String.format("*****find ct xuat kho by phieu xuat kho ma %s", maPhieu));
					listCtTraNhaCungCap = (ctxWS.findCtTraNhaCungCapByMaPhieu(maPhieu)==null?new ArrayList<CtTraNhaCungCap>():ctxWS.findCtTraNhaCungCapByMaPhieu(maPhieu));
					/*for (CtTraNhaCungCap ct : ctxWS.findCtTraNhaCungCapByMaPhieu(maPhieu)) {
						logger.info("CtTraNhaCungCap ma: " + ct.getCttranhacungcapMa());
						CtXuatKhoEx ctxEx = new CtXuatKhoEx();
						ctxEx.setCtXuatKho(ct);
						listCtXuatKhoTraNhaCungCapEx.add(ct);
					}*/
					count = listCtTraNhaCungCap.size();
					isUpdate = "1";
				} else {
					FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_NULL, maPhieu);
					reset();
				}
				tinhTien();
			} catch (Exception e) {
				FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_NULL, maPhieu);
				reset();
				logger.error(String.format("*****Error: %s", e));
			}
		}
		
	}

	/**
	 * Tao chi tiet xuat kho tu ton kho
	 * @param ctx
	 * @param tk
	 * @return
	 */
	private CtTraNhaCungCap createCtTraNhaCungCap(CtTraNhaCungCap ctx, TonKho tk) {
		logger.info(String.format("*****createCtXuatKho()*****"));
		logger.info(String.format("*****Ct xuat kho (input): %s", ctx.getCttranhacungcapMa()));
		logger.info(String.format("*****ton kho (input): %s", tk.getTonkhoMa()));
		
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
			tkXuat.setTonkhoXuat(ctx.getCttranhacungcapSoluong());
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
			tkNhan.setTonkhoNhap(ctx.getCttranhacungcapSoluong());
			tkNhan.setTonkhoTra(null);
			tkNhan.setTonkhoXuat(null);
		}
		
		ctx.setDmnguonkinhphiMaso(tk.getDmnguonkinhphiMaso());
		ctx.setDmquocgiaMaso(tk.getDmquocgiaMaso());
		ctx.setDmthuocMaso(tk.getDmthuocMaso());
		ctx.setDmnhasanxuatMaso(tk.getDmnhasanxuatMaso());
		ctx.setDmnctMaso(tk.getDmnctMaso());
		ctx.setCttranhacungcapDongia(tk.getTonkhoDongia());
		ctx.setCttranhacungcapDongiaban(tk.getTonkhoDongiaban());
		ctx.setCttranhacungcapLo(tk.getTonkhoLo());
		ctx.setCttranhacungcapMalk(tk.getTonkhoMalienket());
		ctx.setCttranhacungcapNamhandung(tk.getTonkhoNamhandung());
		ctx.setCttranhacungcapNamnhap(tk.getTonkhoNamnhap());
		ctx.setCttranhacungcapNgaygiocn(new Date());
		ctx.setCttranhacungcapNgayhandung(tk.getTonkhoNgayhandung());
		ctx.setCttranhacungcapThanghandung(tk.getTonkhoThanghandung());
		ctx.setPhieutranhacungcapMa(phieuXuat);
		ctx.setTonKhoMa(tk.getTonkhoMa());
		logger.info(String.format("*****ct xuat kho: %s", ctx.getCttranhacungcapMa()));
		
		return ctx;
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
		reportTypeKC="PhieuXuatTraNhaCungCap";
		logger.info("Vao Method XuatReport PhieuXuatTraNhaCungCap");
		String baocao1=null;
		String pathTemplate=null;
		try {
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/phieutrakho_02.jrxml";
			logger.info("da thay file templete 5" + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			logger.info("da thay file template ");
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Map<String, Object> params = new HashMap<String, Object>();
			
			PhieuTraNhaCungCapDelegate pxkWS = PhieuTraNhaCungCapDelegate.getInstance();
			PhieuTraNhaCungCap px = pxkWS.findPhieuTraNhaCungCapByMa(maPhieu);
			// saveSum(tuNgay, denNgay, donViMa);
			params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			logger.info(String.format("*****tenSo: %s", params.get("tenSo")));
			params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			logger.info(String.format("*****tenDonVi: %s", params.get("tenDonVi")));
			params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			logger.info(String.format("*****dienThoaiDonVi: %s", params.get("dienThoaiDonVi")));
			Calendar cal = Calendar.getInstance();
			cal.setTime(px.getPhieutranhacungcapNgaylap());
			if (cal != null) {
				logger.info(String.format("*****ngay lap: %s", cal.getTime()));
				params.put("ngayHt", "" + cal.get(Calendar.DAY_OF_MONTH));
				params.put("thangHt", "" + (cal.get(Calendar.MONTH) + 1));
				params.put("namHt", "" + cal.get(Calendar.YEAR));
			} else {
				logger.info("*****ngay lap is null");
				params.put("ngayHt", "");
				params.put("thangHt", "");
				params.put("namHt", "");
			}

			SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
			String ngayGioHt = df.format(Calendar.getInstance().getTime());
			logger.info(String.format("*****ngay gio hien tai: %s", ngayGioHt));
			params.put("gioHt", ngayGioHt);

			params.put("pxMa", px.getPhieutranhacungcapMa());

			if (px.getDmkhoaXuat() != null) {
				params.put("khoaNhan", px.getNhacungcap(true).getDmnhacungcapTen());
			} else {
				params.put("khoaNhan", "");
			}
			logger.info(String.format("*****khoaNhan: %s", params.get("khoaNhan")));

			if (px.getDmkhoaXuat() != null) {
				params.put("khoaXuat", px.getDmkhoaXuat().getDmkhoaTen());
			} else {
				params.put("khoaXuat", "");
			}
			logger.info(String.format("*****khoaXuat: %s", params.get("khoaXuat")));

			params.put("tongTien", px.getPhieutranhacungcapThanhtien());
			logger.info(String.format("*****tongTien: %s", params.get("tongTien")));
			params.put("loaiMa", px.getDmloaithuocMaso().getDmloaithuocMa());
			params.put("nhanvienCn", px.getDtdmnhanvienCn().getDtdmnhanvienMa());
			
															
			logger.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			    logger.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	logger.info(e.getMessage());
			    }
			    jasperPrintKC =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintKC,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"duocpham/", "pdf","xuattranhacungcap");
			    reportPathKC=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    logger.info("duong dan file xuat report :" + baocao1);
			    logger.info("duong dan ******************** :"+reportPathKC);
			    index+= 1;
			    logger.info("Index :" + index);
			    if(conn != null) conn.close();
		}catch (Exception e) {
			logger.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
		logger.info("Thoat Method XuatReport");
	}
	
	public TonKho createTonKho(CtTraNhaCungCap ct) {
		TonKho tk = new TonKho();
		try {
			
			tk=TonKhoUtil.getTonKhoHienTai(ct.getCttranhacungcapMalk(), phieuXuat.getDmkhoaXuat().getDmkhoaMaso());
			tk.setDtdmkhoMaso(null);
			tk.setDtdmnhanvienCn(phieuXuat.getDtdmnhanvienCn(true));
			tk.setTonkhoHienthi(true);
			tk.setTonkhoNhap(null);
			tk.setTonkhoXuat(ct.getCttranhacungcapSoluong());
			tk.setTonkhoTra(null);
			tk.setTonkhoMa(null);
			tk.setTonkhoNgaygiocn(new Date());
		} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		//}
		return tk;
	}
	public String troVe(){
		try {
			logger.info("***** troVe()");
			return "Tranhacungcap";
		} 		
		catch (Exception e) {
			logger.info("***** End exception = " + e);    	
		} 
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public void reset() {
		logger.info("*****reset()*****");
		
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
		phieuXuat = new PhieuTraNhaCungCap();
		listCtTraNhaCungCap = new ArrayList<CtTraNhaCungCap>();
		resetInfo();
		ngayXuat = Utils.getCurrentDate();
		ngayHienTai = Utils.getCurrentDate();
		isUpdate = "0";
		logger.info("*****identity: " + identity.getUsername());
		DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
		DtDmNhanVien nv = nvDelegate.findByNd(identity.getUsername());
		if (nv != null) {
			phieuXuat.setDtdmnhanvienCn(nv);
		}
		tongTien = new Double("0");
		// day la phieu tra nha cung cap nen chi co' the la kho chinh
		phieuXuat.getDmkhoaXuat(true).setDmkhoaMa(IConstantsRes.KHOA_KC_MA); 
		//loai thuoc
		loaiPhieu ="";
		dmLoaiTen ="";
		listDmLoaiThuocs.clear();
		hmLoaiThuoc.clear();
		refreshDmLoaiThuoc();
		//Thuoc
		setDmtMa("");
		setDmtTen("");
		listDmThuocs.clear();
		hmDmThuoc.clear();
		refreshDmThuoc();
		dmLoaiPhieuMa = "";
		hmDmLoaiPhieu.clear();
	}
	
	public void clearDetail() {
		logger.info("***** clearDetail() *****");
		listCtTraNhaCungCap = new ArrayList<CtTraNhaCungCap>();
	}
	
	private void tinhTien() {
		tongTien = new Double("0");
		for (CtTraNhaCungCap ctExt : listCtTraNhaCungCap) {
			Double sl = ctExt.getCttranhacungcapSoluong();
			if (sl == null) {
				sl = new Double("0");
			}
			Double dg = ctExt.getCttranhacungcapDongia();
			if (dg == null) {
				dg = new Double("0");
			}
			tongTien += sl * dg;
		}
		logger.info("*****tong tien: " + tongTien);
	}
	
	private void resetInfo() {
		logger.info("***** resetInfo() *****");
		/*if (phieuXuat.getDmkhoaNhan() == null) {
			phieuXuat.setDmkhoaNhan(new DmK
			hoa());
		}*/
		if (phieuXuat.getNhacungcap() == null) {
			phieuXuat.setNhacungcap(new DmNhaCungCap());
		}
		if (phieuXuat.getDmnguonkinhphiMaso() == null) {
			phieuXuat.setDmnguonkinhphiMaso(new DmNguonKinhPhi());
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
		
		/*if ("".equals(Utils.reFactorString(phieuXuat.getDmkhoaNhan().getDmkhoaMaso()))) {
			phieuXuat.setDmkhoaNhan(null);
		}*/
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
		if ("".equals(Utils.reFactorString(phieuXuat.getDmnguonkinhphiMaso().getDmnguonkinhphiMaso() ))) {
			phieuXuat.setDmnguonkinhphiMaso(null);
		}
		if ("".equals(Utils.reFactorString(phieuXuat.getDmnctMaso(true).getDmnctMaso() ))) {
			phieuXuat.setDmnctMaso(null);
		}
	}

	public void setPhieuXuat(PhieuTraNhaCungCap phieuXuat) {
		this.phieuXuat = phieuXuat;
	}

	public PhieuTraNhaCungCap getPhieuXuat() {
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

	public List<CtTraNhaCungCap> getListCtTraNhaCungCap() {
		return listCtTraNhaCungCap;
	}
	public void setListCtTraNhaCungCap(
			ArrayList<CtTraNhaCungCap> listCtXuatKhoTraNhaCungCap) {
		this.listCtTraNhaCungCap = listCtXuatKhoTraNhaCungCap;
	}
	public CtTraNhaCungCap getSelected() {
		return selected;
	}

	public void setSelected(CtTraNhaCungCap selected) {
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
	
	public String getDmLoaiPhieuMa() {
		return dmLoaiPhieuMa;
	}
	
	public void setDmLoaiPhieuMa(String dmLoaiPhieuMa) {
		this.dmLoaiPhieuMa = dmLoaiPhieuMa;
	}
}
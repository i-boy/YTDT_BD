/**
 * 
 * @author Mai Van Manh
 */

package com.iesvn.yte.dieutri.duocpham.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.NumberFormat;
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
import com.iesvn.yte.dieutri.delegate.CtXuatKhoDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DmLoaiPhieuDelegate;
import com.iesvn.yte.dieutri.delegate.DmLoaiThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DmPhanNhomThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.PhieuDuTruDelegate;
import com.iesvn.yte.dieutri.delegate.PhieuXuatKhoDelegate;
import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
import com.iesvn.yte.dieutri.delegate.YteLogDelegate;
import com.iesvn.yte.dieutri.entity.CtPhieuDt;
import com.iesvn.yte.dieutri.entity.CtXuatKho;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.PhieuDuTru;
import com.iesvn.yte.dieutri.entity.PhieuXuatKho;
import com.iesvn.yte.dieutri.entity.YteLog;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmLoaiPhieu;
import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.entity.DmPhanLoaiThuoc;
import com.iesvn.yte.entity.DmPhanNhomThuoc;
import com.iesvn.yte.util.IConstantsRes;

@Scope(CONVERSATION)
@Name("B4164_Phieuxuathangtutruc")
@Synchronized(timeout = 6000000)
public class XuatHangDenTuTruc implements Serializable {
	private static String FORMAT_DATE = "dd/MM/yyyy";
	private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";
	private String msgFail = "";
	private String msgSuccess = "";
	private String blockGhinhan = "";

	private String resultHidden = "";
	private static final long serialVersionUID = 10L;
	private static Logger log = Logger.getLogger(XuatHangDenTuTruc.class);
	private PhieuXuatKho phieuXuatKho;

	private boolean updateNhapct = false;
	private String ngayXuat;

	// Bien dung cho luoi du lieu
	private String maHang;
	private Integer maSoHang;

	private String tenHang;
	private Integer quyCach;
	private String donVi;
	private String tonKho;
	// private Double xinLinh;
	private Double donGia;
	private Double xuat;
	private String tongTien;

	private Integer nguonctMaso;
	private String nguonctMa;

	private Integer nguonkpMaso;
	private String nguonkpMa;

	private String maFinish;
	private String reportFinished = "";

	private String ngayhientai = "";

	private String resultReportFileName;
	private String resultReportName;
	private String loaiFileXuat;
	
	@Out(required = false)
	private String isReport = "false";

	private String maPhieuDuTru;
	private String maPhieuXuat;
	
	private Boolean hienThiGhiNhan=false;
	private Boolean hienThiHuyPhieu = false;
	private Boolean hienThiInPhieu=false;
	
	 private YteLogDelegate yteLogDele = YteLogDelegate.getInstance();
     private YteLog yteLog;
     private String listDataLog;
	
	@In(required = false)
	@Out(required = false)
	private String tenChuongTrinh;
	
	@DataModel
	private List<CtXuatKho> listCtXuatTuTrucEx = new ArrayList<CtXuatKho>();
	
	@DataModelSelection
	private CtXuatKho nhapctSelected_B4122;	
	
	@In(required = false)
	@Out(required = false)
	Identity identity;

	private DtDmNhanVien nvCn;
	
	private String dmLoaiTen="";
	private DmLoaiThuocDelegate dmLoaiThuocDelegate;
	private List<SelectItem> listDmLoaiThuocs = new ArrayList<SelectItem>();
	HashMap<String, DmLoaiThuoc> hmLoaiThuoc = new HashMap<String, DmLoaiThuoc>();
	private DmLoaiPhieuDelegate dmLoaiPhieuDelegate;
	private List<SelectItem> listDmLoaiPhieus = new ArrayList<SelectItem>();
	
	private DmKhoa dmKhoaXuat = new DmKhoa();

	@Begin(join = true)
	public String init(String loaikho) throws Exception {
		log.info("***Starting init ***");
		resetValue();
		phieuXuatKho = new PhieuXuatKho();
		SetInforUtil.setInforIfNullForPhieuXuatKho(phieuXuatKho);

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat(FORMAT_DATE);
		ngayXuat = formatter.format(cal.getTime());
		maFinish = "";
		blockGhinhan = "";
		SimpleDateFormat formatter1;
		formatter1 = new SimpleDateFormat(FORMAT_DATE);

		ngayhientai = formatter1.format(new Date());
		resultHidden = "";
		
		DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
		setNvCn(nvDelegate.findByNd(identity.getUsername()));
		if (getNvCn() == null) {
			setNvCn(new DtDmNhanVien());
		}
		log.info("nvCn" + nvCn);
		phieuXuatKho.setDtdmnhanvienCn(nvCn);

		nguonctMa = "";
		nguonkpMa = "";
		nguonctMaso = null;
		nguonkpMaso = null;

		if (phieuXuatKho.getPhieudtMa().getDmloaithuocMaso() == null) {
			phieuXuatKho.getPhieudtMa().setDmloaithuocMaso(new DmLoaiThuoc());
		}
		if (phieuXuatKho.getPhieudtMa().getDmphanloaithuocMaso() == null) {
			phieuXuatKho.getPhieudtMa().setDmphanloaithuocMaso(new DmPhanLoaiThuoc());
		}
		DieuTriUtilDelegate dtriUtil = DieuTriUtilDelegate.getInstance();	
		if ("KhoNoiTru".equals(loaikho)){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoNoiTru;
			dmKhoaXuat = (DmKhoa)dtriUtil.findByMa(IConstantsRes.KHOA_NT_MA, "DmKhoa", "dmkhoaMa");
		}else if ("BHYT".equals(loaikho)){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoBHYT;
			dmKhoaXuat = (DmKhoa)dtriUtil.findByMa(IConstantsRes.KHOA_BHYT_MA, "DmKhoa", "dmkhoaMa");
		}else if ("KC".equals(loaikho)){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoChinh;
			dmKhoaXuat = (DmKhoa)dtriUtil.findByMa(IConstantsRes.KHOA_KC_MA, "DmKhoa", "dmkhoaMa");
		}
		phieuXuatKho.setPhieuxuatkhoLoaiPhieu("");
		dmLoaiTen ="";
		listDmLoaiThuocs.clear();
		hmLoaiThuoc.clear();
		refreshDmLoaiThuoc();
		log.info("***End init ***");
		return "QuanLyKhoChinh_XuatKhoChinh_XuatHangDenTuTruc";
	}
	
	@End
	public void end(){
		
	}

	public void resetValue() {
		log.info("Begin resetValue()");
		maHang = "";
		tenHang = "";
		quyCach = 0;
		donVi = "";
		tonKho = "";
		donGia = new Double(0);
		xuat = new Double(0);
		log.info("End resetValue()");
	}

	/**
	 * 
	 * @param List
	 *            CtPhieuDt
	 */
	public void Caculation(List<CtXuatKho> ctxklist) {
		log.info("Begining Caculation: " + ctxklist);
		Double dTongTien = new Double(0);
		if (ctxklist != null) {

			for (CtXuatKho ctxk : ctxklist) {
				if (ctxk.getCtxuatkhoDongia() == null) {
					ctxk.setCtxuatkhoDongia(new Double(0));
				}
				if (ctxk.getCtxuatkhoSoluong() == null) {
					ctxk.setCtxuatkhoSoluong(new Double(0));
				}
				dTongTien += ctxk.getCtxuatkhoDongia().doubleValue()
						* ctxk.getCtxuatkhoSoluong().doubleValue();

			}
			log.info("Tong tien = " + dTongTien);
		}
		NumberFormat numberFormat = NumberFormat.getInstance();
		tongTien = numberFormat.format(dTongTien);
		
		log.info("End Caculation(): ");

	}

	private void updateRow() {
		copyTo(nhapctSelected_B4122);
		int i = listCtXuatTuTrucEx.indexOf(nhapctSelected_B4122);
		if (i < 0) {
			insertRow();
		}
		log.info("****i=" + i + "******");
		listCtXuatTuTrucEx.set(i, nhapctSelected_B4122);
		Caculation(listCtXuatTuTrucEx);
		updateNhapct = false;
	}

	private void insertRow() {
		log.info("begin cache chi tiet ket qua");

		CtXuatKho ctxk = new CtXuatKho();
		SetInforUtil.setInforIfNullForCTPhieuXuatKho(ctxk);
		copyTo(ctxk);
		listCtXuatTuTrucEx.add(ctxk);
		Caculation(listCtXuatTuTrucEx);
	}

	// Ham chuyen chi tiet nhap xuong duoi
	public void enter() throws Exception {
		log.info("*****Begin Enter() *****");

		if (maPhieuDuTru == null || maPhieuDuTru.equals("")) {
			return;
		}
		if (xuat == null || xuat == 0) {
			return;
		}
		if (maHang == null || maHang.equals("")) {
			return;
		}
		if (updateNhapct) {
			updateRow();

		} else {
			insertRow();
		}
		resetValue();
		maFinish = "";
		updateNhapct = false;
		log.info("*****End Enter() *****");
	}

	/**
	 * 
	 * @param chiTietPhieuDuTru
	 */
	private void copyTo(CtXuatKho ctXuatKho) {

		ctXuatKho.getDmthuocMaso().setDmthuocMaso(maSoHang);
		ctXuatKho.getDmthuocMaso().setDmthuocMa(maHang);
		ctXuatKho.getDmthuocMaso().setDmthuocTen(tenHang);

		ctXuatKho.getDmthuocMaso().getDmdonvitinhMaso()
				.setDmdonvitinhTen(donVi);
		log.info("donVi:" + donVi);
		ctXuatKho.setCtxuatkhoSoluong(xuat);
		ctXuatKho.setCtxuatkhoDongia(donGia);
	}

	/**
	 * 
	 * @param chiTietPhieuDuTru
	 */
	private void copyFrom(CtXuatKho ctXuatKho) {
		maHang = ctXuatKho.getDmthuocMaso().getDmthuocMa();
		maSoHang = ctXuatKho.getDmthuocMaso().getDmthuocMaso();
		tenHang = ctXuatKho.getDmthuocMaso().getDmthuocTen();
		donVi = ctXuatKho.getDmthuocMaso().getDmdonvitinhMaso()
				.getDmdonvitinhTen();
		xuat = ctXuatKho.getCtxuatkhoSoluong();
		donGia = ctXuatKho.getCtxuatkhoDongia();
	}

	// Ham delete chi tiet
	public void delete(int index) throws Exception {
		log.info("*****Begin delete() *****");
		
		if (nhapctSelected_B4122 != null){
			log.info("nhapctSelected.getCtxuatkhoDongia():"+nhapctSelected_B4122.getCtxuatkhoDongia());
			log.info("nhapctSelected.getCtxuatkhoSoluong():"+nhapctSelected_B4122.getCtxuatkhoSoluong());
		}
		
		log.info("listCtxk.indexOf(nhapctSelected):"+listCtXuatTuTrucEx.indexOf(nhapctSelected_B4122));
		
		listCtXuatTuTrucEx.remove(index);
		Caculation(listCtXuatTuTrucEx);
		resetValue();
		updateNhapct = false;
		maFinish = "";
		log.info("*****End delete() *****");
	}

	/**
	 * 
	 * @throws Exception
	 */
	public void nhapctAjax() throws Exception {

		log.info("*****Begin nhapctAjax() *****");

		copyFrom(nhapctSelected_B4122);
		updateNhapct = true;
		log.info("***********end nhapctAjax***********");

	}

	/**
	 * press GhiNhan button
	 * 
	 * @throws Exception
	 */
	public void ghinhan() throws Exception {
		log.info("*****Begin Ghi nhan() *****");
		yteLog = new YteLog();
		listDataLog = "";
		PhieuXuatKhoDelegate pxkWS = PhieuXuatKhoDelegate.getInstance();
		TonKhoDelegate tkDelegate = TonKhoDelegate.getInstance();
		log.info("phieuXuatKho.getPhieudtMa(): " + phieuXuatKho.getPhieudtMa().getPhieudtMa());		
			
		RemoveUtil.removeIfNullForPhieuXuatKho(phieuXuatKho);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
		if (ngayXuat != null && !ngayXuat.trim().equals("")) {
			Date d = formatter.parse(ngayXuat);
			phieuXuatKho.setPhieuxuatkhoNgaylap(d);
	
			log.info("phieuXuatKho.setPhieuxuatkhoNgaylap(): "+ phieuXuatKho.getPhieuxuatkhoNgaylap());
		}
			
		for (CtXuatKho ctxk : listCtXuatTuTrucEx) {
			RemoveUtil.removeIfNullForCTPhieuXuatKho(ctxk);
		}
		phieuXuatKho.setPhieuxuatkhoNgaygiocn(new Date());
		NumberFormat numberFormat = NumberFormat.getInstance();		
		phieuXuatKho.setPhieuxuatkhoThanhtien(numberFormat.parse(tongTien).doubleValue());
		maFinish = pxkWS.XuatPhieuDuTruTuTruc(listCtXuatTuTrucEx, phieuXuatKho, IConstantsRes.PRIORITY_TON_LO_THUOC);
			
		log.info("maFinish:" + maFinish);
	
		SetInforUtil.setInforIfNullForPhieuXuatKho(phieuXuatKho);
		for (CtXuatKho ctxk : listCtXuatTuTrucEx) {
			SetInforUtil.setInforIfNullForCTPhieuXuatKho(ctxk);

			//luu log thong tin thuoc
			listDataLog += "Ma LK:"+ ctxk.getCtxuatkhoMalk()+
					" Don gia: "+ ctxk.getCtxuatkhoDongia()+ " Don gia ban: "+ ctxk.getCtxuatkhoDongiaban() + 
					" So luong: "+ ctxk.getCtxuatkhoSoluong()+
					" So lo: "+ ctxk.getCtxuatkhoLo()+
					" Nam SX: " + ctxk.getCtxuatkhoNamnhap()+
					" Nam HD: " + ctxk.getCtxuatkhoNamhandung()+"\n";
		}
			
		if(maFinish != null && !maFinish.equals("")){
			log.info("maFinish:"+maFinish);
			if (maFinish.indexOf(".") >= 0){
				FacesMessages.instance().add(maFinish);
				 hienThiGhiNhan=false;
				 hienThiInPhieu = false;
			}else{
				FacesMessages.instance().add(IConstantsRes.SUCCESS);
				hienThiGhiNhan=false;
				hienThiInPhieu = true;
				maPhieuXuat  = maFinish;
			}
			
//			Luu log he thong
	         yteLog.setForm("B4164_Phieuxuathangtutruc");
	         yteLog.setUserId(identity == null ? "NULL" : identity.getUsername());
	         yteLog.setObjectId(maPhieuXuat);
	         yteLog.setLogString("Phieu du tru: "+ maPhieuDuTru  + 
	         					" Ngay xuat: "+ ngayXuat+
	         					" Loai thuoc: " + phieuXuatKho.getDmloaithuocMaso(true).getDmloaithuocMa()+
	         					" Loai phieu: "+phieuXuatKho.getPhieuxuatkhoLoaiPhieu()+
	         					" Kho nhap: "+ phieuXuatKho.getDmkhoaNhan(true).getDmkhoaMa()+
	         					" Khoa xuat: "+ phieuXuatKho.getDmkhoaXuat(true).getDmkhoaMa()+
	         					" Nguoi nhap: "+  phieuXuatKho.getDtdmnhanvienCn(true).getDtdmnhanvienMa()+
	         					" Nguoi lap: "+phieuXuatKho.getDtdmnhanvienNhan(true).getDtdmnhanvienMa()+
	         					" Nguoi ky: "+phieuXuatKho.getDtdmnhanvienBacsi(true).getDtdmnhanvienMa()+
	         					" Thanh tien: "+ tongTien);
	         yteLog.setDateTime(new Date());
	         yteLog.setListData(listDataLog);
		}
		else{
			FacesMessages.instance().add(IConstantsRes.FAIL);
			hienThiGhiNhan=false;
			hienThiInPhieu = false;
		}
	
		log.info("*****End Ghi nhan() *****");
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
		log.info("-----BEGIN onblurMaLoaiAction()-----"+phieuXuatKho.getDmloaithuocMaso().getDmloaithuocMa());
		String loaihang_ma = phieuXuatKho.getDmloaithuocMaso().getDmloaithuocMa();
		DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)hmLoaiThuoc.get(loaihang_ma.toUpperCase());
		if(dmLoaiThuoc != null) {
			setDmLoaiTen(dmLoaiThuoc.getDmloaithuocTen());
		}
		else {
			setDmLoaiTen("");			
		}
		listDmLoaiPhieus.clear();
		refreshDmLoaiPhieu();
		phieuXuatKho.setPhieuxuatkhoLoaiPhieu("");
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
			phieuXuatKho.setDmloaithuocMaso(dmLoaiThuoc_Find);
		}
	    listDmLoaiPhieus.clear();
		refreshDmLoaiPhieu();
		phieuXuatKho.setPhieuxuatkhoLoaiPhieu("");
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
		if(phieuXuatKho != null && phieuXuatKho.getDmloaithuocMaso() != null){
			List<DmLoaiPhieu> lstDmLoaiPhieus = dmLoaiPhieuDelegate.findAll();
			if(lstDmLoaiPhieus !=null && lstDmLoaiPhieus.size()>0)
			{
				for(DmLoaiPhieu o: lstDmLoaiPhieus){
					if(phieuXuatKho.getDmloaithuocMaso().getDmloaithuocMaso().equals(o.getDmloaithuocMaso().getDmloaithuocMaso())){
						listDmLoaiPhieus.add(new SelectItem(o.getDmloaiphieuTen()));
					}
				}
			}
		}
	}
	
	public String troVe() {
		try {
			log.info("***** troVe()");
			return "B4122_Xuathangtheophieudt";
		} catch (Exception e) {
			log.info("***** End exception = " + e);
		}
		return null;
	}

	public String getReportFinished() {
		return reportFinished;
	}

	public void setReportFinished(String reportFinished) {
		this.reportFinished = reportFinished;
	}

	/**
	 * press NhapLai button
	 * 
	 * @throws Exception
	 */
	public void nhaplai() throws Exception {
		log.info("*****Begin sualai() *****");
		init("");
		listCtXuatTuTrucEx = new ArrayList<CtXuatKho>();
		tongTien = "";
		maPhieuDuTru = "";
		maPhieuXuat="";
		this.maFinish = "";
		
		hienThiGhiNhan=false;
		 hienThiInPhieu = false;
		 
		log.info("*****End sualai() *****");
	}

	// hien thi thong tin khi nhap ma phieu xuat hang theo phieu du tru

	public void displayInforEditPhieuXuatHangTheoPhieuDuTru() throws Exception {
		try {

		} catch (Exception e) {
			System.out
					.println((new StringBuilder())
							.append(
									"error on function displayInforEditPhieuXuatHangTheoPhieuDuTru() ")
							.append(e).toString());
		}
	}
	
	//hien thi thong tin phieu xuat kho sau khi nhap ma phieu xuat kho
	public void displayPhieuXuatKho() throws Exception{
		log.info("Begin displayPhieuXuatKho()");
		listCtXuatTuTrucEx = new ArrayList<CtXuatKho>();
		if(maPhieuXuat == null || maPhieuXuat.equals("")){
			hienThiGhiNhan=false;
			hienThiHuyPhieu = false;			 
			return;
		}
		PhieuXuatKhoDelegate pxkDel = PhieuXuatKhoDelegate.getInstance();
		CtXuatKhoDelegate ctxkDel = CtXuatKhoDelegate.getInstance();
		
		phieuXuatKho = pxkDel.findPhieuXuatKhoByKhoXuat(maPhieuXuat, dmKhoaXuat.getDmkhoaMaso());
		if(phieuXuatKho == null){
			phieuXuatKho = new PhieuXuatKho();
			dmKhoaXuat = new DmKhoa();
			FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_NULL,maPhieuXuat);
			resultHidden = "fail";
			maPhieuDuTru="";
			
			hienThiGhiNhan = false;
			hienThiInPhieu = false;
			return;
		}
		SetInforUtil.setInforIfNullForPhieuXuatKho(phieuXuatKho);
		hienThiInPhieu = true;
		hienThiGhiNhan = false;
		setMaPhieuXuat(phieuXuatKho.getPhieuxuatkhoMa());
		setMaPhieuDuTru(phieuXuatKho.getPhieudtMa().getPhieudtMa());
		SimpleDateFormat frmtDate = new SimpleDateFormat("dd/MM/yyyy");		
		setNgayXuat(frmtDate.format(phieuXuatKho.getPhieuxuatkhoNgaylap()));
		dmLoaiTen = phieuXuatKho.getDmloaithuocMaso().getDmloaithuocTen();
		listCtXuatTuTrucEx = ctxkDel.findByphieuxuatkhoMa(maPhieuXuat);
		Caculation(listCtXuatTuTrucEx);
		maFinish =phieuXuatKho.getPhieuxuatkhoMa();
		log.info("End displayPhieuXuatKho()");
	}

	// Hien thi thong tin phieu du tru sau khi nhap Ma phieu du tru
	public void displayInfor() throws Exception {
		try {
			// thanh do add here
			// reset list of chi tiet xuat kho
			listCtXuatTuTrucEx = new ArrayList<CtXuatKho>();

			log.info("Begining displayInfor()");
			// CtPhieuDtWSService ctpdtService = new
			// CtPhieuDtWSServiceLocator();
			CtPhieuDtDelegate ctpdtWS = CtPhieuDtDelegate.getInstance();
			
			if (maPhieuDuTru == null || maPhieuDuTru.equals("")){				
				 hienThiGhiNhan=false;
				 hienThiHuyPhieu = false;				 
				return;
			}
			PhieuXuatKhoDelegate pxkDel = PhieuXuatKhoDelegate.getInstance();
			CtXuatKhoDelegate ctxkDel = CtXuatKhoDelegate.getInstance();
			
			PhieuXuatKho pxk = pxkDel.findByPhieuDuTruAndKhoXuat(maPhieuDuTru, dmKhoaXuat.getDmkhoaMaso());
			if(pxk != null){
				phieuXuatKho = pxk;
				SetInforUtil.setInforIfNullForPhieuXuatKho(phieuXuatKho);
				setMaPhieuXuat(phieuXuatKho.getPhieuxuatkhoMa());
				setMaPhieuDuTru(phieuXuatKho.getPhieudtMa().getPhieudtMa());
				dmLoaiTen = phieuXuatKho.getDmloaithuocMaso().getDmloaithuocTen();
				SimpleDateFormat frmtDate = new SimpleDateFormat("dd/MM/yyyy");		
				setNgayXuat(frmtDate.format(phieuXuatKho.getPhieuxuatkhoNgaylap()));
				listCtXuatTuTrucEx = ctxkDel.findByphieuxuatkhoMa(maPhieuXuat);
				Caculation(listCtXuatTuTrucEx);
				maFinish =phieuXuatKho.getPhieuxuatkhoMa();
				
				FacesMessages.instance().add(IConstantsRes.PHIEU_DU_TRU_DA_XUAT_HANG,maPhieuDuTru);
				hienThiGhiNhan=false;
				hienThiHuyPhieu = false;
				hienThiInPhieu = true;
				return;
			}
			
			
			PhieuDuTru phieuDt = new PhieuDuTru();			
			phieuDt=ctpdtWS.findByPhieuDuTruKhoXuatPhanBiet(maPhieuDuTru, CtPhieuDtDelegate.LANH_THUOC_TU_TRUC, dmKhoaXuat.getDmkhoaMaso());
			
			List<CtPhieuDt> ctpdt_tmp = ctpdtWS.findByPhieuDuTruMa(maPhieuDuTru);

			if (phieuDt == null || ctpdt_tmp==null || ctpdt_tmp.size()==0 || phieuDt.equals(null)) {
				// log.info("displayInfor phieu du tru 22 ....."+ phieuDuTru);
				System.out.println("displayInfor   phieu du tru 22 IConstantsRes.PHIEU_DU_TRU_NULL....." + IConstantsRes.PHIEU_DU_TRU_NULL);
				FacesMessages.instance().add(IConstantsRes.PHIEU_DU_TRU_NOT_FOUND,maPhieuDuTru);
				resultHidden = "fail";
				maPhieuDuTru="";
				
				hienThiGhiNhan=false;
				hienThiInPhieu = false;
				return;

			} else {
				//if (pdt_temp.getPhieudtMa() != null) {
					//PhieuDuTru phieuDuTru = pdt_temp.getPhieudtMa();

					phieuXuatKho = new PhieuXuatKho();
					phieuXuatKho.setPhieudtMa(phieuDt);

					phieuXuatKho.setDmkhoaNhan(phieuDt.getDmkhoaMaso());
					phieuXuatKho.setDmkhoaXuat(phieuDt.getPhieudtMakho());
					
					maPhieuDuTru = phieuDt.getPhieudtMa();
					if (phieuDt.getDmloaithuocMaso() == null) {
						phieuDt.setDmloaithuocMaso(new DmLoaiThuoc());
					}
					if (phieuDt.getDmphanloaithuocMaso() == null) {
						phieuDt.setDmphanloaithuocMaso(new DmPhanLoaiThuoc());
					}
					phieuXuatKho.setDmloaithuocMaso(phieuDt.getDmloaithuocMaso());
					dmLoaiTen = phieuDt.getDmloaithuocMaso().getDmloaithuocTen();
					phieuXuatKho.setPhieuxuatkhoLoaiPhieu(phieuDt.getPhieudtLoaiPhieu());
					phieuXuatKho.setDtdmnhanvienCn(nvCn);
				
				SetInforUtil.setInforIfNullForPhieuXuatKho(phieuXuatKho);

				listCtXuatTuTrucEx = new ArrayList<CtXuatKho>();

				if (ctpdt_tmp != null) {
					for (CtPhieuDt ctpdt : ctpdt_tmp) {
						CtXuatKho ctxk = new CtXuatKho();
						ctxk.setPhieuxuatkhoMa(phieuXuatKho);
						ctxk.setCtxuatkhoDongia(ctpdt.getCtdtDongia());
						ctxk.setCtxuatkhoSoluong(ctpdt.getCtdtSoluong());
						ctxk.setDmthuocMaso(ctpdt.getDmthuocMaso());
						
						ctxk.setCtxuatkhoMalk(ctpdt.getCtdtMalk());

						SetInforUtil.setInforIfNullForCTPhieuXuatKho(ctxk);

						listCtXuatTuTrucEx.add(ctxk);
					}
					Caculation(listCtXuatTuTrucEx);
				}
			}
			 hienThiGhiNhan=true;
			 hienThiInPhieu = false;
			 
			 PhieuDuTruDelegate pdtDelegate = PhieuDuTruDelegate.getInstance();
			 PhieuDuTru obj = pdtDelegate.find(maPhieuDuTru);
				if (obj.getPhieudtDaXuat()){
					FacesMessages.instance().add(IConstantsRes.PHIEU_DU_TRU_DA_XUAT_HANG,maPhieuDuTru);
					hienThiGhiNhan=false;
					hienThiHuyPhieu = false;
					hienThiInPhieu = true;
				}

		} catch (Exception e) {
			log.info((new StringBuilder()).append(
					"error on function displayInfor").append(e).toString());
		}
		log.info("maFinish : " + maFinish);
		log.info("End displayInfor()");
	}
	public String thuchienAction(){
		log.info("Begining inphieu()");
		if (!maFinish.equals("")) {
			try {
				XuatReport();
			} catch (Exception e) {
				log.info("Loi trong khi xuat report" + e.toString());
			}
		}

		log.info("End inphieu()");
		
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
		reportTypeKC="B4164_Phieuxuathangtutruc";
		log.info("Vao Method XuatReport B4164_Phieuxuathangtutruc");
		String baocao1=null;
		Date currentDate = new Date();

		
			
		try {
			log.info("bat dau method XuatReport ");
			String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/phieuxuatkho_02.jrxml";
			log.info("da thay file templete 5" + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			String khoa = "";
			
//			params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
//			params.put("donvi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
//			params.put("khoa", khoa);
//			params.put("ngayXuat", ngayXuat);
//			params.put("maphieu", maFinish);
//			log.info("Ma phieu :" + maFinish);
			params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			Calendar cal = Calendar.getInstance();
			if (cal != null) {
				log.info(String.format("-----ngay lap: %s", cal.getTime()));
				params.put("ngayHt", "" + cal.get(Calendar.DAY_OF_MONTH));
				params.put("thangHt", "" + (cal.get(Calendar.MONTH) + 1));
				params.put("namHt", "" + cal.get(Calendar.YEAR));
			} 
			
			SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
			String ngayGioHt = df.format(Calendar.getInstance().getTime());
			log.info(String.format("-----ngay gio hien tai: %s", ngayGioHt));
			params.put("gioHt", ngayGioHt);
			
			params.put("pxMa", maFinish);
			
			DieuTriUtilDelegate dieutriUtilDelegate = DieuTriUtilDelegate.getInstance();
			phieuXuatKho = (PhieuXuatKho)dieutriUtilDelegate.findByMa(maFinish, "PhieuXuatKho", "phieuxuatkhoMa");
			
					
			
			if (phieuXuatKho.getDmkhoaNhan() != null) {
				params.put("khoaNhan", phieuXuatKho.getDmkhoaNhan().getDmkhoaTen());
			} else {
				params.put("khoaNhan", "");
			}
			log.info(String.format("-----khoaNhan: %s", params.get("khoaNhan")));

			if (phieuXuatKho.getDmkhoaXuat() != null) {
				params.put("khoaXuat", phieuXuatKho.getDmkhoaXuat().getDmkhoaTen());
			} else {
				params.put("khoaXuat", "");
			}
			
			params.put("tongTien", phieuXuatKho.getPhieuxuatkhoThanhtien());
			
			if(phieuXuatKho.getDtdmnhanvienCn() != null){
				params.put("nhanvienCn", phieuXuatKho.getDtdmnhanvienCn().getDtdmnhanvienMa());
			}
			
			if(phieuXuatKho.getPhieuxuatkhoNgaylap() != null){
				params.put("ngayXuat", phieuXuatKho.getPhieuxuatkhoNgaylap());
			}
			
			resetInfo();
			
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintKC,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"duocpham/", "pdf","B4122_XuatHangTheoPhieuDuTru");
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
		
		log.info("Thoat Method XuatReport");
	}
	
	private void resetInfo() {
		log.info("-----resetInfo()-----");
		if (phieuXuatKho.getDmloaithuocMaso() == null) {
			phieuXuatKho.setDmloaithuocMaso(new DmLoaiThuoc());
		}
		if (phieuXuatKho.getDtdmnhanvienNhan() == null) {
			phieuXuatKho.setDtdmnhanvienNhan(new DtDmNhanVien());
		}
		if (phieuXuatKho.getDmdoituongMaso() == null) {
			phieuXuatKho.setDmdoituongMaso(new DmDoiTuong());
		}
		if (phieuXuatKho.getDtdmnhanvienCn() == null) {
			phieuXuatKho.setDtdmnhanvienCn(new DtDmNhanVien());
		}	
		if (phieuXuatKho.getPhieudtMa().getDmphanloaithuocMaso() == null) {
			phieuXuatKho.getPhieudtMa().setDmphanloaithuocMaso( new DmPhanLoaiThuoc());
		}
		if (phieuXuatKho.getDtdmnhanvienBacsi() == null) {
			phieuXuatKho.setDtdmnhanvienBacsi(new DtDmNhanVien());
		}
		
	}
	
	public static String getFORMAT_DATE() {
		return FORMAT_DATE;
	}

	public static void setFORMAT_DATE(String format_date) {
		FORMAT_DATE = format_date;
	}

	public static String getFORMAT_DATE_TIME() {
		return FORMAT_DATE_TIME;
	}

	public static void setFORMAT_DATE_TIME(String format_date_time) {
		FORMAT_DATE_TIME = format_date_time;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getMaHang() {
		return maHang;
	}

	public void setMaHang(String maHang) {
		this.maHang = maHang;
	}

	public Integer getQuyCach() {
		return quyCach;
	}

	public void setQuyCach(Integer quyCach) {
		this.quyCach = quyCach;
	}

	public String getDonVi() {
		return donVi;
	}

	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}

	public String getTonKho() {
		return tonKho;
	}

	public void setTonKho(String tonKho) {
		this.tonKho = tonKho;
	}

	public Double getDonGia() {
		return donGia;
	}

	public void setDonGia(Double donGia) {
		this.donGia = donGia;
	}

	public boolean isUpdateNhapct() {
		return updateNhapct;
	}

	public void setUpdateNhapct(boolean updateNhapct) {
		this.updateNhapct = updateNhapct;
	}

	public String getTongTien() {
		return tongTien;
	}

	public void setTongTien(String tongTien) {
		this.tongTien = tongTien;
	}

	public String getNgayXuat() {
		return ngayXuat;
	}

	public void setNgayXuat(String ngayXuat) {
		this.ngayXuat = ngayXuat;
	}

	public String getMaFinish() {
		return maFinish;
	}

	public void setMaFinish(String maFinish) {
		this.maFinish = maFinish;
	}

	public String getNgayhientai() {
		return ngayhientai;
	}

	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
	}

	public String getMsgFail() {
		return msgFail;
	}

	public void setMsgFail(String msgFail) {
		this.msgFail = msgFail;
	}

	public String getMsgSuccess() {
		return msgSuccess;
	}

	public void setMsgSuccess(String msgSuccess) {
		this.msgSuccess = msgSuccess;
	}

	public String getResultHidden() {
		return resultHidden;
	}

	public void setResultHidden(String resultHidden) {
		this.resultHidden = resultHidden;
	}

	public Double getXuat() {
		return xuat;
	}

	public void setXuat(Double xuat) {
		this.xuat = xuat;
	}

	public String getBlockGhinhan() {
		return blockGhinhan;
	}

	public void setBlockGhinhan(String blockGhinhan) {
		this.blockGhinhan = blockGhinhan;
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

	public PhieuXuatKho getPhieuXuatKho() {
		return phieuXuatKho;
	}

	public void setPhieuXuatKho(PhieuXuatKho phieuXuatKho) {
		this.phieuXuatKho = phieuXuatKho;
	}

	public List<CtXuatKho> getListCtxk() {
		return listCtXuatTuTrucEx;
	}

	public void setListCtxk(List<CtXuatKho> listCtxk) {
		this.listCtXuatTuTrucEx = listCtxk;
	}

	public DtDmNhanVien getNvCn() {
		return nvCn;
	}

	public void setNvCn(DtDmNhanVien nvCn) {
		this.nvCn = nvCn;
	}

	public String getMaPhieuDuTru() {
		return maPhieuDuTru;
	}

	public void setMaPhieuDuTru(String maPhieuDuTru) {
		this.maPhieuDuTru = maPhieuDuTru;
	}

	// public String getPhanLoaiMa() {
	// return phanLoaiMa;
	// }
	//
	// public void setPhanLoaiMa(String phanLoaiMa) {
	// this.phanLoaiMa = phanLoaiMa;
	// }
	//
	// public Integer getPhanLoaiMaso() {
	// return phanLoaiMaso;
	// }
	//
	// public void setPhanLoaiMaso(Integer phanLoaiMaso) {
	// this.phanLoaiMaso = phanLoaiMaso;
	// }

	public Integer getNguonctMaso() {
		return nguonctMaso;
	}

	public void setNguonctMaso(Integer nguonctMaso) {
		this.nguonctMaso = nguonctMaso;
	}

	public String getNguonctMa() {
		return nguonctMa;
	}

	public void setNguonctMa(String nguonctMa) {
		this.nguonctMa = nguonctMa;
	}

	public Integer getNguonkpMaso() {
		return nguonkpMaso;
	}

	public void setNguonkpMaso(Integer nguonkpMaso) {
		this.nguonkpMaso = nguonkpMaso;
	}

	public String getNguonkpMa() {
		return nguonkpMa;
	}

	public void setNguonkpMa(String nguonkpMa) {
		this.nguonkpMa = nguonkpMa;
	}

	public Integer getMaSoHang() {
		return maSoHang;
	}

	public void setMaSoHang(Integer maSoHang) {
		this.maSoHang = maSoHang;
	}

	public String getTenHang() {
		return tenHang;
	}

	public void setTenHang(String tenHang) {
		this.tenHang = tenHang;
	}

	public CtXuatKho getNhapctSelected_B4122() {
		return nhapctSelected_B4122;
	}

	public void setNhapctSelected_B4122(CtXuatKho nhapctSelected_B4122) {
		this.nhapctSelected_B4122 = nhapctSelected_B4122;
	}

	
	

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	public String getMaPhieuXuat() {
		return maPhieuXuat;
	}

	public void setMaPhieuXuat(String maPhieuXuat) {
		this.maPhieuXuat = maPhieuXuat;
	}

	public Boolean getHienThiHuyPhieu() {
		return hienThiHuyPhieu;
	}

	public void setHienThiHuyPhieu(Boolean hienThiHuyPhieu) {
		this.hienThiHuyPhieu = hienThiHuyPhieu;
	}

	public Boolean getHienThiInPhieu() {
		return hienThiInPhieu;
	}

	public void setHienThiInPhieu(Boolean hienThiInPhieu) {
		this.hienThiInPhieu = hienThiInPhieu;
	}

	public void setHienThiGhiNhan(Boolean hienThiGhiNhan) {
		this.hienThiGhiNhan = hienThiGhiNhan;
	}

	public Boolean getHienThiGhiNhan() {
		return hienThiGhiNhan;
	}

	
}


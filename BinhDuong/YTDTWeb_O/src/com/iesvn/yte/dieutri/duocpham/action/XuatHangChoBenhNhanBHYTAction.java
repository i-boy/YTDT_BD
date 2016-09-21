
package com.iesvn.yte.dieutri.duocpham.action;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.xml.rpc.ServiceException;

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
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
import com.iesvn.yte.dieutri.delegate.CtXuatBhDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
import com.iesvn.yte.dieutri.delegate.PhieuXuatBhDelegate;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
import com.iesvn.yte.dieutri.delegate.YteLogDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.CtXuatBh;
import com.iesvn.yte.dieutri.entity.DtDmBanKham;
import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.HsThtoank;
import com.iesvn.yte.dieutri.entity.PhieuXuatBh;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.dieutri.entity.YteLog;
import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
import com.iesvn.yte.entity.DmBenhIcd;
import com.iesvn.yte.entity.DmBenhVien;
import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmThuoc;
import com.iesvn.yte.entity.DmTinh;
import com.iesvn.yte.util.HoSoThanhToanKhamUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;
import org.jboss.seam.security.Identity;

@Scope(ScopeType.CONVERSATION)
@Name("B4311_Xuathangchobenhnhan")
@Synchronized(timeout = 6000000)
public class XuatHangChoBenhNhanBHYTAction implements Serializable {
	
	private static final long serialVersionUID = 10L;
	private static Logger log = Logger.getLogger(XuatHangChoBenhNhanBHYTAction.class);
	
	private PhieuXuatBh phieuxuatbh;
	private BenhNhan benhnhan;
	private TiepDon tiepdon;
	
	@DataModel
	private List<CtXuatBh> listCtXuatBh;
	@DataModelSelection(value = "listCtXuatBh")
	private CtXuatBh ctxuatbhSelected;
	private CtXuatBh ctxuatbh;
	
	@In(required = false)
	@Out(required = false)
	private String goToCLSPhongKham;
	
	@DataModel
	private java.util.ArrayList<ClsKham> listCLS_XuatHangChoBNBHYT;
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
	@Out(required=false)
	@In(required=false)
	private String reportPathKC=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeKC=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintKC = null;
	
	private int index=0;
	
	private TonKho[] listTonkho;
	private String nvBacsi;
	private String nvPhat;
	private Integer nvPhatSo;
	private String tdBankham;
	private String tdDoituong;
	private String tdDoituongMa;
	private String tdKcbBhyt;
	private String tdChandoan;
	private String nvCapnhat;
	private String pxNgaylap;
	
	private String bnNamsinh;
	private String bnGtinh;	
	private String tdTinhBhyt;
	private String tdKhoiBhyt;	
	
	private String pxPhantramBenhnhan;
	private String pxClsBobot;
	
	private String tkMathuoc;
	private String tkQuocgiaSx;
	private String tkHangSx;
	private String ctxThanhtien;
//	private Double pxSotien; 
	private String tkDongiaban;
	
	private String pxMiengiam;
	private String pxThatthu;
	
	private TonKho tonKho;
	private String tonkhoMa;
	private String ctxSoluong;
	
	private String thieuthuoc = "0";
	private String updateCtXuat;
	private double thanhtienCt = 0;
	private String saveResult;
	private String sNoiDungThu;
	
	
	// Phan danh cho tinh toan
	private int permiengiam = 0;
	private Double miengiam = new Double(0);
	private Double thatthu = new Double(0);
	private int perbhytchi = 0;
	private Double bhytchi = new Double(0);
	private Double thanhtien1 = new Double(0);
	private Double tongtien = new Double(0);
	private int perbntra = 0;
	private Double bntra = new Double(0);
	
	private Double cls;
	private Double thuoc;
	//////////////////////////
	private DmKhoa dmKhoXuat=new DmKhoa();
	
	 private YteLogDelegate yteLogDele = YteLogDelegate.getInstance();
     private YteLog yteLog;
     private String listDataLog;
	
	private List<ThuocPhongKham> listCtTPK_temp = null; // day la Thuoc Ban Kham va Ke toa quay BV, khi thanh toan kham -> tinh luon
		
	public String clsphongkham() throws ServiceException, RemoteException {
		// log.info(hosoCBSelected.getSoHS());
		
		if (benhnhan.getBenhnhanHoten() == null || benhnhan.getBenhnhanHoten().equals("")){
			FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND);
		
			return "";
		}
		
		
		maTiepDonOut = tiepdon.getTiepdonMa();
		
		goToCLSPhongKham = null;
	
		return "ThuVienPhi_SoLieuKhamBenh_CanLamSanPhongKham";
	}
	
	private String reportFinished;
	private String reportFileName;
	
	private String resultReportFileName;
	private String resultReportName;	
	private String loaiFileXuat;
	@Out(required = false)
	private String isReport = "false";
	
	private String nosuccess;
	private String nofoundTD;
	
	private HsThtoank hsttk;
	
	
	public String getNofoundTD() {
		return nofoundTD;
	}

	public void setNofoundTD(String nofoundTD) {
		this.nofoundTD = nofoundTD;
	}
	
	
	@In(required = false)
	@Out(required = false)
	private String tenChuongTrinh;

	private void reset(){
		phieuxuatbh = new PhieuXuatBh();
		ctxuatbh = new CtXuatBh();
		tiepdon = new TiepDon();
		benhnhan = new BenhNhan();
		SetInforUtil.setInforIfNullForBN(benhnhan);
		tongtien = new Double(0);
		listCtXuatBh = new ArrayList<CtXuatBh>();		
		pxNgaylap = Utils.getCurrentDate();
		updateCtXuat = "0";
		pxPhantramBenhnhan = "1";
		sNoiDungThu = "";
//		bankham = new DtDmBanKham();
		nosuccess = "false";
		nofoundTD = "false";
		listCLS_XuatHangChoBNBHYT= new ArrayList<ClsKham>();
	}
	public String getGoToCLSPhongKham() {
		return goToCLSPhongKham;
	}

	public void setGoToCLSPhongKham(String goToCLSPhongKham) {
		this.goToCLSPhongKham = goToCLSPhongKham;
	}

	public String getReportPathKC() {
		return reportPathKC;
	}

	public void setReportPathKC(String reportPathKC) {
		this.reportPathKC = reportPathKC;
	}

	public String getReportTypeKC() {
		return reportTypeKC;
	}

	public void setReportTypeKC(String reportTypeKC) {
		this.reportTypeKC = reportTypeKC;
	}

	public JasperPrint getJasperPrintKC() {
		return jasperPrintKC;
	}

	public void setJasperPrintKC(JasperPrint jasperPrintKC) {
		this.jasperPrintKC = jasperPrintKC;
	}

	public String getSNoiDungThu() {
		return sNoiDungThu;
	}

	public void setSNoiDungThu(String noiDungThu) {
		sNoiDungThu = noiDungThu;
	}

	public int getPermiengiam() {
		return permiengiam;
	}

	public void setPermiengiam(int permiengiam) {
		this.permiengiam = permiengiam;
	}

	public Double getMiengiam() {
		return miengiam;
	}

	public void setMiengiam(Double miengiam) {
		this.miengiam = miengiam;
	}

	public Double getThatthu() {
		return thatthu;
	}

	public void setThatthu(Double thatthu) {
		this.thatthu = thatthu;
	}

	public int getPerbhytchi() {
		return perbhytchi;
	}

	public void setPerbhytchi(int perbhytchi) {
		this.perbhytchi = perbhytchi;
	}

	public Double getBhytchi() {
		return bhytchi;
	}

	public void setBhytchi(Double bhytchi) {
		this.bhytchi = bhytchi;
	}

	public Double getTongtien() {
		return tongtien;
	}

	public void setTongtien(Double tongtien) {
		this.tongtien = tongtien;
	}

	public Double getThanhtien1() {
		return thanhtien1;
	}

	public void setThanhtien1(Double thanhtien1) {
		this.thanhtien1 = thanhtien1;
	}

	public int getPerbntra() {
		return perbntra;
	}

	public void setPerbntra(int perbntra) {
		this.perbntra = perbntra;
	}

	public Double getBntra() {
		return bntra;
	}

	public void setBntra(Double bntra) {
		this.bntra = bntra;
	}

	public Double getCls() {
		return cls;
	}

	public void setCls(Double cls) {
		this.cls = cls;
	}

	public Double getThuoc() {
		return thuoc;
	}

	public void setThuoc(Double thuoc) {
		this.thuoc = thuoc;
	}

	public List<ThuocPhongKham> getListCtTPK_temp() {
		return listCtTPK_temp;
	}

	public void setListCtTPK_temp(List<ThuocPhongKham> listCtTPK_temp) {
		this.listCtTPK_temp = listCtTPK_temp;
	}

	public String getTenChuongTrinh() {
		return tenChuongTrinh;
	}

	public void setTenChuongTrinh(String tenChuongTrinh) {
		this.tenChuongTrinh = tenChuongTrinh;
	}

//	public String getGoToThuocBHYT() {
//		return goToThuocBHYT;
//	}
//
//	public void setGoToThuocBHYT(String goToThuocBHYT) {
//		this.goToThuocBHYT = goToThuocBHYT;
//	}

	public boolean isDaThanhToan() {
		return daThanhToan;
	}

	public void setDaThanhToan(boolean daThanhToan) {
		this.daThanhToan = daThanhToan;
	}

	public double getGioiHanTyLe() {
		return gioiHanTyLe;
	}

	public void setGioiHanTyLe(double gioiHanTyLe) {
		this.gioiHanTyLe = gioiHanTyLe;
	}

	public double getTylebhyt() {
		return tylebhyt;
	}

	public void setTylebhyt(double tylebhyt) {
		this.tylebhyt = tylebhyt;
	}

	public double getTylebhyt2() {
		return tylebhyt2;
	}

	public void setTylebhyt2(double tylebhyt2) {
		this.tylebhyt2 = tylebhyt2;
	}

	public List<ClsKham> getClslist() {
		return clslist;
	}

	public void setClslist(List<ClsKham> clslist) {
		this.clslist = clslist;
	}

	@Begin(join = true)
	public String init(String thuvienphi){
		log.info("---------begin init method-------");
		reset();
		log.info("---------end init method-------");
		hsttk = new HsThtoank();
		DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
		if ("thuvienphi".equals(thuvienphi)){
			//dang o khu vuc thu vien phi
		}else if ("TE".equals(thuvienphi)){ // khu vuc kho tre em			
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoTE;
			dmKhoXuat  = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_TE_MA, "DmKhoa", "dmkhoaMa");
		}else if ("BHYT".equals(thuvienphi)){ // khu vuc kho BHYT			
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoBHYT;
			dmKhoXuat  = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_BHYT_MA, "DmKhoa", "dmkhoaMa");
		}else if("KC".equals(thuvienphi)){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoChinh;
			dmKhoXuat  = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_KC_MA, "DmKhoa", "dmkhoaMa");
		}else if("NT".equals(thuvienphi)){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoNoiTru;
			dmKhoXuat  = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_NT_MA, "DmKhoa", "dmkhoaMa");
		}
		return "QuanLyKhoBHYT_XuatKhoBHYT_XuatHangChoBenhNhan";
	}
	@End
	public void endFucntion(){
		
	}
	
	public void fillCtXuatBh(){
		log.info("---Begin fillCtXuatBh() method----");
		String message = "";
		ctxuatbh = ctxuatbhSelected;
		//TonKho tk = ctxuatbh.getTonkhoMa();
		tonKho = new TonKho();
		
		if(tonKho != null){
			tonkhoMa = ""+tonKho.getTonkhoMa();
			DmThuoc thuoc = tonKho.getDmthuocMaso();
			tkMathuoc = thuoc.getDmthuocMa();
			tkQuocgiaSx = tonKho.getDmquocgiaMaso().getDmquocgiaTen();
			tkHangSx = tonKho.getDmnhasanxuatMaso().getDmnhasanxuatTen();
			double dongiaBan = tonKho.getTonkhoDongiaban();
			tkDongiaban = "" + dongiaBan;
			//ctxSoluong = ""+ ctxuatbh.getCtxuatbhSoluong();
			ctxThanhtien = ""+ (ctxuatbh.getCtxuatbhSoluong() * dongiaBan);
			updateCtXuat = ""+1;
			
		}else{
			log.info("tonKho IS NULL");
		}
		FacesMessages.instance().add(message);
		log.info("---End fillCtXuatBh() method----");
	}
	
	public void createCtXuatBh(){
		log.info("---Begin createCtXuatBh() method----");
		String message = "";	
		try{
			
			TonKhoDelegate tkPort = TonKhoDelegate.getInstance();
			if(updateCtXuat.equals(""+1)){
				log.info("call updateCtXuatbh method");
				//boolean isDuplicate = this.checkIsDuplicateTonkho(Integer.parseInt(tonkhoMa), ctxuatbh.getCtxuatbhThutu(), "update");
				boolean isDuplicate = false; // ham check da bi remove, do nothing
				if(isDuplicate){
					log.info("****update danh muc thuoc da duoc chon****");
					message = IConstantsRes.THUOC_EXIST;
					this.resetCtXuatBh();
				}
				else{
					this.updateCtXuatBh();
				}
			}
			else{
				if(tonkhoMa != null && ! tonkhoMa.trim().equals("")){
					log.info("create ctxuatbh");
					//boolean isDuplicate = this.checkIsDuplicateTonkho(Integer.parseInt(tonkhoMa), 0, "create");
					boolean isDuplicate = false; // ham check da bi remove, do nothing
					if(isDuplicate){
						message = IConstantsRes.THUOC_EXIST;
						log.info("create danh muc thuoc da duoc chon");
						this.resetCtXuatBh();
					}
					else{
						tonKho = tkPort.find(Integer.parseInt(tonkhoMa));
						if(tonKho != null){
							Double tkTon = tonKho.getTonkhoTon();
							if(tkTon == null){
								tkTon = 0.0;
							}
							if(ctxuatbh.getCtxuatbhSoluong() > tkTon){
								message = IConstantsRes.TK_TON_NOTENOUGH +" "+ tonKho.getDmthuocMaso().getDmthuocTen();
								thieuthuoc = ""+1;
							}
							else{
								int size = listCtXuatBh.size();
								ctxuatbh.setCtxuatbhMa(0);
								//ctxuatbh.setTonkhoMa(tonKho);
								ctxuatbh.setCtxuatbhThutu(size + 1);
								listCtXuatBh.add(ctxuatbh);
								
								this.resetCtXuatBh();								
							}
						}
						else{
							log.info("tonKho IS NULL");
							//message += "Chọn mã hàng";
							this.resetCtXuatBh();
						}
					}
				}
				else{
					log.info("tonkhoMa IS NULL");
					this.resetCtXuatBh();
					//message += "Chọn mã hàng";
				}
			}
			FacesMessages.instance().add(message);
			log.info("---End createCtXuatBh() method----");
		}catch (Exception e) {
			thieuthuoc = ""+0;
			log.info(":( ERROR " + e);
		}	
	}
	
	@In(required = false)
	@Out(required = false)
	private String maBanKhamOut;
	
	@In(required = false)
	@Out(required = false)
	private String maTiepDonOut;
	
	
//	@In(required = false)
//	@Out(required = false)
//	private String initB121_3_Xutrithuoctaibankham;
	
//	@In(required = false)
//	@Out(required = false)
//	private String loaiToaThuocThamKhamVaXuTri ;
	
//	@In(required = false)
//	@Out(required = false)
//	private String returnToXuatHangChoBNBHYT;
	
//	@In(required = false)
//	@Out(required = false)
//	private String goToThuocBHYT;
	
//	@Factory("goToThuocBHYT")
	public void goToThuocBHYTForm() throws Exception {
		log.info("begin goToThuocBHYTForm");
//		returnToXuatHangChoBNBHYT = "done";
//		goToThuocBHYT = null;
		reset();
		tiepdon.setTiepdonMa(maTiepDonOut);
		
		if (tiepdon != null &&  tiepdon.getTiepdonMa() != null){
			log.info("loadTiepdonAjax () ");
			loadTiepdonAjax();
		}
		log.info("end goToThuocBHYTForm");
	}
	
//	@Factory("returnToXuatHangChoBNBHYT")
	public void initForReturn() throws Exception {
		log.info("begin initForReturn");
//		goToThuocBHYT = "done";
		//log.info("bankham:"+bankham);

//		if (tiepdon != null && bankham != null && tiepdon.getTiepdonMa() != null && bankham.getDtdmbankhamMa() != null){
//			log.info("loadTiepdonAjax () ");
//			loadTiepdonAjax();
//		}
		if (tiepdon != null &&  tiepdon.getTiepdonMa() != null){
			log.info("loadTiepdonAjax () ");
			loadTiepdonAjax();
		}
		// bao.ttc
		resetForm();
		log.info("end initForReturn");
	}
	
//	public String nhapPhieuBHYT(){
//		
//		log.info("*****begin nhapPhieuBHYT()");
//		if (phieuxuatbh.getPhieuxuatbhMa() != null && !phieuxuatbh.getPhieuxuatbhMa().equals("")){
//			return "";
//		}
//		
//		maTiepDonOut = tiepdon.getTiepdonMa();
////		maBanKhamOut = bankham.getDtdmbankhamMa();
//		
//		
//		
//				
//		initB121_3_Xutrithuoctaibankham = null;
//		loaiToaThuocThamKhamVaXuTri = Utils.KE_TOA_QUAY_BENH_VIEN;
////		returnToXuatHangChoBNBHYT="1";
//		
//		log.info("*****End nhapPhieuBHYT()");
//		return "xutrithuoctaibankham";
//	}
	
	private void updateCtXuatBh(){
		log.info("---Begin updateCtXuatBh() method----");
		try{
			if(tonkhoMa != null && ! tonkhoMa.equals("") 
					&& ctxuatbh.getCtxuatbhSoluong() != 0){
				String message = "";
				
				
				CtXuatBhDelegate ctxWS = CtXuatBhDelegate.getInstance();
				TonKhoDelegate tkPort = TonKhoDelegate.getInstance();
				//TonKho tkNew = tkPort.findByDmThuocMaAndQuocGiaAndHangSX(tkMathuoc,tkQuocgiaSxMa, tkHangSxMa);
				tonKho = tkPort.find(Integer.parseInt(tonkhoMa));
				if(tonKho != null){
					if(ctxuatbh != null){
						double slNew = ctxuatbh.getCtxuatbhSoluong();
						
						if(ctxuatbh.getPhieuxuatbhMa() != null){
							
							Integer ctxMa = ctxuatbh.getCtxuatbhMa();
							
							CtXuatBh ctxOld = null;
							if(ctxMa != null){
								ctxOld = ctxWS.find(ctxMa);
							}
							if(ctxOld != null){
								TonKho tkOld = new TonKho();
								DmThuoc thuocOld = tkOld.getDmthuocMaso();
								
								double tkTonOld = tkOld.getTonkhoTon();
								double slOld = ctxOld.getCtxuatbhSoluong();
								
								if(tonKho.getTonkhoMa().equals(tkOld.getTonkhoMa())){
									if(tkTonOld + slOld - slNew < 0){
										message = "Số lượng tồn kho không đủ cho thuốc " +  thuocOld.getDmthuocTen();
										thieuthuoc = ""+1;
									}
									else{
										listCtXuatBh.remove(ctxuatbh.getCtxuatbhThutu().intValue() - 1);
										listCtXuatBh.add(ctxuatbh.getCtxuatbhThutu().intValue() - 1, ctxuatbh);
										this.resetCtXuatBh();
									}
								}else{
									this.capnhatCtXuatBhMoi(tonKho, slNew, message);
								}
							}
							else{
								this.capnhatCtXuatBhMoi(tonKho, slNew, message);
							}
						}
						else{
							this.capnhatCtXuatBhMoi(tonKho, slNew, message);
						}
					}else{
						log.info("ctxuatbh IS NULL");
					}
				}
				else{
					log.info("Khong tim thay ton kho ung voi danh muc thuoc");
				}
				FacesMessages.instance().add(message);
			}
		}catch (Exception e) {
			log.info(":( ERROR: " + e);
		}
		
		log.info("---End updateCtXuatBh() method----");
	}
	
	private List<ThuocPhongKham> listTPK;
	
	public void ghiNhan(){
		log.info("ghiNhan");
		HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
		hsttk = hsttkDelegate.findBytiepdonMa(tiepdon.getTiepdonMa());
		
		// 20120619 bao.ttc: thay doi nghiep vu, BN phai di thanh toan truoc khi linh thuoc cho du tien BN tra = 0
		// (Nghiep vu truoc: neu tien BN tra = 0 thi tu dong thanh toan va van cho linh thuoc)
		if (hsttk == null) {
			FacesMessages.instance().add(IConstantsRes.benh_nhan_phai_thu_vien_phi);
			resetTiepDon();
			return;							
		}

//		Double thanhtoan = 0.0;
//		if (hsttk == null){
//			hsttk = new HsThtoank();
//			hsttk.setTiepdonMa(tiepdon);
//			tinhToanChoHSTKKham(hsttk);						
//			thanhtoan = hsttk.getHsthtoankThtoan();
//			if(thanhtoan == 0.0){
//				hsttk.setHsthtoankDatt(true);
//				hsttk.setHsthtoankNgaygiott(new Date());
//				hsttkDelegate.capNhatTTHsttk(hsttk,clslist,listCtTPK_temp, false);
//			}else{
//				FacesMessages.instance().add(IConstantsRes.benh_nhan_phai_thu_vien_phi);
//				resetTiepDon();
//				return;							
//			}					
//		}
		
		List<PhieuXuatBh> phieuXuatBhTemp = (List<PhieuXuatBh>) PhieuXuatBhDelegate.getInstance().findByTiepDonMa_Kho(tiepdon.getTiepdonMa(),dmKhoXuat.getDmkhoaMaso());
		if(phieuXuatBhTemp.size()>0){
			nosuccess = "true";
			FacesMessages.instance().add(IConstantsRes.PHIEUXUATBH_DAXUAT);
			return;
		}
		if (phieuxuatbh.getPhieuxuatbhMa() != null && !phieuxuatbh.getPhieuxuatbhMa().equals("")){
			return ;
		}
			try {
				ThamKham tk_tmp = ThamKhamDelegate.getInstance().findByMaTiepDon( tiepdon.getTiepdonMa()); 
				if (tk_tmp==null){
					nosuccess = "true";
					FacesMessages.instance().add(IConstantsRes.XUATHANG_BENHNHAN_BHYT_THATBAI);
					return;
				}
				
				log.info("daThanhToan:"+daThanhToan);
				
				String rs = "";
				if (listTPK != null && listCtXuatBh != null){
					phieuxuatbh.setTiepdonMa(tiepdon);				
					DtDmNhanVien dmNvPhat = new DtDmNhanVien();
					dmNvPhat.setDtdmnhanvienMa(nvPhat);
					dmNvPhat.setDtdmnhanvienMaso(nvPhatSo);
					phieuxuatbh.setDtdmnhanvienPhat(dmNvPhat);
					//begin Tho add
					DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
					DtDmNhanVien nhanViencn = nvDelegate.findByNd(identity.getUsername());
					phieuxuatbh.setDtdmnhanvienCn(nhanViencn);	
					//end Tho add
					phieuxuatbh.setDtdmnhanvienBacsi(tiepdon.getTiepdonBacsi());
					Date dateCrr = new Date();
					phieuxuatbh.setPhieuxuatbhNgaygiocn(dateCrr);
					phieuxuatbh.setPhieuxuatbhNgaygiophat(dateCrr);
					phieuxuatbh.setPhieuxuatbhNgaylap(new SimpleDateFormat("dd/MM/yyyy").parse(pxNgaylap));
					phieuxuatbh.setPhieuxuatbhNoidungthu(sNoiDungThu);
					
					phieuxuatbh.setDmkhoaMaso(dmKhoXuat);
					rs = PhieuXuatBhDelegate.getInstance().createByThuocPhongKham(dmKhoXuat.getDmkhoaMaso(), phieuxuatbh, listTPK, listCtXuatBh, IConstantsRes.PRIORITY_TON_LO_THUOC);
					
					if (!rs.equals("") || !rs.equals("SLTHET") || rs != null){
						phieuxuatbh.setPhieuxuatbhMa(rs);					
					 
						String tyleBNtra = ""+ (100 - hsttk.getHsthtoankTylebh() );
						
						if ("MP".equals( tiepdon.getDoituongMa(true).getDmdoituongMa())){
							tyleBNtra = "0";
						}
						phieuxuatbh.setPhieuxuatbhThanhtienb(hsttk.getHsthtoankBntra());
						phieuxuatbh.setPhieuxuatbhThatthu(hsttk.getHsthtoankThatthu());
					}
				}
				if (rs == null || rs.equals("")){
					nosuccess = "true";
					FacesMessages.instance().add(IConstantsRes.FAIL);
				}else{
					if(rs.indexOf("-")>0){
						String ketqua = rs.substring(rs.indexOf("-")+1,rs.length());
						nosuccess = "true";
						FacesMessages.instance().add(IConstantsRes.PHIEUXUATBH_SLKHONGDUXUAT,ketqua.substring(0,ketqua.indexOf(":")), ketqua.substring(ketqua.indexOf(":")+1,ketqua.length()));
					}else{
						yteLog = new YteLog();
						listDataLog="";
						for (CtXuatBh ctXbh : listCtXuatBh) {
							//luu log thong tin thuoc
							listDataLog += "Ma LK:"+ ctXbh.getCtxuatbhMalk()+
									" Don gia: "+ ctXbh.getCtxuatbhDongia()+ " Don gia ban: "+ ctXbh.getCtxuatbhDongiaban() + 
									" So luong: "+ ctXbh.getCtxuatbhSoluong()+
									" So lo: "+ ctXbh.getCtxuatbhLo()+
									" Nam SX: " + ctXbh.getCtxuatbhNamnhap()+
									" Nam HD: " + ctXbh.getCtxuatbhNamhandung()+ "\n";
						}
//						Luu log he thong
				         yteLog.setForm("B4311_Xuathangchobenhnhan");
				         yteLog.setUserId(identity == null ? "NULL" : identity.getUsername());
				         yteLog.setObjectId(tiepdon.getTiepdonMa());
				         yteLog.setLogString("Ma phieu: "+phieuxuatbh== null ? "NULL":phieuxuatbh.getPhieuxuatbhMa()  + 
				         					" Ma benh nhan: "+ benhnhan.getBenhnhanMa()+
				         					" Doi tuong: "+ tdDoituongMa+ 
				         					" The BHYT: " + tiepdon.getTiepdonSothebh()+
				         					" Ngay lap: "+ pxNgaylap + 
				         					" Chan đoan: "+ tdChandoan+ 
				         					" Bac si: "+ nvBacsi +
				         					" Thanh tien: "+ tongtien +
				         					" Noi dung thu: "+sNoiDungThu);
				         yteLog.setDateTime(new Date());
				         yteLog.setListData(listDataLog);

				         yteLogDele.create(yteLog);
						FacesMessages.instance().add(IConstantsRes.SUCCESS);
					}
				}
			} catch (NumberFormatException e) {
				nosuccess = "true";
				FacesMessages.instance().add(IConstantsRes.FAIL);
			} catch (ParseException e) {
				nosuccess = "true";
				FacesMessages.instance().add(IConstantsRes.FAIL);
			}
	}
	private boolean daThanhToan = true;
	public void loadTiepdonAjax(){
		log.info("---Begin loadTiepdonAjax() ----");
		listCtXuatBh = new ArrayList<CtXuatBh>();
		
		// String message = "";
		// this.resetTiepDon();
		phieuxuatbh = new PhieuXuatBh();
		ctxuatbh = new CtXuatBh();
		listCtXuatBh = new ArrayList<CtXuatBh>();
		benhnhan = new BenhNhan();
		SetInforUtil.setInforIfNullForBN(benhnhan);
		
		// bao.ttc: kiem tra tiepdon
		if(tiepdon == null || tiepdon.getTiepdonMa() == null){
			FacesMessages.instance().add(IConstantsRes.TIEP_DON_UNAVAILABLE, "");
			nofoundTD = "true";
			resetForm();
			return;
		}
		
		try{
			
			TiepDonDelegate tdWsPort = TiepDonDelegate.getInstance();
			String tdMa = tiepdon.getTiepdonMa();
			if(tdMa != null && !tdMa.trim().equals("")){
				tiepdon = tdWsPort.findByTiepdonMa_Kho(tdMa, dmKhoXuat.getDmkhoaMaso());
				if(tiepdon == null){					
					FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND, tdMa);
					this.resetTiepDon();
					nofoundTD = "true";
				} else if(tiepdon!=null ) {
					List<PhieuXuatBh> phieuXuatBhTemp = (List<PhieuXuatBh>) PhieuXuatBhDelegate.getInstance().findByTiepDonMa_Kho(tiepdon.getTiepdonMa(),dmKhoXuat.getDmkhoaMaso());
					if(phieuXuatBhTemp.size() > 0){
						phieuxuatbh.setPhieuxuatbhMa(phieuXuatBhTemp.get(0).getPhieuxuatbhMa());
						loadPhieuXuatBhAjax();
						return;
					}
					if (tiepdon.getDoituongMa().getDmdoituongMa().equals("TP")) {
						FacesMessages.instance().add(IConstantsRes.DOITUONG_THUPHI);
						resetForm();
						return;
					}
					
					HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
					hsttk = hsttkDelegate.findBytiepdonMa(tdMa);
					
					// 20120619 bao.ttc: thay doi nghiep vu, BN phai di thanh toan truoc khi linh thuoc cho du tien BN tra = 0
					// (Nghiep vu truoc: neu tien BN tra = 0 thi tu dong thanh toan va van cho linh thuoc)
					if (hsttk == null) {
						FacesMessages.instance().add(IConstantsRes.benh_nhan_phai_thu_vien_phi);
						resetTiepDon();
						return;							
					}
					
//					Double thanhtoan = 0.0;
//					if (hsttk == null){
//						hsttk = new HsThtoank();
//						hsttk.setTiepdonMa(tiepdon);
//						tinhToanChoHSTKKham(hsttk);						
//						thanhtoan = hsttk.getHsthtoankThtoan();
//						if(thanhtoan == 0.0) {
//							//hsttk.setHsthtoankDatt(true);
//							//hsttk.setHsthtoankNgaygiott(new Date());
//							//hsttkDelegate.capNhatTTHsttk(hsttk,clslist,listCtTPK_temp, false);
//						} else {
//							FacesMessages.instance().add(IConstantsRes.benh_nhan_phai_thu_vien_phi);
//							resetTiepDon();
//							return;							
//						}					
//					}					
					
					//-------- Set phan tram cho noi dung thu
					int tiLeBHYT = 0;
					if(tiepdon.getKhoibhytMa(true).getDtdmkhoibhytTylebhyt1()!=null)
						tiLeBHYT=tiepdon.getKhoibhytMa(true).getDtdmkhoibhytTylebhyt1();
					int iPhanTram = 100 - tiLeBHYT;
					
					if("BH".equals(tiepdon.getDoituongMa(true).getDmdoituongMa())){
						// khac tuyen hoac ngoai tinh thi 50-50
						
						Short tuyen = tiepdon.getTiepdonTuyen();
						//log.info("---tuyen():"+tuyen);
						// xet tuoi
						
						Integer tuoi = tiepdon.getBenhnhanMa(true).getBenhnhanTuoi();	
						if (tuoi == null){
							tuoi = 0;
						}
						Short donvituoi = tiepdon.getBenhnhanMa(true).getBenhnhanDonvituoi();
						if (donvituoi == null){
							donvituoi = 0;
						}
						String tuoikhongxettuyen = IConstantsRes.TUOI_KHONG_XET_TUYEN;
						int iTuoiKoXetTuyen = 200;
						if (tuoikhongxettuyen != null && !tuoikhongxettuyen.equals("")){
							iTuoiKoXetTuyen = Integer.parseInt(tuoikhongxettuyen);
						}
						if (tuoi >= iTuoiKoXetTuyen && donvituoi == 1){
							// la doi tuong >= 85 tuoi
						}else{
								if ( tuyen != null && (tuyen == 2 || tuyen ==3)){ // vuot tuyen
								
								// co 1 so doi tuong uu tien, chap nhan thanh toan nhu thong thuong
								boolean bLaDTuutien = false;
								
								String dsUuTien = IConstantsRes.DANH_SACH_KHOI_BHYT_KHAC_TUYEN_VUOT_TUYEN_KO_CAN_GIAY_CHUYEN_VIEN;
								if (dsUuTien != null && !dsUuTien.equals("")){
									StringTokenizer sTk = new StringTokenizer(dsUuTien,",");
									ArrayList<String> arrayDS = new ArrayList<String>();
									while (sTk.hasMoreTokens()){
										String khoi = sTk.nextToken();
										arrayDS.add(khoi);
									}
									DtDmKhoiBhyt khoiBHYT = tiepdon.getKhoibhytMa();
									if (arrayDS.contains(khoiBHYT.getDtdmkhoibhytMa())){
										bLaDTuutien = true;
									}
								}
								//log.info("---bLaDTuutien:"+bLaDTuutien);
								
									if (bLaDTuutien){
										// tinh binh thuong
									}else{
										//log.info("---tiepDon.getTiepdonCoGiayGioiThieu():"+tiepdon.getTiepdonCoGiayGioiThieu());
										
										// la doi tuong ko uu tuyen + ko co giay
										if (tiepdon.getTiepdonCoGiayGioiThieu() == null
										|| ( tiepdon.getTiepdonCoGiayGioiThieu() != null && tiepdon.getTiepdonCoGiayGioiThieu() == false))
										{
												
											iPhanTram = 50;
											
										}
									}
							}
						}
					}					
					
					sNoiDungThu="Thu "+iPhanTram+IConstantsRes.NOI_DUNG_THU_BHYT;
														
					listTPK = ThuocPhongKhamDelegate.getInstance().findByKhoaKham(tiepdon.getTiepdonMa(), Utils.KE_TOA_QUAY_BENH_VIEN, dmKhoXuat.getDmkhoaMaso().intValue());
					
					if(tenChuongTrinh.equals(MyMenuYTDTAction.thuVienPhi))
						listTPK = ThuocPhongKhamDelegate.getInstance().findByMaTiepDon(tiepdon.getTiepdonMa(), Utils.KE_TOA_QUAY_BENH_VIEN);
					
					//log.info("daThanhToan (load tiep don info):"+daThanhToan);
					
					if (listTPK != null && listTPK.size() > 0){
						for (ThuocPhongKham tpk: listTPK){
							String maphieud = tpk.getThuocphongkhamMaphieud();
							if (maphieud == null || maphieud.equals("")){
								daThanhToan = false;
								break;
							}
						}
					}					
					
					log.info("daThanhToan (load tiep don info):"+daThanhToan);
					CtXuatBh ctxuatbh_ = null;
					for (ThuocPhongKham obj : listTPK){
						obj.setThuocphongkhamMaphieud(phieuxuatbh.getPhieuxuatbhMa());
						ctxuatbh_ = new CtXuatBh();
						ctxuatbh_.setPhieuxuatbhMa(phieuxuatbh);
						ctxuatbh_.setCtxuatbhThutu(null);
						ctxuatbh_.setCtxuatbhSoluong(Double.valueOf(String.valueOf(obj.getThuocphongkhamSoluong())));
						log.info("***** So luong: "+obj.getThuocphongkhamSoluong());
						ctxuatbh_.setCtxuatbhMalk(obj.getThuocphongkhamMalk());
						ctxuatbh_.setDmthuocMaso(obj.getThuocphongkhamMathuoc());
						ctxuatbh_.setDmnctMaso(obj.getDmnctMaso());
						ctxuatbh_.setCtxuatbhLo(obj.getThuocphongkhamLo());
						ctxuatbh_.setDmnguonkinhphiMaso(obj.getDmnguonkinhphiMaso());
						ctxuatbh_.setDmquocgiaMaso(obj.getThuocphongkhamQuocgia());
						ctxuatbh_.setDmnhasanxuatMaso(obj.getThuocphongkhamHangsx());
						ctxuatbh_.setCtxuatbhNamnhap(obj.getThuocphongkhamNamnhap());
						ctxuatbh_.setCtxuatbhNgayhandung(obj.getThuocphongkhamNgayhd());
						ctxuatbh_.setCtxuatbhThanghandung(obj.getThuocphongkhamThanghd());
						ctxuatbh_.setCtxuatbhNamhandung(obj.getThuocphongkhamNamhd());
						ctxuatbh_.setCtxuatbhDongia(obj.getThuocphongkhamDongia());
						ctxuatbh_.setCtxuatbhDongiaban(obj.getThuocphongkhamDongiaban());
						ctxuatbh_.setCtxuatbhSodangky(null);
						ctxuatbh_.setCtxuatbhNgaygiocn(new Date());
						
						listCtXuatBh.add(ctxuatbh_);
					}
					
					pxNgaylap = Utils.getCurrentDate();
					phieuxuatbh.setTiepdonMa(tiepdon);
					log.info("set other fields- in form----");
					this.setFormTiepDon(tiepdon);
					
					
//					Double soTienThuocBHYT = new Double(0);
					
					for (CtXuatBh ctXuatBh : listCtXuatBh){
						tongtien += ctXuatBh.getCtxuatbhDongia() * ctXuatBh.getCtxuatbhSoluong();
					}
					
					log.info("soTienThuocBHYT:"+ tongtien);
					
					phieuxuatbh.setPhieuxuatbhThanhtien(tongtien);
					
					/// hien thi danh sach cls
					ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();
					
					java.util.ArrayList<ClsKham> listCLS_XuatHangChoBNBHYT_temp = (java.util.ArrayList<ClsKham>)clsKhamDelegate.findByTiepdonma(tiepdon.getTiepdonMa());
					//log.info("----------------listCLS_XuatHangChoBNBHYT_temp--------:"+listCLS_XuatHangChoBNBHYT_temp);
					if (listCLS_XuatHangChoBNBHYT_temp != null && listCLS_XuatHangChoBNBHYT_temp.size() > 0) {
						listCLS_XuatHangChoBNBHYT = listCLS_XuatHangChoBNBHYT_temp;
						for (ClsKham cls : listCLS_XuatHangChoBNBHYT) {
							com.iesvn.yte.dieutri.setinfor.SetInforUtil.setInforIfNullForClsKham(cls);		
							
						}			
					}
					//////////////////////////
				}
				else
				{
					this.resetTiepDon();
					FacesMessages.instance().add(IConstantsRes.TIEP_DON_UNAVAILABLE, tdMa);
					nofoundTD = "true";
					log.info("------Ma tiep don khong hop le---------");
				}
			}
			else{
				this.resetTiepDon();
			}
		}
		catch(Exception ex){
			log.info(":( ERROR: " + ex);
			ex.printStackTrace();
		}
		
		log.info("---End loadTiepdonAjax() method-----");
	}
	private double tongChiPhiCanThiet;
	private double bnTra;
	private double gioiHanTyLe;
	private double tylebhyt;
	private double tylebhyt2;
	private List<ClsKham> clslist = null;
	
	private void tinhToanChoHSTKKham(HsThtoank hsttk){
		HoSoThanhToanKhamUtil hsthtoankUtilTmp = new HoSoThanhToanKhamUtil(tiepdon);
		hsthtoankUtilTmp.tinhToanChoHSTKKham(hsttk);
		Utils.setInforForHsThToan(hsttk);
		
		 permiengiam = 0;
		 miengiam = hsthtoankUtilTmp.getMiengiam();
		 thatthu = hsthtoankUtilTmp.getThatthu();
		 perbhytchi = hsthtoankUtilTmp.getPerbhytchi();
		 bhytchi = hsthtoankUtilTmp.getBhytchi();
		 thanhtien1 = hsttk.getHsthtoankTongchi();
		 perbntra = hsthtoankUtilTmp.getPerbntra();
		 bntra = hsthtoankUtilTmp.getBntra();
		
		 cls = ( hsttk.getHsthtoankCls() == null?0:hsttk.getHsthtoankCls().doubleValue() ) 
				 + ( hsttk.getHsthtoankClsndm() == null?0:hsttk.getHsthtoankClsndm().doubleValue() ) ;

		 thuoc = ( hsttk.getHsthtoankThuoc() == null?0:hsttk.getHsthtoankThuoc().doubleValue() ) 
		 + ( hsttk.getHsthtoankThuocndm() == null?0:hsttk.getHsthtoankThuocndm().doubleValue() ) 
		  + ( hsttk.getHsthtoankVtth() == null?0:hsttk.getHsthtoankVtth().doubleValue() ) 
		  + ( hsttk.getHsthtoankVtthndm() == null?0:hsttk.getHsthtoankVtthndm().doubleValue() )  ;
		 
		
		phieuxuatbh.setPhieuxuatbhMiengiam(hsttk.getHsthtoankXetgiam());
		
		
				 
		//phieuxuatbh.setPhieuxuatbhThanhtienb( hsthtoankUtil.getBntra());
		
		// 20110714 bao.ttc: remove vi khi thanh toan phai cap nhat trang thai cho ca Thuoc ban kham va Ke toa quay BV
		// listCtTPK_temp = hsthtoankUtilTmp.getListCtTPK_temp(); // thuoc phong kham tai ban kham
		ThuocPhongKhamDelegate thuocDel = ThuocPhongKhamDelegate.getInstance();
		listCtTPK_temp = thuocDel.find2LoaiByMaTiepDon(tiepdon.getTiepdonMa(), Utils.XU_TRI_THUOC_TAI_BAN_KHAM, Utils.KE_TOA_QUAY_BENH_VIEN);
		
		clslist = hsthtoankUtilTmp.getListCtkq_temp();
		
	}
	
	public void loadPhieuXuatBhAjax(){
		log.info("---Begin loadPhieuXuatBhAjax() method----");
		try{
			//this.resetForm();
			ctxuatbh = new CtXuatBh();
			listCtXuatBh = new ArrayList<CtXuatBh>();
			benhnhan = new BenhNhan();
			SetInforUtil.setInforIfNullForBN(benhnhan);
			tiepdon = new TiepDon();
			String pxMa = phieuxuatbh.getPhieuxuatbhMa();
			if(pxMa != null && ! pxMa.trim().equals("")){
				log.info("Phieu xuat ma khac null");
				PhieuXuatBhDelegate pxWS = PhieuXuatBhDelegate.getInstance();
				
				phieuxuatbh = pxWS.find(pxMa);
				if(phieuxuatbh != null){
					log.info("Tim thay Phieu xuat bh");
					
					tiepdon = phieuxuatbh.getTiepdonMa();
					benhnhan = tiepdon.getBenhnhanMa();
					SetInforUtil.setInforIfNullForBN(benhnhan);
					
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					Date ngaylap = phieuxuatbh.getPhieuxuatbhNgaylap();
					pxNgaylap = df.format(ngaylap);
					
					this.setFormTiepDon(tiepdon);
					log.info("*****Noi dung thu: "+ phieuxuatbh.getPhieuxuatbhNoidungthu());
					sNoiDungThu=phieuxuatbh.getPhieuxuatbhNoidungthu();
					
					//log.info("*****Phieu xuat ma: "+pxMa);
					CtXuatBhDelegate ctXuatBHDel=CtXuatBhDelegate.getInstance();
					listCtXuatBh = ctXuatBHDel.findByPhieuxuatBhMa(phieuxuatbh.getPhieuxuatbhMa());
					
					// hien thi thongtin tong tien
					for (CtXuatBh ctXuatBh : listCtXuatBh){
						tongtien += ctXuatBh.getCtxuatbhDongia() * ctXuatBh.getCtxuatbhSoluong();
					}
					
					/// hien thi danh sach cls
					ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();
					
					java.util.ArrayList<ClsKham> listCLS_XuatHangChoBNBHYT_temp = (java.util.ArrayList<ClsKham>)clsKhamDelegate.findByTiepdonma(tiepdon.getTiepdonMa());
					log.info("----------------listCLS_XuatHangChoBNBHYT_temp--------:"+listCLS_XuatHangChoBNBHYT_temp);
					if (listCLS_XuatHangChoBNBHYT_temp != null && listCLS_XuatHangChoBNBHYT_temp.size() > 0) {
						listCLS_XuatHangChoBNBHYT = listCLS_XuatHangChoBNBHYT_temp;
						for (ClsKham cls : listCLS_XuatHangChoBNBHYT) {
							com.iesvn.yte.dieutri.setinfor.SetInforUtil.setInforIfNullForClsKham(cls);		
							
						}			
					}
					//////////////////////////
				}else{
					//message =  "Không tìm thấy số phiếu " + pxMa;
					log.info("Ko Tim thay Phieu xuat bh");
					FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_NULL, pxMa);
					this.resetForm();
				}
			}else{
				this.resetForm();
			}
		}
		catch (Exception e) {
			log.info(":( ERROR : " + e);
		}
		log.info("---End loadPhieuXuatBhAjax() method-----");
	}
	
	public void deleteCtXuatBhAjax(){
		log.info("---Begin deleteCtXuatBhAjax() method----");
		ctxuatbh = ctxuatbhSelected;
		log.info("ctxuatbh " + ctxuatbh.getCtxuatbhThutu());
		if(ctxuatbh != null){
			listCtXuatBh.remove(ctxuatbh.getCtxuatbhThutu().intValue() - 1);
//			thanhtienCt = this.computeThanhtienCt();
//			Integer congkham = tiepdon.getTiepdonBntra();
//			Double miengiam = phieuxuatbh.getPhieuxuatbhMiengiam();
//			Double thatthu = phieuxuatbh.getPhieuxuatbhThatthu();
//			Double thanhtien = this.computePxThanhtien(Double.parseDouble(""+thanhtienCt), congkham, miengiam, thatthu);
//			phieuxuatbh.setPhieuxuatbhThanhtien(thanhtien);
		}
		log.info("---End deleteCtXuatBhAjax() method-----");
	}
	
	public String troVe(){
		try {
			log.info("***** troVe()"); 
			this.resultReportName = "";
			this.resultReportFileName = "";
			return "B4311_Xuathangchobenhnhan";
		} 		
		catch (Exception e) {
			log.info("***** End exception = " + e);    	
		} 
		return "";
	}
	
	public void resetForm(){
		log.info("---Begin resetForm() method----");
		phieuxuatbh = new PhieuXuatBh();
		ctxuatbh = new CtXuatBh();
		tonKho = null;
		thanhtienCt = 0;
		updateCtXuat = ""+0;
		thieuthuoc = ""+0;
		tonkhoMa = "";
		tkMathuoc = "";
		ctxSoluong = "";
		tiepdon = new TiepDon();
		benhnhan = new BenhNhan();
		SetInforUtil.setInforIfNullForBN(benhnhan);
		listCtXuatBh = new ArrayList<CtXuatBh>();	
		
		listCLS_XuatHangChoBNBHYT = new ArrayList<ClsKham>();	
		
		pxNgaylap = Utils.getCurrentDate();
		nvBacsi = "";
		nvPhat = "";
		tdBankham = "";
		tdDoituong = "";
		tdDoituongMa = "";
		tdKcbBhyt = "";
		tdChandoan = "";
		nvCapnhat = "";
		bnNamsinh = "";
		bnGtinh = "";
		tdTinhBhyt = "";
		tdKhoiBhyt = "";
		pxPhantramBenhnhan = "1";
		pxClsBobot = "";
		tkMathuoc = "";
		tkQuocgiaSx = "";
		tkHangSx = "";
		ctxThanhtien = "";
//		pxSotien = new Double(0);
		tkDongiaban = "";
		pxMiengiam = "";
		pxThatthu = "";
		thieuthuoc = ""+0;
		updateCtXuat = ""+0;
		reportFileName = "";
		reportFinished = "";
		sNoiDungThu="";
		hsttk = new HsThtoank();
		
		 permiengiam = 0;
		 miengiam = new Double(0);
		 thatthu = new Double(0);
		  perbhytchi = 0;
		  bhytchi = new Double(0);
		  thanhtien1 = new Double(0);
		  tongtien = new Double(0);
		  perbntra = 0;
		  bntra = new Double(0);
		
		  cls = new Double(0);
		  thuoc = new Double(0);
		
		log.info("---End resetForm() method-----");
	}
	
	public void resetTiepDon(){
		log.info("---End resetTiepDon() method-----");
		String pxMa = phieuxuatbh.getPhieuxuatbhMa(); 
		if(pxMa != null && ! pxMa.equals("")){
			ctxuatbh = new CtXuatBh();
			updateCtXuat = ""+0;
			thanhtienCt = 0;
			thieuthuoc = ""+0;
			tonKho = null;
			tkMathuoc = "";
			tonkhoMa = "";
			ctxSoluong = "";
			tiepdon = new TiepDon();
			benhnhan = new BenhNhan();
			SetInforUtil.setInforIfNullForBN(benhnhan);
			listCtXuatBh = new ArrayList<CtXuatBh>();
			nvBacsi = "";
			tdBankham = "";
			tdDoituong = "";
			tdDoituongMa = "";
			tdKcbBhyt = "";
			tdChandoan = "";
			bnNamsinh = "";
			bnGtinh = "";
			tdTinhBhyt = "";
			tdKhoiBhyt = "";
			pxPhantramBenhnhan = "1";
			pxClsBobot = "";
			tkMathuoc = "";
			tkQuocgiaSx = "";
			tkHangSx = "";
			ctxThanhtien = "";
//			pxSotien = new Double(0);
			tkDongiaban = "";
			reportFileName = "";
			reportFinished = "";
			sNoiDungThu="";
		}else{
			this.resetForm();
		}
		log.info("---End resetTiepDon() method-----");
	}
	
//	private double computePxThanhtien(Double thanhtienCt,Integer congkham, Double miengiam, Double thatthu){
//		log.info("thanhtienCt: " + thanhtienCt);
//		log.info("congkham: " + congkham);
//		log.info("thatthu: " + thatthu);
//		log.info("miengiam: " + miengiam);
//		double thanhtien = 0;
//		if(congkham == null){
//			congkham = 0;
//		}
//		if(miengiam == null){
//			miengiam = 0.0;
//		}
//		if(thatthu == null){
//			thatthu = 0.0;
//		}
//		if(thanhtienCt != null && congkham != null 
//				&& thatthu != null && miengiam != null){
//			thanhtien = thanhtienCt + congkham - miengiam - thatthu;
//		}
//		log.info("thanhtien: " + thanhtien);
//		return thanhtien;
//	}
	
	private void capnhatCtXuatBhMoi(TonKho tkNew, double slNew, String message){
		log.info("---begin capnhatCtXuatBhMoi(TonKho tkNew, double slNew, String message) method----");
		double tkTonNew = tkNew.getTonkhoTon();
		if(tkTonNew < slNew){
			DmThuoc thuocNew = tkNew.getDmthuocMaso();
			message = "Số lượng tồn kho không đủ cho thuốc " +  thuocNew.getDmthuocTen();
			thieuthuoc = ""+1;
			thuocNew = null;
		}
		else{
			log.info("cap nhat ctxuat vao danh sach ctxuat");
			//ctxuatbh.setTonkhoMa(tkNew);
			log.info("ctxThutu: " + ctxuatbh.getCtxuatbhThutu());
			listCtXuatBh.remove(ctxuatbh.getCtxuatbhThutu().intValue() - 1);
			listCtXuatBh.add(ctxuatbh.getCtxuatbhThutu().intValue() - 1, ctxuatbh);
			this.resetCtXuatBh();
			
//			thanhtienCt = this.computeThanhtienCt();
//			Integer congkham = tiepdon.getTiepdonBntra();
//			Double miengiam = phieuxuatbh.getPhieuxuatbhMiengiam();
//			Double thatthu = phieuxuatbh.getPhieuxuatbhThatthu();
//			double thanhtien = this.computePxThanhtien(thanhtienCt, congkham, miengiam, thatthu);
//			phieuxuatbh.setPhieuxuatbhThanhtien(thanhtien);
//			
//			pxSotien = ""+ (Double.parseDouble(pxPhantramBenhnhan) * thanhtien);
		}
		log.info("---End capnhatCtXuatBhMoi(TonKho tkNew, double slNew, String message) method----");
	}
	
	private void resetCtXuatBh(){
		ctxuatbh = new CtXuatBh();
		tonKho = null;
		updateCtXuat = ""+0;
		thieuthuoc = ""+0;
		tonkhoMa = "";
		ctxSoluong = "";
		tkDongiaban = "";
		tkMathuoc = "";
		tkHangSx = "";
		tkQuocgiaSx = "";
		ctxThanhtien = "";
	}
	
//	private double computeThanhtienCt(){
//		log.info("---Begin computeThanhtienCt() method----");
//		double thanhtien = 0;
//		for(CtXuatBh ctx : listCtXuatBh){
//			double sl = ctx.getCtxuatbhSoluong();
//			//TonKho tk = ctx.getTonkhoMa();
//			double dongiaBan = ctx.getCtxuatbhDongia();
//			thanhtien += (sl * dongiaBan);
//		}
//		log.info("---End computeThanhtienCt() method----");
//		return thanhtien;
//	}
//	
//	private double computeClsKham(ClsKham[] arrayClsK){
//		log.info("begin computeClsKham(ClsKham[] arrayClsK) method");
//		double result = 0;
//		for(ClsKham clsk : arrayClsK){
//			result += (clsk.getClskhamDongiabh() * clsk.getClskhamLan());
//		}
//		log.info("End computeClsKham(ClsKham[] arrayClsK) method");
//		return result;
//	}
	
	private void setFormTiepDon(TiepDon tiepdon){
		log.info("begin setFormTiepDon(TiepDon tiepdon) method");
//		log.info("tiepdon.getTiepdonTylebh() : "+ tiepdon.getTiepdonTylebh());
//		if(tiepdon.getTiepdonTylebh() != null && tiepdon.getTiepdonTylebh().intValue() != 0){
//			pxPhantramBenhnhan = "" + (1 - tiepdon.getTiepdonTylebh()/100);
//			log.info("%benhnhan: " + pxPhantramBenhnhan);
//		}
//		else{ 
//			pxPhantramBenhnhan = ""+ 1;
//		}
//		if(phieuxuatbh.getPhieuxuatbhThanhtien() == null){
//			phieuxuatbh.setPhieuxuatbhThanhtien(Double.parseDouble("0"));
//		}
//			
//		pxSotien = ""+ (Double.parseDouble(pxPhantramBenhnhan) * phieuxuatbh.getPhieuxuatbhThanhtien());
//		log.info("pxSotien " + pxSotien);
//		log.info("pxThanhtien: " + phieuxuatbh.getPhieuxuatbhThanhtien());
		benhnhan = tiepdon.getBenhnhanMa();
		SetInforUtil.setInforIfNullForBN(benhnhan);
		log.info("benhnhan: " + benhnhan);
		if(benhnhan != null){
//			Calendar bnNgaysinh = Calendar.getInstance();
//			if (bnNgaysinh!=null){
//				bnNamsinh = new SimpleDateFormat("yyyy").format(bnNgaysinh.getTime());
//			}else{
//				bnNamsinh = benhnhan.getBenhnhanNamsinh();
//			}
			bnNamsinh = benhnhan.getBenhnhanNamsinh();
			//bnNgaysinh.setTime(benhnhan.getBenhnhanNgaysinh());
			//bnNamsinh = ""+ bnNgaysinh.get(1);
			
			
			 if (benhnhan.getDmgtMaso() != null){
					if ("1".equals(benhnhan.getDmgtMaso().getDmgtMa())){
						bnGtinh = "0";  //1 : Name
					}else{
						bnGtinh = "1";
					}					
				}
			 
			
		}
		
		DmTinh tinhBhyt = tiepdon.getTinhbhytMa();
		if(tinhBhyt != null){
			tdTinhBhyt = ""+tinhBhyt.getDmtinhMaso();
			tinhBhyt = null;
		}
		DtDmKhoiBhyt khoiBhyt = tiepdon.getKhoibhytMa();
		if(khoiBhyt != null){
			tdKhoiBhyt = khoiBhyt.getDtdmkhoibhytMa();
			khoiBhyt = null;
		}
		
		DmBenhVien kcbBhyt = tiepdon.getKcbbhytMa();
		if(kcbBhyt != null){
			tdKcbBhyt = kcbBhyt.getDmbenhvienMa();
			kcbBhyt = null;
		}
		DmDoiTuong dtuong = tiepdon.getDoituongMa();
		if(dtuong != null){
			tdDoituong = dtuong.getDmdoituongTen();
			tdDoituongMa = dtuong.getDmdoituongMa();
			dtuong = null;
		}
		DtDmBanKham bankham = tiepdon.getTiepdonBankham();
		if(bankham != null){
			tdBankham = bankham.getDtdmbankhamTen();
			bankham = null;
		}
//		DmBenhIcd chandoan = tiepdon.getTiepdonMachdoanbd();
//		if(chandoan != null){
//			tdChandoan = chandoan.getDmbenhicdTen();
//			chandoan = null;
//		}
//		DtDmNhanVien dmNvBsi = tiepdon.getTiepdonBacsi();
//		if(dmNvBsi != null){
//			nvBacsi = dmNvBsi.getDtdmnhanvienTen();
//			dmNvBsi = null;
//		}
		DtDmNhanVien dmNvPhat = phieuxuatbh.getDtdmnhanvienPhat();
		if(dmNvPhat != null){
			nvPhat = dmNvPhat.getDtdmnhanvienMa();
			dmNvPhat = null;
		}
		
		//bao.ttc: load "Chan doan" & "Bac si" tu thamkham
		if(tiepdon.getTiepdonBankham() != null && tiepdon.getTiepdonBankham().getDtdmbankhamMa() != null && tiepdon.getTiepdonMa() != null){
			ThamKhamDelegate thamkhamDelegate = ThamKhamDelegate.getInstance();
			ThamKham thamkham = thamkhamDelegate.findByBanKhamVaMaTiepDon(tiepdon.getTiepdonBankham().getDtdmbankhamMa(), tiepdon.getTiepdonMa());

			if (thamkham != null){
				
				if (thamkham.getThamkhamBacsi() != null)
					nvBacsi = thamkham.getThamkhamBacsi().getDtdmnhanvienTen();
				else
					nvBacsi = "";
				//log.info("##################### Bac si: " + nvBacsi);
				
				DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
				
				if (thamkham.getBenhicd10() != null && thamkham.getBenhicd10().getDmbenhicdMaso() != null){
					DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
					if (benh != null){
						tdChandoan = benh.getDmbenhicdTen();
					}
				}
				
			} // END IF thamkham != null
		}
		
		/*try{
			//pxClsBobot = "1";
			ClsKhamWSService clskService = new ClsKhamWSServiceLocator();
			ClsKhamWS clsWS = clskService.getClsKhamWSPort();
			ClsKham[] arrayClsK = clsWS.findByTiepdonMaBhyt(tiepdon.getTiepdonMa());
			log.info("arrayClsk  " + arrayClsK);
			if(arrayClsK != null){
				pxClsBobot = ""+ this.computeClsKham(arrayClsK);
			}
			else{
				pxClsBobot = "1";
			}
		}catch(Exception ex){
			log.info(":( ERROR! " + ex);
		}*/
		log.info("End setFormTiepDon(TiepDon tiepdon) method");
	}
	
	private boolean checkIsDuplicateTonkho(Integer tonkhoMa, int stt, String method){
		boolean rs = false;
		return rs;
	}
	
	public String inPhieu(){
		XuatReport();
		return "B4160_Chonmenuxuattaptin";
	}
	
	public void XuatReport() {
		reportTypeKC="InphieuXUATTHUOCBNBHYT";
		log.info("Vao Method XuatReport xuat hang cho benh nhan BHYT");
		String baocao1=null;
		try {
			log.info("bat dau method XuatReport ");
			String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/phieuxuatkho_03_BHYT.jrxml";
			String pathTemplate_sub1 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/phieuxuatkho_03_BHYT_sub1.jrxml";
			String pathTemplate_sub2 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/phieuxuatkho_03_BHYT_sub2.jrxml";
			String pathTemplate_sub3 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/phieuxuatkho_03_BHYT_sub3.jrxml";
			log.info("da thay file templete 5" + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			JasperReport jasperSub1 =JasperCompileManager.compileReport(pathTemplate_sub1);
			JasperReport jasperSub2 =JasperCompileManager.compileReport(pathTemplate_sub2);
			JasperReport jasperSub3 =JasperCompileManager.compileReport(pathTemplate_sub3);
			log.info("da thay file template ");
			
			Map<String, Object> params = new HashMap<String, Object>();
			
			if (tenChuongTrinh != null && (tenChuongTrinh.equals(MyMenuYTDTAction.quanLyKhoBHYT)|| tenChuongTrinh.equals(MyMenuYTDTAction.quanLyKhoTE) || tenChuongTrinh.equals(MyMenuYTDTAction.quanLyKhoChinh) || tenChuongTrinh.equals(MyMenuYTDTAction.quanLyKhoNoiTru))){
				// xuat ra 1 phieu xuat bhyt
				jasperReport = jasperSub1;
				//params.put("SubPhieuXuatBHYT", jasperSub1 );	
			}else{ // khu vuc vien phi
				// xuat ra 1 phieu thu gom 2 lien
				params.put("sub1", jasperSub2 );
				params.put("sub2", jasperSub3 );	
			}
			
			
			//params.put("SubPhieuThu", jasperSub1 );
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
			Date ngayXp = phieuxuatbh.getPhieuxuatbhNgaygiophat();
			
	        String ngayXuat = "";
	        if (ngayXp != null){
	        	ngayXuat = df.format(ngayXp);
			}
			
			DtDmNhanVien nvCn = phieuxuatbh.getDtdmnhanvienCn();
			if(nvCn != null){
				params.put("nguoiLapPhieu", "" +nvCn.getDtdmnhanvienTen());
			}else{
				params.put("nguoiLapPhieu", "" );
			}
			params.put("namSinh", "" + bnNamsinh);
			
			String sothebh = "";
			String tienkham = "";
			if(tiepdon.getTiepdonBntra() != null && ! tiepdon.getTiepdonBntra().equals("")){
				tienkham = ""+tiepdon.getTiepdonBntra();
			}
			if(tiepdon.getTiepdonSonambh() != null && ! tiepdon.getTiepdonSothebh().equals("")){
				sothebh = tiepdon.getTiepdonSothebh();
			}
			if(tdTinhBhyt == null) tdTinhBhyt = "";
			if(tdKhoiBhyt == null) tdKhoiBhyt = "";
			if(nvBacsi == null) nvBacsi ="";
			if(tdChandoan == null) tdChandoan = "";
			
			params.put("maPhieuXuat", "" + phieuxuatbh.getPhieuxuatbhMa());
			
			if (ngayXuat != null && !ngayXuat.equals("")){
				params.put("ngayXuat", "" + ngayXuat.substring(0, 10));
				params.put("gioXuat", ngayXuat.subSequence(11, 19));
			}
			sothebh = tiepdon.getTiepdonSothebh();
			params.put("tenBenhNhan", "" + benhnhan.getBenhnhanHoten() );
			params.put("chanDoan", " " + tdChandoan);
			params.put("soTheBH", sothebh);
			params.put("kyHieuBS", " " + nvBacsi);
			params.put("tongCong", "" + listCtXuatBh.size());
			params.put("tienKham", "" + tienkham);
			
			Double thtoan = hsttk.getHsthtoankThtoan();
			if (thtoan == null){
				thtoan = new Double(0);
			}
			Double thatthu = hsttk.getHsthtoankThatthu();
			if (thatthu == null){
				thatthu = new Double(0);
			}
			
			Double tienThuoc = thtoan - thatthu;
			params.put("tienThuoc", tienThuoc);
			
			
			params.put("TIENBANGCHU", Utils.formatNumberWithSeprator(tienThuoc) +" "+IConstantsRes.dongVN);
			if(tenChuongTrinh.equals(MyMenuYTDTAction.quanLyKhoBHYT)){
				params.put("xuatTaiKho", "Kho BHYT");
			}else if(tenChuongTrinh.equals(MyMenuYTDTAction.quanLyKhoTE)){
				params.put("xuatTaiKho", "Kho Tr\u1EBB Em");
			}else if(tenChuongTrinh.equals(MyMenuYTDTAction.quanLyKhoChinh)){
				params.put("xuatTaiKho", "Kho Ch\u00EDnh");
			}else if(tenChuongTrinh.equals(MyMenuYTDTAction.quanLyKhoNoiTru)){
				params.put("xuatTaiKho", "Kho N\u1ED9i Tr\u00FA");
			}
			if(bnGtinh.equals("1")){
				params.put("gioiTinh", IConstantsRes.GIOI_TINH_NU);
			}
			else{
				params.put("gioiTinh", IConstantsRes.GIOI_TINH_NAM);
			}
			Calendar cal = Calendar.getInstance();
            log.info(String.format("-----ngay lap: %s", cal.getTime()));
            params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
            params.put("ngay", "" + cal.get(Calendar.DAY_OF_MONTH));
            params.put("thang", "" + (cal.get(Calendar.MONTH) + 1));
            params.put("nam", "" + cal.get(Calendar.YEAR));
            params.put("diaPhuong", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
            
            //*********** BIEN LAI THU TIEN
			params.put("UBNDTINH", IConstantsRes.REPORT_DIEUTRI_UBNDTINH);
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			
			params.put("HOTEN", benhnhan.getBenhnhanHoten());
			
			String diachistr="";
			if(benhnhan.getBenhnhanDiachi() != null)
				diachistr += benhnhan.getBenhnhanDiachi()+",";
			if(benhnhan.getXaMa(true).getDmxaTen() !=null)
				diachistr += benhnhan.getXaMa(true).getDmxaTen()+",";
			if(benhnhan.getHuyenMa(true).getDmhuyenTen() != null)
				diachistr += benhnhan.getHuyenMa(true).getDmhuyenTen()+",";
			if(benhnhan.getTinhMa(true).getDmtinhTen() != null)
				diachistr += benhnhan.getTinhMa(true).getDmtinhTen();
			params.put("diaChiBenhNhan", diachistr );
			
			if(phieuxuatbh.getPhieuxuatbhNoidungthu()!=null)
			params.put("NOIDUNG", phieuxuatbh.getPhieuxuatbhNoidungthu() );
			log.info("***** PhieuxuatbhMa: "+phieuxuatbh.getPhieuxuatbhMa());
			
			params.put("SOTIEN", Utils.formatNumberWithSeprator(tienThuoc));
			params.put("TIENBANGCHU", Utils.NumberToString(tienThuoc));
            
            
            
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintKC,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"duocpham/", "pdf","InphieuXHBNBHYT");
			    reportPathKC=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathKC);
			    index+=1;
			    log.info("Index :" + getIndex());
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Thoat Method XuatReport");
	}
	
	public PhieuXuatBh getPhieuxuatbh() {
		return phieuxuatbh;
	}

	public void setPhieuxuatbh(PhieuXuatBh phieuxuatbh) {
		this.phieuxuatbh = phieuxuatbh;
	}

	public BenhNhan getBenhnhan() {
		return benhnhan;
	}

	public void setBenhnhan(BenhNhan benhnhan) {
		this.benhnhan = benhnhan;
	}

	public TiepDon getTiepdon() {
		return tiepdon;
	}

	public void setTiepdon(TiepDon tiepdon) {
		this.tiepdon = tiepdon;
	}

	public List<CtXuatBh> getListCtXuatBh() {
		return listCtXuatBh;
	}

	public void setListCtXuatBh(List<CtXuatBh> listCtXuatBh) {
		this.listCtXuatBh = listCtXuatBh;
	}
	

	public CtXuatBh getCtxuatbhSelected() {
		return ctxuatbhSelected;
	}

	public void setCtxuatbhSelected(CtXuatBh ctxuatbhSelected) {
		this.ctxuatbhSelected = ctxuatbhSelected;
	}

	public TonKho[] getListTonkho() {
		return listTonkho;
	}

	public void setListTonkho(TonKho[] listTonkho) {
		this.listTonkho = listTonkho;
	}

	public String getTdBankham() {
		return tdBankham;
	}

	public void setTdBankham(String tdBankham) {
		this.tdBankham = tdBankham;
	}

	public String getTdDoituong() {
		return tdDoituong;
	}

	public void setTdDoituong(String tdDoituong) {
		this.tdDoituong = tdDoituong;
	}

	public String getTdKcbBhyt() {
		return tdKcbBhyt;
	}

	public void setTdKcbBhyt(String tdKcbBhyt) {
		this.tdKcbBhyt = tdKcbBhyt;
	}

	public String getTdChandoan() {
		return tdChandoan;
	}

	public void setTdChandoan(String tdChandoan) {
		this.tdChandoan = tdChandoan;
	}

	public String getPxNgaylap() {
		return pxNgaylap;
	}

	public void setPxNgaylap(String pxNgaylap) {
		this.pxNgaylap = pxNgaylap;
	}

	public String getTdTinhBhyt() {
		return tdTinhBhyt;
	}

	public void setTdTinhBhyt(String tdTinhBhyt) {
		this.tdTinhBhyt = tdTinhBhyt;
	}

	public String getTdKhoiBhyt() {
		return tdKhoiBhyt;
	}

	public void setTdKhoiBhyt(String tdKhoiBhyt) {
		this.tdKhoiBhyt = tdKhoiBhyt;
	}

	public String getPxPhantramBenhnhan() {
		return pxPhantramBenhnhan;
	}

	public void setPxPhantramBenhnhan(String pxPhantramBenhnhan) {
		this.pxPhantramBenhnhan = pxPhantramBenhnhan;
	}

	public String getTkMathuoc() {
		return tkMathuoc;
	}

	public void setTkMathuoc(String tkMathuoc) {
		this.tkMathuoc = tkMathuoc;
	}

	public String getTkQuocgiaSx() {
		return tkQuocgiaSx;
	}

	public void setTkQuocgiaSx(String tkQuocgiaSx) {
		this.tkQuocgiaSx = tkQuocgiaSx;
	}

	public String getTkHangSx() {
		return tkHangSx;
	}

	public void setTkHangSx(String tkHangSx) {
		this.tkHangSx = tkHangSx;
	}

//	public Double getPxSotien() {
//		return pxSotien;
//	}
//
//	public void setPxSotien(Double pxSotien) {
//		this.pxSotien = pxSotien;
//	}

	public CtXuatBh getCtxuatbh() {
		return ctxuatbh;
	}

	public void setCtxuatbh(CtXuatBh ctxuatbh) {
		this.ctxuatbh = ctxuatbh;
	}

	public String getBnNamsinh() {
		return bnNamsinh;
	}

	public void setBnNamsinh(String bnNamsinh) {
		this.bnNamsinh = bnNamsinh;
	}

	public String getBnGtinh() {
		return bnGtinh;
	}

	public void setBnGtinh(String bnGtinh) {
		this.bnGtinh = bnGtinh;
	}	
	
	public String getNvBacsi() {
		return nvBacsi;
	}

	public void setNvBacsi(String nvBacsi) {
		this.nvBacsi = nvBacsi;
	}

	public String getNvPhat() {
		return nvPhat;
	}

	public void setNvPhat(String nvPhat) {
		this.nvPhat = nvPhat;
	}

	public String getNvCapnhat() {
		return nvCapnhat;
	}

	public void setNvCapnhat(String nvCapnhat) {
		this.nvCapnhat = nvCapnhat;
	}

	public String getPxClsBobot() {
		return pxClsBobot;
	}

	public void setPxClsBobot(String clsBobot) {
		this.pxClsBobot = clsBobot;
	}

	public String getCtxThanhtien() {
		return ctxThanhtien;
	}

	public void setCtxThanhtien(String ctxThanhtien) {
		this.ctxThanhtien = ctxThanhtien;
	}

	public String getTkDongiaban() {
		return tkDongiaban;
	}

	public void setTkDongiaban(String tkDongiaban) {
		this.tkDongiaban = tkDongiaban;
	}
	
	public String getThieuthuoc() {
		return thieuthuoc;
	}

	public void setThieuthuoc(String thieuthuoc) {
		this.thieuthuoc = thieuthuoc;
	}

	public String getUpdateCtXuat() {
		return updateCtXuat;
	}

	public void setUpdateCtXuat(String updateCtXuat) {
		this.updateCtXuat = updateCtXuat;
	}

	public String getPxMiengiam() {
		return pxMiengiam;
	}

	public void setPxMiengiam(String pxMiengiam) {
		this.pxMiengiam = pxMiengiam;
	}

	public String getPxThatthu() {
		return pxThatthu;
	}

	public void setPxThatthu(String pxThatthu) {
		this.pxThatthu = pxThatthu;
	}

	public String getTonkhoMa() {
		return tonkhoMa;
	}

	public void setTonkhoMa(String tonkhoMa) {
		this.tonkhoMa = tonkhoMa;
	}

	public String getCtxSoluong() {
		return ctxSoluong;
	}

	public void setCtxSoluong(String ctxSoluong) {
		this.ctxSoluong = ctxSoluong;
	}

	public double getThanhtienCt() {
		return thanhtienCt;
	}

	public void setThanhtienCt(double thanhtienCt) {
		this.thanhtienCt = thanhtienCt;
	}

	public String getReportFinished() {
		return reportFinished;
	}

	public void setReportFinished(String reportFinished) {
		this.reportFinished = reportFinished;
	}

	public String getSaveResult() {
		return saveResult;
	}

	public void setSaveResult(String saveResult) {
		this.saveResult = saveResult;
	}

	public String getReportFileName() {
		return reportFileName;
	}

	public void setReportFileName(String reportFileName) {
		this.reportFileName = reportFileName;
	}

	public String getResultReportFileName() {
		return resultReportFileName;
	}

	public void setResultReportFileName(String resultReportFileName) {
		this.resultReportFileName = resultReportFileName;
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

	public String getResultReportName() {
		return resultReportName;
	}

	public void setResultReportName(String resultReportName) {
		this.resultReportName = resultReportName;
	}

//	public DtDmBanKham getBankham() {
//		return bankham;
//	}
//
//	public void setBankham(DtDmBanKham bankham) {
//		this.bankham = bankham;
//	}

	public Integer getNvPhatSo() {
		return nvPhatSo;
	}

	public void setNvPhatSo(Integer nvPhatSo) {
		this.nvPhatSo = nvPhatSo;
	}

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		XuatHangChoBenhNhanBHYTAction.log = log;
	}

	public TonKho getTonKho() {
		return tonKho;
	}

	public void setTonKho(TonKho tonKho) {
		this.tonKho = tonKho;
	}

	public String getMaBanKhamOut() {
		return maBanKhamOut;
	}

	public void setMaBanKhamOut(String maBanKhamOut) {
		this.maBanKhamOut = maBanKhamOut;
	}

	public String getMaTiepDonOut() {
		return maTiepDonOut;
	}

	public void setMaTiepDonOut(String maTiepDonOut) {
		this.maTiepDonOut = maTiepDonOut;
	}

//	public String getInitB121_3_Xutrithuoctaibankham() {
//		return initB121_3_Xutrithuoctaibankham;
//	}
//
//	public void setInitB121_3_Xutrithuoctaibankham(
//			String initB121_3_Xutrithuoctaibankham) {
//		this.initB121_3_Xutrithuoctaibankham = initB121_3_Xutrithuoctaibankham;
//	}
//
//	public String getLoaiToaThuocThamKhamVaXuTri() {
//		return loaiToaThuocThamKhamVaXuTri;
//	}
//
//	public void setLoaiToaThuocThamKhamVaXuTri(String loaiToaThuocThamKhamVaXuTri) {
//		this.loaiToaThuocThamKhamVaXuTri = loaiToaThuocThamKhamVaXuTri;
//	}

	public List<ThuocPhongKham> getListTPK() {
		return listTPK;
	}

	public void setListTPK(List<ThuocPhongKham> listTPK) {
		this.listTPK = listTPK;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

//	public String getReturnToXuatHangChoBNBHYT() {
//		return returnToXuatHangChoBNBHYT;
//	}
//
//	public void setReturnToXuatHangChoBNBHYT(String returnToXuatHangChoBNBHYT) {
//		this.returnToXuatHangChoBNBHYT = returnToXuatHangChoBNBHYT;
//	}

	public String getNosuccess() {
		return nosuccess;
	}

	public void setNosuccess(String nosuccess) {
		this.nosuccess = nosuccess;
	}

	public String getTdDoituongMa() {
		return tdDoituongMa;
	}

	public void setTdDoituongMa(String tdDoituongMa) {
		this.tdDoituongMa = tdDoituongMa;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public double getTongChiPhiCanThiet() {
		return tongChiPhiCanThiet;
	}

	public void setTongChiPhiCanThiet(double tongChiPhiCanThiet) {
		this.tongChiPhiCanThiet = tongChiPhiCanThiet;
	}

	public double getBnTra() {
		return bnTra;
	}

	public void setBnTra(double bnTra) {
		this.bnTra = bnTra;
	}

	public HsThtoank getHsttk() {
		return hsttk;
	}

	public void setHsttk(HsThtoank hsttk) {
		this.hsttk = hsttk;
	}

	public String getNoiDungThu() {
		return sNoiDungThu;
	}

	public void setNoiDungThu(String noiDungThu) {
		sNoiDungThu = noiDungThu;
	}
	
	
}

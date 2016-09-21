
package com.iesvn.yte.dieutri.vienphi.action;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;
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
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.ChuyenVaoNoiTruDelegate;
import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmMqlBhytDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.delegate.YteLogDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.ChuyenVaoNoiTru;
import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.DtDmBanKham;
import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
import com.iesvn.yte.dieutri.entity.DtDmMqlBhyt;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.HsThtoan;
import com.iesvn.yte.dieutri.entity.HsThtoank;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.dieutri.entity.YteLog;
import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
import com.iesvn.yte.entity.DmBenhIcd;
import com.iesvn.yte.entity.DmBenhVien;
import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.entity.DmGioiTinh;
import com.iesvn.yte.entity.DmTinh;
import com.iesvn.yte.util.HoSoThanhToanKhamUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.ThamKhamUtil;
import com.iesvn.yte.util.Utils;

@Scope(ScopeType.CONVERSATION)
@Name("B3232_ThanhToanBenhNhanBHYT")
@Synchronized(timeout = 6000000)
public class ThanhToanBenhNhanBHYTAction implements Serializable {
	
	private static final long serialVersionUID = 10L;
	private static Logger log = Logger.getLogger(ThanhToanBenhNhanBHYTAction.class);
	
	private BenhNhan benhnhan;
	private TiepDon tiepdon;
	private ThamKham thamkham; //bao.ttc: load thong tin Bac si va Chan doan
	
	@DataModel // bao.ttc: list thuoc phong kham
	private java.util.ArrayList<ThuocPhongKham> listThuocPK;
	
	/*@In(required = false)
	@Out(required = false)
	private String goToCLSPhongKham;*/
	
	private List<ThuocPhongKham> listTPK;
	
	@DataModel
	private java.util.ArrayList<ClsKham> listCLS_XuatHangChoBNBHYT;
	
	@Out(required=false)
	@In(required=false)
	private String reportPathKC=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeKC=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintKC = null;
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
	private YteLogDelegate yteLogDele = YteLogDelegate.getInstance();
    private YteLog yteLog ;
    private String listDataLog = "";
	private DtDmNhanVien nhanVienThungan = new DtDmNhanVien();
	
	public void refreshnhanvienthungan(){
		DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
		nhanVienThungan = nvDelegate.findByNd(identity.getUsername());
		if (nhanVienThungan == null) {
			nhanVienThungan = new DtDmNhanVien();
		}
	}

	private int index=0;
	
	private TonKho[] listTonkho;
	private String nvBacsi;
	private String nvPhat;
	private Integer nvPhatSo;
	private String nvPhatTen;
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
	private String tkDongiaban;
	
	private String pxMiengiam;
	private String pxThatthu;
	
	private TonKho tonKho;
	private String tonkhoMa;
	private String ctxSoluong;
	
	private String thieuthuoc = "0";
	private String updateCtXuat;
	private double thanhtienCt = 0.0;
	private String saveResult;
	private String sNoiDungThu;
	private String gioThanhToan;
	
	// Phan danh cho tinh toan
	private int permiengiam = 0;
	private Double miengiam = new Double(0);
	private Double thatthu = new Double(0);
	private int perbhytchi = 0;
	private Double bhytchi = new Double(0);
	private Double thanhtien1 = new Double(0);
	private int perbntra = 0;
	private Double bntra = new Double(0);
	
	private Double cls;
	private Double thuoc;
	
	private String kyhieu;
	private String quyen;
	private String bienlai;
	private String maPhieu = "";
	private Double tienThuocDaTT = 0.0;
	private String strBankham;
	
	private List<ThuocPhongKham> listCtTPK_temp = null; // day la thuoc tai ban kham, khi thanh toan kham -> tinh luon
	
	
	public String clsphongkham() throws ServiceException, RemoteException {
		
		if (benhnhan.getBenhnhanHoten() == null || benhnhan.getBenhnhanHoten().equals("")){
			FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND);
			return "";
		}
		
		
		maTiepDonOut = tiepdon.getTiepdonMa();
		
		//goToCLSPhongKham = null;
	
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
	

	private void reset(){
		
		tiepdon = new TiepDon();
		benhnhan = new BenhNhan();
		SetInforUtil.setInforIfNullForBN(benhnhan);
		listThuocPK = new ArrayList<ThuocPhongKham>();		// bao.ttc: 
		//pxNgaylap = Utils.getCurrentDate();
		//phuc.lc 28-12-2010 begin
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
		pxNgaylap = formatter.format(cal.getTime());		
		gioThanhToan = Utils.getGioPhut(cal.getTime()) ;
		//phuc.lc 28-12-2010 end
		updateCtXuat = "0";
		pxPhantramBenhnhan = "1";
		sNoiDungThu = "";
		nosuccess = "false";
		nofoundTD = "false";
		listCLS_XuatHangChoBNBHYT= new ArrayList<ClsKham>();
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

	public String getGoToThuocBNBHYT() {
		return goToThuocBNBHYT;
	}

	public void setGoToThuocBNBHYT(String goToThuocBNBHYT) {
		this.goToThuocBNBHYT = goToThuocBNBHYT;
	}

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
		//log.info("---------begin init method-------");
		
		reset();
		refreshnhanvienthungan();
		//log.info("---------end init method-------");
		hsttk = new HsThtoank();
		
		return "ThuVienPhi_SoLieuKhamBenh_ThanhToanBenhNhanBHYT";
	}
	@End
	public void endFucntion(){
		
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
	
	@In(required = false)
	@Out(required = false)
	private String returnToThanhToanBNBHYT;
	
	@In(required = false)
	@Out(required = false)
	private String goToThuocBNBHYT;
	
	@Factory("goToThuocBNBHYT")
	public void goToThuocBNBHYTForm() throws Exception {
		//log.info("begin goToThuocBNBHYTForm");
		returnToThanhToanBNBHYT = "done";
		goToThuocBNBHYT = null;
		reset();
		tiepdon.setTiepdonMa(maTiepDonOut);
		
		if (tiepdon != null &&  tiepdon.getTiepdonMa() != null){
			//log.info("loadTiepdonAjax () ");
			loadTiepdonAjax();
		}
		//log.info("end goToThuocBNBHYTForm");
	}
	
	@Factory("returnToThanhToanBNBHYT")
	public void initForReturn() throws Exception {
		//log.info("begin initForReturn");
		goToThuocBNBHYT = "done";

		if (tiepdon != null &&  tiepdon.getTiepdonMa() != null){
			//log.info("loadTiepdonAjax () ");
			loadTiepdonAjax();
		}
		//log.info("end initForReturn");
	}
	
	
	// bao.ttc:
//	public String nhapPhieuBHYT(){
//		
//		log.info("*****begin nhapPhieuBHYT()");
//		
//		maTiepDonOut = tiepdon.getTiepdonMa();
//		
//				
//		//initB121_3_Xutrithuoctaibankham = null;
//		//loaiToaThuocThamKhamVaXuTri = Utils.KE_TOA_QUAY_BENH_VIEN;
//		returnToThanhToanBNBHYT="1";
//		
//		log.info("*****End nhapPhieuBHYT()");
//		return "xutrithuoctaibankham";
//	}
	
	
	public void saved(){
		//log.info("---Begin saved() method----");
		try{
			this.reportFileName = "";
			this.reportFinished = "";
			
			String message = "";
			
			DtDmNhanVien dmNvPhat = new DtDmNhanVien();
			dmNvPhat.setDtdmnhanvienMa(nvPhat);
			
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			cal.setTime(df.parse(df.format(new Date())));
			
			ThuocPhongKham[] arrayCtxBh = new ThuocPhongKham[listThuocPK.size()];
			for(int i = 0; i < listThuocPK.size(); i++){
				arrayCtxBh[i] = listThuocPK.get(i);
			}
			//log.info("---End saved() method-----");
			
			FacesMessages.instance().add(message);
		}catch (Exception e) {
			saveResult = ""+0;
			FacesMessages.instance().add(IConstantsRes.FAIL);
			log.info(":( ERROR: " + e);
		}
	}
	
	public void ghiNhan(){
		
		refreshnhanvienthungan();
			try {
				
				if (daThanhToan==true){ // chua thanh toan tien thuoc
				
					
					FacesMessages.instance().add(IConstantsRes.SUCCESS);
					HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
					
					hsttk.setHsthtoankThatthu(thatthu); //bao.ttc: set thatthu
					hsttk.setHsthtoankKyhieu(kyhieu);
					hsttk.setHsthtoankQuyen(quyen);
					hsttk.setHsthtoankBienlai(bienlai);
					hsttk.setHsthtoankDatt(true);
					
					//hsttk.setHsthtoankNgaygiott(new Date());
					// phuc.lc 28-12-2010 begin
					Calendar dTemp = Utils.getDBDate(gioThanhToan, pxNgaylap);
					Date dNgayGioTT = Calendar.getInstance().getTime();					
					if (dTemp != null){
						dNgayGioTT = dTemp.getTime();						
					}
					hsttk.setHsthtoankNgaygiott(dNgayGioTT);
					// phuc.lc 28-12-2010 end
					tinhToanChoHSTKKham(hsttk);
					//log.info("After tinhToanChoHSTKKham, hsttk = " + hsttk);
					hsttk.setHsthtoankThtoan(new Double(0.0));
					//log.info("nhanvien = " + nhanVienThungan);
					if (nhanVienThungan != null && nhanVienThungan.getDtdmnhanvienMaso() != null) {
						hsttk.setHsthtoankThungan(nhanVienThungan);
					}
					hsttkDelegate.capNhatTTHsttk(hsttk,clslist,listCtTPK_temp, false);
					
					HsThtoank hsttkTemp = hsttkDelegate.findAllBytiepdonMa(tiepdon.getTiepdonMa());
					//log.info("After findAllBytiepdonMa, hsttk = " + hsttk);
					this.maPhieu = hsttkTemp.getHsthtoankMa();
					
					return;
				}
				
                                    yteLog.setForm("B3232_ThanhToanBenhNhanBHYT");
                                    yteLog.setUserId(identity == null ? "NULL" : identity.getUsername());
                                    yteLog.setObjectId(tiepdon.getTiepdonMa());
                                    yteLog.setLogString("Noi dung: "+ thanhtien1+"\n"+
                                                                            "BN tra: "+ bntra+"\n"+
                                                                            "BHYT chi: "+ bhytchi+"\n"+
                                                                            "ClS: "+ cls+"\n"+
                                                                            "Thuoc: "+thuoc+"\n"+
                                                                            "That thu:"+ thatthu+"\n"+
                                                                            "Mien giam: "+ miengiam
                                                                            ); 
                                    yteLog.setDateTime(new Date());
                                    yteLog.setListData(listDataLog);
                                    yteLogDele.create(yteLog);
		        
				//if (rs == null || rs.equals("")){
					//nosuccess = "true";
					//FacesMessages.instance().add(IConstantsRes.FAIL);
				//}else{
					FacesMessages.instance().add(IConstantsRes.SUCCESS);
				//}
			} catch (NumberFormatException e) {
				nosuccess = "true";
				FacesMessages.instance().add(IConstantsRes.FAIL);
			} /*catch (ParseException e) {
				nosuccess = "true";
				FacesMessages.instance().add(IConstantsRes.FAIL);
			}*/
	}
	private boolean daThanhToan = true;
	private String checkThanhToan = "block";
	public void loadTiepdonAjax(){
		yteLog = new YteLog();
		
		log.info("---Begin loadTiepdonAjax() method 123----");
		checkThanhToan = "block";
		listThuocPK = new ArrayList<ThuocPhongKham>(); // bao.ttc: 
		
		benhnhan = new BenhNhan();
		SetInforUtil.setInforIfNullForBN(benhnhan);
		try{
			
			TiepDonDelegate tdWsPort = TiepDonDelegate.getInstance();
			String tdMa = tiepdon.getTiepdonMa();
			if(tdMa != null && ! tdMa.trim().equals("")){
				tiepdon = tdWsPort.find(tdMa);
				
				if(tiepdon == null){
					this.resetTiepDon();
					FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + tdMa);
					nofoundTD = "true";
					resetForm();
					//log.info("------Khong tim thay ma tiep don---------");
				} else if(tiepdon.getTiepdonBankham() == null) {
					Hsba hsbaTmp = HsbaDelegate.getInstance().findByTiepDonMa(tdMa);
					if (hsbaTmp != null) {
						FacesMessages.instance().add(IConstantsRes.TIEP_DON_DUOC_TAO_TU_NOI_TRU, tdMa, hsbaTmp.getHsbaSovaovien());
					} else {
						FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + tdMa);
					}
					this.resetTiepDon();					
					nofoundTD = "true";
					resetForm();
					
					//log.info("------Tiep don ban kham NULL---------");
				}
				else if(tiepdon!=null ){
					//log.info("tiepdonBenhnhan: " + tiepdon.getBenhnhanMa().getBenhnhanMa());
					ChuyenVaoNoiTru cvnt = ChuyenVaoNoiTruDelegate.getInstance().findByMaTiepDon(tiepdon.getTiepdonMa());
					if (cvnt != null) {
						FacesMessages.instance().add(IConstantsRes.BN_DA_CHUYEN_VAO_NOI_TRU);						
						resetForm();
						nofoundTD = "true";
						return;
					}else if (tiepdon.getDoituongMa().getDmdoituongMa().equals("TP")) {
						
						FacesMessages.instance().add(IConstantsRes.DOITUONG_THUPHI);
						//FacesMessages.instance().add(IConstantsRes.KHONG_PHAI_DOI_TUONG_BHYT +", " + IConstantsRes.KHONG_THANH_TOAN_O_DAY);
						resetForm();
						nofoundTD = "true";
						return;
					} else if ("CCL".equals( tiepdon.getTiepdonBankham(true).getDtdmbankhamMa())
							|| "CCN".equals( tiepdon.getTiepdonBankham(true).getDtdmbankhamMa())) {
						FacesMessages.instance().add(IConstantsRes.DOI_TUONG_CAP_CUU + ", " + IConstantsRes.KHONG_THANH_TOAN_O_DAY);
						resetForm();
						nofoundTD = "true";
						return;
					}
					
					//bao.ttc: load "Chan doan" & "Bac si" tu thamkham
					if(tiepdon.getTiepdonBankham() != null && tiepdon.getTiepdonBankham().getDtdmbankhamMa() != null && tiepdon.getTiepdonMa() != null){
						ThamKhamDelegate thamkhamDelegate = ThamKhamDelegate.getInstance();
						//thamkham = thamkhamDelegate.findByBanKhamVaMaTiepDon(tiepdon.getTiepdonBankham().getDtdmbankhamMa(), tiepdon.getTiepdonMa());
						thamkham = thamkhamDelegate.findByMaTiepDon(tiepdon.getTiepdonMa());
						
						if (thamkham != null){
							
							if (thamkham.getThamkhamBacsi() != null)
								nvBacsi = thamkham.getThamkhamBacsi().getDtdmnhanvienTen();
							else
								nvBacsi = "";
							//log.info("##################### Bac si: " + nvBacsi);
							
							//DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
							//String maChanDoan = "";
							//String tenChanDoan = "";
							
							if (thamkham.getBenhicd10() != null && thamkham.getBenhicd10().getDmbenhicdMaso() != null){
								//DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
								//if (benh != null){
								//	tdChandoan = benh.getDmbenhicdTen();
								//}
								tdChandoan = thamkham.getBenhicd10(true).getDmbenhicdTen();
							}
							
						}
					}
					//-------- Set phan tram cho noi dung thu
					int tiLeBHYT = 0;
					//if(tiepdon.getKhoibhytMa(true).getDtdmkhoibhytTylebhyt1()!=null)
					//	tiLeBHYT=tiepdon.getKhoibhytMa(true).getDtdmkhoibhytTylebhyt1();
					
					// phuc.lc 14-10-2010
					// Xet ma quyen loi dua theo so the bao hiem
					String strSotheBHYT = (tiepdon.getTiepdonSothebh() == null ?"" : tiepdon.getTiepdonSothebh());
					if (strSotheBHYT.trim().length() > 3) {
						String strMaQuyenloi = strSotheBHYT.substring(2, 3);
						DtDmMqlBhytDelegate maQLDel = DtDmMqlBhytDelegate.getInstance();
						DtDmMqlBhyt maQL = maQLDel.findByMa(strMaQuyenloi);
						if (maQL != null) {
							// Set lai ty le bao hiem theo  ma quyen loi															
							tiLeBHYT = (maQL.getDtdmmqlbhytTylebhyt1() == null ? 0 : maQL.getDtdmmqlbhytTylebhyt1());																		
						}
					}
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
												
											//iPhanTram = 50;
											iPhanTram = 100 - (new Integer(IConstantsRes.TYLE_BH_VUOT_TUYEN)).intValue();
											
										}
									}
							}
						}
					} // end IF BH equal ...
					
					sNoiDungThu="Thu "+iPhanTram+IConstantsRes.NOI_DUNG_THU_BHYT;
					// phuc.lc 05/01/2010 : Begin fix bug 1961
					if("MP".equals(tiepdon.getDoituongMa(true).getDmdoituongMa())){
						sNoiDungThu=IConstantsRes.NOI_DUNG_THU_MP;
					}
					// phuc.lc 05/01/2010 : END fix bug 1961
					//log.info("daThanhToan (load tiep don info):"+daThanhToan);
					
					//pxNgaylap = Utils.getCurrentDate(); // bao.ttc:
					//phuc.lc 28-12-2010 begin
					Calendar cal = Calendar.getInstance();
					SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
					pxNgaylap = formatter.format(cal.getTime());		
					gioThanhToan = Utils.getGioPhut(cal.getTime()) ;
					//phuc.lc 28-12-2010 end
					//log.info("set other fields- in form----");
					this.setFormTiepDon(tiepdon);
					
					
					// hien thi danh sach cls
					ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();
					
					java.util.ArrayList<ClsKham> listCLS_XuatHangChoBNBHYT_temp = (java.util.ArrayList<ClsKham>)clsKhamDelegate.findByTiepdonma(tiepdon.getTiepdonMa());
					//log.info("----------------listCLS_XuatHangChoBNBHYT_temp--------:"+listCLS_XuatHangChoBNBHYT_temp);
					//if (listCLS_XuatHangChoBNBHYT_temp != null && listCLS_XuatHangChoBNBHYT_temp.size() > 0) {
						
						listCLS_XuatHangChoBNBHYT = listCLS_XuatHangChoBNBHYT_temp;
						for (ClsKham cls : listCLS_XuatHangChoBNBHYT) {
							com.iesvn.yte.dieutri.setinfor.SetInforUtil.setInforIfNullForClsKham(cls);		
							listDataLog+= "CLS ma:"+ cls.getClskhamMahang(true).getDtdmclsbgMa() + 
									" Don gia: "+cls.getClskhamDongia()+"\n";
						}			
					//}
					
					ThuocPhongKhamDelegate thuocDel = ThuocPhongKhamDelegate.getInstance();
					java.util.ArrayList<ThuocPhongKham> thuocTemp = (java.util.ArrayList<ThuocPhongKham>)thuocDel.find2LoaiByMaTiepDon(tiepdon.getTiepdonMa(), Utils.XU_TRI_THUOC_TAI_BAN_KHAM, Utils.KE_TOA_QUAY_BENH_VIEN);
					tienThuocDaTT = 0.0;
					if (thuocTemp !=null && thuocTemp.size()>0) {
						listThuocPK = thuocTemp;
						for (ThuocPhongKham thuocPhongKham : listThuocPK) {
							com.iesvn.yte.dieutri.setinfor.SetInforUtil.setInforIfNullForThuocPhongKham(thuocPhongKham);
							if (thuocPhongKham.getThuocphongkhamDatt() == null) {
								tienThuocDaTT = 0.0;
							} else if (thuocPhongKham.getThuocphongkhamDatt() == true) {
								//tienThuocDaTT += thuocPhongKham.getThuocphongkhamDongia() * thuocPhongKham.getThuocphongkhamSoluong();
								tienThuocDaTT += thuocPhongKham.getThuocphongkhamThanhtien();
							}
							//luu log thong tin thuoc
							listDataLog += " Thuoc Ma LK:"+ thuocPhongKham.getThuocphongkhamMalk()+
									" Don gia: "+  thuocPhongKham.getThuocphongkhamDongia()+ " Don gia ban: "+ thuocPhongKham.getThuocphongkhamDongiaban()+ 
									" So luong: "+ thuocPhongKham.getThuocphongkhamSoluong()+
									" So lo: " +
									" Nam SX: " + thuocPhongKham.getThuocphongkhamNamnhap()+
									" Nam HD: " + thuocPhongKham.getThuocphongkhamNamhd()+ " \n";	
						
						}	
					}
					
					
					HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
					hsttk = hsttkDelegate.findBytiepdonMa(tdMa);
					/*
					 * bao.ttc: kiem tra xem da TT hay chua, neu da TT thi khong cho nhan "Cap nhat"
					 */
					if (hsttk != null){
						FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA);
						checkThanhToan = "none";
						
						permiengiam = 0;
						miengiam = hsttk.getHsthtoankXetgiam();
						thatthu = ( hsttk.getHsthtoankThatthu() == null?0:hsttk.getHsthtoankThatthu().doubleValue() ) ;
						perbhytchi = hsttk.getHsthtoankTylebh();
						thanhtien1 = hsttk.getHsthtoankTongchi();
						perbntra = 100 - perbhytchi;
						bntra = hsttk.getHsthtoankBntra();
						bhytchi = hsttk.getHsthtoankBhyt();
						
						cls = ( hsttk.getHsthtoankCls() == null?0:hsttk.getHsthtoankCls().doubleValue() ) 
						 + ( hsttk.getHsthtoankClsndm() == null?0:hsttk.getHsthtoankClsndm().doubleValue() ) ;
						
						thuoc = ( hsttk.getHsthtoankThuoc() == null?0:hsttk.getHsthtoankThuoc().doubleValue() ) 
						 + ( hsttk.getHsthtoankThuocndm() == null?0:hsttk.getHsthtoankThuocndm().doubleValue() ) 
						 + ( hsttk.getHsthtoankVtth() == null?0:hsttk.getHsthtoankVtth().doubleValue() ) 
						 + ( hsttk.getHsthtoankVtthndm() == null?0:hsttk.getHsthtoankVtthndm().doubleValue() ) ;
						nofoundTD = "false";
						maPhieu = hsttk.getHsthtoankMa();
						//SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
						if (hsttk.getHsthtoankNgaygiott() != null){
							Date ngayGioTT = hsttk.getHsthtoankNgaygiott();
							gioThanhToan = Utils.getGioPhut(ngayGioTT);
							pxNgaylap = formatter.format(ngayGioTT);
						}
					}
					
					else if (hsttk == null){
						hsttk = new HsThtoank();
						//log.info("tiepdon = " + tiepdon);
						hsttk.setTiepdonMa(tiepdon);
						//log.info("hsttk is null");
						tinhToanChoHSTKKham(hsttk);
						//log.info("tyle BH = " + hsttk.getHsthtoankTylebh());
						if (hsttk.getHsthtoankTylebh() != null) {
							sNoiDungThu="Thu "+(100 - hsttk.getHsthtoankTylebh().intValue())+IConstantsRes.NOI_DUNG_THU_BHYT;
						}
						// phuc.lc 05/01/2010 : Begin fix bug 1961
						if("MP".equals(tiepdon.getDoituongMa(true).getDmdoituongMa())){
							sNoiDungThu=IConstantsRes.NOI_DUNG_THU_MP;							
						}
						// phuc.lc 05/01/2010 : END fix bug 1961
						//bntra = bntra - tienThuocDaTT * iPhanTram / 100;
						//bhytchi = bhytchi - tienThuocDaTT * (100 - iPhanTram) / 100;
						nofoundTD = "true";
						maPhieu = "";						
						Hsba hsba = HsbaDelegate.getInstance().findByTiepDonMa(tiepdon.getTiepdonMa());
						if (hsba != null) {
							// Truong hop da chuyen vao noi tru nhung khong chuyen so lieu thi hien thi thong bao  nay
							//FacesMessages.instance().add(IConstantsRes.BENH_NHAN_CO_HSBA_NOI_TRU, hsba.getHsbaSovaovien());
							HsThtoan hstt = HsThtoanDelegate.getInstance().findBySovaovien(hsba.getHsbaSovaovien());
							if(hstt != null) {
								FacesMessages.instance().add(IConstantsRes.BENH_NHAN_CO_HSBA_NOI_TRU,
										"(" + (hstt.getHsthtoanNgaytt() != null ? IConstantsRes.DA_THANH_TOAN :IConstantsRes.CHUA_THANH_TOAN) + ")",
										hsba.getHsbaSovaovien());
							} else {
								FacesMessages.instance().add(IConstantsRes.BENH_NHAN_CO_HSBA_NOI_TRU,
										"(" + IConstantsRes.CHUA_THANH_TOAN + ")",
										hsba.getHsbaSovaovien());
							}
						}
					}
					List<ThamKham> listTk = ThamKhamDelegate.getInstance().findAllByMaTiepDon(tdMa);					
					strBankham = "";
					for (ThamKham eachTk : listTk) {
						strBankham += (strBankham.trim().length() > 0 ? (eachTk.getThamkhamBankham() == null ? "" : ", " + eachTk.getThamkhamBankham().getDtdmbankhamMa()) : (eachTk.getThamkhamBankham() == null ? "" : eachTk.getThamkhamBankham().getDtdmbankhamMa()));
					}
				} // end: IF tiepdon != null
				else
				{
					this.resetTiepDon();
					FacesMessages.instance().add(IConstantsRes.TIEP_DON_UNAVAILABLE + " " + tdMa);
					nofoundTD = "true";
					strBankham = "";
					//log.info("------Ma tiep don khong hop le---------");
				}
			}
			else{
				this.resetTiepDon();
				nofoundTD = "true";
				strBankham = "";
			}
		}
		catch(Exception ex){
			log.info(":( ERROR: " + ex);
			ex.printStackTrace();
		}
		
		//log.info("---End loadTiepdonAjax() method-----");
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
		 //miengiam = hsthtoankUtilTmp.getMiengiam();
		 //phuc.lc 05-10-2010
		 miengiam = hsttk.getHsthtoankXetgiam();
		 thatthu = hsthtoankUtilTmp.getThatthu();
		 perbhytchi = hsthtoankUtilTmp.getPerbhytchi();
		 //bhytchi = hsthtoankUtilTmp.getBhytchi();
		//phuc.lc 05-10-2010
		 bhytchi = hsttk.getHsthtoankBhyt();
		 thanhtien1 = hsttk.getHsthtoankTongchi();		 
		 perbntra = hsthtoankUtilTmp.getPerbntra();
		 //bntra = hsthtoankUtilTmp.getBntra();
		//phuc.lc 05-10-2010
		 bntra = hsttk.getHsthtoankBntra();
		 
		 cls = ( hsttk.getHsthtoankCls() == null?0:hsttk.getHsthtoankCls().doubleValue() ) 
				 + ( hsttk.getHsthtoankClsndm() == null?0:hsttk.getHsthtoankClsndm().doubleValue() ) ;

		 thuoc = ( hsttk.getHsthtoankThuoc() == null?0:hsttk.getHsthtoankThuoc().doubleValue() ) 
		 + ( hsttk.getHsthtoankThuocndm() == null?0:hsttk.getHsthtoankThuocndm().doubleValue() ) 
		  + ( hsttk.getHsthtoankVtth() == null?0:hsttk.getHsthtoankVtth().doubleValue() ) 
		  + ( hsttk.getHsthtoankVtthndm() == null?0:hsttk.getHsthtoankVtthndm().doubleValue() )  ;
		 
		//listCtTPK_temp = hsthtoankUtilTmp.getListCtTPK_temp(); // thuoc phong kham tai ban kham
		ThuocPhongKhamDelegate thuocDel = ThuocPhongKhamDelegate.getInstance();
		listCtTPK_temp = (java.util.ArrayList<ThuocPhongKham>)thuocDel.find2LoaiByMaTiepDon(tiepdon.getTiepdonMa(), Utils.XU_TRI_THUOC_TAI_BAN_KHAM, Utils.KE_TOA_QUAY_BENH_VIEN);
		clslist = hsthtoankUtilTmp.getListCtkq_temp();
		
	}
	
	// bao.ttc: doi ten ham
	public void loadPhieuXuatBhAjax(){
		//log.info("---Begin loadPhieuXuatBhAjax() method----");
		//String message = "";
		try{
			//this.resetForm();
			if (maPhieu == null || maPhieu.equals("")) {
				nofoundTD = "true";
				return;
			}
			
			//listThuocPK = new ArrayList<ThuocPhongKham>(); // bao.ttc: 
			//benhnhan = new BenhNhan();
			//SetInforUtil.setInforIfNullForBN(benhnhan);
			tiepdon = new TiepDon();
			
			HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
			hsttk = hsttkDelegate.findByMaPhieu(maPhieu);			
			if (hsttk == null || hsttk.equals("") 
				|| hsttk.getHsthtoankNgaygiott() == null) {
				nofoundTD = "true";
				FacesMessages.instance().add(IConstantsRes.KHONG_TIM_THAY_PHIEU, maPhieu);
				maPhieu = "";
				resetForm();
				return;
			}
			
			tiepdon = hsttk.getTiepdonMa();
			kyhieu = hsttk.getHsthtoankKyhieu();
			bienlai = hsttk.getHsthtoankBienlai();
			quyen = hsttk.getHsthtoankQuyen();
			loadTiepdonAjax();
			//phuc.lc 28-12-2010 begin
			//if (hsttk != null && hsttk.getHsthtoankNgaygiott() != null){
			//	SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
			//	Date ngayGioTT = hsttk.getHsthtoankNgaygiott();
			//	gioThanhToan = Utils.getGioPhut(ngayGioTT);
			//	pxNgaylap = formatter.format(ngayGioTT);
			//}			
			//phuc.lc 28-12-2010 end
		}
		catch (Exception e) {
			log.info(":( ERROR : " + e);
		}
		//log.info("---End loadPhieuXuatBhAjax() method-----");
	}
	
	
	public String troVe(){
		try {
			//log.info("***** troVe()"); 
			this.resultReportName = "";
			this.resultReportFileName = "";
			return "B3232_ThanhToanBenhNhanBHYT";
		} 		
		catch (Exception e) {
			log.info("***** End exception = " + e);    	
		} 
		return "";
	}
	
	public void resetForm(){
		//log.info("---Begin resetForm() method----");
		refreshnhanvienthungan();
		maPhieu = "";
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
		listThuocPK = new ArrayList<ThuocPhongKham>();	 // bao.ttc: 
		
		listCLS_XuatHangChoBNBHYT = new ArrayList<ClsKham>();	
		
		//pxNgaylap = Utils.getCurrentDate();
		//phuc.lc 28-12-2010 begin
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
		pxNgaylap = formatter.format(cal.getTime());		
		gioThanhToan = Utils.getGioPhut(cal.getTime()) ;
		//phuc.lc 28-12-2010 end
		nvBacsi = "";
		nvPhat = "";
		if (nhanVienThungan != null) {
			nvPhat = nhanVienThungan.getDtdmnhanvienMa();
			nvPhatTen = nhanVienThungan.getDtdmnhanvienTen();
		}
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
		  perbntra = 0;
		  bntra = new Double(0);
		
		  cls = new Double(0);
		  thuoc = new Double(0);
		  strBankham = "";
		  kyhieu = "";
		  quyen = "";
		  bienlai = "";
		//log.info("---End resetForm() method-----");
	}
	
	public void resetTiepDon(){
		//log.info("---End resetTiepDon() method-----");
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
			listThuocPK = new ArrayList<ThuocPhongKham>(); // bao.ttc: 
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
		/*}else{
			this.resetForm();
		}*/
		//log.info("---End resetTiepDon() method-----");
	}
	
	
	
	private void setFormTiepDon(TiepDon tiepdon){
		//log.info("begin setFormTiepDon(TiepDon tiepdon) method");
		//log.info("tiepdon.getTiepdonTylebh() : "+ tiepdon.getTiepdonTylebh());
		
		benhnhan = tiepdon.getBenhnhanMa();
		SetInforUtil.setInforIfNullForBN(benhnhan);
		//log.info("benhnhan: " + benhnhan);
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
		}else {
			tdKcbBhyt = "";
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
		
//bao.ttc: Load ben Thamkham
//		DmBenhIcd chandoan = tiepdon.getTiepdonMachdoanbd();
//		if(chandoan != null){
//			tdChandoan = chandoan.getDmbenhicdTen();
//			log.info("##################### Chan doan: " + tdChandoan);
//			chandoan = null;
//		} else
//			log.info("##################### Chan doan: NULL");
//		
//		DtDmNhanVien dmNvBsi = tiepdon.getTiepdonBacsi();
//		if(dmNvBsi != null){
//			nvBacsi = dmNvBsi.getDtdmnhanvienTen();
//			log.info("##################### Bac si: " + nvBacsi);
//			dmNvBsi = null;
//		} else
//			log.info("##################### Bac si: NULL");
		
		//DtDmNhanVien dmNvPhat = phieuxuatbh.getDtdmnhanvienPhat(); 
		/*if(dmNvPhat != null){
			nvPhat = dmNvPhat.getDtdmnhanvienMa();
			dmNvPhat = null;
		}*/
		
		//log.info("End setFormTiepDon(TiepDon tiepdon) method");
	}
	
	
	public String inPhieu(){
		//log.info("Begin inPhieu(), tiepdon = " + tiepdon + ", Ma TD = " + tiepdon.getTiepdonMa());
		String tdMa = tiepdon.getTiepdonMa();
		HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
		hsttk = hsttkDelegate.findBytiepdonMa(tdMa);
		if(hsttk == null){
			FacesMessages.instance().add(IConstantsRes.KHONG_TIM_THAY_PHIEU);
			//log.info("End InPhieu(): Khong tim thay phieu !");
			return "";
		}
		
		Double thtoan = 0.0;
		// phuc.lc 25-01-2011 : Begin fix bug 2091
		if(tiepdon.getDoituongMa(true).getDmdoituongMa().equals("BH")) {
			if(hsttk.getHsthtoankBntra() == null){
				thtoan = new Double(0);
			} else{
				thtoan = hsttk.getHsthtoankBntra();
			}
		} else {
			if(hsttk.getHsthtoankThtoan() == null){
				thtoan = new Double(0);
			} else{
				thtoan = hsttk.getHsthtoankThtoan();
			}
		}
		// phuc.lc 25-01-2011 : End fix bug 2091
		// phuc.lc 04/01/2011 : nghiep vu thay doi, khong can kiem tra ngoai danh muc nua 
		//Double ndm = 0.0;
		//if(hsttk.getHsthtoankNdm() == null){
		//	ndm = new Double(0);
		//} else{
		//	ndm = hsttk.getHsthtoankNdm();
		//}
		if(thtoan == 0 ){		//bao.ttc: BHYT tra 100% chi phi
			FacesMessages.instance().add(IConstantsRes.KHONG_IN_BIEN_LAI);
			//log.info("End InPhieu(): Khong in bien lai !");
			return "";
		}
		
		//XuatReport();
		XuatReport2();
		//log.info("End inPhieu() ---------------------------");
		return "B4160_Chonmenuxuattaptin";
	}
	
	public void XuatReport2() {
		reportTypeKC="InphieuXHBNBHYT";
		String baocao1 = null;
		ClsKhamDelegate clsKhamDelegate  = ClsKhamDelegate.getInstance();
		// Cac bien luu gia benh nha tra cho tung can lam sang sau  khi da tru % bao hiem
		
		Double mau = 0.0;		//Mau
		Double xntdcn = 0.0;	// XN- TDCN		
		Double cdha = 0.0;		// Chan doan hinh anh
		Double pttt = 0.0;		// PT -TT
		Double ktc = 0.0;		// Dich vu ky thuat cao		
		Double dvp = 0.0;		// Dich vu phong
		Double vc = 0.0;		// Van chuyen (Chuyen vien)
		Double ck = 0.0;		// Cong kham
		Double clskhac = 0.0; 	// Clskhac
		// Cac bien luu gia dich vu cua can lam sang
		
		Double mauDV = 0.0;		//Mau
		Double xntdcnDV = 0.0;	// XN- TDCN		
		Double cdhaDV = 0.0;		// Chan doan hinh anh
		Double ptttDV = 0.0;		// PT -TT
		Double ktcDV = 0.0;		// Dich vu ky thuat cao		
		Double dvpDV = 0.0;		// Dich vu phong
		Double vcDV = 0.0;		// Van chuyen (Chuyen vien)
		Double ckDV = 0.0;		// Cong kham
		Double clskhacDV = 0.0;
		
		boolean hasDV = false;	// bien cho biet co su dung dich vu hay khong
		Double curGia = 0.0;
		Double curGiaDV = 0.0;
		Double tongtien = 0.0;
		Double tongtienDV = 0.0;
		Double miengiam = 0.0;
		// Lay hsttk
		HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
		hsttk = hsttkDelegate.findBytiepdonMa(tiepdon.getTiepdonMa());
		if (hsttk != null) {
			miengiam = hsttk.getHsthtoankXetgiam() == null ? 0.0 : hsttk.getHsthtoankXetgiam(); 
		}
		// lay CLS
		List<ClsKham> listClsKham = clsKhamDelegate.findByTiepdonma(tiepdon.getTiepdonMa());
		
		for (ClsKham eachCls : listClsKham) {
			
			curGia = (eachCls.getClskhamDongiabntra() == null ? 0.0 : eachCls.getClskhamDongiabntra());			
			curGiaDV = (eachCls.getClskhamPhandv() == null ? 0.0 : eachCls.getClskhamPhandv());
			
			// Kiem tra cls co phai la dich vu hay khong
			if (eachCls.getClskhamYeucau() != null && eachCls.getClskhamYeucau().booleanValue() == true) {				
				hasDV = true;
				if (tiepdon.getDoituongMa() != null) {
					if (tiepdon.getDoituongMa().getDmdoituongMa().equalsIgnoreCase("MP")) {
						// Neu la doi tuong mien phi thi tinh lai gia dich vu
						curGia = (eachCls.getClskhamMahang().getDtdmclsbgDongiamp() == null ? 0.0 : eachCls.getClskhamMahang().getDtdmclsbgDongiamp());
						// Doi tuong mien phi yeu cau thuc hien CLS thi : gia dich vu = gia yeu cau - gia mien phi
						curGiaDV = (eachCls.getClskhamDongia() == null ? 0.0 : eachCls.getClskhamDongiabntra()) - curGia;
					}
				}
			}
			tongtien += curGia;
			tongtienDV += curGiaDV;
			// Lay gia benh nhan tra cho tung CLS, gia nay da tru % bao hiem va duoc tinh va luu san trong database	
			if (eachCls.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("MA")) {	// Mau
				mau += curGia;				
				mauDV += curGiaDV;
			} else if (eachCls.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("XN")) {	// Xet nghiem
				xntdcn += curGia;				
				xntdcnDV += curGiaDV;
			} else if (eachCls.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("CD")) {	// Chan doan hinh anh
				cdha += curGia;				
				cdhaDV += curGiaDV;
			} else if (eachCls.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("PT") ||  // Phau thuat
					eachCls.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("TT")) {	// Thu thuat
				pttt += curGia;				
				ptttDV += curGiaDV;
			}else if(eachCls.getClskhamKtcao() != null && eachCls.getClskhamKtcao().booleanValue() == true) {	// Dich vu ky thuat cao
				ktc += curGia;				
				ktcDV += curGiaDV;	
			}else if (eachCls.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("GI")) {	// Tien giuong(phong)
				dvp += curGia;				
				dvpDV += curGiaDV;	
			} else if (eachCls.getClskhamMaloai() != null && eachCls.getClskhamMaloai().getDtdmclsMaso().intValue() == 12){ // 12 la ma so cua can lam san van chuyen
				vc += curGia;				
				vcDV += curGiaDV;
			
			}else if (eachCls.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("KH")) {	// Cong kham
				ck += curGia;
				ckDV += curGiaDV;
			} else {
				clskhac += curGia;
				clskhacDV += curGiaDV;
			}
			
			
		}
		
		// lay thuoc ban` khoam
		ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
		Double thuocDV = 0.0;   	// Tong tien thuoc 
		Double vtthDV = 0.0;		// Tong tien Vat tu tieu hao
		Double thuocBN = 0.0;   	// Thuoc benh nhan tra
		Double vtthBN = 0.0;		// Vat tu tieu hao benh nhan tra
		List<ThuocPhongKham> listTpk_Bankham = tpkDelegate.findByMaTiepDon(tiepdon.getTiepdonMa(), "1"); // thuoc ban kham
		//log.info("listTpk_Bankham.size() = " + listTpk_Bankham.size());
		for(ThuocPhongKham eachTpk : listTpk_Bankham) {
						
			curGia = (eachTpk.getThuocphongkhamTienbntra() == null ? 0.0 : eachTpk.getThuocphongkhamTienbntra());
			
			// Kiem tra Thuoc co phai la dich vu hay khong
			if (eachTpk.getThuocphongkhamYeucau() != null && eachTpk.getThuocphongkhamYeucau().booleanValue() == true) {				
				hasDV = true;
			}
			if (eachTpk.getThuocphongkhamMathuoc()!=null 
					&& eachTpk.getThuocphongkhamMathuoc().getDmthuocPlbhyt() != null
					&& eachTpk.getThuocphongkhamMathuoc().getDmthuocPlbhyt().intValue() == 10 ){  // 10 : nhom vat tu tieu hao
			
				if (eachTpk.getThuocphongkhamYeucau() != null && eachTpk.getThuocphongkhamYeucau().booleanValue() == true) {
					vtthDV += curGia;
					tongtienDV += curGia;
				} else {				
					vtthBN += curGia;
					tongtien += curGia;
				}
			} else {
				if (eachTpk.getThuocphongkhamYeucau() != null && eachTpk.getThuocphongkhamYeucau().booleanValue() == true) {
					thuocDV += curGia;
					tongtienDV += curGia;
				} else {
					thuocBN += curGia;
					tongtien += curGia;
				}
			}
		}
		//log.info("tongtien 1 = " + tongtien);
		// ke toa quay benh vien
		List<ThuocPhongKham>  listTpk_BHYT = tpkDelegate.findByMaTiepDon(tiepdon.getTiepdonMa(), "3"); // ke toa BHYT
		//log.info("listTpk_BHYT.size() = " + listTpk_BHYT.size());
		for(ThuocPhongKham eachTpk : listTpk_BHYT) {
			//log.info("bn tra = " + eachTpk.getThuocphongkhamTienbntra());			
			curGia = (eachTpk.getThuocphongkhamTienbntra() == null ? 0.0 : eachTpk.getThuocphongkhamTienbntra());
			
			// Kiem tra Thuoc co phai la dich vu hay khong
			if (eachTpk.getThuocphongkhamYeucau() != null && eachTpk.getThuocphongkhamYeucau().booleanValue() == true) {				
				hasDV = true;
			}
			//log.info("Loai thuoc ma = " + eachTpk.getThuocphongkhamMathuoc().getDmphanloaithuocMaso().getDmloaithuocMaso().getDmloaithuocMa());
			if (eachTpk.getThuocphongkhamMathuoc()!=null 
					&& eachTpk.getThuocphongkhamMathuoc().getDmthuocPlbhyt() != null
					&& eachTpk.getThuocphongkhamMathuoc().getDmthuocPlbhyt().intValue() == 10 ){  // 10 : nhom vat tu tieu hao			
				if (eachTpk.getThuocphongkhamYeucau() != null && eachTpk.getThuocphongkhamYeucau().booleanValue() == true) {
					vtthDV += curGia;
					tongtienDV += curGia;
				} else {				
					vtthBN += curGia;
					tongtien += curGia;
				}
			} else {
				if (eachTpk.getThuocphongkhamYeucau() != null && eachTpk.getThuocphongkhamYeucau().booleanValue() == true) {
					thuocDV += curGia;
					tongtienDV += curGia;
				} else {
					thuocBN += curGia;
					tongtien += curGia;
				}
			}
		}
		//log.info("tongtien 2 = " + tongtien);
		// Copy code
		String diachistr = "";
		if(benhnhan.getBenhnhanDiachi() != null)
			diachistr += benhnhan.getBenhnhanDiachi()+", ";
		if(benhnhan.getXaMa(true).getDmxaTen() !=null)
			diachistr += benhnhan.getXaMa(true).getDmxaTen()+", ";
		if(benhnhan.getHuyenMa(true).getDmhuyenTen() != null)
			diachistr += benhnhan.getHuyenMa(true).getDmhuyenTen()+", ";
		if(benhnhan.getTinhMa(true).getDmtinhTen() != null)
			diachistr += benhnhan.getTinhMa(true).getDmtinhTen();
		
		String thungan = "";
		if(hsttk.getHsthtoankThungan() != null){
			if (hsttk.getHsthtoankThungan().getDtdmnhanvienTen() != null)
				thungan = hsttk.getHsthtoankThungan().getDtdmnhanvienTen();
			else thungan = "";
		} else thungan = "";
		//log.info("hasDV = " + hasDV);
		tongtien = Utils.rounDoubleForReport(tongtien);
		//log.info("tongtien 3 = " + tongtien + ", miengiam = " + miengiam);
		tongtienDV = Utils.rounDoubleForReport(tongtienDV);
		// phuc.lc 21-04-2011 : Fix bug 2655
		int lanIn = hsttk.getHsthtoankLanin() == null ? 1 : (Integer.valueOf(hsttk.getHsthtoankLanin()).intValue() + 1);
		hsttk.setHsthtoankLanin("" + lanIn);		
		if (hasDV && tongtienDV > 0) {
			// Neu co su dung dich vu thi in 3 lien bien lai thu tien va 3 lien hoa don
			try{
				String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_hoadon.jrxml";
				String pathTemplate_sub1 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_mau1_sub1.jrxml";
				String pathTemplate_sub2 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_mau1_sub2.jrxml";
				String pathTemplate_sub3 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_mau1_sub3.jrxml";
				String pathTemplate_sub4 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_hoadon_subreport0.jrxml";
				String pathTemplate_sub5 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_hoadon_subreport1.jrxml";
				String pathTemplate_sub6 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_hoadon_subreport2.jrxml";
				//log.info("da thay file templete bienlaithulephi_hoadon " + pathTemplate);
				
				JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
				JasperReport jasperSub1 =JasperCompileManager.compileReport(pathTemplate_sub1);
				JasperReport jasperSub2 =JasperCompileManager.compileReport(pathTemplate_sub2);
				JasperReport jasperSub3 =JasperCompileManager.compileReport(pathTemplate_sub3);
				
				JasperReport jasperSub4 =JasperCompileManager.compileReport(pathTemplate_sub4);
				JasperReport jasperSub5 =JasperCompileManager.compileReport(pathTemplate_sub5);
				JasperReport jasperSub6 =JasperCompileManager.compileReport(pathTemplate_sub6);
				//log.info("da thay file template ");
				
				Map<String, Object> params = new HashMap<String, Object>();
				
				params.put("sub1", jasperSub1 );
				params.put("sub2", jasperSub2 );	
				params.put("sub3", jasperSub3 );
				params.put("sub4", jasperSub4 );
				params.put("sub5", jasperSub5 );
				params.put("sub6", jasperSub6 );
				// Cac param chung
				//params.put("DIADIEM", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
				params.put("DIADIEM", IConstantsRes.REPORT_DIEUTRI_TINH);
				params.put("BVBD", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
				params.put("DIACHI_BV", IConstantsRes.REPORT_DIEUTRI_DIA_CHI);
				params.put("MASOTHUE", IConstantsRes.REPORT_DIEUTRI_MA_SO_THUE);
				params.put("HOTEN", benhnhan.getBenhnhanHoten());				
				params.put("DIACHI", diachistr);
				params.put("THUNGAN", thungan);
				
				// Cac param cho mau bien lai thu vien phi
				params.put("NOIDUNG", sNoiDungThu );												
				params.put("SOTIEN", Utils.formatNumberWithSeprator(tongtien - miengiam));
				params.put("TIENBANGCHU", Utils.NumberToString(tongtien - miengiam));				
				params.put("CLS", Utils.formatNumberWithSeprator(clskhac));
				params.put("THUOC", Utils.formatNumberWithSeprator(thuocBN));
				params.put("MAU", Utils.formatNumberWithSeprator(mau));
				params.put("XNTDCN", Utils.formatNumberWithSeprator(xntdcn));
				params.put("CDHA", Utils.formatNumberWithSeprator(cdha));
				params.put("PTTT", Utils.formatNumberWithSeprator(pttt));
				params.put("DVKTC", Utils.formatNumberWithSeprator(ktc));
				params.put("VTTH", Utils.formatNumberWithSeprator(vtthBN));
				params.put("DVP", Utils.formatNumberWithSeprator(dvp + dvpDV));
				params.put("CV", Utils.formatNumberWithSeprator(vc));
				params.put("CONGKHAM", Utils.formatNumberWithSeprator(ck));
				params.put("MIENGIAM", Utils.formatNumberWithSeprator(miengiam));
				
				// Cac param cho mau bien lai thu tien (hoa don)
				params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
				params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
				params.put("THUOCDV", Utils.formatNumberWithSeprator(thuocDV));
				params.put("VTTHDV", Utils.formatNumberWithSeprator(vtthDV));
				params.put("CLSDV", Utils.formatNumberWithSeprator(clskhacDV));
				params.put("MAUDV", Utils.formatNumberWithSeprator(mauDV));
				params.put("XNTDCNDV", Utils.formatNumberWithSeprator(xntdcnDV));
				params.put("CDHADV", Utils.formatNumberWithSeprator(cdhaDV));
				params.put("PTTTDV", Utils.formatNumberWithSeprator(ptttDV));
				params.put("DVKTCDV", Utils.formatNumberWithSeprator(ktcDV));
				params.put("DVPDV", Utils.formatNumberWithSeprator(new Double(0.0)));
				params.put("CVDV", Utils.formatNumberWithSeprator(vcDV));
				params.put("CONGKHAMDV", Utils.formatNumberWithSeprator(ckDV));
				params.put("SOTIENDV", Utils.formatNumberWithSeprator(tongtienDV));
				params.put("TIENBANGCHUDV", Utils.NumberToString(tongtienDV));
				
				params.put("LANIN", "" + lanIn);
				
				Class.forName("oracle.jdbc.OracleDriver");
			    //log.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    } catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintKC =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintKC,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/","pdf","InphieuXHBNBHYT");
			    reportPathKC=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    //log.info("duong dan file xuat report :" + baocao1);
			    //log.info("duong dan -------------------- :"+reportPathKC);
			    index+=1;
			    //log.info("Index :" + getIndex());
			    if(conn != null) conn.close();
			 // phuc.lc 21-04-2011 : Fix bug 2655
			    hsttkDelegate.edit(hsttk);
	            
			} catch(Exception e) {
			    log.info("ERROR Method XuatReport!!!");
				e.printStackTrace();
			}
		} else {
			// Neu khong su dung dich vu thi chi in 3 lien bien lai thu tien
			try{
				String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_mau1.jrxml";
				String pathTemplate_sub1 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_mau1_sub1.jrxml";
				String pathTemplate_sub2 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_mau1_sub2.jrxml";
				String pathTemplate_sub3 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_mau1_sub3.jrxml";
				//log.info("da thay file templete bienlaithulephi_mau1" + pathTemplate);
				
				JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
				JasperReport jasperSub1 =JasperCompileManager.compileReport(pathTemplate_sub1);
				JasperReport jasperSub2 =JasperCompileManager.compileReport(pathTemplate_sub2);
				JasperReport jasperSub3 =JasperCompileManager.compileReport(pathTemplate_sub3);
				//log.info("da thay file template ");
				
				Map<String, Object> params = new HashMap<String, Object>();
				
				params.put("sub1", jasperSub1 );
				params.put("sub2", jasperSub2 );
				params.put("sub3", jasperSub3 );
				
				
				//params.put("DIADIEM", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
				params.put("DIADIEM", IConstantsRes.REPORT_DIEUTRI_TINH);
				params.put("BVBD", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
				params.put("DIACHI_BV", IConstantsRes.REPORT_DIEUTRI_DIA_CHI);
				params.put("MASOTHUE", IConstantsRes.REPORT_DIEUTRI_MA_SO_THUE);
				params.put("HOTEN", benhnhan.getBenhnhanHoten());
				
				params.put("DIACHI", diachistr);
				params.put("NOIDUNG", sNoiDungThu );
				//log.info("tongtien 4 = " + tongtien + ", miengiam = " + miengiam);								
				params.put("SOTIEN", Utils.formatNumberWithSeprator(tongtien - miengiam));
				params.put("TIENBANGCHU", Utils.NumberToString(tongtien - miengiam));
				//params.put("NDM", Utils.formatNumberWithSeprator(ndm));
				
				//bao.ttc: kiem tra nghiep vu tinh tien cac loai dich vu
				params.put("CLS", Utils.formatNumberWithSeprator(clskhac));
				params.put("THUOC", Utils.formatNumberWithSeprator(thuocBN));
				params.put("MAU", Utils.formatNumberWithSeprator(mau));
				params.put("XNTDCN", Utils.formatNumberWithSeprator(xntdcn));
				params.put("CDHA", Utils.formatNumberWithSeprator(cdha));
				params.put("PTTT", Utils.formatNumberWithSeprator(pttt));
				params.put("DVKTC", Utils.formatNumberWithSeprator(ktc));
				params.put("VTTH", Utils.formatNumberWithSeprator(vtthBN));
				params.put("DVP", Utils.formatNumberWithSeprator(dvp + dvpDV));
				params.put("CV", Utils.formatNumberWithSeprator(vc));
				params.put("CONGKHAM", Utils.formatNumberWithSeprator(ck));
				params.put("MIENGIAM", Utils.formatNumberWithSeprator(miengiam));
				params.put("THUNGAN", thungan);
				
				params.put("LANIN", "" + lanIn);
				
				Class.forName("oracle.jdbc.OracleDriver");
			    //log.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    } catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintKC =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintKC,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/","pdf","InphieuXHBNBHYT");
			    reportPathKC=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    //log.info("duong dan file xuat report :" + baocao1);
			    //log.info("duong dan -------------------- :"+reportPathKC);
			    index+=1;
			    //log.info("Index :" + getIndex());
			    if(conn != null) conn.close();
			 // phuc.lc 21-04-2011 : Fix bug 2655
			    hsttkDelegate.edit(hsttk);
	            
			} catch(Exception e) {
			    log.info("ERROR Method XuatReport!!!");
				e.printStackTrace();
			}
		}
	}
	
	//phuc.lc 18-01-2011 : Begin fix bug 1360 - Huy hoa don
	public void huyPhieu() {
		//log.info("Begin huyPhieu(), hsttk = " + hsttk + ", maPhieu = " + maPhieu);
		if (hsttk != null) {
			
			//if (hsttk.getHsthtoankDatt() != null && hsttk.getHsthtoankDatt().booleanValue() == true) {
			if (hsttk.getHsthtoankNgaygiott() != null) {
				
				if (hsttk.getHsthtoankMa() == null) hsttk.setHsthtoankMa(maPhieu); 
				try {
					// Lay danh sach CLS theo ma tiep don de cap nhat lai
					ClsKhamDelegate clsKhamDel = ClsKhamDelegate.getInstance();
					List<ClsKham> listCls = clsKhamDel.findByTiepdonma(hsttk.getTiepdonMa().getTiepdonMa());
					if (listCls != null && listCls.size() > 0) {
						for(ClsKham eachCls : listCls) {
							eachCls.setClskhamDatt(null);
							eachCls.setClskhamNgaygiott(null);
							eachCls.setClskhamMaphieu(null);
							clsKhamDel.edit(eachCls);
						}
					}
					// Lay danh sach Thuoc phong kham (thuoc ban kham) theo ma tiep don de cap nhat lai
					ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
					List<ThuocPhongKham> listTpk = tpkDelegate.findByMaTiepDon(hsttk.getTiepdonMa().getTiepdonMa(), "1"); // thuoc ban kham
					if (listTpk != null && listTpk.size() > 0) {
						for(ThuocPhongKham eachTpk : listTpk) {
							eachTpk.setThuocphongkhamDatt(null);
							eachTpk.setThuocphongkhamNgaygiott(null);
							eachTpk.setThuocphongkhamMaphieud(null);
							tpkDelegate.edit(eachTpk);
						}
					}
					// Lay danh sach Thuoc phong kham (ke toa quay benh vien) theo ma tiep don de cap nhat lai
					listTpk = tpkDelegate.findByMaTiepDon(hsttk.getTiepdonMa().getTiepdonMa(), "3"); // thuoc ban kham
					if (listTpk != null && listTpk.size() > 0) {
						for(ThuocPhongKham eachTpk : listTpk) {
							eachTpk.setThuocphongkhamDatt(null);
							eachTpk.setThuocphongkhamNgaygiott(null);
							eachTpk.setThuocphongkhamMaphieud(null);
							tpkDelegate.edit(eachTpk);
						}
					}
					// Cap nhat hsttk 
					hsttk.setHsthtoankDatt(null);
					hsttk.setHsthtoankNgaygiott(null);
					hsttk.setHsthtoankLanin(null);
					HsThtoankDelegate.getInstance().edit(hsttk);					
					FacesMessages.instance().add(IConstantsRes.HUY_PHIEU_THANH_CONG, hsttk.getHsthtoankMa());
					resetForm();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} else {
				// Thong bao chua thanh toan
			}
		}
		//log.info("End huyPhieu()");
	}
	//phuc.lc 18-01-2011 : End fix bug 1360 - Huy hoa don
	//phuc.lc 27-01-2011 : Begin fix bug 2110 : Them chuc nang In phieu thanh toan
	public String inPhieuThanhToan() {
		return XuatPhieuThanhToan();
		//return "B4160_Chonmenuxuattaptin";
	}
	public String XuatPhieuThanhToan() {
		//log.info("begin XuatPhieuThanhToan() tiepdon = " + tiepdon);
		if (tiepdon == null) return null;
		reportTypeKC="PhieuThanhToanKCB";
		
		String baocao1=null;
		try {
			//log.info("Vao Method XuatPhieuThanhToan() kham chua benh ngoai tru");
			
			SimpleDateFormat sdf=new SimpleDateFormat(Utils.FORMAT_DATE);			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			
			//String matiepDon = thamkham.getTiepdonMa().getTiepdonMa();
			params.put("MATIEPDON", tiepdon.getTiepdonMa() );
			params.put("HOTENBN", benhnhan.getBenhnhanHoten()  );
			
			String diachistr="";
			if(benhnhan.getBenhnhanDiachi() != null)
				diachistr += benhnhan.getBenhnhanDiachi()+", ";
			if(benhnhan.getXaMa(true).getDmxaTen() !=null)
				diachistr += benhnhan.getXaMa(true).getDmxaTen()+", ";
			if(benhnhan.getHuyenMa(true).getDmhuyenTen() != null)
				diachistr += benhnhan.getHuyenMa(true).getDmhuyenTen()+", ";
			if(benhnhan.getTinhMa(true).getDmtinhTen() != null)
				diachistr += benhnhan.getTinhMa(true).getDmtinhTen();
			params.put("DIACHI", diachistr );
			
			if(tiepdon.getDoituongMa(true).getDmdoituongMa().equals("BH")){
				params.put("BHYT_CO", "X" );
				
				if( (tiepdon.getTiepdonTuyen() != null && tiepdon.getTiepdonTuyen().toString().equals("1"))
						|| (tiepdon.getTiepdonCoGiayGioiThieu() != null && tiepdon.getTiepdonCoGiayGioiThieu()) ){
					params.put("DUNGTUYEN","X");
				} else {
					params.put("TRAITUYEN","X");
				}
				
			} else {
				params.put("BHYT_KO", "X" );
			}
			
			if(tiepdon.getTiepdonGiatri1() != null){
				params.put("GTTU", sdf.format(tiepdon.getTiepdonGiatri1()));
			}else{
				params.put("GTTU", "");
			}
				
			if(tiepdon.getTiepdonGiatri2() != null){
				params.put("GTDEN", sdf.format(tiepdon.getTiepdonGiatri2()));
			}else{
				params.put("GTDEN", "");
			}
			if (tiepdon.getTiepdonSothebh() != null && !tiepdon.getTiepdonSothebh().equals("") &&
					tiepdon.getKhoibhytMa(true).getDtdmkhoibhytMa() != null && !tiepdon.getKhoibhytMa(true).getDtdmkhoibhytMa().equals("") &&
					tiepdon.getKcbbhytMa(true).getDmbenhvienMa()!=null
			){
				
				params.put("MATHEBHYT", tiepdon.getTiepdonSothebh());
				params.put("MABENHVIEN", tiepdon.getKcbbhytMa(true).getDmbenhvienMa().replace(".", "-"));
				
				
			}else{
				params.put("MABENHVIEN","");
				params.put("MATHEBHYT","");
			}
			
			if(tiepdon.getKcbbhytMa(true).getDmbenhvienTen()!=null)
				params.put("NOIDKKCBBANDAU", tiepdon.getKcbbhytMa(true).getDmbenhvienTen());
			if(tiepdon.getTinhbhytMa(true).getDmtinhTen()!=null)
				params.put("NOICAPTHE", tiepdon.getTinhbhytMa(true).getDmtinhTen());
			
			if(tiepdon.getTiepdonDonvigoi(true).getDmbenhvienMa()!=null)
				params.put("NOIGIOITHIEU", tiepdon.getTiepdonDonvigoi(true).getDmbenhvienTen());
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(thamkham.getThamkhamNgaygio());
			params.put("NGAYKHAMBENH", new Timestamp(cal.getTimeInMillis()));
			
			params.put("LYDOVAOVIEN", tiepdon.getTiepdonLydovaov());
			
			HsThtoankDelegate thanhToanDel = HsThtoankDelegate.getInstance();
			HsThtoank hsttk = new HsThtoank();
			hsttk = (HsThtoank)thanhToanDel.findBytiepdonMaLast(tiepdon.getTiepdonMa()); //20101109 bao.ttc: tim HSTTK cuoi cung 
			if (hsttk == null || (hsttk.getHsthtoankDatt() != null && !hsttk.getHsthtoankDatt())) {
				hsttk = new HsThtoank();
				hsttk.setTiepdonMa(tiepdon);			
				
				ThamKhamUtil tkUtil = new ThamKhamUtil();
				tkUtil.tinhToanChoHSTKKham(thamkham, hsttk, false, null, null);//tinhToanChoHSTKKham(hsttk);
				Utils.setInforForHsThToan(hsttk);
			}
			//log.info("Ty le bao hiem : " + hsttk.getHsthtoankTylebh());
			
			params.put("BHXHTHANHTOAN", hsttk.getHsthtoankBhyt() );
			params.put("NGUOIBENHTRA", hsttk.getHsthtoankBntra());
			
			// 20110701 bao.ttc: them cac thong tin cho mau moi dung tu ngay 1/7
			if(hsttk.getHsthtoankNgaygiott() != null){
				params.put("NGAYTHANHTOAN", hsttk.getHsthtoankNgaygiott());
				if(thamkham.getThamkhamNgaygio() != null){
					params.put("SONGAYDT", Utils.getDaysBetween(thamkham.getThamkhamNgaygio(), hsttk.getHsthtoankNgaygiott()));
				}
			}
			
			if(tiepdon.getTiepdonBankham() != null){
				if(tiepdon.getTiepdonBankham(true).getDtdmbankhamMa().startsWith("CC")){
					params.put("CAPCUU","X");
					params.put("DUNGTUYEN","");
					params.put("TRAITUYEN","");
				}
			}
			
			params.put("PHIEUSO", hsttk.getHsthtoankMa());
			//log.info("Ty le bao hiem : " + hsttk.getHsthtoankTylebh());
			params.put("TYLEBH",hsttk.getHsthtoankTylebh());
			String tyleBNtra = ""+ (100 - hsttk.getHsthtoankTylebh() );
			
			if ("MP".equals( tiepdon.getDoituongMa(true).getDmdoituongMa())){
				tyleBNtra = "0";
			}
			params.put("PHANTRAMBNTRA",tyleBNtra);
			
			params.put("BIENLAISO", ""  );
			
			String namsinh = "";
			if (tiepdon.getBenhnhanMa(true).getBenhnhanNgaysinh() != null){
				namsinh = sdf.format(tiepdon.getBenhnhanMa(true).getBenhnhanNgaysinh());
			} else {
				namsinh = tiepdon.getBenhnhanMa(true).getBenhnhanNamsinh();
			}
			params.put("namsinh", namsinh);
						
			String maChanDoan = "";
			String tenChanDoan = "";
			String chanDoan = "";
			
			
			if (thamkham.getBenhicd10() != null	&& thamkham.getBenhicd10(true).getDmbenhicdMaso() != null) {
				maChanDoan = thamkham.getBenhicd10(true).getDmbenhicdMa();
				params.put("MABENHICD", maChanDoan);
				tenChanDoan = thamkham.getBenhicd10(true).getDmbenhicdTen();
				chanDoan += maChanDoan + " " + tenChanDoan;
			}
			
			// bao.ttc: them thamkham.ghichu
			if (thamkham.getThamkhamGhichu() != null && !thamkham.getThamkhamGhichu().equals("")) {
				chanDoan += ", " + thamkham.getThamkhamGhichu();
			}
			
			// tiep tuc them benh phu.
			if (thamkham.getBenhicd10phu1() != null	&& thamkham.getBenhicd10phu1(true).getDmbenhicdMaso() != null) {
				maChanDoan = thamkham.getBenhicd10phu1(true).getDmbenhicdMa();
				tenChanDoan = thamkham.getBenhicd10phu1(true).getDmbenhicdTen();
				chanDoan += ", " + maChanDoan + " " + tenChanDoan;
			}
			if (thamkham.getBenhicd10phu2() != null && thamkham.getBenhicd10phu2(true).getDmbenhicdMaso() != null) {
				maChanDoan = thamkham.getBenhicd10phu2(true).getDmbenhicdMa();
				tenChanDoan = thamkham.getBenhicd10phu2(true).getDmbenhicdTen();
				chanDoan += ", " + maChanDoan + " " + tenChanDoan;
			}
			if (thamkham.getBenhicd10phu3() != null && thamkham.getBenhicd10phu3(true).getDmbenhicdMaso() != null) {
				maChanDoan = thamkham.getBenhicd10phu3(true).getDmbenhicdMa();
				tenChanDoan = thamkham.getBenhicd10phu3(true).getDmbenhicdTen();
				chanDoan += ", " + maChanDoan + " " + tenChanDoan;
			}
			if (thamkham.getBenhicd10phu4() != null && thamkham.getBenhicd10phu4(true).getDmbenhicdMaso() != null) {
				maChanDoan = thamkham.getBenhicd10phu4(true).getDmbenhicdMa();
				tenChanDoan = thamkham.getBenhicd10phu4(true).getDmbenhicdTen();
				chanDoan += ", " + maChanDoan + " " + tenChanDoan;
			}
			if (thamkham.getBenhicd10phu5() != null && thamkham.getBenhicd10phu5(true).getDmbenhicdMaso() != null) {
				maChanDoan = thamkham.getBenhicd10phu5(true).getDmbenhicdMa();
				tenChanDoan = thamkham.getBenhicd10phu5(true).getDmbenhicdTen();
				chanDoan += ", " + maChanDoan + " " + tenChanDoan;
			}
			
			params.put("CHANDOAN", chanDoan );
			
			params.put("PHONGKHAM", thamkham.getThamkhamBankham().getDtdmbankhamTen() );
			//Lay danh sach khoa da kham
			List<ThamKham> listTk = ThamKhamDelegate.getInstance().findAllByMaTiepDon(tiepdon.getTiepdonMa());
			List<ClsKham> listClsTmp;
			List<ThuocPhongKham> listTpk;
			StringBuffer bufStr = new StringBuffer();
			Double tongTienDV = 0.0;
			if (listTk != null && listTk.size() > 0) {
				List<Integer> listMasoKhoa = new ArrayList<Integer>(listTk.size());
				for (ThamKham each : listTk) {
					if(each.getThamkhamBankham().getDmkhoaMaso() != null) {
						if(listMasoKhoa.contains(each.getThamkhamBankham().getDmkhoaMaso().getDmkhoaMaso())) {
							continue;
						} else {
							listMasoKhoa.add(each.getThamkhamBankham().getDmkhoaMaso().getDmkhoaMaso());
						}
										
						if (bufStr.length() > 0) {
							bufStr.append(", " + each.getThamkhamBankham().getDmkhoaMaso().getDmkhoaTen());
						} else {
							bufStr.append(each.getThamkhamBankham().getDmkhoaMaso().getDmkhoaTen());
						}
					}
				}
			}		
					// Kiem tra co su dung cls yeu cau khong
					listClsTmp = ClsKhamDelegate.getInstance().findByTiepdonma(tiepdon.getTiepdonMa());
					if (listClsTmp != null && listClsTmp.size() > 0) {
						for (ClsKham eachCls : listClsTmp) {
							if (((eachCls.getClskhamYeucau() != null && eachCls.getClskhamYeucau().booleanValue() == true)
									|| (eachCls.getClskhamNdm() != null && eachCls.getClskhamNdm().booleanValue() == true))
									&& eachCls.getClskhamPhandv() != null) {
								tongTienDV += eachCls.getClskhamPhandv();
							}
						}
					}
					// Kiem tra co su dung thuoc yeu cau khong
					
					listTpk = ThuocPhongKhamDelegate.getInstance().findByMaTiepDon(tiepdon.getTiepdonMa(), "1");
					if (listTpk != null && listTpk.size() > 0) {
						for (ThuocPhongKham eachTpk : listTpk) {
							if ((eachTpk.getThuocphongkhamYeucau() != null && eachTpk.getThuocphongkhamYeucau().booleanValue() == true)
									|| (eachTpk.getThuocphongkhamNdm() != null && eachTpk.getThuocphongkhamNdm().booleanValue() == true))	{
								//tongTienDV += eachTpk.getThuocphongkhamDongia();
								tongTienDV += eachTpk.getThuocphongkhamThanhtien();
							}
						}
					}
					listTpk = ThuocPhongKhamDelegate.getInstance().findByMaTiepDon(tiepdon.getTiepdonMa(), "3");
					if (listTpk != null && listTpk.size() > 0) {
						for (ThuocPhongKham eachTpk : listTpk) {
							if ((eachTpk.getThuocphongkhamYeucau() != null && eachTpk.getThuocphongkhamYeucau().booleanValue() == true)
									|| (eachTpk.getThuocphongkhamNdm() != null && eachTpk.getThuocphongkhamNdm().booleanValue() == true))	{
								//tongTienDV += eachTpk.getThuocphongkhamDongia();
								tongTienDV += eachTpk.getThuocphongkhamThanhtien();
							}
						}
					}
				
			
			params.put("KHOA",bufStr.toString());
			String soTheTe_KhaiSinh_ChungSinh = thamkham.getTiepdonMa(true).getTiepdonSothete();
			
			
			//them vao khai sinh
			if (soTheTe_KhaiSinh_ChungSinh == null || soTheTe_KhaiSinh_ChungSinh.equals("")){
				if (tiepdon.getTiepdonKhaisinh() == null ||
						tiepdon.getTiepdonKhaisinh().equals("")){
					
				}else{
					soTheTe_KhaiSinh_ChungSinh = tiepdon.getTiepdonKhaisinh();
				}
				
			}else{
				if (tiepdon.getTiepdonKhaisinh() == null ||
						tiepdon.getTiepdonKhaisinh().equals("")){
					
				}else{
					soTheTe_KhaiSinh_ChungSinh += "/" + tiepdon.getTiepdonKhaisinh();
				}				
			}
			// them vao chung sinh
			
			if (soTheTe_KhaiSinh_ChungSinh == null || soTheTe_KhaiSinh_ChungSinh.equals("")){
				if (tiepdon.getTiepdonChungsinh() == null ||
						tiepdon.getTiepdonChungsinh().equals("")){
					
				}else{
					soTheTe_KhaiSinh_ChungSinh = tiepdon.getTiepdonChungsinh();
				}
				
			}else{
				if (tiepdon.getTiepdonChungsinh() == null ||
						tiepdon.getTiepdonChungsinh().equals("")){
					
				}else{
					soTheTe_KhaiSinh_ChungSinh += "/" + tiepdon.getTiepdonChungsinh();
				}				
			}
			///
			
			//log.info("soTheTe_KhaiSinh_ChungSinh"+soTheTe_KhaiSinh_ChungSinh);
			
			
			if (soTheTe_KhaiSinh_ChungSinh != null && !soTheTe_KhaiSinh_ChungSinh.equals("")){
				params.put("SOTHETEKSCS", soTheTe_KhaiSinh_ChungSinh);
			}else{
				params.put("SOTHETEKSCS", null);
			}
			//
			String soTheNgheo = tiepdon.getTiepdonThengheo();
			
			//log.info("soTheNgheo"+soTheNgheo);
			
			if (soTheNgheo != null && !soTheNgheo.equals("")){
				params.put("SOTHENGHEO", soTheNgheo);
			}else{
				params.put("SOTHENGHEO", null);
			}
			
			//SUB REPORT 3
			params.put("TONGCHIPHI", hsttk.getHsthtoankTongchi());
			params.put("BANGCHU1", Utils.NumberToString(hsttk.getHsthtoankTongchi()));
			params.put("BANGCHU2", Utils.NumberToString(hsttk.getHsthtoankBntra()));
			params.put("BANGCHU3", Utils.NumberToString(hsttk.getHsthtoankBhyt()));
			params.put("DONVITRIENKHAI", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			
			if(hsttk.getHsthtoankBntra() >= 0){
				params.put("SNGUOIBENHTRA", "0");
			}else{
				params.put("SNGUOIBENHTRA", "-1");
			}
			
			DmGioiTinh gioi = (DmGioiTinh)DieuTriUtilDelegate.getInstance().findByMaSo(tiepdon.getBenhnhanMa(true).getDmgtMaso(true).getDmgtMaso(), "DmGioiTinh","dmgtMaso" );
			if (gioi != null){

				params.put("GIOI", gioi.getDmgtTen());
						
			}
			params.put("DONVITRIENKHAI", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			//log.info("tongTienDV = " + tongTienDV);
			JasperReport jasperReport;
			if (tongTienDV > 0) {
				String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieuthanhtoanKCBngoaitru_DV.jrxml";
				String sub0Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieuthanhtoanKCBngoaitru_subreport0.jrxml";
				String sub1Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieuthanhtoanKCBngoaitru_subreport1.jrxml";
				String sub2Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieuthanhtoanKCBngoaitru_DV_subreport0.jrxml";
				String sub3Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieuthanhtoanKCBngoaitru_DV_subreport1.jrxml";
				
				jasperReport =JasperCompileManager.compileReport(pathTemplate);
				JasperReport sub0Report =JasperCompileManager.compileReport(sub0Template);
				JasperReport sub1Report =JasperCompileManager.compileReport(sub1Template);
				JasperReport sub2Report =JasperCompileManager.compileReport(sub2Template);
				JasperReport sub3Report =JasperCompileManager.compileReport(sub3Template);
				
				params.put("sub0", sub0Report);
				params.put("sub1", sub1Report);
				params.put("sub2", sub2Report);
				params.put("sub3", sub3Report);
			} else {
				String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieuthanhtoanKCBngoaitru.jrxml";
				String sub0Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieuthanhtoanKCBngoaitru_subreport0.jrxml";
				String sub1Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieuthanhtoanKCBngoaitru_subreport1.jrxml";
				
				jasperReport =JasperCompileManager.compileReport(pathTemplate);
				JasperReport sub0Report =JasperCompileManager.compileReport(sub0Template);
				JasperReport sub1Report =JasperCompileManager.compileReport(sub1Template);
				
				params.put("sub0", sub0Report);
				params.put("sub1", sub1Report);
			}
			//log.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			    //log.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintKC =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintKC,getIndex(),IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","PhieuThanhToanKCB");
			    reportPathKC=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    //log.info("duong dan file xuat report :" + baocao1);
			    //log.info("duong dan -------------------- :"+reportPathKC);
			    setIndex(getIndex() + 1);
			    //log.info("Index :" + getIndex());
			    if(conn != null) {
			    	conn.close();
			    	conn = null;
			    }
		}catch (Exception e) {
		    log.info("ERROR Method XuatPhieuThanhToan!!!");
			e.printStackTrace();
			return null;
		}
		//log.info("Thoat Method XuatPhieuThanhToan");
		return "B4160_Chonmenuxuattaptin";
	    
	}
	//phuc.lc 27-01-2011 : End fix bug 2110 
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

	public java.util.ArrayList<ThuocPhongKham> getListThuocPK() { // bao.ttc: 
		return listThuocPK;
	}

	public void setListThuocPK(java.util.ArrayList<ThuocPhongKham> listThuocPK) { // bao.ttc: 
		this.listThuocPK = listThuocPK;
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
	
	public String getNvPhatTen() {
		return nvPhatTen;
	}

	public void setNvPhatTen(String nvPhatTen) {
		this.nvPhatTen = nvPhatTen;
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
		ThanhToanBenhNhanBHYTAction.log = log;
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

	public String getReturnToThanhToanBNBHYT() {
		return returnToThanhToanBNBHYT;
	}

	public void setReturnToThanhToanBNBHYT(String returnToThanhToanBNBHYT) {
		this.returnToThanhToanBNBHYT = returnToThanhToanBNBHYT;
	}

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

	public String getKyhieu() {
		return kyhieu;
	}

	public void setKyhieu(String kyhieu) {
		this.kyhieu = kyhieu;
	}

	public String getQuyen() {
		return quyen;
	}

	public void setQuyen(String quyen) {
		this.quyen = quyen;
	}

	public String getBienlai() {
		return bienlai;
	}

	public void setBienlai(String bienlai) {
		this.bienlai = bienlai;
	}

	public String getMaPhieu() {
		return maPhieu;
	}

	public void setMaPhieu(String maPhieu) {
		this.maPhieu = maPhieu;
	}

	public String getCheckThanhToan() {
		return checkThanhToan;
	}

	public void setCheckThanhToan(String checkThanhToan) {
		this.checkThanhToan = checkThanhToan;
	}
	public DtDmNhanVien getNhanVienThungan() {
		return nhanVienThungan;
	}

	public void setNhanVienThungan(DtDmNhanVien nhanVienThungan) {
		this.nhanVienThungan = nhanVienThungan;
	}

	public String getGioThanhToan() {
		return gioThanhToan;
	}

	public void setGioThanhToan(String gioThanhToan) {
		this.gioThanhToan = gioThanhToan;
	}

	public String getStrBankham() {
		return strBankham;
	}

	public void setStrBankham(String strBankham) {
		this.strBankham = strBankham;
	}
	
}

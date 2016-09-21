package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
import com.iesvn.yte.dieutri.delegate.PcCumThuPhiDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.DtDmCum;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.HsThtoank;
import com.iesvn.yte.dieutri.entity.PcCumThuPhi;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
import com.iesvn.yte.util.HoSoThanhToanKhamUtil;
import com.iesvn.yte.util.IConstantsRes;

@Scope(SESSION)
@Name("B3238_HoanThuTienCC")
@Synchronized(timeout = 6000000)
public class HoanThuTienCCAction implements Serializable {
	private static String FORMAT_DATE = "dd/MM/yyyy";
	private static final long serialVersionUID = 10L;
	private static Logger logger = Logger.getLogger(HoanThuTienCCAction.class);
	
	@DataModel
	private ArrayList<ThuocPhongKham> listTpk;
	@DataModelSelection
	@Out(required = false)
	private ThuocPhongKham tpkSelected;
	
	private TiepDon tiepDon;
	private BenhNhan benhNhan;
	private ThamKham thamKham;
//	private HsThtoank hsttk;
	private String ngayHienTai;
	private String maThuocPk;
	private String maBanKham;
	private Integer gioiTinh;
	private String ngaySinh;
	private String tkMa;
	private String soLuong;
	private String maThuoc;
	private int count;
	private Double bnTra;
	
	private String resultReportFileName;
	private String resultReportName;	
	private String loaiFileXuat;
	@Out(required = false)
	private String isReport = "false";
	
	// Phan danh cho tinh toan
	private int permiengiam = 0;
	private Double miengiam = new Double(0);
	private Double thatthu = new Double(0);
	private int perbhytchi = 0;
	private Double bhytchi = new Double(0);
	private Double thanhtien1 = new Double(0);
	private int perbntra = 0;
	private Double bntra = new Double(0);
	private Double khongThu = new Double(0);
	// end phan danh cho tinh toan
	
	private DtDmNhanVien nhanVienThungan = new DtDmNhanVien();
	
	private String kyhieu;
	private String quyen;
	private String bienlai;
	
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
	private DtDmCum cum = null;
	
	@Create
	public void init() {
		logger.info("-----init()-----");
		
		DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
		nhanVienThungan = nvDelegate.findByNd(identity.getUsername());
		if (nhanVienThungan == null) {
			nhanVienThungan = new DtDmNhanVien();
		}
		logger.info("nhanVienThungan1"+nhanVienThungan);
		PcCumThuPhiDelegate pcCumThuPhiDelegate  = PcCumThuPhiDelegate.getInstance();
		PcCumThuPhi pc =  pcCumThuPhiDelegate.findPcCumThuPhiByNgayAndNhanVien(new Date(), nhanVienThungan.getDtdmnhanvienMa());
		
		logger.info("PcCumThuPhi"+pc);
		
		
		if (pc!=null && pc.getDtdmnhanvienMa() != null){
			
			nhanVienThungan = pc.getDtdmnhanvienMa();
			logger.info("nhanVienThungan:"+nhanVienThungan);
			cum = pc.getDtdmcumMa();
		}
		reset();
	}
	
	
	private void tinhToanChiPhi(ArrayList<ThuocPhongKham>  listTpk){
		
		HsThtoank hsttk = new HsThtoank();
		hsttk.setTiepdonMa(thamKham.getTiepdonMa());
		HoSoThanhToanKhamUtil hsthtoankUtil = new HoSoThanhToanKhamUtil(thamKham.getTiepdonMa());
		hsthtoankUtil.tinhToanChoHSTKKham(hsttk);
		
		 permiengiam = 0;
		 miengiam = hsthtoankUtil.getMiengiam();
		 thatthu = hsthtoankUtil.getThatthu();
		 perbhytchi = hsthtoankUtil.getPerbhytchi();
		 bhytchi = hsthtoankUtil.getBhytchi();
		 thanhtien1 = hsthtoankUtil.getThanhtien1();
		 perbntra = hsthtoankUtil.getPerbntra();
		 bntra = hsthtoankUtil.getBntra();
		 khongThu = hsthtoankUtil.getKhongThu();
		
		
		
	}
	
	public void end() throws ParseException {
		logger.info("-----end()-----");
		if (listTpk.size() > 0) {
			//RemoveUtil.removeAllNullFromThamKham(thamKham);
//			removeInfoForHsThtoank();
			
//			if (hsttk != null) {
//				//hsttk.setHsthtoankBntra(bnTra);
//				Double tt = new Double("0");
//				for (ThuocPhongKham tpk : listTpk) {
//					tpk.setThuocphongkhamMaphieud(null);
//					tpk.setThuocphongkhamTra(tpk.getThuocphongkhamSoluong());
//					DmDoiTuong dt = tpk.getThuocphongkhamThamkham().getTiepdonMa().getDoituongMa();
//					Double dg = new Double("0");
//					Double sl = tpk.getThuocphongkhamTra();
//					if (dt.getDmdoituongMa().equals("BH")) {
//						//DmThuoc dmt = tpk.getThuocphongkhamMathuoc();
//						/**
//						 * Neu thuoc trong danh muc thi lay don gia
//						 * con lai lay gia bh
//						 */
//						dg = tpk.getThuocphongkhamDongiabh();
//						logger.info("-----don gia bh: " + dg);
//					} else {
//						dg = tpk.getThuocphongkhamDongia();
//						logger.info("-----don gia : " + dg);
//					}
//					if (sl != null && dg != null) {
//						tt += sl * dg;
//						logger.info("-----thanh tien: " + tt);
//					}
//				}
//				logger.info("-----thanh tien: " + tt);
//				Double tienThuoc = hsttk.getHsthtoankThuoc();
//				//Double tongchi = hsttk.getHsthtoankTongchi();
//				if (tienThuoc == null) {
//					tienThuoc = new Double("0");
//				}
//				/*
//				if (tongchi == null) {
//					tongchi = new Double("0");
//				}
//				*/
//				tienThuoc += tt;
//				//tongchi = tongchi - tt;
//				hsttk.setHsthtoankHoanthu(tienThuoc);
//				
//				//hsttk.setHsthtoankTongchi(tongchi);
//			}
			for (ThuocPhongKham tpk : listTpk) {
				
				if (tpk.getThuocphongkhamMaphieud() != null && !tpk.getThuocphongkhamMaphieud().equals("")
				   && (tpk.getThuocphongkhamMaphieuh() == null ||  tpk.getThuocphongkhamMaphieuh().equals(""))		
				){
					tpk.setThuocphongkhamThunganHoan(nhanVienThungan);
					tpk.setThuocphongkhamCumHoan(cum);
					SimpleDateFormat formatter;	    
			        formatter = new SimpleDateFormat(FORMAT_DATE); 
			       
			        
			        tpk.setThuocphongkhamKyhieuHoan(kyhieu);
			        tpk.setThuocphongkhamQuyenHoan(quyen);
			        tpk.setThuocphongkhamBienlaiHoan(bienlai);
			       
		           Date d =	formatter.parse(ngayHienTai);
		           Calendar dCalendar = Calendar.getInstance();
		           dCalendar.setTime(d);
			          
				   tpk.setThuocphongkhamNgaygioHoanThu(dCalendar.getTime());
				}
				
			
			}
			
			ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
			try {
				if (tpkDelegate.createHoanThu(listTpk, null)) {
					FacesMessages.instance().add(IConstantsRes.SUCCESS);
				} else {
					FacesMessages.instance().add(IConstantsRes.FAIL);
				}
			} catch (Exception e) {
				logger.error(e);
			}
			
			SetInforUtil.setInforIfNullForThamKham(thamKham);
//			setInforIfNullForHsThtoank();
		}
	}
	
	public void displayInfo() {
		logger.info("displayInfo");
		ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
		HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
		List<ThuocPhongKham> listThuoc = null;
		try {
			listThuoc = tpkDelegate.findByMaPhieu(maThuocPk);
		} catch (Exception e) {
			logger.info(e);
			
		}
		logger.info("listThuoc:"+listThuoc);
		if (listThuoc != null && listThuoc.size() > 0) {
			listTpk = (ArrayList<ThuocPhongKham>) listThuoc;
			for (ThuocPhongKham tpk : listTpk) {
				 if (tpk.getThuocphongkhamMaphieuh() != null && !tpk.getThuocphongkhamMaphieuh().equals("")){
					 FacesMessages.instance().add(IConstantsRes.PHIEU_DA_HOAN_THU, maThuocPk);
						reset();
	                    return ;
	                }
				thamKham = tpk.getThuocphongkhamThamkham();
				tiepDon = thamKham.getTiepdonMa();
				benhNhan = tiepDon.getBenhnhanMa();
//				try {
//					hsttk = hsttkDelegate.findBytiepdonMa(tiepDon.getTiepdonMa());
//				} catch (Exception e) {
//					logger.error(e);
//				}
				logger.info("-----benh nhan: " + benhNhan.getBenhnhanMa());
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				ngaySinh = df.format(benhNhan.getBenhnhanNgaysinh());
				Date ngayGioTmp = tpk.getThuocphongkhamNgaygioHoanThu();
				if (ngayGioTmp!=null){
					ngayHienTai = df.format(ngayGioTmp);
				}
				
				maBanKham = thamKham.getThamkhamBankham().getDtdmbankhamMa();
				maThuocPk = tpk.getThuocphongkhamMaphieud();
				count = listTpk.size();
			}
			
			kyhieu = listTpk.get(0).getThuocphongkhamKyhieuHoan();
			quyen=listTpk.get(0).getThuocphongkhamQuyenHoan();
			bienlai=listTpk.get(0).getThuocphongkhamBienlaiHoan();
			
		} else {
			FacesMessages.instance().add(IConstantsRes.KHONG_TIM_THAY_PHIEU, maThuocPk);
			reset();
		}
		
		tinhToanChiPhi( listTpk);
		
		logger.info("end");
	}
	
	public void reset() {
		logger.info("-----reset()-----");
		listTpk = new ArrayList<ThuocPhongKham>();
		tpkSelected = new ThuocPhongKham();
		benhNhan = new BenhNhan();
		SetInforUtil.setInforIfNullForBN(benhNhan);
		thamKham = new ThamKham();
		SetInforUtil.setInforIfNullForThamKham(thamKham);
		tiepDon = new TiepDon();
		SetInforUtil.setInforIfNullForTiepDon(tiepDon);
//		hsttk = new HsThtoank();
//		setInforIfNullForHsThtoank();
		maBanKham = "";
		maThuocPk = "";
		maThuoc= "";
		count = 0;
		ngaySinh = "";
		
		// Phan danh cho tinh toan
		permiengiam = 0;
		 miengiam = new Double(0);
		 thatthu = new Double(0);
		 perbhytchi = 0;
		bhytchi = new Double(0);
		 thanhtien1 = new Double(0);
		perbntra = 0;
		 bntra = new Double(0);
		 khongThu = new Double(0);
		// end phan danh cho tinh toan
		 
		 kyhieu = "";
			quyen="";
			bienlai="";
	}
	
//	private void removeInfoForHsThtoank() {
//		logger.info("Begin removeInfoForHsThtoank() ");
//		if (hsttk != null) {
//			if (hsttk.getHsthtoankMabenh() != null) {
//				if ("".equals(Utils.reFactorString(hsttk.getHsthtoankMabenh().getDmbenhicdMa()) == null)) {
//					hsttk.setHsthtoankMabenh(null);
//				}
//			}
//			if (hsttk.getHsthtoankNhanviencn() != null) {
//				if ("".equals(Utils.reFactorString(hsttk.getHsthtoankNhanviencn().getDtdmnhanvienMa()) == null)) {
//					hsttk.setHsthtoankNhanviencn(null);
//				}
//			}
//			if (hsttk.getHsthtoankThungan() != null) {
//				if ("".equals(Utils.reFactorString(hsttk.getHsthtoankThungan().getDtdmnhanvienMa()) == null)) {
//					hsttk.setHsthtoankThungan(null);
//				}
//			}
//		}
//	}
//	
//	private void setInforIfNullForHsThtoank() {
//		logger.info("Begin setInforIfNullForHsThtoank() ");
//		if (hsttk.getHsthtoankMabenh() == null) {
//			hsttk.setHsthtoankMabenh(new DmBenhIcd());
//		}
//		if (hsttk.getTiepdonMa() == null) {
//			hsttk.setTiepdonMa(new TiepDon());
//		}
//		if (hsttk.getHsthtoankNhanviencn() == null) {
//			hsttk.setHsthtoankNhanviencn(new DtDmNhanVien());
//		}
//	}
	
	public void setBenhNhan(BenhNhan benhNhan) {
		this.benhNhan = benhNhan;
	}

	public BenhNhan getBenhNhan() {
		return benhNhan;
	}

	public void setThamKham(ThamKham thamKham) {
		this.thamKham = thamKham;
	}

	public ThamKham getThamKham() {
		return thamKham;
	}

	public void setNgayHienTai(String ngayHienTai) {
		this.ngayHienTai = ngayHienTai;
	}

	public String getNgayHienTai() {
		Date dHt = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		return df.format(dHt);
	}

	public void setMaThuocPk(String maThuocPk) {
		this.maThuocPk = maThuocPk;
	}

	public String getMaThuocPk() {
		return maThuocPk;
	}

	public void setTiepDon(TiepDon tiepDon) {
		this.tiepDon = tiepDon;
	}

	public TiepDon getTiepDon() {
		return tiepDon;
	}

	public void setGioiTinh(Integer gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public Integer getGioiTinh() {
		return gioiTinh;
	}

	public void setMaBanKham(String maBanKham) {
		this.maBanKham = maBanKham;
	}

	public String getMaBanKham() {
		return maBanKham;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getNgaySinh() {
		return ngaySinh;
	}

//	public void setHsttk(HsThtoank hsttk) {
//		this.hsttk = hsttk;
//	}
//
//	public HsThtoank getHsttk() {
//		return hsttk;
//	}

	public void setTkMa(String tkMa) {
		this.tkMa = tkMa;
	}

	public String getTkMa() {
		return tkMa;
	}

	public void setListTpk(ArrayList<ThuocPhongKham> listTpk) {
		this.listTpk = listTpk;
	}

	public ArrayList<ThuocPhongKham> getListTpk() {
		return listTpk;
	}

	public void setTpkSelected(ThuocPhongKham tpkSelected) {
		this.tpkSelected = tpkSelected;
	}

	public ThuocPhongKham getTpkSelected() {
		return tpkSelected;
	}

	public void setSoLuong(String soLuong) {
		this.soLuong = soLuong;
	}

	public String getSoLuong() {
		return soLuong;
	}

	public void setMaThuoc(String maThuoc) {
		this.maThuoc = maThuoc;
	}

	public String getMaThuoc() {
		return maThuoc;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public void setBnTra(Double bnTra) {
		this.bnTra = bnTra;
	}

	public Double getBnTra() {
		return bnTra;
	}

	public void setResultReportFileName(String resultReportFileName) {
		this.resultReportFileName = resultReportFileName;
	}

	public String getResultReportFileName() {
		return resultReportFileName;
	}

	public void setResultReportName(String resultReportName) {
		this.resultReportName = resultReportName;
	}

	public String getResultReportName() {
		return resultReportName;
	}

	public void setLoaiFileXuat(String loaiFileXuat) {
		this.loaiFileXuat = loaiFileXuat;
	}

	public String getLoaiFileXuat() {
		return loaiFileXuat;
	}

	public void setIsReport(String isReport) {
		this.isReport = isReport;
	}

	public String getIsReport() {
		return isReport;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		HoanThuTienCCAction.logger = logger;
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

	public Double getKhongThu() {
		return khongThu;
	}

	public void setKhongThu(Double khongThu) {
		this.khongThu = khongThu;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}


	public static String getFORMAT_DATE() {
		return FORMAT_DATE;
	}


	public static void setFORMAT_DATE(String format_date) {
		FORMAT_DATE = format_date;
	}


	public DtDmNhanVien getNhanVienThungan() {
		return nhanVienThungan;
	}


	public void setNhanVienThungan(DtDmNhanVien nhanVienThungan) {
		this.nhanVienThungan = nhanVienThungan;
	}


	public Identity getIdentity() {
		return identity;
	}


	public void setIdentity(Identity identity) {
		this.identity = identity;
	}


	public DtDmCum getCum() {
		return cum;
	}


	public void setCum(DtDmCum cum) {
		this.cum = cum;
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

}

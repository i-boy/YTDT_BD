package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

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

import javax.xml.rpc.ServiceException;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Begin;
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

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
import com.iesvn.yte.dieutri.delegate.PcCumThuPhiDelegate;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.DtDmCum;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.HsThtoank;
import com.iesvn.yte.dieutri.entity.PcCumThuPhi;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
import com.iesvn.yte.entity.DmThuoc;
import com.iesvn.yte.util.HoSoThanhToanKhamUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B3231_Thuocydungcuphongkham")
@Synchronized(timeout = 6000000)
public class ThuocYDungCuPhongKhamAction implements Serializable {
	private static final long serialVersionUID = 10L;
	private static Logger logger = Logger.getLogger(ThuocYDungCuPhongKhamAction.class);
	
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
	
	private Integer gioiTinh;
	private String ngaySinh;
	private String tkMa;
	private String soLuong;
	private Integer updateItem;
	private String isUpdate;
	private String maThuoc;
	private int count;
	
	
	private String kyhieu;
	private String quyen;
	private String bienlai;
	
	
	
	private DtDmNhanVien nhanVienThungan = new DtDmNhanVien();
	
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
	
	private Double cls;
	private Double thuoc;

	
	private String resultReportFileName;
	private String resultReportName;	
	private String loaiFileXuat;
	@Out(required = false)
	private String isReport = "false";
	
	
	private DtDmCum cum = null;
	
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
	
	private List<ThuocPhongKham> listCtTPK_temp = null; // day la thuoc tai ban kham, khi thanh toan kham -> tinh luon
	private List<ClsKham> clslist = null;
	private void tinhToanChoHSTKKham(HsThtoank hsttk, Boolean ghinhan){
		HoSoThanhToanKhamUtil hsthtoankUtilTmp = new HoSoThanhToanKhamUtil(thamKham.getTiepdonMa());
		hsthtoankUtilTmp.tinhToanChoHSTKKham(hsttk);
		Utils.setInforForHsThToan(hsttk);	
		
		if (ghinhan == true){
			listCtTPK_temp = hsthtoankUtilTmp.getListCtTPK_temp(); // thuoc phong kham tai ban kham			
			clslist = hsthtoankUtilTmp.getListCtkq_temp();
		}
		
		
	}
	
	@Begin(join = true)
	public String init() {
		logger.info("-----init()-----");
		goToThuoc ="done";
		
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
		return  "ThuVienPhi_SoLieuKhamBenh_ThuocYDungCuPhongKham";
	}
	
	@End
	public void endFunc(){
		
	}

	@In(required = false)
	@Out(required = false)
	private String maBanKhamOut;
	
	@In(required = false)
	@Out(required = false)
	private String maTiepDonOut;
	
	@In(required = false)
	@Out(required = false)
	private String loaiToaThuocThamKhamVaXuTri ;
	

//	@In(required = false)
//	@Out(required = false)
//	private String initB121_3_Xutrithuoctaibankham;
	
//	@In(required = false)
//	@Out(required = false)
//	private String returnToThuocYDungCuPhongKham;
	
	
	@In(required = false)
	@Out(required = false)
	private String goToThuoc;
	
	@Factory("goToThuoc")
	public void goToThuoc() throws Exception {
		goToThuoc = "done";
		
		
//		returnToThuocYDungCuPhongKham = "done";
		//
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
		//
		logger.info("maTiepDonOut:"+maTiepDonOut);
		//
		tiepDon.setTiepdonMa(maTiepDonOut);
		loadBenhNhan();
		//
	}
	
	
	@Factory("returnToThuocYDungCuPhongKham")
	public void initForReturn() throws Exception {
		goToThuoc = "done";
		logger.info("-----begin initForReturn-----");
		loadBenhNhan();
		
		logger.info("-----end initForReturn-----");
	}
	
//	public String gotoXuTriThuocTaiBanKham(){
//		maTiepDonOut = tiepDon.getTiepdonMa();
//		maBanKhamOut = maBanKham;
//		
//		logger.info("-----maTiepDonOut-----"+maTiepDonOut);
//		logger.info("-----maBanKhamOut-----"+maBanKhamOut);
//		
//		loaiToaThuocThamKhamVaXuTri = Utils.XU_TRI_THUOC_TAI_BAN_KHAM;
//		
//		initB121_3_Xutrithuoctaibankham = null;
//		
//		returnToThuocYDungCuPhongKham="1";
//		
//		return "xutrithuoctaibankham";
//	}
	

	@In(required = false)
	@Out(required = false)
	private String goToCLSPhongKham;
	
	public String clsphongkham() throws ServiceException, RemoteException {
		// log.info(hosoCBSelected.getSoHS());
		
		if (tiepDon.getTiepdonMa() == null || tiepDon.getTiepdonMa().equals("")){
			return "";
		}
		
		maTiepDonOut = tiepDon.getTiepdonMa();
		
		goToCLSPhongKham = null;
	
		return "ThuVienPhi_SoLieuKhamBenh_CanLamSanPhongKham";
	}
	/**
	 * Tìm kiếm bệnh nhân theo mã tiếp đón và mã bàn khám
	 */
	public void loadBenhNhan() {
		logger.info("-----loadBenhNhan()-----");
		logger.info("--***---tiepDon.getTiepdonMa()()---***--:"+tiepDon.getTiepdonMa());
		if (tiepDon.getTiepdonMa() == null || "".equals(tiepDon.getTiepdonMa()) ) {
			//do nothing
		} else {
			ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
//			HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
			ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
			try {
				List<ThamKham> lstThamKham = tkDelegate.findAllByMaTiepDon(tiepDon.getTiepdonMa());
				if (lstThamKham == null || lstThamKham.size() == 0){
					FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + tiepDon.getTiepdonMa());
					return;
				}
				thamKham = lstThamKham.get(0);
				logger.info("-----tham kham: " + thamKham.getThamkhamMa());
				
					tiepDon = thamKham.getTiepdonMa();
					benhNhan = tiepDon.getBenhnhanMa();
					logger.info("-----benh nhan: " + benhNhan.getBenhnhanMa());
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					if (benhNhan.getBenhnhanNgaysinh() != null){
						ngaySinh = df.format(benhNhan.getBenhnhanNgaysinh());
					}
					count = 0;
					listTpk = new ArrayList<ThuocPhongKham>();
					for (ThamKham tkTemp: lstThamKham){
						ArrayList<ThuocPhongKham> listTpkTmp = (ArrayList<ThuocPhongKham>) tpkDelegate.findByThamKham(tkTemp.getThamkhamMa(), Utils.XU_TRI_THUOC_TAI_BAN_KHAM);
						if (listTpkTmp != null && listTpkTmp.size() >0){
							count += listTpkTmp.size();
							for (ThuocPhongKham tpkTemp: listTpkTmp){
								listTpk.add(tpkTemp);
							}
							logger.info("-----listTpk: " + listTpk.size());
						}
						
					}
					
					
//					hsttk = hsttkDelegate.findBytiepdonMa(tiepDon.getTiepdonMa());
//					if (hsttk == null) {
//						hsttk = new HsThtoank();
//					}
//					logger.info("-----hsttk: " + hsttk);
					tinhToanChiPhi(listTpk);
					
				
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
				FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + tiepDon.getTiepdonMa()); 
				reset();
			}
			
		}
		
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
		
		
		 cls = ( hsttk.getHsthtoankCls() == null?0:hsttk.getHsthtoankCls().doubleValue() ) 
		 + ( hsttk.getHsthtoankClsndm() == null?0:hsttk.getHsthtoankClsndm().doubleValue() ) ;

 thuoc = ( hsttk.getHsthtoankThuoc() == null?0:hsttk.getHsthtoankThuoc().doubleValue() ) 
 + ( hsttk.getHsthtoankThuocndm() == null?0:hsttk.getHsthtoankThuocndm().doubleValue() ) 
  + ( hsttk.getHsthtoankVtth() == null?0:hsttk.getHsthtoankVtth().doubleValue() ) 
  + ( hsttk.getHsthtoankVtthndm() == null?0:hsttk.getHsthtoankVtthndm().doubleValue() )  ;
 
	}
	
	
	/**
	 * 
	 */
	public void tiepTucNhap() {
		logger.info("-----tiepTucNhap()-----");
		logger.info("-----tkMa: " + tkMa);
		logger.info("-----isUpdate: " + isUpdate);
		if (!isUpdate.equals("1")) {
			TonKhoDelegate tkDelegate = TonKhoDelegate.getInstance();
			TonKho tk = null;
			try {
				tk = tkDelegate.find(Integer.valueOf(tkMa));
			} catch (Exception e) {
				
			}
			if (updateItem != -1) {
				logger.info("-----Cap nhat thuoc phong kham: " + updateItem);
				ThuocPhongKham tpk = listTpk.get(updateItem);
				if (tk == null) {
					logger.info("-----Cap nhat so luong");
					tpk.setThuocphongkhamSoluong(Double.valueOf(soLuong));
				} else {
					logger.info("-----Cap nhat toan bo");
					tpk = createThuocPhongKhamFromTk(tk);
					tpk.setThuocphongkhamLoai(Utils.XU_TRI_THUOC_TAI_BAN_KHAM);
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					try {
						tpk.setThuocphongkhamNgaygio(df.parse(ngayHienTai));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					tpk.setThuocphongkhamSoluong(Double.valueOf(soLuong));
					tpk.setThuocphongkhamThamkham(thamKham);
					tpk.setThuocphongkhamBacsi(tiepDon.getTiepdonBacsi());
				}
				listTpk.set(updateItem, tpk);
			} else {
				logger.info("-----Them moi thuoc phong kham");
				if (tk != null) {
					ThuocPhongKham tpk = createThuocPhongKhamFromTk(tk);
					tpk.setThuocphongkhamLoai("1");
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					try {
						tpk.setThuocphongkhamNgaygio(df.parse(ngayHienTai));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					tpk.setThuocphongkhamSoluong(Double.valueOf(soLuong));
					tpk.setThuocphongkhamThamkham(thamKham);
					tpk.setThuocphongkhamBacsi(tiepDon.getTiepdonBacsi());
					tpk.setThuocphongkhamNgaygio(new Date());
					listTpk.add(tpk);
				}
			}
			logger.info("-----listTpk: " + listTpk.size());
			updateItem = -1;
			count = listTpk.size();
			maThuoc = "";
		}
		
	}
	
	public void end() throws ParseException {
		logger.info("-----end()-----");
		if (listTpk.size() > 0) {
			//RemoveUtil.removeAllNullFromThamKham(thamKham);
			
//			removeInfoForHsThtoank();
//			
//			if (hsttk != null) {
//				if (hsttk.getTiepdonMa() == null) {
//					hsttk.setTiepdonMa(tiepDon);
//				}
//				Double tt = new Double("0");
//				for (ThuocPhongKham tpk : listTpk) {
//					DmDoiTuong dt = tpk.getThuocphongkhamThamkham().getTiepdonMa().getDoituongMa();
//					Double dg = new Double("0");
//					Double sl = tpk.getThuocphongkhamSoluong();
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
//				Double tongchi = hsttk.getHsthtoankTongchi();
//				if (tienThuoc == null) {
//					tienThuoc = new Double("0");
//				}
//				if (tongchi == null) {
//					tongchi = new Double("0");
//				}
//				tienThuoc += tt;
//				tongchi = tongchi - tt;
//				hsttk.setHsthtoankThuoc(tienThuoc);
//				hsttk.setHsthtoankTongchi(tongchi);
//			}
			
			for (ThuocPhongKham tpk : listTpk) {
				if (tpk.getThuocphongkhamMaphieud() == null || tpk.getThuocphongkhamMaphieud().equals("")){
					
						tpk.setThuocphongkhamThungan(nhanVienThungan);
					
						tpk.setThuocphongkhamCum(cum);
					
						SimpleDateFormat formatter;	    
				        formatter = new SimpleDateFormat(Utils.FORMAT_DATE); 
				       
				       
				        tpk.setThuocphongkhamKyhieu(kyhieu);
				        tpk.setThuocphongkhamQuyen(quyen);
				        tpk.setThuocphongkhamBienlai(bienlai);
						
						
			           Date d =	formatter.parse(ngayHienTai);
			           Calendar dCalendar = Calendar.getInstance();
			           dCalendar.setTime(d);
				          
					   tpk.setThuocphongkhamNgaygiott(dCalendar.getTime());
					
				}
				
			}
			ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
			try {
				maThuocPk = tpkDelegate.createThuocPhongKham(listTpk, null, Utils.XU_TRI_THUOC_TAI_BAN_KHAM);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			logger.info("-----ma phieu: " + maThuocPk);
			if (!maThuocPk.equals("")) {
				FacesMessages.instance().add(IConstantsRes.SUCCESS);
				isUpdate = "1";
				
				HsThtoank hsttk = new HsThtoank();
				hsttk.setTiepdonMa(thamKham.getTiepdonMa());			
				tinhToanChoHSTKKham(hsttk,true);
				Utils.setInforForHsThToan(hsttk);
					
			} else {
				FacesMessages.instance().add(IConstantsRes.FAIL);
			}
			
			SetInforUtil.setInforIfNullForThamKham(thamKham);
//			setInforIfNullForHsThtoank();
		}
	}
	
	public void select(int index) {
		logger.info("-----select(" + index + ")-----");
		updateItem = index;
		DmThuoc thuoc = tpkSelected.getThuocphongkhamMathuoc();
		if (thuoc != null) {
			maThuoc = thuoc.getDmthuocMa();
			soLuong = tpkSelected.getThuocphongkhamSoluong().toString();
		}
	}
	
	public void delete(int index) {
		logger.info("-----delete(" + index + ")-----");
		listTpk.remove(index);
		count = listTpk.size();
		logger.info("-----deleted item: " + index);
	}
	/*
	public String inPhieu(String loaiFile) {
		logger.info(String.format("-----inPhieu()-----"));
		logger.info(String.format("-----loai file: %s", loaiFile));
		Date currentDate = new Date();
		String pathTemplate = "B3231_Thuocydungcuphongkham";
		
		if (!maThuocPk.equals("")) {
			try {
				ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
				List<ThuocPhongKham> listThuoc = tpkDelegate.findByMaPhieu(maThuocPk);
				ThuocPhongKham tpk = listThuoc.get(0);
				
				logger.info(String.format("-----pathTemplate: %s", pathTemplate));
				Map<String, Object> params = new HashMap<String, Object>();
				// saveSum(tuNgay, denNgay, donViMa);
				params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
				logger.info(String.format("-----tenSo: %s", params.get("tenSo")));
				params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
				logger.info(String.format("-----tenDonVi: %s", params.get("tenDonVi")));
				params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
				logger.info(String.format("-----dienThoaiDonVi: %s", params.get("dienThoaiDonVi")));
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(tpk.getThuocphongkhamNgaygio());
				if (cal != null) {
					logger.info(String.format("-----ngay lap: %s", cal.getTime()));
					params.put("ngayHt", "" + cal.get(Calendar.DAY_OF_MONTH));
					params.put("thangHt", "" + (cal.get(Calendar.MONTH) + 1));
					params.put("namHt", "" + cal.get(Calendar.YEAR));
				} else {
					logger.info("-----ngay lap is null");
					params.put("ngayHt", "");
					params.put("thangHt", "");
					params.put("namHt", "");
				}

				SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
				String ngayGioHt = df.format(Calendar.getInstance().getTime());
				logger.info(String.format("-----ngay gio hien tai: %s", ngayGioHt));
				params.put("gioHt", ngayGioHt);

				params.put("phieuMa", tpk.getThuocphongkhamMaphieud());
				
				Double tt = new Double("0");
				for (ThuocPhongKham thuoc : listThuoc) {
					tt += thuoc.getThuocphongkhamDongia() * thuoc.getThuocphongkhamSoluong();
				}
				params.put("tongTien", tt);
				logger.info(String.format("-----tongTien: %s", params.get("tongTien")));
				
				String fileNameExt = String.valueOf(currentDate.getTime()) ;
				String fileName = com.iesvn.yte.ReportUtil.xuatReport(IConstantsRes.PATH_BASE,
															IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI, 
															IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI, "vienphi/",
															pathTemplate , params, loaiFile, fileNameExt);
				
		        if(loaiFile.equalsIgnoreCase("HTML")) {
	            	setResultReportName(fileName);
	            } else {
	            	setResultReportFileName(fileName);
	            }
	            setIsReport("true");
			} catch (Exception ex) {
				logger.error(String.format("-----Error: %s", ex.toString()));
			}
		}
		return "B3231_Chonmenuxuattaptin";
	}
	*/
	@Out(required=false)
	@In(required=false)
	private String reportPathVP=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeVP=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintVP=null;
	
	public String inPhieu(){
		XuatReport();
		return "B3360_Chonmenuxuattaptin";
	}
	int index = 0;
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeVP="Thuocydungcuphongkham";
		logger.info("Vao Method XuatReport thuoc y dung cu phong kham");
		String baocao1=null;
		String pathTemplate=null;
		try {
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithutien.jrxml";
			String sub1Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithutien_sub1.jrxml";
			String sub2Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithutien_sub2.jrxml";
			
			logger.info("da thay file templete " + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			JasperReport sub1Report =JasperCompileManager.compileReport(sub1Template);
			JasperReport sub2Report =JasperCompileManager.compileReport(sub2Template);
			
			logger.info("****Finish creating jasperReport*****");
			
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put("sub1", sub1Report);
			params.put("sub2", sub2Report);
			
			String diachistr="";
			if(benhNhan.getBenhnhanDiachi() != null)
				diachistr += benhNhan.getBenhnhanDiachi()+",";
			if(benhNhan.getXaMa(true).getDmxaTen() !=null)
				diachistr += benhNhan.getXaMa(true).getDmxaTen()+",";
			if(benhNhan.getHuyenMa(true).getDmhuyenTen() != null)
				diachistr += benhNhan.getHuyenMa(true).getDmhuyenTen()+",";
			if(benhNhan.getTinhMa(true).getDmtinhTen() != null)
				diachistr += benhNhan.getTinhMa(true).getDmtinhTen();
			params.put("DIACHI", diachistr );
			
			params.put("UBNDTINH", IConstantsRes.REPORT_DIEUTRI_UBNDTINH);
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			
			params.put("HOTEN", benhNhan.getBenhnhanHoten());
			
			logger.info("*****SOTIEN: "+bntra);
			logger.info("*****So tien format: "+Utils.formatNumberWithSeprator(bntra));
			
			params.put("SOTIEN", Utils.formatNumberWithSeprator(bntra));
			params.put("TIENBANGCHU", Utils.NumberToString(bntra));
			logger.info("*****SOTIEN: "+bntra);
			
			logger.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			    logger.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	logger.info(e.getMessage());
			    }
			    jasperPrintVP =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","Thuocydungcuphongkham");
			    reportPathVP=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    logger.info("duong dan file xuat report :" + baocao1);
			    logger.info("duong dan -------------------- :"+reportPathVP);
			    index+= 1;
			    logger.info("Index :" + index);
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    logger.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    logger.info("Thoat Method XuatReport");
	}
	public void displayInfo() {
		ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
//		HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
		List<ThuocPhongKham> listThuoc = null;
		try {
			listThuoc = tpkDelegate.findByMaPhieu(maThuocPk);
		} catch (Exception e) {
			logger.error(e);
		}
		
		if (listThuoc != null) {
			if (listThuoc.size() > 0){
				kyhieu = listThuoc.get(0).getThuocphongkhamKyhieu();
				quyen = listThuoc.get(0).getThuocphongkhamQuyen();
				bienlai = listThuoc.get(0).getThuocphongkhamBienlai();
			}
			listTpk = (ArrayList<ThuocPhongKham>) listThuoc;
			
			
			for (ThuocPhongKham tpk : listTpk) {
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
				
				
				if (benhNhan.getBenhnhanNgaysinh() != null){
					ngaySinh = df.format(benhNhan.getBenhnhanNgaysinh());
				}
				ngayHienTai = df.format(tpk.getThuocphongkhamNgaygio());
//				maBanKham = thamKham.getThamkhamBankham().getDtdmbankhamMa();
				maThuocPk = tpk.getThuocphongkhamMaphieud();
				count = listTpk.size();
			}
			isUpdate = "1";
		} else {
			FacesMessages.instance().add(IConstantsRes.NOT_FOUND_ID, 
					"thuốc phòng khám", "mã " + maThuocPk);
			reset();
		}
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
//		maBanKham = "";
		maThuocPk = "";
		updateItem = -1;
		maThuoc= "";
		count = 0;
		isUpdate = "0";
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
		 
		 kyhieu="";
			quyen="";
			bienlai="";
		
			cls=null;
			thuoc=null;
	}
	
	public String troVe(){
		try {
			logger.info("***** troVe()");
			return "B3231_Thuocydungcuphongkham";
		} 		
		catch (Exception e) {
			logger.info("***** End exception = " + e);    	
		} 
		return null;
	}
	
	public ThuocPhongKham createThuocPhongKhamFromTk(TonKho tk) {
		ThuocPhongKham tpk = new ThuocPhongKham();
		tpk.setThuocphongkhamDongia(tk.getTonkhoDongia());
		tpk.setThuocphongkhamDongiaban(tk.getTonkhoDongiaban());
		tpk.setThuocphongkhamDongiabh(null);
		tpk.setThuocphongkhamHangsx(tk.getDmnhasanxuatMaso());
		tpk.setThuocphongkhamKhoa(tk.getDmkhoaMaso());
		tpk.setThuocphongkhamLo(tk.getTonkhoLo());
		tpk.setThuocphongkhamLoai(null);
		tpk.setThuocphongkhamMathuoc(tk.getDmthuocMaso());
		tpk.setThuocphongkhamNgaygiocn(new Date());
		tpk.setThuocphongkhamNhanviencn(null);
		tpk.setThuocphongkhamPhanloai(tk.getDmthuocMaso().getDmphanloaithuocMaso());
		tpk.setThuocphongkhamQuocgia(tk.getDmquocgiaMaso());
		tpk.setDmnctMaso(tk.getDmnctMaso());
		tpk.setDmnguonkinhphiMaso(tk.getDmnguonkinhphiMaso());
		tpk.setThuocphongkhamNgayhd(tk.getTonkhoNgayhandung());
		tpk.setThuocphongkhamThanghd(tk.getTonkhoThanghandung());
		tpk.setThuocphongkhamNamhd(tk.getTonkhoNamhandung());
		tpk.setThuocphongkhamNamnhap(tk.getTonkhoNamnhap());
		tpk.setThuocphongkhamMalk(tk.getTonkhoMalienket());
		
		return tpk;
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

//	public void setMaBanKham(String maBanKham) {
//		this.maBanKham = maBanKham;
//	}
//
//	public String getMaBanKham() {
//		return maBanKham;
//	}

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

	public void setUpdateItem(Integer updateItem) {
		this.updateItem = updateItem;
	}

	public Integer getUpdateItem() {
		return updateItem;
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

	public void setUpdate(String isUpdate) {
		this.isUpdate = isUpdate;
	}

	public String getUpdate() {
		return isUpdate;
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

	

	public String getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(String isUpdate) {
		this.isUpdate = isUpdate;
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

	public String getLoaiToaThuocThamKhamVaXuTri() {
		return loaiToaThuocThamKhamVaXuTri;
	}

	public void setLoaiToaThuocThamKhamVaXuTri(String loaiToaThuocThamKhamVaXuTri) {
		this.loaiToaThuocThamKhamVaXuTri = loaiToaThuocThamKhamVaXuTri;
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
//	public String getReturnToThuocYDungCuPhongKham() {
//		return returnToThuocYDungCuPhongKham;
//	}
//
//	public void setReturnToThuocYDungCuPhongKham(
//			String returnToThuocYDungCuPhongKham) {
//		this.returnToThuocYDungCuPhongKham = returnToThuocYDungCuPhongKham;
//	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Double getKhongThu() {
		return khongThu;
	}

	public void setKhongThu(Double khongThu) {
		this.khongThu = khongThu;
	}

	public DtDmNhanVien getNhanVienThungan() {
		return nhanVienThungan;
	}

	public void setNhanVienThungan(DtDmNhanVien nhanVienThungan) {
		this.nhanVienThungan = nhanVienThungan;
	}

	public DtDmCum getCum() {
		return cum;
	}

	public void setCum(DtDmCum cum) {
		this.cum = cum;
	}

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
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

}



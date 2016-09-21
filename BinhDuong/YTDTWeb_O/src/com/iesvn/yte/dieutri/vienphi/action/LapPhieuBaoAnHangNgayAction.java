package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.BenhNhanCheDoAnDelegate;
import com.iesvn.yte.dieutri.delegate.BenhNhanGioAnDelegate;
import com.iesvn.yte.dieutri.delegate.BenhNhanPhieuBaoAnDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmCheDoAnDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmDoiTuongAnDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmDongThemDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmGioAnDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmLoaiAn2Delegate;
import com.iesvn.yte.dieutri.delegate.DtDmLoaiAnDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmMucAnDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.PhieuBaoAnDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.BenhNhanCheDoAn;
import com.iesvn.yte.dieutri.entity.BenhNhanGioAn;
import com.iesvn.yte.dieutri.entity.BenhNhanPhieuBaoAn;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.PhieuBaoAn;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.util.IConstantsRes;

@Scope(SESSION)
@Name("B3140_Lapphieubaoanhangngay")
@Synchronized(timeout = 6000000)
public class LapPhieuBaoAnHangNgayAction implements Serializable
{

	// private static String FORMAT_DATE = "dd/MM/yyyy";
	// private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";

	private static final long serialVersionUID = 10L;
	
	// Phuc add
	//Gia tri cua cac hang so duoi day tuong ung voi id trong bang dt_dm_che_do_an
	private static int CHE_DO_AN_LAT = 1;
	private static int CHE_DO_AN_DD = 2;
	private static int CHE_DO_AN_STM = 3;
	private static int CHE_DO_AN_THUONG = 4;
	private static int CHE_DO_AN_CMO = 5;
	private static int CHE_DO_AN_BDUONG = 6;
	//Gia tri cua cac hang so duoi day tuong ung voi id trong bang dt_dm_gio_an
	private static int GIO_AN_6H = 1;
	private static int GIO_AN_11H = 2;
	private static int GIO_AN_16H = 3;
	private static int GIO_AN_20H = 4;
	
	//private PhieuBaoAn phieubaoan;
	private String khoaMaso;  
		
	private String ngayan;
	private String ngayLayPbaOld;
	private PhieuBaoAnCheDoAn pbacda;
	
	private String ngayhientai;	
	private String sohsbenhan;
	private String doituonganMaso;
	private String loaian1Maso;
	private String loaian2Maso;
	private String mucanMaso;
	private String dongthemMaso;
	private boolean cdaLat;
	private boolean cdaDd;
	private boolean cdaStm;
	private boolean cdaThuong;
	private boolean cdaCmo;
	private boolean cdaBduong;
	private boolean phutroi;
	
	private boolean gioan6;
	
	private boolean gioan11;
	private boolean gioan16;
	private boolean gioan20;
	private int count;
	private int selectedIndex;
	private String gioitinh;
	private String phong;  
	private boolean updatePba;  // Luu gia tri true/false cho biet cap nhat hay luu moi phieu bao an
	private boolean lockGhinhanBtn; 
	private boolean lockInphieuBtn;
	private boolean daRaVien;
	// phuc.lc 14-10-2010
	private String strIDRemove;	// Bien luu danh sach ID cua benh nhan bi xoa khoi list, (fix bug so 1256)
	
	private List<PhieuBaoAnCheDoAn> listPbaCda;
	@Out(required=false)
	@In(required=false)
	private String reportTypeVP=null;
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintVP=null;
	@Out(required=false)
	@In(required=false)
	private String reportPathVP=null;
	@In(scope=ScopeType.PAGE,required=false)
	@Out(scope=ScopeType.PAGE,required=false)
	private String strLapPba;
	@Logger
	private Log log;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Factory("strLapPba")
	public void init() {
		//phieubaoan = new PhieuBaoAn();
		//phieubaoan.setKhoaMaso(new DmKhoa());
		ngayhientai = sdf.format(new Date());
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 1);
		ngayan = sdf.format(cal.getTime());
		reset(false);	
		listPbaCda = new ArrayList<PhieuBaoAnCheDoAn>();
		count = 0;	
		updatePba = false;		
		strLapPba = "";
		strIDRemove = "";
		ngayLayPbaOld = "";
	}
	public void reset(boolean isSearchPba) {				
		pbacda = new PhieuBaoAnCheDoAn();
		BenhNhanPhieuBaoAn bnphieubaoan = new BenhNhanPhieuBaoAn();
		bnphieubaoan.setBnpbaDangsua(new Short("0"));  // set default la dang sua pha
		bnphieubaoan.setBnpbaSoluong(new Integer("2500")); // set defaul la so luong(ml) cua 1 hop sua non thang
		
		Hsba hsBenhan = new Hsba();
		hsBenhan.setBenhnhanMa(new BenhNhan());
		
		bnphieubaoan.setHsbaSovaovien(hsBenhan);		
		pbacda.setBnphieubaoan(bnphieubaoan);				
		sohsbenhan = "";
		doituonganMaso = loaian1Maso = loaian2Maso = mucanMaso = dongthemMaso = "";
		if (! isSearchPba) khoaMaso = "";
		cdaLat = cdaDd = cdaStm =  cdaCmo = cdaBduong = false;
		cdaThuong = true;
		phutroi = false;
		gioan6 = gioan11 = gioan16 = gioan20 = false;
		gioitinh = "";
		phong = "";
		lockGhinhanBtn = false;
		lockInphieuBtn =  true;
		daRaVien = false;
		selectedIndex = -1;
		
	}
	
	public String initFromMenu(String fromMenu)
	{
		//log.info("begin initFromMenu");
		//resetValueFromMenu();
		//hienThiInPhieu=false;
		//log.info("end initFromMenu");
		return "VienPhiTaiKhoa_LapPhieuBaoAnHangNgay";
	}
	public void searchPhieubaoan(int layPbaTruoc, boolean showMsg) {
		// layPbaTruoc = 1 : Tim phieu bao an moi nhat (co ngay an nho hon hoac bang ngay chon tren giao dien) cua khoa duoc chon
		// layPbaTruoc = 0 : Tim phieu bao an co ngay an bang ngay duoc chon tren giao dien cua khoa duoc chon
		// showMsg = false : Khong hien thi thong bao co tim thay hay khong
		// showMsg = true : Hien thi thong bao co tim thay hay khong
		log.info("--enter searchPhieubaoan()---,layPbaTruoc = " + layPbaTruoc);
		reset(true);
		try {
			// Lay danh muc khoa
			DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
			DmKhoa dmKhoa = (DmKhoa) dtUtil.findByMaSo(new Integer(khoaMaso), "DmKhoa", "dmkhoaMaso");
			PhieuBaoAnDelegate pbaDel = PhieuBaoAnDelegate.getInstance();
			PhieuBaoAn pbaTmp = pbaDel.findByKhoaNgayAn(dmKhoa.getDmkhoaMa(), sdf.parse(ngayan));
			if (layPbaTruoc == 1) {
				pbaTmp = pbaDel.findByKhoaNgayAn2(dmKhoa.getDmkhoaMa(), sdf.parse(ngayan));
			}
			listPbaCda.clear();
			if (pbaTmp != null) {
				System.out.println("ngay an search = " + pbaTmp.getPhieubaoanNgayan() + ", equal = " + sdf.format(pbaTmp.getPhieubaoanNgayan()).equals(ngayan));
				if (sdf.format(pbaTmp.getPhieubaoanNgayan()).equals(ngayan)) {
					updatePba = true;
					ngayLayPbaOld = "";
				} else {
					updatePba = false;
					ngayLayPbaOld = sdf.format(pbaTmp.getPhieubaoanNgayan());
				}
				BenhNhanPhieuBaoAnDelegate bnPbaDel = BenhNhanPhieuBaoAnDelegate.getInstance();
				List<BenhNhanPhieuBaoAn> listBnPba = bnPbaDel.findByPbaMaso(pbaTmp.getPhieubaoanMaso());				
				listPbaCda = new ArrayList<PhieuBaoAnCheDoAn>(listBnPba.size());
				BenhNhanCheDoAnDelegate bncdaDel = BenhNhanCheDoAnDelegate.getInstance();
				BenhNhanGioAnDelegate bngaDel = BenhNhanGioAnDelegate.getInstance();
				for(BenhNhanPhieuBaoAn eachBnPba : listBnPba) {
					if(eachBnPba.getHsbaSovaovien().getHsbaNgaygiorav() == null) {
						PhieuBaoAnCheDoAn pbacdaTmp = new PhieuBaoAnCheDoAn();
						pbacdaTmp.setBnphieubaoan(eachBnPba);
						pbacdaTmp.setListBnCda(bncdaDel.findByBnPbaMaso(eachBnPba.getBnpbaMaso()));
						pbacdaTmp.setListBnGa(bngaDel.findByBnPbaMaso(eachBnPba.getBnpbaMaso()));
						listPbaCda.add(pbacdaTmp);
					}
				}
				if (listPbaCda.size() < 1) {
					lockGhinhanBtn = false;
					lockInphieuBtn =  true;
					ngayLayPbaOld = "";
					if (showMsg) {
						// Hien thi thong bao khong tim thay
						FacesMessages.instance().add(IConstantsRes.no_found);
					}
				} else {
					lockGhinhanBtn = false;
					lockInphieuBtn =  (!ngayLayPbaOld.equals(""));
					FacesMessages.instance().clear();
				}
			} else {
				updatePba = false;
				lockGhinhanBtn = false;
				lockInphieuBtn =  true;
				ngayLayPbaOld = "";
				if (showMsg) {
					// Hien thi thong bao khong tim thay
					FacesMessages.instance().add(IConstantsRes.no_found);
				}
			}
			count = listPbaCda.size();
			
			strIDRemove = "";
		} catch (Exception e) {
			e.printStackTrace();
		}		
		log.info("--exit searchPhieubaoan()---");
	}
	
	public void searchBenhnhan() throws Exception
	{
		try
		{
			log.info("--enter searchBenhnhan(), sohsbenhan = " + sohsbenhan);
			if (sohsbenhan.trim().length() < 1) {
				FacesMessages.instance().clear();
				return; 
			}
			
			// Lay danh muc khoa
			DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
			DmKhoa dmKhoa = (DmKhoa) dtUtil.findByMaSo(new Integer(khoaMaso), "DmKhoa", "dmkhoaMaso");
			if (dmKhoa == null) {
				sohsbenhan = "";
				FacesMessages.instance().add(IConstantsRes.KHOA_HSBA_NULL, "", "");
				reset(false);
				return;
			}
			
			HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
			Hsba hoSoBenhAn = hsbaDelegate.findByHsbaAndKhoaDangDieuTri(sohsbenhan, dmKhoa.getDmkhoaMa());
			if (hoSoBenhAn == null) {
				sohsbenhan = "";
				FacesMessages.instance().add(IConstantsRes.KHOA_HSBA_NULL, "", "");
				reset(false);
				return;
			}
			
			// Kiem tra benh nhan da ra vien hay chua			
			if (hoSoBenhAn.getHsbaNgaygiorav() != null) {
				daRaVien = true;
				FacesMessages.instance().add(IConstantsRes.BENH_NHAN_DA_RA_VIEN);								
			} else {
				daRaVien = false;
			}
			
			pbacda.getBnphieubaoan().setHsbaSovaovien(hoSoBenhAn);
			gioitinh = hoSoBenhAn.getBenhnhanMa(true).getDmgtMaso(true).getDmgtMa();
			
			// Lay thong tin phong (trong bang hsba_chuyen_mon)
			HsbaChuyenMon hsbaCm = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_MaKhoa(hoSoBenhAn.getHsbaSovaovien(), dmKhoa.getDmkhoaMa());
			if(hsbaCm != null) {
				phong = hsbaCm.getHsbacmSobuong();
			}
			
		} catch (Exception e) {
			System.out.println("error LapPhieuBaoAn searchBenhnhan" + e);
		}
		log.info("--end searchBenhnhan()---");
	}
	
	// Ham chuyen chi tiet nhap xuong duoi
	public void enter() throws Exception
	{
		log.info("*****Begin Enter(), selectedIndex = " + selectedIndex);
		
		// Set Dmuc doi tuong an 		
		if (doituonganMaso.trim().length() > 0) {
			pbacda.getBnphieubaoan().setDtdmdtaMaso(DtDmDoiTuongAnDelegate.getInstance().find(new Integer(doituonganMaso)));
		} else {
			pbacda.getBnphieubaoan().setDtdmdtaMaso(null);
		}
		// Set Dmuc loai an 1		
		if (loaian1Maso.trim().length() > 0) {
			pbacda.getBnphieubaoan().setDtdmlaMaso(DtDmLoaiAnDelegate.getInstance().find(new Integer(loaian1Maso)));
		} else {
			pbacda.getBnphieubaoan().setDtdmlaMaso(null);
		}
		// Set Dmuc loai an 2		
		if (loaian2Maso.trim().length() > 0) {
			pbacda.getBnphieubaoan().setDtdmla2Maso(DtDmLoaiAn2Delegate.getInstance().find(new Integer(loaian2Maso)));
		} else {
			pbacda.getBnphieubaoan().setDtdmla2Maso(null);
		}
		if (! doituonganMaso.trim().equals("4")) {
			// Set Dmuc muc an 		
			if (mucanMaso.trim().length() > 0) {
				pbacda.getBnphieubaoan().setDtdmmaMaso(DtDmMucAnDelegate.getInstance().find(new Integer(mucanMaso)));
			} else {
				pbacda.getBnphieubaoan().setDtdmmaMaso(null);
			}
		} else {
			pbacda.getBnphieubaoan().setDtdmmaMaso(null);
		}
		// Set Dmuc dong them		
		if (dongthemMaso.trim().length() > 0) {
			pbacda.getBnphieubaoan().setDtdmdtMaso(DtDmDongThemDelegate.getInstance().find(new Integer(dongthemMaso)));
		} else {
			pbacda.getBnphieubaoan().setDtdmdtMaso(null);
		}
		
		pbacda.getBnphieubaoan().setBnpbaPhutroi(phutroi ? new Short("1") : new Short("0"));
		
		// Thiet lap danh sach che do an
		pbacda.setListBnCda(new ArrayList<BenhNhanCheDoAn>()) ;
		
		DtDmCheDoAnDelegate cdaDel = DtDmCheDoAnDelegate.getInstance();
		if (cdaLat) {
			BenhNhanCheDoAn bnCda = new BenhNhanCheDoAn();
			bnCda.setDtdmcdaMaso(cdaDel.find(new Integer(CHE_DO_AN_LAT)));
			pbacda.getListBnCda().add(bnCda);
		}
		if (cdaDd) {
			BenhNhanCheDoAn bnCda = new BenhNhanCheDoAn();
			bnCda.setDtdmcdaMaso(cdaDel.find(new Integer(CHE_DO_AN_DD)));
			pbacda.getListBnCda().add(bnCda);
		}
		if (cdaStm) {
			BenhNhanCheDoAn bnCda = new BenhNhanCheDoAn();
			bnCda.setDtdmcdaMaso(cdaDel.find(new Integer(CHE_DO_AN_STM)));
			pbacda.getListBnCda().add(bnCda);
		}
		if (cdaThuong) {
			BenhNhanCheDoAn bnCda = new BenhNhanCheDoAn();
			bnCda.setDtdmcdaMaso(cdaDel.find(new Integer(CHE_DO_AN_THUONG)));
			pbacda.getListBnCda().add(bnCda);
		}
		if (cdaCmo) {
			BenhNhanCheDoAn bnCda = new BenhNhanCheDoAn();
			bnCda.setDtdmcdaMaso(cdaDel.find(new Integer(CHE_DO_AN_CMO)));
			pbacda.getListBnCda().add(bnCda);
		}
		if (cdaBduong) {
			BenhNhanCheDoAn bnCda = new BenhNhanCheDoAn();
			bnCda.setDtdmcdaMaso(cdaDel.find(new Integer(CHE_DO_AN_BDUONG)));
			pbacda.getListBnCda().add(bnCda);
		}
		
		// Thiet lap danh sach gio an
		pbacda.setListBnGa(new ArrayList<BenhNhanGioAn>()) ;
		DtDmGioAnDelegate gaDel = DtDmGioAnDelegate.getInstance();
		if (gioan6) {
			BenhNhanGioAn bnGa = new BenhNhanGioAn();
			bnGa.setDtdmgaMaso(gaDel.find(new Integer(GIO_AN_6H)));
			pbacda.getListBnGa().add(bnGa);
		}
		if (gioan11) {
			BenhNhanGioAn bnGa = new BenhNhanGioAn();
			bnGa.setDtdmgaMaso(gaDel.find(new Integer(GIO_AN_11H)));
			pbacda.getListBnGa().add(bnGa);
		}
		if (gioan16) {
			BenhNhanGioAn bnGa = new BenhNhanGioAn();
			bnGa.setDtdmgaMaso(gaDel.find(new Integer(GIO_AN_16H)));
			pbacda.getListBnGa().add(bnGa);
		}
		if (gioan20) {
			BenhNhanGioAn bnGa = new BenhNhanGioAn();
			bnGa.setDtdmgaMaso(gaDel.find(new Integer(GIO_AN_20H)));
			pbacda.getListBnGa().add(bnGa);
		}
		
		if (selectedIndex < 0) {
			listPbaCda.add(pbacda);
		} else {
			listPbaCda.set(selectedIndex, pbacda);
		}
		
		count = listPbaCda.size();
		reset(false);
		log.info("*****End Enter(),listPbaCda size = " + listPbaCda.size());
	}
	public String showMucan(PhieuBaoAnCheDoAn phieu) {
		//log.info("-- begin showMucan(), phieu.getBnphieubaoan() = " + phieu.getBnphieubaoan());		
		String mucan = "";
		if (phieu.getBnphieubaoan().getDtdmmaMaso() != null ) {
			mucan = phieu.getBnphieubaoan().getDtdmmaMaso().getDtdmmaTen();			
			mucan = mucan.replaceAll("[.]", "");			
		}
		String dongthem = (phieu.getBnphieubaoan().getDtdmdtMaso() != null ? phieu.getBnphieubaoan().getDtdmdtMaso().getDtdmdtTen() : "");
		String numDongthem = dongthem.replaceAll("[.]", "");
		//log.info("-- dongthem = " + dongthem);
		int imucan = 0;
		if (mucan.trim().length() > 0) {
			imucan = (new Integer(mucan)).intValue();
		}		
		int idongthem = 0;
		if (dongthem.trim().length() > 0) {
			idongthem = (new Integer(numDongthem)).intValue();
		}
		imucan += idongthem;
		mucan = "" + imucan;
		if (mucan.length() > 0) {			
			String reverse = new StringBuffer(mucan).reverse().toString();			
			mucan = new StringBuffer(insertPeriodically(reverse, ".", 3)).reverse().toString();			
		}
		
		return ((imucan > 0 ? "" + mucan : "") + (idongthem > 0 ? " (" + dongthem + ")" : ""));
		//return "";
	}
	
	public String showChedoan(PhieuBaoAnCheDoAn phieu) {
		String strReturn = "";
		for(BenhNhanCheDoAn each : phieu.getListBnCda()) {
			strReturn += (strReturn.trim().length() > 0 ? ", " + each.getDtdmcdaMaso().getDtdmcdaTen() : each.getDtdmcdaMaso().getDtdmcdaTen());
		}		
		return strReturn;
	}
	
	public String showGioan(PhieuBaoAnCheDoAn phieu) {
		String strReturn = "";
		for(BenhNhanGioAn each : phieu.getListBnGa()) {
			strReturn += (strReturn.trim().length() > 0 ? ", " + each.getDtdmgaMaso().getDtdmgaTen() : each.getDtdmgaMaso().getDtdmgaTen());
		}		
		return strReturn;
	}
	
	private String insertPeriodically(
		    String text, String insert, int period)
		{
		    StringBuilder builder = new StringBuilder(
		         text.length() + insert.length() * (text.length()/period)+1);

		    int index = 0;
		    String prefix = "";
		    while (index < text.length())
		    {
		        // Don't put the insert in the very first iteration.
		        // This is easier than appending it *after* each substring
		        builder.append(prefix);
		        prefix = insert;
		        builder.append(text.substring(index, 
		            Math.min(index + period, text.length())));
		        index += period;
		    }
		    return builder.toString();
		}
	public void edit(int index) {
		
		pbacda = listPbaCda.get(index);
		sohsbenhan = pbacda.getBnphieubaoan().getHsbaSovaovien().getHsbaSovaovien();
		doituonganMaso = pbacda.getBnphieubaoan().getDtdmdtaMaso().getDtdmdtaMaso().toString();
		loaian1Maso = pbacda.getBnphieubaoan().getDtdmlaMaso().getDtdmlaMaso().toString();
		loaian2Maso = "";
		if (pbacda.getBnphieubaoan().getDtdmla2Maso() != null) {
			loaian2Maso = pbacda.getBnphieubaoan().getDtdmla2Maso().getDtdmla2Maso().toString();
		}
		// Lay thong tin che do an			
		cdaLat = cdaDd = cdaStm =  cdaCmo = cdaBduong = cdaThuong = false;
				
		for (BenhNhanCheDoAn cda : pbacda.getListBnCda()) {
			if(cda.getDtdmcdaMaso().getDtdmcdaMaso().equals(new Integer(CHE_DO_AN_LAT))) {
				cdaLat = true;
			} else if(cda.getDtdmcdaMaso().getDtdmcdaMaso().equals(new Integer(CHE_DO_AN_DD))) {
				cdaDd = true;
			} else if(cda.getDtdmcdaMaso().getDtdmcdaMaso().equals(new Integer(CHE_DO_AN_STM))) {
				cdaStm = true;
			} else if(cda.getDtdmcdaMaso().getDtdmcdaMaso().equals(new Integer(CHE_DO_AN_CMO))) {
				cdaCmo = true;
			} else if(cda.getDtdmcdaMaso().getDtdmcdaMaso().equals(new Integer(CHE_DO_AN_BDUONG))) {
				cdaBduong = true;
			} else if(cda.getDtdmcdaMaso().getDtdmcdaMaso().equals(new Integer(CHE_DO_AN_THUONG))) {
				cdaThuong = true;
			}
		}
		// Lay thong tin gio an			
		gioan6 = gioan11 = gioan16 =  gioan20 = false;
				
		for (BenhNhanGioAn ga : pbacda.getListBnGa()) {
			if(ga.getDtdmgaMaso().getDtdmgaMaso().equals(new Integer(GIO_AN_6H))) {
				gioan6 = true;
			} else if(ga.getDtdmgaMaso().getDtdmgaMaso().equals(new Integer(GIO_AN_11H))) {
				gioan11 = true;
			} else if(ga.getDtdmgaMaso().getDtdmgaMaso().equals(new Integer(GIO_AN_16H))) {
				gioan16 = true;
			} else if(ga.getDtdmgaMaso().getDtdmgaMaso().equals(new Integer(GIO_AN_20H))) {
				gioan20 = true;
			}
		}
		mucanMaso = (pbacda.getBnphieubaoan().getDtdmmaMaso() == null ? "" : pbacda.getBnphieubaoan().getDtdmmaMaso().getDtdmmaMaso().toString());
		dongthemMaso = "";
		if (pbacda.getBnphieubaoan().getDtdmdtMaso() != null) {
			dongthemMaso = pbacda.getBnphieubaoan().getDtdmdtMaso().getDtdmdtMaso().toString();
		}		
		phutroi = (pbacda.getBnphieubaoan().getBnpbaPhutroi().intValue() == 1);
		gioitinh = pbacda.getBnphieubaoan().getHsbaSovaovien().getBenhnhanMa().getDmgtMaso().getDmgtMa();
		HsbaChuyenMon hsbaCm = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_LastHSBACM(sohsbenhan);
		if(hsbaCm != null) {
			phong = hsbaCm.getHsbacmSobuong();
		}
		selectedIndex = index;
		
	}
	// Ham delete chi tiet
	public void delete(int index) throws Exception
	{
		log.info("*****Begin delete() *****");
		strIDRemove = (strIDRemove.trim().length() > 0 ? "," + listPbaCda.get(index).getBnphieubaoan().getBnpbaMaso() : "" + listPbaCda.get(index).getBnphieubaoan().getBnpbaMaso());
		listPbaCda.remove(index);
		count = listPbaCda.size();
		
		reset(false);
		log.info("*****End delete() *****");
	}
	
	private void luuBnPhieuBaoAn(PhieuBaoAn pba, boolean isUpdate) {
		for (PhieuBaoAnCheDoAn eachPbaCda : listPbaCda) {
			BenhNhanPhieuBaoAn bnPba = eachPbaCda.getBnphieubaoan();
			if (! isUpdate) bnPba.setBnpbaMaso(null); // Set ma so thanh null de luu moi
			bnPba.setPhieubaoanMaso(pba);			
			BenhNhanPhieuBaoAnDelegate.getInstance().myCreate(bnPba, eachPbaCda.getListBnCda(), eachPbaCda.getListBnGa(), isUpdate);
		}
	}
	
	// Ham ghi nhan
	public String ghinhan() throws Exception
	{
		log.info("*****Begin Ghi nhan() *****, updatePba = " + updatePba);
		log.info("*****so phan tu insert *****" + listPbaCda.size());

		if (listPbaCda == null || listPbaCda.size() == 0)
		{
			listPbaCda = new ArrayList<PhieuBaoAnCheDoAn>();
			FacesMessages.instance().add(IConstantsRes.PBA_EMPTY, "", "");
			//hienThiInPhieu=false;
			return "";
		}

		try
		{
			// Kiem tra ngay an duoc chon da co phieu bao an chua (theo khoa)
			// Neu chua co thi luu moi phieu bao an
			// Neu da co thi kiem tra xem co phai nguoi dung muon cap nhat lai phieu bao an hay khong 
			// (khi nguoi dung muon cap nhat phieu bao an, se nhap khoa, ngay an va tim kiem
			// dua vao ID cua phieu bao an de xac dinh cap nhat hay them moi)
			
			// Lay danh muc khoa
			DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
			DmKhoa dmKhoa = (DmKhoa) dtUtil.findByMaSo(new Integer(khoaMaso), "DmKhoa", "dmkhoaMaso");
			// Kiem tra ngay an duoc chon da co phieu bao an chua
			PhieuBaoAnDelegate pbaDel = PhieuBaoAnDelegate.getInstance();
			PhieuBaoAn pbaTmp = pbaDel.findByKhoaNgayAn(dmKhoa.getDmkhoaMa(), sdf.parse(ngayan));
			log.info("pbaTmp = " + pbaTmp);
			if (pbaTmp == null) {
				// Them moi phieu bao an
				pbaTmp = new PhieuBaoAn();
				pbaTmp.setKhoaMaso(dmKhoa);
				pbaTmp.setPhieubaoanNgayan(sdf.parse(ngayan));
				pbaTmp = PhieuBaoAnDelegate.getInstance().myCreate(pbaTmp, false);
				// Luu thong tin benh nhan theo phieu bao an
				luuBnPhieuBaoAn(pbaTmp, false);
				lockGhinhanBtn = true;
				lockInphieuBtn =  false;
				FacesMessages.instance().add(IConstantsRes.INSERT_SUCCESS);
			} else {
				// Kiem tra gia tri bien updatePba
				// Neu gia tri bien nay la false thi khong luu du lieu, hien thi thong bao trung du lieu
				// Neu gia tri cua bien la true, cap nhat lai thong tin phieu bao an
				// Gia tri bien updatePba duoc set trong ham searchPhieubaoan(), khi nguoi dung thuc hien tim kiem phieu bao an
				if (updatePba) {
					if (strIDRemove.trim().length() > 0) {
						// Xoa danh sach benh nhan
						int i =  BenhNhanPhieuBaoAnDelegate.getInstance().removeByPbaMaso(pbaTmp.getPhieubaoanMaso());
						log.info("Xoa " + i + " benh nhan khoi phieu bao an");
						luuBnPhieuBaoAn(pbaTmp, false);
					} else {
						luuBnPhieuBaoAn(pbaTmp, true);
					}
					lockGhinhanBtn = true;
					lockInphieuBtn =  false;
					FacesMessages.instance().add(IConstantsRes.UPDATE_SUCCESS);
				} else {
					lockGhinhanBtn = false;
					lockInphieuBtn =  true;
					// Hien thi thong bao trung du lieu
					FacesMessages.instance().add(IConstantsRes.PBA_EXISTS, ngayan);
				}
			}
			
			

		} catch (Exception e)
		{
			if (updatePba) {
				FacesMessages.instance().add(IConstantsRes.UPDATE_FAIL);
			} else {
				FacesMessages.instance().add(IConstantsRes.INSERT_FAIL);
			}
			
			//resultHidden = "fail";
			// TODO: handle exception
			e.printStackTrace();
			log.error("*************loi***********" + e.toString());
		}
		ngayLayPbaOld = "";
		log.info("*****End Ghi nhan() *****");
		return null;
	}
	public String thuchienAction()
	{
		XuatReport();
		return "B3360_Chonmenuxuattaptin";
	}
	/**
	 * xuat report
	 */
	public void XuatReport()
	{
		reportTypeVP = "lapphieubaoanhangngay_form";
		log.info("Vao Method XuatReport phieu bao an ngay");
		// Lay danh muc khoa
		DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
		DmKhoa dmKhoa = (DmKhoa) dtUtil.findByMaSo(new Integer(khoaMaso), "DmKhoa", "dmkhoaMaso");
		PhieuBaoAnDelegate pbaDel = PhieuBaoAnDelegate.getInstance();
		try {
		PhieuBaoAn pbaTmp = pbaDel.findByKhoaNgayAn(dmKhoa.getDmkhoaMa(), sdf.parse(ngayan));
		//if (pbaTmp != null) {
			String baocao1 = null;
			
				log.info("bat dau method XuatReport ");
				String pathTemplate = IConstantsRes.PATH_BASE
						+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
						+ "vienphi/B3140_Phieubaoan_form.jrxml";
			
	
				JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
			
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("khoa", pbaTmp != null ? pbaTmp.getKhoaMaso().getDmkhoaTen() : "");
		        params.put("khoamaso",pbaTmp != null ? pbaTmp.getKhoaMaso().getDmkhoaMaso() : new Integer(0));
				params.put("ngayan", pbaTmp != null ? pbaTmp.getPhieubaoanNgayan() : sdf.parse(ngayan));				
				
				log.info("================= ");
				Class.forName("oracle.jdbc.OracleDriver");
				    log.info("da thay driver mysql");
				    Connection conn = null;
				    try{
				    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
				    }catch(Exception e){
				    	log.info(e.getMessage());
				    }
				    jasperPrintVP =  JasperFillManager.fillReport(jasperReport,params, conn);
				    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,new Long(new Date().getTime()).intValue(),IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","Xeminlientucphieucongkhai");
				    reportPathVP=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
				    log.info("duong dan file xuat report :" + baocao1);
				    log.info("duong dan -------------------- :"+reportPathVP);
				    //index+= 1;
				    //log.info("Index :" + getIndex());
				    if(conn != null) conn.close();
			
			//} 
		} catch (Exception e){
			log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
		log.info("Thoat Method XuatReport");
	}
	// End Phuc add
	
	public String getSohsbenhan() {
		return sohsbenhan;
	}
	public void setSohsbenhan(String sohsbenhan) {
		this.sohsbenhan = sohsbenhan;
	}
	public boolean isCdaLat() {
		return cdaLat;
	}
	public void setCdaLat(boolean cdaLat) {
		this.cdaLat = cdaLat;
	}
	public boolean isCdaDd() {
		return cdaDd;
	}
	public void setCdaDd(boolean cdaDd) {
		this.cdaDd = cdaDd;
	}
	public boolean isCdaStm() {
		return cdaStm;
	}
	public void setCdaStm(boolean cdaStm) {
		this.cdaStm = cdaStm;
	}
	public boolean isCdaThuong() {
		return cdaThuong;
	}
	public void setCdaThuong(boolean cdaThuong) {
		this.cdaThuong = cdaThuong;
	}
	public boolean isCdaCmo() {
		return cdaCmo;
	}
	public void setCdaCmo(boolean cdaCmo) {
		this.cdaCmo = cdaCmo;
	}
	public boolean isCdaBduong() {
		return cdaBduong;
	}
	public void setCdaBduong(boolean cdaBduong) {
		this.cdaBduong = cdaBduong;
	}
	public boolean isPhutroi() {
		return phutroi;
	}
	public void setPhutroi(boolean phutroi) {
		this.phutroi = phutroi;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getSelectedIndex() {
		return selectedIndex;
	}
	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}
	public String getGioitinh() {
		return gioitinh;
	}
	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}	
	public String getNgayan() {
		return ngayan;
	}
	public void setNgayan(String ngayan) {
		this.ngayan = ngayan;
	}
	public String getNgayhientai() {
		return ngayhientai;
	}
	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
	}
	public String getPhong() {
		return phong;
	}
	public void setPhong(String phong) {
		this.phong = phong;
	}
	public PhieuBaoAnCheDoAn getPbacda() {
		return pbacda;
	}
	public void setPbacda(PhieuBaoAnCheDoAn pbacda) {
		this.pbacda = pbacda;
	}
	public List<PhieuBaoAnCheDoAn> getListPbaCda() {
		return listPbaCda;
	}
	public void setListPbaCda(List<PhieuBaoAnCheDoAn> listPbaCda) {
		this.listPbaCda = listPbaCda;
	}
	
	public String getDoituonganMaso() {
		return doituonganMaso;
	}
	public void setDoituonganMaso(String doituonganMaso) {
		this.doituonganMaso = doituonganMaso;
	}
	public String getLoaian1Maso() {
		return loaian1Maso;
	}
	public void setLoaian1Maso(String loaian1Maso) {
		this.loaian1Maso = loaian1Maso;
	}
	public String getLoaian2Maso() {
		return loaian2Maso;
	}
	public void setLoaian2Maso(String loaian2Maso) {
		this.loaian2Maso = loaian2Maso;
	}
	public String getMucanMaso() {
		return mucanMaso;
	}
	public void setMucanMaso(String mucanMaso) {
		this.mucanMaso = mucanMaso;
	}
	public String getDongthemMaso() {
		return dongthemMaso;
	}
	public void setDongthemMaso(String dongthemMaso) {
		this.dongthemMaso = dongthemMaso;
	}
	public String getKhoaMaso() {
		return khoaMaso;
	}
	public void setKhoaMaso(String khoaMaso) {
		this.khoaMaso = khoaMaso;
	}
	public boolean isGioan6() {
		return gioan6;
	}
	public void setGioan6(boolean gioan6) {
		this.gioan6 = gioan6;
	}
	public boolean isGioan11() {
		return gioan11;
	}
	public void setGioan11(boolean gioan11) {
		this.gioan11 = gioan11;
	}
	public boolean isGioan16() {
		return gioan16;
	}
	public void setGioan16(boolean gioan16) {
		this.gioan16 = gioan16;
	}
	public boolean isGioan20() {
		return gioan20;
	}
	public void setGioan20(boolean gioan20) {
		this.gioan20 = gioan20;
	}
	public boolean isLockGhinhanBtn() {
		return lockGhinhanBtn;
	}
	public void setLockGhinhanBtn(boolean lockGhinhanBtn) {
		this.lockGhinhanBtn = lockGhinhanBtn;
	}
	public boolean isLockInphieuBtn() {
		return lockInphieuBtn;
	}
	public void setLockInphieuBtn(boolean lockInphieuBtn) {
		this.lockInphieuBtn = lockInphieuBtn;
	}
	public String getReportTypeVP(){
		return reportTypeVP;
	}
	public void setReportTypeVP(String reportTypeVP){
		this.reportTypeVP = reportTypeVP;
	}
	
	public String getNgayLayPbaOld() {
		return ngayLayPbaOld;
	}
	public void setNgayLayPbaOld(String ngayLayPbaOld) {
		this.ngayLayPbaOld = ngayLayPbaOld;
	}
	
	public boolean isDaRaVien() {
		return daRaVien;
	}
	public void setDaRaVien(boolean daRaVien) {
		this.daRaVien = daRaVien;
	}

	public class PhieuBaoAnCheDoAn implements Serializable {
		private static final long serialVersionUID = 10L;
		private BenhNhanPhieuBaoAn bnphieubaoan;
		private List<BenhNhanCheDoAn> listBnCda;
		private List<BenhNhanGioAn> listBnGa;
		public List<BenhNhanGioAn> getListBnGa() {
			return listBnGa;
		}
		public void setListBnGa(List<BenhNhanGioAn> listBnGa) {
			this.listBnGa = listBnGa;
		}
		public BenhNhanPhieuBaoAn getBnphieubaoan() {
			return bnphieubaoan;
		}
		public void setBnphieubaoan(BenhNhanPhieuBaoAn bnphieubaoan) {
			this.bnphieubaoan = bnphieubaoan;
		}
		public List<BenhNhanCheDoAn> getListBnCda() {
			return listBnCda;
		}
		public void setListBnCda(List<BenhNhanCheDoAn> listBnCda) {
			this.listBnCda = listBnCda;
		}
		
	}
}

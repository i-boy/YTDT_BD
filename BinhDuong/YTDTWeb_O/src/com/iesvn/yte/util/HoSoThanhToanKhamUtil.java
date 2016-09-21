package com.iesvn.yte.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmMqlBhytDelegate;
import com.iesvn.yte.dieutri.delegate.HoanUngKhamDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.MienGiamDelegate;
import com.iesvn.yte.dieutri.delegate.TamUngKhamDelegate;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocDongYNgoaiTruDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
import com.iesvn.yte.dieutri.entity.DtDmMqlBhyt;
import com.iesvn.yte.dieutri.entity.HsThtoank;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.MienGiam;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.ThuocDongYNgoaiTru;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
import com.iesvn.yte.dieutri.entity.TiepDon;

public class HoSoThanhToanKhamUtil {
	
	private static Logger log = Logger.getLogger(HoSoThanhToanKhamUtil.class);
	
	// Phan danh cho tinh toan
//	public int permiengiam = 0;
	public Double miengiam = new Double(0);
	public Double thatthu = new Double(0);
	public int perbhytchi = 0;
	public Double bhytchi = new Double(0);
	public Double thanhtien1 = new Double(0);
	public int perbntra = 0;
	public Double bntra = new Double(0);
	public TiepDon tiepDon;
	
	public Double DaThanhToan = new Double(0);
	
//	public double tongChiPhi = 0;
	
	
	public Double mienTE = new Double(0);
	
	
	//
	public Double khongThu = new Double(0);
	
	// Thong tin can thiet cho ho so thanh toan kham
	Double thuocTrongDM =  new Double(0);
	Double thuocNDM = new Double(0);
	Double vTTHTrongDM = new Double(0);
	Double vTTHNDM = new Double(0);
	Double cLSMauTrongDM = new Double(0);
	Double clsMauNDM = new Double(0);
	
	Double tienCongKham = new Double(0);
	Double chanDoanHinhAnh = new Double(0);
	Double dichVuKTThongThuong = new Double(0);
	Double dichVuKTC = new Double(0);
	Double chiPhiVC = new Double(0);
	Double xetNghiemTDCN = new Double(0);
	
	Double dichTruyen = new Double(0);
	
	
//	Double pTTTTrongDM = new Double(0);
//	Double pTTTNDM = new Double(0);
	
	Double cLSPhongTrongDM = new Double(0);
	Double clsPhongNDM = new Double(0);
	
	
	Double clsNDM = new Double(0);
	Double clsTrongDM = new Double(0);
	// phuc.lc : 12-12-2010 BEGIN
	Double chiphiPT = new Double(0);
	Double chiphiTT = new Double(0);
	// END
	Double ung = new Double(0);
	Double tra = new Double(0);
	Double soDu = new Double(0); // = bntra - ung + tra	
	
	//
	
	public double minktc = 0;
	public double maxktc = 0;
	public int tylebhyt = 0;
	public int tylebhyt2 = 0;
	public double tyleminktc = 0; // ty le min ktc
	public double tylemaxktc = 0; // ty le max ktc
	public int tyleKTC = 0;
	public double gioiHanTyLe = 0;
	DtDmKhoiBhyt khoiBHYT = null;
	// phuc.lc  27-09-2010	
	private double tongChiPhiCLSBhTra = 0;
	private double tongChiPhiCLSBnTra = 0;
	//private double tongTienThuoc = 0;
	private double tongChiPhiVCBhTra = 0;
	private double tongChiPhiVCBnTra = 0;
	private double tongChiPhiKTCBhTra = 0;
	private double tongChiPhiKTCBnTra = 0;
	private double tongChiPhiThuocBhTra = 0;
	private double tongChiPhiThuocBnTra = 0;
	private boolean bDoituongUutien = false;
	private boolean bVuottuyen = false;
	private double tongMienGiam = 0;	
	// Cap cuu
	private double tongChiPhiCLSBhTra_CapCuu = 0;	
	private double tongChiPhiVCBhTra_CapCuu = 0;	
	private double tongChiPhiKTCBhTra_CapCuu = 0;
	private double tongChiPhiThuocBhTra_CapCuu = 0;	
	public int tylebhyt_CapCuu = 0;
	public int tylebhyt2_CapCuu = 0;
	private boolean bCapCuu = false;
	private double tongChiPhiThuocCLSBnTra_TyleBH = 0;
	private HsThtoank hosoTTK;
	
	private void reset (){
		
//		permiengiam = 0;
//		miengiam = new Double(0);
		thatthu = new Double(0);
		perbhytchi = 0;
		bhytchi = new Double(0);
		thanhtien1 = new Double(0);
		perbntra = 0;
		bntra = new Double(0);
		khongThu = new Double(0);
		
		mienTE = new Double(0);
//		DaThanhToan = new Double(0);		
	}
	
	private boolean checkDTUuTien(String maDT) {
		boolean dtUutien = false;
		String dsUuTien = IConstantsRes.DANH_SACH_KHOI_BHYT_KHAC_TUYEN_VUOT_TUYEN_KO_CAN_GIAY_CHUYEN_VIEN;
		if (dsUuTien != null && !dsUuTien.equals("")){
			StringTokenizer sTk = new StringTokenizer(dsUuTien,",");
			ArrayList<String> arrayDS = new ArrayList<String>();
			while (sTk.hasMoreTokens()){
				String khoi = sTk.nextToken();
				arrayDS.add(khoi);
			}
			
			if (arrayDS.contains(maDT)){
				dtUutien = true;
			}
		}
		return dtUutien;
	}
	public  HoSoThanhToanKhamUtil(TiepDon tiepDon){
		//log.info("----- HoSoThanhToanKhamUtil -----");
		this.tiepDon = tiepDon;
		khoiBHYT = tiepDon.getKhoibhytMa();
		if (khoiBHYT != null) {
			
			
			if (khoiBHYT.getDtdmkhoibhytMinKTC() != null) {
				minktc = khoiBHYT.getDtdmkhoibhytMinKTC();
			}
			if (khoiBHYT.getDtdmkhoibhytMaxKTC() != null) {
				maxktc = khoiBHYT.getDtdmkhoibhytMaxKTC();
			}
			if (khoiBHYT.getDtdmkhoibhytTylebhyt1() != null) {
				tylebhyt = khoiBHYT.getDtdmkhoibhytTylebhyt1();
			}
			//log.info("tylebhyt-----------------------"+tylebhyt);
			if (khoiBHYT.getDtdmkhoibhytTylebhyt2() != null) {
				tylebhyt2 = khoiBHYT.getDtdmkhoibhytTylebhyt2();
			}
			if (khoiBHYT.getDtdmkhoibhytTLMinKTC() != null) {
				tyleminktc = khoiBHYT.getDtdmkhoibhytTLMinKTC();
			}
			if (khoiBHYT.getDtdmkhoibhytTLMaxKTC() != null) {
				tylemaxktc = khoiBHYT.getDtdmkhoibhytTLMaxKTC();
			}
			if (khoiBHYT.getDtdmkhoibhytTylektc() != null) {
				tyleKTC = khoiBHYT.getDtdmkhoibhytTylektc();
			}
			if (khoiBHYT.getDtdmkhoibhytGioiHanTyLe() != null) {
				gioiHanTyLe = khoiBHYT.getDtdmkhoibhytGioiHanTyLe();
			}
			
			// phuc.lc 19-10-2010 : Khong set cac thong so theo khoi bao hiem nua, code duoi day set cac thong so theo ma quyen loi
			String strSotheBHYT = (tiepDon.getTiepdonSothebh() == null ?"" : tiepDon.getTiepdonSothebh());
			if (strSotheBHYT.trim().length() > 3) {
				String strMaQuyenloi = strSotheBHYT.substring(2, 3);
				DtDmMqlBhytDelegate maQLDel = DtDmMqlBhytDelegate.getInstance();
				DtDmMqlBhyt maQL = maQLDel.findByMa(strMaQuyenloi);
				if (maQL != null) {
					// Set lai cac gia tri theo  ma quyen loi
					minktc = (maQL.getDtdmmqlbhytMinktc() == null ? 0 : maQL.getDtdmmqlbhytMinktc());							
					maxktc = (maQL.getDtdmmqlbhytMaxktc() == null ? 0 : maQL.getDtdmmqlbhytMaxktc());								
					tylebhyt = (maQL.getDtdmmqlbhytTylebhyt1() == null ? 0 : maQL.getDtdmmqlbhytTylebhyt1());				
					tylebhyt2 = (maQL.getDtdmmqlbhytTylebhyt2() == null ? 0 : maQL.getDtdmmqlbhytTylebhyt2());				
					tyleminktc = (maQL.getDtdmmqlbhytTlminktc() == null ? 0 : maQL.getDtdmmqlbhytTlminktc());
					tylemaxktc = (maQL.getDtdmmqlbhytTlmaxktc() == null ? 0 : maQL.getDtdmmqlbhytTlmaxktc());
					tyleKTC = (maQL.getDtdmmqlbhytTylektc() == null ? 0 : maQL.getDtdmmqlbhytTylektc());;
					gioiHanTyLe = (maQL.getDtdmmqlbhytGhtyle() == null ? 0 : maQL.getDtdmmqlbhytGhtyle());
					//mienphiVC = (maQL.getDtdmmqlbhytVanchuyen() == null ? false : maQL.getDtdmmqlbhytVanchuyen());
					
				}
			}
			
			if("BH".equals(tiepDon.getDoituongMa(true).getDmdoituongMa())){				
				
				Short tuyen = tiepDon.getTiepdonTuyen();
				//log.info("---tuyen():"+tuyen);
				// xet tuoi
				
				Integer tuoi = tiepDon.getBenhnhanMa(true).getBenhnhanTuoi();	
				if (tuoi == null){
					tuoi = 0;
				}
				Short donvituoi = tiepDon.getBenhnhanMa(true).getBenhnhanDonvituoi();
				if (donvituoi == null){
					donvituoi = 0;
				}
				String tuoikhongxettuyen = IConstantsRes.TUOI_KHONG_XET_TUYEN;
				int iTuoiKoXetTuyen = 200;
				if (tuoikhongxettuyen != null && !tuoikhongxettuyen.equals("")){
					iTuoiKoXetTuyen = Integer.parseInt(tuoikhongxettuyen);
				}
				if (tuoi >= iTuoiKoXetTuyen && donvituoi == 1){					
					// phuc.lc 27-12-2010 : neu la doi tuong >= 85 tuoi va noi dang ky KCB la noi tinh thi duoc xem la doi tuong uu tien
					bDoituongUutien = false;
					if ( tuyen != null && (tuyen == 1 || tuyen == 2) ) {
						bDoituongUutien = true;
					}
				}else{
					//phuc.lc 27-12-2010 : Neu noi dang ky KCB khong phai la noi tinh thi khong duoc xem la doi tuong uu tien
					if ( tuyen != null && tuyen == 3 ) {
						bDoituongUutien = false;
					} else {
						bDoituongUutien = checkDTUuTien(khoiBHYT.getDtdmkhoibhytMa());
					}
				}	
					//log.info("bDoituongUutien = " + bDoituongUutien + ", tuyen = " + tuyen);
					
					if (!bDoituongUutien) { 
						// Neu khong phai doi tuong uu tien, thi xet co vuot tuyen hay khong
						if ( tuyen != null && (tuyen == 2 || tuyen ==3)){ // vuot tuyen
							// la doi tuong ko uu tien + ko co giay
							if (tiepDon.getTiepdonCoGiayGioiThieu() == null	|| ( tiepDon.getTiepdonCoGiayGioiThieu() != null && tiepDon.getTiepdonCoGiayGioiThieu() == false)){
								bVuottuyen = true;	
								tylebhyt = 50;  // ty le su dung khi chi phi vuot qua gioi han ty le bao hiem
								tylebhyt2 = 50; // ty le su dung khi chi phi khong vuot qua gioi han ty le bao hiem
								if (IConstantsRes.TYLE_BH_VUOT_TUYEN.trim().length() > 0 && IConstantsRes.TYLE_BH2_VUOT_TUYEN.trim().length() > 0) {
									try {
										tylebhyt = Integer.parseInt(IConstantsRes.TYLE_BH_VUOT_TUYEN);
										tylebhyt2 = Integer.parseInt(IConstantsRes.TYLE_BH2_VUOT_TUYEN);
									} catch(Exception ex) { 
										tylebhyt = 50;
										tylebhyt2 = 50;
									}
								}																
							}
							//log.info("tyle vuot tuyen = " + tylebhyt);
						}
					}
					
				//}
			}
		}else{
			khoiBHYT = new DtDmKhoiBhyt();
		}
		
		
		Date ngayTiepDon_tmp = tiepDon.getTiepdonNgaygio();
		if (ngayTiepDon_tmp == null){
			return;
		}
		ngayTiepDon = java.util.Calendar.getInstance();
		ngayTiepDon.setTime(ngayTiepDon_tmp);
		ngayTiepDon.set(Calendar.HOUR_OF_DAY, 0);
		ngayTiepDon.set(Calendar.MINUTE, 0);
		ngayTiepDon.set(Calendar.SECOND, 0);

		
		
		Date ngayTiepDon3_tmp = tiepDon.getTiepdonGiatri3();
		
		if (ngayTiepDon3_tmp != null){
			ngayTiepDon3 = java.util.Calendar.getInstance();
			ngayTiepDon3.setTime(ngayTiepDon3_tmp);

		}
		
		Date ngayTiepDon4_tmp = tiepDon.getTiepdonGiatri4();
		
		if (ngayTiepDon4_tmp != null){
			ngayTiepDon4 = java.util.Calendar.getInstance();
			ngayTiepDon4.setTime(ngayTiepDon4_tmp);

		}
		
	}
	java.util.Calendar ngayTiepDon = null;
	java.util.Calendar ngayTiepDon3 = null;
	java.util.Calendar ngayTiepDon4 = null;
		

	private void tinhTamUngKham(){
		TamUngKhamDelegate tamUngKham = TamUngKhamDelegate.getInstance();
		ung = tamUngKham.getTongTamUng(tiepDon.getTiepdonMa());
	}
	private void tinhHoanUngKham(){
		HoanUngKhamDelegate hoanUngKham = HoanUngKhamDelegate.getInstance();
		tra = hoanUngKham.getTongHoanUng(tiepDon.getTiepdonMa());
	}
	

	
	private List<ClsKham> listCtkq_temp = null; // add here to update clskham with column clskhamdongiabntra
	private List<ThuocPhongKham> listCtTPK_temp = null; // day la thuoc quay benh vien
	
	public Double getClsNDM() {
		return clsNDM;
	}

	public void setClsNDM(Double clsNDM) {
		this.clsNDM = clsNDM;
	}

	public Double getClsTrongDM() {
		return clsTrongDM;
	}

	public void setClsTrongDM(Double clsTrongDM) {
		this.clsTrongDM = clsTrongDM;
	}

	public List<ThuocPhongKham> getListCtTPK_temp() {
		return listCtTPK_temp;
	}

	public void setListCtTPK_temp(List<ThuocPhongKham> listCtTPK_temp) {
		this.listCtTPK_temp = listCtTPK_temp;
	}

	public List<ClsKham> getListCtkq_temp() {
		return listCtkq_temp;
	}

	public void setListCtkq_temp(List<ClsKham> listCtkq_temp) {
		this.listCtkq_temp = listCtkq_temp;
	}
	
	// Ham cap nhat dongiatt va thanhtien cho cac CLS kham
	// Ham nay dung de cap nhat cac du lieu cu (truoc ngay 01/10/2011)
	// Viec cap nhat cac thong tin nay se duoc thuc hien o chuc nang chi dinh CLS, khi do khong can su dung ham nay nua
	private List<ClsKham> capnhatDongiattCls(List<ClsKham> listClsKham ) {		
		if (listClsKham.size() > 0) {			
			int solan = 0;
			for(int i = 0; i < listClsKham.size(); i++) {
				if(listClsKham.get(i).getClskhamDongiatt() == 0 && listClsKham.get(i).getClskhamThanhtien() == 0) {
					listClsKham.get(i).setClskhamDongiatt(roundDoubleNumber(listClsKham.get(i).getClskhamDongia()).intValue());				
					// CLS tinh theo lan, so lan khong co so le, nen thanh tien = dongiatt * so lan
					solan = (listClsKham.get(i).getClskhamLan() == null ? 0 : listClsKham.get(i).getClskhamLan().intValue()) - (listClsKham.get(i).getClskhamTra() == null ? 0 : listClsKham.get(i).getClskhamTra().intValue());					
					listClsKham.get(i).setClskhamThanhtien(roundDoubleNumber(new Double(listClsKham.get(i).getClskhamDongiatt() * solan)).intValue());
				}
			}
		}
		return listClsKham;
	}
	
	// Ham cap nhat dongiatt va thanhtien cho cac Thuoc phong kham
	// Ham nay dung de cap nhat cac du lieu cu (truoc ngay 01/10/2011)
	// Viec cap nhat cac thong tin nay se duoc thuc hien o chuc nang chi dinh Thuoc, khi do khong can su dung ham nay nua
	private List<ThuocPhongKham> capnhatDongiattTpk(List<ThuocPhongKham> listTpk ) {		
		if (listTpk.size() > 0) {			
			Double thanhTien = null;
			for(int i = 0; i < listTpk.size(); i++) {
				//if(listTpk.get(i).getThuocphongkhamDongiatt() == 0 && listTpk.get(i).getThuocphongkhamThanhtien() == 0) {
					listTpk.get(i).setThuocphongkhamDongiatt(roundDoubleNumber(listTpk.get(i).getThuocphongkhamDongia()).intValue());
					
					// Thuoc phong kham tinh theo so luong, so luong co the la so le, can phai lam tron thanh tien
					thanhTien = ((listTpk.get(i).getThuocphongkhamSoluong() - (listTpk.get(i).getThuocphongkhamTra() == null ? 0 : listTpk.get(i).getThuocphongkhamTra())) * listTpk.get(i).getThuocphongkhamDongiatt());
					listTpk.get(i).setThuocphongkhamThanhtien(roundDoubleNumber(thanhTien).intValue());
					
				//}
			}
		}
		return listTpk;
	}
	
	private List<ThuocDongYNgoaiTru> capnhatDongiattThuocDongYNgoaiTru(List<ThuocDongYNgoaiTru> listThuocDongY) {		
		if (listThuocDongY.size() > 0) {			
			Double thanhTien = null;
			for(int i = 0; i < listThuocDongY.size(); i++) {
				if(listThuocDongY.get(i).getThuocdongyDongiatt() == 0 && listThuocDongY.get(i).getThuocdongyThanhtien() == 0) {
					listThuocDongY.get(i).setThuocdongyDongiatt(roundDoubleNumber(new Double(listThuocDongY.get(i).getThuocdongyDongia())).intValue());
					
					// Thuoc phong kham tinh theo so luong, so luong co the la so le, can phai lam tron thanh tien
					thanhTien = (listThuocDongY.get(i).getThuocdongySoluong() * listThuocDongY.get(i).getThuocdongyDongiatt());
					listThuocDongY.get(i).setThuocdongyThanhtien(roundDoubleNumber(thanhTien).intValue());
					
				}
			}
		}
		return listThuocDongY;
	}
	private Double roundDoubleNumber(Double dNum) {
		if (dNum == null) return new Double(0);
		double dVal = dNum.doubleValue();
		int iVal = dNum.intValue();		
		if(dVal - iVal > 0) {
			iVal = iVal + 1;
		}
		return new Double(iVal);
	}
	public void tinhToanChoHSTKKham(HsThtoank hsttk){
		log.info("----- tinhToanChoHSTKKham -----");
		// 20120702 bao.ttc: remove vi khong can thiet
		// HoSoThanhToanKhamUtil hsthtoankUtil = new HoSoThanhToanKhamUtil(tiepDon);
		
		ClsKhamDelegate clsKhamDelegate  = ClsKhamDelegate.getInstance();
		// Moc thoi gian kiem tra du lieu cu (ngay 15/10/2011)
		Calendar myCal = Calendar.getInstance();
		myCal.set(2011, 11, 15, 0, 0, 0);  // ngay 15/10/2011
		Date dateUpdate = myCal.getTime();
		// lay CLS
		listCtkq_temp = clsKhamDelegate.findByTiepdonma(tiepDon.getTiepdonMa());		
		// lay thuoc ban` khoam
		ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
		listCtTPK_temp = tpkDelegate.findByMaTiepDon(tiepDon.getTiepdonMa(), "1"); // thuoc ban kham
				
		// ke toa quay benh vien
		List<ThuocPhongKham>  listCtTPK_tempBHYT = tpkDelegate.findByMaTiepDon(tiepDon.getTiepdonMa(), "3"); // ke toa BHYT		
				
		// Cap nhat dongiatt va thanh tien, su dung cho du lieu cu (truoc ngay 01/10/2011)
		// Neu khi chi dinh Thuoc, CLS da luu cac thong tin nay thi khong can thuc hien o day nua
		if (tiepDon.getTiepdonNgaygio().before(dateUpdate)) {
			//Khong can lam tron don gia va thanh tien cua CLS
			//listCtkq_temp = capnhatDongiattCls(listCtkq_temp);			
			listCtTPK_temp = capnhatDongiattTpk(listCtTPK_temp);
			listCtTPK_tempBHYT = capnhatDongiattTpk(listCtTPK_tempBHYT);
		}
		// tinh tong chi phi		
		//double tongChiPhi = tinhTongChiPhiCLS(listCtkq_temp);
		//tongChiPhi= tongChiPhi + tinhToanTongChiPhiThuoc(listCtTPK_temp, Utils.XU_TRI_THUOC_TAI_BAN_KHAM);
		//tongChiPhi= tongChiPhi + tinhToanTongChiPhiThuoc(listCtTPK_tempBHYT, Utils.KE_TOA_QUAY_BENH_VIEN);
		//phuc.lc 27-09-2010
		tongChiPhiCLSBhTra = 0;
		tongChiPhiCLSBnTra = 0;
		//tongTienThuoc = 0;
		tongChiPhiVCBhTra = 0;
		tongChiPhiVCBnTra = 0;
		tongChiPhiKTCBhTra = 0;
		tongChiPhiKTCBnTra = 0;
		tongChiPhiThuocBhTra = 0;
		tongChiPhiThuocBnTra = 0;
		//boolean doituongDB = false;
		boolean mienphiVC = false;
		tongChiPhiCLSBhTra_CapCuu = 0;			
		tongChiPhiVCBhTra_CapCuu = 0;		
		tongChiPhiKTCBhTra_CapCuu = 0;		
		tongChiPhiThuocBhTra_CapCuu = 0;
		
		tongChiPhiThuocCLSBnTra_TyleBH = 0;
		
		String maDoituong = (tiepDon.getDoituongMa() == null ? "" : tiepDon.getDoituongMa().getDmdoituongMa());		// Luu ma doi tuong : BH, TP hay MP
		bCapCuu = false;
		if(maDoituong.equals("BH")) {
			// Kiem tra benh nhan co tiep don cap cuu hay khong
			List<ThamKham> listTk = ThamKhamDelegate.getInstance().findAllByMaTiepDon(tiepDon.getTiepdonMa());
			if(listTk != null) {
				try {
					for(ThamKham tk : listTk) {
						if(tk.getThamkhamBankham().getDtdmbankhamMa().startsWith("CC")) {
							bCapCuu = true;
							break;
						}
					}
				}catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		// Xet ma quyen loi dua theo so the bao hiem
		String strSotheBHYT = (tiepDon.getTiepdonSothebh() == null ?"" : tiepDon.getTiepdonSothebh());
		//log.info("So the BH = " + strSotheBHYT);
		if (strSotheBHYT.trim().length() > 3) {
			String strMaQuyenloi = strSotheBHYT.substring(2, 3);
			DtDmMqlBhytDelegate maQLDel = DtDmMqlBhytDelegate.getInstance();
			DtDmMqlBhyt maQL = maQLDel.findByMa(strMaQuyenloi);
			//log.info("maQL = " + maQL);
			if (maQL != null) {
				// Set lai cac gia tri theo  ma quyen loi
				minktc = (maQL.getDtdmmqlbhytMinktc() == null ? 0 : maQL.getDtdmmqlbhytMinktc());							
				maxktc = (maQL.getDtdmmqlbhytMaxktc() == null ? 0 : maQL.getDtdmmqlbhytMaxktc());
				if (!bVuottuyen || bCapCuu) {
					tylebhyt = (maQL.getDtdmmqlbhytTylebhyt1() == null ? 0 : maQL.getDtdmmqlbhytTylebhyt1());
				}
				tylebhyt2 = (maQL.getDtdmmqlbhytTylebhyt2() == null ? 0 : maQL.getDtdmmqlbhytTylebhyt2());				
				tyleminktc = (maQL.getDtdmmqlbhytTlminktc() == null ? 0 : maQL.getDtdmmqlbhytTlminktc());
				tylemaxktc = (maQL.getDtdmmqlbhytTlmaxktc() == null ? 0 : maQL.getDtdmmqlbhytTlmaxktc());
				tyleKTC = (maQL.getDtdmmqlbhytTylektc() == null ? 0 : maQL.getDtdmmqlbhytTylektc());;
				gioiHanTyLe = (maQL.getDtdmmqlbhytGhtyle() == null ? 0 : maQL.getDtdmmqlbhytGhtyle());
				mienphiVC = (maQL.getDtdmmqlbhytVanchuyen() == null ? false : maQL.getDtdmmqlbhytVanchuyen());
				
			}
		}
		tylebhyt_CapCuu = tylebhyt;
		tylebhyt2_CapCuu = tylebhyt2;
		
		
		// phuc.lc 30-12-2010 : begin fix bug 1928
		Date ngayBatDauBh = tiepDon.getTiepdonGiatri3(); // Ngay bat dau bao hiem tinh theo gia tri the tai benh vien (luu trong truong TIEPDON_GIATRI3 cua bang tiep_don)
		// Neu ngay bat dau theo gia tri tai benh vien bang null, thi lay theo ngay bat dau cua the
		if (ngayBatDauBh == null) {
			//ngayBatDauBh = (tiepDon.getTiepdonGiatri1() == null ? new Date() : tiepDon.getTiepdonGiatri1());
			ngayBatDauBh = tiepDon.getTiepdonGiatri1();
		}
		// phuc.lc 30-12-2010 : end
		// lay ngay het han cua the bao hiem
		Date ngayHetHanBh = tiepDon.getTiepdonGiatri4(); // Ngay het han bao hiem tinh theo gia tri the tai benh vien (luu trong truong TIEPDON_GIATRI4 cua bang tiep_don)
		// Neu ngay het han theo gia tri tai benh vien bang null, thi lay theo ngay het han cua the
		if (ngayHetHanBh == null) {
			//ngayHetHanBh = (tiepDon.getTiepdonGiatri2() == null ? new Date() : tiepDon.getTiepdonGiatri2());
			ngayHetHanBh = tiepDon.getTiepdonGiatri2();
		}
		if (ngayBatDauBh != null) {
			//Calendar cal1 = Calendar.getInstance();
			//cal1.setTime(ngayBatDauBh);
			//cal1.set(Calendar.HOUR, 0);
			//cal1.set(Calendar.MINUTE, 0);
			//cal1.set(Calendar.SECOND, 0);
			//cal1.set(Calendar.MILLISECOND, 0);
			//ngayBatDauBh = cal1.getTime();
			ngayBatDauBh = Utils.removeHourFromDate(ngayBatDauBh);
		}
		if (ngayHetHanBh != null) {
			//Calendar cal2 = Calendar.getInstance();
			//cal2.setTime(ngayHetHanBh);
			//cal2.set(Calendar.HOUR, 0);
			//cal2.set(Calendar.MINUTE, 0);
			//cal2.set(Calendar.SECOND, 0);
			//cal2.set(Calendar.MILLISECOND, 0);
			//ngayHetHanBh = cal2.getTime();
			ngayHetHanBh = Utils.removeHourFromDate(ngayHetHanBh);
		}
		tinhChiPhiCLS(listCtkq_temp, maDoituong,mienphiVC, ngayBatDauBh, ngayHetHanBh, hsttk.getHsthtoankNgaygiott());	
		// Tinh tong tien thuoc tai ban kham		
		tinhChiPhiThuoc(listCtTPK_temp, Utils.XU_TRI_THUOC_TAI_BAN_KHAM, maDoituong, ngayBatDauBh, ngayHetHanBh);
		
		// Tinh tong tien thuoc bao hiem
		tinhChiPhiThuoc(listCtTPK_tempBHYT, Utils.KE_TOA_QUAY_BENH_VIEN, maDoituong, ngayBatDauBh, ngayHetHanBh);
		
		// phuc.lc 13-06-2011 : tinh chi phi thuoc dong y
		ThuocDongYNgoaiTruDelegate thuocDYNTDel = ThuocDongYNgoaiTruDelegate.getInstance();
		List<ThuocDongYNgoaiTru> listThuocDYNgoaiTru = thuocDYNTDel.findByMaTiepDon(tiepDon.getTiepdonMa());
		Date ngayNhanThuocDY;
		if (maDoituong.equals("BH") && IConstantsRes.DONG_Y_APPLY_ALL.equals("1")) {
			// Kiem tra co su dung bai thuoc dong y hay khong						
			if(listThuocDYNgoaiTru.size() > 0) {
				if (tiepDon.getTiepdonNgaygio().before(dateUpdate)) {
					listThuocDYNgoaiTru = capnhatDongiattThuocDongYNgoaiTru(listThuocDYNgoaiTru);
				}
				
				for (ThuocDongYNgoaiTru each : listThuocDYNgoaiTru) {
					ngayNhanThuocDY = Utils.removeHourFromDate(each.getThuocdongyNgaygiocn());
					// Neu ngay nhan thuoc con thoi han bao hiem thi bao hiem tra, neu het han bao hiem thi benh tra( da tinh trong ham tinhChiPhiThuoc(...))
					if(ngayBatDauBh != null && ngayHetHanBh != null
							&& !ngayNhanThuocDY.before(ngayBatDauBh) && !ngayNhanThuocDY.after(ngayHetHanBh)) {
						if(bCapCuu) {
							//tongChiPhiThuocBhTra_CapCuu += (each.getThuocdongySoluong() * each.getThuocdongyDongia());
							tongChiPhiThuocBhTra_CapCuu += each.getThuocdongyThanhtien();
						} else {
							//tongChiPhiThuocBhTra += (each.getThuocdongySoluong() * each.getThuocdongyDongia());
							tongChiPhiThuocBhTra += each.getThuocdongyThanhtien();
						}
						// Chi phi nay cong vao chi phi thuoc trong danh muc (do bao hiem chi tra theo phan ty le)
						//thuocTrongDM +=(each.getThuocdongySoluong() * each.getThuocdongyDongia());
						thuocTrongDM += each.getThuocdongyThanhtien();
					}
				}
			}
		}
		// phuc.lc 13-06-2011 : end
		// Tinh tong chi phi BH tra xem da vuot qua gioi han ty le hay chua
		// tong nay bao gom chi phi CLS va Thuoc (khong tinh chi phi KTC va VC)
		// muc dich de tinh ty le bao hiem tra tren tong chi phi nay		
		double tongChiPhiThuocCLS = tongChiPhiCLSBhTra + tongChiPhiThuocBhTra;
		double tongChiPhiThuocCLS_CapCuu = tongChiPhiCLSBhTra_CapCuu + tongChiPhiThuocBhTra_CapCuu;
		double tyleBhApDung = 0;	// Ty le ap dung thuc su khi tinh toan, ty le nay co the la TYLEBHYT1 hoac TYLEBHYT2, tuy thuoc gia tri tongChiPhiThuocCLSBhTra
		double tyleBhApDung_CapCuu = 0;
		
		tyleBhApDung = tylebhyt; // Ty le nay da tinh cho cac truong hop vuot tuyen luc khoi tao
		tyleBhApDung_CapCuu = tylebhyt_CapCuu;  
		if ((!bVuottuyen || bCapCuu) && (tongChiPhiThuocCLS < gioiHanTyLe) ) {
			tyleBhApDung = tylebhyt2;  // Ty le nay da tinh cho cac truong hop vuot tuyen luc khoi tao
			tyleBhApDung_CapCuu = tylebhyt2_CapCuu;
		}
		// cap nhat lai chi phi benh nhan tra cho tung loai thuoc theo tyleBhApDung (thuoc phong kham)
		capNhatThuocPhongKham(listCtTPK_temp, Utils.XU_TRI_THUOC_TAI_BAN_KHAM, maDoituong,ngayBatDauBh, ngayHetHanBh, tyleBhApDung, tyleBhApDung_CapCuu);
		// cap nhat lai chi phi benh nhan tra cho tung loai thuoc theo tyleBhApDung (thuoc bao hiem)
		capNhatThuocPhongKham(listCtTPK_tempBHYT, Utils.KE_TOA_QUAY_BENH_VIEN, maDoituong, ngayBatDauBh, ngayHetHanBh, tyleBhApDung, tyleBhApDung_CapCuu);
		
		//cap nhat lai chi phi benh nhan tra cho tung CLS thuong theo tyleBhApDung
		// muc dich de tinh mien giam
		capNhatClsKham(listCtkq_temp, maDoituong, ngayBatDauBh, ngayHetHanBh,tyleBhApDung, tyleBhApDung_CapCuu);
		
		// Dua theo tyleBhApDung, tinh ra chi phi bao hiem tra va chi phi benh nhan tra tren tongChiPhiThuocCLSBhTra
		
		//double tongChiPhiThuocCLSBhTra = roundDoubleNumber(new Double(tongChiPhiThuocCLS * (tyleBhApDung / 100)));		
		//double tongChiPhiThuocCLSBhTra_CapCuu = roundDoubleNumber(new Double(tongChiPhiThuocCLS_CapCuu * (tyleBhApDung_CapCuu / 100)));
		//double tongChiPhiThuocCLSBnTra = tongChiPhiThuocCLS - tongChiPhiThuocCLSBhTra;
		//double tongChiPhiThuocCLSBnTra_CapCuu = tongChiPhiThuocCLS_CapCuu - tongChiPhiThuocCLSBhTra_CapCuu;
		//tongChiPhiThuocCLSBnTra += tongChiPhiThuocCLSBnTra_CapCuu;
		//if (tongChiPhiThuocCLSBnTra < 0.0) {
		//	tongChiPhiThuocCLSBnTra = 0.0;
		//}
		
		// Tinh tong chi phi benh nhan phai tra (de hien thi len giao dien)
		double tongChiPhiBnTra = tongChiPhiThuocCLSBnTra_TyleBH + tongChiPhiCLSBnTra + tongChiPhiVCBnTra + tongChiPhiKTCBnTra +  tongChiPhiThuocBnTra;	
		
		// Tinh tong chi phi bao hiem tra (de hien thi len giao dien)
		double tongChiPhiBhTra = (tongChiPhiThuocCLS + tongChiPhiThuocCLS_CapCuu - tongChiPhiThuocCLSBnTra_TyleBH) + tongChiPhiVCBhTra + tongChiPhiKTCBhTra + tongChiPhiVCBhTra_CapCuu + tongChiPhiKTCBhTra_CapCuu;		
		
		
		
		//double tongChiPhiThuocCLSBhTra = tongChiPhiThuocCLS - tongChiPhiThuocCLSBnTra;
		// Tinh tong chi phi benh nhan phai tra (de hien thi len giao dien)
		//double tongChiPhiBnTra = tongChiPhiThuocCLSBnTra + tongChiPhiCLSBnTra + tongChiPhiVCBnTra + tongChiPhiKTCBnTra +  tongChiPhiThuocBnTra;	
		
		// Tinh tong chi phi bao hiem tra (de hien thi len giao dien)
		//double tongChiPhiBhTra = tongChiPhiThuocCLSBhTra + tongChiPhiVCBhTra + tongChiPhiKTCBhTra + tongChiPhiVCBhTra_CapCuu + tongChiPhiKTCBhTra_CapCuu;
		
		//refresh list CLS sau khi cap nhat
		listCtkq_temp = clsKhamDelegate.findByTiepdonma(tiepDon.getTiepdonMa());
		
		//phuc.lc 13-06-2011 : Cap nhat tien benh tra cho thuoc dong y ngoai tru
		if (maDoituong.equals("BH") && IConstantsRes.DONG_Y_APPLY_ALL.equals("1")
				&& listThuocDYNgoaiTru.size() > 0) {
			Double thanhtien = 0.0;	
			boolean bChiPhiCapCuu = false;
			for (ThuocDongYNgoaiTru each : listThuocDYNgoaiTru) {				
				// Kiem tra co phai la Thuoc cap cuu hay khong
				try {
					bChiPhiCapCuu = each.getThuocdongyThamkham().getThamkhamBankham().getDtdmbankhamMa().startsWith("CC");
				} catch (Exception ex) {
					log.info("ERROR Kiem tra co phai la Thuoc cap cuu hay khong: " + ex.toString());
					ex.printStackTrace();
					bChiPhiCapCuu = false;
				}
				ngayNhanThuocDY = Utils.removeHourFromDate(each.getThuocdongyNgaygiocn());
				thanhtien = each.getThuocdongySoluong() * each.getThuocdongyDongia();
				// Neu ngayBatDauBh = null hoac ngayHetHatBh = null => coi nhu het han bao hiem, benh nhan tra toan bo
				if(ngayBatDauBh == null || ngayHetHanBh == null) {
					each.setThuocdongyTylebh(new Double(0));
					each.setThuocdongyTienbntra(thanhtien);
				} else if(ngayNhanThuocDY.before(ngayBatDauBh) || ngayNhanThuocDY.after(ngayHetHanBh)){
					each.setThuocdongyTylebh(new Double(0));
					each.setThuocdongyTienbntra(thanhtien);				
				} else {
					if(bChiPhiCapCuu) {
						each.setThuocdongyTylebh(tyleBhApDung_CapCuu);
						each.setThuocdongyTienbntra(thanhtien - roundDoubleNumber(thanhtien * (tyleBhApDung_CapCuu/100)));
					} else {
						each.setThuocdongyTylebh(tyleBhApDung);
						each.setThuocdongyTienbntra(thanhtien - roundDoubleNumber(thanhtien * (tyleBhApDung/100)));
					}
				}
				thuocDYNTDel.edit(each);
			}
		}
		//phuc.lc 13-06-2011 : end
		
		// Lay thong tin mien giam
		String maMienGiam = "";
		Date ngayBatDauMG = null;
		Date ngayKetThucMG = null;
		Hsba hsba = HsbaDelegate.getInstance().findByTiepDonMa(tiepDon.getTiepdonMa());
		double phantramMienGiam = 0;
		if (hsba != null) {
			List<MienGiam> listMienGiam = MienGiamDelegate.getInstance().getBySoVaoVien(hsba.getHsbaSovaovien());
			if (listMienGiam != null) {
				if (listMienGiam.size() > 0) {
					maMienGiam = listMienGiam.get(0).getMiengiamLoaimien().getDtdmloaimienMa();
					
					ngayBatDauMG = listMienGiam.get(0).getMiengiamNgayd();
					//Calendar cal1 = Calendar.getInstance();
					//cal1.setTime(ngayBatDauMG);
					//cal1.set(Calendar.HOUR, 0);
					//cal1.set(Calendar.MINUTE, 0);
					//cal1.set(Calendar.SECOND, 0);
					//cal1.set(Calendar.MILLISECOND, 0);
					//ngayBatDauMG = cal1.getTime();
					ngayBatDauMG = Utils.removeHourFromDate(ngayBatDauMG);
					
					ngayKetThucMG = listMienGiam.get(0).getMiengiamNgayc();					
					//Calendar cal2 = Calendar.getInstance();
					//cal2.setTime(ngayKetThucMG);
					//cal2.set(Calendar.HOUR, 0);
					//cal2.set(Calendar.MINUTE, 0);
					//cal2.set(Calendar.SECOND, 0);
					//cal2.set(Calendar.MILLISECOND, 0);
					//ngayKetThucMG = cal2.getTime();
					ngayKetThucMG = Utils.removeHourFromDate(ngayKetThucMG);
					phantramMienGiam = (listMienGiam.get(0).getMiengiamTyle() == null ? 0 : listMienGiam.get(0).getMiengiamTyle().doubleValue());
					tongMienGiam += (listMienGiam.get(0).getMiengiamSotien() == null ? 0 : listMienGiam.get(0).getMiengiamSotien().doubleValue());
				}
			}
		}		
		// Nếu loại miễn là "Miễn giảm phần trăm (toàn chi phí) (mã = 2)" hoặc "Miễn một số tiền cụ thể (mã  = 3)"		
		if( maMienGiam.equals("3")) {
			// Miễn một số tiền cụ thể (mã  = 3) : tongMienGiam = số tiền miễn giảm (đã set ở trên)
			
		} else if(maMienGiam.equals("2")) { //"Miễn giảm phần trăm (toàn chi phí) (mã = 2)"
			tongMienGiam += tongChiPhiBnTra * phantramMienGiam / 100;
		} else {
			// Cac loại miễn khác phải tính theo thời gian 
			//tongMienGiam = 0;
			//tinhTongMienGiam(listCtkq_temp, maMienGiam, ngayBatDauMG, ngayKetThucMG);
			tongMienGiam += (tinhTongMienGiamCls(listCtkq_temp, maMienGiam, ngayBatDauMG, ngayKetThucMG) * phantramMienGiam) / 100;
		}				
		//log.info("In HoSoThanhToanKhamUtil, thuocTrongDM = " + thuocTrongDM);
		 hsttk.setHsthtoankThuoc(thuocTrongDM);
		 hsttk.setHsthtoankThuocndm(thuocNDM);
		 hsttk.setHsthtoankVtth(vTTHTrongDM);
		 hsttk.setHsthtoankVtthndm(vTTHNDM);
		 
		 
		 hsttk.setHsthtoankPhong(cLSPhongTrongDM);
		 hsttk.setHsthtoankPhongndm(clsPhongNDM);
		 
		 //log.info("In HoSoThanhToanKhamUtil, tongChiPhiBnTra = " + tongChiPhiBnTra + ", DaThanhToan = " + DaThanhToan);
		 hsttk.setHsthtoankBntra(tongChiPhiBnTra);
		 

//		 hsttk.setHsthtoankBhyt(bhytchi);		 
		 hsttk.setHsthtoankBhyt(tongChiPhiBhTra);
		 //log.info("In HoSoThanhToanKhamUtil, tongChiPhiBhTra = " + tongChiPhiBhTra);
		 hsttk.setHsthtoankTongchi(tongChiPhiBnTra + tongChiPhiBhTra);
		 //hsttk.setHsthtoankXetgiam(miengiam);
		 //phuc.lc 05-10-2010
		 hsttk.setHsthtoankXetgiam(tongMienGiam);
		 hsttk.setHsthtoankTylebh(new Short("" + new Double(tyleBhApDung).intValue()));
		 
		 hsttk.setHsthtoankMiente(mienTE);
		 
		 ////////////////////////////////
		 //log.info("In HoSoThanhToanKhamUtil, clsTrongDM = " + clsTrongDM);
		 hsttk.setHsthtoankCls(clsTrongDM);
		 //log.info("In HoSoThanhToanKhamUtil, clsNDM = " + clsNDM);
		 hsttk.setHsthtoankClsndm(clsNDM);
		 
		 //////////////////////////////////
		 hsttk.setHsthtoankMau(clsMauNDM + cLSMauTrongDM);
		 //log.info("In HoSoThanhToanKhamUtil, tienCongKham = " + tienCongKham);
		 hsttk.setHsthtoankCongkham(tienCongKham);
		 hsttk.setHsthtoankXntdcn(xetNghiemTDCN);
		 hsttk.setHsthtoankCdha(chanDoanHinhAnh);
		 //hsttk.setHsthtoankDvkttt(dichVuKTThongThuong);
		 hsttk.setHsthtoankDvktc(dichVuKTC);
		 hsttk.setHsthtoankCpvc(chiPhiVC);
		 // phuc.lc 12-12-2010  BEGIN
		 //log.info("chiphiPT = " + chiphiPT);
		 hsttk.setHsthtoankPhauthuat(chiphiPT);
		 //log.info("chiphiTT = " + chiphiTT);
		 hsttk.setHsthtoankDvkttt(chiphiTT);		 // END
		 
		 //////////////////////////////////
		 
		 tinhTamUngKham();
		 tinhHoanUngKham();
		 
		 //log.info("ung:"+ung);
		 //log.info("tra:"+tra);
		 
		 hsttk.setHsthtoankTamung(ung);
		 hsttk.setHsthtoankHoanung(tra);
		 
//		 soDu = bntra - ung + tra - DaThanhToan;
		 soDu = tongChiPhiBnTra - ung + tra - DaThanhToan;
		 //log.info("In HoSoThanhToanKhamUtil, soDu = " + soDu);
		 hsttk.setHsthtoankThtoan(soDu);
		 hsttk.setHsthtoankKhongthu(khongThu);
		 
		 hsttk.setHsthtoankNdm(thuocNDM + vTTHNDM + clsNDM);
		 hsttk.setHsthtoankDm(thuocTrongDM + vTTHTrongDM + clsTrongDM);
		 // Tim hsthtoank theo tiep don
		 // neu da co hsthtoank va chua thanh toan thi cap nhat lai cac gia tri
		 // neu chua co thi tao hsthtoank moi
		 //log.info("Ma tiep don = " + tiepDon.getTiepdonMa());
		 HsThtoankDelegate hsDel = HsThtoankDelegate.getInstance();
		 HsThtoank hsTmp = hsDel.findBytiepdonMaLast(tiepDon.getTiepdonMa());
		 
		 // Bien nay phuc vu cho viec cap nhat du lieu cac ho so da thanh toan
		 hosoTTK = new HsThtoank();
		 
		 if(hsTmp != null) {
			 if(hsTmp.getHsthtoankNgaygiott() == null) {
				 //log.info("Cap nhat ho so = " + hsTmp);
				 hsTmp.setHsthtoankThuoc(hsttk.getHsthtoankThuoc());
				 hsTmp.setHsthtoankThuocndm(hsttk.getHsthtoankThuocndm());
				 hsTmp.setHsthtoankVtth(hsttk.getHsthtoankVtth());
				 hsTmp.setHsthtoankVtthndm(hsttk.getHsthtoankVtthndm());				 				
				 hsTmp.setHsthtoankPhong(hsttk.getHsthtoankPhong());
				 hsTmp.setHsthtoankPhongndm(hsttk.getHsthtoankPhongndm());				 				
				 hsTmp.setHsthtoankBntra(hsttk.getHsthtoankBntra());				 		 
				 hsTmp.setHsthtoankBhyt(hsttk.getHsthtoankBhyt());				 
				 hsTmp.setHsthtoankTongchi(hsttk.getHsthtoankTongchi());				 
				 hsTmp.setHsthtoankXetgiam(hsttk.getHsthtoankXetgiam());
				 hsTmp.setHsthtoankTylebh(hsttk.getHsthtoankTylebh());				 
				 hsTmp.setHsthtoankMiente(hsttk.getHsthtoankMiente());				 				
				 hsTmp.setHsthtoankCls(hsttk.getHsthtoankCls());				 
				 hsTmp.setHsthtoankClsndm(hsttk.getHsthtoankClsndm());				 				 
				 hsTmp.setHsthtoankMau(hsttk.getHsthtoankMau());				 
				 hsTmp.setHsthtoankCongkham(hsttk.getHsthtoankCongkham());
				 hsTmp.setHsthtoankXntdcn(hsttk.getHsthtoankXntdcn());
				 hsTmp.setHsthtoankCdha(hsttk.getHsthtoankCdha());				 
				 hsTmp.setHsthtoankDvktc(hsttk.getHsthtoankDvktc());
				 hsTmp.setHsthtoankCpvc(hsttk.getHsthtoankCpvc());				 				
				 hsTmp.setHsthtoankPhauthuat(hsttk.getHsthtoankPhauthuat());				 
				 hsTmp.setHsthtoankDvkttt(hsttk.getHsthtoankDvkttt());				 				
				 hsTmp.setHsthtoankTamung(hsttk.getHsthtoankTamung());
				 hsTmp.setHsthtoankHoanung(hsttk.getHsthtoankHoanung());				 
				 hsTmp.setHsthtoankThtoan(hsttk.getHsthtoankThtoan());
				 hsTmp.setHsthtoankKhongthu(hsttk.getHsthtoankKhongthu());				 
				 hsTmp.setHsthtoankNdm(hsttk.getHsthtoankNdm());
				 hsTmp.setHsthtoankDm(hsttk.getHsthtoankDm()); 				 
				 hsTmp.setHsthtoankNgaygiott(hsttk.getHsthtoankNgaygiott());				 
				 hsTmp.setHsthtoankDatt(hsttk.getHsthtoankDatt());
				 hsDel.edit(hsTmp);				 
			 } else {
				 //log.info("Ho so da thanh toan khong cap nhat");
				 hosoTTK = hsttk;
			 }
		 }else {
			 //log.info("Tao thong tin ho so mo ");
			 hsttk.setTiepdonMa(tiepDon);
			 if(hsttk.getHsthtoankMa() != null) {
				 hsDel.edit(hsttk);
			 } else {
				 hsDel.create(hsttk);
			 }
		 }
	}

	// phuc.lc 27-09-2010
	private void tinhChiPhiCLS(List<ClsKham> clslist, String maDoituong, boolean mienphiVC, Date ngayBatDauBh, Date ngayHetHanBh, Date ngayTT){
		//log.info("Begin tinhChiPhiCLS(), ngayTT = " + ngayTT);			
		boolean bChiPhiCapCuu = false;
		if (clslist ==null || clslist.size() == 0){
			return ;
		}
		if (tiepDon == null){
			return ;
		}
				
		if (clslist != null && clslist.size() > 0) {
			ClsKhamDelegate clsKhamDel = ClsKhamDelegate.getInstance();
			try {
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Date ngayThucHienCLS = null;
				//Calendar cal1 = Calendar.getInstance();
				for (ClsKham clskham : clslist) {
					// so lieu can lam sang da chuyen vao noi tru, khong tinh
					if (clskham.getClskhamStatus() != null && clskham.getClskhamStatus().equals("1") ){
						continue;
					}
					
					
					Short lanKham = clskham.getClskhamLan();
					if (lanKham == null) {
						lanKham = 1;
					}
					clskham.setClskhamLan(lanKham);
					//log .info("lanKham:"+ lanKham );
					
					Short tra = clskham.getClskhamTra();
					if (tra == null){
						tra = 0;
					}
					// can lam sang da thanh toan tien roi, tinh vao tong chi phi benh nhan phai tra va so da thanh toan
					if (clskham.getClskhamDatt() != null && clskham.getClskhamDatt().booleanValue() == true){
						// phuc.lc 05/01/2010 : begin fix bug 1959
						//tongChiPhiCLSBnTra += clskham.getClskhamDongia()*(lanKham - tra) ;
						tongChiPhiCLSBnTra += (clskham.getClskhamDongiabntra() == null ? 0.0 : clskham.getClskhamDongiabntra());
						//DaThanhToan += clskham.getClskhamDongia()*(lanKham - tra);
						DaThanhToan += (clskham.getClskhamDongiabntra() == null ? 0.0 : clskham.getClskhamDongiabntra());
						tinhChiPhiTheoMaCLS(clskham.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa(), (clskham.getClskhamDongia() == null ? 0 : clskham.getClskhamDongia())  , (clskham.getClskhamNdm() != null?clskham.getClskhamNdm().booleanValue():true), lanKham, tra);
						// phuc.lc 05/01/2010 : end fix bug 1959
						//log.info("Da thanh toan, ma CLS = " + clskham.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa());
						//if (clskham.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("KH")) {
						//	tienCongKham += clskham.getClskhamDongia()*(lanKham - tra);
						//}						
						if (clskham.getClskhamNdm() != null && clskham.getClskhamNdm().booleanValue() == true) {
							clsNDM += clskham.getClskhamDongia()*(lanKham - tra);
						} else {
							clsTrongDM += clskham.getClskhamDongia()*(lanKham - tra);
						}
						//log.info("Da thanh toan, tienCongKham = " + tienCongKham);
						continue;
					}
					clskham.setClskhamNgaygiott(ngayTT);
					if (lanKham > tra){
						// Kiem tra co phai la CLS cap cuu hay khong
						try {
							bChiPhiCapCuu = clskham.getClskhamThamkham().getThamkhamBankham().getDtdmbankhamMa().startsWith("CC");
						} catch (Exception ex) {
							log.info("ERROR Kiem tra co phai la CLS cap cuu hay khong: " + ex.toString());
							ex.printStackTrace();
							bChiPhiCapCuu = false;
						}
						// Don gia trong Cls Kham co the la Don gia, Don gia mien phi hoac Don gia yeu cau trong bang Cls bang gia
						// tuy thuoc vao luc them CLS co chon YC hay khong
						
						//if (clskham.getClskhamDongia() == null) {
						//	clskham.setClskhamDongia(new Double(0));
						//}
						//log.info("clskham.getClskhamDongia():"+ clskham.getClskhamDongia() );
						
						if (clskham.getClskhamNdm() != null && clskham.getClskhamNdm().booleanValue() == true) {
							clsNDM += clskham.getClskhamDongia()*(lanKham - tra);
						} else {
							clsTrongDM += clskham.getClskhamDongia()*(lanKham - tra);
						}
						//log.info("clskham.getClskhamMa()" + clskham.getClskhamMa() + "clskham.getClskhamNgaygio():"+ clskham.getClskhamNgaygio() );
						ngayThucHienCLS = (clskham.getClskhamNgaygio() == null ? null : df.parse(df.format(clskham.getClskhamNgaygio())));						
						//cal1.setTime(ngayThucHienCLS);
						//cal1.set(Calendar.HOUR, 0);
						//cal1.set(Calendar.MINUTE, 0);
						//cal1.set(Calendar.SECOND, 0);
						//cal1.set(Calendar.MILLISECOND, 0);
						//ngayThucHienCLS = cal1.getTime();
						ngayThucHienCLS = Utils.removeHourFromDate(ngayThucHienCLS);
						//  Tinh chi phi cac CLS ky thuat cao 
						if (clskham.getClskhamKtcao() != null && clskham.getClskhamKtcao().booleanValue() == true) {							
							tinhChiPhiClsKTC(clsKhamDel, clskham , maDoituong, lanKham, tra, ngayBatDauBh, ngayHetHanBh, ngayThucHienCLS, bChiPhiCapCuu);
						} else if (clskham.getClskhamMaloai() != null && clskham.getClskhamMaloai().getDtdmclsMaso().intValue() == 12){ // 12 la ma so cua can lam san van chuyen
							tinhChiPhiClsVanChuyen(clsKhamDel, clskham, maDoituong, lanKham, tra, mienphiVC, ngayBatDauBh, ngayHetHanBh, ngayThucHienCLS, bChiPhiCapCuu);
						} else { // tinh chi phi CLS thuong
							tinhChiPhiCLSThuong(clskham,  maDoituong, lanKham, tra, ngayBatDauBh, ngayHetHanBh, ngayThucHienCLS, bChiPhiCapCuu);
						}																																						
						// tinh chi phi theo ma CLS
						tinhChiPhiTheoMaCLS(clskham.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa(), (clskham.getClskhamDongia() == null ? 0 : clskham.getClskhamDongia()) , (clskham.getClskhamNdm() != null?clskham.getClskhamNdm().booleanValue():true), lanKham, tra);		
					}																		
				}
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
	}
	private void tinhChiPhiCLSThuong(ClsKham clskham,String maDoituong, int lanKham, int tra, Date ngayBatDauBh, Date ngayHetHanBh, Date ngayThucHienCLS, boolean bChiPhiCapCuu) {
		/*
		+ Mục đích : Tính ra chi phí do BH trả và chi phí do BN trả 
		+ Cách tính :
			- Nếu là đối tượng miễn phí(MP) hoặc thu phí(TP) ==> tính vào chi phí BN trả
			- Nếu là đối tượng BHYT thì tính như sau :
				- Nếu thẻ bảo hiểm hết hạn ==> tính vào chi phí BN trả
				- Nếu CLS là ngoài danh mục thì BH không trả ==> tính vào chi phí BN trả
				- Nếu CLS là yêu cầu thì BH trả theo đơn giá bảo hiểm, phần còn lại BN trả ==> cộng giá BH vào chi phí BH trả, phần còn lại tính vào chi phí BN trả
				- Các trường hợp còn lại (tạm thời chưa xét đến đơn giá thứ 7 & CN và đơn giá miễn) ==> cộng vào chi phí BH trả				
		+ Lưu ý ở đây không xét đến đối tượng đặc biệt (đối tượng ưu tiên), vì tỷ lệ bảo hiểm trả phí đã lưu vào trường TYLEBHYT1 và TLBHYT2 theo mã quyền lợi BHYT 		
		*/
		/*
		 * Đối với CLS thường không cập nhật đơn giá bệnh nhân trả trong hàm tính toán này (không giống như CLS KTC & Vận chuyển)
		 * Đơn giá bệnh nhân trả cho từng CLS thường sẽ được tính lại sau khi đã xác định % BHYT trả
		 */		
		if (maDoituong.equals("MP")) {
			// doi tuong mien phi duoc tinh theo gia mien phi hoac gia yeu cau(neu chon) va do BN tra
			// gia nay duoc luu vao truong don gia trong bang ClsKham thi them vao
			tongChiPhiCLSBnTra += clskham.getClskhamDongia()*(lanKham - tra);
		} else if (maDoituong.equals("TP")){
			// neu la doi tuong thu phi thi tinh vao chi phi benh nhan tra						
			tongChiPhiCLSBnTra += clskham.getClskhamDongia()*(lanKham - tra);					
		} else { // Doi tuong bao hiem
			if (ngayBatDauBh == null || ngayHetHanBh == null) {
				// Neu khong co thong tin thoi han bao hiem thi benh nhan tra
				tongChiPhiCLSBnTra += clskham.getClskhamDongia()*(lanKham - tra);
			}else if (ngayThucHienCLS.before(ngayBatDauBh) || ngayThucHienCLS.after(ngayHetHanBh)) {
				// Neu the bao hiem het han thi benh nhan tra
				tongChiPhiCLSBnTra += clskham.getClskhamDongia()*(lanKham - tra);
			}
			else if (clskham.getClskhamNdm() != null && clskham.getClskhamNdm().booleanValue() == true) { 
			// can sang ngoai danh muc bao hiem, benh nhan tra 														
				tongChiPhiCLSBnTra += clskham.getClskhamDongia()*(lanKham - tra);						
			} else {
				if (clskham.getClskhamYeucau() != null && clskham.getClskhamYeucau().booleanValue() == true) {
				
					// can lan sang yeu cau ==> benh nhan tra phan dich vu, bao hiem tra theo gia bao hiem
					tongChiPhiCLSBnTra += clskham.getClskhamPhandv()*(lanKham - tra);
					if(bChiPhiCapCuu) {
						tongChiPhiCLSBhTra_CapCuu += clskham.getClskhamDongiabh()*(lanKham - tra);
					} else {
						tongChiPhiCLSBhTra += clskham.getClskhamDongiabh()*(lanKham - tra);
					}
				
				}  else {
					if(bChiPhiCapCuu) {
						tongChiPhiCLSBhTra_CapCuu += clskham.getClskhamDongiabh()*(lanKham - tra);
					} else {
						tongChiPhiCLSBhTra += clskham.getClskhamDongia()*(lanKham - tra);
					}
				}
			}
			
		}		
	}
	private void tinhChiPhiClsVanChuyen(ClsKhamDelegate clsKhamDel, ClsKham clskham, String maDoituong, int lanKham, int tra, boolean mienphiVC, Date ngayBatDauBh, Date ngayHetHanBh, Date ngayThucHienCLS, boolean bChiPhiCapCuu){
		/*
		+ Mục đích : Tính ra chi phí do BH trả và chi phí do BN trả 
		+ Cách tính :
			- Nếu là đối tượng miễn phí(MP) hoặc thu phí(TP) ==> tính vào chi phí BN trả
			- Nếu là đối tượng BHYT thì tính như sau :
				- Nếu thẻ bảo hiểm hết hạn ==> tính vào chi phí BN trả
				- Nếu là đối tượng đặc biệt và miễn phí vận chuyển, thì bảo hiểm trả ==> tính vào chi phí BH trả
				- Nếu là đối tượng đặc biệt và không được miễn phí vận chuyển, thì BN trả ==> tính vào chi phí BN trả
				- Nếu vượt tuyến ==> tính vào chi phí BN trả		
				- Nếu ngoài danh mục ==> tính vào chi phí BN trả
				- Nếu đối tượng được miễn phí vận chuyển, thì bảo hiểm trả ==> tính vào chi phí BH trả
				- Nếu đối tượng không được miễn phí vận chuyển, thì bệnh nhân trả ==> tính vào chi phí BN trả			
		*/
		// Cap nhat don gia benh nhan tra, muc dich de tinh mien giam		
		clskham.setClskhamDongiabntra(clskham.getClskhamDongia()*(lanKham - tra));
		if (maDoituong.equals("MP")) {
			// doi tuong mien phi duoc tinh theo gia mien phi hoac gia yeu cau(neu chon) va do BN tra
			// gia nay duoc luu vao truong don gia trong bang ClsKham thi them vao
			tongChiPhiVCBnTra += clskham.getClskhamDongia()*(lanKham - tra);						
		} else if (maDoituong.equals("TP")){
			// neu la doi tuong thu phi thi tinh vao chi phi benh nhan tra						
			tongChiPhiVCBnTra += clskham.getClskhamDongia()*(lanKham - tra);			
			
		} else { // Doi tuong bao hiem
			if (ngayBatDauBh == null || ngayHetHanBh == null) {
				// Neu khong co thong tin thoi han bao hiem thi benh nhan tra
				tongChiPhiVCBnTra += clskham.getClskhamDongia()*(lanKham - tra);								
			}else if (ngayThucHienCLS.before(ngayBatDauBh) || ngayThucHienCLS.after(ngayHetHanBh)) {
				// Neu the bao hiem het han thi benh nhan tra
				tongChiPhiVCBnTra += clskham.getClskhamDongia()*(lanKham - tra);								
			} else if (clskham.getClskhamNdm() != null && clskham.getClskhamNdm().booleanValue() == true) { 
				// can sang ngoai danh muc bao hiem, benh nhan tra 														
				tongChiPhiVCBnTra += clskham.getClskhamDongia()*(lanKham - tra);				
			}  else if (bDoituongUutien || bChiPhiCapCuu) {
				if (mienphiVC) {
					// Thuoc dien mien phi VC, BH tra 100%, benh nhan khong tra
					if(bChiPhiCapCuu) {
						tongChiPhiVCBhTra_CapCuu += clskham.getClskhamDongia()*(lanKham - tra);
					} else {
						tongChiPhiVCBhTra += clskham.getClskhamDongia()*(lanKham - tra);
					}
					// Cap nhat don gia benh nhan tra, muc dich de tinh mien giam
					clskham.setClskhamDongiabntra(new Double(0));
				} else {
					// Khong thuoc dien mien phi VC, BN tra
					tongChiPhiVCBnTra += clskham.getClskhamDongia()*(lanKham - tra);				
				}
			} else if (bVuottuyen){ 
				// Vuot tuyen, benh nhan tra 														
				tongChiPhiVCBnTra += clskham.getClskhamDongia()*(lanKham - tra);				
				 
			} else if(mienphiVC) {
				// Thuoc dien mien phi VC, BH tra 100%, benh nhan khong tra
				tongChiPhiVCBhTra += clskham.getClskhamDongia()*(lanKham - tra);
				// Cap nhat don gia benh nhan tra, muc dich de tinh mien giam
				clskham.setClskhamDongiabntra(new Double(0));							
			} else {
				// Khong thuoc dien mien phi VC, BN tra
				tongChiPhiVCBnTra += clskham.getClskhamDongia()*(lanKham - tra);				
			}									
		}
		//clskham.setClskhamDatt(true); // Da set tren giao dien
		clsKhamDel.edit(clskham);
	}
	private void tinhChiPhiClsKTC(ClsKhamDelegate clsKhamDel, ClsKham clskham, String maDoituong, int lanKham, int tra, Date ngayBatDauBh, Date ngayHetHanBh, Date ngayThucHienCLS, boolean bChiPhiCapCuu) {
		/*
		+ Mục đích : Tính ra chi phí do BH trả và chi phí do BN trả trên các CLS kỹ thuật cao
		+ Cách tính :
			- Nếu là đối tượng miễn phí(MP) hoặc thu phí(TP) ==> tính vào chi phí BN trả
			- Nếu là đối tượng BHYT thì tính như sau :
				- Nếu thẻ bảo hiểm hết hạn ==> tính vào chi phí BN trả			
				- Theo công thức tính tỷ lệ kỹ thuật cao 
		*/
		// Cap nhat don gia benh nhan tra, muc dich de tinh mien giam				
		clskham.setClskhamDongiabntra(clskham.getClskhamDongia()*(lanKham - tra));
		
		if (maDoituong.equals("MP")) {
			// doi tuong mien phi duoc tinh theo gia mien phi hoac gia yeu cau(neu chon) va do BN tra
			// gia nay duoc luu vao truong don gia trong bang ClsKham thi them vao
			tongChiPhiKTCBnTra += clskham.getClskhamDongia()*(lanKham - tra);
			
		} else if (maDoituong.equals("TP")){
			// neu la doi tuong thu phi thi tinh vao chi phi benh nhan tra						
			tongChiPhiKTCBnTra += clskham.getClskhamDongia()*(lanKham - tra);				
		} else { // Doi tuong bao hiem
			if (ngayBatDauBh == null || ngayHetHanBh == null) {
				// Neu khong co thong tin thoi han bao hiem thi benh nhan tra				
				tongChiPhiKTCBnTra += clskham.getClskhamDongia()*(lanKham - tra);				
			}else if (ngayThucHienCLS.before(ngayBatDauBh) || ngayThucHienCLS.after(ngayHetHanBh)) {
				// Neu the bao hiem het han thi benh nhan tra				
				tongChiPhiKTCBnTra += clskham.getClskhamDongia()*(lanKham - tra);				
			}
			else if (clskham.getClskhamNdm() != null && clskham.getClskhamNdm().booleanValue() == true) {				
			// can sang ngoai danh muc bao hiem, benh nhan tra 					
				tongChiPhiKTCBnTra += clskham.getClskhamDongia()*(lanKham - tra);				
			} else {				
				if (clskham.getClskhamYeucau() != null && clskham.getClskhamYeucau().booleanValue() == true) {
					
						// can lan sang yeu cau ==> benh nhan tra phan dich vu, bao hiem tra theo cach tinh ty le KTC						
						tongChiPhiKTCBnTra += clskham.getClskhamPhandv()*(lanKham - tra);						
						double bnTraTmp = tinhTheoTyleKTC((clskham.getClskhamDongiabh() == null ? 0 : clskham.getClskhamDongiabh()) , lanKham, tra, bChiPhiCapCuu);
						// Cap nhat don gia benh nhan tra, muc dich de tinh mien giam
						clskham.setClskhamDongiabntra(clskham.getClskhamPhandv()*(lanKham - tra) + bnTraTmp);
				} else {					
					double bnTraTmp = tinhTheoTyleKTC((clskham.getClskhamDongiabh() == null ? 0 : clskham.getClskhamDongiabh()), lanKham, tra, bChiPhiCapCuu);
					// Cap nhat don gia benh nhan tra, muc dich de tinh mien giam
					clskham.setClskhamDongiabntra(bnTraTmp);
				}
			}
		}
		//clskham.setClskhamDatt(true); // Da set tren giao dien
		clsKhamDel.edit(clskham);
	}
	private double tinhTheoTyleKTC(double donGiaThucHienCLS, int lanKham, int tra, boolean bChiPhiCapCuu) {
		/*
		+ Mục đích : Tính ra chi phí do BH trả và chi phí do BN trả trên các CLS kỹ thuật cao
		+ Cách tính :					
			- Theo công thức tính tỷ lệ kỹ thuật cao
		*/
		// Neu la benh nhan vuot tuyen va khong thuoc dien uu tien thi set lai cac ty le theo ty le cua benh vien
		
		if(bVuottuyen && !bDoituongUutien && !bChiPhiCapCuu) {
			tyleminktc = tylebhyt;
			tyleKTC = tylebhyt;
			tylemaxktc = 0;			
		}
		double giaBhTra = 0;  // Gia bao hiem chap nhan tra cho tung CLS KTC
		double giaBnTra = 0;  // Gia benh nhan phai tra cho tung CLS KTC
		// Truong hop chi phi CLS KTC < MinKTC ==> BH tra theo TyleMin, con lai BN tra
		if (donGiaThucHienCLS < minktc) {
			giaBhTra = (donGiaThucHienCLS * tyleminktc /100) * (lanKham - tra);
			giaBnTra = donGiaThucHienCLS  * (lanKham - tra) -  giaBhTra;
		} // Truong hop chi phi  MinKTC <= CLS KTC <= MaxKTC ==> BH tra theo TyleKTC, con lai BN tra
		else if (donGiaThucHienCLS >= minktc && donGiaThucHienCLS <= maxktc) {
			giaBhTra = (donGiaThucHienCLS * tyleKTC /100) * (lanKham - tra);
			giaBnTra = donGiaThucHienCLS  * (lanKham - tra) -  giaBhTra;
		} // Truong hop chi phi  CLS KTC > MaxKTC ==> phan tren MaxKTC, BH tra theo TyleMax, phan MaxKTC BH tra theo TyleKTC
		else if (donGiaThucHienCLS > maxktc) {
			if(bVuottuyen && !bDoituongUutien && !bChiPhiCapCuu) {
				maxktc = minktc;
			}			
			double trenMaxKTC = donGiaThucHienCLS - maxktc;
			double giaBhTraTrenMax = (trenMaxKTC * tylemaxktc /100) * (lanKham - tra);
			double giaBnTraTrenMax = trenMaxKTC  * (lanKham - tra) -  giaBhTraTrenMax;			
			//phan MaxKTC tinh theo TyleKTC
			giaBhTra = (maxktc * tyleKTC /100) * (lanKham - tra);
			giaBnTra = maxktc  * (lanKham - tra) -  giaBhTra;			
			// cong 2 phan lai
			giaBhTra += giaBhTraTrenMax;
			giaBnTra += giaBnTraTrenMax;			
		}
		tongChiPhiKTCBnTra += giaBnTra;
		if(bChiPhiCapCuu) {
			tongChiPhiKTCBhTra_CapCuu += giaBhTra; 
		} else {
			tongChiPhiKTCBhTra += giaBhTra;
		}
		return giaBnTra;
		
	}
	// Ham tinh chi phi theo ma CLS
	// Vi du : ma CLS la "GI" ==> tinh clsPhongNDM, clsPhongTrongDM
	// ma CLS la "MA" ==> tinh clsMauNDM, clsMauTrongDM
	// ...
	private void tinhChiPhiTheoMaCLS(String maCls, double dongia, boolean ngoaiDM, int lanKham, int tra) {
		//log.info("tinhChiPhiTheoMaCLS(), maCls = " + maCls);
		if (maCls.equalsIgnoreCase("GI")) {
			if (ngoaiDM) {
				clsPhongNDM = clsPhongNDM + dongia*(lanKham - tra);
			} else {
				cLSPhongTrongDM = cLSPhongTrongDM + dongia*(lanKham - tra);
			}
			//dichVuKTThongThuong = dichVuKTThongThuong + dongia*(lanKham - tra);
			
		}else if (maCls.equalsIgnoreCase("MA")) {
			if (ngoaiDM) {
				clsMauNDM = clsMauNDM + dongia*(lanKham - tra);
			} else {
				cLSMauTrongDM = cLSMauTrongDM + dongia*(lanKham - tra);
			}
		} else if (maCls.equalsIgnoreCase("KH")) {
			tienCongKham = tienCongKham + dongia*(lanKham - tra);
		}else if (maCls.equalsIgnoreCase("CD")) {
			chanDoanHinhAnh = chanDoanHinhAnh + dongia*(lanKham - tra);
		} else if (maCls.equalsIgnoreCase("TT")) {
			//dichVuKTThongThuong = dichVuKTThongThuong + dongia*(lanKham - tra);
			// phuc.lc 12-12-2010
			chiphiTT = chiphiTT + dongia*(lanKham - tra);
		} else if (maCls.equalsIgnoreCase("PT")) {
			// dichVuKTThongThuong = dichVuKTThongThuong + dongia*(lanKham - tra);
			// phuc.lc 12-12-2010
			chiphiPT = chiphiPT + dongia*(lanKham - tra);
		}else if (maCls.equalsIgnoreCase("KT")) {
			dichVuKTC = dichVuKTC + dongia*(lanKham - tra);
		}else if (maCls.equalsIgnoreCase("VC")) {
			chiPhiVC = chiPhiVC + dongia*(lanKham - tra);
		}else if (maCls.equalsIgnoreCase("XN")) {
			xetNghiemTDCN = xetNghiemTDCN + dongia*(lanKham - tra);
		}
		
			
	}
	public void capNhatClsKham(List<ClsKham> clslist, String maDoituong, Date ngayBatDauBh, Date ngayHetHanBh, double tyleBhApDung, double tyleBhApDung_CapCuu) {
		if (clslist.size() < 1 ) return;
		ClsKhamDelegate clsKhamDel = ClsKhamDelegate.getInstance();
		Short lanKham = 0;
		Short tra = 0;
		try {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date ngayThucHienCLS = null;
			//Calendar cal1 = Calendar.getInstance();
			boolean bChiPhiCapCuu = false;
			for (ClsKham clskham : clslist) {			
				// can lam sang da thanh toan tien roi(khong phu thuoc ty le ), khong tinh nua
				if (clskham.getClskhamDatt() != null && clskham.getClskhamDatt().booleanValue() == true){				
					continue;
				}
			//  Khong cap nhat don gia benh nhan tra cho cac CLS ky thuat cao & Van chuyen (da cap ham rieng cap nhat cho 2 loai CLS nay) 
				if ((clskham.getClskhamKtcao() != null && clskham.getClskhamKtcao().booleanValue() == true) || 							
					(clskham.getClskhamMaloai() != null && clskham.getClskhamMaloai().getDtdmclsMaso().intValue() == 12)){ // 12 la ma so cua can lam san van chuyen
					continue;
				} 
				lanKham = clskham.getClskhamLan();
				if (lanKham == null) {
					lanKham = 0;
				}
				clskham.setClskhamLan(lanKham);
							
				tra = clskham.getClskhamTra();
				if (tra == null){
					tra = 0;
				}
				ngayThucHienCLS = (clskham.getClskhamNgaygio() == null ? null : df.parse(df.format(clskham.getClskhamNgaygio())));
				
				//cal1.setTime(ngayThucHienCLS);
				//cal1.set(Calendar.HOUR, 0);
				//cal1.set(Calendar.MINUTE, 0);
				//cal1.set(Calendar.SECOND, 0);
				//cal1.set(Calendar.MILLISECOND, 0);
				//ngayThucHienCLS = cal1.getTime();
				ngayThucHienCLS = Utils.removeHourFromDate(ngayThucHienCLS);
				double giaCLS = clskham.getClskhamDongia() * (lanKham - tra);
				// Kiem tra co phai la CLS cap cuu hay khong
				try {
					bChiPhiCapCuu = clskham.getClskhamThamkham().getThamkhamBankham().getDtdmbankhamMa().startsWith("CC");
				} catch (Exception ex) {
					log.info("ERROR Kiem tra co phai la CLS cap cuu hay khong: " + ex.toString());
					ex.printStackTrace();
					bChiPhiCapCuu = false;
				}
				// cap nhat don gia benh nhan tra cho tung CLS thuong				
				if (maDoituong.equalsIgnoreCase("BH")) {
					if (ngayBatDauBh == null || ngayHetHanBh == null){
						clskham.setClskhamDongiabntra(giaCLS);
					} else if ((clskham.getClskhamNdm() != null && clskham.getClskhamNdm().booleanValue() == true) ||
							ngayThucHienCLS.before(ngayBatDauBh) || ngayThucHienCLS.after(ngayHetHanBh)){
						clskham.setClskhamDongiabntra(giaCLS);
					} else if (clskham.getClskhamYeucau() != null && clskham.getClskhamYeucau().booleanValue() == true) {									
						double giaCLSDv = clskham.getClskhamPhandv()*(lanKham - tra);
						double giaCLSBh = clskham.getClskhamDongiabh()*(lanKham - tra);
						if(bChiPhiCapCuu) {
							//giaCLS = giaCLSDv + (giaCLSBh - (giaCLSBh * (tyleBhApDung_CapCuu /100)));
							giaCLS = giaCLSDv + (giaCLSBh - roundDoubleNumber(new Double(giaCLSBh * (tyleBhApDung_CapCuu /100))));							
						} else {
							//giaCLS = giaCLSDv + (giaCLSBh - (giaCLSBh * (tyleBhApDung /100)));
							giaCLS = giaCLSDv + (giaCLSBh - roundDoubleNumber(new Double(giaCLSBh * (tyleBhApDung /100))));
							
						}
						clskham.setClskhamDongiabntra(giaCLS);
						tongChiPhiThuocCLSBnTra_TyleBH += clskham.getClskhamDongiabntra();
					} else {	
						//clskham.setClskhamDongiabntra(giaCLS - (giaCLS * (tyleBhApDung /100) ));
						clskham.setClskhamDongiabntra(giaCLS - roundDoubleNumber(new Double(giaCLS * (tyleBhApDung /100))));
						tongChiPhiThuocCLSBnTra_TyleBH += clskham.getClskhamDongiabntra();
					}
				} else {
					clskham.setClskhamDongiabntra(giaCLS);
				}
				//clskham.setClskhamNgaygiocn(new Date());
				//tongChiPhiThuocCLSBnTra += clskham.getClskhamDongiabntra();
				clsKhamDel.edit(clskham);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public void capNhatThuocPhongKham(List<ThuocPhongKham> tpklist, String loaiThuocPhongKham, String maDoituong, Date ngayBatDauBh, Date ngayHetHanBh, double tyleBhApDung, double tyleBhApDung_CapCuu) {
		if (tpklist.size() < 1 ) return;
		ThuocPhongKhamDelegate tpkDel = ThuocPhongKhamDelegate.getInstance();
		double giaThuoc;		
		Date ngayNhanThuoc;
		//Calendar cal1 = Calendar.getInstance();
		boolean bChiPhiCapCuu = false;
		for (ThuocPhongKham tpk : tpklist) {			
			// thuoc da thanh toan tien roi(khong phu thuoc ty le ), khong tinh nua
			if (tpk.getThuocphongkhamDatt() != null && tpk.getThuocphongkhamDatt().booleanValue() == true){				
				continue;
			}
			// cap nhat don gia benh nhan tra cho tung loai thuoc	
			//giaThuoc = (tpk.getThuocphongkhamDongia() == null ? 0 : tpk.getThuocphongkhamDongia())*(tpk.getThuocphongkhamSoluong() == null ? 0 : tpk.getThuocphongkhamSoluong());			
			giaThuoc = tpk.getThuocphongkhamThanhtien();
			ngayNhanThuoc = tpk.getThuocphongkhamNgaygio();
			
			// Kiem tra co phai la Thuoc cap cuu hay khong
			try {
				bChiPhiCapCuu = tpk.getThuocphongkhamThamkham().getThamkhamBankham().getDtdmbankhamMa().startsWith("CC");
			} catch (Exception ex) {
				log.info("ERROR Kiem tra co phai la Thuoc cap cuu hay khong: " + ex.toString());
				ex.printStackTrace();
				bChiPhiCapCuu = false;
			}	
			ngayNhanThuoc = Utils.removeHourFromDate(ngayNhanThuoc);
			if (Utils.XU_TRI_THUOC_TAI_BAN_KHAM.equals(loaiThuocPhongKham)){				
								
				if (maDoituong.equalsIgnoreCase("BH")) {
					
					if (ngayBatDauBh == null || ngayHetHanBh == null) {
						// Khong co thong tin thoi han bao hiem, benh nhan tra
						tpk.setThuocphongkhamTienbntra(giaThuoc);
					}else if(tpk.getThuocdongyNgoaiTru() != null 
							&& IConstantsRes.DONG_Y_APPLY_ALL.equals("1") 							
							&& !ngayNhanThuoc.before(ngayBatDauBh) 
							&& !ngayNhanThuoc.after(ngayHetHanBh)) {
						    // So tien benh tra cho tung vi thuoc trong bai thuoc la 0 trong truong hop thuoc tinh theo thang
						    tpk.setThuocphongkhamTienbntra(new Double(0));
					}else if (ngayNhanThuoc.before(ngayBatDauBh) || ngayNhanThuoc.after(ngayHetHanBh) 
							|| (tpk.getThuocphongkhamNdm() != null && tpk.getThuocphongkhamNdm().booleanValue() == true) 
							|| (tpk.getThuocphongkhamYeucau() != null && tpk.getThuocphongkhamYeucau().booleanValue() == true)) {
						// Het han bao hiem, benh nhan tra
						tpk.setThuocphongkhamTienbntra(giaThuoc);
					} else {
						if(bChiPhiCapCuu) {
							tpk.setThuocphongkhamTienbntra(giaThuoc - roundDoubleNumber(new Double(giaThuoc * (tyleBhApDung_CapCuu /100))));							
						} else {
							tpk.setThuocphongkhamTienbntra(giaThuoc - roundDoubleNumber(new Double(giaThuoc * (tyleBhApDung /100))));							
						}
						tongChiPhiThuocCLSBnTra_TyleBH += tpk.getThuocphongkhamTienbntra();
					}
				} else {
					tpk.setThuocphongkhamTienbntra(giaThuoc);
				}
								
			} else {
				if (maDoituong.equalsIgnoreCase("BH")) {
					if (ngayBatDauBh == null || ngayHetHanBh == null) {
						// Khong co thong tin thoi han bao hiem, benh nhan tra
						tpk.setThuocphongkhamTienbntra(giaThuoc);
					}else if (ngayNhanThuoc.before(ngayBatDauBh) || ngayNhanThuoc.after(ngayHetHanBh) 
							|| (tpk.getThuocphongkhamNdm() != null && tpk.getThuocphongkhamNdm().booleanValue() == true) 
							|| (tpk.getThuocphongkhamYeucau() != null && tpk.getThuocphongkhamYeucau().booleanValue() == true)) {
						// Het han bao hiem, benh nhan tra
						tpk.setThuocphongkhamTienbntra(giaThuoc);
					} else {
						if(bChiPhiCapCuu) {
							tpk.setThuocphongkhamTienbntra(giaThuoc - roundDoubleNumber(new Double(giaThuoc * (tyleBhApDung_CapCuu /100))));
						} else {
							tpk.setThuocphongkhamTienbntra(giaThuoc - roundDoubleNumber(new Double(giaThuoc * (tyleBhApDung /100))));;
						}
						tongChiPhiThuocCLSBnTra_TyleBH += tpk.getThuocphongkhamTienbntra();
					}
				} else {
					tpk.setThuocphongkhamTienbntra(giaThuoc);
				}
			}
			tpk.setThuocphongkhamTylebh(tyleBhApDung);
			//tongChiPhiThuocCLSBnTra += tpk.getThuocphongkhamTienbntra();
			tpkDel.edit(tpk);
		}
	}
	//phuc.lc 20-04-2011: Fix bug 2726
	// Doi ten ham
	// doi tu void sang double
	private double tinhTongMienGiamCls(List<ClsKham> clslist, String maMienGiam, Date ngayBatDauMG, Date ngayKetThucMG) {
		
		if (clslist.size() < 1 || maMienGiam.trim().length() < 1 || ngayBatDauMG == null || ngayKetThucMG == null) return 0.0;
		double mienGiamCls = 0.0;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			//Calendar cal1 = Calendar.getInstance();
			for (ClsKham clskham : clslist) {			
				// can lam sang da thanh toan tien roi(khong phu thuoc ty le ), khong tinh nua
				if (clskham.getClskhamDatt() != null && clskham.getClskhamDatt().booleanValue() == true){				
					continue;
				}
				Date ngayThHienCLS = df.parse(df.format(clskham.getClskhamNgaygiocn()));
				
				//cal1.setTime(ngayThHienCLS);
				//cal1.set(Calendar.HOUR, 0);
				//cal1.set(Calendar.MINUTE, 0);
				//cal1.set(Calendar.SECOND, 0);
				//cal1.set(Calendar.MILLISECOND, 0);
				//ngayThHienCLS = cal1.getTime();
				ngayThHienCLS = Utils.removeHourFromDate(ngayThHienCLS);
				String maCLS = clskham.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa();
				if((ngayThHienCLS.after(ngayBatDauMG) || ngayThHienCLS.equals(ngayBatDauMG)) 
						&& (ngayThHienCLS.before(ngayKetThucMG) || ngayThHienCLS.equals(ngayKetThucMG))) {
					// vi moi benh nhan chi co mot che do mien giam, nen ma mien giam o day khong thay doi
					// chi thay doi ma cua tung loai CLS
					if ((maMienGiam.equalsIgnoreCase("02B")   // Miễn giảm phần trăm (không miễn máu, giường)
							&& ( !maCLS.equalsIgnoreCase("MA"))  && (!maCLS.equalsIgnoreCase("GI")) ) ||
						(maMienGiam.equalsIgnoreCase("1")   //  Miễn ngày giường điều trị
							&& (maCLS.equalsIgnoreCase("GI")) ) ||
						(maMienGiam.equalsIgnoreCase("4")   //  Miễn % tiền máu
							&& (maCLS.equalsIgnoreCase("MA")) )	||
						(maMienGiam.equalsIgnoreCase("6")   //  Miễn tiền kỹ thuật cao
							&& (maCLS.equalsIgnoreCase("KT")) )	||
						(maMienGiam.equalsIgnoreCase("ALL"))   //  Miễn tiền kỹ thuật cao								 
							){
						//tongMienGiam += clskham.getClskhamDongiabntra();
						mienGiamCls += clskham.getClskhamDongiabntra();
					} 
				}
			}
		}catch (Exception e) {
			log.info("ERROR : " + e.toString());			
			e.printStackTrace();
			mienGiamCls = 0.0;
		}		
		return mienGiamCls;
	}
	private void tinhChiPhiThuoc(List<ThuocPhongKham> listTpk, String loaiThuocPhongKham, String maDoituong, Date ngayBatDauBh, Date ngayHetHanBh){
		/*
		 * Mục đích : Tinh các giá trị sau : 
		 * 	tongChiPhiThuocBnTra, tongChiPhiThuocBhTra , vTTHNDM	,vTTHTrongDM, thuocNDM, thuocTrongDM		
		 */
		//log.info("tinhToanThuoc , listTpk:" + listTpk);
		if (listTpk == null || listTpk.size() == 0){
			return ;
		}
		//reset ();
		boolean bChiPhiCapCuu = false;
		for (ThuocPhongKham tpk: listTpk){
			//
			// Neu la doi tuong bao hiem va thuoc dong y duoc tinh theo thang thi khong cong tien thuoc tung vi 
			// vi duoc tinh theo don gia cua thang thuoc
			Date ngayNhanThuoc = Utils.removeHourFromDate(tpk.getThuocphongkhamNgaygio());
			if(maDoituong.equalsIgnoreCase("BH") 
					&& IConstantsRes.DONG_Y_APPLY_ALL.equals("1") 
					&& tpk.getThuocdongyNgoaiTru() != null 
					&& ngayBatDauBh != null && ngayHetHanBh != null
					&& !ngayNhanThuoc.before(ngayBatDauBh) 
					&& !ngayNhanThuoc.after(ngayHetHanBh)) continue;
			
			//double giaThuoc = (tpk.getThuocphongkhamDongia() == null ? 0 : tpk.getThuocphongkhamDongia())*(tpk.getThuocphongkhamSoluong()==null ? 0 : tpk.getThuocphongkhamSoluong());				
			double giaThuoc = tpk.getThuocphongkhamThanhtien();
			// Kiem tra co phai la Thuoc cap cuu hay khong
			try {
				bChiPhiCapCuu = tpk.getThuocphongkhamThamkham().getThamkhamBankham().getDtdmbankhamMa().startsWith("CC");
			} catch (Exception ex) {
				log.info("ERROR Kiem tra co phai la Thuoc cap cuu hay khong: " + ex.toString());
				ex.printStackTrace();
				bChiPhiCapCuu = false;
			}								
			if (Utils.XU_TRI_THUOC_TAI_BAN_KHAM.equals(loaiThuocPhongKham)){
				// thuoc da tra~ lai, bn ko su dung, ko tinh nua
				if (tpk.getThuocphongkhamMaphieuh() != null && !tpk.getThuocphongkhamMaphieuh().equals("")){
					continue;
				} 
				// thuoc da thanh toan cong vao phi DaThanhToan va chi phi BN tra
				if (tpk.getThuocphongkhamDatt() != null && tpk.getThuocphongkhamDatt() == true ){ 
					//tongChiPhiThuocBnTra += giaThuoc;
					tongChiPhiThuocBnTra += (tpk.getThuocphongkhamTienbntra() == null ? 0.0 : tpk.getThuocphongkhamTienbntra());
					//DaThanhToan += giaThuoc;	
					DaThanhToan += (tpk.getThuocphongkhamTienbntra() == null ? 0.0 : tpk.getThuocphongkhamTienbntra());
				}else if ((tpk.getThuocphongkhamNdm() != null && tpk.getThuocphongkhamNdm().booleanValue() == true) || 
					(tpk.getThuocphongkhamYeucau() != null && tpk.getThuocphongkhamYeucau().booleanValue() == true)){
					tongChiPhiThuocBnTra += giaThuoc;
				}else if (tpk.getThuocphongkhamKhongthu() != null && tpk.getThuocphongkhamKhongthu().booleanValue() == true) {
					// Thuoc Khong thu thi tinh vao mien giam
					tongMienGiam += giaThuoc;
				} else {
					
					if (maDoituong.equalsIgnoreCase("BH")) {
						if (ngayBatDauBh == null || ngayHetHanBh == null) {
							// Khong co thong tin thoi han bao hiem, benh nhan tra
							tongChiPhiThuocBnTra += giaThuoc;
						}else if (ngayNhanThuoc.before(ngayBatDauBh) || ngayNhanThuoc.after(ngayHetHanBh)) {
							// Het han bao hiem, benh nhan tra
							tongChiPhiThuocBnTra += giaThuoc;
						} else {
							if(bChiPhiCapCuu) {
								tongChiPhiThuocBhTra_CapCuu += giaThuoc;
							} else {
								tongChiPhiThuocBhTra += giaThuoc;
							}
						}
					//phuc.lc : 14-05-2011 : Fix bug 2941, cap nhat lai cach tinh gia thuoc doi voi doi tuong mien phi	
					}else if (maDoituong.equalsIgnoreCase("MP")) {
						if ((tpk.getThuocphongkhamMien() != null && tpk.getThuocphongkhamMien().booleanValue() == true)) {
							// Doi tuong mien phi su dung thuoc mien phi thi KHONG tinh vao mien giam
							//tongMienGiam += giaThuoc;
						} else {
							tongChiPhiThuocBnTra += giaThuoc;
						}					
					} else {
						tongChiPhiThuocBnTra += giaThuoc;
					}
				}				
			} else {
				if ((tpk.getThuocphongkhamNdm() != null && tpk.getThuocphongkhamNdm().booleanValue() == true) || 
						(tpk.getThuocphongkhamYeucau() != null && tpk.getThuocphongkhamYeucau().booleanValue() == true)){
						tongChiPhiThuocBnTra += giaThuoc;
					
				}else if (tpk.getThuocphongkhamKhongthu() != null && tpk.getThuocphongkhamKhongthu().booleanValue() == true) {
					// Thuoc Khong thu thi tinh vao mien giam
					tongMienGiam += giaThuoc;
				}else if (maDoituong.equalsIgnoreCase("BH")) {
					if (ngayBatDauBh == null || ngayHetHanBh == null) {
						// Khong co thong tin thoi han bao hiem, benh nhan tra
						tongChiPhiThuocBnTra += giaThuoc;
					} else if (ngayNhanThuoc.before(ngayBatDauBh) || ngayNhanThuoc.after(ngayHetHanBh)) {
						// Het han bao hiem, benh nhan tra
						tongChiPhiThuocBnTra += giaThuoc;
					} else {
						if(bChiPhiCapCuu) {
							tongChiPhiThuocBhTra_CapCuu += giaThuoc;
						} else {
							tongChiPhiThuocBhTra += giaThuoc;
						}
					}
				//phuc.lc : 14-05-2011 : Fix bug 2941, cap nhat lai cach tinh gia thuoc doi voi doi tuong mien phi	
				}else if (maDoituong.equalsIgnoreCase("MP")) {
					if ((tpk.getThuocphongkhamMien() != null && tpk.getThuocphongkhamMien().booleanValue() == true)) {
						// Doi tuong mien phi su dung thuoc mien phi thi KHONG tinh vao mien giam
						//tongMienGiam += giaThuoc;
					} else {
						tongChiPhiThuocBnTra += giaThuoc;
					}	
				} else {
					tongChiPhiThuocBnTra += giaThuoc;
				}
			}
			
			// phan tinh mo rong cho thanh toan cc
			if ((tpk.getThuocphongkhamNdm() != null && tpk.getThuocphongkhamNdm().booleanValue() == true) || 
					(tpk.getThuocphongkhamYeucau() != null && tpk.getThuocphongkhamYeucau().booleanValue() == true)){
				//phuc.lc : 09/09/2011 Fix loi khong khop so lieu thuoc va vat tu tieu hao do danh muc khong giong nhau
				if (tpk.getThuocphongkhamMathuoc()!=null 
						&& tpk.getThuocphongkhamMathuoc().getDmthuocPlbhyt() != null
						&& tpk.getThuocphongkhamMathuoc().getDmthuocPlbhyt().intValue() == 10 ){  // 10 : nhom vat tu tieu hao
					//VTTH
					vTTHNDM = vTTHNDM + giaThuoc;
				}else{
					thuocNDM = thuocNDM + giaThuoc;
				}
				
			}else{
				if (tpk.getThuocphongkhamMathuoc()!=null 
						&& tpk.getThuocphongkhamMathuoc().getDmthuocPlbhyt() != null
						&& tpk.getThuocphongkhamMathuoc().getDmthuocPlbhyt().intValue() == 10 ){ // 10 : nhom vat tu tieu hao
				//VTTH
					vTTHTrongDM = vTTHTrongDM + giaThuoc;
				}else{
					thuocTrongDM = thuocTrongDM + giaThuoc;
				}
			}
			// end phan tinh mo rong cho thanh toan cc
			
		}				
	}
	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		HoSoThanhToanKhamUtil.log = log;
	}

//	public int getPermiengiam() {
//		return permiengiam;
//	}
//
//	public void setPermiengiam(int permiengiam) {
//		this.permiengiam = permiengiam;
//	}

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

	

	public TiepDon getTiepDon() {
		return tiepDon;
	}

	public void setTiepDon(TiepDon tiepDon) {
		this.tiepDon = tiepDon;
	}

	public Double getThuocTrongDM() {
		return thuocTrongDM;
	}

	public void setThuocTrongDM(Double thuocTrongDM) {
		this.thuocTrongDM = thuocTrongDM;
	}

	public Double getThuocNDM() {
		return thuocNDM;
	}

	public void setThuocNDM(Double thuocNDM) {
		this.thuocNDM = thuocNDM;
	}

	public Double getVTTHTrongDM() {
		return vTTHTrongDM;
	}

	public void setVTTHTrongDM(Double trongDM) {
		vTTHTrongDM = trongDM;
	}

	public Double getVTTHNDM() {
		return vTTHNDM;
	}

	public void setVTTHNDM(Double vtthndm) {
		vTTHNDM = vtthndm;
	}

	public Double getCLSMauTrongDM() {
		return cLSMauTrongDM;
	}

	public void setCLSMauTrongDM(Double mauTrongDM) {
		cLSMauTrongDM = mauTrongDM;
	}

	public Double getClsMauNDM() {
		return clsMauNDM;
	}

	public void setClsMauNDM(Double clsMauNDM) {
		this.clsMauNDM = clsMauNDM;
	}

//	public Double getPTTTTrongDM() {
//		return pTTTTrongDM;
//	}
//
//	public void setPTTTTrongDM(Double trongDM) {
//		pTTTTrongDM = trongDM;
//	}
//
//	public Double getPTTTNDM() {
//		return pTTTNDM;
//	}
//
//	public void setPTTTNDM(Double ptttndm) {
//		pTTTNDM = ptttndm;
//	}

	

	public Double getUng() {
		return ung;
	}

	public void setUng(Double ung) {
		this.ung = ung;
	}

	public Double getTra() {
		return tra;
	}

	public void setTra(Double tra) {
		this.tra = tra;
	}

	public Double getSoDu() {
		return soDu;
	}

	public void setSoDu(Double soDu) {
		this.soDu = soDu;
	}

	public Double getKhongThu() {
		return khongThu;
	}

	public void setKhongThu(Double khongThu) {
		this.khongThu = khongThu;
	}

	public Double getCLSPhongTrongDM() {
		return cLSPhongTrongDM;
	}

	public void setCLSPhongTrongDM(Double phongTrongDM) {
		cLSPhongTrongDM = phongTrongDM;
	}

	public Double getClsPhongNDM() {
		return clsPhongNDM;
	}

	public void setClsPhongNDM(Double clsPhongNDM) {
		this.clsPhongNDM = clsPhongNDM;
	}

	public double getMinktc() {
		return minktc;
	}

	public void setMinktc(double minktc) {
		this.minktc = minktc;
	}

	public double getMaxktc() {
		return maxktc;
	}

	public void setMaxktc(double maxktc) {
		this.maxktc = maxktc;
	}

	public int getTylebhyt() {
		return tylebhyt;
	}

	public void setTylebhyt(int tylebhyt) {
		this.tylebhyt = tylebhyt;
	}

	public int getTylebhyt2() {
		return tylebhyt2;
	}

	public void setTylebhyt2(int tylebhyt2) {
		this.tylebhyt2 = tylebhyt2;
	}

	public double getTyleminktc() {
		return tyleminktc;
	}

	public void setTyleminktc(double tyleminktc) {
		this.tyleminktc = tyleminktc;
	}

	public double getTylemaxktc() {
		return tylemaxktc;
	}

	public void setTylemaxktc(double tylemaxktc) {
		this.tylemaxktc = tylemaxktc;
	}

	public int getTyleKTC() {
		return tyleKTC;
	}

	public void setTyleKTC(int tyleKTC) {
		this.tyleKTC = tyleKTC;
	}

	public double getGioiHanTyLe() {
		return gioiHanTyLe;
	}

	public void setGioiHanTyLe(double gioiHanTyLe) {
		this.gioiHanTyLe = gioiHanTyLe;
	}

	public DtDmKhoiBhyt getKhoiBHYT() {
		return khoiBHYT;
	}

	public void setKhoiBHYT(DtDmKhoiBhyt khoiBHYT) {
		this.khoiBHYT = khoiBHYT;
	}

	public Double getMienTE() {
		return mienTE;
	}

	public void setMienTE(Double mienTE) {
		this.mienTE = mienTE;
	}
	public boolean isbVuottuyen() {
		return bVuottuyen;
	}
	public void setbVuottuyen(boolean bVuottuyen) {
		this.bVuottuyen = bVuottuyen;
	}
	public double getTongChiPhiCLSBhTra() {
		return tongChiPhiCLSBhTra;
	}
	public void setTongChiPhiCLSBhTra(double tongChiPhiCLSBhTra) {
		this.tongChiPhiCLSBhTra = tongChiPhiCLSBhTra;
	}
	public double getTongChiPhiThuocBhTra() {
		return tongChiPhiThuocBhTra;
	}
	public void setTongChiPhiThuocBhTra(double tongChiPhiThuocBhTra) {
		this.tongChiPhiThuocBhTra = tongChiPhiThuocBhTra;
	}
	public double getTongChiPhiCLSBhTra_CapCuu() {
		return tongChiPhiCLSBhTra_CapCuu;
	}
	public void setTongChiPhiCLSBhTra_CapCuu(double tongChiPhiCLSBhTraCapCuu) {
		tongChiPhiCLSBhTra_CapCuu = tongChiPhiCLSBhTraCapCuu;
	}
	public double getTongChiPhiVCBhTra_CapCuu() {
		return tongChiPhiVCBhTra_CapCuu;
	}
	public void setTongChiPhiVCBhTra_CapCuu(double tongChiPhiVCBhTraCapCuu) {
		tongChiPhiVCBhTra_CapCuu = tongChiPhiVCBhTraCapCuu;
	}
	public double getTongChiPhiKTCBhTra_CapCuu() {
		return tongChiPhiKTCBhTra_CapCuu;
	}
	public void setTongChiPhiKTCBhTra_CapCuu(double tongChiPhiKTCBhTraCapCuu) {
		tongChiPhiKTCBhTra_CapCuu = tongChiPhiKTCBhTraCapCuu;
	}
	public double getTongChiPhiThuocBhTra_CapCuu() {
		return tongChiPhiThuocBhTra_CapCuu;
	}
	public void setTongChiPhiThuocBhTra_CapCuu(double tongChiPhiThuocBhTraCapCuu) {
		tongChiPhiThuocBhTra_CapCuu = tongChiPhiThuocBhTraCapCuu;
	}
	public HsThtoank getHosoTTK() {
		return hosoTTK;
	}
	public void setHosoTTK(HsThtoank hosoTTK) {
		this.hosoTTK = hosoTTK;
	}
	
	

}

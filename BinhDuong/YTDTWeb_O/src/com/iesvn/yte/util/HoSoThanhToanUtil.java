package com.iesvn.yte.util;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.iesvn.yte.dieutri.delegate.ChuyenVaoNoiTruDelegate;
import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
import com.iesvn.yte.dieutri.delegate.ClsMoDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmMqlBhytDelegate;
import com.iesvn.yte.dieutri.delegate.HoanUngDelegate;
import com.iesvn.yte.dieutri.delegate.HoanUngKhamDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
import com.iesvn.yte.dieutri.delegate.MienGiamDelegate;
import com.iesvn.yte.dieutri.delegate.TamUngDelegate;
import com.iesvn.yte.dieutri.delegate.TamUngKhamDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocDongYNoiTruDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocNoiTruDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
import com.iesvn.yte.dieutri.entity.ChuyenVaoNoiTru;
import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.ClsMo;
import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
import com.iesvn.yte.dieutri.entity.DtDmMqlBhyt;
import com.iesvn.yte.dieutri.entity.HsThtoan;
import com.iesvn.yte.dieutri.entity.HsThtoank;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.MienGiam;
import com.iesvn.yte.dieutri.entity.ThuocDongYNoiTru;
import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
import com.iesvn.yte.dieutri.entity.TiepDon;

public class HoSoThanhToanUtil {
	
			
		private static Logger log = Logger.getLogger(HoSoThanhToanUtil.class);
		
		// Phan danh cho tinh toan
		private int permiengiam = 0;
		private Double miengiam = new Double(0);
		private Double thatthu = new Double(0);
		private Double perbhytchi = new Double(0);
		private Double bhytchi = new Double(0);
		private Double thanhtien1 = new Double(0);
		private int perbntra = 0;
		private Double bntra = new Double(0);
		private Hsba hsba;
		
		private MienGiamDelegate miengiamDelegate = MienGiamDelegate.getInstance();
		private List <MienGiam> lstMienGiam = new ArrayList<MienGiam>();
		
		Double clsNDM = new Double(0);
		Double clsTrongDM = new Double(0);
		
		
		//
		private Double khongThu = new Double(0);
		
		// Thong tin can thiet cho ho so thanh toan kham
		Double thuocTrongDM =  new Double(0);
		Double thuocNDM = new Double(0);
		Double vTTHTrongDM = new Double(0);
		Double vTTHNDM = new Double(0);
		Double cLSMauTrongDM = new Double(0);
		Double clsMauNDM = new Double(0);
		Double cLSPhongTrongDM = new Double(0);
		Double clsPhongNDM = new Double(0);
		
//		Double pTTTTrongDM = new Double(0);
//		Double pTTTNDM = new Double(0);
		
		
		
		Double tienCongKham = new Double(0);
		Double chanDoanHinhAnh = new Double(0);
		Double dichVuKTThongThuong = new Double(0);
		Double dichVuKTC = new Double(0);
		Double chiPhiVC = new Double(0);
		Double xetNghiemTDCN = new Double(0);
		
		Double ung = new Double(0);
		Double tra = new Double(0);
		Double soDu = new Double(0); // = bntra - ung + tra	
		// phuc.lc 12-12-2010	BEGIN
		Double chiphiPT = new Double(0);
		Double chiphiTT = new Double(0);
		// END
		private Double mienTE = new Double(0);
		
		//
		//public double tongChiPhi = 0;
		// phuc.lc 06-10-2010
		
		private double tongChiPhiCLSBhTra = 0;
		private double tongChiPhiCLSBnTra = 0;
		//private double tongTienThuoc = 0;
		private double tongChiPhiVCBhTra = 0;
		private double tongChiPhiVCBnTra = 0;
		private double tongChiPhiKTCBhTra = 0;
		private double tongChiPhiKTCBnTra = 0;
		private double tongChiPhiThuocBhTra = 0;
		private double tongChiPhiThuocBnTra = 0;
		private double tongChiPhiThuocCLSBnTra_TyleBH = 0;
		private double tongMienGiam = 0;
		private boolean bDoituongUutien = false;
		private boolean bVuottuyen = false;
		public Double DaThanhToan = new Double(0);
		private HsThtoank hsttk;
		
		private HsbaBhyt hsbaBHYT;
		
		DtDmKhoiBhyt khoiBHYT;
		

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
		public  HoSoThanhToanUtil(Hsba hsba){
			log.info("----- HoSoThanhToanUtil -----");
			this.hsba = hsba;
			HsbaBhytDelegate hsbaBHYTDelegate = HsbaBhytDelegate.getInstance();
			hsbaBHYT = hsbaBHYTDelegate.findBySoVaoVienLastHsbaBhyt(hsba.getHsbaSovaovien());
			
			lstMienGiam = miengiamDelegate.getBySoVaoVien(hsba.getHsbaSovaovien());
			if (lstMienGiam == null){
				lstMienGiam = new ArrayList<MienGiam>(); 
			}
			if (hsbaBHYT != null) {
				khoiBHYT = hsbaBHYT.getHsbabhytKhoibh();
				if (khoiBHYT != null){
					if (khoiBHYT.getDtdmkhoibhytMinKTC() != null) {
						minktc = khoiBHYT.getDtdmkhoibhytMinKTC();
					}
					if (khoiBHYT.getDtdmkhoibhytMaxKTC() != null) {
						maxktc = khoiBHYT.getDtdmkhoibhytMaxKTC();
					}
//					if (khoiBHYT.getDtdmkhoibhytTylebhyt1() != null) {
//						tylebhyt = khoiBHYT.getDtdmkhoibhytTylebhyt1();
//					}
//					if (khoiBHYT.getDtdmkhoibhytTylebhyt2() != null) {
//						tylebhyt2 = khoiBHYT.getDtdmkhoibhytTylebhyt2();
//					}
					if (khoiBHYT.getDtdmkhoibhytTLMinKTC() != null) {
						tyleminktc = khoiBHYT.getDtdmkhoibhytTLMinKTC();
					}
					if (khoiBHYT.getDtdmkhoibhytTLMaxKTC() != null) {
						tylemaxktc = khoiBHYT.getDtdmkhoibhytTLMaxKTC();
					}
					if (khoiBHYT.getDtdmkhoibhytTylektc() != null) {
						tyleKTC = khoiBHYT.getDtdmkhoibhytTylektc();
					}
					if (khoiBHYT.getDtdmkhoibhytGHTyLeNoiTru() != null) {
						gioiHanTyLe_noi_tru = khoiBHYT.getDtdmkhoibhytGHTyLeNoiTru();
					}
					
					if (khoiBHYT.getDtdmkhoibhytTLBHYT1_NoiTru() != null) {
						tylebhyt_noi_tru = khoiBHYT.getDtdmkhoibhytTLBHYT1_NoiTru();
					}
					if (khoiBHYT.getDtdmkhoibhytTLBHYT2_NoiTru() != null) {
						tylebhyt2_noi_tru = khoiBHYT.getDtdmkhoibhytTLBHYT2_NoiTru();
					}
					if (khoiBHYT.getDtdmkhoibhytGHTyLeNoiTru() != null) {
						gioiHanTyLe_noi_tru = khoiBHYT.getDtdmkhoibhytGHTyLeNoiTru();
					}
					
					// phuc.lc 19-10-2010 : Khong set cac thong so theo khoi bao hiem nua, code duoi day set cac thong so theo ma quyen loi
					// phuc.lc 20-12-2010 : Doi voi benh nhan noi tru thi lay so bao hiem trong bang HsbaBhyt (khong lay tu bang Tiep don)
					//TiepDon tiepDon = TiepDonDelegate.getInstance().findBenhNhanByTiepdonMa(hsba.getTiepdonMa());
					//String strSotheBHYT = (tiepDon.getTiepdonSothebh() == null ?"" : tiepDon.getTiepdonSothebh());
					String strSotheBHYT = (hsbaBHYT.getHsbabhytSothebh() == null ?"" : hsbaBHYT.getHsbabhytSothebh());
					if (strSotheBHYT.trim().length() > 3) {
						String strMaQuyenloi = strSotheBHYT.substring(2, 3);
						DtDmMqlBhytDelegate maQLDel = DtDmMqlBhytDelegate.getInstance();
						DtDmMqlBhyt maQL = maQLDel.findByMa(strMaQuyenloi);
						if (maQL != null) {
							// Set lai cac gia tri theo  ma quyen loi
							minktc = (maQL.getDtdmmqlbhytMinktc() == null ? 0 : maQL.getDtdmmqlbhytMinktc());							
							maxktc = (maQL.getDtdmmqlbhytMaxktc() == null ? 0 : maQL.getDtdmmqlbhytMaxktc());								
							tylebhyt_noi_tru = (maQL.getDtdmmqlbhytTylebhyt1NoiTru() == null ? 0 : maQL.getDtdmmqlbhytTylebhyt1NoiTru());				
							tylebhyt2_noi_tru = (maQL.getDtdmmqlbhytTylebhyt2NoiTru() == null ? 0 : maQL.getDtdmmqlbhytTylebhyt2NoiTru());				
							tyleminktc = (maQL.getDtdmmqlbhytTlminktc() == null ? 0 : maQL.getDtdmmqlbhytTlminktc());
							tylemaxktc = (maQL.getDtdmmqlbhytTlmaxktc() == null ? 0 : maQL.getDtdmmqlbhytTlmaxktc());
							tyleKTC = (maQL.getDtdmmqlbhytTylektc() == null ? 0 : maQL.getDtdmmqlbhytTylektc());;
							gioiHanTyLe_noi_tru = (maQL.getDtdmmqlbhytGhtyleNoiTru() == null ? 0 : maQL.getDtdmmqlbhytGhtyleNoiTru());
							//mienphiVC = (maQL.getDtdmmqlbhytVanchuyen() == null ? false : maQL.getDtdmmqlbhytVanchuyen());
							
						}
					}
					
					if("BH".equals(hsba.getDoituongMa(true).getDmdoituongMa())){
						Short tuyen = hsbaBHYT.getHsbabhytTuyen();
						//log.info("---tuyen():"+tuyen);
						
						// xet tuoi
						
						Integer tuoi = hsba.getBenhnhanMa(true).getBenhnhanTuoi();	
						if (tuoi == null){
							tuoi = 0;
						}
						Short donvituoi = hsba.getBenhnhanMa(true).getBenhnhanDonvituoi();
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
							//bDoituongUutien = checkDTUuTien(khoiBHYT.getDtdmkhoibhytMa());
							if (!bDoituongUutien) { 
								// Neu khong phai doi tuong uu tien, thi xet co vuot tuyen hay khong
								if ( tuyen != null && (tuyen == 2 || tuyen ==3)){
																																				
									// la doi tuong ko uu tuyen + ko co giay
									if (hsbaBHYT.getHsbabhytCoGiayChuyenVien() == null
									|| ( hsbaBHYT.getHsbabhytCoGiayChuyenVien() != null && hsbaBHYT.getHsbabhytCoGiayChuyenVien() == false))
									{
										bVuottuyen = true;	
										tylebhyt_noi_tru = 50;  // ty le su dung khi chi phi vuot qua gioi han ty le bao hiem
										tylebhyt2_noi_tru = 50; // ty le su dung khi chi phi khong vuot qua gioi han ty le bao hiem
										if (IConstantsRes.TYLE_BH_VUOT_TUYEN.trim().length() > 0 && IConstantsRes.TYLE_BH2_VUOT_TUYEN.trim().length() > 0) {
											try {
												tylebhyt_noi_tru = Integer.parseInt(IConstantsRes.TYLE_BH_VUOT_TUYEN);
												tylebhyt2_noi_tru = Integer.parseInt(IConstantsRes.TYLE_BH2_VUOT_TUYEN);
											} catch(Exception ex) { 
												tylebhyt_noi_tru = 50;
												tylebhyt2_noi_tru = 50;
											}
										}																				
									}
									
								}
							}
						}
														
					
				}
				

			}else{
				hsbaBHYT = new HsbaBhyt();
			}
			
			Date ngayGioTinhToan_tmp = hsba.getHsbaNgaygiovaov();
			
			
			ngayGioTinhToan = java.util.Calendar.getInstance();
			ngayGioTinhToan.setTime(ngayGioTinhToan_tmp);
			ngayGioTinhToan.set(Calendar.HOUR_OF_DAY, 0);
			ngayGioTinhToan.set(Calendar.MINUTE, 0);
			ngayGioTinhToan.set(Calendar.SECOND, 0);
			
			
			Date ngayBHYT2_tmp = hsbaBHYT.getHsbabhytGiatri2();
			
			if (ngayBHYT2_tmp != null){
				ngayBHYT2 = java.util.Calendar.getInstance();
				ngayBHYT2.setTime(ngayBHYT2_tmp);

			}
			
			Date ngayBHYT3_tmp =  hsbaBHYT.getHsbabhytGiatri3();
			
			if (ngayBHYT3_tmp != null){
				ngayBHYT3 = java.util.Calendar.getInstance();
				ngayBHYT3.setTime(ngayBHYT3_tmp);
				

			}
		}
		java.util.Calendar ngayGioTinhToan = null;
		java.util.Calendar ngayBHYT2 = null;
		java.util.Calendar ngayBHYT3 = null;
		
		double minktc = 0;
		double maxktc = 0;
//		int tylebhyt = 0;
//		int tylebhyt2 = 0;
		double tyleminktc = 0; // ty le min ktc
		double tylemaxktc = 0; // ty le max ktc
		int tyleKTC = 0;
//		double gioiHanTyLe = 0;
		
		double tylebhyt_noi_tru = 0;
		double tylebhyt2_noi_tru = 0;
		double gioiHanTyLe_noi_tru = 0;
		
		
		
		
		
		private void tinhChiPhiThuoc(List<ThuocNoiTru> listTnt, String maDoituong, Date ngayBatDauBh, Date ngayHetHanBh){
			/*
			 * Mục đích : Tinh các giá trị sau : 
			 * 	tongChiPhiThuocBhTra , vTTHNDM	,vTTHTrongDM, thuocNDM, thuocTrongDM		
			 */		
			if (listTnt == null || listTnt.size() == 0){
				return;
			}
			
			//Calendar cal1 = Calendar.getInstance();
			for (ThuocNoiTru tnt: listTnt){
				// phuc.lc 13-06-2011 : Bo qua cac thuoc dong y neu nhu doi tuong la bao hiem va thuoc dong y duoc tinh theo thang
				if(tnt.getThuocdongyNoiTru() != null && maDoituong.equals("BH") && IConstantsRes.DONG_Y_APPLY_ALL.equals("1")) continue;
					
				Double soLuongTra = (tnt.getThuocnoitruTra() == null ? new Double(0) : tnt.getThuocnoitruTra());
				//double giaThuoc = tnt.getThuocnoitruDongia()* ( tnt.getThuocnoitruSoluong() - soLuongTra) ;	
				double giaThuoc = (tnt.getThuocnoitruSoluong() - soLuongTra == 0 ? 0 : tnt.getThuocnoitruThanhtien()) ;
				Date ngayNhanThuoc = tnt.getThuocnoitruNgaygio();
				
				//cal1.setTime(ngayNhanThuoc);
				//cal1.set(Calendar.HOUR, 0);
				//cal1.set(Calendar.MINUTE, 0);
				//cal1.set(Calendar.SECOND, 0);
				//cal1.set(Calendar.MILLISECOND, 0);
				//ngayNhanThuoc = cal1.getTime();
				ngayNhanThuoc = Utils.removeHourFromDate(ngayNhanThuoc);
				if ((tnt.getThuocnoitruNDM() != null && tnt.getThuocnoitruNDM().booleanValue() == true) || 
					(tnt.getThuocnoitruYeucau() != null && tnt.getThuocnoitruYeucau().booleanValue() == true)){
					tongChiPhiThuocBnTra += giaThuoc;
				} else if (tnt.getThuocnoitruKhongthu() != null && tnt.getThuocnoitruKhongthu().booleanValue() == true) {					
					// phuc.lc 04/08/2011 : Neu thuoc la Khong thu thi khong tinh chi phi
					//tongMienGiam += giaThuoc;
					
				} else if (maDoituong.equalsIgnoreCase("BH")) {
					if (ngayBatDauBh == null || ngayHetHanBh == null) {
						tongChiPhiThuocBnTra += giaThuoc;
					} else if (ngayNhanThuoc.before(ngayBatDauBh) || ngayNhanThuoc.after(ngayHetHanBh)) {
						tongChiPhiThuocBnTra += giaThuoc;
					} else {
						tongChiPhiThuocBhTra += giaThuoc;
					}
				//phuc.lc : 14-05-2011 : Fix bug 2941, cap nhat lai cach tinh gia thuoc doi voi doi tuong mien phi	
				}else if (maDoituong.equalsIgnoreCase("MP")) {
					if ((tnt.getThuocnoitruMien() != null && tnt.getThuocnoitruMien().booleanValue() == true)) {
						// Doi tuong mien phi su dung thuoc mien phi thi KHONG tinh vao mien giam
						//tongMienGiam += giaThuoc;
					} else {
						tongChiPhiThuocBnTra += giaThuoc;
					}
				}
				else {
					tongChiPhiThuocBnTra += giaThuoc;
				}
				// phuc.lc 04/08/2011 : Neu thuoc la Khong thu thi khong tinh chi phi
				if (!(tnt.getThuocnoitruKhongthu() != null && tnt.getThuocnoitruKhongthu().booleanValue() == true)) {
					if ((tnt.getThuocnoitruNDM() != null && tnt.getThuocnoitruNDM().booleanValue() == true) || 
							(tnt.getThuocnoitruYeucau() != null && tnt.getThuocnoitruYeucau().booleanValue() == true)){
						if (tnt.getThuocnoitruMathuoc()!=null 
								&& tnt.getThuocnoitruMathuoc().getDmthuocPlbhyt()!=null 
								&& tnt.getThuocnoitruMathuoc().getDmthuocPlbhyt().intValue() == 10 ){
							//VTTH
							vTTHNDM = vTTHNDM + giaThuoc;
						}else{
							thuocNDM = thuocNDM + giaThuoc;
						}
						
					}else{
						if (tnt.getThuocnoitruMathuoc()!=null 
								&& tnt.getThuocnoitruMathuoc().getDmthuocPlbhyt()!=null 
								&& tnt.getThuocnoitruMathuoc().getDmthuocPlbhyt().intValue() == 10 ){
						//VTTH
							vTTHTrongDM = vTTHTrongDM + giaThuoc;
						}else{
							thuocTrongDM = thuocTrongDM + giaThuoc;
						}
					}
				}
			}				
		}
		public double miengiamdoituong = 0 ;								
		
		
						
		
		private void tinhTamUng(){
			TamUngDelegate tamUng = TamUngDelegate.getInstance();
			ung = tamUng.getTongTamUng(hsba.getHsbaSovaovien());
			//log.info("tinhTamUng():"+ung);
			
			//khu vuc nay tinh tam ung tu ngoai tru chuyen vao
			TamUngKhamDelegate tamUngKham = TamUngKhamDelegate.getInstance();
			Double ungKham = tamUngKham.getTongTamUng(hsba.getTiepdonMa());
			if (ungKham != null){
				ung = ung + ungKham;
			}
		}
		private void tinhHoanUng(){
			HoanUngDelegate hoanung = HoanUngDelegate.getInstance();
			tra = hoanung.getTongHoanUng(hsba.getHsbaSovaovien());
			
			// khu vuc nay tinh hoan ung ngoai tru chuyen vao
			HoanUngKhamDelegate hoanUngKham = HoanUngKhamDelegate.getInstance();
			Double traKham = hoanUngKham.getTongHoanUng(hsba.getTiepdonMa());
			if (traKham != null){
				tra = tra + traKham;
			}
		}
		
		// Ham cap nhat dongiatt va thanhtien cho cac Thuoc noi tru
		// Ham nay dung de cap nhat cac du lieu cu (truoc ngay 01/10/2011)
		// Viec cap nhat cac thong tin nay se duoc thuc hien o chuc nang chi dinh Thuoc, khi do khong can su dung ham nay nua
//		private List<ThuocNoiTru> capnhatDongiattTnt(List<ThuocNoiTru> listTnt ) {		
//			if (listTnt.size() > 0) {				
//				Double thanhTien = null;
//				for(int i = 0; i < listTnt.size(); i++) {
//					//if(listTnt.get(i).getThuocnoitruDongiatt() == 0 && listTnt.get(i).getThuocnoitruThanhtien() == 0) {
//						if (listTnt.get(i).getThuocnoitruKhongthu() != null && listTnt.get(i).getThuocnoitruKhongthu().booleanValue() == true) {
//							listTnt.get(i).setThuocnoitruDongiatt(0);
//							listTnt.get(i).setThuocnoitruThanhtien(0);
//						}else {
//							listTnt.get(i).setThuocnoitruDongiatt(roundDoubleNumber(listTnt.get(i).getThuocnoitruDongia()).intValue());
//							
//							// Thuoc phong kham tinh theo so luong, so luong co the la so le, can phai lam tron thanh tien
//							thanhTien = ((listTnt.get(i).getThuocnoitruSoluong() - (listTnt.get(i).getThuocnoitruTra() == null ? 0 : listTnt.get(i).getThuocnoitruTra()))* listTnt.get(i).getThuocnoitruDongiatt());
//							listTnt.get(i).setThuocnoitruThanhtien(roundDoubleNumber(thanhTien).intValue());
//						}
//					//}
//				}
//			}
//			return listTnt;
//		}
		
		private List<ThuocDongYNoiTru> capnhatDongiattThuocDongYNoiTru(List<ThuocDongYNoiTru> listThuocDongY) {		
			if (listThuocDongY.size() > 0) {				
				Double thanhTien = null;
				for(int i = 0; i < listThuocDongY.size(); i++) {
					//if(listThuocDongY.get(i).getThuocdongyDongiatt() == 0 && listThuocDongY.get(i).getThuocdongyThanhtien() == 0) {
						listThuocDongY.get(i).setThuocdongyDongiatt(roundDoubleNumber(listThuocDongY.get(i).getThuocdongyDongia()).intValue());
						
						// Thuoc phong kham tinh theo so luong, so luong co the la so le, can phai lam tron thanh tien
						thanhTien = (listThuocDongY.get(i).getThuocdongySoluong() * listThuocDongY.get(i).getThuocdongyDongiatt());
						listThuocDongY.get(i).setThuocdongyThanhtien(roundDoubleNumber(thanhTien).intValue());						
					//}
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
		public void tinhToanChoHSTT(HsThtoan hstt){
			
			ClsMoDelegate clsMoDelegate  = ClsMoDelegate.getInstance();
			
			// lay CLS ve
			
			List<ClsMo> listCtkq = clsMoDelegate.findBySoVaoVien(hsba.getHsbaSovaovien());
			ThuocNoiTruDelegate tntDelegate = ThuocNoiTruDelegate.getInstance();
			
			// lay thuoc ve
			
			List<ThuocNoiTru> lstThuocNT = tntDelegate.findBySoVaoVien(hsba.getHsbaSovaovien());
			//log.info("lstThuocPhongKhamThuocBanKham;"+lstThuocNT);
			Calendar myCal = Calendar.getInstance();
			myCal.set(2011, 11, 15, 0, 0, 0);  // ngay 15/10/2011
			Date dateUpdate = myCal.getTime();
			
			/* phuc.lc BEGIN comment 06-10-2010
			// tinh tong chi phi thuoc + CLS
			
			double tongChiPhi = tinhTongChiPhiCLS(listCtkq);
			tongChiPhi = tongChiPhi + tinhTongChiPhiThuoc(lstThuocNT);
			//////////////////////////////////
			
			
			HoSoThanhToanUtil hsthtoanUtil = new HoSoThanhToanUtil(hsba);
			hsthtoanUtil.tinhToanCLS(listCtkq,tongChiPhi);
			phuc.lc END comment */
			
			// 20120628 bao.ttc: remove vi khong can thiet
			// HoSoThanhToanUtil hsthtoanUtil = new HoSoThanhToanUtil(hsba);
			tongChiPhiCLSBhTra = 0;
			tongChiPhiCLSBnTra = 0;
			//tongTienThuoc = 0;
			tongChiPhiVCBhTra = 0;
			tongChiPhiVCBnTra = 0;
			tongChiPhiKTCBhTra = 0;
			tongChiPhiKTCBnTra = 0;
			tongChiPhiThuocBhTra = 0;
			tongChiPhiThuocBnTra = 0;
			tongChiPhiThuocCLSBnTra_TyleBH = 0;
			boolean mienphiVC = false;
			
			String maDoituong = "";
			Date ngayBatDauBh = null;
			Date ngayHetHanBh = null;
			// Xet ma quyen loi dua theo so the bao hiem
			//if (hsba.getTiepdonMa() != null && hsba.getTiepdonMa().trim().length() > 0) {
			//	TiepDon tiepDon = TiepDonDelegate.getInstance().findBenhNhanByTiepdonMa(hsba.getTiepdonMa());
				
				if(hsbaBHYT != null) {
					// Ma doi tuong bao hiem van lay tu bang Tiep don
					//maDoituong = (tiepDon.getDoituongMa() == null ? "" : tiepDon.getDoituongMa().getDmdoituongMa());		// Luu ma doi tuong : BH, TP hay MP
					// Ma doi tuong bao hiem van lay theo hsba
					maDoituong = (hsba.getDoituongMa() == null ? "" : hsba.getDoituongMa().getDmdoituongMa());
					// phuc.lc 30-12-2010 : begin fix bug 1928
					ngayBatDauBh = hsbaBHYT.getHsbabhytGiatri2(); // Ngay bat dau bao hiem tinh theo gia tri the tai benh vien (luu trong truong HSBABHYT_GIATRI2 cua bang hsba_bhyt)
					// Neu ngay bat dau theo gia tri tai benh vien bang null, thi lay theo ngay bat dau cua the
					if (ngayBatDauBh == null) {
						//ngayBatDauBh = (hsbaBHYT.getHsbabhytGiatri0() == null ? new Date() : hsbaBHYT.getHsbabhytGiatri0());
						ngayBatDauBh = hsbaBHYT.getHsbabhytGiatri0();
					}
					ngayHetHanBh = hsbaBHYT.getHsbabhytGiatri3(); // Ngay het han bao hiem tinh theo gia tri the tai benh vien (luu trong truong HSBABHYT_GIATRI3 cua bang hsba_bhyt)
					// Neu ngay het han theo gia tri tai benh vien bang null, thi lay theo ngay het han cua the
					if (ngayHetHanBh == null) {
						//ngayHetHanBh = (hsbaBHYT.getHsbabhytGiatri1() == null ? new Date() : hsbaBHYT.getHsbabhytGiatri1());
						ngayHetHanBh = hsbaBHYT.getHsbabhytGiatri1();
					}					
					// phuc.lc 30-12-2010 : end
					
					//phuc.lc 15-03-2011 : begin fix bug 2422
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
					//phuc.lc 15-03-2011 : end fix bug 2422
					// phuc.lc 20-12-2010 : Doi voi benh nhan noi tru thi lay so bao hiem trong bang HsbaBhyt (khong lay tu bang Tiep don)								
					String strSotheBHYT = (hsbaBHYT.getHsbabhytSothebh() == null ?"" : hsbaBHYT.getHsbabhytSothebh());
					if (strSotheBHYT.trim().length() > 3) {
						String strMaQuyenloi = strSotheBHYT.substring(2, 3);
						DtDmMqlBhytDelegate maQLDel = DtDmMqlBhytDelegate.getInstance();
						DtDmMqlBhyt maQL = maQLDel.findByMa(strMaQuyenloi);
						if (maQL != null) {
							// Set lai cac gia tri theo  ma quyen loi
							minktc = (maQL.getDtdmmqlbhytMinktc() == null ? 0 : maQL.getDtdmmqlbhytMinktc());							
							maxktc = (maQL.getDtdmmqlbhytMaxktc() == null ? 0 : maQL.getDtdmmqlbhytMaxktc());
							if (!bVuottuyen) {
								tylebhyt_noi_tru = (maQL.getDtdmmqlbhytTylebhyt1NoiTru() == null ? 0 : maQL.getDtdmmqlbhytTylebhyt1NoiTru());
							}
											
							tylebhyt2_noi_tru = (maQL.getDtdmmqlbhytTylebhyt2NoiTru() == null ? 0 : maQL.getDtdmmqlbhytTylebhyt2NoiTru());				
							tyleminktc = (maQL.getDtdmmqlbhytTlminktc() == null ? 0 : maQL.getDtdmmqlbhytTlminktc());
							tylemaxktc = (maQL.getDtdmmqlbhytTlmaxktc() == null ? 0 : maQL.getDtdmmqlbhytTlmaxktc());
							tyleKTC = (maQL.getDtdmmqlbhytTylektc() == null ? 0 : maQL.getDtdmmqlbhytTylektc());;
							gioiHanTyLe_noi_tru = (maQL.getDtdmmqlbhytGhtyleNoiTru() == null ? 0 : maQL.getDtdmmqlbhytGhtyleNoiTru());
							mienphiVC = (maQL.getDtdmmqlbhytVanchuyen() == null ? false : maQL.getDtdmmqlbhytVanchuyen());
							
						}
					}
					
				}
			//}
			tinhChiPhiCLS(listCtkq, maDoituong,mienphiVC, ngayBatDauBh, ngayHetHanBh);
			// Tinh tien thuoc
			//log.info("Before tinhChiPhiThuoc()");
			tinhChiPhiThuoc(lstThuocNT,maDoituong, ngayBatDauBh, ngayHetHanBh);
			//log.info("After tinhChiPhiThuoc()");
			//phuc.lc 28-05-2011 : Tinh chi phi chuyen vao tu ngoai tru
			// Kiem tra xem co du lieu chuyen tu ngoai tru vao hay khong
			// Neu co thi thi them chi phi ngoai tru chua thanh toan(cls, thuoc) de xac dinh ty le bao hiem
			ChuyenVaoNoiTru cvnt = ChuyenVaoNoiTruDelegate.getInstance().findBySoVaoVien(hsba.getHsbaSovaovien());
			
			//phuc.lc : 13-06-2011  Tinh chi phi thuoc dong y theo bai thuoc
			ThuocDongYNoiTruDelegate thuocDYNTDel = ThuocDongYNoiTruDelegate.getInstance();
			List<ThuocDongYNoiTru> listThuocDYNoiTru = thuocDYNTDel.findBySoVaoVien(hsba.getHsbaSovaovien());
			Date ngayNhanThuocDY;
			if (maDoituong.equals("BH") && IConstantsRes.DONG_Y_APPLY_ALL.equals("1")) {
				// Kiem tra co su dung bai thuoc dong y hay khong						
				if(listThuocDYNoiTru.size() > 0) {
					if (hsba.getHsbaNgaygiovaov().before(dateUpdate)) {				
						listThuocDYNoiTru = capnhatDongiattThuocDongYNoiTru(listThuocDYNoiTru);
						
					}
					for (ThuocDongYNoiTru each : listThuocDYNoiTru) {
						ngayNhanThuocDY = Utils.removeHourFromDate(each.getThuocdongyNgaygiocn());
						// Neu ngay nhan thuoc con thoi han bao hiem thi bao hiem tra, neu het han bao hiem thi benh tra( da tinh trong ham tinhChiPhiThuoc(...))
						if(ngayBatDauBh != null && ngayHetHanBh != null
								&& !ngayNhanThuocDY.before(ngayBatDauBh) && !ngayNhanThuocDY.after(ngayHetHanBh)) {
							//tongChiPhiThuocBhTra += (each.getThuocdongySoluong() * each.getThuocdongyDongia());
							tongChiPhiThuocBhTra += each.getThuocdongyThanhtien();
							// Chi phi nay cong vao chi phi thuoc trong danh muc (do bao hiem chi tra theo phan ty le)
							//thuocTrongDM +=(each.getThuocdongySoluong() * each.getThuocdongyDongia());
							thuocTrongDM += each.getThuocdongyThanhtien();
						}
					}
				}
			}
			//phuc.lc : 13-06-2011 end
			//ClsKhamDelegate clsKhamDelegate  = ClsKhamDelegate.getInstance();
			//ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();			
			
			//List<ClsKham> listClsKham = new ArrayList<ClsKham>();
			//List<ThuocPhongKham> listTpk_BK = new ArrayList<ThuocPhongKham>();  // thuoc ban kham
			//List<ThuocPhongKham> listTpk_BHYT = new ArrayList<ThuocPhongKham>();  // ke toa BHYT
			HoSoThanhToanKhamUtil hsthtoankUtilTmp = new HoSoThanhToanKhamUtil(new TiepDon());
			if (cvnt != null) {
				// Lay CLS ngoai tru
				//listClsKham = clsKhamDelegate.findByTiepdonma(cvnt.getTiepDon().getTiepdonMa());
				// Lay thuoc ngoai tru (ban kham)
				//listTpk_BK = tpkDelegate.findByMaTiepDon(cvnt.getTiepDon().getTiepdonMa(), "1");
				// Lay thuoc ngoai tru (ke toa BHYT)
				//listTpk_BHYT = tpkDelegate.findByMaTiepDon(cvnt.getTiepDon().getTiepdonMa(), "3");
				hsthtoankUtilTmp = new HoSoThanhToanKhamUtil(cvnt.getTiepDon());
				hsttk = HsThtoankDelegate.getInstance().findBytiepdonMa(cvnt.getTiepDon().getTiepdonMa());
				if(hsttk == null) { hsttk = new HsThtoank(); }								
				hsttk.setTiepdonMa(cvnt.getTiepDon());
				hsthtoankUtilTmp.tinhToanChoHSTKKham(hsttk);
				//log.info("Chi phi thuoc BH tra ngoai tru = " + hsthtoankUtilTmp.getTongChiPhiThuocBhTra());
				//log.info("Chi phi CLS BH tra ngoai tru = " + hsthtoankUtilTmp.getTongChiPhiCLSBhTra());
				
			}
			// phuc.lc 28-05-2011 : END
			// Tinh tong chi phi BH tra xem da vuot qua gioi han ty le hay chua
			// tong nay bao gom chi phi CLS va Thuoc (khong tinh chi phi KTC va VC)
			// muc dich de tinh ty le bao hiem tra tren tong chi phi nay		
			double tongChiPhiThuocCLS = tongChiPhiCLSBhTra + tongChiPhiThuocBhTra;
			
			// 20120628 bao.ttc: double tongChiPhiThuocCLS_NgoaiTru = 0;			
			// if (cvnt != null) {
				// Neu co chuyen so lieu tu ngoai tru vao thi cong them chi phi ngoai tru				
				// tongChiPhiThuocCLS_NgoaiTru = hsthtoankUtilTmp.getTongChiPhiThuocBhTra() + hsthtoankUtilTmp.getTongChiPhiCLSBhTra();
			// }
			
			double tyleBhApDung = 0;	// Ty le ap dung thuc su khi tinh toan, ty le nay co the la TYLEBHYT1 hoac TYLEBHYT2, tuy thuoc gia tri tongChiPhiThuocCLSBhTra
			
			tyleBhApDung = tylebhyt_noi_tru; // Ty le nay da tinh cho cac truong hop vuot tuyen luc khoi tao
			
			//if (!bVuottuyen && (tongChiPhiThuocCLS + tongChiPhiThuocCLS_NgoaiTru)< gioiHanTyLe_noi_tru) {
			// Chi xet ty le bao hiem theo chi phi noi tru, khong xet chi phi ngoai tru (cho cac truong hop chuyen tu ngoai tru vao noi tru)
			if (!bVuottuyen && (tongChiPhiThuocCLS < gioiHanTyLe_noi_tru)) {  
				tyleBhApDung = tylebhyt2_noi_tru;  // Ty le nay da tinh cho cac truong hop vuot tuyen luc khoi tao
			} 
			
			// cap nhat lai chi phi benh nhan tra cho tung loai thuoc theo tyleBhApDung
			capNhatThuocNoiTru(lstThuocNT,maDoituong, ngayBatDauBh, ngayHetHanBh, tyleBhApDung);
			//cap nhat lai chi phi benh nhan tra cho tung CLS thuong theo tyleBhApDung
			// muc dich de tinh mien giam
			capNhatClsMo(listCtkq, ngayBatDauBh, ngayHetHanBh, tyleBhApDung);
			
			// Dua theo tyleBhApDung, tinh ra chi phi bao hiem tra va chi phi benh nhan tra tren tongChiPhiThuocCLSBhTra
			//double tongChiPhiThuocCLSBhTra = roundDoubleNumber(new Double(tongChiPhiThuocCLS * (tyleBhApDung / 100)));
			//double tongChiPhiThuocCLSBnTra = tongChiPhiThuocCLS - tongChiPhiThuocCLSBhTra;
			// Tinh tong chi phi benh nhan phai tra (de hien thi len giao dien)
			//double tongChiPhiBnTra = tongChiPhiThuocCLSBnTra + tongChiPhiCLSBnTra + tongChiPhiVCBnTra + tongChiPhiKTCBnTra +  tongChiPhiThuocBnTra;
			double tongChiPhiBnTra = tongChiPhiThuocCLSBnTra_TyleBH + tongChiPhiCLSBnTra + tongChiPhiVCBnTra + tongChiPhiKTCBnTra +  tongChiPhiThuocBnTra;
			if (tongChiPhiBnTra < 0.0) {
				tongChiPhiBnTra = 0.0;
			}
			// Tinh tong chi phi bao hiem tra (de hien thi len giao dien)
			double tongChiPhiBhTra = (tongChiPhiThuocCLS - tongChiPhiThuocCLSBnTra_TyleBH) + tongChiPhiVCBhTra + tongChiPhiKTCBhTra;			
			
			
			
			//phuc.lc 13-06-2011 : Cap nhat tien benh tra cho thuoc dong y noi tru
			if (maDoituong.equals("BH") && IConstantsRes.DONG_Y_APPLY_ALL.equals("1")
					&& listThuocDYNoiTru.size() > 0) {
				Double thanhtien = 0.0;		
				for (ThuocDongYNoiTru each : listThuocDYNoiTru) {
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
						each.setThuocdongyTylebh(tyleBhApDung);
						each.setThuocdongyTienbntra(thanhtien - roundDoubleNumber(thanhtien * (tyleBhApDung/100)));
					}
					thuocDYNTDel.edit(each);
				}
			}
			//phuc.lc 13-06-2011 : end
			
			//Cap nhat lai chi phi benh nhan tra cho tung thuoc, CLS ngoai tru theo tyleBhApDung
			List<ClsKham> listClsKham = new ArrayList<ClsKham>();
			List<ThuocPhongKham> listTpk_BK = new ArrayList<ThuocPhongKham>();
			List<ThuocPhongKham> listTpk_BH = new ArrayList<ThuocPhongKham>();
			if (cvnt != null) {
				//Cap nhat lai chi phi benh nhan tra cho tung CLS ngoai tru theo tyleBhApDung
				//Cap nhat lai chi phi benh nhan tra cho tung loai thuoc theo tyleBhApDung (thuoc phong kham thuoc ke toa )
				ClsKhamDelegate clsKhamDelegate  = ClsKhamDelegate.getInstance();
				// lay CLS
				listClsKham = clsKhamDelegate.findByTiepdonma(cvnt.getTiepDon().getTiepdonMa());
				
				// lay thuoc ban` khoam
				ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
				listTpk_BK = tpkDelegate.findByMaTiepDon(cvnt.getTiepDon().getTiepdonMa(), "1"); // thuoc ban kham
				
				// ke toa quay benh vien
				listTpk_BH = tpkDelegate.findByMaTiepDon(cvnt.getTiepDon().getTiepdonMa(), "3"); // ke toa BHYT
				/* phuc.lc 24-08-2011 : Bo cac dong code cap nhat Thuoc, CLS duoi day vi khong can tinh lai chi phi ngoai tru theo ty le noi tru
				hsthtoankUtilTmp.capNhatClsKham(listClsKham, ngayBatDauBh, ngayHetHanBh, tyleBhApDung);
				hsthtoankUtilTmp.capNhatThuocPhongKham(listTpk_BK, Utils.XU_TRI_THUOC_TAI_BAN_KHAM, maDoituong, ngayBatDauBh, ngayHetHanBh, tyleBhApDung);
				hsthtoankUtilTmp.capNhatThuocPhongKham(listTpk_BH, Utils.KE_TOA_QUAY_BENH_VIEN, maDoituong, ngayBatDauBh, ngayHetHanBh, tyleBhApDung);
				// Refesh list sau khi cap nhat de tinh mien giam
				listClsKham = clsKhamDelegate.findByTiepdonma(cvnt.getTiepDon().getTiepdonMa());
				listTpk_BK = tpkDelegate.findByMaTiepDon(cvnt.getTiepDon().getTiepdonMa(), "1"); 
				listTpk_BH = tpkDelegate.findByMaTiepDon(cvnt.getTiepDon().getTiepdonMa(), "3");
				*/ 
			}
			//refresh list CLS sau khi cap nhat
			listCtkq = clsMoDelegate.findBySoVaoVien(hsba.getHsbaSovaovien());
			
			// Lay thong tin mien giam
			String maMienGiam = "";
			Date ngayBatDauMG = null;
			Date ngayKetThucMG = null;			
			double phantramMienGiam = 0;
			List<MienGiam> listMienGiam = MienGiamDelegate.getInstance().getBySoVaoVien(hsba.getHsbaSovaovien());
			if (listMienGiam != null) {
				if (listMienGiam.size() > 0) {
					//log.info("mien giam so tien = " + listMienGiam.get(0).getMiengiamSotien());
					maMienGiam = listMienGiam.get(0).getMiengiamLoaimien().getDtdmloaimienMa();
					//Calendar cal1 = Calendar.getInstance();
					ngayBatDauMG = listMienGiam.get(0).getMiengiamNgayd();					
					//cal1.setTime(ngayBatDauMG);
					//cal1.set(Calendar.HOUR, 0);
					//cal1.set(Calendar.MINUTE, 0);
					//cal1.set(Calendar.SECOND, 0);
					//cal1.set(Calendar.MILLISECOND, 0);
					//ngayBatDauMG = cal1.getTime();
					ngayBatDauMG = Utils.removeHourFromDate(ngayBatDauMG);
					
					ngayKetThucMG = listMienGiam.get(0).getMiengiamNgayc();
					//cal1.setTime(ngayKetThucMG);
					//cal1.set(Calendar.HOUR, 0);
					//cal1.set(Calendar.MINUTE, 0);
					//cal1.set(Calendar.SECOND, 0);
					//cal1.set(Calendar.MILLISECOND, 0);
					//ngayKetThucMG =cal1.getTime();
					ngayKetThucMG =Utils.removeHourFromDate(ngayKetThucMG);
					if (listMienGiam.get(0).getMiengiamTyle() == null) {
						phantramMienGiam = 0;
					} else {
						phantramMienGiam = listMienGiam.get(0).getMiengiamTyle().doubleValue();
					}
					
					if (listMienGiam.get(0).getMiengiamSotien() == null) {
						// tongMienGiam = 0;
					} else {
						//tongMienGiam = listMienGiam.get(0).getMiengiamSotien().doubleValue();						
						tongMienGiam += listMienGiam.get(0).getMiengiamSotien().doubleValue();
					}
					
				}
			}
			
			// lay lai danh sach CLS sau khi da cap nhat gia benh nhan tra cho tung CSL (thuc hien cap nhat o method capNhatClsMo(...) o tren)
			
			List<ClsMo> listClsMo = clsMoDelegate.findBySoVaoVien(hsba.getHsbaSovaovien());			
			
			// lay lai danh sach thuoc noi tru sau khi da cap nhat gia thuoc theo ty le bao hiem
			
			List<ThuocNoiTru> lstTNT = tntDelegate.findBySoVaoVien(hsba.getHsbaSovaovien());
			
			// Nếu loại miễn là "Miễn giảm phần trăm (toàn chi phí) (mã = 2)" hoặc "Miễn một số tiền cụ thể (mã  = 3)"		
			if( maMienGiam.equals("3")) {
				// Miễn một số tiền cụ thể (mã  = 3) : tongMienGiam = số tiền miễn giảm (đã set ở trên)
				
			} else if(maMienGiam.equals("2")) { //"Miễn giảm phần trăm (toàn chi phí) (mã = 2)"
				tongMienGiam += tongChiPhiBnTra * phantramMienGiam / 100;
				// Cap nhat mien giam ngoai tru
				if (hsttk != null) {
					hsttk.setHsthtoankXetgiam((hsttk.getHsthtoankBntra() == null ? 0.0 : hsttk.getHsthtoankBntra()) * phantramMienGiam / 100);
				}
			} else {
				// Cac loại miễn khác phải tính theo thời gian 
				//tongMienGiam = 0;
				// Tinh tong mien giam CLS mo
				tinhTongMienGiam(listClsMo, listClsKham, maMienGiam, ngayBatDauMG, ngayKetThucMG);				
				//log.info("tongMienGiam = " + tongMienGiam + ", phantramMienGiam = " + phantramMienGiam);
				// Cong them mien giam thuoc
				tongMienGiam = tongMienGiam + tinhTongMienGiamThuoc(lstTNT,listTpk_BK,listTpk_BH,maMienGiam, ngayBatDauMG, ngayKetThucMG, ngayBatDauBh, ngayHetHanBh, maDoituong);
				tongMienGiam = tongMienGiam * phantramMienGiam / 100;
			}		
			 
			 //////////////////////////////////
			 hstt.setHsthtoanMau(clsMauNDM + cLSMauTrongDM);
			 hstt.setHsthtoanCongkham(tienCongKham);
			 hstt.setHsthtoanXntdcn(xetNghiemTDCN);
			 hstt.setHsthtoanCdha(chanDoanHinhAnh);
			 // hstt.setHsthtoanDvkttt(dichVuKTThongThuong);
			 hstt.setHsthtoanDvktc(dichVuKTC);
			 hstt.setHsthtoanCpvc(chiPhiVC);
			 // phuc.lc 12-12-2010 BEGIN
			 //log.info("chiphiPT = " + chiphiPT);
			 hstt.setHsthtoanPhauthuat(chiphiPT);
			 //log.info("chiphiTT = " + chiphiTT);
			 hstt.setHsthtoanDvkttt(chiphiTT);
			 // END
			 
			 // 20110811 bao.ttc: save ty le BHYT de dung trong Phieu thanh toan
			 perbhytchi = tyleBhApDung;
			 hstt.setHsthtoanTylebh(new Short("" + new Double(tyleBhApDung).intValue()));
			 
			 tinhTamUng();
			 tinhHoanUng();
			 
//			 log.info("ung:"+ung);
//			 log.info("tra:"+tra);
			 
			 hstt.setHsthtoanTamung(ung);
			 hstt.setHsthtoanHoanung(tra);
			 
			 /**
			  * xem xet so tien bn tra tu ngoai tru,...
			  * bao.ttc: dung ham findBytiepdonMaLast
			  */
				
			 	//hstt.setHsthtoanBntra(bntra);
			 	hstt.setHsthtoanBntra(tongChiPhiBnTra);  // tongChiPhiBnTra chua tru tien mien giam
				// hstt.setHsthtoanBhyt(bhytchi);
			 	hstt.setHsthtoanBhyt(tongChiPhiBhTra);
			 	
				 hstt.setHsthtoanThuoc(thuocTrongDM);
				 hstt.setHsthtoanThuocndm(thuocNDM);
				 hstt.setHsthtoanVtth(vTTHTrongDM);
				 hstt.setHsthtoanVtthndm(vTTHNDM);
				 
				 hstt.setHsthtoanCls(clsTrongDM);
				 hstt.setHsthtoanClsndm(clsNDM);
				 
//				 hstt.setHsthtoanTamung(ung);
//				 hstt.setHsthtoanHoanung(tra);
				 
				  
				 hstt.setHsthtoanPhong(cLSPhongTrongDM);
				 hstt.setHsthtoanPhongndm(clsPhongNDM);
				 
				 hstt.setHsthtoanMiente(mienTE);
				 
			 if ("TE".equals(hsba.getDoituongMa(true).getDmdoituongMa())){
				 hstt.setHsthtoanBhyt(hstt.getHsthtoanMiente());
			 }
			 
			 hstt.setHsthtoanMiendt(tongMienGiam);
			 
//			 log.info("hstt.getHsthtoanBntra():"+hstt.getHsthtoanBntra());
			 //soDu = ((hstt.getHsthtoanBntra() == null)?0:hstt.getHsthtoanBntra() ) - ((ung==null)?0:ung) + (tra==null?0:tra) - (miengiamsotien==null?0:miengiamsotien);
			 soDu = ((hstt.getHsthtoanBntra() == null)?0:hstt.getHsthtoanBntra() ) - ((ung==null)?0:ung) + (tra==null?0:tra) - tongMienGiam;
			 
			 hstt.setHsthtoanThtoan(soDu);
			 hstt.setHsthtoanKhongthu(khongThu);
			 
			 // cap nhat hs thanh toan kham
			 /* phuc.lc 24-08-2011 : Khong can cap nhat hsttk (ngoai tru), theo ty le noi tru
			 if (cvnt != null) {		 
				 hsttk.setHsthtoankBhyt(tongChiPhiThuocCLS_NgoaiTru * (tyleBhApDung / 100));
				 hsttk.setHsthtoankBntra(tongChiPhiThuocCLS_NgoaiTru - hsttk.getHsthtoankBhyt());
				 hsttk.setHsthtoankTongchi(hsttk.getHsthtoankBhyt() + hsttk.getHsthtoankBntra());
				 hsttk.setHsthtoankTylebh(new Short("" + new Double(tyleBhApDung).intValue()));
				 hsttk.setHsthtoankThtoan(hsttk.getHsthtoankBntra() - hsttk.getHsthtoankTamung() + hsttk.getHsthtoankHoanung() - hsttk.getHsthtoankXetgiam());
			 }
			 */
		}
		
		// phuc.lc 06-10-2010
		private void tinhChiPhiCLS(List<ClsMo> clslist, String maDoituong, boolean mienphiVC, Date ngayBatDauBh, Date ngayHetHanBh){
			//log.info("Begin tinhChiPhiCLS(), maDoituong = " + maDoituong + "ngayHetHanBh = " + ngayHetHanBh);					
			if (clslist ==null || clslist.size() == 0){
				return ;
			}
			if (hsba == null){
				return ;
			}
					
			if (clslist != null && clslist.size() > 0) {
				ClsMoDelegate clsMoDel = ClsMoDelegate.getInstance();
				try {
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					Date ngayThucHienCLS = null;
					//Calendar cal3 = Calendar.getInstance();
					for (ClsMo clsmo : clslist) {
						Short lanKham = clsmo.getClsmoLan();
						if (lanKham == null) {
							lanKham = 0;
						}
						clsmo.setClsmoLan(lanKham);
						//log .info("lanKham:"+ lanKham );
						
						Short tra = clsmo.getClsmoTra();
						if (tra == null){
							tra = 0;
						}
						
						// can lam sang da thanh toan tien roi, tinh vao tong chi phi benh nhan phai tra va so da thanh toan
						if (clsmo.getClsmoDatt() != null && clsmo.getClsmoDatt().booleanValue() == true){
							tongChiPhiCLSBnTra += clsmo.getClsmoDongia();
							DaThanhToan += clsmo.getClsmoDongia();
							if (clsmo.getClsmoMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("KH")) {
								tienCongKham += clsmo.getClsmoDongia()*(lanKham - tra);
							}
							if (clsmo.getClsmoNDM() != null && clsmo.getClsmoNDM().booleanValue() == true) {
								clsNDM += clsmo.getClsmoDongia()*(lanKham - tra);
							} else {
								clsTrongDM += clsmo.getClsmoDongia()*(lanKham - tra);
							}
							continue;
						}
						
						
						if (lanKham > tra){
							
							// Don gia trong Cls Mo co the la Don gia, Don gia mien phi, Don gia bao hiem hoac Don gia yeu cau trong bang Cls bang gia
							// tuy thuoc vao loai CLS va co chon YC hay khong
							
							if (clsmo.getClsmoDongia() == null) {
								clsmo.setClsmoDongia(new Double(0));
							}
							//log .info("clsmo.getClsmoDongia():"+ clsmo.getClsmoDongia() );
							if (clsmo.getClsmoNDM() != null && clsmo.getClsmoNDM().booleanValue() == true) {
								clsNDM += clsmo.getClsmoDongia()*(lanKham - tra);
							} else {
								clsTrongDM += clsmo.getClsmoDongia()*(lanKham - tra);
							}
							ngayThucHienCLS = (clsmo.getClsmoNgay() == null ? null : df.parse(df.format(clsmo.getClsmoNgay())));
							
							//cal3.setTime(ngayThucHienCLS);
							//cal3.set(Calendar.HOUR, 0);
							//cal3.set(Calendar.MINUTE, 0);
							//cal3.set(Calendar.SECOND, 0);
							//cal3.set(Calendar.MILLISECOND, 0);
							//ngayThucHienCLS = cal3.getTime();
							ngayThucHienCLS = Utils.removeHourFromDate(ngayThucHienCLS);
							//  Tinh chi phi cac CLS ky thuat cao 
							if (clsmo.getClsmoKtcao() != null && clsmo.getClsmoKtcao().booleanValue() == true) {							
								tinhChiPhiClsKTC(clsMoDel, clsmo , maDoituong, lanKham, tra, ngayBatDauBh, ngayHetHanBh, ngayThucHienCLS);
							} else if (clsmo.getClsmoLoaicls() != null && clsmo.getClsmoLoaicls().getDtdmclsMaso().intValue() == 12){ // 12 la ma so cua can lam san van chuyen
								tinhChiPhiClsVanChuyen(clsMoDel, clsmo, maDoituong, lanKham, tra, mienphiVC, ngayBatDauBh, ngayHetHanBh, ngayThucHienCLS);
							} else { // tinh chi phi CLS thuong
								tinhChiPhiCLSThuong(clsmo, maDoituong, lanKham, tra, ngayBatDauBh, ngayHetHanBh, ngayThucHienCLS);
							}																																						
							// tinh chi phi theo ma CLS
							tinhChiPhiTheoMaCLS(clsmo.getClsmoMahang().getDtdmclsbgMaloai().getDtdmclsMa(), (clsmo.getClsmoDongia() == null ? 0 : clsmo.getClsmoDongia()) , (clsmo.getClsmoNDM() != null?clsmo.getClsmoNDM().booleanValue():true), lanKham, tra);		
						}																		
					}
				}catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			
		}
		private void tinhChiPhiCLSThuong(ClsMo clsmo, String maDoituong, int lanKham, int tra, Date ngayBatDauBh, Date ngayHetHanBh, Date ngayThucHienCLS) {
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
				// gia nay duoc luu vao truong don gia trong bang Clsmo thi them vao
				tongChiPhiCLSBnTra += clsmo.getClsmoDongia()*(lanKham - tra);
			} else if (maDoituong.equals("TP")){
				// neu la doi tuong thu phi thi tinh vao chi phi benh nhan tra						
				tongChiPhiCLSBnTra += clsmo.getClsmoDongia()*(lanKham - tra);					
			} else { // Doi tuong bao hiem 
				if (ngayBatDauBh == null || ngayHetHanBh == null) {
					// Neu khong co thong tin thoi han bao hiem thi benh nhan tra
					tongChiPhiCLSBnTra += clsmo.getClsmoDongia()*(lanKham - tra);
				}else if (ngayThucHienCLS.before(ngayBatDauBh) || ngayThucHienCLS.after(ngayHetHanBh)) {
					// Neu the bao hiem het han thi benh nhan tra
					tongChiPhiCLSBnTra += clsmo.getClsmoDongia()*(lanKham - tra);
				}
				else if (clsmo.getClsmoNDM() != null && clsmo.getClsmoNDM().booleanValue() == true) { 
				// can sang ngoai danh muc bao hiem, benh nhan tra 														
					tongChiPhiCLSBnTra += clsmo.getClsmoDongia()*(lanKham - tra);						
				} else {
					if (clsmo.getClsmoYeucau() != null && clsmo.getClsmoYeucau().booleanValue() == true) {
					
						// can lan sang yeu cau ==> benh nhan tra phan dich vu, bao hiem tra theo gia bao hiem
						
						// 20120217 bao.ttc: Khong cong tien nay vi da tinh vao "tongChiPhiThuocCLSBnTra_TyleBH"
						// tongChiPhiCLSBnTra += clsmo.getClsmoPhandv()*(lanKham - tra);
						// END 20120217 bao.ttc
						
						tongChiPhiCLSBhTra += clsmo.getClsmoDongiabh()*(lanKham - tra);
					
					} else {							
						tongChiPhiCLSBhTra += clsmo.getClsmoDongia()*(lanKham - tra);
					}
				}
				
			}
		}
		
		private void tinhChiPhiClsVanChuyen(ClsMoDelegate clsMoDel, ClsMo clsmo, String maDoituong, int lanKham, int tra, boolean mienphiVC, Date ngayBatDauBh, Date ngayHetHanBh, Date ngayThucHienCLS){
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
			clsmo.setClsmoDongiabntra(clsmo.getClsmoDongia()*(lanKham - tra));
			if (maDoituong.equals("MP")) {
				// doi tuong mien phi duoc tinh theo gia mien phi hoac gia yeu cau(neu chon) va do BN tra
				// gia nay duoc luu vao truong don gia trong bang ClsMo thi them vao
				tongChiPhiVCBnTra += clsmo.getClsmoDongia()*(lanKham - tra);						
			} else if (maDoituong.equals("TP")){
				// neu la doi tuong thu phi thi tinh vao chi phi benh nhan tra						
				tongChiPhiVCBnTra += clsmo.getClsmoDongia()*(lanKham - tra);			
				
			} else { // Doi tuong bao hiem 
				if (ngayBatDauBh == null || ngayHetHanBh == null) {
					// Neu khong co thong tin thoi han bao hiem thi benh nhan tra
					tongChiPhiVCBnTra += clsmo.getClsmoDongia()*(lanKham - tra);
				} else if (ngayThucHienCLS.before(ngayBatDauBh) || ngayThucHienCLS.after(ngayHetHanBh)) {
					// Neu the bao hiem het han thi benh nhan tra
					tongChiPhiVCBnTra += clsmo.getClsmoDongia()*(lanKham - tra);								
				} else if (clsmo.getClsmoNDM() != null && clsmo.getClsmoNDM().booleanValue() == true) { 
					// can sang ngoai danh muc bao hiem, benh nhan tra 														
					tongChiPhiVCBnTra += clsmo.getClsmoDongia()*(lanKham - tra);				
				}  else if (bDoituongUutien) {
					if (mienphiVC) {
						// Thuoc dien mien phi VC, BH tra 100%, benh nhan khong tra
						tongChiPhiVCBhTra += clsmo.getClsmoDongia()*(lanKham - tra);
						// Cap nhat don gia benh nhan tra, muc dich de tinh mien giam
						clsmo.setClsmoDongiabntra(new Double(0));
					} else {
						// Khong thuoc dien mien phi VC, BN tra
						tongChiPhiVCBnTra += clsmo.getClsmoDongia()*(lanKham - tra);				
					}
				} else if (bVuottuyen){ 
					// Vuot tuyen, benh nhan tra 														
					tongChiPhiVCBnTra += clsmo.getClsmoDongia()*(lanKham - tra);				
					 
				} else if(mienphiVC) {
					// Thuoc dien mien phi VC, BH tra 100%, benh nhan khong tra
					tongChiPhiVCBhTra += clsmo.getClsmoDongia()*(lanKham - tra);
					// Cap nhat don gia benh nhan tra, muc dich de tinh mien giam
					clsmo.setClsmoDongiabntra(new Double(0));							
				} else {
					// Khong thuoc dien mien phi VC, BN tra
					tongChiPhiVCBnTra += clsmo.getClsmoDongia()*(lanKham - tra);				
				}									
			}
			clsMoDel.edit(clsmo);
		}
		
		private void tinhChiPhiClsKTC(ClsMoDelegate clsMoDel, ClsMo clsmo, String maDoituong, int lanKham, int tra, Date ngayBatDauBh, Date ngayHetHanBh, Date ngayThucHienCLS) {
			/*
			+ Mục đích : Tính ra chi phí do BH trả và chi phí do BN trả trên các CLS kỹ thuật cao
			+ Cách tính :
				- Nếu là đối tượng miễn phí(MP) hoặc thu phí(TP) ==> tính vào chi phí BN trả
				- Nếu là đối tượng BHYT thì tính như sau :
					- Nếu thẻ bảo hiểm hết hạn ==> tính vào chi phí BN trả			
					- Theo công thức tính tỷ lệ kỹ thuật cao 
			*/
			// Cap nhat don gia benh nhan tra, muc dich de tinh mien giam
			clsmo.setClsmoDongiabntra(clsmo.getClsmoDongia()*(lanKham - tra));
			
			if (maDoituong.equals("MP")) {
				// doi tuong mien phi duoc tinh theo gia mien phi hoac gia yeu cau(neu chon) va do BN tra
				// gia nay duoc luu vao truong don gia trong bang ClsMo thi them vao
				tongChiPhiKTCBnTra += clsmo.getClsmoDongia()*(lanKham - tra);
				
			} else if (maDoituong.equals("TP")){
				// neu la doi tuong thu phi thi tinh vao chi phi benh nhan tra						
				tongChiPhiKTCBnTra += clsmo.getClsmoDongia()*(lanKham - tra);				
			} else { // Doi tuong bao hiem 
				if (ngayBatDauBh == null || ngayHetHanBh == null) {
					// Neu khong co thong tin thoi gian bao hiem thi benh nhan tra
					tongChiPhiKTCBnTra += clsmo.getClsmoDongia()*(lanKham - tra);
				}else if (ngayThucHienCLS.before(ngayBatDauBh) || ngayThucHienCLS.after(ngayHetHanBh)) {
					// Neu the bao hiem het han thi benh nhan tra
					tongChiPhiKTCBnTra += clsmo.getClsmoDongia()*(lanKham - tra);				
				}
				else if (clsmo.getClsmoNDM() != null && clsmo.getClsmoNDM().booleanValue() == true) {				
				// can sang ngoai danh muc bao hiem, benh nhan tra 														
					tongChiPhiKTCBnTra += clsmo.getClsmoDongia()*(lanKham - tra);				
				} else {				
					if (clsmo.getClsmoYeucau() != null && clsmo.getClsmoYeucau().booleanValue() == true) {
						
							// can lan sang yeu cau ==> benh nhan tra phan dich vu, bao hiem tra theo cach tinh ty le KTC
							tongChiPhiKTCBnTra += clsmo.getClsmoPhandv()*(lanKham - tra);						
							double bnTraTmp = tinhTheoTyleKTC((clsmo.getClsmoDongiabh() == null ? 0 : clsmo.getClsmoDongiabh()) , lanKham, tra);							
							// Cap nhat don gia benh nhan tra, muc dich de tinh mien giam
							clsmo.setClsmoDongiabntra(clsmo.getClsmoPhandv()*(lanKham - tra) + bnTraTmp);
					} else {					
						double bnTraTmp = tinhTheoTyleKTC((clsmo.getClsmoDongiabh() == null ? 0 : clsmo.getClsmoDongiabh()), lanKham, tra);
						// Cap nhat don gia benh nhan tra, muc dich de tinh mien giam
						clsmo.setClsmoDongiabntra(bnTraTmp);
					}
				}
			}
			clsMoDel.edit(clsmo);
		}
		private double tinhTheoTyleKTC(double donGiaThucHienCLS, int lanKham, int tra) {
			/*
			+ Mục đích : Tính ra chi phí do BH trả và chi phí do BN trả trên các CLS kỹ thuật cao
			+ Cách tính :					
				- Theo công thức tính tỷ lệ kỹ thuật cao
			*/
			// Neu la benh nhan vuot tuyen va khong thuoc dien uu tien thi set lai cac ty le theo ty le cua benh vien
			if(bVuottuyen && !bDoituongUutien) {
				tyleminktc = tylebhyt_noi_tru;
				tyleKTC = (new Double(tylebhyt_noi_tru)).intValue();
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
				if(bVuottuyen && !bDoituongUutien) {
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
			tongChiPhiKTCBhTra += giaBhTra;
			return giaBnTra;
			
		}
		// Ham tinh chi phi theo ma CLS
		// Vi du : ma CLS la "GI" ==> tinh clsPhongNDM, clsPhongTrongDM
		// ma CLS la "MA" ==> tinh clsMauNDM, clsMauTrongDM
		// ...
		private void tinhChiPhiTheoMaCLS(String maCls, double dongia, boolean ngoaiDM, int lanKham, int tra) {
			if (maCls.equalsIgnoreCase("GI")) {
				if (ngoaiDM) {
					clsPhongNDM = clsPhongNDM + dongia*(lanKham - tra);
				} else {
					cLSPhongTrongDM = cLSPhongTrongDM + dongia*(lanKham - tra);
				}				
				
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
				// dichVuKTThongThuong = dichVuKTThongThuong + dongia*(lanKham - tra);
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
			} else if (maCls.equalsIgnoreCase("XN")) {
				xetNghiemTDCN = xetNghiemTDCN + dongia * (lanKham - tra);
			}
				
		}
		private void capNhatClsMo(List<ClsMo> clslist, Date ngayBatDauBh, Date ngayHetHanBh, double tyleBhApDung) {
			if (clslist.size() < 1 ) return;
			ClsMoDelegate clsMoDel = ClsMoDelegate.getInstance();
			Short lanKham = 0;
			Short tra = 0;
			try {			
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Date ngayThucHienCLS = null;
				
				for (ClsMo clsmo : clslist) {			
					// can lam sang da thanh toan tien roi(khong phu thuoc ty le ), khong tinh nua
					if (clsmo.getClsmoDatt() != null && clsmo.getClsmoDatt().booleanValue() == true){				
						continue;
					}
				//  Khong cap nhat don gia benh nhan tra cho cac CLS ky thuat cao & Van chuyen (da cap ham rieng cap nhat cho 2 loai CLS nay) 
					if ((clsmo.getClsmoKtcao() != null && clsmo.getClsmoKtcao().booleanValue() == true) || 							
						(clsmo.getClsmoLoaicls() != null && clsmo.getClsmoLoaicls().getDtdmclsMaso().intValue() == 12)){ // 12 la ma so cua can lam san van chuyen
						continue;
					} 
					lanKham = clsmo.getClsmoLan();
					if (lanKham == null) {
						lanKham = 0;
					}
					clsmo.setClsmoLan(lanKham);
								
					tra = clsmo.getClsmoTra();
					if (tra == null){
						tra = 0;
					}
					ngayThucHienCLS = (clsmo.getClsmoNgay() == null ? null : df.parse(df.format(clsmo.getClsmoNgay())));
					double giaCLS = clsmo.getClsmoDongia() * (lanKham - tra);
					// cap nhat don gia benh nhan tra cho tung CLS thường	
					if (ngayBatDauBh == null || ngayHetHanBh == null){
						clsmo.setClsmoDongiabntra(giaCLS);
					} else if ((clsmo.getClsmoNDM() != null && clsmo.getClsmoNDM().booleanValue() == true) ||
							ngayThucHienCLS.before(ngayBatDauBh) || ngayThucHienCLS.after(ngayHetHanBh)){
						clsmo.setClsmoDongiabntra(giaCLS);
					} else if (clsmo.getClsmoYeucau() != null && clsmo.getClsmoYeucau().booleanValue() == true) {									
						double giaCLSDv = clsmo.getClsmoPhandv()*(lanKham - tra);
						double giaCLSBh = clsmo.getClsmoDongiabh()*(lanKham - tra);
						//giaCLS = giaCLSDv + (giaCLSBh - (giaCLSBh * (tyleBhApDung /100)));
						giaCLS = giaCLSDv + (giaCLSBh - roundDoubleNumber(new Double(giaCLSBh * (tyleBhApDung /100))));
						clsmo.setClsmoDongiabntra(giaCLS);
						tongChiPhiThuocCLSBnTra_TyleBH += clsmo.getClsmoDongiabntra();
					} else {	
						clsmo.setClsmoDongiabntra(giaCLS - roundDoubleNumber(new Double(giaCLS * (tyleBhApDung /100))));
						tongChiPhiThuocCLSBnTra_TyleBH += clsmo.getClsmoDongiabntra();
					}
					//clsmo.setClsmoDongiabntra(giaCLS - (giaCLS * tyleBhApDung /100));
					//clskham.setClskhamNgaygiocn(new Date());
					clsMoDel.edit(clsmo);
				}
			}catch (Exception ex) {
				log.info("ERROR capNhatClsMo: " + ex.toString());
			}
		}
		
		private void capNhatThuocNoiTru(List<ThuocNoiTru> tntlist, String maDoituong, Date ngayBatDauBh, Date ngayHetHanBh, double tyleBhApDung) {
			
			if (tntlist == null || tntlist.size() < 1 ) return;
			ThuocNoiTruDelegate tntDel = ThuocNoiTruDelegate.getInstance();
			double giaThuoc;
			Date ngayNhanThuoc;
			//Calendar cal1 = Calendar.getInstance();
			
			for (ThuocNoiTru tnt : tntlist) {			
				// thuoc da thanh toan tien roi(khong phu thuoc ty le ), khong tinh nua
				if (tnt.getThuocnoitruNgaytt() != null ){				
					continue;
				}
				// cap nhat don gia benh nhan tra cho tung loai thuoc	
				//giaThuoc = tnt.getThuocnoitruDongiatt()*(tnt.getThuocnoitruSoluong() - (tnt.getThuocnoitruTra() == null ? 0 : tnt.getThuocnoitruTra()));			
				giaThuoc = tnt.getThuocnoitruThanhtien();
				ngayNhanThuoc = tnt.getThuocnoitruNgaygio();
				//cal1.setTime(ngayNhanThuoc);
				//cal1.set(Calendar.HOUR, 0);
				//cal1.set(Calendar.MINUTE, 0);
				//cal1.set(Calendar.SECOND, 0);
				//cal1.set(Calendar.MILLISECOND, 0);			
				//ngayNhanThuoc = cal1.getTime();					
				ngayNhanThuoc = Utils.removeHourFromDate(ngayNhanThuoc);
				if (tnt.getThuocnoitruKhongthu() != null && tnt.getThuocnoitruKhongthu().booleanValue() == true){					
					//phuc.lc : Neu thuoc la khong thu thi khong tinh chi phi
					tnt.setThuocnoitruTienbntra(new Double(0));	
				}else if (maDoituong.equalsIgnoreCase("BH")) {
					if (ngayBatDauBh == null || ngayHetHanBh == null) {
						// Khong co thong tin thoi han bao hiem, benh nhan tra
						tnt.setThuocnoitruTienbntra(giaThuoc);
					}else if(tnt.getThuocdongyNoiTru() != null 
							&& IConstantsRes.DONG_Y_APPLY_ALL.equals("1") 							
							&& !ngayNhanThuoc.before(ngayBatDauBh) 
							&& !ngayNhanThuoc.after(ngayHetHanBh)) {
						    // So tien benh tra cho tung vi thuoc trong bai thuoc la 0 trong truong hop thuoc tinh theo thang
						    tnt.setThuocnoitruTienbntra(new Double(0));	
					} else if (ngayNhanThuoc.before(ngayBatDauBh) || ngayNhanThuoc.after(ngayHetHanBh) 
							|| (tnt.getThuocnoitruNDM() != null && tnt.getThuocnoitruNDM().booleanValue() == true) 
							|| ( tnt.getThuocnoitruYeucau() != null && tnt.getThuocnoitruYeucau().booleanValue() == true)) {
						// Het han bao hiem, benh nhan tra
						tnt.setThuocnoitruTienbntra(giaThuoc);
					} else {
						tnt.setThuocnoitruTienbntra(giaThuoc - roundDoubleNumber(new Double(giaThuoc * (tyleBhApDung /100))));
						tongChiPhiThuocCLSBnTra_TyleBH += tnt.getThuocnoitruTienbntra();
					}
				} else {
					tnt.setThuocnoitruTienbntra(giaThuoc);
				}
									
						
				tntDel.edit(tnt);
			}
		}
		private double tinhTongMienGiamThuoc(List<ThuocNoiTru> lstThuocNT,List<ThuocPhongKham> listTpk_BK,List<ThuocPhongKham> listTpk_BH, String maMienGiam,Date ngayBatDauMG, Date ngayKetThucMG, Date ngayBatDauBh, Date ngayHetHanBh, String maDoiTuong) {
			double mienThuoc = 0;
			if (lstThuocNT == null || lstThuocNT.size() < 1 || maMienGiam.trim().length() < 1 || ngayBatDauMG == null || ngayKetThucMG == null) return mienThuoc;
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			if (maMienGiam.equalsIgnoreCase("02B")   // Miễn giảm phần trăm (không miễn máu, giường)
					 || (maMienGiam.equalsIgnoreCase("ALL"))   //  Miễn chung từ ngày đến ngày								 
					){
					try {
						//Calendar cal1 = Calendar.getInstance();
						Date ngayMuaThuoc ;
						for (ThuocNoiTru tnt : lstThuocNT) {
							// thuoc noi tru da thanh toan tien roi(khong phu thuoc ty le ), khong tinh nua
							if (tnt.getThuocnoitruNgaytt() != null ){				
								continue;
							}
							ngayMuaThuoc = df.parse(df.format(tnt.getThuocnoitruNgaygio()));							
							//cal1.setTime(ngayMuaThuoc);
							//cal1.set(Calendar.HOUR, 0);
							//cal1.set(Calendar.MINUTE, 0);
							//cal1.set(Calendar.SECOND, 0);
							//cal1.set(Calendar.MILLISECOND, 0);
							//ngayMuaThuoc = cal1.getTime();
							ngayMuaThuoc = Utils.removeHourFromDate(ngayMuaThuoc);
							// Neu ngay mua thuoc khong nam trong khong thoi gian mien giam thi khong tinh
							if (ngayMuaThuoc.before(ngayBatDauMG) || ngayMuaThuoc.after(ngayKetThucMG)) {
								continue;
							}
							mienThuoc += (tnt.getThuocnoitruTienbntra() == null ? 0 : tnt.getThuocnoitruTienbntra());
							//double soluong = (tnt.getThuocnoitruSoluong() == null ? 0 : tnt.getThuocnoitruSoluong());
							//double tra = (tnt.getThuocnoitruTra() == null ? 0 : tnt.getThuocnoitruTra());
							//double giaThuoc = tnt.getThuocnoitruDongia() * (soluong - tra);
							//if (maDoiTuong.equals("BH")								
							//	&& (ngayHetHanBh.after(ngayBatDauMG) || ngayHetHanBh.equals(ngayBatDauMG)) 
							//	&& (ngayHetHanBh.before(ngayKetThucMG) || ngayHetHanBh.equals(ngayKetThucMG))) {
								// Neu the bao hiem con gia tri trong thoi gian mien giam thi tinh gia thuoc theo gia bao hiem
							//	giaThuoc = tnt.getThuocnoitruDongiabh() *  (soluong - tra);
							//}
							//mienThuoc += giaThuoc;
							
						}
						listTpk_BK.addAll(listTpk_BH);
						for (ThuocPhongKham eachTpk : listTpk_BK) {
							// thuoc da thanh toan tien roi(khong phu thuoc ty le ), khong tinh nua
							if (eachTpk.getThuocphongkhamDatt() != null ){				
								continue;
							}
							ngayMuaThuoc = df.parse(df.format(eachTpk.getThuocphongkhamNgaygio()));							
							
							ngayMuaThuoc = Utils.removeHourFromDate(ngayMuaThuoc);
							// Neu ngay mua thuoc khong nam trong khong thoi gian mien giam thi khong tinh
							if (ngayMuaThuoc.before(ngayBatDauMG) || ngayMuaThuoc.after(ngayKetThucMG)) {
								continue;
							}
							//mienThuoc += (eachTpk.getThuocphongkhamTienbntra() == null ? 0 : eachTpk.getThuocphongkhamTienbntra());
							if (hsttk != null) {
								hsttk.setHsthtoankXetgiam(hsttk.getHsthtoankXetgiam() + eachTpk.getThuocphongkhamTienbntra());
							}
						}
					} catch (Exception e) {
						mienThuoc = 0;
						e.printStackTrace();
					} 
			}
			//log.info("mienThuoc  = " + mienThuoc);
			return mienThuoc;
		}
		private void tinhTongMienGiam(List<ClsMo> clslist, List<ClsKham> clskham, String maMienGiam, Date ngayBatDauMG, Date ngayKetThucMG) {
			if ((clslist.size() < 1 && clskham.size() < 1) || maMienGiam.trim().length() < 1 || ngayBatDauMG == null || ngayKetThucMG == null) return;
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");			
			try {
				//Calendar cal1 = Calendar.getInstance();
				Date ngayThHienCLS;
				for (ClsMo clsmo : clslist) {			
					// can lam sang da thanh toan tien roi(khong phu thuoc ty le ), khong tinh nua
					if ((clsmo.getClsmoDatt() != null && clsmo.getClsmoDatt().booleanValue() == true) ||
							clsmo.getClsmoNgay() == null){				
						continue;
					}
					ngayThHienCLS = df.parse(df.format(clsmo.getClsmoNgay()));
					
					//cal1.setTime(ngayThHienCLS);
					//cal1.set(Calendar.HOUR, 0);
					//cal1.set(Calendar.MINUTE, 0);
					//cal1.set(Calendar.SECOND, 0);
					//cal1.set(Calendar.MILLISECOND, 0);
					//ngayThHienCLS = cal1.getTime();
					ngayThHienCLS = Utils.removeHourFromDate(ngayThHienCLS);
					// Neu ngay thuc hien CLS (ngay chi dinh CLS) khong nam trong khong thoi gian mien giam thi khong tinh
					if (ngayThHienCLS.before(ngayBatDauMG) || ngayThHienCLS.after(ngayKetThucMG)) {
						continue;
					}
					String maCLS = clsmo.getClsmoMahang().getDtdmclsbgMaloai().getDtdmclsMa();
					//if((ngayThHienCLS.after(ngayBatDauMG) || ngayThHienCLS.equals(ngayBatDauMG)) 
					//		&& (ngayThHienCLS.before(ngayKetThucMG) || ngayThHienCLS.equals(ngayKetThucMG))) {
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
							tongMienGiam += clsmo.getClsmoDongiabntra();
						} 
					}
				
				// Tinh mien giam ngoai tru
				if (hsttk != null) {
					hsttk.setHsthtoankXetgiam(new Double(0.0));
				}
				for (ClsKham each : clskham) {			
					// can lam sang da thanh toan tien roi(khong phu thuoc ty le ), khong tinh nua
					if (each.getClskhamDatt() != null && each.getClskhamDatt().booleanValue() == true){				
						continue;
					}
					ngayThHienCLS = df.parse(df.format(each.getClskhamNgaygiocn()));
										
					ngayThHienCLS = Utils.removeHourFromDate(ngayThHienCLS);
					String maCLS = each.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa();
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
							if (hsttk != null) {
								hsttk.setHsthtoankXetgiam(hsttk.getHsthtoankXetgiam() + each.getClskhamDongiabntra());
							}
						} 
					}
				}
				//}
			}catch (Exception e) {
				tongMienGiam = 0;
				e.printStackTrace();
			}
		}
		
			
		
		public static Logger getLog() {
			return log;
		}

		public static void setLog(Logger log) {
			HoSoThanhToanUtil.log = log;
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

		public Double getPerbhytchi() {
			return perbhytchi;
		}

		public void setPerbhytchi(Double perbhytchi) {
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

		public Hsba getHsba() {
			return hsba;
		}

		public void setHsba(Hsba hsba) {
			this.hsba = hsba;
		}

		public Double getKhongThu() {
			return khongThu;
		}

		public void setKhongThu(Double khongThu) {
			this.khongThu = khongThu;
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

//		public Double getPTTTTrongDM() {
//			return pTTTTrongDM;
//		}
//
//		public void setPTTTTrongDM(Double trongDM) {
//			pTTTTrongDM = trongDM;
//		}
//
//		public Double getPTTTNDM() {
//			return pTTTNDM;
//		}
//
//		public void setPTTTNDM(Double ptttndm) {
//			pTTTNDM = ptttndm;
//		}

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



		public HsbaBhyt getHsbaBHYT() {
			return hsbaBHYT;
		}



		public void setHsbaBHYT(HsbaBhyt hsbaBHYT) {
			this.hsbaBHYT = hsbaBHYT;
		}



		public DtDmKhoiBhyt getKhoiBHYT() {
			return khoiBHYT;
		}



		public void setKhoiBHYT(DtDmKhoiBhyt khoiBHYT) {
			this.khoiBHYT = khoiBHYT;
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



		public Double getMienTE() {
			return mienTE;
		}

		public void setMienTE(Double mienTE) {
			this.mienTE = mienTE;
		}
		public HsThtoank getHsttk() {
			return hsttk;
		}
		public void setHsttk(HsThtoank hsttk) {
			this.hsttk = hsttk;
		}
		
	}


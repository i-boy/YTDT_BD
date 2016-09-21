package com.iesvn.yte.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.apache.log4j.Logger;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.HsThtoank;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.entity.DmBenhIcd;

public class ThamKhamUtil {

	public void tinhToanChoHSTKKham(ThamKham thamkham, HsThtoank hsttk,
			Boolean ghinhan, List<ThuocPhongKham> listCtTPK_temp,
			List<ClsKham> clslist) {
		HoSoThanhToanKhamUtil hsthtoankUtilTmp = new HoSoThanhToanKhamUtil(
				thamkham.getTiepdonMa());
		hsthtoankUtilTmp.tinhToanChoHSTKKham(hsttk);
		Utils.setInforForHsThToan(hsttk);
		
		// 20110413 bao.ttc: xet vuot Tuyen hay khong trong class HSTT Util
		bVuotTuyen = hsthtoankUtilTmp.isbVuottuyen();

		if (ghinhan == true) {
			listCtTPK_temp = hsthtoankUtilTmp.getListCtTPK_temp(); // thuoc
			// phong
			// kham tai
			// ban kham
			clslist = hsthtoankUtilTmp.getListCtkq_temp();
		}

	}

	public JasperPrint jasperPrintTD;
	public String reportPathTD;
	public String reportTypeTD;
	private boolean bVuotTuyen = false; // 20110413 bao.ttc: xet vuot Tuyen hay khong trong class HSTT Util

	public void XuatReport_don_thuoc_ketoa_quay(Logger log, ThamKham pthamkham,
			List<ThuocPhongKham> listCtTPK_temp, List<ClsKham> clslist,
			java.util.List<ThuocPhongKham> ctThuocPhongKhams, int index) {
		ThamKham thamkham = ThamKhamDelegate.getInstance().find(pthamkham.getThamkhamMa());
		log.info("Vao Method XuatReport bao cao xu tri thuoc tai ban kham");
		String baocao1 = null;
		try {

			log.info("bat dau method XuatReport ");
			String pathTemplate = "";
			String sub1Template = "";
			String sub2Template = "";
			String sub3Template = "";
			
			if(IConstantsRes.MAU_TOA_THUOC.equals("A4")){
				pathTemplate = IConstantsRes.PATH_BASE
						+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
						+ "tiepdon/toathuoc_main_A4.jrxml";
				sub1Template = IConstantsRes.PATH_BASE
						+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
						+ "tiepdon/toathuoc_sub1_A4.jrxml";
				sub2Template = IConstantsRes.PATH_BASE
						+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
						+ "tiepdon/toathuoc_sub2_A4.jrxml";
				sub3Template = IConstantsRes.PATH_BASE
						+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
						+ "tiepdon/toathuoc_sub3_A4.jrxml";
			}else{
				pathTemplate = IConstantsRes.PATH_BASE
					+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
					+ "tiepdon/toathuoc_main.jrxml";
				sub1Template = IConstantsRes.PATH_BASE
						+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
						+ "tiepdon/toathuoc_sub1.jrxml";
				sub2Template = IConstantsRes.PATH_BASE
						+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
						+ "tiepdon/toathuoc_sub2.jrxml";
				sub3Template = IConstantsRes.PATH_BASE
						+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
						+ "tiepdon/toathuoc_sub3.jrxml";
			}

			log.info("da thay file templete " + pathTemplate);
			log.info("da thay file sub 1 templete " + sub1Template);
			log.info("da thay file sub 2 templete " + sub2Template);
			log.info("da thay file sub 3 templete " + sub3Template);

			JasperReport jasperReport = JasperCompileManager
					.compileReport(pathTemplate);
			JasperReport sub1Report = JasperCompileManager
					.compileReport(sub1Template);
			JasperReport sub2Report = JasperCompileManager
					.compileReport(sub2Template);
			JasperReport sub3Report = JasperCompileManager
					.compileReport(sub3Template);

			SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("DIENTHOAI",
					IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);

			params.put("sub1", sub1Report);
			params.put("sub2", sub2Report);
			params.put("sub3", sub3Report);

			params.put("MATIEPDON", thamkham.getTiepdonMa().getTiepdonMa());

			//log.info("*****MATHAMKHAM: " + thamkham.getThamkhamMa());
			params.put("MATHAMKHAM", thamkham.getThamkhamMa());

			if (thamkham.getTiepdonMa(true).getKcbbhytMa(true)
					.getDmbenhvienTen() != null)
				params.put("NOIDKKCBBANDAU", thamkham.getTiepdonMa(true)
						.getKcbbhytMa(true).getDmbenhvienTen());

			params.put("HOTENBN", thamkham.getTiepdonMa().getBenhnhanMa()
					.getBenhnhanHoten());
			String diachistr = "";
			if (thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDiachi() != null)
				diachistr += thamkham.getTiepdonMa().getBenhnhanMa()
						.getBenhnhanDiachi()
						+ ",";
			if (thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(true)
					.getDmxaTen() != null)
				diachistr += thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(
						true).getDmxaTen()
						+ ",";
			if (thamkham.getTiepdonMa().getBenhnhanMa().getHuyenMa(true)
					.getDmhuyenTen() != null)
				diachistr += thamkham.getTiepdonMa().getBenhnhanMa()
						.getHuyenMa(true).getDmhuyenTen()
						+ ",";
			if (thamkham.getTiepdonMa().getBenhnhanMa().getTinhMa(true)
					.getDmtinhTen() != null)
				diachistr += thamkham.getTiepdonMa().getBenhnhanMa().getTinhMa(
						true).getDmtinhTen();
			params.put("DIACHI", diachistr);
			if (thamkham.getTiepdonMa().getTiepdonGiatri1() != null) {
				params.put("GTTU", sdf.format(thamkham.getTiepdonMa()
						.getTiepdonGiatri1()));
			} else {
				params.put("GTTU", "");
			}

			if (thamkham.getTiepdonMa().getTiepdonGiatri2() != null) {
				params.put("GTDEN", sdf.format(thamkham.getTiepdonMa()
						.getTiepdonGiatri2()));
			} else {
				params.put("GTDEN", "");
			}
			if (thamkham.getTiepdonMa().getTiepdonSothebh() != null
					&& !thamkham.getTiepdonMa().getTiepdonSothebh().equals("")) {
				String maBV = thamkham.getTiepdonMa(true).getKcbbhytMa(true)
						.getDmbenhvienMa();
				if (maBV == null) {
					maBV = "";
				}
				String maTheBHYT = thamkham.getTiepdonMa().getTiepdonSothebh();
				params.put("MATHEBHYT", getMaTheBHYTDisplay(maTheBHYT));
				//params.put("MABENHVIEN", maBV.replace(".", "-"));
				params.put("MABENHVIEN", maBV);

			} else {
				params.put("MATHEBHYT", "");
				params.put("MABENHVIEN", "");
			}
			
			if (thamkham.getThamkhamNgaygio() != null) {
				params.put("NGAYKHAMBENH", thamkham.getThamkhamNgaygio());
			} else {
				params.put("NGAYKHAMBENH", new Date());
			}

			DieuTriUtilDelegate dtDele = DieuTriUtilDelegate.getInstance();

			DtDmNhanVien bacsi = (DtDmNhanVien) dtDele.findByMa(thamkham
					.getThamkhamBacsi(true).getDtdmnhanvienMa(),
					"DtDmNhanVien", "dtdmnhanvienMa");
			if (bacsi != null) {
				params.put("BASIKHAMBENH", bacsi.getDtdmnhanvienTen());
			}

			params.put("NHANVIENCN", thamkham.getThamkhamNhanviencn(true)
					.getDtdmnhanvienTen());

			HsThtoank hsttk = new HsThtoank();
			hsttk.setTiepdonMa(thamkham.getTiepdonMa());
			tinhToanChoHSTKKham(thamkham, hsttk, false, listCtTPK_temp, clslist);
			Utils.setInforForHsThToan(hsttk);

			params.put("BHXHTHANHTOAN", hsttk.getHsthtoankBhyt());
			params.put("NGUOIBENHTRA", hsttk.getHsthtoankThtoan());

			String tyleBNtra = "" + (100 - hsttk.getHsthtoankTylebh());
			log.info("***********222tyleBNtra:" + tyleBNtra);

			if ("MP".equals(thamkham.getTiepdonMa(true).getDoituongMa(true)
					.getDmdoituongMa())) {
				tyleBNtra = "0";
			}
			params.put("PHANTRAMBNTRA", tyleBNtra);

			int iTuoi = 1;
			if(thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanTuoi() != null)
				iTuoi = thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanTuoi();
			int iDonviTuoi = 1;
			if(thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDonvituoi() != null)
				iDonviTuoi = thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDonvituoi();
			String sDonViTuoi = "";
			if (iDonviTuoi == 1)
				//bao.ttc: ko hien don vi tuoi neu tinh bang NAM
				//sDonViTuoi = IConstantsRes.REPORT_NAM;
				sDonViTuoi = "";
			else if (iDonviTuoi == 2)
				sDonViTuoi = IConstantsRes.REPORT_THANG;
			else if (iDonviTuoi == 3)
				sDonViTuoi = IConstantsRes.REPORT_NGAY;

			params.put("tuoi", iTuoi + " " + sDonViTuoi);
			params.put("GIOITINH", thamkham.getTiepdonMa().getBenhnhanMa()
					.getDmgtMaso().getDmgtTen());

			// 20110413 bao.ttc: xet vuot Tuyen hay khong trong class HSTTK Util
			if (bVuotTuyen){
				params.put("VUOTTUYEN", "X");
			} else{
				params.put("VUOTTUYEN", "");
			}
			
			DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate
					.getInstance();
			String maChanDoan = "";
			String tenChanDoan = "";

			//log.info(thamkham.getBenhicd10());

			if (thamkham.getBenhicd10() != null
					&& thamkham.getBenhicd10().getDmbenhicdMaso() != null) {
				DmBenhIcd benh = (DmBenhIcd) dieuTriUtilDelegate.findByMaSo(
						thamkham.getBenhicd10().getDmbenhicdMaso(),
						"DmBenhIcd", "dmbenhicdMaso");
				if (benh != null) {
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
				}

			}

			String chanDoan = maChanDoan + " " + tenChanDoan;
			
			// bao.ttc: them thamkham.ghichu
			if (thamkham.getThamkhamGhichu() != null && !thamkham.getThamkhamGhichu().equals("")) {
				chanDoan += " , " + thamkham.getThamkhamGhichu();
			}
			
			// tiep tuc them benh phu.
			if (thamkham.getBenhicd10phu1() != null
					&& thamkham.getBenhicd10phu1().getDmbenhicdMaso() != null) {
				DmBenhIcd benh = (DmBenhIcd) dieuTriUtilDelegate.findByMaSo(
						thamkham.getBenhicd10phu1().getDmbenhicdMaso(),
						"DmBenhIcd", "dmbenhicdMaso");
				if (benh != null) {
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					chanDoan += " , " + maChanDoan + " " + tenChanDoan;
				}

			}
			if (thamkham.getBenhicd10phu2() != null
					&& thamkham.getBenhicd10phu2().getDmbenhicdMaso() != null) {
				DmBenhIcd benh = (DmBenhIcd) dieuTriUtilDelegate.findByMaSo(
						thamkham.getBenhicd10phu2().getDmbenhicdMaso(),
						"DmBenhIcd", "dmbenhicdMaso");
				if (benh != null) {
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					chanDoan += " , " + maChanDoan + " " + tenChanDoan;
				}

			}
			if (thamkham.getBenhicd10phu3() != null
					&& thamkham.getBenhicd10phu3().getDmbenhicdMaso() != null) {
				DmBenhIcd benh = (DmBenhIcd) dieuTriUtilDelegate.findByMaSo(
						thamkham.getBenhicd10phu3().getDmbenhicdMaso(),
						"DmBenhIcd", "dmbenhicdMaso");
				if (benh != null) {
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					chanDoan += " , " + maChanDoan + " " + tenChanDoan;
				}

			}
			if (thamkham.getBenhicd10phu4() != null
					&& thamkham.getBenhicd10phu4().getDmbenhicdMaso() != null) {
				DmBenhIcd benh = (DmBenhIcd) dieuTriUtilDelegate.findByMaSo(
						thamkham.getBenhicd10phu4().getDmbenhicdMaso(),
						"DmBenhIcd", "dmbenhicdMaso");
				if (benh != null) {
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					chanDoan += " , " + maChanDoan + " " + tenChanDoan;
				}

			}
			if (thamkham.getBenhicd10phu5() != null
					&& thamkham.getBenhicd10phu5().getDmbenhicdMaso() != null) {
				DmBenhIcd benh = (DmBenhIcd) dieuTriUtilDelegate.findByMaSo(
						thamkham.getBenhicd10phu5().getDmbenhicdMaso(),
						"DmBenhIcd", "dmbenhicdMaso");
				if (benh != null) {
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					chanDoan += " , " + maChanDoan + " " + tenChanDoan;
				}

			}

			params.put("CHANDOAN", chanDoan);

			//bao.ttc: Dua Ma BK vao report de chon Thuoc theo BK
			params.put("PHONGKHAM", thamkham.getThamkhamBankham().getDtdmbankhamMaso());
			//log.info("############# PHONG KHAM: " + thamkham.getThamkhamBankham().getDtdmbankhamMaso());

			String sHuyetApMin = "";
			String sHuyetApMax = "";
			String sHuyetAp = "";
			String sMach = "";
			String sNhietDo = "";
			if (thamkham.getTiepdonMa().getTiepdonHamin() != null)
				sHuyetApMin = thamkham.getTiepdonMa().getTiepdonHamin()
						.toString();
			if (thamkham.getTiepdonMa().getTiepdonHamax() != null)
				sHuyetApMax = thamkham.getTiepdonMa().getTiepdonHamax()
						.toString();
			sHuyetAp = "     " + sHuyetApMax + " / " + sHuyetApMin;

			if (thamkham.getTiepdonMa().getTiepdonMach() != null)
				sMach = thamkham.getTiepdonMa().getTiepdonMach().toString();
			if (thamkham.getTiepdonMa().getTiepdonNhietdo() != null)
				sNhietDo = thamkham.getTiepdonMa().getTiepdonNhietdo()
						.toString();

			params.put("NHIETDO", sNhietDo);// thamkham.getTiepdonMa().getTiepdonNhietdo().toString());

			params.put("HUYETAP", sHuyetAp);// sHuyetApMin+"/"+sHuyetApMax);
			params.put("MACH", sMach);// thamkham.getTiepdonMa().getTiepdonMach().toString());

			String soTheTe_KhaiSinh_ChungSinh = thamkham.getTiepdonMa(true)
					.getTiepdonSothete();

			// them vao khai sinh
			if (soTheTe_KhaiSinh_ChungSinh == null
					|| soTheTe_KhaiSinh_ChungSinh.equals("")) {
				if (thamkham.getTiepdonMa(true).getTiepdonKhaisinh() == null
						|| thamkham.getTiepdonMa(true).getTiepdonKhaisinh()
								.equals("")) {

				} else {
					soTheTe_KhaiSinh_ChungSinh = thamkham.getTiepdonMa(true)
							.getTiepdonKhaisinh();
				}

			} else {
				if (thamkham.getTiepdonMa(true).getTiepdonKhaisinh() == null
						|| thamkham.getTiepdonMa(true).getTiepdonKhaisinh()
								.equals("")) {

				} else {
					soTheTe_KhaiSinh_ChungSinh += "/"
							+ thamkham.getTiepdonMa(true).getTiepdonKhaisinh();
				}
			}
			// them vao chung sinh

			if (soTheTe_KhaiSinh_ChungSinh == null
					|| soTheTe_KhaiSinh_ChungSinh.equals("")) {
				if (thamkham.getTiepdonMa(true).getTiepdonChungsinh() == null
						|| thamkham.getTiepdonMa(true).getTiepdonChungsinh()
								.equals("")) {

				} else {
					soTheTe_KhaiSinh_ChungSinh = thamkham.getTiepdonMa(true)
							.getTiepdonChungsinh();
				}

			} else {
				if (thamkham.getTiepdonMa(true).getTiepdonChungsinh() == null
						|| thamkham.getTiepdonMa(true).getTiepdonChungsinh()
								.equals("")) {

				} else {
					soTheTe_KhaiSinh_ChungSinh += "/"
							+ thamkham.getTiepdonMa(true).getTiepdonChungsinh();
				}
			}

			// 20110121 bao.ttc: lay loi dan cua Ke toa quay BV
			if (ctThuocPhongKhams != null && ctThuocPhongKhams.size() > 0) {

				for (int i = 0; i < ctThuocPhongKhams.size(); i++) {
					if ( ctThuocPhongKhams.get(i).getThuocphongkhamLoai().equals("3")
					&& ctThuocPhongKhams.get(i).getThuocphongkhamTenloidan() != null
					&& !ctThuocPhongKhams.get(i).getThuocphongkhamTenloidan().equals("") ) {
						params.put("LOIDAN", ctThuocPhongKhams.get(i).getThuocphongkhamTenloidan());
						break;
					}
				}

			}

			// log.info("soTheTe_KhaiSinh_ChungSinh"+soTheTe_KhaiSinh_ChungSinh);

			if (soTheTe_KhaiSinh_ChungSinh != null
					&& !soTheTe_KhaiSinh_ChungSinh.equals("")) {
				params.put("SOTHETEKSCS", soTheTe_KhaiSinh_ChungSinh);
			} else {
				params.put("SOTHETEKSCS", null);
			}

			log.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			log.info("da thay driver mysql");
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,
						IConstantsRes.DATABASE_USER,
						IConstantsRes.DATABASE_PASS);
			} catch (Exception e) {
				log.info(e.getMessage());
			}
			jasperPrintTD = JasperFillManager.fillReport(jasperReport, params,
					conn);
			baocao1 = XuatReportUtil.ReportUtil(jasperPrintTD, index,
					IConstantsRes.PATH_BASE
							+ IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI
							+ "tiepdon/", "pdf", reportTypeTD);
			reportPathTD = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
			log.info("duong dan file xuat report :" + baocao1);
			log.info("duong dan -------------------- :" + reportPathTD);

			log.info("Index :" + index);
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
		log.info("Thoat Method XuatReport");
	}


//	public void XuatReport_don_thuoc_ketoa_quay_nocls(Logger log, ThamKham pthamkham,
//			List<ThuocPhongKham> listCtTPK_temp, List<ClsKham> clslist,
//			java.util.List<ThuocPhongKham> ctThuocPhongKhams, int index) {
//
//		log.info("Vao Method XuatReport bao cao xu tri thuoc tai ban kham, khong in CLS");
//		ThamKham thamkham = ThamKhamDelegate.getInstance().find(pthamkham.getThamkhamMa());
//		String baocao1 = null;
//		try {
//
//			log.info("bat dau method XuatReport ");
//			String pathTemplate = "";
//			String sub1Template = "";
//			String sub2Template = "";
//			String sub3Template = "";
//			
//			if(IConstantsRes.MAU_TOA_THUOC.equals("A4")){
//				pathTemplate = IConstantsRes.PATH_BASE
//					+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
//					+ "tiepdon/toathuoc_nocls_main_A4.jrxml";
//				sub1Template = IConstantsRes.PATH_BASE
//					+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
//					+ "tiepdon/toathuoc_nocls_sub1_A4.jrxml";
//				sub2Template = IConstantsRes.PATH_BASE
//					+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
//					+ "tiepdon/toathuoc_nocls_sub2_A4.jrxml";
//				sub3Template = IConstantsRes.PATH_BASE
//					+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
//					+ "tiepdon/toathuoc_nocls_sub3_A4.jrxml";
//			}else{
//				pathTemplate = IConstantsRes.PATH_BASE
//					+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
//					+ "tiepdon/toathuoc_nocls_main.jrxml";
//				sub1Template = IConstantsRes.PATH_BASE
//					+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
//					+ "tiepdon/toathuoc_nocls_sub1.jrxml";
//				sub2Template = IConstantsRes.PATH_BASE
//					+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
//					+ "tiepdon/toathuoc_nocls_sub2.jrxml";
//				sub3Template = IConstantsRes.PATH_BASE
//					+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
//					+ "tiepdon/toathuoc_nocls_sub3.jrxml";
//			}
//			log.info("da thay file template " + pathTemplate);
//			log.info("da thay file sub 1 template " + sub1Template);
//			log.info("da thay file sub 2 template " + sub2Template);
//			log.info("da thay file sub 3 template " + sub3Template);
//
//			JasperReport jasperReport = JasperCompileManager
//					.compileReport(pathTemplate);
//			JasperReport sub1Report = JasperCompileManager
//					.compileReport(sub1Template);
//			JasperReport sub2Report = JasperCompileManager
//					.compileReport(sub2Template);
//			JasperReport sub3Report = JasperCompileManager
//					.compileReport(sub3Template);
//
//			SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
//			log.info("da thay file template ");
//			Map<String, Object> params = new HashMap<String, Object>();
//			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
//			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
//			params.put("DIENTHOAI",
//					IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
//
//			params.put("sub1", sub1Report);
//			params.put("sub2", sub2Report);
//			params.put("sub3", sub3Report);
//
//			params.put("MATIEPDON", thamkham.getTiepdonMa().getTiepdonMa());
//
//			//log.info("*****MATHAMKHAM: " + thamkham.getThamkhamMa());
//			params.put("MATHAMKHAM", thamkham.getThamkhamMa());
//
//			if (thamkham.getTiepdonMa(true).getKcbbhytMa(true)
//					.getDmbenhvienTen() != null)
//				params.put("NOIDKKCBBANDAU", thamkham.getTiepdonMa(true)
//						.getKcbbhytMa(true).getDmbenhvienTen());
//
//			params.put("HOTENBN", thamkham.getTiepdonMa().getBenhnhanMa()
//					.getBenhnhanHoten());
//			String diachistr = "";
//			if (thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDiachi() != null)
//				diachistr += thamkham.getTiepdonMa().getBenhnhanMa()
//						.getBenhnhanDiachi()
//						+ ",";
//			if (thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(true)
//					.getDmxaTen() != null)
//				diachistr += thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(
//						true).getDmxaTen()
//						+ ",";
//			if (thamkham.getTiepdonMa().getBenhnhanMa().getHuyenMa(true)
//					.getDmhuyenTen() != null)
//				diachistr += thamkham.getTiepdonMa().getBenhnhanMa()
//						.getHuyenMa(true).getDmhuyenTen()
//						+ ",";
//			if (thamkham.getTiepdonMa().getBenhnhanMa().getTinhMa(true)
//					.getDmtinhTen() != null)
//				diachistr += thamkham.getTiepdonMa().getBenhnhanMa().getTinhMa(
//						true).getDmtinhTen();
//			params.put("DIACHI", diachistr);
//			if (thamkham.getTiepdonMa().getTiepdonGiatri1() != null) {
//				params.put("GTTU", sdf.format(thamkham.getTiepdonMa()
//						.getTiepdonGiatri1()));
//			} else {
//				params.put("GTTU", "");
//			}
//
//			if (thamkham.getTiepdonMa().getTiepdonGiatri2() != null) {
//				params.put("GTDEN", sdf.format(thamkham.getTiepdonMa()
//						.getTiepdonGiatri2()));
//			} else {
//				params.put("GTDEN", "");
//			}
//			if (thamkham.getTiepdonMa().getTiepdonSothebh() != null
//					&& !thamkham.getTiepdonMa().getTiepdonSothebh().equals("")) {
//				String maBV = thamkham.getTiepdonMa(true).getKcbbhytMa(true)
//						.getDmbenhvienMa();
//				if (maBV == null) {
//					maBV = "";
//				}
//				// params.put("MATHEBHYT",
//				// thamkham.getTiepdonMa().getKhoibhytMa(true).getDtdmkhoibhytMa()+
//				// "-"+ thamkham.getTiepdonMa().getTiepdonSothebh()+ "-"+
//				// maBV.replace(".", "-"));
//				String maTheBHYT = thamkham.getTiepdonMa().getTiepdonSothebh();
//				params.put("MATHEBHYT", getMaTheBHYTDisplay(maTheBHYT));
//				//params.put("MABENHVIEN", maBV.replace(".", "-"));
//				params.put("MABENHVIEN", maBV);
//
//			} else {
//				params.put("MATHEBHYT", "");
//				params.put("MABENHVIEN", "");
//			}
//
//			if (thamkham.getThamkhamNgaygio() != null) {
//				params.put("NGAYKHAMBENH", thamkham.getThamkhamNgaygio());
//			} else {
//				params.put("NGAYKHAMBENH", new Date());
//			}
//
//			DieuTriUtilDelegate dtDele = DieuTriUtilDelegate.getInstance();
//
//			DtDmNhanVien bacsi = (DtDmNhanVien) dtDele.findByMa(thamkham
//					.getThamkhamBacsi(true).getDtdmnhanvienMa(),
//					"DtDmNhanVien", "dtdmnhanvienMa");
//			if (bacsi != null) {
//				params.put("BASIKHAMBENH", bacsi.getDtdmnhanvienTen());
//
//			}
//
//			params.put("NHANVIENCN", thamkham.getThamkhamNhanviencn(true)
//					.getDtdmnhanvienTen());
//
//			HsThtoank hsttk = new HsThtoank();
//			hsttk.setTiepdonMa(thamkham.getTiepdonMa());
//			tinhToanChoHSTKKham(thamkham, hsttk, false, listCtTPK_temp, clslist);
//			Utils.setInforForHsThToan(hsttk);
//
//			params.put("BHXHTHANHTOAN", hsttk.getHsthtoankBhyt());
//			params.put("NGUOIBENHTRA", hsttk.getHsthtoankThtoan());
//
//			String tyleBNtra = "" + (100 - hsttk.getHsthtoankTylebh());
//			log.info("***********222tyleBNtra:" + tyleBNtra);
//
//			if ("MP".equals(thamkham.getTiepdonMa(true).getDoituongMa(true)
//					.getDmdoituongMa())) {
//				tyleBNtra = "0";
//			}
//			params.put("PHANTRAMBNTRA", tyleBNtra);
//
//			int iTuoi = 1;
//			if(thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanTuoi() != null)
//				iTuoi = thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanTuoi();
//			int iDonviTuoi = 1;
//			if(thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDonvituoi() != null)
//				iDonviTuoi = thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDonvituoi();
//			String sDonViTuoi = "";
//			if (iDonviTuoi == 1)
//				//bao.ttc: ko hien don vi tuoi neu tinh bang NAM
//				//sDonViTuoi = IConstantsRes.REPORT_NAM;
//				sDonViTuoi = "";
//			else if (iDonviTuoi == 2)
//				sDonViTuoi = IConstantsRes.REPORT_THANG;
//			else if (iDonviTuoi == 3)
//				sDonViTuoi = IConstantsRes.REPORT_NGAY;
//
//			params.put("tuoi", iTuoi + " " + sDonViTuoi);
//			params.put("GIOITINH", thamkham.getTiepdonMa().getBenhnhanMa()
//					.getDmgtMaso().getDmgtTen());
//			
//			//Phuc add new code below			
////			String strVuotTuyen = "";
////			// Lay doi tuong BHYT (CC,CK,TE, ...)
////			String khoiBHYTMa = thamkham.getTiepdonMa().getKhoibhytMa(true).getDtdmkhoibhytMa();
////			// Lay tuyen
////			Short shortTuyen = null;
////			if (thamkham.getTiepdonMa().getTiepdonTuyen() != null)
////				shortTuyen = thamkham.getTiepdonMa().getTiepdonTuyen();
////			String strTuyen = (shortTuyen == null ? "0" : "" + shortTuyen.intValue());
////			
////			boolean coGiayChuyenVien = false;
////			if (thamkham.getTiepdonMa().getTiepdonCoGiayGioiThieu() != null)
////				coGiayChuyenVien = thamkham.getTiepdonMa().getTiepdonCoGiayGioiThieu();
////			
////			if ((khoiBHYTMa.equalsIgnoreCase("CC") || khoiBHYTMa.equalsIgnoreCase("CK") || khoiBHYTMa.equalsIgnoreCase("TE")) || 
////				(iDonviTuoi == 1 && iTuoi >= 85))	{
////				if (strTuyen.equals("3") && !(coGiayChuyenVien)) {
////					strVuotTuyen = "X";
////				}
////			} else {								
////				if ((strTuyen.equals("2") || strTuyen.equals("3") ) && !(coGiayChuyenVien)) { 
////					strVuotTuyen = "X";
////				}
////			}
////			params.put("VUOTTUYEN", strVuotTuyen);
//			// End Phuc add new code
//			
//			// 20110413 bao.ttc: xet vuot Tuyen hay khong trong class HSTTK Util
//			if (bVuotTuyen){
//				params.put("VUOTTUYEN", "X");
//			} else{
//				params.put("VUOTTUYEN", "");
//			}
//			
//			DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate
//					.getInstance();
//			String maChanDoan = "";
//			String tenChanDoan = "";
//
//			//log.info(thamkham.getBenhicd10());
//
//			if (thamkham.getBenhicd10() != null && thamkham.getBenhicd10().getDmbenhicdMaso() != null) {
//				DmBenhIcd benh = (DmBenhIcd) dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10().getDmbenhicdMaso(),
//																			"DmBenhIcd", "dmbenhicdMaso");
//				if (benh != null) {
//					maChanDoan = benh.getDmbenhicdMa();
//					tenChanDoan = benh.getDmbenhicdTen();
//				}
//			}
//
//			String chanDoan = maChanDoan + " " + tenChanDoan;
//			
//			// bao.ttc: them thamkham.ghichu
//			if (thamkham.getThamkhamGhichu() != null && !thamkham.getThamkhamGhichu().equals("")) {
//				chanDoan += ", " + thamkham.getThamkhamGhichu();
//			}
//			
//			// tiep tuc them benh phu.
//			if (thamkham.getBenhicd10phu1() != null
//					&& thamkham.getBenhicd10phu1().getDmbenhicdMaso() != null) {
//				DmBenhIcd benh = (DmBenhIcd) dieuTriUtilDelegate.findByMaSo(
//						thamkham.getBenhicd10phu1().getDmbenhicdMaso(),
//						"DmBenhIcd", "dmbenhicdMaso");
//				if (benh != null) {
//					maChanDoan = benh.getDmbenhicdMa();
//					tenChanDoan = benh.getDmbenhicdTen();
//					chanDoan += " , " + maChanDoan + " " + tenChanDoan;
//				}
//
//			}
//			if (thamkham.getBenhicd10phu2() != null
//					&& thamkham.getBenhicd10phu2().getDmbenhicdMaso() != null) {
//				DmBenhIcd benh = (DmBenhIcd) dieuTriUtilDelegate.findByMaSo(
//						thamkham.getBenhicd10phu2().getDmbenhicdMaso(),
//						"DmBenhIcd", "dmbenhicdMaso");
//				if (benh != null) {
//					maChanDoan = benh.getDmbenhicdMa();
//					tenChanDoan = benh.getDmbenhicdTen();
//					chanDoan += " , " + maChanDoan + " " + tenChanDoan;
//				}
//
//			}
//			if (thamkham.getBenhicd10phu3() != null
//					&& thamkham.getBenhicd10phu3().getDmbenhicdMaso() != null) {
//				DmBenhIcd benh = (DmBenhIcd) dieuTriUtilDelegate.findByMaSo(
//						thamkham.getBenhicd10phu3().getDmbenhicdMaso(),
//						"DmBenhIcd", "dmbenhicdMaso");
//				if (benh != null) {
//					maChanDoan = benh.getDmbenhicdMa();
//					tenChanDoan = benh.getDmbenhicdTen();
//					chanDoan += " , " + maChanDoan + " " + tenChanDoan;
//				}
//
//			}
//			if (thamkham.getBenhicd10phu4() != null
//					&& thamkham.getBenhicd10phu4().getDmbenhicdMaso() != null) {
//				DmBenhIcd benh = (DmBenhIcd) dieuTriUtilDelegate.findByMaSo(
//						thamkham.getBenhicd10phu4().getDmbenhicdMaso(),
//						"DmBenhIcd", "dmbenhicdMaso");
//				if (benh != null) {
//					maChanDoan = benh.getDmbenhicdMa();
//					tenChanDoan = benh.getDmbenhicdTen();
//					chanDoan += " , " + maChanDoan + " " + tenChanDoan;
//				}
//
//			}
//			if (thamkham.getBenhicd10phu5() != null
//					&& thamkham.getBenhicd10phu5().getDmbenhicdMaso() != null) {
//				DmBenhIcd benh = (DmBenhIcd) dieuTriUtilDelegate.findByMaSo(
//						thamkham.getBenhicd10phu5().getDmbenhicdMaso(),
//						"DmBenhIcd", "dmbenhicdMaso");
//				if (benh != null) {
//					maChanDoan = benh.getDmbenhicdMa();
//					tenChanDoan = benh.getDmbenhicdTen();
//					chanDoan += " , " + maChanDoan + " " + tenChanDoan;
//				}
//
//			}
//
//			params.put("CHANDOAN", chanDoan);
//
//			//bao.ttc: Dua Ma BK vao report de chon Thuoc theo BK
//			params.put("PHONGKHAM", thamkham.getThamkhamBankham().getDtdmbankhamMaso());
//			//log.info("############# PHONG KHAM: " + thamkham.getThamkhamBankham().getDtdmbankhamMaso());
//
//			String sHuyetApMin = "";
//			String sHuyetApMax = "";
//			String sHuyetAp = "";
//			String sMach = "";
//			String sNhietDo = "";
//			if (thamkham.getTiepdonMa().getTiepdonHamin() != null)
//				sHuyetApMin = thamkham.getTiepdonMa().getTiepdonHamin()
//						.toString();
//			if (thamkham.getTiepdonMa().getTiepdonHamax() != null)
//				sHuyetApMax = thamkham.getTiepdonMa().getTiepdonHamax()
//						.toString();
//			sHuyetAp = "     " + sHuyetApMax + " / " + sHuyetApMin;
//
//			if (thamkham.getTiepdonMa().getTiepdonMach() != null)
//				sMach = thamkham.getTiepdonMa().getTiepdonMach().toString();
//			if (thamkham.getTiepdonMa().getTiepdonNhietdo() != null)
//				sNhietDo = thamkham.getTiepdonMa().getTiepdonNhietdo()
//						.toString();
//
//			params.put("NHIETDO", sNhietDo);// thamkham.getTiepdonMa().getTiepdonNhietdo().toString());
//
//			params.put("HUYETAP", sHuyetAp);// sHuyetApMin+"/"+sHuyetApMax);
//			params.put("MACH", sMach);// thamkham.getTiepdonMa().getTiepdonMach().toString());
//
//			String soTheTe_KhaiSinh_ChungSinh = thamkham.getTiepdonMa(true)
//					.getTiepdonSothete();
//
//			// them vao khai sinh
//			if (soTheTe_KhaiSinh_ChungSinh == null
//					|| soTheTe_KhaiSinh_ChungSinh.equals("")) {
//				if (thamkham.getTiepdonMa(true).getTiepdonKhaisinh() == null
//						|| thamkham.getTiepdonMa(true).getTiepdonKhaisinh()
//								.equals("")) {
//
//				} else {
//					soTheTe_KhaiSinh_ChungSinh = thamkham.getTiepdonMa(true)
//							.getTiepdonKhaisinh();
//				}
//
//			} else {
//				if (thamkham.getTiepdonMa(true).getTiepdonKhaisinh() == null
//						|| thamkham.getTiepdonMa(true).getTiepdonKhaisinh()
//								.equals("")) {
//
//				} else {
//					soTheTe_KhaiSinh_ChungSinh += "/"
//							+ thamkham.getTiepdonMa(true).getTiepdonKhaisinh();
//				}
//			}
//			// them vao chung sinh
//
//			if (soTheTe_KhaiSinh_ChungSinh == null || soTheTe_KhaiSinh_ChungSinh.equals("")) {
//				if (thamkham.getTiepdonMa(true).getTiepdonChungsinh() != null && !thamkham.getTiepdonMa(true).getTiepdonChungsinh().equals("")) {
//					soTheTe_KhaiSinh_ChungSinh = thamkham.getTiepdonMa(true).getTiepdonChungsinh();
//				}
//
//			} else {
//				if (thamkham.getTiepdonMa(true).getTiepdonChungsinh() != null && !thamkham.getTiepdonMa(true).getTiepdonChungsinh().equals("")) {
//					soTheTe_KhaiSinh_ChungSinh += "/" + thamkham.getTiepdonMa(true).getTiepdonChungsinh();
//				}
//			}
//
//			// 20110121 bao.ttc: lay loi dan cua Ke toa quay BV
//			if (ctThuocPhongKhams != null && ctThuocPhongKhams.size() > 0) {
//				for (int i = 0; i < ctThuocPhongKhams.size(); i++) {
//					if ( ctThuocPhongKhams.get(i).getThuocphongkhamLoai().equals("3")
//					&& ctThuocPhongKhams.get(i).getThuocphongkhamTenloidan() != null
//					&& !ctThuocPhongKhams.get(i).getThuocphongkhamTenloidan().equals("") ) {
//						params.put("LOIDAN", ctThuocPhongKhams.get(i).getThuocphongkhamTenloidan());
//						break;
//					}
//				}
//			}
//
//			// log.info("soTheTe_KhaiSinh_ChungSinh"+soTheTe_KhaiSinh_ChungSinh);
//
//			if (soTheTe_KhaiSinh_ChungSinh != null && !soTheTe_KhaiSinh_ChungSinh.equals("")) {
//				params.put("SOTHETEKSCS", soTheTe_KhaiSinh_ChungSinh);
//			} else {
//				params.put("SOTHETEKSCS", null);
//			}
//
//			log.info("================= ");
//			Class.forName("oracle.jdbc.OracleDriver");
//			log.info("da thay driver mysql");
//			Connection conn = null;
//			try {
//				conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,
//						IConstantsRes.DATABASE_USER,
//						IConstantsRes.DATABASE_PASS);
//			} catch (Exception e) {
//				log.info(e.getMessage());
//			}
//			jasperPrintTD = JasperFillManager.fillReport(jasperReport, params,
//					conn);
//			baocao1 = XuatReportUtil.ReportUtil(jasperPrintTD, index,
//					IConstantsRes.PATH_BASE
//							+ IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI
//							+ "tiepdon/", "pdf", reportTypeTD);
//			reportPathTD = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
//			log.info("duong dan file xuat report :" + baocao1);
//			log.info("duong dan -------------------- :" + reportPathTD);
//
//			log.info("Index :" + index);
//			if (conn != null)
//				conn.close();
//		} catch (Exception e) {
//			log.info("ERROR Method XuatReport!!!");
//			e.printStackTrace();
//		}
//		log.info("Thoat Method XuatReport");
//	}
	
	public String XuatReport_don_thuoc_ketoa_quay(Logger log, ThamKham pthamkham,
			List<ThuocPhongKham> listCtTPK_temp, List<ClsKham> clslist,
			java.util.List<ThuocPhongKham> ctThuocPhongKhams, boolean inTheoBK, boolean incls, int index) {
		
		log.info("Vao XuatReport_don_thuoc_ketoa_quay");
		
		ThamKham thamkham = ThamKhamDelegate.getInstance().find(pthamkham.getThamkhamMa());
		TiepDon tiepdonTT = null;
		BenhNhan benhnhanTT = null;
		
		if ( thamkham != null ) {
			tiepdonTT = thamkham.getTiepdonMa();
			if ( tiepdonTT != null ) {
				benhnhanTT = tiepdonTT.getBenhnhanMa();
			}
		}
		
		if ( thamkham == null || tiepdonTT == null || benhnhanTT == null ) {
			log.info("XuatReport_don_thuoc_ketoa_quay ### thamkham == null || tiepdonTT == null || benhnhanTT == null");
			return "";
		}
		
		String baocao1 = null;
		try {

			//log.info("bat dau method XuatReport ");
			String pathTemplate = "";
			Map<String, Object> params = new HashMap<String, Object>();
			if(IConstantsRes.MAU_TOA_THUOC.equals("A4")){
				pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
													   + "tiepdon/toathuoc/A4/toathuoc_main.jasper";
				params.put("SUBREPORT_DIR", IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/toathuoc/A4/");
			}else{
				pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
													   + "tiepdon/toathuoc/A5/toathuoc_main.jasper";
				params.put("SUBREPORT_DIR", IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/toathuoc/A5/");
			}

			//log.info("da thay file templete " + pathTemplate);

			SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
			
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			
			params.put("MATIEPDON", tiepdonTT.getTiepdonMa());

			if (tiepdonTT.getKcbbhytMa(true).getDmbenhvienTen() != null){
				params.put("NOIDKKCBBANDAU", tiepdonTT.getKcbbhytMa(true).getDmbenhvienTen());
			}
			params.put("HOTENBN", benhnhanTT.getBenhnhanHoten());
			String diachistr = "";
			if (benhnhanTT.getBenhnhanDiachi() != null && !benhnhanTT.getBenhnhanDiachi().equals(""))
				diachistr += benhnhanTT.getBenhnhanDiachi() + ", ";
			if (benhnhanTT.getXaMa(true).getDmxaTen() != null)
				diachistr += benhnhanTT.getXaMa(true).getDmxaTen()	+ ", ";
			if (benhnhanTT.getHuyenMa(true).getDmhuyenTen() != null)
				diachistr += benhnhanTT.getHuyenMa(true).getDmhuyenTen() + ", ";
			if (benhnhanTT.getTinhMa(true).getDmtinhTen() != null)
				diachistr += benhnhanTT.getTinhMa(true).getDmtinhTen();
			params.put("DIACHI", diachistr);
			
			// 20110909 bao.ttc: them truong Co quan
			String coquan = "";
			if (tiepdonTT.getTiepdonMacoquan() != null){
				coquan = tiepdonTT.getTiepdonMacoquan();
			}
			params.put("COQUAN", coquan);
			
			if (tiepdonTT.getTiepdonGiatri1() != null) {
				params.put("GTTU", sdf.format(tiepdonTT.getTiepdonGiatri1()));
			} else {
				params.put("GTTU", "");
			}

			if (tiepdonTT.getTiepdonGiatri2() != null) {
				params.put("GTDEN", sdf.format(tiepdonTT.getTiepdonGiatri2()));
			} else {
				params.put("GTDEN", "");
			}
			if (tiepdonTT.getTiepdonSothebh() != null	&& !tiepdonTT.getTiepdonSothebh().equals("")) {
				String maBV = tiepdonTT.getKcbbhytMa(true).getDmbenhvienMa();
				if (maBV == null) {
					maBV = "";
				}
				String maTheBHYT = tiepdonTT.getTiepdonSothebh();
				params.put("MATHEBHYT", getMaTheBHYTDisplay(maTheBHYT));
				//params.put("MABENHVIEN", maBV.replace(".", "-"));
				params.put("MABENHVIEN", maBV);

			} else {
				params.put("MATHEBHYT", "");
				params.put("MABENHVIEN", "");
			}
			
			if (thamkham.getThamkhamNgaygio() != null) {
				params.put("NGAYKHAMBENH", thamkham.getThamkhamNgaygio());
			} else {
				params.put("NGAYKHAMBENH", new Date());
			}

			params.put("BASIKHAMBENH", thamkham.getThamkhamBacsi() == null ? "" : thamkham.getThamkhamBacsi().getDtdmnhanvienTen());

			// 20120522 bao.ttc: remove vi ko su dung
			
//			HsThtoank hsttk = new HsThtoank();
//			hsttk.setTiepdonMa(tiepdonTT);
//			tinhToanChoHSTKKham(thamkham, hsttk, false, listCtTPK_temp, clslist);
//			Utils.setInforForHsThToan(hsttk);
//
//			params.put("BHXHTHANHTOAN", hsttk.getHsthtoankBhyt());
//			params.put("NGUOIBENHTRA", hsttk.getHsthtoankThtoan());
//
//			String tyleBNtra = "" + (100 - hsttk.getHsthtoankTylebh());
//			log.info("***********222tyleBNtra:" + tyleBNtra);
//
//			if ("MP".equals(thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa())) {
//				tyleBNtra = "0";
//			}
//			params.put("PHANTRAMBNTRA", tyleBNtra);
//			params.put("NHANVIENCN", thamkham.getThamkhamNhanviencn(true).getDtdmnhanvienTen());

			int iTuoi = 1;
			if(benhnhanTT.getBenhnhanTuoi() != null)
				iTuoi = benhnhanTT.getBenhnhanTuoi();
			int iDonviTuoi = 1;
			if(benhnhanTT.getBenhnhanDonvituoi() != null)
				iDonviTuoi = benhnhanTT.getBenhnhanDonvituoi();
			String sDonViTuoi = "";
			if (iDonviTuoi == 1)
				// ko hien don vi tuoi neu tinh bang NAM
				//sDonViTuoi = IConstantsRes.REPORT_NAM;
				sDonViTuoi = "";
			else if (iDonviTuoi == 2)
				sDonViTuoi = IConstantsRes.REPORT_THANG;
			else if (iDonviTuoi == 3)
				sDonViTuoi = IConstantsRes.REPORT_NGAY;

			params.put("TUOI", iTuoi + " " + sDonViTuoi);
			
			// 20110909 bao.ttc: them truong Ho ten cha me doi voi BN Nhi < 6 tuoi
			// 20111130 bao.ttc: check lai Tuoi, neu khong phu hop thi set CHAME null
			if (iDonviTuoi == 1 && iTuoi > 6){
				params.put("CHAME", "");
			} else {
				if (benhnhanTT.getBenhnhanHotenchame() != null){
					String chame = "";
					chame = benhnhanTT.getBenhnhanHotenchame();
					params.put("CHAME", chame);
				}
			}
			
			params.put("GIOITINH", benhnhanTT.getDmgtMaso().getDmgtTen());
			
			// 20120523 bao.ttc: xet vuot Tuyen hay khong giong trong class HSTTK Util
			boolean bDoituongUutien = false;
			if("BH".equals(tiepdonTT.getDoituongMa(true).getDmdoituongMa())){				
				
				Short tuyen = tiepdonTT.getTiepdonTuyen();
				DtDmKhoiBhyt khoiBHYT = tiepdonTT.getKhoibhytMa();
				String tuoikhongxettuyen = IConstantsRes.TUOI_KHONG_XET_TUYEN;
				int iTuoiKoXetTuyen = 200;
				if (tuoikhongxettuyen != null && !tuoikhongxettuyen.equals("")){
					iTuoiKoXetTuyen = Integer.parseInt(tuoikhongxettuyen);
				}
				if (iTuoi >= iTuoiKoXetTuyen && iDonviTuoi == 1){					
					// phuc.lc 27-12-2010 : neu la doi tuong >= 85 tuoi va noi dang ky KCB la noi tinh thi duoc xem la doi tuong uu tien
					bDoituongUutien = false;
					if ( tuyen != null && (tuyen == 1 || tuyen == 2) ) {
						bDoituongUutien = true;
					}
				} else {
					//phuc.lc 27-12-2010 : Neu noi dang ky KCB khong phai la noi tinh thi khong duoc xem la doi tuong uu tien
					if ( tuyen != null && tuyen == 3 ) {
						bDoituongUutien = false;
					} else {
						bDoituongUutien = checkDTUuTien(khoiBHYT.getDtdmkhoibhytMa() == null ? "" : khoiBHYT.getDtdmkhoibhytMa());
					}
				}
					
				if (!bDoituongUutien) { 
					// Neu khong phai doi tuong uu tien, thi xet co vuot tuyen hay khong
					if ( tuyen != null && (tuyen == 2 || tuyen ==3)){ // vuot tuyen
						// la doi tuong ko uu tien + ko co giay
						if (tiepdonTT.getTiepdonCoGiayGioiThieu() == null	|| ( tiepdonTT.getTiepdonCoGiayGioiThieu() != null && tiepdonTT.getTiepdonCoGiayGioiThieu() == false)){
							bVuotTuyen = true;	
						}
					}
				}
				
			}
			
			if (bVuotTuyen){
				params.put("VUOTTUYEN", "X");
			} else{
				params.put("VUOTTUYEN", "");
			}
			
			//DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
			String maChanDoan = "";
			String tenChanDoan = "";
			String chanDoan = "";
			//log.info(thamkham.getBenhicd10());
			
			if (thamkham.getBenhicd10() != null	&& thamkham.getBenhicd10(true).getDmbenhicdMaso() != null) {
				maChanDoan = thamkham.getBenhicd10(true).getDmbenhicdMa();
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
			
			params.put("CHANDOAN", chanDoan);
			
			if(inTheoBK){//in tat ca thuoc cua BN, k di theo ban kham
				params.put("PHONGKHAM", thamkham.getThamkhamBankham().getDtdmbankhamMaso());
			}else{
				params.put("PHONGKHAM", null);
			}
			
			if(incls){
				params.put("IS_PRINTCLS", "1");
			}else{
				params.put("IS_PRINTCLS", "0");
			}
			
			String sHuyetApMin = "";
			String sHuyetApMax = "";
			String sHuyetAp = "";
			String sMach = "";
			String sNhietDo = "";
			if (tiepdonTT.getTiepdonHamin() != null)
				sHuyetApMin = tiepdonTT.getTiepdonHamin().toString();
			if (tiepdonTT.getTiepdonHamax() != null)
				sHuyetApMax = tiepdonTT.getTiepdonHamax()	.toString();
			sHuyetAp = "     " + sHuyetApMax + " / " + sHuyetApMin;

			if (tiepdonTT.getTiepdonMach() != null)
				sMach = tiepdonTT.getTiepdonMach().toString();
			if (tiepdonTT.getTiepdonNhietdo() != null)
				sNhietDo = tiepdonTT.getTiepdonNhietdo().toString();

			params.put("NHIETDO", sNhietDo);// tiepdonTT.getTiepdonNhietdo().toString());

			params.put("HUYETAP", sHuyetAp);// sHuyetApMin+"/"+sHuyetApMax);
			params.put("MACH", sMach);// tiepdonTT.getTiepdonMach().toString());

			String soTheTe_KhaiSinh_ChungSinh = thamkham.getTiepdonMa(true).getTiepdonSothete();

			// them vao khai sinh
			if (soTheTe_KhaiSinh_ChungSinh == null || soTheTe_KhaiSinh_ChungSinh.equals("")) {
				if (thamkham.getTiepdonMa(true).getTiepdonKhaisinh() != null && !thamkham.getTiepdonMa(true).getTiepdonKhaisinh().equals("")) {
					soTheTe_KhaiSinh_ChungSinh = thamkham.getTiepdonMa(true).getTiepdonKhaisinh();
				}
			} else {
				if (thamkham.getTiepdonMa(true).getTiepdonKhaisinh() != null && !thamkham.getTiepdonMa(true).getTiepdonKhaisinh().equals("")) {
					soTheTe_KhaiSinh_ChungSinh += "/" + thamkham.getTiepdonMa(true).getTiepdonKhaisinh();
				}
			}
			// them vao chung sinh

			if (soTheTe_KhaiSinh_ChungSinh == null || soTheTe_KhaiSinh_ChungSinh.equals("")) {
				if (thamkham.getTiepdonMa(true).getTiepdonChungsinh() != null && !thamkham.getTiepdonMa(true).getTiepdonChungsinh().equals("")) {
					soTheTe_KhaiSinh_ChungSinh = thamkham.getTiepdonMa(true).getTiepdonChungsinh();
				}

			} else {
				if (thamkham.getTiepdonMa(true).getTiepdonChungsinh() != null && !thamkham.getTiepdonMa(true).getTiepdonChungsinh().equals("")) {
					soTheTe_KhaiSinh_ChungSinh += "/" + thamkham.getTiepdonMa(true).getTiepdonChungsinh();
				}
			}

			// 20110121 bao.ttc: lay loi dan cua Ke toa quay BV
			if (ctThuocPhongKhams != null && ctThuocPhongKhams.size() > 0) {
				for (int i = 0; i < ctThuocPhongKhams.size(); i++) {
					if ( ctThuocPhongKhams.get(i).getThuocphongkhamLoai().equals("3")
									&& ctThuocPhongKhams.get(i).getThuocphongkhamTenloidan() != null
									&& !ctThuocPhongKhams.get(i).getThuocphongkhamTenloidan().equals("") ) {
						params.put("LOIDAN", ctThuocPhongKhams.get(i).getThuocphongkhamTenloidan());
						break;
					}
				}

			}

			// log.info("soTheTe_KhaiSinh_ChungSinh"+soTheTe_KhaiSinh_ChungSinh);
			if (soTheTe_KhaiSinh_ChungSinh != null && !soTheTe_KhaiSinh_ChungSinh.equals("")) {
				params.put("SOTHETEKSCS", soTheTe_KhaiSinh_ChungSinh);
			} else {
				params.put("SOTHETEKSCS", null);
			}
			
			//log.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			//log.info("da thay driver mysql");
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);
			} catch (Exception e) {
				log.info(e.getMessage());
			}
//			JRSwapFileVirtualizer virtualizer = new JRSwapFileVirtualizer(3, new JRSwapFile(IConstantsRes.PATH_BASE+ IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+ "tiepdon/", 2048, 1024), false);
//			params.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
			jasperPrintTD = JasperFillManager.fillReport(pathTemplate, params, conn);
			baocao1 = XuatReportUtil.ReportUtil(jasperPrintTD, index, IConstantsRes.PATH_BASE
																		+ IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI
																		+ "tiepdon/", "pdf", reportTypeTD);
			reportPathTD = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
			//log.info("duong dan file xuat report :" + baocao1);
			//log.info("duong dan -------------------- :" + reportPathTD);
//			virtualizer.setReadOnly(true);
//			virtualizer.cleanup();
			//log.info("Index :" + index);
			
			if (conn != null)
				conn.close();
			
		} catch (Exception e) {
			log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
			return "";
		}
		//log.info("Thoat Method XuatReport");
		return baocao1;
	}


//	public void XuatReport_don_thuoc_ketoa_quay_nocls(Logger log, ThamKham pthamkham,
//			List<ThuocPhongKham> listCtTPK_temp, List<ClsKham> clslist,
//			java.util.List<ThuocPhongKham> ctThuocPhongKhams, boolean inTheoBK, int index) {
//
//		log.info("Vao Method XuatReport bao cao xu tri thuoc tai ban kham, khong in CLS");
//		ThamKham thamkham = ThamKhamDelegate.getInstance().find(pthamkham.getThamkhamMa());
//		String baocao1 = null;
//		try {
//
//			log.info("bat dau method XuatReport ");
//			String pathTemplate = "";
//			String sub1Template = "";
//			String sub2Template = "";
//			String sub3Template = "";
//			
//			if(IConstantsRes.MAU_TOA_THUOC.equals("A4")){
//				pathTemplate = IConstantsRes.PATH_BASE
//					+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
//					+ "tiepdon/toathuoc_nocls_main_A4.jrxml";
//				sub1Template = IConstantsRes.PATH_BASE
//					+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
//					+ "tiepdon/toathuoc_nocls_sub1_A4.jrxml";
//				sub2Template = IConstantsRes.PATH_BASE
//					+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
//					+ "tiepdon/toathuoc_nocls_sub2_A4.jrxml";
//				sub3Template = IConstantsRes.PATH_BASE
//					+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
//					+ "tiepdon/toathuoc_nocls_sub3_A4.jrxml";
//			}else{
//				pathTemplate = IConstantsRes.PATH_BASE
//					+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
//					+ "tiepdon/toathuoc_nocls_main.jrxml";
//				sub1Template = IConstantsRes.PATH_BASE
//					+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
//					+ "tiepdon/toathuoc_nocls_sub1.jrxml";
//				sub2Template = IConstantsRes.PATH_BASE
//					+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
//					+ "tiepdon/toathuoc_nocls_sub2.jrxml";
//				sub3Template = IConstantsRes.PATH_BASE
//					+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
//					+ "tiepdon/toathuoc_nocls_sub3.jrxml";
//			}
//			log.info("da thay file template " + pathTemplate);
//			log.info("da thay file sub 1 template " + sub1Template);
//			log.info("da thay file sub 2 template " + sub2Template);
//			log.info("da thay file sub 3 template " + sub3Template);
//
//			JasperReport jasperReport = JasperCompileManager
//					.compileReport(pathTemplate);
//			JasperReport sub1Report = JasperCompileManager
//					.compileReport(sub1Template);
//			JasperReport sub2Report = JasperCompileManager
//					.compileReport(sub2Template);
//			JasperReport sub3Report = JasperCompileManager
//					.compileReport(sub3Template);
//
//			SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
//			log.info("da thay file template ");
//			Map<String, Object> params = new HashMap<String, Object>();
//			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
//			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
//			params.put("DIENTHOAI",
//					IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
//
//			params.put("sub1", sub1Report);
//			params.put("sub2", sub2Report);
//			params.put("sub3", sub3Report);
//			log.info("*****MATIEPDON: " + thamkham.getTiepdonMa().getTiepdonMa());
//			params.put("MATIEPDON", thamkham.getTiepdonMa().getTiepdonMa());
//
//			if (thamkham.getTiepdonMa(true).getKcbbhytMa(true)
//					.getDmbenhvienTen() != null)
//				params.put("NOIDKKCBBANDAU", thamkham.getTiepdonMa(true)
//						.getKcbbhytMa(true).getDmbenhvienTen());
//
//			params.put("HOTENBN", thamkham.getTiepdonMa().getBenhnhanMa()
//					.getBenhnhanHoten());
//			String diachistr = "";
//			if (thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDiachi() != null)
//				diachistr += thamkham.getTiepdonMa().getBenhnhanMa()
//						.getBenhnhanDiachi()
//						+ ",";
//			if (thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(true)
//					.getDmxaTen() != null)
//				diachistr += thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(
//						true).getDmxaTen()
//						+ ",";
//			if (thamkham.getTiepdonMa().getBenhnhanMa().getHuyenMa(true)
//					.getDmhuyenTen() != null)
//				diachistr += thamkham.getTiepdonMa().getBenhnhanMa()
//						.getHuyenMa(true).getDmhuyenTen()
//						+ ",";
//			if (thamkham.getTiepdonMa().getBenhnhanMa().getTinhMa(true)
//					.getDmtinhTen() != null)
//				diachistr += thamkham.getTiepdonMa().getBenhnhanMa().getTinhMa(
//						true).getDmtinhTen();
//			params.put("DIACHI", diachistr);
//			
//			// 20110909 bao.ttc: them truong Co quan, Ho ten cha me doi voi BN Nhi < 6 tuoi
//			String coquan = "";
//			if (thamkham.getTiepdonMa().getTiepdonMacoquan() != null){
//				coquan = thamkham.getTiepdonMa().getTiepdonMacoquan();
//			}
//			params.put("COQUAN", coquan);
//			
//			if (thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanHotenchame() != null){
//				String chame = "";
//				chame = thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanHotenchame();
//				params.put("CHAME", chame);
//			}
//			
//			if (thamkham.getTiepdonMa().getTiepdonGiatri1() != null) {
//				params.put("GTTU", sdf.format(thamkham.getTiepdonMa()
//						.getTiepdonGiatri1()));
//			} else {
//				params.put("GTTU", "");
//			}
//
//			if (thamkham.getTiepdonMa().getTiepdonGiatri2() != null) {
//				params.put("GTDEN", sdf.format(thamkham.getTiepdonMa()
//						.getTiepdonGiatri2()));
//			} else {
//				params.put("GTDEN", "");
//			}
//			if (thamkham.getTiepdonMa().getTiepdonSothebh() != null
//					&& !thamkham.getTiepdonMa().getTiepdonSothebh().equals("")) {
//				String maBV = thamkham.getTiepdonMa(true).getKcbbhytMa(true)
//						.getDmbenhvienMa();
//				if (maBV == null) {
//					maBV = "";
//				}
//				String maTheBHYT = thamkham.getTiepdonMa().getTiepdonSothebh();
//				params.put("MATHEBHYT", getMaTheBHYTDisplay(maTheBHYT));
//				//params.put("MABENHVIEN", maBV.replace(".", "-"));
//				params.put("MABENHVIEN", maBV);
//
//			} else {
//				params.put("MATHEBHYT", "");
//				params.put("MABENHVIEN", "");
//			}
//
//			if (thamkham.getThamkhamNgaygio() != null) {
//				params.put("NGAYKHAMBENH", thamkham.getThamkhamNgaygio());
//			} else {
//				params.put("NGAYKHAMBENH", new Date());
//			}
//
//			DieuTriUtilDelegate dtDele = DieuTriUtilDelegate.getInstance();
//
//			DtDmNhanVien bacsi = (DtDmNhanVien) dtDele.findByMa(thamkham
//					.getThamkhamBacsi(true).getDtdmnhanvienMa(),
//					"DtDmNhanVien", "dtdmnhanvienMa");
//			if (bacsi != null) {
//				params.put("BASIKHAMBENH", bacsi.getDtdmnhanvienTen());
//
//			}
//
//			params.put("NHANVIENCN", thamkham.getThamkhamNhanviencn(true)
//					.getDtdmnhanvienTen());
//
//			HsThtoank hsttk = new HsThtoank();
//			hsttk.setTiepdonMa(thamkham.getTiepdonMa());
//			tinhToanChoHSTKKham(thamkham, hsttk, false, listCtTPK_temp, clslist);
//			Utils.setInforForHsThToan(hsttk);
//
//			params.put("BHXHTHANHTOAN", hsttk.getHsthtoankBhyt());
//			params.put("NGUOIBENHTRA", hsttk.getHsthtoankThtoan());
//
//			String tyleBNtra = "" + (100 - hsttk.getHsthtoankTylebh());
//			log.info("***********222tyleBNtra:" + tyleBNtra);
//
//			if ("MP".equals(thamkham.getTiepdonMa(true).getDoituongMa(true)
//					.getDmdoituongMa())) {
//				tyleBNtra = "0";
//			}
//			params.put("PHANTRAMBNTRA", tyleBNtra);
//
//			int iTuoi = 1;
//			if(thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanTuoi() != null)
//				iTuoi = thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanTuoi();
//			int iDonviTuoi = 1;
//			if(thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDonvituoi() != null)
//				iDonviTuoi = thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDonvituoi();
//			String sDonViTuoi = "";
//			if (iDonviTuoi == 1)
//				//bao.ttc: ko hien don vi tuoi neu tinh bang NAM
//				//sDonViTuoi = IConstantsRes.REPORT_NAM;
//				sDonViTuoi = "";
//			else if (iDonviTuoi == 2)
//				sDonViTuoi = IConstantsRes.REPORT_THANG;
//			else if (iDonviTuoi == 3)
//				sDonViTuoi = IConstantsRes.REPORT_NGAY;
//
//			params.put("tuoi", iTuoi + " " + sDonViTuoi);
//			params.put("GIOITINH", thamkham.getTiepdonMa().getBenhnhanMa()
//					.getDmgtMaso().getDmgtTen());
//			
//			// 20110413 bao.ttc: xet vuot Tuyen hay khong trong class HSTTK Util
//			if (bVuotTuyen){
//				params.put("VUOTTUYEN", "X");
//			} else{
//				params.put("VUOTTUYEN", "");
//			}
//			
//			DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate
//					.getInstance();
//			String maChanDoan = "";
//			String tenChanDoan = "";
//
//			//log.info(thamkham.getBenhicd10());
//
//			if (thamkham.getBenhicd10() != null
//					&& thamkham.getBenhicd10().getDmbenhicdMaso() != null) {
//				DmBenhIcd benh = (DmBenhIcd) dieuTriUtilDelegate.findByMaSo(
//						thamkham.getBenhicd10().getDmbenhicdMaso(),
//						"DmBenhIcd", "dmbenhicdMaso");
//				if (benh != null) {
//					maChanDoan = benh.getDmbenhicdMa();
//					tenChanDoan = benh.getDmbenhicdTen();
//				}
//
//			}
//
//			String chanDoan = maChanDoan + " " + tenChanDoan;
//			
//			// bao.ttc: them thamkham.ghichu
//			if (thamkham.getThamkhamGhichu() != null && !thamkham.getThamkhamGhichu().equals("")) {
//				chanDoan += " , " + thamkham.getThamkhamGhichu();
//			}
//			
//			// tiep tuc them benh phu.
//			if (thamkham.getBenhicd10phu1() != null
//					&& thamkham.getBenhicd10phu1().getDmbenhicdMaso() != null) {
//				DmBenhIcd benh = (DmBenhIcd) dieuTriUtilDelegate.findByMaSo(
//						thamkham.getBenhicd10phu1().getDmbenhicdMaso(),
//						"DmBenhIcd", "dmbenhicdMaso");
//				if (benh != null) {
//					maChanDoan = benh.getDmbenhicdMa();
//					tenChanDoan = benh.getDmbenhicdTen();
//					chanDoan += " , " + maChanDoan + " " + tenChanDoan;
//				}
//
//			}
//			if (thamkham.getBenhicd10phu2() != null
//					&& thamkham.getBenhicd10phu2().getDmbenhicdMaso() != null) {
//				DmBenhIcd benh = (DmBenhIcd) dieuTriUtilDelegate.findByMaSo(
//						thamkham.getBenhicd10phu2().getDmbenhicdMaso(),
//						"DmBenhIcd", "dmbenhicdMaso");
//				if (benh != null) {
//					maChanDoan = benh.getDmbenhicdMa();
//					tenChanDoan = benh.getDmbenhicdTen();
//					chanDoan += " , " + maChanDoan + " " + tenChanDoan;
//				}
//
//			}
//			if (thamkham.getBenhicd10phu3() != null
//					&& thamkham.getBenhicd10phu3().getDmbenhicdMaso() != null) {
//				DmBenhIcd benh = (DmBenhIcd) dieuTriUtilDelegate.findByMaSo(
//						thamkham.getBenhicd10phu3().getDmbenhicdMaso(),
//						"DmBenhIcd", "dmbenhicdMaso");
//				if (benh != null) {
//					maChanDoan = benh.getDmbenhicdMa();
//					tenChanDoan = benh.getDmbenhicdTen();
//					chanDoan += " , " + maChanDoan + " " + tenChanDoan;
//				}
//
//			}
//			if (thamkham.getBenhicd10phu4() != null
//					&& thamkham.getBenhicd10phu4().getDmbenhicdMaso() != null) {
//				DmBenhIcd benh = (DmBenhIcd) dieuTriUtilDelegate.findByMaSo(
//						thamkham.getBenhicd10phu4().getDmbenhicdMaso(),
//						"DmBenhIcd", "dmbenhicdMaso");
//				if (benh != null) {
//					maChanDoan = benh.getDmbenhicdMa();
//					tenChanDoan = benh.getDmbenhicdTen();
//					chanDoan += " , " + maChanDoan + " " + tenChanDoan;
//				}
//
//			}
//			if (thamkham.getBenhicd10phu5() != null
//					&& thamkham.getBenhicd10phu5().getDmbenhicdMaso() != null) {
//				DmBenhIcd benh = (DmBenhIcd) dieuTriUtilDelegate.findByMaSo(
//						thamkham.getBenhicd10phu5().getDmbenhicdMaso(),
//						"DmBenhIcd", "dmbenhicdMaso");
//				if (benh != null) {
//					maChanDoan = benh.getDmbenhicdMa();
//					tenChanDoan = benh.getDmbenhicdTen();
//					chanDoan += " , " + maChanDoan + " " + tenChanDoan;
//				}
//
//			}
//
//			params.put("CHANDOAN", chanDoan);
//			
//			if(inTheoBK){//in tat ca thuoc cua BN, k di theo ban kham
//				params.put("MATHAMKHAM", thamkham.getThamkhamMa());
//				params.put("PHONGKHAM", thamkham.getThamkhamBankham().getDtdmbankhamMaso());
//			}else{
//				params.put("MATHAMKHAM", null);
//				params.put("PHONGKHAM", null);
//			}
//			
//			String sHuyetApMin = "";
//			String sHuyetApMax = "";
//			String sHuyetAp = "";
//			String sMach = "";
//			String sNhietDo = "";
//			if (thamkham.getTiepdonMa().getTiepdonHamin() != null)
//				sHuyetApMin = thamkham.getTiepdonMa().getTiepdonHamin()
//						.toString();
//			if (thamkham.getTiepdonMa().getTiepdonHamax() != null)
//				sHuyetApMax = thamkham.getTiepdonMa().getTiepdonHamax()
//						.toString();
//			sHuyetAp = "     " + sHuyetApMax + " / " + sHuyetApMin;
//
//			if (thamkham.getTiepdonMa().getTiepdonMach() != null)
//				sMach = thamkham.getTiepdonMa().getTiepdonMach().toString();
//			if (thamkham.getTiepdonMa().getTiepdonNhietdo() != null)
//				sNhietDo = thamkham.getTiepdonMa().getTiepdonNhietdo()
//						.toString();
//
//			params.put("NHIETDO", sNhietDo);// thamkham.getTiepdonMa().getTiepdonNhietdo().toString());
//
//			params.put("HUYETAP", sHuyetAp);// sHuyetApMin+"/"+sHuyetApMax);
//			params.put("MACH", sMach);// thamkham.getTiepdonMa().getTiepdonMach().toString());
//
//			String soTheTe_KhaiSinh_ChungSinh = thamkham.getTiepdonMa(true)
//					.getTiepdonSothete();
//
//			// them vao khai sinh
//			if (soTheTe_KhaiSinh_ChungSinh == null
//					|| soTheTe_KhaiSinh_ChungSinh.equals("")) {
//				if (thamkham.getTiepdonMa(true).getTiepdonKhaisinh() == null
//						|| thamkham.getTiepdonMa(true).getTiepdonKhaisinh()
//								.equals("")) {
//
//				} else {
//					soTheTe_KhaiSinh_ChungSinh = thamkham.getTiepdonMa(true)
//							.getTiepdonKhaisinh();
//				}
//
//			} else {
//				if (thamkham.getTiepdonMa(true).getTiepdonKhaisinh() == null
//						|| thamkham.getTiepdonMa(true).getTiepdonKhaisinh()
//								.equals("")) {
//
//				} else {
//					soTheTe_KhaiSinh_ChungSinh += "/"
//							+ thamkham.getTiepdonMa(true).getTiepdonKhaisinh();
//				}
//			}
//			// them vao chung sinh
//
//			if (soTheTe_KhaiSinh_ChungSinh == null
//					|| soTheTe_KhaiSinh_ChungSinh.equals("")) {
//				if (thamkham.getTiepdonMa(true).getTiepdonChungsinh() == null
//						|| thamkham.getTiepdonMa(true).getTiepdonChungsinh()
//								.equals("")) {
//
//				} else {
//					soTheTe_KhaiSinh_ChungSinh = thamkham.getTiepdonMa(true)
//							.getTiepdonChungsinh();
//				}
//
//			} else {
//				if (thamkham.getTiepdonMa(true).getTiepdonChungsinh() == null
//						|| thamkham.getTiepdonMa(true).getTiepdonChungsinh()
//								.equals("")) {
//
//				} else {
//					soTheTe_KhaiSinh_ChungSinh += "/"
//							+ thamkham.getTiepdonMa(true).getTiepdonChungsinh();
//				}
//			}
//
//			// 20110121 bao.ttc: lay loi dan cua Ke toa quay BV
//			if (ctThuocPhongKhams != null && ctThuocPhongKhams.size() > 0) {
//
//				for (int i = 0; i < ctThuocPhongKhams.size(); i++) {
//					if ( ctThuocPhongKhams.get(i).getThuocphongkhamLoai().equals("3")
//					&& ctThuocPhongKhams.get(i).getThuocphongkhamTenloidan() != null
//					&& !ctThuocPhongKhams.get(i).getThuocphongkhamTenloidan().equals("") ) {
//						params.put("LOIDAN", ctThuocPhongKhams.get(i).getThuocphongkhamTenloidan());
//						break;
//					}
//				}
//
//			}
//
//			// log.info("soTheTe_KhaiSinh_ChungSinh"+soTheTe_KhaiSinh_ChungSinh);
//
//			if (soTheTe_KhaiSinh_ChungSinh != null
//					&& !soTheTe_KhaiSinh_ChungSinh.equals("")) {
//				params.put("SOTHETEKSCS", soTheTe_KhaiSinh_ChungSinh);
//			} else {
//				params.put("SOTHETEKSCS", null);
//			}
//
//			log.info("================= ");
//			Class.forName("oracle.jdbc.OracleDriver");
//			log.info("da thay driver mysql");
//			Connection conn = null;
//			try {
//				conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,
//						IConstantsRes.DATABASE_USER,
//						IConstantsRes.DATABASE_PASS);
//			} catch (Exception e) {
//				log.info(e.getMessage());
//			}
//			jasperPrintTD = JasperFillManager.fillReport(jasperReport, params,
//					conn);
//			baocao1 = XuatReportUtil.ReportUtil(jasperPrintTD, index,
//					IConstantsRes.PATH_BASE
//							+ IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI
//							+ "tiepdon/", "pdf", reportTypeTD);
//			reportPathTD = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
//			log.info("duong dan file xuat report :" + baocao1);
//			log.info("duong dan -------------------- :" + reportPathTD);
//
//			log.info("Index :" + index);
//			if (conn != null)
//				conn.close();
//		} catch (Exception e) {
//			log.info("ERROR Method XuatReport!!!");
//			e.printStackTrace();
//		}
//		log.info("Thoat Method XuatReport");
//	}

	public String XuatReportBNVe(Logger log, ThamKham pthamkham,
			List<ThuocPhongKham> listCtTPK_temp, List<ClsKham> clslist,
			java.util.List<ThuocPhongKham> ctThuocPhongKhams, int index) {

		log.info("Vao XuatReportBNVe");
		
		ThamKham thamkham = ThamKhamDelegate.getInstance().find(pthamkham.getThamkhamMa());
		TiepDon tiepdonTT = null;
		BenhNhan benhnhanTT = null;
		
		if ( thamkham != null ) {
			tiepdonTT = thamkham.getTiepdonMa();
			if ( tiepdonTT != null ) {
				benhnhanTT = tiepdonTT.getBenhnhanMa();
			}
		}
		
		if ( thamkham == null || tiepdonTT == null || benhnhanTT == null ) {
			log.info("XuatReportBNVe ### thamkham == null || tiepdonTT == null || benhnhanTT == null");
			return "";
		}
		
		String baocao1 = null;
		try {
			//log.info("bat dau method XuatReport ");
			String pathTemplate = "";
			Map<String, Object> params = new HashMap<String, Object>();
			if(IConstantsRes.MAU_TOA_THUOC.equals("A4")){
				pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
													   + "tiepdon/toathuoc/A4/toathuocve.jasper";
			}else{
				pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
													   + "tiepdon/toathuoc/A5/toathuocve.jasper";
			}
			//log.info("da thay file templete " + pathTemplate);

			SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
			log.info("da thay file template ");
			
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);

			params.put("MATIEPDON", tiepdonTT.getTiepdonMa());

			params.put("NHANVIENCN", thamkham.getThamkhamNhanviencn(true).getDtdmnhanvienTen());
			params.put("MATHAMKHAM", thamkham.getThamkhamMa());

			params.put("HOTENBN", benhnhanTT.getBenhnhanHoten());
			String diachistr = "";
			if (benhnhanTT.getBenhnhanDiachi() != null && !benhnhanTT.getBenhnhanDiachi().equals(""))
				diachistr += benhnhanTT.getBenhnhanDiachi() + ", ";
			if (benhnhanTT.getXaMa(true).getDmxaTen() != null)
				diachistr += benhnhanTT.getXaMa(true).getDmxaTen()	+ ", ";
			if (benhnhanTT.getHuyenMa(true).getDmhuyenTen() != null)
				diachistr += benhnhanTT.getHuyenMa(true).getDmhuyenTen() + ", ";
			if (benhnhanTT.getTinhMa(true).getDmtinhTen() != null)
				diachistr += benhnhanTT.getTinhMa(true).getDmtinhTen();
			params.put("DIACHI", diachistr);
			
			// 20110909 bao.ttc: them truong Co quan
			String coquan = "";
			if (tiepdonTT.getTiepdonMacoquan() != null){
				coquan = tiepdonTT.getTiepdonMacoquan();
			}
			params.put("COQUAN", coquan);
			
			if (tiepdonTT.getTiepdonGiatri1() != null) {
				params.put("GTTU", sdf.format(tiepdonTT.getTiepdonGiatri1()));
			} else {
				params.put("GTTU", "");
			}

			if (tiepdonTT.getTiepdonGiatri2() != null) {
				params.put("GTDEN", sdf.format(tiepdonTT.getTiepdonGiatri2()));
			} else {
				params.put("GTDEN", "");
			}
			if (tiepdonTT.getTiepdonSothebh() != null	&& !tiepdonTT.getTiepdonSothebh().equals("")) {
				String maBV = tiepdonTT.getKcbbhytMa(true).getDmbenhvienMa();
				if (maBV == null) {
					maBV = "";
				}
				params.put("MATHEBHYT", tiepdonTT.getTiepdonSothebh()	
										+ "-"
										+ tiepdonTT.getKhoibhytMa(true).getDtdmkhoibhytMa()
										+ "-"
										+ maBV.replace(".", "-"));
				log.info("MATHEBHYT:" + params.get("MATHEBHYT"));
			} else {
				params.put("MATHEBHYT", "");
			}

			if (thamkham.getThamkhamNgaygio() != null) {
				params.put("NGAYKHAMBENH", thamkham.getThamkhamNgaygio());
			} else {
				params.put("NGAYKHAMBENH", new Date());
			}

			// 20120522 bao.ttc: remove vi ko su dung
			
//			HsThtoank hsttk = new HsThtoank();
//			hsttk.setTiepdonMa(tiepdonTT);
//			tinhToanChoHSTKKham(thamkham, hsttk, false, listCtTPK_temp, clslist);// tinhToanChoHSTKKham(thamkham,hsttk,false);
//			Utils.setInforForHsThToan(hsttk);
//
//			params.put("BHXHTHANHTOAN", hsttk.getHsthtoankBhyt());
//			params.put("NGUOIBENHTRA", hsttk.getHsthtoankThtoan());
//
//			String tyleBNtra = "" + (100 - hsttk.getHsthtoankTylebh());
//
//			if ("MP".equals(tiepdonTT.getDoituongMa(true).getDmdoituongMa())) {
//				tyleBNtra = "0";
//			}
//			params.put("PHANTRAMBNTRA", tyleBNtra);

			int iTuoi = 1;
			if(benhnhanTT.getBenhnhanTuoi() != null)
				iTuoi = benhnhanTT.getBenhnhanTuoi();
			int iDonviTuoi = 1;
			if(benhnhanTT.getBenhnhanDonvituoi() != null)
				iDonviTuoi = benhnhanTT.getBenhnhanDonvituoi();
			String sDonViTuoi = "";
			if (iDonviTuoi == 1) {
				sDonViTuoi = "";
				params.put("TUOI", iTuoi + "");
			} else if (iDonviTuoi == 2) {
				sDonViTuoi = IConstantsRes.REPORT_THANG;
				params.put("TUOI", iTuoi + " " + sDonViTuoi);
			} else if (iDonviTuoi == 3) {
				sDonViTuoi = IConstantsRes.REPORT_NGAY;
				params.put("TUOI", iTuoi + " " + sDonViTuoi);
			}
			
			// 20110909 bao.ttc: them truong Ho ten cha me doi voi BN Nhi < 6 tuoi
			// 20111130 bao.ttc: check lai Tuoi, neu khong phu hop thi set CHAME null
			if (iDonviTuoi == 1 && iTuoi > 6){
				params.put("CHAME", "");
			} else {
				if (benhnhanTT.getBenhnhanHotenchame() != null){
					String chame = "";
					chame = benhnhanTT.getBenhnhanHotenchame();
					params.put("CHAME", chame);
				}
			}

			params.put("BASIKHAMBENH", thamkham.getThamkhamBacsi() == null ? "" : thamkham.getThamkhamBacsi().getDtdmnhanvienTen());
			params.put("GIOITINH", benhnhanTT.getDmgtMaso().getDmgtTen());

			String maChanDoan = "";
			String tenChanDoan = "";
			String chanDoan = "";

			if (thamkham.getBenhicd10() != null	&& thamkham.getBenhicd10(true).getDmbenhicdMaso() != null) {
				maChanDoan = thamkham.getBenhicd10(true).getDmbenhicdMa();
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
			
			params.put("CHANDOAN", chanDoan);

			params.put("PHONGKHAM", thamkham.getThamkhamBankham().getDtdmbankhamTen());

			String sHuyetApMin = "";
			String sHuyetApMax = "";
			String sHuyetAp = "";
			String sMach = "";
			String sNhietDo = "";
			if (tiepdonTT.getTiepdonHamin() != null)
				sHuyetApMin = tiepdonTT.getTiepdonHamin().toString();
			if (tiepdonTT.getTiepdonHamax() != null)
				sHuyetApMax = tiepdonTT.getTiepdonHamax().toString();
			sHuyetAp = sHuyetApMax + " / " + sHuyetApMin;

			if (tiepdonTT.getTiepdonMach() != null)
				sMach = tiepdonTT.getTiepdonMach().toString();
			if (tiepdonTT.getTiepdonNhietdo() != null)
				sNhietDo = tiepdonTT.getTiepdonNhietdo().toString();

			params.put("NHIETDO", sNhietDo); // tiepdonTT.getTiepdonNhietdo().toString());

			params.put("HUYETAP", sHuyetAp); // sHuyetApMax + "/" + sHuyetApMin;
			params.put("MACH", sMach); // tiepdonTT.getTiepdonMach().toString());

			String soTheTe_KhaiSinh_ChungSinh = tiepdonTT.getTiepdonSothete();

			// them vao khai sinh
			if (soTheTe_KhaiSinh_ChungSinh == null || soTheTe_KhaiSinh_ChungSinh.equals("")) {
				if (tiepdonTT.getTiepdonKhaisinh() != null && !tiepdonTT.getTiepdonKhaisinh().equals("")) {
					soTheTe_KhaiSinh_ChungSinh = tiepdonTT.getTiepdonKhaisinh();
				}
			} else {
				if (tiepdonTT.getTiepdonKhaisinh() != null && !tiepdonTT.getTiepdonKhaisinh().equals("")) {
					soTheTe_KhaiSinh_ChungSinh += "/" + tiepdonTT.getTiepdonKhaisinh();
				}
			}
			// them vao chung sinh

			if (soTheTe_KhaiSinh_ChungSinh == null || soTheTe_KhaiSinh_ChungSinh.equals("")) {
				if (tiepdonTT.getTiepdonChungsinh() != null && !tiepdonTT.getTiepdonChungsinh().equals("")) {
					soTheTe_KhaiSinh_ChungSinh = tiepdonTT.getTiepdonChungsinh();
				}
			} else {
				if (tiepdonTT.getTiepdonChungsinh() != null && tiepdonTT.getTiepdonChungsinh().equals("")) {
					soTheTe_KhaiSinh_ChungSinh += "/" + tiepdonTT.getTiepdonChungsinh();
				}
			}

			if (ctThuocPhongKhams != null && ctThuocPhongKhams.size() > 0)
				params.put("LOIDAN", ctThuocPhongKhams.get(0).getThuocphongkhamTenloidan());

			if (soTheTe_KhaiSinh_ChungSinh != null && !soTheTe_KhaiSinh_ChungSinh.equals("")) {
				params.put("SOTHETEKSCS", soTheTe_KhaiSinh_ChungSinh);
			} else {
				params.put("SOTHETEKSCS", null);
			}

			//log.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			//log.info("da thay driver mysql");
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,
													IConstantsRes.DATABASE_USER,
													IConstantsRes.DATABASE_PASS);
			} catch (Exception e) {
				log.info(e.getMessage());
				return "";
			}
			jasperPrintTD = JasperFillManager.fillReport(pathTemplate, params, conn);
			baocao1 = XuatReportUtil.ReportUtil(jasperPrintTD, index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "tiepdon/", "pdf", reportTypeTD);
			reportPathTD = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
			//log.info("duong dan file xuat report :" + baocao1);
			//log.info("duong dan -------------------- :" + reportPathTD);

			//log.info("Index :" + index);
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
			return "";
		}
		//log.info("Thoat Method XuatReport");
		return baocao1;
	}

	public String getMaTheBHYTDisplay(String maThe) {
		String maKhoi = maThe.substring(0, 2);
		String maThe1 = maThe.substring(2, 3);
		String maThe2 = maThe.substring(3, 5);
		String maThe3 = maThe.substring(5, 7);
		String maThe4 = maThe.substring(7, 10);
		String maThe5 = maThe.substring(10, maThe.length());
		String strMaThe = maKhoi + " " + maThe1 + " " + maThe2 + " " + maThe3
				+ " " + maThe4 + " " + maThe5;
		return strMaThe;
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
	
}

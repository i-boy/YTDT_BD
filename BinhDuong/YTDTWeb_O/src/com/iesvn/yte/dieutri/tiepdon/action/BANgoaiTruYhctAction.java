package com.iesvn.yte.dieutri.tiepdon.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.faces.FacesMessages;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.BaNgoaiTruYhctDelegate;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.entity.BaNgoaiTruYhct;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B121_11_Bangoaitru_yhct")
@Synchronized(timeout = 6000000)
public class BANgoaiTruYhctAction implements Serializable {

	private static String FORMAT_DATE = "dd/MM/yyyy";

	private static final long serialVersionUID = 10L;
	private int stt;
	private String ngaySinh;
	
	private BaNgoaiTruYhct baNgoaiTruYhct;
	private ThamKham thamkham;
	private TiepDon tiepdon;
	private BenhNhan benhNhan;
	
	private static Logger log = Logger.getLogger(ThamKhamAction.class);
	
	@In(required = false)
	@Out(required = false)
	private String maBanKhamOut;
	
	@In(required = false)
	@Out(required = false)
	private String maTiepDonOut;
	
	
	public void resetValue() {

	}
	
	private String resultHidden = "";
	
	
	@Create
	@Begin(nested = true)
	public String init() throws Exception {
		log.info("*** Starting init BANgoaiTruYHCT ***  maTiepDonOut = " + maTiepDonOut + " , maBanKhamOut = " + maBanKhamOut);
		try{
			ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
			thamkham = tkDelegate.findByBanKhamVaMaTiepDon(maBanKhamOut,maTiepDonOut);
			if (thamkham == null){
				log.info("*** BANgoaiTruYHCT, thamkham == NULL !!");
				return "ghinhan";
			} else if (thamkham.getTiepdonMa() != null){
				tiepdon = thamkham.getTiepdonMa();
				if (tiepdon.getBenhnhanMa() == null){
					log.info("*** BANgoaiTruYHCT, benhnhan == NULL !!");
					return "ghinhan";
				}
				benhNhan = tiepdon.getBenhnhanMa();
			} else {
				log.info("*** BANgoaiTruYHCT, tiepdon == NULL !!");
				return "ghinhan";
			}
			
			BaNgoaiTruYhct baNgoaiTruYhctTemp = null;
			try{
				//phuc.lc 22-07-2011 : begin
				if(thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa().equalsIgnoreCase("BH")) {
					log.info("SotheBH = " + tiepdon.getTiepdonSothebh() + ", Ma BN = " + benhNhan.getBenhnhanMa());
					baNgoaiTruYhctTemp = BaNgoaiTruYhctDelegate.getInstance().findBySotheBHAndMaBNAndBanKhamYhct(tiepdon.getTiepdonSothebh(),
							                                                                             benhNhan.getBenhnhanMa(),
							                                                                             thamkham.getThamkhamBankham(true).getDtdmbankhamMaso());
				} else {
					log.info("Ma BN = " + thamkham.getTiepdonMa(true).getBenhnhanMa(true).getBenhnhanMa());
					baNgoaiTruYhctTemp = BaNgoaiTruYhctDelegate.getInstance().findBySotheBHAndMaBNAndBanKhamYhct(null,
                            																			benhNhan.getBenhnhanMa(),
                            																			thamkham.getThamkhamBankham(true).getDtdmbankhamMaso());
				}
				log.info("baNgoaiTruYhctTemp = " + baNgoaiTruYhctTemp);
				//phuc.lc 22-07-2011 : end
			}catch(Exception e){
				log.info("error:"+ e);
			}
			
			if (baNgoaiTruYhctTemp != null){
				baNgoaiTruYhct = baNgoaiTruYhctTemp;
				log.info("%%%%%%%%%%%%%%%%%%%%%baNgoaiTruYhct.getBantYhctMa(): " + baNgoaiTruYhct.getBantYhctMa());
			}else{
				baNgoaiTruYhct = new BaNgoaiTruYhct();
			}
			
			setOtherValue();
			
		}catch(Exception e){
			log.info("***init Exception = " + e);
		}
		log.info("***Finished init ***");
		return "BenhanngoaitruYHCT";
	}

	@End 
	public void end(){
		
	}
		
	// Ham ghi nhan
	//Xu ly cho nut ghi nhan 
	public String ghiNhanAjax() throws Exception {
		log.info("***Starting ghinhan, ma BANT = " + baNgoaiTruYhct.getBantYhctMa());
		
		
		baNgoaiTruYhct.setBantYhctThamkham(thamkham);
		
		// 20110321 bao.ttc: Luu lai Ma BA Ngoai tru de in phieu
		String maBANT = "";
		baNgoaiTruYhct.setBantYhctBenhnhan(benhNhan);
		baNgoaiTruYhct.setBantYhctBankham(thamkham.getThamkhamBankham());
		if (tiepdon.getDoituongMa(true).getDmdoituongMa().equalsIgnoreCase("BH")) {
			baNgoaiTruYhct.setBantYhctSothebh(tiepdon.getTiepdonSothebh());
		}
		if (baNgoaiTruYhct.getBantYhctBsdieutri() != null
				&& ("".equals(Utils.reFactorString(baNgoaiTruYhct.getBantYhctBsdieutri().getDtdmnhanvienMa())) 
						|| baNgoaiTruYhct.getBantYhctBsdieutri().getDtdmnhanvienMaso() == null) ) {
			baNgoaiTruYhct.setBantYhctBsdieutri(null);
		}
		
		maBANT = BaNgoaiTruYhctDelegate.getInstance().capNhatBenhAnNgoaiTruYhct(baNgoaiTruYhct, baNgoaiTruYhct.getBantYhctMa());
		
		if (maBANT != null && !maBANT.equals("")){
			baNgoaiTruYhct.setBantYhctMa(maBANT);
		}
		// END -- 20110321 bao.ttc: Luu lai Ma BA Ngoai tru de in phieu
		
		FacesMessages.instance().add(IConstantsRes.SUCCESS);
		log.info("***Finished ghinhan **");
		return "/B1_Tiepdon/B121_11_Bangoaitru_yhct";
	}
	
	//Xu ly cho nut quay lai
	@End
	public String quayLai()  throws Exception {
		log.info("*****End quayLai() *****");
		return "ghinhan";
	}
	
	public void lapBAmoi() {
		baNgoaiTruYhct = new BaNgoaiTruYhct();		
	}
	
	@Out(required=false)
	@In(required=false)
	private String reportPathTD=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeTD=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintTD = null;
	
	public String thuchienAction() {
		XuatReport();
		return "B160_Chonmenuxuattaptin";
	}
	
	public void XuatReport() {
		reportTypeTD = "benhAnNgoaitruYhct";
		log.info("Vao Method XuatReport bao cao xcap nhat thong tin chi tiet benh an ngoaitru Yhct");
		String baocao1 = null;
		try {
			log.info("bat dau method XuatReport ");
			String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhanngoaitruYhct.jrxml";
			String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhanngoaitruYhct_subreport0.jrxml";
			String sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhanngoaitruYhct_subreport1.jrxml";
			String sub3Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhanngoaitruYhct_subreport2.jrxml";

			log.info("da thay file templete " + pathTemplate);
			log.info("da thay file sub 1 templete " + sub1Template);
			log.info("da thay file sub 2 templete " + sub2Template);
			log.info("da thay file sub 3 templete " + sub3Template);

			JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
			JasperReport sub1Report = JasperCompileManager.compileReport(sub1Template);
			JasperReport sub2Report = JasperCompileManager.compileReport(sub2Template);
			JasperReport sub3Report = JasperCompileManager.compileReport(sub3Template);

			SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
			
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("DIENTHOAI",IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			params.put("GIAMDOCBENHVIEN", IConstantsRes.REPORT_DIEUTRI_GIAMDOC_BV);
			
			params.put("sub1", sub1Report);
			params.put("sub2", sub2Report);
			params.put("sub3", sub3Report);

			
			BaNgoaiTruYhct baNgoaiTruYhctTemp = BaNgoaiTruYhctDelegate.getInstance().find(baNgoaiTruYhct.getBantYhctMa());
			if (baNgoaiTruYhctTemp != null){
				baNgoaiTruYhct = baNgoaiTruYhctTemp;
			}
			
			// ************* PAGE 1 ************//

			String sHoTenBN = "";
			if (benhNhan.getBenhnhanHoten() != null){
				sHoTenBN = benhNhan.getBenhnhanHoten();
			}
			params.put("HOTENBN", sHoTenBN);
			
			int iTuoi = benhNhan.getBenhnhanTuoi();
			int iDonviTuoi = benhNhan.getBenhnhanDonvituoi();
			String sDonViTuoi = "";
			
			if (iDonviTuoi == 2){
				sDonViTuoi = IConstantsRes.THANG;// "Tháng";
			} else if (iDonviTuoi == 3)
				sDonViTuoi = IConstantsRes.NGAY; // "Ngày";

			params.put("TUOI", iTuoi + " " + sDonViTuoi);
			
			params.put("GIOITINH", benhNhan.getDmgtMaso(true).getDmgtTen());
			
			String sNgheNghiep = "";
			if (benhNhan.getBenhnhanNghe() != null){
				sNgheNghiep = benhNhan.getBenhnhanNghe(true).getDmnghenghiepTen();
				params.put("NGHENGHIEP", sNgheNghiep);
			}
			
			String sDanToc = "";
			if (benhNhan.getDantocMa() != null){
				sDanToc = benhNhan.getDantocMa(true).getDmdantocTen();
				params.put("DANTOC", sDanToc);
			}
			
			String sNgoaiKieu = "";
			if (benhNhan.getQuocgiaMa(true).getDmquocgiaMa() != null)
				sNgoaiKieu = benhNhan.getQuocgiaMa(true).getDmquocgiaMa();
			params.put("NGOAIKIEU", sNgoaiKieu);
			
			String diachistr = "";
			if (benhNhan.getBenhnhanDiachi() != null)
				diachistr += benhNhan.getBenhnhanDiachi();
			if (benhNhan.getXaMa(true).getDmxaTen() != null)
				diachistr += ", " + benhNhan.getXaMa(true).getDmxaTen();
			if (benhNhan.getHuyenMa(true).getDmhuyenTen() != null)
				diachistr += ", " + benhNhan.getHuyenMa(true).getDmhuyenTen();
			if (benhNhan.getTinhMa(true).getDmtinhTen() != null)
				diachistr += ", " + benhNhan.getTinhMa(true).getDmtinhTen();
			
			params.put("DIACHI", diachistr);
			
			String sNgaySinh = "";
			if (benhNhan.getBenhnhanNgaysinh() != null) {
				sNgaySinh = sdf.format(benhNhan.getBenhnhanNgaysinh());
			} else if (benhNhan.getBenhnhanNamsinh() != null){
				sNgaySinh = benhNhan.getBenhnhanNamsinh();
			}
			params.put("NGAYSINH", sNgaySinh);
			
			if (tiepdon.getDoituongMa() != null){
				params.put("DOITUONG", tiepdon.getDoituongMa(true).getDmdoituongTen());
			}
			
			String sSoVaoVien = "";
			if (baNgoaiTruYhct.getBantYhctSovaovien() != null){
				sSoVaoVien = baNgoaiTruYhct.getBantYhctSovaovien();
			}
			params.put("SOVAOVIEN", sSoVaoVien);

			String sGiaTriTu = "";
			String sGiaTriDen = "";
			String sMaTheBHYT = "";
			String sNoiLamViec = "";

			if (tiepdon.getTiepdonGiatri1() != null)
				sGiaTriTu = sdf.format(tiepdon.getTiepdonGiatri1());
			if (tiepdon.getTiepdonGiatri2() != null)
				sGiaTriDen = sdf.format(tiepdon.getTiepdonGiatri2());
			if (tiepdon.getTiepdonSothebh() != null)
				sMaTheBHYT = tiepdon.getTiepdonSothebh();
			if (tiepdon.getKcbbhytMa(true).getDmbenhvienMa() != null)
				sMaTheBHYT += " - " + tiepdon.getKcbbhytMa(true).getDmbenhvienMa();
			if (tiepdon.getTiepdonMacoquan() != null)
				sNoiLamViec = tiepdon.getTiepdonMacoquan();
			
			params.put("GTTU", sGiaTriTu);
			params.put("GTDEN", sGiaTriDen);
			params.put("MATHEBHYT", sMaTheBHYT);
			params.put("NOILAMVIEC", sNoiLamViec);
			
			String sKhiCanBaoTin = "";
			if (tiepdon.getTiepdonBaotin() != null)
				sKhiCanBaoTin = tiepdon.getTiepdonBaotin();
			params.put("BAOTIN", sKhiCanBaoTin);
			
			Date dVaoVien = new Date();
			//Date dRaVien = new Date();
			String sVaoVienLuc = "";
			
			if (tiepdon.getTiepdonNgaygio() != null) {
				dVaoVien = tiepdon.getTiepdonNgaygio();
				sVaoVienLuc = Utils.getGioPhutNgay(tiepdon.getTiepdonNgaygio());
			}
			params.put("NGAYGIOVAOVIEN", sVaoVienLuc);
			
			//if (hosobenhan.getHsbaNgaygiorav() != null) {
			//	dRaVien = hosobenhan.getHsbaNgaygiorav();
			//	params.put("GIORAVIEN", Utils.getGioPhutNgay(dRaVien));
			//	params.put("SONGAYDT", daysBetween(dVaoVien, dRaVien));
			//} else{
				params.put("SONGAYDT", daysBetween(dVaoVien, new Date()));
			//}
			
			
			String sDonViGoi = "";
			if (tiepdon.getTiepdonDonvigoi() != null){
				sDonViGoi = tiepdon.getTiepdonDonvigoi(true).getDmbenhvienTen();
			}
			params.put("DONVIGOI", sDonViGoi);
			
			String chuandoan_noichuyenden = "";
			if ( tiepdon.getTiepdonMachdoanb0() != null ) {
				chuandoan_noichuyenden = tiepdon.getTiepdonMachdoanb0(true).getDmbenhicdMa() + " - ";
				chuandoan_noichuyenden += tiepdon.getTiepdonMachdoanb0(true).getDmbenhicdTen();
			}
			params.put("CHUANDOAN_NOICHUYENDEN", chuandoan_noichuyenden);
			
			params.put("LYDOVAOVIEN", baNgoaiTruYhct.getBantYhctLydovaov());
			params.put("QUATRINHBENHLY", baNgoaiTruYhct.getBantYhctQtbenhly());
			params.put("TIEUSUBENHBANTHAN", baNgoaiTruYhct.getBantYhctTsubenhbt());
			params.put("TIEUSUBENHGIADINH", baNgoaiTruYhct.getBantYhctTsubenhgd());
			
			params.put("MACH", tiepdon.getTiepdonMach());
			params.put("NHIETDO", tiepdon.getTiepdonNhietdo());
			params.put("HUYETAPMAX", tiepdon.getTiepdonHamax());
			params.put("HUYETAPMIN", tiepdon.getTiepdonHamin());
			params.put("NHIPTHO", tiepdon.getTiepdonNhiptho());
			params.put("CANNANG", tiepdon.getTiepdonTrluong());
			
			params.put("BOPHANBIBENH", baNgoaiTruYhct.getBantYhctBpbibenh());
			params.put("XETNGHIEMCANTHIET", baNgoaiTruYhct.getBantYhctXncanthiet());
			
			
			// *************PAGE 2************//
			
			if (thamkham.getThamkhamKetqua() != null){
				params.put("KETQUA", thamkham.getThamkhamKetqua(true).getDmkqdtTen());
			}
			
			String sBenhChinh = "";
			String sBenhKemTheo = "";
				
			if (thamkham.getBenhicd10() != null) {
				sBenhChinh = thamkham.getBenhicd10(true).getDmbenhicdTen();
				params.put("BENHCHINH_MA", thamkham.getBenhicd10(true).getDmbenhicdMa());
			}
			params.put("BENHCHINH", sBenhChinh);
			if (thamkham.getBenhicd10phu1() != null) {
				sBenhKemTheo = thamkham.getBenhicd10phu1(true).getDmbenhicdTen();
				params.put("BENHPHU_MA", thamkham.getBenhicd10phu1(true).getDmbenhicdMa());
			}
			params.put("BENHPHU", sBenhKemTheo);
			
			params.put("VONGCHAN", baNgoaiTruYhct.getBantYhctVongchan());
			params.put("VANCHAN", baNgoaiTruYhct.getBantYhctVanchan());
			params.put("VANCHAN2", baNgoaiTruYhct.getBantYhctVanchan2());
			params.put("THIETCHAN", baNgoaiTruYhct.getBantYhctThietchan());
			params.put("CHANDOAN_BENHDANH", baNgoaiTruYhct.getBantYhctCdbenhdanh());
			params.put("CHANDOAN_BATCUONG", baNgoaiTruYhct.getBantYhctCdbatcuong());
			params.put("CHANDOAN_TANGPHU", baNgoaiTruYhct.getBantYhctCdtangphu());
			params.put("CHANDOAN_NGUYENNHAN", baNgoaiTruYhct.getBantYhctCdngnhan());
			params.put("DIEUTRI_PHEPCHUA", baNgoaiTruYhct.getBantYhctDtphepchua());
			params.put("DIEUTRI_PHUONGTHUOC", baNgoaiTruYhct.getBantYhctDtphthuoc());
			params.put("DIEUTRI_PHUONGHUYET", baNgoaiTruYhct.getBantYhctDtphhuyet());
			params.put("DIEUTRI_XOABOP", baNgoaiTruYhct.getBantYhctDtxoabop());
			params.put("CHEDOANTAINHA", baNgoaiTruYhct.getBantYhctDtcdatnha());
			params.put("CHEDOHOLYTAINHA", baNgoaiTruYhct.getBantYhctDtcdhltnha());
			params.put("TIENLUONG", baNgoaiTruYhct.getBantYhctTienluong());
			
			// *****PAGE 3*******//
			params.put("KETQUACLSCHINH", baNgoaiTruYhct.getBantYhctKqclschinh());
			params.put("KETQUAGIAIPHAUBENH", baNgoaiTruYhct.getBantYhctKqgpbenh());			
			params.put("PHAPTRI_YHHD", baNgoaiTruYhct.getBantYhctPtYhhd());
			params.put("PHAPTRI_YHCT", baNgoaiTruYhct.getBantYhctPtYhct());
			params.put("CHANDOANRAVIEN_YHHD", baNgoaiTruYhct.getBantYhctCdrvYhhd());
			params.put("CHANDOANRAVIEN_YHCT", baNgoaiTruYhct.getBantYhctCdrvYhct());
			params.put("HDTCCDTT", baNgoaiTruYhct.getBantYhctHdtCdtt());
			
			if (baNgoaiTruYhct.getBantYhctBsdieutri() != null)
				params.put("BACSIDT", baNgoaiTruYhct.getBantYhctBsdieutri(true).getDtdmnhanvienTen());
			
			Class.forName("oracle.jdbc.OracleDriver");
			log.info("da thay driver mysql");
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);
				log.info("Da connect DATABASE");
				jasperPrintTD = JasperFillManager.fillReport(jasperReport, params,conn);
				log.info("fillReport THANHCONG");
			} catch (Exception e) {
				log.info(e.getMessage());
				e.printStackTrace();
			}
			
			baocao1 = XuatReportUtil.ReportUtil(jasperPrintTD, index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "tiepdon/", "pdf", "BANgoaitruYhct");
			reportPathTD = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
			log.info("duong dan file xuat report :" + baocao1);
			log.info("duong dan -------------------- :" + reportPathTD);
			index += 1;
			log.info("Index :" + index);
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
		log.info("Thoat Method XuatReport");
	}
	
	private int index = 0;

	private void setOtherValue() {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
		if (benhNhan.getBenhnhanNgaysinh() != null) {
			ngaySinh = formatter.format(benhNhan.getBenhnhanNgaysinh().getTime());
		} else if(benhNhan.getBenhnhanNamsinh()!=null){
			ngaySinh = benhNhan.getBenhnhanNamsinh();
		}
			
	}

	//Ham huy cac service da khoi tao
	public void destroyService() {
		try {
			log.debug("===== begin destroyService() method");			
//			thamKhamWS = null;
			log.debug("***** End destroyService() method");
		} catch (Exception ex) {
			log.debug("*****destroyService Exception: " + ex);
		}
	}	
	
	private static final long ONE_HOUR = 60 * 60 * 1000L;

	private static long daysBetween(Date d1, Date d2) {
		return ( ((d2.getTime() - d1.getTime()) / (ONE_HOUR * 24)) + 1 );
	}
	
	//Ham  se duoc goi khi het session (session timeout cau hinh trong file web.xml)
	@Destroy 
	public void destroy() {
		log.info("************************* destroy ThamKhamAction");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public BenhNhan getBenhNhan() {
		return benhNhan;
	}

	public void setBenhNhan(BenhNhan benhNhan) {
		this.benhNhan = benhNhan;
	}


	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getStt() {
		return stt;
	}

	public void setStt(int stt) {
		this.stt = stt;
	}

	public ThamKham getThamkham() {
		return thamkham;
	}

	public void setThamkham(ThamKham thamkham) {
		this.thamkham = thamkham;
	}
	
	public BaNgoaiTruYhct getBaNgoaiTruYhct() {
		return baNgoaiTruYhct;
	}

	public void setBaNgoaiTruYhct(BaNgoaiTruYhct baNgoaiTruYhct) {
		this.baNgoaiTruYhct = baNgoaiTruYhct;
	}

	public String getResultHidden() {
		return resultHidden;
	}

	public void setResultHidden(String resultHidden) {
		this.resultHidden = resultHidden;
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


}



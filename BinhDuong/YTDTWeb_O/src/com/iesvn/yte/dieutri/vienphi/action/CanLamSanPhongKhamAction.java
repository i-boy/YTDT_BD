package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
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
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
import com.iesvn.yte.dieutri.delegate.ChuyenVaoNoiTruDelegate;
import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoankBackupDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.PcCumThuPhiDelegate;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.ChuyenVaoNoiTru;
import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.DtDmCls;
import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
import com.iesvn.yte.dieutri.entity.DtDmCum;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.HsThtoan;
import com.iesvn.yte.dieutri.entity.HsThtoank;
import com.iesvn.yte.dieutri.entity.HsThtoankBackup;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.PcCumThuPhi;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
import com.iesvn.yte.entity.DmBenhIcd;
import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.entity.DmGioiTinh;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.util.HoSoThanhToanKhamUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.ThamKhamUtil;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B3232_Canlamsanphongkham")
@Synchronized(timeout = 6000000)
public class CanLamSanPhongKhamAction implements Serializable {
	
	private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";

	private String position = com.iesvn.yte.util.IConstantsRes.WEB_PATH + "/"
			+ com.iesvn.yte.util.IConstantsRes.WEB_NAME + "/"
			+ com.iesvn.yte.util.IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI
			+ "vienphi/";

	private String resultReportName = "";
	private String reportFileNameHid = "";
	private String resultReportFileName = "";
	private String loaiFileXuat;
	@Out(required = false)
	private String isReport = "false";

	private static final long serialVersionUID = 10L;
	private BenhNhan benhNhan;
	private ThamKham thamKham;
	private ClsKham clskham;
	private String gioi;
	private String tuoi;
	private String ngaySinh;
	private String ngayTt;
	private String gioThanhToan;
	
	private String kyhieu;
	private String quyen;
	private String bienlai;
	private String sNoiDungThu;
	
	private String mahsttk;
	private String strBankham;
	@DataModel
	private java.util.List<ThuocPhongKham> ctClsPhongKhams; //list thuoc
	
	private List<ThuocPhongKham> listCtTPK_temp = null; // day la thuoc tai ban kham, khi thanh toan kham -> tinh luon
	private List<ClsKham> clslist = null;
	private void tinhToanChoHSTKKham(HsThtoank hsttk, Boolean ghinhan){
		HoSoThanhToanKhamUtil hsthtoankUtilTmp = new HoSoThanhToanKhamUtil(thamKham.getTiepdonMa());
		hsthtoankUtilTmp.tinhToanChoHSTKKham(hsttk);
		//Utils.setInforForHsThToan(hsttk);	
		//caculationThuoc(ctClsPhongKhams);
		
		if (ghinhan == true){
			listCtTPK_temp = hsthtoankUtilTmp.getListCtTPK_temp(); // thuoc phong kham tai ban kham			
			clslist = hsthtoankUtilTmp.getListCtkq_temp();
		}
	}
	

	private String maBanKham;
	// private String diengiai;
	// private String thoiGian;
	// Phan danh cho luoi du lieu
	private String loai = "";
	private String maCLS = "";
	private Integer maSoCLS = null;
	private String tenCLS = "";

	private String maKhoa = "";
	private Boolean mien = new Boolean(false);
	private Boolean ngoaiDanhMuc = new Boolean(false);
	private Boolean dichVu = new Boolean(false);
	private Boolean yeuCau = new Boolean(false);
	private Boolean kyThuatCao = new Boolean(false);
	private short soLuong = 0;
	private Double donGia = new Double(0);
	// Phan danh cho tinh toan
	private int permiengiam = 0;
	private Double miengiam = new Double(0);
	private Double thatthu = new Double(0);
	private int perbhytchi = 0;
	private Double conlai = new Double(0);
	private Double thanhtien1 = new Double(0);
	private int perbntra = 0;
	private Double bntra = new Double(0);
	private Double bntrakn = new Double(0);  // So tien bn tra ky nay
	
	private Double cls;
	private Double thuoc;
	private Double tienThuocTT = 0.0; //trung.th tong tien thuoc thanh toan
	private Double tienThuocDaTT = 0.0; // tien thuoc da Thanh Toan, dung de tinh Conlai
	
	private String resultHidden = "";
	private DtDmNhanVien nhanVienThungan = new DtDmNhanVien();

	private String maPhieu = "";
	
	private String maPhieuTT = ""; //trung.th ma phieu thanh toan

	private static Logger log = Logger
			.getLogger(CanLamSanPhongKhamAction.class);

	@DataModel
	private java.util.List<ClsKham> listCtkq = new java.util.ArrayList<ClsKham>();
	@DataModelSelection(value = "listCtkq")
	@Out(required = false)
	private ClsKham nhapctSelected;

	private boolean updateNhapct = false;
	private String disbledHuyPhieu;
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
	@In(required = false)
	@Out(required = false)
	private String tenChuongTrinh;
	
	
	
	@Out(required=false)
	@In(required=false)
	private String reportPathVP=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeVP=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintVP=null;
	
	private DtDmCum cum = null;

	public void refreshnhanvienthungan(){
		DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
		nhanVienThungan = nvDelegate.findByNd(identity.getUsername());
		if (nhanVienThungan == null) {
			nhanVienThungan = new DtDmNhanVien();
		}
	}
	
	@Restrict("#{s:hasRole('NV_VienPhi') or s:hasRole('QT_VienPhi')}")
	@Begin(join = true)
	public String init() throws Exception {
		log.info("***Starting init ***");
		emtyData();
		goToCLSPhongKham = "none";
		log.info("***Finished init ***");

		// set data from loggin persion
		/**
		 * TODO
		 */

		
		refreshnhanvienthungan();
		PcCumThuPhiDelegate pcCumThuPhiDelegate  = PcCumThuPhiDelegate.getInstance();
		PcCumThuPhi pc =  pcCumThuPhiDelegate.findPcCumThuPhiByNgayAndNhanVien(new Date(), nhanVienThungan.getDtdmnhanvienMa());
		
		if (pc!=null && pc.getDtdmnhanvienMa() != null){
			
			//nhanVienThungan = pc.getDtdmnhanvienMa();
			cum = pc.getDtdmcumMa();
		}
		
		
		
		// String userLogedIn = getUsername();
		//		
		// System.out.println("userLogedIn:"+userLogedIn);
		// System.out.println("userLogedIn:"+userLogedIn);
		// System.out.println("userLogedIn:"+userLogedIn);
		// System.out.println("userLogedIn:"+userLogedIn);
		// System.out.println("userLogedIn:"+userLogedIn);
		// System.out.println("userLogedIn:"+userLogedIn);
		// System.out.println("userLogedIn:"+userLogedIn);
		//		
		// if(userLogedIn != null && !userLogedIn.equals("") ){
		// DtDmNhanVienWSService service = new DtDmNhanVienWSServiceLocator();
		// DtDmNhanVienWS ws = service.getDtDmNhanVienWSPort();
		//			
		// DtDmNhanVien nhanVienLogedIn = ws.findByTenDangNhap(userLogedIn);
		// if (nhanVienLogedIn != null){
		// nhanVienThungan.setDtdmnhanvienMa(nhanVienLogedIn.getDtdmnhanvienMa());
		// }
		// }
		
		tenChuongTrinh = MyMenuYTDTAction.thuVienPhi;
		ctClsPhongKhams = new ArrayList<ThuocPhongKham>();
		
		return "ThuVienPhi_SoLieuKhamBenh_CanLamSanPhongKham";
	}

	@In(required = false)
	@Out(required = false)
	private String maTiepDonOut;
	

	@In(required = false)
	@Out(required = false)
	private String goToCLSPhongKham;
	
	
	@Factory("goToCLSPhongKham")
	@Begin(nested = true)
	public String comefromxuatthuocbhyt() throws Exception {
		goToCLSPhongKham = "gone";
		log.info("***Starting init ***");
		emtyData();
		log.info("***Finished init ***");

		// set data from loggin persion
		/**
		 * TODO
		 */

		refreshnhanvienthungan();
		PcCumThuPhiDelegate pcCumThuPhiDelegate  = PcCumThuPhiDelegate.getInstance();
		PcCumThuPhi pc =  pcCumThuPhiDelegate.findPcCumThuPhiByNgayAndNhanVien(new Date(), nhanVienThungan.getDtdmnhanvienMa());
		
		if (pc!=null && pc.getDtdmnhanvienMa() != null){
			
			//nhanVienThungan = pc.getDtdmnhanvienMa();
			cum = pc.getDtdmcumMa();
		}
		
		
		
		// String userLogedIn = getUsername();
		//		
		// System.out.println("userLogedIn:"+userLogedIn);
		// System.out.println("userLogedIn:"+userLogedIn);
		// System.out.println("userLogedIn:"+userLogedIn);
		// System.out.println("userLogedIn:"+userLogedIn);
		// System.out.println("userLogedIn:"+userLogedIn);
		// System.out.println("userLogedIn:"+userLogedIn);
		// System.out.println("userLogedIn:"+userLogedIn);
		//		
		// if(userLogedIn != null && !userLogedIn.equals("") ){
		// DtDmNhanVienWSService service = new DtDmNhanVienWSServiceLocator();
		// DtDmNhanVienWS ws = service.getDtDmNhanVienWSPort();
		//			
		// DtDmNhanVien nhanVienLogedIn = ws.findByTenDangNhap(userLogedIn);
		// if (nhanVienLogedIn != null){
		// nhanVienThungan.setDtdmnhanvienMa(nhanVienLogedIn.getDtdmnhanvienMa());
		// }
		// }
		
		tenChuongTrinh = MyMenuYTDTAction.thuVienPhi;
		
		maBanKham = null;
		thamKham.getTiepdonMa(true).setTiepdonMa(maTiepDonOut);
		
		displayInfor();
		
		return "ThuVienPhi_SoLieuKhamBenh_CanLamSanPhongKham";
	}

	@End
	public void endFunct(){
		
	}

	private void emtyData() {

		benhNhan = new BenhNhan();
		SetInforUtil.setInforIfNullForBN(benhNhan);
		thamKham = new ThamKham();
		// setInforIfNullForTiepDon();
		SetInforUtil.setInforIfNullForThamKham(thamKham);
		thamKham.setTiepdonMa(new TiepDon());
		thamKham.getTiepdonMa().setBenhnhanMa(benhNhan);
		SetInforUtil.setInforIfNullForTiepDon(thamKham.getTiepdonMa());

		clskham = new ClsKham();
		clskham.setClskhamThamkham(thamKham);

		setInforIfNullForClsKham(clskham);
		nhapctSelected = new ClsKham();
		setInforIfNullForClsKham(nhapctSelected);
		tuoi = "";
		ngaySinh = "";
		//ngayTt = "";
		//phuc.lc 28-12-2010 begin
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
		ngayTt = formatter.format(cal.getTime());		
		gioThanhToan = Utils.getGioPhut(cal.getTime()) ;
		//phuc.lc 28-12-2010 end

		resultHidden = "";
		reportFinished = "";
		reportFileNameHid = "";

		listCtkq = new ArrayList<ClsKham>();
		listCtTPK_temp = new ArrayList<ThuocPhongKham>();
		sNoiDungThu=IConstantsRes.NOI_DUNG_THU_CLS;
		// diengiai ="";

	}

	private void hasNoMaTiepDon() {
		emtyData();
		resetValue();
	}
	
	
	

	public String troVe() {
		try {
			log.info("***** troVe()");
			return "ThuVienPhi_SoLieuKhamBenh_CanLamSanPhongKham";
		} catch (Exception e) {
			log.info("***** End exception = " + e);
		}
		return null;
	}

	public void resetValue() {
		loai = "";
		maCLS = "";
		maSoCLS = null;
		tenCLS = "";
		maKhoa = "";
		mien = new Boolean(false);
		ngoaiDanhMuc = new Boolean(false);
		yeuCau = new Boolean(false);
		kyThuatCao = new Boolean(false);
		soLuong = 0;
		donGia = new Double(0);
		dichVu = new Boolean(false);
		
		sNoiDungThu=IConstantsRes.NOI_DUNG_THU_CLS;
		kyhieu = "";
		quyen="";
		bienlai="";
			
	}

	public void CopyFrom(ClsKham cls) {
		log.info("Begining CopyFrom()" + cls);
		loai = cls.getClskhamLoai();
		maSoCLS = cls.getClskhamMahang().getDtdmclsbgMaso();
		maCLS = cls.getClskhamMahang().getDtdmclsbgMa();
		tenCLS = cls.getClskhamMahang().getDtdmclsbgDiengiai();
		maKhoa = cls.getClskhamKhoa().getDmkhoaMa();
		mien = cls.getClskhamMien();
		ngoaiDanhMuc = cls.getClskhamNdm();
		yeuCau = cls.getClskhamYeucau();
		kyThuatCao = cls.getClskhamKtcao();
		soLuong = cls.getClskhamLan();
		donGia = cls.getClskhamDongia();
		dichVu = cls.getClskhamDichvu();
		log.info("End CopyFrom()");
	}

	public void CopyTo(ClsKham cls) {
		log.info("Begining CopyFrom()" + cls);
		cls.setClskhamLoai(loai);
		cls.getClskhamMahang().setDtdmclsbgMa(maCLS);
		cls.getClskhamMahang().setDtdmclsbgMaso(maSoCLS);
		cls.getClskhamMahang().setDtdmclsbgDiengiai(tenCLS);

		cls.getClskhamKhoa().setDmkhoaMa(maKhoa);
		cls.setClskhamMien(mien);
		cls.setClskhamNdm(ngoaiDanhMuc);
		cls.setClskhamYeucau(yeuCau);
		cls.setClskhamKtcao(kyThuatCao);
		cls.setClskhamLan(soLuong);
		cls.setClskhamDongia(donGia);
		cls.setClskhamDichvu(dichVu);
		log.info("End CopyFrom()");
	}

	
	/**
	 * 
	 * @param listCtkq
	 */
public void Caculation(List<ClsKham> listCtkq){
	tienThuocDaTT = 0.0;
	for (ThuocPhongKham thuocTT: ctClsPhongKhams) {
		if (thuocTT.getThuocphongkhamDatt() == null) {
			tienThuocDaTT = 0.0;
		} else if (thuocTT.getThuocphongkhamDatt() == true) {
			//tienThuocDaTT += (thuocTT.getThuocphongkhamDongia() == null ? 0 : thuocTT.getThuocphongkhamDongia())* thuocTT.getThuocphongkhamSoluong();
			tienThuocDaTT += thuocTT.getThuocphongkhamThanhtien();
		}
	}
			
	HsThtoank hsttk = new HsThtoank();
	hsttk.setTiepdonMa(thamKham.getTiepdonMa());
	HoSoThanhToanKhamUtil hsthtoankUtil = new HoSoThanhToanKhamUtil(thamKham.getTiepdonMa());
	hsthtoankUtil.tinhToanChoHSTKKham(hsttk);
	
	Utils.setInforForHsThToan(hsttk);
	 permiengiam = 0;
	 miengiam = hsthtoankUtil.getMiengiam();
	 thatthu = hsthtoankUtil.getThatthu();
	 perbhytchi = hsthtoankUtil.getPerbhytchi();
	 //conlai = hsttk.getHsthtoankThtoan() - tienThuocDaTT;	 
	 conlai = hsttk.getHsthtoankThtoan();	 
	 thanhtien1 = hsttk.getHsthtoankTongchi();
	 perbntra = hsthtoankUtil.getPerbntra();
	 //bntra = hsthtoankUtil.getBntra();
	 bntra = hsttk.getHsthtoankBntra();
	
	 cls = ( hsttk.getHsthtoankCls() == null?0:hsttk.getHsthtoankCls().doubleValue() ) 
			 + ( hsttk.getHsthtoankClsndm() == null?0:hsttk.getHsthtoankClsndm().doubleValue()) ;

	 thuoc = ( hsttk.getHsthtoankThuoc() == null?0:hsttk.getHsthtoankThuoc().doubleValue() ) 
	 + ( hsttk.getHsthtoankThuocndm() == null?0:hsttk.getHsthtoankThuocndm().doubleValue() ) 
	  + ( hsttk.getHsthtoankVtth() == null?0:hsttk.getHsthtoankVtth().doubleValue() ) 
	  + ( hsttk.getHsthtoankVtthndm() == null?0:hsttk.getHsthtoankVtthndm().doubleValue() )  ;
	 
	 
}
	//trung.th Tinh toan va cap nhat lai thuoc
	public void caculationThuoc(List<ThuocPhongKham> listThuoc){
		tienThuocTT = 0.0;
		for (ThuocPhongKham thuoc: listThuoc) {
			log.info("In caculationThuoc, quoc gia = " + thuoc.getThuocphongkhamQuocgia());
			if (thuoc.getThuocphongkhamHangsx() != null &&
					thuoc.getThuocphongkhamHangsx().getDmnhasanxuatMaso() == null) {
				thuoc.setThuocphongkhamHangsx(null);
			}
			if (thuoc.getThuocphongkhamQuocgia() != null &&
					thuoc.getThuocphongkhamQuocgia().getDmquocgiaMaso() == null) {
				thuoc.setThuocphongkhamQuocgia(null);
			}
			if(thuoc.getThuocphongkhamDatt() == null) {
				ThuocPhongKhamDelegate thuocDel = ThuocPhongKhamDelegate.getInstance();
				//conlai -= thuoc.getThuocphongkhamDongia() * thuoc.getThuocphongkhamSoluong();
				//tienThuocTT += (thuoc.getThuocphongkhamDongia() == null ? 0 : thuoc.getThuocphongkhamDongia()) * thuoc.getThuocphongkhamSoluong();
				tienThuocTT += thuoc.getThuocphongkhamThanhtien();
				thuoc.setThuocphongkhamDatt(true);
				
	            Calendar dCalendar = Calendar.getInstance();
	            thuoc.setThuocphongkhamNgaygiott(dCalendar.getTime());

	            thuocDel.edit(thuoc);
			} else if (thuoc.getThuocphongkhamDatt() == false) {
				ThuocPhongKhamDelegate thuocDel = ThuocPhongKhamDelegate.getInstance();
				//conlai -= thuoc.getThuocphongkhamDongia() * thuoc.getThuocphongkhamSoluong();
				//tienThuocTT += (thuoc.getThuocphongkhamDongia() == null ? 0 : thuoc.getThuocphongkhamDongia()) * thuoc.getThuocphongkhamSoluong();
				tienThuocTT += thuoc.getThuocphongkhamThanhtien();
				thuoc.setThuocphongkhamDatt(true);
				
	            Calendar dCalendar = Calendar.getInstance();
	            thuoc.setThuocphongkhamNgaygiott(dCalendar.getTime());

	            thuocDel.edit(thuoc);
			}
		}
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

	// Ham ghi nhan thuc thi sau khi nhan nut Ghi Nhan
	public void ghinhan() throws Exception {
		log.info("*****Begin Ghi nhan(), thu ngan = " + nhanVienThungan);
		refreshnhanvienthungan();
		if (maPhieu != null && !maPhieu.equals("")) {
			return;
		}

		if (listCtkq == null) {
			listCtkq = new ArrayList<ClsKham>();
		}

		
		log.info("*****so phan tu insert *****" + listCtkq.size());
		boolean checkThuoc = false;
		if (ctClsPhongKhams.size() == 0) {
			checkThuoc = true;
		} else {
			for (ThuocPhongKham thuocTT: ctClsPhongKhams) {
				if (thuocTT.getThuocphongkhamDatt() == null) {
					checkThuoc = false;
					break;
				} else if (thuocTT.getThuocphongkhamDatt()){
					checkThuoc = true;
				}
			}
		}
		boolean checked = false;
		if (checkThuoc) {
			// phuc.lc 28-10-2010
			if (listCtkq.size() == 1) {
				if (listCtkq.get(0).getClskhamNgaygiott() == null && 
						listCtkq.get(0).getClskhamDatt() == false) {
					FacesMessages.instance().add(IConstantsRes.CHUA_CHON_CLS);
					return;
				}
			} else {
				for (int i = 0; i< listCtkq.size(); i++) {
					if (i > 0 && listCtkq.get(i).getClskhamNgaygiott() == null) {
						checked = listCtkq.get(i).getClskhamDatt();
						if (checked) {
							break;
						} 
					}
					if (i == (listCtkq.size() - 1) && checked == false) {
						FacesMessages.instance().add(IConstantsRes.CHUA_CHON_CLS);
						return;
					}
				}
			}
		}

		if (this.thamKham.getTiepdonMa() == null
				|| this.thamKham.getTiepdonMa().getTiepdonMa().equals("")

				|| this.benhNhan.getBenhnhanHoten() == null
				|| this.benhNhan.getBenhnhanHoten().equals("")) {

			resetValue();
			return;
		}

		try {
			/* phuc.lc 07/01/2011 : begin comment:  Chuyen doan code nay xuong duoi phan luu Hsthtoan kham de lay ma phieu Hsthtoan kham lam ma phieu cho CLS
			// ClsKhamWSService clskhamService = new ClsKhamWSServiceLocator();
			// ClsKhamWS clskhamWS = clskhamService.getClsKhamWSPort();

			ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();

			// ClsKham[] clsKhamArray = new ClsKham[listCtkq.size()];
			// log.info("clsKhamArray:" + clsKhamArray);
			for (int i = 0; i < listCtkq.size(); i++) {
				removeIfNullForClsKham(listCtkq.get(i));

				//listCtkq.get(i).setClskhamThungan(nhanVienThungan);
				// listCtkq.get(i).setClskhamDatt(true);
				// listCtkq.get(i).setClskhamThamkham(thamKham);

				// RemoveUtil.removeAllNullFromThamKham(listCtkq.get(i)
				// .getClskhamThamkham());
				// RemoveUtil.removeAllNullFromTiepDon(listCtkq.get(i)
				// .getClskhamThamkham().getTiepdonMa());

				// clsKhamArray[i] = listCtkq.get(i);
			}
			// log.info("clsKhamArray:" + clsKhamArray);
			// log.info("clsKhamArray size :" + clsKhamArray.length);

			ArrayList<ClsKham> arrayListHasMaPhieu = new ArrayList<ClsKham>();
			for (ClsKham cls : listCtkq) {
				
				if (cls.getClskhamCum() == null && ( cls.getClskhamMaphieu() == null
					 || cls.getClskhamMaphieu().equals("") ) && cls.getClskhamDatt() != null &&
					 cls.getClskhamDatt().booleanValue() ==  true){
					cls.setClskhamCum(cum);
					cls.setClskhamThungan(nhanVienThungan);
					
					cls.setClskhamKyHieu(kyhieu);
					cls.setClskhamQuyen(quyen);
					cls.setClskhamBienLai(bienlai);
					cls.setClskhamNoidungthu(sNoiDungThu);
					log.info("*****sNoiDungThu: "+sNoiDungThu);
					
					SimpleDateFormat formatter;	    
			        formatter = new SimpleDateFormat(Utils.FORMAT_DATE); 
			       
			       
		           Date d =	formatter.parse(ngayTt);
		           Calendar dCalendar = Calendar.getInstance();
		           dCalendar.setTime(d);
			          
		           cls.setClskhamNgaygiott(dCalendar.getTime());
					
				}
				if (cls.getClskhamMaphieu() != null
						&& !cls.getClskhamMaphieu().equals("")) {
					arrayListHasMaPhieu.add(cls);
				}
			}
			
			 for (ClsKham cls: listCtkq){
				 
				 log.info(cls.getClskhamDatt());
				 log.info(cls.getClskhamMa());
				 log.info(cls.getClskhamMaphieu());
				 log.info(cls.getClskhamMaphieu() == null);
				 
			 }
			 //Khong lay ma phieu trong clskham ma lay trong hsthtoank
			String maPhieu = "";

			if (maBanKham == null || maBanKham.equals("")) {
				maPhieu = clsKhamDelegate.createClsKham(listCtkq, thamKham
						.getTiepdonMa().getTiepdonMa());

			} else {
				maPhieu = clsKhamDelegate.createClsKham(listCtkq, thamKham
						.getTiepdonMa().getTiepdonMa(), maBanKham);

			}

			this.maPhieu = maPhieu;

			/* phuc.lc 07/01/2011 : End comment */
//////////////////////////////////////////////////////////////////
			if ("TP".equals( thamKham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa())
					&& !"CCL".equals( thamKham.getTiepdonMa(true).getTiepdonBankham(true).getDtdmbankhamMa())
					&& !"CCN".equals( thamKham.getTiepdonMa(true).getTiepdonBankham(true).getDtdmbankhamMa())
					){
				/**
				 *  xoa (neu da co') va` tao moi
				 * 
				 */
				HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
				//HsThtoank hsttk = hsttkDelegate.findBytiepdonMa(thamKham.getTiepdonMa(true).getTiepdonMa());
				// phuc.lc 05/01/2010 : begin fix bug 1959
				//HsThtoank hsttk = hsttkDelegate.findBytiepdonMaLast(thamKham.getTiepdonMa(true).getTiepdonMa());
				// phuc.lc 05/01/2010 : end fix bug 1959
				//if (hsttk != null){
				//	hsttkDelegate.remove(hsttk);
				//}
				
				HsThtoank hsttk = new HsThtoank();
				hsttk.setTiepdonMa(thamKham.getTiepdonMa());
				//phuc.lc: set miengiam
				hsttk.setHsthtoankXetgiam(miengiam);
				//bao.ttc: set thatthu, kyhieu, quyen, bienlai
				hsttk.setHsthtoankThatthu(thatthu);
				hsttk.setHsthtoankKyhieu(kyhieu);
				hsttk.setHsthtoankQuyen(quyen);
				hsttk.setHsthtoankBienlai(bienlai);
				log.info("In ghinhan() 1, that thu = " + hsttk.getHsthtoankThatthu() + ", ky hieu = " + hsttk.getHsthtoankKyhieu() + ", quyen = " + hsttk.getHsthtoankQuyen() + ", bien lai = " + hsttk.getHsthtoankBienlai());
				tinhToanChoHSTKKham(hsttk,true);
				log.info("In ghinhan() 2, that thu = " + hsttk.getHsthtoankThatthu() + ", ky hieu = " + hsttk.getHsthtoankKyhieu() + ", quyen = " + hsttk.getHsthtoankQuyen() + ", bien lai = " + hsttk.getHsthtoankBienlai());
				caculationThuoc(ctClsPhongKhams);
				
				//bao.ttc: set lai HSTHTOAN_thtoan sau khi tru tien thuoc Da TT
				tienThuocDaTT = 0.0;
				for (ThuocPhongKham thuocTT: ctClsPhongKhams) {
					if (thuocTT.getThuocphongkhamDatt() == null) {
						//tienThuocDaTT = 0.0;
					} else if (thuocTT.getThuocphongkhamDatt() == true) {
						//tienThuocDaTT += (thuocTT.getThuocphongkhamDongia() == null ? 0 : thuocTT.getThuocphongkhamDongia()) * thuocTT.getThuocphongkhamSoluong();
						tienThuocDaTT += thuocTT.getThuocphongkhamThanhtien();
					}
				}
				
				
				//phuc.lc 10-01-2011 : Luu thong tin hstthk vao bang hs_thtoank_backup de phuc vu cho viec huy phieu doi voi BN thu phi
				HsThtoankBackup hsBK = new HsThtoankBackup();
				// Copy thong tin tu hsttk vao HsThtoankBackup
				hsBK.setHsthtoankBhxh(hsttk.getHsthtoankBhxh());
				hsBK.setHsthtoankBhyt(hsttk.getHsthtoankBhyt());
				hsBK.setHsthtoankBienlai(hsttk.getHsthtoankBienlai());
				hsBK.setHsthtoankBntra(hsttk.getHsthtoankBntra());
				hsBK.setHsthtoankBovien(hsttk.getHsthtoankBovien());
				hsBK.setHsthtoankCdha(hsttk.getHsthtoankCdha());
				hsBK.setHsthtoankCls(hsttk.getHsthtoankCls());
				hsBK.setHsthtoankClsndm(hsttk.getHsthtoankClsndm());
				hsBK.setHsthtoankCongkham(hsttk.getHsthtoankCongkham());
				hsBK.setHsthtoankCpVc(hsttk.getHsthtoankCpvc());
				hsBK.setHsthtoankCum(hsttk.getHsthtoankCum());
				hsBK.setHsthtoankDatt(hsttk.getHsthtoankDatt());
				//hsBK.setHsthtoankDichtruyen(hsttk.getHsthtoankDichtruyen());
				hsBK.setHsthtoankDienmien(hsttk.getHsthtoankDienmien());
				hsBK.setHsthtoankDm(hsttk.getHsthtoankDm());
				hsBK.setHsthtoankDvKtc(hsttk.getHsthtoankDvktc());
				hsBK.setHsthtoankDvktTt(hsttk.getHsthtoankDvkttt());
				hsBK.setHsthtoankHoanthu(hsttk.getHsthtoankHoanthu());
				hsBK.setHsthtoankHoanung(hsttk.getHsthtoankHoanung());
				hsBK.setHsthtoankKhongthu(hsttk.getHsthtoankKhongthu());
				hsBK.setHsthtoankKyhieu(hsttk.getHsthtoankKyhieu());
				hsBK.setHsthtoankLydott(hsttk.getHsthtoankLydott());
				//hsBK.setHsthtoankMa(hsttk.getHsthtoankMa());
				hsBK.setHsthtoankMabenh(hsttk.getHsthtoankMabenh());
				hsBK.setHsthtoankMau(hsttk.getHsthtoankMau());
				hsBK.setHsthtoankMiendt(hsttk.getHsthtoankMiendt());
				hsBK.setHsthtoankMienmau(hsttk.getHsthtoankMienmau());
				hsBK.setHsthtoankMienphong(hsttk.getHsthtoankMienphong());
				hsBK.setHsthtoankMiente(hsttk.getHsthtoankMiente());
				hsBK.setHsthtoankMienthuoc(hsttk.getHsthtoankMienthuoc());
				hsBK.setHsthtoankMienthuoclao(hsttk.getHsthtoankMienthuoclao());
				hsBK.setHsthtoankNdm(hsttk.getHsthtoankNdm());
				hsBK.setHsthtoankNgansach(hsttk.getHsthtoankNgansach());
				hsBK.setHsthtoankNgayc(hsttk.getHsthtoankNgayc());
				hsBK.setHsthtoankNgayd(hsttk.getHsthtoankNgayd());
				hsBK.setHsthtoankNgaygiocn(hsttk.getHsthtoankNgaygiocn());
				hsBK.setHsthtoankNgaygiott(hsttk.getHsthtoankNgaygiott());
				hsBK.setHsthtoankNhanviencn(hsttk.getHsthtoankNhanviencn());
				hsBK.setHsthtoankPhauthuat(hsttk.getHsthtoankPhauthuat());
				hsBK.setHsthtoankPhauthuatndm(hsttk.getHsthtoankPhauthuatndm());
				hsBK.setHsthtoankPhong(hsttk.getHsthtoankPhong());
				hsBK.setHsthtoankPhongndm(hsttk.getHsthtoankPhongndm());
				hsBK.setHsthtoankQuyen(hsttk.getHsthtoankQuyen());
				hsBK.setHsthtoankSokhoa(hsttk.getHsthtoankSokhoa());
				hsBK.setHsthtoankTamung(hsttk.getHsthtoankTamung());
				hsBK.setHsthtoankThatthu(hsttk.getHsthtoankThatthu());
				hsBK.setHsthtoankThtoan(hsttk.getHsthtoankThtoan());
				hsBK.setHsthtoankThungan(hsttk.getHsthtoankThungan());
				hsBK.setHsthtoankThuoc(hsttk.getHsthtoankThuoc());
				hsBK.setHsthtoankThuocndm(hsttk.getHsthtoankThuocndm());
				hsBK.setHsthtoankTongchi(hsttk.getHsthtoankTongchi());
				hsBK.setHsthtoankTylebh(hsttk.getHsthtoankTylebh());
				hsBK.setHsthtoankVtth(hsttk.getHsthtoankVtth());
				hsBK.setHsthtoankVtthndm(hsttk.getHsthtoankVtthndm());
				hsBK.setHsthtoankXetgiam(hsttk.getHsthtoankXetgiam());
				hsBK.setHsthtoankXntdcn(hsttk.getHsthtoankXntdcn());
				hsBK.setTiepdonMa(hsttk.getTiepdonMa());
				log.info("hsBK.getTiepdonMa() = " + hsBK.getTiepdonMa());
				//phuc.lc 10-01-2011 : end
				// phuc.lc 28-12-2010 end
				
				// phuc.lc 28-12-2010 begin
				Calendar dTemp = Utils.getDBDate(gioThanhToan, ngayTt);
				Date dNgayGioTT = Calendar.getInstance().getTime();					
				if (dTemp != null){
					dNgayGioTT = dTemp.getTime();						
				}
				hsBK.setHsthtoankNgaygiott(dNgayGioTT);				
				hsBK.setHsthtoankThungan(nhanVienThungan.getDtdmnhanvienMaso() == null ? null : nhanVienThungan);
				
				
				
				
				HsThtoank hsttkTmp = hsttkDelegate.findBytiepdonMaLast(thamKham.getTiepdonMa(true).getTiepdonMa());
				if (hsttkTmp != null) {	
					hsttk = hsttkTmp;
					hsttk.setHsthtoankThungan(nhanVienThungan.getDtdmnhanvienMaso() == null ? null : nhanVienThungan);
					hsttk.setHsthtoankThtoan(hsttk.getHsthtoankThtoan() - tienThuocDaTT); //bao.ttc: set lai HSTHTOAN_thtoan sau khi tru tien thuoc Da TT 
					Utils.setInforForHsThToan(hsttk);															
					hsttk.setHsthtoankNgaygiott(dNgayGioTT);					
					hsttkDelegate.edit(hsttk);					
				} else {
					hsttk.setHsthtoankThungan(nhanVienThungan.getDtdmnhanvienMaso() == null ? null : nhanVienThungan);
					hsttk.setHsthtoankThtoan(hsttk.getHsthtoankThtoan() - tienThuocDaTT); //bao.ttc: set lai HSTHTOAN_thtoan sau khi tru tien thuoc Da TT 
					Utils.setInforForHsThToan(hsttk);															
					hsttk.setHsthtoankNgaygiott(dNgayGioTT);					
					hsttkDelegate.create(hsttk);
					
				}
				
				//HsThtoank hsttkTemp = hsttkDelegate.findAllBytiepdonMa(thamKham.getTiepdonMa(true).getTiepdonMa());
				//this.maPhieuTT = hsttkTemp.getHsthtoankMa();
				
				// phuc.lc 07/01/2011 : Thuc hien luu thong tin backup va cap nhat ma phieu thanh toan vao truong maphieud cua thuoc phong kham
				//hsBK.setHsthtoankMa(hsttkTemp.getHsthtoankMa());
				//
				log.info("before create (hsttk BK), ma = " + hsBK.getHsthtoankMa());
				HsThtoankBackupDelegate.getInstance().create(hsBK);
				log.info("After create (hsttk BK), ma = " + hsBK.getHsthtoankMa());
				HsThtoankBackup hsBKTemp = HsThtoankBackupDelegate.getInstance().findBytiepdonMa(hsBK.getTiepdonMa().getTiepdonMa(), 1);
				log.info("After findBytiepdonMa, ma = " + hsBKTemp.getHsthtoankMa());
				this.maPhieuTT = hsBKTemp.getHsthtoankMa();
				ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
				List<ThuocPhongKham> listTpk = tpkDelegate.findByMaTiepDon(thamKham.getTiepdonMa(true).getTiepdonMa(), "1"); // thuoc ban kham
				if(listTpk != null && listTpk.size() > 0) {
					for(ThuocPhongKham eachTpk : listTpk) {
						if (eachTpk.getThuocphongkhamMaphieud() == null) {
							//eachTpk.setThuocphongkhamMaphieud(hsttkTemp.getHsthtoankMa());
							eachTpk.setThuocphongkhamMaphieud(hsBK.getHsthtoankMa());
							tpkDelegate.edit(eachTpk);
						}
					}
				}
				////phuc.lc 10-01-2011 : end
				// phuc.lc 07/01/2011 : Begin Chuyen doan code o tren xuong day				
				ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();			
				for (int i = 0; i < listCtkq.size(); i++) {
					removeIfNullForClsKham(listCtkq.get(i));
				}
				
				ArrayList<ClsKham> arrayListHasMaPhieu = new ArrayList<ClsKham>();
				for (ClsKham cls : listCtkq) {
					
					if (cls.getClskhamCum() == null && ( cls.getClskhamMaphieu() == null
						 || cls.getClskhamMaphieu().equals("") ) && cls.getClskhamDatt() != null &&
						 cls.getClskhamDatt().booleanValue() ==  true){
						cls.setClskhamCum(cum);
						cls.setClskhamThungan(nhanVienThungan.getDtdmnhanvienMaso() == null ? null : nhanVienThungan);
						
						cls.setClskhamKyHieu(kyhieu);
						cls.setClskhamQuyen(quyen);
						cls.setClskhamBienLai(bienlai);
						cls.setClskhamNoidungthu(sNoiDungThu);
						log.info("*****sNoiDungThu: "+sNoiDungThu);
						
						/*SimpleDateFormat formatter;	    
				        formatter = new SimpleDateFormat(Utils.FORMAT_DATE); 
				       
				       
			           Date d =	formatter.parse(ngayTt);
			           Calendar dCalendar = Calendar.getInstance();
			           dCalendar.setTime(d);
				         */ 
			           cls.setClskhamNgaygiott(dNgayGioTT);
						
					}
					if (cls.getClskhamMaphieu() != null
							&& !cls.getClskhamMaphieu().equals("")) {
						arrayListHasMaPhieu.add(cls);
					}
				}
								
				 //Khong lay ma phieu trong clskham ma lay trong hsthtoank
				// maBanKham khong su dung nua, khong can kiem tra
				maPhieu = clsKhamDelegate.createClsKham_v2(listCtkq, thamKham
						.getTiepdonMa().getTiepdonMa(), this.maPhieuTT);
				log.info("maPhieu = " + maPhieu);
				/*if (maBanKham == null || maBanKham.equals("")) {
					maPhieu = clsKhamDelegate.createClsKham(listCtkq, thamKham
							.getTiepdonMa().getTiepdonMa());

				} else {
					maPhieu = clsKhamDelegate.createClsKham(listCtkq, thamKham
							.getTiepdonMa().getTiepdonMa(), maBanKham);

				}*/

				//this.maPhieu = maPhieu;
				// phuc.lc 07/01/2011 : END chuyen code
			}
			
			FacesMessages.instance().add(IConstantsRes.SUCCESS);
			resultHidden = "success";
		} catch (Exception e) {
			FacesMessages.instance().add(IConstantsRes.FAIL);
			resultHidden = "fail";
			log.error("*************loi***********" + e.toString());
			e.printStackTrace();
		}
		try {

			for (int i = 0; i < listCtkq.size(); i++) {
				setInforIfNullForClsKham(listCtkq.get(i));

				SetInforUtil.setInforIfNullForThamKham(listCtkq.get(i)
						.getClskhamThamkham());
				SetInforUtil.setInforIfNullForTiepDon(listCtkq.get(i)
						.getClskhamThamkham().getTiepdonMa());

			}
			// ResetForm();

		} catch (Exception e) {
			log.error("Loi reset form : " + e.toString());
			e.printStackTrace();
		}
		
		//bao.ttc: load lai data
		fromGhiNhan = true;
		displayInfor();
		log.info("*****End Ghi nhan() *****");
		this.reportFinished = "";
	}

	public void nhaplai() throws Exception {
		ResetForm();
	}

	// Ham khi nhan nut sua lai
	public void sualai() throws Exception {
		try {
			ResetForm();
			resultHidden = "";
			this.reportFinished = "";
		} catch (Exception e) {
			log.error("Loi reset form : " + e.toString());
		}
	}

	// Ham khi nhan nut In phieu
	public String inphieu() throws Exception {
		log.info("Begining inphieu(), displayByMaPhieu = " + displayByMaPhieu);

		if ((maPhieu == null || maPhieu.equals("")) && tienThuocTT == 0.0) {  // trung.th:  neu ko co CLS tien thuoc chua thanh toan thi ko in Phieu 
			return "";
		}

		try {
			XuatReport2();
			//XuatReport();
		} catch (Exception e) {
			log.info("Loi trong khi xuat report" + e.toString());
		}
		if(nhanVienThungan==null){
			refreshnhanvienthungan();
		}
		log.info("End inphieu()");
		return "B3360_Chonmenuxuattaptin";
	}
	
	int index = 0;
	public void XuatReport2() {
		reportTypeVP="B3232_Phieuthanhtoanclsphongkham";
		log.info("Vao Method XuatReport B3232_Phieuthanhtoanclsphongkham, displayByMaPhieu = " + displayByMaPhieu);
		String baocao1=null;
		String pathTemplate=null;
		log.info("In XuatReport2(), ctClsPhongKhams.size() = " + ctClsPhongKhams.size() + "thamKham = " + thamKham);
		ClsKhamDelegate clsDel = ClsKhamDelegate.getInstance();
		List<ClsKham> listClsKham = clsDel.findByMaPhieu(maPhieu);
		
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
		Double miengiam2 = 0.0;
		// Lay hsttk backup
		HsThtoankBackupDelegate hsttkBKDelegate = HsThtoankBackupDelegate.getInstance();
		//HsThtoankBackup hsttkBK = hsttkBKDelegate.find(maPhieu);
		HsThtoankBackup hsttkBK = hsttkBKDelegate.findByMaPhieu(maPhieu);
		if (hsttkBK != null) {
			miengiam2 = hsttkBK.getHsthtoankXetgiam() == null ? 0.0 : hsttkBK.getHsthtoankXetgiam(); 
		}
		for (ClsKham eachCls : listClsKham) {			
			int lan = eachCls.getClskhamLan().intValue();
			int tra = eachCls.getClskhamTra() == null ? 0 : eachCls.getClskhamTra().intValue();
			// Kiem tra cls co phai la dich vu hay khong
			if (eachCls.getClskhamYeucau() != null && eachCls.getClskhamYeucau().booleanValue() == true) {				
				hasDV = true;				
				curGia = eachCls.getClskhamMahang().getDtdmclsbgDongia() * (displayByMaPhieu ? lan : (lan - tra));				
				curGiaDV = (eachCls.getClskhamMahang().getDtdmclsbgDongiayc() - eachCls.getClskhamMahang().getDtdmclsbgDongia()) * (displayByMaPhieu ? lan : (lan - tra));
				tongtienDV += curGiaDV;
			} else  {
				curGia = eachCls.getClskhamMahang().getDtdmclsbgDongia() * (displayByMaPhieu ? lan : (lan - tra));
				curGiaDV = 0.0;
			}
			tongtien += curGia;
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
		log.info("tongtien = " + tongtien + ", tongtienDV = " + tongtienDV);
		// lay thuoc ban` khoam
		ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
		Double thuocDV = 0.0;   	// Tong tien thuoc 
		Double vtthDV = 0.0;		// Tong tien Vat tu tieu hao
		Double thuocBN = 0.0;   	// Thuoc benh nhan tra
		Double vtthBN = 0.0;		// Vat tu tieu hao benh nhan tra
		//List<ThuocPhongKham> listTpk_Bankham = tpkDelegate.findByMaTiepDon(thamKham.getTiepdonMa().getTiepdonMa(), "1"); // thuoc ban kham
		List<ThuocPhongKham> listTpk_Bankham = tpkDelegate.findByMaPhieu(maPhieu);
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
		/*
		// ke toa quay benh vien
		List<ThuocPhongKham>  listTpk_BHYT = tpkDelegate.findByMaTiepDon(thamKham.getTiepdonMa().getTiepdonMa(), "3"); // ke toa BHYT
		for(ThuocPhongKham eachTpk : listTpk_BHYT) {
						
			curGia = (eachTpk.getThuocphongkhamTienbntra() == null ? 0.0 : eachTpk.getThuocphongkhamTienbntra());
			
			// Kiem tra Thuoc co phai la dich vu hay khong
			if (eachTpk.getThuocphongkhamYeucau() != null && eachTpk.getThuocphongkhamYeucau().booleanValue() == true) {				
				hasDV = true;
			}

			if (eachTpk.getThuocphongkhamMathuoc()!=null && eachTpk.getThuocphongkhamMathuoc().getDmphanloaithuocMaso()!=null &&
					eachTpk.getThuocphongkhamMathuoc().getDmphanloaithuocMaso().getDmloaithuocMaso() !=null &&
					eachTpk.getThuocphongkhamMathuoc().getDmphanloaithuocMaso().getDmloaithuocMaso().getDmloaithuocMa().equals("YC")){
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
		*/
		// Copy code
		String diachistr = "";
		BenhNhan benhnhan = thamKham.getTiepdonMa().getBenhnhanMa();
		if(benhnhan.getBenhnhanDiachi() != null)
			diachistr += benhnhan.getBenhnhanDiachi()+", ";
		if(benhnhan.getXaMa(true).getDmxaTen() !=null)
			diachistr += benhnhan.getXaMa(true).getDmxaTen()+", ";
		if(benhnhan.getHuyenMa(true).getDmhuyenTen() != null)
			diachistr += benhnhan.getHuyenMa(true).getDmhuyenTen()+", ";
		if(benhnhan.getTinhMa(true).getDmtinhTen() != null)
			diachistr += benhnhan.getTinhMa(true).getDmtinhTen();
		
		String thungan = "";
		if(hsttkBK.getHsthtoankThungan() != null){
			if (hsttkBK.getHsthtoankThungan().getDtdmnhanvienTen() != null)
				thungan = hsttkBK.getHsthtoankThungan().getDtdmnhanvienTen();
			else thungan = "";
		} else thungan = "";
		log.info("hasDV = " + hasDV);
		// phuc.lc 22-04-2011 : Fix bug 2655
		int lanIn = hsttkBK.getHsthtoankLanin() == null ? 1 : (Integer.valueOf(hsttkBK.getHsthtoankLanin()).intValue() + 1);
		hsttkBK.setHsthtoankLanin("" + lanIn);
		if (hasDV && tongtienDV > 0) {
			// Neu co su dung dich vu thi in 3 lien bien lai thu tien va 3 lien hoa don
			try{
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_hoadon.jrxml";
				String pathTemplate_sub1 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_mau1_sub1.jrxml";
				String pathTemplate_sub2 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_mau1_sub2.jrxml";
				String pathTemplate_sub3 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_mau1_sub3.jrxml";
				String pathTemplate_sub4 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_hoadon_subreport0.jrxml";
				String pathTemplate_sub5 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_hoadon_subreport1.jrxml";
				String pathTemplate_sub6 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_hoadon_subreport2.jrxml";
				log.info("da thay file templete bienlaithulephi_hoadon " + pathTemplate);
				
				JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
				JasperReport jasperSub1 =JasperCompileManager.compileReport(pathTemplate_sub1);
				JasperReport jasperSub2 =JasperCompileManager.compileReport(pathTemplate_sub2);
				JasperReport jasperSub3 =JasperCompileManager.compileReport(pathTemplate_sub3);
				
				JasperReport jasperSub4 =JasperCompileManager.compileReport(pathTemplate_sub4);
				JasperReport jasperSub5 =JasperCompileManager.compileReport(pathTemplate_sub5);
				JasperReport jasperSub6 =JasperCompileManager.compileReport(pathTemplate_sub6);
				log.info("da thay file template ");
				
				Map<String, Object> params = new HashMap<String, Object>();
				
				params.put("sub1", jasperSub1 );
				params.put("sub2", jasperSub2 );	
				params.put("sub3", jasperSub3 );
				params.put("sub4", jasperSub4 );
				params.put("sub5", jasperSub5 );
				params.put("sub6", jasperSub6 );
				// Cac param chung
				params.put("DIADIEM", IConstantsRes.REPORT_DIEUTRI_TINH);
				params.put("BVBD", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
				params.put("DIACHI_BV", IConstantsRes.REPORT_DIEUTRI_DIA_CHI);
				params.put("MASOTHUE", IConstantsRes.REPORT_DIEUTRI_MA_SO_THUE);
				params.put("HOTEN", benhnhan.getBenhnhanHoten());				
				params.put("DIACHI", diachistr);
				params.put("THUNGAN", thungan);
				
				// Cac param cho mau bien lai thu vien phi
				params.put("NOIDUNG", sNoiDungThu );				
				params.put("SOTIEN", Utils.formatNumberWithSeprator(tongtien - miengiam2));
				params.put("TIENBANGCHU", Utils.NumberToString(tongtien - miengiam2));				
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
			    log.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    } catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintVP =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","B3232_Phieuthanhtoanclsphongkham");
			    reportPathVP=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathVP);
			    index+= 1;
			    log.info("Index :" + index);
			    if(conn != null) conn.close();
			    
			 // phuc.lc 22-04-2011 : Fix bug 2655
			    hsttkBKDelegate.edit(hsttkBK);
	            
			} catch(Exception e) {
			    log.info("ERROR Method XuatReport!!!");
				e.printStackTrace();
			}
		} else {
			// Neu khong su dung dich vu thi chi in 3 lien bien lai thu tien
			try{
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_mau1.jrxml";
				String pathTemplate_sub1 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_mau1_sub1.jrxml";
				String pathTemplate_sub2 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_mau1_sub2.jrxml";
				String pathTemplate_sub3 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_mau1_sub3.jrxml";
				log.info("da thay file templete bienlaithulephi_mau1" + pathTemplate);
				
				JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
				JasperReport jasperSub1 =JasperCompileManager.compileReport(pathTemplate_sub1);
				JasperReport jasperSub2 =JasperCompileManager.compileReport(pathTemplate_sub2);
				JasperReport jasperSub3 =JasperCompileManager.compileReport(pathTemplate_sub3);
				log.info("da thay file template ");
				
				Map<String, Object> params = new HashMap<String, Object>();
				
				params.put("sub1", jasperSub1 );
				params.put("sub2", jasperSub2 );
				params.put("sub3", jasperSub3 );
				
				
				params.put("DIADIEM", IConstantsRes.REPORT_DIEUTRI_TINH);
				params.put("BVBD", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
				params.put("DIACHI_BV", IConstantsRes.REPORT_DIEUTRI_DIA_CHI);
				params.put("MASOTHUE", IConstantsRes.REPORT_DIEUTRI_MA_SO_THUE);
				params.put("HOTEN", benhnhan.getBenhnhanHoten());
				
				params.put("DIACHI", diachistr);
				params.put("NOIDUNG", sNoiDungThu );
														
				params.put("SOTIEN", Utils.formatNumberWithSeprator(tongtien - miengiam2));
				params.put("TIENBANGCHU", Utils.NumberToString(tongtien - miengiam2));
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
				
				Class.forName("oracle.jdbc.OracleDriver");
			    log.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    } catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintVP =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","B3232_Phieuthanhtoanclsphongkham");
			    reportPathVP=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathVP);
			    index+= 1;
			    log.info("Index :" + index);
			    if(conn != null) conn.close();
			    
			 // phuc.lc 21-04-2011 : Fix bug 2655
			    hsttkBKDelegate.edit(hsttkBK);
	            
			} catch(Exception e) {
			    log.info("ERROR Method XuatReport!!!");
				e.printStackTrace();
			}
		}
	}
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeVP="B3232_Phieuthanhtoanclsphongkham";
		log.info("Vao Method XuatReport B3232_Phieuthanhtoanclsphongkham");
		String baocao1=null;
		String pathTemplate=null;
		try {
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithutien.jrxml";
			String sub1Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithutien_sub1.jrxml";
			String sub2Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithutien_sub2.jrxml";
			log.info("da thay file templete " + pathTemplate);
			
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			JasperReport sub1Report =JasperCompileManager.compileReport(sub1Template);
			JasperReport sub2Report =JasperCompileManager.compileReport(sub2Template);
			log.info("****Finish creating jasperReport*****");
			
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put("sub1", sub1Report);
			params.put("sub2", sub2Report);
			
			//params.put("UBNDTINH", IConstantsRes.REPORT_DIEUTRI_UBNDTINH);
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			params.put("BVBD", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("DIACHI_BV", IConstantsRes.REPORT_DIEUTRI_DIA_CHI);
			params.put("MASOTHUE", IConstantsRes.REPORT_DIEUTRI_MA_SO_THUE);
			params.put("HOTEN", benhNhan.getBenhnhanHoten());
			
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
			
			if(sNoiDungThu!=null)
				params.put("NOIDUNG", sNoiDungThu );
			
//			HsThtoank hsttk = HsThtoankDelegate.getInstance().findAllBytiepdonMa(thamKham.getTiepdonMa().getTiepdonMa());
			System.out.println("CanLamSanPhongKhamAction.XuatReport()*******************************matd=" + thamKham.getTiepdonMa().getTiepdonMa());
			HsThtoank hsttk = HsThtoankDelegate.getInstance().findBytiepdonMaLast(thamKham.getTiepdonMa().getTiepdonMa());
			
			String thungan = "";
			if (hsttk.getHsthtoankThungan() != null){
				if (hsttk.getHsthtoankThungan().getDtdmnhanvienTen() != null)
					thungan = hsttk.getHsthtoankThungan().getDtdmnhanvienTen();
				else thungan = "";
			} else thungan = "";
			
			params.put("THUNGAN", thungan);
			System.out.println("CanLamSanPhongKhamAction.XuatReport()********************************thungan= " + thungan);
			
			///////////get tien tuy theo phieu/////
			ClsKhamDelegate clsKhamDe = ClsKhamDelegate.getInstance();
			Double tienBNTra = clsKhamDe.getSoTienTuPhieu(thamKham.getTiepdonMa().getTiepdonMa(), maPhieu);
			tienBNTra += tienThuocTT; //trung.th Tien cls + tien thuoc, In phieu
			params.put("SOTIEN", Utils.formatNumberWithSeprator(tienBNTra));
			params.put("TIENBANGCHU", Utils.NumberToString(tienBNTra));
			
			// Chi tiet thu ==========================================================================

			Double thuoc = 0.0;
			if (hsttk.getHsthtoankThuoc() == null){
				thuoc = new Double(0);
			} else{
				thuoc = hsttk.getHsthtoankThuoc();
			}
			Double thuocndm = 0.0;
			if (hsttk.getHsthtoankThuocndm() == null){
				thuocndm = new Double(0);
			} else{
				thuocndm = hsttk.getHsthtoankThuocndm();
			}
			Double tienthuoc = thuoc + thuocndm;
			params.put("THUOC", Utils.formatNumberWithSeprator(tienthuoc));
			log.info("================= " + tienthuoc);
			
			Double mau = 0.0;
			if (hsttk.getHsthtoankMau() == null){
				mau = new Double(0);
			} else{
				mau = hsttk.getHsthtoankMau();
			}
			params.put("MAU", Utils.formatNumberWithSeprator(mau));
			log.info("================= " + mau);
			
			Double xntdcn = 0.0;
			if (hsttk.getHsthtoankXntdcn() == null){
				xntdcn = new Double(0);
			} else{
				xntdcn = hsttk.getHsthtoankXntdcn();
			}
			params.put("XNTDCN", Utils.formatNumberWithSeprator(xntdcn));
			log.info("================= " + xntdcn);
			
			Double cdha = 0.0;
			if (hsttk.getHsthtoankCdha() == null){
				cdha = new Double(0);
			} else{
				cdha = hsttk.getHsthtoankCdha();
			}
			params.put("CDHA", Utils.formatNumberWithSeprator(cdha));
			log.info("================= " + cdha);
			
			Double pt = 0.0;
			if (hsttk.getHsthtoankPhauthuat() == null){
				pt = new Double(0);
			} else{
				pt = hsttk.getHsthtoankPhauthuat();
			}
			Double ptndm = 0.0;
			if (hsttk.getHsthtoankPhauthuatndm() == null){
				ptndm = new Double(0);
			} else{
				ptndm = hsttk.getHsthtoankPhauthuatndm();
			}
			Double tienpt = pt + ptndm;
			params.put("PTTT", Utils.formatNumberWithSeprator(tienpt));
			log.info("================= " + tienpt);
			
			Double dvktc = 0.0;
			if (hsttk.getHsthtoankDvktc() == null){
				dvktc = new Double(0);
			} else{
				dvktc = hsttk.getHsthtoankDvktc();
			}
			params.put("DVKTC", Utils.formatNumberWithSeprator(dvktc));
			log.info("================= " + dvktc);
			
			Double vtth = 0.0;
			if (hsttk.getHsthtoankVtth() == null){
				vtth = new Double(0);
			} else{
				vtth = hsttk.getHsthtoankVtth();
			}
			Double vtthndm = 0.0;
			if (hsttk.getHsthtoankVtthndm() == null){
				vtthndm = new Double(0);
			} else{
				vtthndm = hsttk.getHsthtoankVtthndm();
			}
			Double tienvtth = vtth + vtthndm;
			params.put("VTTH", Utils.formatNumberWithSeprator(tienvtth));
			log.info("================= " + tienvtth);
			
			Double dvp = 0.0;
			if (hsttk.getHsthtoankPhong() == null){
				dvp = new Double(0);
			} else{
				dvp = hsttk.getHsthtoankPhong();
			}
			Double dvpndm = 0.0;
			if (hsttk.getHsthtoankPhongndm() == null){
				dvpndm = new Double(0);
			} else{
				dvpndm = hsttk.getHsthtoankPhongndm();
			}
			Double tiendvp = dvp + dvpndm;
			params.put("DVP", Utils.formatNumberWithSeprator(tiendvp));
			log.info("================= " + tiendvp);
			
			Double cv = 0.0;
			if (hsttk.getHsthtoankCpvc() == null){
				cv = new Double(0);
			} else{
				cv = hsttk.getHsthtoankCpvc();
			}
			params.put("CV", Utils.formatNumberWithSeprator(cv));
			log.info("================= " + cv);
			
			Double cls = 0.0;
			if (hsttk.getHsthtoankCls() == null){
				cls = new Double(0);
			} else{
				cls = hsttk.getHsthtoankCls();
			}
			Double clsndm = 0.0;
			if (hsttk.getHsthtoankClsndm() == null){
				clsndm = new Double(0);
			} else{
				clsndm = hsttk.getHsthtoankClsndm();
			}
			Double tiencls = cls + clsndm;
			params.put("CLS", Utils.formatNumberWithSeprator(tiencls));
			log.info("================= " + tiencls);
			
			// Chi tiet thu ==========================================================================
			
			
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","B3232_Phieuthanhtoanclsphongkham");
			    reportPathVP=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathVP);
			    index+= 1;
			    log.info("Index :" + index);
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Thoat Method XuatReport");
	}

	private void setOtherValue() {
		log.info("Begining setOtherValue()");
		SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
		if (thamKham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh() != null) {
			ngaySinh = formatter.format(thamKham.getTiepdonMa().getBenhnhanMa()
					.getBenhnhanNgaysinh().getTime());
		}
		if (clskham.getClskhamNgaygiott() != null
				&& !clskham.getClskhamNgaygiott().equals("")) {
			ngayTt = formatter.format(clskham.getClskhamNgaygiott().getTime());
		}
		if (benhNhan.getDmgtMaso() != null) {
			if ("1".equals(benhNhan.getDmgtMaso().getDmgtMa())) {
				gioi = "r1"; // 1 : Name
			} else {
				gioi = "r2";
			}
		} else {
			gioi = null;
		}
		log.info("End setOtherValue()");
	}


	public void subDisplayInfor() {
		log.info("Begin subDisplayInfor(), tiepDonMa = " + thamKham.getTiepdonMa());
		String maTD = thamKham.getTiepdonMa().getTiepdonMa();
		log.info("maTD = " + maTD);
		ChuyenVaoNoiTru cvnt = ChuyenVaoNoiTruDelegate.getInstance().findByMaTiepDon(maTD);
		if (cvnt != null) {									
			hasNoMaTiepDon();
			FacesMessages.instance().add(IConstantsRes.BN_DA_CHUYEN_VAO_NOI_TRU);
			listCtkq = new ArrayList<ClsKham>();
			ResetForm();
			return;
		} else if (thamKham.getTiepdonMa() == null
				|| thamKham.getTiepdonMa().getTiepdonMa().equals("")) {
			hasNoMaTiepDon();
			FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + maTD);
			listCtkq = new ArrayList<ClsKham>();
			ResetForm();
			return;
		}
		// ClsKhamWSService clskhamService = new ClsKhamWSServiceLocator();
		// ClsKhamWS clskhamWS = clskhamService.getClsKhamWSPort();
		
		ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
		ThamKham tk = tkDelegate.findByMaTiepDon(thamKham.getTiepdonMa()
				.getTiepdonMa());		
		if (tk != null) {
			thamKham = tk;
			SetInforUtil.setInforIfNullForThamKham(thamKham);
			SetInforUtil.setInforIfNullForTiepDon(thamKham.getTiepdonMa());
			benhNhan = thamKham.getTiepdonMa().getBenhnhanMa();
			SetInforUtil.setInforIfNullForBN(benhNhan);

		} else {
			hasNoMaTiepDon();
			FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + maTD);
			listCtkq = new ArrayList<ClsKham>();
			ResetForm();
			return;
		}
	}

	public void SetInforAllCLS(List<ClsKham> clskham_tmp) {
		if (clskham_tmp == null || clskham_tmp.size() == 0) {
			log.info("displayInfor   tham kham 22 ....." + clskham);
			listCtkq = new ArrayList<ClsKham>();
			log.info("displayInfor   tham kham 23 ....." + clskham);
		} else {

			listCtkq = clskham_tmp;

			// ArrayList<ClsKham> listCtkqDaThanhToan = new
			// ArrayList<ClsKham>();
			List<ClsKham> listClsKhamTmp = new ArrayList<ClsKham>();
			int lan = 0;
			int tra = 0;
			for (int i = 0; i < listCtkq.size(); i++) {
				//ClsKham cls = listCtkq.get(i);
				// if (maPhieu == null || maPhieu.equals("")){
				// if (cls.getClskhamDatt()){
				// listCtkqDaThanhToan.add(cls);
				// continue;
				// }
				// }				
				setInforIfNullForClsKham(listCtkq.get(i));				
				listCtkq.get(i).setClskhamThamkham(thamKham);
				// Can lam ro lai nghiep vu cua bug nay
				/*
				// phuc.lc : 13-01-2011 : Fix bug 2026
				lan = (listCtkq.get(i).getClskhamLan() == null ? 0 : listCtkq.get(i).getClskhamLan().intValue());
				tra = (listCtkq.get(i).getClskhamTra() == null ? 0 : listCtkq.get(i).getClskhamTra().intValue());
				if (lan - tra > 0) {
					try {
						ClsKham clsTmp = new ClsKham();
						clsTmp = (ClsKham) BeanUtils.cloneBean(listCtkq.get(i));
						listCtkq.get(i).setClskhamLan(new Short("" + (lan-tra)));
						listCtkq.get(i).setClskhamTra(null);
						clsTmp.setClskhamLan(clsTmp.getClskhamTra());
						clsTmp.setClskhamTra(null);
						clsTmp.setClskhamDatt(false);
						listClsKhamTmp.add(clsTmp);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				*/
			}
			/*
			log.info("Before, listCtkq.size() = " + listCtkq.size() + ", listClsKhamTmp.size() = " + listClsKhamTmp.size());
			if (listClsKhamTmp.size() > 0) {
				listCtkq.addAll(listClsKhamTmp);
			}
			log.info("After, listCtkq.size() = " + listCtkq.size());
			*/
			// for (ClsKham cls : listCtkqDaThanhToan){
			// listCtkq.remove(cls);
			// }
		}
	}
	boolean displayByMaPhieu = false;
	public void displayInforMaPhieu() throws Exception {
		try{
				
			if (maPhieuTT == null || maPhieuTT.equals("")) {
				return;
			}
	
			//bao.ttc: find ma phieu TT trong hsttk
			//HsThtoankDelegate hsttkDele1 = HsThtoankDelegate.getInstance();
			//HsThtoank hsttk_temp = hsttkDele1.findByMaPhieu(maPhieuTT);
			
			// phuc.lc : 10-02-2011 : find ma phieu TT trong hsttk backup
			HsThtoankBackupDelegate hsBKDel = HsThtoankBackupDelegate.getInstance();
			//HsThtoankBackup hsttk_temp = hsBKDel.find(maPhieuTT);
			HsThtoankBackup hsttk_temp = hsBKDel.findByMaPhieu(maPhieuTT);
			if (hsttk_temp == null || hsttk_temp.equals("")) {
				FacesMessages.instance().add(IConstantsRes.KHONG_TIM_THAY_PHIEU, maPhieuTT);
				ResetForm();
				return;
			}
			
			ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
			thamKham = tkDelegate.findByMaTiepDon(hsttk_temp.getTiepdonMa().getTiepdonMa());
			if (!thamKham.getTiepdonMa().getDoituongMa().getDmdoituongMa().equals("TP")) {
				FacesMessages.instance().add(IConstantsRes.KHONG_PHAI_THUPHI);
				ResetForm();
			} else {
				disbledHuyPhieu = "false";
				displayByMaPhieu = true;
				maPhieuTT = hsttk_temp.getHsthtoankMa();
				maPhieu = maPhieuTT;
				kyhieu = hsttk_temp.getHsthtoankKyhieu();
				quyen = hsttk_temp.getHsthtoankQuyen();
				bienlai = hsttk_temp.getHsthtoankBienlai();
				sNoiDungThu = hsttk_temp.getHsthtoankLydott();
				nhanVienThungan = hsttk_temp.getHsthtoankThungan();
				if (nhanVienThungan == null) {
					//nhanVienThungan = new DtDmNhanVien();
					refreshnhanvienthungan();
				}
				//phuc.lc 28-12-2010 begin
				SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
				if (hsttk_temp != null && hsttk_temp.getHsthtoankNgaygiott() != null){				
					Date ngayGioTT = hsttk_temp.getHsthtoankNgaygiott();
					gioThanhToan = Utils.getGioPhut(ngayGioTT);					
					ngayTt = formatter.format(ngayGioTT.getTime());
				}			
				//phuc.lc 28-12-2010 end
				Double thatthu_temp = hsttk_temp.getHsthtoankThatthu();
				log.info("In displayInforMaPhieu(), that thu = " + thatthu + ", thatthu_temp = " + thatthu_temp);
				//displayInfor();
				SetInforUtil.setInforIfNullForThamKham(thamKham);
				SetInforUtil.setInforIfNullForTiepDon(thamKham.getTiepdonMa());
				benhNhan = thamKham.getTiepdonMa().getBenhnhanMa();
				SetInforUtil.setInforIfNullForBN(benhNhan);
				if (benhNhan.getBenhnhanNgaysinh() != null) {
					ngaySinh = formatter.format(benhNhan.getBenhnhanNgaysinh().getTime());
				}
				thatthu = thatthu_temp;
				ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();
				List<ClsKham> clskham_tmp = clsKhamDelegate.findByMaPhieu(maPhieuTT);				
				ThuocPhongKhamDelegate thuocPKDele = ThuocPhongKhamDelegate.getInstance();
				ctClsPhongKhams = thuocPKDele.findByMaPhieu(maPhieuTT); 				
				SetInforAllCLS(clskham_tmp);
				//Caculation(listCtkq);
				permiengiam = 0;
				 miengiam = hsttk_temp.getHsthtoankXetgiam();
				 thatthu = hsttk_temp.getHsthtoankThatthu();
				 perbhytchi = hsttk_temp.getHsthtoankTylebh();
				 //conlai = hsttk.getHsthtoankThtoan() - tienThuocDaTT;	 
				 conlai = new Double(0);
				 cls = new Double(0);
				 for (ClsKham eachCls : clskham_tmp) {
					 //cls += eachCls.getClskhamDongia() * (eachCls.getClskhamLan() - (eachCls.getClskhamTra()== null ? 0 : eachCls.getClskhamTra()));
					 cls += eachCls.getClskhamDongia() * eachCls.getClskhamLan();
					 if (eachCls.getClskhamTra() != null && eachCls.getClskhamTra() > 0) {
						 // Neu trong phieu thanh toan co mot cls nao da hoan thu thi khong cho huy phieu
						 disbledHuyPhieu = "true";
					 }
				 }
				 thuoc = new Double(0);
				 for(ThuocPhongKham eachTpk : ctClsPhongKhams) {
					 //thuoc += ((eachTpk.getThuocphongkhamDongia() == null ? 0 : eachTpk.getThuocphongkhamDongia()) * eachTpk.getThuocphongkhamSoluong());
					 thuoc += eachTpk.getThuocphongkhamThanhtien();
				 }
				 bntra = thanhtien1 = (cls + thuoc);
				 bntrakn = bntra;
				 if (benhNhan.getDmgtMaso() != null) {
					if ("1".equals(benhNhan.getDmgtMaso().getDmgtMa())) {
						gioi = "r1"; // 1 : Name
					} else {
						gioi = "r2";
					}
				} else {
					gioi = null;
				}
				resultHidden = "success";
			}
		}
		catch (Exception e) {
			log.info(" ERROR : " + e);
			e.printStackTrace();
		}
		if(nhanVienThungan==null){
			refreshnhanvienthungan();
		}
		log.info("####################  End displayInforMaPhieu() ######################");

	}	


	boolean fromGhiNhan;
	// Hien thi thong tin CLS Kham cua benh nhan sau khi nhap Ban kham va ma
	// tiep don
	public void displayInfor() throws Exception {
		try {
			log.info("Begining displayInfor(), thamkham = " + thamKham + "tiepDonMa = " + thamKham.getTiepdonMa());

			subDisplayInfor();
			log.info("After subDisplayInfor(), thamkham = " + thamKham + "tiepDonMa = " + thamKham.getTiepdonMa());
			if (thamKham == null || thamKham.getTiepdonMa() == null || thamKham.getTiepdonMa().getTiepdonMa() == null) return;
			ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();
			
			ThuocPhongKhamDelegate thuocPKDele = ThuocPhongKhamDelegate.getInstance();

			List<ClsKham> clskham_tmp;

			if (maBanKham == null || maBanKham.equals("")) {
				log.info("no ban kham");
				clskham_tmp = clsKhamDelegate.findByTiepdonma(thamKham
						.getTiepdonMa().getTiepdonMa());
				ctClsPhongKhams = thuocPKDele.findByMaTiepDon(thamKham.getTiepdonMa().getTiepdonMa(), Utils.XU_TRI_THUOC_TAI_BAN_KHAM);
			} else {
				log.info("having ban kham");
				clskham_tmp = clsKhamDelegate.findByBanKhamVaMaTiepDon(
						maBanKham, thamKham.getTiepdonMa().getTiepdonMa());
				ctClsPhongKhams = thuocPKDele.findByMaTiepDonAndMaBanKham(thamKham.getTiepdonMa().getTiepdonMa(),maBanKham,Utils.XU_TRI_THUOC_TAI_BAN_KHAM);
			}
			
			SetInforAllCLS(clskham_tmp);
			Caculation(listCtkq);
			setOtherValue();
			// Tinh so tien bn tra ky nay, chi tinh tien thuoc chua thanh toan, khong tinh cls, 
			//tien cls se tinh khi chon cls de thanh toan tren giao dien
			bntrakn = new Double(0);
			for (ClsKham eachCls : listCtkq) {
				if(eachCls.getClskhamDatt() == null || !eachCls.getClskhamDatt()) {
					bntrakn += eachCls.getClskhamDongia() * (eachCls.getClskhamLan() - (eachCls.getClskhamTra() == null ? 0 :eachCls.getClskhamTra()));
				}
			}
			for(ThuocPhongKham eachTpk : ctClsPhongKhams) {
				if(eachTpk.getThuocphongkhamDatt() == null || !eachTpk.getThuocphongkhamDatt()) {
					//bntrakn += (eachTpk.getThuocphongkhamDongia()==null ? 0 : eachTpk.getThuocphongkhamDongia()) * eachTpk.getThuocphongkhamSoluong();
					bntrakn += eachTpk.getThuocphongkhamThanhtien();
				}
				//bntrakn += eachTpk.getThuocphongkhamDongia() * (eachTpk.getThuocphongkhamSoluong() - (eachTpk.getThuocphongkhamTra() == null ? 0 : eachTpk.getThuocphongkhamTra())); 
			}
			if (!thamKham.getTiepdonMa().getDoituongMa().getDmdoituongMa().equals("TP")) {
				FacesMessages.instance().add(IConstantsRes.KHONG_PHAI_THUPHI);
				ResetForm();
			} else if ("CCL".equals( thamKham.getTiepdonMa(true).getTiepdonBankham(true).getDtdmbankhamMa())
					|| "CCN".equals( thamKham.getTiepdonMa(true).getTiepdonBankham(true).getDtdmbankhamMa())) {
				FacesMessages.instance().add(IConstantsRes.DOI_TUONG_CAP_CUU + ", " + IConstantsRes.KHONG_THANH_TOAN_O_DAY);
				ResetForm();
			}else {
				// Kiem tra xem co BA noi tru hay khong
				Hsba hsba = HsbaDelegate.getInstance().findByTiepDonMa(thamKham.getTiepdonMa().getTiepdonMa());				
				//if (hsba != null) {
				//	FacesMessages.instance().add(IConstantsRes.BENH_NHAN_CO_HSBA_NOI_TRU, hsba.getHsbaSovaovien());
				//}
				if (hsba != null) {
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
			if(fromGhiNhan) {
				fromGhiNhan = false;
			} else {
				resultHidden = "";
				maPhieu = "";
				maPhieuTT = "";
			}
			List<ThamKham> listTk = ThamKhamDelegate.getInstance().findAllByMaTiepDon(thamKham.getTiepdonMa().getTiepdonMa());
			strBankham = "";
			for (ThamKham eachTk : listTk) {
				strBankham += (strBankham.trim().length() > 0 ? (eachTk.getThamkhamBankham() == null ? "" : ", " + eachTk.getThamkhamBankham().getDtdmbankhamMa()) : (eachTk.getThamkhamBankham() == null ? "" : eachTk.getThamkhamBankham().getDtdmbankhamMa()));
			}
			displayByMaPhieu = false;
		} catch (Exception e) {
			e.printStackTrace();
			log.info((new StringBuilder()).append(
					"error on function displayInfor").append(e).toString());
		}
		log.info("End displayInfor()");
	}

	private void removeIfNullForClsKham(ClsKham cls) {
		log.info("**********Begin setAllNullForClsKham()***********");
		if ("".equals(Utils.reFactorString(cls.getClskhamMahang()
				.getDtdmclsbgMa()))) {
			cls.setClskhamMahang(null);
		}
		if ("".equals(Utils.reFactorString(cls.getClskhamKhoa().getDmkhoaMa()))) {
			cls.setClskhamKhoa(null);
		}
		// if
		// ("".equals(Utils.reFactorString(cls.getClskhamBankham().getDtdmbankhamMa()))){
		// cls.setClskhamBankham(null);
		// log.info("Ban Kham null");
		// }
		log
				.info("----------cls.getClskhamChedott():"
						+ cls.getClskhamChedott());
		if (cls.getClskhamChedott() != null && "".equals(Utils.reFactorString(cls.getClskhamChedott()
				.getDmdoituongMa()))) {
			cls.setClskhamChedott(null);
			log.info("Che do tt null");
		}
		if (cls.getClskhamKhoa2() != null && ""
				.equals(Utils.reFactorString(cls.getClskhamKhoa2()
						.getDmkhoaMa()))) {
			cls.setClskhamKhoa2(null);
			log.info("Khoa 2 null");
		}
		if (cls.getClskhamMabs() != null && "".equals(Utils.reFactorString(cls.getClskhamMabs()
				.getDtdmnhanvienMa()))) {
			cls.setClskhamMabs(null);
			log.info("Bac si null");
		}
		if (cls.getClskhamMaloai() != null && "".equals(Utils.reFactorString(cls.getClskhamMaloai()
				.getDtdmclsMa()))) {
			cls.setClskhamMaloai(null);
			log.info("Ma loai null");
		}
		if (cls.getClskhamNhanviencn() != null && "".equals(Utils.reFactorString(cls.getClskhamNhanviencn()
				.getDtdmnhanvienMa()))) {
			cls.setClskhamNhanviencn(null);
			log.info("Nhan vien cn null");
		}
		if (cls.getClskhamThuchien() != null && "".equals(Utils.reFactorString(cls.getClskhamThuchien()
				.getDtdmnhanvienMa()))) {
			cls.setClskhamThuchien(null);
			log.info("Thuc hien  null");
		}
		if (cls.getClskhamThungan() != null && "".equals(Utils.reFactorString(cls.getClskhamThungan()
				.getDtdmnhanvienMa()))) {
			cls.setClskhamThungan(null);
			log.info("Thu ngan null");
		}
		log.info("**********End setAllNullForClsKham()***********");
	}

	private void setInforIfNullForClsKham(ClsKham cls) {
		log.info("Begining setInforIfNullForClsKham (): " + cls);
		if (cls.getClskhamMahang() == null) {
			cls.setClskhamMahang(new DtDmClsBangGia());
		}
		if (cls.getClskhamKhoa() == null) {
			cls.setClskhamKhoa(new DmKhoa());
		}
		// if (cls.getClskhamBankham() == null) {
		// cls.setClskhamBankham(new DtDmBanKham());
		// }
		if (cls.getClskhamChedott() == null) {
			cls.setClskhamChedott(new DmDoiTuong());
		}

		log
				.info("----------cls.getClskhamChedott():"
						+ cls.getClskhamChedott());
		if (cls.getClskhamKhoa2() == null) {
			cls.setClskhamKhoa2(new DmKhoa());
		}
		if (cls.getClskhamMabs() == null) {
			cls.setClskhamMabs(new DtDmNhanVien());
		}
		if (cls.getClskhamMaloai() == null) {
			cls.setClskhamMaloai(new DtDmCls());
		}
		if (cls.getClskhamNhanviencn() == null) {
			cls.setClskhamNhanviencn(new DtDmNhanVien());
		}
		if (cls.getClskhamThuchien() == null) {
			cls.setClskhamThuchien(new DtDmNhanVien());
		}
		if (cls.getClskhamThungan() == null) {
			cls.setClskhamThungan(new DtDmNhanVien());
		}
		log.info("End setInforIfNullForClsKham (): ");
	}
	

	// Ham reset form
	private void ResetForm() {
		log.info("Begining ResetForm(): ");
		
		benhNhan = new BenhNhan();
		SetInforUtil.setInforIfNullForBN(benhNhan);
		thamKham = new ThamKham();
		thamKham.setTiepdonMa(new TiepDon());
		SetInforUtil.setInforIfNullForThamKham(thamKham);
		SetInforUtil.setInforIfNullForTiepDon(thamKham.getTiepdonMa());

		clskham = new ClsKham();
		clskham.setClskhamThamkham(thamKham);
		setInforIfNullForClsKham(clskham);
		gioi = "";
		tuoi = "";
		ngaySinh = "";
		//ngayTt = "";
		//phuc.lc 28-12-2010 begin
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
		ngayTt = formatter.format(cal.getTime());		
		gioThanhToan = Utils.getGioPhut(cal.getTime()) ;
		//phuc.lc 28-12-2010 end
		// diengiai = "";
		loai = "";
		maCLS = "";
		maSoCLS = null;
		tenCLS = "";
		maKhoa = "";
		mien = new Boolean(false);
		ngoaiDanhMuc = new Boolean(false);
		yeuCau = new Boolean(false);
		kyThuatCao = new Boolean(false);
		soLuong = 0;
		donGia = new Double(0);
		permiengiam = 0;
		miengiam = new Double(0);
		thatthu = new Double(0);
		perbhytchi = 0;
		conlai = new Double(0);
		thanhtien1 = new Double(0);
		perbntra = 0;
		bntra = new Double(0);
		bntrakn = new Double(0);
		listCtkq = new ArrayList<ClsKham>();
		ctClsPhongKhams = new ArrayList<ThuocPhongKham>();
		resultHidden = "";
		maPhieu = "";

		maBanKham = "";
		
		sNoiDungThu=IConstantsRes.NOI_DUNG_THU_CLS;
		kyhieu="";
		quyen="";
		bienlai="";
		maPhieuTT = "";
		
		cls=null;
		thuoc=null;
		strBankham = "";	
		refreshnhanvienthungan();
		log.info("End ResetForm(): ");
	}
	public void huyphieu() {
		log.info("Begin huyphieu, maPhieuTT = " + maPhieuTT);
		if (maPhieuTT == null || maPhieuTT.equals("")) {
			return;
		}
		// Tim HsThtoankBackup theo ma phieu muon huy
		HsThtoankBackupDelegate hsBKDel = HsThtoankBackupDelegate.getInstance();
		//HsThtoankBackup hsttk_temp = hsBKDel.find(maPhieuTT);
		HsThtoankBackup hsttk_temp = hsBKDel.findByMaPhieu(maPhieuTT);		
		if (hsttk_temp == null || hsttk_temp.equals("")) {
			FacesMessages.instance().add(IConstantsRes.KHONG_TIM_THAY_PHIEU, maPhieuTT);			
			return;
		}
		// Cap nhat trang thai CLS theo ma phieu huy
		ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();
		List<ClsKham> clskham_tmp = clsKhamDelegate.findByMaPhieu(maPhieuTT);
		for(ClsKham eachCls : clskham_tmp) {
			eachCls.setClskhamMaphieu(null);
			eachCls.setClskhamDatt(false);
			eachCls.setClskhamNgaygiott(null);
			eachCls.setClskhamTra(null);
			eachCls.setClskhamMaphieud(null); // Set null cho ma phieu hoan thu CLS
			clsKhamDelegate.edit(eachCls);			
		}		
		// Cap nhat trang thai thuoc phong kham theo ma phieu huy
		ThuocPhongKhamDelegate thuocPKDele = ThuocPhongKhamDelegate.getInstance();		
		List<ThuocPhongKham> listTpk_tmp = thuocPKDele.findByMaPhieu(maPhieuTT);
		for(ThuocPhongKham eachTpk : listTpk_tmp) {
			eachTpk.setThuocphongkhamMaphieud(null);
			eachTpk.setThuocphongkhamDatt(false);
			eachTpk.setThuocphongkhamNgaygiott(null);			
			thuocPKDele.edit(eachTpk);
		}
		// Xoa HsThtoankBackup
		hsBKDel.remove(hsttk_temp);
		// Cap nhat HsThtoank
		HsThtoankDelegate hsThtoankDel = HsThtoankDelegate.getInstance();
		HsThtoank hsThtoank = hsThtoankDel.find(maPhieuTT);
		if (hsThtoank != null) {
			hsThtoank.setHsthtoankNgaygiott(null);
			hsThtoank.setHsthtoankDatt(false);
			hsThtoankDel.edit(hsThtoank);
		}
		FacesMessages.instance().add(IConstantsRes.HUY_PHIEU_THANH_CONG, maPhieuTT);
		ResetForm();
		bntrakn = new Double(0);
		resultHidden = "";
		
	}
	// Xuat report
	
	//phuc.lc 18-07-2011 : Begin fix bug 3725 : Them chuc nang In phieu thanh toan
	@Out(required=false)
	@In(required=false)
	private String reportPathKC=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeKC=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintKC = null;
	
	public String inPhieuThanhToan() {
		return XuatPhieuThanhToan();
		//return "B4160_Chonmenuxuattaptin";
	}
	public String XuatPhieuThanhToan() {
		log.info("begin XuatPhieuThanhToan() tiepdon = " + thamKham.getTiepdonMa());
		if (thamKham.getTiepdonMa() == null) return null;
		reportTypeKC="PhieuThanhToanKCB_Thuphi";
		
		String baocao1=null;
		try {
			log.info("Vao Method XuatPhieuThanhToan() kham chua benh ngoai tru");
			
			SimpleDateFormat sdf=new SimpleDateFormat(Utils.FORMAT_DATE);			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			
			//String matiepDon = thamkham.getTiepdonMa().getTiepdonMa();
			params.put("MATIEPDON", thamKham.getTiepdonMa().getTiepdonMa() );
			params.put("HOTENBN", benhNhan.getBenhnhanHoten()  );
			
			String diachistr="";
			if(benhNhan.getBenhnhanDiachi() != null)
				diachistr += benhNhan.getBenhnhanDiachi()+", ";
			if(benhNhan.getXaMa(true).getDmxaTen() !=null)
				diachistr += benhNhan.getXaMa(true).getDmxaTen()+", ";
			if(benhNhan.getHuyenMa(true).getDmhuyenTen() != null)
				diachistr += benhNhan.getHuyenMa(true).getDmhuyenTen()+", ";
			if(benhNhan.getTinhMa(true).getDmtinhTen() != null)
				diachistr += benhNhan.getTinhMa(true).getDmtinhTen();
			params.put("DIACHI", diachistr );
			
			if(thamKham.getTiepdonMa().getDoituongMa(true).getDmdoituongMa().equals("BH")){
				params.put("BHYT_CO", "X" );
				
				if( (thamKham.getTiepdonMa().getTiepdonTuyen() != null && thamKham.getTiepdonMa().getTiepdonTuyen().toString().equals("1"))
						|| (thamKham.getTiepdonMa().getTiepdonCoGiayGioiThieu() != null && thamKham.getTiepdonMa().getTiepdonCoGiayGioiThieu()) ){
					params.put("DUNGTUYEN","X");
				} else {
					params.put("TRAITUYEN","X");
				}
				
			} else {
				params.put("BHYT_KO", "X" );
			}
			
			if(thamKham.getTiepdonMa().getTiepdonGiatri1() != null){
				params.put("GTTU", sdf.format(thamKham.getTiepdonMa().getTiepdonGiatri1()));
			}else{
				params.put("GTTU", "");
			}
				
			if(thamKham.getTiepdonMa().getTiepdonGiatri2() != null){
				params.put("GTDEN", sdf.format(thamKham.getTiepdonMa().getTiepdonGiatri2()));
			}else{
				params.put("GTDEN", "");
			}
			if (thamKham.getTiepdonMa().getTiepdonSothebh() != null && !thamKham.getTiepdonMa().getTiepdonSothebh().equals("") &&
					thamKham.getTiepdonMa().getKhoibhytMa(true).getDtdmkhoibhytMa() != null && !thamKham.getTiepdonMa().getKhoibhytMa(true).getDtdmkhoibhytMa().equals("") &&
					thamKham.getTiepdonMa().getKcbbhytMa(true).getDmbenhvienMa()!=null
			){
				
				params.put("MATHEBHYT", thamKham.getTiepdonMa().getTiepdonSothebh());
				params.put("MABENHVIEN", thamKham.getTiepdonMa().getKcbbhytMa(true).getDmbenhvienMa().replace(".", "-"));
				
				
			}else{
				params.put("MABENHVIEN","");
				params.put("MATHEBHYT","");
			}
			
			if(thamKham.getTiepdonMa().getKcbbhytMa(true).getDmbenhvienTen()!=null)
				params.put("NOIDKKCBBANDAU", thamKham.getTiepdonMa().getKcbbhytMa(true).getDmbenhvienTen());
			if(thamKham.getTiepdonMa().getTinhbhytMa(true).getDmtinhTen()!=null)
				params.put("NOICAPTHE", thamKham.getTiepdonMa().getTinhbhytMa(true).getDmtinhTen());
			
			if(thamKham.getTiepdonMa().getTiepdonDonvigoi(true).getDmbenhvienMa()!=null)
				params.put("NOIGIOITHIEU", thamKham.getTiepdonMa().getTiepdonDonvigoi(true).getDmbenhvienTen());
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(thamKham.getThamkhamNgaygio());
			params.put("NGAYKHAMBENH", new Timestamp(cal.getTimeInMillis()));
			
			params.put("LYDOVAOVIEN", thamKham.getTiepdonMa().getTiepdonLydovaov());
			
			HsThtoankDelegate thanhToanDel = HsThtoankDelegate.getInstance();
			HsThtoank hsttk = new HsThtoank();
			hsttk = (HsThtoank)thanhToanDel.findBytiepdonMaLast(thamKham.getTiepdonMa().getTiepdonMa()); //20101109 bao.ttc: tim HSTTK cuoi cung 
			if (hsttk == null || (hsttk.getHsthtoankDatt() != null && !hsttk.getHsthtoankDatt())) {
				hsttk = new HsThtoank();
				hsttk.setTiepdonMa(thamKham.getTiepdonMa());			
				
				ThamKhamUtil tkUtil = new ThamKhamUtil();
				tkUtil.tinhToanChoHSTKKham(thamKham, hsttk, false, null, null);//tinhToanChoHSTKKham(hsttk);
				
			}
			log.info("Ty le bao hiem : " + hsttk.getHsthtoankTylebh());
			
			params.put("BHXHTHANHTOAN", hsttk.getHsthtoankBhyt() );
			params.put("NGUOIBENHTRA", hsttk.getHsthtoankBntra());
			
			// 20110701 bao.ttc: them cac thong tin cho mau moi dung tu ngay 1/7
			if(hsttk.getHsthtoankNgaygiott() != null){
				params.put("NGAYTHANHTOAN", hsttk.getHsthtoankNgaygiott());
				if(thamKham.getThamkhamNgaygio() != null){
					params.put("SONGAYDT", Utils.getDaysBetween(thamKham.getThamkhamNgaygio(), hsttk.getHsthtoankNgaygiott()));
				}
			}
			
			if(thamKham.getTiepdonMa().getTiepdonBankham() != null){
				if(thamKham.getTiepdonMa().getTiepdonBankham(true).getDtdmbankhamMa().startsWith("CC")){
					params.put("CAPCUU","X");
					params.put("DUNGTUYEN","");
					params.put("TRAITUYEN","");
				}
			}
			
			params.put("PHIEUSO", hsttk.getHsthtoankMa());
			log.info("Ty le bao hiem : " + hsttk.getHsthtoankTylebh());
			params.put("TYLEBH",hsttk.getHsthtoankTylebh());
			String tyleBNtra = ""+ (100 - hsttk.getHsthtoankTylebh() );
			
			if ("MP".equals( thamKham.getTiepdonMa().getDoituongMa(true).getDmdoituongMa())){
				tyleBNtra = "0";
			}
			params.put("PHANTRAMBNTRA",tyleBNtra);
			
			params.put("BIENLAISO", ""  );
			
			String namsinh = "";
			if (thamKham.getTiepdonMa().getBenhnhanMa(true).getBenhnhanNgaysinh() != null){
				namsinh = sdf.format(thamKham.getTiepdonMa().getBenhnhanMa(true).getBenhnhanNgaysinh());
			} else {
				namsinh = thamKham.getTiepdonMa().getBenhnhanMa(true).getBenhnhanNamsinh();
			}
			params.put("namsinh", namsinh);
						
			DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
			String maChanDoan = "";
			String tenChanDoan ="";					
			
			if (thamKham.getBenhicd10() != null && thamKham.getBenhicd10().getDmbenhicdMaso() !=null){
				DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamKham.getBenhicd10().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					params.put("MABENHICD", maChanDoan);
					tenChanDoan = benh.getDmbenhicdTen();
				}
				
			}
			
			String chanDoan = maChanDoan + " " +  tenChanDoan;
			
			// bao.ttc: them thamkham.ghichu
			if (thamKham.getThamkhamGhichu() != null && !thamKham.getThamkhamGhichu().equals("")) {
				chanDoan += " , " + thamKham.getThamkhamGhichu();
			}
			
			//tiep tuc them benh phu.
			if (thamKham.getBenhicd10phu1() != null && thamKham.getBenhicd10phu1().getDmbenhicdMaso() !=null){
				DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamKham.getBenhicd10phu1().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					chanDoan += " , "+ maChanDoan + " " +  tenChanDoan;
				}
				
			}
			if (thamKham.getBenhicd10phu2() != null && thamKham.getBenhicd10phu2().getDmbenhicdMaso() !=null){
				DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamKham.getBenhicd10phu2().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					if (chanDoan.equals("")) {
						chanDoan += maChanDoan + " " +  tenChanDoan;
					} else {
						chanDoan += " , "+ maChanDoan + " " +  tenChanDoan;
					}
				}
				
			}
			if (thamKham.getBenhicd10phu3() != null && thamKham.getBenhicd10phu3().getDmbenhicdMaso() !=null){
				DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamKham.getBenhicd10phu3().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					if (chanDoan.equals("")) {
						chanDoan += maChanDoan + " " +  tenChanDoan;
					} else {
						chanDoan += " , "+ maChanDoan + " " +  tenChanDoan;
					}
				}
				
			}
			if (thamKham.getBenhicd10phu4() != null && thamKham.getBenhicd10phu4().getDmbenhicdMaso() !=null){
				DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamKham.getBenhicd10phu4().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					if (chanDoan.equals("")) {
						chanDoan += maChanDoan + " " +  tenChanDoan;
					} else {
						chanDoan += " , "+ maChanDoan + " " +  tenChanDoan;
					}
				}
				
			}
			if (thamKham.getBenhicd10phu5() != null && thamKham.getBenhicd10phu5().getDmbenhicdMaso() !=null){
				DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamKham.getBenhicd10phu5().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					if (chanDoan.equals("")) {
						chanDoan +=maChanDoan + " " +  tenChanDoan;
					} else {
						chanDoan += " , "+ maChanDoan + " " +  tenChanDoan;
					}
				}
				
			}
			params.put("CHANDOAN", chanDoan );
			
			params.put("PHONGKHAM", thamKham.getThamkhamBankham().getDtdmbankhamTen() );
			//Lay danh sach khoa da kham
			List<ThamKham> listTk = ThamKhamDelegate.getInstance().findAllByMaTiepDon(thamKham.getTiepdonMa().getTiepdonMa());
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
					listClsTmp = ClsKhamDelegate.getInstance().findByTiepdonma(thamKham.getTiepdonMa().getTiepdonMa());
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
					
					listTpk = ThuocPhongKhamDelegate.getInstance().findByMaTiepDon(thamKham.getTiepdonMa().getTiepdonMa(), "1");
					if (listTpk != null && listTpk.size() > 0) {
						for (ThuocPhongKham eachTpk : listTpk) {
							if ((eachTpk.getThuocphongkhamYeucau() != null && eachTpk.getThuocphongkhamYeucau().booleanValue() == true)
									|| (eachTpk.getThuocphongkhamNdm() != null && eachTpk.getThuocphongkhamNdm().booleanValue() == true))	{
								//tongTienDV += eachTpk.getThuocphongkhamDongia(); 
								tongTienDV += eachTpk.getThuocphongkhamThanhtien();
							}
						}
					}
					listTpk = ThuocPhongKhamDelegate.getInstance().findByMaTiepDon(thamKham.getTiepdonMa().getTiepdonMa(), "3");
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
			String soTheTe_KhaiSinh_ChungSinh = thamKham.getTiepdonMa(true).getTiepdonSothete();
			
			
			//them vao khai sinh
			if (soTheTe_KhaiSinh_ChungSinh == null || soTheTe_KhaiSinh_ChungSinh.equals("")){
				if (thamKham.getTiepdonMa().getTiepdonKhaisinh() == null ||
						thamKham.getTiepdonMa().getTiepdonKhaisinh().equals("")){
					
				}else{
					soTheTe_KhaiSinh_ChungSinh = thamKham.getTiepdonMa().getTiepdonKhaisinh();
				}
				
			}else{
				if (thamKham.getTiepdonMa().getTiepdonKhaisinh() == null ||
						thamKham.getTiepdonMa().getTiepdonKhaisinh().equals("")){
					
				}else{
					soTheTe_KhaiSinh_ChungSinh += "/" + thamKham.getTiepdonMa().getTiepdonKhaisinh();
				}				
			}
			// them vao chung sinh
			
			if (soTheTe_KhaiSinh_ChungSinh == null || soTheTe_KhaiSinh_ChungSinh.equals("")){
				if (thamKham.getTiepdonMa().getTiepdonChungsinh() == null ||
						thamKham.getTiepdonMa().getTiepdonChungsinh().equals("")){
					
				}else{
					soTheTe_KhaiSinh_ChungSinh = thamKham.getTiepdonMa().getTiepdonChungsinh();
				}
				
			}else{
				if (thamKham.getTiepdonMa().getTiepdonChungsinh() == null ||
						thamKham.getTiepdonMa().getTiepdonChungsinh().equals("")){
					
				}else{
					soTheTe_KhaiSinh_ChungSinh += "/" + thamKham.getTiepdonMa().getTiepdonChungsinh();
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
			String soTheNgheo = thamKham.getTiepdonMa().getTiepdonThengheo();
			
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
			
			DmGioiTinh gioi = (DmGioiTinh)DieuTriUtilDelegate.getInstance().findByMaSo(thamKham.getTiepdonMa().getBenhnhanMa(true).getDmgtMaso(true).getDmgtMaso(), "DmGioiTinh","dmgtMaso" );
			if (gioi != null){

				params.put("GIOI", gioi.getDmgtTen());
						
			}
			params.put("DONVITRIENKHAI", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			log.info("tongTienDV = " + tongTienDV);
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintKC,getIndex(),IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","PhieuThanhToanKCB");
			    reportPathKC=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathKC);
			    setIndex(getIndex() + 1);
			    log.info("Index :" + getIndex());
			    if(conn != null) {
			    	conn.close();
			    	conn = null;
			    }
		}catch (Exception e) {
		    log.info("ERROR Method XuatPhieuThanhToan!!!");
			e.printStackTrace();
			return null;
		}
		log.info("Thoat Method XuatPhieuThanhToan");
		if(nhanVienThungan==null){
			refreshnhanvienthungan();
		}
		return "B4160_Chonmenuxuattaptin";
	    
	}
	//phuc.lc 18-07-2011 : End fix bug 3725
	private String reportFinished = "";


	public static String getFORMAT_DATE_TIME() {
		return FORMAT_DATE_TIME;
	}

	public static void setFORMAT_DATE_TIME(String format_date_time) {
		FORMAT_DATE_TIME = format_date_time;
	}

	public BenhNhan getBenhNhan() {
		return benhNhan;
	}

	public void setBenhNhan(BenhNhan benhNhan) {
		this.benhNhan = benhNhan;
	}

	public String getTuoi() {
		return tuoi;
	}

	public void setTuoi(String tuoi) {
		this.tuoi = tuoi;
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

	public java.util.List<ClsKham> getListCtkq() {
		return listCtkq;
	}

	public void setListCtkq(java.util.List<ClsKham> listCtkq) {
		this.listCtkq = listCtkq;
	}

	public ClsKham getNhapctSelected() {
		return nhapctSelected;
	}

	public void setNhapctSelected(ClsKham nhapctSelected) {
		this.nhapctSelected = nhapctSelected;
	}

	public boolean isUpdateNhapct() {
		return updateNhapct;
	}

	public void setUpdateNhapct(boolean updateNhapct) {
		this.updateNhapct = updateNhapct;
	}

	// public TiepDon getTiepdon() {
	// return tiepdon;
	// }
	//
	// public void setTiepdon(TiepDon tiepdon) {
	// this.tiepdon = tiepdon;
	// }

	public ThamKham getThamKham() {
		return thamKham;
	}

	public void setThamKham(ThamKham thamKham) {
		this.thamKham = thamKham;
	}

	public ClsKham getClskham() {
		return clskham;
	}

	public void setClskham(ClsKham clskham) {
		this.clskham = clskham;
	}

	public String getGioi() {
		return gioi;
	}

	public void setGioi(String gioi) {
		this.gioi = gioi;
	}

	public String getLoai() {
		return loai;
	}

	public void setLoai(String loai) {
		this.loai = loai;
	}

	public String getMaCLS() {
		return maCLS;
	}

	public void setMaCLS(String maCLS) {
		this.maCLS = maCLS;
	}

	public Integer getMaSoCLS() {
		return maSoCLS;
	}

	public void setMaSoCLS(Integer maSoCLS) {
		this.maSoCLS = maSoCLS;
	}

	public short getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(short soLuong) {
		this.soLuong = soLuong;
	}

	public Double getDonGia() {
		return donGia;
	}

	public void setDonGia(Double donGia) {
		this.donGia = donGia;
	}

	public Boolean getYeuCau() {
		return yeuCau;
	}

	public void setYeuCau(Boolean yeuCau) {
		this.yeuCau = yeuCau;
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

	

	public Double getConlai() {
		return conlai;
	}

	public void setConlai(Double conlai) {
		this.conlai = conlai;
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

	public String getNgayTt() {
		return ngayTt;
	}

	public void setNgayTt(String ngayTt) {
		this.ngayTt = ngayTt;
	}

	// public String getDiengiai() {
	// return diengiai;
	// }
	// public void setDiengiai(String diengiai) {
	// this.diengiai = diengiai;
	// }
	public String getMaKhoa() {
		return maKhoa;
	}

	public void setMaKhoa(String maKhoa) {
		this.maKhoa = maKhoa;
	}

	public Boolean getMien() {
		return mien;
	}

	public void setMien(Boolean mien) {
		this.mien = mien;
	}

	public Boolean getNgoaiDanhMuc() {
		return ngoaiDanhMuc;
	}

	public void setNgoaiDanhMuc(Boolean ngoaiDanhMuc) {
		this.ngoaiDanhMuc = ngoaiDanhMuc;
	}

	public Boolean getKyThuatCao() {
		return kyThuatCao;
	}

	public void setKyThuatCao(Boolean kyThuatCao) {
		this.kyThuatCao = kyThuatCao;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getReportFinished() {
		return reportFinished;
	}

	public void setReportFinished(String reportFinished) {
		this.reportFinished = reportFinished;
	}

	public String getResultHidden() {
		return resultHidden;
	}

	public void setResultHidden(String resultHidden) {
		this.resultHidden = resultHidden;
	}

	public DtDmNhanVien getNhanVienThungan() {
		return nhanVienThungan;
	}

	public void setNhanVienThungan(DtDmNhanVien nhanVienThungan) {
		this.nhanVienThungan = nhanVienThungan;
	}

	public String getTenCLS() {
		return tenCLS;
	}

	public void setTenCLS(String tenCLS) {
		this.tenCLS = tenCLS;
	}

	public String getResultReportName() {
		return resultReportName;
	}

	public void setResultReportName(String resultReportName) {
		this.resultReportName = resultReportName;
	}

	public String getReportFileNameHid() {
		return reportFileNameHid;
	}

	public void setReportFileNameHid(String reportFileNameHid) {
		this.reportFileNameHid = reportFileNameHid;
	}

	public String getResultReportFileName() {
		return resultReportFileName;
	}

	public void setResultReportFileName(String resultReportFileName) {
		this.resultReportFileName = resultReportFileName;
	}

	public String getIsReport() {
		return isReport;
	}

	public void setIsReport(String isReport) {
		this.isReport = isReport;
	}

	public String getLoaiFileXuat() {
		return loaiFileXuat;
	}

	public void setLoaiFileXuat(String loaiFileXuat) {
		this.loaiFileXuat = loaiFileXuat;
	}

	public String getMaPhieu() {
		return maPhieu;
	}

	public void setMaPhieu(String maPhieu) {
		this.maPhieu = maPhieu;
	}

	public String getMaBanKham() {
		return maBanKham;
	}

	public void setMaBanKham(String maBanKham) {
		this.maBanKham = maBanKham;
	}

	public Boolean getDichVu() {
		return dichVu;
	}

	public void setDichVu(Boolean dichVu) {
		this.dichVu = dichVu;
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

	public String getNoiDungThu() {
		return sNoiDungThu;
	}

	public void setNoiDungThu(String noiDungThu) {
		sNoiDungThu = noiDungThu;
	}

	public String getMahsttk() {
		return mahsttk;
	}

	public void setMahsttk(String mahsttk) {
		this.mahsttk = mahsttk;
	}

	public String getMaPhieuTT() {
		return maPhieuTT;
	}

	public void setMaPhieuTT(String maPhieuTT) {
		this.maPhieuTT = maPhieuTT;
	}

	public Double getTienThuocTT() {
		return tienThuocTT;
	}

	public void setTienThuocTT(Double tienThuocTT) {
		this.tienThuocTT = tienThuocTT;
	}

	public String getGioThanhToan() {
		return gioThanhToan;
	}

	public void setGioThanhToan(String gioThanhToan) {
		this.gioThanhToan = gioThanhToan;
	}

	public Double getBntrakn() {
		return bntrakn;
	}

	public void setBntrakn(Double bntrakn) {
		this.bntrakn = bntrakn;
	}

	public String getStrBankham() {
		return strBankham;
	}

	public void setStrBankham(String strBankham) {
		this.strBankham = strBankham;
	}
	
	public String getDisbledHuyPhieu() {
		return disbledHuyPhieu;
	}

	public void setDisbledHuyPhieu(String disbledHuyPhieu) {
		this.disbledHuyPhieu = disbledHuyPhieu;
	}
	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

}

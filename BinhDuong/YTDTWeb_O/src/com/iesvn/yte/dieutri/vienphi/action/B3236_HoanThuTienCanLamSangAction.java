package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

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

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Begin;
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
import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.PcCumThuPhiDelegate;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.DtDmCls;
import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
import com.iesvn.yte.dieutri.entity.DtDmCum;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.HsThtoank;
import com.iesvn.yte.dieutri.entity.PcCumThuPhi;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.util.HoSoThanhToanKhamUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B3236_Hoanthutiencanlamsang")
@Synchronized(timeout = 6000000)
public class B3236_HoanThuTienCanLamSangAction implements Serializable {
	
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
	private Boolean yeuCau = new Boolean(false);
	private Boolean kyThuatCao = new Boolean(false);
	private short soLuong = 0;
	private Double donGia = new Double(0);
	private Boolean dichVu  = new Boolean(false);
	// Phan danh cho tinh toan
	private int permiengiam = 0;
	private Double miengiam = new Double(0);
	private Double thatthu = new Double(0);
	private int perbhytchi = 0;
	private Double bhytchi = new Double(0);
	private Double thanhtien1 = new Double(0);
	private int perbntra = 0;
	private Double bntra = new Double(0);

	private String resultHidden = "";
	private DtDmNhanVien nhanVienThungan = new DtDmNhanVien();
	
	
	private String kyhieu;
	private String quyen;
	private String bienlai;
	
	
	private String maPhieu ;
	public void refreshnhanvienthungan(){
		DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
		nhanVienThungan = nvDelegate.findByNd(identity.getUsername());
		if (nhanVienThungan == null) {
			nhanVienThungan = new DtDmNhanVien();
		}
	}

	private static Logger log = Logger
			.getLogger(B3236_HoanThuTienCanLamSangAction.class);

	@DataModel
	private java.util.List<ClsKham> listCtkq = new java.util.ArrayList<ClsKham>();
	@DataModelSelection
	@Out(required = false)
	private ClsKham nhapctSelected;
	
	
	private ClsKham ctCLSKhamSelectedOld = null; //dung khi chinh sua 1 record

	private ClsKham ctCLSKham = null;

	private boolean updateNhapct = false;
	
	private DtDmCum cum;

	@In(required = false)
	@Out(required = false)
	Identity identity;

	@Restrict("#{s:hasRole('NV_VienPhi') or s:hasRole('QT_VienPhi')}")
	@Begin (join = true)
	public String init() throws Exception {
		log.info("***Starting init ***");
		emtyData();
		log.info("***Finished init ***");

		// set data from loggin persion
		/**
		 * TODO
		 */

		refreshnhanvienthungan();
		
		log.info("nhanVienThungan1"+nhanVienThungan);
		PcCumThuPhiDelegate pcCumThuPhiDelegate  = PcCumThuPhiDelegate.getInstance();
		PcCumThuPhi pc =  pcCumThuPhiDelegate.findPcCumThuPhiByNgayAndNhanVien(new Date(), nhanVienThungan.getDtdmnhanvienMa());
		
		log.info("PcCumThuPhi"+pc);
		
		
		if (pc!=null && pc.getDtdmnhanvienMa() != null){
			log.info("nhanVienThungan:"+nhanVienThungan);
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
		return "ThuVienPhi_SoLieuKhamBenh_HoanThuTienCanLamSam";
	}

	private void emtyData() {
		
		ctCLSKham = new ClsKham();
		SetInforUtil.setInforIfNullForClsKham(ctCLSKham);
		
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
		ngayTt = "";

		resultHidden = "";
		reportFinished = "";
		reportFileNameHid = "";
		
		kyhieu="";
		quyen="";
		bienlai="";

		// diengiai ="";

	}

	private void hasNoMaTiepDon() {
		emtyData();
		resetValue();
	}

	public void Caculation(List<ClsKham> clslist) {
		
		HsThtoank hsttk = new HsThtoank();
		hsttk.setTiepdonMa(thamKham.getTiepdonMa());
		HoSoThanhToanKhamUtil hsthtoankUtil = new HoSoThanhToanKhamUtil(thamKham.getTiepdonMa());
		hsthtoankUtil.tinhToanChoHSTKKham(hsttk);
		
		 permiengiam = 0;
		 miengiam = hsttk.getHsthtoankXetgiam();
		
		 //thatthu = hsthtoankUtil.getThatthu();
		 thatthu = ( hsttk.getHsthtoankThatthu() == null?0:hsttk.getHsthtoankThatthu().doubleValue() ) ;
		 //perbhytchi = hsthtoankUtil.getPerbhytchi();
		 perbhytchi = hsttk.getHsthtoankTylebh();
		 //bhytchi = hsthtoankUtil.getBhytchi();
		 bhytchi = hsttk.getHsthtoankBhyt();
		 //thanhtien1 = hsthtoankUtil.getThanhtien1();
		 thanhtien1 = hsttk.getHsthtoankTongchi();
		 //perbntra = hsthtoankUtil.getPerbntra();
		 perbntra = 100 - perbhytchi;
		 //bntra = hsthtoankUtil.getBntra();
		 bntra = hsttk.getHsthtoankBntra();

	}

	public String troVe() {
		try {
			log.info("***** troVe()");
			return "ThuVienPhi_SoLieuKhamBenh_HoanThuTienCanLamSam";
		} catch (Exception e) {
			log.info("***** End exception = " + e);
		}
		return null;
	}

	public void resetValue() {
		refreshnhanvienthungan();
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
		
		kyhieu="";
		quyen="";
		bienlai="";
	}

	
	// nhan vao link : chinh sua
	public void nhapctAjax() throws Exception {
		log.info("*****Begin nhapctAjax() *****");
		
		if (maPhieu != null && !maPhieu.equals("")){
			return;
		}
		/*if(nhapctSelected.getClskhamMaphieud() != null && !nhapctSelected.getClskhamMaphieud().equals("")){
			return;
		}
		*/
		
		
		ctCLSKham = (ClsKham)BeanUtils.cloneBean( nhapctSelected );
		if(ctCLSKham.getClskhamTra() == null || ctCLSKham.getClskhamTra().shortValue() ==0){
			ctCLSKham.setClskhamTra(ctCLSKham.getClskhamLan());
		}
		
		
		ctCLSKhamSelectedOld = nhapctSelected;		
		
		
		this.loai = ctCLSKham.getClskhamLoai();
		this.maSoCLS = ctCLSKham.getClskhamMahang(true).getDtdmclsbgMaso();
		this.maCLS= ctCLSKham.getClskhamMahang(true).getDtdmclsbgMa();
		this.mien = ctCLSKham.getClskhamMien();
		this.yeuCau = ctCLSKham.getClskhamYeucau();
		this.soLuong = ctCLSKham.getClskhamLan();
		this.donGia = ctCLSKham.getClskhamDongia();
		this.dichVu = ctCLSKham.getClskhamDichvu();
		this.ngoaiDanhMuc = ctCLSKham.getClskhamNdm();
		this.kyThuatCao = ctCLSKham.getClskhamKtcao();
		
		
		this.maKhoa = nhapctSelected.getClskhamKhoa(true).getDmkhoaMa();
		
		
		log.info("***********end nhapctAjax***********");

	}

	

	// Ham chuyen chi tiet nhap xuong duoi
	public void enter() throws Exception {
		log.info("*****Begin Enter() *****");
		
		if (maPhieu != null && !maPhieu.equals("")){
			return;
		}
		
		if (this.maCLS.equals("")) {
			resetValue();
			return;
		}
		
		if (this.soLuong == 0) {
			resetValue();
			return;
		}
		
		if (listCtkq == null) {
			listCtkq = new ArrayList<ClsKham>();
		}

		if (this.thamKham.getTiepdonMa() == null
				|| this.thamKham.getTiepdonMa().getTiepdonMa().equals("")
				|| this.benhNhan.getBenhnhanHoten() == null
				|| this.benhNhan.getBenhnhanHoten().equals("")

		) {
			resetValue();
			return;

		}
		
		//if (ctCLSKham.getClskhamMaphieud() != null){
		//	resetValue();
		//	return;
		//}
		
		if (ctCLSKham.getClskhamMaphieu() == null || ctCLSKham.getClskhamMaphieu().equals("")){
			resetValue();
			return;
		}
		
		if (ctCLSKham.getClskhamTra() == null || ctCLSKham.getClskhamTra().shortValue() <= 0){
			resetValue();
			return;
		}
		
		// phuc.lc 23-11-2010 : Tinh tong so tien hoan thu CLS
		thanhtien1 += (donGia * ctCLSKham.getClskhamTra());
		if(ctCLSKhamSelectedOld != null){
			listCtkq.remove(ctCLSKhamSelectedOld);
			 ctCLSKhamSelectedOld = null;
		}
		
		listCtkq.add(ctCLSKham);	
		
		
		ctCLSKham = new ClsKham();
		SetInforUtil.setInforIfNullForClsKham(ctCLSKham);

		resetValue();
		
		log.info("*****End Enter() *****");
	}

	// Ham delete chi tiet thuc hien khi nhan Link Xoa mot dong chi tiet
	public void delete(int index) throws Exception {
		log.info("*****Begin delete() *****");
		
		if (maPhieu != null && !maPhieu.equals("")){
			return;
		}
		
		ClsKham clsKham_tmp = listCtkq.get(index);
		if(clsKham_tmp.getClskhamMaphieud() != null){
			return;
		}
		
		listCtkq.remove(index);
		
		ctCLSKham = new ClsKham();
		SetInforUtil.setInforIfNullForClsKham(ctCLSKham);
		
		Caculation(listCtkq);
		log.info("*****End delete() *****");
	}

	// Ham ghi nhan thuc thi sau khi nhan nut Ghi Nhan
	public void ghinhan() throws Exception {
		refreshnhanvienthungan();
		if (maPhieu != null && !maPhieu.equals("")){
			return;
		}
		
		if (listCtkq == null || listCtkq.size() ==0) {
			FacesMessages.instance().add(IConstantsRes.CHON_IT_NHAT_MOT_CLS_DE_HOAN_TRA);			
			return;
		}
		
		boolean hasCLSDeHoanTra = false;
		for (ClsKham cls: listCtkq){
			//if (cls.getClskhamMaphieud() != null){
			//	continue;
			//}
			if (cls.getClskhamMaphieu() == null || cls.getClskhamMaphieu().equals("")){
				continue;
			}
			if (cls.getClskhamTra() == null || cls.getClskhamTra().shortValue() <= 0){
				continue;
			}
			hasCLSDeHoanTra = true;
			break;
		}
		
		if (hasCLSDeHoanTra == false){
			FacesMessages.instance().add(IConstantsRes.CHON_IT_NHAT_MOT_CLS_DE_HOAN_TRA);			
			return;
		}
		
		
		
		log.info("*****Begin Ghi nhan() *****");
		log.info("*****so phan tu insert *****" + listCtkq.size());

		if (this.thamKham.getTiepdonMa() == null
				|| this.thamKham.getTiepdonMa().getTiepdonMa().equals("")
				
				|| this.benhNhan.getBenhnhanHoten() == null
				|| this.benhNhan.getBenhnhanHoten().equals("")) {

			resetValue();
			return;
		}

		try {
			// ClsKhamWSService clskhamService = new ClsKhamWSServiceLocator();
			// ClsKhamWS clskhamWS = clskhamService.getClsKhamWSPort();

			ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();

			// ClsKham[] clsKhamArray = new ClsKham[listCtkq.size()];
			// log.info("clsKhamArray:" + clsKhamArray);
			for (int i = 0; i < listCtkq.size(); i++) {
				removeIfNullForClsKham(listCtkq.get(i));

//				listCtkq.get(i).setClskhamThungan(nhanVienThungan);
//				listCtkq.get(i).setClskhamDatt(true);
//				listCtkq.get(i).setClskhamThamkham(thamKham);

//				RemoveUtil.removeAllNullFromThamKham(listCtkq.get(i)
//						.getClskhamThamkham());
//				RemoveUtil.removeAllNullFromTiepDon(listCtkq.get(i)
//						.getClskhamThamkham().getTiepdonMa());

				// clsKhamArray[i] = listCtkq.get(i);
			}
			// log.info("clsKhamArray:" + clsKhamArray);
			// log.info("clsKhamArray size :" + clsKhamArray.length);
			
			ArrayList<ClsKham> arrayListHasMaPhieuH = new ArrayList<ClsKham>();
			for (ClsKham cls: listCtkq){
				if (cls.getClskhamMaphieu() != null && !cls.getClskhamMaphieu().equals("") && 
						(cls.getClskhamMaphieud() == null || cls.getClskhamMaphieud().equals(""))
						&& cls.getClskhamTra() != null && cls.getClskhamTra() > 0
						){
					
					cls.setClskhamCumHoan(cum);
					
					cls.setClskhamKyHieuHoan(kyhieu);
					cls.setClskhamQuyenHoan(quyen);
					cls.setClskhamBienLaiHoan(bienlai);
					
					cls.setClskhamThunganHoan(nhanVienThungan);
					
					SimpleDateFormat formatter;	    
			        formatter = new SimpleDateFormat(Utils.FORMAT_DATE); 
			       
			       
		           Date d =	formatter.parse(ngayTt);
		           Calendar dCalendar = Calendar.getInstance();
		           dCalendar.setTime(d);
			          
		           cls.setClskhamNgaygioHoanThu(dCalendar.getTime());
		           
					
				}
			
				if (cls.getClskhamMaphieud() != null && !cls.getClskhamMaphieud().equals("")){
					arrayListHasMaPhieuH.add(cls);
				}				
			}
//			for (ClsKham cls: arrayListHasMaPhieu){
//				listCtkq.remove(cls);
//			}		
			String maPhieu ;
			
			if (maBanKham==null||
					maBanKham.equals("")){
				 maPhieu = clsKhamDelegate.createClsKhamHoanTra(listCtkq, thamKham.getTiepdonMa().getTiepdonMa());
				
			}else{
				 maPhieu = clsKhamDelegate.createClsKhamHoanTra(listCtkq, thamKham.getTiepdonMa().getTiepdonMa(),maBanKham);
					
			}
			
			this.maPhieu = maPhieu;
			
			log.info("this.maPhieu------------:"+this.maPhieu);
			
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

	@Out(required=false)
	@In(required=false)
	private String reportPathVP=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeVP=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintVP=null;
	
	// Ham khi nhan nut In phieu
	public String inphieu() throws Exception {
		log.info("Begining inphieu()");
		
		if (maPhieu == null || maPhieu.equals("")){
			return "";
		}
		
		try {
			
			XuatReport();
		} catch (Exception e) {
			log.info("Loi trong khi xuat report" + e.toString());
		}
		log.info("End inphieu()");
		return "B3360_Chonmenuxuattaptin";
	}

	int index = 0;
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeVP="Hoanthutiencanlamsang";
		log.info("Vao Method XuatReport thuoc y dung cu phong kham");
		String baocao1=null;
		String pathTemplate=null;
		try {
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/PhieuChi.jrxml";
			
			log.info("da thay file templete " + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			Map<String, Object> params = new HashMap<String, Object>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			
			params.put("tenBenhNhan", benhNhan.getBenhnhanHoten());
			
			String diachistr="";
			if(benhNhan.getBenhnhanDiachi() != null)
				diachistr += benhNhan.getBenhnhanDiachi()+",";
			if(benhNhan.getXaMa(true).getDmxaTen() !=null)
				diachistr += benhNhan.getXaMa(true).getDmxaTen()+",";
			if(benhNhan.getHuyenMa(true).getDmhuyenTen() != null)
				diachistr += benhNhan.getHuyenMa(true).getDmhuyenTen()+",";
			if(benhNhan.getTinhMa(true).getDmtinhTen() != null)
				diachistr += benhNhan.getTinhMa(true).getDmtinhTen();
			params.put("diaChiBenhNhan", diachistr );
			
			//params.put("tienThuoc", bntra);
			//params.put("TIENBANGCHU", Utils.NumberToString(bntra));
			// phuc.lc 29-12-2010 : fix bug 1850
			params.put("tienThuoc", thanhtien1);
			params.put("TIENBANGCHU", Utils.NumberToString(thanhtien1));
			
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","Hoanthutiencanlamsang");
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
			log.info("Ngay sinh :" + ngaySinh);
		}
		if (clskham.getClskhamNgaygiott() != null
				&& !clskham.getClskhamNgaygiott().equals("")) {
			ngayTt = formatter.format(clskham.getClskhamNgaygioHoanThu().getTime());
			log.info("Ngay thanh toan :" + ngayTt);
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

	private void setinfor(ClsKham nhapctSelected) {
		// SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
		DtDmClsBangGia dmkythuat = nhapctSelected.getClskhamMahang();
		// dmkythuat.setDtdmclsbgDiengiai(tenCLS);
		nhapctSelected.setClskhamMahang(dmkythuat);
		if (thamKham != null) {
			nhapctSelected.setClskhamThamkham(thamKham);
		}
		try {
			if (!("".equals(ngayTt))) {
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				cal.setTime(df.parse(ngayTt));
				nhapctSelected.setClskhamNgaygio(cal.getTime());
			}
		} catch (Exception e) {

		}
		log.info(" tenCLS3 " + tenCLS);
		// if (clskham.getClskhamBankham() != null) {
		// nhapctSelected.setClskhamBankham(clskham.getClskhamBankham());
		// }
	}

	public void subDisplayInfor(){
		if (thamKham.getTiepdonMa() == null
				|| thamKham.getTiepdonMa().getTiepdonMa().equals("")) {
			hasNoMaTiepDon();
			FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND);
			listCtkq = new ArrayList<ClsKham>();
			return;
		}
		// ClsKhamWSService clskhamService = new ClsKhamWSServiceLocator();
		// ClsKhamWS clskhamWS = clskhamService.getClsKhamWSPort();

		

		ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
		ThamKham tk = tkDelegate
				.findByMaTiepDon( thamKham
						.getTiepdonMa().getTiepdonMa());
		
		
		if (tk != null) {
			thamKham = tk;
			SetInforUtil.setInforIfNullForThamKham(thamKham);
			SetInforUtil.setInforIfNullForTiepDon(thamKham.getTiepdonMa());
			benhNhan = thamKham.getTiepdonMa().getBenhnhanMa();
			SetInforUtil.setInforIfNullForBN(benhNhan);

		}else{
			hasNoMaTiepDon();
			FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND);
			listCtkq = new ArrayList<ClsKham>();
			return;
		}
	}
	
	public void SetInforAllCLS(List<ClsKham> clskham_tmp){
		
		listCtkq.clear();
		if (clskham_tmp == null || clskham_tmp.size() == 0) {
			log.info("displayInfor   tham kham 22 ....." + clskham);
			listCtkq = new ArrayList<ClsKham>();
			log.info("displayInfor   tham kham 23 ....." + clskham);
		} else {
		
//			listCtkq = clskham_tmp;

			log.info("clskham_tmp khac null va co' data");
//			ArrayList<ClsKham> listCtkqDaThanhToan = new ArrayList<ClsKham>();
			
			for (int i = 0; i < clskham_tmp.size(); i++) {
				ClsKham cls =  clskham_tmp.get(i);
				
				//chi lay nhung cls nao da co' ma phieu
				if (cls.getClskhamMaphieu() == null || cls.getClskhamMaphieu().equals("") ){
					log.info("clskham ko co ma phieu (chua tinh tien) , return");
					continue;
				}
//				if (maPhieu == null || maPhieu.equals("")){
//					if (cls.getClskhamDatt()){
//						listCtkqDaThanhToan.add(cls);
//						continue;
//					}
//				}
				
				setInforIfNullForClsKham(cls);
				cls.setClskhamThamkham(thamKham);
				
				listCtkq.add(cls);
				log.info("listCtkq co du lieu voi kich thuoc:" + listCtkq.size());
			
			}
			
			
			
//			for (ClsKham cls :  listCtkqDaThanhToan){
//				listCtkq.remove(cls);
//			}
		}
	}
	
	public void displayInforMaPhieu(){
		if (maPhieu == null || maPhieu.equals("")){
			return;
		}
		ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();

		List<ClsKham> clskham_tmp = clsKhamDelegate.findByMaPhieuHoan(maPhieu);
		
		if (clskham_tmp==null || clskham_tmp.size() == 0){
			FacesMessages.instance().add(IConstantsRes.KHONG_TIM_THAY_PHIEU,"");			
			
			return;
		}
		
		kyhieu = clskham_tmp.get(0).getClskhamKyHieuHoan();
		quyen = clskham_tmp.get(0).getClskhamQuyenHoan();
		bienlai = clskham_tmp.get(0).getClskhamBienLaiHoan();
		
		
		maPhieu = clskham_tmp.get(0).getClskhamMaphieud();
		
		thamKham = clskham_tmp.get(0).getClskhamThamkham();
		SetInforUtil.setInforIfNullForThamKham(thamKham);
		SetInforUtil.setInforIfNullForTiepDon(thamKham.getTiepdonMa());
		benhNhan = thamKham.getTiepdonMa().getBenhnhanMa();
		SetInforUtil.setInforIfNullForBN(benhNhan);
		
		SetInforAllCLS( clskham_tmp);
		
		Caculation(listCtkq);			
		
		setOtherValue();
		// phuc.lc 23-11-2010 : Tinh tong hoan thu CLS theo ma phieu (fix bug 1416)
		thanhtien1 = new Double(0);
		for(ClsKham clsTmp : clskham_tmp) {
			thanhtien1 += (clsTmp.getClskhamDongia() * clsTmp.getClskhamTra());
		}
		
	}
	// Hien thi thong tin CLS Kham cua benh nhan sau khi nhap Ban kham va ma
	// tiep don
	public void displayInfor() throws Exception {
		try {
			log.info("Begining displayInfor()");

			
			subDisplayInfor();
			
			ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();

			List<ClsKham> clskham_tmp = new ArrayList<ClsKham>() ;
			
			if (maBanKham==null||
					maBanKham.equals("")){
				log.info("no ban kham");
			clskham_tmp = clsKhamDelegate
					.findByTiepdonma( thamKham
							.getTiepdonMa().getTiepdonMa());			
			}else{
				log.info("having ban kham");
				clskham_tmp = clsKhamDelegate
				.findByBanKhamVaMaTiepDon(maBanKham, thamKham
						.getTiepdonMa().getTiepdonMa());		
			}
			
			// phuc.lc 22-11-2010 : Remove cac cls da hoan tra khoi list (fix bug 1416)
			listCtkq.clear();
			
			for(ClsKham eachCls : clskham_tmp) {
				int lan = (eachCls.getClskhamLan() == null ? 0 : eachCls.getClskhamLan().intValue());
				int tra = (eachCls.getClskhamTra() == null ? 0 : eachCls.getClskhamTra().intValue());
				if (lan == tra) {					
					listCtkq.add(eachCls);
				}
			}
			
			for (ClsKham eachCls : listCtkq) {				
				clskham_tmp.remove(eachCls);
			}
			listCtkq.clear();
			maPhieu = "";
			// phuc.lc 22-11-2010 : END
			
			SetInforAllCLS( clskham_tmp);
			
			Caculation(listCtkq);
				
			
			setOtherValue();
			// phuc.lc 23-11-2010 : khi load thong tin theo ma tiep don thi reset tong cong  = 0 (fix bug 1416)
			thanhtien1 = new Double(0);

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
			log.info("Ma hang null");
		}
		if ("".equals(Utils.reFactorString(cls.getClskhamKhoa().getDmkhoaMa()))) {
			cls.setClskhamKhoa(null);
			log.info("Khoa null");
		}
		// if
		// ("".equals(Utils.reFactorString(cls.getClskhamBankham().getDtdmbankhamMa()))){
		// cls.setClskhamBankham(null);
		// log.info("Ban Kham null");
		// }
		log.info("----------cls.getClskhamChedott():"+cls.getClskhamChedott());
		if ("".equals(Utils.reFactorString(cls.getClskhamChedott()
				.getDmdoituongMa()))) {
			cls.setClskhamChedott(null);
			log.info("Che do tt null");
		}
		if (""
				.equals(Utils.reFactorString(cls.getClskhamKhoa2()
						.getDmkhoaMa()))) {
			cls.setClskhamKhoa2(null);
			log.info("Khoa 2 null");
		}
		if ("".equals(Utils.reFactorString(cls.getClskhamMabs()
				.getDtdmnhanvienMa()))) {
			cls.setClskhamMabs(null);
			log.info("Bac si null");
		}
		if ("".equals(Utils.reFactorString(cls.getClskhamMaloai()
				.getDtdmclsMa()))) {
			cls.setClskhamMaloai(null);
			log.info("Ma loai null");
		}
		if ("".equals(Utils.reFactorString(cls.getClskhamNhanviencn()
				.getDtdmnhanvienMa()))) {
			cls.setClskhamNhanviencn(null);
			log.info("Nhan vien cn null");
		}
		if ("".equals(Utils.reFactorString(cls.getClskhamThuchien()
				.getDtdmnhanvienMa()))) {
			cls.setClskhamThuchien(null);
			log.info("Thuc hien  null");
		}
		if ("".equals(Utils.reFactorString(cls.getClskhamThungan()
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
		
		log.info("----------cls.getClskhamChedott():"+cls.getClskhamChedott());
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

//	private void setInforIfNullForClsKham() {
//		log.info("Begin setInforIfNullForClsKham(): ");
//		if (nhapctSelected.getClskhamMahang() == null) {
//			nhapctSelected.setClskhamMahang(new DtDmClsBangGia());
//		}
//		if (nhapctSelected.getClskhamKhoa() == null) {
//			nhapctSelected.setClskhamKhoa(new DmKhoa());
//		}
//		// if (nhapctSelected.getClskhamBankham() == null) {
//		// nhapctSelected.setClskhamBankham(new DtDmBanKham());
//		// }
//		if (nhapctSelected.getClskhamChedott() == null) {
//			nhapctSelected.setClskhamChedott(new DmDoiTuong());
//		}
//		if (nhapctSelected.getClskhamKhoa2() == null) {
//			nhapctSelected.setClskhamKhoa2(new DmKhoa());
//		}
//		if (nhapctSelected.getClskhamMabs() == null) {
//			nhapctSelected.setClskhamMabs(new DtDmNhanVien());
//		}
//		if (nhapctSelected.getClskhamMaloai() == null) {
//			nhapctSelected.setClskhamMaloai(new DtDmCls());
//		}
//		if (nhapctSelected.getClskhamNhanviencn() == null) {
//			nhapctSelected.setClskhamNhanviencn(new DtDmNhanVien());
//		}
//		if (nhapctSelected.getClskhamThuchien() == null) {
//			nhapctSelected.setClskhamThuchien(new DtDmNhanVien());
//		}
//		if (nhapctSelected.getClskhamThungan() == null) {
//			nhapctSelected.setClskhamThungan(new DtDmNhanVien());
//		}
//		log.info("End setInforIfNullForClsKham(): ");
//	}

//	private void setInforIfNullForBN() {
//		log.info("Begin setInforIfNullForBN(): ");
//		// log.info("benhNhan.getTinhMa():"+benhNhan.getTinhMa());
//		if (benhNhan.getTinhMa() == null) {
//			benhNhan.setTinhMa(new DmTinh());
//		}
//
//		if (benhNhan.getHuyenMa() == null) {
//			benhNhan.setHuyenMa(new DmHuyen());
//		}
//		if (benhNhan.getXaMa() == null) {
//			benhNhan.setXaMa(new DmXa());
//		}
//		if (benhNhan.getBenhnhanNghe() == null) {
//			benhNhan.setBenhnhanNghe(new DmNgheNghiep());
//		}
//		if (benhNhan.getDantocMa() == null) {
//			benhNhan.setDantocMa(new DmDanToc());
//		}
//		log.info("End setInforIfNullForBN(): ");
//	}

	// private void setInforIfNullForTiepDon() {
	// log.info("Begin setInforIfNullForTiepDon(): ");
	// if (tiepdon.getDoituongMa() == null) {
	// tiepdon.setDoituongMa(new DmDoiTuong());
	// }
	// if (tiepdon.getTainanMa() == null) {
	// tiepdon.setTainanMa(new DmTaiNan());
	// }
	// if (tiepdon.getDmptgtnMaso() == null) {
	// tiepdon.setDmptgtnMaso(new DmPhuongThucGayTaiNan());
	// }
	// if (tiepdon.getTiepdonDonvigoi() == null) {
	// tiepdon.setTiepdonDonvigoi(new DmBenhVien());
	// }
	// if (tiepdon.getTiepdonMachdoanb0() == null) {
	// tiepdon.setTiepdonMachdoanb0(new DmBenhIcd());
	// }
	// if (tiepdon.getTiepdonTuvong() == null) {
	// tiepdon.setTiepdonTuvong(new DmBenhIcd());
	// }
	// if (tiepdon.getTiepdonChvien() == null) {
	// tiepdon.setTiepdonChvien(new DmBenhVien());
	// }
	// if (tiepdon.getTiepdonBschuyen() == null) {
	// tiepdon.setTiepdonBschuyen(new DtDmNhanVien());
	// }
	// if (tiepdon.getTiepdonLydochvi() == null) {
	// tiepdon.setTiepdonLydochvi(new DtDmLyDoCv());
	// }
	// if (tiepdon.getTiepdonChkhoa() == null) {
	// tiepdon.setTiepdonChkhoa(new DmKhoa());
	// }
	// if (tiepdon.getDiadiemMa() == null) {
	// tiepdon.setDiadiemMa(new DmDiaDiem());
	// }
	// log.info("End setInforIfNullForTiepDon(): ");
	// }

//	private void setInforIfNullForCls() {
//		log.info("Begin setInforIfNullForCls(): ");
//		if (clskham.getClskhamMahang() == null) {
//			clskham.setClskhamMahang(new DtDmClsBangGia());
//		}
//		if (clskham.getClskhamKhoa() == null) {
//			clskham.setClskhamKhoa(new DmKhoa());
//		}
//		// if (clskham.getClskhamBankham() == null) {
//		// clskham.setClskhamBankham(new DtDmBanKham());
//		// }
//		if (clskham.getClskhamChedott() == null) {
//			clskham.setClskhamChedott(new DmDoiTuong());
//		}
//		if (clskham.getClskhamKhoa2() == null) {
//			clskham.setClskhamKhoa2(new DmKhoa());
//		}
//		if (clskham.getClskhamMabs() == null) {
//			clskham.setClskhamMabs(new DtDmNhanVien());
//		}
//		if (clskham.getClskhamMaloai() == null) {
//			clskham.setClskhamMaloai(new DtDmCls());
//		}
//		if (clskham.getClskhamNhanviencn() == null) {
//			clskham.setClskhamNhanviencn(new DtDmNhanVien());
//		}
//		if (clskham.getClskhamThuchien() == null) {
//			clskham.setClskhamThuchien(new DtDmNhanVien());
//		}
//		if (clskham.getClskhamThungan() == null) {
//			clskham.setClskhamThungan(new DtDmNhanVien());
//		}
//		log.info("End setInforIfNullForCls(): ");
//	}

	// Ham reset form
	private void ResetForm() {
		refreshnhanvienthungan();
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
		ngayTt = "";
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
		bhytchi = new Double(0);
		thanhtien1 = new Double(0);
		perbntra = 0;
		bntra = new Double(0);
		listCtkq = new ArrayList<ClsKham>();
		resultHidden = "";
		maPhieu = "";
		
		maBanKham="";
		
		kyhieu="";
		quyen="";
		bienlai="";
		
		log.info("End ResetForm(): ");
	}

	// Xuat report
	private String reportFinished = "";
	
	/*
	private String xuatReportCLs(String tenFileTemplate, String loaiFile)
			throws Exception {

		log.info("tenFileTemplate: " + tenFileTemplate);
		Date currentDate = new Date();
		JasperPrint jasperPrint = null;
		String pathExport = null;
		try {
			log.info("Vao method XuatReport ");
			String pathTemplate = IConstantsRes.PATH_BASE
					+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/"
					+ tenFileTemplate + ".jrxml";
			log.info("pathTemplate:" + pathTemplate);
			JasperReport jasperReport = JasperCompileManager
					.compileReport(pathTemplate);
			log.info("da thay file template ");
			String gioitinh = "";
			String thebhyt = "";
			String tiepdonma = thamKham.getTiepdonMa().getTiepdonMa();
			String ketqua = "";
			String bankham = "";

			if (benhNhan.getDmgtMaso() != null) {
				if ("1".equals(benhNhan.getDmgtMaso().getDmgtMa())) {
					gioitinh = "Nam";
				} else {
					gioitinh = "Nữ";
				}
			} else {
				gioi = null;
			}

			if (thamKham.getTiepdonMa().getTiepdonSothebh() != null
					&& !thamKham.getTiepdonMa().getTiepdonSothebh().equals("")) {
				thebhyt = thamKham.getTiepdonMa().getTiepdonSothebh();
			}
			// if (tiepdon.getTiepdonMa() != null &&
			// !tiepdon.getTiepdonMa().equals("")) {
			// tiepdonma = tiepdon.getTiepdonMa();
			// if (tiepdonma.length() < 6 ) {
			// ketqua = IConstantsRes.REPORT_DIEUTRI_MA_TINH +
			// IConstantsRes.REPORT_DIEUTRI_MA_DON_VI;
			// Calendar cal = new GregorianCalendar();
			// String sYear = String.valueOf(cal.get(Calendar.YEAR));
			// if (sYear.length() == 4) {
			// sYear = sYear.substring(2);
			// }
			// ketqua += sYear;
			// while (tiepdonma.length() < 6) {
			// tiepdonma = "0" + tiepdonma;
			// }
			// ketqua += tiepdonma;
			// }
			// }
			// else {
			// ketqua = "";
			// }
			// if (clskham.getClskhamBankham() != null ) {
			// if (clskham.getClskhamBankham().getDtdmbankhamMa() != null &&
			// !clskham.getClskhamBankham().getDtdmbankhamMa().equals("")) {
			// bankham = clskham.getClskhamBankham().getDtdmbankhamMa();
			// }
			// else {
			// bankham = "";
			// }
			// }
			// else {
			// bankham = "";
			// }
			// log.info("Tiep don ma = " + ketqua);
			Map<String, Object> params = new HashMap<String, Object>();
			// saveSum(tuNgay, denNgay, donViMa);
			params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("dienThoaiDonVi",
					IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			params.put("hoten", benhNhan.getBenhnhanHoten());
			params.put("gioitinh", gioitinh);
			params.put("thebhyt", thebhyt);
			params.put("ngaythanhtoan", ngayTt);
			params.put("bankham", bankham);
			params.put("tiepdonma", tiepdonma);
			
			log.info("maphieu:" + maPhieu);
			params.put("maphieu", maPhieu);

			String fileNameExt = String.valueOf(currentDate.getTime());
			String fileName = ReportUtil.xuatReportBenhAnDieuTri(
					IConstantsRes.PATH_BASE,
					IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI,
					IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI, "vienphi/",
					tenFileTemplate, params, loaiFile, fileNameExt);
			reportFinished = position;
			reportFileNameHid = fileName;
			if (loaiFile.equalsIgnoreCase("HTML")) {
				resultReportName = fileName;
			} else {
				resultReportFileName = fileName;
			}
			setIsReport("true");
			return null;

			// log.info(tiepdon.getTiepdonMa());
			// System.out.print(tiepdon.getTiepdonMa());
			// SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
			// SimpleDateFormat sqlFormatter = new
			// SimpleDateFormat(IConstantsRes.SQL_FORMAT_DATE);
			//			
			// Class.forName("oracle.jdbc.OracleDriver");
			// Connection conn =
			// DriverManager.getConnection(IConstantsRes.DATABASE_URL,
			// IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);
			//            
			// jasperPrint = JasperFillManager.fillReport(jasperReport, params,
			// conn);
			// pathExport = xuatFile(jasperPrint, "HTML",currentDate.getTime() +
			// tenFileTemplate);
			//            
			// reportFinished = pathExport;
			// log.info(reportFinished);
			// conn.close();

		} catch (JRException ex) {
			ex.printStackTrace();
		}
		// reportFinished = position + pathExport;
		reportFinished = pathExport.substring(pathExport.lastIndexOf("/") + 1,
				pathExport.length());
		return position + pathExport;
	}

	 */
	
	// Ham xuat file
	// private String xuatFile(JasperPrint jasperPrint, String loaiFile, String
	// tenFile)throws Exception {
	//		
	// log.info("jasperPrint:"+jasperPrint);
	// log.info("loaiFile:"+loaiFile);
	// log.info("tenFile:"+tenFile);
	//        
	// String fileName = "";
	//
	// String pathExport = IConstantsRes.PATH_BASE +
	// IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/";
	// if (loaiFile.compareToIgnoreCase("HTML") == 0) {
	//			
	// JRHtmlExporter htmlExporter = new JRHtmlExporter();
	// htmlExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	// htmlExporter.setParameter(JRExporterParameter.CHARACTER_ENCODING,
	// "UTF-8");
	// pathExport += tenFile + ".html";
	// htmlExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
	// pathExport);
	// log.info("Đang xuất report...");
	// htmlExporter.exportReport();
	// log.info("Xong!");
	// fileName = tenFile + ".html";
	//			
	// } else if (loaiFile.compareToIgnoreCase("DOC") == 0) {
	// JRRtfExporter rtfExporter = new JRRtfExporter();
	// rtfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	// rtfExporter.setParameter(JRExporterParameter.CHARACTER_ENCODING,
	// "UTF-8");
	// pathExport += tenFile + ".doc";
	// rtfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
	// pathExport);
	// log.info("Đang xuất report...");
	// rtfExporter.exportReport();
	// log.info("Xong!");
	// fileName = tenFile + ".doc";
	//			
	// } else if (loaiFile.compareToIgnoreCase("EXCEL") == 0) {
	// JExcelApiExporter xlsExporter = new JExcelApiExporter();
	// xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	// xlsExporter.setParameter(JRExporterParameter.CHARACTER_ENCODING,"UTF-8");
	// pathExport += tenFile + ".xls";
	// xlsExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
	// pathExport);
	// log.info("Đang xuất report...");
	// xlsExporter.exportReport();
	// log.info("Xong!");
	// fileName = tenFile + ".xls";
	//			
	// } else if (loaiFile.compareToIgnoreCase("PDF") == 0) {
	// JRPdfExporter pdfExporter = new JRPdfExporter();
	// pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	// pdfExporter.setParameter(JRExporterParameter.CHARACTER_ENCODING,
	// "UTF-8");
	// pathExport += tenFile + ".pdf";
	// pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
	// pathExport);
	// log.info("Đang xuất report...");
	// pdfExporter.exportReport();
	// log.info("Xong!");
	// JasperViewer.viewReport(jasperPrint);
	// fileName = tenFile + ".pdf";
	// }
	// else{
	// log.info("Nhập Loại File Không Đúng");
	// }
	// return fileName;
	// }

	

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

	public ClsKham getCtCLSKham() {
		return ctCLSKham;
	}

	public void setCtCLSKham(ClsKham ctCLSKham) {
		this.ctCLSKham = ctCLSKham;
	}

	public Boolean getDichVu() {
		return dichVu;
	}

	public void setDichVu(Boolean dichVu) {
		this.dichVu = dichVu;
	}

	
	public ClsKham getCtCLSKhamSelectedOld() {
		return ctCLSKhamSelectedOld;
	}

	public void setCtCLSKhamSelectedOld(ClsKham ctCLSKhamSelectedOld) {
		this.ctCLSKhamSelectedOld = ctCLSKhamSelectedOld;
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

	public DtDmCum getCum() {
		return cum;
	}

	public void setCum(DtDmCum cum) {
		this.cum = cum;
	}

}



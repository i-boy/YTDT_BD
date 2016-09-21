package com.iesvn.yte.dieutri.tiepdon.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.jboss.seam.annotations.Destroy;
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

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.BenhAnNgoaiTruDelegate;
import com.iesvn.yte.dieutri.delegate.CtBenhAnNgoaiTruDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
import com.iesvn.yte.dieutri.entity.BenhAnNgoaiTru;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.CtBenhAnNgoaiTru;
import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
import com.iesvn.yte.entity.DmBenhIcd;
import com.iesvn.yte.entity.DmDanToc;
import com.iesvn.yte.entity.DmHuyen;
import com.iesvn.yte.entity.DmNgheNghiep;
import com.iesvn.yte.entity.DmQuocGia;
import com.iesvn.yte.entity.DmTinh;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B121_10_Lapbangoaitru")
@Synchronized(timeout = 6000000)
public class LapBANgoaiTruAction implements Serializable {

	private static String FORMAT_DATE = "dd/MM/yyyy";
	private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";

	private static final long serialVersionUID = 10L;
	private int stt;
	private String ngaySinh;
	private String thoiGian;
	private String gioThamKham;
	private String ngay;
	private String dienbienbenh;
	private String ylenh;
	private String bacSiMa;
	private Integer bacSiMaSo_hidden;
	@DataModel
	private List<CtBenhAnNgoaiTru> listCtBANT;
	@DataModelSelection(value="listCtBANT")
	private CtBenhAnNgoaiTru ctbantSelection;
	private CtBenhAnNgoaiTruDelegate ctbantDAO;
	private static Logger log = Logger.getLogger(ThamKhamAction.class);
	
	@In(required = false)
	@Out(required = false)
	private String maBanKhamOut;
	
	@In(required = false)
	@Out(required = false)
	private String maTiepDonOut;
	
	@In(required = false)
	@Out(required = false)
	private String goToLapBANgoaiTru;
	private SimpleDateFormat formatter;
	private BenhNhan benhNhan;
	

	private ThamKham thamkham;	

	
	
	public void resetValue() {


	}
	private String resultHidden ="";
	
	private BenhAnNgoaiTru baNgoaiTru = null;

	
	
	@Begin(nested = true)
	@Factory("goToLapBANgoaiTru")
	public void init() throws Exception {
		log.info("***Starting init, maTiepDonOut = " + maTiepDonOut);		
		try{
			dienbienbenh = "";
			ylenh = "";
			ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
			ctbantDAO = CtBenhAnNgoaiTruDelegate.getInstance();
			thamkham = tkDelegate.findByBanKhamVaMaTiepDon(maBanKhamOut,maTiepDonOut);
			benhNhan = thamkham.getTiepdonMa().getBenhnhanMa();
			
			
			if(thamkham.getThamkhamBacsi() != null){
				bacSiMa = thamkham.getThamkhamBacsi().getDtdmnhanvienMa();
//				bacSiMaSo_hidden = thamkham.getThamkhamBacsi().getDtdmnhanvienMaso();
			}
			
			
			BenhAnNgoaiTru baNgoaiTruTemp = null;
			try{
				//phuc.lc 22-07-2011 : begin
				if(thamkham.getTiepdonMa().getDoituongMa().getDmdoituongMa().equalsIgnoreCase("BH")) {
					log.info("SotheBH = " + thamkham.getTiepdonMa().getTiepdonSothebh() + ", Ma BN = " + thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanMa());
					baNgoaiTruTemp = BenhAnNgoaiTruDelegate.getInstance().findBySotheBHAndMaBNAndBanKham(thamkham.getTiepdonMa().getTiepdonSothebh(),
							                                                                             thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanMa(),
							                                                                             thamkham.getThamkhamBankham().getDtdmbankhamMaso());
				} else {
					log.info("Ma BN = " + thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanMa());
					baNgoaiTruTemp = BenhAnNgoaiTruDelegate.getInstance().findBySotheBHAndMaBNAndBanKham(null,
                            																			thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanMa(),
                            																			thamkham.getThamkhamBankham().getDtdmbankhamMaso());
				}
				log.info("baNgoaiTruTemp = " + baNgoaiTruTemp);
				// baNgoaiTruTemp = BenhAnNgoaiTruDelegate.getInstance().getBANgoaiTru(thamkham.getThamkhamMa());
				//phuc.lc 22-07-2011 : end
			}catch(Exception e){
				log.info("error:"+ e);
			}
			// bao.ttc: log.info("%%%%%%%%%%%%%%%%%%%%%baNgoaiTruTemp:"+baNgoaiTruTemp);
			if (baNgoaiTruTemp != null){
				baNgoaiTru = baNgoaiTruTemp;
				listCtBANT = ctbantDAO.findByBANTMa(baNgoaiTru.getBantMa());
				
				log.info("%%%%%%%%%%%%%%%%%%%%%baNgoaiTru.getBantMa(): " + baNgoaiTru.getBantMa());
				
			}else{
				baNgoaiTru = new BenhAnNgoaiTru();
				listCtBANT = new ArrayList<CtBenhAnNgoaiTru>();
			}
			setOtherValue();
			
			// 20110307 bao.ttc: Lay thong tin Y Lenh theo Ngay lay trong thamkham.getThamkhamNgaygio()
			get_thuoc_info();
			
			destroyService();
			goToLapBANgoaiTru = "";
		}catch(Exception e){
			log.info("***init Exception = " + e);
		}
		log.info("***Finished init ***");		
	}

	@End 
	public void end(){
		goToLapBANgoaiTru = null;
	}
		
	//***********************************************************************************
	
	//Ly
	public void enter() throws Exception {
		log.info("*****Begin Enter() *****");
		insertRow();
		reset_ctbant();
		log.info("*****End Enter() *****");
	}
	private void insertRow(){
		CtBenhAnNgoaiTru each = new CtBenhAnNgoaiTru();
		each.setCtbantDienbienbenh(dienbienbenh);
		each.setCtbantYlenh(ylenh);
		if(ngay != null && !ngay.equals(""))
			each.setCtbantNgay(Utils.getDBDate("00:00", ngay).getTime());
		DtDmNhanVien bacsi = new DtDmNhanVien();
		if(bacSiMaSo_hidden != null && !bacSiMaSo_hidden.equals("")){
			bacsi.setDtdmnhanvienMaso(bacSiMaSo_hidden);
			bacsi.setDtdmnhanvienMa(bacSiMa);
			each.setBacsi(bacsi);
		} else {
			each.setBacsi(null);
		}
		each.setBenhAnNgoaiTru(baNgoaiTru);
		listCtBANT.add(each);
	}
	
	public void delete(int index) throws Exception {
		log.info("*****Begin delete() *****");
		listCtBANT.remove(index);
		reset_ctbant();
		log.info("*****End delete() *****");
	}
	
	public void reset_ctbant(){
		
		dienbienbenh = "";
		ylenh = "";
		// bao.ttc: ko can set lai cac tham so duoi de tranh null exception
		// ngay = "";
		// bacSiMaSo_hidden = null;
		// bacSiMa = "";
	}
	
	// Ham ghi nhan
	//Xu ly cho nut ghi nhan 
	public String ghiNhanAjax() throws Exception {
		log.info("***Starting ghinhan, ma BANT = " + baNgoaiTru.getBantMa());
		
		
		baNgoaiTru.setBantThamkham(thamkham);
		
		//
		if (tuNgay != null && !tuNgay.equals("")){
			SimpleDateFormat df = new SimpleDateFormat(Utils.FORMAT_DATE);
			Date dTN = df.parse(tuNgay);
			baNgoaiTru.setBantDttungay(dTN);
		}
		if (denNgay != null && !denNgay.equals("")){
			SimpleDateFormat df = new SimpleDateFormat(Utils.FORMAT_DATE);
			Date dDN = df.parse(denNgay);
			baNgoaiTru.setBantDtdenngay(dDN);
		}
		
		// 20110321 bao.ttc: Luu lai Ma BA Ngoai tru de in phieu
		String maBANT = "";
		baNgoaiTru.setBantBenhnhan(thamkham.getTiepdonMa().getBenhnhanMa());
		baNgoaiTru.setBantBankham(thamkham.getThamkhamBankham());
		if (thamkham.getTiepdonMa().getDoituongMa().getDmdoituongMa().equalsIgnoreCase("BH")) {
			baNgoaiTru.setBantSothebh(thamkham.getTiepdonMa().getTiepdonSothebh());
		}
		maBANT = BenhAnNgoaiTruDelegate.getInstance().capNhatBenhAnNgoaiTru(baNgoaiTru, baNgoaiTru.getBantMa(), listCtBANT);
		if (maBANT != null && !maBANT.equals("")){
			baNgoaiTru.setBantMa(maBANT);
		}
		// END -- 20110321 bao.ttc: Luu lai Ma BA Ngoai tru de in phieu
		
//		if (baNgoaiTru.getBantMa() == null){
//			BenhAnNgoaiTruDelegate.getInstance().create(baNgoaiTru);
//			
//		}else{
//			BenhAnNgoaiTruDelegate.getInstance().edit(baNgoaiTru);
//		}
		FacesMessages.instance().add(IConstantsRes.SUCCESS);
		log.info("***Finished ghinhan **");
		return "/B1_Tiepdon/B121_10_Lapbangoaitru";
	}
	
	//Xu ly cho nut quay lai
	public String quayLai()  throws Exception {
		log.info("*****Begin quayLai() *****");
		goToLapBANgoaiTru = null;
		log.info("*****End quayLai() *****");
		return "ghinhan";
	}
	
	public void lapBAmoi() {
		baNgoaiTru = new BenhAnNgoaiTru();
		listCtBANT = new ArrayList<CtBenhAnNgoaiTru>();
		reset_ctbant();
		
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
	
	public String thuchienAction(){
		XuatReport();
		return "B160_Chonmenuxuattaptin";
	}
	
	public void XuatReport() {
		reportTypeTD="benhanvaovien";
		log.info("Vao Method XuatReport benhanvaovien");
		String baocao1=null;
		String pathTemplate=null;
		try {
			
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/benhanngoaitru.jrxml";
			String sub0Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/benhanngoaitru_sub1.jrxml";
			String sub1Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/benhanngoaitru_sub2.jrxml";
			String sub2Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/benhanngoaitru_sub3.jrxml";
			log.info("da thay file templete 5" + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			JasperReport sub0Report =JasperCompileManager.compileReport(sub0Template);
			JasperReport sub1Report =JasperCompileManager.compileReport(sub1Template);
			JasperReport sub2Report =JasperCompileManager.compileReport(sub2Template);
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put("sub1", sub0Report);
			params.put("sub2", sub1Report);
			params.put("sub3", sub2Report);
			
			BenhAnNgoaiTru baNgoaiTruTemp = BenhAnNgoaiTruDelegate.getInstance().find(baNgoaiTru.getBantMa());
			if (baNgoaiTruTemp != null){
				params.put("SOVAOVIEN", baNgoaiTruTemp.getBantSovaovien());
			}
			
			params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("benhvien", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("sodt", thamkham.getThamkhamBankham(true).getDtdmbankhamTen());
			
			params.put("HoTen", benhNhan.getBenhnhanHoten());
			SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);
			log.info("****NGAYSINH: "+benhNhan.getBenhnhanNgaysinh());
			log.info("****NAM SINH: "+benhNhan.getBenhnhanNamsinh());
			if (benhNhan.getBenhnhanNgaysinh() != null)
				params.put("NgaySinh", sdf.format(benhNhan.getBenhnhanNgaysinh()));
			else if(benhNhan.getBenhnhanNamsinh()!=null)
				params.put("NgaySinh",  benhNhan.getBenhnhanNamsinh());
			log.info("****NGAYSINH: "+benhNhan.getBenhnhanNgaysinh());
			log.info("****NAM SINH: "+benhNhan.getBenhnhanNamsinh());
			params.put("Tuoi", benhNhan.getBenhnhanTuoi());
			
			params.put("DonViTuoi",  benhNhan.getBenhnhanDonvituoi()==null?new Integer(1):new Integer(benhNhan.getBenhnhanDonvituoi()) );
			params.put("Gioi", benhNhan.getDmgtMaso(true).getDmgtTen());
			
			DieuTriUtilDelegate dele = DieuTriUtilDelegate.getInstance();
			
			DmDanToc danToc = (DmDanToc)dele.findByMa(benhNhan.getDantocMa(true).getDmdantocMa(), "DmDanToc", "dmdantocMa");
		
			if (danToc != null ){
				params.put("DanToc", danToc.getDmdantocTen());
				params.put("MaDanToc", danToc.getDmdantocMa());
			}else{
				params.put("DanToc", "");
				params.put("MaDanToc", "");
			}
			
			String sDiaChi="";
			if(thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(true).getDmxaTen() !=null)
				sDiaChi += thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(true).getDmxaTen()+",";
			
			if (benhNhan.getBenhnhanDiachi() != null) {
				params.put("DiaChi", benhNhan.getBenhnhanDiachi() + " "+sDiaChi.replace(",", ""));
			} else {
				params.put("DiaChi", "" + sDiaChi.replace(",", ""));
			}
			
			DmHuyen huyen = (DmHuyen)dele.findByMa(benhNhan.getHuyenMa(true).getDmhuyenMa(), "DmHuyen", "dmhuyenMa");
			if (huyen != null){
				params.put("Huyen", huyen.getDmhuyenTen());
				params.put("MaHuyen", huyen.getDmhuyenMa());
			}else{
				params.put("Huyen", "");
				params.put("MaHuyen", "");
			}
			
			DmTinh tinh = (DmTinh)dele.findByMa(benhNhan.getTinhMa(true).getDmtinhMa(), "DmTinh", "dmtinhMa");
			if (tinh != null){
				params.put("Tinh", tinh.getDmtinhTen());
				params.put("MaTinh", tinh.getDmtinhMa());
			}else{
				params.put("Tinh", "");
				params.put("MaTinh", "");
			}
			
			DmQuocGia quocGia = (DmQuocGia)dele.findByMa(benhNhan.getQuocgiaMa(true).getDmquocgiaMa(), "DmQuocGia", "dmquocgiaMa");
			
			
			if (quocGia!= null && !"VNA".equals(quocGia.getDmquocgiaMa())){
				params.put("NgoaiKieu", quocGia.getDmquocgiaTen());
				params.put("MaNgoaiKieu", quocGia.getDmquocgiaMa());
			}else{
				params.put("NgoaiKieu", "");
				params.put("MaNgoaiKieu","");
			}
			
			
			DmNgheNghiep ngheNghiep = (DmNgheNghiep)dele.findByMa(benhNhan.getBenhnhanNghe(true).getDmnghenghiepMa(), "DmNgheNghiep", "dmnghenghiepMa");
			
			if (ngheNghiep != null){
				params.put("NgheNghiep", ngheNghiep.getDmnghenghiepTen());															
				params.put("MaNgheNghiep", ngheNghiep.getDmnghenghiepMa());
			}else{
				params.put("NgheNghiep", "");															
				params.put("MaNgheNghiep", "");
				
			}
			
			String doiTuongMa = thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa();
			if (doiTuongMa != null && doiTuongMa.equals("BH")){
				params.put("DoiTuong_BHYT", "X");
				params.put("DoiTuong_ThuPhi", "");
				params.put("DoiTuong_Mien", ""); 
				params.put("DoiTuong_Khac",  "");
			}else if (doiTuongMa != null && doiTuongMa.equals("TP")){
				params.put("DoiTuong_BHYT", "");
				params.put("DoiTuong_ThuPhi","X");
				params.put("DoiTuong_Mien", ""); 
				params.put("DoiTuong_Khac",  "");
			}else if (doiTuongMa != null && doiTuongMa.equals("MP")){
				params.put("DoiTuong_BHYT", "");
				params.put("DoiTuong_ThuPhi", "");
				params.put("DoiTuong_Mien", "X"); 
				params.put("DoiTuong_Khac",  "");
			}else {
				params.put("DoiTuong_BHYT", "");
				params.put("DoiTuong_ThuPhi", "");
				params.put("DoiTuong_Mien", ""); 
				params.put("DoiTuong_Khac",  "X");
			}
			
			
			params.put("NoiLamViec",  thamkham.getTiepdonMa().getTiepdonMacoquan());
			
		
			params.put("QuaTrinhBenhLy",  baNgoaiTru.getBantQtbenhly());
			params.put("TienSuBenhBanThan",  baNgoaiTru.getBantTiensubenhbt());
			params.put("TienSuBenhGiaDinh",  baNgoaiTru.getBantTiensubenhgd());
			params.put("ToanThan",  baNgoaiTru.getBantToanthan());
			params.put("CacBoPhan",  baNgoaiTru.getBantCacbophan());
			params.put("XetNghiem",  baNgoaiTru.getBantXetnghiem());
			params.put("DaXuLy",  baNgoaiTru.getBantDaxuly());
			params.put("TuNgay",  baNgoaiTru.getBantDttungay());
			params.put("DenNgay",  baNgoaiTru.getBantDtdenngay());
			
			params.put("HuyetApMin",  thamkham.getTiepdonMa(true).getTiepdonHamin()); //Double
			params.put("HuyetApMax",  thamkham.getTiepdonMa(true).getTiepdonHamax()); //Double
			
			params.put("Mach",  thamkham.getTiepdonMa(true).getTiepdonMach()); //Double
			params.put("NhietDo",  thamkham.getTiepdonMa(true).getTiepdonNhietdo()); //Double
			params.put("NhipTho",  thamkham.getTiepdonMa(true).getTiepdonNhiptho()); //Double
			params.put("CANNANG",  thamkham.getTiepdonMa(true).getTiepdonTrluong()); //Double
			
//			params.put("ThoiHanBaoHiem", 
			
			
			if (thamkham.getTiepdonMa().getTiepdonSothebh() != null	&& !thamkham.getTiepdonMa().getTiepdonSothebh().equals("")){
				String maBV = thamkham.getTiepdonMa(true).getKcbbhytMa(true).getDmbenhvienMa();
				if (maBV == null){
					maBV = "";
				}
				//params.put("SoTheBHYT", thamkham.getTiepdonMa().getKhoibhytMa(true).getDtdmkhoibhytMa()+ "-"+ thamkham.getTiepdonMa().getTiepdonSothebh()+ "-"+  maBV.replace(".", "-"));
				params.put("SoTheBHYT",  thamkham.getTiepdonMa().getTiepdonSothebh()+ "-"+  maBV.replace(".", "-"));
				
				log.info("SoTheBHYT:"+params.get("SoTheBHYT"));
			}
			else{
				params.put("SoTheBHYT","");
			}
			
			
			Date giaTri2 = thamkham.getTiepdonMa(true).getTiepdonGiatri2();
			if (giaTri2 != null){
				params.put("BHYTGIATRIDEN", giaTri2);
			}
			
			if (thamkham.getTiepdonMa().getTiepdonBaotin() != null) {
				params.put("NguoiBaoTin", thamkham.getTiepdonMa().getTiepdonBaotin()); 
			} else {
				params.put("NguoiBaoTin", ""); 
			}
			
			params.put("NgayGioVaoVien", thamkham.getTiepdonMa(true).getTiepdonNgaygio());
			
			String diengiaitt  = null;
			if (diengiaitt == null){
				diengiaitt = "";
			}
			DmBenhIcd benhtuyentruoc = (DmBenhIcd)dele.findByMa(thamkham.getTiepdonMa(true).getTiepdonMachdoanbd(true).getDmbenhicdMa(), "DmBenhIcd", "dmbenhicdMa");
			log.info("****benhtuyentruoc:"+benhtuyentruoc );
			if (benhtuyentruoc != null){
				
				
				params.put("ChanDoanTuyenTruoc", benhtuyentruoc.getDmbenhicdMa() + " - " + benhtuyentruoc.getDmbenhicdTen() + " " + diengiaitt );
				log.info("*****ChanDoanTuyenTruoc: "+benhtuyentruoc.getDmbenhicdMa() + " - " + benhtuyentruoc.getDmbenhicdTen() + " " + diengiaitt);
			}else{
				if (diengiaitt.equals("")){
					diengiaitt = "";
				}
				params.put("ChanDoanTuyenTruoc", diengiaitt);
			}
			
			params.put("MaTiepDon", thamkham.getTiepdonMa(true).getTiepdonMa());
			
			params.put("DONVITRIENKHAI", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			
			params.put("KHOAKHAMBENH", thamkham.getThamkhamBankham(true).getDmkhoaMaso().getDmkhoaTen());
			
			
			
			if(baNgoaiTru.getBantQtbenhlidbls()!=null)
				params.put("QTBLDBLS",baNgoaiTru.getBantQtbenhlidbls());
			//if(baNgoaiTru.getBant!=null)
//			if(thamkham.getTiepdonMa(true).getTiepdonLydovaov()!=null)
//				params.put("LIDOVAOVIEN",thamkham.getTiepdonMa(true).getTiepdonLydovaov());
			if(baNgoaiTru.getBantLydovaovien()!=null)
				params.put("LIDOVAOVIEN",baNgoaiTru.getBantLydovaovien());
			
			if(thamkham.getThamkhamKetqua(true).getDmkqdtTen()!=null)
				params.put("KETQUADT",thamkham.getThamkhamKetqua(true).getDmkqdtTen());
			
			params.put("MA_BENH_AN_NGOAI_TRU", baNgoaiTru.getBantMa());
			
			log.info("%%%%%%%%%%%%%%%%%%%%%baNgoaiTru.getBantMa():"+baNgoaiTru.getBantMa());
			
			int tongsoluong=0;
			if(baNgoaiTru.getBantPpdieutri()!=null)
				params.put("PPDT",baNgoaiTru.getBantPpdieutri());
			if(baNgoaiTru.getBantPpdieutri()!=null)
				params.put("TTNBRV",baNgoaiTru.getBantTinhtrangnguoibenh());
			if(baNgoaiTru.getBantHuongdt()!=null)
				params.put("HDTCCDTT",baNgoaiTru.getBantHuongdt());
			if(baNgoaiTru.getBantSlxquang()!=null)
			{
				params.put("XQUANG",baNgoaiTru.getBantSlxquang());
				tongsoluong=baNgoaiTru.getBantSlxquang();
			}
			if(baNgoaiTru.getBantSlscanner()!=null)
			{
				params.put("SCANNER",baNgoaiTru.getBantSlscanner());
				tongsoluong+=baNgoaiTru.getBantSlscanner();
			}
			if(baNgoaiTru.getBantSlsieuam()!=null)
			{
				params.put("SIEUAM",baNgoaiTru.getBantSlsieuam());
				tongsoluong+=baNgoaiTru.getBantSlsieuam();
			}
			if(baNgoaiTru.getBantSlkhac()!=null)
			{
				params.put("HSKHAC",baNgoaiTru.getBantSlkhac());
				tongsoluong+=baNgoaiTru.getBantSlkhac();
			}
			if(baNgoaiTru.getBantSlxetnghiem()!=null)
			{
				params.put("XETNGHIEM",baNgoaiTru.getBantSlxetnghiem());
				tongsoluong+=baNgoaiTru.getBantSlxetnghiem();
			}
			params.put("TONGHOSO",tongsoluong);
			if(baNgoaiTru.getBantGiaiphaubenh()!=null)
			params.put("GIAIPHAUBENH",baNgoaiTru.getBantGiaiphaubenh());
			
			DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
			String maChanDoan = "";
			String tenChanDoan ="";
			
			if (thamkham.getBenhicd10() != null && thamkham.getBenhicd10().getDmbenhicdMaso() !=null){
				DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
				}
				
			}
			
			String sBenhChinh = maChanDoan + " " +  tenChanDoan;
			params.put("BENHCHINH", sBenhChinh );
			
			String sBenhPhu = "";
			//tiep tuc them benh phu.
			if (thamkham.getBenhicd10phu1() != null && thamkham.getBenhicd10phu1().getDmbenhicdMaso() !=null){
				DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu1().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					sBenhPhu += maChanDoan + " " +  tenChanDoan;
				}
				
			}
			if (thamkham.getBenhicd10phu2() != null && thamkham.getBenhicd10phu2().getDmbenhicdMaso() !=null){
				DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu2().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					sBenhPhu += " , "+ maChanDoan + " " +  tenChanDoan;
				}
				
			}
			if (thamkham.getBenhicd10phu3() != null && thamkham.getBenhicd10phu3().getDmbenhicdMaso() !=null){
				DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu3().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					sBenhPhu += " , "+ maChanDoan + " " +  tenChanDoan;
				}
				
			}
			if (thamkham.getBenhicd10phu4() != null && thamkham.getBenhicd10phu4().getDmbenhicdMaso() !=null){
				DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu4().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					sBenhPhu += " , "+ maChanDoan + " " +  tenChanDoan;
				}
				
			}
			if (thamkham.getBenhicd10phu5() != null && thamkham.getBenhicd10phu5().getDmbenhicdMaso() !=null){
				DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu5().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					sBenhPhu += " , "+ maChanDoan + " " +  tenChanDoan;
				}
				
			}
			params.put("BENHPHU", sBenhPhu );
			
			if(!sBenhPhu.equals("")) 
				sBenhPhu = ", " + sBenhPhu;
			String sChanDoanBD = sBenhChinh + sBenhPhu;
			            
			params.put("CHANDOANBD",sChanDoanBD);
			
			
			if (thamkham.getTiepdonMa(true).getTiepdonDonvigoi() != null){
				params.put("yte","X");
			}else{
				params.put("tuden","X");
			}
			//params.put("NGUOINHANHS",);
			//params.put("BASIDT,);
			//params.put("BENHCHINH",);
			//params.put("BENHPHU",);
			//params.put("KETQUADT",baNgoaiTru.getBantKqcanlamsang());
			
			
			log.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			    log.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintTD =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintTD,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"tiepdon/", "pdf","benhanvaovien");
			    reportPathTD=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathTD);
			    index+= 1;
			    log.info("Index :" + index);
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Thoat Method XuatReport");
	}
	
	private int index = 0;
	//***********************************************************************************
	public String getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(String thoiGian) {
		this.thoiGian = thoiGian;
	}

	private String tuNgay;
	private String denNgay;
	
	private void setOtherValue() {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
		if (thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh() != null	&& !thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().equals("")) {
			ngaySinh = formatter.format(thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().getTime());
		}
		else if(thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNamsinh()!=null)
			ngaySinh = thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNamsinh();
			
		if (thamkham.getThamkhamNgaygio() != null
				&& !thamkham.getThamkhamNgaygio().equals("")) {
			thoiGian = formatter.format(thamkham.getThamkhamNgaygio().getTime());
			ngay = formatter.format(thamkham.getThamkhamNgaygio().getTime());
			gioThamKham = Utils.getGioPhut(thamkham.getThamkhamNgaygio()) ;
		}
		
		if (baNgoaiTru.getBantDttungay() != null
				&& !baNgoaiTru.getBantDttungay().equals("")) {
			tuNgay = formatter
					.format(baNgoaiTru.getBantDttungay().getTime());
		}
		if (baNgoaiTru.getBantDtdenngay() != null
				&& !baNgoaiTru.getBantDtdenngay().equals("")) {
			denNgay = formatter
					.format(baNgoaiTru.getBantDtdenngay().getTime());
		}
	}

	private void setinfor(ClsKham nhapctSelected) {
		// SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
		DtDmClsBangGia dmkythuat = nhapctSelected.getClskhamMahang();
		//dmkythuat.setDtdmkythuatDiengiai(diengiai);
		nhapctSelected.setClskhamMahang(dmkythuat);
//		if (thamkham.getTiepdonMa() != null) {
//			nhapctSelected.setTiepdonMa(thamkham.getTiepdonMa());
//		}
		if (thamkham.getThamkhamNgaygio() != null
				&& !"".equals(thamkham.getThamkhamNgaygio())) {
			nhapctSelected.setClskhamNgaygio(thamkham.getThamkhamNgaygio());
		}

//		if (thamkham.getThamkhamBankham() != null) {
//			nhapctSelected.setClskhamBankham(thamkham.getThamkhamBankham());
//		}
	}


	// 20110209 bao.ttc: lay thong tin ThuocPhongKham theo Ngay de dua vao Y lenh
	public void get_thuoc_info(){
		
		ylenh = "";
		if(ngay != null && !ngay.equals("")){
			
			if(Utils.getDBDateWithHour(0, ngay, true) == null){
				log.info("### Lap BA Ngoai tru -- get_thuoc_info(): ERROR Parse NGAY !! ");
				return;
			}
			
			Date tuNgay = Utils.getDBDateWithHour(0, ngay, true).getTime();
			Date denNgay = Utils.getDBDateWithHour(23, ngay, false).getTime();
			String thuocBK = "";
			String thuocBH = "";
			String thuocVe = "";
			
			List<ThuocPhongKham> listThuocPhongKham;
			ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
			listThuocPhongKham = tpkDelegate.findByThamKhamVaNgay(thamkham.getThamkhamMa(), tuNgay, denNgay);
			
			if(listThuocPhongKham != null && listThuocPhongKham.size() >0){
				
				for (ThuocPhongKham thuoc : listThuocPhongKham){
					if(thuoc.getThuocphongkhamLoai().equals("3")){ // Thuoc Quay BV
						thuocBH += "- " + thuoc.getThuocphongkhamMathuoc(true).getDmthuocTen();
						if(thuoc.getThuocphongkhamSoluong() != null)
							thuocBH += ", " + thuoc.getThuocphongkhamSoluong().toString();
						thuocBH += " " + thuoc.getThuocphongkhamMathuoc(true).getDmdonvitinhMaso(true).getDmdonvitinhTen();
						if(thuoc.getThuocphongkhamChidan() != null && !thuoc.getThuocphongkhamChidan().equals(""))
							thuocBH += ", " + thuoc.getThuocphongkhamChidan();
						thuocBH += "\n";
					} else if(thuoc.getThuocphongkhamLoai().equals("1")){ // Thuoc Ban kham
						thuocBK += "- " + thuoc.getThuocphongkhamMathuoc(true).getDmthuocTen();
						if(thuoc.getThuocphongkhamSoluong() != null)
							thuocBK += ", " + thuoc.getThuocphongkhamSoluong().toString();
						thuocBK += " " + thuoc.getThuocphongkhamMathuoc(true).getDmdonvitinhMaso(true).getDmdonvitinhTen();
						if(thuoc.getThuocphongkhamChidan() != null && !thuoc.getThuocphongkhamChidan().equals(""))
							thuocBK += ", " + thuoc.getThuocphongkhamChidan();
						thuocBK += "\n";
					} else if(thuoc.getThuocphongkhamLoai().equals("2")){ // Thuoc Toa ve
						thuocVe += "- " + thuoc.getThuocphongkhamMathuoc(true).getDmthuocTen();
						if(thuoc.getThuocphongkhamSoluong() != null)
							thuocVe += ", " + thuoc.getThuocphongkhamSoluong().toString();
						thuocVe += " " + thuoc.getThuocphongkhamMathuoc(true).getDmdonvitinhMaso(true).getDmdonvitinhTen();
						if(thuoc.getThuocphongkhamChidan() != null && !thuoc.getThuocphongkhamChidan().equals(""))
							thuocVe += ", " + thuoc.getThuocphongkhamChidan();
						thuocVe += "\n";
					}
				} // END FOR
				
				if(!thuocBH.equals(""))
					ylenh += "+ " + IConstantsRes.THUOC_QUAY_BV + "\n" + thuocBH;
				if(!thuocBK.equals(""))
					ylenh += "\n+ " + IConstantsRes.THUOC_BAN_KHAM + "\n" + thuocBK;
				if(!thuocVe.equals(""))
					ylenh += "\n+ " + IConstantsRes.THUOC_TOA_VE + "\n" + thuocVe;
				
			} else {
				ylenh = IConstantsRes.KHONG_DUNG_THUOC;
			}
			
		}
		
		//log.info("### Y lenh: " + ylenh);
	}

///////////////////////	
///////////////////
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

	public static String getFORMAT_DATE() {
		return FORMAT_DATE;
	}

	public static void setFORMAT_DATE(String format_date) {
		FORMAT_DATE = format_date;
	}

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

	

	

	

	public String getResultHidden() {
		return resultHidden;
	}

	public void setResultHidden(String resultHidden) {
		this.resultHidden = resultHidden;
	}

	
	public String getGioThamKham() {
		return gioThamKham;
	}

	public void setGioThamKham(String gioThamKham) {
		this.gioThamKham = gioThamKham;
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

	public BenhAnNgoaiTru getBaNgoaiTru() {
		return baNgoaiTru;
	}

	public void setBaNgoaiTru(BenhAnNgoaiTru baNgoaiTru) {
		this.baNgoaiTru = baNgoaiTru;
	}

	public String getTuNgay() {
		return tuNgay;
	}

	public void setTuNgay(String tuNgay) {
		this.tuNgay = tuNgay;
	}

	public String getDenNgay() {
		return denNgay;
	}

	public void setDenNgay(String denNgay) {
		this.denNgay = denNgay;
	}

	public String getNgay() {
		return ngay;
	}

	public void setNgay(String ngay) {
		this.ngay = ngay;
	}

	public String getDienbienbenh() {
		return dienbienbenh;
	}

	public void setDienbienbenh(String dienbienbenh) {
		this.dienbienbenh = dienbienbenh;
	}

	public String getYlenh() {
		return ylenh;
	}

	public void setYlenh(String ylenh) {
		this.ylenh = ylenh;
	}

	public String getBacSiMa() {
		return bacSiMa;
	}

	public void setBacSiMa(String bacSiMa) {
		this.bacSiMa = bacSiMa;
	}

	public Integer getBacSiMaSo_hidden() {
		return bacSiMaSo_hidden;
	}

	public void setBacSiMaSo_hidden(Integer bacSiMaSo_hidden) {
		this.bacSiMaSo_hidden = bacSiMaSo_hidden;
	}

	public List<CtBenhAnNgoaiTru> getListCtBANT() {
		return listCtBANT;
	}

	public void setListCtBANT(List<CtBenhAnNgoaiTru> listCtBANT) {
		this.listCtBANT = listCtBANT;
	}

	public CtBenhAnNgoaiTru getCtbantSelection() {
		return ctbantSelection;
	}

	public void setCtbantSelection(CtBenhAnNgoaiTru ctbantSelection) {
		this.ctbantSelection = ctbantSelection;
	}

	

}



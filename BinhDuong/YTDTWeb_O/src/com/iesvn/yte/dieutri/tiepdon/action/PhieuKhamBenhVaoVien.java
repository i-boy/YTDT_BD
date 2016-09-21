package com.iesvn.yte.dieutri.tiepdon.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
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
import org.jboss.seam.faces.FacesMessages;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.PhieuKbVaoVienDelegate;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.PhieuKbVaoVien;
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
@Name("B120_Phieukhambenhvaovien")
@Synchronized(timeout = 6000000)
public class PhieuKhamBenhVaoVien implements Serializable {

	private static String FORMAT_DATE = "dd/MM/yyyy";
	private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";

	private static final long serialVersionUID = 10L;
	private int stt;
	private String sMaPhieuKVV;
	private String ngaySinh="";
	private String gioi="";
	private PhieuKbVaoVien objPhieuKBVaoVien= new PhieuKbVaoVien(); 
	private String sShowDel="";
	private String sShowPrint="";
	private String ylenh;
	private String ngay;
	
	private static Logger log = Logger.getLogger(ThamKhamAction.class);
	
	@In(required = false)
	@Out(required = false)
	private String maBanKhamOut;
	
	@In(required = false)
	@Out(required = false)
	private String maTiepDonOut;
	
	@In(required = false)
	@Out(required = false)
	private String goToPhieuKhamBenhVaoVien;
	
	@Out(required = false)
	private String bacSiKCB;

	private BenhNhan benhNhan;

	private ThamKham thamkham;

	public void resetValue() {

	}
	
	@Begin(nested = true)
	@Factory("goToPhieuKhamBenhVaoVien")
	public void init() throws Exception {
		log.info("***Starting init ***");		
		try{
			ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
			
			// 20110404 bao.ttc: Khoi tao null, neu tim thay Thamkham thi 
			thamkham = null;
			benhNhan = new BenhNhan();
			thamkham = tkDelegate.findByBanKhamVaMaTiepDon(maBanKhamOut,maTiepDonOut);
			
			if (thamkham != null){
				
				benhNhan = thamkham.getTiepdonMa().getBenhnhanMa();
				objPhieuKBVaoVien=PhieuKbVaoVienDelegate.getInstance().findByMaThamKham(thamkham.getThamkhamMa());
				
				if (objPhieuKBVaoVien != null){
					sShowDel = "true";
					sShowPrint = "true";
					sMaPhieuKVV = objPhieuKBVaoVien.getPkbvvMa();
				} else {
					objPhieuKBVaoVien = new PhieuKbVaoVien();
					sMaPhieuKVV="";
					sShowDel="false";
					sShowPrint="false";
					
					
					// 20110413 bao.ttc: lay thong tin CLS cua thamkham
					List<ClsKham> listCLS;
					String cacxetnghiem = "";
					
					ClsKhamDelegate clskhamDele = ClsKhamDelegate.getInstance();
					listCLS = clskhamDele.findByBanKhamVaMaTiepDon(thamkham.getThamkhamBankham(true).getDtdmbankhamMa(), thamkham.getTiepdonMa(true).getTiepdonMa());
					cacxetnghiem = "";
					
					if(listCLS != null && listCLS.size() >0){
						for (ClsKham cls : listCLS){
							cacxetnghiem += cls.getClskhamMahang(true).getDtdmclsbgDiengiai() + "; ";
						}
					} else {
						cacxetnghiem = "Kh\u00F4ng c\u00F3"; // chu "Khong co"
					}
					objPhieuKBVaoVien.setPkbvvTtketqualamsang(cacxetnghiem);
					
					// 20110427 bao.ttc: load Chan doan de user co the chinh sua
					DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
					String maChanDoan = "";
					String tenChanDoan = "";	
					String chanDoan = "";
					
					if (thamkham.getBenhicd10() != null && thamkham.getBenhicd10().getDmbenhicdMaso() !=null){
						DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
						if (benh != null){
							maChanDoan = benh.getDmbenhicdMa();
							tenChanDoan = benh.getDmbenhicdTen();
						}
						
					}
					
					chanDoan = maChanDoan + " " +  tenChanDoan;
					
					objPhieuKBVaoVien.setPkbvvLydovaovien(chanDoan);
					//if(thamkham.getTiepdonMa(true).getTiepdonLydovaov() != null){
					//	objPhieuKBVaoVien.setPkbvvLydovaovien(thamkham.getTiepdonMa(true).getTiepdonLydovaov());
					//}
					
					//tiep tuc them benh phu.
					if (thamkham.getBenhicd10phu1() != null && thamkham.getBenhicd10phu1().getDmbenhicdMaso() !=null){
						DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu1().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
						if (benh != null){
							maChanDoan = benh.getDmbenhicdMa();
							tenChanDoan = benh.getDmbenhicdTen();
							chanDoan += " , "+ maChanDoan + " " +  tenChanDoan;
						}
						
					}
					if (thamkham.getBenhicd10phu2() != null && thamkham.getBenhicd10phu2().getDmbenhicdMaso() !=null){
						DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu2().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
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
					if (thamkham.getBenhicd10phu3() != null && thamkham.getBenhicd10phu3().getDmbenhicdMaso() !=null){
						DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu3().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
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
					if (thamkham.getBenhicd10phu4() != null && thamkham.getBenhicd10phu4().getDmbenhicdMaso() !=null){
						DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu4().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
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
					if (thamkham.getBenhicd10phu5() != null && thamkham.getBenhicd10phu5().getDmbenhicdMaso() !=null){
						DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu5().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
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
					
					objPhieuKBVaoVien.setPkbvvCdvaovien(chanDoan);
					// END -- 20110427 bao.ttc: load Chan doan de user co the chinh sua
					
					SimpleDateFormat sdfformat = new SimpleDateFormat(FORMAT_DATE);
					if (thamkham.getThamkhamNgaygio() != null ) {
						ngay = sdfformat.format(thamkham.getThamkhamNgaygio());
					}
					get_thuoc_info();
					// 20110528 bao.ttc: dua thong tin xu ly thuoc vao phan da xu ly
					objPhieuKBVaoVien.setPkbvvDaxuly(ylenh);
					
					
				}
				
				if (benhNhan.getDmgtMaso() != null){
					if ("1".equals(benhNhan.getDmgtMaso().getDmgtMa())){
						gioi = "r1";  //1 : Name
					} else {
						gioi = "r2";
					}
				} else {
					gioi = null;
				}
				
				setOtherValue();
				
			} else {
				FacesMessages.instance().add(IConstantsRes.KHONG_TIM_THAY_THAMKHAM);
				log.info(" ### Khong tim thay THAMKHAM nao ung voi Ma Ban kham: " + maBanKhamOut + " ### va Ma Tiep don: " + maTiepDonOut);
			}
			
			// 20110404 bao.ttc: comment vi ko can thiet
			//destroyService();
		}catch(Exception e){
			log.info("***init Exception = " + e);
		}
		log.info("***Finished init ***");		
	}

	@End 
	public void end(){
		
	}
		
	//***********************************************************************************
	// Ham ghi nhan
	//Xu ly cho nut ghi nhan 
	public String ghiNhan() throws Exception {
		log.info("*****Begin ghiNhan() *****");
		
		try {
			PhieuKbVaoVienDelegate PhieuKhamDtNgoaiTruDel=PhieuKbVaoVienDelegate.getInstance();
			
			objPhieuKBVaoVien.setPkbvvThamkham(thamkham);
			sMaPhieuKVV=PhieuKhamDtNgoaiTruDel.capNhatPhieuKbVaoVien(objPhieuKBVaoVien, sMaPhieuKVV);
			FacesMessages.instance().add(IConstantsRes.RPPKBVV_INSERT_SUCCESS,sMaPhieuKVV);
			log.info("*****CAP NHAT THANH CONG  PhieuKhamDtNgoaiTruAction *****");
			sShowDel="true";
			sShowPrint="true";
		} catch (Exception e) {
			FacesMessages.instance().add(IConstantsRes.FAIL);
			log.error("*************loi***********" + e.toString());
			return null;
		}
		log.info("*****End ghiNhan() *****");
		return null;
	}
	
	public void huyPhieu()
	{
		log.info("***** start  huyPhieu() *****");
		if(sMaPhieuKVV==null||sMaPhieuKVV.equals(""))
		{
			return;
		}
		PhieuKbVaoVienDelegate PhieuKhamDtNgoaiTruDel=PhieuKbVaoVienDelegate.getInstance();
		PhieuKbVaoVien obj=PhieuKhamDtNgoaiTruDel.find(sMaPhieuKVV);
		if(obj==null)
			return;
		
		PhieuKhamDtNgoaiTruDel.remove(obj);
		FacesMessages.instance().add(IConstantsRes.RPPKBVV_DELETE_SUCCESS,sMaPhieuKVV);
		log.info("***** XOA THANH CONG *****");
		objPhieuKBVaoVien=new PhieuKbVaoVien();
		sMaPhieuKVV="";
		sShowDel="false";
		sShowPrint="false";
		log.info("***** end  huyPhieu() *****");
	}
	
	//Xu ly cho nut quay lai
	public String quayLai()  throws Exception {
		log.info("*****Begin quayLai() *****");
		goToPhieuKhamBenhVaoVien = null;
		log.info("*****End quayLai() *****");
		return "ghinhan";
	}
	
	// 20110209 bao.ttc: lay thong tin ThuocPhongKham theo Ngay de dua vao Y lenh
	public void get_thuoc_info(){
		
		ylenh = "";
		if(ngay != null && !ngay.equals("")){
			
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
						thuocBH += thuoc.getThuocphongkhamMathuoc(true).getDmthuocTen();
						if(thuoc.getThuocphongkhamSoluong() != null)
							thuocBH += ", " + thuoc.getThuocphongkhamSoluong().toString();
						thuocBH += " " + thuoc.getThuocphongkhamMathuoc(true).getDmdonvitinhMaso(true).getDmdonvitinhTen();
						thuocBH += "; ";
					} else if(thuoc.getThuocphongkhamLoai().equals("1")){ // Thuoc Ban kham
						thuocBK += thuoc.getThuocphongkhamMathuoc(true).getDmthuocTen();
						if(thuoc.getThuocphongkhamSoluong() != null)
							thuocBK += ", " + thuoc.getThuocphongkhamSoluong().toString();
						thuocBK += " " + thuoc.getThuocphongkhamMathuoc(true).getDmdonvitinhMaso(true).getDmdonvitinhTen();
						thuocBK += "; ";
					} else if(thuoc.getThuocphongkhamLoai().equals("2")){ // Thuoc Toa ve
						thuocVe += thuoc.getThuocphongkhamMathuoc(true).getDmthuocTen();
						if(thuoc.getThuocphongkhamSoluong() != null)
							thuocVe += ", " + thuoc.getThuocphongkhamSoluong().toString();
						thuocVe += " " + thuoc.getThuocphongkhamMathuoc(true).getDmdonvitinhMaso(true).getDmdonvitinhTen();
						thuocVe += "; ";
					}
				} // END FOR
				
				if(!thuocBH.equals(""))
					ylenh += "+ " + IConstantsRes.THUOC_QUAY_BV + " " + thuocBH;
				if(!thuocBK.equals(""))
					ylenh += "\n+ " + IConstantsRes.THUOC_BAN_KHAM + " " + thuocBK;
				if(!thuocVe.equals(""))
					ylenh += "\n+ " + IConstantsRes.THUOC_TOA_VE + " " + thuocVe;
				
			} else {
				ylenh = IConstantsRes.KHONG_DUNG_THUOC;
			}
			
		}
		
		//log.info("### Y lenh: " + ylenh);
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
		reportTypeTD="phieukhambenhvaovien";
		log.info("Vao Method XuatReport phieukhamchuyenkhoa");
		String baocao1=null;
		String pathTemplate=null;
		try {
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieukhambenhvaovien.jrxml";
			log.info("Da thay file template: " + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("benhvien", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("sodt", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			
			params.put("HoTen", benhNhan.getBenhnhanHoten());
			
			if (benhNhan.getBenhnhanNgaysinh() != null) {
				params.put("NgaySinh", sdf.format(benhNhan.getBenhnhanNgaysinh()));
			} else {
				params.put("NgaySinh", benhNhan.getBenhnhanNamsinh());
			}
			
			
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
			
			params.put("DiaChi", benhNhan.getBenhnhanDiachi());
			
			// 20110307 bao.ttc: them thong tin Xa
			if (benhNhan.getXaMa() != null && !benhNhan.getXaMa(true).getDmxaTen().equals("")){
				params.put("Xa", benhNhan.getXaMa(true).getDmxaTen());
			} else{
				params.put("Xa", "");
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
			log.info("nghe nghiep :"+ngheNghiep);
			
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
			
			
			params.put("NoiLamViec",  thamkham.getTiepdonMa(true).getTiepdonMacoquan());
			params.put("KHOA",  thamkham.getThamkhamBankham(true).getDmkhoaMaso(true).getDmkhoaTen());
			params.put("VAOKHOA",  thamkham.getTiepdonMa(true).getTiepdonChkhoa(true).getDmkhoaTen());
			
			
			Date giaTri2 = thamkham.getTiepdonMa(true).getTiepdonGiatri2();
			if (giaTri2 != null){
				params.put("BHYTGIATRIDEN", giaTri2);
			}
			params.put("Lydovaovien",  objPhieuKBVaoVien.getPkbvvLydovaovien());
			params.put("QuaTrinhBenhLy",  objPhieuKBVaoVien.getPkbvvQtbenhli());
			params.put("TienSuBenhBanThan",  objPhieuKBVaoVien.getPkbvvTiensubenhbt());
			params.put("TienSuBenhGiaDinh",  objPhieuKBVaoVien.getPkbvvTiensubenhgd());
			params.put("ToanThan",  objPhieuKBVaoVien.getPkbvvToanthan());
			params.put("CacBoPhan",  objPhieuKBVaoVien.getPkbvvCacbophan());
			params.put("DaXuLy",  objPhieuKBVaoVien.getPkbvvDaxuly());
			params.put("TTLQLAMSANG",  objPhieuKBVaoVien.getPkbvvTtketqualamsang());
			params.put("CHUY",  objPhieuKBVaoVien.getPkbvvChuy());
			params.put("BUONG", thamkham.getThamkhamBankham(true).getDtdmbankhamTen());
			params.put("HuyetApMin",  thamkham.getTiepdonMa(true).getTiepdonHamin()); //Double
			params.put("HuyetApMax",  thamkham.getTiepdonMa(true).getTiepdonHamax()); //Double
			
			params.put("Mach",  thamkham.getTiepdonMa(true).getTiepdonMach()); //Double
			params.put("NhietDo",  thamkham.getTiepdonMa(true).getTiepdonNhietdo()); //Double
			params.put("NhipTho",  thamkham.getTiepdonMa(true).getTiepdonNhiptho()); //Double
			params.put("CANNANG",  thamkham.getTiepdonMa(true).getTiepdonTrluong()); //Double
			
//			params.put("ThoiHanBaoHiem", 
//			if (thamkham.getTiepdonMa(true).getTiepdonSothebh() != null && !thamkham.getTiepdonMa(true).getTiepdonSothebh().equals("")){
//				if (thamkham.getTiepdonMa(true).getKhoibhytMa(true).getDtdmkhoibhytMa() != null ){
//					params.put("SoTheBHYT", thamkham.getTiepdonMa(true).getTiepdonSothebh() + " - " + thamkham.getTiepdonMa(true).getKhoibhytMa(true).getDtdmkhoibhytMa());
//				}else{
//					params.put("SoTheBHYT", thamkham.getTiepdonMa(true).getTiepdonSothebh());
//				}
//				
//			}else{
//				params.put("SoTheBHYT", "");
//			}
			
			if (thamkham.getTiepdonMa().getTiepdonSothebh() != null	&& !thamkham.getTiepdonMa().getTiepdonSothebh().equals("")){
				String maBV = thamkham.getTiepdonMa(true).getKcbbhytMa(true).getDmbenhvienMa();
				if (maBV == null){
					maBV = "";
				}
				params.put("SoTheBHYT", thamkham.getTiepdonMa().getTiepdonSothebh()+ "-"+  maBV.replace(".", "-"));
				//params.put("SoTheBHYT", thamkham.getTiepdonMa().getKhoibhytMa(true).getDtdmkhoibhytMa()+ "-"+ thamkham.getTiepdonMa().getTiepdonSothebh()+ "-"+  maBV.replace(".", "-"));
				
				log.info("SoTheBHYT:"+params.get("SoTheBHYT"));
			}
			else{
				params.put("SoTheBHYT","");
			}
			
			params.put("NguoiBaoTin", thamkham.getTiepdonMa(true).getTiepdonBaotin()); 
			
			params.put("NgayGioVaoVien", thamkham.getTiepdonMa(true).getTiepdonNgaygio());
			
			if (thamkham.getTiepdonMa(true).getTiepdonMachdoanbd(true).getDmbenhicdMa() == null) {
				params.put("ChanDoanTuyenTruoc", "");
			} else {
				params.put("ChanDoanTuyenTruoc", thamkham.getTiepdonMa(true).getTiepdonMachdoanbd(true).getDmbenhicdMa() + " - " + thamkham.getTiepdonMa(true).getTiepdonMachdoanbd(true).getDmbenhicdTen());
			}
			
			// 20110427 bao.ttc: 
//			if (thamkham.getBenhicd10(true).getDmbenhicdMa() == null) {
//				params.put("ChanDoanVaoVien", "");
//			} else {
//				params.put("ChanDoanVaoVien", thamkham.getBenhicd10(true).getDmbenhicdMa() + " - " + thamkham.getBenhicd10(true).getDmbenhicdTen());
//			}
			params.put("ChanDoanVaoVien", objPhieuKBVaoVien.getPkbvvCdvaovien());
			
			String bacsikhambenh = "";
			if(thamkham.getThamkhamBacsi() != null){
				bacsikhambenh += thamkham.getThamkhamBacsi().getDtdmnhanvienTen();
			}
			params.put("bacsikhambenh", bacsikhambenh);
			
			params.put("MaTiepDon", thamkham.getTiepdonMa(true).getTiepdonMa());
			
			// 20110822 bao.ttc: In So vao vien
			if (thamkham.getTiepdonMa(true).getTiepdonMa() != null && !thamkham.getTiepdonMa(true).getTiepdonMa().equals("")) {
				
				HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
				Hsba hsba = hsbaDelegate.findByTiepDonMa(thamkham.getTiepdonMa(true).getTiepdonMa());
				if (hsba != null) {
					params.put("SoVaoVien", hsba.getHsbaSovaovien());
				} else {
					params.put("SoVaoVien", "");
				}
				
			}
			
			params.put("DONVITRIENKHAI", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
															
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintTD,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"tiepdon/", "pdf","phieukhambenhvaovien");
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

	private void setOtherValue() {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
		if (thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh() != null && !thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().equals("")) {
			ngaySinh = formatter.format(thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().getTime());
		}
		else if(thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNamsinh()!=null)
			ngaySinh = thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNamsinh();
	}

	public void displayInfor() throws Exception {
		try {
			
		if(sMaPhieuKVV==null|| sMaPhieuKVV.equals(""))
			return;
		
		PhieuKbVaoVienDelegate PhieuKhamDtNgoaiTruDel=PhieuKbVaoVienDelegate.getInstance();
		objPhieuKBVaoVien=new PhieuKbVaoVien();
		List<PhieuKbVaoVien> ls=PhieuKhamDtNgoaiTruDel.findByPhieuKbVaoVien(getMaPhieuKhamBenhVaoVien());
		
		if(ls==null||ls.size()==0)
		{
			FacesMessages.instance().add(IConstantsRes.RPPKBVV_NOT_EXIST);
			sMaPhieuKVV="";
			sShowDel="false";
			sShowPrint="false";
			return;
		}
		sShowDel="true";
		sShowPrint="true";
		objPhieuKBVaoVien=ls.get(0);
		sMaPhieuKVV=objPhieuKBVaoVien.getPkbvvMa();
		//setDaKCT(giayChuyenVien.getGcvbhytKct());
		//setDtNgoaiTru(giayChuyenVien.getGcvbhytDadtngoaitru());
		//setDtNoiTru(giayChuyenVien.getGcvbhytDadtnoitru());
		log.info("*****sMaPhieuKVV: "+sMaPhieuKVV)		;
		
		} catch (Exception e) {
			System.out.println("error on function displayInfor" + e);
		}
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
		log.info("************************* destroy PhieuKbVaoVien");
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

	public String getGioi()
	{
		return gioi;
	}

	public void setGioi(String gioi)
	{
		this.gioi = gioi;
	}


	public String getBacSiKCB() {
		return bacSiKCB;
	}

	public void setBacSiKCB(String bacSiKCB) {
		this.bacSiKCB = bacSiKCB;
	}

	public String getGoToPhieuKhamBenhVaoVien() {
		return goToPhieuKhamBenhVaoVien;
	}

	public void setGoToPhieuKhamBenhVaoVien(String str) {
		this.goToPhieuKhamBenhVaoVien = str;
	}

	public String getMaPhieuKhamBenhVaoVien()
	{
		return sMaPhieuKVV;
	}

	public void setMaPhieuKhamBenhVaoVien(String maGiayChuyenVien)
	{
		sMaPhieuKVV = maGiayChuyenVien;
	}
	
	public String getShowDel()
	{
		return sShowDel;
	}

	public void setShowDel(String showDel)
	{
		sShowDel = showDel;
	}

	public String getShowPrint()
	{
		return sShowPrint;
	}

	public void setShowPrint(String showPrint)
	{
		sShowPrint = showPrint;
	}

	public PhieuKbVaoVien getPhieuKbVaoVien()
	{
		return objPhieuKBVaoVien;
	}

	public void setPhieuKbVaoVien(PhieuKbVaoVien objPhieuKhamDTNgoaiTru)
	{
		this.objPhieuKBVaoVien = objPhieuKhamDTNgoaiTru;
	}

	

}



package com.iesvn.yte.dieutri.duocpham.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
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

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
import com.iesvn.yte.dieutri.delegate.CtTraKhoDelegate;
import com.iesvn.yte.dieutri.delegate.PhieuTraKhoDelegate;
import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
import com.iesvn.yte.dieutri.entity.CtTraKho;
import com.iesvn.yte.dieutri.entity.PhieuTraKho;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
import com.iesvn.yte.entity.DmDonViTinh;
import com.iesvn.yte.util.IConstantsRes;
import com.sun.org.apache.commons.beanutils.BeanUtils;

@Scope(CONVERSATION)
@Name("B4114_Phieutrahangkhoaphong")
@Synchronized(timeout = 6000000)
public class PhieuTraHangKhoaPhongAction implements Serializable{

	private static final long serialVersionUID = 6247559999639195297L;
	private static Logger log = Logger.getLogger(PhieuTraHangKhoaPhongAction.class);
	private PhieuTraKho phieuTraKho;
	private String ngayXuat;
	private CtTraKhoExt ctTraKhoExt;
	@DataModel
	private List<CtTraKhoExt> listCtTraKhoExt;
	@DataModelSelection
	private CtTraKhoExt selectedCtTraKhoExt;
	private boolean isUpdate = false;
	private String nofound;
	private String nosuccess;
	private String noinphieu;
	
	private String resultReportFileName;
	private String resultReportName;	
	private String loaiFileXuat;
	@Out(required = false)
	private String isReport = "false";
	
	@In(required = false)
	@Out(required = false)
	private String ten4112_4114;
	
	String dmKhoXuat = "";

	@In(required = false)
	@Out(required = false)
	private String tenChuongTrinh;
	
	@Begin(join = true)
	public String init(String loaikho,String kho, String formid) {
		log.info("---init");
		resetValue();
		if ("KhoNoiTru".equals(loaikho)){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoNoiTru;
		}else if ("BHYT".equals(loaikho)){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoBHYT;
		}else if ("KC".equals(loaikho)){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoChinh;
		}
		
		dmKhoXuat = kho;
		ten4112_4114 = formid; // 4112 or 4114;
		return "QuanLyKhoChinh_NhapKhoChinh_CacKhoaPhongTraLaiHangTuTuTruc";
		
	}
	
	@End
	public void endConversation(){
		
	}
	
	
	
	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		PhieuTraHangKhoaPhongAction.log = log;
	}

	public boolean isUpdate() {
		return isUpdate;
	}

	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public String getDmKhoXuat() {
		return dmKhoXuat;
	}

	public void setDmKhoXuat(String dmKhoXuat) {
		this.dmKhoXuat = dmKhoXuat;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getTen4112_4114() {
		return ten4112_4114;
	}

	public void setTen4112_4114(String ten4112_4114) {
		this.ten4112_4114 = ten4112_4114;
	}

	public String getIsReport() {
		return isReport;
	}

	public void setIsReport(String isReport) {
		this.isReport = isReport;
	}

	public String getResultReportFileName() {
		return resultReportFileName;
	}

	public void setResultReportFileName(String resultReportFileName) {
		this.resultReportFileName = resultReportFileName;
	}

	public String getResultReportName() {
		return resultReportName;
	}

	public void setResultReportName(String resultReportName) {
		this.resultReportName = resultReportName;
	}

	public String getLoaiFileXuat() {
		return loaiFileXuat;
	}

	public void setLoaiFileXuat(String loaiFileXuat) {
		this.loaiFileXuat = loaiFileXuat;
	}

	public String getNofound() {
		return nofound;
	}

	public void setNofound(String nofound) {
		this.nofound = nofound;
	}

	public String getNosuccess() {
		return nosuccess;
	}

	public void setNosuccess(String nosuccess) {
		this.nosuccess = nosuccess;
	}

	public String getNoinphieu() {
		return noinphieu;
	}

	public void setNoinphieu(String noinphieu) {
		this.noinphieu = noinphieu;
	}


	
	private void resetValue() {
		log.info("---resetValue");
		ngayXuat = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
		phieuTraKho = new PhieuTraKho();
		SetInforUtil.setInforIfNullForPhieuTraKho(phieuTraKho);
		listCtTraKhoExt = new ArrayList<CtTraKhoExt>();
		ctTraKhoExt =  new CtTraKhoExt();
		isUpdate = false;
		nofound = "false";
		nosuccess = "false";
		noinphieu = "false";
	}
	
//	@Factory("listCtTraKhoExt") 
//	public void createListCtTraKhoExt(){
//		log.info("---createListCtTraKhoExt");
//		listCtTraKhoExt = new ArrayList<CtTraKhoExt>();
//	}
	
	public void enter() throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException{
		log.info("---enter");
		updateTonKho(ctTraKhoExt);
		if (isUpdate) {
			isUpdate = false;
		}else{
			listCtTraKhoExt.add(ctTraKhoExt);
		}
		ctTraKhoExt = new CtTraKhoExt();
	}
	
	public void delete(){
		log.info("---delete");
		listCtTraKhoExt.remove(selectedCtTraKhoExt);
		ctTraKhoExt = new CtTraKhoExt();
	}
	
	public void select(){
		log.info("---delete");
		ctTraKhoExt = selectedCtTraKhoExt;
		isUpdate = true;
	}
	
	private void updateTonKho(CtTraKhoExt _ctTraKhoExt) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
		Integer matonkho = _ctTraKhoExt.getTonKho().getTonkhoMa();
		if (matonkho==null) return;
		TonKhoDelegate tkDelegate = TonKhoDelegate.getInstance();
		TonKho tk = tkDelegate.find(matonkho);
		CtTraKho cttk = _ctTraKhoExt.getCtTraKho();
		TonKho tkTra = duplicateTk(tk);
		if (tkTra != null) {
			tkTra.setTonkhoXuat(cttk.getCttrakhoSoluong());
			tkTra.setTonkhoNhap(null);
			tkTra.setTonkhoTra(null);
		}
		
		TonKho tkNhan = duplicateTk(tk);
		if (tkNhan != null) {
			tkNhan.setTonkhoNhap(cttk.getCttrakhoSoluong());
			tkNhan.setTonkhoXuat(null);
			tkNhan.setTonkhoTra(null);
		}
		_ctTraKhoExt.setTonKhoTra(tkTra);
		_ctTraKhoExt.setTonKhoNhan(tkNhan);
		cttk.setDmnguonkinhphiMaso(tk.getDmnguonkinhphiMaso());
		cttk.setDmquocgiaMaso(tk.getDmquocgiaMaso());
		cttk.setDmthuocMaso(tk.getDmthuocMaso());
		cttk.setDmnhasanxuatMaso(tk.getDmnhasanxuatMaso());
		cttk.setDmnctMaso(tk.getDmnctMaso());
		cttk.setCttrakhoDongia(tk.getTonkhoDongia());
		cttk.setCttrakhoDongiaban(tk.getTonkhoDongiaban());
		cttk.setCttrakhoLo(tk.getTonkhoLo());
		cttk.setCttrakhoMalk(tk.getTonkhoMalienket());
		cttk.setCttrakhoNamhandung(tk.getTonkhoNamhandung());
		cttk.setCttrakhoNamnhap(tk.getTonkhoNamnhap());
		cttk.setCttrakhoNgaygiocn(new Date());
		cttk.setCttrakhoNgayhandung(tk.getTonkhoNgayhandung());
		cttk.setCttrakhoThanghandung(tk.getTonkhoThanghandung());
		cttk.setPhieutrakhoMa(phieuTraKho);
		cttk.setCttrakhoNgaygiocn(new Date());
	} 
	
	private TonKho duplicateTk(TonKho tk) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
		if (tk != null) {
			TonKho temp = (TonKho) BeanUtils.cloneBean(tk);
			temp.setTonkhoMa(null);
			temp.setTonkhoNgaygiocn(new Date());
//			TonKho temp = new TonKho();
//			temp.setTonkhoTon(tk.getTonkhoTon());
//			temp.setDmkhoaMaso(tk.getDmkhoaMaso());
//			temp.setDmnguonkinhphiMaso(tk.getDmnguonkinhphiMaso());
//			temp.setDmquocgiaMaso(tk.getDmquocgiaMaso());
//			temp.setDmthuocMaso(tk.getDmthuocMaso());
//			temp.setDmnhasanxuatMaso(tk.getDmnhasanxuatMaso());
//			temp.setDmnctMaso(tk.getDmnctMaso());
//			temp.setDtdmnhanvienCn(tk.getDtdmnhanvienCn());
//			temp.setTonkhoCapphat(tk.getTonkhoCapphat());
//			temp.setTonkhoDongia(tk.getTonkhoDongia());
//			temp.setTonkhoDongiaban(tk.getTonkhoDongiaban());
//			temp.setTonkhoHienthi(tk.getTonkhoHienthi());
//			temp.setTonkhoLo(tk.getTonkhoLo());
//			temp.setTonkhoMalienket(tk.getTonkhoMalienket());
//			temp.setTonkhoMucthue(tk.getTonkhoMucthue());
//			temp.setTonkhoNamhandung(tk.getTonkhoNamhandung());
//			temp.setTonkhoNamnhap(tk.getTonkhoNamnhap());
//			temp.setTonkhoNgaygiocn(new Date());
//			temp.setTonkhoNgayhandung(tk.getTonkhoNgayhandung());
//			temp.setTonkhoPhanbiet(tk.getTonkhoPhanbiet());
//			temp.setTonkhoSodangky(tk.getTonkhoSodangky());
//			temp.setTonkhoThanghandung(tk.getTonkhoThanghandung());
			return temp;
		}
		return null;
	}
	
	
	
	public void displayPhieuTraKho() throws Exception{
		log.info("---displayPhieuTraKho");
		ctTraKhoExt = new CtTraKhoExt();
		String maPhieuTra = phieuTraKho.getPhieutrakhoMa();
		if (maPhieuTra !=null && !maPhieuTra.equals("")){
			PhieuTraKhoDelegate delegate = PhieuTraKhoDelegate.getInstance();
			PhieuTraKho ptk_tmp = delegate.findByPhieutrakhoMa(maPhieuTra);
			if (ptk_tmp != null){
				phieuTraKho = ptk_tmp;
				SetInforUtil.setInforIfNullForPhieuTraKho(phieuTraKho);
				ngayXuat = new SimpleDateFormat("dd/MM/yyyy").format(phieuTraKho.getPhieutrakhoNgay());
				CtTraKhoDelegate delegateCt = CtTraKhoDelegate.getInstance();
				for (CtTraKho obj : delegateCt.findByphieutrakhoMa(phieuTraKho.getPhieutrakhoMa())) {
					CtTraKhoExt ct = new CtTraKhoExt();
					ct.setCtTraKho(obj);
					listCtTraKhoExt.add(ct);
				}
			}else{
				nofound = "true";
			}
		}
	}
	
	public void ghiNhan() throws Exception{
		log.info("---ghiNhan");
		if (listCtTraKhoExt.size()>0) {
			List<CtTraKho> list = new ArrayList<CtTraKho>();
			List<TonKho> listTonKhoTra = new ArrayList<TonKho>();
			List<TonKho> listTonKhoNhan = new ArrayList<TonKho>();
			for (CtTraKhoExt obj : listCtTraKhoExt) {
				list.add(obj.getCtTraKho());
				listTonKhoTra.add(obj.getTonKhoTra());
				listTonKhoNhan.add(obj.getTonKhoNhan());
			}
			phieuTraKho.setPhieutrakhoNgaygiocn(new Date());
			if (!ngayXuat.equals("")) {
				phieuTraKho.setPhieutrakhoNgay(new SimpleDateFormat("dd/MM/yyyy").parse(ngayXuat));
			}
			RemoveUtil.removeIfNullForPhieuTraKho(phieuTraKho);
			PhieuTraKhoDelegate delegate = PhieuTraKhoDelegate.getInstance();
			String result = delegate.createPhieuTra(phieuTraKho, list, listTonKhoNhan, listTonKhoTra);
			if (result.equals("")){
				nosuccess = "true";
				FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THATBAI);
			}else{
				phieuTraKho.setPhieutrakhoMa(result);
				SetInforUtil.setInforIfNullForPhieuTraKho(phieuTraKho);
				FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THANHCONG, result);
			}
		}else{
			nosuccess = "true";
			FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_DMT_NULL);
		}
	}
	
	public void nhapMoi() throws Exception{
		log.info("---nhapMoi");
		resetValue();
	}
	public String thuchienAction(){
		XuatReport();
		return "B4160_Chonmenuxuattaptin";
	}
	int index= 0;
	
	/**
	 * xuat report 
	 */
	
	@Out(required=false)
	@In(required=false)
	private String reportPathKC=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeKC=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintKC = null;
	
	public void XuatReport() {
		reportTypeKC="D03_phieuxuatkho";
		log.info("Vao Method XuatReport D03_phieuxuatkho");
		String baocao1=null;
		Date currentDate = new Date();

		if (!phieuTraKho.getPhieutrakhoMa().equals("")) {
			
		try {
			PhieuTraKhoDelegate ptkWS = PhieuTraKhoDelegate.getInstance();
			PhieuTraKho px = ptkWS.find(phieuTraKho.getPhieutrakhoMa());

			
			String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/phieutrakho_01.jrxml";
			log.info(String.format("-----pathTemplate: %s", pathTemplate));
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			log.info(String.format("-----tenSo: %s", params.get("tenSo")));
			params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			log.info(String.format("-----tenDonVi: %s", params.get("tenDonVi")));
			params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			
			params.put("DONVI", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			
			log.info(String.format("-----dienThoaiDonVi: %s", params.get("dienThoaiDonVi")));
			Calendar cal = Calendar.getInstance();
			cal.setTime(px.getPhieutrakhoNgay());
			if (cal != null) {
				log.info(String.format("-----ngay lap: %s", cal.getTime()));
				params.put("ngayHt", "" + cal.get(Calendar.DAY_OF_MONTH));
				params.put("thangHt", "" + (cal.get(Calendar.MONTH) + 1));
				params.put("namHt", "" + cal.get(Calendar.YEAR));
			} else {
				log.info("-----ngay lap is null");
				params.put("ngayHt", "");
				params.put("thangHt", "");
				params.put("namHt", "");
			}

			SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
			String ngayGioHt = df.format(Calendar.getInstance().getTime());
			log.info(String.format("-----ngay gio hien tai: %s", ngayGioHt));
			params.put("gioHt", ngayGioHt);

			params.put("pxMa", px.getPhieutrakhoMa());

			if (px.getDmkhoaNhan() != null) {
				params.put("khoaNhan", px.getDmkhoaNhan().getDmkhoaTen());
			} else {
				params.put("khoaNhan", "");
			}
			log.info(String.format("-----khoaNhan: %s", params.get("khoaNhan")));

			if (px.getDmkhoaTra() != null) {
				params.put("khoaXuat", px.getDmkhoaTra().getDmkhoaTen());
			} else {
				params.put("khoaXuat", "");
			}
			log.info(String.format("-----khoaXuat: %s", params.get("khoaXuat")));

			params.put("tongTien", px.getPhieutrakhoThanhtien());
			log.info(String.format("-----tongTien: %s", params.get("tongTien")));
			params.put("nhanvienCn", px.getDtdmnhanvienCn().getDtdmnhanvienMa());
			
			
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintKC,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"duocpham/", "pdf","D03_phieuxuatkho");
			    reportPathKC=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathKC);
			    index+=1;
			    log.info("Index :" + index);
			    if(conn != null) conn.close();
		}catch (Exception e) {
			log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
		}
		log.info("Thoat Method XuatReport");
	}
	
//	public String inPhieu(String loaiFile) throws Exception{
//		log.info(String.format("-----inPhieu()-----"));
//		log.info(String.format("-----loai file: %s", loaiFile));
//		Date currentDate = new Date();
//
//		if (!phieuTraKho.getPhieutrakhoMa().equals("")) {
//			try {
//
//				PhieuTraKhoDelegate ptkWS = PhieuTraKhoDelegate.getInstance();
//				PhieuTraKho px = ptkWS.find(phieuTraKho.getPhieutrakhoMa());
//
//				String pathTemplate = "D02_phieutrakho";
//				log.info(String.format("-----pathTemplate: %s", pathTemplate));
//				Map<String, Object> params = new HashMap<String, Object>();
//				params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
//				log.info(String.format("-----tenSo: %s", params.get("tenSo")));
//				params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
//				log.info(String.format("-----tenDonVi: %s", params.get("tenDonVi")));
//				params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
//				log.info(String.format("-----dienThoaiDonVi: %s", params.get("dienThoaiDonVi")));
//				Calendar cal = Calendar.getInstance();
//				cal.setTime(px.getPhieutrakhoNgay());
//				if (cal != null) {
//					log.info(String.format("-----ngay lap: %s", cal.getTime()));
//					params.put("ngayHt", "" + cal.get(Calendar.DAY_OF_MONTH));
//					params.put("thangHt", "" + (cal.get(Calendar.MONTH) + 1));
//					params.put("namHt", "" + cal.get(Calendar.YEAR));
//				} else {
//					log.info("-----ngay lap is null");
//					params.put("ngayHt", "");
//					params.put("thangHt", "");
//					params.put("namHt", "");
//				}
//
//				SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
//				String ngayGioHt = df.format(Calendar.getInstance().getTime());
//				log.info(String.format("-----ngay gio hien tai: %s", ngayGioHt));
//				params.put("gioHt", ngayGioHt);
//
//				params.put("pxMa", px.getPhieutrakhoMa());
//
//				if (px.getDmkhoaNhan() != null) {
//					params.put("khoaNhan", px.getDmkhoaNhan().getDmkhoaTen());
//				} else {
//					params.put("khoaNhan", "");
//				}
//				log.info(String.format("-----khoaNhan: %s", params.get("khoaNhan")));
//
//				if (px.getDmkhoaTra() != null) {
//					params.put("khoaXuat", px.getDmkhoaTra().getDmkhoaTen());
//				} else {
//					params.put("khoaXuat", "");
//				}
//				log.info(String.format("-----khoaXuat: %s", params.get("khoaXuat")));
//
//				params.put("tongTien", px.getPhieutrakhoThanhtien());
//				log.info(String.format("-----tongTien: %s", params.get("tongTien")));
//				params.put("nhanvienCn", px.getDtdmnhanvienCn().getDtdmnhanvienMa());
//
//				String fileNameExt = String.valueOf(currentDate.getTime()) ;
//				String fileName = com.iesvn.yte.ReportUtil.xuatReport(IConstantsRes.PATH_BASE,
//															IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI, 
//															IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI, "duocpham/",
//															pathTemplate , params, loaiFile, fileNameExt);
//				if(loaiFile.equalsIgnoreCase("HTML")) {
//	            	resultReportName = fileName;
//	            } else {
//	            	resultReportFileName = fileName;
//	            }
//	            setIsReport("true");
//	            noinphieu = "true";
//			} catch (Exception ex) {
//				log.info(String.format("-----Error: %s", ex.toString()));
//			}
//		}
//		return "B4114_Chonmenuxuattaptin";
//	}
	
	public String troVe(){
		try {
			log.info("***** troVe()");
			return "B4114_Phieutrahangkhoaphong";
		} 		
		catch (Exception e) {
			log.info("***** End exception = " + e);    	
		} 
		return null;
	}

	public class CtTraKhoExt{
		private CtTraKho ctTraKho;
		private TonKho tonKho;
		private TonKho tonKhoTra;
		private TonKho tonKhoNhan;
		private Double thanhTien;
		public Double getThanhTien() {
			return thanhTien;
		}
		public void setThanhTien(Double thanhTien) {
			this.thanhTien = thanhTien;
		}
		public CtTraKhoExt(){
			ctTraKho = new CtTraKho();
			tonKho = new TonKho();
			SetInforUtil.setInforIfNullForTonKho(tonKho);
			if (tonKho.getDmthuocMaso().getDmdonvitinhMaso() == null) {
				tonKho.getDmthuocMaso().setDmdonvitinhMaso(new DmDonViTinh());
			}
			thanhTien = new Double(0);
			
		}
		public CtTraKho getCtTraKho() {
			return ctTraKho;
		}
		public void setCtTraKho(CtTraKho ctTraKho) {
			this.ctTraKho = ctTraKho;
		}
		public TonKho getTonKho() {
			return tonKho;
		}
		public void setTonKho(TonKho tonKho) {
			this.tonKho = tonKho;
		}
		public TonKho getTonKhoTra() {
			return tonKhoTra;
		}
		public void setTonKhoTra(TonKho tonKhoTra) {
			this.tonKhoTra = tonKhoTra;
		}
		public TonKho getTonKhoNhan() {
			return tonKhoNhan;
		}
		public void setTonKhoNhan(TonKho tonKhoNhan) {
			this.tonKhoNhan = tonKhoNhan;
		}
		
		
	}

	

	public String getNgayXuat() {
		return ngayXuat;
	}

	public void setNgayXuat(String ngayXuat) {
		this.ngayXuat = ngayXuat;
	}

	public CtTraKhoExt getCtTraKhoExt() {
		return ctTraKhoExt;
	}

	public void setCtTraKhoExt(CtTraKhoExt ctTraKhoExt) {
		this.ctTraKhoExt = ctTraKhoExt;
	}

	public List<CtTraKhoExt> getListCtTraKhoExt() {
		return listCtTraKhoExt;
	}

	public void setListCtTraKhoExt(List<CtTraKhoExt> listCtTraKhoExt) {
		this.listCtTraKhoExt = listCtTraKhoExt;
	}

	public CtTraKhoExt getSelectedCtTraKhoExt() {
		return selectedCtTraKhoExt;
	}

	public void setSelectedCtTraKhoExt(CtTraKhoExt selectedCtTraKhoExt) {
		this.selectedCtTraKhoExt = selectedCtTraKhoExt;
	}

	public PhieuTraKho getPhieuTraKho() {
		return phieuTraKho;
	}

	public void setPhieuTraKho(PhieuTraKho phieuTraKho) {
		this.phieuTraKho = phieuTraKho;
	}

	
	
}

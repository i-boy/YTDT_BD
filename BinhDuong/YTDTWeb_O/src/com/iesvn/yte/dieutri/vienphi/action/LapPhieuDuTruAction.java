/**
 * 
 * @author Mai Van Manh
 */

package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

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
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.AuthorizationException;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.CtPhieuDtDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DmKhoaDelegate;
import com.iesvn.yte.dieutri.delegate.DmLoaiPhieuDelegate;
import com.iesvn.yte.dieutri.delegate.DmLoaiThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocNoiTruDelegate;
import com.iesvn.yte.dieutri.delegate.YteLogDelegate;
import com.iesvn.yte.dieutri.entity.CtPhieuDt;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.PhieuDuTru;
import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
import com.iesvn.yte.dieutri.entity.YteLog;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmLoaiPhieu;
import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.entity.DmNguonChuongTrinh;
import com.iesvn.yte.entity.DmNguonKinhPhi;
import com.iesvn.yte.entity.DmTang;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;


@Scope(CONVERSATION)
@Name("B3125_Lapphieudutru")
@Synchronized(timeout = 6000000)
public class LapPhieuDuTruAction implements Serializable {
	private static String FORMAT_DATE = "dd/MM/yyyy";
	private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH:mm:ss";
	private String msgFail = "";
	private String msgSuccess = "";
	
	
	private String position = com.iesvn.yte.util.IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/";
	
	private String resultHidden ="";
	private static final long serialVersionUID = 10L;
	private static Logger log = Logger.getLogger(LapPhieuDuTruAction.class);
	private PhieuDuTru phieuDuTru;
	private boolean updateNhapct = false;
	private String ngayXuat;
	private String ngayXuatDen;
	
	// Bien dung cho luoi du lieu
	private String maHang;
	private String tenHang;
	private Integer quyCach;
	private String donVi;
	private String tonKho;
	private Double xinLinh;
	private Double donGia;
	
	private Integer nguonctMaso;
	private String nguonctMa;
	
	private Integer nguonkpMaso;
	private String nguonkpMa;
	
	private Integer maSoHang;
	
	private String tongTien;

	private String maFinish;
	
	private String ngayhientai = "";
	
	private String reportFinished="";
	private String reportFileNameHid = "";
	
	private String resultReportFileName;
	private String resultReportName;
	private String loaiFileXuat;
	@Out(required = false)
	private String isReport = "false";
	
	@DataModel
	private List<CtPhieuDt> listCtkq = new ArrayList<CtPhieuDt>();
	
	@DataModelSelection
	@Out(required = false)
	private CtPhieuDt nhapctSelected;
	
	private YteLogDelegate yteLogDele = YteLogDelegate.getInstance();
    private YteLog yteLog;
    private String listDataLog;
     
    @In(required = false)
 	@Out(required = false)
 	Identity identity;
	
	private DtDmNhanVien nvCn;
	
	private String hienThiGhiNhan="true";
	private String hienThiHuyPhieu = "";
	private String hienThiInPhieu="";
	private String gioLapPhieuTu = "";
	private String gioLapPhieuDen = "";
	private String dmDoiTuongTen = "";
	
	private List<ThuocNoiTru> listTNT = new ArrayList<ThuocNoiTru>();
	
	//Tho add
	private String dmLoaiTen="";
	private DmLoaiThuocDelegate dmLoaiThuocDelegate;
	private List<SelectItem> listDmLoaiThuocs = new ArrayList<SelectItem>();
	HashMap<String, DmLoaiThuoc> hmLoaiThuoc = new HashMap<String, DmLoaiThuoc>();
	private DmLoaiPhieuDelegate dmLoaiPhieuDelegate;
	HashMap<String,DmLoaiPhieu> hmDmLoaiPhieu = new HashMap<String,DmLoaiPhieu>();
	private String dmLoaiPhieuMa ="";
	private List<SelectItem> listDmLoaiPhieus = new ArrayList<SelectItem>();
	private List<SelectItem> listDmDoiTuongs = new ArrayList<SelectItem>();
	HashMap<String, DmDoiTuong> hmDoiTuong = new HashMap<String, DmDoiTuong>();
	
	private DmKhoaDelegate dmKhoaDel = DmKhoaDelegate.getInstance();
	private List<SelectItem> listDmTangs = new ArrayList<SelectItem>();
	private List<SelectItem> listDmKhoaNTs = new ArrayList<SelectItem>();
	private List<DmKhoa> listDmKhoaNTAll = new ArrayList<DmKhoa>();
	private HashMap<String, DmKhoa> hmDmKhoaNTAll = new HashMap<String, DmKhoa>();
	private String dmkhoaTen ="";
	private String dmkhoaMa = "";
	
	@Restrict("#{s:hasRole('NV_KhoaNoiTru') or s:hasRole('QT_KhoaNoiTru')}")
	@Begin(join = true)
	public String init() throws Exception {
		//log.info("***Starting init ***");
		resetValue();
		phieuDuTru = new PhieuDuTru();
		SetInforUtil.setInforIfNullForPhieuDuTru(phieuDuTru);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter;	    
        formatter = new SimpleDateFormat(FORMAT_DATE); 
        ngayXuat = formatter.format(cal.getTime());
        ngayXuatDen = formatter.format(cal.getTime());
        maFinish = "";
        SimpleDateFormat formatter1;	    
        formatter1 = new SimpleDateFormat(FORMAT_DATE); 
        
		ngayhientai = formatter1.format(new Date());
		resultHidden="";
		reportFinished= "";
		reportFileNameHid= "";
				
		DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
		setNvCn(nvDelegate.findByNd(identity.getUsername()));
		if (getNvCn() == null) {
			setNvCn(new DtDmNhanVien());
		}
		//log.info("nvCn"+nvCn);
		phieuDuTru.setDtdmnhanvienCn(nvCn);
		listDmLoaiThuocs.clear();
		hmLoaiThuoc.clear();
		refreshDmLoaiThuoc();
		refreshDmDoiTuong();
		hmDmKhoaNTAll.clear();
		refreshDmKhoaNT();	
		phieuDuTru.setDmtangMaso(new DmTang());
		//log.info("***End init ***");
		return "VienPhiTaiKhoa_SoLieuCLSPhauThuat_LapPhieuDuTru";
	}
	
	@End
	public void endFunct(){
		
	}
	
	public void huyPhieuDT(){
		CtPhieuDtDelegate ctPhieuDTDelegate = CtPhieuDtDelegate.getInstance();
		String returnKQ = ctPhieuDTDelegate.huyPhieuDuTru_New(phieuDuTru.getPhieudtMa());
		if (returnKQ.equals("DX")){
			FacesMessages.instance().add(IConstantsRes.PHIEU_DU_TRU_DA_XUAT_HANG,phieuDuTru.getPhieudtMa());
			hienThiGhiNhan="";
			hienThiHuyPhieu = "";
			hienThiInPhieu="";
		}else{
			FacesMessages.instance().add(IConstantsRes.HUY_PHIEU_THANH_CONG,phieuDuTru.getPhieudtMa());

//			Luu log he thong
	         yteLog.setForm("B3125_Lapphieudutru");
	         yteLog.setUserId(identity == null ? "NULL" : identity.getUsername());
	         yteLog.setObjectId(phieuDuTru.getPhieudtMa());
	         yteLog.setLogString("Huy phieu du tru: " + phieuDuTru.getPhieudtMa()+
	        		 			" Ngay xuat tu ngay: "+ gioLapPhieuTu + " " + ngayXuat +
	        		 			" Ngay xuat den ngay: "+ gioLapPhieuDen + " " + ngayXuatDen +
	        		 			" Doi tuong: "+ (dmDoiTuongTen == null ? "NULL" : dmDoiTuongTen)+
	        		 			" Loai thuoc: " + phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocMa()+
	        		 			" Loai phieu: "+ phieuDuTru.getPhieudtLoaiPhieu()+
	        		 			" Khoa lap phieu: "+ phieuDuTru.getDmkhoaMaso(true).getDmkhoaMa()+
	        		 			" Buong: "+ phieuDuTru.getDmtangMaso(true).getDmtangMa()+
	        		 			" Kho xuat: "+ phieuDuTru.getPhieudtMakho(true).getDmkhoaMa()+
	        		 			" Nguoi nhap: "+ phieuDuTru.getDtdmnhanvienCn(true).getDtdmnhanvienMa()+
	        		 			" Nguoi lap: "+ phieuDuTru.getDtdmnhanvienLapphieu(true).getDtdmnhanvienMa()+
	        		 			" Nguoi ky:" + phieuDuTru.getDtdmnhanvienBacsiky(true).getDtdmnhanvienMa()+
	        		 			" Thanh tien: "+ tongTien
	         					);
	         yteLog.setDateTime(new Date());
	         yteLog.setListData(listDataLog);
	         
	         yteLogDele.create(yteLog);

			phieuDuTru = new PhieuDuTru();
			SetInforUtil.setInforIfNullForPhieuDuTru(phieuDuTru);
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat formatter;	    
	        formatter = new SimpleDateFormat(FORMAT_DATE); 
	        ngayXuat = formatter.format(cal.getTime());
	        ngayXuatDen = formatter.format(cal.getTime());
	        maFinish = "";
	        SimpleDateFormat formatter1;	    
	        formatter1 = new SimpleDateFormat(FORMAT_DATE); 
	        
			ngayhientai = formatter1.format(new Date());
			resultHidden="";
			reportFinished= "";
			reportFileNameHid= "";
			listCtkq = new ArrayList<CtPhieuDt>();
			
			
			 nguonctMaso = null;
			 nguonctMa ="";
			
			 nguonkpMaso=null;
			 nguonkpMa="";
			 tongTien="";
			
			 hienThiGhiNhan="true";
			 hienThiHuyPhieu = "";
			 hienThiInPhieu="";
		}			
		
	}
	public void resetValue() {
		//log.info("Begin resetValue()");
		//SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
		//ngayXuat = formatter.format(new Date());
		//log.info("Ngay xuat :" + ngayXuat);
		maHang = "";
		tenHang = "";
		quyCach = 0;
		donVi = "";
		tonKho = "";
		xinLinh = new Double(0);
		donGia = new Double(0);
		//tongTien = new Double(0);
		dmLoaiTen ="";
		gioLapPhieuTu ="00:00";
		gioLapPhieuDen ="23:59";
		dmDoiTuongTen ="";
		dmLoaiPhieuMa ="";
		hmDmLoaiPhieu.clear();
		dmkhoaTen = "";
		dmkhoaMa = "";
		//log.info("End resetValue()");
	}
	/**
	 * 
	 * @param List CtPhieuDt
	 */
	public void Caculation(List<CtPhieuDt> ctpdtlist) {
		//log.info("Begining Caculation: " + ctpdtlist);
		Double dTongTien = new Double(0);
		if (ctpdtlist != null ) {
			
			for (CtPhieuDt ctpdt : ctpdtlist) {
				dTongTien += ctpdt.getCtdtDongia().doubleValue() * ctpdt.getCtdtSoluong().doubleValue();
			}
			//log.info("Tong tien dTongTien= " + dTongTien);
		}
		//log.info("End Caculation(): ");
		
		NumberFormat numberFormat = NumberFormat.getInstance();
		tongTien = numberFormat.format(dTongTien);
	}

	//Begin Tho add
	public String getDmkhoaTen() {
		return dmkhoaTen;
	}

	public void setDmkhoaTen(String dmkhoaTen) {
		this.dmkhoaTen = dmkhoaTen;
	}
	
	public String getDmkhoaMa() {
		return dmkhoaMa;
	}

	public void setDmkhoaMa(String dmkhoaMa) {
		this.dmkhoaMa = dmkhoaMa;
	}
	
	public void onblurMaKhoaAction(){
		//log.info("-----BEGIN onblurMaKhoaAction()-----");
		if(dmkhoaMa != null){
			if(hmDmKhoaNTAll != null){
				DmKhoa dmKhoa = new DmKhoa();
				dmKhoa = (DmKhoa)hmDmKhoaNTAll.get(dmkhoaMa.toUpperCase());
				if(dmKhoa != null) {
					phieuDuTru.setDmkhoaMaso(dmKhoa);
					dmkhoaTen = dmKhoa.getDmkhoaTen();
					dmkhoaMa = dmKhoa.getDmkhoaMa().toUpperCase();
				}
			}
			refreshDmTang();
			phieuDuTru.setDmtangMaso(new DmTang());
		}
		//log.info("-----END onblurMaKhoaAction()-----");
	}
	
	public void onblurTenKhoaAction(){
		//log.info("-----BEGIN onblurTenKhoaAction()-----");
		if(dmkhoaTen != null){
			Boolean hasTenKhoa = false;
			java.util.Set set = hmDmKhoaNTAll.entrySet();
		    Iterator i = set.iterator();
		    DmKhoa dmKhoa_Finded = new DmKhoa();
		    while(i.hasNext()){
		    	Map.Entry me = (Map.Entry)i.next();
		    	DmKhoa dmKhoa = (DmKhoa)me.getValue();
		    	if(dmKhoa.getDmkhoaTen() == dmkhoaTen || dmKhoa.getDmkhoaTen().equals(dmkhoaTen)){		    		
		    		hasTenKhoa = true;
		    		dmKhoa_Finded = dmKhoa;
		    		break;
		    	}	    		
		    }
		    if(hasTenKhoa){
		    	phieuDuTru.setDmkhoaMaso(dmKhoa_Finded);
		    	dmkhoaTen = dmKhoa_Finded.getDmkhoaTen();
				dmkhoaMa = dmKhoa_Finded.getDmkhoaMa().toUpperCase();
		    }		    
		    refreshDmTang();
		    phieuDuTru.setDmtangMaso(new DmTang());
		}
		//log.info("-----END onblurTenKhoaAction()-----");
	}
	
	public void refreshDmKhoaNT(){
		dmKhoaDel = DmKhoaDelegate.getInstance();
		listDmKhoaNTAll.clear();
		listDmKhoaNTs.clear();
		listDmKhoaNTAll = dmKhoaDel.getKhoaNT();
		hmDmKhoaNTAll.clear();
		for(DmKhoa o: listDmKhoaNTAll){
			hmDmKhoaNTAll.put(o.getDmkhoaMa().toUpperCase(), o);
		}
		for(DmKhoa each : listDmKhoaNTAll){
			listDmKhoaNTs.add(new SelectItem(each.getDmkhoaTen()));
		}
	}
	
	public void onblurTenTangAction(){
		if(phieuDuTru != null && phieuDuTru.getDmtangMaso(true).getDmtangTen() != null){
			DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
			List<DmTang> lstTangs = dtUtil.findTenLike("DmTang", "dmtangTen", phieuDuTru.getDmtangMaso(true).getDmtangTen());
			if(lstTangs != null && lstTangs.size()>0){
				phieuDuTru.setDmtangMaso(lstTangs.get(0));
			}else{
				phieuDuTru.setDmtangMaso(new DmTang());
			}
		}
	}
	
	public void refreshDmTang(){
		listDmTangs.clear();
		if(phieuDuTru != null && phieuDuTru.getDmkhoaMaso(true).getDmkhoaMaso() != null){
			String khoaMa = phieuDuTru.getDmkhoaMaso(true).getDmkhoaMa();			
			DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
			listDmTangs.add(new SelectItem("Tat ca"));
			//Get tat ca cac tang cua khoa chuyen den, show gia tri default truoc
			List<DmTang> lstDmTangs = dtUtil.findMaLike("DmTang", "dmkhoaMaso.dmkhoaMa", "dmtangChon", khoaMa, true);
			if(lstDmTangs != null && lstDmTangs.size()>0){				
				for(DmTang dmTang:lstDmTangs){					
					listDmTangs.add(new SelectItem(dmTang.getDmtangTen()));
				}
			}
		}
	}
	
	public String getDmLoaiTen() {
		return dmLoaiTen;
	}

	public void setDmLoaiTen(String dmLoaiTen) {
		this.dmLoaiTen = dmLoaiTen;
	}

	public List<SelectItem> getListDmLoaiThuocs() {
		return listDmLoaiThuocs;
	}

	public void setListDmLoaiThuocs(List<SelectItem> listDmLoaiThuocs) {
		this.listDmLoaiThuocs = listDmLoaiThuocs;
	}
	
	public void onblurMaLoaiAction(){
		//log.info("-----BEGIN onblurMaLoaiAction()-----"+phieuDuTru.getDmloaithuocMaso().getDmloaithuocMa());
		String loaihang_ma = phieuDuTru.getDmloaithuocMaso().getDmloaithuocMa();
		DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)hmLoaiThuoc.get(loaihang_ma.toUpperCase());
		if(dmLoaiThuoc != null) {
			setDmLoaiTen(dmLoaiThuoc.getDmloaithuocTen());
		}
		else {
			setDmLoaiTen("");			
		}
		listDmLoaiPhieus.clear();
		refreshDmLoaiPhieu();
		phieuDuTru.setPhieudtLoaiPhieu("");
		dmLoaiPhieuMa ="";
		//log.info("-----END onblurMaLoaiAction()-----");
	}
	
	public void onblurTenLoaiThuocAction(){
		//log.info("-----BEGIN onblurTenLoaiThuocAction()-----");
		Boolean hasTenLoai = false;
		String maLoai = null;
		
		DmLoaiThuoc dmLoaiThuoc_Find = new DmLoaiThuoc();
		java.util.Set set = hmLoaiThuoc.entrySet();
		Iterator i = set.iterator();
		while(i.hasNext()){
		    Map.Entry me = (Map.Entry)i.next();
		    DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)me.getValue();
		    if(dmLoaiThuoc.getDmloaithuocTen() == dmLoaiTen || dmLoaiThuoc.getDmloaithuocTen().equals(dmLoaiTen)){
		    	hasTenLoai = true;
		    	dmLoaiThuoc_Find = dmLoaiThuoc;
		    	break;
		    }	    		
		}	
		if(hasTenLoai){
			phieuDuTru.setDmloaithuocMaso(dmLoaiThuoc_Find);
		}
	    listDmLoaiPhieus.clear();
		refreshDmLoaiPhieu();
		phieuDuTru.setPhieudtLoaiPhieu("");
		dmLoaiPhieuMa ="";
		//log.info("-----END onblurTenLoaiThuocAction()-----");
	}
	
	public void refreshDmLoaiThuoc(){
		listDmLoaiThuocs.clear();
		dmLoaiThuocDelegate = DmLoaiThuocDelegate.getInstance();
		hmLoaiThuoc.clear();
		hmLoaiThuoc = dmLoaiThuocDelegate.findAllDm();
		if( hmLoaiThuoc != null ){
			java.util.Set set = hmLoaiThuoc.entrySet();
		    Iterator i = set.iterator();
		    while(i.hasNext()){
		    	Map.Entry me = (Map.Entry)i.next();
		    	DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)me.getValue();
		    	listDmLoaiThuocs.add(new SelectItem(dmLoaiThuoc.getDmloaithuocTen()));
		    }
		}
	}

	public List<SelectItem> getListDmLoaiPhieus() {
		return listDmLoaiPhieus;
	}

	public void setListDmLoaiPhieus(List<SelectItem> listDmLoaiPhieus) {
		this.listDmLoaiPhieus = listDmLoaiPhieus;
	}
	
	public void refreshDmLoaiPhieu(){
		listDmLoaiPhieus.clear();
		hmDmLoaiPhieu.clear();
		dmLoaiPhieuDelegate = DmLoaiPhieuDelegate.getInstance();
		if(phieuDuTru != null && phieuDuTru.getDmloaithuocMaso() != null){
			List<DmLoaiPhieu> lstDmLoaiPhieus = dmLoaiPhieuDelegate.findAll();
			if(lstDmLoaiPhieus !=null && lstDmLoaiPhieus.size()>0)
			{
				for(DmLoaiPhieu o: lstDmLoaiPhieus){
					hmDmLoaiPhieu.put(o.getDmloaiphieuMa(), o);
					if(phieuDuTru.getDmloaithuocMaso().getDmloaithuocMaso().equals(o.getDmloaithuocMaso().getDmloaithuocMaso())){
						listDmLoaiPhieus.add(new SelectItem(o.getDmloaiphieuMa() + " - " +o.getDmloaiphieuTen()));
					}
				}
			}
		}
	}
	
	public void onblurLoaiPhieuAction(){
		//log.info("-----BEGIN onblurLoaiPhieuAction()-----");
		listCtkq.clear();
		String loaiPhieuItem = phieuDuTru.getPhieudtLoaiPhieu();
		if(!loaiPhieuItem.equals("")){
			dmLoaiPhieuMa = loaiPhieuItem.substring(0, loaiPhieuItem.indexOf(" - ")).trim();
		}		
		//log.info("-----END onblurLoaiPhieuAction()-----");
	}
	
	//Dm doi tuong
	public String getDmDoiTuongTen() {
		return dmDoiTuongTen;
	}
	
	public void setDmDoiTuongTen(String dmDoiTuongTen) {
		this.dmDoiTuongTen = dmDoiTuongTen;
	}
	
	public List<SelectItem> getListDmDoiTuongs() {
		return listDmDoiTuongs;
	}

	public void setListDmDoiTuongs(List<SelectItem> listDmDoiTuongs) {
		this.listDmDoiTuongs = listDmDoiTuongs;
	}
	public void onblurMaDoiTuongAction(){
		//log.info("-----BEGIN onblurMaDoiTuongAction()-----");
		String doituong_ma = phieuDuTru.getDmdoituongMaso(true).getDmdoituongMa();
		DmDoiTuong dmDoiTuong = (DmDoiTuong)hmDoiTuong.get(doituong_ma.toUpperCase());
		if(dmDoiTuong != null) {
			setDmDoiTuongTen(dmDoiTuong.getDmdoituongTen());
			phieuDuTru.setDmdoituongMaso(dmDoiTuong);
		}
		else {
			setDmDoiTuongTen("");	
			phieuDuTru.setDmdoituongMaso(new DmDoiTuong());
		}
		//log.info("-----END onblurMaDoiTuongAction()-----");
	}
	
	public void onblurTenDoiTuongAction(){
		//log.info("-----BEGIN onblurTenDoiTuongAction()-----");
		Boolean hasTenDT = false;
		String maDT= null;
		
		DmDoiTuong dmDoiTuong_Find = new DmDoiTuong();
		java.util.Set set = hmDoiTuong.entrySet();
		Iterator i = set.iterator();
		if(dmDoiTuongTen.equals("T\u00E2\u0301t ca\u0309")){
			phieuDuTru.setDmdoituongMaso(new DmDoiTuong());
		}else{
			while(i.hasNext()){
			    Map.Entry me = (Map.Entry)i.next();
			    DmDoiTuong dmDoiTuong = (DmDoiTuong)me.getValue();
			    if(dmDoiTuong.getDmdoituongTen() == dmDoiTuongTen || dmDoiTuong.getDmdoituongTen().equals(dmDoiTuongTen)){
			    	hasTenDT = true;
			    	dmDoiTuong_Find = dmDoiTuong;
			    	break;
			    }	    		
			}	
			if(hasTenDT){
				phieuDuTru.setDmdoituongMaso(dmDoiTuong_Find);
			}else{
				phieuDuTru.setDmdoituongMaso(new DmDoiTuong());
			}
		}
		//log.info("-----END onblurTenDoiTuongAction()-----");
	}
	
	public void refreshDmDoiTuong(){
		listDmDoiTuongs.clear();
		DieuTriUtilDelegate dieutriDel = DieuTriUtilDelegate.getInstance();
		hmDoiTuong.clear();
		hmDoiTuong = dieutriDel.findByDoiTuongThuPhi();
		if( hmDoiTuong != null ){
			java.util.Set set = hmDoiTuong.entrySet();
		    Iterator i = set.iterator();
		    listDmDoiTuongs.add(new SelectItem("T\u00E2\u0301t ca\u0309"));
		    while(i.hasNext()){
		    	Map.Entry me = (Map.Entry)i.next();
		    	DmDoiTuong dmDoiTuong = (DmDoiTuong)me.getValue();		    	
		    	listDmDoiTuongs.add(new SelectItem(dmDoiTuong.getDmdoituongTen()));
		    }
		}		
	}
	//End Tho add

	// Ham chuyen chi tiet nhap xuong duoi
	public void enter() throws Exception {
		
		//log.info("xinLinh:"+xinLinh);
		//log.info("tonKho:"+tonKho);
		//log.info("maHang:"+maHang);
		
		//log.info("xinLinh:"+xinLinh);
		//log.info("tonKho:"+tonKho);
		//log.info("maHang:"+maHang);
		
		if (maHang == null || maHang.equals("")){
			resetValue();
			maFinish = "";
			//log.info("mahang null");
			return;
		}
		
		
		//in case of xinlinh = "" on screen
		try{			
			xinLinh.doubleValue() ;
			Double.parseDouble(tonKho);
		}catch( Exception ex){
			xinLinh = new Double(0);
			tonKho = "0";
		}
		
		if (xinLinh == null || xinLinh.doubleValue() == 0){
			resetValue();
			maFinish = "";
			//log.info("xinLinh null");
			return;
		}
		
		if (tonKho == null || tonKho.equals("") || Double.parseDouble(tonKho) < xinLinh.doubleValue() ){
			resetValue();
			maFinish = "";
			//log.info("tonKho null");
			//log.info("Double.parseDouble(tonKho) < xinLinh.doubleValue()");
			return;
		}
		
		//log.info("*****Begin Enter() *****");
		if (updateNhapct) {
			updateRow();			
		} else {
			insertRow();
		}
		resetValue();
		maFinish = "";
		//log.info("*****End Enter() *****");
	}
	
	private void updateRow(){
		//log.info("*****updateNhapct");

		copyTo(nhapctSelected);
		int i = listCtkq.indexOf(nhapctSelected);
		if (i<0){
			insertRow();
		}
		//log.info("****i=" + i + "******");
		listCtkq.set(i, nhapctSelected);
		Caculation(listCtkq);
		updateNhapct = false;
	}
	
	private void insertRow(){
		//log.info("begin cache chi tiet ket qua");

		CtPhieuDt ctPhieuDuTru = new CtPhieuDt();
		SetInforUtil.setInforIfNullForCTPhieuDuTru(ctPhieuDuTru);
		copyTo(ctPhieuDuTru);
		listCtkq.add(ctPhieuDuTru);
		Caculation(listCtkq);
		
	}
	
	/**
	 * 
	 * @param chiTietPhieuDuTru
	 */
	private void copyTo(CtPhieuDt chiTietPhieuDuTru){
		//log.info("maHang:"+maHang);
		chiTietPhieuDuTru.getDmthuocMaso().setDmthuocMa(maHang);
		chiTietPhieuDuTru.getDmthuocMaso().setDmthuocMaso(maSoHang);
		
		//log.info("tenHang:"+tenHang);
		chiTietPhieuDuTru.getDmthuocMaso().setDmthuocTen(tenHang);
//		log.info("quyCach:"+quyCach);
//		chiTietPhieuDuTru.setCtdtQuycach(quyCach);
		//log.info("donVi:"+donVi);
		chiTietPhieuDuTru.getDmthuocMaso().getDmdonvitinhMaso().setDmdonvitinhTen(donVi);
		
		chiTietPhieuDuTru.setCtdtSoluong(xinLinh);
		chiTietPhieuDuTru.setCtdtDongia(donGia);	
		
		if (nguonctMa != null && !nguonctMa.equals("")){
			DmNguonChuongTrinh nguonCT = new DmNguonChuongTrinh();
			nguonCT.setDmnctMaso(nguonctMaso);
			nguonCT.setDmnctMa(nguonctMa);
			
			chiTietPhieuDuTru.setDmnctMaso(nguonCT);
		}
		
		if (nguonkpMa != null && !nguonkpMa.equals("")){
			DmNguonKinhPhi nguonKP = new DmNguonKinhPhi();
			nguonKP.setDmnguonkinhphiMaso(nguonkpMaso);
			nguonKP.setDmnguonkinhphiMa(nguonkpMa);
			
			chiTietPhieuDuTru.setDmnguonkinhphiMaso(nguonKP);
		}
		
		
	}
	
	/**
	 * 
	 * @param chiTietPhieuDuTru
	 */
	private void copyFrom(CtPhieuDt chiTietPhieuDuTru){
		maHang = chiTietPhieuDuTru.getDmthuocMaso().getDmthuocMa();
		maSoHang= chiTietPhieuDuTru.getDmthuocMaso().getDmthuocMaso();
		tenHang = chiTietPhieuDuTru.getDmthuocMaso().getDmthuocTen();
//		quyCach = chiTietPhieuDuTru.getCtdtQuycach();
		donVi = chiTietPhieuDuTru.getDmthuocMaso().getDmdonvitinhMaso().getDmdonvitinhTen();
		xinLinh = chiTietPhieuDuTru.getCtdtSoluong();
		donGia = chiTietPhieuDuTru.getCtdtDongia();	
		
		if (chiTietPhieuDuTru.getDmnctMaso() != null && !chiTietPhieuDuTru.getDmnctMaso().getDmnctMa().equals("")){
			
			nguonctMaso = chiTietPhieuDuTru.getDmnctMaso().getDmnctMaso();
			nguonctMa = chiTietPhieuDuTru.getDmnctMaso().getDmnctMa();
			
		}
		
		if (chiTietPhieuDuTru.getDmnguonkinhphiMaso() != null && !chiTietPhieuDuTru.getDmnguonkinhphiMaso().getDmnguonkinhphiMa().equals("")){
			
			nguonkpMaso = chiTietPhieuDuTru.getDmnguonkinhphiMaso().getDmnguonkinhphiMaso();
			nguonkpMa = chiTietPhieuDuTru.getDmnguonkinhphiMaso().getDmnguonkinhphiMa();
			
		}	
	}
	
	// Ham delete chi tiet
	public void delete() throws Exception {
		//log.info("*****Begin delete() *****");
		listCtkq.remove(nhapctSelected);
		Caculation(listCtkq);
		resetValue();
		updateNhapct = false;
		maFinish = "";
		//log.info("*****End delete() *****");
	}
	/**
	 * 
	 * @throws Exception
	 */
	public void nhapctAjax() throws Exception {

		//log.info("*****Begin nhapctAjax() *****");

		copyFrom(nhapctSelected);
		updateNhapct = true;
		//log.info("***********end nhapctAjax***********");

	}
	/**
	 * press GhiNhan button
	 * @throws Exception
	 */
	public void ghinhan() throws Exception {
		//log.info("*****Begin Ghi nhan() *****");
		//log.info("*****Ghi nhan phieu du tru *****" + phieuDuTru);
		//log.info("*****so phan tu insert *****" + listCtkq.size());
		
		String maphieu = phieuDuTru.getPhieudtMa();
		if (maphieu != null && !maphieu.equals("")){
			 if (!Identity.instance().hasRole("QT_KhoaNoiTru")){
			     throw new AuthorizationException("");
     	   }			
		}		 
		
		try {
			yteLog = new YteLog();
			listDataLog="";
			
			if (listCtkq == null || listCtkq.size() ==0){
				// them cau canh bao o day: Ban can phai tong hop thuoc tu yeu cau benh nhan hay khong co thuoc sau khi tong hop.
				FacesMessages.instance().add(IConstantsRes.PHIEU_DU_TRU_KHONGCOTHUOC);
				return;
			}			
			CtPhieuDtDelegate ctPhieuDelegate = CtPhieuDtDelegate.getInstance();
			RemoveUtil.removeIfNullForPhieuDuTru(phieuDuTru);
			setinfor(phieuDuTru);
			for (int i = 0; i < listCtkq.size(); i++) {
				//CtPhieuDt phieuDt = (CtPhieuDt)BeanUtils.cloneBean(listCtkq.get(i));
				listCtkq.get(i).setPhieudtMa(phieuDuTru);
				listCtkq.get(i).setCtdtNgaygiocn(new Date());
				//luu log thong tin thuoc
				listDataLog += "Ma LK:"+ listCtkq.get(i).getCtdtMalk()+
						" Don gia: "+  listCtkq.get(i).getCtdtDongianhap()+ " Don gia ban: "+ listCtkq.get(i).getCtdtDongia() + 
						" So luong: "+ listCtkq.get(i).getCtdtSoluong()+
						" So lo: "+ listCtkq.get(i).getCtdtLo() +
						" Nam SX: " + listCtkq.get(i).getCtdtNamnhap()+
						" Nam HD: " + listCtkq.get(i).getCtdtNamhandung()+ "\n";	
				 
				RemoveUtil.removeIfNullForCTPhieuDuTru(listCtkq.get(i));
			}
			phieuDuTru.setPhieudtPhanBiet(CtPhieuDtDelegate.LANH_THUOC_KHOA_PHONG);
			phieuDuTru.setPhieudtNgaygiocn(new Date());
			if ("".equals(Utils.reFactorString(phieuDuTru.getDmtangMaso(true).getDmtangMa())))
			{
				phieuDuTru.setDmtangMaso(null);
				//log.info("Buong null");
			}
			maFinish = ctPhieuDelegate.capNhatPhieuDuTru(listCtkq, listTNT , phieuDuTru, maphieu, IConstantsRes.PRIORITY_TON_LO_THUOC);
			if(maFinish != null && !maFinish.equals("")){
				//log.info("maFinish:"+maFinish);
					if (maFinish.indexOf(".") >= 0){
						FacesMessages.instance().add(maFinish);
						hienThiGhiNhan="true";
						hienThiHuyPhieu = "";
						hienThiInPhieu="";
					}else{
						FacesMessages.instance().add(IConstantsRes.SUCCESS);
						phieuDuTru.setPhieudtMa(maFinish);
						displayInfor();
						
						hienThiGhiNhan="";
						hienThiHuyPhieu = "";
						hienThiInPhieu="true";

//						Luu log he thong
				         yteLog.setForm("B3125_Lapphieudutru");
				         yteLog.setUserId(identity == null ? "NULL" : identity.getUsername());
				         yteLog.setObjectId(maFinish);
				         yteLog.setLogString(" Ngay xuat tu ngay: "+ gioLapPhieuTu + " " + ngayXuat +
				        		 			" Ngay xuat den ngay: "+ gioLapPhieuDen + " " + ngayXuatDen +
				        		 			" Doi tuong: "+ (dmDoiTuongTen == null ? "NULL" : dmDoiTuongTen)+
				        		 			" Loai thuoc: " + phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocMa()+
				        		 			" Loai phieu: "+ phieuDuTru.getPhieudtLoaiPhieu()+
				        		 			" Khoa lap phieu: "+ phieuDuTru.getDmkhoaMaso(true).getDmkhoaMa()+
				        		 			" Buong: "+ phieuDuTru.getDmtangMaso(true).getDmtangMa()+
				        		 			" Kho xuat: "+ phieuDuTru.getPhieudtMakho(true).getDmkhoaMa()+
				        		 			" Nguoi nhap: "+ phieuDuTru.getDtdmnhanvienCn(true).getDtdmnhanvienMa()+
				        		 			" Nguoi lap: "+ phieuDuTru.getDtdmnhanvienLapphieu(true).getDtdmnhanvienMa()+
				        		 			" Nguoi ky:" + phieuDuTru.getDtdmnhanvienBacsiky(true).getDtdmnhanvienMa()+
				        		 			" Thanh tien: "+ tongTien
				         					);
				         yteLog.setDateTime(new Date());
				         yteLog.setListData(listDataLog);
				         
				         yteLogDele.create(yteLog);
					}
			}
			else{
				FacesMessages.instance().add(IConstantsRes.FAIL);
			}
			
			//log.info("maFinish: " +maFinish);
			
			resultHidden="success";
		} catch (Exception e) {
			log.error("*************loi trong qua trinh dua du lieu lien server***********" + e.toString());
			e.printStackTrace();
			FacesMessages.instance().add(IConstantsRes.FAIL);
			resultHidden="fail";
		}
		
		//repare data to print
		SetInforUtil.setInforIfNullForPhieuDuTru(phieuDuTru);
		for (int i = 0; i < listCtkq.size(); i++) {
			listCtkq.get(i).setPhieudtMa(phieuDuTru);
			SetInforUtil.setInforIfNullForCTPhieuDuTru(listCtkq.get(i));			
		}
		
		//log.info("*****End Ghi nhan() *****");
	}
	/**
	 * press InPhieu button
	 * @throws Exception
	 */
	public String inphieu()throws Exception{
		//log.info("Begining inphieu()");
		if (!maFinish.equals("")) {
			try {
				XuatReport();
			} catch (Exception e) {
				log.info("Loi trong khi xuat report" + e.toString());
			}
		}
		
		//log.info("End inphieu()");
		return "B3360_Chonmenuxuattaptin";//B3125_Chonmenuxuattaptin
	}
	/**
	 * author Chi
	 * @return
	 */
	public String troVe(){
		try {
			//log.info("***** troVe()");    	
			return "B3125_Lapphieudutru";
		} 		
		catch (Exception e) {
			log.info("***** End exception = " + e);    	
		} 
		return null;
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
	
	
	int index = 0;
	public String XuatReport() {
		String baocao1=null;
		reportTypeVP="B3125_Lapphieudutru";
		try {
			//log.info("bat dau method XuatReport ");
			String pathTemplate = "";
			String loaiPhieu = phieuDuTru.getPhieudtLoaiPhieu();
			String loaiThuoc = loaiPhieu.substring(0, loaiPhieu.indexOf(" - ")).trim();
			if(loaiThuoc.equals("GN")){//Thuoc gay nghien
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieudutru_01GayNghien.jrxml";
			}else if(loaiThuoc.equals("HT")){//Thuoc huong tam than
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieudutru_01HTT.jrxml";
			}else{//cac loai con lai
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieudutru_01.jrxml";
			}
			
			//log.info("da thay file templete 5" + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			//log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM );
			if(loaiThuoc.equals("GN")){
				params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_GN );
			}else if(loaiThuoc.equals("HT")){
				params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_HT );
			}else if(loaiThuoc.equals("YC")){
				params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_YC );
				params.put("TITLE_RIGHT", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_YC_MS );
				params.put("TABLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_YC_TABLE);
			}else if(loaiThuoc.equals("HC")){
				params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_HC );
				params.put("TITLE_RIGHT", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_HC_MS );
				params.put("TABLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_HC_TABLE );
			}else{
				params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC );
				params.put("TITLE_RIGHT", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_MS );
				params.put("TABLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_TABLE );
			}
			params.put("makhoa",  phieuDuTru.getDmkhoaMaso().getDmkhoaMa());			
			DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
			DmKhoa khoa = (DmKhoa)dieuTriUtilDelegate.findByMa(phieuDuTru.getDmkhoaMaso().getDmkhoaMa(), "DmKhoa", "dmkhoaMa");
			params.put("khoa",  khoa.getDmkhoaTen());
			
			//log.info("------------------------------------------------------------------");
			//log.info("khoa:"+ phieuDuTru.getDmkhoaMaso().getDmkhoaTen());
			//log.info("maphieu:"+phieuDuTru.getPhieudtMa());
			//log.info("------------------------------------------------------------------");			
			
			params.put("maphieu", phieuDuTru.getPhieudtMa());		
			
			params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("donvi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			Class.forName("oracle.jdbc.OracleDriver");
		    //log.info("da thay driver mysql");
		    Connection conn = null;
		    try{
		    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
		    }catch(Exception e){
		    	log.info(e.getMessage());
		    }
			jasperPrintVP =  JasperFillManager.fillReport(jasperReport,params,conn);
			baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","B3125_Lapphieudutru");
			reportPathVP=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			//log.info("duong dan file xuat report :" + baocao1);
			//log.info("duong dan -------------------- :"+reportPathVP);
			index+=1;
			//log.info("Index :" + index);
		    
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    //log.info("Thoat Method XuatReport");
	    return "B3360_Chonmenuxuattaptin";
	}

	public String getReportFinished() {
		return reportFinished;
	}

	public void setReportFinished(String reportFinished) {
		this.reportFinished = reportFinished;
	}

	/**
	 * press NhapLai button
	 * @throws Exception
	 */
	public void nhaplai()throws Exception {
		//log.info("*****Begin nhaplai() *****");
		init() ;
		
		tongTien = "0";
		
		hienThiGhiNhan="true";
		hienThiHuyPhieu="";
		hienThiInPhieu="";
		
		
		
		listCtkq = new ArrayList<CtPhieuDt>();
		//log.info("*****End nhaplai() *****");
	}
	
	public void taothongtin() throws Exception {
		listCtkq = new ArrayList<CtPhieuDt>();
		listTNT = new ArrayList<ThuocNoiTru>();
		//log.info("Begin taothongtin()");
		//log.info("phieuDuTru.getPhieudtMa():"+phieuDuTru.getPhieudtMa());
		if (phieuDuTru.getPhieudtMa() == null || phieuDuTru.getPhieudtMa().equals("")) {
			try {
				ThuocNoiTruDelegate tntDelegate = ThuocNoiTruDelegate.getInstance();
				
				SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE_TIME); 
		        
				Date tuNgay = null;
				Date denNgay = null;
				try {
					ngayXuat = ngayXuat + " " + gioLapPhieuTu+":00";
					ngayXuatDen = ngayXuatDen + " " + gioLapPhieuDen+":00";
		            tuNgay = formatter.parse(ngayXuat);		            
		            denNgay = formatter.parse(ngayXuatDen);
		        } catch (Exception e) {
		        	log.info("Loi khi set gia tri ngay theo format he thong quy dinh" + e);
		        }
				Integer khoaMaso = 0;
				Integer khoMaso = 0;
				Integer tangMaso = 0;
				
				if (phieuDuTru.getDmkhoaMaso().getDmkhoaMa() != null && !phieuDuTru.getDmkhoaMaso().getDmkhoaMa().equals("")) {
					khoaMaso = phieuDuTru.getDmkhoaMaso().getDmkhoaMaso();
				}
				
				if (phieuDuTru.getPhieudtMakho().getDmkhoaMa() != null && !phieuDuTru.getPhieudtMakho().getDmkhoaMa().equals("")) {
					khoMaso = phieuDuTru.getPhieudtMakho().getDmkhoaMaso();
				}
				
				Integer doituongMaso = 0;
				if(phieuDuTru.getDmdoituongMaso(true).getDmdoituongMaso() != null){
					doituongMaso = phieuDuTru.getDmdoituongMaso(true).getDmdoituongMaso();
				}
				
				if(phieuDuTru.getDmtangMaso(true).getDmtangMaso() != null){
					tangMaso = phieuDuTru.getDmtangMaso(true).getDmtangMaso();
				}
				
				String loaiMa = "";
				String loaiPhieu = phieuDuTru.getPhieudtLoaiPhieu();
				if(loaiPhieu != null || !loaiPhieu.equals(""))
				{
					if (phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocMa() != null ){
						if( phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocMa().toUpperCase().equals("TD")){					
							if(!dmLoaiPhieuMa.equals("HT") && !dmLoaiPhieuMa.equals("GN")){
								loaiMa = "TD-TT";
								DmLoaiPhieu lp = hmDmLoaiPhieu.get(dmLoaiPhieuMa);
								if(lp.getDmloaiphieuDvt() != null){
									loaiMa+=";"+lp.getDmloaiphieuDvt();
								}
							}else{
								loaiMa = "TD-"+ dmLoaiPhieuMa;
							}
						}else if( phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocMa().toUpperCase().equals("DY")){
							DmLoaiPhieu lp = hmDmLoaiPhieu.get(dmLoaiPhieuMa);
							if(lp.getDmloaiphieuDvt() != null){
								loaiMa = "DY-"+ lp.getDmloaiphieuDvt();
							}
						}else{
							//query theo dm loai thuoc trong ThuocNoiTruDelegate
							loaiMa = phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocMa();
						}	
					}	
				}
				/*Format cua loaiMa: loaiMa chac chan khac ""
	                Tan duoc: thuoc thuong -> Format la: TD-TT
	                Tan duoc: thuoc vien   -> Format la: TD-TT;IN (35, 36, 37, 44)
	                Tan duoc: thuoc khac   -> Format la: TD-TT;NOT IN (35, 36, 37, 44)
	                Tan duoc: huong than   -> Format la: TD-HT
	                Tan duoc: gay nghien   -> Format la: TD-GN
	                Y dung cu:             -> Format la YC
	                Hoa chat               -> Format la HC
	                Dong Y - Thanh pham    -> Format la DY-DD
	                Dong Y - Vi			   -> Format la DY-DD2
	                Thuoc chuong trinh     -> Format la CT
				 */
				//log.info("loai ma = " + loaiMa);
				//log.info("Khoa ma so = " + khoaMaso);
				//log.info("Kho ma so = " + khoMaso);
				//log.info("Buong ma so = " + tangMaso);
				List<ThuocNoiTru> tntTemps = tntDelegate.findDanhSachTNTDuTruLinh(tuNgay, denNgay, loaiMa, khoaMaso, khoMaso, doituongMaso, tangMaso);
				
				listTNT = tntTemps;
				List<CtPhieuDt> listCtTemp = new ArrayList<CtPhieuDt>();
				
				if (tntTemps != null) {
					for (ThuocNoiTru tntTemp : tntTemps) {
						//Do Don gia bi set = 0 doi voi 1 so BN mien phi hoac khong thu khi cap thuoc cho BN, can phai set lai don gia khi lap phieu de tinh tien dung
	                    tntTemp.setThuocnoitruDongia(tntTemp.getThuocnoitruDongiabh());
	                    CtPhieuDt ctdt = changetoData(tntTemp,listCtTemp);						
						SetInforUtil.setInforIfNullForCTPhieuDuTru(ctdt);
						listCtTemp.add(ctdt);
	                }
				} else {
					FacesMessages.instance().add(IConstantsRes.PHIEU_DU_TRU_KHONGCOTHUOC);
					return;
				}
				
				if(listCtTemp != null && listCtTemp.size()> 0){
					Double dTongTien = new Double(0);
					NumberFormat numberFormat = NumberFormat.getInstance();
					
					for (CtPhieuDt ctdttemp : listCtTemp) {
						Double soluongXinLinh = ctdttemp.getCtdtSoluong();
						// 20120626 bao.ttc: lam tron de tranh cac truong hop dung 2 lo: 0.9999999999993 va 1.0000000000007
						soluongXinLinh = Utils.roundDoubleTwoDecimals(soluongXinLinh);
						
						if(ctdttemp.getDmthuocMaso(true).getDmphanloaithuocMaso().getDmphanloaithuocNhom3() != null && ctdttemp.getDmthuocMaso(true).getDmphanloaithuocMaso().getDmphanloaithuocNhom3().equals("1")){
							//Tho add rule 17/8/2011: tuy thuoc vao phan loai thuoc cua thuoc do: Se duoc lam tron neu 0.0001 den 0.9999 se lam tron thanh 1
							int sochan = soluongXinLinh.intValue();						
							if(sochan < soluongXinLinh){
								sochan+=1;
							}
							soluongXinLinh = Double.parseDouble(Integer.toString(sochan));
						}
						ctdttemp.setCtdtSoluong(soluongXinLinh);
						listCtkq.add(ctdttemp);
						
						dTongTien += ctdttemp.getCtdtDongia().doubleValue() * ctdttemp.getCtdtSoluong().doubleValue();
					}
					tongTien = numberFormat.format(dTongTien);
					// bao.ttc: khong can tinh vi da ghep vao FOR o tren: Caculation(listCtkq);
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("*************loi***********" + e.toString());
			}
		}
		
		//log.info("End taothongtin()");
	}
	private CtPhieuDt changetoData(ThuocNoiTru tnt, java.util.List<CtPhieuDt> list) throws Exception {
		//log.info("Begin changetoData() : " + tnt);
		CtPhieuDt result = new CtPhieuDt();
		boolean haveinlist = false;
		if (list.size() > 0) {			
			for (int i = 0; i < list.size(); i++) {
				CtPhieuDt temp = list.get(i);
				//Tho - 28/7/2011: change rule: rule cu: neu cung ma so thuoc thi chi nhom thanh 1 dong. Rule moi: neu cung ma lk thi nhom thanh 1 dong
				if (temp.getCtdtMalk().equals(tnt.getThuocnoitruMalk())) {
					list.remove(i);
					temp.setCtdtSoluong(tnt.getThuocnoitruSoluong() + temp.getCtdtSoluong());
					result = temp;
					haveinlist = true;
					break;
				}
			}			
		}
		if (haveinlist == false) {
			result.setCtdtSoluong(tnt.getThuocnoitruSoluong());
			result.setCtdtDongia(tnt.getThuocnoitruDongia());
			result.setDmthuocMaso(tnt.getThuocnoitruMathuoc());
			// DuyenLP kiem tra malk khong co
			if(tnt.getThuocnoitruMalk()== null  || tnt.getThuocnoitruMalk().equals("")){
				result.setCtdtMalk("");
			}else{
				result.setCtdtMalk(tnt.getThuocnoitruMalk());
			}
		}
		
		//log.info("End changetoData()");
		return result;
	}
	
	// Hien thi thong tin CLS Kham cua benh nhan sau khi nhap Ma phieu du tru
	public void displayInfor() throws Exception {
		try {
			
			if (phieuDuTru.getPhieudtMa() == null || phieuDuTru.getPhieudtMa().equals("")){
				return;
			}
			//log.info("Begining displayInfor()");
//			CtPhieuDtWSService ctpdtService = new CtPhieuDtWSServiceLocator();
//			CtPhieuDtWS ctpdtWS = ctpdtService.getCtPhieuDtWSPort();
			
			CtPhieuDtDelegate ctPhieuDTDelegate = CtPhieuDtDelegate.getInstance();
			
			 //List<CtPhieuDt> ctpdt_tmp = ctPhieuDTDelegate.findByPhieuDuTruMa(phieuDuTru.getPhieudtMa());
			//triet sua 31/03/2010
			PhieuDuTru objPhieuDt = ctPhieuDTDelegate.findByPhieuDuTruPhanBiet(phieuDuTru.getPhieudtMa(),CtPhieuDtDelegate.LANH_THUOC_KHOA_PHONG);
			
			if (objPhieuDt == null ) {
				//log.info("displayInfor   phieu du tru 22 ....."+ phieuDuTru);
				
				FacesMessages.instance().add(IConstantsRes.PHIEU_DU_TRU_NOT_FOUND,phieuDuTru.getPhieudtMa());
				this.nhaplai();
			} else {
				yteLog = new YteLog();
				listDataLog="";
				
				List<CtPhieuDt> ctpdt_tmp =ctPhieuDTDelegate.findByPhieuDuTruMa(objPhieuDt.getPhieudtMa());
				//log.info("Co thong tin phieu du tru" + objPhieuDt);
				phieuDuTru = objPhieuDt;
				SetInforUtil.setInforIfNullForPhieuDuTru(phieuDuTru);
				maFinish = phieuDuTru.getPhieudtMa();
				//log.info("Co thong tin ct phieu du tru" + ctpdt_tmp);
				setOtherValue();
				listCtkq = new ArrayList<CtPhieuDt>();
				dmLoaiTen = phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocTen();
				dmkhoaMa = phieuDuTru.getDmkhoaMaso(true).getDmkhoaMa();
				dmkhoaTen = phieuDuTru.getDmkhoaMaso(true).getDmkhoaTen();
				
				if (ctpdt_tmp != null) {
					for (CtPhieuDt ctpdt : ctpdt_tmp) {
						SetInforUtil.setInforIfNullForCTPhieuDuTru(ctpdt);
						listCtkq.add(ctpdt);
						//luu log thong tin thuoc
						listDataLog += "Ma LK:"+ ctpdt.getCtdtMalk()+
								" Don gia: "+  ctpdt.getCtdtDongianhap()+ " Don gia ban: "+ ctpdt.getCtdtDongia() + 
								" So luong: "+ ctpdt.getCtdtSoluong()+
								" So lo: "+ ctpdt.getCtdtLo() +
								" Nam SX: " + ctpdt.getCtdtNamnhap()+
								" Nam HD: " + ctpdt.getCtdtNamhandung()+ "\n";	
				
					}
					Caculation(listCtkq);
				}
				
				hienThiGhiNhan = "";
				hienThiInPhieu="true";
				if (phieuDuTru.getPhieudtDaXuat() != null && phieuDuTru.getPhieudtDaXuat() == true){
					hienThiHuyPhieu="";
				}else {
					hienThiHuyPhieu="true";
				}
			}
		} catch (Exception e) {
			log.info((new StringBuilder()).append(
					"error on function displayInfor").append(e).toString());
		}
		//log.info("maFinish : " + maFinish);
		//log.info("End displayInfor()");
	}
	
	private void setOtherValue() {
		//log.info("Begining setOtherValue()");
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
		if (phieuDuTru.getPhieudtNgay() !=  null) {
			ngayXuat = formatter.format(phieuDuTru.getPhieudtNgay().getTime());
			//log.info("Ngay xuat :" + ngayXuat);
		}
		
		//log.info("End setOtherValue()");
	}
	
	private void setinfor(PhieuDuTru pdt) {
		//log.info("Begining setinfor()");
		
		try {
			if (!("".equals(ngayXuat))) {
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				cal.setTime(df.parse(ngayXuat));
				phieuDuTru.setPhieudtNgay(cal.getTime());
			}
		} catch (Exception e) {
			log.info("ERRoR: setinfor(PhieuDuTru pdt) " +e);
		}
		 
	}

	public java.util.List<CtPhieuDt> getListCtkq() {
		return listCtkq;
	}

	public void setListCtkq(java.util.List<CtPhieuDt> listCtkq) {
		this.listCtkq = listCtkq;
	}

	public CtPhieuDt getNhapctSelected() {
		return nhapctSelected;
	}

	public void setNhapctSelected(CtPhieuDt nhapctSelected) {
		this.nhapctSelected = nhapctSelected;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getMaHang() {
		return maHang;
	}

	public void setMaHang(String maHang) {
		this.maHang = maHang;
	}

	
	public Integer getQuyCach() {
		return quyCach;
	}

	public void setQuyCach(Integer quyCach) {
		this.quyCach = quyCach;
	}

	public String getDonVi() {
		return donVi;
	}

	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}

	public String getTonKho() {
		return tonKho;
	}

	public void setTonKho(String tonKho) {
		this.tonKho = tonKho;
	}

	public Double getXinLinh() {
		return xinLinh;
	}

	public void setXinLinh(Double xinLinh) {
		this.xinLinh = xinLinh;
	}

	public Double getDonGia() {
		return donGia;
	}

	public void setDonGia(Double donGia) {
		this.donGia = donGia;
	}

	public  boolean isUpdateNhapct() {
		return updateNhapct;
	}

	public  void setUpdateNhapct(boolean updateNhapct) {
		this.updateNhapct = updateNhapct;
	}

	public PhieuDuTru getPhieuDuTru() {
		return phieuDuTru;
	}

	public void setPhieuDuTru(PhieuDuTru phieuDuTru) {
		this.phieuDuTru = phieuDuTru;
	}

	public String getTongTien() {
		return tongTien;
	}

	public void setTongTien(String tongTien) {
		this.tongTien = tongTien;
	}

	public String getNgayXuat() {
		return ngayXuat;
	}

	public void setNgayXuat(String ngayXuat) {
		this.ngayXuat = ngayXuat;
	}

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		LapPhieuDuTruAction.log = log;
	}

	public String getTenHang() {
		return tenHang;
	}

	public void setTenHang(String tenHang) {
		this.tenHang = tenHang;
	}

	public String getMaFinish() {
		return maFinish;
	}

	public void setMaFinish(String maFinish) {
		this.maFinish = maFinish;
	}

	public String getNgayhientai() {
		return ngayhientai;
	}

	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
	}

	public String getMsgFail() {
		return msgFail;
	}

	public void setMsgFail(String msgFail) {
		this.msgFail = msgFail;
	}

	public String getMsgSuccess() {
		return msgSuccess;
	}

	public void setMsgSuccess(String msgSuccess) {
		this.msgSuccess = msgSuccess;
	}

	public String getResultHidden() {
		return resultHidden;
	}

	public void setResultHidden(String resultHidden) {
		this.resultHidden = resultHidden;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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

	public String getIsReport() {
		return isReport;
	}

	public void setIsReport(String isReport) {
		this.isReport = isReport;
	}

	public Integer getNguonctMaso() {
		return nguonctMaso;
	}

	public void setNguonctMaso(Integer nguonctMaso) {
		this.nguonctMaso = nguonctMaso;
	}

	public String getNguonctMa() {
		return nguonctMa;
	}

	public void setNguonctMa(String nguonctMa) {
		this.nguonctMa = nguonctMa;
	}

	public Integer getNguonkpMaso() {
		return nguonkpMaso;
	}

	public void setNguonkpMaso(Integer nguonkpMaso) {
		this.nguonkpMaso = nguonkpMaso;
	}

	public String getNguonkpMa() {
		return nguonkpMa;
	}

	public void setNguonkpMa(String nguonkpMa) {
		this.nguonkpMa = nguonkpMa;
	}

	public Integer getMaSoHang() {
		return maSoHang;
	}

	public void setMaSoHang(Integer maSoHang) {
		this.maSoHang = maSoHang;
	}

	public DtDmNhanVien getNvCn() {
		return nvCn;
	}

	public void setNvCn(DtDmNhanVien nvCn) {
		this.nvCn = nvCn;
	}

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	public String getHienThiGhiNhan() {
		return hienThiGhiNhan;
	}

	public void setHienThiGhiNhan(String hienThiGhiNhan) {
		this.hienThiGhiNhan = hienThiGhiNhan;
	}

	public String getHienThiHuyPhieu() {
		return hienThiHuyPhieu;
	}

	public void setHienThiHuyPhieu(String hienThiHuyPhieu) {
		this.hienThiHuyPhieu = hienThiHuyPhieu;
	}

	public String getHienThiInPhieu() {
		return hienThiInPhieu;
	}

	public void setHienThiInPhieu(String hienThiInPhieu) {
		this.hienThiInPhieu = hienThiInPhieu;
	}

	public String getNgayXuatDen() {
		return ngayXuatDen;
	}

	public void setNgayXuatDen(String ngayXuatDen) {
		this.ngayXuatDen = ngayXuatDen;
	}
	
	public String getGioLapPhieuTu() {
		return gioLapPhieuTu;
	}
	
	public void setGioLapPhieuTu(String gioLapPhieuTu) {
		this.gioLapPhieuTu = gioLapPhieuTu;
	}
	
	public String getGioLapPhieuDen() {
		return gioLapPhieuDen;
	}
	
	public void setGioLapPhieuDen(String gioLapPhieuDen) {
		this.gioLapPhieuDen = gioLapPhieuDen;
	}
	
	public String getDmLoaiPhieuMa() {
		return dmLoaiPhieuMa;
	}
	
	public void setDmLoaiPhieuMa(String dmLoaiPhieuMa) {
		this.dmLoaiPhieuMa = dmLoaiPhieuMa;
	}
	
	public List<SelectItem> getListDmKhoaNTs() {
		return listDmKhoaNTs;
	}

	public void setListDmKhoaNTs(List<SelectItem> listDmKhoaNTs) {
		this.listDmKhoaNTs = listDmKhoaNTs;
	}
	
	public List<SelectItem> getListDmTangs() {
		return listDmTangs;
	}

	public void setListDmTangs(List<SelectItem> listDmTangs) {
		this.listDmTangs = listDmTangs;
	}
}


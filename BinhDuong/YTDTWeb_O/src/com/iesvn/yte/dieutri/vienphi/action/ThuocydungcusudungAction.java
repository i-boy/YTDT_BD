package com.iesvn.yte.dieutri.vienphi.action;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Calendar;

import javax.faces.model.SelectItem;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.log4j.Logger;
import org.jboss.seam.ScopeType;
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
import org.jboss.seam.security.Identity;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
import com.iesvn.yte.dieutri.delegate.BenhNhanDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DmKhoaDelegate;
import com.iesvn.yte.dieutri.delegate.DmThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaKhoaDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocDongYNoiTruDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocNoiTruDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
import com.iesvn.yte.dieutri.delegate.YteLogDelegate;
import com.iesvn.yte.dieutri.entity.BaithuocThuoc;
import com.iesvn.yte.dieutri.entity.DmBaiThuoc;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.HsThtoan;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.HsbaKhoa;
import com.iesvn.yte.dieutri.entity.ThuocDongYNoiTru;
import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
import com.iesvn.yte.dieutri.entity.ThuocNoiTruXuatVien;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.dieutri.entity.YteLog;
import com.iesvn.yte.entity.DmDonViTinh;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmNhaSanXuat;
import com.iesvn.yte.entity.DmTang;
import com.iesvn.yte.entity.DmThuoc;
import com.iesvn.yte.util.HoSoThanhToanUtil;
import com.iesvn.yte.util.HsttThreadUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;
import com.sun.org.apache.commons.beanutils.BeanUtils;

@Scope(ScopeType.CONVERSATION)
@Name("B3111_Thuocydungcusudung")
@Synchronized(timeout = 6000000)
public class ThuocydungcusudungAction implements Serializable {
	
	private static final long serialVersionUID = 10L;
	private static Logger logger = Logger.getLogger(ThuocydungcusudungAction.class);
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
	@Out(required=false)
	@In(required=false)
	private String reportPathTD=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeTD=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintTD = null;
	
	private int index=0;
	
	@DataModel
	private List<TonKhoEx> listTkEx;
	private List<TonKhoEx> listTkEx_Tutruc;
	private List<TonKhoEx> listTkEx_Pdt;
	private List<TonKhoEx> listTkEx_KeToa;
	
	@DataModelSelection("listTkEx")
	private TonKhoEx tkExSelected;
	private ArrayList<ThuocNoiTru> listTntDel;
	private ArrayList<ThuocNoiTru> listTntDel_Tutruc;
	private ArrayList<ThuocNoiTru> listTntDel_Pdt;
	private ArrayList<ThuocNoiTru> listTntDel_KeToa;
	//Tho add de luu so vao vien cu va ngay so lieu cu
	private String sovaovienOld;
	private String ngaySoLieuOld;
	private String khoaMaOld;
	//End Tho add de luu so vao vien cu
	
	private DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance(); // 20120604 bao.ttc: chi khai bao 1 delegate de dung chung
	
	private TonKhoEx tkEx;
	private Hsba hsba;
	private HsbaKhoa hsbaKhoa;
	private HsThtoan hsthtoan;
	
	private HsbaBhyt hsbaBhyt;
	private DmKhoa khoa;
	private String lanVao;
	private String ngayVv;
	private String ngayHt = Utils.getCurrentDate();
	private String gioNhapThuoc = Utils.getGioPhut(new Date()); // 20120425 bao.ttc: them Gio cap nhat Thuoc
	private int tkMa;
	private Boolean isUpdate;
	private String maPhieu;
	private Integer count;
	private String tutrucPdt;
	private String tutrucPdtOLD = "";
	
	private String malk;
	private Double tongTien;
	private String dmdonvitinhTen;
	private String checkKiemKe = "false";
	private String handung ="";
	private String hamluong="";
	private Double tonTong= new Double(0.0);
	private List<TonKho> listTonKhoHienTai = new ArrayList<TonKho>();
	private Integer dmBaithuocMaso  = 0;
	private String dmBaithuocMa;
	private int sobaithuoc = 1;
	private DtDmNhanVien nhanVienCN = null;
	private HashMap<Integer,List> hsmListThuocDYNoiTru = new HashMap<Integer,List>();
	
	private int ctThuocNoiTruSelectedOld = -1; //Tho add - dung khi chinh sua 1 record
	
	private boolean checkLoad = false;
	private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	@In(required = false)
	@Out(required = false)
	private String checkThanhtoan = "block";
	
	@In(required = false)
	@Out(required = false)
	private String tenChuongTrinh;
	//Tho add 22-02-2011
	private Boolean isYeucau = false;
	private Boolean isKhongthu = false;
	private Boolean isAllowedUpdateSoLuong = true;
	
	private Integer sochia;
	private Integer sobichia;
	
	private String dmdoituongMa;
	
	@In(required = false)
	@Out(required = false)
	private String chuyenManHinhThuocCLS_MaKhoa; // bao.ttc: doi thanh Ma so Khoa
	
	@In(required = false)
	@Out(required = false)
	private String chuyenManHinhThuocCLS_TangMaSo;
	
	@In(required = false)
	@Out(required = false)
	private String chuyenManHinhThuocCLS_SoBenhAn;
    
	private String focusDochuyenTuManHinhThuocCLS;
	
	public String getFocusDochuyenTuManHinhThuocCLS() {
		return focusDochuyenTuManHinhThuocCLS;
	}

	public void setFocusDochuyenTuManHinhThuocCLS(
			String focusDochuyenTuManHinhThuocCLS) {
		this.focusDochuyenTuManHinhThuocCLS = focusDochuyenTuManHinhThuocCLS;
	}
	
	private DmKhoaDelegate dmKhoaDel = DmKhoaDelegate.getInstance();
	private HashMap<String, DmKhoa> hmDmKhoaNTAll = new HashMap<String, DmKhoa>();
	private List<SelectItem> listDmKhoaNTs = new ArrayList<SelectItem>();
	private List<DmKhoa> listDmKhoaNTAll = new ArrayList<DmKhoa>();
	private DmTang tangChuyenDen = new DmTang();
	private List<SelectItem> listDmTangs = new ArrayList<SelectItem>();
	
	private String maChiDan;
	private String tenChiDan;

	public String getMaChiDan() {
		return maChiDan;
	}
	public void setMaChiDan(String maChiDan) {
		this.maChiDan = maChiDan;
	}
	public String getTenChiDan() {
		return tenChiDan;
	}
	public void setTenChiDan(String tenChiDan) {
		this.tenChiDan = tenChiDan;
	}
	
	public List<SelectItem> getListDmKhoaNTs() {
		return listDmKhoaNTs;
	}

	public void setListDmKhoaNTs(List<SelectItem> listDmKhoaNTs) {
		this.listDmKhoaNTs = listDmKhoaNTs;
	}
	
	public void onblurMaKhoaAction(){
		//logger.info("-----BEGIN onblurMaKhoaAction()-----");
		if(khoa != null && khoa.getDmkhoaMa() != null){
			String maKhoa = khoa.getDmkhoaMa();
			if(hmDmKhoaNTAll != null){
				DmKhoa dmKhoa = (DmKhoa)hmDmKhoaNTAll.get(maKhoa.toUpperCase());
				if(dmKhoa != null) {
					khoa = dmKhoa;
					//logger.info("Tim ma khoa: Da thay khoa "+ khoa.getDmkhoaTen());
				}
				else {
					khoa = new DmKhoa();
					return;
				}
			}
			// 20120316 bao.ttc: them Tang
			tangChuyenDen = new DmTang();
			refreshDmTang();
			// bao.ttc: chua can refresh luc nay: refreshDmThuoc(tutrucPdt);
		}
		//logger.info("-----END onblurMaKhoaAction()-----");
	}
	
	public void onblurTenKhoaAction(){
		//logger.info("-----BEGIN onblurTenKhoaAction()-----");
		if(khoa != null && khoa.getDmkhoaTen() != null){
			String tenKhoa = khoa.getDmkhoaTen();
			Boolean hasTenKhoa = false;
			java.util.Set set = hmDmKhoaNTAll.entrySet();
		    Iterator i = set.iterator();
		    DmKhoa dmKhoa_Finded = new DmKhoa();
		    while(i.hasNext()){
		    	Map.Entry me = (Map.Entry)i.next();
		    	DmKhoa dmKhoa = (DmKhoa)me.getValue();
		    	if(dmKhoa.getDmkhoaTen() == tenKhoa || dmKhoa.getDmkhoaTen().equals(tenKhoa)){
		    		hasTenKhoa = true;
		    		dmKhoa_Finded = dmKhoa;
		    		break;
		    	}	    		
		    }
		    if(hasTenKhoa){
		    	khoa.setDmkhoaMaso(dmKhoa_Finded.getDmkhoaMaso());
		    	khoa.setDmkhoaMa(dmKhoa_Finded.getDmkhoaMa().toUpperCase());
		    	khoa.setDmkhoaTen(dmKhoa_Finded.getDmkhoaTen());
		    }else{
		    	khoa = new DmKhoa();
		    	return;
		    }
		    // 20120316 bao.ttc: them Tang
 			tangChuyenDen = new DmTang();
 			refreshDmTang();
 			// bao.ttc: chua can refresh luc nay: refreshDmThuoc(tutrucPdt);
		}
		//logger.info("-----END onblurTenKhoaAction()-----");
	}
	
	public void refreshDmKhoaNT(){
		
		if ( listDmKhoaNTAll.size() == 0 || listDmKhoaNTs.size() == 0 || hmDmKhoaNTAll.size() == 0 ) {
			
			listDmKhoaNTAll.clear();
			listDmKhoaNTs.clear();
			hmDmKhoaNTAll.clear();
			
			dmKhoaDel = DmKhoaDelegate.getInstance();
			listDmKhoaNTAll = dmKhoaDel.getKhoaNT();
			
			for(DmKhoa each : listDmKhoaNTAll){
				hmDmKhoaNTAll.put(each.getDmkhoaMa(), each);
				listDmKhoaNTs.add(new SelectItem(each.getDmkhoaTen()));
			}
		}
	}
	
	public void onblurTenTangAction(){
		if(tangChuyenDen != null && tangChuyenDen.getDmtangTen() != null){
			//DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
			List<DmTang> lstTangs = dtUtil.findTenLike("DmTang", "dmtangTen", tangChuyenDen.getDmtangTen());
			if(lstTangs != null && lstTangs.size()>0){
				tangChuyenDen = lstTangs.get(0);
			}else{
				tangChuyenDen = new DmTang();
			}
		}
	}
	
	public void refreshDmTang(){
		listDmTangs = new ArrayList<SelectItem>();
		if(khoa != null && khoa.getDmkhoaMaso() != null){
			String khoaMa = khoa.getDmkhoaMa();
			//DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
			//Get tat ca cac tang cua khoa chuyen den, show gia tri default truoc
			List<DmTang> lstDmTangs = dtUtil.findMaLike("DmTang", "dmkhoaMaso.dmkhoaMa", "dmtangChon", khoaMa, true);
			if(lstDmTangs != null && lstDmTangs.size()>0){
				for(DmTang dmTang:lstDmTangs){
					listDmTangs.add(new SelectItem(dmTang.getDmtangTen()));
				}
				// 20120315 bao.ttc: Truong hop khong co tang default thi chon tang dau tien trong list
				tangChuyenDen = lstDmTangs.get(0);
				for(DmTang dmTang:lstDmTangs){
					if(dmTang.getDmtangDefault().booleanValue() == true){
						tangChuyenDen = dmTang;
						break;
					}
				}
			}
		}
	}
	
	@Begin(join = true)
	public String init() {
		//logger.info("-----init()-----");
		reset();
		tenChuongTrinh = MyMenuYTDTAction.vienPhiTaiKhoa;
		
		// 20120417 bao.ttc: tam thoi khong dung chuc nang nay
		if ( chuyenManHinhThuocCLS_SoBenhAn != null && !chuyenManHinhThuocCLS_SoBenhAn.equals("")
				&& chuyenManHinhThuocCLS_MaKhoa != null && !chuyenManHinhThuocCLS_MaKhoa.equals("")
				&& chuyenManHinhThuocCLS_TangMaSo != null && !chuyenManHinhThuocCLS_TangMaSo.equals("") ){
					
			hsba.setHsbaSovaovien(chuyenManHinhThuocCLS_SoBenhAn);
			//DieuTriUtilDelegate dtDel = DieuTriUtilDelegate.getInstance();
			DmKhoa khoaTemp = (DmKhoa) dtUtil.findByMa(chuyenManHinhThuocCLS_MaKhoa.toUpperCase(), "DmKhoa", "dmkhoaMa");
			
			if (khoaTemp != null && khoaTemp.getDmkhoaMa() != null && !khoaTemp.getDmkhoaMa().equals("") ) {
				khoa = khoaTemp;
				int tangMaso = 0;
				try {
					tangMaso = Integer.parseInt(chuyenManHinhThuocCLS_TangMaSo);
				} catch (Exception e) {
					logger.info(" Thuoc Noi tru INIT Exception: Parse TangMaSo !!");
					tangMaso = 0;
				}
				DmTang tangChuyenDenTemp = (DmTang) dtUtil.findByMaSo(tangMaso, "DmTang", "dmtangMaso");
				
				if (tangChuyenDenTemp != null) {
					tangChuyenDen = tangChuyenDenTemp;
					loadHsba();
					focusDochuyenTuManHinhThuocCLS = "true";
				}
			}
		}
		
		return "VienPhiTaiKhoa_SoLieuBNSuDung_ThuocYDungCuSuDung";
	}
	
	@End
	public void endFunct(){
		
	}
	
	/** ==================== BEGIN LY THEM CODE ==================== */	
	private DmThuocDelegate dmThuocDelegate;
	private List<SelectItem> listDmThuocs = new ArrayList<SelectItem>();
	private List<DmThuoc> listDmThuocAll;
	private HashMap<String, DmThuoc> hmDmThuocAll = new HashMap<String, DmThuoc>();
	private String searchType = "1"; // 20110117 bao.ttc: cach search Thuoc: "1": ten Thuoc; "2": Ten khoa hoc
	
	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	
	public List<SelectItem> getListDmThuocs() {
		return listDmThuocs;
	}

	public void setListDmThuocs(List<SelectItem> listDmThuocs) {
		this.listDmThuocs = listDmThuocs;
	}
	
	public void onblurMaThuocAction(){
		//logger.info("-----BEGIN onblurMaThuocAction()-----");
		maChiDan = "";
		tenChiDan="";
		if(tkEx != null && tkEx.getTnt(true) != null && tkEx.getTnt(true).getThuocnoitruMathuoc(true) != null){
			String maThuoc = tkEx.getTnt(true).getThuocnoitruMathuoc(true).getDmthuocMa();
			if(hmDmThuocAll != null){
				DmThuoc dmThuoc = hmDmThuocAll.get(maThuoc.toUpperCase());
				if(dmThuoc != null) {
					DmThuoc dmThuoc1 = dmThuocDelegate.findByMaThuoc(maThuoc.toUpperCase());//lay ten thuoc
					tkEx.getTnt(true).setThuocnoitruMathuoc(dmThuoc1);
					tkEx.getTnt(true).getThuocnoitruMathuoc(true).setDmthuocTen(dmThuoc.getDmthuocTen());					
					//logger.info("-----DA THAY DMTHUOC-----");
					setHamluong(dmThuoc1.getDmthuocHamluong());
					setDmdonvitinhTen(dmThuoc1.getDmdonvitinhMaso().getDmdonvitinhTen());
					tkEx.getTnt(true).setThuocnoitruMathuoc(dmThuoc1);
					tkEx.getTnt(true).setThuocnoitruMien(dmThuoc1.getDmthuocMien());
					tkEx.getTnt(true).setThuocnoitruNDM(!dmThuoc1.getDmthuocIndanhmuc());
					if(dmThuoc1.getDmthuocYcu() != null && dmThuoc1.getDmthuocYcu()){
						tkEx.getTnt(true).setThuocnoitruLoai("Y");
					}else{
						tkEx.getTnt(true).setThuocnoitruLoai("T");
					}
					tkEx.getTnt(true).setThuocnoitruKho(getDmKho(dmThuoc.getDmthuocMaso(), tutrucPdt));
				}
				else{
					tkEx.getTnt(true).setThuocnoitruMathuoc(new DmThuoc());
					setHamluong("");
					setDmdonvitinhTen("");		
					tkEx.getTnt(true).setThuocnoitruKho(new DmKhoa());
				}
			}
			
			setIsKhongthu(false);
			setIsYeucau(false);
			
			if(tutrucPdt.equals("2") && hsba != null && hsba.getDoituongMa(true).getDmdoituongMa() != null && hsba.getDoituongMa(true).getDmdoituongMa().toUpperCase().equals("TP")){
				setTonTong(new Double(0.0));
				tkEx.getTnt(true).setThuocnoitruHangsx(null);
				tkEx.getTnt(true).setThuocnoitruQuocgia(null);
				tkEx.getTnt(true).setThuocnoitruSoluong(null);
				return;
			}
			//get so luong ton thuoc hien tai cua ma/ten thuoc do, tinh so lieu tong, set lai field tonTong
			Integer masoThuoc = tkEx.getTnt(true).getThuocnoitruMathuoc(true).getDmthuocMaso();
			if(masoThuoc != null){
				getTonKhoHienTai(masoThuoc, tutrucPdt);
				if(listTonKhoHienTai != null && listTonKhoHienTai.size()>0){
					TonKho tk = listTonKhoHienTai.get(0);					
					if(tk.getTonkhoMaphieukiem() != null){
						setTonTong(new Double(0.0));
						tkEx.getTnt(true).setThuocnoitruMathuoc(null);
						setCheckKiemKe("true");
					}else{
						Double ton = new Double(0.0);
						for(TonKho tk1: listTonKhoHienTai){
							ton += tk1.getTonkhoTon();
						}
						setTonTong(ton);
					}
				}
			}			
		}else{
			setTonTong(new Double(0.0));
		}
		tkEx.getTnt(true).setThuocnoitruHangsx(null);
		tkEx.getTnt(true).setThuocnoitruQuocgia(null);
		tkEx.getTnt(true).setThuocnoitruSoluong(null);
		//logger.info("-----END onblurMaThuocAction()-----");
	}	
	
	public void onblurTenThuocAction(){
		//logger.info("-----BEGIN onblurTenThuocAction()-----");
		maChiDan="";
		tenChiDan="";
		if(tkEx != null && tkEx.getTnt(true) != null && tkEx.getTnt(true).getThuocnoitruMathuoc(true) != null){
			String tenThuoc = tkEx.getTnt(true).getThuocnoitruMathuoc(true).getDmthuocTen();
			if(tenThuoc.length() > 6){
				String maThuoc = tenThuoc.substring(tenThuoc.length() - 6);    
				//logger.info("-----MA THUOC: " + maThuoc + " -----");
				DmThuoc dmThuoc = dmThuocDelegate.findByMaThuoc(maThuoc.toUpperCase());
				if(dmThuoc != null) {
					tkEx.getTnt(true).setThuocnoitruMathuoc(dmThuoc);
					tkEx.getTnt(true).setThuocnoitruMien(dmThuoc.getDmthuocMien());
					tkEx.getTnt(true).setThuocnoitruNDM(!dmThuoc.getDmthuocIndanhmuc());
					tkEx.getTnt(true).getThuocnoitruMathuoc(true).setDmthuocTen(dmThuoc.getDmthuocTen());
					tkEx.getTnt(true).setThuocnoitruLoai(dmThuoc.getDmthuocYcu() == null ? "" : dmThuoc.getDmthuocYcu().toString());					
					//logger.info("-----DA THAY DMTHUOC-----");
					setHamluong(dmThuoc.getDmthuocHamluong());
					setDmdonvitinhTen(dmThuoc.getDmdonvitinhMaso().getDmdonvitinhTen());	
					tkEx.getTnt(true).setThuocnoitruKho(getDmKho(dmThuoc.getDmthuocMaso(), tutrucPdt));
				}else {
					DmThuoc dmThuoc1 = dmThuocDelegate.findByTenThuoc(tenThuoc);
					if(dmThuoc1 != null){
						tkEx.getTnt(true).setThuocnoitruMathuoc(dmThuoc1);
						tkEx.getTnt(true).setThuocnoitruMien(dmThuoc1.getDmthuocMien());
						tkEx.getTnt(true).setThuocnoitruNDM(!dmThuoc1.getDmthuocIndanhmuc());
						tkEx.getTnt(true).getThuocnoitruMathuoc(true).setDmthuocTen(dmThuoc1.getDmthuocTen());
						tkEx.getTnt(true).setThuocnoitruLoai(dmThuoc1.getDmthuocYcu() == null ? "" : dmThuoc1.getDmthuocYcu().toString());					
						//logger.info("-----DA THAY DMTHUOC-----");
						setHamluong(dmThuoc1.getDmthuocHamluong());
						setDmdonvitinhTen(dmThuoc1.getDmdonvitinhMaso().getDmdonvitinhTen());	
						tkEx.getTnt(true).setThuocnoitruKho(getDmKho(dmThuoc1.getDmthuocMaso(), tutrucPdt));
					}else{					
						tkEx.getTnt(true).setThuocnoitruMathuoc(new DmThuoc());
						//logger.info("-----KHONG THAY DMTHUOC-----");
						setHamluong("");
						setDmdonvitinhTen("");
						setTonTong(new Double(0.0));
						tkEx.getTnt(true).setThuocnoitruKho(new DmKhoa());
					}
				}
				
			} else {
				tkEx.getTnt(true).setThuocnoitruMathuoc(new DmThuoc());
				setHamluong("");
				setDmdonvitinhTen("");
				setTonTong(new Double(0.0));
			}
			setIsKhongthu(false);
			setIsYeucau(false);
			if(tutrucPdt.equals("2") && hsba.getDoituongMa(true).getDmdoituongMa() != null && hsba.getDoituongMa(true).getDmdoituongMa().toUpperCase().equals("TP")){
				setTonTong(new Double(0.0));
				tkEx.getTnt(true).setThuocnoitruHangsx(null);
				tkEx.getTnt(true).setThuocnoitruQuocgia(null);
				tkEx.getTnt(true).setThuocnoitruSoluong(null);
				return;
			}
			//get so luong ton thuoc hien tai cua ma/ten thuoc do, tinh so lieu tong, set lai field tonTong
			Integer masoThuoc = tkEx.getTnt(true).getThuocnoitruMathuoc(true).getDmthuocMaso();
			if(masoThuoc != null){
				getTonKhoHienTai(masoThuoc, tutrucPdt);
				if(listTonKhoHienTai != null && listTonKhoHienTai.size()>0){
					TonKho tk = listTonKhoHienTai.get(0);					
					if(tk.getTonkhoMaphieukiem() != null){
						setTonTong(new Double(0.0));
						tkEx.getTnt(true).setThuocnoitruMathuoc(null);
						setCheckKiemKe("true");
					}else{
						Double ton = new Double(0.0);
						for(TonKho tk1: listTonKhoHienTai){
							ton += tk1.getTonkhoTon();
						}
						setTonTong(ton);
					}
				}
			}else{
				setTonTong(new Double(0.0));
			}
		}else{
			setTonTong(new Double(0.0));
		}
		tkEx.getTnt(true).setThuocnoitruHangsx(null);
		tkEx.getTnt(true).setThuocnoitruQuocgia(null);
		tkEx.getTnt(true).setThuocnoitruSoluong(null);
		//logger.info("-----END onblurTenThuocAction()-----");
	}	

	public void refreshDmThuoc(String isTutruc){
		//29-04-2011 - Lay danh muc thuoc theo kho chinh va kho noi tru neu ton cua cac thuoc do > 0
		if(isTutruc.equals("0")){//dang chon kho duoc
			//DieuTriUtilDelegate dieutriUtilDel = DieuTriUtilDelegate.getInstance();
			String dinhhuongThuocNT = IConstantsRes.DINH_HUONG_THUOC_NT;
			if(dinhhuongThuocNT.indexOf(',')>0){
				String khoaMa1 = dinhhuongThuocNT.substring(0,dinhhuongThuocNT.indexOf(','));
				String khoaMa2 = dinhhuongThuocNT.substring(dinhhuongThuocNT.indexOf(',')+1,dinhhuongThuocNT.length());
				DmKhoa khoa1 = (DmKhoa)dtUtil.findByMa(khoaMa1, "DmKhoa", "dmkhoaMa");
				DmKhoa khoa2 = (DmKhoa)dtUtil.findByMa(khoaMa2, "DmKhoa", "dmkhoaMa");
				listDmThuocAll = dmThuocDelegate.findAll(khoa1.getDmkhoaMaso(),khoa2.getDmkhoaMaso());
			}else{
				DmKhoa khoa1 = (DmKhoa)dtUtil.findByMa(dinhhuongThuocNT, "DmKhoa", "dmkhoaMa");
				if(khoa1.getDmkhoaMaso() != null){
					listDmThuocAll = dmThuocDelegate.findAll(khoa1.getDmkhoaMaso());
				}
			}
			tutrucPdtOLD = "0"; // trang thai list Thuoc hien tai
		} else if(isTutruc.equals("1")){//dang chon tu truc
			if(khoa.getDmkhoaMaso() != null){
				listDmThuocAll = dmThuocDelegate.findAll(khoa.getDmkhoaMaso());
			}
			tutrucPdtOLD = "1"; // trang thai list Thuoc hien tai
		} else if(isTutruc.equals("2")){//dang chon Ke toa xuat vien
			if(hsba != null && hsba.getDoituongMa(true).getDmdoituongMa() != null && hsba.getDoituongMa(true).getDmdoituongMa().toUpperCase().equals("TP")){//BN thu phi
				listDmThuocAll = dmThuocDelegate.findAll();
			}else{//BN mien phi, BHYT
				//DieuTriUtilDelegate dieutriUtilDel = DieuTriUtilDelegate.getInstance();
				String dinhhuongThuocNT = IConstantsRes.DINH_HUONG_THUOC_NT;
				if(dinhhuongThuocNT.indexOf(',')>0){
					String khoaMa1 = dinhhuongThuocNT.substring(0,dinhhuongThuocNT.indexOf(','));
					String khoaMa2 = dinhhuongThuocNT.substring(dinhhuongThuocNT.indexOf(',')+1,dinhhuongThuocNT.length());
					DmKhoa khoa1 = (DmKhoa)dtUtil.findByMa(khoaMa1, "DmKhoa", "dmkhoaMa");
					DmKhoa khoa2 = (DmKhoa)dtUtil.findByMa(khoaMa2, "DmKhoa", "dmkhoaMa");
					listDmThuocAll = dmThuocDelegate.findAll(khoa1.getDmkhoaMaso(),khoa2.getDmkhoaMaso());
				}else{
					DmKhoa khoa1 = (DmKhoa)dtUtil.findByMa(dinhhuongThuocNT, "DmKhoa", "dmkhoaMa");
					if(khoa1.getDmkhoaMaso() != null){
						listDmThuocAll = dmThuocDelegate.findAll(khoa1.getDmkhoaMaso());
					}
				}
			}
			tutrucPdtOLD = "2"; // trang thai list Thuoc hien tai
		}		
		listDmThuocs.clear();
		hmDmThuocAll.clear();
		
		// 20120604 bao.ttc: add chung voi ham FOR khi tao list ten Thuoc
//		for(DmThuoc o: listDmThuocAll){
//			hmDmThuocAll.put(o.getDmthuocMa(), o);
//		}
		
		if(listDmThuocAll != null && listDmThuocAll.size() > 0){
			if(searchType.equals("2")){
				//logger.info("-----Tim theo ten Khoa hoc-----");				
				for(DmThuoc each : listDmThuocAll){
					hmDmThuocAll.put(each.getDmthuocMa(), each);
					listDmThuocs.add(new SelectItem(each.getDmthuocTenkh() + " - " + each.getDmdonvitinhMaso(true).getDmdonvitinhTen() + " - " + each.getDmthuocTen() + " - " + each.getDmthuocMa()));
				}
			} else {
				//logger.info("-----Tim theo ten Thuong mai-----");
				for(DmThuoc each : listDmThuocAll){
					hmDmThuocAll.put(each.getDmthuocMa(), each);
					listDmThuocs.add(new SelectItem(each.getDmthuocTen() + " - " + each.getDmdonvitinhMaso(true).getDmdonvitinhTen() + " - " + each.getDmthuocTenkh() + " - " + each.getDmthuocMa()));
				}
			}
			listDmThuocAll.clear();
		}
	}
	
	public DmKhoa getDmKho(Integer masoThuoc, String isTutruc){
		DmKhoa dmKho = new DmKhoa();
		if(isTutruc.equals("0")){//dang chon kho duoc
			//Dua vao phan nhom 2, lay ma khoa tuong ung
			DmKhoaDelegate dmKhoaDel = DmKhoaDelegate.getInstance();
			Integer khoaMaso = dmKhoaDel.findMaSoByMasoThuoc(masoThuoc);
			dmKho = new DmKhoa(khoaMaso);
		}else if(isTutruc.equals("1")){//dang chon tu truc
			dmKho = khoa;
		}else if(isTutruc.equals("2")){
			if(hsba.getDoituongMa(true).getDmdoituongMa() != null && hsba.getDoituongMa(true).getDmdoituongMa().toUpperCase().equals("TP")){//BN thu phi
			}else{
				//Dua vao phan nhom 2, lay ma khoa tuong ung
				DmKhoaDelegate dmKhoaDel = DmKhoaDelegate.getInstance();
				Integer khoaMaso = dmKhoaDel.findMaSoByMasoThuoc(masoThuoc);
				dmKho = new DmKhoa(khoaMaso);
			}
		}
		return dmKho;
	}
	
	public void getTonKhoHienTai(Integer masoThuoc, String isTutruc){
		//get danh dach ton kho cac lo thuoc cua ma so thuoc do
		TonKhoDelegate tonkhoDel = TonKhoDelegate.getInstance();
		listTonKhoHienTai.clear();
		List<TonKho> lstTonKhoHT = new ArrayList<TonKho>();
		if(isTutruc.equals("0") || isTutruc.equals("2")){//dang chon kho duoc
			//Dua vao phan nhom 2, lay ma khoa tuong ung
			DmKhoaDelegate dmKhoaDel = DmKhoaDelegate.getInstance();
			Integer khoaMaso = dmKhoaDel.findMaSoByMasoThuoc(masoThuoc);
			if(khoaMaso > 0){
				lstTonKhoHT = tonkhoDel.getTonKhoHienTai(masoThuoc, khoaMaso, IConstantsRes.PRIORITY_TON_LO_THUOC);
			}			
		}else if(isTutruc.equals("1")){//dang chon tu truc
			if(khoa.getDmkhoaMaso() != null){
				lstTonKhoHT = tonkhoDel.getTonKhoHienTai(masoThuoc, khoa.getDmkhoaMaso(), IConstantsRes.PRIORITY_TON_LO_THUOC);
			}
		}		
		if(lstTonKhoHT != null){
			listTonKhoHienTai = lstTonKhoHT;
		}
	}
	
	public void displayTonInfo(){
		//kiem tra, neu so luong nhap chi thuoc tron trong 1 lo thi hien thi cac thong tin: han dung, NSX, HSX, Mien, NDM, ham luong, don gia cho lo do
		if(listTonKhoHienTai != null && listTonKhoHienTai.size()>0){
			if(tkEx.getTnt(true).getThuocnoitruMathuoc() != null){
				TonKho tk = listTonKhoHienTai.get(0);//lay lo dau tien
				if(tkEx.getTnt(true).getThuocnoitruSoluong() != null && tkEx.getTnt(true).getThuocnoitruSoluong() <= tk.getTonkhoTon()){
					String handungStr ="";
					if(tk.getTonkhoNamhandung() != null){
						if(tk.getTonkhoThanghandung() != null){
							if(tk.getTonkhoNgayhandung() != null){
								handungStr = tk.getTonkhoNgayhandung() +"/" + tk.getTonkhoThanghandung() +"/" + tk.getTonkhoNamhandung();
							}else{
								handungStr = tk.getTonkhoThanghandung() + "/" +tk.getTonkhoNamhandung();
							}
						}else{
							handungStr = tk.getTonkhoNamhandung();
						}
					}
					setHandung(handungStr);
					tkEx.getTnt(true).setThuocnoitruDongiatt(Utils.roundDoubleNumber(tk.getTonkhoDongia()));
					tkEx.getTnt(true).setThuocnoitruDongia(tk.getTonkhoDongia());
					tkEx.getTnt(true).setThuocnoitruHangsx(tk.getDmnhasanxuatMaso());
					tkEx.getTnt(true).setThuocnoitruQuocgia(tk.getDmquocgiaMaso());
				}
			}
		}
	}
	
	public void displayTonInfo1(){
		//kiem tra, neu so luong nhap chi thuoc tron trong 1 lo thi hien thi cac thong tin: han dung, NSX, HSX, Mien, NDM, ham luong, don gia cho lo do
		if(listTonKhoHienTai != null && listTonKhoHienTai.size()>0){
			if(tkEx.getTnt(true).getThuocnoitruMathuoc() != null){
				TonKho tk = listTonKhoHienTai.get(0);//lay lo dau tien
				if(sochia !=null && sobichia != null && sobichia != 0){
					Double soluongCap = Double.valueOf(sochia/sobichia);
					if(soluongCap <= tk.getTonkhoTon()){
						String handungStr ="";
						if(tk.getTonkhoNamhandung() != null){
							if(tk.getTonkhoThanghandung() != null){
								if(tk.getTonkhoNgayhandung() != null){
									handungStr = tk.getTonkhoNgayhandung() +"/" + tk.getTonkhoThanghandung() +"/" + tk.getTonkhoNamhandung();
								}else{
									handungStr = tk.getTonkhoThanghandung() + "/" +tk.getTonkhoNamhandung();
								}
							}else{
								handungStr = tk.getTonkhoNamhandung();
							}
						}
						setHandung(handungStr);
						tkEx.getTnt(true).setThuocnoitruDongiatt(Utils.roundDoubleNumber(tk.getTonkhoDongia()));
						tkEx.getTnt(true).setThuocnoitruDongia(tk.getTonkhoDongia());
						tkEx.getTnt(true).setThuocnoitruHangsx(tk.getDmnhasanxuatMaso());
						tkEx.getTnt(true).setThuocnoitruQuocgia(tk.getDmquocgiaMaso());
					}
				}
			}
		}
	}
	
	public void getDmThuocs(){
		if(tutrucPdt.equals("0")){			
			//DieuTriUtilDelegate dieutriUtilDel = DieuTriUtilDelegate.getInstance();
			DmKhoa khoa1 = (DmKhoa)dtUtil.findByMa("KCH", "DmKhoa", "dmkhoaMa");
			DmKhoa khoa2 = (DmKhoa)dtUtil.findByMa("KNT", "DmKhoa", "dmkhoaMa");
			listDmThuocAll = dmThuocDelegate.findAll(khoa1.getDmkhoaMaso(),khoa2.getDmkhoaMaso());
		}else if(tutrucPdt.equals("1")){
			if(khoa.getDmkhoaMaso() != null){
				listDmThuocAll = dmThuocDelegate.findAll(khoa.getDmkhoaMaso());
			}
		}else if(tutrucPdt.equals("2")){
			if(hsba.getDoituongMa(true).getDmdoituongMa() != null && hsba.getDoituongMa(true).getDmdoituongMa().toUpperCase().equals("TP")){//BN thu phi
				listDmThuocAll = dmThuocDelegate.findAll();
			}else{//BN mien phi, BHYT
				//DieuTriUtilDelegate dieutriUtilDel = DieuTriUtilDelegate.getInstance();
				String dinhhuongThuocNT = IConstantsRes.DINH_HUONG_THUOC_NT;
				if(dinhhuongThuocNT.indexOf(',')>0){
					String khoaMa1 = dinhhuongThuocNT.substring(0,dinhhuongThuocNT.indexOf(','));
					String khoaMa2 = dinhhuongThuocNT.substring(dinhhuongThuocNT.indexOf(',')+1,dinhhuongThuocNT.length());
					DmKhoa khoa1 = (DmKhoa)dtUtil.findByMa(khoaMa1, "DmKhoa", "dmkhoaMa");
					DmKhoa khoa2 = (DmKhoa)dtUtil.findByMa(khoaMa2, "DmKhoa", "dmkhoaMa");
					listDmThuocAll = dmThuocDelegate.findAll(khoa1.getDmkhoaMaso(),khoa2.getDmkhoaMaso());
				}else{
					DmKhoa khoa1 = (DmKhoa)dtUtil.findByMa(dinhhuongThuocNT, "DmKhoa", "dmkhoaMa");
					if(khoa1.getDmkhoaMaso() != null){
						listDmThuocAll = dmThuocDelegate.findAll(khoa1.getDmkhoaMaso());
					}
				}
			}
		}
		listDmThuocs.clear();
		hmDmThuocAll.clear();
		
		// 20120604 bao.ttc: add chung voi ham FOR khi tao list ten Thuoc
//		for(DmThuoc o: listDmThuocAll){
//			hmDmThuocAll.put(o.getDmthuocMa(), o);
//		}
		
		if(listDmThuocAll != null && listDmThuocAll.size() > 0){
			if(searchType.equals("2")){
				//logger.info("-----Tim theo ten Khoa hoc-----");
				for(DmThuoc each : listDmThuocAll){
					hmDmThuocAll.put(each.getDmthuocMa(), each);
					listDmThuocs.add(new SelectItem(each.getDmthuocTenkh() + " - " + each.getDmdonvitinhMaso(true).getDmdonvitinhTen() + " - " + each.getDmthuocTen() + " - " + each.getDmthuocMa()));
				}
			} else {
				//logger.info("-----Tim theo ten Thuong mai-----");
				for(DmThuoc each : listDmThuocAll){
					hmDmThuocAll.put(each.getDmthuocMa(), each);
					listDmThuocs.add(new SelectItem(each.getDmthuocTen() + " - " + each.getDmdonvitinhMaso(true).getDmdonvitinhTen() + " - " + each.getDmthuocTenkh() + " - " + each.getDmthuocMa()));
				}
			}
			listDmThuocAll.clear();
		}
	}

/** ==================== END LY THEM CODE ==================== */	
	
	public void reset() {
		listTkEx = new ArrayList<TonKhoEx>();
		listTkEx_Pdt = new ArrayList<TonKhoEx>();
		listTkEx_Tutruc = new ArrayList<TonKhoEx>();
		listTkEx_KeToa = new ArrayList<TonKhoEx>();
		checkThanhtoan ="block";
		checkLoad = false;
		hsba = new Hsba();
		hsbaKhoa = new HsbaKhoa();
		hsbaBhyt = new HsbaBhyt();
		tkExSelected = new TonKhoEx();
		tkEx = new TonKhoEx();
		if (khoa == null){
			khoa = new DmKhoa();
			tangChuyenDen = new DmTang();
		}
		// 20120413 bao.ttc: giu lai tang dang chon de nhap lieu nhanh: tangChuyenDen = new DmTang();
		maChiDan="";
		tenChiDan="";
		
		ngayHt = Utils.getCurrentDate();
		gioNhapThuoc = Utils.getGioPhut(new Date()); // 20120425 bao.ttc: them Gio cap nhat Thuoc
		
		ngayVv = "";
		lanVao = "";
		tkMa = 0;
		isUpdate = false;
		maPhieu = "";
		count = 0;
		tutrucPdt = "0";
		listTntDel = new ArrayList<ThuocNoiTru>();
		listTntDel_Pdt = new ArrayList<ThuocNoiTru>();
		listTntDel_Tutruc = new ArrayList<ThuocNoiTru>();
		listTntDel_KeToa = new ArrayList<ThuocNoiTru>();
		tongTien = new Double("0");
		
		refreshDmKhoaNT();
		
		//20110116 bao.ttc
		dmThuocDelegate = DmThuocDelegate.getInstance();

		if (!tutrucPdtOLD.equals("0")) {
			refreshDmThuoc("0");
		}
		
		isYeucau = false;
		isKhongthu = false;
		sovaovienOld = "";
		ngaySoLieuOld = "";
		khoaMaOld = "";
		checkKiemKe = "false";
		dmdonvitinhTen = "";
		handung ="";
		hamluong="";
		tonTong= new Double(0.0);
		sochia = null;
		sobichia = null;
		hsmListThuocDYNoiTru = new HashMap<Integer,List>();
		DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
		nhanVienCN = nvDelegate.findByNd(identity.getUsername());
		dmdoituongMa = "";
		//logger.info("-----end call reset()-----"+nhanVienCN);
	}
	
	public void reloadThuocNT(){	
		if(tutrucPdt.equals("0")){			
			listTkEx = listTkEx_Pdt; 
		}else if(tutrucPdt.equals("1")){
			listTkEx= listTkEx_Tutruc;
		}else{
			listTkEx= listTkEx_KeToa;
		}
		if (!tutrucPdtOLD.equals(tutrucPdt)) {
			refreshDmThuoc(tutrucPdt);
		}
		count = listTkEx.size();
	}
	
	public int getSobaithuoc() {
		return sobaithuoc;
	}

	public void setSobaithuoc(int sobaithuoc) {
		this.sobaithuoc = sobaithuoc;
	}
	
	public Integer getDmBaithuocMaso() {
		return dmBaithuocMaso;
	}

	public void setDmBaithuocMaso(Integer dmBaithuocMaso) {
		this.dmBaithuocMaso = dmBaithuocMaso;
	}


	public String getDmBaithuocMa() {
		return dmBaithuocMa;
	}


	public void setDmBaithuocMa(String dmBaithuocMa) {
		this.dmBaithuocMa = dmBaithuocMa;
	}
	
	//Xu ly su kien them bai thuoc
	public void addBaiThuoc() throws Exception{
		//logger.info("Begin addBaiThuoc, sobaithuoc = " + sobaithuoc);
		if(!checkThanhtoan.equals("none")){
			if(dmBaithuocMaso != null && dmBaithuocMaso != 0){
//				if(listThuocDY2Array.size() > 0){
//					//Da ton tai 1 bai thuoc trong toa, Khong duoc add bai thuoc nua
//					ThuocDongYNoiTru thuocDY = (ThuocDongYNoiTru)listThuocDY2Array.get(0).get(0);
//					DieuTriUtilDelegate dtUtilDel = DieuTriUtilDelegate.getInstance();
//					DmBaiThuoc dmBaiThuoc = (DmBaiThuoc)dtUtilDel.findByMaSo(thuocDY.getDmbaithuocMaso().getDmbaithuocMaso(), "DmBaiThuoc", "dmbaithuocMaso");
//					String tenbaithuoc = dmBaiThuoc.getDmbaithuocTen();
//					FacesMessages.instance().add(IConstantsRes.BAI_THUOC_DY_FOUNDED, tenbaithuoc);
//				}else{
					//kiem tra neu da add bai thuoc do roi thi khong cho add nua
					if(hsmListThuocDYNoiTru != null && hsmListThuocDYNoiTru.size() > 0){
						java.util.Set set = hsmListThuocDYNoiTru.entrySet();
					    Iterator i = set.iterator();
					    boolean founded = false;
					    while(i.hasNext()){
					    	Map.Entry me = (Map.Entry)i.next();
					    	Integer key = (Integer)me.getKey();
							if(dmBaithuocMaso.equals(key)){
								founded = true;
								break;
							}  										 		
					    }
					    if(founded){
					    	//DieuTriUtilDelegate dtUtilDel = DieuTriUtilDelegate.getInstance();
					    	DmBaiThuoc dmBaiThuoc = (DmBaiThuoc)dtUtil.findByMaSo(dmBaithuocMaso, "DmBaiThuoc", "dmbaithuocMaso");
					    	String tenbaithuoc = dmBaiThuoc.getDmbaithuocTen();
					    	FacesMessages.instance().add(IConstantsRes.BAI_THUOC_DY_FOUNDED, tenbaithuoc);
					    	return;
					    }
					}
					List<BaithuocThuoc> listbaithuocThuoc = (ArrayList<BaithuocThuoc>)dtUtil.findByAllConditions("BaithuocThuoc", "dmbaithuocMaso", "dmthuocMaso",dmBaithuocMaso+"","");
					if(listbaithuocThuoc.size() == 0){
						//Khong co vi thuoc trong bai thuoc
						FacesMessages.instance().add(IConstantsRes.BAI_THUOC_DY_NOTFOUND_VITHUOC);
						return;
					}
					//Kiem tra, neu ton cua tat ca cac vi con thi add tat ca xuong luoi
					//neu 1 trong cac vi k con ton thi khong cho add
					for(int i = 0; i < listbaithuocThuoc.size(); i++){
						BaithuocThuoc baithuocThuoc = listbaithuocThuoc.get(i);
						Double soluongcapBN = (double)(baithuocThuoc.getBaithuocthuocSoluong() * sobaithuoc);
						
						//get ton kho hien tai cua moi ma thuoc trong BaithuocThuoc
						getTonKhoHienTai(baithuocThuoc.getDmthuocMaso().getDmthuocMaso(), tutrucPdt);
						if(listTonKhoHienTai != null && listTonKhoHienTai.size()>0){
							Double tonHT = new Double(0.0);
							for(TonKho tk :listTonKhoHienTai){
								tonHT = tonHT + tk.getTonkhoTon();
								if(tk.getTonkhoMaphieukiem() != null){
									tonTong = 0.0;
									break;
								}else{
									tonTong = tonHT;
								}
							}						
							if(soluongcapBN > tonTong){
								FacesMessages.instance().add(IConstantsRes.XLBK_SOLUONGKHONGDU);
								return;
							}
						}else{
							FacesMessages.instance().add(IConstantsRes.XLBK_HETTONTHUOC, baithuocThuoc.getDmthuocMaso().getDmthuocMa());
							return;
						}
					}
					
					ThuocDongYNoiTru thuocDongY = new ThuocDongYNoiTru();
					thuocDongY.setThuocdongySoluong(sobaithuoc);
					thuocDongY.setThuocdongyNgaygiocn(new Date());
					thuocDongY.setDmbaithuocMaso(new DmBaiThuoc(dmBaithuocMaso));
					thuocDongY.setThuocdongyNhanviencn(nhanVienCN);
					//get don gia theo config, set theo loai: tu truc, pdt, xuat vien
					if(IConstantsRes.DONG_Y_APPLY_ALL.equals("1")){
						//Don gia lay tu config
						Double dongia_DongY = Double.parseDouble(IConstantsRes.EACH_THANGTHUOC_PRICE);
						thuocDongY.setThuocdongyDongia(dongia_DongY);
					}else{//Don gia theo don gia trong ton kho
						thuocDongY.setThuocdongyDongia(new Double(0.0));
					}
					thuocDongY.setThuocdongyLoai(tutrucPdt);
					thuocDongY.setThuocdongyKhoa(khoa);
					
					List listThuocDongYNoiTru = new ArrayList();
					listThuocDongYNoiTru.add(thuocDongY);//Khi vao ghi nhan, set lai so vao vien, khoa
					listThuocDongYNoiTru.add(new Boolean(false));
					listThuocDongYNoiTru.add(tutrucPdt);
					hsmListThuocDYNoiTru.put(dmBaithuocMaso, listThuocDongYNoiTru);
					
					if (tkEx != null && tkEx.getTnt(true).getThuocnoitruNgaygio() == null){
						tkEx.getTnt(true).setThuocnoitruNgaygio(new Date());
					}
					if (tkEx.getTnt(true).getThuocnoitruNgaygio() == null){
						tkEx.getTnt(true).setThuocnoitruNgaygio(new Date());
					}
					if (tkEx.getTnt(true).getThuocnoitruNhanviencn() == null){
						tkEx.getTnt(true).setThuocnoitruNhanviencn(nhanVienCN);
					}
					if (tkEx.getTnt(true).getThuocnoitruQuocgia(true).getDmquocgiaMaso() == null){
						tkEx.getTnt(true).getThuocnoitruQuocgia(true).setDmquocgiaMa("XXX");
					}
					if (tkEx.getTnt(true).getThuocnoitruHangsx() != null &&
							tkEx.getTnt(true).getThuocnoitruHangsx().getDmnhasanxuatMa() != null &&
							!tkEx.getTnt(true).getThuocnoitruHangsx().getDmnhasanxuatMa().equals("") 
							){
							// lay ten tu server ve
							//DieuTriUtilDelegate DTDel = DieuTriUtilDelegate.getInstance();
							DmNhaSanXuat nsxTmp = (DmNhaSanXuat)dtUtil.findByMa(tkEx.getTnt(true).getThuocnoitruHangsx().getDmnhasanxuatMa(), "DmNhaSanXuat", "dmnhasanxuatMa");
							if (nsxTmp != null){
								tkEx.getTnt(true).getThuocnoitruHangsx().setDmnhasanxuatTen(nsxTmp.getDmnhasanxuatTen());
							}
						
					}
					
					String doituongMa  ="";
					if(hsba.getDoituongMa(true).getDmdoituongMa() != null){
						doituongMa = hsba.getDoituongMa(true).getDmdoituongMa();
					}
					
					try{
						// Date dHt = df.parse(ngayHt);
						// 20120425 bao.ttc: them Gio cap nhat Thuoc
						Date dHt = new Date();
						if (gioNhapThuoc != null && !gioNhapThuoc.equals("") && ngayHt != null	&& !ngayHt.equals("")) {
							Calendar ngaygioNhapThuoc = Utils.getDBDate(gioNhapThuoc, ngayHt);
							if (ngaygioNhapThuoc != null) {
								dHt = ngaygioNhapThuoc.getTime();
							} else {
								dHt = new Date();
							}
						} // END -- 20120425 bao.ttc: them Gio cap nhat Thuoc
						
						int vitri=-1;
						//Tho add: 25/05/2011 Kiem tra truoc khi add xuong luoi, neu con ton kho cua tung vi thuoc, neu het thi k cho add va thong bao loi
						for(int i = 0; i < listbaithuocThuoc.size(); i++){
							BaithuocThuoc baithuocThuoc = listbaithuocThuoc.get(i);
							Double soluongcapBN = (double)(baithuocThuoc.getBaithuocthuocSoluong() * sobaithuoc);
							
							//get ton kho hien tai cua moi ma thuoc trong BaithuocThuoc
							getTonKhoHienTai(baithuocThuoc.getDmthuocMaso().getDmthuocMaso(), tutrucPdt);
							if(listTonKhoHienTai != null && listTonKhoHienTai.size()>0){
								ThuocNoiTru thuocNT = new ThuocNoiTru();							
								
								thuocNT.setHsbaKhoa(hsbaKhoa);
								thuocNT.setThuocnoitruKhoa(khoa);
								thuocNT.setThuocnoitruYeucau(isYeucau);
								thuocNT.setThuocnoitruKhongthu(isKhongthu);
								thuocNT.setThuocnoitruStatus("0");
								thuocNT.setThuocnoitruNgaygio(dHt);						
								thuocNT.setThuocnoitruNDM(!baithuocThuoc.getDmthuocMaso().getDmthuocIndanhmuc());
								thuocNT.setThuocnoitruNgaygiocn(new Date());
								thuocNT.setThuocnoitruNhanviencn(nhanVienCN);
								thuocNT.setDmbaithuocMaso(dmBaithuocMaso);
								thuocNT.setDmdoituongMaso(hsba.getDoituongMa(true));
								
								//DieuTriUtilDelegate dtUtilDel = DieuTriUtilDelegate.getInstance();
								DmDonViTinh dvt = (DmDonViTinh)dtUtil.findByMaSo(baithuocThuoc.getDmthuocMaso().getDmdonvitinhMaso().getDmdonvitinhMaso(), "DmDonViTinh", "dmdonvitinhMaso");
								thuocNT.setThuocnoitruMathuoc(baithuocThuoc.getDmthuocMaso());
								thuocNT.getThuocnoitruMathuoc().setDmdonvitinhMaso(dvt);
														
								if(tutrucPdt.equals("0") || tutrucPdt.equals("2")){//pdt or ke toa xuat vien
									if(tutrucPdt.equals("0")){
										thuocNT.setThuocnoitruTutrucPdt(0);
									}else if(tutrucPdt.equals("2")){
										thuocNT.setThuocnoitruTutrucPdt(1);
									}
									//Dua vao phan nhom 2, lay ma khoa tuong ung
									DmKhoaDelegate dmKhoaDel = DmKhoaDelegate.getInstance();
									Integer khoaMaso = dmKhoaDel.findMaSoByMasoThuoc(baithuocThuoc.getDmthuocMaso().getDmthuocMaso());
									thuocNT.setThuocnoitruKho(new DmKhoa(khoaMaso));
								}
								else if(tutrucPdt.equals("1")){//tu truc
									thuocNT.setThuocnoitruTutrucPdt(1);
									thuocNT.setThuocnoitruKho(khoa);
								}
								TonKhoEx tkExNew = (TonKhoEx) BeanUtils.cloneBean(tkEx);
								for(int j = 0; j<listTonKhoHienTai.size(); j++){
									TonKho tonkhoHienTai = listTonKhoHienTai.get(j);
									Double tonLo = tonkhoHienTai.getTonkhoTon();
									ThuocNoiTru ctThuocnoitruNew = new ThuocNoiTru();
									ctThuocnoitruNew = (ThuocNoiTru) BeanUtils.cloneBean(thuocNT);
									tkExNew.setIsAllowedUpdate(true);
									if(soluongcapBN <= tonLo){
										tkExNew.setTk(tonkhoHienTai);
										ctThuocnoitruNew.setThuocnoitruSoluong(soluongcapBN);
										setThuocNoiTruInfo(ctThuocnoitruNew, tonkhoHienTai, doituongMa);
										tkExNew.setTnt(ctThuocnoitruNew);
										if(vitri >-1){
											listTkEx.set(vitri,tkExNew);							
										}else{
											tkExNew.setThutu(listTkEx.size());
											listTkEx.add(tkExNew);
										}
										break;
									}else{
										soluongcapBN = soluongcapBN - tonLo;
										ctThuocnoitruNew.setThuocnoitruSoluong(tonLo);
										setThuocNoiTruInfo(ctThuocnoitruNew, tonkhoHienTai, doituongMa);						
										tkExNew.setTnt(ctThuocnoitruNew);
										tkExNew.setTk(tonkhoHienTai);
										tkExNew.setIsAllowedUpdate(true);
										if(vitri >-1){
											listTkEx.set(vitri,tkExNew);							
										}else{
											tkExNew.setThutu(listTkEx.size());
											listTkEx.add(tkExNew);
										}
									}							
								}
							}						
						}	
						dmBaithuocMa = "";
						dmBaithuocMaso = 0;
						
					}catch(Exception er){
						er.printStackTrace();
					}
					if (tutrucPdt.equals("0")){//Pdt
						listTkEx_Pdt = listTkEx;
					}else if (tutrucPdt.equals("1")){//tu truc					
						listTkEx_Tutruc = listTkEx;
					}else{
						listTkEx_KeToa = listTkEx;
					}
					tinhTien(listTkEx_Pdt, listTkEx_Tutruc,listTkEx_KeToa);
					tkEx = new TonKhoEx();
					count = listTkEx.size();
//				}
			}
			sobaithuoc = 1;
		}else{
			//Thong bao: khong cho them, xoa sua, vi benh an nay da thanh toan vien phi roi hoac khac khoa dang dieu tri
			FacesMessages.instance().add(IConstantsRes.HSBA_NOT_EDIT);
		}	
	}
	
	
	public void loadHsba() {
		//logger.info("-----loadHsba()-----"+ sovaovienOld + " - ngay so lieu cu:"+ ngaySoLieuOld + " - khoa cu: "+ khoaMaOld);
		String khoaMa = khoa.getDmkhoaMa();
		String hsbaMa = hsba.getHsbaSovaovien();
		int tangMaso = 0;
		if (tangChuyenDen != null && tangChuyenDen.getDmtangMaso() != null) {
			tangMaso = tangChuyenDen.getDmtangMaso();
		}
		
		HsbaKhoaDelegate hsbaKhoaDelegate = HsbaKhoaDelegate.getInstance();
		BenhNhanDelegate bnDelegate = BenhNhanDelegate.getInstance();
		HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();
		ThuocNoiTruDelegate tntDelegate = ThuocNoiTruDelegate.getInstance();
		
		if (!khoaMa.equals("") && hsbaMa != null && !hsbaMa.equals("") && (tangMaso != 0) ) {
			
			// 20120312 bao.ttc: phai them Tang de search list Thuoc chinh xac - Giao dien: them phan dmTang
			//hsbaKhoa = hsbaKhoaDelegate.findBySoVaoVienAndKhoaMa(hsba.getHsbaSovaovien().toUpperCase(),khoaMa.toUpperCase());
			hsbaKhoa = hsbaKhoaDelegate.findBySoVaoVienAndKhoaMaAndTang(hsba.getHsbaSovaovien().toUpperCase(),khoaMa.toUpperCase(), tangMaso);
			//Khong ton tai so vao vien
			if (hsbaKhoa == null) {
				FacesMessages.instance().add(IConstantsRes.HSBA_NULL, hsbaMa);				
				reset();
				return;
			}
			
			
			checkThanhtoan = "block";	
			hsba = hsbaKhoa.getHsbaSovaovien();			
			//neu khoa dang chon trung voi khoa dang dieu tri thi cho sua, nguoc lai khong cho sua
			if (!khoa.getDmkhoaMaso().equals(hsba.getHsbaKhoadangdt().getDmkhoaMaso())) {
				checkThanhtoan = "none";
				//checkLoad = false;
			}
			// 20120312 bao.ttc: kiem tra tuong tu voi Tang dang DT
			if (hsba.getTangDangdt() != null && !hsba.getTangDangdt().getDmtangMaso().equals(tangMaso) ) {
				checkThanhtoan = "none";
				//checkLoad = false;
			}
			checkLoad = true;
			
			if(!sovaovienOld.equals(hsba.getHsbaSovaovien()) || !ngaySoLieuOld.equals(ngayHt) || !khoaMaOld.equals(khoaMa)){
				tkExSelected = new TonKhoEx();
				tkEx = new TonKhoEx();
				ngayVv = "";
				lanVao = "";
				tkMa = 0;
				isUpdate = false;
				maPhieu = "";
				count = 0;
				listTntDel = new ArrayList<ThuocNoiTru>();
				listTkEx_Pdt = new ArrayList<TonKhoEx>();
				listTkEx_Tutruc = new ArrayList<TonKhoEx>();
				listTkEx_KeToa = new ArrayList<TonKhoEx>();
				hsmListThuocDYNoiTru = new HashMap<Integer,List>();
				tongTien = new Double("0");
				
				try {
					hsthtoan = HsThtoanDelegate.getInstance().findBySovaovien(hsba.getHsbaSovaovien().toUpperCase());
					if (hsthtoan == null || hsthtoan.getHsthtoanNgaytt() == null) {
					} else {
						if (!hsthtoan.getHsthtoanNgaytt().equals("")) {
							checkThanhtoan = "none";
							FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA);
						}
					}
					hsbaBhyt = hsbaBhytDelegate.findBySoVaoVienLastHsbaBhyt(hsbaMa.toUpperCase());
						
					if (hsbaBhyt == null) {
						hsbaBhyt = new HsbaBhyt();
					}
						
					Date dNgayVaoV = hsba.getHsbaNgaygiovaov();
					if (dNgayVaoV != null){
						ngayVv = df.format(dNgayVaoV);
					}
							
						
			        lanVao = bnDelegate.getLanVao(hsba.getBenhnhanMa().getBenhnhanMa().toUpperCase()).toString();
			            
			        //bao.ttc
			        //logger.info("#####-----##### hsba.getHsbaSovaovien(): " + hsba.getHsbaSovaovien());
			        //logger.info("#####-----##### khoaMa: " + khoaMa);
			        //logger.info("#####-----##### ngayHt: " + ngayHt);
			        
			        // 20120312 bao.ttc: tim list Thuoc them dieu kien Tang de chinh xac
			        //List<ThuocNoiTru> listTnt = tntDelegate.findBySoVaoVienAndKhoaMaAndLanAndNgay(hsba.getHsbaSovaovien().toUpperCase(),khoaMa.toUpperCase(),hsbaKhoa.getHsbakhoaLan(),ngayHt);
			        List<ThuocNoiTru> listTnt = tntDelegate.findBySoVaoVienAndKhoaMaAndTangAndNgay(hsba.getHsbaSovaovien().toUpperCase(),khoaMa.toUpperCase(), tangMaso, ngayHt);
			        
			        
			        if (listTnt != null && listTnt.size() > 0){
			        	for (int i = 0; i < listTnt.size(); i++) {
							TonKhoEx tkExt = new TonKhoEx();
							ThuocNoiTru thuoc = listTnt.get(i);					
							if (thuoc.getThuocnoitruTutrucPdt()!= null &&thuoc.getThuocnoitruTutrucPdt() == 1 ) {
								// la tu truc
								tkExt.setIsAllowedUpdate(false);
								tkExt.setTnt(thuoc);
								tkExt.setThutu(i);
								listTkEx_Tutruc.add(tkExt);
							} else if (thuoc.getThuocnoitruTutrucPdt()!= null &&thuoc.getThuocnoitruTutrucPdt() == 0 ){
								// la phieu du tru
								if ( thuoc.getThuocnoitruMaPhieuDT() == null && thuoc.getThuocnoitruStatus().equals("0") ) {
									// neu chua lap phieu du tru
									if(checkThanhtoan.equals("none")){
										tkExt.setIsAllowedUpdate(false);
									}else{
										tkExt.setIsAllowedUpdate(true);
									}
								}else{// da lap phieu du tru
									tkExt.setIsAllowedUpdate(false);
								}					
								tkExt.setTnt(thuoc);
								tkExt.setThutu(i);
								listTkEx_Pdt.add(tkExt);
							}else{
								// la toa thuoc xuat vien
								if ( thuoc.getThuocnoitruStatus().equals("0") ) {
									// neu chua lap phieu du tru
									if(checkThanhtoan.equals("none")){
										tkExt.setIsAllowedUpdate(false);
									}else{
										tkExt.setIsAllowedUpdate(true);
									}
								}else{// da lap phieu du tru
									tkExt.setIsAllowedUpdate(false);
								}								
								tkExt.setTnt(thuoc);
								tkExt.setThutu(i);
								listTkEx_KeToa.add(tkExt);
							}				
						}
			        }
			        dmdoituongMa = hsba.getDoituongMa().getDmdoituongMa();
			        
			        if (!tutrucPdtOLD.equals(tutrucPdt)) {
			        	refreshDmThuoc(tutrucPdt);//them dong nay de lay thuoc lai theo doi tuong va loai: kho duoc, tu truc, xuat vien
			        }
					
			        if(hsba.getDoituongMa().getDmdoituongMa().equals("TP")){
						
						// 20120312 bao.ttc: tim list Thuoc them dieu kien Tang de chinh xac
						//List<ThuocNoiTruXuatVien> listTntxv = tntDelegate.findThuocXVBySoVaoVienAndKhoaMaAndLanAndNgay(hsba.getHsbaSovaovien().toUpperCase(),khoaMa.toUpperCase(),hsbaKhoa.getHsbakhoaLan(),ngayHt);
						List<ThuocNoiTruXuatVien> listTntxv = tntDelegate.findThuocXVBySoVaoVienAndKhoaMaAndTangAndNgay(hsba.getHsbaSovaovien().toUpperCase(), khoaMa.toUpperCase(), tangMaso, ngayHt);
						
						if(listTntxv != null){
							for (int i = 0; i < listTntxv.size(); i++) {
								TonKhoEx tkExt = new TonKhoEx();
								ThuocNoiTruXuatVien thuocxv = listTntxv.get(i);
								
								ThuocNoiTru tnt = new ThuocNoiTru();
								tnt.setThuocnoitruMa(thuocxv.getThuocnoitruxvMa());
								tnt.setHsbaKhoa(thuocxv.getHsbaKhoa());
								tnt.setThuocnoitruBacsi(thuocxv.getThuocnoitruxvBacsi());
								tnt.setThuocnoitruBosung(thuocxv.getThuocnoitruxvBosung());
								tnt.setThuocnoitruCum(thuocxv.getThuocnoitruxvCum());
								tnt.setThuocnoitruKhoa(thuocxv.getThuocnoitruxvKhoa());
								tnt.setThuocnoitruLoai(thuocxv.getThuocnoitruxvLoai());
								tnt.setThuocnoitruTutrucPdt(2);
								tnt.setThuocnoitruMaphong(thuocxv.getThuocnoitruxvMaphong());
								tnt.setThuocnoitruMathuoc(thuocxv.getThuocnoitruxvMathuoc());
								tnt.setThuocnoitruNgaygio(thuocxv.getThuocnoitruxvNgaygio());
								tnt.setThuocnoitruNgaygiocn(thuocxv.getThuocnoitruxvNgaygiocn());
								tnt.setThuocnoitruNhanviencn(thuocxv.getThuocnoitruxvNhanviencn());
								tnt.setThuocnoitruPhanloai(thuocxv.getThuocnoitruxvPhanloai());
								tnt.setThuocnoitruPhong(thuocxv.getThuocnoitruxvPhong());
								tnt.setThuocnoitruSoluong(thuocxv.getThuocnoitruxvSoluong());
								tnt.setThuocnoitruStt(thuocxv.getThuocnoitruxvStt());
								tnt.setThuocnoitruMaChidan(thuocxv.getThuocnoitruxvMaChidan());
								tnt.setThuocnoitruTenchidan(thuocxv.getThuocnoitruxvTenchidan());
								if(checkThanhtoan.equals("none")){
									tkExt.setIsAllowedUpdate(false);
								}else{
									tkExt.setIsAllowedUpdate(true);
								}		
								tkExt.setTnt(tnt);
								tkExt.setThutu(i);
								listTkEx_KeToa.add(tkExt);
							}
						}						
					}
					if(tutrucPdt.equals("0")){					
						listTkEx = listTkEx_Pdt;
					}
					else if(tutrucPdt.equals("1")){					
						listTkEx = listTkEx_Tutruc;
					}else{
						listTkEx = listTkEx_KeToa;
					}
					ThuocDongYNoiTruDelegate thuocDYDel = ThuocDongYNoiTruDelegate.getInstance();
					Date ngayHtDate = new Date();
					try{
						ngayHtDate = df.parse(ngayHt);
					}catch(Exception er){}
					List<ThuocDongYNoiTru> lstThuocDYNoiTru = thuocDYDel.findBySoVaoVienandKhoaDTandNgayandLoai(hsba.getHsbaSovaovien().toUpperCase(), khoaMa.toUpperCase(), ngayHtDate, tutrucPdt);
					for(ThuocDongYNoiTru thuocDongYNoiTru:lstThuocDYNoiTru){
						List listThuocDongYNoiTru = new ArrayList();
						listThuocDongYNoiTru.add(thuocDongYNoiTru);
						listThuocDongYNoiTru.add(new Boolean(true));//neu da luu DB thi set true
						listThuocDongYNoiTru.add(tutrucPdt);
						
						hsmListThuocDYNoiTru.put(thuocDongYNoiTru.getDmbaithuocMaso().getDmbaithuocMaso(), listThuocDongYNoiTru);
					}
					
					chuyenManHinhThuocCLS_MaKhoa = khoa.getDmkhoaMa();
					chuyenManHinhThuocCLS_TangMaSo = tangChuyenDen.getDmtangMaso().toString();
					chuyenManHinhThuocCLS_SoBenhAn = hsba.getHsbaSovaovien();
					
					tinhTien(listTkEx_Pdt,listTkEx_Tutruc,listTkEx_KeToa);
					count = listTkEx.size();
					setSovaovienOld(hsba.getHsbaSovaovien());
					setNgaySoLieuOld(ngayHt);
					setKhoaMaOld(khoaMa);
				} catch (Exception e) {
					FacesMessages.instance().add(IConstantsRes.HSBA_NULL, hsbaMa);
					e.printStackTrace();
					reset();
				}	
			}else{
				if(listTkEx != null && listTkEx.size()>0){
					tinhTien(listTkEx_Pdt,listTkEx_Tutruc,listTkEx_KeToa);
					count = listTkEx.size();
				}
			}
		}				
	}
	
	public void themCt() {
		//logger.info("-----themCt()-----");
		if(hsba.getDoituongMa(true).getDmdoituongMa() != null){
			dmdoituongMa = hsba.getDoituongMa(true).getDmdoituongMa();
		}
		if(!checkThanhtoan.equals("none")){
			try{
				//Su dung doi tuong ctThuocNoiTruSelectedOld de luu lai doi tuong truoc khi chinh sua
				//khi chinh sua xong se xoa doi tuong nay va them vao 1 doi tuong da chinh sua	
				int viTri = -1;
				if(ctThuocNoiTruSelectedOld != -1 ){
					if (listTkEx.size() > ctThuocNoiTruSelectedOld){
						viTri = ctThuocNoiTruSelectedOld ;
						//cho phep sua doi thong tin khac cua thuoc noi tru, ngoai tru so luong
						if(viTri > -1 && !tkEx.getIsAllowedUpdate()){
							ThuocNoiTru thuocNT = (ThuocNoiTru) BeanUtils.cloneBean(tkEx.getTnt());
							thuocNT.setThuocnoitruYeucau(isYeucau);
							thuocNT.setThuocnoitruKhongthu(isKhongthu);
							thuocNT.setThuocnoitruMaChidan(maChiDan);
							thuocNT.setThuocnoitruTenchidan(tenChiDan);
							
							boolean mien = false;
							if(thuocNT.getThuocnoitruMien() != null && thuocNT.getThuocnoitruMien() == true){
								mien = true;
							}
							boolean yeucau = false;
							if(thuocNT.getThuocnoitruYeucau() != null && thuocNT.getThuocnoitruYeucau() == true){
								yeucau = true;
							}
							if(dmdoituongMa.toUpperCase().equals("MP")){
								if(mien && yeucau && !thuocNT.getThuocnoitruKhongthu()){
									thuocNT.setThuocnoitruDongia(thuocNT.getThuocnoitruDongia());				
								}else{
									thuocNT.setThuocnoitruDongia(new Double(0.0));				
								}
							}else {//BH va Thu phi
								if(thuocNT.getThuocnoitruKhongthu()){
									thuocNT.setThuocnoitruDongia(new Double(0.0));
								} else {
									thuocNT.setThuocnoitruDongia(thuocNT.getThuocnoitruDongia());
								}
							}
							thuocNT.setThuocnoitruDongiatt(Utils.roundDoubleNumber(thuocNT.getThuocnoitruDongia()));
							thuocNT.setThuocnoitruThanhtien(Utils.roundDoubleNumber(thuocNT.getThuocnoitruDongiatt()* thuocNT.getThuocnoitruSoluong()));
							
							tkEx.setTnt(thuocNT);
							listTkEx.set(viTri,tkEx);
							setHamluong("");
							setTonTong(new Double(0.0));
							setHandung("");
							dmdonvitinhTen ="";
							tkEx = new TonKhoEx();
							count = listTkEx.size();
							maChiDan ="";
							tenChiDan="";
							viTri = -1;
							isYeucau = false;
							isKhongthu = false;
							return;
						}else{
							listTkEx.remove(ctThuocNoiTruSelectedOld);
						}					
						ctThuocNoiTruSelectedOld = -1;						
					}else{
						ctThuocNoiTruSelectedOld = -1;
						tkEx = new TonKhoEx();
						return;
					}					
				}
											
				/*Kiem tra trong listTonKhoHienTai
				 * Xet cac truong hop sau
				 *   - Neu BN chua nhan thuoc: thuocnoitru_status = 0 va thuocnoitru_maphieudutru = null: chua lap phieu du tru: 
				 *   		+ duoc phep cong don tren luoi (lay cung thuocnoitru ma neu da luu vao DB su dung cho update)
				 *   		+ soluongcapBN = soluongcapBN + so luong tren luoi
				 *   		+ soluongcapBNNew = soluongcapBN: tong so luong cap BN bien nay de kiem tra tong xuat BN co <= tontong khong
				 *   - Neu BN chua nhan thuoc: thuocnoitru_status = 0 va thuocnoitru_maphieudutru != null: da lap phieu du tru: 
				 *   		+ khong duoc phep cong don
				 *   		+ soluongcapBN = soluongcapBN
				 *   		+ soluongcapBNNew = soluongcapBN + so luong tren luoi: tong so luong cap BN bien nay de kiem tra tong xuat BN co <= tontong khong
				 *   - Neu BN da nhan duoc thuoc: thuocnoitru_status = 2 va thuocnoitru_maphieudt != null: phieu du tru da xuat 
				 *   		+ khong cho phep cong don
				 *   		+ soluongcapBN = soluongcapBN
				 *   		+ soluongcapBNNew = soluongcapBN: tong so luong cap BN bien nay de kiem tra tong xuat BN co <= tontong khong
				*/
				getTonKhoHienTai(tkEx.getTnt().getThuocnoitruMathuoc().getDmthuocMaso(), tutrucPdt);
				Double soluongcapBN = tkEx.getTnt().getThuocnoitruSoluong();
				DecimalFormat decimalFormat = new DecimalFormat("#.#####");
				String soluongcapBN1 = decimalFormat.format(soluongcapBN);
				if(soluongcapBN1.length() > 4){
					soluongcapBN1 = soluongcapBN1.substring(0, soluongcapBN1.length()-1);
					soluongcapBN = Double.valueOf(soluongcapBN1);
				}			
				Double soluongDaCapTrenGrid = new Double(0.0);
				
				//SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				//SimpleDateFormat timedf = new SimpleDateFormat("HH:mm:ss");
				//Calendar cal = Calendar.getInstance();		    
				//String currenttime = timedf.format(cal.getTime());
				//Date dHt = df.parse(ngayHt+ " " + currenttime);
				
				// 20120425 bao.ttc: them Gio cap nhat Thuoc
				Date dHt = new Date();
				if (gioNhapThuoc != null && !gioNhapThuoc.equals("") && ngayHt != null	&& !ngayHt.equals("")) {
					Calendar ngaygioNhapThuoc = Utils.getDBDate(gioNhapThuoc, ngayHt);
					if (ngaygioNhapThuoc != null) {
						dHt = ngaygioNhapThuoc.getTime();
					} else {
						dHt = new Date();
					}
				} // END -- 20120425 bao.ttc: them Gio cap nhat Thuoc
				
				int vitri=-1;
				Integer thuocnoitru_ma = null;
				Double tongsoluongcapBN = new Double(0.0);
				if(listTkEx.size() >0){						
					for(int j = 0; j< listTkEx.size();j++){
						TonKhoEx tonkhoExGrid = listTkEx.get(j);
						ThuocNoiTru thuocnoitruGrid = tonkhoExGrid.getTnt();
						
						Boolean khongthu = ( thuocnoitruGrid.getThuocnoitruKhongthu() == null ? false : thuocnoitruGrid.getThuocnoitruKhongthu() );
						Boolean yeucau = ( thuocnoitruGrid.getThuocnoitruYeucau() == null ? false : thuocnoitruGrid.getThuocnoitruYeucau() );
						
						//neu kiem tra tren luoi neu cung ma thuoc nhung khac khong thu thi khong cong don
						if( tkEx.getTnt().getThuocnoitruMathuoc().getDmthuocMa().toUpperCase().equals(thuocnoitruGrid.getThuocnoitruMathuoc().getDmthuocMa().toUpperCase())
						   && isKhongthu.equals(khongthu) && isYeucau.equals(yeucau) ) {
							if(tonkhoExGrid.getIsAllowedUpdate()){
								soluongDaCapTrenGrid = soluongDaCapTrenGrid + thuocnoitruGrid.getThuocnoitruSoluong();
								vitri= j;
								thuocnoitru_ma = thuocnoitruGrid.getThuocnoitruMa();
							}else{//Khong duoc phep sua: kiem tra phieu du tru da xuat hay chua: neu chua xuat
								if(thuocnoitruGrid.getThuocnoitruStatus().equals("1") && thuocnoitruGrid.getThuocnoitruMaPhieuDT() != null){
									tongsoluongcapBN = tongsoluongcapBN + thuocnoitruGrid.getThuocnoitruSoluong();
								}							
							}
						}
					}
					soluongcapBN = soluongcapBN + soluongDaCapTrenGrid;
					tongsoluongcapBN = tongsoluongcapBN + soluongcapBN;
					String tongsoluongcapBN1 = decimalFormat.format(tongsoluongcapBN);
					tongsoluongcapBN = Double.valueOf(tongsoluongcapBN1);
					if(tongsoluongcapBN > tonTong){
						if(!dmdoituongMa.toUpperCase().equals("TP") && !tutrucPdt.equals("2")){
							tkEx.setTnt(null);
							tkEx.setTk(null);
							setHamluong("");
							setTonTong(new Double(0.0));
							setHandung("");
							maChiDan ="";
							tenChiDan="";
							FacesMessages.instance().add(IConstantsRes.XLBK_SOLUONGKHONGDU);
							return;
						}						
					}
				}				
				
				ThuocNoiTru thuocNT = new ThuocNoiTru();
				thuocNT = tkEx.getTnt();
				
				//Dua vao phan nhom 2, lay ma khoa tuong ung
				DmKhoaDelegate dmKhoaDel = DmKhoaDelegate.getInstance();
				Integer khoaMaso = dmKhoaDel.findMaSoByMasoThuoc(tkEx.getTnt(true).getThuocnoitruMathuoc(true).getDmthuocMaso());
				thuocNT.setThuocnoitruKho(dmKhoaDel.find(khoaMaso));
				thuocNT.setHsbaKhoa(hsbaKhoa);
				thuocNT.setThuocnoitruKhoa(khoa);
				thuocNT.setThuocnoitruYeucau(isYeucau);
				thuocNT.setThuocnoitruKhongthu(isKhongthu);
				thuocNT.setThuocnoitruStatus("0");
				thuocNT.setThuocnoitruNgaygio(dHt);	
				thuocNT.setThuocnoitruNgaygiocn(new Date());	
				thuocNT.setThuocnoitruMaPhieuDT(null);
				thuocNT.setThuocnoitruMaChidan(maChiDan);
				thuocNT.setThuocnoitruTenchidan(tenChiDan);
				thuocNT.setDmdoituongMaso(hsba.getDoituongMa(true));
				if(tutrucPdt.equals("0")){//pdt
					thuocNT.setThuocnoitruTutrucPdt(0);
				}
				else if(tutrucPdt.equals("1")){//tu truc
					thuocNT.setThuocnoitruTutrucPdt(1);
				}else{
					thuocNT.setThuocnoitruTutrucPdt(2);
				}
				TonKhoEx tkExNew = (TonKhoEx) BeanUtils.cloneBean(tkEx);
				if(listTonKhoHienTai != null && listTonKhoHienTai.size()>0){
					for(int i = 0; i<listTonKhoHienTai.size(); i++){
						TonKho tonkhoHienTai = listTonKhoHienTai.get(i);
						Double tonLo = tonkhoHienTai.getTonkhoTon();
						tkExNew = new TonKhoEx();
						ThuocNoiTru ctThuocnoitruNew = new ThuocNoiTru();
						ctThuocnoitruNew = (ThuocNoiTru) BeanUtils.cloneBean(thuocNT);
						tkExNew.setIsAllowedUpdate(true);
						if(soluongcapBN <= tonLo){	
							tkExNew.setTk(tonkhoHienTai);
							ctThuocnoitruNew.setThuocnoitruSoluong(soluongcapBN);
							setThuocNoiTruInfo(ctThuocnoitruNew, tonkhoHienTai, dmdoituongMa);
							tkExNew.setTnt(ctThuocnoitruNew);
							
							if(vitri >-1){	
								ThuocNoiTru tnt = tkExNew.getTnt();
								tnt.setThuocnoitruMa(thuocnoitru_ma);
								tkExNew.setTnt(tnt);
								listTkEx.set(vitri,tkExNew);								
							}else{
								tkExNew.setThutu(listTkEx.size());
								listTkEx.add(tkExNew);
							}
							break;
						}else{
							soluongcapBN = soluongcapBN - tonLo;
							ctThuocnoitruNew.setThuocnoitruSoluong(tonLo);	
							setThuocNoiTruInfo(ctThuocnoitruNew, tonkhoHienTai, dmdoituongMa);						
							tkExNew.setTnt(ctThuocnoitruNew);
							tkExNew.setTk(tonkhoHienTai);
							tkExNew.setIsAllowedUpdate(true);
							if(vitri >-1){
								ThuocNoiTru tnt = tkExNew.getTnt();
								tnt.setThuocnoitruMa(thuocnoitru_ma);
								tkExNew.setTnt(tnt);
								listTkEx.set(vitri,tkExNew);							
							}else{
								tkExNew.setThutu(listTkEx.size());
								listTkEx.add(tkExNew);
							}						
						}
						vitri=-1;
					}
				}else{//ton het, chi add thuoc vao luoi neu doi tuong la thu phi
					if(dmdoituongMa.toUpperCase().equals("TP") && tutrucPdt.equals("2")){
						ThuocNoiTru ctThuocnoitruNew = new ThuocNoiTru();
						ctThuocnoitruNew = (ThuocNoiTru) BeanUtils.cloneBean(thuocNT);
						tkExNew.setIsAllowedUpdate(true);
						tkExNew.setTnt(ctThuocnoitruNew);
						if(vitri >-1){
							listTkEx.set(vitri,tkExNew);							
						}else{
							tkExNew.setThutu(listTkEx.size());
							listTkEx.add(tkExNew);
						}
					}					
				}
				if (tutrucPdt.equals("0")){//Pdt
					listTkEx_Pdt = listTkEx;
				}else if (tutrucPdt.equals("1")){//tu truc					
					listTkEx_Tutruc = listTkEx;
				}else{
					listTkEx_KeToa = listTkEx;
				}
				tinhTien(listTkEx_Pdt, listTkEx_Tutruc,listTkEx_KeToa);
				setHamluong("");
				setTonTong(new Double(0.0));
				setHandung("");
				dmdonvitinhTen ="";
				tkEx = new TonKhoEx();
				count = listTkEx.size();
			}catch(Exception er){
				er.printStackTrace();
			}	
		}else{
			//Thong bao: khong cho them, xoa sua, vi benh an nay da thanh toan vien phi roi hoac khac khoa dang dieu tri
			FacesMessages.instance().add(IConstantsRes.HSBA_NOT_EDIT);
		}		
		maChiDan ="";
		tenChiDan="";
	}
	
	public void setThuocNoiTruInfo(ThuocNoiTru tnt, TonKho tonHt, String doituongMa){
		boolean mien = false;
		if(tnt.getThuocnoitruMien() != null && tnt.getThuocnoitruMien() == true){
			mien = true;
		}
		boolean yeucau = false;
		if(tnt.getThuocnoitruYeucau() != null && tnt.getThuocnoitruYeucau() == true){
			yeucau = true;
		}
		if(doituongMa.toUpperCase().equals("MP")){
			if(mien && yeucau && !tnt.getThuocnoitruKhongthu()){
				tnt.setThuocnoitruDongia(tonHt.getTonkhoDongia());				
			}else{
				tnt.setThuocnoitruDongia(new Double(0.0));				
			}
		}else {//BH va Thu phi
			if(doituongMa.toUpperCase().equals("TP") && tutrucPdt.equals("2")){
				tnt.setThuocnoitruDongia(new Double(0.0));
			}else{
				if(tnt.getThuocnoitruKhongthu() != null  && tnt.getThuocnoitruKhongthu()){
					tnt.setThuocnoitruDongia(new Double(0.0));
				} else {
					tnt.setThuocnoitruDongia(tonHt.getTonkhoDongia());
				}
			}			
		}
		tnt.setThuocnoitruDongiatt(Utils.roundDoubleNumber(tnt.getThuocnoitruDongia()));
		tnt.setThuocnoitruThanhtien(Utils.roundDoubleNumber(tnt.getThuocnoitruDongiatt()* tnt.getThuocnoitruSoluong()));
		tnt.setThuocnoitruDongiabh(tonHt.getTonkhoDongia());//report dung field nay cho so lieu su dung khoa, lap phieu du tru linh/tra dung field nay de set lai cho don gia. Vien phi khong dung field nay de tinh vien phi
		tnt.setThuocnoitruMalk(tonHt.getTonkhoMalienket());
		tnt.setThuocnoitruDongiaban(tonHt.getTonkhoDongiaban());		
		tnt.setThuocnoitruNamnhap(tonHt.getTonkhoNamnhap());
		tnt.setThuocnoitruHangsx(tonHt.getDmnhasanxuatMaso());
		tnt.setThuocnoitruQuocgia(tonHt.getDmquocgiaMaso());
		tnt.setThuocnoitruNgayhd(tonHt.getTonkhoNgayhandung());
		tnt.setThuocnoitruThanghd(tonHt.getTonkhoThanghandung());
		tnt.setThuocnoitruNamhd(tonHt.getTonkhoNamhandung());
		tnt.setThuocnoitruNguon(tonHt.getDmnctMaso());
	}
	
	public void getDataFromPreviousDate() {
		//logger.info("getDataFromPreviousDate begin");
		listTkEx = new ArrayList<TonKhoEx>();
		hsmListThuocDYNoiTru.clear();
		String khoaMa = khoa.getDmkhoaMa();
		String hsbaMa = hsba.getHsbaSovaovien();
		int tangMaso = 0;
		if (tangChuyenDen != null && tangChuyenDen.getDmtangMaso() != null) {
			tangMaso = tangChuyenDen.getDmtangMaso();
		}
		
		try{
			//Calendar cal = Calendar.getInstance();		    
			//String currenttime = timedf.format(cal.getTime());
			//Date dHt = df.parse(ngayHt+ " " + currenttime);
			
			// 20120425 bao.ttc: them Gio cap nhat Thuoc
			Date dHt = new Date();
			if (gioNhapThuoc != null && !gioNhapThuoc.equals("") && ngayHt != null	&& !ngayHt.equals("")) {
				Calendar ngaygioNhapThuoc = Utils.getDBDate(gioNhapThuoc, ngayHt);
				if (ngaygioNhapThuoc != null) {
					dHt = ngaygioNhapThuoc.getTime();
				} else {
					dHt = new Date();
				}
			} // END -- 20120425 bao.ttc: them Gio cap nhat Thuoc
			
			if (!khoaMa.equals("") && !hsbaMa.equals("") && (tangMaso != 0)) {
				ThuocNoiTruDelegate tntDelegate = ThuocNoiTruDelegate.getInstance();
				HsbaKhoaDelegate hsbaKhoaDelegate = HsbaKhoaDelegate.getInstance();
				
				// 20120312 bao.ttc: tim them dieu kien Tang de tim chinh xac
				//hsbaKhoa = hsbaKhoaDelegate.findBySoVaoVienAndKhoaMa(hsba.getHsbaSovaovien(),khoaMa);
				hsbaKhoa = hsbaKhoaDelegate.findBySoVaoVienAndKhoaMaAndTang(hsba.getHsbaSovaovien(),khoaMa, tangMaso);
				
				String sPreviousDate = (Integer.parseInt(ngayHt.substring(0, 2)) - 1) + ngayHt.substring(2);
				if (sPreviousDate.trim().length() < 10) {
					sPreviousDate = "0" + sPreviousDate;
				}
				//logger.info("getDataFromPreviousDate previousDate: " + sPreviousDate);
				
				// 20120312 bao.ttc: tim list Thuoc them dieu kien Tang de chinh xac
				//List<ThuocNoiTru> listTnt = tntDelegate.findBySoVaoVienAndKhoaMaAndLanAndNgay(hsbaMa.toUpperCase(), khoaMa.toUpperCase(), hsbaKhoa.getHsbakhoaLan(), sPreviousDate);
				List<ThuocNoiTru> listTnt = tntDelegate.findBySoVaoVienAndKhoaMaAndTangAndNgay(hsbaMa.toUpperCase(), khoaMa.toUpperCase(), tangMaso, sPreviousDate);
				
				if(listTnt != null && listTnt.size()>0){
					//Cong don so luong thuoc, neu cac lo co cung 1 ma thuoc					
					List<ThuocNoiTru> listTnt_Temp = new ArrayList<ThuocNoiTru>();
					for (ThuocNoiTru tnt : listTnt) {
						tnt.setThuocnoitruStatus("0");
						tnt.setThuocnoitruMa(null);
						tnt.setThuocnoitruNgaygio(dHt);
						tnt.setThuocnoitruNgaygiocn(new Date());
						tnt.setThuocnoitruMaPhieuDT(null);
						tnt.setThuocnoitruMaphieupdttra(null);
						tnt.setThuocnoitruMaphieu(null);
						tnt.setThuocnoitruTra(null);
						tnt.setThuocnoitruMalk(null);//K quan tam den ma lien ket, chi quan tam den ma thuoc, khi ghi nhan se set lai ma lk, don gia nay lai
						
						ThuocNoiTru ctThuocnoitruNew = new ThuocNoiTru();
						if(listTnt_Temp.size() == 0){	
							ctThuocnoitruNew = (ThuocNoiTru) BeanUtils.cloneBean(tnt);
							listTnt_Temp.add(ctThuocnoitruNew);
						}else{	
							ThuocNoiTru ctThuocnoitru_Finded = new ThuocNoiTru();
							boolean foundInList = false;
							int vitri = -1;
							for(int j = 0; j<listTnt_Temp.size(); j++){
								ThuocNoiTru ctThuocnoitruNew1 = listTnt_Temp.get(j);
								if(ctThuocnoitruNew1.getThuocnoitruMathuoc().getDmthuocMaso().equals(tnt.getThuocnoitruMathuoc().getDmthuocMaso())){
									ctThuocnoitru_Finded = ctThuocnoitruNew1;
									foundInList = true;
									vitri = j;
									break;
								}
							}
							if(foundInList == true){
								ctThuocnoitruNew = (ThuocNoiTru) BeanUtils.cloneBean(tnt);
								ctThuocnoitruNew.setThuocnoitruSoluong(tnt.getThuocnoitruSoluong() + ctThuocnoitru_Finded.getThuocnoitruSoluong());
								listTnt_Temp.set(vitri,ctThuocnoitruNew);
							}else{
								ctThuocnoitruNew = (ThuocNoiTru) BeanUtils.cloneBean(tnt);
								listTnt_Temp.add(ctThuocnoitruNew);
							}
						}
						ctThuocnoitruNew = new ThuocNoiTru();						
					}

					//Add list vao luoi
					int i = 0;
					for (ThuocNoiTru thuoc: listTnt_Temp) {
						TonKhoEx tkExt = new TonKhoEx();
						if (thuoc.getThuocnoitruTutrucPdt()!= null &&thuoc.getThuocnoitruTutrucPdt() == 1 ) {
							// la tu truc
							tkExt.setTnt(thuoc);
							tkExt.setIsAllowedUpdate(true);
							tkExt.setThutu(i);
							listTkEx_Tutruc.add(tkExt);						
						}else if (thuoc.getThuocnoitruTutrucPdt()!= null &&thuoc.getThuocnoitruTutrucPdt() == 0 ){
							// la phieu du tru
							tkExt.setTnt(thuoc);
							tkExt.setIsAllowedUpdate(true);
							tkExt.setThutu(i);
							listTkEx_Pdt.add(tkExt);
						}else{
							// la toa thuoc xuat vien
							tkExt.setTnt(thuoc);
							tkExt.setIsAllowedUpdate(true);
							tkExt.setThutu(i);
							listTkEx_KeToa.add(tkExt);
						}
						i++;
					}
					if (tutrucPdt.equals("0")){//Pdt
						listTkEx = listTkEx_Pdt;
					}else if (tutrucPdt.equals("1")){//tu truc
						listTkEx = listTkEx_Tutruc;
					}else{
						listTkEx = listTkEx_KeToa;
					}
					count = listTkEx.size();
				}
				//get thuoc dong y ung voi ngay truoc do: sPreviousDate
				Date previousDate = df.parse(sPreviousDate);
				ThuocDongYNoiTruDelegate thuocDYNoiTruDel = ThuocDongYNoiTruDelegate.getInstance();
				List<ThuocDongYNoiTru> lstThuocDYNoiTru = thuocDYNoiTruDel.findBySoVaoVienandKhoaDTandNgayandLoai(hsbaMa, khoaMa, previousDate, tutrucPdt);
				for(ThuocDongYNoiTru thuocDongYNoiTru:lstThuocDYNoiTru){
					List listThuocDongYNoiTru = new ArrayList();
					listThuocDongYNoiTru.add(thuocDongYNoiTru);
					listThuocDongYNoiTru.add(new Boolean(false));//neu da luu DB thi set true
					listThuocDongYNoiTru.add(tutrucPdt);
					
					hsmListThuocDYNoiTru.put(thuocDongYNoiTru.getDmbaithuocMaso().getDmbaithuocMaso(),listThuocDongYNoiTru);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//logger.info("getDataFromPreviousDate end");		
	}
	
	public void delete(int index) {
		//logger.info("-----delete(" + index + ")-----");
		maChiDan = "";
		tenChiDan = "";
		TonKhoEx tkExDel = listTkEx.remove(index);	
		if(tutrucPdt.equals("2") && hsba.getDoituongMa().getDmdoituongMa().toUpperCase().equals("TP")){
			listTntDel_KeToa.add(tkExDel.getTnt());
		}else{
			listTntDel.add(tkExDel.getTnt());
		}		
		if(tutrucPdt.equals("0")){//pdt	
			for (int i = 0; i < listTkEx_Pdt.size(); i++) {
				TonKhoEx tkExt = listTkEx_Pdt.get(i);
				tkExt.setThutu(i);
			}	
			listTkEx_Pdt = listTkEx;
		}else if(tutrucPdt.equals("1")){
			for (int i = 0; i < listTkEx_Tutruc.size(); i++) {
				TonKhoEx tkExt = listTkEx_Tutruc.get(i);
				tkExt.setThutu(i);
			}
			listTkEx_Tutruc = listTkEx;
		}else{
			for (int i = 0; i < listTkEx_KeToa.size(); i++) {
				TonKhoEx tkExt = listTkEx_KeToa.get(i);
				tkExt.setThutu(i);
			}
			listTkEx_KeToa = listTkEx;
		}
		//Kiem tra tren list listTkEx con lai, neu het thuoc dong y thi remove thang thuoc ra khoi list
		boolean isExistedThuocDY = false;
		for(TonKhoEx tkEx:listTkEx){			
			if(tkEx.getTnt().getDmbaithuocMaso() != null){//Con thuoc dong y
				isExistedThuocDY = true;
				break;
			}				
		}
		if(!isExistedThuocDY){
			hsmListThuocDYNoiTru.remove(tkExDel.getTnt().getDmbaithuocMaso());
		}
		tkEx = new TonKhoEx();
		setHamluong("");
		setDmdonvitinhTen("");
		setHandung("");
		setTonTong(new Double(0.0));
		count = listTkEx.size();
		tinhTien(listTkEx_Pdt,listTkEx_Tutruc,listTkEx_KeToa);
	}
	
	/**
	 *  Nhan nut ghi nhan
	 */
	public void end() {
		//logger.info("-----end()-----, dmkhoaMaso = " + khoa);
		
		// 20120508 bao.ttc: save user action log to database
		YteLogDelegate yteLogDele = YteLogDelegate.getInstance();
		YteLog yteLog = new YteLog();
		String yteLogString = "";
		String yteListData = "";
		SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE_TIME);

		yteLog.setForm("THUOC_Noi_Tru");
		yteLog.setUserId(identity == null ? "NULL" : identity.getUsername());

		yteLog.setObjectId(hsba == null ? "NULL" : hsba.getHsbaSovaovien());
		yteLogString += "Khoa:" + (khoa == null ? "NULL" : khoa.getDmkhoaMa())
					+ " - Tang:" + (tangChuyenDen == null ? "NULL" : tangChuyenDen.getDmtangMa())
					+ " - BAK:" + (hsbaKhoa == null ? "NULL" : hsbaKhoa.getHsbakhoaMaso());
		
		try{
			
			//set lai so vao vien va khoa, co the khi add bai thuoc, nguoi dung chua nhap so vao vien
			HashMap hsmThuocDYNTTemp = new HashMap<Integer,List>();
			if(hsmListThuocDYNoiTru != null && hsmListThuocDYNoiTru.size() > 0){
				java.util.Set set = hsmListThuocDYNoiTru.entrySet();
			    Iterator i = set.iterator();
			    while(i.hasNext()){
			    	Map.Entry me = (Map.Entry)i.next();
			    	List listThuocDY = (List)me.getValue();
					ThuocDongYNoiTru thuocDY = (ThuocDongYNoiTru)listThuocDY.get(0);
					Boolean isSavedDB = (Boolean)listThuocDY.get(1);
					thuocDY.setThuocdongyKhoa(khoa);
					thuocDY.setHsbaSovaovien(hsba);
					listThuocDY.set(0, thuocDY);
					hsmThuocDYNTTemp.put(thuocDY.getDmbaithuocMaso().getDmbaithuocMaso(), listThuocDY);
				}
			}
			hsmListThuocDYNoiTru = hsmThuocDYNTTemp;
			
			String doituongMa  =hsba.getDoituongMa().getDmdoituongMa();
			//Kiem tra ton kho cua thuoc lay tu toa thuoc truoc con ton hay khong, neu khong thi khong cho ghi nhan, thong bao thuoc do.
			//get ton kho hien tai de kiem tra neu so luong toa thuoc truoc du de xuat thi add ma lk cua ton do cho thuoc noi tru
			//Update lai listTkEx voi ton kho tuong ung voi don gia
			ArrayList<TonKhoEx> listTkEx_Temp = new ArrayList<TonKhoEx>();
			if(listTkEx.size()>0){
				for (TonKhoEx tkEx_: listTkEx) {
					ThuocNoiTru tnt = tkEx_.getTnt();
					if(tnt.getThuocnoitruMalk() == null || tnt.getThuocnoitruMalk() == ""){//thuoc nao co malk = null thi thuc hien lay ton hien tai moi nhat
						String isUseTuTruc = "";
						if(tnt.getThuocnoitruTutrucPdt()!= null && tnt.getThuocnoitruTutrucPdt() == 1){
							isUseTuTruc ="1";//tu truc
						} else if(tnt.getThuocnoitruTutrucPdt()!= null && tnt.getThuocnoitruTutrucPdt() == 0){
							isUseTuTruc ="0";//phieu du tru 
						}else{
							isUseTuTruc ="2";//toa thuoc xuat vien
						}
							
						Double soluongcapBN = tnt.getThuocnoitruSoluong();
						Double tongTon = 0.0;
						getTonKhoHienTai(tnt.getThuocnoitruMathuoc().getDmthuocMaso(), isUseTuTruc);
						if(listTonKhoHienTai != null && listTonKhoHienTai.size()>0){					
							for(int j = 0; j<listTonKhoHienTai.size(); j++){
								tongTon = tongTon + listTonKhoHienTai.get(j).getTonkhoTon();
							}					
						}
						if(soluongcapBN > tongTon){
							//Bao loi, khong co thuoc ton de cap cho BN
							if(isUseTuTruc.equals("2") && doituongMa.toUpperCase().equals("TP")){//xuat vien va thu phi thi k can kiem tra
								//khong lam gi ca
							}else{
								FacesMessages.instance().add(IConstantsRes.XLBK_HETTONTHUOC, tnt.getThuocnoitruMathuoc().getDmthuocMa());
								return;
							}							
						}
							
						TonKhoEx tkExNew = (TonKhoEx) BeanUtils.cloneBean(tkEx_);
						if(listTonKhoHienTai != null && listTonKhoHienTai.size()>0){
							for(int j = 0; j<listTonKhoHienTai.size(); j++){
								TonKho tonkhoHienTai = listTonKhoHienTai.get(j);
								Double tonLo = tonkhoHienTai.getTonkhoTon();
								tkExNew = new TonKhoEx();
								ThuocNoiTru ctThuocnoitruNew = new ThuocNoiTru();
								ctThuocnoitruNew = (ThuocNoiTru) BeanUtils.cloneBean(tnt);
								tkExNew.setIsAllowedUpdate(true);
								if(soluongcapBN <= tonLo){	
										tkExNew.setTk(tonkhoHienTai);
										ctThuocnoitruNew.setThuocnoitruSoluong(soluongcapBN);
										setThuocNoiTruInfo(ctThuocnoitruNew, tonkhoHienTai, doituongMa);
										tkExNew.setTnt(ctThuocnoitruNew);
										
										listTkEx_Temp.add(tkExNew);
										break;
								}else{
										soluongcapBN = soluongcapBN - tonLo;
										ctThuocnoitruNew.setThuocnoitruSoluong(tonLo);	
										setThuocNoiTruInfo(ctThuocnoitruNew, tonkhoHienTai, doituongMa);						
										tkExNew.setTnt(ctThuocnoitruNew);
										tkExNew.setTk(tonkhoHienTai);
										tkExNew.setIsAllowedUpdate(true);
										
										tkExNew.setThutu(listTkEx_Temp.size());
										listTkEx_Temp.add(tkExNew);
								}
							}
						}else{
							if(tutrucPdt.equals("2") && doituongMa.toUpperCase().equals("TP")){//doi tuong thu phi va chon xuat vien
								listTkEx_Temp.add(tkEx_);
							}
						}
					}else{
						listTkEx_Temp.add(tkEx_);
					}									
				}
			}			
			listTkEx = listTkEx_Temp;
			if(tutrucPdt.equals("0")){
				listTkEx_Pdt = listTkEx;
			}else if(tutrucPdt.equals("1")){
				listTkEx_Tutruc = listTkEx;
			}else{
				listTkEx_KeToa = listTkEx;
			}
			ArrayList<ThuocNoiTru> listTnt = new ArrayList<ThuocNoiTru>();
			if(listTkEx_Pdt.size()>0){
				yteListData += "#PDT: ";
				int stt = 1;
				for (TonKhoEx tkExNew: listTkEx_Pdt) {
					ThuocNoiTru thuoc = tkExNew.getTnt();
					listTnt.add(thuoc);
					// NHAT KY HE THONG
					yteListData += stt + "/ " + (thuoc.getThuocnoitruMathuoc() == null ? "NULL" : thuoc.getThuocnoitruMathuoc(true).getDmthuocMa())
								+ "  " + sdf.format(thuoc.getThuocnoitruNgaygio())
								+ "  SL:" + thuoc.getThuocnoitruSoluong()
								+ "  Gia:" + thuoc.getThuocnoitruDongiatt()
								//+ "  BAK:" + (thuoc.getHsbaKhoa() == null ? "NULL" : thuoc.getHsbaKhoa(true).getHsbakhoaMaso())
								+ " # ";
					stt++;
				}
			}
			if(listTkEx_Tutruc.size()>0){
				yteListData += "#TuTruc: ";
				int stt = 1;
				for (TonKhoEx tkExNew: listTkEx_Tutruc) {
					ThuocNoiTru thuoc = tkExNew.getTnt();
					listTnt.add(thuoc);
					// NHAT KY HE THONG
					yteListData += stt + "/ " + (thuoc.getThuocnoitruMathuoc() == null ? "NULL" : thuoc.getThuocnoitruMathuoc(true).getDmthuocMa())
								+ "  " + sdf.format(thuoc.getThuocnoitruNgaygio())
								+ "  SL:" + thuoc.getThuocnoitruSoluong()
								+ "  Gia:" + thuoc.getThuocnoitruDongiatt()
								//+ "  BAK:" + (thuoc.getHsbaKhoa() == null ? "NULL" : thuoc.getHsbaKhoa(true).getHsbakhoaMaso())
								+ " # ";
					stt++;
				}
			}
			ThuocNoiTruDelegate tntDelegate = ThuocNoiTruDelegate.getInstance();
			//Xet toa thuoc xuat vien: neu doi tuong khac thu phi thi ghi nhan binh thuong vao bang thuoc noi tru, nguoc lai thi ghi vao bang thuoc noi tru xuat vien
			List<ThuocNoiTruXuatVien> listTntXv = new ArrayList<ThuocNoiTruXuatVien>();
			if(listTkEx_KeToa.size()>0){
				if(!hsba.getDoituongMa().getDmdoituongMa().toUpperCase().equals("TP")){
					yteListData += "#ToaXV_BHYT: ";
					int stt = 1;
					for (TonKhoEx tkExNew: listTkEx_KeToa) {
						if( tkExNew.getIsAllowedUpdate() == true )
						{
							ThuocNoiTru thuoc = tkExNew.getTnt();
							listTnt.add(thuoc);
							// NHAT KY HE THONG
							yteListData += stt + "/ " + (thuoc.getThuocnoitruMathuoc() == null ? "NULL" : thuoc.getThuocnoitruMathuoc(true).getDmthuocMa())
										+ "  " + sdf.format(thuoc.getThuocnoitruNgaygio())
										+ "  SL:" + thuoc.getThuocnoitruSoluong()
										+ "  Gia:" + thuoc.getThuocnoitruDongiatt()
										//+ "  BAK:" + (thuoc.getHsbaKhoa() == null ? "NULL" : thuoc.getHsbaKhoa(true).getHsbakhoaMaso())
										+ " # ";
							stt++;
						}
					}
				}else{
					yteListData += "#ToaXV_ThuPhi: ";
					int stt = 1;
					for (TonKhoEx tkExNew: listTkEx_KeToa) {
						if( tkExNew.getIsAllowedUpdate() == true )
						{
							ThuocNoiTru thuoc = tkExNew.getTnt();
							ThuocNoiTruXuatVien thuocxv = new ThuocNoiTruXuatVien();
							thuocxv.setThuocnoitruxvMa(thuoc.getThuocnoitruMa());
							thuocxv.setHsbaKhoa(thuoc.getHsbaKhoa());
							thuocxv.setThuocnoitruxvBacsi(thuoc.getThuocnoitruBacsi());
							thuocxv.setThuocnoitruxvBosung(thuoc.getThuocnoitruBosung());
							thuocxv.setThuocnoitruxvCum(thuoc.getThuocnoitruCum());
							thuocxv.setThuocnoitruxvKhoa(thuoc.getThuocnoitruKhoa());
							thuocxv.setThuocnoitruxvLoai(thuoc.getThuocnoitruLoai());
							thuocxv.setThuocnoitruxvMaphong(thuoc.getThuocnoitruMaphong());
							thuocxv.setThuocnoitruxvMathuoc(thuoc.getThuocnoitruMathuoc());
							thuocxv.setThuocnoitruxvNgaygio(thuoc.getThuocnoitruNgaygio());
							thuocxv.setThuocnoitruxvNgaygiocn(thuoc.getThuocnoitruNgaygiocn());
							thuocxv.setThuocnoitruxvNhanviencn(thuoc.getThuocnoitruNhanviencn());
							thuocxv.setThuocnoitruxvPhanloai(thuoc.getThuocnoitruPhanloai());
							thuocxv.setThuocnoitruxvPhong(thuoc.getThuocnoitruPhong());
							thuocxv.setThuocnoitruxvSoluong(thuoc.getThuocnoitruSoluong());
							thuocxv.setThuocnoitruxvStt(thuoc.getThuocnoitruStt());
							thuocxv.setThuocnoitruxvMaChidan(thuoc.getThuocnoitruMaChidan());
							thuocxv.setThuocnoitruxvTenchidan(thuoc.getThuocnoitruTenchidan());
							listTntXv.add(thuocxv);
							// NHAT KY HE THONG
							yteListData += stt + "/ " + (thuocxv.getThuocnoitruxvMathuoc() == null ? "NULL" : thuocxv.getThuocnoitruxvMathuoc().getDmthuocMa())
										+ "  " + sdf.format(thuocxv.getThuocnoitruxvNgaygio())
										+ "  SL:" + thuocxv.getThuocnoitruxvSoluong()
										//+ "  BAK:" + (thuocxv.getHsbaKhoa() == null ? "" : thuocxv.getHsbaKhoa(true).getHsbakhoaMaso())
										+ " # ";
							stt++;
						}
					}
				}
			}
			List<ThuocNoiTruXuatVien> listTntXvDel = new ArrayList<ThuocNoiTruXuatVien>();
			if(listTntDel_KeToa.size()>0){
				yteLogString += " # Xoa_ToaXV: " + listTntDel_KeToa.size();
				for (ThuocNoiTru thuoc: listTntDel_KeToa){
					if(thuoc.getThuocnoitruTutrucPdt() == 2){//ke toa xuat vien								
						ThuocNoiTruXuatVien thuocxv = new ThuocNoiTruXuatVien();
						thuocxv.setThuocnoitruxvMa(thuoc.getThuocnoitruMa());
						thuocxv.setHsbaKhoa(thuoc.getHsbaKhoa());
						thuocxv.setThuocnoitruxvBacsi(thuoc.getThuocnoitruBacsi());
						thuocxv.setThuocnoitruxvBosung(thuoc.getThuocnoitruBosung());
						thuocxv.setThuocnoitruxvCum(thuoc.getThuocnoitruCum());
						thuocxv.setThuocnoitruxvKhoa(thuoc.getThuocnoitruKhoa());
						thuocxv.setThuocnoitruxvLoai(thuoc.getThuocnoitruLoai());
						thuocxv.setThuocnoitruxvMaphong(thuoc.getThuocnoitruMaphong());
						thuocxv.setThuocnoitruxvMathuoc(thuoc.getThuocnoitruMathuoc());
						thuocxv.setThuocnoitruxvNgaygio(thuoc.getThuocnoitruNgaygio());
						thuocxv.setThuocnoitruxvNgaygiocn(thuoc.getThuocnoitruNgaygiocn());
						thuocxv.setThuocnoitruxvNhanviencn(thuoc.getThuocnoitruNhanviencn());
						thuocxv.setThuocnoitruxvPhanloai(thuoc.getThuocnoitruPhanloai());
						thuocxv.setThuocnoitruxvPhong(thuoc.getThuocnoitruPhong());
						thuocxv.setThuocnoitruxvSoluong(thuoc.getThuocnoitruSoluong());
						thuocxv.setThuocnoitruxvStt(thuoc.getThuocnoitruStt());
						thuocxv.setThuocnoitruxvMaChidan(thuoc.getThuocnoitruMaChidan());
						thuocxv.setThuocnoitruxvTenchidan(thuoc.getThuocnoitruTenchidan());
						listTntXvDel.add(thuocxv);
					}
				}
			}
			if(listTntXv.size() > 0 || listTntXvDel.size() > 0){
				maPhieu = tntDelegate.createThuocNoiTruXuatVien(listTntXv, listTntXvDel);
			}
			
			if(listTnt.size()>0 || listTntDel.size() > 0){
				//logger.info("-----list listTnt: " + listTnt.size());
				//logger.info("-----list listTntDel: " + listTntDel.size());
				yteLogString += " # Xoa_Thuoc: " + listTntDel.size();
				maPhieu = tntDelegate.createThuocNoiTru(listTnt, listTntDel,hsmListThuocDYNoiTru);
			}			
			
			///////////////////////////////////////////////////
//			HsThtoanDelegate hsThtoanDelegate = HsThtoanDelegate.getInstance();
//			HsThtoan hsThtoan = hsThtoanDelegate.findBySovaovien(hsba.getHsbaSovaovien());			
//			if (hsThtoan != null) {				
//				hsThtoanDelegate.remove(hsThtoan);
//			}
//
//			hsThtoan = new HsThtoan();
//			hsThtoan.setHsbaSovaovien(hsba);
//			// phuc.lc 14-12-2010 Fix bug 1716 
//			hsThtoan.setHsthtoanKhoa(khoa);
//			
//			HoSoThanhToanUtil hsthtoanUtil = new HoSoThanhToanUtil(hsba);
//			hsthtoanUtil.tinhToanChoHSTT(hsThtoan);
//			Utils.setInforForHsThToan(hsThtoan);
//			hsThtoanDelegate.create(hsThtoan);
			///////////////////////////////////////////////////
			
			// 20120702 bao.ttc: THREAD RUN de tang toc do xu ly
			HsttThreadUtil capnhatHstt = new HsttThreadUtil();
			capnhatHstt.setHoSoBenhAn(hsba);
			capnhatHstt.start();
			
			//logger.info("-----maPhieu:" + maPhieu+ "##");
			if (!maPhieu.equals("")) {
				FacesMessages.instance().add(IConstantsRes.SUCCESS);
				hsmListThuocDYNoiTru.clear();
				setSovaovienOld("");
				setNgaySoLieuOld("");
				setKhoaMaOld("");
				loadHsba();	
				listTntDel.clear();
				listTntDel_KeToa.clear();
			} else {
				if(listTnt.size()>0 || listTntDel.size() > 0){
					FacesMessages.instance().add(IConstantsRes.FAIL);
				}				
			}		
			ctThuocNoiTruSelectedOld = -1;
			
			// 20120508 bao.ttc: save user action log to database
			yteLog.setLogString(yteLogString);
			yteLog.setListData(yteListData);
			yteLog.setDateTime(new Date());
			yteLogDele.create(yteLog);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void chinhSuaAJAX(int index){
		//logger.info("*****Begin chinhSuaAJAX()"+ " index: " + index);
		try{
			tkExSelected = listTkEx.get(index);
			if(tkExSelected == null){
				//logger.info("tkExSelected == null");
				return;
			}
			
			tkEx = (TonKhoEx)BeanUtils.cloneBean(tkExSelected) ;
			maChiDan = tkEx.getTnt().getThuocnoitruMaChidan();
			tenChiDan = tkEx.getTnt().getThuocnoitruTenchidan();
			
			if(dmdoituongMa.toUpperCase().equals("TP") && tutrucPdt.equals("2")){
				setTonTong(new Double(0.0));
			}else{
				getTonKhoHienTai(tkEx.getTnt().getThuocnoitruMathuoc().getDmthuocMaso(), tutrucPdt);
				Double tonHT = new Double(0.0);
				for(TonKho tk :listTonKhoHienTai){
					tonHT = tonHT + tk.getTonkhoTon();
					if(tk.getTonkhoMaphieukiem() != null){
						setTonTong(new Double(0.0));
						tkEx.getTnt().setThuocnoitruMathuoc(null);
						setCheckKiemKe("true");
						break;
					}else{
						setTonTong(tonHT);
					}
				}
			}			
			String handungStr ="";
			if(tkEx.getTnt().getThuocnoitruNamhd() != null ){
				if(!tkEx.getTnt().getThuocnoitruNamhd().equals("")){
					if(tkEx.getTnt().getThuocnoitruThanghd() != null ){
						if(tkEx.getTnt().getThuocnoitruThanghd().equals("")){
							handungStr = tkEx.getTnt().getThuocnoitruNamhd();
						}else{
							if(tkEx.getTnt().getThuocnoitruNgayhd() != null ){
								if(!tkEx.getTnt().getThuocnoitruNgayhd().equals("")){
									handungStr = tkEx.getTnt().getThuocnoitruNgayhd() +"/" + tkEx.getTnt().getThuocnoitruThanghd() +"/" + tkEx.getTnt().getThuocnoitruNamhd();
								}else{
									handungStr = tkEx.getTnt().getThuocnoitruThanghd() +"/" + tkEx.getTnt().getThuocnoitruNamhd();
								}
								
							}else{
								handungStr = tkEx.getTnt().getThuocnoitruThanghd()+ "/" +tkEx.getTnt().getThuocnoitruNamhd();
							}
						}				
					}else{
						handungStr = tkEx.getTnt().getThuocnoitruNamhd();
					}
				}			
			}
			setIsAllowedUpdateSoLuong(tkEx.getIsAllowedUpdate());
			setHandung(handungStr);
			setHamluong(tkEx.getTnt().getThuocnoitruMathuoc().getDmthuocHamluong());
			setIsYeucau(tkEx.getTnt().getThuocnoitruYeucau());
			setIsKhongthu(tkEx.getTnt().getThuocnoitruKhongthu());
			//DieuTriUtilDelegate dtUtilDel = DieuTriUtilDelegate.getInstance();
			DmDonViTinh dvt = (DmDonViTinh)dtUtil.findByMaSo(tkEx.getTnt().getThuocnoitruMathuoc().getDmdonvitinhMaso().getDmdonvitinhMaso(), "DmDonViTinh", "dmdonvitinhMaso");
			setDmdonvitinhTen(dvt.getDmdonvitinhTen());
			tkEx.getTnt().getThuocnoitruMathuoc().setDmdonvitinhMaso(dvt);
			ctThuocNoiTruSelectedOld = index;
			//logger.info("*****End chinhSuaAJAX()"+tkEx.getIsAllowedUpdate());
		}catch(Exception er){
			logger.info("ERROR: " + er.getMessage());
		}
	}
	
	private void tinhTien(List<TonKhoEx> listTkEx1, List<TonKhoEx> listTkEx2, List<TonKhoEx> listTkEx3) {
		tongTien = new Double("0");
		DecimalFormat decimalFormat = new DecimalFormat("#.####");
		
		if(listTkEx1.size()>0 && listTkEx1 != null){
			for (TonKhoEx tkExt : listTkEx1) {
				ThuocNoiTru thuoc = tkExt.getTnt();
				Double sl = thuoc.getThuocnoitruSoluong();
				String sl1 = decimalFormat.format(sl);
				sl = Double.valueOf(sl1);
				if (sl == null) {
					sl = new Double("0");
				}
				Double dg = thuoc.getThuocnoitruDongia();
				if (dg == null) {
					dg = new Double("0");
				}
				tongTien += sl * dg;
			}
		}
		if(listTkEx2.size()>0 && listTkEx2 != null){
			for (TonKhoEx tkExt : listTkEx2) {
				ThuocNoiTru thuoc = tkExt.getTnt();
				Double sl = thuoc.getThuocnoitruSoluong();
				String sl1 = decimalFormat.format(sl);
				sl = Double.valueOf(sl1);
				if (sl == null) {
					sl = new Double("0");
				}
				Double dg = thuoc.getThuocnoitruDongia();
				if (dg == null) {
					dg = new Double("0");
				}
				tongTien += sl * dg;
			}
		}
		if(listTkEx3.size()>0 && listTkEx3 != null){
			for (TonKhoEx tkExt : listTkEx3) {
				ThuocNoiTru thuoc = tkExt.getTnt();
				Double sl = thuoc.getThuocnoitruSoluong();
				String sl1 = decimalFormat.format(sl);
				sl = Double.valueOf(sl1);
				if (sl == null) {
					sl = new Double("0");
				}
				Double dg = thuoc.getThuocnoitruDongia();
				if (dg == null) {
					dg = new Double("0");
				}
				tongTien += sl * dg;
			}
		}
		//logger.info("-----tongTien: " + tongTien);
	}
	
	public String thuchienToaThuocAction(){
		//logger.info("Vao Method XuatReport bao cao ke toa xuat vien");
		reportTypeTD = "CapNhatThuocBNSD";
			
		String hsbaMa = hsba.getHsbaSovaovien();
		String baocao1 = null;
		try {
			//logger.info("bat dau method XuatReport ");
			String pathTemplate = "";
			String sub1Template = "";
			String sub2Template = "";
			String sub3Template = "";
			if(hsba != null && hsba.getDoituongMa(true).getDmdoituongMa().toUpperCase().equals("TP")){//Thu phi
				if(IConstantsRes.MAU_TOA_THUOC.equals("A4")){
					pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
										+ "hsba/toathuoc_thuphi.jrxml";
					sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
										+ "hsba/toathuoc_thuphi_sub1_A4.jrxml";
					sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
										+ "hsba/toathuoc_thuphi_sub2_A4.jrxml";
					sub3Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
										+ "hsba/toathuoc_thuphi_sub3_A4.jrxml";
				}else{
					pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
										+ "hsba/toathuoc_thuphi_main.jrxml";
					sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
										+ "hsba/toathuoc_thuphi_sub1.jrxml";
					sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
										+ "hsba/toathuoc_thuphi_sub2.jrxml";
					sub3Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
										+ "hsba/toathuoc_thuphi_sub3.jrxml";
				}
			}else{//BHYT & Mien Phi				
				if(IConstantsRes.MAU_TOA_THUOC.equals("A4")){
					pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
										+ "hsba/toathuoc_main_A4.jrxml";
					sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
										+ "hsba/toathuoc_sub1_A4.jrxml";
					sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
										+ "hsba/toathuoc_sub2_A4.jrxml";
					sub3Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
										+ "hsba/toathuoc_sub3_A4.jrxml";
				}else{
					pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
										+ "hsba/toathuoc_main.jrxml";
					sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
										+ "hsba/toathuoc_sub1.jrxml";
					sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
										+ "hsba/toathuoc_sub2.jrxml";
					sub3Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
										+ "hsba/toathuoc_sub3.jrxml";
				}
			}
				
			//logger.info("da thay file template " + pathTemplate);
			JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
			JasperReport sub1Report = JasperCompileManager.compileReport(sub1Template);
			JasperReport sub2Report = JasperCompileManager.compileReport(sub2Template);
			JasperReport sub3Report = JasperCompileManager.compileReport(sub3Template);

			SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
			//logger.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("DIENTHOAI",	IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);

			params.put("sub1", sub1Report);
			params.put("sub2", sub2Report);
			params.put("sub3", sub3Report);				
				
			params.put("SOVAOVIEN", hsbaMa);								

			params.put("HOTENBN", hsba.getBenhnhanMa(true).getBenhnhanHoten());
			String diachistr = "";
			if (hsba.getBenhnhanMa(true).getBenhnhanDiachi() != null){
				diachistr += hsba.getBenhnhanMa(true).getBenhnhanDiachi()+ ",";
			}
			if (hsba.getBenhnhanMa(true).getXaMa(true).getDmxaTen() != null){
				diachistr += hsba.getBenhnhanMa(true).getXaMa(true).getDmxaTen()+ ",";
			}
			if (hsba.getBenhnhanMa(true).getHuyenMa(true).getDmhuyenTen() != null){
				diachistr += hsba.getBenhnhanMa(true).getHuyenMa(true).getDmhuyenTen()+ ",";
			}
			if (hsba.getBenhnhanMa(true).getTinhMa(true).getDmtinhTen() != null){
				diachistr += hsba.getBenhnhanMa(true).getTinhMa(true).getDmtinhTen();
			}
			params.put("DIACHI", diachistr);
				
			String soTheTe_KhaiSinh_ChungSinh = "";
				
			HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();
			HsbaBhyt hsbaBhyt = hsbaBhytDelegate.findBySoVaoVienLastHsbaBhyt(hsbaMa);
				
			if(hsba.getDoituongMa(true).getDmdoituongMa().toUpperCase().equals("BH")){
				if (hsbaBhyt.getHsbabhytMakcb(true).getDmbenhvienTen() != null)
					params.put("NOIDKKCBBANDAU", hsbaBhyt.getHsbabhytMakcb(true).getDmbenhvienTen());
				if (hsbaBhyt.getHsbabhytGiatri0() != null) {
					params.put("GTTU", sdf.format(hsbaBhyt.getHsbabhytGiatri0()));
				} else {
					params.put("GTTU", "");
				}
				if (hsbaBhyt.getHsbabhytGiatri1() != null) {
					params.put("GTDEN", sdf.format(hsbaBhyt.getHsbabhytGiatri1()));
				} else {
					params.put("GTDEN", "");
				}
					
				if (hsbaBhyt.getHsbabhytSothebh() != null && !hsbaBhyt.getHsbabhytSothebh().equals("")) {
					String maBV =hsbaBhyt.getHsbabhytMakcb(true).getDmbenhvienMa();
					if (maBV == null) {
						maBV = "";
					}
					String maTheBHYT = hsbaBhyt.getHsbabhytSothebh();
					params.put("MATHEBHYT", getMaTheBHYTDisplay(maTheBHYT));
					params.put("MABENHVIEN", maBV);

				} else {
					params.put("MATHEBHYT", "");
					params.put("MABENHVIEN", "");
				}
					
				soTheTe_KhaiSinh_ChungSinh = hsbaBhyt.getHsbabhytKhaisinh();
			}else{
				params.put("NOIDKKCBBANDAU","");
				params.put("GTTU", "");
				params.put("GTDEN", "");
				params.put("MATHEBHYT", "");
				params.put("MABENHVIEN", "");
			}				
			params.put("NGAYDIEUTRI", df.parse(ngayHt));					
			params.put("BASIDIEUTRI", null);
			if(nhanVienCN != null){
				params.put("NHANVIENCN", nhanVienCN.getDtdmnhanvienTen());
			}else{
				params.put("NHANVIENCN", "");
			}
				
			int iTuoi = 1;
			if(hsba.getBenhnhanMa(true).getBenhnhanTuoi() != null)
				iTuoi =hsba.getBenhnhanMa(true).getBenhnhanTuoi();
			int iDonviTuoi = 1;
			if(hsba.getBenhnhanMa(true).getBenhnhanDonvituoi() != null)
				iDonviTuoi = hsba.getBenhnhanMa(true).getBenhnhanDonvituoi();
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
			params.put("GIOITINH", hsba.getBenhnhanMa().getDmgtMaso().getDmgtTen());
			
			if (iDonviTuoi == 1 && iTuoi > 6){
				params.put("CHAME", "");
			} else {
				if (hsba.getBenhnhanMa(true).getBenhnhanHotenchame() != null){
					String chame = "";
					chame = hsba.getBenhnhanMa(true).getBenhnhanHotenchame();
					params.put("CHAME", chame);
				}
			}
				
			boolean bVuotTuyen = false;
			// 20110413 bao.ttc: xet vuot Tuyen hay khong trong class HSTTK Util
			if (bVuotTuyen){
				params.put("VUOTTUYEN", "X");
			} else{
				params.put("VUOTTUYEN", "");
			}		
			
			HsbaChuyenMon hsbaChuyenMon = null; 
			HsbaChuyenMonDelegate chuyenMonDelegate = HsbaChuyenMonDelegate.getInstance();
			hsbaChuyenMon = chuyenMonDelegate.findBySoVaoVien_MaKhoa(hsba.getHsbaSovaovien(), hsba.getHsbaKhoadangdt(true).getDmkhoaMa());
			
			String chandoan = "";//hsba.getHsbaMachdravien(true).getDmbenhicdTen();
			if (hsbaChuyenMon != null){
				chandoan += hsbaChuyenMon.getHsbacmBenhchinh() == null ? "" : hsbaChuyenMon.getHsbacmBenhchinh(true).getDmbenhicdTen();
				chandoan += hsbaChuyenMon.getHsbacmDiengiaibc() == null ? "" : ", " + hsbaChuyenMon.getHsbacmDiengiaibc();
				chandoan += hsbaChuyenMon.getHsbacmBenhphu() == null ? "" : ", " + hsbaChuyenMon.getHsbacmBenhphu(true).getDmbenhicdTen();
				chandoan += hsbaChuyenMon.getHsbacmBenhphu2() == null ? "" : ", " + hsbaChuyenMon.getHsbacmBenhphu2(true).getDmbenhicdTen();
				chandoan += hsbaChuyenMon.getHsbacmBenhphu3() == null ? "" : ", " + hsbaChuyenMon.getHsbacmBenhphu3(true).getDmbenhicdTen();
				chandoan += hsbaChuyenMon.getHsbacmBenhphu4() == null ? "" : ", " + hsbaChuyenMon.getHsbacmBenhphu4(true).getDmbenhicdTen();
				chandoan += hsbaChuyenMon.getHsbacmBenhphu5() == null ? "" : ", " + hsbaChuyenMon.getHsbacmBenhphu5(true).getDmbenhicdTen();
			}
			if (chandoan.equals("")){
				hsbaChuyenMon = chuyenMonDelegate.findBySoVaoVien_MaKhoa(hsba.getHsbaSovaovien(), hsba.getHsbaKhoadangdtCm(true).getDmkhoaMa());
				if (hsbaChuyenMon != null){
					chandoan += hsbaChuyenMon.getHsbacmBenhchinh() == null ? "" : hsbaChuyenMon.getHsbacmBenhchinh(true).getDmbenhicdTen();
					chandoan += hsbaChuyenMon.getHsbacmDiengiaibc() == null ? "" : ", " + hsbaChuyenMon.getHsbacmDiengiaibc();
					chandoan += hsbaChuyenMon.getHsbacmBenhphu() == null ? "" : ", " + hsbaChuyenMon.getHsbacmBenhphu(true).getDmbenhicdTen();
					chandoan += hsbaChuyenMon.getHsbacmBenhphu2() == null ? "" : ", " + hsbaChuyenMon.getHsbacmBenhphu2(true).getDmbenhicdTen();
					chandoan += hsbaChuyenMon.getHsbacmBenhphu3() == null ? "" : ", " + hsbaChuyenMon.getHsbacmBenhphu3(true).getDmbenhicdTen();
					chandoan += hsbaChuyenMon.getHsbacmBenhphu4() == null ? "" : ", " + hsbaChuyenMon.getHsbacmBenhphu4(true).getDmbenhicdTen();
					chandoan += hsbaChuyenMon.getHsbacmBenhphu5() == null ? "" : ", " + hsbaChuyenMon.getHsbacmBenhphu5(true).getDmbenhicdTen();
				}
			}
			//logger.info("================= CHANDOAN: " + chandoan);
			params.put("CHANDOAN",chandoan);
			params.put("KHOADIEUTRI", khoa.getDmkhoaMaso());
				
			// 20111027 bao.ttc: them thong tin Mach, Huyet ap, Nhiet do
			String sHuyetApMin = "";
			String sHuyetApMax = "";
			String sHuyetAp = "";
			String sMach = "";
			String sNhietDo = "";
				
			TiepDon tiepdon;
			TiepDonDelegate tdDele = TiepDonDelegate.getInstance();
				
			if (hsba.getTiepdonMa() != null && !hsba.getTiepdonMa().equals("")){
				tiepdon = tdDele.find(hsba.getTiepdonMa());
				if (tiepdon != null){
						
					if (tiepdon.getTiepdonMach() != null){
						sMach = tiepdon.getTiepdonMach().toString();
						params.put("MACH", sMach);
					}
						
					if (tiepdon.getTiepdonHamax() != null){
						sHuyetApMax = tiepdon.getTiepdonHamax().toString();
					}
					if (tiepdon.getTiepdonHamin() != null){
						sHuyetApMin = tiepdon.getTiepdonHamin().toString();
					}
					sHuyetAp = sHuyetApMax + " / " + sHuyetApMin;
					if (tiepdon.getTiepdonHamax() != null || tiepdon.getTiepdonHamin() != null){
						params.put("HUYETAP", sHuyetAp);
					}
						
					if (tiepdon.getTiepdonNhietdo() != null){
						sNhietDo = tiepdon.getTiepdonNhietdo().toString();
						params.put("NHIETDO", sNhietDo);
					}
				} // END: tiepdon != null
			}
			// END 20111027 bao.ttc: them thong tin Mach, Huyet ap, Nhiet do
				
				
			if (soTheTe_KhaiSinh_ChungSinh != null && !soTheTe_KhaiSinh_ChungSinh.equals("")) {
				params.put("SOTHETEKSCS", soTheTe_KhaiSinh_ChungSinh);
			} else {
				params.put("SOTHETEKSCS", null);
			}

			//logger.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");			
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER,	IConstantsRes.DATABASE_PASS);
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
			jasperPrintTD = JasperFillManager.fillReport(jasperReport, params, conn);
			baocao1 = XuatReportUtil.ReportUtil(jasperPrintTD, index, IConstantsRes.PATH_BASE
																			+ IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI
																			+ "hsba/", "pdf", reportTypeTD);
			reportPathTD = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
			//logger.info("duong dan file xuat report :" + baocao1);
			//logger.info("duong dan -------------------- :" + reportPathTD);
			index+=1;
			//logger.info("Index :" + index);
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			logger.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
		//logger.info("Thoat Method XuatReport");
		return "B160_Chonmenuxuattaptin";
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

	public String getNgayVv() {
		return ngayVv;
	}

	public void setNgayVv(String ngayVv) {
		this.ngayVv = ngayVv;
	}

	public HsbaBhyt getHsbaBhyt() {
		return hsbaBhyt;
	}

	public void setHsbaBhyt(HsbaBhyt hsbaBhyt) {
		this.hsbaBhyt = hsbaBhyt;
	}

	public String getNgayHt() {
		return ngayHt;
	}

	public void setNgayHt(String ngayHt) {
		this.ngayHt = ngayHt;
	}

	public String getGioNhapThuoc() {
		return gioNhapThuoc;
	}

	public void setGioNhapThuoc(String gioNhapThuoc) {
		this.gioNhapThuoc = gioNhapThuoc;
	}

	public String getLanVao() {
		return lanVao;
	}

	public void setLanVao(String lanVao) {
		this.lanVao = lanVao;
	}

	public int getTkMa() {
		return tkMa;
	}

	public void setTkMa(int tkMa) {
		this.tkMa = tkMa;
	}

	public Hsba getHsba() {
		return hsba;
	}

	public void setHsba(Hsba hsba) {
		this.hsba = hsba;
	}

	public List<TonKhoEx> getListTkEx() {
		return listTkEx;
	}

	public void setListTkEx(ArrayList<TonKhoEx> listTkEx) {
		this.listTkEx = listTkEx;
	}

	public TonKhoEx getTkExSelected() {
		return tkExSelected;
	}

	public void setTkExSelected(TonKhoEx tkExSelected) {
		this.tkExSelected = tkExSelected;
	}

	public TonKhoEx getTkEx() {
		return tkEx;
	}

	public void setTkEx(TonKhoEx tkEx) {
		this.tkEx = tkEx;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public String getMaPhieu() {
		return maPhieu;
	}

	public void setMaPhieu(String maPhieu) {
		this.maPhieu = maPhieu;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public DmKhoa getKhoa() {
		return khoa;
	}

	public void setKhoa(DmKhoa khoa) {
		this.khoa = khoa;
	}

	public DmTang getTangChuyenDen() {
		return tangChuyenDen;
	}

	public void setTangChuyenDen(DmTang tangChuyenDen) {
		this.tangChuyenDen = tangChuyenDen;
	}

	public List<SelectItem> getListDmTangs() {
		return listDmTangs;
	}

	public void setListDmTangs(List<SelectItem> listDmTangs) {
		this.listDmTangs = listDmTangs;
	}

	public String getTutrucPdt() {
		return tutrucPdt;
	}

	public void setTutrucPdt(String tutrucPdt) {
		this.tutrucPdt = tutrucPdt;
	}
	
	public String getMalk() {
		return malk;
	}

	public void setMalk(String malk) {
		this.malk = malk;
	}

	public ArrayList<ThuocNoiTru> getListTntDel() {
		return listTntDel;
	}

	public void setListTntDel(ArrayList<ThuocNoiTru> listTntDel) {
		this.listTntDel = listTntDel;
	}

	public Double getTongTien() {
		return tongTien;
	}

	public void setTongTien(Double tongTien) {
		this.tongTien = tongTien;
	}

	public HsThtoan getHsthtoan() {
		return hsthtoan;
	}

	public void setHsthtoan(HsThtoan hsthtoan) {
		this.hsthtoan = hsthtoan;
	}

	public String getCheckThanhtoan() {
		return checkThanhtoan;
	}

	public void setCheckThanhtoan(String checkThanhtoan) {
		this.checkThanhtoan = checkThanhtoan;
	}
	
	public void selectRadioValue(javax.faces.event.ValueChangeEvent event)
	{
		javax.faces.component.html.HtmlSelectOneRadio radio=(javax.faces.component.html.HtmlSelectOneRadio) event.getComponent(); 
		if(radio.getValue().toString().equals("0")){
			setTutrucPdt("0");
			listTkEx = new ArrayList<TonKhoEx>();
			listTkEx = listTkEx_Pdt;
		}else if(radio.getValue().toString().equals("1")){
			setTutrucPdt("1");
			listTkEx = new ArrayList<TonKhoEx>();
			listTkEx = listTkEx_Tutruc;
		}else{
			setTutrucPdt("2");
			listTkEx = new ArrayList<TonKhoEx>();
			listTkEx = listTkEx_KeToa;
		}
		count = listTkEx.size();
	}
	
	public Boolean getIsYeucau() {
		return isYeucau;
	}

	public void setIsYeucau	(Boolean isYeucau) {
		this.isYeucau = isYeucau;
	}
	
	public Boolean getIsKhongthu() {
		return isKhongthu;
	}

	public void setIsKhongthu(Boolean isKhongthu) {
		this.isKhongthu = isKhongthu;
	}
	
	public String getSovaovienOld() {
		return sovaovienOld;
	}

	public void setSovaovienOld(String sovaovienOld) {
		this.sovaovienOld = sovaovienOld;
	}
	
	public String getNgaySoLieuOld() {
		return ngaySoLieuOld;
	}

	public void setNgaySoLieuOld(String ngaySoLieuOld) {
		this.ngaySoLieuOld = ngaySoLieuOld;
	}
	
	public String getKhoaMaOld() {
		return khoaMaOld;
	}

	public void setKhoaMaOld(String khoaMaOld) {
		this.khoaMaOld = khoaMaOld;
	}
	
	public String getCheckKiemKe() {
		return checkKiemKe;
	}

	public void setCheckKiemKe(String checkKiemKe) {
		this.checkKiemKe = checkKiemKe;
	}
	
	public String getDmdonvitinhTen() {
		return dmdonvitinhTen;
	}

	public void setDmdonvitinhTen(String dmdonvitinhTen) {
		this.dmdonvitinhTen = dmdonvitinhTen;
	}
	
	public Double getTonTong() {
		return tonTong;
	}

	public void setTonTong(Double tonTong) {
		this.tonTong = tonTong;
	}
	
	public String getHamluong() {
		return hamluong;
	}

	public void setHamluong(String hamluong) {
		this.hamluong = hamluong;
	}
	
	public String getHandung() {
		return handung;
	}

	public void setHandung(String handung) {
		this.handung = handung;
	}
	
	public Integer getSochia() {
		return sochia;
	}

	public void setSochia(Integer sochia) {
		this.sochia = sochia;
	}
	
	public Integer getSobichia() {
		return sobichia;
	}

	public void setSobichia(Integer sobichia) {
		this.sobichia = sobichia;
	}
	
	public Boolean getIsAllowedUpdateSoLuong() {
		return isAllowedUpdateSoLuong;
	}

	public void setIsAllowedUpdateSoLuong(Boolean isAllowedUpdateSoLuong) {
		this.isAllowedUpdateSoLuong = isAllowedUpdateSoLuong;
	}	
	
	public String getDmdoituongMa() {
		return dmdoituongMa;
	}
	
	public void setDmdoituongMa(String dmdoituongMa) {
		this.dmdoituongMa = dmdoituongMa;
	}

	public boolean isCheckLoad() {
		return checkLoad;
	}

	public void setCheckLoad(boolean checkLoad) {
		this.checkLoad = checkLoad;
	}
	
	
}

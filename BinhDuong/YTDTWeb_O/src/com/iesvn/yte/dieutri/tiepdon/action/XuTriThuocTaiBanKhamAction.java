package com.iesvn.yte.dieutri.tiepdon.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;

import javax.faces.model.SelectItem;

import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.jboss.seam.ScopeType;
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
import org.jboss.seam.security.Identity;

import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DmThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmBanKhamDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocDongYNgoaiTruDelegate;
import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
import com.iesvn.yte.dieutri.delegate.YteLogDelegate;
import com.iesvn.yte.dieutri.entity.BaithuocThuoc;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.DmBaiThuoc;
import com.iesvn.yte.dieutri.entity.DtDmBanKham;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.HsThtoank;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
import com.iesvn.yte.dieutri.entity.ThuocDongYNgoaiTru;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.entity.YteLog;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.entity.DmDonViTinh;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmNhaSanXuat;
import com.iesvn.yte.entity.DmThuoc;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.ThamKhamUtil;
import com.iesvn.yte.util.Utils;
import com.iesvn.yte.dieutri.entity.TonKho;

@Scope(CONVERSATION)
@Name("B121_3_Xutrithuoctaibankham")
@Synchronized(timeout = 6000000)
public class XuTriThuocTaiBanKhamAction implements Serializable {

	private static String FORMAT_DATE = "dd/MM/yyyy";
	private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";

	private static final long serialVersionUID = 10L;
	private int stt;
	private String ngaySinh;
	private String thoiGian;
	private String gioThamKham;
	private String mabs;
	private Integer masobs;
	
	//Phuc add
	private String tenbs;
	private String chandoan;
	private int sobaithuoc = 1;
	private static Logger log = Logger.getLogger(XuTriThuocTaiBanKhamAction.class);

	@In(scope=ScopeType.PAGE,required = false)
	@Out(scope=ScopeType.PAGE,required = false)
	private String initB121_3_Xutrithuoctaibankham;
	
	@DataModel
	private java.util.List<ThuocPhongKham> ctThuocPhongKhams;
	@DataModel
	private java.util.List<ThuocPhongKhamExt> listTPKBanKhamTruoc;//Tho add hien thi danh sach Thuoc phong kham ban kham truoc neu co
	
	@In(required = false)
	@Out(required = false)
	Identity identity;

//	@In(required = false)
//	@Out(required = false)
//	private String returnToXuatHangChoBNBHYT;
	
	@In(required = false)
	@Out(required = false)
	private String returnToThuocYDungCuPhongKham;
	
	@Out(required=false)
	@In(required=false)
	String fromMenu ;

	@In(required = false)
	@Out(required = false)
	private String maBanKhamOut;
	
	@In(required = false)
	@Out(required = false)
	private String maTiepDonOut;
	
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
	
//	private BenhNhan benhNhan;
	
	private String tenThuoc;
	
	private Integer khoaMaSo;
	private String khoaMa;

	private String sLoiDan;
	private String tenLoiDan;
	private String tenLoiDans;
	
	private String handung ="";
	private String hamluong="";
	private String dmdonvitinhTen;

	private ThamKham thamkham;
	private TiepDon tiepdonTPK;
	private BenhNhan benhnhanTPK;
	
	private HashMap<Integer,List> hsmListThuocDYNgoaiTru = new HashMap<Integer,List>();
	private Integer thuocdongyMaso;

	@DataModelSelection("ctThuocPhongKhams")
	@Out(required = false)
	private ThuocPhongKham ctThuocPhongKhamSelected;
	
	private int ctThuocPhongKhamSelectedOld = -1; //dung khi chinh sua 1 record

	private boolean updateNhapct = false;

	private ThuocPhongKham ctThuocPhongKham = null;
//	private ThamKhamWS thamKhamWS = null;
	private DmDoiTuong doiTuong = null;
	private Double tonTong = new Double(0.0);
	private List<TonKho> listTonKhoHienTai = new ArrayList<TonKho>();
	private String checkKiemKe ="false";
	
	@In(required = false)
	@Out(required = false)
	private String loaiToaThuocThamKhamVaXuTri ;	
	
	private String loaiToaThuocOld = "";
	private String titleThamKhamVaXuTri;
	
	private String resultReportFileName;
	private String resultReportName;	
	private String loaiFileXuat;
	@Out(required = false)
	private String isReport = "false";
	private String maFinish = "";
	private Integer dmBaithuocMaso  = 0;
	private String dmBaithuocMa;
	
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

	@End 
	public void end(){
		
	}
	
	/** ==================== BEGIN LY THEM CODE - Tho edit again ==================== */	
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
		//log.info("-----BEGIN onblurMaThuocAction()-----");
		//Tùy logic form mà set cho đúng
		if(ctThuocPhongKham != null && ctThuocPhongKham.getThuocphongkhamMathuoc(true) != null){
			String maThuoc = ctThuocPhongKham.getThuocphongkhamMathuoc(true).getDmthuocMa();
			if(hmDmThuocAll != null){
				DmThuoc dmThuoc1 = hmDmThuocAll.get(maThuoc.toUpperCase());
				if(dmThuoc1 != null) {
					ctThuocPhongKham = new ThuocPhongKham();
					DmThuoc dmThuoc2 = dmThuocDelegate.findByMaThuoc(maThuoc.toUpperCase());
					ctThuocPhongKham.setThuocphongkhamMathuoc(dmThuoc2);
					ctThuocPhongKham.setThuocphongkhamMien(dmThuoc2.getDmthuocMien());
					ctThuocPhongKham.setThuocphongkhamNdm(!dmThuoc2.getDmthuocIndanhmuc());
					ctThuocPhongKham.getThuocphongkhamMathuoc(true).setDmthuocTen(dmThuoc1.getDmthuocTen());					
					//log.info("-----DA THAY DMTHUOC-----");
					setHamluong(dmThuoc1.getDmthuocHamluong());
					setDmdonvitinhTen(dmThuoc1.getDmdonvitinhMaso().getDmdonvitinhTen());
				}
				else {
					//ctThuocPhongKham.setThuocphongkhamMathuoc(null);
					ctThuocPhongKham = new ThuocPhongKham();
					setHamluong("");
					setDmdonvitinhTen("");
					setTonTong(new Double(0.0));
				}
			}
		}else {
			//ctThuocPhongKham.setThuocphongkhamMathuoc(null);
			ctThuocPhongKham = new ThuocPhongKham();
			setHamluong("");
			setDmdonvitinhTen("");
			setTonTong(new Double(0.0));
		}
		setMaChiDan("");
		setTenChiDan("");
		if (!loaiToaThuocThamKhamVaXuTri.equals(Utils.KE_TOA_BENH_NHAN_TU_MUA)){
			//get so luong ton thuoc hien tai cua ma/ten thuoc do, tinh so lieu tong, set lai field tonTong
			
			if(ctThuocPhongKham.getThuocphongkhamMathuoc() != null){
				Integer masoThuoc = ctThuocPhongKham.getThuocphongkhamMathuoc().getDmthuocMaso();
				getTonKhoHienTai(masoThuoc);
				if(listTonKhoHienTai != null && listTonKhoHienTai.size()>0){
					TonKho tk = listTonKhoHienTai.get(0);					
					if(tk.getTonkhoMaphieukiem() != null){
						setTonTong(new Double(0.0));
						ctThuocPhongKham.setThuocphongkhamMathuoc(null);
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
		}		
		ctThuocPhongKham.setThuocphongkhamHangsx(null);
		ctThuocPhongKham.setThuocphongkhamQuocgia(null);
		//log.info("-----END onblurMaThuocAction()-----");
	}
	
	public void onblurTenThuocAction(){
		//log.info("-----BEGIN onblurTenThuocAction()-----");
		if(ctThuocPhongKham != null && ctThuocPhongKham.getThuocphongkhamMathuoc(true) != null){
			String tenThuoc = ctThuocPhongKham.getThuocphongkhamMathuoc(true).getDmthuocTen();
			//20110122 bao.ttc: kiem tra do dai ten thuoc (doi voi truong hop tu nhap, khong chon tu combobox)
			if(tenThuoc.length() > 6){
				String maThuoc = tenThuoc.substring(tenThuoc.length() - 6);    
				
				DmThuoc dmThuoc2 = dmThuocDelegate.findByMaThuoc(maThuoc);
				if(dmThuoc2 != null) {
					ctThuocPhongKham = new ThuocPhongKham();
					ctThuocPhongKham.setThuocphongkhamMathuoc(dmThuoc2);
					ctThuocPhongKham.getThuocphongkhamMathuoc(true).setDmthuocTen(dmThuoc2.getDmthuocTen());
					ctThuocPhongKham.setThuocphongkhamMien(dmThuoc2.getDmthuocMien());
					ctThuocPhongKham.setThuocphongkhamNdm(!dmThuoc2.getDmthuocIndanhmuc());
					//log.info("-----DA THAY DMTHUOC-----");
					setHamluong(dmThuoc2.getDmthuocHamluong());
					setDmdonvitinhTen(dmThuoc2.getDmdonvitinhMaso().getDmdonvitinhTen());
				} else {
					//ctThuocPhongKham.setThuocphongkhamMathuoc(null);
					ctThuocPhongKham = new ThuocPhongKham();
					setHamluong("");
					setDmdonvitinhTen("");
					setTonTong(new Double(0.0));
				}
			} else {
				//ctThuocPhongKham.setThuocphongkhamMathuoc(null);
				ctThuocPhongKham = new ThuocPhongKham();
				setDmdonvitinhTen("");
				setHamluong("");
				setTonTong(new Double(0.0));
			}
		}
		setMaChiDan("");
		setTenChiDan("");
		if (!loaiToaThuocThamKhamVaXuTri.equals(Utils.KE_TOA_BENH_NHAN_TU_MUA)){
			//get so luong ton thuoc hien tai cua ma/ten thuoc do, tinh so lieu tong, set lai field tonTong
			if(ctThuocPhongKham.getThuocphongkhamMathuoc() != null){
				Integer masoThuoc = ctThuocPhongKham.getThuocphongkhamMathuoc().getDmthuocMaso();
				getTonKhoHienTai(masoThuoc);
				if(listTonKhoHienTai != null && listTonKhoHienTai.size()>0){
					TonKho tk = listTonKhoHienTai.get(0);					
					if(tk.getTonkhoMaphieukiem() != null){
						setTonTong(new Double(0.0));
						setCheckKiemKe("true");
						ctThuocPhongKham.setThuocphongkhamMathuoc(null);
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
		}
		ctThuocPhongKham.setThuocphongkhamHangsx(null);
		ctThuocPhongKham.setThuocphongkhamQuocgia(null);
		//log.info("-----END onblurTenThuocAction()-----");
	}
	
	public void getTonKhoHienTai(Integer masoThuoc){
		//get danh dach ton kho cac lo thuoc cua ma so thuoc do
		TonKhoDelegate tonkhoDel = TonKhoDelegate.getInstance();
		listTonKhoHienTai.clear();
		List<TonKho> lstTonKhoHT = new ArrayList<TonKho>();
		lstTonKhoHT = tonkhoDel.getTonKhoHienTai(masoThuoc, khoaMaSo, IConstantsRes.PRIORITY_TON_LO_THUOC);
		if(lstTonKhoHT != null){
			listTonKhoHienTai = lstTonKhoHT;
		}
	}
	
	public void displayTonInfo(){
		//kiem tra, neu so luong nhap chi thuoc tron trong 1 lo thi hien thi cac thong tin: han dung, NSX, HSX, Mien, NDM, ham luong, don gia cho lo do
		if(listTonKhoHienTai != null && listTonKhoHienTai.size()>0){
			if(ctThuocPhongKham.getThuocphongkhamMathuoc() != null){
				TonKho tk = listTonKhoHienTai.get(0);//lay lo dau tien
				if(ctThuocPhongKham.getThuocphongkhamSoluong() != null && ctThuocPhongKham.getThuocphongkhamSoluong() <= tk.getTonkhoTon()){
					String handungStr ="";
					if(tk.getTonkhoNamhandung() != null || tk.getTonkhoNamhandung() != ""){
						if(tk.getTonkhoThanghandung() != null || tk.getTonkhoThanghandung() != ""){
							if(tk.getTonkhoNgayhandung() != null || tk.getTonkhoNgayhandung() != ""){
								handungStr = tk.getTonkhoNgayhandung() +"/" + tk.getTonkhoThanghandung() +"/" + tk.getTonkhoNamhandung();
							}else{
								handungStr = tk.getTonkhoThanghandung() + "/" +tk.getTonkhoNamhandung();
							}
						}else{
							handungStr = tk.getTonkhoNamhandung();
						}
					}
					setHandung(handungStr);
					ctThuocPhongKham.setThuocphongkhamDongiatt(Utils.roundDoubleNumber(tk.getTonkhoDongia()));
					ctThuocPhongKham.setThuocphongkhamDongia(tk.getTonkhoDongia());
					ctThuocPhongKham.setThuocphongkhamHangsx(tk.getDmnhasanxuatMaso());
					ctThuocPhongKham.setThuocphongkhamQuocgia(tk.getDmquocgiaMaso());
				}
			}
		}
	}
	
	public void refreshDmThuoc(){
		dmThuocDelegate = DmThuocDelegate.getInstance();
		listDmThuocs.clear();
		if(null == listDmThuocs || listDmThuocs.isEmpty()){
			//28-04-2011 - Lay danh muc thuoc theo khoa neu ton cua cac thuoc do > 0
			if (loaiToaThuocThamKhamVaXuTri.equals(Utils.KE_TOA_BENH_NHAN_TU_MUA)){
				listDmThuocAll = dmThuocDelegate.findAll();
			}else{
				if(!khoaMa.equals("KCH") && !khoaMa.equals("KBH") && !khoaMa.equals("KNT") && !khoaMa.equals("KTE")){
					listDmThuocAll = dmThuocDelegate.findAll(khoaMaSo);
				}else{
					listDmThuocAll = dmThuocDelegate.findAll(khoaMa);
				}
			}
			//log.info("listDmThuocAll.size: "+ listDmThuocAll.size());
		}	
		
		//Tho add
		hmDmThuocAll.clear();
		for(DmThuoc o: listDmThuocAll){
			hmDmThuocAll.put(o.getDmthuocMa(), o);
		}
		if(listDmThuocAll != null && listDmThuocAll.size() > 0){
			if(searchType.equals("2")){
				listDmThuocs.clear();
				for(DmThuoc each : listDmThuocAll){
					listDmThuocs.add(new SelectItem(each.getDmthuocTenkh() + " - " + each.getDmdonvitinhMaso(true).getDmdonvitinhTen() + " - " + each.getDmthuocTen() + " - " + each.getDmthuocMa()));
				}
			} else {
				listDmThuocs.clear();	
				for(DmThuoc each : listDmThuocAll){
					listDmThuocs.add(new SelectItem(each.getDmthuocTen() + " - " + each.getDmdonvitinhMaso(true).getDmdonvitinhTen() + " - " + each.getDmthuocTenkh() + " - " + each.getDmthuocMa()));
				}
			}
			listDmThuocAll.clear();
		}
	}

/** ==================== END LY THEM CODE ==================== */	

	///////////////////////////////////////////////////////////////	
	public String thuchienAction_toa_thuoc_quay(){
		if (Utils.KE_TOA_BENH_NHAN_TU_MUA.equals(loaiToaThuocThamKhamVaXuTri)) { // ke toa ve

			reportTypeTD = "KeToaVe";
		} else { // 
			reportTypeTD = "XuTriThuocTaiBanKham";
		}
		ThamKhamUtil tkUtil = new ThamKhamUtil();
		tkUtil.reportTypeTD = reportTypeTD;
		tkUtil.XuatReport_don_thuoc_ketoa_quay(log,  thamkham, listCtTPK_temp, 
				clslist, ctThuocPhongKhams,  index);
		jasperPrintTD = tkUtil.jasperPrintTD;
		reportPathTD=tkUtil.reportPathTD;
		reportTypeTD=tkUtil.reportTypeTD;
		index = index + 1;
		return "B160_Chonmenuxuattaptin";
	}
	
	private List<ThuocPhongKham> listCtTPK_temp = null; // day la thuoc tai ban kham, khi thanh toan kham -> tinh luon
	private List<ClsKham> clslist = null;
	
//	public String thuchienAction(){
//		XuatReport();
//		return "B160_Chonmenuxuattaptin";
//	}
	
	/**
	 * 
	 * @return
	 */
	public String troVe(){
		try {
			//log.info("***** troVe()");
			ctThuocPhongKhamSelectedOld = -1;
			
			ctThuocPhongKhams = null;
			ctThuocPhongKhamSelected= null;
			ctThuocPhongKham = null;
			thamkham=null;
			listTPKBanKhamTruoc = null;
			hsmListThuocDYNgoaiTru = null;
			listTonKhoHienTai = null;
			hmDmThuocAll = null;
			return "B121_3_Xutrithuoctaibankham";
		} 		
		catch (Exception e) {
			log.info("***** troVe() exception = " + e);    	
		} 
		return null;
	}
	
	////////////////////////////////////////////////////////////////
	
	private Set <Integer>rowsToUpdate;
	public Set <Integer>getRowsToUpdate() {
	 return rowsToUpdate;
	}

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
	
	//Xu ly su kien them bai thuoc
	public void addBaiThuoc() throws Exception{
		//log.info("Begin addBaiThuoc, sobaithuoc = " + sobaithuoc);
		if(dmBaithuocMaso != null && dmBaithuocMaso != 0){
			/*Kiem tra trong list xem da ton tai bai thuoc nao chua
			 * Neu ton tai roi thi khong cho add nua
			 * Neu chua ton tai thi add xuong
			 */
//			if(hsmListThuocDYNgoaiTru.size() > 0){
//				//Da ton tai 1 bai thuoc trong toa, Khong duoc add bai thuoc nua
//				ThuocDongYNgoaiTru thuocDY = (ThuocDongYNgoaiTru)listThuocDY2Array.get(0).get(0);
//				DieuTriUtilDelegate dtUtilDel = DieuTriUtilDelegate.getInstance();
//				DmBaiThuoc dmBaiThuoc = (DmBaiThuoc)dtUtilDel.findByMaSo(thuocDY.getDmbaithuocMaso().getDmbaithuocMaso(), "DmBaiThuoc", "dmbaithuocMaso");
//				String tenbaithuoc = dmBaiThuoc.getDmbaithuocTen();
//				FacesMessages.instance().add(IConstantsRes.BAI_THUOC_DY_FOUNDED, tenbaithuoc);
//			}else{
				//kiem tra neu da add bai thuoc do roi thi khong cho add nua
				if(hsmListThuocDYNgoaiTru != null && hsmListThuocDYNgoaiTru.size() > 0){
					java.util.Set set = hsmListThuocDYNgoaiTru.entrySet();
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
				    	DieuTriUtilDelegate dtUtilDel = DieuTriUtilDelegate.getInstance();
				    	DmBaiThuoc dmBaiThuoc = (DmBaiThuoc)dtUtilDel.findByMaSo(dmBaithuocMaso, "DmBaiThuoc", "dmbaithuocMaso");
				    	String tenbaithuoc = dmBaiThuoc.getDmbaithuocTen();
				    	FacesMessages.instance().add(IConstantsRes.BAI_THUOC_DY_FOUNDED, tenbaithuoc);
				    	return;
				    }
				}
				List<BaithuocThuoc> listbaithuocThuoc = (ArrayList<BaithuocThuoc>)DieuTriUtilDelegate.getInstance().findByAllConditions("BaithuocThuoc", "dmbaithuocMaso", "dmthuocMaso",dmBaithuocMaso+"","");
				if(listbaithuocThuoc.size() == 0){
					//Khong co vi thuoc trong bai thuoc
					FacesMessages.instance().add(IConstantsRes.BAI_THUOC_DY_NOTFOUND_VITHUOC);
					return;
				}
				//Kiem tra doi voi Xu ly ban kham va ke toa ve, neu ton cua tat ca cac vi con thi add tat ca xuong luoi
				//neu 1 trong cac vi k con ton thi khong cho add
				for(int i = 0; i < listbaithuocThuoc.size(); i++){
					BaithuocThuoc baithuocThuoc = listbaithuocThuoc.get(i);
					Double soluongThuocInBaithuoc = (double)(baithuocThuoc.getBaithuocthuocSoluong() * sobaithuoc);
					if (loaiToaThuocThamKhamVaXuTri.equals(Utils.XU_TRI_THUOC_TAI_BAN_KHAM) || loaiToaThuocThamKhamVaXuTri.equals(Utils.KE_TOA_QUAY_BENH_VIEN)){
						//get ton kho hien tai cua moi ma thuoc trong BaithuocThuoc
						getTonKhoHienTai(baithuocThuoc.getDmthuocMaso().getDmthuocMaso());
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
							if(soluongThuocInBaithuoc > tonTong){
								FacesMessages.instance().add(IConstantsRes.XLBK_SOLUONGKHONGDU);
								return;
							}
						}else{							
							FacesMessages.instance().add(IConstantsRes.XLBK_HETTONTHUOC, baithuocThuoc.getDmthuocMaso().getDmthuocMa());
							return;
						}
					}
				}
				ThuocDongYNgoaiTru thuocDongY = new ThuocDongYNgoaiTru();
				thuocDongY.setThuocdongySoluong(sobaithuoc);
				thuocDongY.setThuocdongyNgaygiocn(new Date());
				thuocDongY.setDmbaithuocMaso(new DmBaiThuoc(dmBaithuocMaso));
				thuocDongY.setThuocdongyNhanviencn(nhanVienCN);
				//get don gia theo config, set theo loai: xu tri ban kham, ke toa ve, quay BV
				if(IConstantsRes.DONG_Y_APPLY_ALL.equals("1")){
					//Don gia lay tu config
					Double dongia_DongY = Double.parseDouble(IConstantsRes.EACH_THANGTHUOC_PRICE);
					thuocDongY.setThuocdongyDongia(dongia_DongY);
				}else{//Don gia theo don gia trong ton kho
					thuocDongY.setThuocdongyDongia(new Double(0.0));
				}
				thuocDongY.setThuocdongyLoai(loaiToaThuocThamKhamVaXuTri);
				DmKhoa khoa = new DmKhoa();
				khoa.setDmkhoaMaso(khoaMaSo);
				thuocDongY.setThuocdongyKhoa(khoa);
				
				List listThuocDongYNgoaiTru = new ArrayList();
				listThuocDongYNgoaiTru.add(thuocDongY);//Khi vao ghi nhan, set lai tiep don ma, tham kham
				listThuocDongYNgoaiTru.add(new Boolean(false));
				listThuocDongYNgoaiTru.add(loaiToaThuocThamKhamVaXuTri);
				hsmListThuocDYNgoaiTru.put(dmBaithuocMaso,listThuocDongYNgoaiTru);
				
				if (ctThuocPhongKham.getThuocphongkhamNgaygio() == null){
					ctThuocPhongKham.setThuocphongkhamNgaygio(new Date());
				}
				if (thamkham.getThamkhamBacsi() != null && ctThuocPhongKham.getThuocphongkhamBacsi() == null){
					ctThuocPhongKham.setThuocphongkhamBacsi(thamkham.getThamkhamBacsi());
				}
				if (ctThuocPhongKham.getThuocphongkhamNgaygio() == null){
					ctThuocPhongKham.setThuocphongkhamNgaygio(new Date());
				}
				if (ctThuocPhongKham.getThuocphongkhamNhanviencn() == null){
					ctThuocPhongKham.setThuocphongkhamNhanviencn(nhanVienCN);
				}
				if (ctThuocPhongKham.getThuocphongkhamQuocgia(true).getDmquocgiaMaso() == null){
					ctThuocPhongKham.getThuocphongkhamQuocgia(true).setDmquocgiaMa("XXX");
				}
				
				if (ctThuocPhongKham.getThuocphongkhamHangsx() != null &&
						ctThuocPhongKham.getThuocphongkhamHangsx().getDmnhasanxuatMa() != null &&
						!ctThuocPhongKham.getThuocphongkhamHangsx().getDmnhasanxuatMa().equals("") 
						){
						// lay ten tu server ve
						DieuTriUtilDelegate DTDel = DieuTriUtilDelegate.getInstance();
						DmNhaSanXuat nsxTmp = (DmNhaSanXuat)DTDel.findByMa(ctThuocPhongKham.getThuocphongkhamHangsx().getDmnhasanxuatMa(), "DmNhaSanXuat", "dmnhasanxuatMa");
						if (nsxTmp != null){
							ctThuocPhongKham.getThuocphongkhamHangsx().setDmnhasanxuatTen(nsxTmp.getDmnhasanxuatTen());
						}
					
				}
				ctThuocPhongKham.setThuocphongkhamMaChidan(maChiDan);
				ctThuocPhongKham.setThuocphongkhamChidan(tenChiDan);
				
				//Tho add: 25/05/2011 Kiem tra truoc khi add xuong luoi, neu con ton kho cua tung vi thuoc, neu het thi k cho add va thong bao loi
				for(int i = 0; i < listbaithuocThuoc.size(); i++){
					BaithuocThuoc baithuocThuoc = listbaithuocThuoc.get(i);
					Double soluongThuocInBaithuoc = (double)(baithuocThuoc.getBaithuocthuocSoluong() * sobaithuoc);
					DieuTriUtilDelegate dtUtilDel = DieuTriUtilDelegate.getInstance();
					DmDonViTinh dvt = (DmDonViTinh)dtUtilDel.findByMaSo(baithuocThuoc.getDmthuocMaso().getDmdonvitinhMaso().getDmdonvitinhMaso(), "DmDonViTinh", "dmdonvitinhMaso");
					ctThuocPhongKham.setThuocphongkhamMathuoc(baithuocThuoc.getDmthuocMaso());
					ctThuocPhongKham.setThuocphongkhamNdm(!baithuocThuoc.getDmthuocMaso().getDmthuocIndanhmuc());
					ctThuocPhongKham.setThuocphongkhamMien(baithuocThuoc.getDmthuocMaso().getDmthuocMien());
					ctThuocPhongKham.getThuocphongkhamMathuoc().setDmdonvitinhMaso(dvt);
					ctThuocPhongKham.setDmbaithuocMaso(dmBaithuocMaso);
					ctThuocPhongKham.setThuocphongkhamChedott(tiepdonTPK.getDoituongMa(true));//luu doi tuong BHYT, thu phi, mien phi
					if (loaiToaThuocThamKhamVaXuTri.equals(Utils.XU_TRI_THUOC_TAI_BAN_KHAM) || loaiToaThuocThamKhamVaXuTri.equals(Utils.KE_TOA_QUAY_BENH_VIEN)){
						//get ton kho hien tai cua moi ma thuoc trong BaithuocThuoc
						getTonKhoHienTai(baithuocThuoc.getDmthuocMaso().getDmthuocMaso());
						if(listTonKhoHienTai != null && listTonKhoHienTai.size()>0){							
							for(int j = 0; j<listTonKhoHienTai.size(); j++){
								TonKho tonkhoHienTai = listTonKhoHienTai.get(j);
								Double tonLo = tonkhoHienTai.getTonkhoTon();
								ThuocPhongKham ctThuocPK = (ThuocPhongKham)BeanUtils.cloneBean(ctThuocPhongKham);
								if(soluongThuocInBaithuoc <= tonLo){
									ctThuocPK.setThuocphongkhamSoluong(soluongThuocInBaithuoc);
									setThuocPhongKhamInfo(ctThuocPK, tonkhoHienTai, tiepdonTPK.getDoituongMa(true).getDmdoituongMa());
									ctThuocPhongKhams.add(ctThuocPK);
									
									ctThuocPK = new ThuocPhongKham();
									break;
								}else{
									ctThuocPK = new ThuocPhongKham();
									ctThuocPK = (ThuocPhongKham)BeanUtils.cloneBean(ctThuocPhongKham);
									soluongThuocInBaithuoc = soluongThuocInBaithuoc - tonLo;
									ctThuocPK.setThuocphongkhamSoluong(tonLo);	
									setThuocPhongKhamInfo(ctThuocPK, tonkhoHienTai, tiepdonTPK.getDoituongMa(true).getDmdoituongMa());
									ctThuocPhongKhams.add(ctThuocPK);
									
									ctThuocPK = new ThuocPhongKham();
								}							
							}
						}
					}else{
						ctThuocPhongKham.setThuocphongkhamSoluong(soluongThuocInBaithuoc);
						ctThuocPhongKhams.add(ctThuocPhongKham);
					}	
					ctThuocPhongKham = new ThuocPhongKham();
				}//end for					
				dmBaithuocMa = "";
				dmBaithuocMaso = 0;
//			}				
			
		}
		sobaithuoc = 1;
	}
	//Xu ly su kien tab cua o cach dung.
	public void enterAJAX() throws Exception{
		//log.info("*****Begin enterAJAX()");	
		try{			
			/**
			 *  add thong tin Khi cập nhật chỉ định thuốc vào bảng THUOC_PHONG_KHAM, 
			 *  them các thông tin NGAYGIO, BACSI, 
			 *  các thông tin từ bảng TON_KHO, NGAYGIOCN, NHANVIENCN.
			 * 
			 */
			if (thamkham.getThamkhamBacsi() != null && ctThuocPhongKham.getThuocphongkhamBacsi() == null){
				ctThuocPhongKham.setThuocphongkhamBacsi(thamkham.getThamkhamBacsi());
			}
			if (ctThuocPhongKham.getThuocphongkhamNgaygio() == null){
				ctThuocPhongKham.setThuocphongkhamNgaygio(new Date());
			}
			if (ctThuocPhongKham.getThuocphongkhamNhanviencn() == null){
				ctThuocPhongKham.setThuocphongkhamNhanviencn(nhanVienCN);
			}
			if (ctThuocPhongKham.getThuocphongkhamQuocgia(true).getDmquocgiaMaso() == null){
				ctThuocPhongKham.getThuocphongkhamQuocgia(true).setDmquocgiaMa("XXX");
			}			
			
			//
			// Them ten vao hang san xuat
			//
			if (ctThuocPhongKham.getThuocphongkhamHangsx() != null &&
					ctThuocPhongKham.getThuocphongkhamHangsx().getDmnhasanxuatMa() != null &&
					!ctThuocPhongKham.getThuocphongkhamHangsx().getDmnhasanxuatMa().equals("") 
					){
					// lay ten tu server ve
					DieuTriUtilDelegate DTDel = DieuTriUtilDelegate.getInstance();
					DmNhaSanXuat nsxTmp = (DmNhaSanXuat)DTDel.findByMa(ctThuocPhongKham.getThuocphongkhamHangsx().getDmnhasanxuatMa(), "DmNhaSanXuat", "dmnhasanxuatMa");
					if (nsxTmp != null){
						ctThuocPhongKham.getThuocphongkhamHangsx().setDmnhasanxuatTen(nsxTmp.getDmnhasanxuatTen());
					}
				
			}
			ctThuocPhongKham.setThuocphongkhamMaChidan(maChiDan);
			ctThuocPhongKham.setThuocphongkhamChidan(tenChiDan);
						
			String doituongMa = tiepdonTPK.getDoituongMa(true).getDmdoituongMa();
			DieuTriUtilDelegate dtUtilDel = DieuTriUtilDelegate.getInstance();
			DmDonViTinh dvt = (DmDonViTinh)dtUtilDel.findByMaSo(ctThuocPhongKham.getThuocphongkhamMathuoc().getDmdonvitinhMaso().getDmdonvitinhMaso(), "DmDonViTinh", "dmdonvitinhMaso");
			ctThuocPhongKham.getThuocphongkhamMathuoc().setDmdonvitinhMaso(dvt);
			ctThuocPhongKham.setThuocphongkhamChedott(tiepdonTPK.getDoituongMa(true));//luu doi tuong BHYT, thu phi, mien phi
			//Su dung doi tuong ctThuocPhongKhamSelectedOld de luu lai doi tuong truoc khi chinh sua
			//khi chinh sua xong se xoa doi tuong nay va them vao 1 doi tuong da chinh sua		
			int viTriEdit = -1;
			if(ctThuocPhongKhamSelectedOld != -1 ){
				if (ctThuocPhongKhams.size() > ctThuocPhongKhamSelectedOld){
					viTriEdit = ctThuocPhongKhamSelectedOld ;
					
					// 20120221 bao.ttc: khong remove dong Thuoc can edit ngay ma kiem tra xem co trung Ma thuoc trong grid hay khong roi moi remove
					//ctThuocPhongKhams.remove(ctThuocPhongKhamSelectedOld);
					// set SL dong dinh edit = 0 de cong don khi thay doi SL
					if (ctThuocPhongKhams.get(viTriEdit) != null){
						ctThuocPhongKhams.get(viTriEdit).setThuocphongkhamSoluong(new Double(0.0));
					}
					// END -- 20120221 bao.ttc
					
					ctThuocPhongKhamSelectedOld = -1;				
				}else{
					ctThuocPhongKhamSelectedOld = -1;
					
					ctThuocPhongKham = new ThuocPhongKham();
					maChiDan = "";
					tenChiDan = "";
					setHamluong("");
					setTonTong(new Double(0.0));
					setHandung("");
					if (rowsToUpdate == null){
						rowsToUpdate = new HashSet<Integer>();
					}
					rowsToUpdate.clear();
					for (int i = 0 ; i < ctThuocPhongKhams.size();i++){
						rowsToUpdate.add(new Integer(i));
					}
					return;
				}				
			}
			
			/*Kiem tra trong listTonKhoHienTai 
			 * neu so luong cap cho BN <= so luong ton lo dau tien thi add 1 dong xuong luoi
			 * neu so luong cap cho BN > so luong ton cua lo dau tien thi xet den lo thu 2, k du xet ton lo ke tiep -> add nhieu dong xuong luoi
			*/
			//Tho add - Kiem tra so luong thuoc cap BN thuoc 1 lo hay nhieu lo thi se tao nhieu ctThuocPhongKham			
			if (loaiToaThuocThamKhamVaXuTri.equals(Utils.XU_TRI_THUOC_TAI_BAN_KHAM) || loaiToaThuocThamKhamVaXuTri.equals(Utils.KE_TOA_QUAY_BENH_VIEN)){
				getTonKhoHienTai(ctThuocPhongKham.getThuocphongkhamMathuoc().getDmthuocMaso());
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
				Double soluongcapBN = ctThuocPhongKham.getThuocphongkhamSoluong();
				
				if(loaiToaThuocThamKhamVaXuTri.equals(Utils.XU_TRI_THUOC_TAI_BAN_KHAM)){
					//Kiem tra dong lo nao tren luoi da luu DB, dong lo nao chua luu
					//Neu luu roi thi check voi so luong cap moi va ton kho hien tai
					//Neu chua luu thi soluongBN = soluongBN + so luong cap moi
					int vitriTrungMaThuocBK = -1;
					if(ctThuocPhongKhams.size() >0){
						Double soluongDaCapTrenGrid = new Double(0.0);
						for(int j = 0; j< ctThuocPhongKhams.size();j++){
							ThuocPhongKham thuocphongkhamGrid = ctThuocPhongKhams.get(j);
							//Neu du lieu chuan bi add tren luoi chua luu DB thi:
							//kiem tra tren luoi neu cung ma thuoc nhung khac khong thu thi khong cong don
							if(thuocphongkhamGrid.getThuocphongkhamMa() == null){
								if(ctThuocPhongKham.getThuocphongkhamMathuoc().getDmthuocMa().equals(thuocphongkhamGrid.getThuocphongkhamMathuoc().getDmthuocMa())
									&& ctThuocPhongKham.getThuocphongkhamKhongthu().equals(thuocphongkhamGrid.getThuocphongkhamKhongthu())){
									soluongDaCapTrenGrid = soluongDaCapTrenGrid + thuocphongkhamGrid.getThuocphongkhamSoluong();
									vitriTrungMaThuocBK= j;
								}								
							}
						}
						soluongcapBN = soluongcapBN + soluongDaCapTrenGrid;
						if(soluongcapBN > tonTong){
							// 20120221 bao.ttc: remove dong Thuoc can edit
							if (viTriEdit > -1){
								ctThuocPhongKhams.remove(viTriEdit);
								viTriEdit = -1;
							}
							
							ctThuocPhongKham = new ThuocPhongKham();
							maChiDan = "";
							tenChiDan = "";
							setHamluong("");
							setTonTong(new Double(0.0));
							setHandung("");
							FacesMessages.instance().add(IConstantsRes.XLBK_SOLUONGKHONGDU);
							return;
						}												
					}
					
					if(listTonKhoHienTai != null && listTonKhoHienTai.size()>0){
						for(int i = 0; i<listTonKhoHienTai.size(); i++){
							TonKho tonkhoHienTai = listTonKhoHienTai.get(i);
							Double tonLo = tonkhoHienTai.getTonkhoTon();
							ThuocPhongKham ctThuocPK = (ThuocPhongKham)BeanUtils.cloneBean(ctThuocPhongKham);
							if(soluongcapBN <= tonLo){
								ctThuocPK.setThuocphongkhamSoluong(soluongcapBN);
								setThuocPhongKhamInfo(ctThuocPK, tonkhoHienTai, doituongMa);
								//Xoa list tren luoi va add lai
								if(vitriTrungMaThuocBK >-1){
									ctThuocPhongKhams.set(vitriTrungMaThuocBK,ctThuocPK);
									
									// 20120221 bao.ttc: remove dong Thuoc can edit
									if (viTriEdit > -1 && viTriEdit != vitriTrungMaThuocBK ){
										ctThuocPhongKhams.remove(viTriEdit);
										viTriEdit = -1;
									}
								} else {
									// 20120221 bao.ttc: add tai vi tri Thuoc can edit
									if (viTriEdit > -1){
										ctThuocPhongKhams.set(viTriEdit,ctThuocPK);
										viTriEdit = -1;
									} else {
										ctThuocPhongKhams.add(ctThuocPK);
									}
								}
								vitriTrungMaThuocBK=-1;
								ctThuocPK = new ThuocPhongKham();
								break;
							}else{
								ctThuocPK = new ThuocPhongKham();
								ctThuocPK = (ThuocPhongKham)BeanUtils.cloneBean(ctThuocPhongKham);
								soluongcapBN = soluongcapBN - tonLo;
								ctThuocPK.setThuocphongkhamSoluong(tonLo);	
								setThuocPhongKhamInfo(ctThuocPK, tonkhoHienTai, doituongMa);
								//Xoa list tren luoi va add lai
								if(vitriTrungMaThuocBK >-1){
									ctThuocPhongKhams.set(vitriTrungMaThuocBK,ctThuocPK);
									
									// 20120221 bao.ttc: remove dong Thuoc can edit
									if (viTriEdit > -1 && viTriEdit != vitriTrungMaThuocBK ){
										ctThuocPhongKhams.remove(viTriEdit);
										viTriEdit = -1;
									}
								}else{
									// 20120221 bao.ttc: add tai vi tri Thuoc can edit
									if (viTriEdit > -1){
										ctThuocPhongKhams.set(viTriEdit,ctThuocPK);
										viTriEdit = -1;
									} else {
										ctThuocPhongKhams.add(ctThuocPK);
									}
								}
								ctThuocPK = new ThuocPhongKham();
								vitriTrungMaThuocBK=-1;
							}							
						}
					}					
				}else{//ke toa quay BV: khi da vao duoc man hinh nay tuc la BN chua thanh toan vien phi, tuc thuoc cap cho BN chua dc xuat
					//Kiem tra du lieu tren luoi co thuoc hay khong, neu co thi kiem tra tren luoi					
					Double soluongDaCapTrenGrid = new Double(0.0);
					int vitriTrungMaThuocQBV=-1;
					if(ctThuocPhongKhams.size() >0){						
						for(int j = 0; j< ctThuocPhongKhams.size();j++){
							ThuocPhongKham thuocphongkhamGrid = ctThuocPhongKhams.get(j);
							if(ctThuocPhongKham.getThuocphongkhamMathuoc().getDmthuocMa().equals(thuocphongkhamGrid.getThuocphongkhamMathuoc().getDmthuocMa())
								&& ctThuocPhongKham.getThuocphongkhamKhongthu().equals(thuocphongkhamGrid.getThuocphongkhamKhongthu())){
								soluongDaCapTrenGrid = soluongDaCapTrenGrid + thuocphongkhamGrid.getThuocphongkhamSoluong();
								vitriTrungMaThuocQBV= j;
							}
						}
						soluongcapBN = soluongcapBN + soluongDaCapTrenGrid;
						if(soluongcapBN > tonTong){
							// 20120221 bao.ttc: remove dong Thuoc can edit
							if (viTriEdit > -1){
								ctThuocPhongKhams.remove(viTriEdit);
								viTriEdit = -1;
							}
							
							ctThuocPhongKham = new ThuocPhongKham();
							maChiDan = "";
							tenChiDan = "";
							setHamluong("");
							setTonTong(new Double(0.0));
							setHandung("");
							FacesMessages.instance().add(IConstantsRes.XLBK_SOLUONGKHONGDU);
							return;
						}
					}					
										
					if(listTonKhoHienTai != null && listTonKhoHienTai.size()>0){
						for(int i = 0; i<listTonKhoHienTai.size(); i++){
							TonKho tonkhoHienTai = listTonKhoHienTai.get(i);
							Double tonLo = tonkhoHienTai.getTonkhoTon();
							ThuocPhongKham ctThuocPK = (ThuocPhongKham)BeanUtils.cloneBean(ctThuocPhongKham);
							if(soluongcapBN <= tonLo){
								ctThuocPK.setThuocphongkhamSoluong(soluongcapBN);
								setThuocPhongKhamInfo(ctThuocPK, tonkhoHienTai, doituongMa);
								if(vitriTrungMaThuocQBV >-1){
									ctThuocPhongKhams.set(vitriTrungMaThuocQBV,ctThuocPK);
									
									// 20120221 bao.ttc: remove dong Thuoc can edit
									if (viTriEdit > -1 && viTriEdit != vitriTrungMaThuocQBV ){
										ctThuocPhongKhams.remove(viTriEdit);
										viTriEdit = -1;
									}
								}else{
									// 20120221 bao.ttc: add tai vi tri Thuoc can edit
									if (viTriEdit > -1){
										ctThuocPhongKhams.set(viTriEdit,ctThuocPK);
										viTriEdit = -1;
									} else {
										ctThuocPhongKhams.add(ctThuocPK);
									}
								}
								ctThuocPK = new ThuocPhongKham();
								vitriTrungMaThuocQBV=-1;
								break;
							}else{
								ctThuocPK = new ThuocPhongKham();
								ctThuocPK = (ThuocPhongKham)BeanUtils.cloneBean(ctThuocPhongKham);
								soluongcapBN = soluongcapBN - tonLo;
								ctThuocPK.setThuocphongkhamSoluong(tonLo);	
								setThuocPhongKhamInfo(ctThuocPK, tonkhoHienTai, doituongMa);
								if(vitriTrungMaThuocQBV >-1){
									ctThuocPhongKhams.set(vitriTrungMaThuocQBV,ctThuocPK);
									
									// 20120221 bao.ttc: remove dong Thuoc can edit
									if (viTriEdit > -1 && viTriEdit != vitriTrungMaThuocQBV ){
										ctThuocPhongKhams.remove(viTriEdit);
										viTriEdit = -1;
									}
								}else{
									// 20120221 bao.ttc: add tai vi tri Thuoc can edit
									if (viTriEdit > -1){
										ctThuocPhongKhams.set(viTriEdit,ctThuocPK);
										viTriEdit = -1;
									} else {
										ctThuocPhongKhams.add(ctThuocPK);
									}
								}
								vitriTrungMaThuocQBV=-1;
								ctThuocPK = new ThuocPhongKham();
							}
						}
					}
				}				
			}

			maChiDan = "";
			tenChiDan = "";
			setThuocdongyMaso(0);
			setHamluong("");
			setTonTong(new Double(0.0));
			setHandung("");			
			
			if (rowsToUpdate == null){
				rowsToUpdate = new HashSet<Integer>();
			}
			rowsToUpdate.clear();
			for (int i = 0 ; i < ctThuocPhongKhams.size();i++){
				rowsToUpdate.add(new Integer(i));
			}
			
		}catch(Exception e){
			log.info("***** enterAJAX() Exception = " + e);
			
		}	
		ctThuocPhongKham = new ThuocPhongKham();
		//log.info("*****End enterAJAX()");
	}
	
	public void setThuocPhongKhamInfo(ThuocPhongKham ctThuocPK, TonKho tonkhoHienTai, String doituongMa){
		boolean mien = false;
		if(ctThuocPK.getThuocphongkhamMien() != null && ctThuocPK.getThuocphongkhamMien() == true){
			mien = true;
		}
		boolean yeucau = false;
		if(ctThuocPK.getThuocphongkhamYeucau() != null && ctThuocPK.getThuocphongkhamYeucau() == true){
			yeucau = true;
		}
		if(doituongMa.equals("MP")){
			if(mien && yeucau && !ctThuocPK.getThuocphongkhamKhongthu()){
				ctThuocPK.setThuocphongkhamDongia(tonkhoHienTai.getTonkhoDongia());
			}else{
				ctThuocPK.setThuocphongkhamDongia(new Double(0.0));				
			}
		}else {//BH va Thu phi			
			if(ctThuocPK.getThuocphongkhamKhongthu()){
				ctThuocPK.setThuocphongkhamDongia(new Double(0.0));
			} else {
				ctThuocPK.setThuocphongkhamDongia(tonkhoHienTai.getTonkhoDongia());
			}
		}
		ctThuocPK.setThuocphongkhamDongiatt(Utils.roundDoubleNumber(ctThuocPK.getThuocphongkhamDongia()));
		ctThuocPK.setThuocphongkhamThanhtien(Utils.roundDoubleNumber(ctThuocPK.getThuocphongkhamDongiatt() * ctThuocPK.getThuocphongkhamSoluong()));
		
		ctThuocPK.setThuocphongkhamDongiabh(tonkhoHienTai.getTonkhoDongia());//su dung cho muc dich report, lap phieu du tru tu truc neu dung o ban kham
		ctThuocPK.setThuocphongkhamMalk(tonkhoHienTai.getTonkhoMalienket());		
		ctThuocPK.setThuocphongkhamDongiaban(tonkhoHienTai.getTonkhoDongiaban());		
		ctThuocPK.setThuocphongkhamNamnhap(tonkhoHienTai.getTonkhoNamnhap());
		ctThuocPK.setThuocphongkhamHangsx(tonkhoHienTai.getDmnhasanxuatMaso());
		ctThuocPK.setThuocphongkhamQuocgia(tonkhoHienTai.getDmquocgiaMaso());
		ctThuocPK.setThuocphongkhamLo(tonkhoHienTai.getTonkhoLo());
		ctThuocPK.setThuocphongkhamNgayhd(tonkhoHienTai.getTonkhoNgayhandung());
		ctThuocPK.setThuocphongkhamThanghd(tonkhoHienTai.getTonkhoThanghandung());
		ctThuocPK.setThuocphongkhamNamhd(tonkhoHienTai.getTonkhoNamhandung());
		ctThuocPK.setDmnctMaso(tonkhoHienTai.getDmnctMaso());
		ctThuocPK.setDmnguonkinhphiMaso(tonkhoHienTai.getDmnguonkinhphiMaso());
		ctThuocPK.setThuocphongkhamNgaygiocn(new Date());
		ctThuocPK.setThuocphongkhamNhanviencn(nhanVienCN);
	}
	
	public String getDvtName(int index){
		ThuocPhongKham thuocPK = ctThuocPhongKhams.get(index);
		if(thuocPK != null){
			DieuTriUtilDelegate dtUtilDel = DieuTriUtilDelegate.getInstance();
			if(thuocPK.getThuocphongkhamMathuoc(true).getDmdonvitinhMaso(true).getDmdonvitinhMaso() != null){
				DmDonViTinh dvt = (DmDonViTinh)dtUtilDel.findByMaSo(thuocPK.getThuocphongkhamMathuoc(true).getDmdonvitinhMaso(true).getDmdonvitinhMaso(), "DmDonViTinh", "dmdonvitinhMaso");
				return dvt.getDmdonvitinhTen();
			}			
		}
		return "";
	}
	
	// 20110217 bao.ttc: them / xoa 1 record rong de render DataTable trong truong hop edit so luong thuoc
	public void deleteRecordForUpdateDataModelTable() {
		if(ctThuocPhongKhams != null && ctThuocPhongKhams.size() > 0)
			ctThuocPhongKhams.remove(ctThuocPhongKhams.size() - 1);
	}
	
	public void addRecordForUpdateDataModelTable() {
		ctThuocPhongKhams.add(new ThuocPhongKham());
	}
	// END -- bao.ttc: them / xoa 1 record rong de render DataTable trong truong hop edit so luong thuoc
	
	public void chinhSuaAJAX(int index) throws Exception{
		//log.info("*****Begin chinhSuaAJAX()");
		
		ctThuocPhongKhamSelected = ctThuocPhongKhams.get(index);
		if(ctThuocPhongKhamSelected == null){
			//log.info("ctThuocPhongKhamSelected == null");
			return;
		}		
		
		if (ctThuocPhongKhamSelected != null && ctThuocPhongKhamSelected.getThuocphongkhamMaphieud() != null && !ctThuocPhongKhamSelected.getThuocphongkhamMaphieud().equals("")){
			//log.info("ctThuocPhongKhamSelected != null :"+ctThuocPhongKhamSelected); 
			return;
		}
		
		//Kiem tra, neu doi voi xu ly ban kham, dong thuoc nay da luu DB thi khong dc chinh sua
		if (loaiToaThuocThamKhamVaXuTri.equals(Utils.XU_TRI_THUOC_TAI_BAN_KHAM) && ctThuocPhongKhamSelected != null && ctThuocPhongKhamSelected.getThuocphongkhamMa() != null){
			//log.info("Ban khong duoc phep chinh sua ma thuoc: "+ctThuocPhongKhamSelected.getThuocphongkhamMathuoc().getDmthuocMa() + " nay");			
			FacesMessages.instance().add(IConstantsRes.XLBK_NOTEDIT,ctThuocPhongKhamSelected.getThuocphongkhamMalk());
			ctThuocPhongKhamSelected = new ThuocPhongKham();
			ctThuocPhongKham = new ThuocPhongKham();
			maChiDan = "";
			tenChiDan = "";
			setHamluong("");
			setTonTong(new Double(0.0));
			setHandung("");
			return;
		}
		if(ctThuocPhongKhamSelected.getThuocdongyNgoaiTru() != null){
			thuocdongyMaso = ctThuocPhongKhamSelected.getThuocdongyNgoaiTru().getThuocdongyMaso();
		}else{
			thuocdongyMaso = 0;
		}
		
		ctThuocPhongKham = (ThuocPhongKham)BeanUtils.cloneBean(ctThuocPhongKhamSelected) ;
		//sLoiDan= ctThuocPhongKham.getThuocphongkhamMaloidan();
		tenLoiDans = ctThuocPhongKham.getThuocphongkhamTenloidan();
		
		maChiDan = ctThuocPhongKham.getThuocphongkhamMaChidan();
		tenChiDan = ctThuocPhongKham.getThuocphongkhamChidan();
		
		getTonKhoHienTai(ctThuocPhongKham.getThuocphongkhamMathuoc().getDmthuocMaso());
		Double tonHT = new Double(0.0);
		for(TonKho tk :listTonKhoHienTai){
			tonHT = tonHT + tk.getTonkhoTon();
			if(tk.getTonkhoMaphieukiem() != null){
				setTonTong(new Double(0.0));
				ctThuocPhongKham.setThuocphongkhamMathuoc(null);
				setCheckKiemKe("true");
				break;
			}else{
				setTonTong(tonHT);
			}
		}
		String handungStr ="";
		if(ctThuocPhongKham.getThuocphongkhamNamhd() != null ){
			if(!ctThuocPhongKham.getThuocphongkhamNamhd().equals("")){
				if(ctThuocPhongKham.getThuocphongkhamThanghd() != null ){
					if(ctThuocPhongKham.getThuocphongkhamThanghd().equals("")){
						handungStr = ctThuocPhongKham.getThuocphongkhamNamhd();
					}else{
						if(ctThuocPhongKham.getThuocphongkhamNgayhd() != null ){
							if(!ctThuocPhongKham.getThuocphongkhamNgayhd().equals("")){
								handungStr = ctThuocPhongKham.getThuocphongkhamNgayhd() +"/" + ctThuocPhongKham.getThuocphongkhamThanghd() +"/" + ctThuocPhongKham.getThuocphongkhamNamhd();
							}else{
								handungStr = ctThuocPhongKham.getThuocphongkhamThanghd() +"/" + ctThuocPhongKham.getThuocphongkhamNamhd();
							}
							
						}else{
							handungStr = ctThuocPhongKham.getThuocphongkhamThanghd() + "/" +ctThuocPhongKham.getThuocphongkhamNamhd();
						}
					}				
				}else{
					handungStr = ctThuocPhongKham.getThuocphongkhamNamhd();
				}
			}			
		}
		setHandung(handungStr);
		setHamluong(ctThuocPhongKham.getThuocphongkhamMathuoc().getDmthuocHamluong());
		DieuTriUtilDelegate dtUtilDel = DieuTriUtilDelegate.getInstance();
		DmDonViTinh dvt = (DmDonViTinh)dtUtilDel.findByMaSo(ctThuocPhongKham.getThuocphongkhamMathuoc().getDmdonvitinhMaso().getDmdonvitinhMaso(), "DmDonViTinh", "dmdonvitinhMaso");
		setDmdonvitinhTen(dvt.getDmdonvitinhTen());
		ctThuocPhongKham.getThuocphongkhamMathuoc().setDmdonvitinhMaso(dvt);
		//log.info("maChiDan:"+maChiDan);
		//log.info("tenChiDan:"+tenChiDan);
		
		ctThuocPhongKhamSelectedOld = index;		
		//log.info("*****End chinhSuaAJAX()");
	}
	
	public void xoaAJAX(int index) throws Exception{
		//log.info("*****Begin xoaAJAX()");		
		ThuocPhongKham ctThuocPK = ctThuocPhongKhams.get(index);
		if (ctThuocPK != null && ctThuocPK.getThuocphongkhamMaphieud() != null && !ctThuocPK.getThuocphongkhamMaphieud().equals("")){
			 return;
		}
		
		try{
			ctThuocPhongKhams.remove(index);
			//Kiem tra tren list ctThuocPhongKhams con lai, neu het thuoc dong y thi remove thang thuoc ra khoi list
			boolean isExistedThuocDY = false;
			for(ThuocPhongKham tpk:ctThuocPhongKhams){				
				if(tpk.getDmbaithuocMaso() != null){//Con thuoc dong y
					isExistedThuocDY = true;
					break;
				}				
			}
			if(!isExistedThuocDY){
				hsmListThuocDYNgoaiTru.remove(ctThuocPK.getDmbaithuocMaso());
			}
			ctThuocPhongKham = new ThuocPhongKham();
		}catch(Exception e){
			log.info("***** xoaAJAX Exception = " + e);
			
		}
		
		//log.info("*****End xoaAJAX()");
	}
	
	///////////////////////////////////////////////////////////////
	
	
	private DtDmNhanVien nhanVienCN = null;
	
	private Boolean hienThiInPhieu = false;
	
	// Note:
	//public static String XU_TRI_THUOC_TAI_BAN_KHAM = "1";	
	//public static String KE_TOA_BENH_NHAN_TU_MUA = "2";
	//public static String KE_TOA_QUAY_BENH_VIEN = "3";
	
	@Begin(join = true)
	public String initFromMenu(String fromMenu, String loaiThuocPhongKham){ //   1 or 2 or 3
		//log.info("#####-----##### initFromMenu() -- @Begin(join = true)");
		this.fromMenu = fromMenu;
		initB121_3_Xutrithuoctaibankham  = "no";
		loaiToaThuocThamKhamVaXuTri = loaiThuocPhongKham;
		resetValueFromMenu();
		initService();
		if (loaiToaThuocThamKhamVaXuTri.equals(Utils.KE_TOA_BENH_NHAN_TU_MUA)){
			return "ketoave";
		}
		
		DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
		nhanVienCN = nvDelegate.findByNd(identity.getUsername());
		
		return "xutrithuoctaibankham";
	}
	
	public void displayInforFromMenu() throws Exception {
		maBanKhamOut = thamkham.getThamkhamBankham(true).getDtdmbankhamMa();
		maTiepDonOut = thamkham.getTiepdonMa(true).getTiepdonMa();
		tempInit();
	}
	
	public void resetValueFromMenu(){
		maBanKhamOut = "";
		maTiepDonOut = "";
		thamkham = new ThamKham();
		ngaySinh= "";
		thoiGian = "";
		ctThuocPhongKham = new ThuocPhongKham();
//		SetInforUtil.setInforIfNullForThuocPhongKham(ctThuocPhongKham);
		doiTuong = new DmDoiTuong();
		ctThuocPhongKhams = new ArrayList<ThuocPhongKham>();
		hsmListThuocDYNgoaiTru = new HashMap<Integer,List>();
		sLoiDan = "";
		ngaySinh = "";
		thuocdongyMaso = 0;
		SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
		thoiGian = formatter.format(new Date());
		gioThamKham = Utils.getGioPhut(new Date()) ;
		hienThiInPhieu = false;
		//Phuc add
		sobaithuoc = 1;
	}
	
	@Factory("initB121_3_Xutrithuoctaibankham")
	@Begin(nested = true)
	public void init() throws Exception {
		//log.info("#####-----##### init() -- @Factory - @Begin(nested = true)");
		tempInit();
		this.fromMenu = "";
		//log.info("***Finished init ***");
	}

	private void tempInit(){
		try{
			initService();
			
			sLoiDan = "";
			tenLoiDans = "";  //bao.ttc: reset loi dan
			//mabs = "";
			checkKiemKe ="false";
			thuocdongyMaso = 0;
			if (maBanKhamOut == null || maBanKhamOut.equals("") || maTiepDonOut == null || maTiepDonOut.equals("")){
				resetValueFromMenu();
				return;
			}
			hsmListThuocDYNgoaiTru = new HashMap<Integer,List>();
			ctThuocPhongKham = new ThuocPhongKham();			
			
			ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
			ThamKham thamkham_temp = tkDelegate.findByBanKhamVaMaTiepDon(maBanKhamOut, maTiepDonOut);
			
			if (thamkham_temp == null){
				resetValueFromMenu();
				FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND);
				return ;
			}
			
			thamkham = thamkham_temp;
			tiepdonTPK = ( thamkham.getTiepdonMa() == null ? new TiepDon() : thamkham.getTiepdonMa() );
			benhnhanTPK = ( tiepdonTPK.getBenhnhanMa() == null ? new BenhNhan() : tiepdonTPK.getBenhnhanMa() );
			
			if (thamkham.getThamkhamBacsi()!=null){				
				mabs = thamkham.getThamkhamBacsi().getDtdmnhanvienMa();				
				masobs = thamkham.getThamkhamBacsi().getDtdmnhanvienMaso();
				// Phuc add
				tenbs = thamkham.getThamkhamBacsi().getDtdmnhanvienTen();
			} else {
				mabs="";
				tenbs = "";
				masobs=null;
			}
			// Phuc add			
			chandoan = (thamkham.getBenhicd10() != null ? thamkham.getBenhicd10().getDmbenhicdTen() : "");
			
			// GET DMKHOA_MASO from BANKHAM
			DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
			DmKhoa dmKhoa=new DmKhoa();			
			//Tho add
			if (loaiToaThuocThamKhamVaXuTri.equals(Utils.XU_TRI_THUOC_TAI_BAN_KHAM)){
				DtDmBanKhamDelegate dtDmBKDelegate = DtDmBanKhamDelegate.getInstance();
				DtDmBanKham dtDmBK = dtDmBKDelegate.find(thamkham.getThamkhamBankham().getDtdmbankhamMaso());
				dmKhoa = dtDmBK.getDmkhoaMaso();	
			}else{//KE_TOA_BENH_NHAN_TU_MUA, KE_TOA_QUAY_BENH_VIEN
				//neu IS_USED_KHOTE=1: Benh nhan kham ban kham Nhi dau tien se nhan thuoc tai kho TE nguoc lai se dua vao ban kham tinh chat
				//neu IS_USED_KHOTE=0: Khong co kho TE. Dua vao ban kham tinh chat.
				if( IConstantsRes.IS_USED_KHOTE == "0"){
					if(thamkham.getThamkhamBankham(true).getDtdmbankhamTinhchat().equals(IConstantsRes.KHO_BHYT_MA)){
						dmKhoa  = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_BHYT_MA, "DmKhoa", "dmkhoaMa");					
					}else if(thamkham.getThamkhamBankham(true).getDtdmbankhamTinhchat().equals(IConstantsRes.KHO_TRE_EM_MA)){
						//khong co Kho TE, dieu kien nay xem nhu khong hoat dong
						dmKhoa  = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_TE_MA, "DmKhoa", "dmkhoaMa");					
					}else if(thamkham.getThamkhamBankham(true).getDtdmbankhamTinhchat().equals(IConstantsRes.KHO_CHINH_MA)){
						dmKhoa  = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_KC_MA, "DmKhoa", "dmkhoaMa");					
					}else if(thamkham.getThamkhamBankham(true).getDtdmbankhamTinhchat().equals(IConstantsRes.KHO_NOITRU_MA)){
						dmKhoa  = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_NT_MA, "DmKhoa", "dmkhoaMa");					
					}
				}else{//=1 co KHO TE
					//get khoa tinh chat cua ban kham dau tien de lay thuoc tai kho nao.
					//TiepDonDelegate tdonDelegate = TiepDonDelegate.getInstance();
					//String tiepDonMa = tiepdonTPK.getTiepdonMa();
					//TiepDon tiepdon = tdonDelegate.find(tiepDonMa);
					DtDmBanKham dtDmBK = tiepdonTPK.getTiepdonBankham();
					if(dtDmBK.getDtdmbankhamTinhchat().equals(IConstantsRes.KHO_BHYT_MA)){
						dmKhoa  = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_BHYT_MA, "DmKhoa", "dmkhoaMa");					
					}else if(dtDmBK.getDtdmbankhamTinhchat().equals(IConstantsRes.KHO_TRE_EM_MA)){
						dmKhoa  = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_TE_MA, "DmKhoa", "dmkhoaMa");					
					}else if(dtDmBK.getDtdmbankhamTinhchat().equals(IConstantsRes.KHO_CHINH_MA)){
						dmKhoa  = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_KC_MA, "DmKhoa", "dmkhoaMa");					
					}else if(dtDmBK.getDtdmbankhamTinhchat().equals(IConstantsRes.KHO_NOITRU_MA)){
						dmKhoa  = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_NT_MA, "DmKhoa", "dmkhoaMa");					
					}
				}
				
			}
			//End Tho add
			khoaMaSo = dmKhoa.getDmkhoaMaso();
			khoaMa = dmKhoa.getDmkhoaMa();
			//log.info("khoaMaSo "+khoaMaSo);
			//log.info("khoaMa "+khoaMa);
			
			
//			benhNhan = thamkham.getTiepdonMa().getBenhnhanMa();
			doiTuong = tiepdonTPK.getDoituongMa();
			
			/* find danh sach thuoc phong kham thuoc ban kham va ma tiep don*/
			
			ThuocPhongKhamDelegate thuocPKDele = ThuocPhongKhamDelegate.getInstance();
			
			ctThuocPhongKhams = thuocPKDele.findByMaTiepDonAndMaBanKham(tiepdonTPK.getTiepdonMa(), thamkham.getThamkhamBankham(true).getDtdmbankhamMa(),String.valueOf(loaiToaThuocThamKhamVaXuTri));			
			if(ctThuocPhongKhams!=null && ctThuocPhongKhams.size()>0)
			{
				tenLoiDans = ctThuocPhongKhams.get(0).getThuocphongkhamTenloidan(); 
			}
			ThuocDongYNgoaiTruDelegate thuocDYNgoaiTruDel = ThuocDongYNgoaiTruDelegate.getInstance();
			List<ThuocDongYNgoaiTru> lstThuocDongYNgoaiTru = thuocDYNgoaiTruDel.findByMaTiepDonAndMaBanKham(tiepdonTPK.getTiepdonMa(), thamkham.getThamkhamBankham(true).getDtdmbankhamMa(),String.valueOf(loaiToaThuocThamKhamVaXuTri));
			for(ThuocDongYNgoaiTru thuocDongYNgoaiTru:lstThuocDongYNgoaiTru){
				List listThuocDongYNgoaiTru = new ArrayList();
				listThuocDongYNgoaiTru.add(thuocDongYNgoaiTru);
				listThuocDongYNgoaiTru.add(new Boolean(true));//neu da luu DB thi set true
				listThuocDongYNgoaiTru.add(loaiToaThuocThamKhamVaXuTri);
				hsmListThuocDYNgoaiTru.put(thuocDongYNgoaiTru.getDmbaithuocMaso().getDmbaithuocMaso(), listThuocDongYNgoaiTru);
			}
			
			if (ctThuocPhongKhams == null){
				ctThuocPhongKhams = new ArrayList<ThuocPhongKham>();
			}
			
//			for (ThuocPhongKham tpk:  ctThuocPhongKhams){
//				maFinish = tpk.getThuocphongkhamThamkham().getThamkhamMa().toString();
//				log.info("-----ma phieu: " + maFinish);
//				break;
//			}
			
			// 20120531 bao.ttc: chi refresh list DmThuoc khi thay doi giua: QuayBV va ThuocBK
			if ( !loaiToaThuocOld.equals(loaiToaThuocThamKhamVaXuTri)
					|| listDmThuocs == null || listDmThuocs.isEmpty() ) {
				
				initB121_3_Xutrithuoctaibankham = "done";
				loaiToaThuocOld = loaiToaThuocThamKhamVaXuTri;
				refreshDmThuoc();
			} else {
				initB121_3_Xutrithuoctaibankham = "done";
				log.info(" ---###--- KHONG RELOAD DMTHUOC: listDmThuocs.size() = " + listDmThuocs.size());
			}
			
			setOtherValue();
			
		}catch(Exception e){
			log.info("***init Exception = " + e);
			e.printStackTrace();
		}
	}
	
	/** ========== LY THEM CODE - Tho sua ========== */
	public void getToaThuocTruoc(){
		ThamKhamDelegate thamKhamDelegate = ThamKhamDelegate.getInstance();
		ThuocPhongKhamDelegate thuocPKDele = ThuocPhongKhamDelegate.getInstance();
		hsmListThuocDYNgoaiTru.clear();
		ThamKham thamkham_loadthuoc = thamKhamDelegate.getLastThamKhamWithSoTheBHYTAndBanKham(tiepdonTPK.getTiepdonSothebh(), thamkham.getThamkhamBankham().getDtdmbankhamMaso());
		if(thamkham_loadthuoc != null){
			List<ThuocPhongKham> ctToaThuocTruoc = thuocPKDele.findByMaTiepDonAndMaBanKham(thamkham_loadthuoc.getTiepdonMa(true).getTiepdonMa(), thamkham_loadthuoc.getThamkhamBankham(true).getDtdmbankhamMa(),String.valueOf(loaiToaThuocThamKhamVaXuTri));
			try{
				// 20110112 bao.ttc: Xu ly cac thong tin cua toa thuoc truoc - Tho update ngay 27/5/2011
				if (ctToaThuocTruoc != null && ctToaThuocTruoc.size() > 0){
					//Cong don so luong thuoc, neu cac lo co cung 1 ma thuoc					
					List<ThuocPhongKham> listTpk_Temp = new ArrayList<ThuocPhongKham>();
					for (ThuocPhongKham toaThuocTruoc : ctToaThuocTruoc){
						ThuocPhongKham thuocphongkhamNew = new ThuocPhongKham();
						thuocphongkhamNew = (ThuocPhongKham) BeanUtils.cloneBean(toaThuocTruoc);
						thuocphongkhamNew.setThuocphongkhamMaphieud(null);
						thuocphongkhamNew.setThuocphongkhamDatt(null);
						thuocphongkhamNew.setThuocphongkhamMa(null);
						thuocphongkhamNew.setThuocphongkhamNgaygio(new Date());
						thuocphongkhamNew.setThuocphongkhamNgaygiocn(new Date());
						thuocphongkhamNew.setThuocphongkhamMalk(null);//khi ghi nhan, kiem tra lay ton lo thich hop, set lai don gia, ma lk
						
						if (thamkham.getThamkhamBacsi() != null)
							thuocphongkhamNew.setThuocphongkhamBacsi(thamkham.getThamkhamBacsi());
						
						if(listTpk_Temp.size() == 0){							
							listTpk_Temp.add(thuocphongkhamNew);
						}else{
							ThuocPhongKham thuocphongkham_Finded = new ThuocPhongKham();
							boolean foundInList = false;
							int vitri = -1;
							for(int j = 0; j<listTpk_Temp.size(); j++){
								ThuocPhongKham thuocphongkhamNew1 = listTpk_Temp.get(j);
								if(thuocphongkhamNew1.getThuocphongkhamMathuoc().getDmthuocMaso().equals(thuocphongkhamNew.getThuocphongkhamMathuoc().getDmthuocMaso())){
									thuocphongkham_Finded = thuocphongkhamNew1;
									foundInList = true;
									vitri = j;
									break;
								}
							}
							if(foundInList == true){
								thuocphongkhamNew.setThuocphongkhamSoluong(thuocphongkhamNew.getThuocphongkhamSoluong() + thuocphongkham_Finded.getThuocphongkhamSoluong());
								listTpk_Temp.set(vitri,thuocphongkhamNew);
							}else{
								listTpk_Temp.add(thuocphongkhamNew);
							}
						}
					}
					
					for (ThuocPhongKham toaThuocTruoc : listTpk_Temp){						
						ctThuocPhongKhams.add(toaThuocTruoc);
					}
					
				}else{
//					xuat thong bao tren giao dien neu ko co toa thuoc truoc
					FacesMessages.instance().add(IConstantsRes.TOA_THUOC_TRUOC_KO_CO);
				}
				ThuocDongYNgoaiTruDelegate thuocDYNgoaiTruDel = ThuocDongYNgoaiTruDelegate.getInstance();
				List<ThuocDongYNgoaiTru> lstThuocDongYNgoaiTru = thuocDYNgoaiTruDel.findByMaTiepDonAndMaBanKham(thamkham_loadthuoc.getTiepdonMa(true).getTiepdonMa(), thamkham_loadthuoc.getThamkhamBankham(true).getDtdmbankhamMa(),String.valueOf(loaiToaThuocThamKhamVaXuTri));
				for(ThuocDongYNgoaiTru thuocDongYNgoaiTru:lstThuocDongYNgoaiTru){
					List listThuocDongYNgoaiTru = new ArrayList();	
					listThuocDongYNgoaiTru.add(thuocDongYNgoaiTru);
					listThuocDongYNgoaiTru.add(new Boolean(false));//neu da luu DB thi set true
					listThuocDongYNgoaiTru.add(loaiToaThuocThamKhamVaXuTri);
					hsmListThuocDYNgoaiTru.put(thuocDongYNgoaiTru.getDmbaithuocMaso().getDmbaithuocMaso(), listThuocDongYNgoaiTru);
				}
			}catch(Exception er){
				er.printStackTrace();
			}
		}
		//log.info("END getToaThuocTruoc() ctThuocPhongKhams.size() " + ctThuocPhongKhams.size());
	}
	/** ========== END ========== */
	//Ham khoi tao cac web service
	public void initService() {		
		if (loaiToaThuocThamKhamVaXuTri.equals( Utils.XU_TRI_THUOC_TAI_BAN_KHAM)){
			titleThamKhamVaXuTri = IConstantsRes.KE_TOA_TAI_BAN_KHAM;
		}else if (loaiToaThuocThamKhamVaXuTri.equals( Utils.KE_TOA_BENH_NHAN_TU_MUA)){
			titleThamKhamVaXuTri = IConstantsRes.KE_TOA_VE;
		}else if (loaiToaThuocThamKhamVaXuTri.equals( Utils.KE_TOA_QUAY_BENH_VIEN)){
			titleThamKhamVaXuTri = IConstantsRes.KE_TOA_QUAY_BENH_VIEN;
		}
		
		hienThiInPhieu = true;
		
		//log.info("titleThamKhamVaXuTri:"+titleThamKhamVaXuTri);
		//log.info("initService() -- loaiToaThuocThamKhamVaXuTri: " + loaiToaThuocThamKhamVaXuTri);
	}	
	
	private void setOtherValue() {
		SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
		if (benhnhanTPK.getBenhnhanNgaysinh() != null && !benhnhanTPK.getBenhnhanNgaysinh().equals("")) {
			ngaySinh = formatter.format(benhnhanTPK.getBenhnhanNgaysinh().getTime());
		}
		if (thamkham.getThamkhamNgaygio() != null && !thamkham.getThamkhamNgaygio().equals("")) {
			thoiGian = formatter.format(thamkham.getThamkhamNgaygio().getTime());
		}
		if(thamkham.getThamkhamNgaygio() != null){
			gioThamKham = Utils.getGioPhut(thamkham.getThamkhamNgaygio()) ;
		}
	}
	
	// ***************************************************************************************
	// Ham ghi nhan
	public String ghiNhanAjax() throws Exception {
		//log.info("***** Begin ghiNhanAjax() THUOC_Ngoai_Tru *****");

		// 20120523 bao.ttc: khong can kiem tra lai vi da kiem tra o Tham kham va Xu tri
		
//		HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
//		if ( thamkham.getTiepdonMa(true).getTiepdonMa() != null && !thamkham.getTiepdonMa(true).getTiepdonMa().equals("") ){			
//			if ( thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa().equals("BH") ) {				
//				HsThtoank hsttk_tmp = hsttkDelegate.findBytiepdonMa(thamkham.getTiepdonMa(true).getTiepdonMa());				
//				if (hsttk_tmp != null){
//					FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA);
//					return "";
//				}
//			}
//		}
		
		// 20120510 bao.ttc: save user action log to database
		YteLogDelegate yteLogDele = YteLogDelegate.getInstance();
		YteLog yteLog = new YteLog();
		String yteLogString = "";
		String yteListData = "";
		SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE_TIME);

		yteLog.setForm("B121_3_Xutrithuoctaibankham");
		yteLog.setUserId(identity == null ? "NULL" : identity.getUsername());
		
		if (thamkham != null) {
			yteLog.setObjectId(tiepdonTPK.getTiepdonMa() == null ? "NULL" : tiepdonTPK.getTiepdonMa());
			yteLogString += "BK:" + (thamkham.getThamkhamBankham() == null ? "NULL" : thamkham.getThamkhamBankham(true).getDtdmbankhamMa())
						+ " - TK:" + (thamkham.getThamkhamMa());
		}
		
		try {			
			//set lai tham kham va tiep don, co the khi add bai thuoc, nguoi dung chua nhap ma tiep don
			HashMap hsmThuocDYNGTTemp = new HashMap<Integer,List>();
			if(hsmListThuocDYNgoaiTru != null && hsmListThuocDYNgoaiTru.size() > 0){
				java.util.Set set = hsmListThuocDYNgoaiTru.entrySet();
			    Iterator i = set.iterator();
			    while(i.hasNext()){
			    	Map.Entry me = (Map.Entry)i.next();
			    	List listThuocDY = (List)me.getValue();
			    	ThuocDongYNgoaiTru thuocDY = (ThuocDongYNgoaiTru)listThuocDY.get(0);
					Boolean isSavedDB = (Boolean)listThuocDY.get(1);
					thuocDY.setThuocdongyThamkham(thamkham);
					thuocDY.setTiepdonMa(tiepdonTPK);
					listThuocDY.set(0, thuocDY);
					hsmThuocDYNGTTemp.put(thuocDY.getDmbaithuocMaso().getDmbaithuocMaso(), listThuocDY);   										 		
			    }
			}
			
			hsmListThuocDYNgoaiTru = hsmThuocDYNGTTemp;
			DmKhoa dmKhoa = new DmKhoa();
			dmKhoa.setDmkhoaMaso(khoaMaSo);
			
			for (ThuocPhongKham tpk:  ctThuocPhongKhams){
				RemoveUtil.removeIfNullForThuocPhongKham(tpk);
				tpk.setThuocphongkhamThamkham(thamkham);
				tpk.setThuocphongkhamLoai(String.valueOf(loaiToaThuocThamKhamVaXuTri));// 1: xu tri tai ban kham, ...
				tpk.setThuocphongkhamKhoa(dmKhoa);
				tpk.setThuocphongkhamTenloidan(tenLoiDans);
				
				// Set don gia bao hiem bang don gia (gia bao hiem cua thuoc hien tai khong su dung)
				tpk.setThuocphongkhamDongiabh(tpk.getThuocphongkhamDongia());
			}

			ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
			if (thamkham.getThamkhamNgaygio() == null){
				thamkham.setThamkhamNgaygio(new Date());
			}
			
			thamkham.setThamkhamNgaygiocn(new Date());
			
			//** INSERT NHAN VIEN CAP NHAT -- 20120523 bao.ttc: remove vi da co Nhat ky he thong
//			DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
//			DtDmNhanVien nhanVienCN = nvDelegate.findByNd(identity.getUsername());
//			thamkham.setThamkhamNhanviencn(nhanVienCN);
			
			RemoveUtil.removeAllNullFromTiepDon(thamkham.getTiepdonMa());
			RemoveUtil.removeAllNullFromThamKham(thamkham);
			
			String doituongMa = tiepdonTPK.getDoituongMa(true).getDmdoituongMa();
			//DieuTriUtilDelegate dtUtilDelegate = DieuTriUtilDelegate.getInstance();
			if(loaiToaThuocThamKhamVaXuTri.equals(Utils.XU_TRI_THUOC_TAI_BAN_KHAM)||loaiToaThuocThamKhamVaXuTri.equals(Utils.KE_TOA_BENH_NHAN_TU_MUA)) {				
				
				if(loaiToaThuocThamKhamVaXuTri.equals(Utils.XU_TRI_THUOC_TAI_BAN_KHAM)){
					yteListData += "#Thuoc_BK - " + sdf.format(new Date()) + ": ";
					int sott = 1;
					//Sau do get ton kho hien tai de kiem tra neu so luong toa thuoc truoc du de xuat thi add ma lk cua ton do cho thuoc phong kham
					List<ThuocPhongKham> listTpks = new ArrayList<ThuocPhongKham>(); 
					for (ThuocPhongKham tpk: ctThuocPhongKhams) {
						if(tpk.getThuocphongkhamMalk() == null || tpk.getThuocphongkhamMalk() == ""){//update ton lo chinh xac va gia chinh xac
							Double soluongcapBN = tpk.getThuocphongkhamSoluong();
							
							getTonKhoHienTai(tpk.getThuocphongkhamMathuoc().getDmthuocMaso());
							Double tongTon = 0.0;
							if(listTonKhoHienTai != null && listTonKhoHienTai.size()>0){					
								for(int j = 0; j<listTonKhoHienTai.size(); j++){
									tongTon = tongTon + listTonKhoHienTai.get(j).getTonkhoTon();
								}					
							}
							if(soluongcapBN > tongTon){
								//Bao loi, khong co thuoc ton de cap cho BN
								FacesMessages.instance().add(IConstantsRes.XLBK_HETTONTHUOC, tpk.getThuocphongkhamMathuoc().getDmthuocMa());
								return "";
							}
							if(listTonKhoHienTai != null && listTonKhoHienTai.size()>0){
								for(int j = 0; j<listTonKhoHienTai.size(); j++){
									TonKho tonkhoHienTai = listTonKhoHienTai.get(j);
									Double tonLo = tonkhoHienTai.getTonkhoTon();
									ThuocPhongKham ctThuocPK = (ThuocPhongKham)BeanUtils.cloneBean(tpk);
									if(soluongcapBN <= tonLo){
										ctThuocPK.setThuocphongkhamSoluong(soluongcapBN);
										setThuocPhongKhamInfo(ctThuocPK, tonkhoHienTai, doituongMa);
										listTpks.add(ctThuocPK);
										
										// NHAT KY HE THONG
										yteListData += sott + "/ " + (ctThuocPK.getThuocphongkhamMathuoc() == null ? "NULL" : ctThuocPK.getThuocphongkhamMathuoc(true).getDmthuocMa())
										+ "  SL:" + ctThuocPK.getThuocphongkhamSoluong()
										+ "  Gia:" + ctThuocPK.getThuocphongkhamDongia()
										+ " # ";
										sott++;
										
										ctThuocPK = new ThuocPhongKham();
										break;
									}else{
										ctThuocPK = new ThuocPhongKham();
										ctThuocPK = (ThuocPhongKham)BeanUtils.cloneBean(tpk);
										soluongcapBN = soluongcapBN - tonLo;
										ctThuocPK.setThuocphongkhamSoluong(tonLo);	
										setThuocPhongKhamInfo(ctThuocPK, tonkhoHienTai, doituongMa);
										listTpks.add(ctThuocPK);
										
										// NHAT KY HE THONG
										yteListData += sott + "/ " + (ctThuocPK.getThuocphongkhamMathuoc() == null ? "NULL" : ctThuocPK.getThuocphongkhamMathuoc(true).getDmthuocMa())
										+ "  SL:" + ctThuocPK.getThuocphongkhamSoluong()
										+ "  Gia:" + ctThuocPK.getThuocphongkhamDongia()
										+ " # ";
										sott++;
										
										ctThuocPK = new ThuocPhongKham();
									}
								}
							}
						}else{
							listTpks.add(tpk);
							
							// NHAT KY HE THONG
							yteListData += sott + "/ " + (tpk.getThuocphongkhamMathuoc() == null ? "NULL" : tpk.getThuocphongkhamMathuoc(true).getDmthuocMa())
							+ "  SL:" + tpk.getThuocphongkhamSoluong()
							+ "  Gia:" + tpk.getThuocphongkhamDongia()
							+ " # ";
							sott++;
						}					
					}
					ctThuocPhongKhams = listTpks;
				}				
				tpkDelegate.createToaThuocPhongKham(ctThuocPhongKhams,thamkham, loaiToaThuocThamKhamVaXuTri, hsmListThuocDYNgoaiTru);
			}else if(loaiToaThuocThamKhamVaXuTri.equals(Utils.KE_TOA_QUAY_BENH_VIEN)){	
				yteListData += "#Quay_BV - " + sdf.format(new Date()) + ": ";
				int sott = 1;
				//Sau do get ton kho hien tai de kiem tra neu so luong toa thuoc truoc du de xuat thi add ma lk cua ton do cho thuoc phong kham
				List<ThuocPhongKham> listTpks = new ArrayList<ThuocPhongKham>(); 
				for (ThuocPhongKham tpk: ctThuocPhongKhams) {
					if(tpk.getThuocphongkhamMalk() == null || tpk.getThuocphongkhamMalk() == ""){//update ton lo chinh xac va gia chinh xac
						Double soluongcapBN = tpk.getThuocphongkhamSoluong();
						
						getTonKhoHienTai(tpk.getThuocphongkhamMathuoc().getDmthuocMaso());
						Double tongTon = 0.0;
						if(listTonKhoHienTai != null && listTonKhoHienTai.size()>0){					
							for(int j = 0; j<listTonKhoHienTai.size(); j++){
								tongTon = tongTon + listTonKhoHienTai.get(j).getTonkhoTon();
							}					
						}
						if(soluongcapBN > tongTon){
							//Bao loi, khong co thuoc ton de cap cho BN
							FacesMessages.instance().add(IConstantsRes.XLBK_HETTONTHUOC, tpk.getThuocphongkhamMathuoc().getDmthuocTen());
							return "";
						}
						if(listTonKhoHienTai != null && listTonKhoHienTai.size()>0){
							for(int j = 0; j<listTonKhoHienTai.size(); j++){
								TonKho tonkhoHienTai = listTonKhoHienTai.get(j);
								Double tonLo = tonkhoHienTai.getTonkhoTon();
								ThuocPhongKham ctThuocPK = (ThuocPhongKham)BeanUtils.cloneBean(tpk);
								if(soluongcapBN <= tonLo){
									ctThuocPK.setThuocphongkhamSoluong(soluongcapBN);
									setThuocPhongKhamInfo(ctThuocPK, tonkhoHienTai, doituongMa);
									listTpks.add(ctThuocPK);
									
									// NHAT KY HE THONG
									yteListData += sott + "/ " + (ctThuocPK.getThuocphongkhamMathuoc() == null ? "NULL" : ctThuocPK.getThuocphongkhamMathuoc(true).getDmthuocMa())
									+ "  SL:" + ctThuocPK.getThuocphongkhamSoluong()
									+ "  Gia:" + ctThuocPK.getThuocphongkhamDongia()
									+ " # ";
									sott++;
									
									ctThuocPK = new ThuocPhongKham();
									break;
								}else{
									ctThuocPK = new ThuocPhongKham();
									ctThuocPK = (ThuocPhongKham)BeanUtils.cloneBean(tpk);
									soluongcapBN = soluongcapBN - tonLo;
									ctThuocPK.setThuocphongkhamSoluong(tonLo);	
									setThuocPhongKhamInfo(ctThuocPK, tonkhoHienTai, doituongMa);
									listTpks.add(ctThuocPK);
									
									// NHAT KY HE THONG
									yteListData += sott + "/ " + (ctThuocPK.getThuocphongkhamMathuoc() == null ? "NULL" : ctThuocPK.getThuocphongkhamMathuoc(true).getDmthuocMa())
									+ "  SL:" + ctThuocPK.getThuocphongkhamSoluong()
									+ "  Gia:" + ctThuocPK.getThuocphongkhamDongia()
									+ " # ";
									sott++;
									
									ctThuocPK = new ThuocPhongKham();
								}
							}
						}
					}else{
						listTpks.add(tpk);
						
						// NHAT KY HE THONG
						yteListData += sott + "/ " + (tpk.getThuocphongkhamMathuoc() == null ? "NULL" : tpk.getThuocphongkhamMathuoc(true).getDmthuocMa())
						+ "  SL:" + tpk.getThuocphongkhamSoluong()
						+ "  Gia:" + tpk.getThuocphongkhamDongia()
						+ " # ";
						sott++;
					}					
				}
				ctThuocPhongKhams = listTpks;
				tpkDelegate.createKeToaQuayBenhVien(ctThuocPhongKhams,thamkham, loaiToaThuocThamKhamVaXuTri, hsmListThuocDYNgoaiTru);
			}
			
			// 20120523 bao.ttc: remove, find lai khong co muc dich gi ??
			
//			ThuocPhongKhamDelegate thuocPKDele = ThuocPhongKhamDelegate.getInstance();
//			ctThuocPhongKhams = thuocPKDele.findByMaTiepDonAndMaBanKham(maTiepDonOut, maBanKhamOut,String.valueOf(loaiToaThuocThamKhamVaXuTri));
//			if (ctThuocPhongKhams == null){
//				ctThuocPhongKhams = new ArrayList<ThuocPhongKham>();
//			}
			
//			log.info("goi lai thong tin thuoc pk s:" + ctThuocPhongKhams);
			
//			for (ThuocPhongKham tpk2:  ctThuocPhongKhams){
//				maFinish = tpk2.getThuocphongkhamThamkham().getThamkhamMa().toString();
//				log.info("-----ma phieu: " + maFinish);
//				break;
//			}
//			setOtherValue();
			
			HsThtoank hsttk = new HsThtoank();
			hsttk.setTiepdonMa(tiepdonTPK);	
			ThamKhamUtil tkUtil = new ThamKhamUtil();
			tkUtil.tinhToanChoHSTKKham(thamkham, hsttk, false, listCtTPK_temp, clslist);//tinhToanChoHSTKKham(hsttk,true);
			
			FacesMessages.instance().add(IConstantsRes.SUCCESS);
			hsmListThuocDYNgoaiTru.clear();
			
			ctThuocPhongKhamSelectedOld = -1;
			ctThuocPhongKhamSelected = new ThuocPhongKham();
			
			// 20120523 bao.ttc: save user action log to database
			yteLog.setLogString(yteLogString);
			yteLog.setListData(yteListData);
			yteLog.setDateTime(new Date());
			yteLogDele.create(yteLog);
			
		}catch (Exception e) {
			FacesMessages.instance().add(IConstantsRes.FAIL);
			//resultHidden = "fail";
			e.printStackTrace();
			log.error("ERROR -- ghiNhanAjax() THUOC_Ngoai_Tru" + e.toString());
		}

		//log.info("*****End ghiNhanAjax() *****");
		
		if (fromMenu == null || fromMenu.equals("")){
			//return quaylai();
			return "ghinhan";
		}
		return null;

	}

	public String quaylai() throws Exception {
		//initB121_3_Xutrithuoctaibankham = null;
		
		// 20120523 bao.ttc: remove vi khong su dung
//		if (returnToXuatHangChoBNBHYT != null && !returnToXuatHangChoBNBHYT.equals("")){
//			returnToXuatHangChoBNBHYT = null;
//			return "QuanLyKhoBHYT_XuatKhoBHYT_XuatHangChoBenhNhan";
//		}
//		
//		if (returnToThuocYDungCuPhongKham != null && !returnToThuocYDungCuPhongKham.equals("")){
//			returnToThuocYDungCuPhongKham = null;
//			return "ThuVienPhi_SoLieuKhamBenh_ThuocYDungCuPhongKham";
//		}
		
		return "ghinhan";
	}
	
	public void showCLSBanKhamTruoc(){
		//log.info("Begin showCLSBanKhamTruoc");
		listTPKBanKhamTruoc = new ArrayList<ThuocPhongKhamExt>();
		ThamKhamDelegate thamkhamDel = ThamKhamDelegate.getInstance();
		if(thamkham != null){
			List<ThuocPhongKham> thuocphongkhams = thamkhamDel.getListThuocBanKhamTruoc(tiepdonTPK.getTiepdonMa(), thamkham.getThamkhamMa());
			for(ThuocPhongKham tpk:thuocphongkhams){
				ThuocPhongKhamExt tpkExt = new ThuocPhongKhamExt();
				tpkExt.setTpk(tpk);
				tpkExt.setBankhamTen(tpk.getThuocphongkhamThamkham().getThamkhamBankham(true).getDtdmbankhamTen());
				listTPKBanKhamTruoc.add(tpkExt);
			}
		}		
		//log.info("End showCLSBanKhamTruoc");
	}

	// ***************************************************************************************
	//Ham huy cac service da khoi tao
	public void destroyService() {
	}	
	
	//Ham  se duoc goi khi het session (session timeout cau hinh trong file web.xml)
	@Destroy 
	public void destroy() {
		log.info("************************* destroy() B121_3_Xutrithuoctaibankham");
	}
	
	/////////////////////////////////////////////////////////////////////////////////////
	// Cac ham get set
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

//	public BenhNhan getBenhNhan() {
//		return benhNhan;
//	}
//
//	public void setBenhNhan(BenhNhan benhNhan) {
//		this.benhNhan = benhNhan;
//	}

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

	public boolean isUpdateNhapct() {
		return updateNhapct;
	}

	public void setUpdateNhapct(boolean updateNhapct) {
		this.updateNhapct = updateNhapct;
	}

	public ThamKham getThamkham() {
		return thamkham;
	}

	public void setThamkham(ThamKham thamkham) {
		this.thamkham = thamkham;
	}

	public TiepDon getTiepdonTPK() {
		return tiepdonTPK;
	}

	public void setTiepdonTPK(TiepDon tiepdonTPK) {
		this.tiepdonTPK = tiepdonTPK;
	}

	public BenhNhan getBenhnhanTPK() {
		return benhnhanTPK;
	}

	public void setBenhnhanTPK(BenhNhan benhnhanTPK) {
		this.benhnhanTPK = benhnhanTPK;
	}

	public String getGioThamKham() {
		return gioThamKham;
	}

	public void setGioThamKham(String gioThamKham) {
		this.gioThamKham = gioThamKham;
	}

	public String getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(String thoiGian) {
		this.thoiGian = thoiGian;
	}

	public DmDoiTuong getDoiTuong() {
		return doiTuong;
	}

	public void setDoiTuong(DmDoiTuong doiTuong) {
		this.doiTuong = doiTuong;
	}

	public ThuocPhongKham getCtThuocPhongKham() {
		return ctThuocPhongKham;
	}

	public void setCtThuocPhongKham(ThuocPhongKham ctThuocPhongKham) {
		this.ctThuocPhongKham = ctThuocPhongKham;
	}

	public String getInitB121_3_Xutrithuoctaibankham() {
		return initB121_3_Xutrithuoctaibankham;
	}

	public void setInitB121_3_Xutrithuoctaibankham(
			String initB121_3_Xutrithuoctaibankham) {
		this.initB121_3_Xutrithuoctaibankham = initB121_3_Xutrithuoctaibankham;
	}

	public java.util.List<ThuocPhongKham> getCtThuocPhongKhams() {
		return ctThuocPhongKhams;
	}

	public void setCtThuocPhongKhams(
			java.util.List<ThuocPhongKham> ctThuocPhongKhams) {
		this.ctThuocPhongKhams = ctThuocPhongKhams;
	}

	public ThuocPhongKham getCtThuocPhongKhamSelected() {
		return ctThuocPhongKhamSelected;
	}

	public void setCtThuocPhongKhamSelected(ThuocPhongKham ctThuocPhongKhamSelected) {
		this.ctThuocPhongKhamSelected = ctThuocPhongKhamSelected;
	}

	public int getCtThuocPhongKhamSelectedOld() {
		return ctThuocPhongKhamSelectedOld;
	}

	public void setCtThuocPhongKhamSelectedOld(
			int ctThuocPhongKhamSelectedOld) {
		this.ctThuocPhongKhamSelectedOld = ctThuocPhongKhamSelectedOld;
	}

	public String getTenThuoc() {
		return tenThuoc;
	}

	public void setTenThuoc(String tenThuoc) {
		this.tenThuoc = tenThuoc;
	}

	public String getTitleThamKhamVaXuTri() {
		return titleThamKhamVaXuTri;
	}

	public void setTitleThamKhamVaXuTri(String titleThamKhamVaXuTri) {
		this.titleThamKhamVaXuTri = titleThamKhamVaXuTri;
	}

	public Integer getKhoaMaSo() {
		return khoaMaSo;
	}

	public void setKhoaMaSo(Integer khoaMaSo) {
		this.khoaMaSo = khoaMaSo;
	}

	public void setResultReportFileName(String resultReportFileName) {
		this.resultReportFileName = resultReportFileName;
	}

	public String getResultReportFileName() {
		return resultReportFileName;
	}

	public void setResultReportName(String resultReportName) {
		this.resultReportName = resultReportName;
	}

	public String getResultReportName() {
		return resultReportName;
	}

	public void setLoaiFileXuat(String loaiFileXuat) {
		this.loaiFileXuat = loaiFileXuat;
	}

	public String getLoaiFileXuat() {
		return loaiFileXuat;
	}

	public void setIsReport(String isReport) {
		this.isReport = isReport;
	}

	public String getIsReport() {
		return isReport;
	}

	public void setMaFinish(String maFinish) {
		this.maFinish = maFinish;
	}

	public String getMaFinish() {
		return maFinish;
	}
	
//	public String getReturnToXuatHangChoBNBHYT() {
//		return returnToXuatHangChoBNBHYT;
//	}
//
//	public void setReturnToXuatHangChoBNBHYT(String returnToXuatHangChoBNBHYT) {
//		this.returnToXuatHangChoBNBHYT = returnToXuatHangChoBNBHYT;
//	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return this.index;
	}

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		XuTriThuocTaiBanKhamAction.log = log;
	}

	public String getReturnToThuocYDungCuPhongKham() {
		return returnToThuocYDungCuPhongKham;
	}

	public void setReturnToThuocYDungCuPhongKham(
			String returnToThuocYDungCuPhongKham) {
		this.returnToThuocYDungCuPhongKham = returnToThuocYDungCuPhongKham;
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

	public String getReportPathTD() {
		return reportPathTD;
	}

	public void setReportPathTD(String reportPathTD) {
		this.reportPathTD = reportPathTD;
	}

	public String getReportTypeTD() {
		return reportTypeTD;
	}

	public void setReportTypeTD(String reportTypeTD) {
		this.reportTypeTD = reportTypeTD;
	}

	public JasperPrint getJasperPrintTD() {
		return jasperPrintTD;
	}

	public void setJasperPrintTD(JasperPrint jasperPrintTD) {
		this.jasperPrintTD = jasperPrintTD;
	}

	public String getLoaiToaThuocThamKhamVaXuTri() {
		return loaiToaThuocThamKhamVaXuTri;
	}

	public void setLoaiToaThuocThamKhamVaXuTri(String loaiToaThuocThamKhamVaXuTri) {
		this.loaiToaThuocThamKhamVaXuTri = loaiToaThuocThamKhamVaXuTri;
	}

	public String getFromMenu() {
		return fromMenu;
	}

	public void setFromMenu(String fromMenu) {
		this.fromMenu = fromMenu;
	}

	public void setRowsToUpdate(Set<Integer> rowsToUpdate) {
		this.rowsToUpdate = rowsToUpdate;
	}

	public Boolean getHienThiInPhieu() {
		return hienThiInPhieu;
	}

	public void setHienThiInPhieu(Boolean hienThiInPhieu)
	{
		this.hienThiInPhieu = hienThiInPhieu;
	}
	
	public String getSLoiDan()
	{
		return sLoiDan;
	}
	public void setSLoiDan(String loiDan)
	{
		sLoiDan = loiDan;
	}
	public String getTenLoiDan() {
		return tenLoiDan;
	}
	public void setTenLoiDan(String tenLoiDan) {
		this.tenLoiDan = tenLoiDan;
	}

	public String getMabs() {
		return mabs;
	}

	public void setMabs(String mabs) {
		this.mabs = mabs;
	}

	public Integer getMasobs() {
		return masobs;
	}

	public void setMasobs(Integer masobs) {
		this.masobs = masobs;
	}
	
	public String getTenLoiDans() {
		return tenLoiDans;
	}

	public void setTenLoiDans(String tenLoiDans) {
		this.tenLoiDans = tenLoiDans;
	}
	
	public String getKhoaMa() {
		return khoaMa;
	}

	public void setKhoaMa(String khoaMa) {
		this.khoaMa = khoaMa;
	}
	
	public String getTenbs() {
		return tenbs;
	}

	public void setTenbs(String tenbs) {
		this.tenbs = tenbs;
	}

	public String getChandoan() {
		return chandoan;
	}

	public void setChandoan(String chandoan) {
		this.chandoan = chandoan;
	}

	public int getSobaithuoc() {
		return sobaithuoc;
	}

	public void setSobaithuoc(int sobaithuoc) {
		this.sobaithuoc = sobaithuoc;
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
	
	public Integer getThuocdongyMaso() {
		return thuocdongyMaso;
	}

	public void setThuocdongyMaso(Integer thuocdongyMaso) {
		this.thuocdongyMaso = thuocdongyMaso;
	}
	
	public java.util.List<ThuocPhongKhamExt> getListTPKBanKhamTruoc() {
		return listTPKBanKhamTruoc;
	}

	public void setListTPKBanKhamTruoc(java.util.ArrayList<ThuocPhongKhamExt> listTPKBanKhamTruoc) {
		this.listTPKBanKhamTruoc = listTPKBanKhamTruoc;
	}
	
	public class ThuocPhongKhamExt{
		ThuocPhongKham tpk;
		String bankhamTen;
		public ThuocPhongKhamExt(){}
		public ThuocPhongKhamExt(ThuocPhongKham tpk, String bankhamTen){
			this.tpk = tpk;
			this.bankhamTen = bankhamTen;
		}
		public ThuocPhongKham getTpk(boolean created){
			if(created){
				tpk = new ThuocPhongKham();
			}
			return tpk;
		}
		public ThuocPhongKham getTpk(){
			return tpk;
		}
		public void setTpk(ThuocPhongKham tpk){
			this.tpk = tpk;
		}
		public String getBankhamTen(){
			return bankhamTen;
		}
		public void setBankhamTen(String bankhamTen){
			this.bankhamTen = bankhamTen;
		}
	}
}
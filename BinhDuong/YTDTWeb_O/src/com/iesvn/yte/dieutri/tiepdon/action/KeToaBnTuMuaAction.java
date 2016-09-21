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
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocDongYNgoaiTruDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.entity.BaithuocThuoc;
import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.DmBaiThuoc;
import com.iesvn.yte.dieutri.entity.DtDmBanKham;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.HsThtoank;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
import com.iesvn.yte.dieutri.entity.ThuocDongYNgoaiTru;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.entity.DmDonViTinh;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmNhaSanXuat;
import com.iesvn.yte.entity.DmThuoc;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.ThamKhamUtil;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B121_5_Ketoabntumua")
@Synchronized(timeout = 6000000)
public class KeToaBnTuMuaAction implements Serializable {

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
	private static Logger log = Logger.getLogger(KeToaBnTuMuaAction.class);

	@In(scope=ScopeType.PAGE,required = false)
	@Out(scope=ScopeType.PAGE,required = false)
	private String initB121_5_Ketoabntumua;
	
	@DataModel
	private java.util.List<ThuocPhongKham> ctThuocPhongKhams;
	
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
	private HashMap<Integer,List> hsmListThuocDYNgoaiTru = new HashMap<Integer,List>();
	private Integer thuocdongyMaso;

	@DataModelSelection("ctThuocPhongKhams")
	@Out(required = false)
	private ThuocPhongKham ctThuocPhongKhamSelected;
	
	@DataModel
	private java.util.List<ThuocPhongKhamExt> listTPKBanKhamTruoc;//Tho add hien thi danh sach Thuoc phong kham ban kham truoc neu co
	
	private int ctThuocPhongKhamSelectedOld = -1; //dung khi chinh sua 1 record

	private boolean updateNhapct = false;

	private ThuocPhongKham ctThuocPhongKham = null;
//	private ThamKhamWS thamKhamWS = null;
	private DmDoiTuong doiTuong = null;
	
	@In(required = false)
	@Out(required = false)
	private String loaiToaThuocThamKhamVaXuTri ;
	
	
	private String titleThamKhamVaXuTri;
	
	private String resultReportFileName;
	private String resultReportName;	
	private String loaiFileXuat;
	@Out(required = false)
	private String isReport = "false";
	private String maFinish;
	private Integer dmBaithuocMaso  = 0;
	private String dmBaithuocMa;
	private Integer dmthuocMaso;
	private String dmthuocMa;
	private String dmthuocTen;
	private Double thuocphongkhamSoluong;
	private Integer dmdonvitinhMaso;
	
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
		log.info("-----BEGIN onblurMaThuocAction()-----");
		//Tùy logic form mà set cho đúng
		
			if(hmDmThuocAll != null){
				DmThuoc dmThuoc1 = hmDmThuocAll.get(dmthuocMa.toUpperCase());
				if(dmThuoc1 != null) {
					DmThuoc dmThuoc2 = dmThuocDelegate.findByMaThuoc(dmthuocMa.toUpperCase());
					setDmthuocMaso(dmThuoc2.getDmthuocMaso());
					setDmthuocMa(dmThuoc2.getDmthuocMa());
					setDmthuocTen(dmThuoc2.getDmthuocTen());
					log.info("-----DA THAY DMTHUOC-----");
					setHamluong(dmThuoc1.getDmthuocHamluong());
					setDmdonvitinhTen(dmThuoc1.getDmdonvitinhMaso().getDmdonvitinhTen());
					setDmdonvitinhMaso(dmThuoc2.getDmdonvitinhMaso().getDmdonvitinhMaso());
				}
				else {
					setDmthuocMaso(null);
					setDmthuocMa("");
					setDmthuocTen("");
					setHamluong("");
					setDmdonvitinhTen("");
					setDmdonvitinhMaso(null);
				}
			}
		
		setMaChiDan("");
		setTenChiDan("");
		log.info("-----END onblurMaThuocAction()-----");
	}
	
	public void onblurTenThuocAction(){
		log.info("-----BEGIN onblurTenThuocAction()-----");
		
			//20110122 bao.ttc: kiem tra do dai ten thuoc (doi voi truong hop tu nhap, khong chon tu combobox)
			if(dmthuocTen.length() > 6){
				String maThuoc = dmthuocTen.substring(dmthuocTen.length() - 6);    
				System.out.println("MA THUOC(onblurTenThuoc): " + maThuoc);
				DmThuoc dmThuoc2 = dmThuocDelegate.findByMaThuoc(maThuoc);
				if(dmThuoc2 != null) {
					setDmthuocMaso(dmThuoc2.getDmthuocMaso());
					setDmthuocMa(dmThuoc2.getDmthuocMa());		
					setDmthuocTen(dmThuoc2.getDmthuocTen());
					//log.info("-----DA THAY DMTHUOC-----");
					setHamluong(dmThuoc2.getDmthuocHamluong());
					setDmdonvitinhTen(dmThuoc2.getDmdonvitinhMaso().getDmdonvitinhTen());
					setDmdonvitinhMaso(dmThuoc2.getDmdonvitinhMaso().getDmdonvitinhMaso());
				} else {
					setDmthuocMaso(null);
					setDmthuocMa("");
					setDmthuocTen("");
					setHamluong("");
					setDmdonvitinhTen("");
					setDmdonvitinhMaso(null);
				}
			} else {
				setDmthuocMaso(null);
				setDmthuocMa("");
				setDmthuocTen("");
				setDmdonvitinhTen("");
				setDmdonvitinhMaso(null);
				setHamluong("");
			}
		setMaChiDan("");
		setTenChiDan("");
		
		log.info("-----END onblurTenThuocAction()-----");
	}
		
	public void refreshDmThuoc(){
		dmThuocDelegate = DmThuocDelegate.getInstance();
		listDmThuocs.clear();
		//28-04-2011 - Lay danh muc thuoc theo khoa neu ton cua cac thuoc do > 0
		if (loaiToaThuocThamKhamVaXuTri.equals(Utils.KE_TOA_BENH_NHAN_TU_MUA)){
			listDmThuocAll = dmThuocDelegate.findAll();
		}
		System.out.println("listDmThuocAll.size: "+ listDmThuocAll.size());
		//Tho add
		hmDmThuocAll.clear();
		for(DmThuoc o: listDmThuocAll){
			hmDmThuocAll.put(o.getDmthuocMa(), o);
		}
		if(listDmThuocAll != null && listDmThuocAll.size() > 0){
			if(searchType.equals("2")){
				log.info("-----Tim theo ten Khoa hoc-----");
				listDmThuocs.clear();
				for(DmThuoc each : listDmThuocAll){
					listDmThuocs.add(new SelectItem(each.getDmthuocTenkh() + " - " + each.getDmdonvitinhMaso(true).getDmdonvitinhTen() + " - " + each.getDmthuocTen() + " - " + each.getDmthuocMa()));
				}
			} else {
				log.info("-----Tim theo ten Thuong mai-----");
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
	public String thuchienAction_toa_thuoc_bn_ve(){
		if (Utils.KE_TOA_BENH_NHAN_TU_MUA.equals(loaiToaThuocThamKhamVaXuTri)) { // ke toa ve
			reportTypeTD = "KeToaVe";
		}
		ThamKhamUtil tkUtil = new ThamKhamUtil();
		tkUtil.reportTypeTD = reportTypeTD;
		String fileName = tkUtil.XuatReportBNVe(log,  thamkham, listCtTPK_temp, 
												clslist, ctThuocPhongKhams,  index);
		if(fileName.equals("")){
			FacesMessages.instance().add(IConstantsRes.IN_TOA_THUOC_ERROR);
			return "";
		}
		jasperPrintTD = tkUtil.jasperPrintTD;
		reportPathTD=tkUtil.reportPathTD;
		reportTypeTD=tkUtil.reportTypeTD;
		
		index = index + 1;
		return "B160_Chonmenuxuattaptin";
	}
	
	private List<ThuocPhongKham> listCtTPK_temp = null; // day la thuoc tai ban kham, khi thanh toan kham -> tinh luon
	private List<ClsKham> clslist = null;
	
	public String troVe(){
		try {
			log.info("***** troVe()");
			ctThuocPhongKhamSelectedOld = -1;
			
			ctThuocPhongKhams = null;
			ctThuocPhongKhamSelected= null;
			ctThuocPhongKham = null;
			thamkham=null;
			
			return "B121_3_Xutrithuoctaibankham";
		} 		
		catch (Exception e) {
			log.info("***** End exception = " + e);    	
		} 
		return null;
	}
	
	public void showCLSBanKhamTruoc(){
		log.info("Begin showCLSBanKhamTruoc");
		listTPKBanKhamTruoc = new ArrayList<ThuocPhongKhamExt>();
		ThamKhamDelegate thamkhamDel = ThamKhamDelegate.getInstance();
		if(thamkham != null){
			List<ThuocPhongKham> thuocphongkhams = thamkhamDel.getListThuocBanKhamTruoc(thamkham.getTiepdonMa().getTiepdonMa(), thamkham.getThamkhamMa());
			for(ThuocPhongKham tpk:thuocphongkhams){
				ThuocPhongKhamExt tpkExt = new ThuocPhongKhamExt();
				tpkExt.setTpk(tpk);
				tpkExt.setBankhamTen(tpk.getThuocphongkhamThamkham().getThamkhamBankham(true).getDtdmbankhamTen());
				listTPKBanKhamTruoc.add(tpkExt);
			}
		}		
		log.info("End showCLSBanKhamTruoc");
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
		log.info("Begin addBaiThuoc, sobaithuoc = " + sobaithuoc);
		if(dmBaithuocMaso != null && dmBaithuocMaso != 0){
			/*Kiem tra trong list xem da ton tai bai thuoc nao chua
			 * Neu ton tai roi thi khong cho add nua
			 * Neu chua ton tai thi add xuong
			 */
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
				ctThuocPhongKham.setThuocphongkhamChedott(thamkham.getTiepdonMa(true).getDoituongMa(true));//luu doi tuong BHYT, thu phi, mien phi
				ctThuocPhongKham.setDmbaithuocMaso(dmBaithuocMaso);
				if (loaiToaThuocThamKhamVaXuTri.equals(Utils.KE_TOA_BENH_NHAN_TU_MUA)){
					ctThuocPhongKham.setThuocphongkhamSoluong(soluongThuocInBaithuoc);
					ctThuocPhongKhams.add(ctThuocPhongKham);
				}	
				ctThuocPhongKham = new ThuocPhongKham();
			}//end for					
			dmBaithuocMa = "";
			dmBaithuocMaso = 0;				
			
		}
		sobaithuoc = 1;
	}
	//Xu ly su kien tab cua o cach dung.
	public void enterAJAX() throws Exception{
		log.info("*****Begin enterAJAX()");	
		ctThuocPhongKham = new ThuocPhongKham();
		try{			
			/**
			 *  add thong tin Khi cập nhật chỉ định thuốc vào bảng THUOC_PHONG_KHAM, 
			 *  them các thông tin NGAYGIO, BACSI, 
			 *  các thông tin từ bảng TON_KHO, NGAYGIOCN, NHANVIENCN.
			 * 
			 */
			DmThuoc dmThuoc = new DmThuoc();
			dmThuoc.setDmthuocMaso(dmthuocMaso);
			dmThuoc.setDmthuocMa(dmthuocMa);
			dmThuoc.setDmthuocTen(dmthuocTen);
			ctThuocPhongKham.setThuocphongkhamMathuoc(dmThuoc);
			ctThuocPhongKham.setThuocphongkhamSoluong(getThuocphongkhamSoluong());
			ctThuocPhongKham.setThuocphongkhamBacsi(thamkham.getThamkhamBacsi());
			ctThuocPhongKham.setThuocphongkhamNhanviencn(nhanVienCN);
			ctThuocPhongKham.setThuocphongkhamNgaygio(new Date());
			
			ctThuocPhongKham.setThuocphongkhamMaChidan(maChiDan);
			ctThuocPhongKham.setThuocphongkhamChidan(tenChiDan);
						
			String doituongMa = thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa();
			DieuTriUtilDelegate dtUtilDel = DieuTriUtilDelegate.getInstance();
			DmDonViTinh dvt = (DmDonViTinh)dtUtilDel.findByMaSo(dmdonvitinhMaso, "DmDonViTinh", "dmdonvitinhMaso");
			ctThuocPhongKham.getThuocphongkhamMathuoc().setDmdonvitinhMaso(dvt);
			ctThuocPhongKham.setThuocphongkhamChedott(thamkham.getTiepdonMa(true).getDoituongMa(true));//luu doi tuong BHYT, thu phi, mien phi
			//Su dung doi tuong ctThuocPhongKhamSelectedOld de luu lai doi tuong truoc khi chinh sua
			//khi chinh sua xong se xoa doi tuong nay va them vao 1 doi tuong da chinh sua		
			int viTri = -1;
			if(ctThuocPhongKhamSelectedOld != -1 ){
				if (ctThuocPhongKhams.size() > ctThuocPhongKhamSelectedOld){
					viTri = ctThuocPhongKhamSelectedOld ;
					ctThuocPhongKhams.remove(ctThuocPhongKhamSelectedOld);
					ctThuocPhongKhamSelectedOld = -1;				
				}else{
					ctThuocPhongKhamSelectedOld = -1;
					
					ctThuocPhongKham = new ThuocPhongKham();
					maChiDan = "";
					tenChiDan = "";
					setHamluong("");
					setHandung("");
					setDmthuocMa("");
					setDmthuocMaso(null);
					setDmthuocTen("");
					setDmdonvitinhMaso(null);
					setDmdonvitinhTen("");
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
			System.out.println("viTri: " +viTri);
			//Tho add - Kiem tra so luong thuoc cap BN thuoc 1 lo hay nhieu lo thi se tao nhieu ctThuocPhongKham
			if (viTri == -1){
				if(ctThuocPhongKhams.size()>0){
					int vt = -1;
					double sl = 0;
					boolean foundOnNewsList = false;
					for(int i = 0; i< ctThuocPhongKhams.size();i++)
					{
						ThuocPhongKham ctTpk = ctThuocPhongKhams.get(i);	
						if(ctThuocPhongKham.getThuocphongkhamMathuoc().getDmthuocMa().equals(ctTpk.getThuocphongkhamMathuoc().getDmthuocMa())){
							foundOnNewsList = true;
							vt = i;
							sl = ctThuocPhongKham.getThuocphongkhamSoluong() + ctTpk.getThuocphongkhamSoluong();
							break;
						}											
					}						
					if(foundOnNewsList ==  false){
						ctThuocPhongKhams.add(ctThuocPhongKham);
					}else{
						ctThuocPhongKham.setThuocphongkhamSoluong(new Double(sl));
						ctThuocPhongKhams.set(vt, ctThuocPhongKham);
					}					
				}else{
					ctThuocPhongKhams.add(ctThuocPhongKham);
				}	
				//End - Tho add 25/3/2011: Xu ly cong don hay khong cong don cung 1 lo thuoc
			}else{
				ctThuocPhongKhams.add(viTri,ctThuocPhongKham);
			}			

			maChiDan = "";
			tenChiDan = "";
			setThuocdongyMaso(0);
			setHamluong("");
			setHandung("");			
			setDmthuocMa("");
			setDmthuocMaso(null);
			setDmthuocTen("");
			setDmdonvitinhMaso(null);
			setDmdonvitinhTen("");
			setThuocphongkhamSoluong(null);
			if (rowsToUpdate == null){
				rowsToUpdate = new HashSet<Integer>();
			}
			rowsToUpdate.clear();
			for (int i = 0 ; i < ctThuocPhongKhams.size();i++){
				rowsToUpdate.add(new Integer(i));
			}
			
		}catch(Exception e){
			log.info("*****Exception = " + e);
			
		}	
		ctThuocPhongKham = new ThuocPhongKham();
		log.info("*****End enterAJAX()");
	}
	
	public String getDvtName(int index){
		log.info("*****getDVTName()");
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
		log.info("*****Begin chinhSuaAJAX()");
		
		/*
		HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
		HsThtoank hsttk_tmp = hsttkDelegate.findBytiepdonMa(thamkham.getTiepdonMa(true).getTiepdonMa());
		if (hsttk_tmp != null){
			FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA);
			return ;
		}
		*/
		ctThuocPhongKhamSelected = ctThuocPhongKhams.get(index);
		if(ctThuocPhongKhamSelected == null){
			log.info("ctThuocPhongKhamSelected == null");
			return;
		}
		
		
		if (ctThuocPhongKhamSelected != null && ctThuocPhongKhamSelected.getThuocphongkhamMaphieud() != null && !ctThuocPhongKhamSelected.getThuocphongkhamMaphieud().equals("")){
			log.info("ctThuocPhongKhamSelected != null :"+ctThuocPhongKhamSelected); 
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
		setDmdonvitinhMaso(dvt.getDmdonvitinhMaso());
		setDmdonvitinhTen(dvt.getDmdonvitinhTen());
		setDmthuocMaso(ctThuocPhongKham.getThuocphongkhamMathuoc().getDmthuocMaso());
		setDmthuocMa(ctThuocPhongKham.getThuocphongkhamMathuoc().getDmthuocMa());
		setDmthuocTen(ctThuocPhongKham.getThuocphongkhamMathuoc().getDmthuocTen());
		setThuocphongkhamSoluong(ctThuocPhongKham.getThuocphongkhamSoluong());
		
		ctThuocPhongKhamSelectedOld = index;		
		log.info("*****End chinhSuaAJAX()");
	}
	
	public void xoaAJAX(int index) throws Exception{
		log.info("*****Begin xoaAJAX()");		
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
			log.info("*****Exception = " + e);
			
		}
		
		log.info("*****End xoaAJAX()");
	}
	
	///////////////////////////////////////////////////////////////
	
	
	private DtDmNhanVien nhanVienCN = null;
	
	private Boolean hienThiInPhieu = false;
	//
	// Note:
	//public static String XU_TRI_THUOC_TAI_BAN_KHAM = "1";	
	//public static String KE_TOA_BENH_NHAN_TU_MUA = "2";
	//public static String KE_TOA_QUAY_BENH_VIEN = "3";
	//
	//
	@Begin(join = true)
	public String initFromMenu(String fromMenu, String loaiThuocPhongKham){ //   1 or 2 or 3
		this.fromMenu = fromMenu;
		initB121_5_Ketoabntumua  = "no";
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
		doiTuong = thamkham.getTiepdonMa(true).getDoituongMa(true);
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
	
	@Factory("initB121_5_Ketoabntumua")
	@Begin(nested = true)
	public void init() throws Exception {
		log.info("***Starting init ***");
		tempInit();
		this.fromMenu = "";
		log.info("***Finished init ***");
	}

	private void tempInit(){
		try{
			initService();
			
			sLoiDan = "";
			tenLoiDans = "";  //bao.ttc: reset loi dan
			//mabs = "";
			thuocdongyMaso = 0;
			if (maBanKhamOut == null || maBanKhamOut.equals("") || maTiepDonOut == null || maTiepDonOut.equals("")){
				resetValueFromMenu();
				return;
			}
			hsmListThuocDYNgoaiTru = new HashMap<Integer,List>();
			ctThuocPhongKham = new ThuocPhongKham();
//			SetInforUtil.setInforIfNullForThuocPhongKham(ctThuocPhongKham);			
			
			ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
			ThamKham thamkham_temp = tkDelegate.findByBanKhamVaMaTiepDon(maBanKhamOut, maTiepDonOut);
			
			if (thamkham_temp == null){
				resetValueFromMenu();
				FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND);
				return ;
			}
			thamkham = thamkham_temp;
			
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
			if (loaiToaThuocThamKhamVaXuTri.equals(Utils.KE_TOA_BENH_NHAN_TU_MUA)){
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
					TiepDonDelegate tdonDelegate = TiepDonDelegate.getInstance();
					String tiepDonMa = thamkham.getTiepdonMa(true).getTiepdonMa();
					TiepDon tiepdon = tdonDelegate.find(tiepDonMa);
					DtDmBanKham dtDmBK = tiepdon.getTiepdonBankham();
					if(dtDmBK.getDtdmbankhamTinhchat().equals(IConstantsRes.KHO_BHYT_MA)){
						dmKhoa  = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_BHYT_MA, "DmKhoa", "dmkhoaMa");					
					}else if(dtDmBK.getDtdmbankhamTinhchat().equals(IConstantsRes.KHO_TRE_EM_MA)){
						dmKhoa  = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_TE_MA, "DmKhoa", "dmkhoaMa");					
					}else if(dtDmBK.getDtdmbankhamTinhchat().equals(IConstantsRes.KHO_CHINH_MA)){
						dmKhoa  = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_KC_MA, "DmKhoa", "dmkhoaMa");					
					}else if(dtDmBK.getDtdmbankhamTinhchat().equals(IConstantsRes.KHO_NOITRU_MA)){
						dmKhoa  = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_NT_MA, "DmKhoa", "dmkhoaMa");					
					}
					//System.out.println("Lay ton thuoc tai kho: " + dmKhoa.getDmkhoaMa());
				}
				
			}
			//End Tho add
			khoaMaSo = dmKhoa.getDmkhoaMaso();
			khoaMa = dmKhoa.getDmkhoaMa();
			doiTuong = thamkham.getTiepdonMa().getDoituongMa();
			
			initB121_5_Ketoabntumua = "0";
			
			ThuocPhongKhamDelegate thuocPKDele = ThuocPhongKhamDelegate.getInstance();
			
			ctThuocPhongKhams = thuocPKDele.findByMaTiepDonAndMaBanKham(thamkham.getTiepdonMa(true).getTiepdonMa(), thamkham.getThamkhamBankham(true).getDtdmbankhamMa(),String.valueOf(loaiToaThuocThamKhamVaXuTri));			
			if(ctThuocPhongKhams!=null && ctThuocPhongKhams.size()>0)
			{
				tenLoiDans = ctThuocPhongKhams.get(0).getThuocphongkhamTenloidan(); 
				log.info("****ctThuocPhongKhams SIZE: "+ctThuocPhongKhams.size());
				log.info("****ctThuocPhongKhams MA: "+ctThuocPhongKhams.get(0).getThuocphongkhamMa());
				log.info("****LOI DAN: "+sLoiDan);
				for(int i=0;i<ctThuocPhongKhams.size(); i++)
				{
					if(ctThuocPhongKhams.get(i).getThuocphongkhamMaloidan()!=null && !ctThuocPhongKhams.get(i).getThuocphongkhamMaloidan().equals(""))
					{
						tenLoiDans = ctThuocPhongKhams.get(i).getThuocphongkhamTenloidan();
						log.info("****LOI DAN index i: "+i+ sLoiDan);
						
						break;
					}
				}
			}
			ThuocDongYNgoaiTruDelegate thuocDYNgoaiTruDel = ThuocDongYNgoaiTruDelegate.getInstance();
			List<ThuocDongYNgoaiTru> lstThuocDongYNgoaiTru = thuocDYNgoaiTruDel.findByMaTiepDonAndMaBanKham(thamkham.getTiepdonMa(true).getTiepdonMa(), thamkham.getThamkhamBankham(true).getDtdmbankhamMa(),String.valueOf(loaiToaThuocThamKhamVaXuTri));
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
			
			//log.info("ctThuocPhongKhams1111111:"+ctThuocPhongKhams);
			
			for (ThuocPhongKham tpk:  ctThuocPhongKhams){
//				SetInforUtil.setInforIfNullForThuocPhongKham(tpk);
				maFinish = tpk.getThuocphongkhamThamkham().getThamkhamMa().toString();
				log.info("-----ma phieu: " + maFinish);
				break;
			}
			
			//20110116 bao.ttc
			refreshDmThuoc();
				
			setOtherValue();
			destroyService();
			
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
		ThamKham thamkham_loadthuoc = thamKhamDelegate.getLastThamKhamWithSoTheBHYTAndBanKham(thamkham.getTiepdonMa(true).getTiepdonSothebh(), thamkham.getThamkhamBankham().getDtdmbankhamMaso());
		if(thamkham_loadthuoc != null){
			log.info("DA THAY thamkham_loadthuoc " + thamkham_loadthuoc.getThamkhamMa());
			List<ThuocPhongKham> ctToaThuocTruoc = thuocPKDele.findByMaTiepDonAndMaBanKham(thamkham_loadthuoc.getTiepdonMa(true).getTiepdonMa(), thamkham_loadthuoc.getThamkhamBankham(true).getDtdmbankhamMa(),String.valueOf(loaiToaThuocThamKhamVaXuTri));
			try{
				// 20110112 bao.ttc: Xu ly cac thong tin cua toa thuoc truoc - Tho update ngay 27/5/2011
				if (ctToaThuocTruoc != null && ctToaThuocTruoc.size() > 0){
					//Cong don so luong thuoc, neu cac lo co cung 1 ma thuoc					
					List<ThuocPhongKham> listTpk_Temp = new ArrayList<ThuocPhongKham>();
					for (ThuocPhongKham toaThuocTruoc : ctToaThuocTruoc){
						toaThuocTruoc.setThuocphongkhamMaphieud(null);
						toaThuocTruoc.setThuocphongkhamDatt(null);
						toaThuocTruoc.setThuocphongkhamMa(null);
						toaThuocTruoc.setThuocphongkhamNgaygio(new Date());
						toaThuocTruoc.setThuocphongkhamNgaygiocn(new Date());
						toaThuocTruoc.setThuocphongkhamMalk(null);//khi ghi nhan, kiem tra lay ton lo thich hop, set lai don gia, ma lk
						
						if (thamkham.getThamkhamBacsi() != null)
							toaThuocTruoc.setThuocphongkhamBacsi(thamkham.getThamkhamBacsi());
						
						ThuocPhongKham thuocphongkhamNew = new ThuocPhongKham();
						if(listTpk_Temp.size() == 0){
							thuocphongkhamNew = (ThuocPhongKham) BeanUtils.cloneBean(toaThuocTruoc);
							listTpk_Temp.add(thuocphongkhamNew);
						}else{
							ThuocPhongKham thuocphongkham_Finded = new ThuocPhongKham();
							boolean foundInList = false;
							int vitri = -1;
							for(int j = 0; j<listTpk_Temp.size(); j++){
								ThuocPhongKham thuocphongkhamNew1 = listTpk_Temp.get(j);
								if(thuocphongkhamNew1.getThuocphongkhamMathuoc().getDmthuocMaso().equals(toaThuocTruoc.getThuocphongkhamMathuoc().getDmthuocMaso())){
									thuocphongkham_Finded = thuocphongkhamNew1;
									foundInList = true;
									vitri = j;
									break;
								}
							}
							if(foundInList == true){
								thuocphongkhamNew = (ThuocPhongKham) BeanUtils.cloneBean(toaThuocTruoc);
								thuocphongkhamNew.setThuocphongkhamSoluong(thuocphongkhamNew.getThuocphongkhamSoluong() + thuocphongkham_Finded.getThuocphongkhamSoluong());
								listTpk_Temp.set(vitri,thuocphongkhamNew);
							}else{
								thuocphongkhamNew = (ThuocPhongKham) BeanUtils.cloneBean(toaThuocTruoc);
								listTpk_Temp.add(thuocphongkhamNew);
							}
						}
						thuocphongkhamNew = new ThuocPhongKham();
					}
					System.out.println("listTpk_Temp.size: " +listTpk_Temp.size());
					for (ThuocPhongKham toaThuocTruoc : listTpk_Temp){						
						ctThuocPhongKhams.add(toaThuocTruoc);
					}
					//ctThuocPhongKhams.addAll(ctToaThuocTruoc);
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
		log.info("END getToaThuocTruoc() ctThuocPhongKhams.size() " + ctThuocPhongKhams.size());
	}
	/** ========== END ========== */
	//Ham khoi tao cac web service
	public void initService() {		
		if (loaiToaThuocThamKhamVaXuTri.equals( Utils.KE_TOA_BENH_NHAN_TU_MUA)){
			titleThamKhamVaXuTri = IConstantsRes.KE_TOA_VE;
		}		
		hienThiInPhieu = true;
		
		log.info("titleThamKhamVaXuTri:"+titleThamKhamVaXuTri);
		log.info("loaiToaThuocThamKhamVaXuTri:"+loaiToaThuocThamKhamVaXuTri);
	}	
	
	private void setOtherValue() {
		SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
		if (thamkham.getTiepdonMa().getBenhnhanMa()
				.getBenhnhanNgaysinh() != null
				&& !thamkham.getTiepdonMa().getBenhnhanMa()
						.getBenhnhanNgaysinh().equals("")) {
			ngaySinh = formatter.format(thamkham.getTiepdonMa()
					.getBenhnhanMa().getBenhnhanNgaysinh().getTime());
		}
		if (thamkham.getThamkhamNgaygio() != null
				&& !thamkham.getThamkhamNgaygio().equals("")) {
			thoiGian = formatter
					.format(thamkham.getThamkhamNgaygio().getTime());
		}
		if(thamkham.getThamkhamNgaygio() != null){
			gioThamKham = Utils.getGioPhut(thamkham.getThamkhamNgaygio()) ;
		}
	}
	//
	// ***************************************************************************************
	// Ham ghi nhan
	public String ghiNhanAjax() throws Exception {
		log.info("*****Begin ghiNhanAjax() *****");
		
		HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
		// 20110330 bao.ttc: Kiem tra xem da Thanh toan hay chua de disable nut Ghi nhan doi voi BN BHYT
		if ( thamkham.getTiepdonMa(true).getTiepdonMa() != null && !thamkham.getTiepdonMa(true).getTiepdonMa().equals("") ){			
			if ( thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa().equals("BH") ) {				
				HsThtoank hsttk_tmp = hsttkDelegate.findBytiepdonMa(thamkham.getTiepdonMa(true).getTiepdonMa());				
				if (hsttk_tmp != null){
					FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA);
					return "";
				}
			}
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
					System.out.println("Ghi nhan ----thuocDY.Maso= " + thuocDY.getThuocdongyMaso());
					System.out.println("Ghi nhan-----bai thuoc ma so= "+thuocDY.getDmbaithuocMaso().getDmbaithuocMaso());
					thuocDY.setThuocdongyThamkham(thamkham);
					thuocDY.setTiepdonMa(thamkham.getTiepdonMa(true));
					listThuocDY.set(0, thuocDY);
					hsmThuocDYNGTTemp.put(thuocDY.getDmbaithuocMaso().getDmbaithuocMaso(), listThuocDY);   										 		
			    }
			}
			hsmListThuocDYNgoaiTru = hsmThuocDYNGTTemp;	
			for (ThuocPhongKham tpk:  ctThuocPhongKhams){
				RemoveUtil.removeIfNullForThuocPhongKham(tpk);
				tpk.setThuocphongkhamThamkham(thamkham);
				
				log.info(loaiToaThuocThamKhamVaXuTri);
				tpk.setThuocphongkhamLoai(String.valueOf(loaiToaThuocThamKhamVaXuTri));// 1: xu tri tai ban kham, ...
				
				DmKhoa dmKhoa = new DmKhoa();
				dmKhoa.setDmkhoaMaso(khoaMaSo);
				tpk.setThuocphongkhamKhoa(dmKhoa);
				// phuc.lc 25-10-2010 : BEGIN Add code
				tpk.setThuocphongkhamTenloidan(tenLoiDans);
				
				// Set don gia bao hiem bang don gia (gia bao hiem cua thuoc hien tai khong su dung)
				tpk.setThuocphongkhamDongiabh(tpk.getThuocphongkhamDongia());
				
				System.out.println("Bai Thuoc ma so: "+ tpk.getDmbaithuocMaso());
			}
		
			ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
			if (thamkham.getThamkhamNgaygio() == null){
				thamkham.setThamkhamNgaygio(new Date());
			}
			
			thamkham.setThamkhamNgaygiocn(new Date());
			//** INSERT NHAN VIEN CAP NHAT
			DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
			DtDmNhanVien nhanVienCN = nvDelegate.findByNd(identity.getUsername());
			thamkham.setThamkhamNhanviencn(nhanVienCN);
			
			RemoveUtil.removeAllNullFromTiepDon(thamkham.getTiepdonMa());
			RemoveUtil.removeAllNullFromThamKham(thamkham);
			
			String doituongMa = thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa();
			DieuTriUtilDelegate dtUtilDelegate = DieuTriUtilDelegate.getInstance();
			tpkDelegate.createToaThuocPhongKham(ctThuocPhongKhams,thamkham, loaiToaThuocThamKhamVaXuTri, hsmListThuocDYNgoaiTru);
			
			/* find danh sach thuoc phong kham thuoc ban kham va ma tiep don*/
			ThuocPhongKhamDelegate thuocPKDele = ThuocPhongKhamDelegate.getInstance();
			ctThuocPhongKhams = thuocPKDele.findByMaTiepDonAndMaBanKham(maTiepDonOut, maBanKhamOut,String.valueOf(loaiToaThuocThamKhamVaXuTri));
			if (ctThuocPhongKhams == null){
				ctThuocPhongKhams = new ArrayList<ThuocPhongKham>();
			}
			
			log.info("goi lai thong tin thuoc pk s:" + ctThuocPhongKhams);
			
			for (ThuocPhongKham tpk2:  ctThuocPhongKhams){
//				SetInforUtil.setInforIfNullForThuocPhongKham(tpk);
				maFinish = tpk2.getThuocphongkhamThamkham().getThamkhamMa().toString();
				log.info("-----ma phieu: " + maFinish);
				break;
			}
			setOtherValue();
			//////////////////////////////////////////////////////////////////
			
			FacesMessages.instance().add(IConstantsRes.SUCCESS);
			hsmListThuocDYNgoaiTru.clear();
			
			ctThuocPhongKhamSelectedOld = -1;
			ctThuocPhongKhamSelected = new ThuocPhongKham();
			
		}catch (Exception e) {
			FacesMessages.instance().add(IConstantsRes.FAIL);
			//resultHidden = "fail";
			e.printStackTrace();
			log.error("*************loi***********" + e.toString());
		}

		log.info("*****End ghiNhanAjax() *****");
		
		// 20110912 bao.ttc: fix sau khi ghi nhan khong tro ve Tham kham va Xu tri de tiep tuc In phieu CLS
//		if (fromMenu == null || fromMenu.equals("")){
//			return quaylai();
//		}
		
		return null;

	}

	public String quaylai() throws Exception {
		initB121_5_Ketoabntumua = null;
		
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

	// ***************************************************************************************
	//Ham huy cac service da khoi tao
	public void destroyService() {
//		try {
//			log.debug("===== begin destroyService() method");			
////			thamKhamWS = null;
//			log.debug("***** End destroyService() method");
//		} catch (Exception ex) {
//			log.debug("*****destroyService Exception: " + ex);
//		}
	}	
	
	//Ham  se duoc goi khi het session (session timeout cau hinh trong file web.xml)
	@Destroy 
	public void destroy() {
		log.info("************************* destroy KeToaBenhNhanTuMua");
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

	public String getInitB121_5_Ketoabntumua() {
		return initB121_5_Ketoabntumua;
	}

	public void setInitB121_5_Ketoabntumua(
			String initB121_5_Ketoabntumua) {
		this.initB121_5_Ketoabntumua = initB121_5_Ketoabntumua;
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
		KeToaBnTuMuaAction.log = log;
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
	
	public Integer getDmthuocMaso() {
		return dmthuocMaso;
	}

	public void setDmthuocMaso(Integer dmthuocMaso) {
		this.dmthuocMaso = dmthuocMaso;
	}
	
	public String getDmthuocMa() {
		return dmthuocMa;
	}

	public void setDmthuocMa(String dmthuocMa) {
		this.dmthuocMa = dmthuocMa;
	}
	
	public String getDmthuocTen() {
		return dmthuocTen;
	}

	public void setDmthuocTen(String dmthuocTen) {
		this.dmthuocTen = dmthuocTen;
	}
	
	public Double getThuocphongkhamSoluong() {
		return thuocphongkhamSoluong;
	}

	public void setThuocphongkhamSoluong(Double thuocphongkhamSoluong) {
		this.thuocphongkhamSoluong = thuocphongkhamSoluong;
	}
	
	public Integer getDmdonvitinhMaso() {
		return dmdonvitinhMaso;
	}

	public void setDmdonvitinhMaso(Integer dmdonvitinhMaso) {
		this.dmdonvitinhMaso = dmdonvitinhMaso;
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
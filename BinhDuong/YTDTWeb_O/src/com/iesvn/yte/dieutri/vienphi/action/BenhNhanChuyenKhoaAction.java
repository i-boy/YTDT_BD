package com.iesvn.yte.dieutri.vienphi.action;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DmKhoaDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaKhoaDelegate;
import com.iesvn.yte.dieutri.delegate.YteLogDelegate;
import com.iesvn.yte.dieutri.entity.HsThtoan;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaKhoa;
import com.iesvn.yte.dieutri.entity.YteLog;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmTang;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(ScopeType.CONVERSATION)
@Name("B3114_Benhnhanchuyenkhoa")
@Synchronized(timeout = 6000000)
public class BenhNhanChuyenKhoaAction implements Serializable {
	
	private static final long serialVersionUID = 10L;
	private static Logger logger = Logger.getLogger(BenhNhanChuyenKhoaAction.class);
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
	private Hsba hsba;
	private HsbaKhoa hsbaKhoa;
	
	
//	private DmKhoa khoa;
	
	private DmTang tangDangDieuTri;
	
	private DmKhoa khoaChuyenDen;
	private DmTang tangChuyenDen;
	
	private String gioChuyenKhoa;
	private String ngayChuyenKhoa;
	
	private String ngayHt;
		
	private DmKhoaDelegate dmKhoaDel = DmKhoaDelegate.getInstance();
	private HashMap<String, DmKhoa> hmDmKhoaNTAll = new HashMap<String, DmKhoa>();
	private List<SelectItem> listDmKhoaNTs = new ArrayList<SelectItem>();
	private List<DmKhoa> listDmKhoaNTAll = new ArrayList<DmKhoa>();
	private List<SelectItem> listDmTangs = new ArrayList<SelectItem>();
	private String hidGhiNhan = "false";
	
	public String getNgayHt() {
		return ngayHt;
	}
	
	public void setNgayHt(String ngayHt) {
		this.ngayHt = ngayHt;
	}
	
	@Begin(join =  true)
	public String init() {
		logger.info("-----init()-----");
		ngayHt = Utils.getCurrentDate();
		reset();
		refreshDmKhoaNT();
		return "VienPhiTaiKhoa_SoLieuBNSuDung_BenhNhanChuyenKhoa";
	}
	
	@End
	public void endFunct(){
		
	}
	
	public void end(){
		HsbaKhoaDelegate hsbaKhoaDelegate = HsbaKhoaDelegate.getInstance();
		
		if (hsbaKhoa.getHsbakhoaMaso() == null || khoaChuyenDen.getDmkhoaMaso() == null || tangChuyenDen.getDmtangMaso() == null) {			
			FacesMessages.instance().add(IConstantsRes.NHAP_CHINH_XAC_KHOA_BUONG);
			return;
		}
		
		// 20120312 bao.ttc: chinh Facade de sinh ra hsbaKhoa moi neu Sovaovien + Khoa + Tang chuyen den chua ton tai
		String hsbaMa = hsbaKhoaDelegate.benhNhanChuyenKhoa(hsbaKhoa, khoaChuyenDen, null, tangChuyenDen);
		//String hsbaMa = hsba.getHsbaSovaovien();
		if(hsbaMa != null && !hsbaMa.equals("")){
//			Luu log he thong
			 YteLogDelegate yteLogDele = YteLogDelegate.getInstance();
	         YteLog yteLog = new YteLog();

	         yteLog.setForm("B3114_Benhnhanchuyenkhoa");
	         yteLog.setUserId(identity == null ? "NULL" : identity.getUsername());
	         yteLog.setObjectId(hsba.getHsbaSovaovien());
	         yteLog.setLogString("Khoa hien tai: "+ (hsbaKhoa.getKhoaMa() == null ? "NULL" :hsbaKhoa.getKhoaMa().getDmkhoaTen()) +"\n"+ 
	        		 			 "Tang hien tai: " + (hsbaKhoa.getDmtangMaso() == null ? "NULL" : hsbaKhoa.getDmtangMaso().getDmtangTen())+"\n"+
	        		 			 "Khoa chuyen den: "+ (khoaChuyenDen == null ? "NULL" : khoaChuyenDen.getDmkhoaTen())+"\n"+
	        		 			 "Tang chuyen den: "+(tangChuyenDen== null ? "NULL" : tangChuyenDen.getDmtangTen()));
	         yteLog.setDateTime(new Date());

	         yteLogDele.create(yteLog);
	         
			FacesMessages.instance().add(IConstantsRes.CAP_NHAT_THANH_CONG_VOI_SO_VAO_VIEN, hsbaMa);
		} else {
			FacesMessages.instance().add(IConstantsRes.UPDATE_FAIL);
		}
		reset();
		return;
	}
	
	public void loadHsba() {
		logger.info("-----loadHsba()-----");
		hidGhiNhan ="false";
		String hsbaMa = hsba.getHsbaSovaovien();
		
		if (hsbaMa == null || hsbaMa.equals("") ){
			logger.info("hsba.getHsbaSovaovien() == null");
			reset();
			return;
		}
			
		try {
			
			HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
			Hsba hsbaCur = hsbaDelegate.find(hsbaMa);
		    if  (hsbaCur == null){
		    	FacesMessages.instance().add(IConstantsRes.HSBA_NULL, hsbaMa);
				logger.info("khong tim thay sobenhan");
				reset();
				return ;
		    }
		    //ThoVNA - Neu da thanh toan vien phi roi thi khong cho chuyen khoa
		    HsThtoan hsthtoan = HsThtoanDelegate.getInstance().findBySovaovien(hsbaMa);
		    if (hsthtoan != null && hsthtoan.getHsthtoanNgaytt() != null) {
		    	hidGhiNhan ="true";
				FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA);
			}
		    // hsba da ton tai
		    hsba = hsbaCur;
		    //SetInforUtil.setInforIfNullForHSBA(hsba);
		    
		    if (hsba.getHsbaKhoadangdt() == null){
		    	FacesMessages.instance().add(IConstantsRes.HSBA_NULL, hsbaMa);
		    	logger.info("khoaDangDieuTri == null");
		    	reset();
		    	return;
		    }
			
			HsbaKhoaDelegate hsbaKhoaDelegate = HsbaKhoaDelegate.getInstance();
			
			// 20120312 bao.ttc: them thong tin Tang khi tim kiem hsbaKhoa BN dang dieu tri
			hsbaKhoa = hsbaKhoaDelegate.findBySoVaoVienAndKhoaMaAndTang(hsba.getHsbaSovaovien(), hsba.getHsbaKhoadangdt(true).getDmkhoaMa(),hsba.getTangDangdt(true).getDmtangMaso());
			if (hsbaKhoa == null) {
				FacesMessages.instance().add(IConstantsRes.HSBA_NULL, hsbaMa);
				reset();
				return;
			}
			khoaChuyenDen = new DmKhoa();
			tangChuyenDen = new DmTang();
			tangDangDieuTri = hsbaKhoa.getDmtangMaso();
		} catch (Exception e) {
			FacesMessages.instance().add(IConstantsRes.HSBA_NULL, hsbaMa);
			e.printStackTrace();
			reset();
		}

	}
	
	public void reset(){
		gioChuyenKhoa = "";
		ngayChuyenKhoa = "";
		hsba = new Hsba();
		hsbaKhoa = new HsbaKhoa();
		//khoa = new DmKhoa();
		tangDangDieuTri = new DmTang();
		khoaChuyenDen = new DmKhoa();
		tangChuyenDen = new DmTang();
		hidGhiNhan ="false";
	}
	
	public void onblurMaKhoaAction(){
		logger.info("-----BEGIN onblurMaKhoaAction()-----");
		if(khoaChuyenDen != null && khoaChuyenDen.getDmkhoaMa() != null){
			String maKhoa = khoaChuyenDen.getDmkhoaMa();
			if(hmDmKhoaNTAll != null){
				DmKhoa dmKhoa = (DmKhoa)hmDmKhoaNTAll.get(maKhoa.toUpperCase());
				if(dmKhoa != null) {
					khoaChuyenDen = dmKhoa;
					logger.info("Tim ma khoa: Da thay khoa "+ khoaChuyenDen.getDmkhoaTen());
				}
				else {
					khoaChuyenDen = new DmKhoa();
					return;
				}
			}
			tangChuyenDen = new DmTang();
			refreshDmTang();
		}
		logger.info("-----END onblurMaKhoaAction()-----");
	}
	
	public void onblurTenKhoaAction(){
		logger.info("-----BEGIN onblurTenKhoaAction()-----");
		if(khoaChuyenDen != null && khoaChuyenDen.getDmkhoaTen() != null){
			String tenKhoa = khoaChuyenDen.getDmkhoaTen();
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
		    	khoaChuyenDen.setDmkhoaMaso(dmKhoa_Finded.getDmkhoaMaso());
		    	khoaChuyenDen.setDmkhoaMa(dmKhoa_Finded.getDmkhoaMa());
		    	khoaChuyenDen.setDmkhoaTen(dmKhoa_Finded.getDmkhoaTen());
		    }else{
		    	khoaChuyenDen = new DmKhoa();
		    	return;
		    }
		    tangChuyenDen = new DmTang();
		    refreshDmTang();
		}
		logger.info("-----END onblurTenKhoaAction()-----");
	}
	
	public void refreshDmKhoaNT(){
		dmKhoaDel = DmKhoaDelegate.getInstance();
		listDmKhoaNTAll.clear();
		listDmKhoaNTs.clear();
		listDmKhoaNTAll = dmKhoaDel.getKhoaNT();
		hmDmKhoaNTAll.clear();
		for(DmKhoa o: listDmKhoaNTAll){
			hmDmKhoaNTAll.put(o.getDmkhoaMa(), o);
		}
		for(DmKhoa each : listDmKhoaNTAll){
			listDmKhoaNTs.add(new SelectItem(each.getDmkhoaTen()));
		}
	}
	
	public void onblurTenTangAction(){
		if(tangChuyenDen != null && tangChuyenDen.getDmtangTen() != null){
			DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
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
		if(khoaChuyenDen != null && khoaChuyenDen.getDmkhoaMaso() != null){
			String khoaMa = khoaChuyenDen.getDmkhoaMa().toUpperCase();
			DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
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
	
	public String getGioChuyenKhoa() {
		return gioChuyenKhoa;
	}
	public void setGioChuyenKhoa(String gioChuyenKhoa) {
		this.gioChuyenKhoa = gioChuyenKhoa;
	}
	public String getNgayChuyenKhoa() {
		return ngayChuyenKhoa;
	}
	public void setNgayChuyenKhoa(String ngayChuyenKhoa) {
		this.ngayChuyenKhoa = ngayChuyenKhoa;
	}
	public static Logger getLogger() {
		return logger;
	}
	public static void setLogger(Logger logger) {
		BenhNhanChuyenKhoaAction.logger = logger;
	}
	public Identity getIdentity() {
		return identity;
	}
	public void setIdentity(Identity identity) {
		this.identity = identity;
	}
	public Hsba getHsba() {
		return hsba;
	}
	public void setHsba(Hsba hsba) {
		this.hsba = hsba;
	}
	public HsbaKhoa getHsbaKhoa() {
		return hsbaKhoa;
	}
	public void setHsbaKhoa(HsbaKhoa hsbaKhoa) {
		this.hsbaKhoa = hsbaKhoa;
	}
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	public DmKhoa getKhoaChuyenDen() {
		return khoaChuyenDen;
	}
	
	public void setKhoaChuyenDen(DmKhoa khoaChuyenDen) {
		this.khoaChuyenDen = khoaChuyenDen;
	}
	
	public List<SelectItem> getListDmKhoaNTs() {
		return listDmKhoaNTs;
	}

	public void setListDmKhoaNTs(List<SelectItem> listDmKhoaNTs) {
		this.listDmKhoaNTs = listDmKhoaNTs;
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
	
	public String getHidGhiNhan() {
		return hidGhiNhan;
	}
	public void setHidGhiNhan(String hidGhiNhan) {
		this.hidGhiNhan = hidGhiNhan;
	}
	
	public DmTang getTangDangDieuTri() {
		return tangDangDieuTri;
	}
	public void setTangDangDieuTri(DmTang tangDangDieuTri) {
		this.tangDangDieuTri = tangDangDieuTri;
	}
}

package com.iesvn.yte.dieutri.duocpham.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

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
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.dieutri.delegate.DmLoaiThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DmPhanLoaiThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DmThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.KiemKeKhoDelegate;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.KiemKe;
import com.iesvn.yte.dieutri.entity.KiemKeKho;
import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.entity.DmPhanLoaiThuoc;
import com.iesvn.yte.entity.DmThuoc;

import com.iesvn.yte.util.PagedList;

@Scope(CONVERSATION)
@Name("B4144_CapnhatSoLieuKiemKeGUI")
@Synchronized(timeout = 6000000)
public class CapNhatSoLieuThucTeGUI implements Serializable {

	private static final long serialVersionUID = -340255448614143263L;
	private static Logger log = Logger.getLogger(CapNhatSoLieuThucTeGUI.class);
	private KiemKeKho kiemkeKho;
	@DataModel
	private List<KiemKeKho> listKiemKeKho = new ArrayList<KiemKeKho>();

	@Out(required=false)
	@In(required=false)
	private String resetFormCapNhatSLGUI=null;
	
	@In(required = false)
	@Out(required = false)
	private KiemKe kiemkeOut;
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
	@In(required = false)
	@Out(required = false)
	private KiemKeKho kiemkeKhoOut;
	
	private Integer dmtMaso;
	private String dmtMa;
	private String dmtTen;
	private Integer loaihang_maso;
	private String loaihang_ma;
	private Integer plthuoc_maso;
	private String plthuoc_ma;
	private String plthuocTen;
	private Integer dmnctMaso;
	private String dmnctMa;
	private Integer dmnguonkinhphiMaso;
	private String dmnguonkinhphiMa;
	private Double tonhientai;
	private Double tonsauKiemKe;
	private List<SelectItem> listDmThuocs = new ArrayList<SelectItem>();	
	private List<SelectItem> listDmPhanLoaiThuocs = new ArrayList<SelectItem>();
	private HashMap<String, DmThuoc> hmDmThuoc = new HashMap<String, DmThuoc>();
	private DmThuocDelegate dmThuocDelegate;
	private DmLoaiThuocDelegate dmLoaiThuocDelegate;
	private DmPhanLoaiThuocDelegate dmPhanLoaiThuocDelegate;
	private String dmloaithuocTen="";
	private HashMap<String, DmLoaiThuoc> hmLoaiThuoc = new HashMap<String, DmLoaiThuoc>();
	private HashMap<String, DmPhanLoaiThuoc> hmPhanLoaiThuoc = new HashMap<String, DmPhanLoaiThuoc>();
	private KiemKe kiemke;
	
	private int page;
	private int itemsPerPage; 
	private List<KiemKeKho> items;
	private int totalPages;
	
	public int getItemsPerPage() { 
		return itemsPerPage;
	}  

	public void setItemsPerPage(int itemsPerPage) {  
		this.itemsPerPage = itemsPerPage;
	}  

	public int getPage() { 
		return page;
	}  
	
	public void setPage(int page) {
		this.page = page; 
		fetch();
	} 
	
	public int getTotalPages() { 
		return totalPages;
	}  
	
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages; 
	} 
	
	public List<KiemKeKho> getList() {
		return items;  
	} 
	
	private void fetch() { 
		KiemKeKhoDelegate kkkDel=KiemKeKhoDelegate.getInstance();
		int total = kkkDel.getAllKiemKeKhoTotal(kiemke.getMaphieukiem()).intValue();  
		int limit = itemsPerPage;  
		int offset = (page - 1) * itemsPerPage;  
		List<KiemKeKho> list = kkkDel.getItemKiemKeKhos(kiemke.getMaphieukiem(),limit, offset);  
		items = new PagedList(list, total, itemsPerPage);  
	} 
	
	public void resetItemsPerPage(){
		setPage(1);
		KiemKeKhoDelegate kkkDel=KiemKeKhoDelegate.getInstance();
		int total = kkkDel.getAllKiemKeKhoTotal(kiemke.getMaphieukiem()).intValue(); 
		totalPages = (int)(total/itemsPerPage) + 1;
	}	
	
	public Integer getDmtMaso() {
		return dmtMaso;
	}

	public void setDmtMaso(Integer dmtMaso) {
		this.dmtMaso = dmtMaso;
	}
	
	public String getDmtMa() {
		return dmtMa;
	}

	public void setDmtMa(String dmtMa) {
		this.dmtMa = dmtMa;
	}
	
	public String getDmtTen() {
		return dmtTen;
	}

	public void setDmtTen(String dmtTen) {
		this.dmtTen = dmtTen;
	}
	
	public Integer getLoaihang_maso() {
		return loaihang_maso;
	}

	public void setLoaihang_maso(Integer loaihangMaso) {
		loaihang_maso = loaihangMaso;
	}

	public String getLoaihang_ma() {
		return loaihang_ma;
	}

	public void setLoaihang_ma(String loaihangMa) {
		loaihang_ma = loaihangMa;
	}
	
	public Integer getPlthuoc_maso() {
		return plthuoc_maso;
	}

	public void setPlthuoc_maso(Integer plthuoc_maso) {
		this.plthuoc_maso = plthuoc_maso;
	}
	
	public String getDmloaithuocTen(){
		return dmloaithuocTen;
	}
	public void setDmloaithuocTen(String dmloaithuocTen){
		this.dmloaithuocTen = dmloaithuocTen;
	}
	
	public String getPlthuoc_ma(){
		return plthuoc_ma;
	}
	public void setPlthuoc_ma(String plthuoc_ma){
		this.plthuoc_ma = plthuoc_ma;
	}
	
	public String getPlthuocTen(){
		return plthuocTen;
	}
	public void setPlthuocTen(String plthuocTen){
		this.plthuocTen = plthuocTen;
	}
	
	public String getDmnctMa() {
		return dmnctMa;
	}

	public void setDmnctMa(String dmnctMa) {
		this.dmnctMa = dmnctMa;
	}
	
	public Integer getDmnctMaso() {
		return dmnctMaso;
	}

	public void setDmnctMaso(Integer dmnctMaso) {
		this.dmnctMaso = dmnctMaso;
	}
	
	public String getDmnguonkinhphiMa() {
		return dmnguonkinhphiMa;
	}

	public void setDmnguonkinhphiMa(String dmnguonkinhphiMa) {
		this.dmnguonkinhphiMa = dmnguonkinhphiMa;
	}
	
	public Integer getDmnguonkinhphiMaso() {
		return dmnguonkinhphiMaso;
	}

	public void setDmnguonkinhphiMaso(Integer dmnguonkinhphiMaso) {
		this.dmnguonkinhphiMaso = dmnguonkinhphiMaso;
	}
	
	public Double getTonsauKiemKe() {
		return tonsauKiemKe;
	}

	public void setTonsauKiemKe(Double tonsauKiemKe) {
		this.tonsauKiemKe = tonsauKiemKe;
	}
	
	public Double getTonhientai() {
		return tonhientai;
	}

	public void setTonhientai(Double tonhientai) {
		this.tonhientai = tonhientai;
	}
	
	private List<SelectItem> listDmLoaiThuocs = new ArrayList<SelectItem>();
	
	public List<SelectItem> getListDmLoaiThuocs() {
		return listDmLoaiThuocs;
	}

	public void setListDmLoaiThuocs(List<SelectItem> listDmLoaiThuocs) {
		this.listDmLoaiThuocs = listDmLoaiThuocs;
	}
	
	public List<SelectItem> getListDmPhanLoaiThuocs() {
		return listDmPhanLoaiThuocs;
	}

	public void setListDmPhanLoaiThuocs(List<SelectItem> listDmPhanLoaiThuocs) {
		this.listDmPhanLoaiThuocs = listDmPhanLoaiThuocs;
	}
	
	public void onblurMaLoaiThuocAction() {
		log.info("-----BEGIN onblurMaLoaiThuocAction()-----");
		DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)hmLoaiThuoc.get(loaihang_ma.toUpperCase());
		if(dmLoaiThuoc != null) {
			setDmloaithuocTen(dmLoaiThuoc.getDmloaithuocTen());
			setLoaihang_maso(dmLoaiThuoc.getDmloaithuocMaso());
		}
		else {
			setDmloaithuocTen("");	
			setLoaihang_maso(null);
		}	
		setDmtMa("");
		setDmtTen("");
		listDmThuocs.clear();
		hmDmThuoc.clear();
		listDmPhanLoaiThuocs.clear();
		hmPhanLoaiThuoc.clear();
		refreshDmPhanLoaiThuoc();
		refreshDmThuoc();
		log.info("-----END onblurMaLoaiThuocAction()-----");
	}

	public void onblurTenLoaiThuocAction() {
		log.info("-----BEGIN onblurTenLoaiThuocAction()-----");
		Boolean hasTenLoai = false;
		
		java.util.Set set = hmLoaiThuoc.entrySet();
	    Iterator i = set.iterator();
	    DmLoaiThuoc dmLoaiThuoc_Finded = new DmLoaiThuoc();
	    while(i.hasNext()){
	    	Map.Entry me = (Map.Entry)i.next();
	    	DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)me.getValue();
	    	if(dmLoaiThuoc.getDmloaithuocTen() == dmloaithuocTen || dmLoaiThuoc.getDmloaithuocTen().equals(dmloaithuocTen)){
	    		hasTenLoai = true;
	    		dmLoaiThuoc_Finded = dmLoaiThuoc;
	    		break;
	    	}	    		
	    }
	    if(hasTenLoai == true) {
			setDmloaithuocTen(dmLoaiThuoc_Finded.getDmloaithuocTen());
			setLoaihang_maso(dmLoaiThuoc_Finded.getDmloaithuocMaso());
		}
		else {
			setDmloaithuocTen("");	
			setLoaihang_maso(null);
		}	
	    setDmtMa("");
		setDmtTen("");
		setPlthuoc_ma("");
		setPlthuocTen("");
		setPlthuoc_maso(null);
		listDmThuocs.clear();
		hmDmThuoc.clear();
		refreshDmPhanLoaiThuoc();
		refreshDmThuoc();	    
		log.info("-----END onblurTenLoaiThuocAction()-----");
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

	public void onblurMaPhanLoaiThuocAction() {
		log.info("-----BEGIN onblurMaPhanLoaiThuocAction()-----");
		DmPhanLoaiThuoc dmPhanLoaiThuoc = (DmPhanLoaiThuoc)hmPhanLoaiThuoc.get(plthuoc_ma.toUpperCase());
		if(dmPhanLoaiThuoc != null) {
			setPlthuocTen(dmPhanLoaiThuoc.getDmphanloaithuocTen());
			setPlthuoc_maso(dmPhanLoaiThuoc.getDmphanloaithuocMaso());
		}
		else {
			setPlthuocTen("");	
			setPlthuoc_maso(null);
		}	
		
		setDmtMa("");
		setDmtTen("");
		listDmThuocs.clear();
		hmDmThuoc.clear();
		refreshDmThuoc();
		log.info("-----END onblurMaLoaiThuocAction()-----");
	}

	public void onblurTenPhanLoaiThuocAction() {
		log.info("-----BEGIN onblurTenPhanLoaiThuocAction()-----");
		Boolean hasTenPhanLoai = false;
		
		java.util.Set set = hmPhanLoaiThuoc.entrySet();
	    Iterator i = set.iterator();
	    DmPhanLoaiThuoc dmPhanLoaiThuoc_Finded = new DmPhanLoaiThuoc();
	    while(i.hasNext()){
	    	Map.Entry me = (Map.Entry)i.next();
	    	DmPhanLoaiThuoc dmPhanLoaiThuoc = (DmPhanLoaiThuoc)me.getValue();
	    	if(dmPhanLoaiThuoc.getDmphanloaithuocTen() == dmloaithuocTen || dmPhanLoaiThuoc.getDmphanloaithuocTen().equals(plthuocTen)){
	    		hasTenPhanLoai = true;
	    		dmPhanLoaiThuoc_Finded = dmPhanLoaiThuoc;
	    		break;
	    	}	    		
	    }
	    if(hasTenPhanLoai == true) {
	    	setPlthuocTen(dmPhanLoaiThuoc_Finded.getDmphanloaithuocTen());
			setPlthuoc_maso(dmPhanLoaiThuoc_Finded.getDmphanloaithuocMaso());
		}
		else {
			setPlthuocTen("");	
			setPlthuoc_maso(null);
		}
	    setDmtMa("");
		setDmtTen("");
		listDmThuocs.clear();
		hmDmThuoc.clear();
		refreshDmThuoc();	    
		log.info("-----END onblurTenLoaiThuocAction()-----");
	}
	
	public void refreshDmPhanLoaiThuoc(){
		listDmPhanLoaiThuocs.clear();
		dmPhanLoaiThuocDelegate = DmPhanLoaiThuocDelegate.getInstance();
		hmPhanLoaiThuoc.clear();
		List<DmPhanLoaiThuoc> lstDmPLT = dmPhanLoaiThuocDelegate.findByDtdmloaiMa(loaihang_ma);
		if(lstDmPLT != null || lstDmPLT.size() > 0){
			for(DmPhanLoaiThuoc o:lstDmPLT){
				listDmPhanLoaiThuocs.add(new SelectItem(o.getDmphanloaithuocTen()));
				hmPhanLoaiThuoc.put(o.getDmphanloaithuocMa(), o);
			}
		}	
		refreshDmThuoc();
	}
	
	public List<SelectItem> getListDmThuocs() {
		return listDmThuocs;
	}

	public void setListDmThuocs(List<SelectItem> listDmThuocs) {
		this.listDmThuocs = listDmThuocs;
	}
	
	public void onblurMaThuocAction(){
		log.info("-----BEGIN onblurMaThuocAction()-----");
		if (dmtMa != null) {
			DmThuoc dmThuoc = hmDmThuoc.get(dmtMa.toUpperCase());
			if(dmThuoc != null) {
				setDmtTen(dmThuoc.getDmthuocTen());
				setDmtMaso(dmThuoc.getDmthuocMaso());
				log.info("-----DA THAY DMTHUOC-----");
			}
			else {
				setDmtTen("");
				setDmtMaso(null);
			}
		}
		log.info("-----END onblurMaThuocAction()-----");
	}
	
	public void onblurTenThuocAction(){
		log.info("-----BEGIN onblurTenThuocAction()-----");
		Boolean hasTenThuoc = false;
		String maThuoc = "";
		Integer masoThuoc = null;
		if( hmDmThuoc != null ){
			java.util.Set set = hmDmThuoc.entrySet();
			Iterator i = set.iterator();
			while(i.hasNext()){
				Map.Entry me = (Map.Entry)i.next();
			   	DmThuoc dmThuoc = (DmThuoc)me.getValue();
			   	if(dmThuoc.getDmthuocTen().trim() == dmtTen || dmThuoc.getDmthuocTen().trim().equals(dmtTen)){
			   		hasTenThuoc = true;
			   		maThuoc = dmThuoc.getDmthuocMa();
			   		masoThuoc = dmThuoc.getDmthuocMaso();
			   		break;
			   	}	    		
			}
		}
	    if(hasTenThuoc){
	    	setDmtMa(maThuoc);
	    	setDmtMaso(masoThuoc);
			log.info("-----DA THAY DMTHUOC-----");
	    }else{
	    	setDmtMa("");
	    }
		log.info("-----END onblurTenThuocAction()-----");
	}
	
	public void refreshDmThuoc(){
		listDmThuocs.clear();
		hmDmThuoc.clear();
		dmThuocDelegate = DmThuocDelegate.getInstance();
		List<DmThuoc> lstDmThuoc = dmThuocDelegate.findByLoaiPhanLoaiThuoc(loaihang_ma, plthuoc_ma);	
		if(lstDmThuoc !=null && lstDmThuoc.size()>0)
		{
			for(DmThuoc o: lstDmThuoc){
				listDmThuocs.add(new SelectItem(o.getDmthuocTen()));
				hmDmThuoc.put(o.getDmthuocMa(), o);
			}
		}
	}
	
	@Factory("resetFormCapNhatSLGUI")
	public void resetFormCapNhatSLGUI() {
		log.info("resetFormCapNhatSLGUI()");
		resetvalue();
		itemsPerPage = 20;
		kiemke = kiemkeOut;
		if(kiemkeKhoOut != null){			
			kiemkeKho = kiemkeKhoOut;
			listKiemKeKho.add(kiemkeKho);
		}	
		refreshDmLoaiThuoc();
		refreshDmPhanLoaiThuoc();
		refreshDmThuoc();
	}
	
	@Begin (join = true)
	public String init(String kho) throws Exception {
		resetvalue();
		itemsPerPage = 20;
		kiemke = kiemkeOut;
		if(kiemkeKhoOut != null){
			kiemkeKho = kiemkeKhoOut;
			listKiemKeKho.add(kiemkeKho);
		}
		refreshDmLoaiThuoc();
		refreshDmPhanLoaiThuoc();
		refreshDmThuoc();
		return "QuanLyKhoChinh_KiemKeKhoChinh_CapNhatSoLieuKiemKeThucTeGUI";
	}
	
	@End
	public void end(){
		
	}
	
	public String quayve() throws Exception{
		resetvalue();
		resetFormCapNhatSLGUI = null;
		return "QuanLyKhoLe_KiemKeKhoChinh_XemBangKiemKeDinhKy";
	}
	
	public void resetvalue(){
		log.info("---resetValue");
		kiemkeKho = new KiemKeKho();
		listKiemKeKho = new ArrayList<KiemKeKho>();
		loaihang_ma ="";
		loaihang_maso = null;
		dmloaithuocTen ="";
		plthuoc_ma ="";
		plthuocTen="";
		plthuoc_maso = null;
		dmtMa="";
		dmtMaso=null;
		dmtTen="";
		dmnctMaso = null;
		dmnctMa ="";
		dmnguonkinhphiMaso = null;
		dmnguonkinhphiMa ="";
	}
	
	public void resetFields(){
		log.info("---resetFields");
		loaihang_ma ="";
		loaihang_maso = null;
		dmloaithuocTen ="";
		plthuoc_ma ="";
		plthuocTen="";
		plthuoc_maso = null;
		dmtMa="";
		dmtMaso=null;
		dmtTen="";
		dmnctMaso = null;
		dmnctMa ="";
		dmnguonkinhphiMaso = null;
		dmnguonkinhphiMa ="";
		kiemkeKho = new KiemKeKho();
	}
	
	public void timkiem(){
		log.info("---timkiem---");
		KiemKeKhoDelegate kiemkeKhoDel = KiemKeKhoDelegate.getInstance();
		List<KiemKeKho> lstKiemKeKho = kiemkeKhoDel.findKiemKeKhoForCapNhatSLGUI(kiemke.getMaphieukiem(), loaihang_maso, plthuoc_maso, dmtMaso, dmnctMaso, dmnguonkinhphiMaso);
		if(lstKiemKeKho != null || lstKiemKeKho.size() >0){
			listKiemKeKho = lstKiemKeKho;
		}else{
			FacesMessages.instance().add("Kh\u00F4ng c\u00F3 thu\u1ED1c trong b\u1EA3ng ki\u1EC3m k\u00EA: " +kiemke.getMaphieukiem(), null);
		}
	}
	
	public void resetForm(){
		resetvalue();
	}
	
	public void ghinhan(){
		log.info("---Begin ghinhan---");
		KiemKeKhoDelegate kiemkeKhoDel = KiemKeKhoDelegate.getInstance();
		DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
		DtDmNhanVien nv = nvDelegate.findByNd(identity.getUsername());
		if(nv == null){
			FacesMessages.instance().add(identity.getUsername() + " kh\u00F4ng thu\u1ED9c nh\u00E2n vi\u00EAn c\u1EE7a B\u1EC7nh vi\u1EC7n");
			return;
		}
		if(kiemkeKho.getKiemkeMa() != null){
			kiemkeKho.setDtdmnhanvienCn(nv);
			kiemkeKho.setKiemkeNgaygiocn(new Date());
			kiemkeKhoDel.edit(kiemkeKho);		
			//Neu kiemkeKho sau khi ghi nhan co thay doi neu co trong listKiemKeKho thi rest lai record kiemkeKho trong list nay tren GUI
			int i = 0;
			for(KiemKeKho kiemkekho:listKiemKeKho){
				if(kiemkekho.getKiemkeMa() == kiemkeKho.getKiemkeMa()){
					listKiemKeKho.set(i, kiemkeKho);
					break;
				}
				i++;
			}
			resetFields();
			FacesMessages.instance().add("C\u1EADp nh\u1EADt s\u1ED1 li\u1EC7u th\u00E0nh c\u00F4ng!");
		}else{
			FacesMessages.instance().add("Kh\u00F4ng c\u00F3 d\u1EEF li\u1EC7u Ki\u1EC3m k\u00EA kho!");
		}
		log.info("---End ghinhan---");
	}
	
	public void selectKiemKeKho(int index){
		log.info("---selectKiemKeKho---"+ index);
		kiemkeKho = listKiemKeKho.get(index);
	}
	
	public KiemKeKho getKiemkeKho(){
		return this.kiemkeKho;
	}
	
	public void setKiemkeKho(KiemKeKho kiemkeKho){
		this.kiemkeKho = kiemkeKho;
	}
}

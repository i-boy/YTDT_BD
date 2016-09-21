/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.duocpham.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
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

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.log.Log;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DmLoaiThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DmPhanLoaiThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DmThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DmKhoaDelegate;
import com.iesvn.yte.dieutri.vienphi.action.TonKhoEx;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.entity.DmNhaCungCap;
import com.iesvn.yte.entity.DmPhanLoaiThuoc;
import com.iesvn.yte.entity.DmThuoc;
import com.iesvn.yte.util.IConstantsRes;

@Scope(CONVERSATION)
@Name("B4414_Inchitietnhaphang")
@Synchronized(timeout = 6000000)
public class Inchitietnhaphang implements Serializable {
	@Logger
	private Log log;
	
	@Out(required=false)
	@In(required=false)
	private String reportPathKC=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeKC=null;
	
	String dmKhoXuat = "";
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintKC = null;
	
	@DataModel
	private List<DmPhanLoaiThuoc> listDmPLThuoc = new ArrayList<DmPhanLoaiThuoc>();

	@DataModelSelection("listDmPLThuoc")
	@Out(required = false)
	private DmPhanLoaiThuoc dmPLThuocSelect; 
	
	@Out(required=false)
	@In(required=false)
	private String resetFormB4414=null;
	
	private String thoigian_thang=null;  
	private String thoigian_nam=null;
	private String tungay=null;
	private String denngay=null;
	private String ngayhientai;
	private int index=0;
	private Integer loaihang_maso=null;
	private Integer hang_maso=null;
	private Integer kho_maso=null;
	private Integer noiban_maso=null;	
	private String chon=null;
	private String loaihang_ma="";
	private String hang_ma="";
	private String kho_ma="";
	private String noiban_ma="";
	
	private String plthuoc_ma="";
	//Tho add	
	private String nhapXuatHang ="0";
	private String dmTenKho ="";
	private List<SelectItem> listDmKhos = new ArrayList<SelectItem>();
	HashMap<String,DmKhoa> hmDmKho = new HashMap<String,DmKhoa>();
	//DmKho-khoa tra
	private Boolean coTra = false;
	private String khoatra_ma ="";
	private Integer khoatra_maso = null;
	private String dmTenKhoaTra ="";
	private List<SelectItem> listDmKhoaTras = new ArrayList<SelectItem>();
	HashMap<String,DmKhoa> hmDmKhoaTra = new HashMap<String,DmKhoa>();
	
	//DmNCC_Xuat
	private Boolean coTraNCC = false;
	private Integer ncc_maso=null;
	private String ncc_ma = "";
	
	//DmKhoKhoa nhan
	private Integer noinhan_maso=null;
	private String noinhan_ma="";
	private String dmTenKhoaNhan ="";
	private List<SelectItem> listDmKhoaNhans = new ArrayList<SelectItem>();
	HashMap<String,DmKhoa> hmDmKhoaNhan = new HashMap<String,DmKhoa>();
		
	private DmThuocDelegate dmThuocDelegate;
	private DmLoaiThuocDelegate dmLoaiThuocDelegate;
	private DmPhanLoaiThuocDelegate dmPhanLoaiThuocDelegate;
	private DmKhoaDelegate dmKhoaDelegate;
	private String dmtTen="";
	private List<SelectItem> listDmThuocs = new ArrayList<SelectItem>();
	private String dmLoaiTen ="";
	private List<SelectItem> listDmLoaiThuocs = new ArrayList<SelectItem>();
	private String dmPhanLoaiTen ="";
	private List<SelectItem> listDmPhanLoaiThuocs = new ArrayList<SelectItem>();
	HashMap<String, DmLoaiThuoc> hmLoaiThuoc = new HashMap<String, DmLoaiThuoc>();
	HashMap<String, DmPhanLoaiThuoc> hmPhanLoaiThuoc = new HashMap<String, DmPhanLoaiThuoc>();
	HashMap<String,DmThuoc> hmDmThuoc = new HashMap<String,DmThuoc>();
	//End Tho add
	
	@Begin (join = true)
	public String init(String kho) {
		
		log.info("init()");
		dmKhoXuat = kho;
		ngayhientai = com.iesvn.yte.util.Utils.getCurrentDate();
		resetForm();
		return "BaoCaoDuoc_InBaoCaoKhoChinh_InChiTietNhapXuatHang";
	}
	
	@Factory("resetFormB4414")
	public void initresetFormB4413() {
		log.info("init()");
		ngayhientai = com.iesvn.yte.util.Utils.getCurrentDate();
		resetForm();
	}
	
	@End
	public void end(){

	}
	
	//Begin Tho add
	public String getDmTenKho() {
		return dmTenKho;
	}

	public void setDmTenKho(String dmTenKho) {
		this.dmTenKho = dmTenKho;
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
	
	public String getDmPhanLoaiTen() {
		return dmPhanLoaiTen;
	}

	public void setDmPhanLoaiTen(String dmPhanLoaiTen) {
		this.dmPhanLoaiTen = dmPhanLoaiTen;
	}

	public List<SelectItem> getListDmPhanLoaiThuocs() {
		return listDmPhanLoaiThuocs;
	}

	public void setListDmPhanLoaiThuocs(List<SelectItem> listDmPhanLoaiThuocs) {
		this.listDmPhanLoaiThuocs = listDmPhanLoaiThuocs;
	}
	
	public void onblurMaLoaiAction(){
		log.info("-----BEGIN onblurMaLoaiAction()-----"+loaihang_ma);
		DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)hmLoaiThuoc.get(loaihang_ma.toUpperCase());
		if(dmLoaiThuoc != null) {
			setDmLoaiTen(dmLoaiThuoc.getDmloaithuocTen());
		}
		else {
			setDmLoaiTen("");			
		}
		setPlthuoc_ma("");
	    setDmPhanLoaiTen("");
		refreshDmPhanLoaiThuoc();
		refreshDmThuoc();
		listDmPLThuoc.clear();
		log.info("-----END onblurMaLoaiAction()-----");
	}
	
	public void onblurTenLoaiThuocAction(){
		log.info("-----BEGIN onblurTenLoaiThuocAction()-----");
		Boolean hasTenLoai = false;
		String maLoai = null;
		if (dmLoaiTen == ""){
			setLoaihang_ma("");
		}
		java.util.Set set = hmLoaiThuoc.entrySet();
	    Iterator i = set.iterator();
	    while(i.hasNext()){
	    	Map.Entry me = (Map.Entry)i.next();
	    	DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)me.getValue();
	    	if(dmLoaiThuoc.getDmloaithuocTen() == dmLoaiTen || dmLoaiThuoc.getDmloaithuocTen().equals(dmLoaiTen)){
	    		hasTenLoai = true;
	    		maLoai = dmLoaiThuoc.getDmloaithuocMa();
	    		break;
	    	}	    		
	    }	
	    if(hasTenLoai){
	    	setLoaihang_ma(maLoai);
	    }else{
	    	setLoaihang_ma("");
	    }
	    
	    setPlthuoc_ma("");
	    setDmPhanLoaiTen("");
	    refreshDmPhanLoaiThuoc();
	    refreshDmThuoc();
	    listDmPLThuoc.clear();
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
		    listDmLoaiThuocs.add(new SelectItem("All"));
		    while(i.hasNext()){
		    	Map.Entry me = (Map.Entry)i.next();
		    	DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)me.getValue();
		    	listDmLoaiThuocs.add(new SelectItem(dmLoaiThuoc.getDmloaithuocTen()));
		    }
		}
	}
	
	public void onblurMaPhanLoaiAction(){
		log.info("-----BEGIN onblurMaPhanLoaiAction()-----");
		DmPhanLoaiThuoc dmPhanLoaiThuoc = (DmPhanLoaiThuoc)hmPhanLoaiThuoc.get(plthuoc_ma.toUpperCase());
		if(dmPhanLoaiThuoc != null) {
			setDmPhanLoaiTen(dmPhanLoaiThuoc.getDmphanloaithuocTen());
		}
		else {
			setDmPhanLoaiTen("");
			setPlthuoc_ma("");
		}
		log.info("-----END onblurMaThuocAction()-----");
	}
	
	public void onblurTenPhanLoaiThuocAction(){
		log.info("-----BEGIN onblurTenPhanLoaiThuocAction()-----");
		Boolean hasTenLoai = false;
		String maPhanLoai = null;
		if (dmPhanLoaiTen == ""){
			setPlthuoc_ma("");
		}
		java.util.Set set = hmPhanLoaiThuoc.entrySet();
	    Iterator i = set.iterator();
	    while(i.hasNext()){
	    	Map.Entry me = (Map.Entry)i.next();
	    	DmPhanLoaiThuoc dmPhanLoaiThuoc = (DmPhanLoaiThuoc)me.getValue();
	    	if(dmPhanLoaiThuoc.getDmphanloaithuocTen() == dmPhanLoaiTen || dmPhanLoaiThuoc.getDmphanloaithuocTen().equals(dmPhanLoaiTen)){
	    		hasTenLoai = true;
	    		maPhanLoai = dmPhanLoaiThuoc.getDmphanloaithuocMa();
	    		break;
	    	}	    		
	    }	
	    if(hasTenLoai){
	    	setPlthuoc_ma(maPhanLoai);
	    }else{
	    	setPlthuoc_ma("");
	    }
		log.info("-----END onblurTenThuocAction()-----");
	}

	public void refreshDmPhanLoaiThuoc(){
		listDmPhanLoaiThuocs.clear();		
		listDmPhanLoaiThuocs.add(new SelectItem("All"));
		dmPhanLoaiThuocDelegate = DmPhanLoaiThuocDelegate.getInstance();
		hmPhanLoaiThuoc.clear();
		if( !loaihang_ma.equals("") ){
			List<DmPhanLoaiThuoc> lstDmPLThuoc = dmPhanLoaiThuocDelegate.findByDtdmloaiMa(loaihang_ma);
			if(lstDmPLThuoc != null && lstDmPLThuoc.size()>0){
				for(DmPhanLoaiThuoc o: lstDmPLThuoc){
					listDmPhanLoaiThuocs.add(new SelectItem(o.getDmphanloaithuocTen()));
					hmPhanLoaiThuoc.put(o.getDmphanloaithuocMa(), o);
				}
			}
		}
	}
		
	public String getDmtTen() {
		return dmtTen;
	}

	public void setDmtTen(String dmtTen) {
		this.dmtTen = dmtTen;
	}

	public List<SelectItem> getListDmThuocs() {
		return listDmThuocs;
	}

	public void setListDmThuocs(List<SelectItem> listDmThuocs) {
		this.listDmThuocs = listDmThuocs;
	}
	
	public void onblurMaThuocAction(){
		log.info("-----BEGIN onblurMaThuocAction()-----");
		DmThuoc dmThuoc = hmDmThuoc.get(hang_ma.toUpperCase());
		if(dmThuoc != null) {
			setDmtTen(dmThuoc.getDmthuocTen());
			setHang_maso(dmThuoc.getDmthuocMaso());
			log.info("-----DA THAY DMTHUOC-----");
		}
		else {
			setDmtTen("");
			setHang_maso(null);
			setHang_ma("");
		}
		log.info("-----END onblurMaThuocAction()-----");
	}
	
	public void onblurTenThuocAction(){
		log.info("-----BEGIN onblurTenThuocAction()-----"+dmtTen);
		Boolean hasTenThuoc = false;
		String maThuoc = null;
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
	    	setHang_ma(maThuoc);
	    	setHang_maso(masoThuoc);
			log.info("-----DA THAY DMTHUOC-----");
	    }else{
	    	setHang_ma("");
	    	setHang_maso(masoThuoc);
	    }		
		log.info("-----END onblurTenThuocAction()-----");
	}

	public void refreshDmThuoc(){
		listDmThuocs.clear();
		hmDmThuoc.clear();
		dmThuocDelegate = DmThuocDelegate.getInstance();
		List<DmThuoc> lstDmThuoc = dmThuocDelegate.findByLoai_ListPhanLoaiThuoc(loaihang_ma, listDmPLThuoc);	
		if(lstDmThuoc !=null && lstDmThuoc.size()>0)
		{
			for(DmThuoc o: lstDmThuoc){
				listDmThuocs.add(new SelectItem(o.getDmthuocTen()));
				hmDmThuoc.put(o.getDmthuocMa(), o);
			}
		}
	}
	
	public List<SelectItem> getListDmKhos() {
		return listDmKhos;
	}

	public void setListDmKhos(List<SelectItem> listDmKhos) {
		this.listDmKhos = listDmKhos;
	}
	
	public void onblurMaKhoAction(){
		log.info("-----BEGIN onblurMaKhoAction()-----");
		DmKhoa dmKhoa = (DmKhoa)hmDmKho.get(kho_ma.toUpperCase());
		if(dmKhoa != null) {
			setDmTenKho(dmKhoa.getDmkhoaTen());
			setKho_maso(dmKhoa.getDmkhoaMaso());
		}
		else {
			setDmTenKho("");	
			setKho_maso(null);
		}
		setKhoatra_ma("");
    	setDmTenKhoaTra("");
    	setNoinhan_ma("");
    	setDmTenKhoaNhan("");
		if(kho_ma.toUpperCase().equals(IConstantsRes.KHOA_KC_MA)){
			refreshDmKhoaKhoTra();
		}else
    		refreshDmKhoaTra();
		log.info("-----END onblurMaKhoAction()-----");
	}
	
	public void onblurTenKhoAction(){
		log.info("-----BEGIN onblurTenKhoAction()-----");
		Boolean hasTenKho = false;
		String maKho = null;
		Integer masoKho = null;
		if (dmTenKho == ""){
			setKho_ma("");
		}
		java.util.Set set = hmDmKho.entrySet();
	    Iterator i = set.iterator();
	    while(i.hasNext()){
	    	Map.Entry me = (Map.Entry)i.next();
	    	DmKhoa dmKhoa = (DmKhoa)me.getValue();
	    	if(dmKhoa.getDmkhoaTen() == dmTenKho || dmKhoa.getDmkhoaTen().equals(dmTenKho)){
	    		hasTenKho = true;
	    		maKho = dmKhoa.getDmkhoaMa();
	    		masoKho = dmKhoa.getDmkhoaMaso();
	    		break;
	    	}	    		
	    }	
	    if(hasTenKho){
	    	setKho_ma(maKho);
	    	setKho_maso(masoKho);	    	
	    }else{
	    	setKho_ma("");
	    	setKho_maso(null);
	    }	
	    setKhoatra_ma("");
    	setDmTenKhoaTra("");
    	setNoinhan_ma("");
    	setDmTenKhoaNhan("");
	    if(maKho.equals(IConstantsRes.KHOA_KC_MA))
			refreshDmKhoaKhoTra();
    	else
    		refreshDmKhoaTra();
		log.info("-----END onblurTenKhoAction()-----");
	}
	
	public void refreshDmKho(){
		listDmKhos.clear();
		dmKhoaDelegate = DmKhoaDelegate.getInstance();
		hmDmKho.clear();
		List<DmKhoa> lstDmKho = dmKhoaDelegate.getKhoChinh_KhoLe();	
		if(lstDmKho !=null && lstDmKho.size()>0)
		{
			for(DmKhoa o: lstDmKho){
				listDmKhos.add(new SelectItem(o.getDmkhoaTen()));
				hmDmKho.put(o.getDmkhoaMa(), o);
			}
		}
	}	
	//Begin Dm Khoa tra
	public List<SelectItem> getListDmKhoaTras() {
		return listDmKhoaTras;
	}

	public void setListDmKhoaTras(List<SelectItem> listDmKhoaTras) {
		this.listDmKhoaTras = listDmKhoaTras;
	}	
	
	public String getDmTenKhoaTra() {
		return dmTenKhoaTra;
	}

	public void setDmTenKhoaTra(String dmTenKhoaTra) {
		this.dmTenKhoaTra = dmTenKhoaTra;
	} 
	
	public void onblurMaKhoaTraAction(){
		log.info("-----BEGIN onblurMaKhoaTraAction()-----");
		DmKhoa dmKhoa = (DmKhoa)hmDmKhoaTra.get(khoatra_ma.toUpperCase());
		if(dmKhoa != null) {
			setDmTenKhoaTra(dmKhoa.getDmkhoaTen());
			setKhoatra_maso(dmKhoa.getDmkhoaMaso());
		}
		else {
			setDmTenKhoaTra("");
			setKhoatra_maso(null);
		}		
		log.info("-----END onblurMaKhoaTraAction()-----");
	}
	
	public void onblurTenKhoaTraAction(){
		log.info("-----BEGIN onblurTenKhoaTraAction()-----");
		Boolean hasTenKhoa = false;
		String maKhoa = null;
		Integer masoKhoa = null;
		if (dmTenKhoaTra == ""){
			setKhoatra_ma("");
			setKhoatra_maso(null);
		}
		java.util.Set set = hmDmKhoaTra.entrySet();
	    Iterator i = set.iterator();
	    while(i.hasNext()){
	    	Map.Entry me = (Map.Entry)i.next();
	    	DmKhoa dmKhoa = (DmKhoa)me.getValue();
	    	if(dmKhoa.getDmkhoaTen() == dmTenKhoaTra || dmKhoa.getDmkhoaTen().equals(dmTenKhoaTra)){
	    		hasTenKhoa = true;
	    		maKhoa = dmKhoa.getDmkhoaMa();
	    		masoKhoa = dmKhoa.getDmkhoaMaso();
	    		break;
	    	}	    		
	    }	
	    if(hasTenKhoa){
	    	setKhoatra_ma(maKhoa);
	    	setKhoatra_maso(masoKhoa);
	    }else{
	    	setKhoatra_ma("");
	    	setKhoatra_maso(null);
	    }
		log.info("-----END onblurTenKhoaTraAction()-----");
	}
	
	public void refreshDmKhoaTra(){
		listDmKhoaTras.clear();
		dmKhoaDelegate = DmKhoaDelegate.getInstance();
		hmDmKhoaTra.clear();
		List<DmKhoa> lstDmKhoa = dmKhoaDelegate.getKhoaLamSang();
		if(lstDmKhoa !=null && lstDmKhoa.size()>0)
		{
			for(DmKhoa o: lstDmKhoa){
				listDmKhoaTras.add(new SelectItem(o.getDmkhoaTen()));
				hmDmKhoaTra.put(o.getDmkhoaMa(), o);
			}
		}
	}
	
	public void refreshDmKhoaKhoTra(){
		listDmKhoaTras.clear();
		dmKhoaDelegate = DmKhoaDelegate.getInstance();
		hmDmKhoaTra.clear();
		List<DmKhoa> lstDmKhoa = dmKhoaDelegate.getKhoaLamSang();
		List<DmKhoa> lstDmKho = dmKhoaDelegate.getKhoLe();
		if(lstDmKhoa !=null && lstDmKhoa.size()>0)
		{
			for(DmKhoa o: lstDmKhoa){
				listDmKhoaTras.add(new SelectItem(o.getDmkhoaTen()));
				hmDmKhoaTra.put(o.getDmkhoaMa(), o);
			}			
		}
		if(lstDmKho !=null && lstDmKho.size()>0)
		{
			for(DmKhoa o: lstDmKho){
				listDmKhoaTras.add(new SelectItem(o.getDmkhoaTen()));
				hmDmKhoaTra.put(o.getDmkhoaMa(), o);
			}			
		}
	}
	//End DmKhoa Tra
	
	//Begin DmKhoa Nhan
	public List<SelectItem> getListDmKhoaNhans() {
		return listDmKhoaNhans;
	}

	public void setListDmKhoaNhans(List<SelectItem> listDmKhoaNhans) {
		this.listDmKhoaNhans = listDmKhoaNhans;
	}
	
	public String getDmTenKhoaNhan() {
		return dmTenKhoaNhan;
	}

	public void setDmTenKhoaNhan(String dmTenKhoaNhan) {
		this.dmTenKhoaNhan = dmTenKhoaNhan;
	} 
	
	public void onblurMaKhoaNhanAction(){
		log.info("-----BEGIN onblurMaKhoaNhanAction()-----");
		DmKhoa dmKhoa = (DmKhoa)hmDmKhoaNhan.get(noinhan_ma.toUpperCase());
		if(dmKhoa != null) {
			setDmTenKhoaNhan(dmKhoa.getDmkhoaTen());
			setNoinhan_maso(dmKhoa.getDmkhoaMaso());
		}
		else {
			setDmTenKhoaNhan("");
			setNoinhan_maso(null);
		}		
		log.info("-----END onblurMaKhoaNhanAction()-----");
	}
	
	public void onblurTenKhoaNhanAction(){
		log.info("-----BEGIN onblurTenKhoaNhanAction()-----");
		Boolean hasTenKhoa = false;
		String maKhoa = null;
		Integer masoKhoa = null;
		
		if (dmTenKhoaNhan == ""){
			setNoinhan_ma("");
			setNoinhan_maso(null);
		}
		java.util.Set set = hmDmKhoaNhan.entrySet();
	    Iterator i = set.iterator();
	    while(i.hasNext()){
	    	Map.Entry me = (Map.Entry)i.next();
	    	DmKhoa dmKhoa = (DmKhoa)me.getValue();
	    	if(dmKhoa.getDmkhoaTen() == dmTenKhoaNhan || dmKhoa.getDmkhoaTen().equals(dmTenKhoaNhan)){
	    		hasTenKhoa = true;
	    		maKhoa = dmKhoa.getDmkhoaMa();
	    		masoKhoa = dmKhoa.getDmkhoaMaso();
	    		break;
	    	}	    		
	    }	
	    if(hasTenKhoa){
	    	setNoinhan_ma(maKhoa);
	    	setNoinhan_maso(masoKhoa);
	    }else{
	    	setNoinhan_ma("");
	    	setNoinhan_maso(null);
	    }
		log.info("-----END onblurTenKhoaNhanAction()-----");
	}	
	
	//gom ca kho le, kho thanh ly, don vi tuyen duoi va khoa can lam san
	public void refreshDmKhoaNhan(){
		listDmKhoaNhans.clear();
		dmKhoaDelegate = DmKhoaDelegate.getInstance();
		hmDmKhoaNhan.clear();
		List<DmKhoa> lstDmKhoa = dmKhoaDelegate.getKhoaLamSang();
		if(lstDmKhoa !=null && lstDmKhoa.size()>0)
		{
			for(DmKhoa o: lstDmKhoa){
				listDmKhoaNhans.add(new SelectItem(o.getDmkhoaTen()));
				hmDmKhoaNhan.put(o.getDmkhoaMa(), o);
			}
		}
		List<DmKhoa> lstDmKhoLe = dmKhoaDelegate.getKhoLe_TL_TD();
		if(lstDmKhoLe !=null && lstDmKhoLe.size()>0)
		{
			for(DmKhoa o: lstDmKhoLe){
				listDmKhoaNhans.add(new SelectItem(o.getDmkhoaTen()));
				hmDmKhoaNhan.put(o.getDmkhoaMa(), o);
			}
		}
	}
	//End DmKhoa Nhan	
	public Boolean getCoTra() {
		return coTra;
	}

	public void setCoTra(Boolean coTra) {
		this.coTra = coTra;
	}	
	
	public String getNhapXuatHang(){
		return nhapXuatHang;
	}
	
	public void setNhapXuatHang(String nhapXuatHang){
		this.nhapXuatHang = nhapXuatHang;
	}	
	
	public void selectRadioValue( javax.faces.event.ValueChangeEvent event)
	{
		javax.faces.component.html.HtmlSelectOneRadio radio=(javax.faces.component.html.HtmlSelectOneRadio) event.getComponent(); 
		System.out.println(radio.getValue()); 
		if(radio.getValue().toString().equals("0")){
			setNhapXuatHang("0");
		}
		else
		{
			setNhapXuatHang("1");
		}
	}
	//End Tho add	
	
	public String thuchienAction(){
		XuatReport();
		resetForm();
		return "B4160_Chonmenuxuattaptin";
	}
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetList(){
		log.info("=============reset list=============");
		listDmPLThuoc.clear();
	}
	
	/**
	 * Method tra ve String truyen tham so vao file jrxml
	 */
	private String getListDVMaParamsHelp(List<String> inputs){
        log.info("Vao method getListDVMaParamsHelp ");
        log.info("Inputs: " + inputs.toString());
        String result = "";
        for(String each : inputs){
                if(each != "")
                        result += "'"+each + "',";
        }
        result = result.substring(0, result.length()-1);
        log.info("Thoat method getListDVMaParamsHelp ");
        log.info("Output: " + result);
        return result;
	}
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		thoigian_thang=String.valueOf(currentDate.getMonth() +1);  
		thoigian_nam=String.valueOf(currentDate.getYear()+1900);
		tungay = sdf.format(currentDate);
		denngay = sdf.format(currentDate);
		resetFormB4414 = "";
		loaihang_ma="";
		hang_ma="";
		kho_ma="";
		noiban_ma="";
		noinhan_ma="";
		plthuoc_ma="";
		noinhan_maso = null;	
		noiban_maso = null;
		khoatra_ma ="";
		khoatra_maso=null;
		listDmPLThuoc.clear();
		nhapXuatHang="0";
		coTra=false;
		coTraNCC=false;
		ncc_maso = null;
		ncc_ma ="";
		
		setDmLoaiTen("");
		setDmPhanLoaiTen("");
		setHang_ma("");
		setDmTenKho("");
		setDmtTen("");
		setDmTenKhoaTra("");
		setDmTenKhoaNhan("");
		listDmThuocs.clear();
		listDmLoaiThuocs.clear();
		listDmPhanLoaiThuocs.clear();
		hmLoaiThuoc.clear();
		hmPhanLoaiThuoc.clear();
		hmDmThuoc.clear();
		listDmKhoaNhans.clear();
		listDmKhoaTras.clear();
		hmDmKhoaNhan.clear();
		hmDmKhoaTra.clear();
		listDmKhos.clear();
		hmDmKho.clear();
		
		refreshDmLoaiThuoc();
		refreshDmPhanLoaiThuoc();
		refreshDmThuoc();
		refreshDmKho();
		refreshDmKhoaNhan();
		refreshDmKhoaTra();
	}
	
	/**
	 * ham them mot phan tu vao DS
	 */
	public void enter(){
		log.info("bat dau them du lieu vao luoi");
		DmPhanLoaiThuoc plThuoc;
		boolean test=false;
		DieuTriUtilDelegate dtutilDelegate=DieuTriUtilDelegate.getInstance();
		if(!plthuoc_ma.equals("")){
			if((listDmPLThuoc.size()==0) && (plthuoc_ma !=null)){
				log.info("size list bang 0");
				Object obj=dtutilDelegate.findByMa(plthuoc_ma , "DmPhanLoaiThuoc", "dmphanloaithuocMa");
				plThuoc=(DmPhanLoaiThuoc)obj;
				listDmPLThuoc.add(plThuoc);
				log.info("da add phan tu dau tien" + listDmPLThuoc.size());
			}else if ((listDmPLThuoc.size()>0) && (plthuoc_ma !=null)){
				log.info("size list lon hon 0");
				for(DmPhanLoaiThuoc item:listDmPLThuoc){
					if(item.getDmphanloaithuocMa().equals(plthuoc_ma)){
						test=true;
					}
				}
				if(test == false){
					Object obj=dtutilDelegate.findByMa(plthuoc_ma , "DmPhanLoaiThuoc", "dmphanloaithuocMa");
					plThuoc=(DmPhanLoaiThuoc)obj;
					listDmPLThuoc.add(plThuoc);
				}
				log.info("da add phan tu" + listDmPLThuoc.size());
			}
		}
		setPlthuoc_ma("");
		refreshDmThuoc();
	}
	
	
	/**
	 * Ham xoa mot record
	 */
	public void deletedmPLThuoc(){
		log.info("bat dau xoa , size" + listDmPLThuoc.size());
		listDmPLThuoc.remove(dmPLThuocSelect);
		refreshDmThuoc();
		log.info("da xoa , size" + listDmPLThuoc.size());
		log.info("ket thuc xoa");
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeKC="Inchitietnhaphang";
		log.info("Vao Method XuatReport In chi tiet nhap hang");
		String baocao1=null;
		String pathTemplate=null;
		String filename=null;
		try {
			log.info("bat dau method XuatReport ");
			log.info("chon: "+chon);
			if("0".equals(nhapXuatHang)){
				if (IConstantsRes.KHOA_KC_MA.equals(dmKhoXuat)){
					if(!coTra){
						pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D19_solieunhapkho.jrxml";
					}else{
						//co check tra kho
						pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D19_solieunhapkho_trakho.jrxml";						
					}					
					log.info("da thay file templete 5" + pathTemplate);
					filename="Inchitietnhaphang";
					log.info("ten file " + filename);
				}else if (IConstantsRes.KHOA_NT_MA.equals(dmKhoXuat)){	
					if(!coTra){
						pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D19_solieunhapkho_knt.jrxml";
					}else{
						//co check tra kho
						pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D19_solieunhapkho_knt_trakho.jrxml";
					}					
					log.info("da thay file templete 5" + pathTemplate);
					filename="Inchitietnhaphang";
					log.info("ten file " + filename);
				}else {	//Kho BHYT va TE
					pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D19_BHYT_solieunhapkho.jrxml";																										         
					log.info("da thay file templete 5" + pathTemplate);
					filename="Inchitietnhaphang";
					log.info("ten file " + filename);
				}
			}else if("1".equals(nhapXuatHang)){
				if (IConstantsRes.KHOA_KC_MA.equals(dmKhoXuat) || (IConstantsRes.KHOA_NT_MA.equals(dmKhoXuat))){						
					//Nơi nhận: kho Lẻ; khoa phòng (nội, ngoại trú); trả nhà cung cấp; thanh lý; đơn vị tuyến dưới; xử trí tại bàn khám.
					//co check tra nha cung cap hay khong
					if(!coTraNCC){//khong check co tra nha CC thi report se khong hien thi thuoc do kho chinh tra nha cung cap
						pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D20_solieucapphat.jrxml";pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D20_solieucapphat.jrxml";
					}else{//co check tra nha cung cap thi:
						//neu khong chon nha cung cap nao thi report se bao gom thuoc ma tra cho tat ca nha cung cap
						//nguoc lai, se lay tra thuoc cho nha cung cap user chon
						pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D20_solieucapphat_TraNCC.jrxml";						
					}
					log.info("da thay file templete 5" + pathTemplate);
					filename="Inchitietxuathang";
					log.info("ten file " + filename);
				}else{	//Kho BHYT va TE
					pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D20_BHYT_solieucapphat.jrxml";
					log.info("da thay file templete 5" + pathTemplate);
					filename="Inchitietnhaphang";
					log.info("ten file " + filename);
				}
			}			
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			log.info("tu ngay " + tungay);
			log.info("den ngay " + denngay);
			
			
			String strInputTonDauDate = IConstantsRes.INPUT_TONDAU;		
			Date dNgayTonCuoi = sdf.parse(strInputTonDauDate);
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(strInputTonDauDate));
			c.add(Calendar.DATE, 1);
			strInputTonDauDate = sdf.format(c.getTime());
			Date dNgayTonDau = sdf.parse(strInputTonDauDate);
			Date dTuNgay = sdf.parse(tungay);
			if(dTuNgay.before(dNgayTonCuoi) || dTuNgay.equals(dNgayTonCuoi)){
				params.put("TUNGAY", strInputTonDauDate);
				params.put("TUNGAYDATE", dNgayTonDau);
			}else{
				params.put("TUNGAY", tungay);
				params.put("TUNGAYDATE", dTuNgay);
			}
			
			params.put("DENNGAY", denngay);
			params.put("DENNGAYDATE", sdf.parse(denngay));
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			DieuTriUtilDelegate dtutilDelegate=DieuTriUtilDelegate.getInstance();
			log.info("noi ban ma so thanh" + noiban_maso);
			if(nhapXuatHang.equals("0")){
				if(noiban_maso!=null){
					params.put("NOIBANMASO", noiban_maso);
					DmNhaCungCap cungcap=new DmNhaCungCap();
					Object obj=dtutilDelegate.findByMa(noiban_ma , "DmNhaCungCap", "dmnhacungcapMa");
					cungcap=(DmNhaCungCap)obj;
					params.put("NOIBAN", cungcap.getDmnhacungcapTen());
				}else{
					params.put("NOIBAN", "T\u1EA5t c\u1EA3");
				}
				if(khoatra_maso !=null){
					params.put("KHOTRAMASO", khoatra_maso);
				}
			}else if(nhapXuatHang.equals("1")){
				if(noinhan_maso!=null){
					params.put("NOINHANMASO", noinhan_maso);
					DmKhoa khoa=new DmKhoa();
					Object obj=dtutilDelegate.findByMa(noinhan_ma , "DmKhoa", "dmkhoaMa");
					khoa=(DmKhoa)obj;
					params.put("NOINHAN", khoa.getDmkhoaTen());
				}else{
					params.put("NOINHAN", "T\u1EA5t c\u1EA3");
				}
			}
			
			log.info("thuoc ma so " + hang_maso);
			if(hang_maso!=null){
				params.put("THUOCMASO", hang_maso);
			}
			
			log.info("loai thuoc ma so " + loaihang_maso);
			if(loaihang_maso!=null){
				params.put("LOAITHUOCMASO", loaihang_maso);
			}
			
			log.info("kho ma so " + kho_maso);
			if(kho_maso!=null){
				params.put("KHOMASO", kho_maso);
				DmKhoa khoa=new DmKhoa();
				Object obj=dtutilDelegate.findByMaSo(kho_maso, "DmKhoa", "dmkhoaMaso");
				khoa=(DmKhoa)obj;
				params.put("TENKHO", khoa.getDmkhoaTen());
			}
			
			//Truyen tham so danh sach loai phau thuat
			if(listDmPLThuoc.size()>0){
				log.info("bat dau add danh sach phan loai thuoc");
				List<String> listTemp=new ArrayList<String>();
				String phanloaistr=new String("");
				for(DmPhanLoaiThuoc item:listDmPLThuoc){
					listTemp.add(item.getDmphanloaithuocMa());
					phanloaistr+=item.getDmphanloaithuocTen()+",";
				}
				log.info("choi phan loai " + phanloaistr);
				phanloaistr=phanloaistr.substring(0, phanloaistr.length()-1)+".";
				log.info("sau khi modify " + phanloaistr);
				String listLoaiPT=getListDVMaParamsHelp(listTemp);
				log.info("danh sach phan loai thuoc" + listLoaiPT);
				params.put("PLTHUOCMA", listLoaiPT);
				params.put("PHANLOAI", phanloaistr);
			}else{
				params.put("PHANLOAI", "T\u1EA5t c\u1EA3");
			}
			if(ncc_maso!=null){
				params.put("NCC_MASO", ncc_maso);
			}
			
			log.info("================= ");
			Class.forName(IConstantsRes.ORACLE_DRIVER);
			    log.info("da thay driver Oracle");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintKC =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintKC,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"duocpham/", "pdf",filename);
			    reportPathKC=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathKC);
			    index+=1;
			    log.info("Index :" + getIndex());
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Thoat Method XuatReport");
	}
	
	
	public String getThoigian_thang() {
		return thoigian_thang;
	}
	public void setThoigian_thang(String thoigian_thang) {
		this.thoigian_thang = thoigian_thang;
	}
	public String getThoigian_nam() {
		return thoigian_nam;
	}
	public void setThoigian_nam(String thoigian_nam) {
		this.thoigian_nam = thoigian_nam;
	}
	public String getTungay() {
		return tungay;
	}
	public void setTungay(String tungay) {
		this.tungay = tungay;
	}
	public String getDenngay() {
		return denngay;
	}
	public void setDenngay(String denngay) {
		this.denngay = denngay;
	}
	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
	}
	public String getNgayhientai() {
		return ngayhientai;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public Integer getLoaihang_maso() {
		return loaihang_maso;
	}

	public void setLoaihang_maso(Integer loaihang_maso) {
		this.loaihang_maso = loaihang_maso;
	}

	public Integer getHang_maso() {
		return hang_maso;
	}

	public void setHang_maso(Integer hang_maso) {
		this.hang_maso = hang_maso;
	}

	public Integer getKho_maso() {
		return kho_maso;
	}

	public void setKho_maso(Integer kho_maso) {
		this.kho_maso = kho_maso;
	}

	public void setNoiban_maso(Integer noiban_maso) {
		this.noiban_maso = noiban_maso;
	}

	public Integer getNoiban_maso() {
		return noiban_maso;
	}

	public void setNoiban_ma(String noiban_ma) {
		this.noiban_ma = noiban_ma;
	}

	public String getNoiban_ma() {
		return noiban_ma;
	}

	public String getPlthuoc_ma() {
		return plthuoc_ma;
	}

	public void setPlthuoc_ma(String plthuoc_ma) {
		this.plthuoc_ma = plthuoc_ma;
	}

	public void setNoinhan_maso(Integer noinhan_maso) {
		this.noinhan_maso = noinhan_maso;
	}

	public Integer getNoinhan_maso() {
		return noinhan_maso;
	}

	public void setNoinhan_ma(String noinhan_ma) {
		this.noinhan_ma = noinhan_ma;
	}

	public String getNoinhan_ma() {
		return noinhan_ma;
	}

	public String getLoaihang_ma() {
		return loaihang_ma;
	}

	public void setLoaihang_ma(String loaihang_ma) {
		this.loaihang_ma = loaihang_ma;
	}

	public String getHang_ma() {
		return hang_ma;
	}

	public void setHang_ma(String hang_ma) {
		this.hang_ma = hang_ma;
	}

	public String getKho_ma() {
		return kho_ma;
	}

	public void setKho_ma(String kho_ma) {
		this.kho_ma = kho_ma;
	}
	
	public Integer getKhoatra_maso() {
		return khoatra_maso;
	}

	public void setKhoatra_maso(Integer khoatra_maso) {
		this.khoatra_maso = khoatra_maso;
	}
	
	public String getKhoatra_ma() {
		return khoatra_ma;
	}

	public void setKhoatra_ma(String khoatra_ma) {
		this.khoatra_ma = khoatra_ma;
	}

	public String getDmKhoXuat() {
		return dmKhoXuat;
	}

	public void setDmKhoXuat(String dmKhoXuat) {
		this.dmKhoXuat = dmKhoXuat;
	}
	
	public Boolean getCoTraNCC() {
		return coTraNCC;
	}

	public void setCoTraNCC(Boolean coTraNCC) {
		this.coTraNCC = coTraNCC;
	}
	
	public void setNcc_maso(Integer ncc_maso) {
		this.ncc_maso = ncc_maso;
	}

	public Integer getNcc_maso() {
		return noinhan_maso;
	}

	public void setNcc_ma(String ncc_ma) {
		this.ncc_ma = ncc_ma;
	}

	public String getNcc_ma() {
		return ncc_ma;
	}
}

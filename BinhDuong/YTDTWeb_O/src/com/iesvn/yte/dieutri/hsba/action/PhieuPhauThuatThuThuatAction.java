package com.iesvn.yte.dieutri.hsba.action;

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

import org.apache.commons.collections.set.CompositeSet.SetMutator;
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
import org.jboss.seam.security.Identity;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.session.DmBenhIcdFacade;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmClsBangGiaDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaPhieuPhauThuatThuThuatDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
import com.iesvn.yte.dieutri.entity.DtDmLoaiPhauThuat;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.DtDmPhauThuat;
import com.iesvn.yte.dieutri.entity.DtDmVoCam;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.HsbaPhieuPhauThuatThuThuat;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.entity.DmBenhIcd;
import com.iesvn.yte.entity.DmDanToc;
import com.iesvn.yte.entity.DmGioiTinh;
import com.iesvn.yte.entity.DmHuyen;
import com.iesvn.yte.entity.DmNgheNghiep;
import com.iesvn.yte.entity.DmTinh;
import com.iesvn.yte.entity.DmXa;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B292_Phieuphauthuatthuthuat")
@Synchronized(timeout = 6000000)
public class PhieuPhauThuatThuThuatAction implements Serializable {

	private static final long serialVersionUID = -5106695460338085910L;
	private static Logger log = Logger
			.getLogger(PhieuPhauThuatThuThuatAction.class);

	private DtDmNhanVien newNhanVien;
	private DtDmNhanVien newNhanViengm;
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	private HsbaPhieuPhauThuatThuThuat ppttt;
	private String ngayCap;
	private String ngayPTTT;
	private String nosuccess;
	private String nofound;
	private String nofoundHSBA;
	private String crrDate;
	private boolean isUpdate;

	@DataModel
	private List<DtDmNhanVien> bacsiList;
	@In(required = false)
	@Out(required = false)
	@DataModelSelection
	private DtDmNhanVien bacsiSelected;
	
	
	private List<DtDmNhanVien> bacsigmList;
	private DtDmNhanVien bacsigmSelected;
	
	@In(required = false)
	@Out(required = false)
	private String duongdanStrDT = null;

	@Out(required = false)
	@In(required = false)
	private String loaiBCDT = null;

	@In(required = false)
	@Out(required = false)
	JasperPrint jasperPrintDT = null;

	private int index = 0;

	private String resultReportFileName;
	private String resultReportName;
	private String loaiFileXuat;
	@Out(required = false)
	private String isReport = "false";

	@Restrict("#{s:hasRole('NV_KhoaNoiTru') or s:hasRole('QT_KhoaNoiTru')}")
	@Begin(join = true)
	public String init() {

		resetValue();

		return "DieuTri_LapVanBanTheoMau_PhieuPhauThuatThuThuat";
	}
	

	private String gioPTTT;

	public String getGioPTTT() {
		return gioPTTT;
	}

	public void setGioPTTT(String gioPTTT) {
		this.gioPTTT = gioPTTT;
	}

	private String gioVV;
	private String ngayVV;

	private String ngayRut;
	private String ngayCatChi;

	public String getNgayCatChi() {
		return ngayCatChi;
	}

	public void setNgayCatChi(String ngayCatChi) {
		this.ngayCatChi = ngayCatChi;
	}

	public String getNgayRut() {
		return ngayRut;
	}

	public void setNgayRut(String ngayRut) {
		this.ngayRut = ngayRut;
	}

	public String getGioVV() {
		return gioVV;
	}

	public void setGioVV(String gioVV) {
		this.gioVV = gioVV;
	}

	public String getNgayVV() {
		return ngayVV;
	}

	public void setNgayVV(String ngayVV) {
		this.ngayVV = ngayVV;
	}

	//private int chuandoanMaso;
	//private String chuandoanMa = "";
	//private String chuandoanName = "";

	private String sobuong = "";
	private String sogiuong = "";

	public String getSobuong() {
		return sobuong;
	}

	public void setSobuong(String sobuong) {
		this.sobuong = sobuong;
	}

	public String getSogiuong() {
		return sogiuong;
	}

	public void setSogiuong(String sogiuong) {
		this.sogiuong = sogiuong;
	}
	
	public void themBacsi() {
		if (bacsiList == null) bacsiList = new ArrayList<DtDmNhanVien>();
		if(newNhanVien==null|newNhanVien.getDtdmnhanvienMaso()==null) return;
		DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
		DtDmNhanVien nv = (DtDmNhanVien)dieuTriUtilDelegate.findByMa(newNhanVien.getDtdmnhanvienMa(), "DtDmNhanVien", "dtdmnhanvienMa");
		//DtDmNhanVien nv = new DtDmNhanVien(newNhanVien.getDtdmnhanvienMaso(), newNhanVien.getDtdmnhanvienMa());
		
		if (nv != null){
			if (!this.bacsiList.contains(nv)){
				this.bacsiList.add(nv);
			}
		}
		//log.info("bacsiList.size="+this.bacsiList.size());
	}
	public void themBacsigm() {
		if (bacsigmList == null) bacsigmList = new ArrayList<DtDmNhanVien>();
		if(newNhanViengm==null|newNhanViengm.getDtdmnhanvienMaso()==null) return;
		DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
		DtDmNhanVien nvgm = (DtDmNhanVien)dieuTriUtilDelegate.findByMa(newNhanViengm.getDtdmnhanvienMa(), "DtDmNhanVien", "dtdmnhanvienMa");
		//DtDmNhanVien nv = new DtDmNhanVien(newNhanVien.getDtdmnhanvienMaso(), newNhanVien.getDtdmnhanvienMa());
		
		if (nvgm != null){
			if (!this.bacsigmList.contains(nvgm)){
				this.bacsigmList.add(nvgm);
			}
		}
		//log.info("bacsigmList.size="+this.bacsigmList.size());
	}
	
	public void deleteCurrentBacsiRow() {
		log.info("deleteCurrentBacsiRow");
		if (this.bacsiList == null || this.bacsiList.size() == 0) {
			return;
		}		
		log.info(this.bacsiSelected.getDtdmnhanvienMa());
		this.bacsiList.remove(this.bacsiSelected);
		bacsiSelected = null;
	}
	
	public void deleteCurrentBacsigmRow() {
		log.info("deleteCurrentBacsigmRow");
		if (this.bacsigmList == null || this.bacsigmList.size() == 0) {
			return;
		}		
		log.info(this.bacsigmSelected.getDtdmnhanvienMa());
		this.bacsigmList.remove(this.bacsigmSelected);
		bacsigmSelected = null;
	}
	
	public void deleteCurrentBacsiRow(DtDmNhanVien currentBacsi) {
		if (this.bacsiList == null || this.bacsiList.size() == 0) {
			return;
		}		
		this.bacsiList.remove(currentBacsi);
		bacsiSelected = null;
	}
	
	public void deleteCurrentBacsigmRow(DtDmNhanVien currentBacsigm) {
		if (this.bacsigmList == null || this.bacsigmList.size() == 0) {
			return;
		}		
		this.bacsigmList.remove(currentBacsigm);
		bacsigmSelected = null;
	}
	
	public DtDmNhanVien getNewNhanVien(boolean create) {
		if (create && newNhanVien == null) {
			newNhanVien = new DtDmNhanVien();
		}
		return newNhanVien;
	}
	public DtDmNhanVien getNewNhanVien() {
		return newNhanVien;
	}
	public void setNewNhanVien(DtDmNhanVien newNhanVien) {
		this.newNhanVien = newNhanVien;
	}
	public List<DtDmNhanVien> getBacsiList() {
		return bacsiList;
	}

	public DtDmNhanVien getNewNhanViengm(boolean create) {
		if (create && newNhanViengm == null) {
			newNhanViengm = new DtDmNhanVien();
		}
		return newNhanViengm;
	}
	public DtDmNhanVien getNewNhanViengm() {
		return newNhanViengm;
	}
	public void setNewNhanViengm(DtDmNhanVien newNhanViengm) {
		this.newNhanViengm = newNhanViengm;
	}
	public List<DtDmNhanVien> getBacsigmList() {
		return bacsigmList;
	}
	
	public void findGiuongPhong() {
		// if(ppttt.getHsbapptttMaso()!=null)
		// System.out.println("ppttt="+ppttt.getHsbapptttMaso());
		// if(ppttt.getHsbaSovaovien(true).getHsbaSovaovien()!=null)
		// System.out.println("ppttt.getHsbaSovaovien(true)="+ppttt.getHsbaSovaovien(true));
		// if(ppttt.getHsbaSovaovien(true).getHsbaKhoadangdt(true).getDmkhoaMaso()!=null)
		// System.out.println("ppttt.getHsbaSovaovien(true).getHsbaKhoadangdt(true)="+ppttt.getHsbaSovaovien(true).getHsbaKhoadangdt(true));
		try {
			if (ppttt.getHsbaSovaovien(true).getHsbaKhoadangdt(true).getDmkhoaMaso() != null) {
				HsbaChuyenMonDelegate hsbaChuyenMonDelegate = HsbaChuyenMonDelegate.getInstance();
				HsbaChuyenMon hsbaChuyenMon = hsbaChuyenMonDelegate.findBySoVaoVien_MaKhoa(ppttt.getHsbaSovaovien(true).getHsbaSovaovien(), ppttt.getHsbaSovaovien(true).getHsbaKhoadangdt(true).getDmkhoaMa());
				setSobuong(hsbaChuyenMon.getHsbacmSobuong());
				setSogiuong(hsbaChuyenMon.getHsbacmSogiuong());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}


	@End
	public void endFunc() {

	}

	public void resetValue() {
		log.info("---resetValue");
		ppttt = new HsbaPhieuPhauThuatThuThuat();
		this.bacsiList = new ArrayList<DtDmNhanVien>();
		this.newNhanVien = new DtDmNhanVien();
		
		this.bacsigmList = new ArrayList<DtDmNhanVien>();
		this.newNhanViengm = new DtDmNhanVien();
		
		crrDate = ngayCap = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		gioPTTT=ngayPTTT=gioVV=ngayVV=ngayRut=ngayCatChi="";
		nosuccess = nofound = nofoundHSBA = "false";
		isUpdate = false;
		Hsba hsba_tmp = new Hsba();
		setInfoIfNullForHsba(hsba_tmp);
		ppttt.setHsbaSovaovien(hsba_tmp);
		findGiuongPhong();
		nofoundHSBA = "true";
	}

	public void displayHSBA() {
		log.info("---displayHSBAquang");
		this.bacsiList = new ArrayList<DtDmNhanVien>();
		this.newNhanVien = new DtDmNhanVien();
		
		this.bacsigmList = new ArrayList<DtDmNhanVien>();
		this.newNhanViengm = new DtDmNhanVien();
		
		String maHsba = ppttt.getHsbaSovaovien().getHsbaSovaovien().trim();
		Hsba hsba_tmp = null;
		if (!maHsba.equals("")) {
			hsba_tmp = HsbaDelegate.getInstance().find(maHsba);
			if (hsba_tmp == null) {
				resetValue();
			} else {
				ppttt.setHsbaSovaovien(hsba_tmp);
				ppttt.setHsbapptttKhoachidinh(hsba_tmp.getHsbaKhoadangdt());
				setNgayVV(formatDate(hsba_tmp.getHsbaNgaygiovaov()));
				setGioVV(formatDateTime(hsba_tmp.getHsbaNgaygiovaov()));
				nofoundHSBA = "false";
			}
		} else {
			resetValue();
		}
		findGiuongPhong();

	}
	
	public void displayLoaiTTPT(){
		
		if (ppttt.getHsbapptttPhuongphap(true).getDtdmclsbgMa()!= null || ppttt.getHsbapptttPhuongphap(true).getDtdmclsbgMa()!= ""){
			DtDmClsBangGiaDelegate bangGiaDelegate = DtDmClsBangGiaDelegate.getInstance();
			DtDmClsBangGia bangGia = bangGiaDelegate.findByMa(ppttt.getHsbapptttPhuongphap(true).getDtdmclsbgMa());
			if (bangGia!= null){
				ppttt.setHsbapptttPhuongphap(bangGia);
				log.info(" vao  ma "+  ppttt.getHsbapptttPhuongphap(true).getDtdmclsbgMaso());
				ppttt.setHsbapptttLoaipttt(bangGia.getDtdmclsbgLoaipttt(true).getDtdmloaiptTen()); 
			}
		}else{
			ppttt.setHsbapptttLoaipttt("");
		}
	}

	private void setInfoIfNullForHsba(Hsba obj) {
		if (obj.getBenhnhanMa() == null) {
			BenhNhan _benhnhan = new BenhNhan();
			setInfoIfNullForBenhNhan(_benhnhan);
			obj.setBenhnhanMa(_benhnhan);
		}
	}

	private void setInfoIfNullForBenhNhan(BenhNhan obj) {
		if (obj.getDmgtMaso() == null)
			obj.setDmgtMaso(new DmGioiTinh());
		if (obj.getDantocMa() == null)
			obj.setDantocMa(new DmDanToc());
		if (obj.getTinhMa() == null)
			obj.setTinhMa(new DmTinh());
		if (obj.getHuyenMa() == null)
			obj.setHuyenMa(new DmHuyen());
		if (obj.getXaMa() == null)
			obj.setXaMa(new DmXa());
		if (obj.getBenhnhanNghe() == null)
			obj.setBenhnhanNghe(new DmNgheNghiep());
	}

	public void displayPhieuPhauThuatThuThuat() {
		log.info("---displayPhieuPhauThuatThuThuat");
		String maPpttt = ppttt.getHsbapptttMa().trim();
		HsbaPhieuPhauThuatThuThuat ppttt_tmp = null;
		if (!maPpttt.equals("")) {
			HsbaPhieuPhauThuatThuThuatDelegate hsbaPhieuPhauThuatThuThuatDelegate =  HsbaPhieuPhauThuatThuThuatDelegate.getInstance();
			ppttt_tmp =hsbaPhieuPhauThuatThuThuatDelegate.findByHsbapptttMa(maPpttt);
			if (ppttt_tmp == null) {
				nofound = "true";
				crrDate = ngayCap = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
				ppttt_tmp = new HsbaPhieuPhauThuatThuThuat();
				FacesMessages.instance().add(IConstantsRes.PPTTT_NULL, maPpttt);

			}

			ppttt = ppttt_tmp;

			setNgayCap(formatDate(ppttt.getHsbapptttNgaycap()));
			setNgayCatChi(formatDate(ppttt.getHsbapptttNgaycatchi()));
			setNgayPTTT(formatDate(ppttt.getHsbapptttNgaypttt()));
			setNgayRut(formatDate(ppttt.getHsbapptttNgayrut()));
			setGioPTTT(formatDateTime(ppttt.getHsbapptttNgaypttt()));

			setNgayVV(formatDate(ppttt.getHsbaSovaovien(true).getHsbaNgaygiovaov()));
			setGioVV(formatDateTime(ppttt.getHsbaSovaovien(true).getHsbaNgaygiovaov()));
			
			bacsiList = hsbaPhieuPhauThuatThuThuatDelegate.findBacsiByHsbapptttMa(ppttt.getHsbapptttMa());
			bacsigmList = hsbaPhieuPhauThuatThuThuatDelegate.findBacsigmByHsbapptttMa(ppttt.getHsbapptttMa());
			 
//			setChuandoanMa(ppttt.getHsbaSovaovien(true).getHsbaTuvong(true)
//					.getDmbenhicdMa());
//			if (ppttt.getHsbaSovaovien(true).getHsbaTuvong(true)
//					.getDmbenhicdMaso() != null) {
//				setChuandoanMaso(ppttt.getHsbaSovaovien(true).getHsbaTuvong(
//						true).getDmbenhicdMaso());
//			}
//
//			setChuandoanName(ppttt.getHsbaSovaovien(true).getHsbaTuvong(true)
//					.getDmbenhicdTen());

			isUpdate = true;
		} else {
			nofound = "true";
			crrDate = ngayCap = new SimpleDateFormat("dd/MM/yyyy")
					.format(new Date());
			ppttt = new HsbaPhieuPhauThuatThuThuat();
		}
		findGiuongPhong();
	}

	public String getdmBenhIcdMaCX() {
		return ppttt.getHsbaSovaovien(true).getHsbaTuvong(true)
				.getDmbenhicdMa();
	}

	public String getdmBenhIcdTenCX() {
		return ppttt.getHsbaSovaovien(true).getHsbaTuvong(true)
				.getDmbenhicdTen();
	}

	public void ghiNhan() {
//		log.info("---ghiNhan" + ppttt.getHsbapptttChuandoanmaSau());
//		log.info("---ghiNhan"+ ppttt.getHsbapptttChuandoanmaSau(true).getDmbenhicdMaso());
//		log.info("---ghiNhan"+ ppttt.getHsbapptttChuandoanmaSau(true).getDmbenhicdMa());

		String result = "";
		if (!ngayCap.trim().equals("")) {
			ppttt.setHsbapptttNgaygiocn(Utils.getDBDate("00:00", ngayCap).getTime());
		}
		// System.out.println("ppttt.getHsbapptttChuandoanmaTruoc(true).dmbenhicdMa = "+ppttt.getHsbapptttChuandoanmaTruoc(true).getDmbenhicdMa());
		// if (!ngayCX.trim().equals("")){
		// if(gioCX.trim().equals("")){
		// ppttt.setHsbapptttNgaycx(Utils.getDBDate("00:00", ngayCX).getTime());
		// }else
		// {
		// ppttt.setHsbapptttNgaycx(Utils.getDBDate(gioCX, ngayCX).getTime());
		// }
		// }
		if (!ngayVV.trim().equals("")) {
			if (gioVV.trim().equals("")) {
				ppttt.setHsbapptttNgayvaovien(Utils.getDBDate("00:00", ngayVV).getTime());
			} else {
				ppttt.setHsbapptttNgayvaovien(Utils.getDBDate(gioVV, ngayVV).getTime());
			}
		}
		
	
		if (gioPTTT.trim().equals("")) {
			ppttt.setHsbapptttNgaypttt(Utils.getDBDate("00:00", ngayPTTT).getTime());
		} else {
			ppttt.setHsbapptttNgaypttt(Utils.getDBDate(gioPTTT, ngayPTTT).getTime());
		}
	
		
		if(!ngayCap.trim().equals("")){
			ppttt.setHsbapptttNgaycap(Utils.getDBDate("00:00", ngayCap).getTime());
		}
		if(!ngayCatChi.trim().equals("")){
		ppttt.setHsbapptttNgaycatchi(Utils.getDBDate("00:00", ngayCatChi).getTime());
		}
		if(!ngayRut.trim().equals("")){
			ppttt.setHsbapptttNgayrut(Utils.getDBDate("00:00", ngayRut).getTime());
		}
//		System.out.println("ngayCap="+ppttt.getHsbapptttNgaycap().toString());
//		System.out.println("ngayCatChi="+ppttt.getHsbapptttNgaycatchi().toString());
//		System.out.println("ngayRut="+ppttt.getHsbapptttNgayrut().toString());
		ppttt.setHsbapptttNgaygiocn(new Date());
		ppttt.setNhanvienMa(DtDmNhanVienDelegate.getInstance().findByNd(identity.getUsername()));
//		DmBenhIcd dmbTmp = new DmBenhIcd();
		//System.out.println("chuan doan ma so = " + getChuandoanMaso());
		// RemoveUtil.removeAllNullFromHSBAPhieuPhauThuatThuThuat(ppttt);
		RemoveUtil.removeAllNullFromHSBAPhieuPhauThuatThuThuat(ppttt);

//		System.out.println("xem ma bac si = "
//				+ ppttt.getHsbapptttBacsi().getDtdmnhanvienMa());

//		try {
//			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate
//					.getInstance();
//			// System.out.println("chuandoanma = "+getChuandoanMa());
//			// dmbTmp = (DmBenhIcd)dtutilDelegate.findByMaSo(getChuandoanMaso(),
//			// "DmBenhIcd", "dmbenhicdMaso");
//			dmbTmp = (DmBenhIcd) dtutilDelegate.findByMa(getChuandoanMa(),
//					"DmBenhIcd", "dmbenhicdMa");
//			// System.out.println("Maso tim duoc = "+dmbTmp.getDmbenhicdMaso());
//			ppttt.setHsbapptttChuandoanma(dmbTmp);
//
//		} catch (Exception ex) {
//			System.out.println("Loi tai " + ex.toString());
//		}
		if (isUpdate) {
//			result = HsbaPhieuPhauThuatThuThuatDelegate.getInstance().update(ppttt);
//			result = HsbaPhieuPhauThuatThuThuatDelegate.getInstance().editHsbaPhieuPhauThuatThuThuat(ppttt,this.bacsiList);
			result = HsbaPhieuPhauThuatThuThuatDelegate.getInstance().editHsbaPhieuPhauThuatThuThuat(ppttt,this.bacsiList,this.bacsigmList);
			if (result == null || result.equals("")) {
				nosuccess = "true";
				FacesMessages.instance().add(IConstantsRes.PPTTT_CN_THATBAI);
			} else {
				ppttt.setHsbapptttMa(result);
				FacesMessages.instance().add(IConstantsRes.PPTTT_CN_THANHCONG,
						result);
			}
		} else {
//			result = HsbaPhieuPhauThuatThuThuatDelegate.getInstance().insert(ppttt);
//			result = HsbaPhieuPhauThuatThuThuatDelegate.getInstance().createHsbaPhieuPhauThuatThuThuat(ppttt,this.bacsiList);
			result = HsbaPhieuPhauThuatThuThuatDelegate.getInstance().createHsbaPhieuPhauThuatThuThuat(ppttt,this.bacsiList,this.bacsigmList);
			if (result == null || result.equals("")) {
				nosuccess = "true";
				FacesMessages.instance().add(IConstantsRes.PPTTT_LT_THATBAI);
			} else {
				ppttt.setHsbapptttMa(result);
				FacesMessages.instance().add(IConstantsRes.PPTTT_LT_THANHCONG,
						result);
			}
		}
	}
	public String luongbacsi;
	public String getLuongbacsi(){
		if((bacsiList==null)||(bacsiList.size()==0))
		{
			luongbacsi =null;
		}else
		{
			luongbacsi = bacsiList.size()+"";
		}
		return luongbacsi;
	}
	public void setLuongbacsi(String luongbacsi)
	{
		this.luongbacsi = luongbacsi;
	}
	
	public String luongbacsigm;
	public String getLuongbacsigm(){
		if((bacsigmList==null)||(bacsigmList.size()==0))
		{
			luongbacsigm =null;
		}else
		{
			luongbacsigm = bacsigmList.size()+"";
		}
		return luongbacsigm;
	}
	public void setLuongbacsigm(String luongbacsigm)
	{
		this.luongbacsigm = luongbacsigm;
	}
	
	public String thuchienAction() {
		XuatReport();
		return "B260_Chonmenuxuattaptin";
	}
	
	public void huyphieuAction() {
		log.info("huyphieuAction " + ppttt.getHsbapptttMa());
		HsbaPhieuPhauThuatThuThuatDelegate hsbaPhieuPhauThuatThuThuatDelegate = HsbaPhieuPhauThuatThuThuatDelegate.getInstance();
		try{
			hsbaPhieuPhauThuatThuThuatDelegate.remove(ppttt);
			FacesMessages.instance().add(IConstantsRes.HUY_PHIEU_THANH_CONG,ppttt.getHsbapptttMa());
			resetValue();
		}catch (Exception e) {
			FacesMessages.instance().add(IConstantsRes.HUY_PHIEU_THAT_BAI,ppttt.getHsbapptttMa());
			// TODO: handle exception
		}
	}
	

	/**
	 * xuat report
	 */
	
	public String getBacsiFromList(List<DtDmNhanVien> bacsi){
		if((bacsi==null) || (bacsi.size()==0)){
			return "";
		}
		String tmp = "";
		String prefix = "";
		for(int i=0; i<bacsi.size();i++)
		{	
			// 20120206 bao.ttc: check NULL
			if (bacsi.get(i).getDmhocviMaso() != null){
				tmp+=prefix+(bacsi.get(i).getDmhocviMaso(true).getDmhocviMa()+". "+ bacsi.get(i).getDtdmnhanvienTen());
			} else {
				tmp+=prefix + bacsi.get(i).getDtdmnhanvienTen();
			}
			
			if(i==0)
				prefix= ", ";
		}
		tmp+=".";
		
		return tmp;
		
	}
	public void XuatReport() {
		loaiBCDT = "PhieuPhauThuatThuThuat";
		log.info("Vao Method XuatReport phieu phau thuat thu thuat");
		HsbaPhieuPhauThuatThuThuatDelegate hsbaPhieuPhauThuatThuThuatDelegate =  HsbaPhieuPhauThuatThuThuatDelegate.getInstance();
		String baocao1 = null;
		String pathTemplate = null;
		try {
			pathTemplate = IConstantsRes.PATH_BASE
					+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
					+ "hsba/Phieuphauthuatthuthuat_main.jrxml";
			String sub1Template = IConstantsRes.PATH_BASE
			+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
			+ "hsba/Phieuphauthuatthuthuat_sub1.jrxml";
			String sub2Template = IConstantsRes.PATH_BASE
					+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
					+ "hsba/Phieuphauthuatthuthuat_sub2.jrxml";
			log.info("da thay file templete 5" + pathTemplate);
			log.info("da thay file sub 1 templete " + sub1Template);
			log.info("da thay file sub 2 templete " + sub2Template);
			
			JasperReport jasperReport = JasperCompileManager
					.compileReport(pathTemplate);
			JasperReport sub1Report = JasperCompileManager
				.compileReport(sub1Template);
			JasperReport sub2Report = JasperCompileManager
				.compileReport(sub2Template);
			
			
			
			log.info("da thay file template ");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put("sub1", sub1Report);
			params.put("sub2", sub2Report);
			
			params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			// log.info(String.format("-----tenSo: %s", params.get("tenSo")));
			params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			// log.info(String.format("-----tenDonVi: %s",
			// params.get("tenDonVi")));
			params.put("dienThoaiDonVi",
					IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			// log.info(String.format("-----dienThoaiDonVi: %s",
			// params.get("dienThoaiDonVi")));
			params.put("khoaRav", ppttt.getHsbaSovaovien(true).getHsbaKhoarav(
					true).getDmkhoaTen());
			// log.info(String.format("-----khoa ra vien: %s",
			// params.get("khoaRav")));
			params.put("GIAMDOC", IConstantsRes.REPORT_DIEUTRI_GIAMDOC_BV);

			params.put("magiay", ppttt.getHsbapptttMa());
			log.info(ppttt.getHsbapptttMa());

			// 20101111 bao.ttc: them cac thong tin khac
			if (ppttt.getHsbaSovaovien() != null) {

				Hsba hsba = ppttt.getHsbaSovaovien();
				String diachistr = "";
				if (hsba.getBenhnhanMa().getBenhnhanDiachi() != null)
					diachistr += hsba.getBenhnhanMa().getBenhnhanDiachi()
							+ ", ";
				if (hsba.getBenhnhanMa().getXaMa(true).getDmxaTen() != null)
					diachistr += hsba.getBenhnhanMa().getXaMa(true)
							.getDmxaTen()
							+ ", ";
				if (hsba.getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() != null)
					diachistr += hsba.getBenhnhanMa().getHuyenMa(true)
							.getDmhuyenTen()
							+ ", ";
				if (hsba.getBenhnhanMa().getTinhMa(true).getDmtinhTen() != null)
					diachistr += hsba.getBenhnhanMa().getTinhMa(true)
							.getDmtinhTen();
				params.put("DIACHI", diachistr);
				
				

				findGiuongPhong();
				params.put("BUONG", getSobuong());
				params.put("GIUONG", getSogiuong());
				if (hsba.getHsbaKhoadangdt().getDmkhoaTen() != null)
					params.put("KHOA_HIENTAI", hsba.getHsbaKhoadangdt()
							.getDmkhoaTen());
				else
					params.put("KHOA_HIENTAI", "");
				if (ppttt.getHsbapptttTrinhtu() != null) {
					params.put("TRINHTU_PTTT", ppttt.getHsbapptttTrinhtu());
				} else {
					params.put("TRINHTU_PTTT", "");
				}
				params.put("gioiTinh",  hsba.getBenhnhanMa().getDmgtMaso(true).getDmgtTen());
				log.info("gioitinh = "+hsba.getBenhnhanMa().getDmgtMaso(true).getDmgtTen());
				params.put("SOVAOVIEN",hsba.getHsbaSovaovien() );
				log.info("sovaovien = "+hsba.getHsbaSovaovien());
				params.put("BACSI_PHAUTHUAT",getBacsiFromList(hsbaPhieuPhauThuatThuThuatDelegate.findBacsiByHsbapptttMa(ppttt.getHsbapptttMa())));
				params.put("BACSI_GAYME",getBacsiFromList(hsbaPhieuPhauThuatThuThuatDelegate.findBacsigmByHsbapptttMa(ppttt.getHsbapptttMa())));

				HsbaBhytDelegate hsbaBHYTDelegate = HsbaBhytDelegate
						.getInstance();
				HsbaBhyt hsbaBHYT = hsbaBHYTDelegate
						.findBySoVaoVienLastHsbaBhyt(hsba.getHsbaSovaovien());
				if (hsbaBHYT != null) {
					if (hsbaBHYT.getHsbabhytGiatri0() != null)
						params.put("GIATRITU", sdf.format(hsbaBHYT
								.getHsbabhytGiatri0()));
					if (hsbaBHYT.getHsbabhytGiatri1() != null)
						params.put("GIATRIDEN", sdf.format(hsbaBHYT
								.getHsbabhytGiatri1()));
					// if(hsbaBHYT.getHsbabhytSothebh() != null)
					// params.put("SOTHEBH", hsbaBHYT.getHsbabhytSothebh());

					String so_bhyt = "";
					if (hsbaBHYT.getHsbabhytSothebh() != null
							&& !hsbaBHYT.getHsbabhytSothebh().equals("")) {
						so_bhyt = hsbaBHYT.getHsbabhytSothebh();
						params.put("khoiBhytMa", so_bhyt.substring(0, 2));
						params.put("khoiBhytMa1", so_bhyt.substring(2, 3));
						params.put("tinhBhytMa", so_bhyt.substring(3, 5));
						params.put("namBhyt", so_bhyt.substring(5, 7));
						params.put("maCoQuan", so_bhyt.substring(7, 10));
						if (hsbaBHYT.getHsbabhytMakcb(true).getDmbenhvienMa() != null)
							params.put("THEBHYT", so_bhyt.substring(10)
									+ " - "
									+ hsbaBHYT.getHsbabhytMakcb(true)
											.getDmbenhvienMa());
						else
							params.put("THEBHYT", so_bhyt.substring(10));
					}

				}

			}
			// 20101111 bao.ttc: them cac thong tin khac -- END

			log.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			log.info("da thay driver mysql");
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,
						IConstantsRes.DATABASE_USER,
						IConstantsRes.DATABASE_PASS);
			} catch (Exception e) {
				log.info(e.getMessage());
			}
			jasperPrintDT = JasperFillManager.fillReport(jasperReport, params,
					conn);
			baocao1 = XuatReportUtil.ReportUtil(jasperPrintDT, index,
					IConstantsRes.PATH_BASE
							+ IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI
							+ "hsba/", "pdf", "PhieuPhauThuatThuThuat");
			duongdanStrDT = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
			log.info("duong dan file xuat report :" + baocao1);
			log.info("duong dan -------------------- :" + duongdanStrDT);
			index += 1;
			log.info("Index :" + getIndex());
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
		log.info("Thoat Method XuatReport");
	}

	public String formatDate(Date date) {
		return date == null ? "" : new SimpleDateFormat("dd/MM/yyyy")
				.format(date);
	}

	public String formatDateTime(Date date) {
		System.out.println("qdate = "
				+ (date == null ? "isnull" : date.toString()));
		return date == null ? "" : Utils.getGioPhut(date);
	}

	public String formatGtBenhNhan(String gioitinh) {
		if (gioitinh == null || gioitinh.trim().equals("")) {
			return "";
		}
		return gioitinh.equals("1") ? "true" : "false";
	}

	public HsbaPhieuPhauThuatThuThuat getPpttt() {
		return ppttt;
	}

	public void setPpttt(HsbaPhieuPhauThuatThuThuat ppttt) {
		this.ppttt = ppttt;
	}

	public String getNgayCap() {
		return ngayCap;
	}

	public void setNgayCap(String ngayCap) {
		this.ngayCap = ngayCap;
	}

	public String getNgayPTTT() {
		return ngayPTTT;
	}

	public void setNgayPTTT(String ngayPTTT) {
		this.ngayPTTT = ngayPTTT;
	}

	public String getNosuccess() {
		return nosuccess;
	}

	public void setNosuccess(String nosuccess) {
		this.nosuccess = nosuccess;
	}

	public String getNofound() {
		return nofound;
	}

	public void setNofound(String nofound) {
		this.nofound = nofound;
	}

	public String getNofoundHSBA() {
		return nofoundHSBA;
	}

	public void setNofoundHSBA(String nofoundHSBA) {
		this.nofoundHSBA = nofoundHSBA;
	}

	public boolean isUpdate() {
		return isUpdate;
	}

	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
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

	public String getCrrDate() {
		return crrDate;
	}

	public void setCrrDate(String crrDate) {
		this.crrDate = crrDate;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}

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
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaBienBanHoiChanPhauThuatDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.HsbaBienBanHoiChanPhauThuat;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.entity.DmDanToc;
import com.iesvn.yte.entity.DmGioiTinh;
import com.iesvn.yte.entity.DmHuyen;
import com.iesvn.yte.entity.DmNgheNghiep;
import com.iesvn.yte.entity.DmTinh;
import com.iesvn.yte.entity.DmXa;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B294_Bienbanhoichanphauthuat")
@Synchronized(timeout = 6000000)
public class BienBanHoiChanPhauThuatAction implements Serializable {

	private static final long serialVersionUID = -5106695460338085910L;
	private static Logger log = Logger
			.getLogger(BienBanHoiChanPhauThuatAction.class);

	private DtDmNhanVien newNhanVien;
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	private HsbaBienBanHoiChanPhauThuat bbhcpt;
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

	
	////Bac si gay me ///////
	private DtDmNhanVien newNhanViengm;
	private List<DtDmNhanVien> bacsigmList;
	private DtDmNhanVien bacsigmSelected;

	public void themBacsigm() {
			if (bacsigmList == null) bacsigmList = new ArrayList<DtDmNhanVien>();
			if(newNhanViengm==null|newNhanViengm.getDtdmnhanvienMaso()==null) return;
			DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
			DtDmNhanVien nvgm = (DtDmNhanVien)dieuTriUtilDelegate.findByMa(newNhanViengm.getDtdmnhanvienMa(), "DtDmNhanVien", "dtdmnhanvienMa");
			//DtDmNhanVien nv = new DtDmNhanVien(newNhanVien.getDtdmnhanvienMaso(), newNhanVien.getDtdmnhanvienMa());
			
			log.info("newNhanViengm.getDtdmnhanvienTen()=" + newNhanViengm.getDtdmnhanvienTen());
			log.info("nvgm.getDtdmnhanvienTen()=" + nvgm.getDtdmnhanvienTen());
			log.info("bacsigmList.contains(nv)=" + bacsigmList.contains(nvgm));
			if (!this.bacsigmList.contains(nvgm)){
				this.bacsigmList.add(nvgm);
			}
			log.info("bacsigmList.size="+this.bacsigmList.size());
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
		
		
		public void deleteCurrentBacsigmRow(DtDmNhanVien currentBacsigm) {
			if (this.bacsigmList == null || this.bacsigmList.size() == 0) {
				return;
			}		
			this.bacsigmList.remove(currentBacsigm);
			bacsigmSelected = null;
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
		
	
	////////End bac si gay me ////////////////////////
		
	/////// Bac si ptv //////////////////////////////
		private DtDmNhanVien newNhanVienptv;
		private List<DtDmNhanVien> bacsiptvList;
		private DtDmNhanVien bacsiptvSelected;

		public void themBacsiptv() {
		if (bacsiptvList == null) bacsiptvList = new ArrayList<DtDmNhanVien>();
		if(newNhanVienptv==null|newNhanVienptv.getDtdmnhanvienMaso()==null) return;
		DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
		DtDmNhanVien nvptv = (DtDmNhanVien)dieuTriUtilDelegate.findByMa(newNhanVienptv.getDtdmnhanvienMa(), "DtDmNhanVien", "dtdmnhanvienMa");
		//DtDmNhanVien nv = new DtDmNhanVien(newNhanVien.getDtdmnhanvienMaso(), newNhanVien.getDtdmnhanvienMa());
		
		log.info("newNhanVienptv.getDtdmnhanvienTen()=" + newNhanVienptv.getDtdmnhanvienTen());
		log.info("nvptv.getDtdmnhanvienTen()=" + nvptv.getDtdmnhanvienTen());
		log.info("bacsiptvList.contains(nv)=" + bacsiptvList.contains(nvptv));
		if (!this.bacsiptvList.contains(nvptv)){
			this.bacsiptvList.add(nvptv);
		}
		log.info("bacsiptvList.size="+this.bacsiptvList.size());
	}
	
	public void deleteCurrentBacsiptvRow() {
		log.info("deleteCurrentBacsiptvRow");
		if (this.bacsiptvList == null || this.bacsiptvList.size() == 0) {
			return;
		}		
		log.info(this.bacsiptvSelected.getDtdmnhanvienMa());
		this.bacsiptvList.remove(this.bacsiptvSelected);
		bacsiptvSelected = null;
	}
	
	
	public void deleteCurrentBacsiptvRow(DtDmNhanVien currentBacsiptv) {
		if (this.bacsiptvList == null || this.bacsiptvList.size() == 0) {
			return;
		}		
		this.bacsiptvList.remove(currentBacsiptv);
		bacsiptvSelected = null;
	}
	public DtDmNhanVien getNewNhanVienptv(boolean create) {
		if (create && newNhanVienptv == null) {
			newNhanVienptv = new DtDmNhanVien();
		}
		return newNhanVienptv;
	}
	public DtDmNhanVien getNewNhanVienptv() {
		return newNhanVienptv;
	}
	public void setNewNhanVienptv(DtDmNhanVien newNhanVienptv) {
		this.newNhanVienptv = newNhanVienptv;
	}
	public List<DtDmNhanVien> getBacsiptvList() {
		return bacsiptvList;
	}
	public String luongbacsiptv;
	public String getLuongbacsiptv(){
		if((bacsiptvList==null)||(bacsiptvList.size()==0))
		{
			luongbacsiptv =null;
		}else
		{
			luongbacsiptv = bacsiptvList.size()+"";
		}
		return luongbacsiptv;
	}
	public void setLuongbacsiptv(String luongbacsiptv)
	{
		this.luongbacsiptv = luongbacsiptv;
	}
			
	/////// End Bac si ptv //////////////////////////////
	/////// Bac si tpk //////////////////////////////
	private DtDmNhanVien newNhanVientpk;
	private List<DtDmNhanVien> bacsitpkList;
	private DtDmNhanVien bacsitpkSelected;

	public void themBacsitpk() {
			if (bacsitpkList == null) bacsitpkList = new ArrayList<DtDmNhanVien>();
			if(newNhanVientpk==null|newNhanVientpk.getDtdmnhanvienMaso()==null) return;
			DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
			DtDmNhanVien nvtpk = (DtDmNhanVien)dieuTriUtilDelegate.findByMa(newNhanVientpk.getDtdmnhanvienMa(), "DtDmNhanVien", "dtdmnhanvienMa");
			//DtDmNhanVien nv = new DtDmNhanVien(newNhanVien.getDtdmnhanvienMaso(), newNhanVien.getDtdmnhanvienMa());
			if (nvtpk != null){
				if (!this.bacsitpkList.contains(nvtpk)){
					this.bacsitpkList.add(nvtpk);
				}
			}
		}
		
		public void deleteCurrentBacsitpkRow() {
			log.info("deleteCurrentBacsitpkRow");
			if (this.bacsitpkList == null || this.bacsitpkList.size() == 0) {
				return;
			}		
			log.info(this.bacsitpkSelected.getDtdmnhanvienMa());
			this.bacsitpkList.remove(this.bacsitpkSelected);
			bacsitpkSelected = null;
		}
		
		
		public void deleteCurrentBacsitpkRow(DtDmNhanVien currentBacsitpk) {
			if (this.bacsitpkList == null || this.bacsitpkList.size() == 0) {
				return;
			}		
			this.bacsitpkList.remove(currentBacsitpk);
			bacsitpkSelected = null;
		}
		public DtDmNhanVien getNewNhanVientpk(boolean create) {
			if (create && newNhanVientpk == null) {
				newNhanVientpk = new DtDmNhanVien();
			}
			return newNhanVientpk;
		}
		public DtDmNhanVien getNewNhanVientpk() {
			return newNhanVientpk;
		}
		public void setNewNhanVientpk(DtDmNhanVien newNhanVientpk) {
			this.newNhanVientpk = newNhanVientpk;
		}
		public List<DtDmNhanVien> getBacsitpkList() {
			return bacsitpkList;
		}
		public String luongbacsitpk;
		public String getLuongbacsitpk(){
			if((bacsitpkList==null)||(bacsitpkList.size()==0))
			{
				luongbacsitpk =null;
			}else
			{
				luongbacsitpk = bacsitpkList.size()+"";
			}
			return luongbacsitpk;
		}
		public void setLuongbacsitpk(String luongbacsitpk)
		{
			this.luongbacsitpk = luongbacsitpk;
		}
		
		
	/////// End Bac si tpk //////////////////////////////
	
	
	
	@Restrict("#{s:hasRole('NV_KhoaNoiTru') or s:hasRole('QT_KhoaNoiTru')}")
	@Begin(join = true)
	public String init() {

		resetValue();
		System.out.println("end init ");

		return "DieuTri_LapVanBanTheoMau_BienBanHoiChanPhauThuat";
	}
	

	private String gioPTTT;

	public String getGioPTTT() {
		return gioPTTT;
	}

	public void setGioPTTT(String gioPTTT) {
		this.gioPTTT = gioPTTT;
	}

	private String gioTGHC;
	private String ngayTGHC;

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

	public String getGioTGHC() {
		return gioTGHC;
	}

	public void setGioTGHC(String gioTGHC) {
		this.gioTGHC = gioTGHC;
	}

	public String getNgayTGHC() {
		return ngayTGHC;
	}

	public void setNgayTGHC(String ngayTGHC) {
		this.ngayTGHC = ngayTGHC;
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
	
	public void findGiuongPhong() {
		// if(bbhcpt.getHsbabbhcptMaso()!=null)
		// System.out.println("bbhcpt="+bbhcpt.getHsbabbhcptMaso());
		// if(bbhcpt.getHsbaSovaovien(true).getHsbaSovaovien()!=null)
		// System.out.println("bbhcpt.getHsbaSovaovien(true)="+bbhcpt.getHsbaSovaovien(true));
		// if(bbhcpt.getHsbaSovaovien(true).getHsbaKhoadangdt(true).getDmkhoaMaso()!=null)
		// System.out.println("bbhcpt.getHsbaSovaovien(true).getHsbaKhoadangdt(true)="+bbhcpt.getHsbaSovaovien(true).getHsbaKhoadangdt(true));
		try {
			if (bbhcpt.getHsbaSovaovien(true).getHsbaKhoadangdt(true)
					.getDmkhoaMaso() != null) {
				HsbaChuyenMonDelegate hsbaChuyenMonDelegate = HsbaChuyenMonDelegate
						.getInstance();
				HsbaChuyenMon hsbaChuyenMon = hsbaChuyenMonDelegate
						.findBySoVaoVien_MaKhoa(bbhcpt.getHsbaSovaovien(true)
								.getHsbaSovaovien(), bbhcpt.getHsbaSovaovien(
								true).getHsbaKhoadangdt(true).getDmkhoaMa());
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
		bbhcpt = new HsbaBienBanHoiChanPhauThuat();
		this.bacsiList = new ArrayList<DtDmNhanVien>();
		this.newNhanVien = new DtDmNhanVien();
		
		this.bacsigmList = new ArrayList<DtDmNhanVien>();
		this.newNhanViengm = new DtDmNhanVien();
		this.bacsiptvList = new ArrayList<DtDmNhanVien>();
		this.newNhanVienptv = new DtDmNhanVien();
		this.bacsitpkList = new ArrayList<DtDmNhanVien>();
		this.newNhanVientpk = new DtDmNhanVien();
		
		crrDate = ngayCap = new SimpleDateFormat("dd/MM/yyyy")
				.format(new Date());
		gioPTTT=ngayPTTT=gioTGHC=ngayTGHC=ngayRut=ngayCatChi="";
		nosuccess = nofound = nofoundHSBA = "false";
		isUpdate = false;
		
		findGiuongPhong();
	}

	public void displayHSBA() {
		log.info("---displayHSBAquang");
		this.bacsiList = new ArrayList<DtDmNhanVien>();
		this.newNhanVien = new DtDmNhanVien();
		
		this.bacsigmList = new ArrayList<DtDmNhanVien>();
		this.newNhanViengm = new DtDmNhanVien();
		
		String maHsba = bbhcpt.getHsbaSovaovien().getHsbaSovaovien().trim();
		Hsba hsba_tmp = null;
		if (!maHsba.equals("")) {
			hsba_tmp = HsbaDelegate.getInstance().find(maHsba);
			if (hsba_tmp == null) {
				nofoundHSBA = "true";
				hsba_tmp = new Hsba();
				FacesMessages.instance().add(IConstantsRes.HSBA_NULL, maHsba);
				hsba_tmp = new Hsba();
				setInfoIfNullForHsba(hsba_tmp);
				bbhcpt.setHsbaSovaovien(hsba_tmp);
//				setNgayTGHC("");
//				setGioTGHC("");
			} else {
				bbhcpt.setHsbaSovaovien(hsba_tmp);
				bbhcpt.setHsbabbhcptLdvv(hsba_tmp.getHsbaLydovao());

//				setNgayTGHC(formatDate(hsba_tmp.getHsbaNgaygiovaov()));
//				setGioTGHC(formatDateTime(hsba_tmp.getHsbaNgaygiovaov()));
			}
		} else {
			nofoundHSBA = "true";
			hsba_tmp = new Hsba();
			setInfoIfNullForHsba(hsba_tmp);
			bbhcpt.setHsbaSovaovien(hsba_tmp);
//			setNgayTGHC("");
//			setGioTGHC("");
		}
		findGiuongPhong();

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

	public void displayBienBanHoiChanPhauThuat() {
		log.info("---displayBienBanHoiChanPhauThuat");
		String maBbhcpt = bbhcpt.getHsbabbhcptMa().trim();
		HsbaBienBanHoiChanPhauThuat bbhcpt_tmp = null;
		if (!maBbhcpt.equals("")) {
			HsbaBienBanHoiChanPhauThuatDelegate hsbaBienBanHoiChanPhauThuatDelegate =  HsbaBienBanHoiChanPhauThuatDelegate.getInstance();
			bbhcpt_tmp =hsbaBienBanHoiChanPhauThuatDelegate.findByHsbabbhcptMa(maBbhcpt);
			if (bbhcpt_tmp == null) {
				nofound = "true";
				crrDate = ngayCap = new SimpleDateFormat("dd/MM/yyyy")
						.format(new Date());
				bbhcpt_tmp = new HsbaBienBanHoiChanPhauThuat();
				FacesMessages.instance().add(IConstantsRes.BBHCPT_NULL, maBbhcpt);

			}

			bbhcpt = bbhcpt_tmp;

			setNgayCap(formatDate(bbhcpt.getHsbabbhcptNgaycap()));
			
			setNgayPTTT(formatDate(bbhcpt.getHsbabbhcptNgaypttt()));
			setGioPTTT(formatDateTime(bbhcpt.getHsbabbhcptNgaypttt()));

			setNgayTGHC(formatDate(bbhcpt.getHsbabbhcptNgaytghc()));
			setGioTGHC(formatDateTime(bbhcpt.getHsbabbhcptNgaytghc()));
			
//			bacsiList = hsbaBienBanHoiChanPhauThuatDelegate.findBacsiByHsbabbhcptMa(bbhcpt.getHsbabbhcptMa());
			bacsigmList = hsbaBienBanHoiChanPhauThuatDelegate.findBacsigmByHsbabbhcptMa(bbhcpt.getHsbabbhcptMa());
			bacsiptvList = hsbaBienBanHoiChanPhauThuatDelegate.findBacsiptvByHsbabbhcptMa(bbhcpt.getHsbabbhcptMa());
			bacsitpkList = hsbaBienBanHoiChanPhauThuatDelegate.findBacsitpkByHsbabbhcptMa(bbhcpt.getHsbabbhcptMa());
			 
//			setChuandoanMa(bbhcpt.getHsbaSovaovien(true).getHsbaTuvong(true)
//					.getDmbenhicdMa());
//			if (bbhcpt.getHsbaSovaovien(true).getHsbaTuvong(true)
//					.getDmbenhicdMaso() != null) {
//				setChuandoanMaso(bbhcpt.getHsbaSovaovien(true).getHsbaTuvong(
//						true).getDmbenhicdMaso());
//			}
//
//			setChuandoanName(bbhcpt.getHsbaSovaovien(true).getHsbaTuvong(true)
//					.getDmbenhicdTen());

			isUpdate = true;
		} else {
			nofound = "true";
			crrDate = ngayCap = new SimpleDateFormat("dd/MM/yyyy")
					.format(new Date());
			bbhcpt = new HsbaBienBanHoiChanPhauThuat();
		}
		findGiuongPhong();
	}

	public void ghiNhan() {
//		log.info("---ghiNhan" + bbhcpt.getHsbabbhcptChuandoanmaSau());
//		log.info("---ghiNhan"+ bbhcpt.getHsbabbhcptChuandoanmaSau(true).getDmbenhicdMaso());
//		log.info("---ghiNhan"+ bbhcpt.getHsbabbhcptChuandoanmaSau(true).getDmbenhicdMa());

		String result = "";
		if (!ngayCap.trim().equals("")) {
			bbhcpt.setHsbabbhcptNgaygiocn(Utils.getDBDate("00:00", ngayCap)
					.getTime());
		}
		// System.out.println("bbhcpt.getHsbabbhcptChuandoanmaTruoc(true).dmbenhicdMa = "+bbhcpt.getHsbabbhcptChuandoanmaTruoc(true).getDmbenhicdMa());
		// if (!ngayCX.trim().equals("")){
		// if(gioCX.trim().equals("")){
		// bbhcpt.setHsbabbhcptNgaycx(Utils.getDBDate("00:00", ngayCX).getTime());
		// }else
		// {
		// bbhcpt.setHsbabbhcptNgaycx(Utils.getDBDate(gioCX, ngayCX).getTime());
		// }
		// }
//		System.out.println("gioTGHC="+gioTGHC);
//		System.out.println("ngayTGHC="+ngayTGHC);
		
		bbhcpt.setHsbabbhcptNgaytghc(Utils.getDBDate(gioTGHC, ngayTGHC).getTime());
		if(!ngayPTTT.trim().equals("")){
			if(!gioPTTT.trim().equals("")){
				bbhcpt.setHsbabbhcptNgaypttt(Utils.getDBDate(gioPTTT, ngayPTTT).getTime());
			}else if(gioPTTT.trim().equals("00:00")){
				bbhcpt.setHsbabbhcptNgaypttt(Utils.getDBDate("00:01", ngayPTTT).getTime());
			}
			else
			{
				bbhcpt.setHsbabbhcptNgaypttt(Utils.getDBDate("00:00", ngayPTTT).getTime());
			}
		}
		
		if(!ngayCap.trim().equals("")){
			bbhcpt.setHsbabbhcptNgaycap(Utils.getDBDate("00:00", ngayCap).getTime());
		}
		
//		System.out.println("ngayCap="+bbhcpt.getHsbabbhcptNgaycap().toString());
//		System.out.println("ngayCatChi="+bbhcpt.getHsbabbhcptNgaycatchi().toString());
//		System.out.println("ngayRut="+bbhcpt.getHsbabbhcptNgayrut().toString());
		bbhcpt.setHsbabbhcptNgaygiocn(new Date());
		bbhcpt.setNhanvienMa(DtDmNhanVienDelegate.getInstance().findByNd(
				identity.getUsername()));
//		DmBenhIcd dmbTmp = new DmBenhIcd();
		//System.out.println("chuan doan ma so = " + getChuandoanMaso());
		// RemoveUtil.removeAllNullFromHSBABienBanHoiChanPhauThuat(bbhcpt);
		RemoveUtil.removeAllNullFromHSBABienBanHoiChanPhauThuat(bbhcpt);

//		System.out.println("xem ma bac si = "
//				+ bbhcpt.getHsbabbhcptBacsi().getDtdmnhanvienMa());

//		try {
//			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate
//					.getInstance();
//			// System.out.println("chuandoanma = "+getChuandoanMa());
//			// dmbTmp = (DmBenhIcd)dtutilDelegate.findByMaSo(getChuandoanMaso(),
//			// "DmBenhIcd", "dmbenhicdMaso");
//			dmbTmp = (DmBenhIcd) dtutilDelegate.findByMa(getChuandoanMa(),
//					"DmBenhIcd", "dmbenhicdMa");
//			// System.out.println("Maso tim duoc = "+dmbTmp.getDmbenhicdMaso());
//			bbhcpt.setHsbabbhcptChuandoanma(dmbTmp);
//
//		} catch (Exception ex) {
//			System.out.println("Loi tai " + ex.toString());
//		}
		if (isUpdate) {
//			result = HsbaBienBanHoiChanPhauThuatDelegate.getInstance().update(bbhcpt);
//			result = HsbaBienBanHoiChanPhauThuatDelegate.getInstance().editHsbaBienBanHoiChanPhauThuat(bbhcpt,this.bacsiList);
			result = HsbaBienBanHoiChanPhauThuatDelegate.getInstance().editHsbaBienBanHoiChanPhauThuat(bbhcpt,this.bacsiptvList,this.bacsigmList,this.bacsitpkList);
			if (result == null || result.equals("")) {
				nosuccess = "true";
				FacesMessages.instance().add(IConstantsRes.BBHCPT_CN_THATBAI);
			} else {
				bbhcpt.setHsbabbhcptMa(result);
				FacesMessages.instance().add(IConstantsRes.BBHCPT_CN_THANHCONG,
						result);
			}
		} else {
//			result = HsbaBienBanHoiChanPhauThuatDelegate.getInstance().insert(bbhcpt);
//			result = HsbaBienBanHoiChanPhauThuatDelegate.getInstance().createHsbaBienBanHoiChanPhauThuat(bbhcpt,this.bacsiList);
			result = HsbaBienBanHoiChanPhauThuatDelegate.getInstance().createHsbaBienBanHoiChanPhauThuat(bbhcpt,this.bacsiptvList,this.bacsigmList,this.bacsitpkList);
			if (result == null || result.equals("")) {
				nosuccess = "true";
				FacesMessages.instance().add(IConstantsRes.BBHCPT_LT_THATBAI);
			} else {
				bbhcpt.setHsbabbhcptMa(result);
				FacesMessages.instance().add(IConstantsRes.BBHCPT_LT_THANHCONG,
						result);
			}
		}
	}
	
	
	
	public String thuchienAction() {
		XuatReport();
		return "B260_Chonmenuxuattaptin";
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
			tmp+=prefix+bacsi.get(i).getDtdmnhanvienTen();
			if(i==0)
				prefix= ", ";
		}
		tmp+=".";
		
		
		return tmp;
		
	}
	public void XuatReport() {
		loaiBCDT = "bienbanhoichanphauthuat";
		HsbaBienBanHoiChanPhauThuatDelegate hsbaBienBanHoiChanPhauThuatDelegate =  HsbaBienBanHoiChanPhauThuatDelegate.getInstance();
		String baocao1 = null;
		String pathTemplate = null;
		try {
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/Bienbanhoichanphauthuat.jrxml";
			log.info("da thay file templete 5" + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Map<String, Object> params = new HashMap<String, Object>();
			
		
			
			params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			params.put("GIAMDOC", IConstantsRes.REPORT_DIEUTRI_GIAMDOC_BV);
			findGiuongPhong();
			params.put("BUONG", getSobuong()==null?" ":getSobuong()+" ");

			params.put("magiay", bbhcpt.getHsbabbhcptMa());
			log.info(bbhcpt.getHsbabbhcptMa());
			
			if(bbhcpt.getHsbabbhcptHinhthuc()==1){
				params.put("CAPCUU", "X");
			}else if(bbhcpt.getHsbabbhcptHinhthuc()==2)
			{
				params.put("BANKHAN", "X");
			}else if(bbhcpt.getHsbabbhcptHinhthuc()==3)
			{
				params.put("CHUONGTRINH", "X");
			}

			if (bbhcpt.getHsbaSovaovien() != null) {

				Hsba hsba = bbhcpt.getHsbaSovaovien();
				
				// 20120220 bao.ttc: tinh Tuoi BN den thoi diem ngay Hoi chan
				int iTuoi = hsba.getBenhnhanMa(true).getBenhnhanTuoi();
				int iDonviTuoi = hsba.getBenhnhanMa(true).getBenhnhanDonvituoi();
				String sDonViTuoi = "";
				
				Date datenow = new Date();
				if (bbhcpt.getHsbabbhcptNgaycap() != null){
					datenow = bbhcpt.getHsbabbhcptNgaycap();
				}
				
				if (iDonviTuoi == 1){
					sDonViTuoi = "";
					
				} else if (iDonviTuoi == 2){
					sDonViTuoi = IConstantsRes.THANG;// "Tháng";
					if (hsba.getBenhnhanMa(true).getBenhnhanNgaysinh() != null){
						Long monthDiff = (datenow.getTime() - hsba.getBenhnhanMa(true).getBenhnhanNgaysinh().getTime()) / (30*24*60*60*1000L);
						iTuoi = monthDiff.intValue() + 1;
					} else {
						try {
							int namsinh = Integer.parseInt(hsba.getBenhnhanMa(true).getBenhnhanNamsinh());
							iTuoi = ( ( datenow.getYear() == namsinh ? datenow.getYear() + 1 : datenow.getYear() ) - namsinh ) * 12;
						} catch (Exception e) {
							log.info("### Loi chuyen Nam sinh --> int ");
						}
					}
					
				} else if (iDonviTuoi == 3){
					sDonViTuoi = IConstantsRes.NGAY; // "Ngày";
					if (hsba.getBenhnhanMa(true).getBenhnhanNgaysinh() != null){
						Long datediff = (datenow.getTime() - hsba.getBenhnhanMa(true).getBenhnhanNgaysinh().getTime()) / (24*60*60*1000L);
						iTuoi = datediff.intValue() + 1;
					}
				}
				
				if (iTuoi <= 0){
					iTuoi = 1;
				}
				// END -- 20120220 bao.ttc: tinh Tuoi BN den thoi diem ngay Hoi chan
				params.put("TUOI", iTuoi + " " + sDonViTuoi);
				
				
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
				if (hsba.getBenhnhanMa().getDmgtMaso(true).getDmgtTen() != null)
					params.put("gioiTinh", hsba.getBenhnhanMa().getDmgtMaso(
							true).getDmgtTen());
				else
					params.put("gioiTinh", "");
				params.put("SOVAOVIEN", hsba.getHsbaSovaovien());
				
				params.put("PHAUTHUATVIEN_PHUMO",getBacsiFromList(hsbaBienBanHoiChanPhauThuatDelegate.findBacsiptvByHsbabbhcptMa(bbhcpt.getHsbabbhcptMa())));
				params.put("NGUOI_GAYME",getBacsiFromList(hsbaBienBanHoiChanPhauThuatDelegate.findBacsigmByHsbabbhcptMa(bbhcpt.getHsbabbhcptMa())));
				params.put("THANHPHAN_KHAC",getBacsiFromList(hsbaBienBanHoiChanPhauThuatDelegate.findBacsitpkByHsbabbhcptMa(bbhcpt.getHsbabbhcptMa())));
				

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
							+ "hsba/", "pdf", "BienBanHoiChanPhauThuat");
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

	public HsbaBienBanHoiChanPhauThuat getBbhcpt() {
		return bbhcpt;
	}

	public void setBbhcpt(HsbaBienBanHoiChanPhauThuat bbhcpt) {
		this.bbhcpt = bbhcpt;
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


package com.iesvn.yte.dieutri.duocpham.action;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.jasperreports.engine.JasperPrint;

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

import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
import com.iesvn.yte.dieutri.delegate.CtXuatBhXuatVienDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
import com.iesvn.yte.dieutri.delegate.PhieuXuatBhXuatVienDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocNoiTruDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
import com.iesvn.yte.dieutri.delegate.YteLogDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.HsThtoan;
import com.iesvn.yte.dieutri.entity.PhieuXuatBhXuatVien;
import com.iesvn.yte.dieutri.entity.CtXuatBhXuatVien;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.dieutri.entity.YteLog;
import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
import com.iesvn.yte.entity.DmBenhIcd;
import com.iesvn.yte.entity.DmBenhVien;
import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmThuoc;
import com.iesvn.yte.entity.DmTinh;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;
import org.jboss.seam.security.Identity;

@Scope(ScopeType.CONVERSATION)
@Name("B4166_Xuathangchobenhnhan")
@Synchronized(timeout = 6000000)
public class XuatHangChoBenhNhanXuatVienAction implements Serializable {
	
	private static final long serialVersionUID = 10L;
	private static Logger log = Logger.getLogger(XuatHangChoBenhNhanXuatVienAction.class);	
	
	private PhieuXuatBhXuatVien phieuXuatBhXuatVien;
	private BenhNhan benhnhan;
	private Hsba hsba;
	private HsbaBhyt hsbaBhyt;
	
	@DataModel
	private List<CtXuatBhXuatVien> listCtXuatBhXuatVien;
	@DataModelSelection(value = "listCtXuatBhXuatVien")
	private CtXuatBhXuatVien ctxuatbhSelected;
	private CtXuatBhXuatVien ctxuatbh;
		
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
	@Out(required=false)
	@In(required=false)
	private String reportPathKC=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeKC=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintKC = null;
	
	@In(required = false)
	@Out(required = false)
	private String tenChuongTrinh;
	
	private int index=0;
	
	private String nvPhat;
	private Integer nvPhatSo;
	private String hsbaDoituong;
	private String hsbaDoituongMa;
	private String hsbaKcbBhyt;
	private String hsbaChandoan;
	private String nvCapnhat;
	private String pxNgaylap;
	
	private String bnNamsinh;
	private String bnGtinh;	
	private String hsbaTinhBhyt;
	private String hsbaKhoiBhyt;	
	
	private String tntMathuoc;
	private String tntQuocgiaSx;
	private String tntHangSx;
	private String ctxThanhtien;
	private String tkDongiaban;
	
	private TonKho tonKho;
	private String tonkhoMa;
	private String ctxSoluong;
	
	private String thieuthuoc = "0";
	private String updateCtXuat;
	private double thanhtienCt = 0;
	private String saveResult;
	
	private String nosuccess;
	private String nofoundHsba;
	
	private HsThtoan hstt;
	
	private List<ThuocNoiTru> listTNT;
	private String reportFinished;
	private String reportFileName;
	private DmKhoa dmKhoXuat = new DmKhoa();
	
	 private YteLogDelegate yteLogDele = YteLogDelegate.getInstance();
     private YteLog yteLog;
     private String listDataLog;
	
	@Begin(join = true)
	public String init(String loaiKho){
		//log.info("---------begin init method------- B4166_Xuathangchobenhnhan: " + loaiKho);
		reset();
		hstt = new HsThtoan();
		DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
		
		if ("NT".equals(loaiKho)){ // khu vuc kho noi tru			
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoNoiTru;
			dmKhoXuat  = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_NT_MA, "DmKhoa", "dmkhoaMa");
		}else if ("KC".equals(loaiKho)){ // khu vuc kho Chinh			
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoChinh;
			dmKhoXuat  = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_KC_MA, "DmKhoa", "dmkhoaMa");
		}else if ("BHYT".equals(loaiKho)){ // khu vuc kho BHYT		
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoBHYT;
			dmKhoXuat  = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_BHYT_MA, "DmKhoa", "dmkhoaMa");
		}else if ("TE".equals(loaiKho)){ // khu vuc kho Tre em			
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoTE;
			dmKhoXuat  = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_TE_MA, "DmKhoa", "dmkhoaMa");
		}
		//log.info("---------end init method------- B4166_Xuathangchobenhnhan");
		return "QuanLyKhoChinh_XuatKhoBHYT_XuatHangChoBenhNhan";
	}
	@End
	public void endFucntion(){
		
	}
	
	private void reset(){
		phieuXuatBhXuatVien = new PhieuXuatBhXuatVien();
		ctxuatbh = new CtXuatBhXuatVien();
		hsba = new Hsba();
		benhnhan = new BenhNhan();
		SetInforUtil.setInforIfNullForBN(benhnhan);
		listCtXuatBhXuatVien = new ArrayList<CtXuatBhXuatVien>();		
		pxNgaylap = Utils.getCurrentDate();
		updateCtXuat = "0";
		nosuccess = "false";
		nofoundHsba = "false";
	}
	
	public void fillCtXuatBh(){
		//log.info("---Begin fillCtXuatBh() method----");
		String message = "";
		ctxuatbh = ctxuatbhSelected;
		tonKho = new TonKho();
		
		if(tonKho != null){
			tonkhoMa = ""+tonKho.getTonkhoMa();
			DmThuoc thuoc = tonKho.getDmthuocMaso();
			tntMathuoc = thuoc.getDmthuocMa();
			tntQuocgiaSx = tonKho.getDmquocgiaMaso().getDmquocgiaTen();
			tntHangSx = tonKho.getDmnhasanxuatMaso().getDmnhasanxuatTen();
			double dongiaBan = tonKho.getTonkhoDongiaban();
			tkDongiaban = "" + dongiaBan;
			ctxThanhtien = ""+ (ctxuatbh.getCtxuatbhxvSoluong() * dongiaBan);
			updateCtXuat = ""+1;
			
		}
		FacesMessages.instance().add(message);
		//log.info("---End fillCtXuatBh() method----");
	}
	
	public void loadHsbaAjax(){
		//log.info("---Begin loadHsbaAjax() ----");
		listCtXuatBhXuatVien = new ArrayList<CtXuatBhXuatVien>();
		phieuXuatBhXuatVien = new PhieuXuatBhXuatVien();
		ctxuatbh = new CtXuatBhXuatVien();
		benhnhan = new BenhNhan();
		SetInforUtil.setInforIfNullForBN(benhnhan);
		
		if(hsba == null || hsba.getHsbaSovaovien() == null){
			FacesMessages.instance().add(IConstantsRes.SO_VAO_VIEN_UNAVAILABLE, "");
			nofoundHsba = "true";
			//log.info("------ hsba == null || hsba.getHsbaSovaovien() == null ------");
			resetForm();
			return;
		}		
		
		try{			
			HsbaDelegate hsbaWsPort = HsbaDelegate.getInstance();
			String soVaovien = hsba.getHsbaSovaovien();
			if(soVaovien != null && !soVaovien.trim().equals("")){
				hsba = hsbaWsPort.find(soVaovien);				
				if(hsba == null){					
					FacesMessages.instance().add(IConstantsRes.SO_VAO_VIEN_NOTFOUND, soVaovien);
					this.resetHsba();
					nofoundHsba = "true";
					//log.info("------Khong tim thay so vao vien---------");
				}
				else if(hsba!=null ){
					
					HsbaBhytDelegate hsbaBhytDel = HsbaBhytDelegate.getInstance();
					hsbaBhyt = hsbaBhytDel.findBySoVaoVienLastHsbaBhyt(hsba.getHsbaSovaovien());
					//load thong tin benh nhan, hsba, hsbaBhyt len form
					this.setFormHsba(hsba, hsbaBhyt);
					//log.info("HsbaBenhnhan: " + hsba.getBenhnhanMa().getBenhnhanMa());
					List<PhieuXuatBhXuatVien> phieuXuatBhTemp = (List<PhieuXuatBhXuatVien>) PhieuXuatBhXuatVienDelegate.getInstance().findBySovaovien_Kho(hsba.getHsbaSovaovien(), dmKhoXuat.getDmkhoaMaso());
					if(phieuXuatBhTemp.size() > 0){
						phieuXuatBhXuatVien.setPhieuxuatbhxvMa(phieuXuatBhTemp.get(0).getPhieuxuatbhxvMa());
						loadPhieuXuatBhAjax();
						return;
					}
					if (hsba.getDoituongMa().getDmdoituongMa().equals("TP")) {
						FacesMessages.instance().add(IConstantsRes.DOITUONG_THUPHI);
						resetForm();
						return;
					}
					
					// HsbaKhoaDelegate hsbaKhoaDel = HsbaKhoaDelegate.getInstance();
					// 20120308 bao.ttc: NOTES Tim thuoc noi tru Xuat vien theo HSBA Khoa (can sua lai cho dung khoa, buong dang DT)
					// HsbaKhoa hsbaKhoa = hsbaKhoaDel.findBySoVaoVienLastHsbaKhoa(soVaovien);
					// HsbaKhoa hsbaKhoa = hsbaKhoaDel.findBySoVaoVienAndKhoaMaAndTang(hsba.getHsbaSovaovien(), hsba.getHsbaKhoadangdt(true).getDmkhoaMa(), hsba.getTangDangdt(true).getDmtangMaso());
					//listTNT = ThuocNoiTruDelegate.getInstance().findByHsbaKhoa(hsbaKhoa.getHsbakhoaMaso());
					
					listTNT = ThuocNoiTruDelegate.getInstance().findTNTXuatVienBySoVaoVien(hsba.getHsbaSovaovien());
					
					CtXuatBhXuatVien ctxuatbh_ = null;
					for (ThuocNoiTru obj : listTNT){
						obj.setThuocnoitruMaphieu(phieuXuatBhXuatVien.getPhieuxuatbhxvMa());
						ctxuatbh_ = new CtXuatBhXuatVien();
						ctxuatbh_.setPhieuxuatbhxvMa(phieuXuatBhXuatVien);
						ctxuatbh_.setCtxuatbhxvSoluong(Double.valueOf(String.valueOf(obj.getThuocnoitruSoluong())));
						ctxuatbh_.setCtxuatbhxvMalk(obj.getThuocnoitruMalk());
						ctxuatbh_.setDmthuocMaso(obj.getThuocnoitruMathuoc());
						ctxuatbh_.setDmnctMaso(null);
						ctxuatbh_.setDmnguonkinhphiMaso(null);
						ctxuatbh_.setDmquocgiaMaso(obj.getThuocnoitruQuocgia());
						ctxuatbh_.setDmnhasanxuatMaso(obj.getThuocnoitruHangsx());
						ctxuatbh_.setCtxuatbhxvNamnhap(null);
						ctxuatbh_.setCtxuatbhxvNgayhandung(null);
						ctxuatbh_.setCtxuatbhxvThanghandung(null);
						ctxuatbh_.setCtxuatbhxvNamhandung(null);
						ctxuatbh_.setCtxuatbhxvDongia(obj.getThuocnoitruDongia());
						ctxuatbh_.setCtxuatbhxvNgaygiocn(null);
						
						listCtXuatBhXuatVien.add(ctxuatbh_);
					}
					
					pxNgaylap = Utils.getCurrentDate();
					phieuXuatBhXuatVien.setHsba(hsba);					
					
					Double soTienThuocBHYT = new Double(0);
					
					for (CtXuatBhXuatVien ctXuatBh : listCtXuatBhXuatVien){
						soTienThuocBHYT = ctXuatBh.getCtxuatbhxvDongia() * ctXuatBh.getCtxuatbhxvSoluong();
					}
					
					//log.info("soTienThuocBHYT:"+ soTienThuocBHYT);
					
					phieuXuatBhXuatVien.setPhieuxuatbhxvThanhtien(soTienThuocBHYT);
				}
				else
				{
					this.resetHsba();
					FacesMessages.instance().add(IConstantsRes.SO_VAO_VIEN_UNAVAILABLE, soVaovien);
					nofoundHsba = "true";
					//log.info("------So vao vien khong hop le---------");
				}
			}
			else{
				this.resetHsba();
			}
		}
		catch(Exception ex){
			log.info(":( ERROR: " + ex);
			ex.printStackTrace();
		}
		
		//log.info("---End loadTiepdonAjax() method-----");
	}
	
	public void loadPhieuXuatBhAjax(){
		//log.info("---Begin loadPhieuXuatBhAjax() method----");
		
		try{
			//this.resetForm();
			ctxuatbh = new CtXuatBhXuatVien();
			listCtXuatBhXuatVien = new ArrayList<CtXuatBhXuatVien>();
			benhnhan = new BenhNhan();
			SetInforUtil.setInforIfNullForBN(benhnhan);
			hsba = new Hsba();
			String pxMa = phieuXuatBhXuatVien.getPhieuxuatbhxvMa();
			if(pxMa != null && ! pxMa.trim().equals("")){
				//log.info("Phieu xuat ma khac null");
				PhieuXuatBhXuatVienDelegate pxWS = PhieuXuatBhXuatVienDelegate.getInstance();				
				
				CtXuatBhXuatVienDelegate ctXuatBHDel = CtXuatBhXuatVienDelegate.getInstance();
				phieuXuatBhXuatVien = pxWS.find(pxMa);
				if(phieuXuatBhXuatVien != null){
					//log.info("Tim thay Phieu xuat bh");
					
					hsba = phieuXuatBhXuatVien.getHsba();
					HsbaBhytDelegate hsbaBhytDel = HsbaBhytDelegate.getInstance();
					hsbaBhyt = hsbaBhytDel.findBySoVaoVienLastHsbaBhyt(hsba.getHsbaSovaovien());
					benhnhan = hsba.getBenhnhanMa();
					
					SetInforUtil.setInforIfNullForBN(benhnhan);
					
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					Date ngaylap = phieuXuatBhXuatVien.getPhieuxuatbhxvNgaylap();
					pxNgaylap = df.format(ngaylap);
					
					this.setFormHsba(hsba, hsbaBhyt);
					listCtXuatBhXuatVien = ctXuatBHDel.findByPhieuxuatBhXuatVienMa(phieuXuatBhXuatVien.getPhieuxuatbhxvMa());
				}else{
					//log.info("Ko Tim thay Phieu xuat bh");
					FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_NULL, pxMa);
					this.resetForm();
				}
			}else{
				this.resetForm();
			}
		}
		catch (Exception e) {
			log.info(":( ERROR : " + e);
		}
		//log.info("---End loadPhieuXuatBhAjax() method-----");
	}
	
	private void setFormHsba(Hsba hsba, HsbaBhyt hsbaBhyt){
		//log.info("begin setFormHsba(Hsba hsba, HsbaBhyt hsbaBhyt) method");
		benhnhan = hsba.getBenhnhanMa();
		SetInforUtil.setInforIfNullForBN(benhnhan);
		//log.info("benhnhan: " + benhnhan);
		if(benhnhan != null){
			bnNamsinh = benhnhan.getBenhnhanNamsinh();
			 if (benhnhan.getDmgtMaso() != null){
					if ("1".equals(benhnhan.getDmgtMaso().getDmgtMa())){
						bnGtinh = "0";  //1 : Name
					}else{
						bnGtinh = "1";
					}					
				}
		}
		
		DmTinh tinhBhyt = hsbaBhyt.getHsbabhytTinhbh();
		if(tinhBhyt != null){
			hsbaTinhBhyt = ""+tinhBhyt.getDmtinhMaso();
			tinhBhyt = null;
		}
		DtDmKhoiBhyt khoiBhyt = hsbaBhyt.getHsbabhytKhoibh();
		if(khoiBhyt != null){
			hsbaKhoiBhyt = khoiBhyt.getDtdmkhoibhytMa();
			khoiBhyt = null;
		}
		
		DmBenhVien kcbBhyt = hsbaBhyt.getHsbabhytMakcb();
		if(kcbBhyt != null){
			hsbaKcbBhyt = kcbBhyt.getDmbenhvienMa();
			kcbBhyt = null;
		}
		DmDoiTuong dtuong = hsba.getDoituongMa();
		if(dtuong != null){
			hsbaDoituong = dtuong.getDmdoituongTen();
			hsbaDoituongMa = dtuong.getDmdoituongMa();
			dtuong = null;
		}
		DtDmNhanVien dmNvPhat = phieuXuatBhXuatVien.getDtdmnhanvienPhat();
		if(dmNvPhat != null){
			nvPhat = dmNvPhat.getDtdmnhanvienMa();
			dmNvPhat = null;
		}
		
		DmBenhIcd chandoan  = hsba.getHsbaMachdravien();
		if(chandoan != null){
			hsbaChandoan = chandoan.getDmbenhicdTen();
		}		
		//log.info("End setFormHsba(Hsba hsba, HsbaBhyt hsbaBhyt) method");
	}	
	
	public void resetForm(){
		//log.info("---Begin resetForm() method----");
		phieuXuatBhXuatVien = new PhieuXuatBhXuatVien();
		ctxuatbh = new CtXuatBhXuatVien();
		tonKho = null;
		thanhtienCt = 0;
		updateCtXuat = ""+0;
		thieuthuoc = ""+0;
		tonkhoMa = "";
		tntMathuoc = "";
		ctxSoluong = "";
		hsba = new Hsba();
		benhnhan = new BenhNhan();
		hsbaBhyt = new HsbaBhyt();
		SetInforUtil.setInforIfNullForBN(benhnhan);
		listCtXuatBhXuatVien = new ArrayList<CtXuatBhXuatVien>();	
		
		pxNgaylap = Utils.getCurrentDate();
		nvPhat = "";
		hsbaDoituong = "";
		hsbaDoituongMa = "";
		hsbaKcbBhyt = "";
		hsbaChandoan = "";
		nvCapnhat = "";
		bnNamsinh = "";
		bnGtinh = "";
		hsbaTinhBhyt = "";
		hsbaKhoiBhyt = "";
		tntMathuoc = "";
		tntQuocgiaSx = "";
		tntHangSx = "";
		ctxThanhtien = "";
		tkDongiaban = "";
		thieuthuoc = ""+0;
		updateCtXuat = ""+0;
		hstt = new HsThtoan();
		reportFileName = "";
		reportFinished = "";
			
		//log.info("---End resetForm() method-----");
	}
	
	public void resetHsba(){
		//log.info("---End resetHsba() method-----");
		String pxMa = phieuXuatBhXuatVien.getPhieuxuatbhxvMa(); 
		if(pxMa != null && ! pxMa.equals("")){
			ctxuatbh = new CtXuatBhXuatVien();
			updateCtXuat = ""+0;
			thanhtienCt = 0;
			thieuthuoc = ""+0;
			tonKho = null;
			tntMathuoc = "";
			tonkhoMa = "";
			ctxSoluong = "";
			hsba = new Hsba();
			benhnhan = new BenhNhan();
			hsbaBhyt = new HsbaBhyt();
			SetInforUtil.setInforIfNullForBN(benhnhan);
			listCtXuatBhXuatVien = new ArrayList<CtXuatBhXuatVien>();
			hsbaDoituong = "";
			hsbaDoituongMa = "";
			hsbaKcbBhyt = "";
			hsbaChandoan = "";
			bnNamsinh = "";
			bnGtinh = "";
			hsbaTinhBhyt = "";
			hsbaKhoiBhyt = "";
			tntMathuoc = "";
			tntQuocgiaSx = "";
			tntHangSx = "";
			ctxThanhtien = "";
			tkDongiaban = "";
			reportFileName = "";
			reportFinished = "";
		}else{
			this.resetForm();
		}
		//log.info("---End resetHsba() method-----");
	}
		
	public void ghiNhan(){
		//log.info("ghiNhan");		
		List<PhieuXuatBhXuatVien> phieuXuatBhTemp = (List<PhieuXuatBhXuatVien>) PhieuXuatBhXuatVienDelegate.getInstance().findBySovaovien_Kho(hsba.getHsbaSovaovien(), dmKhoXuat.getDmkhoaMaso());
		if(phieuXuatBhTemp.size()>0){
			nosuccess = "true";
			FacesMessages.instance().add(IConstantsRes.DA_XUAT_THUOC_NT, benhnhan.getBenhnhanHoten());
			return;
		}
		if (phieuXuatBhXuatVien.getPhieuxuatbhxvMa() != null && !phieuXuatBhXuatVien.getPhieuxuatbhxvMa().equals("")){
			nosuccess = "true";
			FacesMessages.instance().add(IConstantsRes.DA_XUAT_THUOC_NT, benhnhan.getBenhnhanHoten());
			return;
		}
		
		//Kiem tra xem BN nay da thanh toan vien phi chua, neu chua thi khong dc xuat thuoc
		HsThtoanDelegate hsttDel = HsThtoanDelegate.getInstance();
		HsThtoan hstt = hsttDel.findBySovaovien(hsba.getHsbaSovaovien());
		if(hstt == null){
			nosuccess = "true";
			FacesMessages.instance().add(IConstantsRes.HSBA_NOTFOUND);
			return;
		}else{
			//Chua thanh toan, khong duoc xuat thuoc
			Boolean datt = hstt.getHsthtoanDatt();
			Double tienBNTra = hstt.getHsthtoanBntra();
			
			if(tienBNTra > 0){
				if(datt == null || (datt != null && datt.booleanValue() == false)){
					nosuccess = "true";
					FacesMessages.instance().add(IConstantsRes.CHUA_THANH_TOAN_VP);
					return;
				}				
			}
			if(datt != null && datt.booleanValue() == false){
				nosuccess = "true";
				FacesMessages.instance().add(IConstantsRes.CHUA_THANH_TOAN_VP);
				return;
			}			
		}
		try {	
			HsbaDelegate hsbaWsPort = HsbaDelegate.getInstance();
			String soVaovien = hsba.getHsbaSovaovien();
			Hsba hsba_tmp = hsbaWsPort.find(soVaovien);
			if (hsba_tmp == null){
				nosuccess = "true";
				FacesMessages.instance().add(IConstantsRes.HSBA_NOTFOUND);
				return;
			}
			String rs = "";
			if (listTNT != null && listCtXuatBhXuatVien != null){
				phieuXuatBhXuatVien.setHsba(hsba_tmp);				
				DtDmNhanVien dmNvPhat = new DtDmNhanVien();
				dmNvPhat.setDtdmnhanvienMa(nvPhat);
				dmNvPhat.setDtdmnhanvienMaso(nvPhatSo);
				phieuXuatBhXuatVien.setDtdmnhanvienPhat(dmNvPhat);
				DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
				DtDmNhanVien nhanViencn = nvDelegate.findByNd(identity.getUsername());
				phieuXuatBhXuatVien.setDtdmnhanvienCn(nhanViencn);	
				phieuXuatBhXuatVien.setPhieuxuatbhxvNgaygiocn(new Date());
				phieuXuatBhXuatVien.setPhieuxuatbhxvNgaygiophat(new Date());
				phieuXuatBhXuatVien.setPhieuxuatbhxvNgaylap(new SimpleDateFormat("dd/MM/yyyy").parse(pxNgaylap));
					
				phieuXuatBhXuatVien.setDmkhoaMaso(dmKhoXuat);
				
				rs = PhieuXuatBhXuatVienDelegate.getInstance().createByThuocNoiTru(dmKhoXuat.getDmkhoaMaso().intValue(),phieuXuatBhXuatVien, listTNT, listCtXuatBhXuatVien);					
			}
			if (rs == null || rs.equals("")){
				nosuccess = "true";
				FacesMessages.instance().add(IConstantsRes.FAIL);
			}else if(rs.equals("SLTHET")){
				nosuccess = "true";
				FacesMessages.instance().add(IConstantsRes.PHIEUXUATBH_SLKHONGDUXUAT);
			}else{
				yteLog = new YteLog();
				listDataLog="";
				for (ThuocNoiTru thuocNoiTru : listTNT) {
					//luu log thong tin thuoc
					listDataLog += "Ma LK:"+ thuocNoiTru.getThuocnoitruMalk()+
							" Don gia: "+ thuocNoiTru.getThuocnoitruDongia()+ " Don gia ban: "+ thuocNoiTru.getThuocnoitruDongiaban() + 
							" So luong: "+ thuocNoiTru.getThuocnoitruSoluong()+
							" So lo: "+
							" Nam SX: " + thuocNoiTru.getThuocnoitruNamnhap()+
							" Nam HD: " + thuocNoiTru.getThuocnoitruNamhd()+ "\n";
				}
//				Luu log he thong
		         yteLog.setForm("B4166_Xuathangchobenhnhan");
		         yteLog.setUserId(identity == null ? "NULL" : identity.getUsername());
		         yteLog.setObjectId(hsba.getHsbaSovaovien());
		         yteLog.setLogString("So phieu: "+ getPhieuxuatbhXuatVien(true).getPhieuxuatbhxvMa() + 
		         					" Ma benh nhan: "+ benhnhan.getBenhnhanMa()+
		         					" Doi tuong: "+ hsbaDoituongMa+ 
		         					" The BHYT: " + (hsbaBhyt== null ? "NULL" : hsbaBhyt.getHsbabhytSothebh())+
		         					" Ngay lap: "+ pxNgaylap + 
		         					" Chan đoan: "+ hsbaChandoan+ 
		         					" Nguoi phat: "+ nvPhat );
		         yteLog.setDateTime(new Date());
		         yteLog.setListData(listDataLog);

		         yteLogDele.create(yteLog);
				FacesMessages.instance().add(IConstantsRes.SUCCESS);
			}
		} catch (NumberFormatException e) {
			nosuccess = "true";
			FacesMessages.instance().add(IConstantsRes.FAIL);
		} catch (ParseException e) {
			nosuccess = "true";
			FacesMessages.instance().add(IConstantsRes.FAIL);
		}
	}
	
	public String getTenChuongTrinh() {
		return tenChuongTrinh;
	}

	public void setTenChuongTrinh(String tenChuongTrinh) {
		this.tenChuongTrinh = tenChuongTrinh;
	}
	
	public List<ThuocNoiTru> getListTNT() {
		return listTNT;
	}

	public void setListTPK(List<ThuocNoiTru> listTNT) {
		this.listTNT = listTNT;
	}
	
	public String getTonkhoMa(){
		return tonkhoMa;
	}
	
	public void setTonkhoMa(String tonkhoMa){
		this.tonkhoMa = tonkhoMa;
	}
	
	public String getThieuthuoc() {
		return thieuthuoc;
	}

	public void setThieuthuoc(String thieuthuoc) {
		this.thieuthuoc = thieuthuoc;
	}
	
	public double getThanhtienCt() {
		return thanhtienCt;
	}

	public void setThanhtienCt(double thanhtienCt) {
		this.thanhtienCt = thanhtienCt;
	}
	
	public String getSaveResult() {
		return saveResult;
	}

	public void setSaveResult(String saveResult) {
		this.saveResult = saveResult;
	}
	
	public String getReportFinished() {
		return reportFinished;
	}

	public void setReportFinished(String reportFinished) {
		this.reportFinished = reportFinished;
	}
	
	public String getReportFileName() {
		return reportFileName;
	}

	public void setReportFileName(String reportFileName) {
		this.reportFileName = reportFileName;
	}
	
	public Integer getNvPhatSo() {
		return nvPhatSo;
	}

	public void setNvPhatSo(Integer nvPhatSo) {
		this.nvPhatSo = nvPhatSo;
	}
	
	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		XuatHangChoBenhNhanXuatVienAction.log = log;
	}
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	public String getNosuccess() {
		return nosuccess;
	}

	public void setNosuccess(String nosuccess) {
		this.nosuccess = nosuccess;
	}

	public String getHsbaDoituongMa() {
		return hsbaDoituongMa;
	}

	public void setHsbaDoituongMa(String hsbaDoituongMa) {
		this.hsbaDoituongMa = hsbaDoituongMa;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}
	
	public HsThtoan getHstt() {
		return hstt;
	}

	public void setHstt(HsThtoan hstt) {
		this.hstt = hstt;
	}
	
	public String getNofoundHsba() {
		return nofoundHsba;
	}

	public void setNofoundHsba(String nofoundHsba) {
		this.nofoundHsba = nofoundHsba;
	}
	
	public PhieuXuatBhXuatVien getPhieuxuatbhXuatVien() {
		return phieuXuatBhXuatVien;
	}
	
	public PhieuXuatBhXuatVien getPhieuxuatbhXuatVien(boolean created) {
		if(created){
			phieuXuatBhXuatVien = new PhieuXuatBhXuatVien(); 
		}
		return phieuXuatBhXuatVien;
	}

	public void setPhieuxuatbhXuatVien(PhieuXuatBhXuatVien phieuXuatBhXuatVien) {
		this.phieuXuatBhXuatVien = phieuXuatBhXuatVien;
	}
	
	public BenhNhan getBenhnhan() {
		return benhnhan;
	}

	public void setBenhnhan(BenhNhan benhnhan) {
		this.benhnhan = benhnhan;
	}
	
	public Hsba getHsba(){
		return hsba;
	}
	
	public void setHsba(Hsba hsba){
		this.hsba = hsba;
	}
	
	public String getBnNamsinh() {
		return bnNamsinh;
	}

	public void setBnNamsinh(String bnNamsinh) {
		this.bnNamsinh = bnNamsinh;
	}

	public String getBnGtinh() {
		return bnGtinh;
	}

	public void setBnGtinh(String bnGtinh) {
		this.bnGtinh = bnGtinh;
	}
	
	public String getNvPhat() {
		return nvPhat;
	}

	public void setNvPhat(String nvPhat) {
		this.nvPhat = nvPhat;
	}

	public String getNvCapnhat() {
		return nvCapnhat;
	}

	public void setNvCapnhat(String nvCapnhat) {
		this.nvCapnhat = nvCapnhat;
	}
	
	public String getHsbaDoituong() {
		return hsbaDoituong;
	}

	public void setHsbaDoituong(String hsbaDoituong) {
		this.hsbaDoituong = hsbaDoituong;
	}
	
	public HsbaBhyt getHsbaBhyt(){
		return hsbaBhyt;
	}
	
	public void setHsbaBhyt(HsbaBhyt hsbaBhyt){
		this.hsbaBhyt = hsbaBhyt;
	}
	
	public String getHsbaKcbBhyt() {
		return hsbaKcbBhyt;
	}

	public void setHsbaKcbBhyt(String hsbaKcbBhyt) {
		this.hsbaKcbBhyt = hsbaKcbBhyt;
	}

	public String getHsbaChandoan() {
		return hsbaChandoan;
	}

	public void setHsbaChandoan(String hsbaChandoan) {
		this.hsbaChandoan = hsbaChandoan;
	}

	public String getPxNgaylap() {
		return pxNgaylap;
	}

	public void setPxNgaylap(String pxNgaylap) {
		this.pxNgaylap = pxNgaylap;
	}

	public String getHsbaTinhBhyt() {
		return hsbaTinhBhyt;
	}

	public void setHsbaTinhBhyt(String hsbaTinhBhyt) {
		this.hsbaTinhBhyt = hsbaTinhBhyt;
	}

	public String getHsbaKhoiBhyt() {
		return hsbaKhoiBhyt;
	}

	public void setHsbaKhoiBhyt(String hsbaKhoiBhyt) {
		this.hsbaKhoiBhyt = hsbaKhoiBhyt;
	}
	
	public List<CtXuatBhXuatVien> getListCtXuatBhXuatVien(){
		return listCtXuatBhXuatVien;
	}
	
	public void setListCtXuatBhXuatVien(List<CtXuatBhXuatVien> listCtXuatBhXuatVien){
		this.listCtXuatBhXuatVien = listCtXuatBhXuatVien;
	}
}

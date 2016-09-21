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
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaLapTrichBienBanHoiChanDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.HsbaLapTrichBienBanHoiChan;
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
@Name("B293_Laptrichbienbanhoichan")
@Synchronized(timeout = 6000000)
public class LapTrichBienBanHoiChanAction implements Serializable {

	private static final long serialVersionUID = -5106695460338085910L;
	private static Logger log = Logger
			.getLogger(LapTrichBienBanHoiChanAction.class);

	private DtDmNhanVien newNhanVien;
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	private HsbaLapTrichBienBanHoiChan ltbbhc;
	private String ngayTGHC;
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
	
	String ngayCap;
	
	
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

		return "DieuTri_LapVanBanTheoMau_LapTrichBienBanHoiChan";
	}
	

	public String luongthanhvien;
	public String getLuongthanhvien(){
		if((bacsiList==null)||(bacsiList.size()==0))
		{
			luongthanhvien =null;
		}else
		{
			luongthanhvien = bacsiList.size()+"";
		}
		return luongthanhvien;
	}
	public void setLuongthanhvien(String luongthanhvien)
	{
		this.luongthanhvien = luongthanhvien;
	}
	
	private String gioTGHC;

	public String getGioTGHC() {
		return gioTGHC;
	}

	public void setGioTGHC(String gioTGHC) {
		this.gioTGHC = gioTGHC;
	}


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
		
		log.info("newNhanVien.getDtdmnhanvienTen()=" + newNhanVien.getDtdmnhanvienTen());
		log.info("nv.getDtdmnhanvienTen()=" + nv.getDtdmnhanvienTen());
		log.info("bacsiList.contains(nv)=" + bacsiList.contains(nv));
		if (!this.bacsiList.contains(nv)){
			this.bacsiList.add(nv);
		}
		log.info("bacsiList.size="+this.bacsiList.size());
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
	
	
	
	public void deleteCurrentBacsiRow(DtDmNhanVien currentBacsi) {
		if (this.bacsiList == null || this.bacsiList.size() == 0) {
			return;
		}		
		this.bacsiList.remove(currentBacsi);
		bacsiSelected = null;
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

	
	
	
	
	public void findGiuongPhong() {
		// if(ltbbhc.getHsbaltbbhcMaso()!=null)
		// System.out.println("ltbbhc="+ltbbhc.getHsbaltbbhcMaso());
		// if(ltbbhc.getHsbaSovaovien(true).getHsbaSovaovien()!=null)
		// System.out.println("ltbbhc.getHsbaSovaovien(true)="+ltbbhc.getHsbaSovaovien(true));
		// if(ltbbhc.getHsbaSovaovien(true).getHsbaKhoadangdt(true).getDmkhoaMaso()!=null)
		// System.out.println("ltbbhc.getHsbaSovaovien(true).getHsbaKhoadangdt(true)="+ltbbhc.getHsbaSovaovien(true).getHsbaKhoadangdt(true));
		try {
			if (ltbbhc.getHsbaSovaovien(true).getHsbaKhoadangdt(true)
					.getDmkhoaMaso() != null) {
				HsbaChuyenMonDelegate hsbaChuyenMonDelegate = HsbaChuyenMonDelegate
						.getInstance();
				HsbaChuyenMon hsbaChuyenMon = hsbaChuyenMonDelegate
						.findBySoVaoVien_MaKhoa(ltbbhc.getHsbaSovaovien(true)
								.getHsbaSovaovien(), ltbbhc.getHsbaSovaovien(
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
		ltbbhc = new HsbaLapTrichBienBanHoiChan();
		this.bacsiList = new ArrayList<DtDmNhanVien>();
		this.newNhanVien = new DtDmNhanVien();
		
		crrDate = ngayCap = new SimpleDateFormat("dd/MM/yyyy")
				.format(new Date());
		gioTGHC=ngayTGHC="";
		nosuccess = nofound = nofoundHSBA = "false";
		isUpdate = false;
		
		findGiuongPhong();
	}

	public void displayHSBA() {
		log.info("---displayHSBAquang");
		this.bacsiList = new ArrayList<DtDmNhanVien>();
		this.newNhanVien = new DtDmNhanVien();
		
		
		String maHsba = ltbbhc.getHsbaSovaovien().getHsbaSovaovien().trim();
		Hsba hsba_tmp = null;
		if (!maHsba.equals("")) {
			hsba_tmp = HsbaDelegate.getInstance().find(maHsba);
			if (hsba_tmp == null) {
				nofoundHSBA = "true";
				hsba_tmp = new Hsba();
				FacesMessages.instance().add(IConstantsRes.HSBA_NULL, maHsba);
				hsba_tmp = new Hsba();
				setInfoIfNullForHsba(hsba_tmp);
				ltbbhc.setHsbaSovaovien(hsba_tmp);
				ltbbhc.setHsbaltbbhcChuandoanma(hsba_tmp.getHsbaMachdoanbd(true));
			} else {
				ltbbhc.setHsbaSovaovien(hsba_tmp);

			
			}
		} else {
			nofoundHSBA = "true";
			hsba_tmp = new Hsba();
			setInfoIfNullForHsba(hsba_tmp);
			ltbbhc.setHsbaSovaovien(hsba_tmp);
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

	public void displayLapTrichBienBanHoiChan() {
		log.info("---displayLapTrichBienBanHoiChan");
		String maLTBBHC = ltbbhc.getHsbaltbbhcMa().trim();
		HsbaLapTrichBienBanHoiChan ltbbhc_tmp = null;
		if (!maLTBBHC.equals("")) {
			HsbaLapTrichBienBanHoiChanDelegate hsbaLapTrichBienBanHoiChanDelegate =  HsbaLapTrichBienBanHoiChanDelegate.getInstance();
			ltbbhc_tmp =hsbaLapTrichBienBanHoiChanDelegate.findByHsbaltbbhcMa(maLTBBHC);
			if (ltbbhc_tmp == null) {
				nofound = "true";
				crrDate = ngayCap = new SimpleDateFormat("dd/MM/yyyy")
						.format(new Date());
				ltbbhc_tmp = new HsbaLapTrichBienBanHoiChan();
				FacesMessages.instance().add(IConstantsRes.LTBBHC_NULL, maLTBBHC);

			}

			ltbbhc = ltbbhc_tmp;

			setNgayCap(formatDate(ltbbhc.getHsbaltbbhcNgaycap()));
			setNgayTGHC(formatDate(ltbbhc.getHsbaltbbhcThoigianhoichan()));
			setGioTGHC(formatDateTime(ltbbhc.getHsbaltbbhcThoigianhoichan()));

			
			
			bacsiList = hsbaLapTrichBienBanHoiChanDelegate.findBacsiByHsbaltbbhcMa(ltbbhc.getHsbaltbbhcMa());
			
			System.out.println("bacsiList.size()="+bacsiList.size());
			
			 
//			setChuandoanMa(ltbbhc.getHsbaSovaovien(true).getHsbaTuvong(true)
//					.getDmbenhicdMa());
//			if (ltbbhc.getHsbaSovaovien(true).getHsbaTuvong(true)
//					.getDmbenhicdMaso() != null) {
//				setChuandoanMaso(ltbbhc.getHsbaSovaovien(true).getHsbaTuvong(
//						true).getDmbenhicdMaso());
//			}
//
//			setChuandoanName(ltbbhc.getHsbaSovaovien(true).getHsbaTuvong(true)
//					.getDmbenhicdTen());

			isUpdate = true;
		} else {
			nofound = "true";
			crrDate = ngayCap = new SimpleDateFormat("dd/MM/yyyy")
					.format(new Date());
			ltbbhc = new HsbaLapTrichBienBanHoiChan();
		}
		findGiuongPhong();
	}

	public String getdmBenhIcdMaCX() {
		return ltbbhc.getHsbaSovaovien(true).getHsbaTuvong(true)
				.getDmbenhicdMa();
	}

	public String getdmBenhIcdTenCX() {
		return ltbbhc.getHsbaSovaovien(true).getHsbaTuvong(true)
				.getDmbenhicdTen();
	}

	public void ghiNhan() {
//		log.info("---ghiNhan" + ltbbhc.getHsbaltbbhcChuandoanmaSau());
//		log.info("---ghiNhan"+ ltbbhc.getHsbaltbbhcChuandoanmaSau(true).getDmbenhicdMaso());
//		log.info("---ghiNhan"+ ltbbhc.getHsbaltbbhcChuandoanmaSau(true).getDmbenhicdMa());

		String result = "";
		if (!ngayCap.trim().equals("")) {
			ltbbhc.setHsbaltbbhcNgaygiocn(Utils.getDBDate("00:00", ngayCap)
					.getTime());
		}
		// System.out.println("ltbbhc.getHsbaltbbhcChuandoanmaTruoc(true).dmbenhicdMa = "+ltbbhc.getHsbaltbbhcChuandoanmaTruoc(true).getDmbenhicdMa());
		// if (!ngayCX.trim().equals("")){
		// if(gioCX.trim().equals("")){
		// ltbbhc.setHsbaltbbhcNgaycx(Utils.getDBDate("00:00", ngayCX).getTime());
		// }else
		// {
		// ltbbhc.setHsbaltbbhcNgaycx(Utils.getDBDate(gioCX, ngayCX).getTime());
		// }
		// }
		

		ltbbhc.setHsbaltbbhcThoigianhoichan(Utils.getDBDate(gioTGHC, ngayTGHC).getTime());
		if(!ngayCap.trim().equals("")){
			ltbbhc.setHsbaltbbhcNgaycap(Utils.getDBDate("00:00", ngayCap).getTime());
		}
//		System.out.println("ngayCap="+ltbbhc.getHsbaltbbhcNgaycap().toString());
//		System.out.println("ngayCatChi="+ltbbhc.getHsbaltbbhcNgaycatchi().toString());
//		System.out.println("ngayRut="+ltbbhc.getHsbaltbbhcNgayrut().toString());
		ltbbhc.setHsbaltbbhcNgaygiocn(new Date());
//		ltbbhc.setNhanvienMa(DtDmNhanVienDelegate.getInstance().findByNd(identity.getUsername()));
//		DmBenhIcd dmbTmp = new DmBenhIcd();
		//System.out.println("chuan doan ma so = " + getChuandoanMaso());
		// RemoveUtil.removeAllNullFromHSBALapTrichBienBanHoiChan(ltbbhc);
		RemoveUtil.removeAllNullFromHSBALapTrichBienBanHoiChan(ltbbhc);

//		System.out.println("xem ma bac si = "
//				+ ltbbhc.getHsbaltbbhcBacsi().getDtdmnhanvienMa());

//		try {
//			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate
//					.getInstance();
//			// System.out.println("chuandoanma = "+getChuandoanMa());
//			// dmbTmp = (DmBenhIcd)dtutilDelegate.findByMaSo(getChuandoanMaso(),
//			// "DmBenhIcd", "dmbenhicdMaso");
//			dmbTmp = (DmBenhIcd) dtutilDelegate.findByMa(getChuandoanMa(),
//					"DmBenhIcd", "dmbenhicdMa");
//			// System.out.println("Maso tim duoc = "+dmbTmp.getDmbenhicdMaso());
//			ltbbhc.setHsbaltbbhcChuandoanma(dmbTmp);
//
//		} catch (Exception ex) {
//			System.out.println("Loi tai " + ex.toString());
//		}
		if (isUpdate) {
//			result = HsbaLapTrichBienBanHoiChanDelegate.getInstance().update(ltbbhc);
//			result = HsbaLapTrichBienBanHoiChanDelegate.getInstance().editHsbaLapTrichBienBanHoiChan(ltbbhc,this.bacsiList);
			result = HsbaLapTrichBienBanHoiChanDelegate.getInstance().editHsbaLapTrichBienBanHoiChan(ltbbhc,this.bacsiList);
			if (result == null || result.equals("")) {
				nosuccess = "true";
				FacesMessages.instance().add(IConstantsRes.LTBBHC_CN_THATBAI);
			} else {
				ltbbhc.setHsbaltbbhcMa(result);
				FacesMessages.instance().add(IConstantsRes.LTBBHC_CN_THANHCONG,
						result);
			}
		} else {
//			result = HsbaLapTrichBienBanHoiChanDelegate.getInstance().insert(ltbbhc);
//			result = HsbaLapTrichBienBanHoiChanDelegate.getInstance().createHsbaLapTrichBienBanHoiChan(ltbbhc,this.bacsiList);
			result = HsbaLapTrichBienBanHoiChanDelegate.getInstance().createHsbaLapTrichBienBanHoiChan(ltbbhc,this.bacsiList);
			if (result == null || result.equals("")) {
				nosuccess = "true";
				FacesMessages.instance().add(IConstantsRes.LTBBHC_LT_THATBAI);
			} else {
				ltbbhc.setHsbaltbbhcMa(result);
				FacesMessages.instance().add(IConstantsRes.LTBBHC_LT_THANHCONG,
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
			tmp+=prefix+bacsi.get(i).getDmhocviMaso(true).getDmhocviMa() + " "+bacsi.get(i).getDtdmnhanvienTen();
			if(i==0)
				prefix= ", ";
		}
		tmp+=".";
		
		
		return tmp;
		
	}
	public void XuatReport() {
		loaiBCDT = "LapTrichBienBanHoiChan";
		log.info("Vao Method XuatReport lap trich bien ban hoi chan");
		HsbaLapTrichBienBanHoiChanDelegate hsbaLapTrichBienBanHoiChanDelegate =  HsbaLapTrichBienBanHoiChanDelegate.getInstance();
		String baocao1 = null;
		String pathTemplate = null;
		try {
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/Laptrichbienbanhoichan.jrxml";
			log.info("da thay file templete 5" + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			params.put("khoaRav", ltbbhc.getHsbaSovaovien(true).getHsbaKhoarav(true).getDmkhoaTen());
			params.put("KHOA_HIENTAI", ltbbhc.getHsbaSovaovien(true).getHsbaKhoadangdt(true).getDmkhoaTen());
			params.put("GIAMDOC", IConstantsRes.REPORT_DIEUTRI_GIAMDOC_BV);
			findGiuongPhong();
			
			params.put("GIUONG", getSogiuong()==null?" ":getSogiuong()+" ");
			params.put("BUONG", getSobuong()==null?" ":getSobuong()+" ");

			params.put("magiay", ltbbhc.getHsbaltbbhcMa());
			log.info(ltbbhc.getHsbaltbbhcMa());

			if (ltbbhc.getHsbaSovaovien() != null) {

				Hsba hsba = ltbbhc.getHsbaSovaovien();
				
				// 20120220 bao.ttc: tinh Tuoi BN den thoi diem ngay Hoi chan
				int iTuoi = hsba.getBenhnhanMa(true).getBenhnhanTuoi();
				int iDonviTuoi = hsba.getBenhnhanMa(true).getBenhnhanDonvituoi();
				String sDonViTuoi = "";
				
				Date datenow = new Date();
				if (ltbbhc.getHsbaltbbhcNgaycap() != null){
					datenow = ltbbhc.getHsbaltbbhcNgaycap();
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
					diachistr += hsba.getBenhnhanMa().getBenhnhanDiachi() + ", ";
				if (hsba.getBenhnhanMa().getXaMa(true).getDmxaTen() != null)
					diachistr += hsba.getBenhnhanMa().getXaMa(true).getDmxaTen() + ", ";
				if (hsba.getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() != null)
					diachistr += hsba.getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() + ", ";
				if (hsba.getBenhnhanMa().getTinhMa(true).getDmtinhTen() != null)
					diachistr += hsba.getBenhnhanMa().getTinhMa(true).getDmtinhTen();
				params.put("DIACHI", diachistr);
				
				if (hsba.getBenhnhanMa().getDmgtMaso(true).getDmgtTen() != null)
					params.put("gioiTinh", hsba.getBenhnhanMa().getDmgtMaso(true).getDmgtTen());
				else
					params.put("gioiTinh", "");
				params.put("SOVAOVIEN", hsba.getHsbaSovaovien());
				
				if (hsba.getHsbaKhoadangdt().getDmkhoaTen() != null)
					params.put("KHOA_HIENTAI", hsba.getHsbaKhoadangdt().getDmkhoaTen());
				else
					params.put("KHOA_HIENTAI", "");

				params.put("BACSI_THANHVIEN",getBacsiFromList(hsbaLapTrichBienBanHoiChanDelegate.findBacsiByHsbaltbbhcMa(ltbbhc.getHsbaltbbhcMa())));

				HsbaBhytDelegate hsbaBHYTDelegate = HsbaBhytDelegate.getInstance();
				HsbaBhyt hsbaBHYT = hsbaBHYTDelegate.findBySoVaoVienLastHsbaBhyt(hsba.getHsbaSovaovien());
				if (hsbaBHYT != null) {
					if (hsbaBHYT.getHsbabhytGiatri0() != null)
						params.put("GIATRITU", sdf.format(hsbaBHYT.getHsbabhytGiatri0()));
					if (hsbaBHYT.getHsbabhytGiatri1() != null)
						params.put("GIATRIDEN", sdf.format(hsbaBHYT.getHsbabhytGiatri1()));
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
							+ "hsba/", "pdf", "LapTrichBienBanHoiChan");
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

	public HsbaLapTrichBienBanHoiChan getLtbbhc() {
		return ltbbhc;
	}

	public void setLtbbhcX(HsbaLapTrichBienBanHoiChan ltbbhc) {
		this.ltbbhc = ltbbhc;
	}

	public String getNgayCap() {
		return ngayCap;
	}

	public void setNgayCap(String ngayCap) {
		this.ngayCap = ngayCap;
	}

	public String getNgayTGHC() {
		return ngayTGHC;
	}

	public void setNgayTGHC(String ngayTGHC) {
		this.ngayTGHC = ngayTGHC;
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

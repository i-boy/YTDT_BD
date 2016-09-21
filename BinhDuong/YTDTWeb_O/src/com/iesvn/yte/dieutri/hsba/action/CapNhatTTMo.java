package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;

import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaMoDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.HsbaMo;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B211_1_Capnhathongtinmo")
@Synchronized(timeout = 6000000)
public class CapNhatTTMo implements Serializable {
	private static String FORMAT_DATE = "dd/MM/yyyy";
	private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";

	private static final long serialVersionUID = 10L;

	@DataModel
	private java.util.List<HsbaMo> listCtkqB211_1 = null;

	@In(required = false)
	@Out(required = false)
	@DataModelSelection
	private HsbaMo ttSelected;

	@In(required = false)
	@Out(required = false)
	private String goToB211_1;

	private BenhNhan benhNhan;

	@In(required = false)
	@Out(required = false)
	private String soBenhAn;

	@In(required = false)
	@Out(required = false)
	private String khoaMa;

	private String khoaMaDisplay;
	private String soBenhAnDisplay;

	public String getKhoaMaDisplay() {
		return khoaMaDisplay;
	}

	public void setKhoaMaDisplay(String khoaMaDisplay) {
		this.khoaMaDisplay = khoaMaDisplay;
	}

	public String getSoBenhAnDisplay() {
		return soBenhAnDisplay;
	}

	public void setSoBenhAnDisplay(String soBenhAnDisplay) {
		this.soBenhAnDisplay = soBenhAnDisplay;
	}

	@Factory("goToB211_1")
	@Create
	@Begin(nested = true)
	public String setValueForListHSBAMo() {
		try {
			ngaySinh = "";
			displayInfor();
			khoaMaDisplay = khoaMa;
			soBenhAnDisplay = soBenhAn;		
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "DieuTri_CapNhat_CapNhatThongTinMo";
	}

	private static Logger log = Logger.getLogger(CapNhatTTMo.class);

	private String noiDung;
	private String capCuuPhien;
	private Boolean chetBanMo;
	private String maPhauThuatThuThuat;
	private Integer maSoPhauThuatThuThuat;
	private String maVoCam;
	private Integer maSoVoCam;
	private Boolean taiBien;

	private String maPhauThuatVien;
	private Integer maSoPhauThuatVien;

	private Integer maSoCapCuuPhien;

	private void resetValue() {
		this.noiDung = "";
		this.capCuuPhien = "";
		this.chetBanMo = new Boolean(false);
		this.maPhauThuatThuThuat = "";
		this.maVoCam = "";
		this.taiBien = new Boolean(false);

		this.maPhauThuatVien = "";

		this.gioMo = "";
		this.ngayMo = "";

		//this.soBenhAn = "";
		this.ngaySinh = "";

	}

	private String resultHidden = "";

	/**
	 * 
	 * @param kqMo
	 */
	private void copyTo(HsbaMo hsbaMo) {

		hsbaMo.setHsbamoNoidung(this.noiDung);

		hsbaMo.setHsbamoChetbanmo(this.chetBanMo);
		hsbaMo.getHsbamoMamo().setDtdmphauthuatMa(this.maPhauThuatThuThuat);
		hsbaMo.getHsbamoMamo().setDtdmphauthuatMaso(this.maSoPhauThuatThuThuat);

		hsbaMo.getVocamMaso().setDtdmvocamMa(this.maVoCam);
		hsbaMo.getVocamMaso().setDtdmvocamMaso(this.maSoVoCam);

		hsbaMo.setHsbamoTaibien(this.taiBien);

		hsbaMo.getHsbamoBacsi().setDtdmnhanvienMa(this.maPhauThuatVien);
		hsbaMo.getHsbamoBacsi().setDtdmnhanvienMaso(this.maSoPhauThuatVien);
		

		hsbaMo.getHsbamoCcphien().setDtdmccpMaso(this.maSoCapCuuPhien);
		hsbaMo.getHsbamoCcphien().setDtdmccpMa(this.capCuuPhien);

		if (this.gioMo != null && !this.gioMo.equals("") && this.ngayMo != null
				&& !this.ngayMo.equals("")) {
			hsbaMo.setHsbamoNgaygiomo(Utils.getDBDate(this.gioMo, this.ngayMo).getTime());
		} else {
			hsbaMo.setHsbamoNgaygiomo(null);
		}

	}

	/**
	 * 
	 * @param kqMo
	 */
	private void copyFrom(HsbaMo hsbaMo) {

		this.noiDung = hsbaMo.getHsbamoNoidung();
		this.capCuuPhien = hsbaMo.getHsbamoCcphien().getDtdmccpMa();
		this.maSoCapCuuPhien = hsbaMo.getHsbamoCcphien().getDtdmccpMaso();
		this.chetBanMo = hsbaMo.getHsbamoChetbanmo();
		this.maPhauThuatThuThuat = hsbaMo.getHsbamoMamo().getDtdmphauthuatMa();
		this.maSoPhauThuatThuThuat = hsbaMo.getHsbamoMamo()
				.getDtdmphauthuatMaso();

		this.maVoCam = hsbaMo.getVocamMaso().getDtdmvocamMa();
		this.maSoVoCam = hsbaMo.getVocamMaso().getDtdmvocamMaso();

		this.taiBien = hsbaMo.getHsbamoTaibien();

		this.maPhauThuatVien = hsbaMo.getHsbamoBacsi().getDtdmnhanvienMa();
		this.maSoPhauThuatVien = hsbaMo.getHsbamoBacsi().getDtdmnhanvienMaso();

		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat(FORMAT_DATE);
		if (hsbaMo.getHsbamoNgaygiomo() != null) {
			Date ngaygiobatdaumo = hsbaMo.getHsbamoNgaygiomo();
			this.gioMo = Utils.getGioPhut(ngaygiobatdaumo);
			this.ngayMo = formatter.format(ngaygiobatdaumo);
		} else {
			this.gioMo = "";
			this.ngayMo = "";
		}

	}

	

	private String ngaySinh;

	private Integer khoaMaSo;

	private String gioMo;
	private String ngayMo;

	private boolean bUpdated = false;

	private String gioi = "";

	/**
	 * function handler for on click event
	 * 
	 * @throws Exception
	 */
	public void changetoupdate() throws Exception {
		bUpdated = true;
		

		copyFrom(this.ttSelected);

	}

	/**
	 * 
	 * @throws Exception
	 */
	public void nhaplai() throws Exception {

		// kqMoSelected = new KetQuaMo();
		// setInforIfNullForKetQuaMo(kqMoSelected);

		benhNhan = new BenhNhan();
		SetInforUtil.setInforIfNullForBN(benhNhan);

		this.listCtkqB211_1.removeAll(this.listCtkqB211_1);

		// tuoi = "";
		ngaySinh = "";
		//this.soBenhAn = "";

		log.debug("this.listCtkqB211_1.size():" + this.listCtkqB211_1.size());

		resetValue();
	}

	/**
	 * 
	 */
	private void setOtherValue() {
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat(FORMAT_DATE);

		if (benhNhan.getBenhnhanNgaysinh() != null
				&& !benhNhan.getBenhnhanNgaysinh().equals("")) {
			ngaySinh = formatter.format(benhNhan.getBenhnhanNgaysinh()
					.getTime());

		}
		if (benhNhan.getDmgtMaso() != null) {
			if ("1".equals(benhNhan.getDmgtMaso().getDmgtMa())) {
				gioi = "r1"; // 1 : Name
			} else {
				gioi = "r2";
			}
		} else {
			gioi = null;
		}
	}

	/**
	 * 
	 * @throws Exception
	 */
	public void displayInfor() throws Exception {
		try {

//			HsbaChuyenMonWSService service = new HsbaChuyenMonWSServiceLocator();
//			HsbaChuyenMonWS ws = service.getHsbaChuyenMonWSPort();
			
			HsbaChuyenMonDelegate hsbaCMDelegate = HsbaChuyenMonDelegate.getInstance();
			
			log.info("displayInfor()");
			log.info("his.soBenhAn:" + this.soBenhAn);
			log.info("his.khoaMa:" + this.khoaMa);

			System.out.println("displayInfor()");
			System.out.println("his.soBenhAn:" + this.soBenhAn);
			System.out.println("his.khoaMa:" + this.khoaMa);

			if (this.soBenhAn == null || this.soBenhAn.trim().equals("")) {
				nhaplai();
				System.out.println("return at 1");
				return;
			}
			if (this.khoaMa == null || this.khoaMa.trim().equals("")) {
				nhaplai();
				System.out.println("return at 2");
				return;

			}
			String khoa_tmp = this.khoaMa;
			String hsba_tmp = this.soBenhAn;

			System.out.println("displayInfor()");
			System.out.println("his.soBenhAn:" + this.soBenhAn);
			System.out.println("his.khoaMa:" + this.khoaMa);

			HsbaChuyenMon hoSoBenhAnCM_temp = hsbaCMDelegate.findBySoVaoVien_MaKhoa(
					this.soBenhAn, this.khoaMa);
			if (hoSoBenhAnCM_temp != null) {

				benhNhan = hoSoBenhAnCM_temp.getHsbaSovaovien().getBenhnhanMa();

				SimpleDateFormat formatter;
				formatter = new SimpleDateFormat(FORMAT_DATE);

				SetInforUtil.setInforIfNullForBN(benhNhan);
				//this.soBenhAn = hoSoBenhAnCM_temp.getHsbaSovaovien().getHsbaSovaovien();
			} else {

				nhaplai();

				FacesMessages.instance().add(IConstantsRes.KHOA_HSBA_NULL,
						hsba_tmp, khoa_tmp);
				resultHidden = "fail";

				System.out.println("return at 2");
				return;
			}

//			HsbaMoWSService serviceKq = new HsbaMoWSServiceLocator();
//			HsbaMoWS wsKq = serviceKq.getHsbaMoWSPort();
			
			HsbaMoDelegate hsbaMoDelegate = HsbaMoDelegate.getInstance();
			
			 List<HsbaMo> arrayHsbaMo = hsbaMoDelegate.findBySoSoVaoVienVaKhoaMa(
					this.soBenhAn, this.khoaMa);
			// this.listCtkqB211_1.removeAll(this.listCtkqB211_1);
			listCtkqB211_1 = new ArrayList<HsbaMo>();
			if (arrayHsbaMo != null && arrayHsbaMo.size() > 0) {
				for (HsbaMo kqMo : arrayHsbaMo) {

					SetInforUtil.setInforIfNullForHSBAMo(kqMo);
					this.listCtkqB211_1.add(kqMo);

				}
			}

			setOtherValue();

			System.out.println("this.listCtkqB211_1:"
					+ this.listCtkqB211_1.size());

		} catch (Exception e) {
			System.out.println("error on function displayInfor" + e);
		}
	}

	/**
	 * 
	 */
	public void deleteCurrentRow(int index) {

		System.out.println("delete current row....");
		if (this.listCtkqB211_1 == null || this.listCtkqB211_1.size() == 0) {
			return;
		}
		System.out.println("delete current row...."
				+ this.listCtkqB211_1.size());
		this.listCtkqB211_1.remove(index);
		ttSelected = new HsbaMo();
		SetInforUtil.setInforIfNullForHSBAMo(ttSelected);

		khoaMaDisplay = khoaMa;
		soBenhAnDisplay = soBenhAn;
	}
	
	public String quayLai(){
		return "DieuTri_CapNhat_CapNhatThongTinChung";
	}
	private void addNewRow() {

		HsbaMo hsbaMo_tmp = new HsbaMo();
		SetInforUtil.setInforIfNullForHSBAMo(hsbaMo_tmp);
		copyTo(hsbaMo_tmp);

		System.out.println("add current row....");
		if (hsbaMo_tmp.getHsbamoNoidung() != null
				&& !hsbaMo_tmp.getHsbamoNoidung().equals("")) {
			System.out.println("add current row....");
			this.listCtkqB211_1.add(0, hsbaMo_tmp);

		}
		resetValue();
	}

	private void updateRow() {
		this.bUpdated = false;

		copyTo(this.ttSelected);
		int indexOfSelected = listCtkqB211_1.indexOf(this.ttSelected);
		if (indexOfSelected < 0) {
			addNewRow();
		} else {
			this.listCtkqB211_1.set(listCtkqB211_1.indexOf(this.ttSelected),
					this.ttSelected);
		}

		resetValue();

	}

	/*
	 * 
	 */
	public void addNewRowOrUpdate() {

		System.out.println("bUpdated...." + bUpdated);

		if (noiDung == null || noiDung.equals("")) {
			System.out.println("noi dung = empty, do nothing");
			return;
		}
		
		
		System.out.println("khoaMa:" + khoaMa);
		System.out.println("soBenhAn:" + soBenhAn);
		
		if (khoaMa == null || khoaMa.equals("") || soBenhAn == null
				|| soBenhAn.equals("")) {
			System.out
					.println("khoaMa = empty or soBenhAn = empty, do nothing");
			return;
		}

		if (this.bUpdated) {
			updateRow();
		} else {
			addNewRow();
		}

		khoaMaDisplay = khoaMa;
		soBenhAnDisplay = soBenhAn;
	}

	/**
	 * 
	 * @throws Exception
	 */
	public void ghiNhan() throws Exception {
		try {
			if (listCtkqB211_1 == null) {
				return;
			}
			// note: if size of list of ket qua mo is 0 -> have to call WS to
			// delete

//			HsbaMoWSService service = new HsbaMoWSServiceLocator();
//			HsbaMoWS ws = service.getHsbaMoWSPort();
			HsbaMoDelegate hsbaMoDelegate = HsbaMoDelegate.getInstance();
			
//			List<HsbaMo> arrayKqMo = new HsbaMo[listCtkqB211_1.size()];

//			for (int i = 0; i < listCtkqB211_1.size(); i++) {
//				System.out.println("listCtkqB211_1.get(i)"
//						+ listCtkqB211_1.get(i));
//			}
			for (int i = 0; i < listCtkqB211_1.size(); i++) {

				RemoveUtil.removeAllNullFromHSBAMo(listCtkqB211_1.get(i));
//				arrayKqMo[i] = listCtkqB211_1.get(i);
			}
			hsbaMoDelegate.capNhatKetQuaMo(listCtkqB211_1, soBenhAn, khoaMa);

			//listCtkqB211_1 = new ArrayList<HsbaMo>();
			//ttSelected = new HsbaMo();
			//SetInforUtil.setInforIfNullForHSBAMo(ttSelected);
			//resetValue();

			FacesMessages.instance().add(IConstantsRes.SUCCESS);
			resultHidden = "success";
		} catch (Exception e) {
			FacesMessages.instance().add(IConstantsRes.FAIL);
			resultHidden = "fail";
			e.printStackTrace();

		}

		khoaMaDisplay = khoaMa;
		soBenhAnDisplay = soBenhAn;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

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

	public BenhNhan getBenhNhan() {
		return benhNhan;
	}

	public void setBenhNhan(BenhNhan benhNhan) {
		this.benhNhan = benhNhan;
	}

	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getGioi() {
		return gioi;
	}

	public void setGioi(String gioi) {
		this.gioi = gioi;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getSoBenhAn() {
		return soBenhAn;
	}

	public void setSoBenhAn(String soBenhAn) {
		this.soBenhAn = soBenhAn;
	}

	public String getKhoaMa() {
		return khoaMa;
	}

	public void setKhoaMa(String khoaMa) {
		this.khoaMa = khoaMa;
	}

	public boolean isBUpdated() {
		return bUpdated;
	}

	public void setBUpdated(boolean updated) {
		bUpdated = updated;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public String getMaPhauThuatThuThuat() {
		return maPhauThuatThuThuat;
	}

	public void setMaPhauThuatThuThuat(String maPhauThuatThuThuat) {
		this.maPhauThuatThuThuat = maPhauThuatThuThuat;
	}

	public String getMaVoCam() {
		return maVoCam;
	}

	public void setMaVoCam(String maVoCam) {
		this.maVoCam = maVoCam;
	}

	public String getMaPhauThuatVien() {
		return maPhauThuatVien;
	}

	public void setMaPhauThuatVien(String maPhauThuatVien) {
		this.maPhauThuatVien = maPhauThuatVien;
	}

	public String getCapCuuPhien() {
		return capCuuPhien;
	}

	public void setCapCuuPhien(String capCuuPhien) {
		this.capCuuPhien = capCuuPhien;
	}

	public Boolean getChetBanMo() {
		return chetBanMo;
	}

	public void setChetBanMo(Boolean chetBanMo) {
		this.chetBanMo = chetBanMo;
	}

	public Boolean getTaiBien() {
		return taiBien;
	}

	public void setTaiBien(Boolean taiBien) {
		this.taiBien = taiBien;
	}

	public String getResultHidden() {
		return resultHidden;
	}

	public void setResultHidden(String resultHidden) {
		this.resultHidden = resultHidden;
	}

	public Integer getKhoaMaSo() {
		return khoaMaSo;
	}

	public void setKhoaMaSo(Integer khoaMaSo) {
		this.khoaMaSo = khoaMaSo;
	}

	public Integer getMaSoPhauThuatThuThuat() {
		return maSoPhauThuatThuThuat;
	}

	public void setMaSoPhauThuatThuThuat(Integer maSoPhauThuatThuThuat) {
		this.maSoPhauThuatThuThuat = maSoPhauThuatThuThuat;
	}

	public Integer getMaSoVoCam() {
		return maSoVoCam;
	}

	public void setMaSoVoCam(Integer maSoVoCam) {
		this.maSoVoCam = maSoVoCam;
	}

	public Integer getMaSoPhauThuatVien() {
		return maSoPhauThuatVien;
	}

	public void setMaSoPhauThuatVien(Integer maSoPhauThuatVien) {
		this.maSoPhauThuatVien = maSoPhauThuatVien;
	}

	public Integer getMaSoCapCuuPhien() {
		return maSoCapCuuPhien;
	}

	public void setMaSoCapCuuPhien(Integer maSoCapCuuPhien) {
		this.maSoCapCuuPhien = maSoCapCuuPhien;
	}

	public java.util.List<HsbaMo> getListCtkqB211_1() {
		return listCtkqB211_1;
	}

	public void setListCtkqB211_1(java.util.List<HsbaMo> listCtkqB211_1) {
		this.listCtkqB211_1 = listCtkqB211_1;
	}

	public HsbaMo getTtSelected() {
		return ttSelected;
	}

	public void setTtSelected(HsbaMo ttSelected) {
		this.ttSelected = ttSelected;
	}

	public String getGioMo() {
		return gioMo;
	}

	public void setGioMo(String gioMo) {
		this.gioMo = gioMo;
	}

	public String getNgayMo() {
		return ngayMo;
	}

	public void setNgayMo(String ngayMo) {
		this.ngayMo = ngayMo;
	}

	public String getGoToB211_1() {
		return goToB211_1;
	}

	public void setGoToB211_1(String goToB211_1) {
		this.goToB211_1 = goToB211_1;
	}

}



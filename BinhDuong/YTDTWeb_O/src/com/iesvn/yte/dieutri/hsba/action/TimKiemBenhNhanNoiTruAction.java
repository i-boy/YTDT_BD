package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;

import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B253_Timbenhnhantheonhieutieuchi")
@Synchronized(timeout = 6000000)
public class TimKiemBenhNhanNoiTruAction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2423255254960254349L;
	private static Logger log = Logger.getLogger(TimKiemBenhNhanNoiTruAction.class);


	private String thoigian_thang=null;  
	private String thoigian_nam=null;
	private String tungay=null;
	private String denngay=null;
	private String ngayhientai;
	
	
	private String sobenhan;
	private String hoten;
	private String tuoi;
	private String namsinh;
	private Boolean gioi;
	
	private String matinh;
	private String tentinh;
	private String mahuyen;
	private String tenhuyen;
	private String maxa;
	private String tenxa;
	private String diachi;
	
	private String manghenghiep;
	private String tennghenghiep;
	
	private String madantoc;
	private String tendantoc;
	
	private String madoituong;
	private String tendoituong;
	
	private String madoituongBHYT;
	private String tendoituongBHYT;
	
	private String maloaitainan;
	private String tenloaitainan;
	
	private String mabvchuyen;
	private String tenbvchuyen;
	
	private String ngayvaovien;
	
	private String machandoanbd;
	private String tenchandoanbd;
	
	private String makhoadieutri;
	private String tenkhoadieutri;
	
	private String machandoanicd10;
	private String tenchandoanicd10;
	
	
	
	private String maketquadieutri;
	private String tenketquadieutri;
	
	private String ngayravien;
	
	
	
	
	
	@Create
	@Begin(join = true)
	public String init() {
		resetValue();
		return "DieuTri_DichVuTienIch_TimBenhNhanTheoNhieuTieuChi";
	}
	@End
	public void end(){
		
	}

	public void resetValue() {
		log.info("---resetValue");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		ngayhientai = Utils.getCurrentDate();
		thoigian_thang=String.valueOf(currentDate.getMonth() +1);
		thoigian_nam=String.valueOf(currentDate.getYear()+1900);
		tungay = sdf.format(currentDate);
		denngay = sdf.format(currentDate);
		resetResultValue();
		sobenhan=null;
		
	}
	private void resetResultValue(){
		 
		 hoten=null;
		 tuoi=null;
		 namsinh=null;
		 gioi=null;
		
		 matinh=null;
		 tentinh=null;
		 mahuyen=null;
		 tenhuyen=null;
		 maxa=null;
		 tenxa=null;
		 diachi=null;
		
		 manghenghiep=null;
		 tennghenghiep=null;
		
		 madantoc=null;
		 tendantoc=null;
		
		 madoituong=null;
		 tendoituong=null;
		
		 madoituongBHYT=null;
		 tendoituongBHYT=null;
		
		 maloaitainan=null;
		 tenloaitainan=null;
		
		 mabvchuyen=null;
		 tenbvchuyen=null;
		
		 ngayvaovien=null;
		
		 machandoanbd=null;
		 tenchandoanbd=null;
		
		 makhoadieutri=null;
		 tenkhoadieutri=null;
		
		 machandoanicd10=null;
		 tenchandoanicd10=null;
		
		
		
		 maketquadieutri=null;
		 tenketquadieutri=null;
		
		 ngayravien=null;
	}
	public void refresh(){
		log.info("call refresh()");
		resetResultValue();
		setInforFromChoseValue();
		log.info("end call refresh()");
	}
	
	private void setInforFromChoseValue(){
		log.info("setInforFromChoseValue()");
		log.info(sobenhan);
		if (sobenhan == null || sobenhan.equals("")){
			return;
		}
		HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
		Hsba hsba = hsbaDelegate.find(sobenhan);
		log.info(hsba);
		if (hsba == null){
			return;
		}
		
		hoten = hsba.getBenhnhanMa(true).getBenhnhanHoten();
		tuoi = hsba.getBenhnhanMa(true).getBenhnhanTuoi() + "";
		log.info(hoten);
		log.info(tuoi);
		
		Short donvituoi = hsba.getBenhnhanMa(true).getBenhnhanDonvituoi();
		if (donvituoi == 1){
			
		}else if (donvituoi == 2){
			tuoi = tuoi + " "+IConstantsRes.THANG;
		}else if (donvituoi == 3){
			tuoi = tuoi + " "+IConstantsRes.NGAY;
		}
		
		SimpleDateFormat df = new SimpleDateFormat(Utils.FORMAT_DATE);
		Date dNamSinh = hsba.getBenhnhanMa(true).getBenhnhanNgaysinh();
		if (dNamSinh != null){
			namsinh = df.format(dNamSinh);
		}else{
			namsinh = hsba.getBenhnhanMa(true).getBenhnhanNamsinh();
		}
		
		
		
		
		String sGioi = hsba.getBenhnhanMa(true).getDmgtMaso(true).getDmgtMa();
		if ("0".equals(sGioi)){//nu
			gioi = false;
		}else {//nam
			gioi = true;
		}
		
		matinh = hsba.getBenhnhanMa(true).getTinhMa(true).getDmtinhMa();
		tentinh = hsba.getBenhnhanMa(true).getTinhMa(true).getDmtinhTen();
		
		maxa = hsba.getBenhnhanMa(true).getXaMa(true).getDmxaMa();
		tenxa = hsba.getBenhnhanMa(true).getXaMa(true).getDmxaTen();
		
		mahuyen = hsba.getBenhnhanMa(true).getHuyenMa(true).getDmhuyenMa();
		tenhuyen = hsba.getBenhnhanMa(true).getHuyenMa(true).getDmhuyenTen();
		
		diachi = hsba.getBenhnhanMa(true).getBenhnhanDiachi();
		
		manghenghiep = hsba.getBenhnhanMa(true).getBenhnhanNghe(true).getDmnghenghiepMa();
		tennghenghiep = hsba.getBenhnhanMa(true).getBenhnhanNghe(true).getDmnghenghiepTen();
		
		madantoc = hsba.getBenhnhanMa(true).getDantocMa(true).getDmdantocMa();
		tendantoc = hsba.getBenhnhanMa(true).getDantocMa(true).getDmdantocTen();
		
		madoituong = hsba.getDoituongMa(true).getDmdoituongMa();
		tendoituong =  hsba.getDoituongMa(true).getDmdoituongTen();
		
		// DT BHYT
		
		maloaitainan = hsba.getTainanMa(true).getDmtainanMa();
		tenloaitainan = hsba.getTainanMa(true).getDmtainanTen();
		
		mabvchuyen = hsba.getHsbaDonvigoi(true).getDmbenhvienMa();
		tenbvchuyen = hsba.getHsbaDonvigoi(true).getDmbenhvienTen();
		
		Date dNgayGioVaoVien = hsba.getHsbaNgaygiovaov();
		if (dNgayGioVaoVien != null){
			ngayvaovien = df.format(dNgayGioVaoVien);
		}
		
		machandoanbd = hsba.getHsbaMachdoanbd(true).getDmbenhicdMa();
		tenchandoanbd =  hsba.getHsbaMachdoanbd(true).getDmbenhicdTen();
		
		machandoanicd10 = hsba.getHsbaMachdravien(true).getDmbenhicdMa();
		tenchandoanicd10 =  hsba.getHsbaMachdravien(true).getDmbenhicdTen();
		
		makhoadieutri = hsba.getHsbaKhoadangdt(true).getDmkhoaMa();
		tenkhoadieutri = hsba.getHsbaKhoadangdt(true).getDmkhoaTen();
		
		maketquadieutri = hsba.getHsbaKetqua(true).getDmkqdtMa();
		tenketquadieutri = hsba.getHsbaKetqua(true).getDmkqdtTen();
		
		Date dNgayGioRaVien = hsba.getHsbaNgaygiorav();
		if (dNgayGioRaVien != null){
			ngayravien = df.format(dNgayGioRaVien);
		}
		
		
	}
	public static Logger getLog() {
		return log;
	}
	public static void setLog(Logger log) {
		TimKiemBenhNhanNoiTruAction.log = log;
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
	public String getNgayhientai() {
		return ngayhientai;
	}
	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getSobenhan() {
		return sobenhan;
	}
	public void setSobenhan(String sobenhan) {
		this.sobenhan = sobenhan;
	}
	public String getHoten() {
		return hoten;
	}
	public void setHoten(String hoten) {
		this.hoten = hoten;
	}
	public String getTuoi() {
		return tuoi;
	}
	public void setTuoi(String tuoi) {
		this.tuoi = tuoi;
	}
	public String getNamsinh() {
		return namsinh;
	}
	public void setNamsinh(String namsinh) {
		this.namsinh = namsinh;
	}
	public Boolean getGioi() {
		return gioi;
	}
	public void setGioi(Boolean gioi) {
		this.gioi = gioi;
	}
	public String getMatinh() {
		return matinh;
	}
	public void setMatinh(String matinh) {
		this.matinh = matinh;
	}
	public String getTentinh() {
		return tentinh;
	}
	public void setTentinh(String tentinh) {
		this.tentinh = tentinh;
	}
	public String getMahuyen() {
		return mahuyen;
	}
	public void setMahuyen(String mahuyen) {
		this.mahuyen = mahuyen;
	}
	public String getTenhuyen() {
		return tenhuyen;
	}
	public void setTenhuyen(String tenhuyen) {
		this.tenhuyen = tenhuyen;
	}
	public String getMaxa() {
		return maxa;
	}
	public void setMaxa(String maxa) {
		this.maxa = maxa;
	}
	public String getTenxa() {
		return tenxa;
	}
	public void setTenxa(String tenxa) {
		this.tenxa = tenxa;
	}
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	public String getManghenghiep() {
		return manghenghiep;
	}
	public void setManghenghiep(String manghenghiep) {
		this.manghenghiep = manghenghiep;
	}
	public String getTennghenghiep() {
		return tennghenghiep;
	}
	public void setTennghenghiep(String tennghenghiep) {
		this.tennghenghiep = tennghenghiep;
	}
	public String getMadantoc() {
		return madantoc;
	}
	public void setMadantoc(String madantoc) {
		this.madantoc = madantoc;
	}
	public String getTendantoc() {
		return tendantoc;
	}
	public void setTendantoc(String tendantoc) {
		this.tendantoc = tendantoc;
	}
	public String getMadoituong() {
		return madoituong;
	}
	public void setMadoituong(String madoituong) {
		this.madoituong = madoituong;
	}
	public String getTendoituong() {
		return tendoituong;
	}
	public void setTendoituong(String tendoituong) {
		this.tendoituong = tendoituong;
	}
	public String getMadoituongBHYT() {
		return madoituongBHYT;
	}
	public void setMadoituongBHYT(String madoituongBHYT) {
		this.madoituongBHYT = madoituongBHYT;
	}
	public String getTendoituongBHYT() {
		return tendoituongBHYT;
	}
	public void setTendoituongBHYT(String tendoituongBHYT) {
		this.tendoituongBHYT = tendoituongBHYT;
	}
	public String getMaloaitainan() {
		return maloaitainan;
	}
	public void setMaloaitainan(String maloaitainan) {
		this.maloaitainan = maloaitainan;
	}
	public String getTenloaitainan() {
		return tenloaitainan;
	}
	public void setTenloaitainan(String tenloaitainan) {
		this.tenloaitainan = tenloaitainan;
	}
	public String getMabvchuyen() {
		return mabvchuyen;
	}
	public void setMabvchuyen(String mabvchuyen) {
		this.mabvchuyen = mabvchuyen;
	}
	public String getTenbvchuyen() {
		return tenbvchuyen;
	}
	public void setTenbvchuyen(String tenbvchuyen) {
		this.tenbvchuyen = tenbvchuyen;
	}
	public String getNgayvaovien() {
		return ngayvaovien;
	}
	public void setNgayvaovien(String ngayvaovien) {
		this.ngayvaovien = ngayvaovien;
	}
	public String getMachandoanbd() {
		return machandoanbd;
	}
	public void setMachandoanbd(String machandoanbd) {
		this.machandoanbd = machandoanbd;
	}
	public String getTenchandoanbd() {
		return tenchandoanbd;
	}
	public void setTenchandoanbd(String tenchandoanbd) {
		this.tenchandoanbd = tenchandoanbd;
	}
	public String getMakhoadieutri() {
		return makhoadieutri;
	}
	public void setMakhoadieutri(String makhoadieutri) {
		this.makhoadieutri = makhoadieutri;
	}
	public String getTenkhoadieutri() {
		return tenkhoadieutri;
	}
	public void setTenkhoadieutri(String tenkhoadieutri) {
		this.tenkhoadieutri = tenkhoadieutri;
	}
	public String getMachandoanicd10() {
		return machandoanicd10;
	}
	public void setMachandoanicd10(String machandoanicd10) {
		this.machandoanicd10 = machandoanicd10;
	}
	public String getTenchandoanicd10() {
		return tenchandoanicd10;
	}
	public void setTenchandoanicd10(String tenchandoanicd10) {
		this.tenchandoanicd10 = tenchandoanicd10;
	}

	public String getMaketquadieutri() {
		return maketquadieutri;
	}
	public void setMaketquadieutri(String maketquadieutri) {
		this.maketquadieutri = maketquadieutri;
	}
	public String getTenketquadieutri() {
		return tenketquadieutri;
	}
	public void setTenketquadieutri(String tenketquadieutri) {
		this.tenketquadieutri = tenketquadieutri;
	}
	public String getNgayravien() {
		return ngayravien;
	}
	public void setNgayravien(String ngayravien) {
		this.ngayravien = ngayravien;
	}
	


}

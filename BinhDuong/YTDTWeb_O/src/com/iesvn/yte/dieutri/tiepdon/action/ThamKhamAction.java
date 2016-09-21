package com.iesvn.yte.dieutri.tiepdon.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.faces.FacesMessages;

import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.DtDmBanKham;
import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.entity.DmBenhIcd;
import com.iesvn.yte.entity.DmDanToc;
import com.iesvn.yte.entity.DmDieuTri;
import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.entity.DmHuyen;
import com.iesvn.yte.entity.DmKetQuaDieuTri;
import com.iesvn.yte.entity.DmNgheNghiep;
import com.iesvn.yte.entity.DmTinh;
import com.iesvn.yte.entity.DmXa;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B121_1_Thamkham")
@Synchronized(timeout = 6000000)
public class ThamKhamAction implements Serializable {

	private static String FORMAT_DATE = "dd/MM/yyyy";
	private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";

	private static final long serialVersionUID = 10L;
	private int stt;
	private String ngaySinh;
	private String thoiGian;
	private String gioThamKham;
	private String diengiai;
	private static Logger log = Logger.getLogger(ThamKhamAction.class);
	
	@In(required = false)
	@Out(required = false)
	private String maBanKhamOut;
	
	@In(required = false)
	@Out(required = false)
	private String maTiepDonOut;
	
	@In(required = false)
	@Out(required = false)
	private String goToThamKham;
	
	
	
	//@In(required = false)
	//@Out(required = false)
	private BenhNhan benhNhan;
	
	//@In(required = false)
	private ThamKham thamkham;


	private boolean updateNhapct = false;

	private String loai = "";
	private String maSo = "";
	private String maKhoa = "";
	private Boolean mien = new Boolean(false);
	private Boolean ngoaiDanhMuc = new Boolean(false);
	private Boolean yeuCau = new Boolean(false);
	private Boolean kyThuatCao = new Boolean(false);
	
	private String tenCLS = "";

	private short soLuong = 0;
	private Double donGia = new Double(0);
	private DmDoiTuong doiTuong = null;
	
//	private ThamKhamWS thamKhamWS = null;
	
	public void resetValue() {

		loai = "";
		maSo = "";
		maKhoa = "";
		mien = new Boolean(false);
		ngoaiDanhMuc = new Boolean(false);
		yeuCau = new Boolean(false);
		kyThuatCao = new Boolean(false);
		soLuong = 0;
		donGia = new Double(0);
		tenCLS = "";

	}
	private String resultHidden ="";
	
	public void CopyFrom(ClsKham cls) {

		loai = cls.getClskhamLoai();
		maSo = cls.getClskhamMahang().getDtdmclsbgMa();
		maKhoa = cls.getClskhamKhoa().getDmkhoaMa();
		mien = cls.getClskhamMien();
		ngoaiDanhMuc = cls.getClskhamNdm();
		yeuCau = cls.getClskhamYeucau();
		kyThuatCao = cls.getClskhamKtcao();
		soLuong = cls.getClskhamLan();
		donGia = cls.getClskhamDongia();
	
	}

	public void CopyTo(ClsKham cls) {

		cls.setClskhamLoai(loai);
		cls.getClskhamMahang().setDtdmclsbgMa(maSo);
		cls.getClskhamKhoa().setDmkhoaMa(maKhoa);
		cls.setClskhamMien(mien);
		cls.setClskhamNdm(ngoaiDanhMuc);
		cls.setClskhamYeucau(yeuCau);
		cls.setClskhamKtcao(kyThuatCao);
		cls.setClskhamLan(soLuong);
		cls.setClskhamDongia(donGia);
		
		System.out.println("tenCLS:"+tenCLS);
		
		cls.getClskhamMahang().setDtdmclsbgDiengiai(tenCLS);
	}
	
	
	@Begin(nested = true)
	@Factory("goToThamKham")
	public void init() throws Exception {
		log.info("***Starting init ***");		
		try{
			initService();
			
			log.info("maBanKhamOut:"+maBanKhamOut);
			log.info("maTiepDonOut:"+maTiepDonOut);
			
			ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
			
			thamkham = tkDelegate.findByBanKhamVaMaTiepDon(maBanKhamOut,maTiepDonOut);
			benhNhan = thamkham.getTiepdonMa().getBenhnhanMa();
			doiTuong = thamkham.getTiepdonMa().getDoituongMa();
			log.info("thamkham.getTiepdonMa().getDoituongMa().getDmdoituongTen() = " + thamkham.getTiepdonMa().getDoituongMa().getDmdoituongTen());
			log.info("thamkham.getTiepdonMa().getDoituongMa().getDmdoituongMa() = " + thamkham.getTiepdonMa().getDoituongMa().getDmdoituongMa());
			log.info("benhNhan.getBenhnhanMa():" + benhNhan.getBenhnhanMa());
			log.info("benhNhan.getBenhnhanNgaysinh():" + benhNhan.getBenhnhanNgaysinh());
			
			log.info("thamkham.getThamkhamBankham().getDtdmbankhamMa():" + thamkham.getThamkhamBankham().getDtdmbankhamMa());
			log.info("thamkham.getThamkhamBankham().getDtdmbankhamTen():" + thamkham.getThamkhamBankham().getDtdmbankhamTen());
			log.info("thamkham.getThamkhamBankham().getThamkhamNgaygio():" + thamkham.getThamkhamNgaygio());
	
			setOtherValue();
			
			destroyService();
		}catch(Exception e){
			log.info("***init Exception = " + e);
		}
		log.info("***Finished init ***");		
	}

	@End 
	public void end(){
		
	}
	//Ham khoi tao cac web service
	public void initService() {
//		try {
////			log.debug("===== begin initService() method");
////			ThamKhamWSService thamKhamService = new ThamKhamWSServiceLocator();
////			thamKhamWS = thamKhamService.getThamKhamWSPort();
////			log.debug("***** End initService() method");
//		} catch (Exception ex) {
//			log.debug("*****initService Exception: " + ex);
//		}
	}	
	
	//***********************************************************************************
	// Ham ghi nhan
	//Xu ly cho nut ghi nhan 
	public String ghiNhanAjax() throws Exception {
		log.info("*****Begin ghiNhanAjax() *****");
		
		try {
//			ThamKhamWSService thamKhamService = new ThamKhamWSServiceLocator();
//			ThamKhamWS thamKhamWS = thamKhamService.getThamKhamWSPort();
//			
			ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
			if(thamkham != null && thamkham.getThamkhamMa() != null){
				ThamKham thamKhamEdit = tkDelegate.find(thamkham.getThamkhamMa());
				thamKhamEdit.setThamkhamBenhsu(thamkham.getThamkhamBenhsu());
				thamKhamEdit.setThamkhamThamkham(thamkham.getThamkhamThamkham());
				thamKhamEdit.setThamkhamXutri(thamkham.getThamkhamXutri());
				log.info("*****thamKhamWS.edit(thamKhamEdit) *****");
				tkDelegate.edit(thamKhamEdit);
				FacesMessages.instance().add(IConstantsRes.SUCCESS);
			}
			else{
				FacesMessages.instance().add(IConstantsRes.FAIL);
			}
//			thamKhamWS = null;
//			thamKhamService = null;
		} catch (Exception e) {
			FacesMessages.instance().add(IConstantsRes.FAIL);
			log.error("*************loi***********" + e.toString());
			return null;
			
		}

		
		log.info("*****End ghiNhanAjax() *****");
		return quayLai();
	}
	
	//Xu ly cho nut quay lai
	public String quayLai()  throws Exception {
		log.info("*****Begin quayLai() *****");
		goToThamKham = null;
		log.info("*****End quayLai() *****");
		return "ghinhan";
	}
	
	//***********************************************************************************
	public String getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(String thoiGian) {
		this.thoiGian = thoiGian;
	}

	private void setOtherValue() {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
		if (thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh() != null	&& !thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().equals("")) {
			ngaySinh = formatter.format(thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().getTime());
		}
		else if(thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNamsinh()!=null)
			ngaySinh = thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNamsinh();
		
		if (thamkham.getThamkhamNgaygio() != null
				&& !thamkham.getThamkhamNgaygio().equals("")) {
			thoiGian = formatter
					.format(thamkham.getThamkhamNgaygio().getTime());
		}
		if(thamkham.getThamkhamNgaygio() != null){
			gioThamKham = Utils.getGioPhut(thamkham.getThamkhamNgaygio()) ;
		}
	}

	private void setinfor(ClsKham nhapctSelected) {
		// SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
		DtDmClsBangGia dmkythuat = nhapctSelected.getClskhamMahang();
		//dmkythuat.setDtdmkythuatDiengiai(diengiai);
		nhapctSelected.setClskhamMahang(dmkythuat);
//		if (thamkham.getTiepdonMa() != null) {
//			nhapctSelected.setTiepdonMa(thamkham.getTiepdonMa());
//		}
		if (thamkham.getThamkhamNgaygio() != null
				&& !"".equals(thamkham.getThamkhamNgaygio())) {
			nhapctSelected.setClskhamNgaygio(thamkham.getThamkhamNgaygio());
		}

//		if (thamkham.getThamkhamBankham() != null) {
//			nhapctSelected.setClskhamBankham(thamkham.getThamkhamBankham());
//		}
	}

	public void setFieldIesVnIfNull(Object obj) throws Exception {

		java.lang.reflect.Field[] fList = obj.getClass().getDeclaredFields();
		for (java.lang.reflect.Field field : fList) {
			Class c = field.getType();
			// System.out.println(c.getCanonicalName());
			if (c.getCanonicalName().startsWith("com.iesvn")) {
				Constructor ccc = Class.forName(c.getCanonicalName())
						.getConstructors()[0];
				// System.out.println(ccc);
				field.set(obj, ccc.newInstance());
			}
		}
	}

	public void displayInfor() throws Exception {
		try {

		} catch (Exception e) {
			System.out.println("error on function displayInfor" + e);
		}
	}

	private void setAllNullForClsKham(ClsKham cls) {
		log.info("**********Begin setAllNullForClsKham()***********");
		if ("".equals(Utils.reFactorString(cls.getClskhamMahang()
				.getDtdmclsbgMa()))) {
			cls.setClskhamMahang(null);
		}
		if ("".equals(Utils.reFactorString(cls.getClskhamKhoa().getDmkhoaMa()))) {
			cls.setClskhamKhoa(null);
		}
		// if (
		// "".equals(Utils.reFactorString(cls.getClskhamBankham().getDtdmbankhamMa()))
		// ){
		// cls.setClskhamBankham(null);
		// }
		if ("".equals(Utils.reFactorString(cls.getClskhamChedott()
				.getDmdoituongMa()))) {
			cls.setClskhamChedott(null);
		}
		if (""
				.equals(Utils.reFactorString(cls.getClskhamKhoa2()
						.getDmkhoaMa()))) {
			cls.setClskhamKhoa2(null);
		}
		if ("".equals(Utils.reFactorString(cls.getClskhamMabs()
				.getDtdmnhanvienMa()))) {
			cls.setClskhamMabs(null);
		}
		if ("".equals(Utils.reFactorString(cls.getClskhamMaloai()
				.getDtdmclsMa()))) {
			cls.setClskhamMaloai(null);
		}
		if ("".equals(Utils.reFactorString(cls.getClskhamNhanviencn()
				.getDtdmnhanvienMa()))) {
			cls.setClskhamNhanviencn(null);
		}
		if ("".equals(Utils.reFactorString(cls.getClskhamThuchien()
				.getDtdmnhanvienMa()))) {
			cls.setClskhamThuchien(null);
		}
		if ("".equals(Utils.reFactorString(cls.getClskhamThungan()
				.getDtdmnhanvienMa()))) {
			cls.setClskhamThungan(null);
		}
		log.info("**********End setAllNullForClsKham()***********");
	}

	

	private void setInforIfNullForThamKham() {
		if (thamkham.getThamkhamBankham() == null) {
			thamkham.setThamkhamBankham(new DtDmBanKham());
		}
		if (thamkham.getThamkhamBacsi() == null) {
			thamkham.setThamkhamBacsi(new DtDmNhanVien());
		}
		if (thamkham.getBenhicd10() == null) {
			thamkham.setBenhicd10(new DmBenhIcd());
		}
		if (thamkham.getThamkhamKetqua() == null) {
		thamkham.setThamkhamKetqua(new DmKetQuaDieuTri());
		}
		if (thamkham.getThamkhamDieutri() == null) {
			thamkham.setThamkhamDieutri(new DmDieuTri());
		}
		if (thamkham.getThamkhamChbankham() == null) {
		thamkham.setThamkhamChbankham(new DtDmBanKham());
		}
	}

	private void setInforIfNullForBN() {
		// System.out.println("benhNhan.getTinhMa():"+benhNhan.getTinhMa());
		if (benhNhan.getTinhMa() == null) {
			benhNhan.setTinhMa(new DmTinh());
		}

		if (benhNhan.getHuyenMa() == null) {
			benhNhan.setHuyenMa(new DmHuyen());
		}
		if (benhNhan.getXaMa() == null) {
			benhNhan.setXaMa(new DmXa());
		}
		if (benhNhan.getBenhnhanNghe() == null) {
			benhNhan.setBenhnhanNghe(new DmNgheNghiep());
		}
		if (benhNhan.getDantocMa() == null) {
			benhNhan.setDantocMa(new DmDanToc());
		}
	}

	// public void removeAllNullFromBN(){
	// if ( "".equals(Utils.reFactorString(benhNhan.getTinhMa().getDmtinhMa()))
	// ){
	// benhNhan.setTinhMa(null);
	// }
	// if (
	// "".equals(Utils.reFactorString(benhNhan.getHuyenMa().getDmhuyenMa())) ){
	// benhNhan.setHuyenMa(null);
	// }
	// if ( "".equals(Utils.reFactorString(benhNhan.getXaMa().getDmxaMa())) ){
	//	
	// benhNhan.setXaMa(null);
	// }
	// if (
	// "".equals(Utils.reFactorString(benhNhan.getBenhnhanNghe().getDmngheMa()))
	// ){
	//		
	// benhNhan.setBenhnhanNghe(null);
	// }
	// if (
	// "".equals(Utils.reFactorString(benhNhan.getDantocMa().getDmdantocMaso()))
	// ){
	//		
	// benhNhan.setDantocMa(null);
	// }
	// }

///////////////////////	
///////////////////
	//Ham huy cac service da khoi tao
	public void destroyService() {
		try {
			log.debug("===== begin destroyService() method");			
//			thamKhamWS = null;
			log.debug("***** End destroyService() method");
		} catch (Exception ex) {
			log.debug("*****destroyService Exception: " + ex);
		}
	}	
	
	
	//Ham  se duoc goi khi het session (session timeout cau hinh trong file web.xml)
	@Destroy 
	public void destroy() {
		log.info("************************* destroy ThamKhamAction");
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

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getStt() {
		return stt;
	}

	public void setStt(int stt) {
		this.stt = stt;
	}

	public  boolean isUpdateNhapct() {
		return updateNhapct;
	}

	public  void setUpdateNhapct(boolean updateNhapct) {
		this.updateNhapct = updateNhapct;
	}

	public ThamKham getThamkham() {
		return thamkham;
	}

	public void setThamkham(ThamKham thamkham) {
		this.thamkham = thamkham;
	}

	public String getDiengiai() {
		return diengiai;
	}

	public void setDiengiai(String diengiai) {
		this.diengiai = diengiai;
	}

	public String getLoai() {
		return loai;
	}

	public void setLoai(String loai) {
		this.loai = loai;
	}

	public String getMaSo() {
		return maSo;
	}

	public void setMaSo(String maSo) {
		this.maSo = maSo;
	}

	public String getMaKhoa() {
		return maKhoa;
	}

	public void setMaKhoa(String maKhoa) {
		this.maKhoa = maKhoa;
	}

	public Boolean getMien() {
		return mien;
	}

	public void setMien(Boolean mien) {
		this.mien = mien;
	}

	public Boolean getNgoaiDanhMuc() {
		return ngoaiDanhMuc;
	}

	public void setNgoaiDanhMuc(Boolean ngoaiDanhMuc) {
		this.ngoaiDanhMuc = ngoaiDanhMuc;
	}

	public Boolean getYeuCau() {
		return yeuCau;
	}

	public void setYeuCau(Boolean yeuCau) {
		this.yeuCau = yeuCau;
	}

	public Boolean getKyThuatCao() {
		return kyThuatCao;
	}

	public void setKyThuatCao(Boolean kyThuatCao) {
		this.kyThuatCao = kyThuatCao;
	}

	public short getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(short soLuong) {
		this.soLuong = soLuong;
	}

	public Double getDonGia() {
		return donGia;
	}

	public void setDonGia(Double donGia) {
		this.donGia = donGia;
	}

	public String getResultHidden() {
		return resultHidden;
	}

	public void setResultHidden(String resultHidden) {
		this.resultHidden = resultHidden;
	}

	public String getTenCLS() {
		return tenCLS;
	}

	public void setTenCLS(String tenCLS) {
		this.tenCLS = tenCLS;
	}

	public String getGioThamKham() {
		return gioThamKham;
	}

	public void setGioThamKham(String gioThamKham) {
		this.gioThamKham = gioThamKham;
	}

	public DmDoiTuong getDoiTuong() {
		return doiTuong;
	}

	public void setDoiTuong(DmDoiTuong doiTuong) {
		this.doiTuong = doiTuong;
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

	public String getGoToThamKham() {
		return goToThamKham;
	}

	public void setGoToThamKham(String goToThamKham) {
		this.goToThamKham = goToThamKham;
	}

}



package com.iesvn.yte.dieutri.tiepdon.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.util.List;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.DtDmTuyenKcb;
import com.iesvn.yte.dieutri.entity.HsThtoank;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.util.HoSoThanhToanKhamUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B138_Chuyensolieuvaonoitru")
@Synchronized(timeout = 6000000)
public class ChuyenSoLieuVaoNoiTruAction implements Serializable {

	private static final long serialVersionUID = 10L;
	
	@Logger
	private Log log;

	private String matinh;
	private String tentinh;
	private String mahuyen;
	private String tenhuyen;
	private String maxa;
	private String tenxa;
	private String diachi;
	
	private String ngay;
	
	private String gioitinh;
	
	private String hoten;
	private String tuoi;
	private String matiepdon;
	
	private String madantoc;
	private String tendantoc;
	
	private String sothebhyt;
	private String tuyenbh;
	
	private String makhoa;
	private Integer masokhoa;
	private String sobenhan;
	
	private String makcbbhyt;
	private String tenkcbbhyt;
	
	
	private TiepDon tiepdon;
	private Hsba hsba;
	
	
	public Log getLog() {
		return log;
	}
	public void setLog(Log log) {
		this.log = log;
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
	public String getGioitinh() {
		return gioitinh;
	}
	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
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
	public String getMatiepdon() {
		return matiepdon;
	}
	public void setMatiepdon(String matiepdon) {
		this.matiepdon = matiepdon;
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
	public String getSothebhyt() {
		return sothebhyt;
	}
	public void setSothebhyt(String sothebhyt) {
		this.sothebhyt = sothebhyt;
	}
	public String getTuyenbh() {
		return tuyenbh;
	}
	public void setTuyenbh(String tuyenbh) {
		this.tuyenbh = tuyenbh;
	}
	public String getMakhoa() {
		return makhoa;
	}
	public void setMakhoa(String makhoa) {
		this.makhoa = makhoa;
	}
	public Integer getMasokhoa() {
		return masokhoa;
	}
	public void setMasokhoa(Integer masokhoa) {
		this.masokhoa = masokhoa;
	}
	public String getSobenhan() {
		return sobenhan;
	}
	public void setSobenhan(String sobenhan) {
		this.sobenhan = sobenhan;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	@Begin(join = true)
	public String init(){
		log.info("begin init");
		
		resetValue();
		log.info("end init");
		return "TiepDon_TiepDonKhamBenhCapCuu_ChuyenSoLieuVaoNoiTru";
	}
	public void resetValue(){
		ngay = Utils.getCurrentDate();
		
		
		matinh= null;
		tentinh= null;
		mahuyen= null;
		tenhuyen= null;
		maxa= null;
		tenxa= null;
		diachi= null;
		
		
		
		gioitinh= null;
		
		hoten= null;
		tuoi= null;
		matiepdon= null;
		
		madantoc= null;
		tendantoc= null;
		
		sothebhyt= null;
		tuyenbh= null;
		
		makhoa= null;
		masokhoa= null;
		sobenhan= null;
		
		makcbbhyt= null;
		tenkcbbhyt= null;
		
		
		tiepdon= null;
		hsba= null;
		
	}

	
	
	public void displayInfor() throws Exception {
		log.info("---displayInfor()-----");
		if(matiepdon != null && !matiepdon.equals(""))
		{
			TiepDonDelegate tdDelegate=TiepDonDelegate.getInstance();
			TiepDon td_Tmp = tdDelegate.find(matiepdon);
			if (td_Tmp != null) 
			{
				tiepdon = td_Tmp;
				
				matiepdon = tiepdon.getTiepdonMa();
				
				BenhNhan benhnhan = tiepdon.getBenhnhanMa();
				
				hoten = benhnhan.getBenhnhanHoten();
				gioitinh = benhnhan.getDmgtMaso(true).getDmgtTen();
				tuoi = benhnhan.getBenhnhanTuoi() + " " + (benhnhan.getBenhnhanDonvituoi() == 2?IConstantsRes.THANG: (benhnhan.getBenhnhanDonvituoi() == 3?IConstantsRes.NGAY:""));
				
				matinh = benhnhan.getTinhMa(true).getDmtinhMa();
				tentinh = benhnhan.getTinhMa(true).getDmtinhTen();
				
				mahuyen = benhnhan.getHuyenMa(true).getDmhuyenMa();
				tenhuyen = benhnhan.getHuyenMa(true).getDmhuyenTen();
				
				maxa = benhnhan.getXaMa(true).getDmxaMa();
				tenxa = benhnhan.getXaMa(true).getDmxaTen();
				
				diachi = benhnhan.getBenhnhanDiachi();
				
				madantoc = benhnhan.getDantocMa(true).getDmdantocMa();
				tendantoc = benhnhan.getDantocMa(true).getDmdantocTen();
				
				sothebhyt = tiepdon.getTiepdonSothebh();
				
				makcbbhyt = tiepdon.getKcbbhytMa(true).getDmbenhvienMa();
				tenkcbbhyt = tiepdon.getKcbbhytMa(true).getDmbenhvienTen();
				
				if (tiepdon.getTiepdonTuyen() != null){
					
				}
				DieuTriUtilDelegate dtUtilDelegate = DieuTriUtilDelegate.getInstance();
				DtDmTuyenKcb dtDmTuyenKcb = (DtDmTuyenKcb)dtUtilDelegate.findByMa ( String.valueOf( tiepdon.getTiepdonTuyen()), "DtDmTuyenKcb", "dtdmtuyenkcbMa");
				if (dtDmTuyenKcb != null){
					tuyenbh = dtDmTuyenKcb.getDtdmtuyenkcbTen();
				}
				
				
				HsbaDelegate hsbaDele = HsbaDelegate.getInstance();
				hsba = hsbaDele.findByTiepDonMa(matiepdon);
				if (hsba != null){
					sobenhan = hsba.getHsbaSovaovien();
					DmKhoa khoa = hsba.getHsbaKhoavaov();
					if(khoa != null){
						makhoa = khoa.getDmkhoaMa();
						masokhoa = khoa.getDmkhoaMaso();
					}
				}
			}
			else
			{
				FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + matiepdon);
				resetValue();
			}
			
		}
		
		
		/*if (matiepdon != null && !matiepdon.equals("")){
			
			TiepDonDelegate tdDelegate = TiepDonDelegate.getInstance();
			TiepDon td_Tmp = tdDelegate.find(matiepdon);
			if (td_Tmp == null){
				return;
			}
			tiepdon = td_Tmp;
			
			matiepdon = tiepdon.getTiepdonMa();
			
			BenhNhan benhnhan = tiepdon.getBenhnhanMa();
			
			hoten = benhnhan.getBenhnhanHoten();
			gioitinh = benhnhan.getDmgtMaso(true).getDmgtTen();
			tuoi = benhnhan.getBenhnhanTuoi() + (benhnhan.getBenhnhanDonvituoi() == 2?IConstantsRes.THANG: (benhnhan.getBenhnhanDonvituoi() == 3?IConstantsRes.NGAY:""));
			
			matinh = benhnhan.getTinhMa(true).getDmtinhMa();
			tentinh = benhnhan.getTinhMa(true).getDmtinhTen();
			
			mahuyen = benhnhan.getHuyenMa(true).getDmhuyenMa();
			tenhuyen = benhnhan.getHuyenMa(true).getDmhuyenTen();
			
			maxa = benhnhan.getXaMa(true).getDmxaMa();
			tenxa = benhnhan.getXaMa(true).getDmxaTen();
			
			diachi = benhnhan.getBenhnhanDiachi();
			
			madantoc = benhnhan.getDantocMa(true).getDmdantocMa();
			tendantoc = benhnhan.getDantocMa(true).getDmdantocTen();
			
			sothebhyt = tiepdon.getTiepdonSothebh();
			
			makcbbhyt = tiepdon.getKcbbhytMa(true).getDtdmkcbbhytMa();
			tenkcbbhyt = tiepdon.getKcbbhytMa(true).getDtdmkcbbhytTen();
			
			if (tiepdon.getTiepdonTuyen() != null){
				
			}
			DieuTriUtilDelegate dtUtilDelegate = DieuTriUtilDelegate.getInstance();
			DtDmTuyenKcb dtDmTuyenKcb = (DtDmTuyenKcb)dtUtilDelegate.findByMa ( String.valueOf( tiepdon.getTiepdonTuyen()), "DtDmTuyenKcb", "dtdmtuyenkcbMa");
			if (dtDmTuyenKcb != null){
				tuyenbh = dtDmTuyenKcb.getDtdmtuyenkcbTen();
			}
			
			
			HsbaDelegate hsbaDele = HsbaDelegate.getInstance();
			hsba = hsbaDele.findByTiepDonMa(matiepdon);
			if (hsba != null){
				sobenhan = hsba.getHsbaSovaovien();
				DmKhoa khoa = hsba.getHsbaKhoavaov();
				if(khoa != null){
					makhoa = khoa.getDmkhoaMa();
					masokhoa = khoa.getDmkhoaMaso();
				}
			}
			
		}*/
	}
	

	@End 
	public void end(){
		
	}
	
	


	// Ham ghi nhan
	public String ghinhan() throws Exception {
		try{
			if (tiepdon == null){
				return "";
			}
			HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
			HsThtoank hsttk = hsttkDelegate.findBytiepdonMa(tiepdon.getTiepdonMa());
			ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
			ThamKham tk = tkDelegate.findByMaTiepDon(tiepdon.getTiepdonMa());
			
			if (tk.getThamkhamDieutri() == null) { //Neu ben thamkhamvaXutri chua chon huong xu ly thi thong bao
				FacesMessages.instance().add(IConstantsRes.CHUACHON_HXL);
				return "";
			}
			String maHuongDieuTri = tk.getThamkhamDieutri().getDmdieutriMa();
			
			if (!maHuongDieuTri.equals("V")) { //Neu ben thamkhamvaXutri khong chon huong xu ly la dieu tri noi tru thi thong bao
				FacesMessages.instance().add(IConstantsRes.KHONGPHAI_HXL_DTNT);
				return "";
			}
			if (hsttk != null){
				FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA);
				return "";
			}
			tk.setThamkhamChuyenSoLieuVaoNoiTru(true); //khi chuyen so lieu vao noi tru can phai cap nhat lai truong chuyensolieuvaonoitru ben thamkham
			
			tkDelegate.edit(tk);
			hsttk = new HsThtoank();
			hsttk.setTiepdonMa(tiepdon);			
			tinhToanChoHSTKKham(hsttk);
			Utils.setInforForHsThToan(hsttk);
			
			//String maPhieuTTK = hsttkDelegate.capNhatTTHsttk(hsttk,clslist,listCtTPK_temp);
			log.info("---"+makhoa+"--");
			//String ketqua = tkDelegate.chuyenSoLieuVaoNoiTru(hsttk,matiepdon, makhoa, sobenhan);
			
			FacesMessages.instance().add(IConstantsRes.SUCCESS);
			//sobenhan = ketqua;
			
			
		} catch (Exception e) {
			FacesMessages.instance().add(IConstantsRes.FAIL);
			
			// TODO: handle exception
			e.printStackTrace();
			log.error("*************loi***********" + e.toString());
		}

		
		log.info("*****End Ghi nhan() *****");
		
		return null;
	}
	
	private List<ThuocPhongKham> listCtTPK_temp = null; // day la thuoc tai ban kham, khi thanh toan kham -> tinh luon
	private List<ClsKham> clslist = null;
	private void tinhToanChoHSTKKham(HsThtoank hsttk){
		HoSoThanhToanKhamUtil hsthtoankUtilTmp = new HoSoThanhToanKhamUtil(tiepdon);
		hsthtoankUtilTmp.tinhToanChoHSTKKham(hsttk);
		Utils.setInforForHsThToan(hsttk);	
		
		
		listCtTPK_temp = hsthtoankUtilTmp.getListCtTPK_temp(); // thuoc phong kham tai ban kham			
		clslist = hsthtoankUtilTmp.getListCtkq_temp();
		
		
		
	}
	
	public String getNgay() {
		return ngay;
	}
	public void setNgay(String ngay) {
		this.ngay = ngay;
	}
	public String getMakcbbhyt() {
		return makcbbhyt;
	}
	public void setMakcbbhyt(String makcbbhyt) {
		this.makcbbhyt = makcbbhyt;
	}
	public String getTenkcbbhyt() {
		return tenkcbbhyt;
	}
	public void setTenkcbbhyt(String tenkcbbhyt) {
		this.tenkcbbhyt = tenkcbbhyt;
	}
	public TiepDon getTiepdon() {
		return tiepdon;
	}
	public void setTiepdon(TiepDon tiepdon) {
		this.tiepdon = tiepdon;
	}
	public Hsba getHsba() {
		return hsba;
	}
	public void setHsba(Hsba hsba) {
		this.hsba = hsba;
	}




}



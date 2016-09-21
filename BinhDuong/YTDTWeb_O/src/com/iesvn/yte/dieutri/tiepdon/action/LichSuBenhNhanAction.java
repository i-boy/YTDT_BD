package com.iesvn.yte.dieutri.tiepdon.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;

import com.iesvn.yte.dieutri.delegate.BenhNhanDelegate;
import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmBanKhamDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.DtDmBanKham;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.setinfor.SetInforUtil;

@Scope(SESSION)
@Name("B115_Lichsubenhnhan")
@Synchronized(timeout = 6000000)
public class LichSuBenhNhanAction implements Serializable{

	private static final long serialVersionUID = -2423255254960254349L;
	private static Logger log = Logger.getLogger(LichSuBenhNhanAction.class);
	private BenhNhan benhNhan;
	private TiepDon tiepDon;
	@DataModel
	private List<TiepDon> listOfMaTiepDon;
	
	@DataModelSelection("listOfMaTiepDon")
	private TiepDon tiepDonSelection;
	
	@DataModel
	private java.util.ArrayList<ClsKham> listClsKham;
	
	@DataModelSelection("listClsKham")
	private ClsKham clsKhamSelection;
	
	@DataModel
	private List<ToaThuoc> listOfToaThuoc;
	
	@DataModelSelection("listOfToaThuoc")
	private ToaThuoc toaThuocSelection;
	
	private Boolean giaTriThucHienCLS;
	
	@In(required=false)
	@Out(required=false)
	private String benhNhan_ma;
	
	@In(required=false)
	@Out(required=false)
	private String tiepDon_ma;
	
	public String getBenhNhan_ma() {
		return benhNhan_ma;
	}
	public void setBenhNhan_ma(String benhNhanMa) {
		benhNhan_ma = benhNhanMa;
	}
	public String getTiepDon_ma() {
		return tiepDon_ma;
	}
	public void setTiepDon_ma(String tiepDonMa) {
		tiepDon_ma = tiepDonMa;
	}
	@Create
	@Begin(join = true)
	public String init() {
		resetValue();
		return "TiepDon_TiepDonBenhNhan_LichSuBenhNhan";
	}
	@End
	public void end(){
		
	}

	public void resetValue() {
		log.info("---resetValue");
		benhNhan = new BenhNhan();
		SetInforUtil.setInforIfNullForBN(benhNhan);
		tiepDon = new TiepDon();
		listOfMaTiepDon = new ArrayList<TiepDon>();
		listClsKham = new ArrayList<ClsKham>();
		listOfToaThuoc = new ArrayList<ToaThuoc>();
		SetInforUtil.setInforIfNullForTiepDon(tiepDon);
	}
	
	public void maTiepDonClick(){
		//Xu ly hien danh sach CLS
		String maTiepDon = tiepDonSelection.getTiepdonMa();
		ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();
		java.util.ArrayList<ClsKham> listClsKham_temp = (java.util.ArrayList<ClsKham>)clsKhamDelegate.findByTiepdonma(maTiepDon);
		if (listClsKham_temp != null && listClsKham_temp.size() > 0) {
			listClsKham = listClsKham_temp;
			for (ClsKham cls : listClsKham) {
				com.iesvn.yte.dieutri.setinfor.SetInforUtil.setInforIfNullForClsKham(cls);		
				log.info(cls.getClskhamTh());
			}			
		}
		
		//Xu ly hien danh sach toa thuoc
		listOfToaThuoc = new ArrayList<ToaThuoc>();
		List<DtDmBanKham> listBanKham = new ArrayList<DtDmBanKham>();
		List<DtDmBanKham> listBanKham_Temp = DtDmBanKhamDelegate.getInstance().findByMaTiepDon(maTiepDon);
		if(listBanKham_Temp != null) listBanKham.addAll(listBanKham_Temp);
		for(DtDmBanKham each : listBanKham){
			ToaThuoc toaThuoc = new ToaThuoc();
			toaThuoc.setMaBanKham(each.getDtdmbankhamMa());
			toaThuoc.setMaTiepDon(maTiepDon);
			List<ThuocPhongKham> listTemp = ThuocPhongKhamDelegate.getInstance().findByMaTiepDonAndMaBanKham(maTiepDon, each.getDtdmbankhamMa(), "1");
			if(listTemp != null && listTemp.size() > 0) toaThuoc.setThuocBanKham(true);
			listTemp = ThuocPhongKhamDelegate.getInstance().findByMaTiepDonAndMaBanKham(maTiepDon, each.getDtdmbankhamMa(), "2");
			if(listTemp != null && listTemp.size() > 0) toaThuoc.setToaThuocVe(true);
			listTemp = ThuocPhongKhamDelegate.getInstance().findByMaTiepDonAndMaBanKham(maTiepDon, each.getDtdmbankhamMa(), "3");
			if(listTemp != null && listTemp.size() > 0) toaThuoc.setThuocBHYT(true);
			listOfToaThuoc.add(toaThuoc);
		}
	}
	
	public void refresh_bn(){
		log.info("---refresh_bn");
		if (tiepDon_ma != null && benhNhan_ma !=null) {
			tiepDon.setTiepdonMa(tiepDon_ma);
			benhNhan.setBenhnhanMa(benhNhan_ma);
		}
		listOfMaTiepDon = new ArrayList<TiepDon>();
		listClsKham = new ArrayList<ClsKham>();
		listOfToaThuoc = new ArrayList<ToaThuoc>();
		TiepDon tdtmp = TiepDonDelegate.getInstance().find(tiepDon.getTiepdonMa());
		tiepDon = tdtmp==null?new TiepDon():tdtmp;		
		SetInforUtil.setInforIfNullForTiepDon(tiepDon);
		BenhNhan bntmp = BenhNhanDelegate.getInstance().find(benhNhan.getBenhnhanMa());
		benhNhan = bntmp==null? new BenhNhan():bntmp;
		SetInforUtil.setInforIfNullForBN(benhNhan);
		if(bntmp != null) listOfMaTiepDon.addAll(TiepDonDelegate.getInstance().findFinalByBenhNhan(bntmp.getBenhnhanMa()));
	}
	
	public void clickToaThuocAction(String loai, String maBanKham){
		log.info("---clickToaThuocAction - loai: " + loai + " - MaBanKham: " + maBanKham);
		ToaThuocPopupAction.maTiepDon = toaThuocSelection.maTiepDon;
		ToaThuocPopupAction.hoTenBN = benhNhan.getBenhnhanHoten();
		ToaThuocPopupAction.ctThuocPhongKhamsPO = new ArrayList<ThuocPhongKham>();
		ToaThuocPopupAction.ctThuocPhongKhamsPO.addAll(ThuocPhongKhamDelegate.getInstance().findByMaTiepDonAndMaBanKham(toaThuocSelection.maTiepDon, maBanKham, loai));
	}
	
	public String formatDate(Date date){
		return date==null?"":new SimpleDateFormat("dd/MM/yyyy").format(date); 
	}
	
	public String formatGtBenhNhan(String gioitinh){
		if (gioitinh==null || gioitinh.trim().equals("")){
			return "";
		}
		return gioitinh.equals("1")?"nam":"nu";
	}
	
	public BenhNhan getBenhNhan() {
		return benhNhan;
	}

	public void setBenhNhan(BenhNhan benhNhan) {
		this.benhNhan = benhNhan;
	}

	public TiepDon getTiepDon() {
		return tiepDon;
	}
	
	public TiepDon getTiepDonSelection() {
		return tiepDonSelection;
	}
	public void setTiepDonSelection(TiepDon tiepDonSelection) {
		this.tiepDonSelection = tiepDonSelection;
	}
	public ClsKham getClsKhamSelection() {
		return clsKhamSelection;
	}
	public void setClsKhamSelection(ClsKham clsKhamSelection) {
		this.clsKhamSelection = clsKhamSelection;
	}
	public Boolean getGiaTriThucHienCLS() {
		return giaTriThucHienCLS;
	}
	public void setGiaTriThucHienCLS(Boolean giaTriThucHienCLS) {
		this.giaTriThucHienCLS = giaTriThucHienCLS;
	}
	public void setTiepDon(TiepDon tiepDon) {
		this.tiepDon = tiepDon;
	}
	public List<TiepDon> getListOfMaTiepDon() {
		return listOfMaTiepDon;
	}
	public void setListOfMaTiepDon(List<TiepDon> listOfMaTiepDon) {
		this.listOfMaTiepDon = listOfMaTiepDon;
	}
	public java.util.ArrayList<ClsKham> getListClsKham() {
		return listClsKham;
	}
	public void setListClsKham(java.util.ArrayList<ClsKham> listClsKham) {
		this.listClsKham = listClsKham;
	}
	public List<ToaThuoc> getListOfToaThuoc() {
		return listOfToaThuoc;
	}
	public void setListOfToaThuoc(List<ToaThuoc> listOfToaThuoc) {
		this.listOfToaThuoc = listOfToaThuoc;
	}
	public ToaThuoc getToaThuocSelection() {
		return toaThuocSelection;
	}
	public void setToaThuocSelection(ToaThuoc toaThuocSelection) {
		this.toaThuocSelection = toaThuocSelection;
	}

	public class ToaThuoc{
		private String maBanKham;
		private String maTiepDon;
		private boolean thuocBanKham;
		private boolean thuocBHYT;
		private boolean toaThuocVe;
		
		public ToaThuoc(){}

		public String getMaBanKham() {
			return maBanKham;
		}

		public void setMaBanKham(String maBanKham) {
			this.maBanKham = maBanKham;
		}

		public String getMaTiepDon() {
			return maTiepDon;
		}

		public void setMaTiepDon(String maTiepDon) {
			this.maTiepDon = maTiepDon;
		}

		public boolean isThuocBanKham() {
			return thuocBanKham;
		}

		public void setThuocBanKham(boolean thuocBanKham) {
			this.thuocBanKham = thuocBanKham;
		}

		public boolean isThuocBHYT() {
			return thuocBHYT;
		}

		public void setThuocBHYT(boolean thuocBHYT) {
			this.thuocBHYT = thuocBHYT;
		}

		public boolean isToaThuocVe() {
			return toaThuocVe;
		}

		public void setToaThuocVe(boolean toaThuocVe) {
			this.toaThuocVe = toaThuocVe;
		}
		
		
	}
}

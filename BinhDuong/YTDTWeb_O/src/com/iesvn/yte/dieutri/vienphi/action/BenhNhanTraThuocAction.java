package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.dieutri.delegate.HsbaKhoaDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocNoiTruDelegate;
import com.iesvn.yte.dieutri.delegate.YteLogDelegate;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaKhoa;
import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
import com.iesvn.yte.dieutri.entity.YteLog;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B3112_Benhnhantrathuoc")
@Synchronized(timeout = 6000000)
public class BenhNhanTraThuocAction implements Serializable {
	private static final long serialVersionUID = 10L;
	private static Logger logger = Logger.getLogger(BenhNhanTraThuocAction.class);
	
	private YteLogDelegate yteLogDele = YteLogDelegate.getInstance();
        private YteLog yteLog;
        private String listDataLog;
     
        @In(required = false)
 	@Out(required = false)
 	Identity identity;
	
	@DataModel
	private ArrayList<ThuocNoiTru> listTnt;
	@DataModelSelection
	@Out(required = false)
	private ThuocNoiTru tntSelected;
	private Hsba hsba;
	private String maKhoa;
	private String ngayHienTai;
	private Integer updateItem;
	private Integer count;
	private String tra;
	private String maThuoc;
	private String dg;
	private String sl;
	private String dv;
	private String tenthuoc;
	@Begin(join = true)
	public String init() {
		logger.info("-----init()-----");
		reset();
		return "VienPhiTaiKhoa_SoLieuBNSuDung_BenhNhanTraThuoc";
	}
	
	@End
	public void endFunct(){
		
	}
	
	public void reset() {
		logger.info("-----reset()-----");
		listTnt = new ArrayList<ThuocNoiTru>();
		hsba = new Hsba();
		ngayHienTai = Utils.getCurrentDate();
		updateItem = 0;
		count = listTnt.size();
		tra = "";
		maThuoc = "";
		dg = "";
		sl = "";
		maKhoa = "";
		tenthuoc ="";
		dv="";
	}

	public void tiepTucNhap() {
		logger.info("-----tiepTucNhap()-----");
		if (updateItem.intValue() != -1) {
			ThuocNoiTru tnt = listTnt.get(updateItem.intValue());
			if (tnt.getThuocnoitruTutrucPdt() == 1) {
				//Double soluong = tnt.getThuocnoitruSoluong();
				//tnt.setThuocnoitruSoluong(Double.valueOf(soluong) - Double.valueOf(tra));
				tnt.setThuocnoitruTra(new Double(tra));
			} else if (tnt.getThuocnoitruTutrucPdt() == 0){
				tnt.setThuocnoitruTra(Double.valueOf(tra));
			}
			maThuoc = "";
			dg = "";
			sl = "";
			tra = "";
			tenthuoc ="";
			dv="";
		}
	}
	
	public void end() {
		logger.info("-----end()-----");
		if (listTnt.size() > 0) {
			yteLog = new YteLog();
			listDataLog="";
			ThuocNoiTruDelegate tntDelegate = ThuocNoiTruDelegate.getInstance();
			if (tntDelegate.benhNhanTraThuoc(listTnt)) {
				for (ThuocNoiTru tnt : listTnt) {
					//luu log thong tin thuoc
					listDataLog += "Ma LK:"+ tnt.getThuocnoitruMalk()+
							" Don gia: "+  tnt.getThuocnoitruDongia()+ " Don gia ban: "+ tnt.getThuocnoitruDongiaban() + 
							" So luong: "+ tnt.getThuocnoitruSoluong()+
							" So lo: "+  
							" Nam SX: " + tnt.getThuocnoitruNamnhap()+
							" Nam HD: " + tnt.getThuocnoitruNamhd()+ "\n";		
				}
				

//				Luu log he thong
		         yteLog.setForm("B3112_Benhnhantrathuoc");
		         yteLog.setUserId(identity == null ? "NULL" : identity.getUsername());
		         yteLog.setObjectId(hsba.getHsbaSovaovien());
		         yteLog.setLogString(" Ngay nhan thuoc: "+ ngayHienTai+
		        		 			" Ho ten BN: "+ hsba.getBenhnhanMa(true).getBenhnhanHoten()+
		        		 			" Khoa: " + maKhoa
		         					);
		         yteLog.setDateTime(new Date());
		         yteLog.setListData(listDataLog);

		         yteLogDele.create(yteLog);
				
				FacesMessages.instance().add(IConstantsRes.SUCCESS);
			} else {
				FacesMessages.instance().add(IConstantsRes.FAIL);
			}
		}
	}
	
	public void loadHsba() {
		logger.info("-----loadHsba()-----");
		
		HsbaKhoaDelegate hsbaKhoaDelegate = HsbaKhoaDelegate.getInstance();
		
		// 20120312 bao.ttc: them thong tin Tang de tim kiem hsbaKhoa can tra thuoc chinh xac - Them o giao dien
		HsbaKhoa hsbaKhoa = hsbaKhoaDelegate.findBySoVaoVienAndKhoaMa(hsba.getHsbaSovaovien(),maKhoa);
		//Khong ton tai so vao vien
		if (hsbaKhoa == null) {
			FacesMessages.instance().add(IConstantsRes.HSBA_NULL, hsba.getHsbaSovaovien());				
			reset();
			return;
		}
		hsba = hsbaKhoa.getHsbaSovaovien();	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date ngayNhanThuoc = null;
		ThuocNoiTruDelegate tntDelegate = ThuocNoiTruDelegate.getInstance();
		try {
			if(ngayHienTai != null){
				ngayNhanThuoc = sdf.parse(ngayHienTai);
			}
			
			if (hsba.getHsbaSovaovien() != null) {
				//cho phep lay thuoc tai khoa theo ngay, khong theo lan
				// 20120312 bao.ttc: them thong tin Tang de tim kiem Thuoc can tra chinh xac
				listTnt = (ArrayList<ThuocNoiTru>) tntDelegate.findBySoVaoVienAndKhoaMaAndLanAndNgayNhan(hsba.getHsbaSovaovien(), maKhoa,null, ngayNhanThuoc);
				if (listTnt == null || listTnt.size() == 0) {
					FacesMessages.instance().add(IConstantsRes.THUOC_KHOA_HSBA_NULL, hsba.getHsbaSovaovien(), maKhoa, sdf.format(ngayNhanThuoc));
					reset();
				} else {
					
					hsba = hsbaKhoa.getHsbaSovaovien();
					
					logger.info("-----hsba: " + hsba);
					logger.info("-----listTnt: " + listTnt);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void select(int index) {
		logger.info("-----select(" + index + ")-----");
		updateItem = index;
		ThuocNoiTru tnt = listTnt.get(index);
		System.out.println("Tu truc PDT: " + tnt.getThuocnoitruTutrucPdt());
		if (tnt.getThuocnoitruTutrucPdt() == 0) {
			// tra hang theo pdt
			logger.info("-----tra hang theo pdt");
			if (tnt.getThuocnoitruMaPhieuDT() != null) {
				// da lap phieu dt
				if (tnt.getThuocnoitruMaphieupdttra() == null) {
					if (tnt.getThuocnoitruStatus() == "0") {
						// chua xuat hang theo pdt
						logger.info("-----chua xuat hang theo pdt");
						maThuoc = "";
						dg = "";
						sl = "";
						tra = "";
						tenthuoc ="";
						dv="";
						FacesMessages.instance().add(IConstantsRes.BENHNHANTRATHUOC_PDT_XUAT_NULL);
					} else if (tnt.getThuocnoitruStatus().equals("2") || tnt.getThuocnoitruStatus().equals("3") || tnt.getThuocnoitruStatus().equals("5")) {
						// da xuat hang phieu du tru
						logger.info("-----da xuat hang phieu du tru");
						maThuoc = tnt.getThuocnoitruMathuoc().getDmthuocMa();
						dg = tnt.getThuocnoitruDongia().toString();
						dv = tnt.getThuocnoitruMathuoc().getDmdonvitinhMaso().getDmdonvitinhTen();
						sl = tnt.getThuocnoitruSoluong().toString();
						tra = tnt.getThuocnoitruTra() == null ? "" : tnt.getThuocnoitruTra().toString();
						tenthuoc = tnt.getThuocnoitruMathuoc().getDmthuocTen();
					} 
				}
			} else {
				// chua lap phieu dt
				logger.info("-----chua lap phieu dt");
				FacesMessages.instance().add(IConstantsRes.BENHNHANTRATHUOC_PDT_NULL);
				maThuoc = "";
				dg = "";
				sl = "";
				tra = "";
				tenthuoc ="";
				dv="";
			}
		} else if (tnt.getThuocnoitruTutrucPdt() == 1){
			// tra hang theo tu truc
			logger.info("-----tra hang theo tu truc");
			maThuoc = tnt.getThuocnoitruMathuoc().getDmthuocMa();
			dg = tnt.getThuocnoitruDongia().toString();
			sl = tnt.getThuocnoitruSoluong().toString();
			tra = tnt.getThuocnoitruTra() == null ? "" : tnt.getThuocnoitruTra().toString();
			dv = tnt.getThuocnoitruMathuoc().getDmdonvitinhMaso().getDmdonvitinhTen();
			tenthuoc = tnt.getThuocnoitruMathuoc().getDmthuocTen();
		}
		logger.info("-----maThuoc = " + maThuoc);
		logger.info("-----tenThuoc = " + tenthuoc);
		logger.info("-----donvi = " + dv);
		logger.info("-----dg = " + dg);
		logger.info("-----sl = " + sl);
	}

	public void setHsba(Hsba hsba) {
		this.hsba = hsba;
	}

	public Hsba getHsba() {
		return hsba;
	}

	public void setMaKhoa(String maKhoa) {
		this.maKhoa = maKhoa;
	}

	public String getMaKhoa() {
		return maKhoa;
	}

	public void setListTnt(ArrayList<ThuocNoiTru> listTnt) {
		this.listTnt = listTnt;
	}

	public ArrayList<ThuocNoiTru> getListTnt() {
		return listTnt;
	}

	public void setTntSelected(ThuocNoiTru tntSelected) {
		this.tntSelected = tntSelected;
	}

	public ThuocNoiTru getTntSelected() {
		return tntSelected;
	}

	public void setNgayHienTai(String ngayHienTai) {
		this.ngayHienTai = ngayHienTai;
	}

	public String getNgayHienTai() {
		return ngayHienTai;
	}
	
	public void setUpdateItem(Integer updateItem) {
		this.updateItem = updateItem;
	}

	public Integer getUpdateItem() {
		return updateItem;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getCount() {
		return count;
	}

	public void setTra(String tra) {
		this.tra = tra;
	}

	public String getTra() {
		return tra;
	}

	public String getMaThuoc() {
		return maThuoc;
	}

	public void setMaThuoc(String maThuoc) {
		this.maThuoc = maThuoc;
	}

	public String getDg() {
		return dg;
	}

	public void setDg(String dg) {
		this.dg = dg;
	}

	public String getSl() {
		return sl;
	}

	public void setSl(String sl) {
		this.sl = sl;
	}
	
	public String getDv() {
		return dv;
	}

	public void setDv(String dv) {
		this.dv = dv;
	}
	
	public String getTenthuoc() {
		return tenthuoc;
	}

	public void setTenthuoc(String tenthuoc) {
		this.tenthuoc = tenthuoc;
	}
}

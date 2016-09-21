package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.DtDmCls;
import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
import com.iesvn.yte.dieutri.entity.DtDmClsKhoa;
import com.iesvn.yte.dieutri.entity.DtDmLoaiPhauThuat;
import com.iesvn.yte.entity.DanhSachLoaiClsBangGia;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.util.IConstantsRes;

@Name("DtDmBangGiaCanLamSan_add")
@Scope(SESSION)
public class DmBanggiacanlamsanExt_add implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final int emptyValueCbo = -1;
	private static final String emptyLabelCbo = "...";

	@In(required = false)
	@Out(required = false)
	private DtDmClsBangGia clsbanggia;

	private List<SelectItem> listMaLoai;
	private List<SelectItem> listPhanLoai;
	
	private List<SelectItem> listKhoa;
	private List<SelectItem> listLoaiPTTT;
	
	private Integer dmkhoaMaso;
	private String maLoaiCLS;
	private DanhSachLoaiClsBangGia dsLoaiCLS;
	@Logger
	private Log log;

	@Create
	public String init() {
		log.info("Init DmBanggiacanlamsanExt_add....");
		
		resetValue();
		initComboxMaLoai();
		initComboxPhanLoai();
		initComboxKhoa();
		initComboxLoaiPTTT();
		dsLoaiCLS = new DanhSachLoaiClsBangGia();
		return "/B3_Vienphi/ThuVienPhi/B3253_Banggiacanlamsan_add";
	}

	public void save(int phanLoaiId) {
		log.info("Save DmBanggiacanlamsanExt_add...., maLoaiCLS = " + maLoaiCLS);
		
		String ma = "";
		Date date = new Date();
		
		ma = this.clsbanggia.getDtdmclsbgMa();
		this.clsbanggia.setDtdmclsbgNgaygiocn((double)date.getTime());
		//this.clsbanggia.setDtdmclsbgChon(true);
		
		/*if (maLoaiId == this.emptyValueCbo) {
			this.clsbanggia.setDtdmclsbgMaloai(null);
		} else {
			this.clsbanggia.setDtdmclsbgMaloai(this.clsbanggia.getDtdmclsbgPhanloai());
		}*/
		
		if (phanLoaiId == this.emptyValueCbo) {
			this.clsbanggia.setDtdmclsbgPhanloai(null);
			this.clsbanggia.setDtdmclsbgMaloai(null);
		}else {
			this.clsbanggia.setDtdmclsbgMaloai(this.clsbanggia.getDtdmclsbgPhanloai());
		}
		clsbanggia.setDtdmclsbgLoai(maLoaiCLS);
		if (clsbanggia.getDtdmclsbgLoaipttt() != null) {
			if (clsbanggia.getDtdmclsbgLoaipttt().getDtdmloaiptMaso() != null) {
				if (clsbanggia.getDtdmclsbgLoaipttt().getDtdmloaiptMaso().intValue() == -1) {
					clsbanggia.setDtdmclsbgLoaipttt(null);
				}
			}
		}
		
		/*if (maLoaiCLS.trim().length() > 0) {
			clsbanggia.setDtdmclsbgLoai(maLoaiCLS);
		} else {
			clsbanggia.setDtdmclsbgLoai(null);
		}
		*/
		try {
			DtDmClsBangGia bg = (DtDmClsBangGia)DieuTriUtilDelegate.getInstance().findByMa(this.clsbanggia.getDtdmclsbgMa(), "DtDmClsBangGia", "dtdmclsbgMa");
			if (bg != null){
				FacesMessages.instance().add(IConstantsRes.MA_KO_TRUNG_NHAU);
				return ;
			}
			
			bg = (DtDmClsBangGia)DieuTriUtilDelegate.getInstance().findByMa(this.clsbanggia.getDtdmclsbgDiengiai(), "DtDmClsBangGia", "dtdmclsbgDiengiai");
			if (bg != null){
				FacesMessages.instance().add(IConstantsRes.TEN_KO_TRUNG_NHAU);
				return ;
			}
			
			//bg = (DtDmClsBangGia)DieuTriUtilDelegate.getInstance().findByMa(this.clsbanggia.getDtdmclsbgMa(), "DtDmClsBangGia", "dtdmclsbgMa");
			log.info("ngayCN = " + this.clsbanggia.getDtdmclsbgNgaygiocn());
			DieuTriUtilDelegate.getInstance().create(this.clsbanggia);
			DtDmClsBangGia bg1 = (DtDmClsBangGia)DieuTriUtilDelegate.getInstance().findByMa(this.clsbanggia.getDtdmclsbgMa(), "DtDmClsBangGia", "dtdmclsbgMa");
			DtDmClsKhoa dtDmClsKhoa = new DtDmClsKhoa();
			dtDmClsKhoa.setDtdmclsKhoaChon(true);
			dtDmClsKhoa.setDtdmclsKhoaNgaygiocn((double)new Date().getTime());
			//phuc.lc : 22-08-2011 : Fix bug 5853
			if (dmkhoaMaso == -1) {				
				dtDmClsKhoa.setDmkhoaMaso(null);
			} else {
				dtDmClsKhoa.setDmkhoaMaso(new DmKhoa(dmkhoaMaso));
			}
			dtDmClsKhoa.setDtdmclsMaso(bg1);
			if(dtDmClsKhoa.getDmkhoaMaso() != null) {
				DieuTriUtilDelegate.getInstance().create(dtDmClsKhoa);
			}
			FacesMessages.instance().add(IConstantsRes.clsbanggia_cr_su, ma);
			reset();
			
		} catch (Exception e) {
			log.error(e.toString());
			FacesMessages.instance().add(IConstantsRes.clsbanggia_cr_fa, ma);
		}
	}

	public String goBack() {
		log.info("GoBack DmBanggiacanlamsanExt_add....");
		
		return "/B3_Vienphi/ThuVienPhi/B3253_Banggiacanlamsan";
	}
	
	public void reset() {
		log.info("Reset DmBanggiacanlamsanExt_add....");

		resetValue();
	}

	private void resetValue() {
		this.clsbanggia = new DtDmClsBangGia();
		this.clsbanggia.setDtdmclsbgMaloai(new DtDmCls());
		this.clsbanggia.setDtdmclsbgPhanloai(new DtDmCls());
		this.clsbanggia.setDtdmclsbgLoaipttt(new DtDmLoaiPhauThuat());
		this.clsbanggia.setDtdmclsbgChon(true);		
		dmkhoaMaso = new Integer(-1);
		maLoaiCLS = "";
		
	}
	
	private void initComboxKhoa() {	
		SelectItem item ;
		
		this.listKhoa = new ArrayList<SelectItem>();
		
		item = new SelectItem(this.emptyValueCbo,this.emptyLabelCbo);
		this.listKhoa.add(item);
		
		List<DmKhoa> listTemp = DieuTriUtilDelegate.getInstance().findAll("DmKhoa");
		
		for (DmKhoa dmKhoa : listTemp) {
			if(dmKhoa.getDmkhoaCls() != null && dmKhoa.getDmkhoaCls() == true){
				item = new SelectItem(dmKhoa.getDmkhoaMaso(),dmKhoa.getDmkhoaTen());
				this.listKhoa.add(item);
			}
		}
	
	}
	
	private void initComboxMaLoai() {	
		SelectItem item ;
		
		this.listMaLoai = new ArrayList();
		
		item = new SelectItem(this.emptyValueCbo,this.emptyLabelCbo);
		this.listMaLoai.add(item);
		
		List<DtDmCls> lstempCls = DieuTriUtilDelegate.getInstance().findAll("DtDmCls", "dtdmclsChon", true);
		
		for (DtDmCls dtDmCls : lstempCls) {
			item = new SelectItem(dtDmCls.getDtdmclsMaso(),dtDmCls.getDtdmclsTen());
			this.listMaLoai.add(item);
		}
	
	}
	
	private void initComboxPhanLoai() {	
		SelectItem item ;
		
		this.listPhanLoai = new ArrayList();
		
		item = new SelectItem(this.emptyValueCbo,this.emptyLabelCbo);
		this.listPhanLoai.add(item);
		
		List<DtDmCls> lstempCls = DieuTriUtilDelegate.getInstance().findAll("DtDmCls", "dtdmclsChon", true);
		
		for (DtDmCls dtDmCls : lstempCls) {
			item = new SelectItem(dtDmCls.getDtdmclsMaso(),dtDmCls.getDtdmclsTen());
			this.listPhanLoai.add(item);
		}
	
	}
	private void initComboxLoaiPTTT() {	
		SelectItem item ;
		
		this.listLoaiPTTT = new ArrayList();
		
		item = new SelectItem(this.emptyValueCbo,this.emptyLabelCbo);
		this.listLoaiPTTT.add(item);
		
		List<DtDmLoaiPhauThuat> lstempLoaiPT = DieuTriUtilDelegate.getInstance().findAll("DtDmLoaiPhauThuat", "dtdmloaiptChon", true);
		
		for (DtDmLoaiPhauThuat dtDmLoaiPT : lstempLoaiPT) {
			item = new SelectItem(dtDmLoaiPT.getDtdmloaiptMaso(),dtDmLoaiPT.getDtdmloaiptTen());
			this.listLoaiPTTT.add(item);
		}
	
	}
	

	public DtDmClsBangGia getClsbanggia() {			
		return clsbanggia;
	}

	public void setClsbanggia(DtDmClsBangGia clsbanggia) {
		this.clsbanggia = clsbanggia;
	}

	public List<SelectItem> getListMaLoai() {
		return listMaLoai;
	}

	public void setListMaLoai(List<SelectItem> listMaLoai) {
		this.listMaLoai = listMaLoai;
	}

	public List<SelectItem> getListPhanLoai() {
		return listPhanLoai;
	}

	public void setListPhanLoai(List<SelectItem> listPhanLoai) {
		this.listPhanLoai = listPhanLoai;
	}

	public List<SelectItem> getListKhoa() {
		return listKhoa;
	}

	public void setListKhoa(List<SelectItem> listKhoa) {
		this.listKhoa = listKhoa;
	}

	public Integer getDmkhoaMaso() {
		return dmkhoaMaso;
	}

	public void setDmkhoaMaso(Integer dmkhoaMaso) {
		this.dmkhoaMaso = dmkhoaMaso;
	}

	public String getMaLoaiCLS() {
		return maLoaiCLS;
	}

	public void setMaLoaiCLS(String maLoaiCLS) {
		this.maLoaiCLS = maLoaiCLS;
	}

	public DanhSachLoaiClsBangGia getDsLoaiCLS() {
		return dsLoaiCLS;
	}

	public void setDsLoaiCLS(DanhSachLoaiClsBangGia dsLoaiCLS) {
		this.dsLoaiCLS = dsLoaiCLS;
	}

	public List<SelectItem> getListLoaiPTTT() {
		return listLoaiPTTT;
	}

	public void setListLoaiPTTT(List<SelectItem> listLoaiPTTT) {
		this.listLoaiPTTT = listLoaiPTTT;
	}
	
	

	
}

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
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.jsf.ListDataModel;
import org.jboss.seam.log.Log;

import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmClsBangGiaDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmClsKhoaDelegate;
import com.iesvn.yte.dieutri.entity.DtDmCls;
import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
import com.iesvn.yte.dieutri.entity.DtDmClsKhoa;
import com.iesvn.yte.dieutri.entity.DtDmLoaiPhauThuat;
import com.iesvn.yte.entity.DanhSachLoaiClsBangGia;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.util.IConstantsRes;

@Name("DtDmBangGiaCanLamSan_edit")
@Scope(SESSION)
public class DmBanggiacanlamsanExt_edit implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final int emptyValueCbo = -1;
	private static final String emptyLabelCbo = "...";

	//@In(required = false)
	//@Out(required = false)
	private DtDmClsBangGia clsbanggia;
	
	private DtDmClsBangGia clsbanggiaTemp;

	private List<SelectItem> listMaLoai;
	private List<SelectItem> listPhanLoai;
	private List<SelectItem> listLoaiPTTT;
	private String maLoaiCLS;
	private DanhSachLoaiClsBangGia dsLoaiCLS;
	private String maCls;
	
	private List<SelectItem> listKhoa;
	
	private Integer dmkhoaMaso;

	@Logger
	private Log log;

	//@Create
	public String init(Integer clsbgMaso) {
		log.info("Init DmBanggiacanlamsanExt_edit...., ma so = " + clsbgMaso);
		clsbanggia = DtDmClsBangGiaDelegate.getInstance().find(clsbgMaso);
		resetValue();
		initComboxKhoa();
		initComboxMaLoai();
		initComboxPhanLoai();
		initComboxLoaiPTTT();
		setValueForForm();
		maCls = clsbanggia.getDtdmclsbgMa();
		clsbanggiaTemp = clsbanggia;
		dsLoaiCLS = new DanhSachLoaiClsBangGia();
		maLoaiCLS = clsbanggia.getDtdmclsbgLoai();
		//List<DtDmClsKhoa> dtDmClsKhoaList = DtDmClsKhoaDelegate.getInstance().findByMaSoCLS(this.clsbanggia.getDtdmclsbgMaso());
		List<DtDmClsKhoa> dtDmClsKhoaList = DtDmClsKhoaDelegate.getInstance().findByMaSoCLS(clsbgMaso);
		if(dtDmClsKhoaList.size() > 0){
			dmkhoaMaso = (dtDmClsKhoaList.get(0).getDmkhoaMaso() == null ? -1 : dtDmClsKhoaList.get(0).getDmkhoaMaso().getDmkhoaMaso());
		}
		System.out.println("DmBanggiacanlamsanExt_edit.init()*********maCls=" + maCls);
		
		return "/B3_Vienphi/ThuVienPhi/B3253_Banggiacanlamsan_edit";
	}

	public void save(int phanLoaiId) {
		log.info("Save DmBanggiacanlamsanExt_edit....");
		
		//String ma = "";
		Date date = new Date();
		//ma = this.clsbanggia.getDtdmclsbgMa();
		//phuc.lc comment 01-10-2010
		//clsbanggia = clsbanggiaTemp;
		this.clsbanggia.setDtdmclsbgNgaygiocn((double)date.getTime());
		
		/*if (maLoaiId == this.emptyValueCbo) {
			this.clsbanggia.setDtdmclsbgMaloai(null);
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
		}else {
			clsbanggia.setDtdmclsbgLoai(null);
		}*/
		try {
			
			DtDmClsBangGia bg = (DtDmClsBangGia)DieuTriUtilDelegate.getInstance().findByMa(maCls, "DtDmClsBangGia", "dtdmclsbgMa");
			
			if (bg != null){
				//if (clsbanggiaTemp.getDtdmclsbgMaso() == null) {
					//clsbanggia = bg;
				//} else 
				System.out.println("DmBanggiacanlamsanExt_edit.save()************* clsbanggiaTemp.maso= " + clsbanggia.getDtdmclsbgMaso());
				if (bg.getDtdmclsbgMaso().intValue() != clsbanggia.getDtdmclsbgMaso().intValue()){
					log.info("*****clsbanggia !=null");
					FacesMessages.instance().add(IConstantsRes.TEN_KO_TRUNG_NHAU);
					return ;	
				}
				
			}
			DieuTriUtilDelegate.getInstance().edit(this.clsbanggia);
			List<DtDmClsKhoa> dtDmClsKhoaList = DtDmClsKhoaDelegate.getInstance().findByMaSoCLS(this.clsbanggia.getDtdmclsbgMaso());
			if(dtDmClsKhoaList.size() > 0){
				DtDmClsKhoa dtDmClsKhoa = dtDmClsKhoaList.get(0);
				dtDmClsKhoa.setDtdmclsKhoaNgaygiocn((double)new Date().getTime());
				//phuc.lc : 22-08-2011 : Fix bug 5853
				if (dmkhoaMaso == -1) {				
					dtDmClsKhoa.setDmkhoaMaso(null);
				} else {
					dtDmClsKhoa.setDmkhoaMaso(new DmKhoa(dmkhoaMaso));
				}
				if(dtDmClsKhoa.getDmkhoaMaso() != null) {
					DieuTriUtilDelegate.getInstance().edit(dtDmClsKhoa);
				} else {
					DieuTriUtilDelegate.getInstance().remove(dtDmClsKhoa);
				}
			}else {
				DtDmClsKhoa dtDmClsKhoa = new DtDmClsKhoa();
				dtDmClsKhoa.setDtdmclsMaso(clsbanggia);
				dtDmClsKhoa.setDtdmclsKhoaNgaygiocn((double)new Date().getTime());
				//phuc.lc : 22-08-2011 : Fix bug 5853
				if (dmkhoaMaso == -1) {				
					dtDmClsKhoa.setDmkhoaMaso(null);
				} else {
					dtDmClsKhoa.setDmkhoaMaso(new DmKhoa(dmkhoaMaso));
				}
				dtDmClsKhoa.setDtdmclsKhoaChon(true);
				if (dtDmClsKhoa.getDmkhoaMaso() != null) {
					DieuTriUtilDelegate.getInstance().create(dtDmClsKhoa);
				}
			}
			FacesMessages.instance().add(IConstantsRes.clsbanggia_up_su, maCls);
			
			/*if (maLoaiId == this.emptyValueCbo) {
				this.clsbanggia.setDtdmclsbgMaloai(new DtDmCls());
			}*/
			
			if (phanLoaiId == this.emptyValueCbo) {
				this.clsbanggia.setDtdmclsbgPhanloai(new DtDmCls());
			}
			
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
			FacesMessages.instance().add(IConstantsRes.clsbanggia_up_fa, maCls);
		}
	}

	public String goBack() {
		log.info("GoBack DmBanggiacanlamsanExt_edit....");
		
		return "/B3_Vienphi/ThuVienPhi/B3253_Banggiacanlamsan";
	}
	
	private void setValueForForm() {
		log.info("Set value for form DmNhaCungCapExt_edit...., ma so = " +clsbanggia.getDtdmclsbgMaso());
		
		int rowIndex = -1;
		ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listDtDmClsBangGia");
		rowIndex = lsDataModel.getRowIndex();
		//log.info("In Set value for form DmNhaCungCapExt_edit...., rowIndex = " + rowIndex);
		if (rowIndex != -1) {
			this.clsbanggia = ((List<DtDmClsBangGia>)lsDataModel.getWrappedData()).get(rowIndex);
		}	
		//log.info("End Set value for form DmNhaCungCapExt_edit...., ma so = " +clsbanggia.getDtdmclsbgMaso());
	}

	private void resetValue() {
		this.clsbanggia = new DtDmClsBangGia();
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

	public String getMaCls() {
		return maCls;
	}

	public void setMaCls(String maCls) {
		this.maCls = maCls;
	}

	public DtDmClsBangGia getClsbanggiaTemp() {
		return clsbanggiaTemp;
	}

	public void setClsbanggiaTemp(DtDmClsBangGia clsbanggiaTemp) {
		this.clsbanggiaTemp = clsbanggiaTemp;
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

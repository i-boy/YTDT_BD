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
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmClsBangGiaDelegate;
import com.iesvn.yte.dieutri.entity.DtDmCls;
import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
import com.iesvn.yte.util.IConstantsRes;

@Name("DtDmBangGiaCanLamSan")
@Scope(SESSION)
public class DmBanggiacanlamsanExt implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<SelectItem> listPhanLoai;
	private boolean searchDaxoa = false;
	private int scrollerPage = 1;
	@In(required = false)
	@Out(required = false)
	private DtDmClsBangGia clsbanggia;

	@DataModel
	private List<DtDmClsBangGia> listDtDmClsBangGia;

	@Logger
	private Log log;

	@Create
	public String init() {
		log.info("Init DmBanggiacanlamsanExt....");
		
		resetValue();
		initComboxPhanLoai();
		return "/B3_Vienphi/ThuVienPhi/B3253_Banggiacanlamsan";
	}

	public void search() {
		log.info("Search DmBanggiacanlamsanExt...., search da xoa = " + searchDaxoa);
		
		//this.listDtDmClsBangGia = DieuTriUtilDelegate.getInstance().findByAllConditions("DtDmClsBangGia", "dtdmclsbgMa", "dtdmclsbgDiengiai", clsbanggia.getDtdmclsbgMa(), clsbanggia.getDtdmclsbgDiengiai(), clsbanggia.getDtdmclsbgPhanloai().getDtdmclsMaso());
		listDtDmClsBangGia = DtDmClsBangGiaDelegate.getInstance().findByAllConditions(clsbanggia.getDtdmclsbgMa(), clsbanggia.getDtdmclsbgDiengiai(), clsbanggia.getDtdmclsbgPhanloai().getDtdmclsMaso(), searchDaxoa);
		
		if (listDtDmClsBangGia.size() == 0) { 
			FacesMessages.instance().add(IConstantsRes.no_found);
		}
		
	}
	private void initComboxPhanLoai() {	
		SelectItem item ;
		
		listPhanLoai = new ArrayList<SelectItem>();
		
		item = new SelectItem(new Integer(0),"");
		this.listPhanLoai.add(item);
		
		List<DtDmCls> lstempCls = DieuTriUtilDelegate.getInstance().findAll("DtDmCls", "dtdmclsChon", true);
		
		for (DtDmCls dtDmCls : lstempCls) {
			item = new SelectItem(dtDmCls.getDtdmclsMaso(),dtDmCls.getDtdmclsTen());
			listPhanLoai.add(item);
		}
	
	}

	public void delete(int rowIndex) {
		log.info("Delete DmBanggiacanlamsanExt....");
		
		if (rowIndex != -1) {
			
			String ma = "";
			Date date = new Date();
			
			ma = this.listDtDmClsBangGia.get(rowIndex).getDtdmclsbgMa();
						
			try {						
				DtDmClsBangGia be = this.listDtDmClsBangGia.get(rowIndex);
				
				be.setDtdmclsbgChon(false);
				be.setDtdmclsbgNgaygiocn((double)date.getTime());
				DieuTriUtilDelegate.getInstance().edit(be);
				this.listDtDmClsBangGia.remove(rowIndex);

				FacesMessages.instance().add(IConstantsRes.clsbanggia_de_su, ma);

			} catch (Exception e) {
				log.error(e.toString());
				FacesMessages.instance().add(IConstantsRes.clsbanggia_de_fa, ma);
			}
		}
	}

	public void reset() {
		log.info("Reset DmBanggiacanlamsanExt....");

		resetValue();
	}

	public String goback() {
		log.info("Goback DmBanggiacanlamsanExt....");

		return "/MyMainForm";
	}
	
	private void resetValue() {				
		this.clsbanggia = new DtDmClsBangGia();
		this.listDtDmClsBangGia = new ArrayList<DtDmClsBangGia>();
		//phuc.lc :/05/08/2011 : Fix bug 3763
		searchDaxoa = false;
		scrollerPage = 1;
	}

	public DtDmClsBangGia getClsbanggia() {
		return clsbanggia;
	}

	public void setClsbanggia(DtDmClsBangGia clsbanggia) {
		this.clsbanggia = clsbanggia;
	}

	public List<DtDmClsBangGia> getListDtDmClsBangGia() {
		return listDtDmClsBangGia;
	}

	public void setListDtDmClsBangGia(List<DtDmClsBangGia> listDtDmClsBangGia) {
		this.listDtDmClsBangGia = listDtDmClsBangGia;
	}

	public List<SelectItem> getListPhanLoai() {
		return listPhanLoai;
	}

	public void setListPhanLoai(List<SelectItem> listPhanLoai) {
		this.listPhanLoai = listPhanLoai;
	}

	public boolean isSearchDaxoa() {
		return searchDaxoa;
	}

	public void setSearchDaxoa(boolean searchDaxoa) {
		this.searchDaxoa = searchDaxoa;
	}

	public int getScrollerPage() {
		return scrollerPage;
	}

	public void setScrollerPage(int scrollerPage) {
		this.scrollerPage = scrollerPage;
	}
	
	
	
}

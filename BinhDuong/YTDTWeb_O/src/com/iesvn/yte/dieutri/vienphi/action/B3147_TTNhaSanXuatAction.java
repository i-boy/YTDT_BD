package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.apache.commons.beanutils.BeanUtils;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmNhaSxSpddDelegate;
import com.iesvn.yte.dieutri.entity.DtDmNhaSxSpdd;
import com.iesvn.yte.util.IConstantsRes;

@Scope(SESSION)
@Name("B3147_TTNhaSanXuat")
public class B3147_TTNhaSanXuatAction implements Serializable
{

	private static final long serialVersionUID = 10L;					
	
	private DtDmNhaSxSpdd nsxSpdd;
	private List<DtDmNhaSxSpdd> listNsxSpdd;

	@In(scope=ScopeType.PAGE,required=false)
	@Out(scope=ScopeType.PAGE,required=false)
	private String strNhaSX;
	@Logger
	private Log log;
	
	@Factory("strNhaSX")
	public void init() {
		nsxSpdd =  new DtDmNhaSxSpdd();
		listNsxSpdd = new ArrayList<DtDmNhaSxSpdd>();
		listNsxSpdd = DtDmNhaSxSpddDelegate.getInstance().findAll();
		strNhaSX = "";
	}
	public void saveNhaSX() {
		log.info("begin save,ma so = " + nsxSpdd.getDtdmnsxMaso() + ", size = " + listNsxSpdd.size());
		// kiem tra ma nha san xuat da ton tai chua
		DtDmNhaSxSpdd nsxTmp = new DtDmNhaSxSpdd();
		DieuTriUtilDelegate utilDel = DieuTriUtilDelegate.getInstance();
		nsxTmp = (DtDmNhaSxSpdd) utilDel.findByMa(nsxSpdd.getDtdmnsxMa(), "DtDmNhaSxSpdd", "dtdmnsxMa");
		if (nsxTmp != null) {
			//log.info("nsxTmp.getDtdmnsxMaso() = " + nsxTmp.getDtdmnsxMaso() + ", nsxSpdd.getDtdmnsxMaso()" + nsxSpdd.getDtdmnsxMaso());
			if (!nsxTmp.getDtdmnsxMaso().equals(nsxSpdd.getDtdmnsxMaso())) {
				FacesMessages.instance().add(IConstantsRes.MA_DA_TON_TAI, nsxSpdd.getDtdmnsxMa());
				return;
			}
		}
		nsxSpdd.setDtdmnsxNgaygiocn(new Double(new Date().getTime()));
		DtDmNhaSxSpddDelegate nsxDel = DtDmNhaSxSpddDelegate.getInstance();
		if (nsxSpdd.getDtdmnsxMaso() == null) {
			nsxDel.create(nsxSpdd);
			FacesMessages.instance().add(IConstantsRes.INSERT_SUCCESS);
		} else {
			nsxDel.edit(nsxSpdd);
			FacesMessages.instance().add(IConstantsRes.UPDATE_SUCCESS);
		}
		listNsxSpdd = nsxDel.findAll();
		nsxSpdd = new DtDmNhaSxSpdd();
		log.info("End save, size = " + listNsxSpdd.size());
	}
	public void edit(int index) {
		//nsxSpdd = listNsxSpdd.get(index);
		try {
			nsxSpdd = (DtDmNhaSxSpdd)BeanUtils.cloneBean(listNsxSpdd.get(index));
		} catch (Exception e) {
			nsxSpdd = new DtDmNhaSxSpdd();
			log.info(e.toString());
		}
		FacesMessages.instance().clear();
	}
	public DtDmNhaSxSpdd getNsxSpdd() {
		return nsxSpdd;
	}

	public void setNsxSpdd(DtDmNhaSxSpdd nsxSpdd) {
		this.nsxSpdd = nsxSpdd;
	}
	public List<DtDmNhaSxSpdd> getListNsxSpdd() {
		return listNsxSpdd;
	}
	public void setListNsxSpdd(List<DtDmNhaSxSpdd> listNsxSpdd) {
		this.listNsxSpdd = listNsxSpdd;
	}
}
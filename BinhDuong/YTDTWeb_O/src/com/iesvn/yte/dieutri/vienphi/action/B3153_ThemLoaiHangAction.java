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
import com.iesvn.yte.dieutri.delegate.DtDmLoaiThucPhamDelegate;
import com.iesvn.yte.dieutri.entity.DtDmLoaiThucPham;
import com.iesvn.yte.entity.DmDonViTinh;
import com.iesvn.yte.util.IConstantsRes;

@Scope(SESSION)
@Name("B3153_ThemLoaiHang")
public class B3153_ThemLoaiHangAction implements Serializable
{

	private static final long serialVersionUID = 10L;					
	
	private DtDmLoaiThucPham loaiTp;	
	private List<DtDmLoaiThucPham> listLoaiTp;

	@In(scope=ScopeType.PAGE,required=false)
	@Out(scope=ScopeType.PAGE,required=false)
	private String strLoaiTp;
	@Logger
	private Log log;
	
	@Factory("strLoaiTp")
	public void init() {
		loaiTp =  new DtDmLoaiThucPham();
		loaiTp.setDmdonvitinhMaso(new DmDonViTinh());
		listLoaiTp = new ArrayList<DtDmLoaiThucPham>();
		listLoaiTp = DtDmLoaiThucPhamDelegate.getInstance().findAll();
		strLoaiTp = "";
	}
	public void saveLoaiTp() {
		log.info("begin save");
		// kiem tra ma thuc pham da ton tai chua
		DtDmLoaiThucPham loaiTpTmp = new DtDmLoaiThucPham();
		DieuTriUtilDelegate utilDel = DieuTriUtilDelegate.getInstance();
		loaiTpTmp = (DtDmLoaiThucPham) utilDel.findByMa(loaiTp.getDtdmltpMa(), "DtDmLoaiThucPham", "dtdmltpMa");
		if (loaiTpTmp != null) {
			//log.info("nsxTmp.getDtdmnsxMaso() = " + nsxTmp.getDtdmnsxMaso() + ", nsxSpdd.getDtdmnsxMaso()" + nsxSpdd.getDtdmnsxMaso());
			if (!loaiTpTmp.getDtdmltpMaso().equals(loaiTp.getDtdmltpMaso())) {
				FacesMessages.instance().add(IConstantsRes.MA_DA_TON_TAI, loaiTp.getDtdmltpMa());
				return;
			}
		}
		loaiTp.setDtdmltpNgaygiocn(new Double(new Date().getTime()));
		DtDmLoaiThucPhamDelegate loaiTpDel = DtDmLoaiThucPhamDelegate.getInstance();
		if (loaiTp.getDtdmltpMaso() == null) {
			loaiTpDel.create(loaiTp);
			FacesMessages.instance().add(IConstantsRes.INSERT_SUCCESS);
		} else {
			loaiTpDel.edit(loaiTp);
			FacesMessages.instance().add(IConstantsRes.UPDATE_SUCCESS);
		}
		listLoaiTp = loaiTpDel.findAll();
		loaiTp = new DtDmLoaiThucPham();
		loaiTp.setDmdonvitinhMaso(new DmDonViTinh());
		log.info("End save, size = " + listLoaiTp.size());
	}
	public void edit(int index) {
		//nsxSpdd = listNsxSpdd.get(index);
		try {
			loaiTp = (DtDmLoaiThucPham)BeanUtils.cloneBean(listLoaiTp.get(index));
		} catch (Exception e) {
			loaiTp = new DtDmLoaiThucPham();
			loaiTp.setDmdonvitinhMaso(new DmDonViTinh());
			log.info(e.toString());
		}
		FacesMessages.instance().clear();
	}
	public DtDmLoaiThucPham getLoaiTp() {
		return loaiTp;
	}
	public void setLoaiTp(DtDmLoaiThucPham loaiTp) {
		this.loaiTp = loaiTp;
	}
	public List<DtDmLoaiThucPham> getListLoaiTp() {
		return listLoaiTp;
	}
	public void setListLoaiTp(List<DtDmLoaiThucPham> listLoaiTp) {
		this.listLoaiTp = listLoaiTp;
	}
}
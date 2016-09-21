package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.iesvn.yte.dieutri.delegate.DuTruThucPhamDelegate;
import com.iesvn.yte.dieutri.entity.DtDmLoaiThucPham;
import com.iesvn.yte.dieutri.entity.DuTruThucPham;
import com.iesvn.yte.util.IConstantsRes;

@Scope(SESSION)
@Name("B3152_DuTruThucPham")
public class B3152_DuTruThucPhamAction implements Serializable
{

	private static final long serialVersionUID = 10L;					
	
	private String ngaynhap;	
	private String soluong;	
	private DuTruThucPham dutruTP;
	private String dvtTen;
		
	private String ngayhientai;
	private boolean lockedSaveButton;
	private int selectedIndex;
	private List<DuTruThucPham> listDuTruTP;
	
	private DuTruThucPhamDelegate dttpDel = DuTruThucPhamDelegate.getInstance();
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	@In(scope=ScopeType.PAGE,required=false)
	@Out(scope=ScopeType.PAGE,required=false)
	private String strDuTruTP;
	@Logger
	private Log log;
	
	@Factory("strDuTruTP")
	public void init() {
		reset();
		strDuTruTP = "";
	}
	
	public void emptyData(){
		dutruTP = new DuTruThucPham();
		dutruTP.setDtdmltpMaso(new DtDmLoaiThucPham());		
		soluong = "";
		dvtTen = "";
		selectedIndex =  -1;
	}
	
	public void reset() {
		listDuTruTP = new ArrayList<DuTruThucPham>();
		ngaynhap = sdf.format(new Date());
		ngayhientai = sdf.format(new Date());
		lockedSaveButton = false;
		emptyData();
		FacesMessages.instance().clear();
	}
	
	public void seachDuTruTP(){
		listDuTruTP = new ArrayList<DuTruThucPham>();
		try {
			listDuTruTP = dttpDel.findByDate(sdf.parse(ngaynhap));
			if (listDuTruTP != null) {								
				FacesMessages.instance().clear();
			} else {
				listDuTruTP = new ArrayList<DuTruThucPham>();				
				FacesMessages.instance().add(IConstantsRes.no_found);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	public void edit(int index) {
		log.info("*****Begin edit(),index = " + index);
		
		DuTruThucPham dutruTP_tmp = listDuTruTP.get(index);
		try {
			dutruTP = (DuTruThucPham) BeanUtils.cloneBean(dutruTP_tmp);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		soluong = dutruTP_tmp.getDttpSlthucdat().toString();	
		ngaynhap = sdf.format(dutruTP_tmp.getDttpNgaydutru());
		
		selectedIndex = index;
		log.info("*****End edit()" );
		
	}
	
	// Ham delete chi tiet
	public void delete(int index) throws Exception
	{
		log.info("*****Begin delete() *****, index = " + index + ", dutruTP = " + listDuTruTP.get(index));
		// Xoa du lieu trong DB
		dttpDel.remove(listDuTruTP.get(index));
		// Xoa item trong list
		listDuTruTP.remove(index); 
		
		dutruTP = new DuTruThucPham();		
		emptyData();
		log.info("*****End delete() *****, listDuTruTP.size = " + listDuTruTP.size());
	}
	
	
	public void saveDuTruTP() {
		log.info("begin save saveDuTruTP()");
		try {
			
			dutruTP.setDttpNgaydutru(sdf.parse(ngaynhap));
			dutruTP.setDttpSlthucdat(new Float(soluong));
			// Kiem tra thong tin du tru TP theo ngay
			
			DuTruThucPham dttpTmp = dttpDel.findByLoaiTPNgayDutru(dutruTP.getDtdmltpMaso().getDtdmltpMa(), sdf.parse(ngaynhap));
			
			// Neu thong tin chua co thi luu moi
			if (dttpTmp == null) {
				DuTruThucPhamDelegate.getInstance().create(dutruTP);
				FacesMessages.instance().add(IConstantsRes.INSERT_SUCCESS);
				lockedSaveButton = true;
			} else {
				if (dutruTP.getDttpMaso() != null) {
					// Neu da co thong tin va dutruTP.getDttpMaso() != null ==> cap nhat lai thong tin
					DuTruThucPhamDelegate.getInstance().edit(dutruTP);
					FacesMessages.instance().add(IConstantsRes.UPDATE_SUCCESS);
					lockedSaveButton = true;
				} else {
					// neu da co thong tin va dutruTP = null ==> hien thi thong bao thong tin da co
					FacesMessages.instance().add(IConstantsRes.DU_TRU_TP_EXISTS, ngaynhap);
					lockedSaveButton = false;
				}
			}
			emptyData();
			seachDuTruTP();
		} catch (Exception e) {
			log.info("ERROR : " + e.toString());
			e.printStackTrace();
			FacesMessages.instance().add(IConstantsRes.INSERT_FAIL);
			lockedSaveButton = false;
		}
		log.info("end save");
	}
	public void searchPhieuDuTruTP() {
		log.info("--enter searchPhieuDuTruTP()--- ma TP = " + dutruTP.getDtdmltpMaso());		
		try {					
			DuTruThucPhamDelegate dttpDel = DuTruThucPhamDelegate.getInstance();
			DuTruThucPham dttpTmp = dttpDel.findByLoaiTPNgayDutru(dutruTP.getDtdmltpMaso().getDtdmltpMa(), sdf.parse(ngaynhap));
			if (dttpTmp != null) {
				dutruTP = (DuTruThucPham)BeanUtils.cloneBean(dttpTmp);
				soluong = "" + dutruTP.getDttpSlthucdat();
				FacesMessages.instance().clear();
			} else {				
				// Hien thi thong bao khong tim thay
				FacesMessages.instance().add(IConstantsRes.no_found);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		log.info("--exit searchPhieuDuTruTP()---");
	}
	public void showDvt() {		
		log.info("begin showDvt(), donviTinh ma so = " + dutruTP.getDtdmltpMaso().getDmdonvitinhMaso());
		DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
		DtDmLoaiThucPham dmLoaiTP = (DtDmLoaiThucPham) dtUtil.findByMaSo(dutruTP.getDtdmltpMaso().getDtdmltpMaso(), "DtDmLoaiThucPham", "dtdmltpMaso");
		if (dmLoaiTP == null) {
			dvtTen = "";
			return;
		}		
		dvtTen = "(" + (dmLoaiTP.getDmdonvitinhMaso() == null ? "" : dmLoaiTP.getDmdonvitinhMaso().getDmdonvitinhTen()) + ")";		
		log.info("end showDvt(), dvtTen = " + dvtTen);
	}
	public String getNgaynhap() {
		return ngaynhap;
	}
	public void setNgaynhap(String ngaynhap) {
		this.ngaynhap = ngaynhap;
	}
	public String getSoluong() {
		return soluong;
	}
	public void setSoluong(String soluong) {
		this.soluong = soluong;
	}	
	public boolean isLockedSaveButton() {
		return lockedSaveButton;
	}
	public void setLockedSaveButton(boolean lockedSaveButton) {
		this.lockedSaveButton = lockedSaveButton;
	}
	public String getNgayhientai() {
		return ngayhientai;
	}
	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
	}
	public DuTruThucPham getDutruTP() {
		return dutruTP;
	}
	public void setDutruTP(DuTruThucPham dutruTP) {
		this.dutruTP = dutruTP;
	}
	public String getDvtTen() {
		return dvtTen;
	}
	public void setDvtTen(String dvtTen) {
		this.dvtTen = dvtTen;
	}
	public int getSelectedIndex() {
		return selectedIndex;
	}
	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}
	public List<DuTruThucPham> getListDuTruTP() {
		return listDuTruTP;
	}
	public void setListDuTruTP(List<DuTruThucPham> listDuTruTP) {
		this.listDuTruTP = listDuTruTP;
	}
	
}
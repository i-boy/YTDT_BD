package com.iesvn.yte.dieutri.duocpham.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.xml.rpc.ServiceException;

import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DmPhanLoaiThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DmThuocDelegate;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmPhanLoaiThuoc;
import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.util.IConstantsRes;


@Name("B4168_Nhomhang")
@Scope(SESSION)
public class B4168_Nhomhang implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private  DmPhanLoaiThuoc nhomhang;

	@DataModel
	private List<DmPhanLoaiThuoc> listPhanLoaiThuocs;
	private List<SelectItem> listLoaiThuocs;
	

	@Logger
	private Log log;

	@Create  																			
	public String init() {													
		log.info("Init B4168_Nhomhang....");
		resetValue();														
		listLoaiThuocs = new  ArrayList<SelectItem>();
		List<DmLoaiThuoc> list = DieuTriUtilDelegate.getInstance().findAll("DmLoaiThuoc", "dmloaithuocDt", true);
		listLoaiThuocs.add(new SelectItem(null, ""));
		for (DmLoaiThuoc item : list) {
			listLoaiThuocs.add(new SelectItem(item.getDmloaithuocMaso(), item.getDmloaithuocTen()));
		}
		return "/B4_Duocpham/KhoChinh/B4168_nhomhang";   					
	}

	@SuppressWarnings("unchecked")
	public String search() {													
		log.info("Search Danh muc phan loai thuoc");
		DmPhanLoaiThuocDelegate pltDel = DmPhanLoaiThuocDelegate.getInstance();		
		listPhanLoaiThuocs.clear();
		listPhanLoaiThuocs = pltDel.findByMaAndTenAndDmloaiMa(nhomhang.getDmphanloaithuocMa(), nhomhang.getDmphanloaithuocTen(), nhomhang.getDmloaithuocMaso(true).getDmloaithuocMaso());
		
		if (listPhanLoaiThuocs.size() == 0) { 										
			FacesMessages.instance().add(IConstantsRes.no_found);			
		}
		
		return "/B4_Duocpham/KhoChinh/B4168_nhomhang";
	}
	
	public String getKhoName(String strKhoMaso){
		try{
			Integer khoMaso = Integer.parseInt(strKhoMaso);
			DieuTriUtilDelegate dtDel = DieuTriUtilDelegate.getInstance();
			DmKhoa dmKhoa = (DmKhoa)dtDel.findByMaSo(khoMaso, "DmKhoa", "dmkhoaMaso");
			if(dmKhoa != null){
				return dmKhoa.getDmkhoaTen();
			}
			return "";
		}catch(Exception er){
			return "";
		}
	}
	
	public void delete(int rowIndex) {											
		log.info("Delete B4168_nhomhang....");

		if (rowIndex != -1) {
			String maPLT = this.listPhanLoaiThuocs.get(rowIndex).getDmphanloaithuocMa();
			Integer masoPLT = this.listPhanLoaiThuocs.get(rowIndex).getDmphanloaithuocMaso();			
			try {
				DmPhanLoaiThuoc dm = new DmPhanLoaiThuoc();
				
				//kiem tra neu co ton tai dm thuoc thuoc phan loai nay thi k cho xoa kho DB nhung set DmPhanloaithuocDt thanh false
				//nguoc lai neu khong ton tai dm thuoc thuoc phan loai nay thi xoa duoc
				DmThuocDelegate dmThuocDel = DmThuocDelegate.getInstance();
				DmPhanLoaiThuocDelegate pltDel = DmPhanLoaiThuocDelegate.getInstance();
				dm = pltDel.find(masoPLT);
				if(dmThuocDel.hasThuoInPhanLoaiThuoc(masoPLT)){					
					dm.setDmphanloaithuocDt(false);
					dm.setDmphanloaithuocNgaygiocn((double)(new Date()).getTime());
					pltDel.edit(dm);
				}else{
					pltDel.remove(dm);
				}
				//search lai list
				listPhanLoaiThuocs = pltDel.findByMaAndTenAndDmloaiMa(nhomhang.getDmphanloaithuocMa(), nhomhang.getDmphanloaithuocTen(), nhomhang.getDmloaithuocMaso(true).getDmloaithuocMaso());
				FacesMessages.instance().add(IConstantsRes.plthuoc_de_su, maPLT);
			} catch (Exception e) {
				log.error(e.toString());
				FacesMessages.instance().add(IConstantsRes.plthuoc_de_fa, maPLT, e.toString());
			}
		}
	}	
	
	public void reset() {													
		log.info("Reset B4168_Nhomhang....");
		resetValue();
	}

	private void resetValue() {		
		this.nhomhang = new DmPhanLoaiThuoc();
		this.nhomhang.setDmloaithuocMaso(new DmLoaiThuoc());
		this.listPhanLoaiThuocs = new ArrayList<DmPhanLoaiThuoc>(); 							
	}	
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public DmPhanLoaiThuoc getNhomhang() {
		return nhomhang;
	}

	public void setNhomhang(DmPhanLoaiThuoc nhomhang) {
		this.nhomhang = nhomhang;
	}

	public List<DmPhanLoaiThuoc> getListPhanLoaiThuocs() {
		return listPhanLoaiThuocs;
	}

	public void setListPhanLoaiThuocs(List<DmPhanLoaiThuoc> listPhanLoaiThuocs){
		this.listPhanLoaiThuocs = listPhanLoaiThuocs;
	}
	public List<SelectItem> getListLoaiThuocs() {
		return listLoaiThuocs;
	}

	public void setListLoaiThuocs(List<SelectItem> listLoaiThuocs) {
		this.listLoaiThuocs = listLoaiThuocs;
	}
}
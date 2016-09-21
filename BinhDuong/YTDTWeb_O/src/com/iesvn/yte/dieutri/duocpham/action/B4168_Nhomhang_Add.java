package com.iesvn.yte.dieutri.duocpham.action;

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
import com.iesvn.yte.dieutri.delegate.DmKhoaDelegate;
import com.iesvn.yte.entity.DmPhanLoaiThuoc;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.util.IConstantsRes;

@Name("B4168_Nhomhang_Add")
@Scope(SESSION)
public class B4168_Nhomhang_Add implements Serializable{
	
	private static final long serialVersionUID = 1L;
		
		@In(required = false)
		@Out(required = false)
		private  DmPhanLoaiThuoc nhomhang;
		
		@Logger
		private Log log;
		
		private List<SelectItem> listLoaiThuocs;
		private List<SelectItem> listKhoPhats;
		private Boolean lamtron = false;
		
		public String init() {
			log.info("Init B4168_Nhomhang_Add....");
			
			resetValue();
			initCbo();
			log.info("End Init B4168_Nhomhang_Add");
			return "/B4_Duocpham/KhoChinh/B4168_nhomhang_add";
		}
		
		public void save() {
			log.info("Save B4168_Nhomhang_Add....");
			String ma = "";
			Date date = new Date();
			if(lamtron){
				nhomhang.setDmphanloaithuocNhom3("1");
			}else{
				nhomhang.setDmphanloaithuocNhom3("0");
			}
			ma = nhomhang.getDmphanloaithuocMa();										
			nhomhang.setDmphanloaithuocNgaygiocn((double)date.getTime());
			
			if((nhomhang.getDmloaithuocMaso(true).getDmloaithuocMaso())==null){
				nhomhang.setDmloaithuocMaso(null);
			}
			
			DmPhanLoaiThuoc plthuoc = (DmPhanLoaiThuoc)DieuTriUtilDelegate.getInstance().findByMa(nhomhang.getDmphanloaithuocMa(), "DmPhanLoaiThuoc", "dmphanloaithuocMa");
			if (plthuoc != null){
				FacesMessages.instance().add(IConstantsRes.plthuoc_cr_ma_trung, ma);
				return ;
			}
			
			try {
				DieuTriUtilDelegate.getInstance().create(nhomhang);				
				FacesMessages.instance().add(IConstantsRes.plthuoc_cr_su, ma);
				resetValue();
				
			} catch (Exception e) {
				log.error(e.toString());
				e.printStackTrace();
				FacesMessages.instance().add(IConstantsRes.plthuoc_cr_fa, ma);
			}			
		}

		public String goBack() {
			log.info("GoBack B4168 Nhom hang....");
			resetValue();
			return "/B4_Duocpham/KhoChinh/B4168_nhomhang";
		}	
		
		public void reset() {
			log.info("Reset B4168_nhomhang....");

			resetValue();
		}		
		private void resetValue() {			
			nhomhang = new DmPhanLoaiThuoc();
			nhomhang.setDmphanloaithuocDt(true);
			lamtron = false;
		}
		
		private void initCbo(){																
			log.info("Init Select items for combobox....");	
			initCboKhoPhat();
			initCboLoaithuoc();
		}
				
		@SuppressWarnings("unchecked")
		private void initCboKhoPhat(){
			log.info("getList_Kho phat....");			
			listKhoPhats = new  ArrayList<SelectItem>();
			listKhoPhats.add(new SelectItem(null, ""));
			DmKhoaDelegate dmKhoaDel = DmKhoaDelegate.getInstance();
			List<DmKhoa> list = dmKhoaDel.getKhoChinh_KhoLe();
			for (DmKhoa item : list) {
				listKhoPhats.add(new SelectItem(item.getDmkhoaMaso(), item.getDmkhoaTen()));
			}
		}
		
		@SuppressWarnings("unchecked")
		private void initCboLoaithuoc(){
			log.info("getList_loai thuoc....");
			
			listLoaiThuocs = new  ArrayList<SelectItem>();
			listLoaiThuocs.add(new SelectItem(null, ""));
			List<DmLoaiThuoc> list = (List<DmLoaiThuoc>)DieuTriUtilDelegate.getInstance()
											.findAll("DmLoaiThuoc", "dmloaithuocDt", true);
			for (DmLoaiThuoc item : list) {
				listLoaiThuocs.add(new SelectItem(item.getDmloaithuocMaso(), 
														   item.getDmloaithuocTen()));
			}
		}

		public DmPhanLoaiThuoc getNhomhang() {
			return nhomhang;
		}

		public void setNhomhang(DmPhanLoaiThuoc nhomhang) {
			this.nhomhang = nhomhang;
		}

		public Log getLog() {
			return log;
		}

		public void setLog(Log log) {
			this.log = log;
		}

		public static long getSerialVersionUID() {
			return serialVersionUID;
		}

		public List<SelectItem> getListLoaiThuocs() {
			return listLoaiThuocs;
		}

		public void setListLoaiThuocs(List<SelectItem> listLoaiThuocs) {
			this.listLoaiThuocs = listLoaiThuocs;
		}
		
		public List<SelectItem> getListKhoPhats() {
			return listKhoPhats;
		}

		public void setListKhoPhats(List<SelectItem> listKhoPhats) {
			this.listKhoPhats = listKhoPhats;
		}
		
		public Boolean getLamtron() {
			return lamtron;
		}

		public void setLamtron(Boolean lamtron) {
			this.lamtron = lamtron;
		}
}

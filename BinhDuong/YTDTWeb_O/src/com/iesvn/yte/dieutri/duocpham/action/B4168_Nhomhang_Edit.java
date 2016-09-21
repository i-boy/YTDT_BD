package com.iesvn.yte.dieutri.duocpham.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DmKhoaDelegate;
import com.iesvn.yte.entity.DmBaoQuanThuoc;
import com.iesvn.yte.entity.DmCachDungThuoc;
import com.iesvn.yte.entity.DmDonViTinh;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.entity.DmNhaSanXuat;
import com.iesvn.yte.entity.DmPhanLoaiThuoc;
import com.iesvn.yte.entity.DmPhanNhomThuoc;
import com.iesvn.yte.entity.DmQuocGia;
import com.iesvn.yte.entity.DmThuoc;
import com.iesvn.yte.util.IConstantsRes;

@Name("B4168_Nhomhang_Edit")
@Scope(CONVERSATION)
public class B4168_Nhomhang_Edit implements Serializable{
	
	private static final long serialVersionUID = 1L;

		@In(required = false)
		@Out(required = false)
		private  DmPhanLoaiThuoc nhomhang;
		
		private List<SelectItem> listLoaiThuocs;
		private List<SelectItem> listKhoPhats;

		@Logger
		private Log log;
		
		private Boolean lamtron = false;

		@Begin(join = true)
		public String init() {
			log.info("Init B4168_Nhomhang_Edit....");
			int rowIndex = -1;			
			ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listPhanLoaiThuocs");
			rowIndex = lsDataModel.getRowIndex();
			log.info("rowIndex..****************..:"+rowIndex);
			if (rowIndex != -1) {
				resetValue();
				load_rowIndex(rowIndex,lsDataModel);
				initCbo();		
				return "/B4_Duocpham/KhoChinh/B4168_nhomhang_edit";
			}else{
				return "";
			}
			
		}
		
		public void reset() {
			log.info("Reset B4168_Nhomhang_Edit....");
			resetValue();
		}
		
		@SuppressWarnings("unchecked")
		public void load_rowIndex(int rowIndex,ListDataModel lsDataModel ){
			log.info("Load rowIndex B4168_Nhomhang_Edit ....");
			if (rowIndex != -1) {
				nhomhang = ((List<DmPhanLoaiThuoc>)lsDataModel.getWrappedData()).get(rowIndex);
				if((nhomhang.getDmloaithuocMaso())==null){
					nhomhang.setDmloaithuocMaso(new DmLoaiThuoc());
				}
				System.out.println("nhom hang: "+nhomhang.getDmphanloaithuocNhom2());
				Integer khoMasoInt = Integer.parseInt(nhomhang.getDmphanloaithuocNhom2());
				String strLamtron = nhomhang.getDmphanloaithuocNhom3();
				if(strLamtron != null){
					if(strLamtron.equals("1")){
						lamtron = true;
					}else{
						lamtron = false;
					}
				}else{
					lamtron = false;
				}
				
				DieuTriUtilDelegate dtDel = DieuTriUtilDelegate.getInstance();
				DmKhoa dmKhoa = (DmKhoa)dtDel.findByMaSo(khoMasoInt, "DmKhoa", "dmkhoaMaso");				
			}
			
			log.info("rowIndex =" + rowIndex );		
		}
		
		public void save() {
			log.info("Save B4168_Nhomhang_Edit....");
			
			String ma = "";
			Date date = new Date();
			nhomhang.setDmphanloaithuocNgaygiocn((double)date.getTime());
			ma = nhomhang.getDmphanloaithuocMa();
			if(lamtron){
				nhomhang.setDmphanloaithuocNhom3("1");
			}else{
				nhomhang.setDmphanloaithuocNhom3("0");
			}			
			
			if((nhomhang.getDmloaithuocMaso(true).getDmloaithuocMaso())==null){
				nhomhang.setDmloaithuocMaso(null);
			}
			try {					
				DieuTriUtilDelegate.getInstance().edit(nhomhang);									//Edit một đối tượng vào database
				FacesMessages.instance().add(IConstantsRes.plthuoc_up_su, ma);
				
			} catch (Exception e) {
				log.error(e.toString());				
				FacesMessages.instance().add(IConstantsRes.plthuoc_up_fa, ma);
			}finally{
				resetValue();
		}
	}
		
		@End
		public String goBack() {
			log.info("GoBack B4168 Mat hang....");
			
			return "/B4_Duocpham/KhoChinh/B4168_nhomhang";
		}

		private void resetValue() {					
			nhomhang = new DmPhanLoaiThuoc();
			lamtron = false;
		}
		
		private void initCbo(){																	//Hàm này dùng để khởi tạo
			log.info("Init Select items for combobox....");										//danh sách các thư mục
			initCboKhoPhat();
			initCboLoaithuoc();
		}
		
		@SuppressWarnings("unchecked")
		private void initCboKhoPhat(){
			log.info("getList_Kho phat....");			
			listKhoPhats = new  ArrayList<SelectItem>();
			DmKhoaDelegate dmKhoaDel = DmKhoaDelegate.getInstance();
			List<DmKhoa> list = dmKhoaDel.getKhoChinh_KhoLe();
			for (DmKhoa item : list) {
				listKhoPhats.add(new SelectItem(item.getDmkhoaMaso(), item.getDmkhoaTen()));
			}
		}
		
		@SuppressWarnings("unchecked")
		private void initCboLoaithuoc(){
			log.info("getList_loai thuoc....");			
			this.listLoaiThuocs = new  ArrayList<SelectItem>();
			List<DmLoaiThuoc> list = (List<DmLoaiThuoc>)DieuTriUtilDelegate.getInstance().findAll("DmLoaiThuoc", "dmloaithuocDt", true);
			for (DmLoaiThuoc item : list) {
				listLoaiThuocs.add(new SelectItem(item.getDmloaithuocMaso(), item.getDmloaithuocTen()));
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

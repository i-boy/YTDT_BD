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
import com.iesvn.yte.entity.DmBaoQuanThuoc;
import com.iesvn.yte.entity.DmCachDungThuoc;
import com.iesvn.yte.entity.DmDonViTinh;
import com.iesvn.yte.entity.DmNhaSanXuat;
import com.iesvn.yte.entity.DmPhanLoaiThuoc;
import com.iesvn.yte.entity.DmPhanNhomThuoc;
import com.iesvn.yte.entity.DmQuocGia;
import com.iesvn.yte.entity.DmThuoc;
import com.iesvn.yte.util.IConstantsRes;

@Name("B4151_Mathang_Edit")
@Scope(CONVERSATION)
public class B4151_Mathang_Edit implements Serializable{
	
	private static final long serialVersionUID = 1L;

		@In(required = false)
		@Out(required = false)
		private  DmThuoc mathang;
		
		private List<SelectItem> listB4151_Donvitinh;
		private List<SelectItem> listB4151_Baoquan;
		private List<SelectItem> listB4151_Nhomthuoc;
		private List<SelectItem> listB4151_Cachdung;
		private List<SelectItem> listB4151_Nhasanxuat;
		private List<SelectItem> listB4151_Phanloaithuoc;
		private List<SelectItem> listB4151_QuocGia;
		private List<SelectItem> listB4151_Loaithuoc;

		@Logger
		private Log log;

		@Begin(join = true)
		public String init() {
			log.info("Init B4151_Mathang....");
			int rowIndex = -1;			
			ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listB4151");
			rowIndex = lsDataModel.getRowIndex();
			log.info("rowIndex..****************..:"+rowIndex);
			if (rowIndex != -1) {
				resetValue();
				load_rowIndex(rowIndex,lsDataModel);
				initCbo();		
				return "/B4_Duocpham/KhoChinh/B4151_mathang_edit";
			}else{
				return "";
			}
			
		}
		
		public void reset() {
			log.info("Reset B4151_Mathang....");

			resetValue();
		}
		
		@SuppressWarnings("unchecked")
		public void load_rowIndex(int rowIndex,ListDataModel lsDataModel ){
			log.info("Load rowIndex B4151_Mathang ....");
			
			
			
			if (rowIndex != -1) {
				this.mathang = ((List<DmThuoc>)lsDataModel.getWrappedData()).get(rowIndex);
				
				log.info("this.mathang.getDmthuocSoDangKy():"+this.mathang.getDmthuocSoDangKy());
				if((this.mathang.getDmbaoquanMaso())== null){  						//Kiểm tra nếu đối tượng hiện hành là null thì new đối tuợng mới
					this.mathang.setDmbaoquanMaso(new DmBaoQuanThuoc());
				}
				
				if((this.mathang.getDmcachdungMaso())==null){
					this.mathang.setDmcachdungMaso(new DmCachDungThuoc());
				}
				
				if((this.mathang.getDmdonvitinhMaso())==null){
					this.mathang.setDmdonvitinhMaso(new DmDonViTinh());
				}
				
				if((this.mathang.getDmnhasanxuatMaso())==null){
					this.mathang.setDmnhasanxuatMaso(new DmNhaSanXuat());
				}
				
				if((this.mathang.getDmphanloaithuocMaso())==null){
					this.mathang.setDmphanloaithuocMaso(new DmPhanLoaiThuoc());
				}
					
				if((this.mathang.getDmphannhomthuocMaso())==null){
					this.mathang.setDmphannhomthuocMaso(new DmPhanNhomThuoc());
				}	
				if((this.mathang.getDmthuocNuocdefa())==null){
					this.mathang.setDmthuocNuocdefa(new DmQuocGia());
				}
			}
			
			log.info("rowIndex =" + rowIndex );		
		}
		
		public void save() {
			log.info("Save B4151_Mat hang_Edit....");
			
			String ma = "";
			Date date = new Date();
			this.mathang.setDmthuocNgaygiocn((double)date.getTime());									//Lấy ngày giờ hiện hành ép vể kiểu double
			
			ma = this.mathang.getDmthuocMa();
			
			
			this.mathang.setDmthuocDt(true);															//Gán thuộc tính true khi thêm mới và về false khi delete
			
			if((this.mathang.getDmbaoquanMaso(true).getDmbaoquanthuocMaso())== null){
				this.mathang.setDmbaoquanMaso(null);
			}
			if((this.mathang.getDmcachdungMaso(true).getDmcachdungthuocMaso())==null){
				this.mathang.setDmcachdungMaso(null);
			}
			
			if((this.mathang.getDmdonvitinhMaso(true).getDmdonvitinhMaso())==null){
				this.mathang.setDmdonvitinhMaso(null);
			}
			
			if((this.mathang.getDmnhasanxuatMaso(true).getDmnhasanxuatMaso())==null){
				this.mathang.setDmnhasanxuatMaso(null);
			}
			
			if((this.mathang.getDmphanloaithuocMaso(true).getDmphanloaithuocMaso())==null){
				this.mathang.setDmphanloaithuocMaso(null);
			}
				
			if((this.mathang.getDmthuocNuocdefa(true).getDmquocgiaMaso())==null){
				this.mathang.setDmthuocNuocdefa(null);
			}
			
			if(this.mathang.getDmthuocPlbhyt() == null){
				this.mathang.setDmthuocPlbhyt(new Short((short) 1));
			}
			
			try {	
				
				DieuTriUtilDelegate.getInstance().edit(this.mathang);									//Edit một đối tượng vào database
				FacesMessages.instance().add(IConstantsRes.thuoc_up_su, ma);
				
			} catch (Exception e) {
				log.error(e.toString());				
				FacesMessages.instance().add(IConstantsRes.thuoc_up_fa, ma);
			}finally{
				resetValue();
		}
	}
		
		@End
		public String goBack() {
			log.info("GoBack B4151 Mat hang....");
			
			return "/B4_Duocpham/KhoChinh/B4151_mathang";
		}

		private void resetValue() {		
			
			this.mathang = new DmThuoc();
			this.mathang.setDmdonvitinhMaso(new DmDonViTinh());
			this.mathang.setDmcachdungMaso(new DmCachDungThuoc());
			this.mathang.setDmbaoquanMaso(new DmBaoQuanThuoc());
			this.mathang.setDmphannhomthuocMaso(new DmPhanNhomThuoc());
			this.mathang.setDmnhasanxuatMaso(new DmNhaSanXuat());
			this.mathang.setDmphanloaithuocMaso(new DmPhanLoaiThuoc());
			this.mathang.setDmthuocInplinh(true);
			this.mathang.setDmthuocNuocdefa(new DmQuocGia());
			this.mathang.setDmthuocPlbhyt(new Short((short)1));
		}
		
		private void initCbo(){																	//Hàm này dùng để khởi tạo
			log.info("Init Select items for combobox....");										//danh sách các thư mục
			
			initCboDonViTinh();
			initCboBaoquan();
			initCboCachdung();
			initCboNhasanxuat();
			initCboNhomthuoc();
			initCboPhanloaithuoc();
			initCboQuocGia();
			initCboLoaiThuoc();
		}
		
		@SuppressWarnings("unchecked")
		private void initCboQuocGia() {
			log.info("getList_quocgia_item B4152_Dm Thuoc....");
			
			this.listB4151_QuocGia = new  ArrayList<SelectItem>();
			
			List<DmQuocGia> list = (List<DmQuocGia>)DieuTriUtilDelegate.getInstance()
										.findAll("DmQuocGia");
			for (DmQuocGia item : list) {										   			//duyệt qua làn lượt các đối tượng trong list
				listB4151_QuocGia.add(new SelectItem(item.getDmquocgiaMaso(),  			//thuộc tính value của selectIterm
													   item.getDmquocgiaTenvn())); 			//thuộc tính label của selectIterm
			}
			
		}

		@SuppressWarnings("unchecked")
		private void initCboDonViTinh(){															//Khởi tạo danh sách selectIterm for dDonViTinh
			log.info("getList_donvitinh_item B4151_Dm Thuoc....");
			
			this.listB4151_Donvitinh = new  ArrayList<SelectItem>();
			
				
			
			List<DmDonViTinh> list = (List<DmDonViTinh>)DieuTriUtilDelegate.getInstance()
										.findAll("DmDonViTinh", "dmdonvitinhDt", true);
			for (DmDonViTinh item : list) {															//duyệt qua làn lượt các đối tượng trong list			
				listB4151_Donvitinh.add(new SelectItem(item.getDmdonvitinhMaso(), 					//thuộc tính value của selectIterm
													   item.getDmdonvitinhTen()));					//thuộc tính label của selectIterm
			}
			
		}
		
		public List<SelectItem> getListB4151_QuocGia() {
			return listB4151_QuocGia;
		}

		public void setListB4151_QuocGia(List<SelectItem> listB4151QuocGia) {
			listB4151_QuocGia = listB4151QuocGia;
		}

		@SuppressWarnings("unchecked")
		private void initCboBaoquan(){
			log.info("getList_Bao quan_item B4151_Dm Thuoc....");
			
			this.listB4151_Baoquan = new  ArrayList<SelectItem>();			
			this.listB4151_Baoquan.add(new SelectItem(null,"ch\u1ECDn c\u00E1ch b\u1EA3o qu\u1EA3n..."));			
			
			List<DmBaoQuanThuoc> list = (List<DmBaoQuanThuoc>)DieuTriUtilDelegate.getInstance()
											.findAll("DmBaoQuanThuoc", "dmbaoquanthuocDt", true);
			for (DmBaoQuanThuoc item : list) {
				listB4151_Baoquan.add(new SelectItem(item.getDmbaoquanthuocMaso(),
													 item.getDmbaoquanthuocTen()));
			}
		}
		
		@SuppressWarnings("unchecked")
		private void initCboCachdung(){
			log.info("getList_Cach dung_item B4151_Dm Thuoc....");
			
			this.listB4151_Cachdung = new  ArrayList<SelectItem>();
			this.listB4151_Cachdung.add(new SelectItem(null,"ch\u1ECDn c\u00E1ch d\u00F9ng thu\u1ED1c..."));
			
			List<DmCachDungThuoc> list = (List<DmCachDungThuoc>)DieuTriUtilDelegate.getInstance()
											.findAll("DmCachDungThuoc", "dmcachdungthuocDt", true);
			for (DmCachDungThuoc item : list) {
				listB4151_Cachdung.add(new SelectItem(item.getDmcachdungthuocMaso(), 
													  item.getDmcachdungthuocTen()));
			}
		}
		
		@SuppressWarnings("unchecked")
		private void initCboNhomthuoc(){
			log.info("getList_Nhom thuoc_item B4151_Dm Thuoc....");
			
			this.listB4151_Nhomthuoc = new  ArrayList<SelectItem>();
			this.listB4151_Nhomthuoc.add(new SelectItem(null,"ch\u1ECDn nh\u00F3m thu\u1ED1c..."));
						
			List<DmPhanNhomThuoc> list = (List<DmPhanNhomThuoc>)DieuTriUtilDelegate.getInstance()
											.findAll("DmPhanNhomThuoc", "dmphannhomthuocDt", true);
			for (DmPhanNhomThuoc item : list) {
				listB4151_Nhomthuoc.add(new SelectItem(item.getDmphannhomthuocMaso(),
													   item.getDmphannhomthuocTen()));
			}
		}
		
		@SuppressWarnings("unchecked")
		private void initCboNhasanxuat(){
			log.info("getList_Nha san xuat_item B4151_Dm Thuoc....");
			
			this.listB4151_Nhasanxuat = new  ArrayList<SelectItem>();
			this.listB4151_Nhasanxuat.add(new SelectItem(null,"ch\u1ECDn nh\u00E0 s\u1EA3n xu\u1EA5t..."));
			
			List<DmNhaSanXuat> list = (List<DmNhaSanXuat>)DieuTriUtilDelegate.getInstance()
										.findAll("DmNhaSanXuat", "dmnhasanxuatDt", true);
			for (DmNhaSanXuat item : list) {
				listB4151_Nhasanxuat.add(new SelectItem(item.getDmnhasanxuatMaso(), 
														item.getDmnhasanxuatTen()));
			}
		}
		
		@SuppressWarnings("unchecked")
		private void initCboPhanloaithuoc(){
			log.info("getList_Phan loai thuoc_item B4151_Dm Thuoc....");
			
			this.listB4151_Phanloaithuoc = new  ArrayList<SelectItem>();
			this.listB4151_Phanloaithuoc.add(new SelectItem(null,"ch\u1ECDn ph\u00E2n lo\u1EA1i thu\u1ED1c"));
			
			boolean notIn = false;
			Integer dmLoaithuocMaso = 4;//Y dung cu - hardcode
			if(this.mathang.getDmthuocPlbhyt() == 1){
				notIn = true;				
			}
			//List<DmPhanLoaiThuoc> list = (List<DmPhanLoaiThuoc>)DieuTriUtilDelegate.getInstance().getListDmPhanLoaiThuoc(dmLoaithuocMaso, notIn);
			List<DmPhanLoaiThuoc> list = (List<DmPhanLoaiThuoc>)DieuTriUtilDelegate.getInstance().findAll("DmPhanLoaiThuoc", "dmphanloaithuocDt", true);
			for (DmPhanLoaiThuoc item : list) {
				listB4151_Phanloaithuoc.add(new SelectItem(item.getDmphanloaithuocMaso(), 
														   item.getDmphanloaithuocTen()));
			}
		}
		
		@SuppressWarnings("unchecked")
		private void initCboLoaiThuoc(){
			log.info("getList_Loai thuoc_item B4151_Dm Thuoc....");
			
			this.listB4151_Loaithuoc = new  ArrayList<SelectItem>();
			listB4151_Loaithuoc.add(new SelectItem(1, "Nh\u00F3m thu\u1ED1c, h\u00F3a ch\u1EA5t"));
			listB4151_Loaithuoc.add(new SelectItem(10, "Nh\u00F3m v\u1EADt t\u01B0 y t\u1EBF ti\u00EAu hao"));			
		}
		
		public void onChangeLoaithuoc(){
			this.listB4151_Phanloaithuoc = new  ArrayList<SelectItem>();
			boolean notIn = false;
			Integer dmLoaithuocMaso = 4;//Y dung cu - hardcode
			if(this.mathang.getDmthuocPlbhyt() == 1){
				notIn = true;				
			}
			//List<DmPhanLoaiThuoc> list = (List<DmPhanLoaiThuoc>)DieuTriUtilDelegate.getInstance().getListDmPhanLoaiThuoc(dmLoaithuocMaso, notIn);
			List<DmPhanLoaiThuoc> list = (List<DmPhanLoaiThuoc>)DieuTriUtilDelegate.getInstance().findAll("DmPhanLoaiThuoc", "dmphanloaithuocDt", true);
			for (DmPhanLoaiThuoc item : list) {
				listB4151_Phanloaithuoc.add(new SelectItem(item.getDmphanloaithuocMaso(), item.getDmphanloaithuocTen()));
			}
		}

		public DmThuoc getMathang() {
			return mathang;
		}

		public void setMathang(DmThuoc mathang) {
			this.mathang = mathang;
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

		public List<SelectItem> getListB4151_Donvitinh() {
			return listB4151_Donvitinh;
		}

		public void setListB4151_Donvitinh(List<SelectItem> listB4151_Donvitinh) {
			this.listB4151_Donvitinh = listB4151_Donvitinh;
		}

		public List<SelectItem> getListB4151_Baoquan() {
			return listB4151_Baoquan;
		}

		public void setListB4151_Baoquan(List<SelectItem> listB4151_Baoquan) {
			this.listB4151_Baoquan = listB4151_Baoquan;
		}

		public List<SelectItem> getListB4151_Nhomthuoc() {
			return listB4151_Nhomthuoc;
		}

		public void setListB4151_Nhomthuoc(List<SelectItem> listB4151_Nhomthuoc) {
			this.listB4151_Nhomthuoc = listB4151_Nhomthuoc;
		}

		public List<SelectItem> getListB4151_Cachdung() {
			return listB4151_Cachdung;
		}

		public void setListB4151_Cachdung(List<SelectItem> listB4151_Cachdung) {
			this.listB4151_Cachdung = listB4151_Cachdung;
		}

		public List<SelectItem> getListB4151_Nhasanxuat() {
			return listB4151_Nhasanxuat;
		}

		public void setListB4151_Nhasanxuat(List<SelectItem> listB4151_Nhasanxuat) {
			this.listB4151_Nhasanxuat = listB4151_Nhasanxuat;
		}

		public List<SelectItem> getListB4151_Phanloaithuoc() {
			return listB4151_Phanloaithuoc;
		}

		public void setListB4151_Phanloaithuoc(List<SelectItem> listB4151_Phanloaithuoc) {
			this.listB4151_Phanloaithuoc = listB4151_Phanloaithuoc;
		}
		
		public List<SelectItem> getListB4151_Loaithuoc() {
			return listB4151_Loaithuoc;
		}

		public void setListB4151_Loaithuoc(List<SelectItem> listB4151_Loaithuoc) {
			this.listB4151_Loaithuoc = listB4151_Loaithuoc;
		}
		
}

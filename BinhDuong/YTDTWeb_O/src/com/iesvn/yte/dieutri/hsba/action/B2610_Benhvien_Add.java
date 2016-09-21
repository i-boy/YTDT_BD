package com.iesvn.yte.dieutri.hsba.action;

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
import com.iesvn.yte.entity.DmBenhVien;
import com.iesvn.yte.entity.DmLoaiBenhVien;
import com.iesvn.yte.entity.DmTinh;
import com.iesvn.yte.entity.DmTuyen;
import com.iesvn.yte.entity.DmVungMien;
import com.iesvn.yte.util.IConstantsRes;

@Name("B2610_Benhvien_Add")
@Scope(SESSION)
public class B2610_Benhvien_Add implements Serializable{
	
	private static final long serialVersionUID = 1L;
		
		@In(required = false)
		@Out(required = false)
		private  DmBenhVien benhvien;
		
		@Logger
		private Log log;
		
		private List<SelectItem> listB2610_Tinh;
		private List<SelectItem> listB2610_Tuyen;
		private List<SelectItem> listB2610_LoaiBenhVien;
		private List<SelectItem> listB2610_VungMien;

		@Create
		public String init() {
			log.info("Init B2610 Benh vien add....");
			
			resetValue();
			initCbo();
			
			return "/B2_Dieutri/B2610_benhvien_add";
		}
		
		public void save() {
			log.info("Save B2610 Benh vien_add....");
			
			String ma = "";
			Date date = new Date();
			
			ma = this.benhvien.getDmbenhvienMa();
			this.benhvien.setDmbenhvienDt(true);										//Gán thuộc tính true khi thêm mới và về false khi delete		
			this.benhvien.setDmbenhvienNgaygiocn((double)date.getTime());				//Lấy ngày giờ hiện hành ép vể kiểu double
			
			try {
				DieuTriUtilDelegate.getInstance().create(this.benhvien);				//Insert một đối tượng vào database
				FacesMessages.instance().add(IConstantsRes.benhvien_cr_su, ma);
				
			} catch (Exception e) {
				log.error(e.toString());
				FacesMessages.instance().add(IConstantsRes.benhvien_cr_fa, ma);
			}
		}
		
		public void reset() {
			log.info("Reset B2610 Benh vien_add....");

			resetValue();
		}

		private void resetValue() {															//Khởi tạo ban đầu
			
			this.benhvien = new DmBenhVien();
			this.benhvien.setDmtinhMaso(new DmTinh());
			this.benhvien.setDmtuyenMaso(new DmTuyen());
			this.benhvien.setDmloaibvMaso(new DmLoaiBenhVien());
			this.benhvien.setDmvungmienMaso(new DmVungMien());
			
		}
		
/*		public void give_Event_Combobox(Object para){
			Integer Intl = new Integer("0");
			Intl = (Integer)para;
			initCboHuyen(Intl);
			
		}*/
		
		private void initCbo(){																//Hàm này dùng để khởi tạo
			log.info("Init Select items for combobox....");									//danh sách các thư mục
			
			initCboTinh();
			//initCboHuyen();
			initCboTuyen();
			initCboLoaiBenhVien();
			initCboVungMien();
		}
		
		@SuppressWarnings("unchecked")
		private void initCboTinh(){													//Khởi tạo danh sách selectIterm for dDonViTinh
			log.info("getList_Tinh_item B2610 Benh vien_add....");
			
			this.listB2610_Tinh = new  ArrayList<SelectItem>();
			
			List<DmTinh> list = (List<DmTinh>)DieuTriUtilDelegate.getInstance()
										.findAll("DmTinh");
			for (DmTinh item : list) {										   			//duyệt qua làn lượt các đối tượng trong list
				listB2610_Tinh.add(new SelectItem(item.getDmtinhMaso(),  			//thuộc tính value của selectIterm
													   item.getDmtinhTen())); 			//thuộc tính label của selectIterm
			}
			
		}
		
		/*		@SuppressWarnings("unchecked")
		private void initCboHuyen(Integer abc){
			log.info("getList_Huyen_item B2610 Benh vien_add....");
			
			this.listB2610_Huyen = new  ArrayList<SelectItem>();
			List<DmHuyen> list = (List<DmHuyen>)DieuTriUtilDelegate.getInstance()
											.findAllChacon("DmHuyen", "dmhuyenMaso", abc);
			for (DmHuyen item : list) {
				listB2610_Huyen.add(new SelectItem(item.getDmhuyenMaso(), 
													 item.getDmhuyenTen()));
			}
		}*/
		
		@SuppressWarnings("unchecked")
		private void initCboTuyen(){
			log.info("getList_Tuyen_item B2610 Benh vien_add....");
			
			this.listB2610_Tuyen = new  ArrayList<SelectItem>();
			List<DmTuyen> list = (List<DmTuyen>)DieuTriUtilDelegate.getInstance()
											.findAll("DmTuyen", "dmtuyenDt", true);
			for (DmTuyen item : list) {
				listB2610_Tuyen.add(new SelectItem(item.getDmtuyenMaso(),
													  item.getDmtuyenTen()));
			}
		}
		
		@SuppressWarnings("unchecked")
		private void initCboLoaiBenhVien(){
			log.info("getList_Loai Benh Vien _item B2610 Benh vien_add....");
			
			this.listB2610_LoaiBenhVien = new  ArrayList<SelectItem>();
			List<DmLoaiBenhVien> list = (List<DmLoaiBenhVien>)DieuTriUtilDelegate.getInstance()
											.findAll("DmLoaiBenhVien", "dmloaibvDt", true);
			for (DmLoaiBenhVien item : list) {
				listB2610_LoaiBenhVien.add(new SelectItem(item.getDmloaibvMaso(), 
													   item.getDmloaibvTen()));
			}
		}
		
		@SuppressWarnings("unchecked")
		private void initCboVungMien(){
			log.info("getList_Vung Mien_item B2610 Benh vien_add....");
			
			this.listB2610_VungMien = new  ArrayList<SelectItem>();
			List<DmVungMien> list = (List<DmVungMien>)DieuTriUtilDelegate.getInstance()
										.findAll("DmVungMien");
			for (DmVungMien item : list) {
				listB2610_VungMien.add(new SelectItem(item.getDmvungmienMaso(), 
														item.getDmvungmienTen()));
			}
		}

		public DmBenhVien getBenhvien() {
			return benhvien;
		}

		public void setBenhvien(DmBenhVien benhvien) {
			this.benhvien = benhvien;
		}

		public Log getLog() {
			return log;
		}

		public void setLog(Log log) {
			this.log = log;
		}

		public List<SelectItem> getListB2610_Tinh() {
			return listB2610_Tinh;
		}

		public void setListB2610_Tinh(List<SelectItem> listB2610_Tinh) {
			this.listB2610_Tinh = listB2610_Tinh;
		}

		public List<SelectItem> getListB2610_Tuyen() {
			return listB2610_Tuyen;
		}

		public void setListB2610_Tuyen(List<SelectItem> listB2610_Tuyen) {
			this.listB2610_Tuyen = listB2610_Tuyen;
		}

		public List<SelectItem> getListB2610_LoaiBenhVien() {
			return listB2610_LoaiBenhVien;
		}

		public void setListB2610_LoaiBenhVien(List<SelectItem> listB2610_LoaiBenhVien) {
			this.listB2610_LoaiBenhVien = listB2610_LoaiBenhVien;
		}

		public List<SelectItem> getListB2610_VungMien() {
			return listB2610_VungMien;
		}

		public void setListB2610_VungMien(List<SelectItem> listB2610_VungMien) {
			this.listB2610_VungMien = listB2610_VungMien;
		}

		public static long getSerialVersionUID() {
			return serialVersionUID;
		}	
}
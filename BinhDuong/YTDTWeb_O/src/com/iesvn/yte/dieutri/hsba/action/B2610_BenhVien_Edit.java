package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmBenhVien;
import com.iesvn.yte.entity.DmLoaiBenhVien;
import com.iesvn.yte.entity.DmTinh;
import com.iesvn.yte.entity.DmTuyen;
import com.iesvn.yte.entity.DmVungMien;
import com.iesvn.yte.util.IConstantsRes;

@Name("B2610_BenhVien_Edit")
@Scope(SESSION)
public class B2610_BenhVien_Edit implements Serializable{
	
	private static final long serialVersionUID = 1L;

		@In(required = false)
		@Out(required = false)
		private  DmBenhVien benhvien;
		
		private List<SelectItem> listB2610_Tinh;
		private List<SelectItem> listB2610_Tuyen;
		private List<SelectItem> listB2610_LoaiBenhVien;
		private List<SelectItem> listB2610_VungMien;
		
		@Logger
		private Log log;

		@Create
		public String init() {
			log.info("Init B2610 Benh vien Edit....");
			
			resetValue();
			load_rowIndex();
			initCbo();		
			return "/B2_Dieutri/B2610_benhvien_edit";
		}
		
		public void reset() {
			log.info("Reset B2610 Benh vien Edit....");

			resetValue();
		}
		
		@SuppressWarnings("unchecked")
		public void load_rowIndex(){
			log.info("Load rowIndex B2610 Benh vien Edit ....");
			
			int rowIndex = -1;
			
			ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listB2610");
			rowIndex = lsDataModel.getRowIndex();
			
			if (rowIndex != -1) {
				this.benhvien = ((List<DmBenhVien>)lsDataModel.getWrappedData()).get(rowIndex);
				
				if((this.benhvien.getDmtinhMaso())== null){  						//Kiểm tra nếu đối tượng hiện hành là null thì new đối tuợng mới
					this.benhvien.setDmtinhMaso(new DmTinh());
				}
				
				if((this.benhvien.getDmtuyenMaso())==null){
					this.benhvien.setDmtuyenMaso(new DmTuyen());
				}
				
				if((this.benhvien.getDmloaibvMaso())==null){
					this.benhvien.setDmloaibvMaso(new DmLoaiBenhVien());
				}
				
				if((this.benhvien.getDmvungmienMaso())==null){
					this.benhvien.setDmvungmienMaso(new DmVungMien());
				}				
			}
			
			log.info("rowIndex =" + rowIndex );		
		}
		
		public void save() {
			log.info("Save B2610 Benh vien Edit....");
			
			String ma = "";
			Date date = new Date();
			this.benhvien.setDmbenhvienNgaygiocn((double)date.getTime());									//Lấy ngày giờ hiện hành ép vể kiểu double
			
			ma = this.benhvien.getDmbenhvienMa();
			
			
			this.benhvien.setDmbenhvienDt(true);															//Gán thuộc tính true khi thêm mới và về false khi delete
			
			if((this.benhvien.getDmtinhMaso().getDmtinhMaso())== null){
				this.benhvien.setDmtinhMaso(null);
			}
			if((this.benhvien.getDmtuyenMaso().getDmtuyenMaso())==null){
				this.benhvien.setDmtuyenMaso(null);
			}
			
			if((this.benhvien.getDmloaibvMaso().getDmloaibvMaso())==null){
				this.benhvien.setDmloaibvMaso(null);
			}
			
			if((this.benhvien.getDmvungmienMaso().getDmvungmienMaso())==null){
				this.benhvien.setDmvungmienMaso(null);
			}
			
			try {	
				
				DieuTriUtilDelegate.getInstance().edit(this.benhvien);									//Edit một đối tượng vào database
				FacesMessages.instance().add(IConstantsRes.benhvien_up_su, ma);
				
			} catch (Exception e) {
				log.error(e.toString());				
				
				FacesMessages.instance().add(IConstantsRes.benhvien_up_fa, ma);		
			}finally{
				resetValue();
		}
	}
		

		private void resetValue() {		
			
			this.benhvien = new DmBenhVien();
			this.benhvien.setDmtinhMaso(new DmTinh());
			this.benhvien.setDmloaibvMaso(new DmLoaiBenhVien());
			this.benhvien.setDmvungmienMaso(new DmVungMien());
			this.benhvien.setDmtuyenMaso(new DmTuyen());
		}
		
		private void initCbo(){																	//Hàm này dùng để khởi tạo
			log.info("Init Select items for combobox....");										//danh sách các thư mục
			
			initCboTinh();
			initCboTuyen();
			initCboLoaibv();
			initCboVungmien();
		}
		
		@SuppressWarnings("unchecked")
		private void initCboTinh(){															//Khởi tạo danh sách selectIterm for dDonViTinh
			log.info("getList_tinh_item B2610 Benh vien Edit....");
			
			this.listB2610_Tinh = new  ArrayList<SelectItem>();
			
				this.listB2610_Tinh.add(new SelectItem(null,"chọn tỉnh..."));								//Tạo một dòng trắng cho combobox
			
			List<DmTinh> list = (List<DmTinh>)DieuTriUtilDelegate.getInstance()
										.findAll("DmTinh");
			for (DmTinh item : list) {															//duyệt qua làn lượt các đối tượng trong list			
				listB2610_Tinh.add(new SelectItem(item.getDmtinhMaso(), 					//thuộc tính value của selectIterm
													   item.getDmtinhTen()));					//thuộc tính label của selectIterm
			}
			
		}
		
		@SuppressWarnings("unchecked")
		private void initCboTuyen(){
			log.info("getList_Tuyen_item B2610 Benh vien Edit....");
			
			this.listB2610_Tuyen = new  ArrayList<SelectItem>();			
			this.listB2610_Tuyen.add(new SelectItem(null,"chọn tuyến..."));			
			
			List<DmTuyen> list = (List<DmTuyen>)DieuTriUtilDelegate.getInstance()
											.findAll("DmTuyen", "dmtuyenDt", true);
			for (DmTuyen item : list) {
				listB2610_Tuyen.add(new SelectItem(item.getDmtuyenMaso(),
													 item.getDmtuyenTen()));
			}
		}
		
		@SuppressWarnings("unchecked")
		private void initCboLoaibv(){
			log.info("getList_Loai benhvien_item B2610 Benh vien Edit....");
			
			this.listB2610_LoaiBenhVien = new  ArrayList<SelectItem>();
			this.listB2610_LoaiBenhVien.add(new SelectItem(null,"Chọn lọai bệnh viện..."));
			
			List<DmLoaiBenhVien> list = (List<DmLoaiBenhVien>)DieuTriUtilDelegate.getInstance()
											.findAll("DmLoaiBenhVien", "dmloaibvDt", true);
			for (DmLoaiBenhVien item : list) {
				listB2610_LoaiBenhVien.add(new SelectItem(item.getDmloaibvMaso(), 
													  item.getDmloaibvTen()));
			}
		}
		
		@SuppressWarnings("unchecked")
		private void initCboVungmien(){
			log.info("getList_Vungmien_item B2610 Benh vien Edit....");
			
			this.listB2610_VungMien = new  ArrayList<SelectItem>();
			this.listB2610_VungMien.add(new SelectItem(null,"Chọn vùng miền..."));
						
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

		public Log getLog() {
			return log;
		}

		public void setLog(Log log) {
			this.log = log;
		}

		public static long getSerialVersionUID() {
			return serialVersionUID;
		}
		
		
}

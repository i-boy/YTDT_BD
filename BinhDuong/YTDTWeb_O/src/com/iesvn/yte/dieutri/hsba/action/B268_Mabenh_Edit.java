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
import com.iesvn.yte.entity.DmBenhIcd;
import com.iesvn.yte.entity.DmChuongBenh;
import com.iesvn.yte.util.IConstantsRes;

@Name("B268_Mabenh_Edit")
@Scope(SESSION)
public class B268_Mabenh_Edit implements Serializable{
	
	private static final long serialVersionUID = 1L;

		@In(required = false)
		@Out(required = false)
		private  DmBenhIcd mabenh;
		
		private List<SelectItem> listB268_Chuongbenh;

		@Logger
		private Log log;

		@Create
		public String init() {
			log.info("Init B268_Ma benh Edit....");
			
			resetValue();
			initCbo();
			this.load_rowIndex();
			
			return "/B2_Dieutri/B268_mabenh_edit";
		}
		
		public void save() {
			log.info("Save B268_mabenh_edit....");
			
			String ma = "";
			Date date = new Date();
			
			ma = this.mabenh.getDmbenhicdMa();			
			this.mabenh.setDmbenhicdDt(true);															//Gán thuộc tính true khi thêm mới và về false khi delete
			this.mabenh.setDmbenhicdNgaygiocn((double)date.getTime());								//Lấy ngày giờ hiện hành ép vể kiểu double
			
			try {
				if(this.mabenh.getDmchuongbenhMaso(true).getDmchuongbenhMaso() == null){
					this.mabenh.setDmchuongbenhMaso(null);
			}			
				this.mabenh.setDmbenhicdNgaygiocn((double)date.getTime());								//Lấy ngày giờ hiện hành ép vể kiểu double
				DieuTriUtilDelegate.getInstance().edit(this.mabenh);									//Edit một đối tượng vào database
				FacesMessages.instance().add(IConstantsRes.mabenh_up_su, ma);
				
			} catch (Exception e) {
				log.error(e.toString());
				
				FacesMessages.instance().add(IConstantsRes.mabenh_up_fa, ma);
			}finally{
				resetValue();
			}
		}
		
		public void reset() {
			log.info("Reset  B268_mabenh_edit....");

			resetValue();
		}
		
		@SuppressWarnings("unchecked")
		private void load_rowIndex(){
			log.info("Load rowIndex  B268_mabenh_edit ....");
			
			int rowIndex = -1;
			
			ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listB268");
			rowIndex = lsDataModel.getRowIndex();
			
			if (rowIndex != -1) {
				this.mabenh = ((List<DmBenhIcd>)lsDataModel.getWrappedData()).get(rowIndex);
				
				if(this.mabenh.getDmchuongbenhMaso() == null){
					this.mabenh.setDmchuongbenhMaso(new DmChuongBenh());
				}
			}	
			
		}

		private void resetValue() {		
			
			this.mabenh = new DmBenhIcd();
			this.mabenh.setDmchuongbenhMaso(new DmChuongBenh());			
		}
		
		private void initCbo(){																	//Hàm này dùng để khởi tạo
			log.info("Init Select items for combobox....");										//danh sách các thư mục
			
			initCboChuongbenh();
		}
		
		@SuppressWarnings("unchecked")
		private void initCboChuongbenh(){																//Khởi tạo danh sách selectIterm for dDonViTinh
			log.info("getList_Chuongbenh_edit B268_mabenh_edit....");
			
			this.listB268_Chuongbenh = new  ArrayList<SelectItem>();
			this.listB268_Chuongbenh.add(new SelectItem(null,"..."));
			
			List<DmChuongBenh> list = (List<DmChuongBenh>)DieuTriUtilDelegate.getInstance()
										.findAll("DmChuongBenh", "dmchuongbenhDt", true);
			for (DmChuongBenh item : list) {															//duyệt qua làn lượt các đối tượng trong list			
				listB268_Chuongbenh.add(new SelectItem(item.getDmchuongbenhMaso(), 						//thuộc tính value của selectIterm
													   item.getDmchuongbenhTen()));						//thuộc tính label của selectIterm
			}
			
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

		public DmBenhIcd getMabenh() {
			return mabenh;
		}

		public void setMabenh(DmBenhIcd mabenh) {
			this.mabenh = mabenh;
		}

		public List<SelectItem> getListB268_Chuongbenh() {
			return listB268_Chuongbenh;
		}

		public void setListB268_Chuongbenh(List<SelectItem> listB268_Chuongbenh) {
			this.listB268_Chuongbenh = listB268_Chuongbenh;
		}	
}
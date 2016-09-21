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
import com.iesvn.yte.entity.DmNgheNghiep;
import com.iesvn.yte.entity.DmNgheNghiepBaoCao;
import com.iesvn.yte.util.IConstantsRes;

@Name("B269_Nghenghiep_Add")
@Scope(SESSION)
public class B269_Nghenghiep_Add implements Serializable{
	
	private static final long serialVersionUID = 1L;

		@In(required = false)
		@Out(required = false)
		private  DmNgheNghiep nghenghiep;
		
		@Logger
		private Log log;
		
		//Khai báo danh sách các danh mục 
		private List<SelectItem> listB269_Phanloai;

		@Create
		public String init() {
			log.info("Init B269 Nghe nghiep....");
			
			resetValue();
			initCbo();
			
			return "/B2_Dieutri/B269_nghenghiep_add";
		}
		
		public void save() {
			log.info("Save B269_Nghe nghiep_add....");
			
			String ma = "";
			Date date = new Date();
			
			ma = this.nghenghiep.getDmnghenghiepMa();
			this.nghenghiep.setDmnghenghiepDt(true);										//Gán thuộc tính true khi thêm mới và về false khi delete			
			this.nghenghiep.setDmnghenghiepNgaygiocn((double)date.getTime());				//Lấy ngày giờ hiện hành ép vể kiểu double
			
			if(this.nghenghiep.getDmnghenghiepPhanloai(true).getDmnghenghiepbcMaso() == null){
				this.nghenghiep.setDmnghenghiepPhanloai(null);
			}
			
			try {
				DieuTriUtilDelegate.getInstance().create(this.nghenghiep);				//Insert một đối tượng vào database
				FacesMessages.instance().add(IConstantsRes.nghenghiep_cr_su, ma);
				
			} catch (Exception e) {
				log.error(e.toString());
				FacesMessages.instance().add(IConstantsRes.nghenghiep_cr_fa, ma);
			}finally{
				resetValue();
			}
		}

		public String goBack() {
			log.info("GoBack B269 Nghe nghiep....");
			
			return "/B2_Dieutri/B269_nghenghiep";
		}
		
		public void reset() {
			log.info("Reset B269 Nghe nghiep add....");

			resetValue();
		}

		private void resetValue() {															//Khởi tạo ban đầu
			
			this.nghenghiep = new DmNgheNghiep();
			this.nghenghiep.setDmnghenghiepPhanloai(new DmNgheNghiepBaoCao());
			
		}
		
		private void initCbo(){																//Hàm này dùng để khởi tạo
			log.info("Init Select items for combobox....");									//danh sách các thư mục
			
			initCboPhanloai();
		}
		
		@SuppressWarnings("unchecked")
		private void initCboPhanloai(){													//Khởi tạo danh sách selectIterm for dDonViTinh
			log.info("getList_Phanloai_item B269_Dm Nghe nghiep....");
			
			this.listB269_Phanloai = new  ArrayList<SelectItem>();
			
			List<DmNgheNghiepBaoCao> list = (List<DmNgheNghiepBaoCao>)DieuTriUtilDelegate.getInstance()
										.findAll("DmNgheNghiepBaoCao", "dmnghenghiepbcDt", true);
			for (DmNgheNghiepBaoCao item : list) {										   			//duyệt qua làn lượt các đối tượng trong list
				listB269_Phanloai.add(new SelectItem(item.getDmnghenghiepbcMaso(),  			//thuộc tính value của selectIterm
													   item.getDmnghenghiepbcTen())); 			//thuộc tính label của selectIterm
			}		
		}
				
		public DmNgheNghiep getNghenghiep() {
			return nghenghiep;
		}

		public void setNghenghiep(DmNgheNghiep nghenghiep) {
			this.nghenghiep = nghenghiep;
		}

		public Log getLog() {
			return log;
		}

		public void setLog(Log log) {
			this.log = log;
		}

		public List<SelectItem> getListB269_Phanloai() {
			return listB269_Phanloai;
		}

		public void setListB269_Phanloai(List<SelectItem> listB269_Phanloai) {
			this.listB269_Phanloai = listB269_Phanloai;
		}

		public static long getSerialVersionUID() {
			return serialVersionUID;
		}
		
}

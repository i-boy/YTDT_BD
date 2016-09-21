package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmNgheNghiep;
import com.iesvn.yte.util.IConstantsRes;


@Name("B269_Nghenghiep")
@Scope(SESSION)
public class B269_Nghenghiep implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@In(required = false)
	@Out(required = false)
	private  DmNgheNghiep nghenghiep;

	@DataModel
	private List<DmNgheNghiep> listB269;

	@Logger
	private Log log;

	@Create  																//Bát buộc phải có đối với mỗi action				
	public String init() {													//hàm khởi tạo mỗi khi vào
		log.info("Init B269_Mathang....");									//log ra màn hình để biết chạy đến đâu
		
		resetValue();														//Khởi tạo giá trị ban đầu
		
		return "/B2_Dieutri/B269_nghenghiep";   							//Trả vè trang có đường dẫn tương ứng
	}
	
	@SuppressWarnings("unchecked")
	public String search() {													//Tìm kiếm tất cả các đối tượng hiện hành
		log.info("Search B269_Dm Nghe nghiep....");
		
		this.listB269 = (List<DmNgheNghiep>)DieuTriUtilDelegate.getInstance()	//Load tất cả các danh mục thuốc hiện có gán vào một danh sách
							.findByAllConditions("DmNgheNghiep", "dmnghenghiepMa", "dmnghenghiepTen",
									"dmnghenghiepDt",this.nghenghiep.getDmnghenghiepMa(), 
										this.nghenghiep.getDmnghenghiepTen(),true);
		
		if (this.listB269.size() == 0) { 										//Kiểm tra xem danh sách có phần tử nào không
			FacesMessages.instance().add("Không Tìm thấy Kết quả nào");			//Gủi thông báo ra màn hình thông qua đối tượng message
		}
		
		return "/B2_Dieutri/B269_nghenghiep";
	}
	
	public void delete(int rowIndex) {											//Delete đối tượng hiện hành (rowIndex) khi chọn vào một dòng của dataTable
		log.info("Delete B269_Dm Nghe nghiep....");

		if (rowIndex != -1) {
			
			String ma = "";
			ma = this.listB269.get(rowIndex).getDmnghenghiepMa();					//Truy xuất mã của đối tượng hiện hành (rowIndex)
			DmNgheNghiep dm = new DmNgheNghiep();
			dm = this.listB269.get(rowIndex);									//Lấy đối tượng hiện hành có chỉ số rowIndex			
			dm.setDmnghenghiepDt(false);										//Thuộc tính này set về false khi ta delete
			
			try {				
				DieuTriUtilDelegate.getInstance().edit(dm);							//Cac đối tượng bị delete chỉ cần set thuộc tính "ta qui định" về false
				this.listB269.remove(rowIndex);  													//Load lại danh sách 
				FacesMessages.instance().add(IConstantsRes.nghenghiep_de_su, ma);

			} catch (Exception e) {
				log.error(e.toString());
				FacesMessages.instance().add(IConstantsRes.nghenghiep_de_fa, ma);
			}
		}
	}
	
	public void reset() {													//Đưa các đối tượng về trạng thái ban đầu
		log.info("Reset B269_Nghe nghiep....");

		resetValue();
	}

	private void resetValue() {
		this.nghenghiep = new DmNgheNghiep();
		this.listB269 = new ArrayList<DmNgheNghiep>(); 						//Chú ý phải new một đối tượng List Trước khí add
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
	

	public DmNgheNghiep getNghenghiep() {
		return nghenghiep;
	}

	public void setNghenghiep(DmNgheNghiep nghenghiep) {
		this.nghenghiep = nghenghiep;
	}

	public List<DmNgheNghiep> getListB269() {
		return listB269;
	}

	public void setListB4151(List<DmNgheNghiep> listB269) {
		this.listB269 = listB269;
	}
}

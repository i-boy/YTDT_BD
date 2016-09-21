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
import com.iesvn.yte.entity.DmBenhIcd;
import com.iesvn.yte.util.IConstantsRes;


@Name("B268_Mabenh")
@Scope(SESSION)
public class B268_Mabenh implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@In(required = false)
	@Out(required = false)
	private  DmBenhIcd mabenh;

	@DataModel
	private List<DmBenhIcd> listB268;

	@Logger
	private Log log;

	@Create  																//Bát buộc phải có đối với mỗi action				
	public String init() {													//hàm khởi tạo mỗi khi vào
		log.info("Init B268_MabenhICD....");									//log ra màn hình để biết chạy đến đâu
		
		resetValue();														//Khởi tạo giá trị ban đầu
		
		return "/B2_Dieutri/B268_mabenh";   							//Trả vè trang có đường dẫn tương ứng
	}
	
	@SuppressWarnings("unchecked")
	public String search() {													//Tìm kiếm tất cả các đối tượng hiện hành
		log.info("Search B268_MabenhICD....");
		
		this.listB268 = (List<DmBenhIcd>)DieuTriUtilDelegate.getInstance()	//Load tất cả các danh mục thuốc hiện có gán vào một danh sách
							.findByAllConditions("DmBenhIcd", "dmbenhicdMa", "dmbenhicdTen",
									"dmbenhicdDt",this.mabenh.getDmbenhicdMa(), 
										this.mabenh.getDmbenhicdTen(),true);
		
		if (this.listB268.size() == 0) { 										//Kiểm tra xem danh sách có phần tử nào không
			FacesMessages.instance().add(IConstantsRes.no_found);			//Gủi thông báo ra màn hình thông qua đối tượng message
		}
		
		return "/B2_Dieutri/B268_mabenh";
	}
	
	public void delete(int rowIndex) {											//Delete đối tượng hiện hành (rowIndex) khi chọn vào một dòng của dataTable
		log.info("Delete B268_MabenhICD....");

		if (rowIndex != -1) {
			
			String ma = "";
			ma = this.listB268.get(rowIndex).getDmbenhicdMa();										//Truy xuất mã của đối tượng hiện hành (rowIndex)
			DmBenhIcd dm = new DmBenhIcd();
			dm = this.listB268.get(rowIndex);														//Lấy đối tượng hiện hành có chỉ số rowIndex			
			dm.setDmbenhicdDt(false);																//Thuộc tính này set về false khi ta delete
			
			try {				
				DieuTriUtilDelegate.getInstance().edit(dm);											//Cac đối tượng bị delete chỉ cần set thuộc tính "ta qui định" về false
				this.listB268.remove(rowIndex);  													//Load lại danh sách 
				FacesMessages.instance().add("Xóa thành công loại bệnh mã " + ma);

			} catch (Exception e) {
				log.error(e.toString());
				FacesMessages.instance().add("Xóa thất bại loại bệnh mã " + ma);
			}
		}
	}
	
	public void reset() {																			//Đưa các đối tượng về trạng thái ban đầu
		log.info("Reset B268_MabenhICD....");

		resetValue();
	}

	private void resetValue() {
		this.mabenh = new DmBenhIcd();
		this.listB268 = new ArrayList<DmBenhIcd>(); 						//Chú ý phải new một đối tượng List Trước khí add
	}
	

	public DmBenhIcd getMabenh() {
		return mabenh;
	}

	public void setMabenh(DmBenhIcd mabenh) {
		this.mabenh = mabenh;
	}

	public List<DmBenhIcd> getListB268() {
		return listB268;
	}

	public void setListB268(List<DmBenhIcd> listB268) {
		this.listB268 = listB268;
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

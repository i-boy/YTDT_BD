package com.iesvn.yte.dieutri.duocpham.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import com.iesvn.yte.dieutri.delegate.BaithuocThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
import com.iesvn.yte.dieutri.entity.BaithuocThuoc;
import com.iesvn.yte.entity.DmPhanLoaiThuoc;
import com.iesvn.yte.entity.DmThuoc;
import com.iesvn.yte.util.IConstantsRes;


@Name("B4151_Mathang")
@Scope(SESSION)
public class B4151_Mathang implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private  DmThuoc mathang;

	@DataModel
	private List<DmThuoc> listB4151;
	private List<SelectItem> listB4151_Phanloaithuoc;
	

	@Logger
	private Log log;

	@Create  																//Bát buộc phải có đối với mỗi action				
	public String init() {													//hàm khởi tạo mỗi khi vào
		log.info("Init B4151_Mathang....");									//log ra màn hình để biết chạy đến đâu
		
		resetValue();														//Khởi tạo giá trị ban đầu
		this.listB4151_Phanloaithuoc = new  ArrayList<SelectItem>();
		List<DmPhanLoaiThuoc> list = (List<DmPhanLoaiThuoc>)DieuTriUtilDelegate.getInstance()
										.findAll("DmPhanLoaiThuoc", "dmphanloaithuocDt", true);
		listB4151_Phanloaithuoc.add(new SelectItem(null, ""));
		for (DmPhanLoaiThuoc item : list) {
			listB4151_Phanloaithuoc.add(new SelectItem(item.getDmphanloaithuocMaso(), 
													   item.getDmphanloaithuocTen()));
		}
		return "/B4_Duocpham/KhoChinh/B4151_mathang";   					//Trả vè trang có đường dẫn tương ứng
	}

	@SuppressWarnings("unchecked")
	public String search() {													//Tìm kiếm tất cả các đối tượng hiện hành
		log.info("Search 4151_Dm Thuoc.... ma phan loai  = " + mathang.getDmphanloaithuocMaso());

		//this.listB4151 = (List<DmThuoc>)DieuTriUtilDelegate.getInstance()
		//.findByAllConditions("DmThuoc", "dmthuocMa", "dmthuocTen", "dmthuocDt",
		//					this.mathang.getDmthuocMa(), this.mathang.getDmthuocTen(),true);  ////Load tất cả các danh mục thuốc hiện có gán vào một danh sách
		
		//this.listB4151 = (List<DmThuoc>)DieuTriUtilDelegate.getInstance().findByAllConditionsSortAsc("DmThuoc", "dmthuocMa", "dmthuocTen", "dmthuocDt",this.mathang.getDmthuocMa(), this.mathang.getDmthuocTen(),true);  //bao.ttc: sort by name ASC   Load tất cả các danh mục thuốc hiện có gán vào một danh sách		
		this.listB4151 = (List<DmThuoc>)DieuTriUtilDelegate.getInstance().findByAllConditionsSortAsc("DmThuoc", "dmthuocMa", "dmthuocTen", "dmthuocDt","dmphanloaithuocMaso.dmphanloaithuocMaso",this.mathang.getDmthuocMa().toUpperCase(), this.mathang.getDmthuocTen(),mathang.getDmphanloaithuocMaso().getDmphanloaithuocMaso());  //phuc.lc: Search gan dung theo ma, search theo phan loai thuoc
		
		if (this.listB4151.size() == 0) { 										//Kiểm tra xem danh sách có phần tử nào không
			FacesMessages.instance().add(IConstantsRes.no_found);			//Gủi thông báo ra màn hình thông qua đối tượng message
		}
		
		return "/B4_Duocpham/KhoChinh/B4151_mathang";
	}
	
	public void delete(int rowIndex) {											//Delete đối tượng hiện hành (rowIndex) khi chọn vào một dòng của dataTable
		log.info("Delete B4151_Dm Thuoc....");

		if (rowIndex != -1) {
			String ma = "";
			ma = this.listB4151.get(rowIndex).getDmthuocMa();					//Truy xuất mã của đối tượng hiện hành (rowIndex)		
			try {
				DmThuoc dm = new DmThuoc();
				dm = this.listB4151.get(rowIndex);								//Lấy đối tượng hiện hành có chỉ số rowIndex			
				dm.setDmthuocDt(false);											//Thuộc tính này set về false khi ta delete
				
				dm.setDmthuocNgaygiocn((double)(new Date()).getTime());
				//Tho add 20-01-2011 Neu con ton (>0) trong tat ca cac khoa/kho (ngoai tru kho thanh ly va dv tuyen duoi) thi khong duoc xoa
				TonKhoDelegate tonkhoDelegate = TonKhoDelegate.getInstance();
				if(!tonkhoDelegate.checkTonKhoHienTai(dm.getDmthuocMaso())){
					DieuTriUtilDelegate.getInstance().edit(dm);	//Cac đối tượng bị delete chỉ cần set thuộc tính "ta qui định" về false
					
					//Xoa vi thuoc nay ra khoi bai thuoc thuoc
					BaithuocThuocDelegate baithuocThuocDel = BaithuocThuocDelegate.getInstance();
					baithuocThuocDel.deleteAllBaithuocThuocsByMasoThuoc(dm.getDmthuocMaso());
					
					this.search();  												//Load lại danh sách 
					FacesMessages.instance().add(IConstantsRes.thuoc_de_su, ma);
				}else{
					FacesMessages.instance().add(IConstantsRes.thuoc_de_war, ma);
				}
			} catch (Exception e) {
				log.error(e.toString());
				FacesMessages.instance().add(IConstantsRes.thuoc_de_fa, ma);
			}
		}
	}
	
	
	public void reset() {													//Đưa các đối tượng về trạng thái ban đầu
		log.info("Reset B4151_Mathang....");

		resetValue();
	}

	private void resetValue() {
		
		this.mathang = new DmThuoc();
		this.mathang.setDmphanloaithuocMaso(new DmPhanLoaiThuoc());
		this.listB4151 = new ArrayList<DmThuoc>(); 							//Chú ý phải new một đối tượng List Trước khí add
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

	public DmThuoc getMathang() {
		return mathang;
	}

	public void setMathang(DmThuoc mathang) {
		this.mathang = mathang;
	}

	public List<DmThuoc> getListB4151() {
		return listB4151;
	}

	public void setListB4151(List<DmThuoc> listB4151) {
		this.listB4151 = listB4151;
	}
	public List<SelectItem> getListB4151_Phanloaithuoc() {
		return listB4151_Phanloaithuoc;
	}

	public void setListB4151_Phanloaithuoc(List<SelectItem> listB4151Phanloaithuoc) {
		listB4151_Phanloaithuoc = listB4151Phanloaithuoc;
	}
}
package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.jsf.ListDataModel;
import org.jboss.seam.log.Log;

import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.util.IConstantsRes;

@Name("B263_NhanVien_edit")
@Scope(SESSION)
public class B263_Nhanvien_edit implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private DtDmNhanVien nhanvien;

	@Logger
	private Log log;

	public DtDmNhanVien getNhanvien() {
		return nhanvien;
	}

	public void setNhanvien(DtDmNhanVien nhanvien) {
		this.nhanvien = nhanvien;
	}
	
	@Create
	public String init() {
		log.info("Init nhanvien_edit...");
		
		resetValue();
		setValueForForm();
		
		return "/B2_Dieutri/B263_Nhanvien_edit";
	}

	public void save() {
		log.info("Save nhanvien...");
		
		String ma = "";
		
		ma = this.nhanvien.getDtdmnhanvienMa();
		setValueDmNhanVien();
		
		try {
			
			//Bo sung 3 ky tu sau trong 'ma nhan vien'
			/*String nvMaTemp = this.nhanvien.getDtdmnhanvienMa();
			int count1 = 0;
			
			DtDmNhanVienDelegate nhanvienDAO = DtDmNhanVienDelegate.getInstance();
			
			for (DtDmNhanVien each : nhanvienDAO.findAll()) {
				if(each.getDtdmnhanvienMa().length() > 5){
					if(nvMaTemp.equals((each.getDtdmnhanvienMa()).substring(0, 3))){
						int count1_temp = Integer.parseInt(each.getDtdmnhanvienMa().substring(3, 6));
						if(count1 <= count1_temp){
							count1 = count1_temp;
						}			
					}
				}				
			}
			count1 = count1 + 1;
			if((count1+"").length() == 1){
				if(count1 == 0){
					nvMaTemp = nvMaTemp + "001";
				}
				else{
					nvMaTemp = nvMaTemp + "00" + count1 + "";
				}	
			}
			else if((count1+"").length() == 2){
				nvMaTemp = nvMaTemp + "0" + count1 + "";
			}
			else if((count1+"").length() == 3){
				nvMaTemp = nvMaTemp + count1 + "";
			}
			else{
				nvMaTemp = nvMaTemp + "999";
			}		
			this.nhanvien.setDtdmnhanvienMa(nvMaTemp);*/
			
			DtDmNhanVienDelegate.getInstance().edit(this.nhanvien);
			FacesMessages.instance().add(IConstantsRes.nhanvien_up_su, ma);
			
		} catch (Exception e) {
			log.error(e.toString());
			FacesMessages.instance().add(IConstantsRes.nhanvien_up_fa, ma);
		}
	}

	public String goBack() {
		log.info("GoBack Danhsachnhanvien...");
		
		return "/B2_Dieutri/B263_Nhanvien";
	}
	
	public void setValueDmNhanVien() {
		Date date = new Date();
		this.nhanvien.setDtdmnhanvienNgaygiocn((double)date.getTime());
	}
	
	private void setValueForForm() {
		int rowIndex = -1;
		ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listNhanvien");
		rowIndex = lsDataModel.getRowIndex();
		this.nhanvien = ((List<DtDmNhanVien>)lsDataModel.getWrappedData()).get(rowIndex); 
	}

	public void reset() {
		log.info("Reset nhanvien...");

		resetValue();
	}

	private void resetValue() {
		this.nhanvien = new DtDmNhanVien();
	}
}

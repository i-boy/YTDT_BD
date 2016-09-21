package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienKhoaDelegate;
import com.iesvn.yte.dieutri.delegate.NguoiDungDelegate;
import com.iesvn.yte.dieutri.delegate.NguoiDungVaiTroDelegate;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.DtDmNhanvienKhoa;
import com.iesvn.yte.entity.NguoiDung;
import com.iesvn.yte.entity.NguoiDungVaiTro;
import com.iesvn.yte.util.IConstantsRes;

@Name("B263_NhanVien")
@Scope(SESSION)
public class B263_Nhanvien implements Serializable{

	private static final long serialVersionUID = 1L;

	@In(required = false)
	@Out(required = false)
	private DtDmNhanVien nhanvien;
	
	@DataModel
	private List<DtDmNhanVien> listNhanvien;

	@Logger
	private Log log;

	//--------------------------
	public DtDmNhanVien getNhanvien() {
		return nhanvien;
	}

	public void setNhanvien(DtDmNhanVien nhanvien) {
		this.nhanvien = nhanvien;
	}

	public List<DtDmNhanVien> getListNhanvien() {
		return listNhanvien;
	}

	public void setListNhanvien(List<DtDmNhanVien> listNhanvien) {
		this.listNhanvien = listNhanvien;
	}

	//-----------------------------
	@Create
	public String init() {
		log.info("Init nhanvien...");
		
		resetValue();
		
		return "/B2_Dieutri/B263_Nhanvien";
	}

	public void search() {
		log.info("Search nhanvien...");
		
		this.listNhanvien = DieuTriUtilDelegate.getInstance().findByAllConditions("DtDmNhanVien", "dtdmnhanvienMa", "dtdmnhanvienTen", "dtdmnhanvienChon", this.nhanvien.getDtdmnhanvienMa(), this.nhanvien.getDtdmnhanvienTen(), true);
		
		log.info("list****************:" + this.listNhanvien.size());
		
		if (this.listNhanvien.size() == 0) { 
			FacesMessages.instance().add(IConstantsRes.no_found);
		}
	}

	public void delete(int rowIndex) {
		log.info("Delete nhanvien...");

		if (rowIndex != -1) {
			String ma = "";
			ma = this.listNhanvien.get(rowIndex).getDtdmnhanvienMa();
			
			try {						
				DtDmNhanVien be = this.listNhanvien.get(rowIndex);
				
				be.setDtdmnhanvienChon(false);
				// 20111012 bao.ttc: them ngay gio cap nhat de update gear
				Date date = new Date();
				be.setDtdmnhanvienNgaygiocn((double)date.getTime());
				
				 //20111207 thach.n:xoa nguoi dung va vai tro
				if(be!=null){
					
					// set lai nhan vien ben nhan vien khoa
					DtDmNhanvienKhoa nvkhoa = new DtDmNhanvienKhoa();
					nvkhoa = DtDmNhanVienKhoaDelegate.getInstance().findByMaNguoiDung(be);
					if(nvkhoa!=null){
						Date date1 = new Date();
						nvkhoa.setDtdmnhanvienKhoaChon(false);
						nvkhoa.setDtdmnhanvienKhoaNgaygiocn((double)date1.getTime());
						DtDmNhanVienKhoaDelegate.getInstance().edit(nvkhoa);
					}
					
					if(be.getNdMaso()!= null){
				    NguoiDung nguoidung = be.getNdMaso();
					List<NguoiDungVaiTro> lstvaitro = NguoiDungVaiTroDelegate.getInstance().findByNguoiDung(nguoidung.getNdMaso());
				    
					for (NguoiDungVaiTro nguoiDungVaiTro : lstvaitro) {
					NguoiDungVaiTroDelegate.getInstance().remove(nguoiDungVaiTro);
				    }
					
					be.setNdMaso(null);
					DieuTriUtilDelegate.getInstance().edit(be);
					NguoiDungDelegate.getInstance().remove(nguoidung);
					}else{
						DieuTriUtilDelegate.getInstance().edit(be);
					}
					
					
				
				     this.listNhanvien.remove(rowIndex);
				FacesMessages.instance().add(IConstantsRes.nhanvien_de_su, ma);
				}else{
			    FacesMessages.instance().add(IConstantsRes.nhanvien_de_su, ma);	
				}
			} catch (Exception e) {
				log.error(e.toString());
				FacesMessages.instance().add(IConstantsRes.nhanvien_de_fa, ma);
			}
		}
	}

	public void reset() {
		log.info("Reset nhanvien....");

		resetValue();
	}
	
	public String goMainPage() {
		log.info("Go main page...");
		
		return "/MyMainForm";
	}

	private void resetValue() {
		this.nhanvien = new DtDmNhanVien();
		this.listNhanvien = new ArrayList<DtDmNhanVien>();
	}
}

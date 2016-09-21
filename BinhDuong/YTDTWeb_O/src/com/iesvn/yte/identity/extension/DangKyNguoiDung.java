package com.iesvn.yte.identity.extension;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.faces.FacesMessages;

import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.NguoiDungDelegate;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.entity.NguoiDung;
import com.iesvn.yte.util.IConstantsRes;

@Scope(SESSION)
@Name("Dangkynguoidung")
@Synchronized(timeout = 6000000)
public class DangKyNguoiDung  implements Serializable {
	private static final long serialVersionUID = 10L;
	private static Logger log = Logger.getLogger(DangKyNguoiDung.class);
	
	private String username;
	private Integer nhanVienMaso;
	private String nhanVienMa;
	private String password;
	private String passwordConfirm;
	private String currentUserName;
	
	@Create
	public void init() {
		log.info("-----init()-----");
		//LY THEM CODE============
		dtDmNhanVienDelegate = DtDmNhanVienDelegate.getInstance();
		listDtDmNhanViens = new ArrayList<SelectItem>();
		for(DtDmNhanVien each : dtDmNhanVienDelegate.findAll()){
			listDtDmNhanViens.add(new SelectItem(each.getDtdmnhanvienTen()));
		}
		//END=========================
		reset();
	}
	
	public String reset(){
		nhanVienMaso = null;
		nhanVienMa= "";
		nhanVienTen = "";
		password ="";
		passwordConfirm ="";
		
		return null;
	}
	

	
	public void displayInfor(){
		log.info("-----displayInfor()-----");
		username = nhanVienMa;
		DtDmNhanVienDelegate delegate = DtDmNhanVienDelegate.getInstance();
		DtDmNhanVien nhanvien = null;
		if (nhanVienMaso != null) {
			try {
				nhanvien = delegate.find(nhanVienMaso);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		log.info("-----nhanvien: " + nhanvien);
		NguoiDung nd = null;
		if (nhanvien == null){
			//ko tim thay nhan vien nay,
			reset();
			FacesMessages.instance().add(IConstantsRes.NHAN_VIEN_KHONG_TIM_THAY, username);
		}else{
			nd = nhanvien.getNdMaso();
			if (nd != null) {
				
				username = nd.getNdTendangnhap();
				currentUserName = username;
				password = nd.getNdMadangnhap();
			}
		}
		
	}
	
	public String register(){
		log.info("-----register()-----");
		
        
		DtDmNhanVienDelegate delegate = DtDmNhanVienDelegate.getInstance();
		DtDmNhanVien nhanvien = null;
		if (nhanVienMaso != null) {
			try {
				nhanvien = delegate.find(nhanVienMaso);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		log.info("-----nhanvien: " + nhanvien);
		
		if (nhanvien == null){
			//ko tim thay nhan vien nay,
			log.info("-----nhan vien is null");
			reset();
			FacesMessages.instance().add(IConstantsRes.NHAN_VIEN_KHONG_TIM_THAY, username);
			return null;
		} else {
			log.info("-----nhan vien is not null");
			log.info("-----username: " + username);
			NguoiDungDelegate ndDelegate = NguoiDungDelegate.getInstance();
			
			NguoiDung nd = new NguoiDung();
			nd = ndDelegate.findByMa(username);
			
			
			if (nd == null) {
				log.info("-----nguoi dung is null");
				nd = new NguoiDung();
				nd.setNdTendangnhap(username.toUpperCase()); // 20111208 bao.ttc: UPPER de dong bo username o cac table
				nd.setNdMadangnhap(password);
				ndDelegate.createNguoiDung(nd, nhanvien);
				reset();
				FacesMessages.instance().add(IConstantsRes.REGISTER_SUCCESS);
			} else {
				log.info("-----nguoi dung null");
				nd.setNdTendangnhap(username.toUpperCase());  // 20111208 bao.ttc: UPPER de dong bo username o cac table
				nd.setNdMadangnhap(password);
				ndDelegate.edit(nd);
				reset();
				FacesMessages.instance().add(IConstantsRes.REGISTER_SUCCESS);
				
			}
		}
		
		return null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getNhanVienMaso() {
		return nhanVienMaso;
	}

	public void setNhanVienMaso(Integer nhanVienMaso) {
		this.nhanVienMaso = nhanVienMaso;
	}

	public String getNhanVienMa() {
		return nhanVienMa;
	}

	public void setNhanVienMa(String nhanVienMa) {
		this.nhanVienMa = nhanVienMa;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getCurrentUserName() {
		return currentUserName;
	}

	public void setCurrentUserName(String currentUserName) {
		this.currentUserName = currentUserName;
	}

	//LY THEM CODE======================
	private String nhanVienTen;
	private DtDmNhanVienDelegate dtDmNhanVienDelegate;
	private List<SelectItem> listDtDmNhanViens = new ArrayList<SelectItem>();
	
	public List<SelectItem> getListDtDmNhanViens() {
		return listDtDmNhanViens;
	}

	public void setListDtDmNhanViens(List<SelectItem> listDtDmNhanViens) {
		this.listDtDmNhanViens = listDtDmNhanViens;
	}
	public String getNhanVienTen() {
		return nhanVienTen;
	}

	public void setNhanVienTen(String nhanVienTen) {
		this.nhanVienTen = nhanVienTen;
	}
	
	public void onblurTenNhanVienAction(){
		log.info("-----BEGIN onblurTenNhanVienAction()-----");
		DtDmNhanVien nhanvien = dtDmNhanVienDelegate.findByTenNhanVien(nhanVienTen);
		//Tùy logic form mà set cho đúng
		if(nhanvien != null) {
			nhanVienMaso = nhanvien.getDtdmnhanvienMaso();
			nhanVienMa = nhanvien.getDtdmnhanvienMa();
			log.info("-----DA THAY DTDMNHANVIEN-----");
		}
	}
	
	public void onblurMaNhanVienAction(){
		log.info("-----BEGIN onblurMaNhanVienAction()-----");
		DtDmNhanVien nhanvien = dtDmNhanVienDelegate.findByMaNhanVien(nhanVienMa);
		//Tùy logic form mà set cho đúng
		if(nhanvien != null) {
			nhanVienMaso = nhanvien.getDtdmnhanvienMaso();
			nhanVienTen = nhanvien.getDtdmnhanvienTen();
			log.info("-----DA THAY DTDMNHANVIEN-----");
		}
	}
}
